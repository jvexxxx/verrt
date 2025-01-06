/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
/*     */ import java.io.File;
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
/*     */ class TomcatWebappClassLoaderBaseHandler
/*     */   implements ClassLoaderHandler
/*     */ {
/*     */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/*  56 */     return "org.apache.catalina.loader.WebappClassLoaderBase".equals(classLoaderClass.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isParentFirst(ClassLoader classLoader, ReflectionUtils reflectionUtils) {
/*  67 */     Object delegateObject = reflectionUtils.getFieldVal(false, classLoader, "delegate");
/*  68 */     if (delegateObject != null) {
/*  69 */       return ((Boolean)delegateObject).booleanValue();
/*     */     }
/*     */     
/*  72 */     return true;
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
/*  87 */     boolean isParentFirst = isParentFirst(classLoader, classLoaderOrder.reflectionUtils);
/*  88 */     if (isParentFirst)
/*     */     {
/*  90 */       classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
/*     */     }
/*  92 */     if ("org.apache.tomee.catalina.TomEEWebappClassLoader".equals(classLoader.getClass().getName())) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/*  97 */         classLoaderOrder.delegateTo(Class.forName("org.apache.openejb.OpenEJB").getClassLoader(), true, log);
/*     */       }
/*  99 */       catch (LinkageError|ClassNotFoundException linkageError) {}
/*     */     }
/*     */ 
/*     */     
/* 103 */     classLoaderOrder.add(classLoader, log);
/* 104 */     if (!isParentFirst)
/*     */     {
/* 106 */       classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
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
/* 125 */     Object resources = classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getResources");
/*     */     
/* 127 */     Object baseURLs = classpathOrder.reflectionUtils.invokeMethod(false, resources, "getBaseUrls");
/* 128 */     classpathOrder.addClasspathEntryObject(baseURLs, classLoader, scanSpec, log);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 133 */     List<List<?>> allResources = (List<List<?>>)classpathOrder.reflectionUtils.getFieldVal(false, resources, "allResources");
/*     */     
/* 135 */     if (allResources != null)
/*     */     {
/* 137 */       for (List<?> webResourceSetList : allResources) {
/*     */ 
/*     */ 
/*     */         
/* 141 */         for (Object webResourceSet : webResourceSetList) {
/* 142 */           if (webResourceSet != null) {
/*     */             
/* 144 */             File file = (File)classpathOrder.reflectionUtils.invokeMethod(false, webResourceSet, "getFileBase");
/*     */             
/* 146 */             String base = (file == null) ? null : file.getPath();
/* 147 */             if (base == null)
/*     */             {
/* 149 */               base = (String)classpathOrder.reflectionUtils.invokeMethod(false, webResourceSet, "getBase");
/*     */             }
/*     */             
/* 152 */             if (base == null)
/*     */             {
/*     */ 
/*     */               
/* 156 */               base = (String)classpathOrder.reflectionUtils.invokeMethod(false, webResourceSet, "getBaseUrlString");
/*     */             }
/*     */             
/* 159 */             if (base != null) {
/*     */ 
/*     */               
/* 162 */               String archivePath = (String)classpathOrder.reflectionUtils.getFieldVal(false, webResourceSet, "archivePath");
/*     */               
/* 164 */               if (archivePath != null && !archivePath.isEmpty())
/*     */               {
/* 166 */                 base = base + "!" + (archivePath.startsWith("/") ? archivePath : ("/" + archivePath));
/*     */               }
/* 168 */               String className = webResourceSet.getClass().getName();
/*     */ 
/*     */               
/* 171 */               boolean isJar = (className.equals("java.org.apache.catalina.webresources.JarResourceSet") || className.equals("java.org.apache.catalina.webresources.JarWarResourceSet"));
/*     */ 
/*     */               
/* 174 */               String internalPath = (String)classpathOrder.reflectionUtils.invokeMethod(false, webResourceSet, "getInternalPath");
/*     */               
/* 176 */               if (internalPath != null && !internalPath.isEmpty() && !internalPath.equals("/")) {
/* 177 */                 classpathOrder.addClasspathEntryObject(base + (isJar ? "!" : "") + (
/* 178 */                     internalPath.startsWith("/") ? internalPath : ("/" + internalPath)), classLoader, scanSpec, log);
/*     */                 continue;
/*     */               } 
/* 181 */               classpathOrder.addClasspathEntryObject(base, classLoader, scanSpec, log);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 189 */     Object urls = classpathOrder.reflectionUtils.invokeMethod(false, classLoader, "getURLs");
/* 190 */     classpathOrder.addClasspathEntryObject(urls, classLoader, scanSpec, log);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\TomcatWebappClassLoaderBaseHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */