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
/*    */ class CxfContainerClassLoaderHandler
/*    */   implements ClassLoaderHandler
/*    */ {
/*    */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/* 52 */     return "org.apache.openejb.server.cxf.transport.util.CxfContainerClassLoader"
/* 53 */       .equals(classLoaderClass.getName());
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
/*    */     try {
/* 69 */       classLoaderOrder.delegateTo(
/* 70 */           Class.forName("org.apache.openejb.server.cxf.transport.util.CxfUtil").getClassLoader(), true, log);
/*    */     }
/* 72 */     catch (LinkageError|ClassNotFoundException linkageError) {}
/*    */ 
/*    */ 
/*    */     
/* 76 */     classLoaderOrder.delegateTo((ClassLoader)classLoaderOrder.reflectionUtils
/* 77 */         .invokeMethod(false, classLoader, "tccl"), false, log);
/*    */ 
/*    */     
/* 80 */     classLoaderOrder.add(classLoader, log);
/*    */   }
/*    */   
/*    */   public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {}
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\CxfContainerClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */