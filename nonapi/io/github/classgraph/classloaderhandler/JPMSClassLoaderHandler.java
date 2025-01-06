/*    */ package nonapi.io.github.classgraph.classloaderhandler;
/*    */ 
/*    */ import java.net.URL;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ class JPMSClassLoaderHandler
/*    */   implements ClassLoaderHandler
/*    */ {
/*    */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/* 57 */     return ("jdk.internal.loader.ClassLoaders$AppClassLoader".equals(classLoaderClass.getName()) || "jdk.internal.loader.BuiltinClassLoader"
/* 58 */       .equals(classLoaderClass.getName()));
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
/* 73 */     classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
/* 74 */     classLoaderOrder.add(classLoader, log);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/* 96 */     Object ucpVal = classpathOrder.reflectionUtils.getFieldVal(false, classLoader, "ucp");
/* 97 */     if (ucpVal != null) {
/* 98 */       URL[] urls = (URL[])classpathOrder.reflectionUtils.invokeMethod(false, ucpVal, "getURLs");
/* 99 */       classpathOrder.addClasspathEntryObject(urls, classLoader, scanSpec, log);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\JPMSClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */