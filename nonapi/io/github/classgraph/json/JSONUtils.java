/*     */ package nonapi.io.github.classgraph.json;
/*     */ 
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Collection;
/*     */ import java.util.concurrent.Callable;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class JSONUtils
/*     */ {
/*     */   private static Method isAccessibleMethod;
/*     */   private static Method setAccessibleMethod;
/*     */   private static Method trySetAccessibleMethod;
/*     */   static final String ID_KEY = "__ID";
/*     */   static final String ID_PREFIX = "[#";
/*     */   static final String ID_SUFFIX = "]";
/*     */   
/*     */   static {
/*     */     try {
/*  53 */       isAccessibleMethod = AccessibleObject.class.getDeclaredMethod("isAccessible", new Class[0]);
/*  54 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/*     */     try {
/*  58 */       setAccessibleMethod = AccessibleObject.class.getDeclaredMethod("setAccessible", new Class[] { boolean.class });
/*  59 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/*     */     try {
/*  63 */       trySetAccessibleMethod = AccessibleObject.class.getDeclaredMethod("trySetAccessible", new Class[0]);
/*  64 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isAccessible(AccessibleObject obj) {
/*  70 */     if (isAccessibleMethod != null) {
/*     */       
/*     */       try {
/*  73 */         if (((Boolean)isAccessibleMethod.invoke(obj, new Object[0])).booleanValue()) {
/*  74 */           return true;
/*     */         }
/*  76 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean tryMakeAccessible(AccessibleObject obj) {
/*  84 */     if (setAccessibleMethod != null) {
/*     */       try {
/*  86 */         setAccessibleMethod.invoke(obj, new Object[] { Boolean.valueOf(true) });
/*  87 */         return true;
/*  88 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/*  92 */     if (trySetAccessibleMethod != null) {
/*     */       try {
/*  94 */         if (((Boolean)trySetAccessibleMethod.invoke(obj, new Object[0])).booleanValue()) {
/*  95 */           return true;
/*     */         }
/*  97 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean makeAccessible(final AccessibleObject obj, ReflectionUtils reflectionUtils) {
/* 108 */     if (isAccessible(obj) || tryMakeAccessible(obj)) {
/* 109 */       return true;
/*     */     }
/*     */     try {
/* 112 */       return ((Boolean)reflectionUtils.doPrivileged(new Callable<Boolean>()
/*     */           {
/*     */             public Boolean call() throws Exception {
/* 115 */               return Boolean.valueOf(JSONUtils.tryMakeAccessible(obj));
/*     */             }
/*     */           })).booleanValue();
/* 118 */     } catch (Throwable t) {
/* 119 */       return false;
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
/* 138 */   private static final String[] JSON_CHAR_REPLACEMENTS = new String[256];
/*     */   
/*     */   static {
/* 141 */     for (int c = 0; c < 256; c++) {
/* 142 */       if (c == 32) {
/* 143 */         c = 127;
/*     */       }
/* 145 */       int nibble1 = c >> 4;
/* 146 */       char hexDigit1 = (nibble1 <= 9) ? (char)(48 + nibble1) : (char)(65 + nibble1 - 10);
/* 147 */       int nibble0 = c & 0xF;
/* 148 */       char hexDigit0 = (nibble0 <= 9) ? (char)(48 + nibble0) : (char)(65 + nibble0 - 10);
/* 149 */       JSON_CHAR_REPLACEMENTS[c] = "\\u00" + hexDigit1 + "" + hexDigit0;
/*     */     } 
/* 151 */     JSON_CHAR_REPLACEMENTS[34] = "\\\"";
/* 152 */     JSON_CHAR_REPLACEMENTS[92] = "\\\\";
/* 153 */     JSON_CHAR_REPLACEMENTS[10] = "\\n";
/* 154 */     JSON_CHAR_REPLACEMENTS[13] = "\\r";
/* 155 */     JSON_CHAR_REPLACEMENTS[9] = "\\t";
/* 156 */     JSON_CHAR_REPLACEMENTS[8] = "\\b";
/* 157 */     JSON_CHAR_REPLACEMENTS[12] = "\\f";
/*     */   }
/*     */ 
/*     */   
/* 161 */   private static final String[] INDENT_LEVELS = new String[17];
/*     */   static {
/* 163 */     StringBuilder buf = new StringBuilder();
/* 164 */     for (int i = 0; i < INDENT_LEVELS.length; i++) {
/* 165 */       INDENT_LEVELS[i] = buf.toString();
/* 166 */       buf.append(' ');
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
/*     */   static void escapeJSONString(String unsafeStr, StringBuilder buf) {
/* 188 */     if (unsafeStr == null) {
/*     */       return;
/*     */     }
/*     */     
/* 192 */     boolean needsEscaping = false; int i, n;
/* 193 */     for (i = 0, n = unsafeStr.length(); i < n; i++) {
/* 194 */       char c = unsafeStr.charAt(i);
/* 195 */       if (c > 'ÿ' || JSON_CHAR_REPLACEMENTS[c] != null) {
/* 196 */         needsEscaping = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 200 */     if (!needsEscaping) {
/* 201 */       buf.append(unsafeStr);
/*     */       
/*     */       return;
/*     */     } 
/* 205 */     for (i = 0, n = unsafeStr.length(); i < n; i++) {
/* 206 */       char c = unsafeStr.charAt(i);
/* 207 */       if (c > 'ÿ') {
/* 208 */         buf.append("\\u");
/* 209 */         int nibble3 = (c & 0xF000) >> 12;
/* 210 */         buf.append((nibble3 <= 9) ? (char)(48 + nibble3) : (char)(65 + nibble3 - 10));
/* 211 */         int nibble2 = (c & 0xF00) >> 8;
/* 212 */         buf.append((nibble2 <= 9) ? (char)(48 + nibble2) : (char)(65 + nibble2 - 10));
/* 213 */         int nibble1 = (c & 0xF0) >> 4;
/* 214 */         buf.append((nibble1 <= 9) ? (char)(48 + nibble1) : (char)(65 + nibble1 - 10));
/* 215 */         int nibble0 = c & 0xF;
/* 216 */         buf.append((nibble0 <= 9) ? (char)(48 + nibble0) : (char)(65 + nibble0 - 10));
/*     */       } else {
/* 218 */         String replacement = JSON_CHAR_REPLACEMENTS[c];
/* 219 */         if (replacement == null) {
/* 220 */           buf.append(c);
/*     */         } else {
/* 222 */           buf.append(replacement);
/*     */         } 
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
/*     */   public static String escapeJSONString(String unsafeStr) {
/* 236 */     StringBuilder buf = new StringBuilder(unsafeStr.length() * 2);
/* 237 */     escapeJSONString(unsafeStr, buf);
/* 238 */     return buf.toString();
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
/*     */   static void indent(int depth, int indentWidth, StringBuilder buf) {
/* 254 */     int maxIndent = INDENT_LEVELS.length - 1; int d;
/* 255 */     for (d = depth * indentWidth; d > 0; ) {
/* 256 */       int n = Math.min(d, maxIndent);
/* 257 */       buf.append(INDENT_LEVELS[n]);
/* 258 */       d -= n;
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
/*     */   
/*     */   static Object getFieldValue(Object containingObj, Field field) throws IllegalArgumentException, IllegalAccessException {
/* 281 */     Class<?> fieldType = field.getType();
/* 282 */     if (fieldType == int.class)
/* 283 */       return Integer.valueOf(field.getInt(containingObj)); 
/* 284 */     if (fieldType == long.class)
/* 285 */       return Long.valueOf(field.getLong(containingObj)); 
/* 286 */     if (fieldType == short.class)
/* 287 */       return Short.valueOf(field.getShort(containingObj)); 
/* 288 */     if (fieldType == double.class)
/* 289 */       return Double.valueOf(field.getDouble(containingObj)); 
/* 290 */     if (fieldType == float.class)
/* 291 */       return Float.valueOf(field.getFloat(containingObj)); 
/* 292 */     if (fieldType == boolean.class)
/* 293 */       return Boolean.valueOf(field.getBoolean(containingObj)); 
/* 294 */     if (fieldType == byte.class)
/* 295 */       return Byte.valueOf(field.getByte(containingObj)); 
/* 296 */     if (fieldType == char.class) {
/* 297 */       return Character.valueOf(field.getChar(containingObj));
/*     */     }
/* 299 */     return field.get(containingObj);
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
/*     */   static boolean isBasicValueType(Class<?> cls) {
/* 314 */     return (cls == String.class || cls == Integer.class || cls == int.class || cls == Long.class || cls == long.class || cls == Short.class || cls == short.class || cls == Float.class || cls == float.class || cls == Double.class || cls == double.class || cls == Byte.class || cls == byte.class || cls == Character.class || cls == char.class || cls == Boolean.class || cls == boolean.class || cls
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 323 */       .isEnum() || cls == Class.class);
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
/*     */   static boolean isBasicValueType(Type type) {
/* 335 */     if (type instanceof Class)
/* 336 */       return isBasicValueType((Class)type); 
/* 337 */     if (type instanceof ParameterizedType) {
/* 338 */       return isBasicValueType(((ParameterizedType)type).getRawType());
/*     */     }
/* 340 */     return false;
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
/*     */   static boolean isBasicValueType(Object obj) {
/* 352 */     return (obj == null || obj instanceof String || obj instanceof Integer || obj instanceof Boolean || obj instanceof Long || obj instanceof Float || obj instanceof Double || obj instanceof Short || obj instanceof Byte || obj instanceof Character || obj
/*     */       
/* 354 */       .getClass().isEnum() || obj instanceof Class);
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
/*     */   static boolean isCollectionOrArray(Object obj) {
/* 366 */     Class<?> cls = obj.getClass();
/* 367 */     return (Collection.class.isAssignableFrom(cls) || cls.isArray());
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
/*     */   static Class<?> getRawType(Type type) {
/* 383 */     if (type instanceof Class)
/* 384 */       return (Class)type; 
/* 385 */     if (type instanceof ParameterizedType) {
/* 386 */       return (Class)((ParameterizedType)type).getRawType();
/*     */     }
/* 388 */     throw new IllegalArgumentException("Illegal type: " + type);
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
/*     */   static boolean fieldIsSerializable(Field field, boolean onlySerializePublicFields, ReflectionUtils reflectionUtils) {
/* 407 */     int modifiers = field.getModifiers();
/* 408 */     if ((!onlySerializePublicFields || Modifier.isPublic(modifiers)) && !Modifier.isTransient(modifiers) && 
/* 409 */       !Modifier.isFinal(modifiers) && (modifiers & 0x1000) == 0) {
/* 410 */       return makeAccessible(field, reflectionUtils);
/*     */     }
/* 412 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\JSONUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */