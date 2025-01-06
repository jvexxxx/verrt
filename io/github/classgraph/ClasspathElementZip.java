/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.PosixFilePermission;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;
/*     */ import nonapi.io.github.classgraph.concurrency.SingletonMap;
/*     */ import nonapi.io.github.classgraph.concurrency.WorkQueue;
/*     */ import nonapi.io.github.classgraph.fastzipfilereader.FastZipEntry;
/*     */ import nonapi.io.github.classgraph.fastzipfilereader.LogicalZipFile;
/*     */ import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
/*     */ import nonapi.io.github.classgraph.fastzipfilereader.ZipFileSlice;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.ClassfileReader;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ import nonapi.io.github.classgraph.utils.FastPathResolver;
/*     */ import nonapi.io.github.classgraph.utils.FileUtils;
/*     */ import nonapi.io.github.classgraph.utils.JarUtils;
/*     */ import nonapi.io.github.classgraph.utils.LogNode;
/*     */ import nonapi.io.github.classgraph.utils.URLPathEncoder;
/*     */ import nonapi.io.github.classgraph.utils.VersionFinder;
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
/*     */ class ClasspathElementZip
/*     */   extends ClasspathElement
/*     */ {
/*     */   private final String rawPath;
/*     */   LogicalZipFile logicalZipFile;
/*     */   private String zipFilePath;
/*  81 */   private final ConcurrentHashMap<String, Resource> relativePathToResource = new ConcurrentHashMap<>();
/*     */   
/*  83 */   private final Set<String> strippedAutomaticPackageRootPrefixes = new HashSet<>();
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
/*     */   String moduleNameFromManifestFile;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String derivedAutomaticModuleName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ClasspathElementZip(Scanner.ClasspathEntryWorkUnit workUnit, NestedJarHandler nestedJarHandler, ScanSpec scanSpec) {
/* 106 */     super(workUnit, scanSpec);
/* 107 */     Object rawPathObj = workUnit.classpathEntryObj;
/*     */ 
/*     */ 
/*     */     
/* 111 */     String rawPath = null;
/* 112 */     if (rawPathObj instanceof Path) {
/*     */       
/*     */       try {
/* 115 */         rawPath = ((Path)rawPathObj).toUri().toString();
/* 116 */       } catch (IOError|SecurityException iOError) {}
/*     */     }
/*     */ 
/*     */     
/* 120 */     if (rawPath == null) {
/* 121 */       rawPath = rawPathObj.toString();
/*     */     }
/* 123 */     this.rawPath = rawPath;
/* 124 */     this.zipFilePath = rawPath;
/* 125 */     this.nestedJarHandler = nestedJarHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void open(WorkQueue<Scanner.ClasspathEntryWorkUnit> workQueue, LogNode log) throws InterruptedException {
/* 134 */     if (!this.scanSpec.scanJars) {
/* 135 */       if (log != null) {
/* 136 */         log(this.classpathElementIdx, "Skipping classpath element, since jar scanning is disabled: " + this.rawPath, log);
/*     */       }
/*     */       
/* 139 */       this.skipClasspathElement = true;
/*     */       return;
/*     */     } 
/* 142 */     LogNode subLog = (log == null) ? null : log(this.classpathElementIdx, "Opening jar: " + this.rawPath, log);
/* 143 */     int plingIdx = this.rawPath.indexOf('!');
/* 144 */     String outermostZipFilePathResolved = FastPathResolver.resolve(FileUtils.currDirPath(), 
/* 145 */         (plingIdx < 0) ? this.rawPath : this.rawPath.substring(0, plingIdx));
/* 146 */     if (!this.scanSpec.jarAcceptReject.isAcceptedAndNotRejected(outermostZipFilePathResolved)) {
/* 147 */       if (subLog != null) {
/* 148 */         subLog.log("Skipping jarfile that is rejected or not accepted: " + this.rawPath);
/*     */       }
/* 150 */       this.skipClasspathElement = true;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*     */     try {
/*     */       Map.Entry<LogicalZipFile, String> logicalZipFileAndPackageRoot;
/*     */       
/*     */       try {
/* 159 */         logicalZipFileAndPackageRoot = (Map.Entry<LogicalZipFile, String>)this.nestedJarHandler.nestedPathToLogicalZipFileAndPackageRootMap.get(this.rawPath, subLog);
/* 160 */       } catch (nonapi.io.github.classgraph.concurrency.SingletonMap.NullSingletonException|nonapi.io.github.classgraph.concurrency.SingletonMap.NewInstanceException e) {
/*     */ 
/*     */         
/* 163 */         throw new IOException("Could not get logical zipfile " + this.rawPath + " : " + (
/* 164 */             (e.getCause() == null) ? e : e.getCause()));
/*     */       } 
/* 166 */       this.logicalZipFile = logicalZipFileAndPackageRoot.getKey();
/* 167 */       if (this.logicalZipFile == null)
/*     */       {
/* 169 */         throw new IOException("Logical zipfile was null");
/*     */       }
/*     */ 
/*     */       
/* 173 */       this.zipFilePath = FastPathResolver.resolve(FileUtils.currDirPath(), this.logicalZipFile.getPath());
/*     */ 
/*     */       
/* 176 */       String packageRoot = logicalZipFileAndPackageRoot.getValue();
/* 177 */       if (!packageRoot.isEmpty()) {
/* 178 */         this.packageRootPrefix = packageRoot + "/";
/*     */       }
/* 180 */     } catch (IOException|IllegalArgumentException e) {
/* 181 */       Map.Entry<LogicalZipFile, String> logicalZipFileAndPackageRoot; if (subLog != null) {
/* 182 */         subLog.log("Could not open jarfile " + this.rawPath + " : " + logicalZipFileAndPackageRoot);
/*     */       }
/* 184 */       this.skipClasspathElement = true;
/*     */       
/*     */       return;
/*     */     } 
/* 188 */     if (!this.scanSpec.enableSystemJarsAndModules && this.logicalZipFile.isJREJar) {
/*     */ 
/*     */       
/* 191 */       if (subLog != null) {
/* 192 */         subLog.log("Ignoring JRE jar: " + this.rawPath);
/*     */       }
/* 194 */       this.skipClasspathElement = true;
/*     */       
/*     */       return;
/*     */     } 
/* 198 */     if (!this.logicalZipFile.isAcceptedAndNotRejected(this.scanSpec.jarAcceptReject)) {
/* 199 */       if (subLog != null) {
/* 200 */         subLog.log("Skipping jarfile that is rejected or not accepted: " + this.rawPath);
/*     */       }
/* 202 */       this.skipClasspathElement = true;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 208 */     int childClasspathEntryIdx = 0;
/* 209 */     if (this.scanSpec.scanNestedJars) {
/* 210 */       for (FastZipEntry zipEntry : this.logicalZipFile.entries) {
/* 211 */         for (String libDirPrefix : ClassLoaderHandlerRegistry.AUTOMATIC_LIB_DIR_PREFIXES) {
/*     */           
/* 213 */           if (zipEntry.entryNameUnversioned.startsWith(libDirPrefix) && zipEntry.entryNameUnversioned
/* 214 */             .endsWith(".jar")) {
/* 215 */             String entryPath = zipEntry.getPath();
/* 216 */             if (subLog != null) {
/* 217 */               subLog.log("Found nested lib jar: " + entryPath);
/*     */             }
/* 219 */             workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(entryPath, getClassLoader(), this, childClasspathEntryIdx++, ""));
/*     */ 
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 230 */     Set<String> scheduledChildClasspathElements = new HashSet<>();
/* 231 */     scheduledChildClasspathElements.add(this.rawPath);
/*     */ 
/*     */ 
/*     */     
/* 235 */     if (this.logicalZipFile.classPathManifestEntryValue != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 240 */       String jarParentDir = FileUtils.getParentDirPath(this.logicalZipFile.getPathWithinParentZipFileSlice());
/*     */ 
/*     */       
/* 243 */       for (String childClassPathEltPathRelative : this.logicalZipFile.classPathManifestEntryValue
/* 244 */         .split(" ")) {
/* 245 */         if (!childClassPathEltPathRelative.isEmpty()) {
/*     */           
/* 247 */           String childClassPathEltPath = FastPathResolver.resolve(jarParentDir, childClassPathEltPathRelative);
/*     */ 
/*     */           
/* 250 */           ZipFileSlice parentZipFileSlice = this.logicalZipFile.getParentZipFileSlice();
/*     */ 
/*     */           
/* 253 */           String childClassPathEltPathWithPrefix = (parentZipFileSlice == null) ? childClassPathEltPath : (
/* 254 */             parentZipFileSlice.getPath() + (childClassPathEltPath.startsWith("/") ? "!" : "!/") + childClassPathEltPath);
/*     */           
/* 256 */           if (scheduledChildClasspathElements.add(childClassPathEltPathWithPrefix))
/*     */           {
/* 258 */             workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(childClassPathEltPathWithPrefix, 
/* 259 */                   getClassLoader(), this, childClasspathEntryIdx++, ""));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 269 */     if (this.logicalZipFile.bundleClassPathManifestEntryValue != null) {
/* 270 */       String zipFilePathPrefix = this.zipFilePath + "!/";
/*     */       
/* 272 */       for (String childBundlePath : this.logicalZipFile.bundleClassPathManifestEntryValue.split(",")) {
/*     */         
/* 274 */         while (childBundlePath.startsWith("/")) {
/* 275 */           childBundlePath = childBundlePath.substring(1);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 281 */         if (!childBundlePath.isEmpty() && !childBundlePath.equals(".")) {
/*     */           
/* 283 */           String childClassPathEltPath = zipFilePathPrefix + FileUtils.sanitizeEntryPath(childBundlePath, true, true);
/*     */ 
/*     */           
/* 286 */           if (scheduledChildClasspathElements.add(childClassPathEltPath))
/*     */           {
/* 288 */             workQueue.addWorkUnit(new Scanner.ClasspathEntryWorkUnit(childClassPathEltPath, getClassLoader(), this, childClasspathEntryIdx++, ""));
/*     */           }
/*     */         } 
/*     */       } 
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
/*     */ 
/*     */ 
/*     */   
/*     */   private Resource newResource(final FastZipEntry zipEntry, final String pathRelativeToPackageRoot) {
/* 308 */     return new Resource(this, zipEntry.uncompressedSize)
/*     */       {
/* 310 */         private final AtomicBoolean isOpen = new AtomicBoolean();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public String getPath() {
/* 318 */           return pathRelativeToPackageRoot;
/*     */         }
/*     */ 
/*     */         
/*     */         public String getPathRelativeToClasspathElement() {
/* 323 */           if (zipEntry.entryName.startsWith(ClasspathElementZip.this.packageRootPrefix)) {
/* 324 */             return zipEntry.entryName.substring(ClasspathElementZip.this.packageRootPrefix.length());
/*     */           }
/* 326 */           return zipEntry.entryName;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public long getLastModified() {
/* 332 */           return zipEntry.getLastModifiedTimeMillis();
/*     */         }
/*     */         
/*     */         public Set<PosixFilePermission> getPosixFilePermissions() {
/*     */           Set<PosixFilePermission> perms;
/* 337 */           int fileAttributes = zipEntry.fileAttributes;
/*     */           
/* 339 */           if (fileAttributes == 0) {
/* 340 */             perms = null;
/*     */           } else {
/* 342 */             perms = new HashSet<>();
/* 343 */             if ((fileAttributes & 0x100) > 0) {
/* 344 */               perms.add(PosixFilePermission.OWNER_READ);
/*     */             }
/* 346 */             if ((fileAttributes & 0x80) > 0) {
/* 347 */               perms.add(PosixFilePermission.OWNER_WRITE);
/*     */             }
/* 349 */             if ((fileAttributes & 0x40) > 0) {
/* 350 */               perms.add(PosixFilePermission.OWNER_EXECUTE);
/*     */             }
/* 352 */             if ((fileAttributes & 0x20) > 0) {
/* 353 */               perms.add(PosixFilePermission.GROUP_READ);
/*     */             }
/* 355 */             if ((fileAttributes & 0x10) > 0) {
/* 356 */               perms.add(PosixFilePermission.GROUP_WRITE);
/*     */             }
/* 358 */             if ((fileAttributes & 0x8) > 0) {
/* 359 */               perms.add(PosixFilePermission.GROUP_EXECUTE);
/*     */             }
/* 361 */             if ((fileAttributes & 0x4) > 0) {
/* 362 */               perms.add(PosixFilePermission.OTHERS_READ);
/*     */             }
/* 364 */             if ((fileAttributes & 0x2) > 0) {
/* 365 */               perms.add(PosixFilePermission.OTHERS_WRITE);
/*     */             }
/* 367 */             if ((fileAttributes & 0x1) > 0) {
/* 368 */               perms.add(PosixFilePermission.OTHERS_EXECUTE);
/*     */             }
/*     */           } 
/* 371 */           return perms;
/*     */         }
/*     */ 
/*     */         
/*     */         ClassfileReader openClassfile() throws IOException {
/* 376 */           return new ClassfileReader(open(), this);
/*     */         }
/*     */ 
/*     */         
/*     */         public InputStream open() throws IOException {
/* 381 */           if (ClasspathElementZip.this.skipClasspathElement)
/*     */           {
/* 383 */             throw new IOException("Jarfile could not be opened");
/*     */           }
/* 385 */           if (this.isOpen.getAndSet(true)) {
/* 386 */             throw new IOException("Resource is already open -- cannot open it again without first calling close()");
/*     */           }
/*     */           
/*     */           try {
/* 390 */             this.inputStream = zipEntry.getSlice().open(this);
/* 391 */             this.length = zipEntry.uncompressedSize;
/* 392 */             return this.inputStream;
/*     */           }
/* 394 */           catch (IOException e) {
/* 395 */             close();
/* 396 */             throw e;
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public ByteBuffer read() throws IOException {
/* 402 */           if (ClasspathElementZip.this.skipClasspathElement)
/*     */           {
/* 404 */             throw new IOException("Jarfile could not be opened");
/*     */           }
/* 406 */           if (this.isOpen.getAndSet(true)) {
/* 407 */             throw new IOException("Resource is already open -- cannot open it again without first calling close()");
/*     */           }
/*     */           
/*     */           try {
/* 411 */             this.byteBuffer = zipEntry.getSlice().read();
/* 412 */             this.length = this.byteBuffer.remaining();
/* 413 */             return this.byteBuffer;
/* 414 */           } catch (IOException e) {
/* 415 */             close();
/* 416 */             throw e;
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public byte[] load() throws IOException {
/* 422 */           if (ClasspathElementZip.this.skipClasspathElement)
/*     */           {
/* 424 */             throw new IOException("Jarfile could not be opened");
/*     */           }
/* 426 */           if (this.isOpen.getAndSet(true)) {
/* 427 */             throw new IOException("Resource is already open -- cannot open it again without first calling close()");
/*     */           }
/*     */           
/* 430 */           Resource res = this; 
/* 431 */           try { byte[] byteArray = zipEntry.getSlice().load();
/* 432 */             res.length = byteArray.length;
/* 433 */             byte[] arrayOfByte1 = byteArray;
/* 434 */             if (res != null) res.close();  return arrayOfByte1; }
/*     */           catch (Throwable throwable) { if (res != null)
/*     */               try { res.close(); }
/*     */               catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */                 throw throwable; }
/* 439 */            } public void close() { if (this.isOpen.getAndSet(false)) {
/* 440 */             if (this.byteBuffer != null)
/*     */             {
/*     */               
/* 443 */               this.byteBuffer = null;
/*     */             }
/*     */ 
/*     */             
/* 447 */             super.close();
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
/* 463 */     return this.relativePathToResource.get(relativePath);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void scanPaths(LogNode log) {
/* 474 */     if (this.logicalZipFile == null) {
/* 475 */       this.skipClasspathElement = true;
/*     */     }
/* 477 */     if (!checkResourcePathAcceptReject(getZipFilePath(), log)) {
/* 478 */       this.skipClasspathElement = true;
/*     */     }
/* 480 */     if (this.skipClasspathElement) {
/*     */       return;
/*     */     }
/* 483 */     if (this.scanned.getAndSet(true))
/*     */     {
/* 485 */       throw new IllegalArgumentException("Already scanned classpath element " + getZipFilePath());
/*     */     }
/*     */ 
/*     */     
/* 489 */     LogNode subLog = (log == null) ? null : log(this.classpathElementIdx, "Scanning jarfile classpath element " + getZipFilePath(), log);
/*     */     
/* 491 */     boolean isModularJar = false;
/* 492 */     if (VersionFinder.JAVA_MAJOR_VERSION >= 9) {
/*     */       
/* 494 */       String moduleName = this.moduleNameFromModuleDescriptor;
/* 495 */       if (moduleName == null || moduleName.isEmpty()) {
/* 496 */         moduleName = this.moduleNameFromManifestFile;
/*     */       }
/* 498 */       if (moduleName != null && moduleName.isEmpty()) {
/* 499 */         moduleName = null;
/*     */       }
/* 501 */       if (moduleName != null) {
/* 502 */         isModularJar = true;
/*     */       }
/*     */     } 
/*     */     
/* 506 */     Set<String> loggedNestedClasspathRootPrefixes = null;
/* 507 */     String prevParentRelativePath = null;
/* 508 */     ScanSpec.ScanSpecPathMatch prevParentMatchStatus = null;
/* 509 */     for (FastZipEntry zipEntry : this.logicalZipFile.entries) {
/* 510 */       String relativePath = zipEntry.entryNameUnversioned;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 516 */       if (!this.scanSpec.enableMultiReleaseVersions && relativePath
/* 517 */         .startsWith("META-INF/versions/")) {
/* 518 */         if (subLog != null) {
/* 519 */           if (VersionFinder.JAVA_MAJOR_VERSION < 9) {
/* 520 */             subLog.log("Skipping versioned entry in jar, because JRE version " + VersionFinder.JAVA_MAJOR_VERSION + " does not support this: " + relativePath);
/*     */             continue;
/*     */           } 
/* 523 */           subLog.log("Found unexpected versioned entry in jar (the jar's manifest file may be missing the \"Multi-Release\" key) -- skipping: " + relativePath);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 533 */       if (isModularJar && relativePath.indexOf('/') < 0 && relativePath.endsWith(".class") && 
/* 534 */         !relativePath.equals("module-info.class")) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 539 */       if (this.nestedClasspathRootPrefixes != null) {
/*     */         
/* 541 */         boolean reachedNestedRoot = false;
/* 542 */         for (String nestedClasspathRoot : this.nestedClasspathRootPrefixes) {
/* 543 */           if (relativePath.startsWith(nestedClasspathRoot)) {
/*     */             
/* 545 */             if (subLog != null) {
/* 546 */               if (loggedNestedClasspathRootPrefixes == null) {
/* 547 */                 loggedNestedClasspathRootPrefixes = new HashSet<>();
/*     */               }
/* 549 */               if (loggedNestedClasspathRootPrefixes.add(nestedClasspathRoot)) {
/* 550 */                 subLog.log("Reached nested classpath root, stopping recursion to avoid duplicate scanning: " + nestedClasspathRoot);
/*     */               }
/*     */             } 
/*     */             
/* 554 */             reachedNestedRoot = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 558 */         if (reachedNestedRoot) {
/*     */           continue;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 564 */       if (!this.packageRootPrefix.isEmpty() && !relativePath.startsWith(this.packageRootPrefix)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 569 */       if (!this.packageRootPrefix.isEmpty()) {
/* 570 */         relativePath = relativePath.substring(this.packageRootPrefix.length());
/*     */       } else {
/*     */         
/* 573 */         for (int i = 0; i < ClassLoaderHandlerRegistry.AUTOMATIC_PACKAGE_ROOT_PREFIXES.length; i++) {
/* 574 */           String packageRoot = ClassLoaderHandlerRegistry.AUTOMATIC_PACKAGE_ROOT_PREFIXES[i];
/* 575 */           if (relativePath.startsWith(packageRoot)) {
/*     */             
/* 577 */             relativePath = relativePath.substring(packageRoot.length());
/*     */ 
/*     */ 
/*     */             
/* 581 */             String packageRootWithoutFinalSlash = packageRoot.endsWith("/") ? packageRoot.substring(0, packageRoot.length() - 1) : packageRoot;
/*     */             
/* 583 */             this.strippedAutomaticPackageRootPrefixes.add(packageRootWithoutFinalSlash);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 589 */       if (!checkResourcePathAcceptReject(relativePath, log)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 595 */       int lastSlashIdx = relativePath.lastIndexOf('/');
/* 596 */       String parentRelativePath = (lastSlashIdx < 0) ? "/" : relativePath.substring(0, lastSlashIdx + 1);
/* 597 */       boolean parentRelativePathChanged = !parentRelativePath.equals(prevParentRelativePath);
/*     */ 
/*     */       
/* 600 */       ScanSpec.ScanSpecPathMatch parentMatchStatus = parentRelativePathChanged ? this.scanSpec.dirAcceptMatchStatus(parentRelativePath) : prevParentMatchStatus;
/* 601 */       prevParentRelativePath = parentRelativePath;
/* 602 */       prevParentMatchStatus = parentMatchStatus;
/*     */       
/* 604 */       if (parentMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_REJECTED_PATH_PREFIX) {
/*     */         
/* 606 */         if (subLog != null) {
/* 607 */           subLog.log("Skipping rejected path: " + relativePath);
/*     */         }
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 613 */       Resource resource = newResource(zipEntry, relativePath);
/* 614 */       if (this.relativePathToResource.putIfAbsent(relativePath, resource) == null) {
/*     */         
/* 616 */         if (parentMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX || parentMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_PATH || (parentMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_CLASS_PACKAGE && this.scanSpec
/*     */ 
/*     */           
/* 619 */           .classfileIsSpecificallyAccepted(relativePath))) {
/*     */           
/* 621 */           addAcceptedResource(resource, parentMatchStatus, false, subLog); continue;
/* 622 */         }  if (this.scanSpec.enableClassInfo && relativePath.equals("module-info.class"))
/*     */         {
/*     */ 
/*     */           
/* 626 */           addAcceptedResource(resource, parentMatchStatus, true, subLog);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 632 */     File zipfile = getFile();
/* 633 */     if (zipfile != null) {
/* 634 */       this.fileToLastModified.put(zipfile, Long.valueOf(zipfile.lastModified()));
/*     */     }
/*     */     
/* 637 */     finishScanPaths(subLog);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getModuleName() {
/* 648 */     String moduleName = this.moduleNameFromModuleDescriptor;
/* 649 */     if (moduleName == null || moduleName.isEmpty()) {
/* 650 */       moduleName = this.moduleNameFromManifestFile;
/*     */     }
/* 652 */     if (moduleName == null || moduleName.isEmpty()) {
/* 653 */       if (this.derivedAutomaticModuleName == null) {
/* 654 */         this.derivedAutomaticModuleName = JarUtils.derivedAutomaticModuleName(this.zipFilePath);
/*     */       }
/* 656 */       moduleName = this.derivedAutomaticModuleName;
/*     */     } 
/* 658 */     return (moduleName == null || moduleName.isEmpty()) ? null : moduleName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String getZipFilePath() {
/* 667 */     return this.packageRootPrefix.isEmpty() ? this.zipFilePath : (
/* 668 */       this.zipFilePath + "!/" + this.packageRootPrefix.substring(0, this.packageRootPrefix.length() - 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   URI getURI() {
/*     */     try {
/* 677 */       return new URI(URLPathEncoder.normalizeURLPath(getZipFilePath()));
/* 678 */     } catch (URISyntaxException e) {
/* 679 */       throw new IllegalArgumentException("Could not form URI: " + e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   List<URI> getAllURIs() {
/* 689 */     if (this.strippedAutomaticPackageRootPrefixes.isEmpty()) {
/* 690 */       return Collections.singletonList(getURI());
/*     */     }
/* 692 */     URI uri = getURI();
/* 693 */     List<URI> uris = new ArrayList<>();
/* 694 */     uris.add(uri);
/* 695 */     String uriStr = uri.toString();
/* 696 */     for (String prefix : this.strippedAutomaticPackageRootPrefixes) {
/*     */       try {
/* 698 */         uris.add(new URI(uriStr + "!/" + prefix));
/* 699 */       } catch (URISyntaxException uRISyntaxException) {}
/*     */     } 
/*     */ 
/*     */     
/* 703 */     return uris;
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
/*     */   File getFile() {
/* 716 */     if (this.logicalZipFile != null) {
/* 717 */       return this.logicalZipFile.getPhysicalFile();
/*     */     }
/*     */     
/* 720 */     int plingIdx = this.rawPath.indexOf('!');
/* 721 */     String outermostZipFilePathResolved = FastPathResolver.resolve(FileUtils.currDirPath(), 
/* 722 */         (plingIdx < 0) ? this.rawPath : this.rawPath.substring(0, plingIdx));
/* 723 */     return new File(outermostZipFilePathResolved);
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
/* 734 */     return getZipFilePath();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 742 */     if (obj == this)
/* 743 */       return true; 
/* 744 */     if (!(obj instanceof ClasspathElementZip)) {
/* 745 */       return false;
/*     */     }
/* 747 */     ClasspathElementZip other = (ClasspathElementZip)obj;
/* 748 */     return getZipFilePath().equals(other.getZipFilePath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 756 */     return getZipFilePath().hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ClasspathElementZip.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */