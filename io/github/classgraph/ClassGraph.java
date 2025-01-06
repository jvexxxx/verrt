/*      */ package io.github.classgraph;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.net.URI;
/*      */ import java.net.URL;
/*      */ import java.util.List;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.ExecutionException;
/*      */ import java.util.concurrent.ExecutorService;
/*      */ import java.util.concurrent.Future;
/*      */ import java.util.regex.Pattern;
/*      */ import nonapi.io.github.classgraph.classpath.SystemJarFinder;
/*      */ import nonapi.io.github.classgraph.concurrency.AutoCloseableExecutorService;
/*      */ import nonapi.io.github.classgraph.concurrency.InterruptionChecker;
/*      */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*      */ import nonapi.io.github.classgraph.scanspec.AcceptReject;
/*      */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*      */ import nonapi.io.github.classgraph.utils.JarUtils;
/*      */ import nonapi.io.github.classgraph.utils.LogNode;
/*      */ import nonapi.io.github.classgraph.utils.VersionFinder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ClassGraph
/*      */ {
/*   69 */   ScanSpec scanSpec = new ScanSpec();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   75 */   static final int DEFAULT_NUM_WORKER_THREADS = Math.max(2, 
/*      */ 
/*      */       
/*   78 */       (int)Math.ceil(
/*      */         
/*   80 */         Math.min(4.0D, Runtime.getRuntime().availableProcessors() * 0.75D) + 
/*      */         
/*   82 */         Runtime.getRuntime().availableProcessors() * 1.25D));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public enum CircumventEncapsulationMethod
/*      */   {
/*   94 */     NONE,
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  100 */     NARCISSUS,
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  106 */     JVM_DRIVER;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  128 */   public static CircumventEncapsulationMethod CIRCUMVENT_ENCAPSULATION = CircumventEncapsulationMethod.NONE;
/*      */ 
/*      */ 
/*      */   
/*      */   private final ReflectionUtils reflectionUtils;
/*      */ 
/*      */ 
/*      */   
/*      */   private LogNode topLevelLog;
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph() {
/*  141 */     this.reflectionUtils = new ReflectionUtils();
/*      */     
/*  143 */     ScanResult.init(this.reflectionUtils);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getVersion() {
/*  152 */     return VersionFinder.getVersion();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph verbose() {
/*  163 */     if (this.topLevelLog == null) {
/*  164 */       this.topLevelLog = new LogNode();
/*      */     }
/*  166 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph verbose(boolean verbose) {
/*  177 */     if (verbose) {
/*  178 */       verbose();
/*      */     }
/*  180 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableAllInfo() {
/*  198 */     enableClassInfo();
/*  199 */     enableFieldInfo();
/*  200 */     enableMethodInfo();
/*  201 */     enableAnnotationInfo();
/*  202 */     enableStaticFinalFieldConstantInitializerValues();
/*  203 */     ignoreClassVisibility();
/*  204 */     ignoreFieldVisibility();
/*  205 */     ignoreMethodVisibility();
/*  206 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableClassInfo() {
/*  216 */     this.scanSpec.enableClassInfo = true;
/*  217 */     this.scanSpec.enableMultiReleaseVersions = false;
/*  218 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph ignoreClassVisibility() {
/*  228 */     enableClassInfo();
/*  229 */     this.scanSpec.ignoreClassVisibility = true;
/*  230 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableMethodInfo() {
/*  241 */     enableClassInfo();
/*  242 */     this.scanSpec.enableMethodInfo = true;
/*  243 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph ignoreMethodVisibility() {
/*  254 */     enableClassInfo();
/*  255 */     enableMethodInfo();
/*  256 */     this.scanSpec.ignoreMethodVisibility = true;
/*  257 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableFieldInfo() {
/*  268 */     enableClassInfo();
/*  269 */     this.scanSpec.enableFieldInfo = true;
/*  270 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph ignoreFieldVisibility() {
/*  281 */     enableClassInfo();
/*  282 */     enableFieldInfo();
/*  283 */     this.scanSpec.ignoreFieldVisibility = true;
/*  284 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableStaticFinalFieldConstantInitializerValues() {
/*  317 */     enableClassInfo();
/*  318 */     enableFieldInfo();
/*  319 */     this.scanSpec.enableStaticFinalFieldConstantInitializerValues = true;
/*  320 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableAnnotationInfo() {
/*  332 */     enableClassInfo();
/*  333 */     this.scanSpec.enableAnnotationInfo = true;
/*  334 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableInterClassDependencies() {
/*  347 */     enableClassInfo();
/*  348 */     enableFieldInfo();
/*  349 */     enableMethodInfo();
/*  350 */     enableAnnotationInfo();
/*  351 */     ignoreClassVisibility();
/*  352 */     ignoreFieldVisibility();
/*  353 */     ignoreMethodVisibility();
/*  354 */     this.scanSpec.enableInterClassDependencies = true;
/*  355 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph disableRuntimeInvisibleAnnotations() {
/*  367 */     enableClassInfo();
/*  368 */     this.scanSpec.disableRuntimeInvisibleAnnotations = true;
/*  369 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph disableJarScanning() {
/*  380 */     this.scanSpec.scanJars = false;
/*  381 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph disableNestedJarScanning() {
/*  390 */     this.scanSpec.scanNestedJars = false;
/*  391 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph disableDirScanning() {
/*  400 */     this.scanSpec.scanDirs = false;
/*  401 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph disableModuleScanning() {
/*  410 */     this.scanSpec.scanModules = false;
/*  411 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableExternalClasses() {
/*  424 */     enableClassInfo();
/*  425 */     this.scanSpec.enableExternalClasses = true;
/*  426 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph initializeLoadedClasses() {
/*  436 */     this.scanSpec.initializeLoadedClasses = true;
/*  437 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph removeTemporaryFilesAfterScan() {
/*  448 */     this.scanSpec.removeTemporaryFilesAfterScan = true;
/*  449 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph overrideClasspath(String overrideClasspath) {
/*  468 */     if (overrideClasspath.isEmpty()) {
/*  469 */       throw new IllegalArgumentException("Can't override classpath with an empty path");
/*      */     }
/*  471 */     for (String classpathElement : JarUtils.smartPathSplit(overrideClasspath, this.scanSpec)) {
/*  472 */       this.scanSpec.addClasspathOverride(classpathElement);
/*      */     }
/*  474 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph overrideClasspath(Iterable<?> overrideClasspathElements) {
/*  490 */     if (!overrideClasspathElements.iterator().hasNext()) {
/*  491 */       throw new IllegalArgumentException("Can't override classpath with an empty path");
/*      */     }
/*  493 */     for (Object classpathElement : overrideClasspathElements) {
/*  494 */       this.scanSpec.addClasspathOverride(classpathElement);
/*      */     }
/*  496 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph overrideClasspath(Object... overrideClasspathElements) {
/*  512 */     if (overrideClasspathElements.length == 0) {
/*  513 */       throw new IllegalArgumentException("Can't override classpath with an empty path");
/*      */     }
/*  515 */     for (Object classpathElement : overrideClasspathElements) {
/*  516 */       this.scanSpec.addClasspathOverride(classpathElement);
/*      */     }
/*  518 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph filterClasspathElements(ClasspathElementFilter classpathElementFilter) {
/*  568 */     this.scanSpec.filterClasspathElements(classpathElementFilter);
/*  569 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph filterClasspathElementsByURL(ClasspathElementURLFilter classpathElementURLFilter) {
/*  582 */     this.scanSpec.filterClasspathElements(classpathElementURLFilter);
/*  583 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph addClassLoader(ClassLoader classLoader) {
/*  600 */     this.scanSpec.addClassLoader(classLoader);
/*  601 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph overrideClassLoaders(ClassLoader... overrideClassLoaders) {
/*  618 */     this.scanSpec.overrideClassLoaders(overrideClassLoaders);
/*  619 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph ignoreParentClassLoaders() {
/*  629 */     this.scanSpec.ignoreParentClassLoaders = true;
/*  630 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph addModuleLayer(Object moduleLayer) {
/*  648 */     this.scanSpec.addModuleLayer(moduleLayer);
/*  649 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph overrideModuleLayers(Object... overrideModuleLayers) {
/*  665 */     this.scanSpec.overrideModuleLayers(overrideModuleLayers);
/*  666 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph ignoreParentModuleLayers() {
/*  675 */     this.scanSpec.ignoreParentModuleLayers = true;
/*  676 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph acceptPackages(String... packageNames) {
/*  694 */     enableClassInfo();
/*  695 */     for (String packageName : packageNames) {
/*  696 */       String packageNameNormalized = AcceptReject.normalizePackageOrClassName(packageName);
/*      */       
/*  698 */       this.scanSpec.packageAcceptReject.addToAccept(packageNameNormalized);
/*  699 */       String path = AcceptReject.packageNameToPath(packageNameNormalized);
/*  700 */       this.scanSpec.pathAcceptReject.addToAccept(path + "/");
/*  701 */       if (packageNameNormalized.isEmpty()) {
/*  702 */         this.scanSpec.pathAcceptReject.addToAccept("");
/*      */       }
/*  704 */       if (!packageNameNormalized.contains("*"))
/*      */       {
/*  706 */         if (packageNameNormalized.isEmpty()) {
/*  707 */           this.scanSpec.packagePrefixAcceptReject.addToAccept("");
/*  708 */           this.scanSpec.pathPrefixAcceptReject.addToAccept("");
/*      */         } else {
/*  710 */           this.scanSpec.packagePrefixAcceptReject.addToAccept(packageNameNormalized + ".");
/*  711 */           this.scanSpec.pathPrefixAcceptReject.addToAccept(path + "/");
/*      */         } 
/*      */       }
/*      */     } 
/*  715 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph whitelistPackages(String... packageNames) {
/*  729 */     return acceptPackages(packageNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph acceptPaths(String... paths) {
/*  741 */     for (String path : paths) {
/*  742 */       String pathNormalized = AcceptReject.normalizePath(path);
/*      */       
/*  744 */       String packageName = AcceptReject.pathToPackageName(pathNormalized);
/*  745 */       this.scanSpec.packageAcceptReject.addToAccept(packageName);
/*  746 */       this.scanSpec.pathAcceptReject.addToAccept(pathNormalized + "/");
/*  747 */       if (pathNormalized.isEmpty()) {
/*  748 */         this.scanSpec.pathAcceptReject.addToAccept("");
/*      */       }
/*  750 */       if (!pathNormalized.contains("*"))
/*      */       {
/*  752 */         if (pathNormalized.isEmpty()) {
/*  753 */           this.scanSpec.packagePrefixAcceptReject.addToAccept("");
/*  754 */           this.scanSpec.pathPrefixAcceptReject.addToAccept("");
/*      */         } else {
/*  756 */           this.scanSpec.packagePrefixAcceptReject.addToAccept(packageName + ".");
/*  757 */           this.scanSpec.pathPrefixAcceptReject.addToAccept(pathNormalized + "/");
/*      */         } 
/*      */       }
/*      */     } 
/*  761 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph whitelistPaths(String... paths) {
/*  775 */     return acceptPaths(paths);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph acceptPackagesNonRecursive(String... packageNames) {
/*  797 */     enableClassInfo();
/*  798 */     for (String packageName : packageNames) {
/*  799 */       String packageNameNormalized = AcceptReject.normalizePackageOrClassName(packageName);
/*  800 */       if (packageNameNormalized.contains("*")) {
/*  801 */         throw new IllegalArgumentException("Cannot use a glob wildcard here: " + packageNameNormalized);
/*      */       }
/*      */       
/*  804 */       this.scanSpec.packageAcceptReject.addToAccept(packageNameNormalized);
/*  805 */       this.scanSpec.pathAcceptReject.addToAccept(AcceptReject.packageNameToPath(packageNameNormalized) + "/");
/*  806 */       if (packageNameNormalized.isEmpty()) {
/*  807 */         this.scanSpec.pathAcceptReject.addToAccept("");
/*      */       }
/*      */     } 
/*  810 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph whitelistPackagesNonRecursive(String... packageNames) {
/*  824 */     return acceptPackagesNonRecursive(packageNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph acceptPathsNonRecursive(String... paths) {
/*  841 */     for (String path : paths) {
/*  842 */       if (path.contains("*")) {
/*  843 */         throw new IllegalArgumentException("Cannot use a glob wildcard here: " + path);
/*      */       }
/*  845 */       String pathNormalized = AcceptReject.normalizePath(path);
/*      */       
/*  847 */       this.scanSpec.packageAcceptReject.addToAccept(AcceptReject.pathToPackageName(pathNormalized));
/*  848 */       this.scanSpec.pathAcceptReject.addToAccept(pathNormalized + "/");
/*  849 */       if (pathNormalized.isEmpty()) {
/*  850 */         this.scanSpec.pathAcceptReject.addToAccept("");
/*      */       }
/*      */     } 
/*  853 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph whitelistPathsNonRecursive(String... paths) {
/*  867 */     return acceptPathsNonRecursive(paths);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph rejectPackages(String... packageNames) {
/*  883 */     enableClassInfo();
/*  884 */     for (String packageName : packageNames) {
/*  885 */       String packageNameNormalized = AcceptReject.normalizePackageOrClassName(packageName);
/*  886 */       if (packageNameNormalized.isEmpty()) {
/*  887 */         throw new IllegalArgumentException("Rejecting the root package (\"\") will cause nothing to be scanned");
/*      */       }
/*      */ 
/*      */       
/*  891 */       this.scanSpec.packageAcceptReject.addToReject(packageNameNormalized);
/*  892 */       String path = AcceptReject.packageNameToPath(packageNameNormalized);
/*  893 */       this.scanSpec.pathAcceptReject.addToReject(path + "/");
/*  894 */       if (!packageNameNormalized.contains("*")) {
/*      */         
/*  896 */         this.scanSpec.packagePrefixAcceptReject.addToReject(packageNameNormalized + ".");
/*  897 */         this.scanSpec.pathPrefixAcceptReject.addToReject(path + "/");
/*      */       } 
/*      */     } 
/*  900 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph blacklistPackages(String... packageNames) {
/*  914 */     return rejectPackages(packageNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph rejectPaths(String... paths) {
/*  925 */     for (String path : paths) {
/*  926 */       String pathNormalized = AcceptReject.normalizePath(path);
/*  927 */       if (pathNormalized.isEmpty()) {
/*  928 */         throw new IllegalArgumentException("Rejecting the root package (\"\") will cause nothing to be scanned");
/*      */       }
/*      */ 
/*      */       
/*  932 */       String packageName = AcceptReject.pathToPackageName(pathNormalized);
/*  933 */       this.scanSpec.packageAcceptReject.addToReject(packageName);
/*  934 */       this.scanSpec.pathAcceptReject.addToReject(pathNormalized + "/");
/*  935 */       if (!pathNormalized.contains("*")) {
/*      */         
/*  937 */         this.scanSpec.packagePrefixAcceptReject.addToReject(packageName + ".");
/*  938 */         this.scanSpec.pathPrefixAcceptReject.addToReject(pathNormalized + "/");
/*      */       } 
/*      */     } 
/*  941 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph blacklistPaths(String... paths) {
/*  954 */     return rejectPaths(paths);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph acceptClasses(String... classNames) {
/*  971 */     enableClassInfo();
/*  972 */     for (String className : classNames) {
/*  973 */       String classNameNormalized = AcceptReject.normalizePackageOrClassName(className);
/*      */       
/*  975 */       this.scanSpec.classAcceptReject.addToAccept(classNameNormalized);
/*  976 */       this.scanSpec.classfilePathAcceptReject
/*  977 */         .addToAccept(AcceptReject.classNameToClassfilePath(classNameNormalized));
/*  978 */       String packageName = PackageInfo.getParentPackageName(classNameNormalized);
/*      */ 
/*      */       
/*  981 */       this.scanSpec.classPackageAcceptReject.addToAccept(packageName);
/*  982 */       this.scanSpec.classPackagePathAcceptReject.addToAccept(AcceptReject.packageNameToPath(packageName) + "/");
/*      */     } 
/*  984 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph whitelistClasses(String... classNames) {
/*  997 */     return acceptClasses(classNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph rejectClasses(String... classNames) {
/* 1013 */     enableClassInfo();
/* 1014 */     for (String className : classNames) {
/* 1015 */       String classNameNormalized = AcceptReject.normalizePackageOrClassName(className);
/* 1016 */       this.scanSpec.classAcceptReject.addToReject(classNameNormalized);
/* 1017 */       this.scanSpec.classfilePathAcceptReject
/* 1018 */         .addToReject(AcceptReject.classNameToClassfilePath(classNameNormalized));
/*      */     } 
/* 1020 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph blacklistClasses(String... classNames) {
/* 1033 */     return rejectClasses(classNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph acceptJars(String... jarLeafNames) {
/* 1045 */     for (String jarLeafName : jarLeafNames) {
/* 1046 */       String leafName = JarUtils.leafName(jarLeafName);
/* 1047 */       if (!leafName.equals(jarLeafName)) {
/* 1048 */         throw new IllegalArgumentException("Can only accept jars by leafname: " + jarLeafName);
/*      */       }
/* 1050 */       this.scanSpec.jarAcceptReject.addToAccept(leafName);
/*      */     } 
/* 1052 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph whitelistJars(String... jarLeafNames) {
/* 1066 */     return acceptJars(jarLeafNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph rejectJars(String... jarLeafNames) {
/* 1078 */     for (String jarLeafName : jarLeafNames) {
/* 1079 */       String leafName = JarUtils.leafName(jarLeafName);
/* 1080 */       if (!leafName.equals(jarLeafName)) {
/* 1081 */         throw new IllegalArgumentException("Can only reject jars by leafname: " + jarLeafName);
/*      */       }
/* 1083 */       this.scanSpec.jarAcceptReject.addToReject(leafName);
/*      */     } 
/* 1085 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph blacklistJars(String... jarLeafNames) {
/* 1099 */     return rejectJars(jarLeafNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void acceptOrRejectLibOrExtJars(boolean accept, String... jarLeafNames) {
/* 1111 */     if (jarLeafNames.length == 0) {
/*      */       
/* 1113 */       for (String libOrExtJar : SystemJarFinder.getJreLibOrExtJars()) {
/* 1114 */         acceptOrRejectLibOrExtJars(accept, new String[] { JarUtils.leafName(libOrExtJar) });
/*      */       } 
/*      */     } else {
/* 1117 */       for (String jarLeafName : jarLeafNames) {
/* 1118 */         String leafName = JarUtils.leafName(jarLeafName);
/* 1119 */         if (!leafName.equals(jarLeafName)) {
/* 1120 */           throw new IllegalArgumentException("Can only " + (
/* 1121 */               accept ? "accept" : "reject") + " jars by leafname: " + jarLeafName);
/*      */         }
/* 1123 */         if (jarLeafName.contains("*")) {
/*      */           
/* 1125 */           Pattern pattern = AcceptReject.globToPattern(jarLeafName, true);
/* 1126 */           boolean found = false;
/* 1127 */           for (String libOrExtJarPath : SystemJarFinder.getJreLibOrExtJars()) {
/* 1128 */             String libOrExtJarLeafName = JarUtils.leafName(libOrExtJarPath);
/* 1129 */             if (pattern.matcher(libOrExtJarLeafName).matches()) {
/*      */               
/* 1131 */               if (!libOrExtJarLeafName.contains("*")) {
/* 1132 */                 acceptOrRejectLibOrExtJars(accept, new String[] { libOrExtJarLeafName });
/*      */               }
/* 1134 */               found = true;
/*      */             } 
/*      */           } 
/* 1137 */           if (!found && this.topLevelLog != null) {
/* 1138 */             this.topLevelLog.log("Could not find lib or ext jar matching wildcard: " + jarLeafName);
/*      */           }
/*      */         } else {
/*      */           
/* 1142 */           boolean found = false;
/* 1143 */           for (String libOrExtJarPath : SystemJarFinder.getJreLibOrExtJars()) {
/* 1144 */             String libOrExtJarLeafName = JarUtils.leafName(libOrExtJarPath);
/* 1145 */             if (jarLeafName.equals(libOrExtJarLeafName)) {
/* 1146 */               if (accept) {
/* 1147 */                 this.scanSpec.libOrExtJarAcceptReject.addToAccept(jarLeafName);
/*      */               } else {
/* 1149 */                 this.scanSpec.libOrExtJarAcceptReject.addToReject(jarLeafName);
/*      */               } 
/* 1151 */               if (this.topLevelLog != null) {
/* 1152 */                 this.topLevelLog.log((accept ? "Accepting" : "Rejecting") + " lib or ext jar: " + libOrExtJarPath);
/*      */               }
/*      */               
/* 1155 */               found = true;
/*      */               break;
/*      */             } 
/*      */           } 
/* 1159 */           if (!found && this.topLevelLog != null) {
/* 1160 */             this.topLevelLog.log("Could not find lib or ext jar: " + jarLeafName);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph acceptLibOrExtJars(String... jarLeafNames) {
/* 1178 */     acceptOrRejectLibOrExtJars(true, jarLeafNames);
/* 1179 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph whitelistLibOrExtJars(String... jarLeafNames) {
/* 1194 */     return acceptLibOrExtJars(jarLeafNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph rejectLibOrExtJars(String... jarLeafNames) {
/* 1207 */     acceptOrRejectLibOrExtJars(false, jarLeafNames);
/* 1208 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph blacklistLibOrExtJars(String... jarLeafNames) {
/* 1223 */     return rejectLibOrExtJars(jarLeafNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph acceptModules(String... moduleNames) {
/* 1234 */     for (String moduleName : moduleNames) {
/* 1235 */       this.scanSpec.moduleAcceptReject.addToAccept(AcceptReject.normalizePackageOrClassName(moduleName));
/*      */     }
/* 1237 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph whitelistModules(String... moduleNames) {
/* 1250 */     return acceptModules(moduleNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph rejectModules(String... moduleNames) {
/* 1261 */     for (String moduleName : moduleNames) {
/* 1262 */       this.scanSpec.moduleAcceptReject.addToReject(AcceptReject.normalizePackageOrClassName(moduleName));
/*      */     }
/* 1264 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph blacklistModules(String... moduleNames) {
/* 1277 */     return rejectModules(moduleNames);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph acceptClasspathElementsContainingResourcePath(String... resourcePaths) {
/* 1290 */     for (String resourcePath : resourcePaths) {
/* 1291 */       String resourcePathNormalized = AcceptReject.normalizePath(resourcePath);
/* 1292 */       this.scanSpec.classpathElementResourcePathAcceptReject.addToAccept(resourcePathNormalized);
/*      */     } 
/* 1294 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph whitelistClasspathElementsContainingResourcePath(String... resourcePaths) {
/* 1308 */     return acceptClasspathElementsContainingResourcePath(resourcePaths);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph rejectClasspathElementsContainingResourcePath(String... resourcePaths) {
/* 1321 */     for (String resourcePath : resourcePaths) {
/* 1322 */       String resourcePathNormalized = AcceptReject.normalizePath(resourcePath);
/* 1323 */       this.scanSpec.classpathElementResourcePathAcceptReject.addToReject(resourcePathNormalized);
/*      */     } 
/* 1325 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public ClassGraph blacklistClasspathElementsContainingResourcePath(String... resourcePaths) {
/* 1339 */     return rejectClasspathElementsContainingResourcePath(resourcePaths);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableRemoteJarScanning() {
/* 1356 */     this.scanSpec.enableURLScheme("http");
/* 1357 */     this.scanSpec.enableURLScheme("https");
/* 1358 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableURLScheme(String scheme) {
/* 1371 */     this.scanSpec.enableURLScheme(scheme);
/* 1372 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableSystemJarsAndModules() {
/* 1385 */     enableClassInfo();
/* 1386 */     this.scanSpec.enableSystemJarsAndModules = true;
/* 1387 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph setMaxBufferedJarRAMSize(int maxBufferedJarRAMSize) {
/* 1419 */     this.scanSpec.maxBufferedJarRAMSize = maxBufferedJarRAMSize;
/* 1420 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableMemoryMapping() {
/* 1430 */     this.scanSpec.enableMemoryMapping = true;
/* 1431 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableMultiReleaseVersions() {
/* 1442 */     this.scanSpec.enableMultiReleaseVersions = true;
/*      */     
/* 1444 */     this.scanSpec.enableClassInfo = false;
/* 1445 */     this.scanSpec.ignoreClassVisibility = false;
/* 1446 */     this.scanSpec.enableMethodInfo = false;
/* 1447 */     this.scanSpec.ignoreMethodVisibility = false;
/* 1448 */     this.scanSpec.enableFieldInfo = false;
/* 1449 */     this.scanSpec.ignoreFieldVisibility = false;
/* 1450 */     this.scanSpec.enableStaticFinalFieldConstantInitializerValues = false;
/* 1451 */     this.scanSpec.enableAnnotationInfo = false;
/* 1452 */     this.scanSpec.enableInterClassDependencies = false;
/* 1453 */     this.scanSpec.disableRuntimeInvisibleAnnotations = false;
/* 1454 */     this.scanSpec.enableExternalClasses = false;
/* 1455 */     this.scanSpec.enableSystemJarsAndModules = false;
/* 1456 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassGraph enableRealtimeLogging() {
/* 1470 */     verbose();
/* 1471 */     LogNode.logInRealtime(true);
/* 1472 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void scanAsync(final ExecutorService executorService, final int numParallelTasks, final ScanResultProcessor scanResultProcessor, final FailureHandler failureHandler) {
/* 1518 */     if (scanResultProcessor == null)
/*      */     {
/*      */       
/* 1521 */       throw new IllegalArgumentException("scanResultProcessor cannot be null");
/*      */     }
/* 1523 */     if (failureHandler == null)
/*      */     {
/*      */       
/* 1526 */       throw new IllegalArgumentException("failureHandler cannot be null");
/*      */     }
/*      */     
/* 1529 */     executorService.execute(new Runnable()
/*      */         {
/*      */           public void run()
/*      */           {
/*      */             try {
/* 1534 */               (new Scanner(true, ClassGraph.this.scanSpec, executorService, numParallelTasks, scanResultProcessor, failureHandler, ClassGraph.this
/* 1535 */                   .reflectionUtils, ClassGraph.this.topLevelLog)).call();
/* 1536 */             } catch (InterruptedException|java.util.concurrent.CancellationException|ExecutionException e) {
/*      */               
/* 1538 */               failureHandler.onFailure(e);
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Future<ScanResult> scanAsync(boolean performScan, ExecutorService executorService, int numParallelTasks) {
/*      */     try {
/* 1562 */       return executorService.submit(new Scanner(performScan, this.scanSpec, executorService, numParallelTasks, null, null, this.reflectionUtils, this.topLevelLog));
/*      */     }
/* 1564 */     catch (InterruptedException e) {
/*      */ 
/*      */       
/* 1567 */       return executorService.submit(new Callable<ScanResult>()
/*      */           {
/*      */             public ScanResult call() throws Exception {
/* 1570 */               throw e;
/*      */             }
/*      */           });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Future<ScanResult> scanAsync(ExecutorService executorService, int numParallelTasks) {
/* 1590 */     return scanAsync(true, executorService, numParallelTasks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ScanResult scan(ExecutorService executorService, int numParallelTasks) {
/*      */     try {
/* 1615 */       ScanResult scanResult = scanAsync(executorService, numParallelTasks).get();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1629 */       if (scanResult == null) {
/* 1630 */         throw new NullPointerException();
/*      */       }
/* 1632 */       return scanResult;
/*      */     }
/* 1634 */     catch (InterruptedException|java.util.concurrent.CancellationException e) {
/* 1635 */       throw new ClassGraphException("Scan interrupted", e);
/* 1636 */     } catch (ExecutionException e) {
/* 1637 */       throw new ClassGraphException("Uncaught exception during scan", InterruptionChecker.getCause(e));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ScanResult scan(int numThreads) {
/* 1653 */     AutoCloseableExecutorService executorService = new AutoCloseableExecutorService(numThreads); try {
/* 1654 */       ScanResult scanResult = scan((ExecutorService)executorService, numThreads);
/* 1655 */       executorService.close();
/*      */       return scanResult;
/*      */     } catch (Throwable throwable) {
/*      */       try {
/*      */         executorService.close();
/*      */       } catch (Throwable throwable1) {
/*      */         throwable.addSuppressed(throwable1);
/*      */       } 
/*      */       throw throwable;
/*      */     } 
/*      */   }
/*      */   public ScanResult scan() {
/* 1667 */     return scan(DEFAULT_NUM_WORKER_THREADS);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ScanResult getClasspathScanResult(AutoCloseableExecutorService executorService) {
/*      */     try {
/* 1685 */       ScanResult scanResult = scanAsync(false, (ExecutorService)executorService, DEFAULT_NUM_WORKER_THREADS).get();
/*      */ 
/*      */       
/* 1688 */       if (scanResult == null) {
/* 1689 */         throw new NullPointerException();
/*      */       }
/* 1691 */       return scanResult;
/*      */     }
/* 1693 */     catch (InterruptedException|java.util.concurrent.CancellationException e) {
/* 1694 */       throw new ClassGraphException("Scan interrupted", e);
/* 1695 */     } catch (ExecutionException e) {
/* 1696 */       throw new ClassGraphException("Uncaught exception during scan", InterruptionChecker.getCause(e));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<File> getClasspathFiles() {
/* 1711 */     AutoCloseableExecutorService executorService = new AutoCloseableExecutorService(DEFAULT_NUM_WORKER_THREADS); try {
/* 1712 */       ScanResult scanResult = getClasspathScanResult(executorService); try {
/* 1713 */         List<File> list = scanResult.getClasspathFiles();
/* 1714 */         if (scanResult != null) scanResult.close();  executorService.close(); return list;
/*      */       } catch (Throwable throwable) {
/*      */         if (scanResult != null)
/*      */           try {
/*      */             scanResult.close();
/*      */           } catch (Throwable throwable1) {
/*      */             throwable.addSuppressed(throwable1);
/*      */           }  
/*      */         throw throwable;
/*      */       } 
/*      */     } catch (Throwable throwable) {
/*      */       try {
/*      */         executorService.close();
/*      */       } catch (Throwable throwable1) {
/*      */         throwable.addSuppressed(throwable1);
/*      */       } 
/*      */       throw throwable;
/* 1731 */     }  } public String getClasspath() { return JarUtils.pathElementsToPathStr(getClasspathFiles()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<URI> getClasspathURIs() {
/* 1744 */     AutoCloseableExecutorService executorService = new AutoCloseableExecutorService(DEFAULT_NUM_WORKER_THREADS); try {
/* 1745 */       ScanResult scanResult = getClasspathScanResult(executorService); 
/* 1746 */       try { List<URI> list = scanResult.getClasspathURIs();
/* 1747 */         if (scanResult != null) scanResult.close();  executorService.close(); return list; }
/*      */       catch (Throwable throwable) { if (scanResult != null)
/*      */           try { scanResult.close(); }
/*      */           catch (Throwable throwable1)
/*      */           { throwable.addSuppressed(throwable1); }
/*      */             throw throwable; }
/*      */     
/*      */     } catch (Throwable throwable) {
/*      */       try {
/*      */         executorService.close();
/*      */       } catch (Throwable throwable1) {
/*      */         throwable.addSuppressed(throwable1);
/*      */       }  throw throwable;
/* 1760 */     }  } public List<URL> getClasspathURLs() { AutoCloseableExecutorService executorService = new AutoCloseableExecutorService(DEFAULT_NUM_WORKER_THREADS); 
/* 1761 */     try { ScanResult scanResult = getClasspathScanResult(executorService); 
/* 1762 */       try { List<URL> list = scanResult.getClasspathURLs();
/* 1763 */         if (scanResult != null) scanResult.close();  executorService.close(); return list; }
/*      */       catch (Throwable throwable) { if (scanResult != null)
/*      */           try { scanResult.close(); }
/*      */           catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */             throw throwable; }
/*      */        }
/*      */     catch (Throwable throwable) { try {
/*      */         executorService.close();
/*      */       } catch (Throwable throwable1) {
/*      */         throwable.addSuppressed(throwable1);
/*      */       }  throw throwable; }
/* 1774 */      } public List<ModuleRef> getModules() { AutoCloseableExecutorService executorService = new AutoCloseableExecutorService(DEFAULT_NUM_WORKER_THREADS); try {
/* 1775 */       ScanResult scanResult = getClasspathScanResult(executorService); try {
/* 1776 */         List<ModuleRef> list = scanResult.getModules();
/* 1777 */         if (scanResult != null) scanResult.close();  executorService.close();
/*      */         return list;
/*      */       } catch (Throwable throwable) {
/*      */         if (scanResult != null)
/*      */           try {
/*      */             scanResult.close();
/*      */           } catch (Throwable throwable1) {
/*      */             throwable.addSuppressed(throwable1);
/*      */           }  
/*      */         throw throwable;
/*      */       } 
/*      */     } catch (Throwable throwable) {
/*      */       try {
/*      */         executorService.close();
/*      */       } catch (Throwable throwable1) {
/*      */         throwable.addSuppressed(throwable1);
/*      */       } 
/*      */       throw throwable;
/*      */     }  }
/*      */   
/*      */   public ModulePathInfo getModulePathInfo() {
/* 1798 */     this.scanSpec.modulePathInfo.getRuntimeInfo(this.reflectionUtils);
/* 1799 */     return this.scanSpec.modulePathInfo;
/*      */   }
/*      */   
/*      */   @FunctionalInterface
/*      */   public static interface FailureHandler {
/*      */     void onFailure(Throwable param1Throwable);
/*      */   }
/*      */   
/*      */   @FunctionalInterface
/*      */   public static interface ScanResultProcessor {
/*      */     void processScanResult(ScanResult param1ScanResult);
/*      */   }
/*      */   
/*      */   @FunctionalInterface
/*      */   public static interface ClasspathElementURLFilter {
/*      */     boolean includeClasspathElement(URL param1URL);
/*      */   }
/*      */   
/*      */   @FunctionalInterface
/*      */   public static interface ClasspathElementFilter {
/*      */     boolean includeClasspathElement(String param1String);
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ClassGraph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */