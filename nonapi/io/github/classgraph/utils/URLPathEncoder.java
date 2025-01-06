/*     */ package nonapi.io.github.classgraph.utils;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class URLPathEncoder
/*     */ {
/*  41 */   private static boolean[] safe = new boolean[256];
/*     */   static {
/*     */     int i;
/*  44 */     for (i = 97; i <= 122; i++) {
/*  45 */       safe[i] = true;
/*     */     }
/*  47 */     for (i = 65; i <= 90; i++) {
/*  48 */       safe[i] = true;
/*     */     }
/*  50 */     for (i = 48; i <= 57; i++) {
/*  51 */       safe[i] = true;
/*     */     }
/*     */     
/*  54 */     safe[43] = true; safe[46] = true; safe[95] = true; safe[45] = true; safe[36] = true;
/*     */     
/*  56 */     safe[44] = true; safe[41] = true; safe[40] = true; safe[39] = true; safe[42] = true; safe[33] = true;
/*     */     
/*  58 */     safe[47] = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private static final char[] HEXADECIMAL = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*     */ 
/*     */ 
/*     */   
/*  68 */   private static final String[] SCHEME_PREFIXES = new String[] { "jrt:", "file:", "jar:file:", "jar:", "http:", "https:" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void unescapeChars(String str, boolean isQuery, ByteArrayOutputStream buf) {
/*  79 */     if (str.isEmpty()) {
/*     */       return;
/*     */     }
/*  82 */     for (int chrIdx = 0, len = str.length(); chrIdx < len; chrIdx++) {
/*  83 */       char c = str.charAt(chrIdx);
/*  84 */       if (c == '%') {
/*     */         
/*  86 */         if (chrIdx <= len - 3) {
/*     */ 
/*     */           
/*  89 */           char c1 = str.charAt(++chrIdx);
/*     */ 
/*     */           
/*  92 */           int digit1 = (c1 >= '0' && c1 <= '9') ? (c1 - 48) : ((c1 >= 'a' && c1 <= 'f') ? (c1 - 97 + 10) : ((c1 >= 'A' && c1 <= 'F') ? (c1 - 65 + 10) : -1));
/*  93 */           char c2 = str.charAt(++chrIdx);
/*     */ 
/*     */           
/*  96 */           int digit2 = (c2 >= '0' && c2 <= '9') ? (c2 - 48) : ((c2 >= 'a' && c2 <= 'f') ? (c2 - 97 + 10) : ((c2 >= 'A' && c2 <= 'F') ? (c2 - 65 + 10) : -1));
/*  97 */           if (digit1 < 0 || digit2 < 0) {
/*     */             try {
/*  99 */               buf.write(str.substring(chrIdx - 2, chrIdx + 1).getBytes(StandardCharsets.UTF_8));
/* 100 */             } catch (IOException iOException) {}
/*     */           }
/*     */           else {
/*     */             
/* 104 */             buf.write((byte)(digit1 << 4 | digit2));
/*     */           } 
/*     */         } 
/* 107 */       } else if (isQuery && c == '+') {
/* 108 */         buf.write(32);
/* 109 */       } else if (c <= '') {
/* 110 */         buf.write((byte)c);
/*     */       } else {
/*     */         try {
/* 113 */           buf.write(Character.toString(c).getBytes(StandardCharsets.UTF_8));
/* 114 */         } catch (IOException iOException) {}
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
/*     */   public static String decodePath(String str) {
/* 129 */     int queryIdx = str.indexOf('?');
/* 130 */     String partBeforeQuery = (queryIdx < 0) ? str : str.substring(0, queryIdx);
/* 131 */     String partFromQuery = (queryIdx < 0) ? "" : str.substring(queryIdx);
/* 132 */     ByteArrayOutputStream buf = new ByteArrayOutputStream();
/* 133 */     unescapeChars(partBeforeQuery, false, buf);
/* 134 */     unescapeChars(partFromQuery, true, buf);
/* 135 */     return new String(buf.toByteArray(), StandardCharsets.UTF_8);
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
/*     */   public static String encodePath(String path) {
/* 147 */     int validColonPrefixLen = 0;
/* 148 */     for (String scheme : SCHEME_PREFIXES) {
/* 149 */       if (path.startsWith(scheme)) {
/* 150 */         validColonPrefixLen = scheme.length();
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 155 */     if (VersionFinder.OS == VersionFinder.OperatingSystem.Windows) {
/* 156 */       int j = validColonPrefixLen;
/* 157 */       if (j < path.length() && path.charAt(j) == '/') {
/* 158 */         j++;
/*     */       }
/* 160 */       if (j < path.length() - 1 && Character.isLetter(path.charAt(j)) && path.charAt(j + 1) == ':') {
/* 161 */         validColonPrefixLen = j + 2;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 166 */     byte[] pathBytes = path.getBytes(StandardCharsets.UTF_8);
/* 167 */     StringBuilder encodedPath = new StringBuilder(pathBytes.length * 3);
/* 168 */     for (int i = 0; i < pathBytes.length; i++) {
/* 169 */       byte pathByte = pathBytes[i];
/* 170 */       int b = pathByte & 0xFF;
/* 171 */       if (safe[b] || (b == 58 && i < validColonPrefixLen)) {
/* 172 */         encodedPath.append((char)b);
/*     */       } else {
/* 174 */         encodedPath.append('%');
/* 175 */         encodedPath.append(HEXADECIMAL[(b & 0xF0) >> 4]);
/* 176 */         encodedPath.append(HEXADECIMAL[b & 0xF]);
/*     */       } 
/*     */     } 
/* 179 */     return encodedPath.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String normalizeURLPath(String urlPath) {
/* 190 */     String urlPathNormalized = urlPath;
/* 191 */     if (!urlPathNormalized.startsWith("jrt:") && !urlPathNormalized.startsWith("http://") && 
/* 192 */       !urlPathNormalized.startsWith("https://")) {
/*     */ 
/*     */       
/* 195 */       if (urlPathNormalized.startsWith("jar:")) {
/* 196 */         urlPathNormalized = urlPathNormalized.substring(4);
/*     */       }
/* 198 */       if (urlPathNormalized.startsWith("file:")) {
/* 199 */         urlPathNormalized = urlPathNormalized.substring(4);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 204 */       String windowsDrivePrefix = "";
/* 205 */       if (VersionFinder.OS == VersionFinder.OperatingSystem.Windows) {
/* 206 */         if (urlPathNormalized.length() >= 2 && Character.isLetter(urlPathNormalized.charAt(0)) && urlPathNormalized
/* 207 */           .charAt(1) == ':') {
/*     */           
/* 209 */           windowsDrivePrefix = urlPathNormalized.substring(0, 2);
/* 210 */           urlPathNormalized = urlPathNormalized.substring(2);
/* 211 */         } else if (urlPathNormalized.length() >= 3 && urlPathNormalized.charAt(0) == '/' && 
/* 212 */           Character.isLetter(urlPathNormalized.charAt(1)) && urlPathNormalized.charAt(2) == ':') {
/*     */           
/* 214 */           windowsDrivePrefix = urlPathNormalized.substring(1, 3);
/* 215 */           urlPathNormalized = urlPathNormalized.substring(3);
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 220 */       urlPathNormalized = urlPathNormalized.replace("/!", "!").replace("!/", "!").replace("!", "!/");
/*     */ 
/*     */       
/* 223 */       if (windowsDrivePrefix.isEmpty()) {
/*     */ 
/*     */         
/* 226 */         urlPathNormalized = urlPathNormalized.startsWith("/") ? ("file:" + urlPathNormalized) : ("file:/" + urlPathNormalized);
/*     */       }
/*     */       else {
/*     */         
/* 230 */         urlPathNormalized = "file:/" + windowsDrivePrefix + (urlPathNormalized.startsWith("/") ? urlPathNormalized : ("/" + urlPathNormalized));
/*     */       } 
/*     */ 
/*     */       
/* 234 */       if (urlPathNormalized.contains("!") && !urlPathNormalized.startsWith("jar:")) {
/* 235 */         urlPathNormalized = "jar:" + urlPathNormalized;
/*     */       }
/*     */     } 
/* 238 */     return encodePath(urlPathNormalized);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgrap\\utils\URLPathEncoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */