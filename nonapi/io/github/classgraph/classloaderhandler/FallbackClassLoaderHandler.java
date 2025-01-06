/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
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
/*     */ class FallbackClassLoaderHandler
/*     */   implements ClassLoaderHandler
/*     */ {
/*     */   public static boolean canHandle(Class<?> classLoaderClass, LogNode log) {
/*  55 */     return true;
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
/*  70 */     classLoaderOrder.delegateTo(classLoader.getParent(), true, log);
/*  71 */     classLoaderOrder.add(classLoader, log);
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
/*  88 */     boolean valid = false;
/*  89 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/*  90 */         .invokeMethod(false, classLoader, "getClassPath"), classLoader, scanSpec, log);
/*     */     
/*  92 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/*  93 */         .invokeMethod(false, classLoader, "getClasspath"), classLoader, scanSpec, log);
/*     */     
/*  95 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/*  96 */         .invokeMethod(false, classLoader, "classpath"), classLoader, scanSpec, log);
/*     */     
/*  98 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/*  99 */         .invokeMethod(false, classLoader, "classPath"), classLoader, scanSpec, log);
/*     */     
/* 101 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 102 */         .invokeMethod(false, classLoader, "cp"), classLoader, scanSpec, log);
/* 103 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 104 */         .getFieldVal(false, classLoader, "classpath"), classLoader, scanSpec, log);
/*     */     
/* 106 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 107 */         .getFieldVal(false, classLoader, "classPath"), classLoader, scanSpec, log);
/*     */     
/* 109 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 110 */         .getFieldVal(false, classLoader, "cp"), classLoader, scanSpec, log);
/* 111 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 112 */         .invokeMethod(false, classLoader, "getPath"), classLoader, scanSpec, log);
/*     */     
/* 114 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 115 */         .invokeMethod(false, classLoader, "getPaths"), classLoader, scanSpec, log);
/*     */     
/* 117 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 118 */         .invokeMethod(false, classLoader, "path"), classLoader, scanSpec, log);
/*     */     
/* 120 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 121 */         .invokeMethod(false, classLoader, "paths"), classLoader, scanSpec, log);
/*     */     
/* 123 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 124 */         .getFieldVal(false, classLoader, "paths"), classLoader, scanSpec, log);
/*     */     
/* 126 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 127 */         .getFieldVal(false, classLoader, "paths"), classLoader, scanSpec, log);
/*     */     
/* 129 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 130 */         .invokeMethod(false, classLoader, "getDir"), classLoader, scanSpec, log);
/*     */     
/* 132 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 133 */         .invokeMethod(false, classLoader, "getDirs"), classLoader, scanSpec, log);
/*     */     
/* 135 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 136 */         .invokeMethod(false, classLoader, "dir"), classLoader, scanSpec, log);
/* 137 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 138 */         .invokeMethod(false, classLoader, "dirs"), classLoader, scanSpec, log);
/*     */     
/* 140 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 141 */         .getFieldVal(false, classLoader, "dir"), classLoader, scanSpec, log);
/* 142 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 143 */         .getFieldVal(false, classLoader, "dirs"), classLoader, scanSpec, log);
/* 144 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 145 */         .invokeMethod(false, classLoader, "getFile"), classLoader, scanSpec, log);
/*     */     
/* 147 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 148 */         .invokeMethod(false, classLoader, "getFiles"), classLoader, scanSpec, log);
/*     */     
/* 150 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 151 */         .invokeMethod(false, classLoader, "file"), classLoader, scanSpec, log);
/*     */     
/* 153 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 154 */         .invokeMethod(false, classLoader, "files"), classLoader, scanSpec, log);
/*     */     
/* 156 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 157 */         .getFieldVal(false, classLoader, "file"), classLoader, scanSpec, log);
/* 158 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 159 */         .getFieldVal(false, classLoader, "files"), classLoader, scanSpec, log);
/*     */     
/* 161 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 162 */         .invokeMethod(false, classLoader, "getJar"), classLoader, scanSpec, log);
/*     */     
/* 164 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 165 */         .invokeMethod(false, classLoader, "getJars"), classLoader, scanSpec, log);
/*     */     
/* 167 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 168 */         .invokeMethod(false, classLoader, "jar"), classLoader, scanSpec, log);
/* 169 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 170 */         .invokeMethod(false, classLoader, "jars"), classLoader, scanSpec, log);
/*     */     
/* 172 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 173 */         .getFieldVal(false, classLoader, "jar"), classLoader, scanSpec, log);
/* 174 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 175 */         .getFieldVal(false, classLoader, "jars"), classLoader, scanSpec, log);
/* 176 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 177 */         .invokeMethod(false, classLoader, "getURL"), classLoader, scanSpec, log);
/*     */     
/* 179 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 180 */         .invokeMethod(false, classLoader, "getURLs"), classLoader, scanSpec, log);
/*     */     
/* 182 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 183 */         .invokeMethod(false, classLoader, "getUrl"), classLoader, scanSpec, log);
/*     */     
/* 185 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 186 */         .invokeMethod(false, classLoader, "getUrls"), classLoader, scanSpec, log);
/*     */     
/* 188 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 189 */         .invokeMethod(false, classLoader, "url"), classLoader, scanSpec, log);
/* 190 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 191 */         .invokeMethod(false, classLoader, "urls"), classLoader, scanSpec, log);
/*     */     
/* 193 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 194 */         .getFieldVal(false, classLoader, "url"), classLoader, scanSpec, log);
/* 195 */     valid |= classpathOrder.addClasspathEntryObject(classpathOrder.reflectionUtils
/* 196 */         .getFieldVal(false, classLoader, "urls"), classLoader, scanSpec, log);
/* 197 */     if (log != null)
/* 198 */       log.log("FallbackClassLoaderHandler " + (valid ? "found" : "did not find") + " classpath entries in unknown ClassLoader " + classLoader); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\FallbackClassLoaderHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */