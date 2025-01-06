/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.file.attribute.PosixFilePermission;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import nonapi.io.github.classgraph.concurrency.SingletonMap;
/*     */ import nonapi.io.github.classgraph.concurrency.WorkQueue;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.ClassfileReader;
/*     */ import nonapi.io.github.classgraph.recycler.RecycleOnClose;
/*     */ import nonapi.io.github.classgraph.recycler.Recycler;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ import nonapi.io.github.classgraph.utils.CollectionUtils;
/*     */ import nonapi.io.github.classgraph.utils.LogNode;
/*     */ import nonapi.io.github.classgraph.utils.ProxyingInputStream;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ClasspathElementModule
/*     */   extends ClasspathElement
/*     */ {
/*     */   final ModuleRef moduleRef;
/*     */   SingletonMap<ModuleRef, Recycler<ModuleReaderProxy, IOException>, IOException> moduleRefToModuleReaderProxyRecyclerMap;
/*     */   private Recycler<ModuleReaderProxy, IOException> moduleReaderProxyRecycler;
/*  77 */   private final Set<String> allResourcePaths = new HashSet<>();
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
/*     */   ClasspathElementModule(ModuleRef moduleRef, SingletonMap<ModuleRef, Recycler<ModuleReaderProxy, IOException>, IOException> moduleRefToModuleReaderProxyRecyclerMap, Scanner.ClasspathEntryWorkUnit workUnit, ScanSpec scanSpec) {
/*  95 */     super(workUnit, scanSpec);
/*  96 */     this.moduleRefToModuleReaderProxyRecyclerMap = moduleRefToModuleReaderProxyRecyclerMap;
/*  97 */     this.moduleRef = moduleRef;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void open(WorkQueue<Scanner.ClasspathEntryWorkUnit> workQueueIgnored, LogNode log) throws InterruptedException {
/* 107 */     if (!this.scanSpec.scanModules) {
/* 108 */       if (log != null) {
/* 109 */         log(this.classpathElementIdx, "Skipping module, since module scanning is disabled: " + getModuleName(), log);
/*     */       }
/*     */       
/* 112 */       this.skipClasspathElement = true;
/*     */       return;
/*     */     } 
/*     */     try {
/* 116 */       this.moduleReaderProxyRecycler = (Recycler<ModuleReaderProxy, IOException>)this.moduleRefToModuleReaderProxyRecyclerMap.get(this.moduleRef, log);
/* 117 */     } catch (IOException|nonapi.io.github.classgraph.concurrency.SingletonMap.NullSingletonException|nonapi.io.github.classgraph.concurrency.SingletonMap.NewInstanceException e) {
/* 118 */       if (log != null) {
/* 119 */         log(this.classpathElementIdx, "Skipping invalid module " + getModuleName() + " : " + (
/* 120 */             (e.getCause() == null) ? (String)e : (String)e.getCause()), log);
/*     */       }
/* 122 */       this.skipClasspathElement = true;
/*     */       return;
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
/*     */   private Resource newResource(final String resourcePath) {
/* 135 */     return new Resource(this, -1L)
/*     */       {
/*     */         private ModuleReaderProxy moduleReaderProxy;
/*     */ 
/*     */         
/* 140 */         private final AtomicBoolean isOpen = new AtomicBoolean();
/*     */ 
/*     */         
/*     */         public String getPath() {
/* 144 */           return resourcePath;
/*     */         }
/*     */ 
/*     */         
/*     */         public long getLastModified() {
/* 149 */           return 0L;
/*     */         }
/*     */ 
/*     */         
/*     */         public Set<PosixFilePermission> getPosixFilePermissions() {
/* 154 */           return null;
/*     */         }
/*     */ 
/*     */         
/*     */         public ByteBuffer read() throws IOException {
/* 159 */           if (ClasspathElementModule.this.skipClasspathElement)
/*     */           {
/* 161 */             throw new IOException("Module could not be opened");
/*     */           }
/* 163 */           if (this.isOpen.getAndSet(true)) {
/* 164 */             throw new IOException("Resource is already open -- cannot open it again without first calling close()");
/*     */           }
/*     */           
/*     */           try {
/* 168 */             this.moduleReaderProxy = (ModuleReaderProxy)ClasspathElementModule.this.moduleReaderProxyRecycler.acquire();
/*     */ 
/*     */             
/* 171 */             this.byteBuffer = this.moduleReaderProxy.read(resourcePath);
/* 172 */             this.length = this.byteBuffer.remaining();
/* 173 */             return this.byteBuffer;
/*     */           }
/* 175 */           catch (SecurityException|OutOfMemoryError e) {
/* 176 */             close();
/* 177 */             throw new IOException("Could not open " + this, e);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         ClassfileReader openClassfile() throws IOException {
/* 183 */           return new ClassfileReader(open(), this);
/*     */         }
/*     */ 
/*     */         
/*     */         public URI getURI() {
/*     */           try {
/* 189 */             ModuleReaderProxy localModuleReaderProxy = (ModuleReaderProxy)ClasspathElementModule.this.moduleReaderProxyRecycler.acquire();
/*     */             try {
/* 191 */               return localModuleReaderProxy.find(resourcePath);
/*     */             } finally {
/* 193 */               ClasspathElementModule.this.moduleReaderProxyRecycler.recycle(localModuleReaderProxy);
/*     */             } 
/* 195 */           } catch (IOException e) {
/* 196 */             throw new RuntimeException(e);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public InputStream open() throws IOException {
/* 202 */           if (ClasspathElementModule.this.skipClasspathElement)
/*     */           {
/* 204 */             throw new IOException("Module could not be opened");
/*     */           }
/* 206 */           if (this.isOpen.getAndSet(true)) {
/* 207 */             throw new IOException("Resource is already open -- cannot open it again without first calling close()");
/*     */           }
/*     */           
/*     */           try {
/* 211 */             final Resource thisResource = this;
/* 212 */             this.moduleReaderProxy = (ModuleReaderProxy)ClasspathElementModule.this.moduleReaderProxyRecycler.acquire();
/* 213 */             this.inputStream = (InputStream)new ProxyingInputStream(this.moduleReaderProxy.open(resourcePath))
/*     */               {
/*     */                 public void close() throws IOException
/*     */                 {
/* 217 */                   super.close();
/*     */ 
/*     */                   
/*     */                   try {
/* 221 */                     thisResource.close();
/* 222 */                   } catch (Exception exception) {}
/*     */                 }
/*     */               };
/*     */ 
/*     */ 
/*     */             
/* 228 */             this.length = -1L;
/* 229 */             return this.inputStream;
/*     */           }
/* 231 */           catch (SecurityException e) {
/* 232 */             close();
/* 233 */             throw new IOException("Could not open " + this, e);
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/*     */         public byte[] load() throws IOException {
/* 239 */           Resource res = this; 
/* 240 */           try { byte[] byteArray; read();
/*     */             
/* 242 */             if (res.byteBuffer.hasArray() && res.byteBuffer.position() == 0 && res.byteBuffer
/* 243 */               .limit() == res.byteBuffer.capacity()) {
/* 244 */               byteArray = res.byteBuffer.array();
/*     */             } else {
/* 246 */               byteArray = new byte[res.byteBuffer.remaining()];
/* 247 */               res.byteBuffer.get(byteArray);
/*     */             } 
/* 249 */             res.length = byteArray.length;
/* 250 */             byte[] arrayOfByte1 = byteArray;
/* 251 */             if (res != null) res.close();  return arrayOfByte1; }
/*     */           catch (Throwable throwable) { if (res != null)
/*     */               try { res.close(); }
/*     */               catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*     */                 throw throwable; }
/* 256 */            } public void close() { if (this.isOpen.getAndSet(false)) {
/* 257 */             if (this.moduleReaderProxy != null) {
/* 258 */               if (this.byteBuffer != null) {
/*     */                 
/* 260 */                 this.moduleReaderProxy.release(this.byteBuffer);
/* 261 */                 this.byteBuffer = null;
/*     */               } 
/*     */               
/* 264 */               ClasspathElementModule.this.moduleReaderProxyRecycler.recycle(this.moduleReaderProxy);
/*     */ 
/*     */ 
/*     */               
/* 268 */               this.moduleReaderProxy = null;
/*     */             } 
/*     */ 
/*     */             
/* 272 */             super.close();
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
/* 288 */     return this.allResourcePaths.contains(relativePath) ? newResource(relativePath) : null;
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
/* 299 */     if (this.skipClasspathElement) {
/*     */       return;
/*     */     }
/* 302 */     if (this.scanned.getAndSet(true))
/*     */     {
/* 304 */       throw new IllegalArgumentException("Already scanned classpath element " + this);
/*     */     }
/*     */ 
/*     */     
/* 308 */     LogNode subLog = (log == null) ? null : log(this.classpathElementIdx, "Scanning module " + this.moduleRef.getName(), log);
/*     */ 
/*     */     
/* 311 */     boolean isModularJar = (VersionFinder.JAVA_MAJOR_VERSION >= 9 && getModuleName() != null);
/*     */ 
/*     */     
/* 314 */     try { RecycleOnClose<ModuleReaderProxy, IOException> moduleReaderProxyRecycleOnClose = this.moduleReaderProxyRecycler.acquireRecycleOnClose();
/*     */       
/*     */       try { List<String> resourceRelativePaths;
/*     */         
/* 318 */         try { resourceRelativePaths = ((ModuleReaderProxy)moduleReaderProxyRecycleOnClose.get()).list(); }
/* 319 */         catch (SecurityException e)
/* 320 */         { if (subLog != null) {
/* 321 */             subLog.log("Could not get resource list for module " + this.moduleRef.getName(), e);
/*     */           }
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
/* 415 */           if (moduleReaderProxyRecycleOnClose != null) moduleReaderProxyRecycleOnClose.close();  return; }  CollectionUtils.sortIfNotEmpty(resourceRelativePaths); String prevParentRelativePath = null; ScanSpec.ScanSpecPathMatch prevParentMatchStatus = null; for (String relativePath : resourceRelativePaths) { if (relativePath.endsWith("/")) continue;  if (!this.scanSpec.enableMultiReleaseVersions && relativePath.startsWith("META-INF/versions/")) { if (subLog != null) subLog.log("Found unexpected nested versioned entry in module -- skipping: " + relativePath);  continue; }  if (isModularJar && relativePath.indexOf('/') < 0 && relativePath.endsWith(".class") && !relativePath.equals("module-info.class")) continue;  if (!checkResourcePathAcceptReject(relativePath, log)) continue;  int lastSlashIdx = relativePath.lastIndexOf('/'); String parentRelativePath = (lastSlashIdx < 0) ? "/" : relativePath.substring(0, lastSlashIdx + 1); boolean parentRelativePathChanged = !parentRelativePath.equals(prevParentRelativePath); ScanSpec.ScanSpecPathMatch parentMatchStatus = (prevParentRelativePath == null || parentRelativePathChanged) ? this.scanSpec.dirAcceptMatchStatus(parentRelativePath) : prevParentMatchStatus; prevParentRelativePath = parentRelativePath; prevParentMatchStatus = parentMatchStatus; if (parentMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_REJECTED_PATH_PREFIX) { if (subLog != null) subLog.log("Skipping rejected path: " + relativePath);  continue; }  if (this.allResourcePaths.add(relativePath)) { if (parentMatchStatus == ScanSpec.ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX || parentMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_PATH || (parentMatchStatus == ScanSpec.ScanSpecPathMatch.AT_ACCEPTED_CLASS_PACKAGE && this.scanSpec.classfileIsSpecificallyAccepted(relativePath))) { addAcceptedResource(newResource(relativePath), parentMatchStatus, false, subLog); continue; }  if (this.scanSpec.enableClassInfo && relativePath.equals("module-info.class")) addAcceptedResource(newResource(relativePath), parentMatchStatus, true, subLog);  }  }  File moduleFile = this.moduleRef.getLocationFile(); if (moduleFile != null && moduleFile.exists()) this.fileToLastModified.put(moduleFile, Long.valueOf(moduleFile.lastModified()));  if (moduleReaderProxyRecycleOnClose != null) moduleReaderProxyRecycleOnClose.close();  } catch (Throwable resourceRelativePaths) { if (moduleReaderProxyRecycleOnClose != null) try { moduleReaderProxyRecycleOnClose.close(); } catch (Throwable throwable) { resourceRelativePaths.addSuppressed(throwable); }   throw resourceRelativePaths; }  } catch (IOException e)
/* 416 */     { if (subLog != null) {
/* 417 */         subLog.log("Exception opening module " + this.moduleRef.getName(), e);
/*     */       }
/* 419 */       this.skipClasspathElement = true; }
/*     */ 
/*     */     
/* 422 */     finishScanPaths(subLog);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ModuleRef getModuleRef() {
/* 431 */     return this.moduleRef;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getModuleName() {
/* 441 */     String moduleName = this.moduleRef.getName();
/* 442 */     if (moduleName == null || moduleName.isEmpty()) {
/* 443 */       moduleName = this.moduleNameFromModuleDescriptor;
/*     */     }
/* 445 */     return (moduleName == null || moduleName.isEmpty()) ? null : moduleName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getModuleNameOrEmpty() {
/* 454 */     String moduleName = getModuleName();
/* 455 */     return (moduleName == null) ? "" : moduleName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   URI getURI() {
/* 463 */     URI uri = this.moduleRef.getLocation();
/* 464 */     if (uri == null)
/*     */     {
/* 466 */       throw new IllegalArgumentException("Module " + getModuleName() + " has a null location");
/*     */     }
/* 468 */     return uri;
/*     */   }
/*     */ 
/*     */   
/*     */   List<URI> getAllURIs() {
/* 473 */     return Collections.singletonList(getURI());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   File getFile() {
/*     */     try {
/* 482 */       URI uri = this.moduleRef.getLocation();
/* 483 */       if (uri != null && !uri.getScheme().equals("jrt")) {
/* 484 */         File file = new File(uri);
/* 485 */         if (file.exists()) {
/* 486 */           return file;
/*     */         }
/*     */       } 
/* 489 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 492 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 502 */     return this.moduleRef.toString();
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
/*     */   public boolean equals(Object obj) {
/* 517 */     if (obj == this)
/* 518 */       return true; 
/* 519 */     if (!(obj instanceof ClasspathElementModule)) {
/* 520 */       return false;
/*     */     }
/* 522 */     ClasspathElementModule other = (ClasspathElementModule)obj;
/* 523 */     return getModuleNameOrEmpty().equals(other.getModuleNameOrEmpty());
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
/*     */   public int hashCode() {
/* 536 */     return getModuleNameOrEmpty().hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ClasspathElementModule.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */