/*    */ package nonapi.io.github.classgraph.classloaderhandler;
/*    */ 
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
/*    */ class WeblogicClassLoaderHandler
/*    */   implements ClassLoaderHandler
/*    */ {
/*    */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/* 52 */     return ("weblogic.utils.classloaders.ChangeAwareClassLoader".equals(classLoaderClass.getName()) || "weblogic.utils.classloaders.GenericClassLoader"
/* 53 */       .equals(classLoaderClass.getName()) || "weblogic.utils.classloaders.FilteringClassLoader"
/* 54 */       .equals(classLoaderClass.getName()) || "weblogic.servlet.jsp.JspClassLoader"
/*    */ 
/*    */       
/* 57 */       .equals(classLoaderClass.getName()) || "weblogic.servlet.jsp.TagFileClassLoader"
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
/*    */   public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/* 91 */     classpathOrder.addClasspathPathStr((String)classpathOrder.reflectionUtils
/* 92 */         .invokeMethod(false, classLoader, "getFinderClassPath"), classLoader, scanSpec, log);
/*    */     
/* 94 */     classpathOrder.addClasspathPathStr((String)classpathOrder.reflectionUtils
/* 95 */         .invokeMethod(false, classLoader, "getClassPath"), classLoader, scanSpec, log);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\WeblogicClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */