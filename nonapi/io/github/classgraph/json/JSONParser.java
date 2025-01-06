/*     */ package nonapi.io.github.classgraph.json;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import nonapi.io.github.classgraph.types.ParseException;
/*     */ import nonapi.io.github.classgraph.types.Parser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class JSONParser
/*     */   extends Parser
/*     */ {
/*     */   private JSONParser(String string) throws ParseException {
/*  56 */     super(string);
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
/*     */   private int getAndParseHexChar() throws ParseException {
/*  69 */     char hexChar = getc();
/*  70 */     if (hexChar >= '0' && hexChar <= '9')
/*  71 */       return hexChar - 48; 
/*  72 */     if (hexChar >= 'a' && hexChar <= 'f')
/*  73 */       return hexChar - 97 + 10; 
/*  74 */     if (hexChar >= 'A' && hexChar <= 'F') {
/*  75 */       return hexChar - 65 + 10;
/*     */     }
/*  77 */     throw new ParseException(this, "Invalid character in Unicode escape sequence: " + hexChar);
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
/*     */   private CharSequence parseString() throws ParseException {
/*  99 */     skipWhitespace();
/* 100 */     if (peek() != '"') {
/* 101 */       return null;
/*     */     }
/* 103 */     next();
/* 104 */     int startIdx = getPosition();
/*     */ 
/*     */     
/* 107 */     boolean hasEscape = false;
/* 108 */     while (hasMore()) {
/* 109 */       char c = getc();
/* 110 */       if (c == '\\') {
/* 111 */         switch (getc()) {
/*     */           case '"':
/*     */           case '\'':
/*     */           case '/':
/*     */           case '\\':
/*     */           case 'b':
/*     */           case 'f':
/*     */           case 'n':
/*     */           case 'r':
/*     */           case 't':
/* 121 */             hasEscape = true;
/*     */             continue;
/*     */           case 'u':
/* 124 */             hasEscape = true;
/* 125 */             advance(4);
/*     */             continue;
/*     */         } 
/* 128 */         throw new ParseException(this, "Invalid escape sequence: \\" + c);
/*     */       } 
/* 130 */       if (c == '"') {
/*     */         break;
/*     */       }
/*     */     } 
/* 134 */     int endIdx = getPosition() - 1;
/* 135 */     if (!hasEscape) {
/* 136 */       return getSubsequence(startIdx, endIdx);
/*     */     }
/*     */ 
/*     */     
/* 140 */     setPosition(startIdx);
/* 141 */     StringBuilder buf = new StringBuilder();
/* 142 */     while (hasMore()) {
/* 143 */       char c = getc();
/* 144 */       if (c == '\\') {
/* 145 */         int charVal; char c2 = getc();
/* 146 */         switch (c2) {
/*     */           case 'b':
/* 148 */             buf.append('\b');
/*     */             continue;
/*     */           case 'f':
/* 151 */             buf.append('\f');
/*     */             continue;
/*     */           case 'n':
/* 154 */             buf.append('\n');
/*     */             continue;
/*     */           case 'r':
/* 157 */             buf.append('\r');
/*     */             continue;
/*     */           case 't':
/* 160 */             buf.append('\t');
/*     */             continue;
/*     */           case '"':
/*     */           case '\'':
/*     */           case '/':
/*     */           case '\\':
/* 166 */             buf.append(c2);
/*     */             continue;
/*     */           case 'u':
/* 169 */             charVal = 0;
/* 170 */             charVal = getAndParseHexChar() << 12;
/* 171 */             charVal |= getAndParseHexChar() << 8;
/* 172 */             charVal |= getAndParseHexChar() << 4;
/* 173 */             charVal |= getAndParseHexChar();
/* 174 */             buf.append((char)charVal);
/*     */             continue;
/*     */         } 
/* 177 */         throw new ParseException(this, "Invalid escape sequence: \\" + c);
/*     */       } 
/* 179 */       if (c == '"') {
/*     */         break;
/*     */       }
/* 182 */       buf.append(c);
/*     */     } 
/*     */     
/* 185 */     skipWhitespace();
/* 186 */     return buf.toString();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Number parseNumber() throws ParseException {
/* 213 */     int startIdx = getPosition();
/* 214 */     if (peekMatches("Infinity")) {
/* 215 */       advance(8);
/* 216 */       return Double.valueOf(Double.POSITIVE_INFINITY);
/* 217 */     }  if (peekMatches("-Infinity")) {
/* 218 */       advance(9);
/* 219 */       return Double.valueOf(Double.NEGATIVE_INFINITY);
/* 220 */     }  if (peekMatches("NaN")) {
/* 221 */       advance(3);
/* 222 */       return Double.valueOf(Double.NaN);
/*     */     } 
/* 224 */     if (peek() == '-') {
/* 225 */       next();
/*     */     }
/* 227 */     int integralStartIdx = getPosition();
/* 228 */     for (; hasMore(); next()) {
/* 229 */       char c = peek();
/* 230 */       if (c < '0' || c > '9') {
/*     */         break;
/*     */       }
/*     */     } 
/* 234 */     int integralEndIdx = getPosition();
/* 235 */     int numIntegralDigits = integralEndIdx - integralStartIdx;
/* 236 */     if (numIntegralDigits == 0) {
/* 237 */       throw new ParseException(this, "Expected a number");
/*     */     }
/* 239 */     boolean hasFractionalPart = (peek() == '.');
/* 240 */     if (hasFractionalPart) {
/* 241 */       next();
/* 242 */       for (; hasMore(); next()) {
/* 243 */         char c = peek();
/* 244 */         if (c < '0' || c > '9') {
/*     */           break;
/*     */         }
/*     */       } 
/* 248 */       if (getPosition() - integralEndIdx + 1 == 0) {
/* 249 */         throw new ParseException(this, "Expected digits after decimal point");
/*     */       }
/*     */     } 
/* 252 */     boolean hasExponentPart = (peek() == 'e' || peek() == 'E');
/* 253 */     if (hasExponentPart) {
/* 254 */       next();
/* 255 */       char sign = peek();
/* 256 */       if (sign == '-' || sign == '+') {
/* 257 */         next();
/*     */       }
/* 259 */       int exponentStart = getPosition();
/* 260 */       for (; hasMore(); next()) {
/* 261 */         char c = peek();
/* 262 */         if (c < '0' || c > '9') {
/*     */           break;
/*     */         }
/*     */       } 
/* 266 */       if (getPosition() - exponentStart == 0) {
/* 267 */         throw new ParseException(this, "Expected an exponent");
/*     */       }
/*     */     } 
/* 270 */     int endIdx = getPosition();
/* 271 */     String numberStr = getSubstring(startIdx, endIdx);
/* 272 */     if (hasFractionalPart || hasExponentPart)
/* 273 */       return Double.valueOf(numberStr); 
/* 274 */     if (numIntegralDigits < 10)
/* 275 */       return Integer.valueOf(numberStr); 
/* 276 */     if (numIntegralDigits == 10) {
/*     */       
/* 278 */       long longVal = Long.parseLong(numberStr);
/* 279 */       if (longVal >= -2147483648L && longVal <= 2147483647L) {
/* 280 */         return Integer.valueOf((int)longVal);
/*     */       }
/* 282 */       return Long.valueOf(longVal);
/*     */     } 
/*     */     
/* 285 */     return Long.valueOf(numberStr);
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
/*     */   private JSONArray parseJSONArray() throws ParseException {
/* 305 */     expect('[');
/* 306 */     skipWhitespace();
/* 307 */     if (peek() == ']') {
/*     */       
/* 309 */       next();
/* 310 */       return new JSONArray(Collections.emptyList());
/*     */     } 
/*     */     
/* 313 */     List<Object> elements = new ArrayList();
/* 314 */     boolean first = true;
/* 315 */     while (peek() != ']') {
/* 316 */       if (first) {
/* 317 */         first = false;
/*     */       } else {
/* 319 */         expect(',');
/*     */       } 
/* 321 */       elements.add(parseJSON());
/*     */     } 
/* 323 */     expect(']');
/* 324 */     return new JSONArray(elements);
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
/*     */   private JSONObject parseJSONObject() throws ParseException {
/* 344 */     expect('{');
/* 345 */     skipWhitespace();
/* 346 */     if (peek() == '}') {
/*     */       
/* 348 */       next();
/* 349 */       return new JSONObject(Collections.emptyList());
/*     */     } 
/*     */     
/* 352 */     List<Map.Entry<String, Object>> kvPairs = new ArrayList<>();
/* 353 */     JSONObject jsonObject = new JSONObject(kvPairs);
/* 354 */     boolean first = true;
/* 355 */     while (peek() != '}') {
/* 356 */       if (first) {
/* 357 */         first = false;
/*     */       } else {
/* 359 */         expect(',');
/*     */       } 
/* 361 */       CharSequence key = parseString();
/* 362 */       if (key == null) {
/* 363 */         throw new ParseException(this, "Object keys must be strings");
/*     */       }
/* 365 */       if (peek() != ':') {
/* 366 */         return null;
/*     */       }
/* 368 */       expect(':');
/* 369 */       Object value = parseJSON();
/*     */ 
/*     */       
/* 372 */       if (key.equals("__ID")) {
/* 373 */         if (value == null) {
/* 374 */           throw new ParseException(this, "Got null value for \"__ID\" key");
/*     */         }
/* 376 */         jsonObject.objectId = (CharSequence)value; continue;
/*     */       } 
/* 378 */       kvPairs.add(new AbstractMap.SimpleEntry<>(key.toString(), value));
/*     */     } 
/*     */     
/* 381 */     expect('}');
/* 382 */     return jsonObject;
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
/*     */   private Object parseJSON() throws ParseException {
/* 405 */     skipWhitespace();
/* 406 */     char c = peek();
/* 407 */     if (c == '{') {
/*     */       
/* 409 */       JSONObject obj = parseJSONObject();
/* 410 */       skipWhitespace();
/* 411 */       return obj;
/*     */     } 
/* 413 */     if (c == '[') {
/*     */       
/* 415 */       JSONArray arr = parseJSONArray();
/* 416 */       skipWhitespace();
/* 417 */       return arr;
/*     */     } 
/* 419 */     if (c == '"') {
/*     */       
/* 421 */       CharSequence charSequence = parseString();
/* 422 */       skipWhitespace();
/* 423 */       if (charSequence == null) {
/* 424 */         throw new ParseException(this, "Invalid string");
/*     */       }
/* 426 */       return charSequence;
/*     */     } 
/* 428 */     if (peekMatches("true")) {
/*     */       
/* 430 */       advance(4);
/* 431 */       skipWhitespace();
/* 432 */       return Boolean.TRUE;
/*     */     } 
/* 434 */     if (peekMatches("false")) {
/*     */       
/* 436 */       advance(5);
/* 437 */       skipWhitespace();
/* 438 */       return Boolean.FALSE;
/*     */     } 
/* 440 */     if (peekMatches("null")) {
/*     */       
/* 442 */       advance(4);
/* 443 */       skipWhitespace();
/* 444 */       return null;
/*     */     } 
/*     */ 
/*     */     
/* 448 */     Number num = parseNumber();
/* 449 */     skipWhitespace();
/* 450 */     return num;
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
/*     */   static Object parseJSON(String str) throws ParseException {
/* 464 */     return (new JSONParser(str)).parseJSON();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\JSONParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */