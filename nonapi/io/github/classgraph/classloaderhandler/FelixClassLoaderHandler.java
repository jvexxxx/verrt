/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
/*     */ import nonapi.io.github.classgraph.classpath.ClasspathOrder;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
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
/*     */ class FelixClassLoaderHandler
/*     */   implements ClassLoaderHandler
/*     */ {
/*     */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/*  65 */     return ("org.apache.felix.framework.BundleWiringImpl$BundleClassLoaderJava5"
/*  66 */       .equals(classLoaderClass.getName()) || "org.apache.felix.framework.BundleWiringImpl$BundleClassLoader"
/*     */       
/*  68 */       .equals(classLoaderClass.getName()));
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
/*  83 */     classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
/*  84 */     classLoaderOrder.add(classLoader, log);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static File getContentLocation(Object content, ReflectionUtils reflectionUtils) {
/*  95 */     return (File)reflectionUtils.invokeMethod(false, content, "getFile");
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
/*     */   
/*     */   private static void addBundle(Object bundleWiring, ClassLoader classLoader, ClasspathOrder classpathOrderOut, Set<Object> bundles, ScanSpec scanSpec, LogNode log) {
/* 118 */     bundles.add(bundleWiring);
/*     */ 
/*     */     
/* 121 */     Object revision = classpathOrderOut.reflectionUtils.invokeMethod(false, bundleWiring, "getRevision");
/*     */     
/* 123 */     Object content = classpathOrderOut.reflectionUtils.invokeMethod(false, revision, "getContent");
/*     */     
/* 125 */     File location = (content != null) ? getContentLocation(content, classpathOrderOut.reflectionUtils) : null;
/* 126 */     if (location != null) {
/*     */       
/* 128 */       classpathOrderOut.addClasspathEntry(location, classLoader, scanSpec, log);
/*     */ 
/*     */       
/* 131 */       List<?> embeddedContent = (List)classpathOrderOut.reflectionUtils.invokeMethod(false, revision, "getContentPath");
/*     */       
/* 133 */       if (embeddedContent != null) {
/* 134 */         for (Object embedded : embeddedContent) {
/* 135 */           if (embedded != content) {
/*     */ 
/*     */             
/* 138 */             File embeddedLocation = (embedded != null) ? getContentLocation(embedded, classpathOrderOut.reflectionUtils) : null;
/* 139 */             if (embeddedLocation != null) {
/* 140 */               classpathOrderOut.addClasspathEntry(embeddedLocation, classLoader, scanSpec, log);
/*     */             }
/*     */           } 
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/* 163 */     Set<Object> bundles = new HashSet();
/* 164 */     Object bundleWiring = classpathOrder.reflectionUtils.getFieldVal(false, classLoader, "m_wiring");
/* 165 */     addBundle(bundleWiring, classLoader, classpathOrder, bundles, scanSpec, log);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     List<?> requiredWires = (List)classpathOrder.reflectionUtils.invokeMethod(false, bundleWiring, "getRequiredWires", String.class, null);
/*     */     
/* 172 */     if (requiredWires != null)
/* 173 */       for (Object wire : requiredWires) {
/* 174 */         Object provider = classpathOrder.reflectionUtils.invokeMethod(false, wire, "getProviderWiring");
/*     */         
/* 176 */         if (!bundles.contains(provider))
/* 177 */           addBundle(provider, classLoader, classpathOrder, bundles, scanSpec, log); 
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\FelixClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */