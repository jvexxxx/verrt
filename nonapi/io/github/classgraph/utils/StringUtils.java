/*     */ package nonapi.io.github.classgraph.utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StringUtils
/*     */ {
/*     */   public static String readString(byte[] arr, int startOffset, int numBytes, boolean replaceSlashWithDot, boolean stripLSemicolon) throws IllegalArgumentException {
/*  62 */     if (startOffset < 0L || numBytes < 0 || startOffset + numBytes > arr.length) {
/*  63 */       throw new IllegalArgumentException("offset or numBytes out of range");
/*     */     }
/*  65 */     char[] chars = new char[numBytes];
/*  66 */     int byteIdx = 0;
/*  67 */     int charIdx = 0;
/*  68 */     for (; byteIdx < numBytes; byteIdx++) {
/*  69 */       int c = arr[startOffset + byteIdx] & 0xFF;
/*  70 */       if (c > 127) {
/*     */         break;
/*     */       }
/*  73 */       chars[charIdx++] = (char)((replaceSlashWithDot && c == 47) ? '.' : c);
/*     */     } 
/*  75 */     while (byteIdx < numBytes) {
/*  76 */       int c2, c3, c4, c = arr[startOffset + byteIdx] & 0xFF;
/*  77 */       switch (c >> 4) {
/*     */         case 0:
/*     */         case 1:
/*     */         case 2:
/*     */         case 3:
/*     */         case 4:
/*     */         case 5:
/*     */         case 6:
/*     */         case 7:
/*  86 */           byteIdx++;
/*  87 */           chars[charIdx++] = (char)((replaceSlashWithDot && c == 47) ? '.' : c);
/*     */           continue;
/*     */         
/*     */         case 12:
/*     */         case 13:
/*  92 */           byteIdx += 2;
/*  93 */           if (byteIdx > numBytes) {
/*  94 */             throw new IllegalArgumentException("Bad modified UTF8");
/*     */           }
/*  96 */           c2 = arr[startOffset + byteIdx - 1];
/*  97 */           if ((c2 & 0xC0) != 128) {
/*  98 */             throw new IllegalArgumentException("Bad modified UTF8");
/*     */           }
/* 100 */           c3 = (c & 0x1F) << 6 | c2 & 0x3F;
/* 101 */           chars[charIdx++] = (char)((replaceSlashWithDot && c3 == 47) ? '.' : c3);
/*     */           continue;
/*     */         
/*     */         case 14:
/* 105 */           byteIdx += 3;
/* 106 */           if (byteIdx > numBytes) {
/* 107 */             throw new IllegalArgumentException("Bad modified UTF8");
/*     */           }
/* 109 */           c2 = arr[startOffset + byteIdx - 2];
/* 110 */           c3 = arr[startOffset + byteIdx - 1];
/* 111 */           if ((c2 & 0xC0) != 128 || (c3 & 0xC0) != 128) {
/* 112 */             throw new IllegalArgumentException("Bad modified UTF8");
/*     */           }
/* 114 */           c4 = (c & 0xF) << 12 | (c2 & 0x3F) << 6 | c3 & 0x3F;
/* 115 */           chars[charIdx++] = (char)((replaceSlashWithDot && c4 == 47) ? '.' : c4);
/*     */           continue;
/*     */       } 
/*     */       
/* 119 */       throw new IllegalArgumentException("Bad modified UTF8");
/*     */     } 
/*     */     
/* 122 */     if (charIdx == numBytes && !stripLSemicolon) {
/* 123 */       return new String(chars);
/*     */     }
/* 125 */     if (stripLSemicolon) {
/* 126 */       if (charIdx < 2 || chars[0] != 'L' || chars[charIdx - 1] != ';') {
/* 127 */         throw new IllegalArgumentException("Expected string to start with 'L' and end with ';', got \"" + new String(chars) + "\"");
/*     */       }
/*     */       
/* 130 */       return new String(chars, 1, charIdx - 2);
/*     */     } 
/* 132 */     return new String(chars, 0, charIdx);
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
/*     */   public static void join(StringBuilder buf, String addAtBeginning, String sep, String addAtEnd, Iterable<?> iterable) {
/* 153 */     if (!addAtBeginning.isEmpty()) {
/* 154 */       buf.append(addAtBeginning);
/*     */     }
/* 156 */     boolean first = true;
/* 157 */     for (Object item : iterable) {
/* 158 */       if (first) {
/* 159 */         first = false;
/*     */       } else {
/* 161 */         buf.append(sep);
/*     */       } 
/* 163 */       buf.append((item == null) ? "null" : item.toString());
/*     */     } 
/* 165 */     if (!addAtEnd.isEmpty()) {
/* 166 */       buf.append(addAtEnd);
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
/*     */   public static String join(String sep, Iterable<?> iterable) {
/* 180 */     StringBuilder buf = new StringBuilder();
/* 181 */     join(buf, "", sep, "", iterable);
/* 182 */     return buf.toString();
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
/*     */   public static String join(String sep, Object... items) {
/* 195 */     StringBuilder buf = new StringBuilder();
/* 196 */     boolean first = true;
/* 197 */     for (Object item : items) {
/* 198 */       if (first) {
/* 199 */         first = false;
/*     */       } else {
/* 201 */         buf.append(sep);
/*     */       } 
/* 203 */       buf.append(item.toString());
/*     */     } 
/* 205 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgrap\\utils\StringUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */