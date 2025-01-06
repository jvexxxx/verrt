/*     */ package nonapi.io.github.classgraph.classpath;
/*     */ 
/*     */ import io.github.classgraph.ClassGraph;
/*     */ import java.io.File;
/*     */ import java.io.IOError;
/*     */ import java.lang.reflect.Array;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ import nonapi.io.github.classgraph.utils.FastPathResolver;
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
/*     */ public class ClasspathOrder
/*     */ {
/*     */   private final ScanSpec scanSpec;
/*     */   public ReflectionUtils reflectionUtils;
/*  67 */   private final Set<String> classpathEntryUniqueResolvedPaths = new HashSet<>();
/*     */ 
/*     */   
/*  70 */   private final List<ClasspathEntry> order = new ArrayList<>();
/*     */ 
/*     */   
/*  73 */   private static final List<String> AUTOMATIC_PACKAGE_ROOT_SUFFIXES = new ArrayList<>();
/*     */ 
/*     */   
/*  76 */   private static final Pattern schemeMatcher = Pattern.compile("^[a-zA-Z][a-zA-Z+\\-.]+:");
/*     */   
/*     */   static {
/*  79 */     for (String prefix : ClassLoaderHandlerRegistry.AUTOMATIC_PACKAGE_ROOT_PREFIXES) {
/*  80 */       AUTOMATIC_PACKAGE_ROOT_SUFFIXES.add("!/" + prefix.substring(0, prefix.length() - 1));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ClasspathEntry
/*     */   {
/*     */     public final Object classpathEntryObj;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final ClassLoader classLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ClasspathEntry(Object classpathEntryObj, ClassLoader classLoader) {
/* 103 */       this.classpathEntryObj = classpathEntryObj;
/* 104 */       this.classLoader = classLoader;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 109 */       return Objects.hash(new Object[] { this.classpathEntryObj });
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/* 114 */       if (obj == this)
/* 115 */         return true; 
/* 116 */       if (!(obj instanceof ClasspathEntry)) {
/* 117 */         return false;
/*     */       }
/* 119 */       ClasspathEntry other = (ClasspathEntry)obj;
/* 120 */       return Objects.equals(this.classpathEntryObj, other.classpathEntryObj);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 125 */       return this.classpathEntryObj + " [" + this.classLoader + "]";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ClasspathOrder(ScanSpec scanSpec, ReflectionUtils reflectionUtils) {
/* 136 */     this.scanSpec = scanSpec;
/* 137 */     this.reflectionUtils = reflectionUtils;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ClasspathEntry> getOrder() {
/* 146 */     return this.order;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> getClasspathEntryUniqueResolvedPaths() {
/* 155 */     return this.classpathEntryUniqueResolvedPaths;
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
/*     */   private boolean filter(URL classpathElementURL, String classpathElementPath) {
/* 168 */     if (this.scanSpec.classpathElementFilters != null) {
/* 169 */       for (Object filterObj : this.scanSpec.classpathElementFilters) {
/* 170 */         if ((classpathElementURL != null && filterObj instanceof ClassGraph.ClasspathElementURLFilter && 
/* 171 */           !((ClassGraph.ClasspathElementURLFilter)filterObj).includeClasspathElement(classpathElementURL)) || (classpathElementPath != null && filterObj instanceof ClassGraph.ClasspathElementFilter && 
/*     */ 
/*     */           
/* 174 */           !((ClassGraph.ClasspathElementFilter)filterObj).includeClasspathElement(classpathElementPath))) {
/* 175 */           return false;
/*     */         }
/*     */       } 
/*     */     }
/* 179 */     return true;
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
/*     */   boolean addSystemClasspathEntry(String pathEntry, ClassLoader classLoader) {
/* 193 */     if (this.classpathEntryUniqueResolvedPaths.add(pathEntry)) {
/* 194 */       this.order.add(new ClasspathEntry(pathEntry, classLoader));
/* 195 */       return true;
/*     */     } 
/* 197 */     return false;
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
/*     */   private boolean addClasspathEntry(Object pathElement, String pathElementStr, ClassLoader classLoader, ScanSpec scanSpec) {
/* 218 */     String pathElementStrWithoutSuffix = pathElementStr;
/* 219 */     boolean hasSuffix = false;
/* 220 */     for (String suffix : AUTOMATIC_PACKAGE_ROOT_SUFFIXES) {
/* 221 */       if (pathElementStr.endsWith(suffix)) {
/*     */         
/* 223 */         pathElementStrWithoutSuffix = pathElementStr.substring(0, pathElementStr
/* 224 */             .length() - suffix.length());
/* 225 */         hasSuffix = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 229 */     if (pathElement instanceof URL || pathElement instanceof URI || pathElement instanceof Path || pathElement instanceof File) {
/*     */       
/* 231 */       Object pathElementWithoutSuffix = pathElement;
/* 232 */       if (hasSuffix) {
/*     */         
/*     */         try {
/*     */ 
/*     */ 
/*     */           
/* 238 */           pathElementWithoutSuffix = (pathElement instanceof URL) ? new URL(pathElementStrWithoutSuffix) : ((pathElement instanceof URI) ? new URI(pathElementStrWithoutSuffix) : ((pathElement instanceof Path) ? Paths.get(pathElementStrWithoutSuffix, new String[0]) : pathElementStrWithoutSuffix));
/* 239 */         } catch (MalformedURLException|java.net.URISyntaxException|java.nio.file.InvalidPathException e) {
/*     */ 
/*     */           
/*     */           try {
/*     */             
/* 244 */             pathElementWithoutSuffix = (pathElement instanceof URL) ? new URL("file:" + pathElementStrWithoutSuffix) : ((pathElement instanceof URI) ? new URI("file:" + pathElementStrWithoutSuffix) : pathElementStrWithoutSuffix);
/* 245 */           } catch (MalformedURLException|java.net.URISyntaxException|java.nio.file.InvalidPathException e2) {
/* 246 */             return false;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 251 */       if (this.classpathEntryUniqueResolvedPaths.add(pathElementStrWithoutSuffix)) {
/*     */         
/* 253 */         this.order.add(new ClasspathEntry(pathElementWithoutSuffix, classLoader));
/* 254 */         return true;
/*     */       } 
/*     */     } else {
/* 257 */       String pathElementStrResolved = FastPathResolver.resolve(FileUtils.currDirPath(), pathElementStrWithoutSuffix);
/*     */       
/* 259 */       if (scanSpec.overrideClasspath == null && (
/* 260 */         SystemJarFinder.getJreLibOrExtJars().contains(pathElementStrResolved) || pathElementStrResolved
/* 261 */         .equals(SystemJarFinder.getJreRtJarPath())))
/*     */       {
/*     */         
/* 264 */         return false;
/*     */       }
/* 266 */       if (this.classpathEntryUniqueResolvedPaths.add(pathElementStrResolved)) {
/* 267 */         this.order.add(new ClasspathEntry(pathElementStrResolved, classLoader));
/* 268 */         return true;
/*     */       } 
/*     */     } 
/* 271 */     return false;
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
/*     */   public boolean addClasspathEntry(Object pathElement, ClassLoader classLoader, ScanSpec scanSpec, LogNode log) {
/* 292 */     if (pathElement == null) {
/* 293 */       return false;
/*     */     }
/*     */     
/* 296 */     if (pathElement instanceof Path) {
/*     */       
/*     */       try {
/* 299 */         pathElementStr = ((Path)pathElement).toUri().toString();
/*     */ 
/*     */         
/* 302 */         if (pathElementStr.startsWith("file:///")) {
/* 303 */           pathElementStr = ((Path)pathElement).toFile().toString();
/*     */         }
/* 305 */       } catch (IOError|SecurityException e) {
/* 306 */         pathElementStr = pathElement.toString();
/*     */       } 
/*     */     } else {
/* 309 */       pathElementStr = pathElement.toString();
/*     */     } 
/* 311 */     String pathElementStr = FastPathResolver.resolve(FileUtils.currDirPath(), pathElementStr);
/* 312 */     if (pathElementStr.isEmpty()) {
/* 313 */       return false;
/*     */     }
/* 315 */     URL pathElementURL = null;
/* 316 */     boolean hasWildcardSuffix = false;
/*     */     
/* 318 */     if (pathElementStr.endsWith("/*") || pathElementStr.endsWith("\\*")) {
/* 319 */       hasWildcardSuffix = true;
/* 320 */       pathElementStr = pathElementStr.substring(0, pathElementStr.length() - 2);
/*     */     }
/* 322 */     else if (pathElementStr.equals("*")) {
/* 323 */       hasWildcardSuffix = true;
/* 324 */       pathElementStr = "";
/*     */     } else {
/*     */       
/* 327 */       Matcher m1 = schemeMatcher.matcher(pathElementStr);
/* 328 */       if (m1.find()) {
/*     */ 
/*     */         
/*     */         try {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 336 */           pathElementURL = (pathElement instanceof URL) ? (URL)pathElement : ((pathElement instanceof URI) ? ((URI)pathElement).toURL() : ((pathElement instanceof Path) ? ((Path)pathElement).toUri().toURL() : ((pathElement instanceof File) ? ((File)pathElement).toURI().toURL() : null)));
/* 337 */         } catch (MalformedURLException|IllegalArgumentException|IOError|SecurityException malformedURLException) {}
/*     */ 
/*     */         
/* 340 */         if (pathElementURL == null) {
/*     */           
/* 342 */           String urlStr = pathElementStr.replace("%", "%25");
/*     */           try {
/* 344 */             pathElementURL = new URL(urlStr);
/* 345 */           } catch (MalformedURLException e) {
/*     */             try {
/* 347 */               pathElementURL = (new File(urlStr)).toURI().toURL();
/* 348 */             } catch (MalformedURLException|IllegalArgumentException|IOError|SecurityException e1) {
/*     */ 
/*     */               
/*     */               try {
/* 352 */                 pathElementURL = new URL(pathElementStr);
/* 353 */               } catch (MalformedURLException malformedURLException) {}
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 359 */         if (pathElementURL == null && 
/* 360 */           log != null) {
/* 361 */           log.log("Failed to convert classpath element to URL: " + pathElement);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 366 */     if (pathElementURL != null || pathElement instanceof URI || pathElement instanceof File || pathElement instanceof Path) {
/*     */       
/* 368 */       if (!filter(pathElementURL, pathElementStr)) {
/* 369 */         if (log != null) {
/* 370 */           log.log("Classpath element did not match filter criterion, skipping: " + pathElementStr);
/*     */         }
/* 372 */         return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 378 */       Object classpathElementObj = (pathElement instanceof File) ? pathElementStr : ((pathElementURL != null) ? pathElementURL : pathElement);
/* 379 */       if (addClasspathEntry(classpathElementObj, pathElementStr, classLoader, scanSpec)) {
/* 380 */         if (log != null) {
/* 381 */           log.log("Found classpath element: " + pathElementStr);
/*     */         }
/* 383 */         return true;
/*     */       } 
/* 385 */       if (log != null) {
/* 386 */         log.log("Ignoring duplicate classpath element: " + pathElementStr);
/*     */       }
/* 388 */       return false;
/*     */     } 
/*     */     
/* 391 */     if (hasWildcardSuffix) {
/*     */ 
/*     */       
/* 394 */       String baseDirPath = pathElementStr;
/* 395 */       String baseDirPathResolved = FastPathResolver.resolve(FileUtils.currDirPath(), baseDirPath);
/* 396 */       if (!filter(pathElementURL, baseDirPath) || (
/* 397 */         !baseDirPathResolved.equals(baseDirPath) && !filter(pathElementURL, baseDirPathResolved))) {
/* 398 */         if (log != null) {
/* 399 */           log.log("Classpath element did not match filter criterion, skipping: " + pathElementStr);
/*     */         }
/* 401 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 405 */       File baseDir = new File(baseDirPathResolved);
/* 406 */       if (!baseDir.exists()) {
/* 407 */         if (log != null) {
/* 408 */           log.log("Directory does not exist for wildcard classpath element: " + pathElementStr);
/*     */         }
/* 410 */         return false;
/*     */       } 
/* 412 */       if (!FileUtils.canRead(baseDir)) {
/* 413 */         if (log != null) {
/* 414 */           log.log("Cannot read directory for wildcard classpath element: " + pathElementStr);
/*     */         }
/* 416 */         return false;
/*     */       } 
/* 418 */       if (!baseDir.isDirectory()) {
/* 419 */         if (log != null) {
/* 420 */           log.log("Wildcard is appended to something other than a directory: " + pathElementStr);
/*     */         }
/* 422 */         return false;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 427 */       LogNode dirLog = (log == null) ? null : log.log("Adding classpath elements from wildcarded directory: " + pathElementStr);
/* 428 */       File[] baseDirFiles = baseDir.listFiles();
/* 429 */       if (baseDirFiles != null) {
/* 430 */         for (File fileInDir : baseDirFiles) {
/* 431 */           String name = fileInDir.getName();
/* 432 */           if (!name.equals(".") && !name.equals("..")) {
/*     */             
/* 434 */             String fileInDirPath = fileInDir.getPath();
/* 435 */             String fileInDirPathResolved = FastPathResolver.resolve(FileUtils.currDirPath(), fileInDirPath);
/*     */             
/* 437 */             if (addClasspathEntry(fileInDirPathResolved, fileInDirPathResolved, classLoader, scanSpec)) {
/*     */               
/* 439 */               if (dirLog != null) {
/* 440 */                 dirLog.log("Found classpath element: " + fileInDirPath + (
/* 441 */                     fileInDirPath.equals(fileInDirPathResolved) ? "" : (
/* 442 */                     " -> " + fileInDirPathResolved)));
/*     */               }
/*     */             }
/* 445 */             else if (dirLog != null) {
/* 446 */               dirLog.log("Ignoring duplicate classpath element: " + fileInDirPath + (
/* 447 */                   fileInDirPath.equals(fileInDirPathResolved) ? "" : (
/* 448 */                   " -> " + fileInDirPathResolved)));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 453 */         return true;
/*     */       } 
/* 455 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 459 */     if (pathElementStr.indexOf('*') >= 0) {
/* 460 */       if (log != null) {
/* 461 */         log.log("Wildcard classpath elements can only end with a suffix of \"/*\", can't use globs elsewhere in the path: " + pathElementStr);
/*     */       }
/*     */       
/* 464 */       return false;
/*     */     } 
/* 466 */     String pathElementResolved = FastPathResolver.resolve(FileUtils.currDirPath(), pathElementStr);
/* 467 */     if (!filter(pathElementURL, pathElementStr) || (!pathElementResolved.equals(pathElementStr) && 
/* 468 */       !filter(pathElementURL, pathElementResolved))) {
/* 469 */       if (log != null) {
/* 470 */         log.log("Classpath element did not match filter criterion, skipping: " + pathElementStr + (
/* 471 */             pathElementStr.equals(pathElementResolved) ? "" : (" -> " + pathElementResolved)));
/*     */       }
/* 473 */       return false;
/*     */     } 
/* 475 */     if (pathElementResolved.startsWith("//")) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 480 */         File file = new File(pathElementResolved);
/* 481 */         if (addClasspathEntry(file, pathElementResolved, classLoader, scanSpec)) {
/* 482 */           if (log != null) {
/* 483 */             log.log("Found classpath element: " + file + (
/* 484 */                 pathElementStr.equals(pathElementResolved) ? "" : (
/* 485 */                 " -> " + pathElementResolved)));
/*     */           }
/* 487 */           return true;
/*     */         } 
/* 489 */         if (log != null) {
/* 490 */           log.log("Ignoring duplicate classpath element: " + pathElementStr + (
/* 491 */               pathElementStr.equals(pathElementResolved) ? "" : (
/* 492 */               " -> " + pathElementResolved)));
/*     */         }
/* 494 */         return false;
/*     */       }
/* 496 */       catch (Exception exception) {}
/*     */     }
/*     */ 
/*     */     
/* 500 */     if (addClasspathEntry(pathElementResolved, pathElementResolved, classLoader, scanSpec)) {
/* 501 */       if (log != null) {
/* 502 */         log.log("Found classpath element: " + pathElementStr + (
/* 503 */             pathElementStr.equals(pathElementResolved) ? "" : (" -> " + pathElementResolved)));
/*     */       }
/* 505 */       return true;
/*     */     } 
/* 507 */     if (log != null) {
/* 508 */       log.log("Ignoring duplicate classpath element: " + pathElementStr + (
/* 509 */           pathElementStr.equals(pathElementResolved) ? "" : (" -> " + pathElementResolved)));
/*     */     }
/* 511 */     return false;
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
/*     */   public boolean addClasspathEntries(List<Object> overrideClasspath, ClassLoader classLoader, ScanSpec scanSpec, LogNode log) {
/* 531 */     if (overrideClasspath == null || overrideClasspath.isEmpty()) {
/* 532 */       return false;
/*     */     }
/* 534 */     for (Object pathElement : overrideClasspath) {
/* 535 */       addClasspathEntry(pathElement, classLoader, scanSpec, log);
/*     */     }
/* 537 */     return true;
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
/*     */   public boolean addClasspathPathStr(String pathStr, ClassLoader classLoader, ScanSpec scanSpec, LogNode log) {
/* 556 */     if (pathStr == null || pathStr.isEmpty()) {
/* 557 */       return false;
/*     */     }
/* 559 */     String[] parts = JarUtils.smartPathSplit(pathStr, scanSpec);
/* 560 */     if (parts.length == 0) {
/* 561 */       return false;
/*     */     }
/* 563 */     for (String pathElement : parts) {
/* 564 */       addClasspathEntry(pathElement, classLoader, scanSpec, log);
/*     */     }
/* 566 */     return true;
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
/*     */   public boolean addClasspathEntryObject(Object pathObject, ClassLoader classLoader, ScanSpec scanSpec, LogNode log) {
/* 590 */     boolean valid = false;
/* 591 */     if (pathObject != null) {
/* 592 */       if (pathObject instanceof URL || pathObject instanceof URI || pathObject instanceof Path || pathObject instanceof File) {
/*     */         
/* 594 */         valid |= addClasspathEntry(pathObject, classLoader, scanSpec, log);
/* 595 */       } else if (pathObject instanceof Iterable) {
/* 596 */         for (Object elt : pathObject) {
/* 597 */           valid |= addClasspathEntryObject(elt, classLoader, scanSpec, log);
/*     */         }
/*     */       } else {
/* 600 */         Class<?> valClass = pathObject.getClass();
/* 601 */         if (valClass.isArray()) {
/* 602 */           for (int j = 0, n = Array.getLength(pathObject); j < n; j++) {
/* 603 */             Object elt = Array.get(pathObject, j);
/* 604 */             valid |= addClasspathEntryObject(elt, classLoader, scanSpec, log);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 609 */           valid |= addClasspathPathStr(pathObject.toString(), classLoader, scanSpec, log);
/*     */         } 
/*     */       } 
/*     */     }
/* 613 */     return valid;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classpath\ClasspathOrder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */