/*     */ package nonapi.io.github.classgraph.classpath;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.utils.FastPathResolver;
/*     */ import nonapi.io.github.classgraph.utils.FileUtils;
/*     */ import nonapi.io.github.classgraph.utils.JarUtils;
/*     */ import nonapi.io.github.classgraph.utils.VersionFinder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SystemJarFinder
/*     */ {
/*  44 */   private static final Set<String> RT_JARS = new LinkedHashSet<>();
/*     */ 
/*     */   
/*     */   private static final String RT_JAR;
/*     */ 
/*     */   
/*  50 */   private static final Set<String> JRE_LIB_OR_EXT_JARS = new LinkedHashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     String systemRoot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean addJREPath(File dir) {
/*  67 */     if (dir != null && !dir.getPath().isEmpty() && FileUtils.canReadAndIsDir(dir)) {
/*  68 */       File[] dirFiles = dir.listFiles();
/*  69 */       if (dirFiles != null) {
/*  70 */         for (File file : dirFiles) {
/*  71 */           String filePath = file.getPath();
/*  72 */           if (filePath.endsWith(".jar")) {
/*  73 */             String jarPathResolved = FastPathResolver.resolve(FileUtils.currDirPath(), filePath);
/*  74 */             if (jarPathResolved.endsWith("/rt.jar")) {
/*  75 */               RT_JARS.add(jarPathResolved);
/*     */             } else {
/*  77 */               JRE_LIB_OR_EXT_JARS.add(jarPathResolved);
/*     */             } 
/*     */             try {
/*  80 */               File canonicalFile = file.getCanonicalFile();
/*  81 */               String canonicalFilePath = canonicalFile.getPath();
/*  82 */               if (!canonicalFilePath.equals(filePath)) {
/*     */                 
/*  84 */                 String canonicalJarPathResolved = FastPathResolver.resolve(FileUtils.currDirPath(), filePath);
/*  85 */                 JRE_LIB_OR_EXT_JARS.add(canonicalJarPathResolved);
/*     */               } 
/*  87 */             } catch (IOException|SecurityException iOException) {}
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/*  92 */         return true;
/*     */       } 
/*     */     } 
/*  95 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 100 */     String javaHome = VersionFinder.getProperty("java.home");
/* 101 */     if (javaHome == null || javaHome.isEmpty()) {
/* 102 */       javaHome = System.getenv("JAVA_HOME");
/*     */     }
/* 104 */     if (javaHome != null && !javaHome.isEmpty()) {
/* 105 */       File javaHomeFile = new File(javaHome);
/* 106 */       addJREPath(javaHomeFile);
/* 107 */       if (javaHomeFile.getName().equals("jre")) {
/*     */         
/* 109 */         File jreParent = javaHomeFile.getParentFile();
/* 110 */         addJREPath(jreParent);
/* 111 */         addJREPath(new File(jreParent, "lib"));
/* 112 */         addJREPath(new File(jreParent, "lib/ext"));
/*     */       } else {
/*     */         
/* 115 */         addJREPath(new File(javaHomeFile, "jre"));
/*     */       } 
/* 117 */       addJREPath(new File(javaHomeFile, "lib"));
/* 118 */       addJREPath(new File(javaHomeFile, "lib/ext"));
/* 119 */       addJREPath(new File(javaHomeFile, "jre/lib"));
/* 120 */       addJREPath(new File(javaHomeFile, "jre/lib/ext"));
/* 121 */       addJREPath(new File(javaHomeFile, "packages"));
/* 122 */       addJREPath(new File(javaHomeFile, "packages/lib"));
/* 123 */       addJREPath(new File(javaHomeFile, "packages/lib/ext"));
/*     */     } 
/* 125 */     String javaExtDirs = VersionFinder.getProperty("java.ext.dirs");
/* 126 */     if (javaExtDirs != null && !javaExtDirs.isEmpty()) {
/* 127 */       for (String javaExtDir : JarUtils.smartPathSplit(javaExtDirs, null)) {
/* 128 */         if (!javaExtDir.isEmpty()) {
/* 129 */           addJREPath(new File(javaExtDir));
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 135 */     switch (VersionFinder.OS) {
/*     */       case Linux:
/*     */       case Unix:
/*     */       case BSD:
/*     */       case Unknown:
/* 140 */         addJREPath(new File("/usr/java/packages"));
/* 141 */         addJREPath(new File("/usr/java/packages/lib"));
/* 142 */         addJREPath(new File("/usr/java/packages/lib/ext"));
/*     */         break;
/*     */       case MacOSX:
/* 145 */         addJREPath(new File("/System/Library/Java"));
/* 146 */         addJREPath(new File("/System/Library/Java/Libraries"));
/* 147 */         addJREPath(new File("/System/Library/Java/Extensions"));
/*     */         break;
/*     */       case Windows:
/* 150 */         systemRoot = (File.separatorChar == '\\') ? System.getenv("SystemRoot") : null;
/* 151 */         if (systemRoot != null) {
/* 152 */           addJREPath(new File(systemRoot, "Sun\\Java"));
/* 153 */           addJREPath(new File(systemRoot, "Sun\\Java\\lib"));
/* 154 */           addJREPath(new File(systemRoot, "Sun\\Java\\lib\\ext"));
/* 155 */           addJREPath(new File(systemRoot, "Oracle\\Java"));
/* 156 */           addJREPath(new File(systemRoot, "Oracle\\Java\\lib"));
/* 157 */           addJREPath(new File(systemRoot, "Oracle\\Java\\lib\\ext"));
/*     */         } 
/*     */         break;
/*     */       
/*     */       case Solaris:
/* 162 */         addJREPath(new File("/usr/jdk/packages"));
/* 163 */         addJREPath(new File("/usr/jdk/packages/lib"));
/* 164 */         addJREPath(new File("/usr/jdk/packages/lib/ext"));
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 170 */     RT_JAR = RT_JARS.isEmpty() ? null : FastPathResolver.resolve(RT_JARS.iterator().next());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getJreRtJarPath() {
/* 180 */     return RT_JAR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Set<String> getJreLibOrExtJars() {
/* 189 */     return JRE_LIB_OR_EXT_JARS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classpath\SystemJarFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */