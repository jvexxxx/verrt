/*     */ package nonapi.io.github.classgraph.utils;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class JarUtils
/*     */ {
/*  50 */   public static final Pattern URL_SCHEME_PATTERN = Pattern.compile("[a-zA-Z][a-zA-Z0-9+-.]+[:].*");
/*     */ 
/*     */   
/*  53 */   private static final Pattern DASH_VERSION = Pattern.compile("-(\\d+(\\.|$))");
/*     */ 
/*     */   
/*  56 */   private static final Pattern NON_ALPHANUM = Pattern.compile("[^A-Za-z0-9]");
/*     */ 
/*     */   
/*  59 */   private static final Pattern REPEATING_DOTS = Pattern.compile("(\\.)(\\1)+");
/*     */ 
/*     */   
/*  62 */   private static final Pattern LEADING_DOTS = Pattern.compile("^\\.");
/*     */ 
/*     */   
/*  65 */   private static final Pattern TRAILING_DOTS = Pattern.compile("\\.$");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   private static final String[] UNIX_NON_PATH_SEPARATORS = new String[] { "jar:", "file:", "http://", "https://", "\\:" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final int[] UNIX_NON_PATH_SEPARATOR_COLON_POSITIONS = new int[UNIX_NON_PATH_SEPARATORS.length]; static {
/*  86 */     for (int i = 0; i < UNIX_NON_PATH_SEPARATORS.length; i++) {
/*  87 */       UNIX_NON_PATH_SEPARATOR_COLON_POSITIONS[i] = UNIX_NON_PATH_SEPARATORS[i].indexOf(':');
/*  88 */       if (UNIX_NON_PATH_SEPARATOR_COLON_POSITIONS[i] < 0) {
/*  89 */         throw new RuntimeException("Could not find ':' in \"" + UNIX_NON_PATH_SEPARATORS[i] + "\"");
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] smartPathSplit(String pathStr, ScanSpec scanSpec) {
/* 112 */     return smartPathSplit(pathStr, File.pathSeparatorChar, scanSpec);
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
/*     */   public static String[] smartPathSplit(String pathStr, char separatorChar, ScanSpec scanSpec) {
/* 128 */     if (pathStr == null || pathStr.isEmpty()) {
/* 129 */       return new String[0];
/*     */     }
/* 131 */     if (separatorChar != ':') {
/*     */       
/* 133 */       List<String> partsFiltered = new ArrayList<>();
/* 134 */       for (String part : pathStr.split(String.valueOf(separatorChar))) {
/* 135 */         String partFiltered = part.trim();
/* 136 */         if (!partFiltered.isEmpty()) {
/* 137 */           partsFiltered.add(partFiltered);
/*     */         }
/*     */       } 
/* 140 */       return partsFiltered.<String>toArray(new String[0]);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 145 */     Set<Integer> splitPoints = new HashSet<>();
/* 146 */     int i = -1; do {
/* 147 */       boolean foundNonPathSeparator = false;
/* 148 */       for (int k = 0; k < UNIX_NON_PATH_SEPARATORS.length; k++) {
/*     */         
/* 150 */         int startIdx = i - UNIX_NON_PATH_SEPARATOR_COLON_POSITIONS[k];
/* 151 */         if (pathStr.regionMatches(true, startIdx, UNIX_NON_PATH_SEPARATORS[k], 0, UNIX_NON_PATH_SEPARATORS[k]
/* 152 */             .length()) && (startIdx == 0 || pathStr
/* 153 */           .charAt(startIdx - 1) == ':')) {
/*     */           
/* 155 */           foundNonPathSeparator = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 159 */       if (!foundNonPathSeparator && scanSpec != null && scanSpec.allowedURLSchemes != null && 
/* 160 */         !scanSpec.allowedURLSchemes.isEmpty())
/*     */       {
/* 162 */         for (String scheme : scanSpec.allowedURLSchemes) {
/*     */           
/* 164 */           if (!scheme.equals("http") && !scheme.equals("https") && !scheme.equals("jar") && 
/* 165 */             !scheme.equals("file")) {
/* 166 */             int schemeLen = scheme.length();
/* 167 */             int startIdx = i - schemeLen;
/* 168 */             if (pathStr.regionMatches(true, startIdx, scheme, 0, schemeLen) && (startIdx == 0 || pathStr
/* 169 */               .charAt(startIdx - 1) == ':')) {
/* 170 */               foundNonPathSeparator = true;
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/* 176 */       if (!foundNonPathSeparator)
/*     */       {
/* 178 */         splitPoints.add(Integer.valueOf(i));
/*     */       }
/*     */       
/* 181 */       i = pathStr.indexOf(':', i + 1);
/* 182 */     } while (i >= 0);
/*     */     
/* 184 */     splitPoints.add(Integer.valueOf(pathStr.length()));
/*     */ 
/*     */ 
/*     */     
/* 188 */     List<Integer> splitPointsSorted = new ArrayList<>(splitPoints);
/* 189 */     CollectionUtils.sortIfNotEmpty(splitPointsSorted);
/* 190 */     List<String> parts = new ArrayList<>();
/* 191 */     for (int j = 1; j < splitPointsSorted.size(); j++) {
/* 192 */       int idx0 = ((Integer)splitPointsSorted.get(j - 1)).intValue();
/* 193 */       int idx1 = ((Integer)splitPointsSorted.get(j)).intValue();
/*     */       
/* 195 */       String part = pathStr.substring(idx0 + 1, idx1).trim();
/* 196 */       part = part.replaceAll("\\\\:", ":");
/*     */       
/* 198 */       if (!part.isEmpty()) {
/* 199 */         parts.add(part);
/*     */       }
/*     */     } 
/* 202 */     return parts.<String>toArray(new String[0]);
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
/*     */   private static void appendPathElt(Object pathElt, StringBuilder buf) {
/* 217 */     if (buf.length() > 0) {
/* 218 */       buf.append(File.pathSeparatorChar);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     String path = (File.separatorChar == '\\') ? pathElt.toString() : pathElt.toString().replaceAll(File.pathSeparator, "\\" + File.pathSeparator);
/* 225 */     buf.append(path);
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
/*     */   public static String pathElementsToPathStr(Object... pathElts) {
/* 238 */     StringBuilder buf = new StringBuilder();
/* 239 */     for (Object pathElt : pathElts) {
/* 240 */       appendPathElt(pathElt, buf);
/*     */     }
/* 242 */     return buf.toString();
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
/*     */   public static String pathElementsToPathStr(Iterable<?> pathElts) {
/* 255 */     StringBuilder buf = new StringBuilder();
/* 256 */     for (Object pathElt : pathElts) {
/* 257 */       appendPathElt(pathElt, buf);
/*     */     }
/* 259 */     return buf.toString();
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
/*     */   public static String leafName(String path) {
/* 272 */     int bangIdx = path.indexOf('!');
/* 273 */     int endIdx = (bangIdx >= 0) ? bangIdx : path.length();
/*     */     
/* 275 */     int leafStartIdx = 1 + ((File.separatorChar == '/') ? path.lastIndexOf('/', endIdx) : Math.max(path.lastIndexOf('/', endIdx), path.lastIndexOf(File.separatorChar, endIdx)));
/*     */ 
/*     */     
/* 278 */     int sepIdx = path.indexOf("---");
/* 279 */     if (sepIdx >= 0) {
/* 280 */       sepIdx += "---".length();
/*     */     }
/* 282 */     leafStartIdx = Math.max(leafStartIdx, sepIdx);
/* 283 */     leafStartIdx = Math.min(leafStartIdx, endIdx);
/* 284 */     return path.substring(leafStartIdx, endIdx);
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
/*     */   public static String classfilePathToClassName(String classfilePath) {
/* 297 */     if (!classfilePath.endsWith(".class")) {
/* 298 */       throw new IllegalArgumentException("Classfile path does not end with \".class\": " + classfilePath);
/*     */     }
/* 300 */     return classfilePath.substring(0, classfilePath.length() - 6).replace('/', '.');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String classNameToClassfilePath(String className) {
/* 311 */     return className.replace('.', '/') + ".class";
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
/*     */   public static String derivedAutomaticModuleName(String jarPath) {
/* 328 */     int endIdx = jarPath.length();
/* 329 */     int lastPlingIdx = jarPath.lastIndexOf('!');
/* 330 */     if (lastPlingIdx > 0 && jarPath
/*     */       
/* 332 */       .lastIndexOf('.') <= Math.max(lastPlingIdx, jarPath.lastIndexOf('/')))
/*     */     {
/* 334 */       endIdx = lastPlingIdx;
/*     */     }
/*     */     
/* 337 */     int secondToLastPlingIdx = (endIdx == 0) ? -1 : jarPath.lastIndexOf("!", endIdx - 1);
/*     */     
/* 339 */     int startIdx = Math.max(secondToLastPlingIdx, jarPath.lastIndexOf('/', endIdx - 1)) + 1;
/*     */     
/* 341 */     int lastDotBeforeLastPlingIdx = jarPath.lastIndexOf('.', endIdx - 1);
/* 342 */     if (lastDotBeforeLastPlingIdx > startIdx)
/*     */     {
/* 344 */       endIdx = lastDotBeforeLastPlingIdx;
/*     */     }
/*     */ 
/*     */     
/* 348 */     String moduleName = jarPath.substring(startIdx, endIdx);
/*     */ 
/*     */     
/* 351 */     Matcher matcher = DASH_VERSION.matcher(moduleName);
/* 352 */     if (matcher.find()) {
/* 353 */       moduleName = moduleName.substring(0, matcher.start());
/*     */     }
/*     */ 
/*     */     
/* 357 */     moduleName = NON_ALPHANUM.matcher(moduleName).replaceAll(".");
/*     */ 
/*     */     
/* 360 */     moduleName = REPEATING_DOTS.matcher(moduleName).replaceAll(".");
/*     */ 
/*     */     
/* 363 */     if (moduleName.length() > 0 && moduleName.charAt(0) == '.') {
/* 364 */       moduleName = LEADING_DOTS.matcher(moduleName).replaceAll("");
/*     */     }
/*     */ 
/*     */     
/* 368 */     int len = moduleName.length();
/* 369 */     if (len > 0 && moduleName.charAt(len - 1) == '.') {
/* 370 */       moduleName = TRAILING_DOTS.matcher(moduleName).replaceAll("");
/*     */     }
/* 372 */     return moduleName;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgrap\\utils\JarUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */