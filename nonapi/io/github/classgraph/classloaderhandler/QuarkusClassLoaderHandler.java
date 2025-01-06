/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
/*     */ import java.io.IOError;
/*     */ import java.net.URI;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
/*     */ import nonapi.io.github.classgraph.classpath.ClasspathOrder;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
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
/*     */ class QuarkusClassLoaderHandler
/*     */   implements ClassLoaderHandler
/*     */ {
/*     */   private static final String RUNTIME_CLASSLOADER = "io.quarkus.runner.RuntimeClassLoader";
/*     */   private static final String QUARKUS_CLASSLOADER = "io.quarkus.bootstrap.classloading.QuarkusClassLoader";
/*     */   private static final String RUNNER_CLASSLOADER = "io.quarkus.bootstrap.runner.RunnerClassLoader";
/*     */   
/*     */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/*  71 */     return ("io.quarkus.runner.RuntimeClassLoader".equals(classLoaderClass.getName()) || "io.quarkus.bootstrap.classloading.QuarkusClassLoader"
/*  72 */       .equals(classLoaderClass.getName()) || "io.quarkus.bootstrap.runner.RunnerClassLoader"
/*  73 */       .equals(classLoaderClass.getName()));
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
/*     */   public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode log) {
/*  88 */     classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
/*  89 */     classLoaderOrder.add(classLoader, log);
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
/*     */   public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/* 107 */     String classLoaderName = classLoader.getClass().getName();
/* 108 */     if ("io.quarkus.runner.RuntimeClassLoader".equals(classLoaderName)) {
/* 109 */       findClasspathOrderForRuntimeClassloader(classLoader, classpathOrder, scanSpec, log);
/* 110 */     } else if ("io.quarkus.bootstrap.classloading.QuarkusClassLoader".equals(classLoaderName)) {
/* 111 */       findClasspathOrderForQuarkusClassloader(classLoader, classpathOrder, scanSpec, log);
/* 112 */     } else if ("io.quarkus.bootstrap.runner.RunnerClassLoader".equals(classLoaderName)) {
/* 113 */       findClasspathOrderForRunnerClassloader(classLoader, classpathOrder, scanSpec, log);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void findClasspathOrderForQuarkusClassloader(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/* 120 */     for (Object element : classpathOrder.reflectionUtils.getFieldVal(false, classLoader, "elements")) {
/*     */       
/* 122 */       String elementClassName = element.getClass().getName();
/* 123 */       if ("io.quarkus.bootstrap.classloading.JarClassPathElement".equals(elementClassName)) {
/* 124 */         classpathOrder.addClasspathEntry(classpathOrder.reflectionUtils.getFieldVal(false, element, "file"), classLoader, scanSpec, log); continue;
/*     */       } 
/* 126 */       if ("io.quarkus.bootstrap.classloading.DirectoryClassPathElement".equals(elementClassName)) {
/* 127 */         classpathOrder.addClasspathEntry(classpathOrder.reflectionUtils.getFieldVal(false, element, "root"), classLoader, scanSpec, log);
/*     */         continue;
/*     */       } 
/* 130 */       Object rootPath = classpathOrder.reflectionUtils.invokeMethod(false, element, "getRoot");
/* 131 */       if (rootPath instanceof Path) {
/* 132 */         classpathOrder.addClasspathEntry(rootPath, classLoader, scanSpec, log);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void findClasspathOrderForRuntimeClassloader(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/* 142 */     Collection<Path> applicationClassDirectories = (Collection<Path>)classpathOrder.reflectionUtils.getFieldVal(false, classLoader, "applicationClassDirectories");
/* 143 */     if (applicationClassDirectories != null) {
/* 144 */       for (Path path : applicationClassDirectories) {
/*     */         try {
/* 146 */           URI uri = path.toUri();
/* 147 */           classpathOrder.addClasspathEntryObject(uri, classLoader, scanSpec, log);
/* 148 */         } catch (IOError|SecurityException e) {
/* 149 */           if (log != null) {
/* 150 */             log.log("Could not convert path to URI: " + path);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void findClasspathOrderForRunnerClassloader(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/* 160 */     for (Object[] elementArray : ((Map)classpathOrder.reflectionUtils
/* 161 */       .getFieldVal(false, classLoader, "resourceDirectoryMap")).values()) {
/* 162 */       for (Object element : elementArray) {
/* 163 */         String elementClassName = element.getClass().getName();
/* 164 */         if ("io.quarkus.bootstrap.runner.JarResource".equals(elementClassName))
/* 165 */           classpathOrder.addClasspathEntry(classpathOrder.reflectionUtils
/* 166 */               .getFieldVal(false, element, "jarPath"), classLoader, scanSpec, log); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\QuarkusClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */