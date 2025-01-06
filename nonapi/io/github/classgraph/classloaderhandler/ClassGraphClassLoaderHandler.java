/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
/*     */ import io.github.classgraph.ClassGraphClassLoader;
/*     */ import java.net.URL;
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
/*     */ class ClassGraphClassLoaderHandler
/*     */   implements ClassLoaderHandler
/*     */ {
/*     */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/*  58 */     boolean matches = "io.github.classgraph.ClassGraphClassLoader".equals(classLoaderClass.getName());
/*  59 */     if (matches && log != null) {
/*  60 */       log.log("Sharing a `ClassGraphClassLoader` between multiple nested scans is not advisable, because scan criteria may differ between scans. See: https://github.com/classgraph/classgraph/issues/485");
/*     */     }
/*     */ 
/*     */     
/*  64 */     return matches;
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
/*  79 */     classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
/*  80 */     classLoaderOrder.add(classLoader, log);
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
/*     */   public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/* 102 */     for (URL url : ((ClassGraphClassLoader)classLoader).getURLs()) {
/* 103 */       if (url != null)
/* 104 */         classpathOrder.addClasspathEntry(url, classLoader, scanSpec, log); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\ClassGraphClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */