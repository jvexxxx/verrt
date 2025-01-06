/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.file.DirectoryStream;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.PosixFileAttributes;
/*     */ import java.nio.file.attribute.PosixFilePermission;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;
/*     */ import nonapi.io.github.classgraph.concurrency.WorkQueue;
/*     */ import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
/*     */ import nonapi.io.github.classgraph.fileslice.PathSlice;
/*     */ import nonapi.io.github.classgraph.fileslice.Slice;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.ClassfileReader;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ import nonapi.io.github.classgraph.utils.FastPathResolver;
/*     */ import nonapi.io.github.classgraph.utils.FileUtils;
/*     */ import nonapi.io.github.classgraph.utils.LogNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ClasspathElementDir
/*     */   extends ClasspathElement
/*     */ {
/*     */   private final Path classpathEltPath;
/*  70 */   private final Set<Path> scannedCanonicalPaths = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final NestedJarHandler nestedJarHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ClasspathElementDir(Scanner.ClasspathEntryWorkUnit workUnit, NestedJarHandler nestedJarHandler, ScanSpec scanSpec) {
/*  87 */     super(workUnit, scanSpec);
/*  88 */     this.classpathEltPath = (Path)workUnit.classpathEntryObj;
/*  89 */     this.nestedJarHandler = nestedJarHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void open(WorkQueue<Scanner.ClasspathEntryWorkUnit> workQueue, LogNode log) {
/*  98 */     if (!this.scanSpec.scanDirs) {
/*  99 */       if (log != null) {
/* 100 */         log(this.classpathElementIdx, "Skipping classpath element, since dir scanning is disabled: " + this.classpathEltPath, log);
/*     */       }
/*     */       
/* 103 */       this.skipClasspathElement = true;
/*     */       
/*     */       return;
/*     */     } 
/*     */     try {
/* 108 */       int childClasspathEntryIdx = 0;
/* 109 */       for (String libDirPrefix : ClassLoaderHandlerRegistry.AUTOMATIC_LIB_DIR_PREFIXES) {
/* 110 */         Path libDirPath = this.classpathEltPath.resolve(libDirPrefix);
/* 111 */         if (FileUtils.canReadAndIsDir(libDirPath)) {
/*     */           
/* 113 */           try { DirectoryStream<Path> stream = Files.newDirectoryStream(libDirPath); 
/* 114 */             try { for (Path filePath : stream) {
/* 115 */                 if (Files.isRegularFile(filePath, new java.nio.file.LinkOption[0]) && filePath.getFileName().endsWith(".jar")) {
/* 116 */                   if (log != null) {
/* 117 */                     log(this.classpathElementIdx, "Found lib jar: " + filePath, log);
/*     */                   }
/* 119 */                   workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(filePath, getClassLoader(), this, childClasspathEntryIdx++, ""));
/*     */                 } 
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/* 125 */               if (stream != null) stream.close();  } catch (Throwable throwable) { if (stream != null) try { stream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException iOException) {}
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 131 */       if (this.packageRootPrefix.isEmpty()) {
/* 132 */         for (String packageRootPrefix : ClassLoaderHandlerRegistry.AUTOMATIC_PACKAGE_ROOT_PREFIXES) {
/* 133 */           Path packageRoot = this.classpathEltPath.resolve(packageRootPrefix);
/* 134 */           if (FileUtils.canReadAndIsDir(packageRoot)) {
/* 135 */             if (log != null) {
/* 136 */               log(this.classpathElementIdx, "Found package root: " + packageRootPrefix, log);
/*     */             }
/* 138 */             workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(packageRoot, getClassLoader(), this, childClasspathEntryIdx++, packageRootPrefix));
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     }
/* 145 */     catch (SecurityException e) {
/* 146 */       if (log != null) {
/* 147 */         log(this.classpathElementIdx, "Skipping classpath element, since dir cannot be accessed: " + this.classpathEltPath, log);
/*     */       }
/*     */       
/* 150 */       this.skipClasspathElement = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Resource newResource(final Path resourcePath, final NestedJarHandler nestedJarHandler) {
/*     */     long length;
/*     */     try {
/* 166 */       length = Files.size(resourcePath);
/* 167 */     } catch (IOException|SecurityException e) {
/* 168 */       length = -1L;
/*     */     } 
/* 170 */     return new Resource(this, length)
/*     */       {
/*     */         private PathSlice pathSlice;
/*     */ 
/*     */         
/* 175 */         private final AtomicBoolean isOpen = new AtomicBoolean();
/*     */ 
/*     */         
/*     */         public String getPath() {
/* 179 */           String path = FastPathResolver.resolve(ClasspathElementDir.this.classpathEltPath.relativize(resourcePath).toString());
/* 180 */           while (path.startsWith("/")) {
/* 181 */             path = path.substring(1);
/*     */           }
/* 183 */           return path;
/*     */         }
/*     */ 
/*     */         
/*     */         public String getPathRelativeToClasspathElement() {
/* 188 */           return ClasspathElementDir.this.packageRootPrefix.isEmpty() ? getPath() : (ClasspathElementDir.this.packageRootPrefix + getPath());
/*     */         }
/*     */ 
/*     */         
/*     */         public long getLastModified() {
/*     */           try {
/* 194 */             return resourcePath.toFile().lastModified();
/* 195 */           } catch (UnsupportedOperationException e) {
/* 196 */             return 0L;
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public Set<PosixFilePermission> getPosixFilePermissions() {
/* 203 */           Set<PosixFilePermission> posixFilePermissions = null;
/*     */           
/*     */           try {
/* 206 */             posixFilePermissions = ((PosixFileAttributes)Files.<PosixFileAttributes>readAttributes(resourcePath, PosixFileAttributes.class, new java.nio.file.LinkOption[0])).permissions();
/* 207 */           } catch (UnsupportedOperationException|IOException|SecurityException unsupportedOperationException) {}
/*     */ 
/*     */           
/* 210 */           return posixFilePermissions;
/*     */         }
/*     */ 
/*     */         
/*     */         public ByteBuffer read() throws IOException {
/* 215 */           if (ClasspathElementDir.this.skipClasspathElement)
/*     */           {
/* 217 */             throw new IOException("Parent directory could not be opened");
/*     */           }
/* 219 */           if (this.isOpen.getAndSet(true)) {
/* 220 */             throw new IOException("Resource is already open -- cannot open it again without first calling close()");
/*     */           }
/*     */           
/* 223 */           this.pathSlice = new PathSlice(resourcePath, nestedJarHandler);
/* 224 */           this.length = this.pathSlice.sliceLength;
/* 225 */           this.byteBuffer = this.pathSlice.read();
/* 226 */           return this.byteBuffer;
/*     */         }
/*     */ 
/*     */         
/*     */         ClassfileReader openClassfile() throws IOException {
/* 231 */           if (ClasspathElementDir.this.skipClasspathElement)
/*     */           {
/* 233 */             throw new IOException("Parent directory could not be opened");
/*     */           }
/* 235 */           if (this.isOpen.getAndSet(true)) {
/* 236 */             throw new IOException("Resource is already open -- cannot open it again without first calling close()");
/*     */           }
/*     */ 
/*     */           
/* 240 */           this.pathSlice = new PathSlice(resourcePath, nestedJarHandler);
/* 241 */           this.length = this.pathSlice.sliceLength;
/* 242 */           return new ClassfileReader((Slice)this.pathSlice, this);
/*     */         }
/*     */ 
/*     */         
/*     */         public InputStream open() throws IOException {
/* 247 */           if (ClasspathElementDir.this.skipClasspathElement)
/*     */           {
/* 249 */             throw new IOException("Parent directory could not be opened");
/*     */           }
/* 251 */           if (this.isOpen.getAndSet(true)) {
/* 252 */             throw new IOException("Resource is already open -- cannot open it again without first calling close()");
/*     */           }
/*     */           
/* 255 */           this.pathSlice = new PathSlice(resourcePath, nestedJarHandler);
/* 256 */           this.inputStream = this.pathSlice.open(this);
/* 257 */           this.length = this.pathSlice.sliceLength;
/* 258 */           return this.inputStream;
/*     */         }
/*     */ 
/*     */         
/*     */         public byte[] load() throws IOException {
/* 263 */           read();
/* 264 */           Resource res = this; 
/* 265 */           try { this.pathSlice = new PathSlice(resourcePath, nestedJarHandler);
/* 266 */             byte[] bytes = this.pathSlice.load();
/* 267 */             res.length = bytes.length;
/* 268 */             byte[] arrayOfByte1 = bytes;
/* 269 */             if (res != null) res.close();  return arrayOfByte1; }
/*     */           catch (Throwable throwable) { if (res != null)
/*     */               try { res.close(); }
/*     */               catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */                 throw throwable; }
/* 274 */            } public void close() { if (this.isOpen.getAndSet(false)) {
/* 275 */             if (this.byteBuffer != null)
/*     */             {
/* 277 */               this.byteBuffer = null;
/*     */             }
/* 279 */             if (this.pathSlice != null) {
/* 280 */               this.pathSlice.close();
/* 281 */               nestedJarHandler.markSliceAsClosed((Slice)this.pathSlice);
/* 282 */               this.pathSlice = null;
/*     */             } 
/*     */ 
/*     */             
/* 286 */             super.close();
/*     */           }  }
/*     */       
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Resource getResource(String relativePath) {
/* 302 */     Path resourcePath = this.classpathEltPath.resolve(relativePath);
/* 303 */     return FileUtils.canReadAndIsFile(resourcePath) ? newResource(resourcePath, this.nestedJarHandler) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void scanPathRecursively(Path path, LogNode log) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: iconst_0
/*     */     //   2: anewarray java/nio/file/LinkOption
/*     */     //   5: invokeinterface toRealPath : ([Ljava/nio/file/LinkOption;)Ljava/nio/file/Path;
/*     */     //   10: astore_3
/*     */     //   11: aload_0
/*     */     //   12: getfield scannedCanonicalPaths : Ljava/util/Set;
/*     */     //   15: aload_3
/*     */     //   16: invokeinterface add : (Ljava/lang/Object;)Z
/*     */     //   21: ifne -> 53
/*     */     //   24: aload_2
/*     */     //   25: ifnull -> 52
/*     */     //   28: aload_2
/*     */     //   29: new java/lang/StringBuilder
/*     */     //   32: dup
/*     */     //   33: invokespecial <init> : ()V
/*     */     //   36: ldc 'Reached symlink cycle, stopping recursion: '
/*     */     //   38: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   41: aload_1
/*     */     //   42: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   45: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   48: invokevirtual log : (Ljava/lang/String;)Lnonapi/io/github/classgraph/utils/LogNode;
/*     */     //   51: pop
/*     */     //   52: return
/*     */     //   53: goto -> 89
/*     */     //   56: astore #4
/*     */     //   58: aload_2
/*     */     //   59: ifnull -> 88
/*     */     //   62: aload_2
/*     */     //   63: new java/lang/StringBuilder
/*     */     //   66: dup
/*     */     //   67: invokespecial <init> : ()V
/*     */     //   70: ldc 'Could not canonicalize path: '
/*     */     //   72: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   75: aload_1
/*     */     //   76: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   79: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   82: aload #4
/*     */     //   84: invokevirtual log : (Ljava/lang/String;Ljava/lang/Throwable;)Lnonapi/io/github/classgraph/utils/LogNode;
/*     */     //   87: pop
/*     */     //   88: return
/*     */     //   89: aload_0
/*     */     //   90: getfield classpathEltPath : Ljava/nio/file/Path;
/*     */     //   93: aload_1
/*     */     //   94: invokeinterface relativize : (Ljava/nio/file/Path;)Ljava/nio/file/Path;
/*     */     //   99: invokeinterface toString : ()Ljava/lang/String;
/*     */     //   104: invokestatic resolve : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   107: astore #4
/*     */     //   109: aload #4
/*     */     //   111: ldc_w '/'
/*     */     //   114: invokevirtual startsWith : (Ljava/lang/String;)Z
/*     */     //   117: ifeq -> 131
/*     */     //   120: aload #4
/*     */     //   122: iconst_1
/*     */     //   123: invokevirtual substring : (I)Ljava/lang/String;
/*     */     //   126: astore #4
/*     */     //   128: goto -> 109
/*     */     //   131: aload #4
/*     */     //   133: ldc_w '/'
/*     */     //   136: invokevirtual endsWith : (Ljava/lang/String;)Z
/*     */     //   139: ifne -> 165
/*     */     //   142: new java/lang/StringBuilder
/*     */     //   145: dup
/*     */     //   146: invokespecial <init> : ()V
/*     */     //   149: aload #4
/*     */     //   151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   154: ldc_w '/'
/*     */     //   157: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   160: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   163: astore #4
/*     */     //   165: aload #4
/*     */     //   167: ldc_w '/'
/*     */     //   170: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   173: istore #5
/*     */     //   175: aload_0
/*     */     //   176: getfield nestedClasspathRootPrefixes : Ljava/util/List;
/*     */     //   179: ifnull -> 227
/*     */     //   182: aload_0
/*     */     //   183: getfield nestedClasspathRootPrefixes : Ljava/util/List;
/*     */     //   186: aload #4
/*     */     //   188: invokeinterface contains : (Ljava/lang/Object;)Z
/*     */     //   193: ifeq -> 227
/*     */     //   196: aload_2
/*     */     //   197: ifnull -> 226
/*     */     //   200: aload_2
/*     */     //   201: new java/lang/StringBuilder
/*     */     //   204: dup
/*     */     //   205: invokespecial <init> : ()V
/*     */     //   208: ldc_w 'Reached nested classpath root, stopping recursion to avoid duplicate scanning: '
/*     */     //   211: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   214: aload #4
/*     */     //   216: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   219: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   222: invokevirtual log : (Ljava/lang/String;)Lnonapi/io/github/classgraph/utils/LogNode;
/*     */     //   225: pop
/*     */     //   226: return
/*     */     //   227: aload_0
/*     */     //   228: getfield scanSpec : Lnonapi/io/github/classgraph/scanspec/ScanSpec;
/*     */     //   231: getfield enableMultiReleaseVersions : Z
/*     */     //   234: ifne -> 279
/*     */     //   237: aload #4
/*     */     //   239: ldc_w 'META-INF/versions/'
/*     */     //   242: invokevirtual startsWith : (Ljava/lang/String;)Z
/*     */     //   245: ifeq -> 279
/*     */     //   248: aload_2
/*     */     //   249: ifnull -> 278
/*     */     //   252: aload_2
/*     */     //   253: new java/lang/StringBuilder
/*     */     //   256: dup
/*     */     //   257: invokespecial <init> : ()V
/*     */     //   260: ldc_w 'Found unexpected nested versioned entry in directory classpath element -- skipping: '
/*     */     //   263: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   266: aload #4
/*     */     //   268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   271: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   274: invokevirtual log : (Ljava/lang/String;)Lnonapi/io/github/classgraph/utils/LogNode;
/*     */     //   277: pop
/*     */     //   278: return
/*     */     //   279: aload_0
/*     */     //   280: aload #4
/*     */     //   282: aload_2
/*     */     //   283: invokevirtual checkResourcePathAcceptReject : (Ljava/lang/String;Lnonapi/io/github/classgraph/utils/LogNode;)Z
/*     */     //   286: ifne -> 290
/*     */     //   289: return
/*     */     //   290: aload_0
/*     */     //   291: getfield scanSpec : Lnonapi/io/github/classgraph/scanspec/ScanSpec;
/*     */     //   294: aload #4
/*     */     //   296: invokevirtual dirAcceptMatchStatus : (Ljava/lang/String;)Lnonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch;
/*     */     //   299: astore #6
/*     */     //   301: aload #6
/*     */     //   303: getstatic nonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch.HAS_REJECTED_PATH_PREFIX : Lnonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch;
/*     */     //   306: if_acmpne -> 340
/*     */     //   309: aload_2
/*     */     //   310: ifnull -> 339
/*     */     //   313: aload_2
/*     */     //   314: new java/lang/StringBuilder
/*     */     //   317: dup
/*     */     //   318: invokespecial <init> : ()V
/*     */     //   321: ldc_w 'Reached rejected directory, stopping recursive scan: '
/*     */     //   324: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   327: aload #4
/*     */     //   329: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   332: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   335: invokevirtual log : (Ljava/lang/String;)Lnonapi/io/github/classgraph/utils/LogNode;
/*     */     //   338: pop
/*     */     //   339: return
/*     */     //   340: aload #6
/*     */     //   342: getstatic nonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch.NOT_WITHIN_ACCEPTED_PATH : Lnonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch;
/*     */     //   345: if_acmpne -> 349
/*     */     //   348: return
/*     */     //   349: aload_2
/*     */     //   350: ifnonnull -> 357
/*     */     //   353: aconst_null
/*     */     //   354: goto -> 455
/*     */     //   357: aload_2
/*     */     //   358: new java/lang/StringBuilder
/*     */     //   361: dup
/*     */     //   362: invokespecial <init> : ()V
/*     */     //   365: ldc_w '1:'
/*     */     //   368: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   371: aload_3
/*     */     //   372: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   375: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   378: new java/lang/StringBuilder
/*     */     //   381: dup
/*     */     //   382: invokespecial <init> : ()V
/*     */     //   385: ldc_w 'Scanning Path: '
/*     */     //   388: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   391: aload_1
/*     */     //   392: invokeinterface toString : ()Ljava/lang/String;
/*     */     //   397: invokestatic resolve : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   400: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   403: aload_1
/*     */     //   404: aload_3
/*     */     //   405: invokeinterface equals : (Ljava/lang/Object;)Z
/*     */     //   410: ifeq -> 418
/*     */     //   413: ldc ''
/*     */     //   415: goto -> 446
/*     */     //   418: new java/lang/StringBuilder
/*     */     //   421: dup
/*     */     //   422: invokespecial <init> : ()V
/*     */     //   425: ldc_w ' ; canonical path: '
/*     */     //   428: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   431: aload_3
/*     */     //   432: invokeinterface toString : ()Ljava/lang/String;
/*     */     //   437: invokestatic resolve : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   440: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   443: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   446: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   449: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   452: invokevirtual log : (Ljava/lang/String;Ljava/lang/String;)Lnonapi/io/github/classgraph/utils/LogNode;
/*     */     //   455: astore #7
/*     */     //   457: new java/util/ArrayList
/*     */     //   460: dup
/*     */     //   461: invokespecial <init> : ()V
/*     */     //   464: astore #8
/*     */     //   466: aload_1
/*     */     //   467: invokestatic newDirectoryStream : (Ljava/nio/file/Path;)Ljava/nio/file/DirectoryStream;
/*     */     //   470: astore #9
/*     */     //   472: aload #9
/*     */     //   474: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   479: astore #10
/*     */     //   481: aload #10
/*     */     //   483: invokeinterface hasNext : ()Z
/*     */     //   488: ifeq -> 516
/*     */     //   491: aload #10
/*     */     //   493: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   498: checkcast java/nio/file/Path
/*     */     //   501: astore #11
/*     */     //   503: aload #8
/*     */     //   505: aload #11
/*     */     //   507: invokeinterface add : (Ljava/lang/Object;)Z
/*     */     //   512: pop
/*     */     //   513: goto -> 481
/*     */     //   516: aload #9
/*     */     //   518: ifnull -> 560
/*     */     //   521: aload #9
/*     */     //   523: invokeinterface close : ()V
/*     */     //   528: goto -> 560
/*     */     //   531: astore #10
/*     */     //   533: aload #9
/*     */     //   535: ifnull -> 557
/*     */     //   538: aload #9
/*     */     //   540: invokeinterface close : ()V
/*     */     //   545: goto -> 557
/*     */     //   548: astore #11
/*     */     //   550: aload #10
/*     */     //   552: aload #11
/*     */     //   554: invokevirtual addSuppressed : (Ljava/lang/Throwable;)V
/*     */     //   557: aload #10
/*     */     //   559: athrow
/*     */     //   560: goto -> 609
/*     */     //   563: astore #9
/*     */     //   565: aload_2
/*     */     //   566: ifnull -> 608
/*     */     //   569: aload_2
/*     */     //   570: new java/lang/StringBuilder
/*     */     //   573: dup
/*     */     //   574: invokespecial <init> : ()V
/*     */     //   577: ldc_w 'Could not read directory '
/*     */     //   580: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   583: aload_1
/*     */     //   584: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   587: ldc_w ' : '
/*     */     //   590: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   593: aload #9
/*     */     //   595: invokevirtual getMessage : ()Ljava/lang/String;
/*     */     //   598: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   601: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   604: invokevirtual log : (Ljava/lang/String;)Lnonapi/io/github/classgraph/utils/LogNode;
/*     */     //   607: pop
/*     */     //   608: return
/*     */     //   609: aload #8
/*     */     //   611: invokestatic sort : (Ljava/util/List;)V
/*     */     //   614: getstatic nonapi/io/github/classgraph/utils/VersionFinder.JAVA_MAJOR_VERSION : I
/*     */     //   617: bipush #9
/*     */     //   619: if_icmplt -> 633
/*     */     //   622: aload_0
/*     */     //   623: invokevirtual getModuleName : ()Ljava/lang/String;
/*     */     //   626: ifnull -> 633
/*     */     //   629: iconst_1
/*     */     //   630: goto -> 634
/*     */     //   633: iconst_0
/*     */     //   634: istore #9
/*     */     //   636: aload #6
/*     */     //   638: getstatic nonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch.ANCESTOR_OF_ACCEPTED_PATH : Lnonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch;
/*     */     //   641: if_acmpeq -> 894
/*     */     //   644: aload #8
/*     */     //   646: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   651: astore #10
/*     */     //   653: aload #10
/*     */     //   655: invokeinterface hasNext : ()Z
/*     */     //   660: ifeq -> 891
/*     */     //   663: aload #10
/*     */     //   665: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   670: checkcast java/nio/file/Path
/*     */     //   673: astore #11
/*     */     //   675: aload #11
/*     */     //   677: iconst_0
/*     */     //   678: anewarray java/nio/file/LinkOption
/*     */     //   681: invokestatic isRegularFile : (Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z
/*     */     //   684: ifeq -> 888
/*     */     //   687: aload_0
/*     */     //   688: getfield classpathEltPath : Ljava/nio/file/Path;
/*     */     //   691: aload #11
/*     */     //   693: invokeinterface relativize : (Ljava/nio/file/Path;)Ljava/nio/file/Path;
/*     */     //   698: astore #12
/*     */     //   700: aload #12
/*     */     //   702: invokeinterface toString : ()Ljava/lang/String;
/*     */     //   707: invokestatic resolve : (Ljava/lang/String;)Ljava/lang/String;
/*     */     //   710: astore #13
/*     */     //   712: iload #9
/*     */     //   714: ifeq -> 747
/*     */     //   717: iload #5
/*     */     //   719: ifeq -> 747
/*     */     //   722: aload #13
/*     */     //   724: ldc_w '.class'
/*     */     //   727: invokevirtual endsWith : (Ljava/lang/String;)Z
/*     */     //   730: ifeq -> 747
/*     */     //   733: aload #13
/*     */     //   735: ldc_w 'module-info.class'
/*     */     //   738: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   741: ifne -> 747
/*     */     //   744: goto -> 653
/*     */     //   747: aload_0
/*     */     //   748: aload #13
/*     */     //   750: aload #7
/*     */     //   752: invokevirtual checkResourcePathAcceptReject : (Ljava/lang/String;Lnonapi/io/github/classgraph/utils/LogNode;)Z
/*     */     //   755: ifne -> 759
/*     */     //   758: return
/*     */     //   759: aload #6
/*     */     //   761: getstatic nonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX : Lnonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch;
/*     */     //   764: if_acmpeq -> 795
/*     */     //   767: aload #6
/*     */     //   769: getstatic nonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch.AT_ACCEPTED_PATH : Lnonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch;
/*     */     //   772: if_acmpeq -> 795
/*     */     //   775: aload #6
/*     */     //   777: getstatic nonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch.AT_ACCEPTED_CLASS_PACKAGE : Lnonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch;
/*     */     //   780: if_acmpne -> 856
/*     */     //   783: aload_0
/*     */     //   784: getfield scanSpec : Lnonapi/io/github/classgraph/scanspec/ScanSpec;
/*     */     //   787: aload #13
/*     */     //   789: invokevirtual classfileIsSpecificallyAccepted : (Ljava/lang/String;)Z
/*     */     //   792: ifeq -> 856
/*     */     //   795: aload_0
/*     */     //   796: aload #11
/*     */     //   798: aload_0
/*     */     //   799: getfield nestedJarHandler : Lnonapi/io/github/classgraph/fastzipfilereader/NestedJarHandler;
/*     */     //   802: invokespecial newResource : (Ljava/nio/file/Path;Lnonapi/io/github/classgraph/fastzipfilereader/NestedJarHandler;)Lio/github/classgraph/Resource;
/*     */     //   805: astore #14
/*     */     //   807: aload_0
/*     */     //   808: aload #14
/*     */     //   810: aload #6
/*     */     //   812: iconst_0
/*     */     //   813: aload #7
/*     */     //   815: invokevirtual addAcceptedResource : (Lio/github/classgraph/Resource;Lnonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch;ZLnonapi/io/github/classgraph/utils/LogNode;)V
/*     */     //   818: aload_0
/*     */     //   819: getfield fileToLastModified : Ljava/util/Map;
/*     */     //   822: aload #11
/*     */     //   824: invokeinterface toFile : ()Ljava/io/File;
/*     */     //   829: aload #11
/*     */     //   831: invokeinterface toFile : ()Ljava/io/File;
/*     */     //   836: invokevirtual lastModified : ()J
/*     */     //   839: invokestatic valueOf : (J)Ljava/lang/Long;
/*     */     //   842: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   847: pop
/*     */     //   848: goto -> 853
/*     */     //   851: astore #15
/*     */     //   853: goto -> 888
/*     */     //   856: aload #7
/*     */     //   858: ifnull -> 888
/*     */     //   861: aload #7
/*     */     //   863: new java/lang/StringBuilder
/*     */     //   866: dup
/*     */     //   867: invokespecial <init> : ()V
/*     */     //   870: ldc_w 'Skipping non-accepted file: '
/*     */     //   873: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   876: aload #12
/*     */     //   878: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   881: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   884: invokevirtual log : (Ljava/lang/String;)Lnonapi/io/github/classgraph/utils/LogNode;
/*     */     //   887: pop
/*     */     //   888: goto -> 653
/*     */     //   891: goto -> 1043
/*     */     //   894: aload_0
/*     */     //   895: getfield scanSpec : Lnonapi/io/github/classgraph/scanspec/ScanSpec;
/*     */     //   898: getfield enableClassInfo : Z
/*     */     //   901: ifeq -> 1043
/*     */     //   904: aload #4
/*     */     //   906: ldc_w '/'
/*     */     //   909: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   912: ifeq -> 1043
/*     */     //   915: aload #8
/*     */     //   917: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   922: astore #10
/*     */     //   924: aload #10
/*     */     //   926: invokeinterface hasNext : ()Z
/*     */     //   931: ifeq -> 1043
/*     */     //   934: aload #10
/*     */     //   936: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   941: checkcast java/nio/file/Path
/*     */     //   944: astore #11
/*     */     //   946: aload #11
/*     */     //   948: invokeinterface getFileName : ()Ljava/nio/file/Path;
/*     */     //   953: invokeinterface toString : ()Ljava/lang/String;
/*     */     //   958: ldc_w 'module-info.class'
/*     */     //   961: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   964: ifeq -> 1040
/*     */     //   967: aload #11
/*     */     //   969: iconst_0
/*     */     //   970: anewarray java/nio/file/LinkOption
/*     */     //   973: invokestatic isRegularFile : (Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z
/*     */     //   976: ifeq -> 1040
/*     */     //   979: aload_0
/*     */     //   980: aload #11
/*     */     //   982: aload_0
/*     */     //   983: getfield nestedJarHandler : Lnonapi/io/github/classgraph/fastzipfilereader/NestedJarHandler;
/*     */     //   986: invokespecial newResource : (Ljava/nio/file/Path;Lnonapi/io/github/classgraph/fastzipfilereader/NestedJarHandler;)Lio/github/classgraph/Resource;
/*     */     //   989: astore #12
/*     */     //   991: aload_0
/*     */     //   992: aload #12
/*     */     //   994: aload #6
/*     */     //   996: iconst_1
/*     */     //   997: aload #7
/*     */     //   999: invokevirtual addAcceptedResource : (Lio/github/classgraph/Resource;Lnonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch;ZLnonapi/io/github/classgraph/utils/LogNode;)V
/*     */     //   1002: aload_0
/*     */     //   1003: getfield fileToLastModified : Ljava/util/Map;
/*     */     //   1006: aload #11
/*     */     //   1008: invokeinterface toFile : ()Ljava/io/File;
/*     */     //   1013: aload #11
/*     */     //   1015: invokeinterface toFile : ()Ljava/io/File;
/*     */     //   1020: invokevirtual lastModified : ()J
/*     */     //   1023: invokestatic valueOf : (J)Ljava/lang/Long;
/*     */     //   1026: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   1031: pop
/*     */     //   1032: goto -> 1043
/*     */     //   1035: astore #13
/*     */     //   1037: goto -> 1043
/*     */     //   1040: goto -> 924
/*     */     //   1043: aload #8
/*     */     //   1045: invokeinterface iterator : ()Ljava/util/Iterator;
/*     */     //   1050: astore #10
/*     */     //   1052: aload #10
/*     */     //   1054: invokeinterface hasNext : ()Z
/*     */     //   1059: ifeq -> 1148
/*     */     //   1062: aload #10
/*     */     //   1064: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   1069: checkcast java/nio/file/Path
/*     */     //   1072: astore #11
/*     */     //   1074: aload #11
/*     */     //   1076: iconst_0
/*     */     //   1077: anewarray java/nio/file/LinkOption
/*     */     //   1080: invokestatic isDirectory : (Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z
/*     */     //   1083: ifeq -> 1094
/*     */     //   1086: aload_0
/*     */     //   1087: aload #11
/*     */     //   1089: aload #7
/*     */     //   1091: invokespecial scanPathRecursively : (Ljava/nio/file/Path;Lnonapi/io/github/classgraph/utils/LogNode;)V
/*     */     //   1094: goto -> 1145
/*     */     //   1097: astore #12
/*     */     //   1099: aload #7
/*     */     //   1101: ifnull -> 1145
/*     */     //   1104: aload #7
/*     */     //   1106: new java/lang/StringBuilder
/*     */     //   1109: dup
/*     */     //   1110: invokespecial <init> : ()V
/*     */     //   1113: ldc_w 'Could not read sub-directory '
/*     */     //   1116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1119: aload #11
/*     */     //   1121: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
/*     */     //   1124: ldc_w ' : '
/*     */     //   1127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1130: aload #12
/*     */     //   1132: invokevirtual getMessage : ()Ljava/lang/String;
/*     */     //   1135: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   1138: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   1141: invokevirtual log : (Ljava/lang/String;)Lnonapi/io/github/classgraph/utils/LogNode;
/*     */     //   1144: pop
/*     */     //   1145: goto -> 1052
/*     */     //   1148: aload #7
/*     */     //   1150: ifnull -> 1158
/*     */     //   1153: aload #7
/*     */     //   1155: invokevirtual addElapsedTime : ()V
/*     */     //   1158: aload_1
/*     */     //   1159: invokeinterface toFile : ()Ljava/io/File;
/*     */     //   1164: astore #10
/*     */     //   1166: aload_0
/*     */     //   1167: getfield fileToLastModified : Ljava/util/Map;
/*     */     //   1170: aload #10
/*     */     //   1172: aload #10
/*     */     //   1174: invokevirtual lastModified : ()J
/*     */     //   1177: invokestatic valueOf : (J)Ljava/lang/Long;
/*     */     //   1180: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   1185: pop
/*     */     //   1186: goto -> 1191
/*     */     //   1189: astore #10
/*     */     //   1191: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #319	-> 0
/*     */     //   #320	-> 11
/*     */     //   #321	-> 24
/*     */     //   #322	-> 28
/*     */     //   #324	-> 52
/*     */     //   #331	-> 53
/*     */     //   #326	-> 56
/*     */     //   #327	-> 58
/*     */     //   #328	-> 62
/*     */     //   #330	-> 88
/*     */     //   #333	-> 89
/*     */     //   #334	-> 109
/*     */     //   #335	-> 120
/*     */     //   #337	-> 131
/*     */     //   #338	-> 142
/*     */     //   #340	-> 165
/*     */     //   #342	-> 175
/*     */     //   #343	-> 196
/*     */     //   #344	-> 200
/*     */     //   #347	-> 226
/*     */     //   #353	-> 227
/*     */     //   #354	-> 242
/*     */     //   #355	-> 248
/*     */     //   #356	-> 252
/*     */     //   #359	-> 278
/*     */     //   #363	-> 279
/*     */     //   #364	-> 289
/*     */     //   #367	-> 290
/*     */     //   #368	-> 301
/*     */     //   #370	-> 309
/*     */     //   #371	-> 313
/*     */     //   #373	-> 339
/*     */     //   #375	-> 340
/*     */     //   #377	-> 348
/*     */     //   #380	-> 349
/*     */     //   #382	-> 357
/*     */     //   #383	-> 392
/*     */     //   #384	-> 413
/*     */     //   #385	-> 418
/*     */     //   #382	-> 452
/*     */     //   #387	-> 457
/*     */     //   #388	-> 466
/*     */     //   #389	-> 472
/*     */     //   #390	-> 503
/*     */     //   #391	-> 513
/*     */     //   #392	-> 516
/*     */     //   #388	-> 531
/*     */     //   #397	-> 560
/*     */     //   #392	-> 563
/*     */     //   #393	-> 565
/*     */     //   #394	-> 569
/*     */     //   #396	-> 608
/*     */     //   #398	-> 609
/*     */     //   #401	-> 614
/*     */     //   #404	-> 636
/*     */     //   #406	-> 644
/*     */     //   #408	-> 675
/*     */     //   #409	-> 687
/*     */     //   #410	-> 700
/*     */     //   #413	-> 712
/*     */     //   #414	-> 738
/*     */     //   #415	-> 744
/*     */     //   #419	-> 747
/*     */     //   #420	-> 758
/*     */     //   #424	-> 759
/*     */     //   #427	-> 789
/*     */     //   #429	-> 795
/*     */     //   #430	-> 807
/*     */     //   #434	-> 818
/*     */     //   #437	-> 848
/*     */     //   #435	-> 851
/*     */     //   #438	-> 853
/*     */     //   #439	-> 856
/*     */     //   #440	-> 861
/*     */     //   #444	-> 888
/*     */     //   #445	-> 894
/*     */     //   #447	-> 915
/*     */     //   #448	-> 946
/*     */     //   #449	-> 979
/*     */     //   #450	-> 991
/*     */     //   #452	-> 1002
/*     */     //   #455	-> 1032
/*     */     //   #453	-> 1035
/*     */     //   #456	-> 1037
/*     */     //   #458	-> 1040
/*     */     //   #461	-> 1043
/*     */     //   #463	-> 1074
/*     */     //   #464	-> 1086
/*     */     //   #470	-> 1094
/*     */     //   #466	-> 1097
/*     */     //   #467	-> 1099
/*     */     //   #468	-> 1104
/*     */     //   #471	-> 1145
/*     */     //   #473	-> 1148
/*     */     //   #474	-> 1153
/*     */     //   #479	-> 1158
/*     */     //   #480	-> 1166
/*     */     //   #483	-> 1186
/*     */     //   #481	-> 1189
/*     */     //   #484	-> 1191
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   11	45	3	canonicalPath	Ljava/nio/file/Path;
/*     */     //   58	31	4	e	Ljava/lang/Exception;
/*     */     //   503	10	11	subPath	Ljava/nio/file/Path;
/*     */     //   472	88	9	stream	Ljava/nio/file/DirectoryStream;
/*     */     //   565	44	9	e	Ljava/lang/Exception;
/*     */     //   807	46	14	resource	Lio/github/classgraph/Resource;
/*     */     //   700	188	12	subPathRelative	Ljava/nio/file/Path;
/*     */     //   712	176	13	subPathRelativeStr	Ljava/lang/String;
/*     */     //   675	213	11	subPath	Ljava/nio/file/Path;
/*     */     //   991	49	12	resource	Lio/github/classgraph/Resource;
/*     */     //   946	94	11	subPath	Ljava/nio/file/Path;
/*     */     //   1099	46	12	e	Ljava/lang/SecurityException;
/*     */     //   1074	71	11	subPath	Ljava/nio/file/Path;
/*     */     //   1166	20	10	file	Ljava/io/File;
/*     */     //   0	1192	0	this	Lio/github/classgraph/ClasspathElementDir;
/*     */     //   0	1192	1	path	Ljava/nio/file/Path;
/*     */     //   0	1192	2	log	Lnonapi/io/github/classgraph/utils/LogNode;
/*     */     //   89	1103	3	canonicalPath	Ljava/nio/file/Path;
/*     */     //   109	1083	4	dirRelativePathStr	Ljava/lang/String;
/*     */     //   175	1017	5	isDefaultPackage	Z
/*     */     //   301	891	6	parentMatchStatus	Lnonapi/io/github/classgraph/scanspec/ScanSpec$ScanSpecPathMatch;
/*     */     //   457	735	7	subLog	Lnonapi/io/github/classgraph/utils/LogNode;
/*     */     //   466	726	8	pathsInDir	Ljava/util/List;
/*     */     //   636	556	9	isModularJar	Z
/*     */     // Local variable type table:
/*     */     //   start	length	slot	name	signature
/*     */     //   472	88	9	stream	Ljava/nio/file/DirectoryStream<Ljava/nio/file/Path;>;
/*     */     //   466	726	8	pathsInDir	Ljava/util/List<Ljava/nio/file/Path;>;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   0	52	56	java/io/IOException
/*     */     //   0	52	56	java/lang/SecurityException
/*     */     //   466	560	563	java/io/IOException
/*     */     //   466	560	563	java/lang/SecurityException
/*     */     //   472	516	531	java/lang/Throwable
/*     */     //   538	545	548	java/lang/Throwable
/*     */     //   818	848	851	java/lang/UnsupportedOperationException
/*     */     //   1002	1032	1035	java/lang/UnsupportedOperationException
/*     */     //   1074	1094	1097	java/lang/SecurityException
/*     */     //   1158	1186	1189	java/lang/UnsupportedOperationException
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void scanPaths(LogNode log) {
/* 494 */     if (!checkResourcePathAcceptReject(this.classpathEltPath.toString(), log)) {
/* 495 */       this.skipClasspathElement = true;
/*     */     }
/* 497 */     if (this.skipClasspathElement) {
/*     */       return;
/*     */     }
/* 500 */     if (this.scanned.getAndSet(true))
/*     */     {
/* 502 */       throw new IllegalArgumentException("Already scanned classpath element " + this);
/*     */     }
/*     */ 
/*     */     
/* 506 */     LogNode subLog = (log == null) ? null : log(this.classpathElementIdx, "Scanning Path classpath element " + getURI(), log);
/*     */     
/* 508 */     scanPathRecursively(this.classpathEltPath, subLog);
/*     */     
/* 510 */     finishScanPaths(subLog);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getModuleName() {
/* 520 */     return (this.moduleNameFromModuleDescriptor == null || this.moduleNameFromModuleDescriptor.isEmpty()) ? null : 
/* 521 */       this.moduleNameFromModuleDescriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getFile() {
/*     */     try {
/* 533 */       return this.classpathEltPath.toFile();
/* 534 */     } catch (UnsupportedOperationException e) {
/* 535 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   URI getURI() {
/*     */     try {
/* 545 */       return this.classpathEltPath.toUri();
/* 546 */     } catch (IOError|SecurityException e) {
/* 547 */       throw new IllegalArgumentException("Could not convert to URI: " + this.classpathEltPath);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   List<URI> getAllURIs() {
/* 553 */     return Collections.singletonList(getURI());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     try {
/* 565 */       return this.classpathEltPath.toUri().toString();
/* 566 */     } catch (IOError|SecurityException e) {
/* 567 */       return this.classpathEltPath.toString();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 576 */     return Objects.hash(new Object[] { this.classpathEltPath });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 584 */     if (obj == this)
/* 585 */       return true; 
/* 586 */     if (!(obj instanceof ClasspathElementDir)) {
/* 587 */       return false;
/*     */     }
/* 589 */     ClasspathElementDir other = (ClasspathElementDir)obj;
/* 590 */     return Objects.equals(this.classpathEltPath, other.classpathEltPath);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ClasspathElementDir.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */