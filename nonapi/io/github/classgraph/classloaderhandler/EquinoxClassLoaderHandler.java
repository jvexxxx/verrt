/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
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
/*     */ class EquinoxClassLoaderHandler
/*     */   implements ClassLoaderHandler
/*     */ {
/*     */   private static boolean alreadyReadSystemBundles;
/*  51 */   private static final String[] FIELD_NAMES = new String[] { "cp", "nestedDirName" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/*  67 */     return "org.eclipse.osgi.internal.loader.EquinoxClassLoader".equals(classLoaderClass.getName());
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
/*  82 */     classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
/*  83 */     classLoaderOrder.add(classLoader, log);
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
/*     */   private static void addBundleFile(Object bundlefile, Set<Object> path, ClassLoader classLoader, ClasspathOrder classpathOrderOut, ScanSpec scanSpec, LogNode log) {
/* 106 */     if (bundlefile != null && path.add(bundlefile)) {
/*     */       
/* 108 */       Object baseFile = classpathOrderOut.reflectionUtils.getFieldVal(false, bundlefile, "basefile");
/* 109 */       if (baseFile != null) {
/* 110 */         boolean foundClassPathElement = false;
/* 111 */         for (String fieldName : FIELD_NAMES) {
/* 112 */           Object fieldVal = classpathOrderOut.reflectionUtils.getFieldVal(false, bundlefile, fieldName);
/*     */           
/* 114 */           if (fieldVal != null) {
/* 115 */             foundClassPathElement = true;
/*     */             
/* 117 */             Object base = baseFile;
/* 118 */             String sep = "/";
/* 119 */             if (bundlefile.getClass().getName()
/* 120 */               .equals("org.eclipse.osgi.storage.bundlefile.NestedDirBundleFile")) {
/*     */               
/* 122 */               Object baseBundleFile = classpathOrderOut.reflectionUtils.getFieldVal(false, bundlefile, "baseBundleFile");
/*     */               
/* 124 */               if (baseBundleFile != null && baseBundleFile.getClass().getName()
/* 125 */                 .equals("org.eclipse.osgi.storage.bundlefile.ZipBundleFile")) {
/* 126 */                 base = baseBundleFile;
/* 127 */                 sep = "!/";
/*     */               } 
/*     */             } 
/* 130 */             String pathElement = base + sep + fieldVal;
/* 131 */             classpathOrderOut.addClasspathEntry(pathElement, classLoader, scanSpec, log);
/*     */             break;
/*     */           } 
/*     */         } 
/* 135 */         if (!foundClassPathElement)
/*     */         {
/* 137 */           classpathOrderOut.addClasspathEntry(baseFile.toString(), classLoader, scanSpec, log);
/*     */         }
/*     */       } 
/*     */       
/* 141 */       addBundleFile(classpathOrderOut.reflectionUtils.getFieldVal(false, bundlefile, "wrapped"), path, classLoader, classpathOrderOut, scanSpec, log);
/*     */       
/* 143 */       addBundleFile(classpathOrderOut.reflectionUtils.getFieldVal(false, bundlefile, "next"), path, classLoader, classpathOrderOut, scanSpec, log);
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
/*     */   private static void addClasspathEntries(Object owner, ClassLoader classLoader, ClasspathOrder classpathOrderOut, ScanSpec scanSpec, LogNode log) {
/* 165 */     Object entries = classpathOrderOut.reflectionUtils.getFieldVal(false, owner, "entries");
/* 166 */     if (entries != null) {
/* 167 */       for (int i = 0, n = Array.getLength(entries); i < n; i++) {
/*     */         
/* 169 */         Object entry = Array.get(entries, i);
/*     */         
/* 171 */         Object bundlefile = classpathOrderOut.reflectionUtils.getFieldVal(false, entry, "bundlefile");
/* 172 */         addBundleFile(bundlefile, new HashSet(), classLoader, classpathOrderOut, scanSpec, log);
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
/* 192 */     Object manager = classpathOrder.reflectionUtils.getFieldVal(false, classLoader, "manager");
/* 193 */     addClasspathEntries(manager, classLoader, classpathOrder, scanSpec, log);
/*     */ 
/*     */     
/* 196 */     Object fragments = classpathOrder.reflectionUtils.getFieldVal(false, manager, "fragments");
/* 197 */     if (fragments != null) {
/* 198 */       for (int f = 0, fragLength = Array.getLength(fragments); f < fragLength; f++) {
/*     */         
/* 200 */         Object fragment = Array.get(fragments, f);
/* 201 */         addClasspathEntries(fragment, classLoader, classpathOrder, scanSpec, log);
/*     */       } 
/*     */     }
/*     */     
/* 205 */     if (!alreadyReadSystemBundles) {
/*     */       
/* 207 */       Object delegate = classpathOrder.reflectionUtils.getFieldVal(false, classLoader, "delegate");
/*     */       
/* 209 */       Object container = classpathOrder.reflectionUtils.getFieldVal(false, delegate, "container");
/*     */       
/* 211 */       Object storage = classpathOrder.reflectionUtils.getFieldVal(false, container, "storage");
/*     */       
/* 213 */       Object moduleContainer = classpathOrder.reflectionUtils.getFieldVal(false, storage, "moduleContainer");
/*     */ 
/*     */       
/* 216 */       Object moduleDatabase = classpathOrder.reflectionUtils.getFieldVal(false, moduleContainer, "moduleDatabase");
/*     */ 
/*     */       
/* 219 */       Object modulesById = classpathOrder.reflectionUtils.getFieldVal(false, moduleDatabase, "modulesById");
/*     */ 
/*     */       
/* 222 */       Object module0 = classpathOrder.reflectionUtils.invokeMethod(false, modulesById, "get", Object.class, 
/* 223 */           Long.valueOf(0L));
/*     */       
/* 225 */       Object bundle = classpathOrder.reflectionUtils.invokeMethod(false, module0, "getBundle");
/*     */       
/* 227 */       Object bundleContext = classpathOrder.reflectionUtils.invokeMethod(false, bundle, "getBundleContext");
/*     */ 
/*     */       
/* 230 */       Object bundles = classpathOrder.reflectionUtils.invokeMethod(false, bundleContext, "getBundles");
/* 231 */       if (bundles != null) {
/* 232 */         for (int i = 0, n = Array.getLength(bundles); i < n; i++) {
/*     */           
/* 234 */           Object equinoxBundle = Array.get(bundles, i);
/*     */           
/* 236 */           Object module = classpathOrder.reflectionUtils.getFieldVal(false, equinoxBundle, "module");
/*     */ 
/*     */           
/* 239 */           String location = (String)classpathOrder.reflectionUtils.getFieldVal(false, module, "location");
/*     */           
/* 241 */           if (location != null) {
/* 242 */             int fileIdx = location.indexOf("file:");
/* 243 */             if (fileIdx >= 0) {
/* 244 */               location = location.substring(fileIdx);
/* 245 */               classpathOrder.addClasspathEntry(location, classLoader, scanSpec, log);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/* 250 */       alreadyReadSystemBundles = true;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\EquinoxClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */