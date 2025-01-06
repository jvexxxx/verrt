/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
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
/*     */ public class BaseTypeSignature
/*     */   extends TypeSignature
/*     */ {
/*     */   private final char typeSignatureChar;
/*     */   
/*     */   BaseTypeSignature(char typeSignatureChar) {
/*  50 */     switch (typeSignatureChar) {
/*     */       case 'B':
/*     */       case 'C':
/*     */       case 'D':
/*     */       case 'F':
/*     */       case 'I':
/*     */       case 'J':
/*     */       case 'S':
/*     */       case 'V':
/*     */       case 'Z':
/*  60 */         this.typeSignatureChar = typeSignatureChar;
/*     */         return;
/*     */     } 
/*  63 */     throw new IllegalArgumentException("Illegal " + BaseTypeSignature.class
/*  64 */         .getSimpleName() + " type: '" + typeSignatureChar + "'");
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
/*     */   static String getTypeStr(char typeChar) {
/*  78 */     switch (typeChar) {
/*     */       case 'B':
/*  80 */         return "byte";
/*     */       case 'C':
/*  82 */         return "char";
/*     */       case 'D':
/*  84 */         return "double";
/*     */       case 'F':
/*  86 */         return "float";
/*     */       case 'I':
/*  88 */         return "int";
/*     */       case 'J':
/*  90 */         return "long";
/*     */       case 'S':
/*  92 */         return "short";
/*     */       case 'Z':
/*  94 */         return "boolean";
/*     */       case 'V':
/*  96 */         return "void";
/*     */     } 
/*  98 */     return null;
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
/*     */   static char getTypeChar(String typeStr) {
/* 110 */     switch (typeStr) {
/*     */       case "byte":
/* 112 */         return 'B';
/*     */       case "char":
/* 114 */         return 'C';
/*     */       case "double":
/* 116 */         return 'D';
/*     */       case "float":
/* 118 */         return 'F';
/*     */       case "int":
/* 120 */         return 'I';
/*     */       case "long":
/* 122 */         return 'J';
/*     */       case "short":
/* 124 */         return 'S';
/*     */       case "boolean":
/* 126 */         return 'Z';
/*     */       case "void":
/* 128 */         return 'V';
/*     */     } 
/* 130 */     return Character.MIN_VALUE;
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
/*     */   static Class<?> getType(char typeChar) {
/* 142 */     switch (typeChar) {
/*     */       case 'B':
/* 144 */         return byte.class;
/*     */       case 'C':
/* 146 */         return char.class;
/*     */       case 'D':
/* 148 */         return double.class;
/*     */       case 'F':
/* 150 */         return float.class;
/*     */       case 'I':
/* 152 */         return int.class;
/*     */       case 'J':
/* 154 */         return long.class;
/*     */       case 'S':
/* 156 */         return short.class;
/*     */       case 'Z':
/* 158 */         return boolean.class;
/*     */       case 'V':
/* 160 */         return void.class;
/*     */     } 
/* 162 */     return null;
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
/*     */   public char getTypeSignatureChar() {
/* 174 */     return this.typeSignatureChar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeStr() {
/* 183 */     return getTypeStr(this.typeSignatureChar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getType() {
/* 192 */     return getType(this.typeSignatureChar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addTypeAnnotation(List<Classfile.TypePathNode> typePath, AnnotationInfo annotationInfo) {
/* 199 */     addTypeAnnotation(annotationInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Class<?> loadClass() {
/* 209 */     return getType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   <T> Class<T> loadClass(Class<T> superclassOrInterfaceType) {
/* 217 */     Class<?> type = getType();
/* 218 */     if (!superclassOrInterfaceType.isAssignableFrom(type)) {
/* 219 */       throw new IllegalArgumentException("Primitive class " + getTypeStr() + " cannot be cast to " + superclassOrInterfaceType
/* 220 */           .getName());
/*     */     }
/*     */     
/* 223 */     Class<T> classT = (Class)type;
/* 224 */     return classT;
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
/*     */   static BaseTypeSignature parse(Parser parser) {
/* 237 */     switch (parser.peek()) {
/*     */       case 'B':
/* 239 */         parser.next();
/* 240 */         return new BaseTypeSignature('B');
/*     */       case 'C':
/* 242 */         parser.next();
/* 243 */         return new BaseTypeSignature('C');
/*     */       case 'D':
/* 245 */         parser.next();
/* 246 */         return new BaseTypeSignature('D');
/*     */       case 'F':
/* 248 */         parser.next();
/* 249 */         return new BaseTypeSignature('F');
/*     */       case 'I':
/* 251 */         parser.next();
/* 252 */         return new BaseTypeSignature('I');
/*     */       case 'J':
/* 254 */         parser.next();
/* 255 */         return new BaseTypeSignature('J');
/*     */       case 'S':
/* 257 */         parser.next();
/* 258 */         return new BaseTypeSignature('S');
/*     */       case 'Z':
/* 260 */         parser.next();
/* 261 */         return new BaseTypeSignature('Z');
/*     */       case 'V':
/* 263 */         parser.next();
/* 264 */         return new BaseTypeSignature('V');
/*     */     } 
/* 266 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getClassName() {
/* 277 */     return getTypeStr();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassInfo getClassInfo() {
/* 285 */     return null;
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
/*     */   protected void findReferencedClassNames(Set<String> refdClassNames) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 320 */     return this.typeSignatureChar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 328 */     if (obj == this)
/* 329 */       return true; 
/* 330 */     if (!(obj instanceof BaseTypeSignature)) {
/* 331 */       return false;
/*     */     }
/* 333 */     BaseTypeSignature other = (BaseTypeSignature)obj;
/* 334 */     return (Objects.equals(this.typeAnnotationInfo, other.typeAnnotationInfo) && other.typeSignatureChar == this.typeSignatureChar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equalsIgnoringTypeParams(TypeSignature other) {
/* 343 */     if (!(other instanceof BaseTypeSignature)) {
/* 344 */       return false;
/*     */     }
/* 346 */     return (this.typeSignatureChar == ((BaseTypeSignature)other).typeSignatureChar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toStringInternal(boolean useSimpleNames, AnnotationInfoList annotationsToExclude, StringBuilder buf) {
/* 354 */     if (this.typeAnnotationInfo != null) {
/* 355 */       for (AnnotationInfo annotationInfo : this.typeAnnotationInfo) {
/* 356 */         if (annotationsToExclude == null || !annotationsToExclude.contains(annotationInfo)) {
/* 357 */           annotationInfo.toString(useSimpleNames, buf);
/* 358 */           buf.append(' ');
/*     */         } 
/*     */       } 
/*     */     }
/* 362 */     buf.append(getTypeStr());
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\BaseTypeSignature.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */