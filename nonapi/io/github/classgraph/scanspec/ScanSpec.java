/*     */ package nonapi.io.github.classgraph.scanspec;
/*     */ 
/*     */ import io.github.classgraph.ModulePathInfo;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
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
/*     */ public class ScanSpec
/*     */ {
/*  59 */   public AcceptReject.AcceptRejectWholeString packageAcceptReject = new AcceptReject.AcceptRejectWholeString('.');
/*     */ 
/*     */   
/*  62 */   public AcceptReject.AcceptRejectPrefix packagePrefixAcceptReject = new AcceptReject.AcceptRejectPrefix('.');
/*     */ 
/*     */   
/*  65 */   public AcceptReject.AcceptRejectWholeString pathAcceptReject = new AcceptReject.AcceptRejectWholeString('/');
/*     */ 
/*     */   
/*  68 */   public AcceptReject.AcceptRejectPrefix pathPrefixAcceptReject = new AcceptReject.AcceptRejectPrefix('/');
/*     */ 
/*     */   
/*  71 */   public AcceptReject.AcceptRejectWholeString classAcceptReject = new AcceptReject.AcceptRejectWholeString('.');
/*     */ 
/*     */   
/*  74 */   public AcceptReject.AcceptRejectWholeString classfilePathAcceptReject = new AcceptReject.AcceptRejectWholeString('/');
/*     */ 
/*     */   
/*  77 */   public AcceptReject.AcceptRejectWholeString classPackageAcceptReject = new AcceptReject.AcceptRejectWholeString('.');
/*     */ 
/*     */   
/*  80 */   public AcceptReject.AcceptRejectWholeString classPackagePathAcceptReject = new AcceptReject.AcceptRejectWholeString('/');
/*     */ 
/*     */   
/*  83 */   public AcceptReject.AcceptRejectWholeString moduleAcceptReject = new AcceptReject.AcceptRejectWholeString('.');
/*     */ 
/*     */   
/*  86 */   public AcceptReject.AcceptRejectLeafname jarAcceptReject = new AcceptReject.AcceptRejectLeafname('/');
/*     */ 
/*     */   
/*  89 */   public AcceptReject.AcceptRejectWholeString classpathElementResourcePathAcceptReject = new AcceptReject.AcceptRejectWholeString('/');
/*     */ 
/*     */ 
/*     */   
/*  93 */   public AcceptReject.AcceptRejectLeafname libOrExtJarAcceptReject = new AcceptReject.AcceptRejectLeafname('/');
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean scanJars = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean scanNestedJars = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean scanDirs = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean scanModules = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enableClassInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enableFieldInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enableMethodInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enableAnnotationInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enableStaticFinalFieldConstantInitializerValues;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enableInterClassDependencies;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enableExternalClasses;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enableSystemJarsAndModules;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ignoreClassVisibility;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ignoreFieldVisibility;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ignoreMethodVisibility;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean disableRuntimeInvisibleAnnotations;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean extendScanningUpwardsToExternalClasses = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> allowedURLSchemes;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public transient List<ClassLoader> addedClassLoaders;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public transient List<ClassLoader> overrideClassLoaders;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public transient List<Object> addedModuleLayers;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public transient List<Object> overrideModuleLayers;
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Object> overrideClasspath;
/*     */ 
/*     */ 
/*     */   
/*     */   public transient List<Object> classpathElementFilters;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean initializeLoadedClasses;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeTemporaryFilesAfterScan;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ignoreParentClassLoaders;
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ignoreParentModuleLayers;
/*     */ 
/*     */ 
/*     */   
/* 231 */   public ModulePathInfo modulePathInfo = new ModulePathInfo();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 256 */   public int maxBufferedJarRAMSize = 67108864;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enableMemoryMapping;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean enableMultiReleaseVersions;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sortPrefixes() {
/* 275 */     for (Field field : ScanSpec.class.getDeclaredFields()) {
/* 276 */       if (AcceptReject.class.isAssignableFrom(field.getType())) {
/*     */         try {
/* 278 */           ((AcceptReject)field.get(this)).sortPrefixes();
/* 279 */         } catch (ReflectiveOperationException e) {
/* 280 */           throw new RuntimeException("Field is not accessible: " + field, e);
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
/*     */   public void addClasspathOverride(Object overrideClasspathElement) {
/* 298 */     if (this.overrideClasspath == null) {
/* 299 */       this.overrideClasspath = new ArrayList();
/*     */     }
/* 301 */     if (overrideClasspathElement instanceof ClassLoader) {
/* 302 */       throw new IllegalArgumentException("Need to pass ClassLoader instances to overrideClassLoaders, not overrideClasspath");
/*     */     }
/*     */     
/* 305 */     this.overrideClasspath
/* 306 */       .add((
/* 307 */         overrideClasspathElement instanceof String || overrideClasspathElement instanceof java.net.URL || overrideClasspathElement instanceof java.net.URI) ? overrideClasspathElement : 
/* 308 */         overrideClasspathElement.toString());
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
/*     */   public void filterClasspathElements(Object filterLambda) {
/* 321 */     if (!(filterLambda instanceof io.github.classgraph.ClassGraph.ClasspathElementFilter) && !(filterLambda instanceof io.github.classgraph.ClassGraph.ClasspathElementURLFilter))
/*     */     {
/* 323 */       throw new IllegalArgumentException();
/*     */     }
/* 325 */     if (this.classpathElementFilters == null) {
/* 326 */       this.classpathElementFilters = new ArrayList(2);
/*     */     }
/* 328 */     this.classpathElementFilters.add(filterLambda);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addClassLoader(ClassLoader classLoader) {
/* 339 */     if (this.addedClassLoaders == null) {
/* 340 */       this.addedClassLoaders = new ArrayList<>();
/*     */     }
/* 342 */     if (classLoader != null) {
/* 343 */       this.addedClassLoaders.add(classLoader);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enableURLScheme(String scheme) {
/* 354 */     if (scheme == null || scheme.length() < 2) {
/* 355 */       throw new IllegalArgumentException("URL schemes must contain at least two characters");
/*     */     }
/* 357 */     if (this.allowedURLSchemes == null) {
/* 358 */       this.allowedURLSchemes = new HashSet<>();
/*     */     }
/* 360 */     this.allowedURLSchemes.add(scheme.toLowerCase());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void overrideClassLoaders(ClassLoader... overrideClassLoaders) {
/* 371 */     if (overrideClassLoaders.length == 0) {
/* 372 */       throw new IllegalArgumentException("At least one override ClassLoader must be provided");
/*     */     }
/* 374 */     this.addedClassLoaders = null;
/* 375 */     this.overrideClassLoaders = new ArrayList<>();
/* 376 */     for (ClassLoader classLoader : overrideClassLoaders) {
/* 377 */       if (classLoader != null) {
/* 378 */         this.overrideClassLoaders.add(classLoader);
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
/*     */   private static boolean isModuleLayer(Object moduleLayer) {
/* 391 */     if (moduleLayer == null) {
/* 392 */       throw new IllegalArgumentException("ModuleLayer references must not be null");
/*     */     }
/* 394 */     for (Class<?> currClass = moduleLayer.getClass(); currClass != null; 
/* 395 */       currClass = currClass.getSuperclass()) {
/* 396 */       if (currClass.getName().equals("java.lang.ModuleLayer")) {
/* 397 */         return true;
/*     */       }
/*     */     } 
/* 400 */     return false;
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
/*     */   public void addModuleLayer(Object moduleLayer) {
/* 415 */     if (!isModuleLayer(moduleLayer)) {
/* 416 */       throw new IllegalArgumentException("moduleLayer must be of type java.lang.ModuleLayer");
/*     */     }
/* 418 */     if (this.addedModuleLayers == null) {
/* 419 */       this.addedModuleLayers = new ArrayList();
/*     */     }
/* 421 */     this.addedModuleLayers.add(moduleLayer);
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
/*     */   public void overrideModuleLayers(Object... overrideModuleLayers) {
/* 436 */     if (overrideModuleLayers == null) {
/* 437 */       throw new IllegalArgumentException("overrideModuleLayers cannot be null");
/*     */     }
/* 439 */     if (overrideModuleLayers.length == 0) {
/* 440 */       throw new IllegalArgumentException("At least one override ModuleLayer must be provided");
/*     */     }
/* 442 */     for (Object moduleLayer : overrideModuleLayers) {
/* 443 */       if (!isModuleLayer(moduleLayer)) {
/* 444 */         throw new IllegalArgumentException("moduleLayer must be of type java.lang.ModuleLayer");
/*     */       }
/*     */     } 
/* 447 */     this.addedModuleLayers = null;
/* 448 */     this.overrideModuleLayers = new ArrayList();
/* 449 */     Collections.addAll(this.overrideModuleLayers, overrideModuleLayers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum ScanSpecPathMatch
/*     */   {
/* 459 */     HAS_REJECTED_PATH_PREFIX,
/*     */     
/* 461 */     HAS_ACCEPTED_PATH_PREFIX,
/*     */     
/* 463 */     AT_ACCEPTED_PATH,
/*     */     
/* 465 */     ANCESTOR_OF_ACCEPTED_PATH,
/*     */     
/* 467 */     AT_ACCEPTED_CLASS_PACKAGE,
/*     */     
/* 469 */     NOT_WITHIN_ACCEPTED_PATH;
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
/*     */   public ScanSpecPathMatch dirAcceptMatchStatus(String relativePath) {
/* 482 */     if (this.pathAcceptReject.isRejected(relativePath) || this.pathPrefixAcceptReject.isRejected(relativePath))
/*     */     {
/* 484 */       return ScanSpecPathMatch.HAS_REJECTED_PATH_PREFIX;
/*     */     }
/*     */     
/* 487 */     if (this.pathAcceptReject.acceptIsEmpty() && this.classPackagePathAcceptReject.acceptIsEmpty())
/*     */     {
/* 489 */       return (relativePath.isEmpty() || relativePath.equals("/")) ? ScanSpecPathMatch.AT_ACCEPTED_PATH : 
/* 490 */         ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX;
/*     */     }
/*     */ 
/*     */     
/* 494 */     if (this.pathAcceptReject.isSpecificallyAcceptedAndNotRejected(relativePath))
/*     */     {
/* 496 */       return ScanSpecPathMatch.AT_ACCEPTED_PATH;
/*     */     }
/* 498 */     if (this.classPackagePathAcceptReject.isSpecificallyAcceptedAndNotRejected(relativePath))
/*     */     {
/* 500 */       return ScanSpecPathMatch.AT_ACCEPTED_CLASS_PACKAGE;
/*     */     }
/*     */ 
/*     */     
/* 504 */     if (this.pathPrefixAcceptReject.isSpecificallyAccepted(relativePath))
/*     */     {
/* 506 */       return ScanSpecPathMatch.HAS_ACCEPTED_PATH_PREFIX;
/*     */     }
/*     */ 
/*     */     
/* 510 */     if (relativePath
/*     */       
/* 512 */       .equals("/") || this.pathAcceptReject
/*     */       
/* 514 */       .acceptHasPrefix(relativePath) || this.classfilePathAcceptReject
/*     */       
/* 516 */       .acceptHasPrefix(relativePath)) {
/* 517 */       return ScanSpecPathMatch.ANCESTOR_OF_ACCEPTED_PATH;
/*     */     }
/*     */ 
/*     */     
/* 521 */     return ScanSpecPathMatch.NOT_WITHIN_ACCEPTED_PATH;
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
/*     */   public boolean classfileIsSpecificallyAccepted(String relativePath) {
/* 534 */     return this.classfilePathAcceptReject.isSpecificallyAcceptedAndNotRejected(relativePath);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean classOrPackageIsRejected(String className) {
/* 545 */     return (this.classAcceptReject.isRejected(className) || this.packagePrefixAcceptReject.isRejected(className));
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
/*     */   public void log(LogNode log) {
/* 557 */     if (log != null) {
/* 558 */       LogNode scanSpecLog = log.log("ScanSpec:");
/* 559 */       for (Field field : ScanSpec.class.getDeclaredFields()) {
/*     */         try {
/* 561 */           scanSpecLog.log(field.getName() + ": " + field.get(this));
/* 562 */         } catch (ReflectiveOperationException reflectiveOperationException) {}
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\scanspec\ScanSpec.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */