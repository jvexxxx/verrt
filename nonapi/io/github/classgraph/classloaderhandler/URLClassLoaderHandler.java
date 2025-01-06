/*    */ package nonapi.io.github.classgraph.classloaderhandler;
/*    */ 
/*    */ import java.net.URL;
/*    */ import java.net.URLClassLoader;
/*    */ import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
/*    */ import nonapi.io.github.classgraph.classpath.ClasspathOrder;
/*    */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*    */ import nonapi.io.github.classgraph.utils.LogNode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class URLClassLoaderHandler
/*    */   implements ClassLoaderHandler
/*    */ {
/*    */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/* 55 */     return "java.net.URLClassLoader".equals(classLoaderClass.getName());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode log) {
/* 70 */     classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
/* 71 */     classLoaderOrder.add(classLoader, log);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/* 88 */     URL[] urls = ((URLClassLoader)classLoader).getURLs();
/* 89 */     if (urls != null)
/* 90 */       for (URL url : urls) {
/* 91 */         if (url != null)
/* 92 */           classpathOrder.addClasspathEntry(url, classLoader, scanSpec, log); 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\URLClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */