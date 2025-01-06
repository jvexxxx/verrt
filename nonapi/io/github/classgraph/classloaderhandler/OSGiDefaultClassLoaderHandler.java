/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
/*     */ import java.io.File;
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
/*     */ class OSGiDefaultClassLoaderHandler
/*     */   implements ClassLoaderHandler
/*     */ {
/*     */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/*  58 */     return "org.eclipse.osgi.internal.baseadaptor.DefaultClassLoader".equals(classLoaderClass.getName());
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
/*  73 */     classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
/*  74 */     classLoaderOrder.add(classLoader, log);
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
/*     */   public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/*  91 */     Object classpathManager = classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getClasspathManager");
/*     */     
/*  93 */     Object[] entries = (Object[])classpathOrder.reflectionUtils.getFieldVal(false, classpathManager, "entries");
/*     */     
/*  95 */     if (entries != null)
/*  96 */       for (Object entry : entries) {
/*  97 */         Object bundleFile = classpathOrder.reflectionUtils.invokeMethod(false, entry, "getBundleFile");
/*     */         
/*  99 */         File baseFile = (File)classpathOrder.reflectionUtils.invokeMethod(false, bundleFile, "getBaseFile");
/*     */         
/* 101 */         if (baseFile != null)
/* 102 */           classpathOrder.addClasspathEntry(baseFile.getPath(), classLoader, scanSpec, log); 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\OSGiDefaultClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */