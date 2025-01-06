/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
/*     */ import java.util.SortedSet;
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
/*     */ class PlexusClassWorldsClassRealmClassLoaderHandler
/*     */   implements ClassLoaderHandler
/*     */ {
/*     */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/*  59 */     return "org.codehaus.plexus.classworlds.realm.ClassRealm".equals(classLoaderClass.getName());
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
/*     */   private static boolean isParentFirstStrategy(ClassLoader classRealmInstance, ReflectionUtils reflectionUtils) {
/*  71 */     Object strategy = reflectionUtils.getFieldVal(false, classRealmInstance, "strategy");
/*  72 */     if (strategy != null) {
/*  73 */       String strategyClassName = strategy.getClass().getName();
/*  74 */       if (strategyClassName.equals("org.codehaus.plexus.classworlds.strategy.SelfFirstStrategy") || strategyClassName
/*  75 */         .equals("org.codehaus.plexus.classworlds.strategy.OsgiBundleStrategy"))
/*     */       {
/*  77 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  81 */     return true;
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
/*     */   public static void findClassLoaderOrder(ClassLoader classRealm, ClassLoaderOrder classLoaderOrder, LogNode log) {
/*  97 */     Object foreignImports = classLoaderOrder.reflectionUtils.getFieldVal(false, classRealm, "foreignImports");
/*     */     
/*  99 */     if (foreignImports != null) {
/*     */       
/* 101 */       SortedSet<Object> foreignImportEntries = (SortedSet<Object>)foreignImports;
/* 102 */       for (Object entry : foreignImportEntries) {
/*     */         
/* 104 */         ClassLoader foreignImportClassLoader = (ClassLoader)classLoaderOrder.reflectionUtils.invokeMethod(false, entry, "getClassLoader");
/*     */         
/* 106 */         classLoaderOrder.delegateTo(foreignImportClassLoader, true, log);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 111 */     boolean isParentFirst = isParentFirstStrategy(classRealm, classLoaderOrder.reflectionUtils);
/*     */ 
/*     */     
/* 114 */     if (!isParentFirst)
/*     */     {
/* 116 */       classLoaderOrder.add(classRealm, log);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     ClassLoader parentClassLoader = (ClassLoader)classLoaderOrder.reflectionUtils.invokeMethod(false, classRealm, "getParentClassLoader");
/*     */     
/* 124 */     classLoaderOrder.delegateTo(parentClassLoader, true, log);
/* 125 */     classLoaderOrder.delegateTo(classRealm.getParent(), true, log);
/*     */ 
/*     */     
/* 128 */     if (isParentFirst)
/*     */     {
/* 130 */       classLoaderOrder.add(classRealm, log);
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
/* 149 */     URLClassLoaderHandler.findClasspathOrder(classLoader, classpathOrder, scanSpec, log);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\PlexusClassWorldsClassRealmClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */