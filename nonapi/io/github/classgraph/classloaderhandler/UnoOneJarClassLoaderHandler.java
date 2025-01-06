/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
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
/*     */ class UnoOneJarClassLoaderHandler
/*     */   implements ClassLoaderHandler
/*     */ {
/*     */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/*  52 */     return ("com.needhamsoftware.unojar.JarClassLoader".equals(classLoaderClass.getName()) || "com.simontuffs.onejar.JarClassLoader"
/*  53 */       .equals(classLoaderClass.getName()));
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
/*  68 */     classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
/*  69 */     classLoaderOrder.add(classLoader, log);
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
/*     */   public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/*  89 */     String unoJarOneJarPath = (String)classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getOneJarPath");
/*     */     
/*  91 */     classpathOrder.addClasspathEntry(unoJarOneJarPath, classLoader, scanSpec, log);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     String unoJarJarPath = System.getProperty("uno-jar.jar.path");
/*  97 */     classpathOrder.addClasspathEntry(unoJarJarPath, classLoader, scanSpec, log);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     String oneJarJarPath = System.getProperty("one-jar.jar.path");
/* 105 */     classpathOrder.addClasspathEntry(oneJarJarPath, classLoader, scanSpec, log);
/*     */ 
/*     */ 
/*     */     
/* 109 */     String oneJarClassPath = System.getProperty("one-jar.class.path");
/* 110 */     if (oneJarClassPath != null)
/* 111 */       classpathOrder.addClasspathEntryObject(oneJarClassPath.split("\\|"), classLoader, scanSpec, log); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\UnoOneJarClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */