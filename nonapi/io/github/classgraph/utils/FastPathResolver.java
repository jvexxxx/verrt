/*     */ package nonapi.io.github.classgraph.utils;
/*     */ 
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FastPathResolver
/*     */ {
/*  44 */   private static final Pattern percentMatcher = Pattern.compile("([%][0-9a-fA-F][0-9a-fA-F])+");
/*     */ 
/*     */   
/*  47 */   private static final Pattern schemeTwoSlashMatcher = Pattern.compile("^[a-zA-Z+\\-.]+://");
/*     */ 
/*     */   
/*  50 */   private static final Pattern schemeOneSlashMatcher = Pattern.compile("^[a-zA-Z+\\-.]+:/");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void translateSeparator(String path, int startIdx, int endIdx, boolean stripFinalSeparator, StringBuilder buf) {
/*  75 */     for (int i = startIdx; i < endIdx; i++) {
/*  76 */       char c = path.charAt(i);
/*  77 */       if (c == '\\' || c == '/') {
/*     */         
/*  79 */         if (i < endIdx - 1 || !stripFinalSeparator) {
/*     */           
/*  81 */           char prevChar = (buf.length() == 0) ? Character.MIN_VALUE : buf.charAt(buf.length() - 1);
/*  82 */           if (prevChar != '/') {
/*  83 */             buf.append('/');
/*     */           }
/*     */         } 
/*     */       } else {
/*  87 */         buf.append(c);
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
/*     */   private static int hexCharToInt(char c) {
/* 100 */     return (c >= '0' && c <= '9') ? (c - 48) : (
/* 101 */       (c >= 'a' && c <= 'f') ? (c - 97 + 10) : (
/* 102 */       c - 65 + 10));
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
/*     */   private static void unescapePercentEncoding(String path, int startIdx, int endIdx, StringBuilder buf) {
/* 119 */     if (endIdx - startIdx == 3 && path.charAt(startIdx + 1) == '2' && path.charAt(startIdx + 2) == '0') {
/*     */       
/* 121 */       buf.append(' ');
/*     */     } else {
/* 123 */       byte[] bytes = new byte[(endIdx - startIdx) / 3];
/* 124 */       for (int i = startIdx, j = 0; i < endIdx; i += 3, j++) {
/* 125 */         char c1 = path.charAt(i + 1);
/* 126 */         char c2 = path.charAt(i + 2);
/* 127 */         int digit1 = hexCharToInt(c1);
/* 128 */         int digit2 = hexCharToInt(c2);
/* 129 */         bytes[j] = (byte)(digit1 << 4 | digit2);
/*     */       } 
/*     */       
/* 132 */       String str = new String(bytes, StandardCharsets.UTF_8);
/*     */       
/* 134 */       str = str.replace("/", "%2F").replace("\\", "%5C");
/* 135 */       buf.append(str);
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
/*     */   public static String normalizePath(String path, boolean isFileOrJarURL) {
/* 150 */     boolean hasPercent = (path.indexOf('%') >= 0);
/* 151 */     if (!hasPercent && path.indexOf('\\') < 0 && !path.endsWith("/")) {
/* 152 */       return path;
/*     */     }
/* 154 */     int len = path.length();
/* 155 */     StringBuilder buf = new StringBuilder();
/*     */     
/* 157 */     if (hasPercent && isFileOrJarURL) {
/*     */       
/* 159 */       int prevEndMatchIdx = 0;
/* 160 */       Matcher matcher = percentMatcher.matcher(path);
/* 161 */       while (matcher.find()) {
/* 162 */         int startMatchIdx = matcher.start();
/* 163 */         int endMatchIdx = matcher.end();
/* 164 */         translateSeparator(path, prevEndMatchIdx, startMatchIdx, false, buf);
/*     */         
/* 166 */         unescapePercentEncoding(path, startMatchIdx, endMatchIdx, buf);
/* 167 */         prevEndMatchIdx = endMatchIdx;
/*     */       } 
/* 169 */       translateSeparator(path, prevEndMatchIdx, len, true, buf);
/*     */     } else {
/*     */       
/* 172 */       translateSeparator(path, 0, len, true, buf);
/* 173 */       return buf.toString();
/*     */     } 
/* 175 */     return buf.toString();
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
/*     */   public static String resolve(String resolveBasePath, String relativePath) {
/*     */     boolean matchedPrefix;
/*     */     String pathResolved;
/* 193 */     if (relativePath == null || relativePath.isEmpty()) {
/* 194 */       return (resolveBasePath == null) ? "" : resolveBasePath;
/*     */     }
/*     */     
/* 197 */     String prefix = "";
/* 198 */     boolean isAbsolutePath = false;
/* 199 */     boolean isFileOrJarURL = false;
/* 200 */     int startIdx = 0;
/*     */     
/*     */     do {
/* 203 */       matchedPrefix = false;
/* 204 */       if (relativePath.regionMatches(true, startIdx, "jar:", 0, 4)) {
/*     */         
/* 206 */         matchedPrefix = true;
/* 207 */         startIdx = 4;
/* 208 */         isFileOrJarURL = true;
/* 209 */       } else if (relativePath.regionMatches(true, startIdx, "http://", 0, 7)) {
/*     */         
/* 211 */         matchedPrefix = true;
/* 212 */         startIdx += 7;
/*     */         
/* 214 */         prefix = prefix + "http://";
/*     */ 
/*     */         
/* 217 */         isAbsolutePath = true;
/*     */       }
/* 219 */       else if (relativePath.regionMatches(true, startIdx, "https://", 0, 8)) {
/*     */         
/* 221 */         matchedPrefix = true;
/* 222 */         startIdx += 8;
/* 223 */         prefix = prefix + "https://";
/* 224 */         isAbsolutePath = true;
/* 225 */       } else if (relativePath.regionMatches(true, startIdx, "jrt:", 0, 5)) {
/*     */         
/* 227 */         matchedPrefix = true;
/* 228 */         startIdx += 4;
/* 229 */         prefix = prefix + "jrt:";
/* 230 */         isAbsolutePath = true;
/* 231 */       } else if (relativePath.regionMatches(true, startIdx, "file:", 0, 5)) {
/*     */         
/* 233 */         matchedPrefix = true;
/* 234 */         startIdx += 5;
/* 235 */         isFileOrJarURL = true;
/*     */       } else {
/*     */         
/* 238 */         String relPath = (startIdx == 0) ? relativePath : relativePath.substring(startIdx);
/* 239 */         Matcher m2 = schemeTwoSlashMatcher.matcher(relPath);
/* 240 */         if (m2.find()) {
/* 241 */           matchedPrefix = true;
/* 242 */           String m2Match = m2.group();
/* 243 */           startIdx += m2Match.length();
/* 244 */           prefix = prefix + m2Match;
/*     */ 
/*     */           
/* 247 */           isAbsolutePath = true;
/*     */         } else {
/* 249 */           Matcher m1 = schemeOneSlashMatcher.matcher(relPath);
/* 250 */           if (m1.find()) {
/* 251 */             matchedPrefix = true;
/* 252 */             String m1Match = m1.group();
/* 253 */             startIdx += m1Match.length();
/* 254 */             prefix = prefix + m1Match;
/* 255 */             isAbsolutePath = true;
/*     */           } 
/*     */         } 
/*     */       } 
/* 259 */     } while (matchedPrefix);
/*     */ 
/*     */     
/* 262 */     if (VersionFinder.OS == VersionFinder.OperatingSystem.Windows) {
/* 263 */       if (relativePath.startsWith("//", startIdx) || relativePath.startsWith("\\\\", startIdx)) {
/*     */         
/* 265 */         startIdx += 2;
/* 266 */         prefix = prefix + "//";
/* 267 */         isAbsolutePath = true;
/* 268 */       } else if (relativePath.length() - startIdx > 2 && Character.isLetter(relativePath.charAt(startIdx)) && relativePath
/* 269 */         .charAt(startIdx + 1) == ':') {
/*     */         
/* 271 */         isAbsolutePath = true;
/* 272 */       } else if (relativePath.length() - startIdx > 3 && (relativePath
/* 273 */         .charAt(startIdx) == '/' || relativePath.charAt(startIdx) == '\\') && 
/* 274 */         Character.isLetter(relativePath.charAt(startIdx + 1)) && relativePath
/* 275 */         .charAt(startIdx + 2) == ':') {
/*     */         
/* 277 */         isAbsolutePath = true;
/* 278 */         startIdx++;
/*     */       } 
/*     */     }
/*     */     
/* 282 */     if (relativePath.length() - startIdx > 1 && (relativePath
/* 283 */       .charAt(startIdx) == '/' || relativePath.charAt(startIdx) == '\\')) {
/* 284 */       isAbsolutePath = true;
/*     */     }
/*     */ 
/*     */     
/* 288 */     String pathStr = normalizePath((startIdx == 0) ? relativePath : relativePath.substring(startIdx), isFileOrJarURL);
/*     */     
/* 290 */     if (!pathStr.equals("/")) {
/*     */       
/* 292 */       if (pathStr.endsWith("/")) {
/* 293 */         pathStr = pathStr.substring(0, pathStr.length() - 1);
/*     */       }
/* 295 */       if (pathStr.endsWith("!")) {
/* 296 */         pathStr = pathStr.substring(0, pathStr.length() - 1);
/*     */       }
/* 298 */       if (pathStr.endsWith("/")) {
/* 299 */         pathStr = pathStr.substring(0, pathStr.length() - 1);
/*     */       }
/* 301 */       if (pathStr.isEmpty()) {
/* 302 */         pathStr = "/";
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 308 */     if (isAbsolutePath || resolveBasePath == null || resolveBasePath.isEmpty()) {
/*     */ 
/*     */       
/* 311 */       pathResolved = FileUtils.sanitizeEntryPath(pathStr, false, true);
/*     */     }
/*     */     else {
/*     */       
/* 315 */       pathResolved = FileUtils.sanitizeEntryPath(resolveBasePath + (
/* 316 */           resolveBasePath.endsWith("/") ? "" : "/") + pathStr, false, true);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 321 */     return prefix.isEmpty() ? pathResolved : (prefix + pathResolved);
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
/*     */   public static String resolve(String pathStr) {
/* 333 */     return resolve(null, pathStr);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgrap\\utils\FastPathResolver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */