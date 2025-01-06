/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Array;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
/*     */ import nonapi.io.github.classgraph.classpath.ClasspathOrder;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ import nonapi.io.github.classgraph.utils.FileUtils;
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
/*     */ class JBossClassLoaderHandler
/*     */   implements ClassLoaderHandler
/*     */ {
/*     */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/*  68 */     return "org.jboss.modules.ModuleClassLoader".equals(classLoaderClass.getName());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void handleResourceLoader(Object resourceLoader, ClassLoader classLoader, ClasspathOrder classpathOrderOut, ScanSpec scanSpec, LogNode log) {
/* 103 */     if (resourceLoader == null) {
/*     */       return;
/*     */     }
/*     */     
/* 107 */     Object root = classpathOrderOut.reflectionUtils.getFieldVal(false, resourceLoader, "root");
/*     */     
/* 109 */     File physicalFile = (File)classpathOrderOut.reflectionUtils.invokeMethod(false, root, "getPhysicalFile");
/*     */     
/* 111 */     String path = null;
/* 112 */     if (physicalFile != null) {
/* 113 */       String name = (String)classpathOrderOut.reflectionUtils.invokeMethod(false, root, "getName");
/* 114 */       if (name != null) {
/*     */         
/* 116 */         File file = new File(physicalFile.getParentFile(), name);
/* 117 */         if (FileUtils.canRead(file)) {
/* 118 */           path = file.getAbsolutePath();
/*     */         } else {
/*     */           
/* 121 */           path = physicalFile.getAbsolutePath();
/*     */         } 
/*     */       } else {
/* 124 */         path = physicalFile.getAbsolutePath();
/*     */       } 
/*     */     } else {
/* 127 */       path = (String)classpathOrderOut.reflectionUtils.invokeMethod(false, root, "getPathName");
/* 128 */       if (path == null) {
/*     */ 
/*     */         
/* 131 */         File file = (root instanceof Path) ? ((Path)root).toFile() : ((root instanceof File) ? (File)root : null);
/* 132 */         if (file != null) {
/* 133 */           path = file.getAbsolutePath();
/*     */         }
/*     */       } 
/*     */     } 
/* 137 */     if (path == null) {
/* 138 */       File file = (File)classpathOrderOut.reflectionUtils.getFieldVal(false, resourceLoader, "fileOfJar");
/*     */       
/* 140 */       if (file != null) {
/* 141 */         path = file.getAbsolutePath();
/*     */       }
/*     */     } 
/* 144 */     if (path != null) {
/* 145 */       classpathOrderOut.addClasspathEntry(path, classLoader, scanSpec, log);
/*     */     }
/* 147 */     else if (log != null) {
/* 148 */       log.log("Could not determine classpath for ResourceLoader: " + resourceLoader);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void handleRealModule(Object module, Set<Object> visitedModules, ClassLoader classLoader, ClasspathOrder classpathOrderOut, ScanSpec scanSpec, LogNode log) {
/* 172 */     if (!visitedModules.add(module)) {
/*     */       return;
/*     */     }
/*     */     
/* 176 */     ClassLoader moduleLoader = (ClassLoader)classpathOrderOut.reflectionUtils.invokeMethod(false, module, "getClassLoader");
/*     */     
/* 178 */     if (moduleLoader == null) {
/* 179 */       moduleLoader = classLoader;
/*     */     }
/*     */     
/* 182 */     Object vfsResourceLoaders = classpathOrderOut.reflectionUtils.invokeMethod(false, moduleLoader, "getResourceLoaders");
/*     */     
/* 184 */     if (vfsResourceLoaders != null) {
/* 185 */       for (int i = 0, n = Array.getLength(vfsResourceLoaders); i < n; i++) {
/*     */ 
/*     */ 
/*     */         
/* 189 */         Object resourceLoader = Array.get(vfsResourceLoaders, i);
/*     */ 
/*     */ 
/*     */         
/* 193 */         handleResourceLoader(resourceLoader, moduleLoader, classpathOrderOut, scanSpec, log);
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
/* 213 */     Object module = classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getModule");
/* 214 */     Object callerModuleLoader = classpathOrder.reflectionUtils.invokeMethod(false, module, "getCallerModuleLoader");
/*     */     
/* 216 */     Set<Object> visitedModules = new HashSet();
/*     */ 
/*     */     
/* 219 */     Map<Object, Object> moduleMap = (Map<Object, Object>)classpathOrder.reflectionUtils.getFieldVal(false, callerModuleLoader, "moduleMap");
/*     */     
/* 221 */     Set<Map.Entry<Object, Object>> moduleMapEntries = (moduleMap != null) ? moduleMap.entrySet() : Collections.<Map.Entry<Object, Object>>emptySet();
/* 222 */     for (Map.Entry<Object, Object> ent : moduleMapEntries) {
/*     */       
/* 224 */       Object val = ent.getValue();
/*     */       
/* 226 */       Object realModule = classpathOrder.reflectionUtils.invokeMethod(false, val, "getModule");
/* 227 */       handleRealModule(realModule, visitedModules, classLoader, classpathOrder, scanSpec, log);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 232 */     Map<String, List<?>> pathsMap = (Map<String, List<?>>)classpathOrder.reflectionUtils.invokeMethod(false, module, "getPaths");
/* 233 */     for (Map.Entry<String, List<?>> ent : pathsMap.entrySet()) {
/* 234 */       for (Object localLoader : ent.getValue()) {
/*     */         
/* 236 */         Object moduleClassLoader = classpathOrder.reflectionUtils.getFieldVal(false, localLoader, "this$0");
/*     */ 
/*     */         
/* 239 */         Object realModule = classpathOrder.reflectionUtils.getFieldVal(false, moduleClassLoader, "module");
/*     */         
/* 241 */         handleRealModule(realModule, visitedModules, classLoader, classpathOrder, scanSpec, log);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\JBossClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */