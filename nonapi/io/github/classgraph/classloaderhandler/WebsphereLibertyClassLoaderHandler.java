/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class WebsphereLibertyClassLoaderHandler
/*     */   implements ClassLoaderHandler
/*     */ {
/*     */   private static final String PKG_PREFIX = "com.ibm.ws.classloading.internal.";
/*     */   private static final String IBM_APP_CLASS_LOADER = "com.ibm.ws.classloading.internal.AppClassLoader";
/*     */   private static final String IBM_THREAD_CONTEXT_CLASS_LOADER = "com.ibm.ws.classloading.internal.ThreadContextClassLoader";
/*     */   
/*     */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/*  78 */     return ("com.ibm.ws.classloading.internal.AppClassLoader".equals(classLoaderClass.getName()) || "com.ibm.ws.classloading.internal.ThreadContextClassLoader"
/*  79 */       .equals(classLoaderClass.getName()));
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
/*  94 */     classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
/*  95 */     classLoaderOrder.add(classLoader, log);
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
/*     */   private static Collection<Object> getPaths(Object containerClassLoader, ReflectionUtils reflectionUtils) {
/* 112 */     if (containerClassLoader == null) {
/* 113 */       return Collections.emptyList();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     Collection<Object> urls = callGetUrls(containerClassLoader, "getContainerURLs", reflectionUtils);
/* 120 */     if (urls != null && !urls.isEmpty()) {
/* 121 */       return urls;
/*     */     }
/*     */ 
/*     */     
/* 125 */     Object container = reflectionUtils.getFieldVal(false, containerClassLoader, "container");
/* 126 */     if (container == null) {
/* 127 */       return Collections.emptyList();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 132 */     urls = callGetUrls(container, "getURLs", reflectionUtils);
/* 133 */     if (urls != null && !urls.isEmpty()) {
/* 134 */       return urls;
/*     */     }
/*     */ 
/*     */     
/* 138 */     Object delegate = reflectionUtils.getFieldVal(false, container, "delegate");
/* 139 */     if (delegate == null) {
/* 140 */       return Collections.emptyList();
/*     */     }
/*     */     
/* 143 */     String path = (String)reflectionUtils.getFieldVal(false, delegate, "path");
/* 144 */     if (path != null && path.length() > 0) {
/* 145 */       return Collections.singletonList(path);
/*     */     }
/*     */     
/* 148 */     Object base = reflectionUtils.getFieldVal(false, delegate, "base");
/* 149 */     if (base == null)
/*     */     {
/* 151 */       return Collections.emptyList();
/*     */     }
/*     */     
/* 154 */     Object archiveFile = reflectionUtils.getFieldVal(false, base, "archiveFile");
/* 155 */     if (archiveFile != null) {
/* 156 */       File file = (File)archiveFile;
/* 157 */       return Collections.singletonList(file.getAbsolutePath());
/*     */     } 
/* 159 */     return Collections.emptyList();
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
/*     */   private static Collection<Object> callGetUrls(Object container, String methodName, ReflectionUtils reflectionUtils) {
/* 176 */     if (container != null) {
/*     */       try {
/* 178 */         Collection<Object> results = (Collection<Object>)reflectionUtils.invokeMethod(false, container, methodName);
/*     */         
/* 180 */         if (results != null && !results.isEmpty()) {
/* 181 */           Collection<Object> allUrls = new HashSet();
/* 182 */           for (Object result : results) {
/* 183 */             if (result instanceof Collection) {
/*     */               
/* 185 */               for (Object url : result) {
/* 186 */                 if (url != null)
/* 187 */                   allUrls.add(url); 
/*     */               }  continue;
/*     */             } 
/* 190 */             if (result != null) {
/* 191 */               allUrls.add(result);
/*     */             }
/*     */           } 
/* 194 */           return allUrls;
/*     */         } 
/* 196 */       } catch (UnsupportedOperationException unsupportedOperationException) {}
/*     */     }
/*     */ 
/*     */     
/* 200 */     return Collections.emptyList();
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
/* 218 */     Object smartClassPath, appLoader = classpathOrder.reflectionUtils.getFieldVal(false, classLoader, "appLoader");
/* 219 */     if (appLoader != null) {
/* 220 */       smartClassPath = classpathOrder.reflectionUtils.getFieldVal(false, appLoader, "smartClassPath");
/*     */     } else {
/* 222 */       smartClassPath = classpathOrder.reflectionUtils.getFieldVal(false, classLoader, "smartClassPath");
/*     */     } 
/* 224 */     if (smartClassPath != null) {
/*     */ 
/*     */       
/* 227 */       Collection<Object> paths = callGetUrls(smartClassPath, "getClassPath", classpathOrder.reflectionUtils);
/*     */       
/* 229 */       if (!paths.isEmpty()) {
/* 230 */         for (Object path : paths) {
/* 231 */           classpathOrder.addClasspathEntry(path, classLoader, scanSpec, log);
/*     */         
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 237 */         List<Object> classPathElements = (List<Object>)classpathOrder.reflectionUtils.getFieldVal(false, smartClassPath, "classPath");
/* 238 */         if (classPathElements != null && !classPathElements.isEmpty())
/* 239 */           for (Object classPathElement : classPathElements) {
/* 240 */             Collection<Object> subPaths = getPaths(classPathElement, classpathOrder.reflectionUtils);
/*     */             
/* 242 */             for (Object path : subPaths)
/* 243 */               classpathOrder.addClasspathEntry(path, classLoader, scanSpec, log); 
/*     */           }  
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\WebsphereLibertyClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */