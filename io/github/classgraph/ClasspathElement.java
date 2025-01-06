/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import nonapi.io.github.classgraph.concurrency.WorkQueue;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ import nonapi.io.github.classgraph.utils.FileUtils;
/*     */ import nonapi.io.github.classgraph.utils.JarUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class ClasspathElement
/*     */   implements Comparable<ClasspathElement>
/*     */ {
/*     */   int classpathElementIdx;
/*     */   List<String> nestedClasspathRootPrefixes;
/*     */   boolean skipClasspathElement;
/*     */   boolean containsSpecificallyAcceptedClasspathElementResourcePath;
/*     */   final int classpathElementIdxWithinParent;
/*  85 */   Collection<ClasspathElement> childClasspathElements = new ConcurrentLinkedQueue<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   protected final List<Resource> acceptedResources = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   protected List<Resource> acceptedClassfileResources = new ArrayList<>();
/*     */ 
/*     */   
/* 100 */   protected final Map<File, Long> fileToLastModified = new ConcurrentHashMap<>();
/*     */ 
/*     */   
/* 103 */   protected final AtomicBoolean scanned = new AtomicBoolean(false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassLoader classLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String packageRootPrefix;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String moduleNameFromModuleDescriptor;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final ScanSpec scanSpec;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ClasspathElement(Scanner.ClasspathEntryWorkUnit workUnit, ScanSpec scanSpec) {
/* 131 */     this.packageRootPrefix = workUnit.packageRootPrefix;
/* 132 */     this.classpathElementIdxWithinParent = workUnit.classpathElementIdxWithinParent;
/* 133 */     this.classLoader = workUnit.classLoader;
/* 134 */     this.scanSpec = scanSpec;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(ClasspathElement other) {
/* 142 */     return this.classpathElementIdxWithinParent - other.classpathElementIdxWithinParent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ClassLoader getClassLoader() {
/* 153 */     return this.classLoader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int getNumClassfileMatches() {
/* 162 */     return (this.acceptedClassfileResources == null) ? 0 : this.acceptedClassfileResources.size();
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
/*     */   protected boolean checkResourcePathAcceptReject(String relativePath, LogNode log) {
/* 178 */     if (!this.scanSpec.classpathElementResourcePathAcceptReject.acceptAndRejectAreEmpty()) {
/* 179 */       if (this.scanSpec.classpathElementResourcePathAcceptReject.isRejected(relativePath)) {
/* 180 */         if (log != null) {
/* 181 */           log.log("Reached rejected classpath element resource path, stopping scanning: " + relativePath);
/*     */         }
/* 183 */         return false;
/*     */       } 
/* 185 */       if (this.scanSpec.classpathElementResourcePathAcceptReject.isSpecificallyAccepted(relativePath)) {
/* 186 */         if (log != null) {
/* 187 */           log.log("Reached specifically accepted classpath element resource path: " + relativePath);
/*     */         }
/* 189 */         this.containsSpecificallyAcceptedClasspathElementResourcePath = true;
/*     */       } 
/*     */     } 
/* 192 */     return true;
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
/*     */   void maskClassfiles(int classpathIdx, Set<String> classpathRelativePathsFound, LogNode log) {
/* 215 */     List<Resource> acceptedClassfileResourcesFiltered = new ArrayList<>(this.acceptedClassfileResources.size());
/* 216 */     boolean foundMasked = false;
/* 217 */     for (Resource res : this.acceptedClassfileResources) {
/* 218 */       String pathRelativeToPackageRoot = res.getPath();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 223 */       if (!pathRelativeToPackageRoot.equals("module-info.class") && 
/* 224 */         !pathRelativeToPackageRoot.equals("package-info.class") && 
/* 225 */         !pathRelativeToPackageRoot.endsWith("/package-info.class") && 
/*     */         
/* 227 */         !classpathRelativePathsFound.add(pathRelativeToPackageRoot)) {
/*     */ 
/*     */         
/* 230 */         foundMasked = true;
/* 231 */         if (log != null)
/* 232 */           log.log(String.format("%06d-1", new Object[] { Integer.valueOf(classpathIdx) }), "Ignoring duplicate (masked) class " + 
/* 233 */               JarUtils.classfilePathToClassName(pathRelativeToPackageRoot) + " found at " + res); 
/*     */         continue;
/*     */       } 
/* 236 */       acceptedClassfileResourcesFiltered.add(res);
/*     */     } 
/*     */     
/* 239 */     if (foundMasked)
/*     */     {
/*     */ 
/*     */       
/* 243 */       this.acceptedClassfileResources = acceptedClassfileResourcesFiltered;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addAcceptedResource(Resource resource, ScanSpec.ScanSpecPathMatch parentMatchStatus, boolean isClassfileOnly, LogNode log) {
/* 264 */     String path = resource.getPath();
/* 265 */     boolean isClassFile = FileUtils.isClassfile(path);
/* 266 */     boolean isAccepted = false;
/* 267 */     if (isClassFile) {
/*     */       
/* 269 */       if (this.scanSpec.enableClassInfo && !this.scanSpec.classfilePathAcceptReject.isRejected(path)) {
/*     */         
/* 271 */         this.acceptedClassfileResources.add(resource);
/* 272 */         isAccepted = true;
/*     */       } 
/*     */     } else {
/*     */       
/* 276 */       isAccepted = true;
/*     */     } 
/*     */     
/* 279 */     if (!isClassfileOnly)
/*     */     {
/* 281 */       this.acceptedResources.add(resource);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 286 */     if (log != null && isAccepted) {
/* 287 */       String logStr, type = isClassFile ? "classfile" : "resource";
/*     */       
/* 289 */       switch (parentMatchStatus) {
/*     */         case HAS_ACCEPTED_PATH_PREFIX:
/* 291 */           logStr = "Found " + type + " within subpackage of accepted package: ";
/*     */           break;
/*     */         case AT_ACCEPTED_PATH:
/* 294 */           logStr = "Found " + type + " within accepted package: ";
/*     */           break;
/*     */         case AT_ACCEPTED_CLASS_PACKAGE:
/* 297 */           logStr = "Found specifically-accepted " + type + ": ";
/*     */           break;
/*     */         default:
/* 300 */           logStr = "Found accepted " + type + ": ";
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 305 */       resource.scanLog = log.log("0:" + path, logStr + path + (
/* 306 */           path.equals(resource.getPathRelativeToClasspathElement()) ? "" : (
/* 307 */           " ; full path: " + resource.getPathRelativeToClasspathElement())));
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
/*     */   protected void finishScanPaths(LogNode log) {
/* 320 */     if (log != null) {
/* 321 */       if (this.acceptedResources.isEmpty() && this.acceptedClassfileResources.isEmpty()) {
/* 322 */         log.log(this.scanSpec.enableClassInfo ? "No accepted classfiles or resources found" : 
/* 323 */             "Classfile scanning is disabled, and no accepted resources found");
/* 324 */       } else if (this.acceptedResources.isEmpty()) {
/* 325 */         log.log("No accepted resources found");
/* 326 */       } else if (this.acceptedClassfileResources.isEmpty()) {
/* 327 */         log.log(this.scanSpec.enableClassInfo ? "No accepted classfiles found" : 
/* 328 */             "Classfile scanning is disabled");
/*     */       } 
/*     */     }
/* 331 */     if (log != null) {
/* 332 */       log.addElapsedTime();
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
/*     */   
/*     */   protected LogNode log(int classpathElementIdx, String msg, LogNode log) {
/* 350 */     return log.log(String.format("%07d", new Object[] { Integer.valueOf(classpathElementIdx) }), msg);
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
/*     */   protected LogNode log(int classpathElementIdx, String msg, Throwable t, LogNode log) {
/* 367 */     return log.log(String.format("%07d", new Object[] { Integer.valueOf(classpathElementIdx) }), msg, t);
/*     */   }
/*     */   
/*     */   abstract void open(WorkQueue<Scanner.ClasspathEntryWorkUnit> paramWorkQueue, LogNode paramLogNode) throws InterruptedException;
/*     */   
/*     */   abstract void scanPaths(LogNode paramLogNode);
/*     */   
/*     */   abstract Resource getResource(String paramString);
/*     */   
/*     */   abstract URI getURI();
/*     */   
/*     */   abstract List<URI> getAllURIs();
/*     */   
/*     */   abstract File getFile();
/*     */   
/*     */   abstract String getModuleName();
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ClasspathElement.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */