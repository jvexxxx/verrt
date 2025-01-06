/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrayTypeSignature
/*     */   extends ReferenceTypeSignature
/*     */ {
/*     */   private final String typeSignatureStr;
/*     */   private String className;
/*     */   private ArrayClassInfo arrayClassInfo;
/*     */   private Class<?> elementClassRef;
/*     */   private final TypeSignature nestedType;
/*     */   
/*     */   ArrayTypeSignature(TypeSignature elementTypeSignature, int numDims, String typeSignatureStr) {
/*  71 */     boolean typeSigHasTwoOrMoreDims = typeSignatureStr.startsWith("[[");
/*  72 */     if (numDims < 1)
/*  73 */       throw new IllegalArgumentException("numDims < 1"); 
/*  74 */     if (((numDims >= 2)) != typeSigHasTwoOrMoreDims) {
/*  75 */       throw new IllegalArgumentException("numDims does not match type signature");
/*     */     }
/*  77 */     this.typeSignatureStr = typeSignatureStr;
/*  78 */     this
/*     */ 
/*     */ 
/*     */       
/*  82 */       .nestedType = typeSigHasTwoOrMoreDims ? new ArrayTypeSignature(elementTypeSignature, numDims - 1, typeSignatureStr.substring(1)) : elementTypeSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTypeSignatureStr() {
/*  91 */     return this.typeSignatureStr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeSignature getElementTypeSignature() {
/* 100 */     ArrayTypeSignature curr = this;
/* 101 */     while (curr.nestedType instanceof ArrayTypeSignature) {
/* 102 */       curr = (ArrayTypeSignature)curr.nestedType;
/*     */     }
/* 104 */     return curr.getNestedType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumDimensions() {
/* 113 */     int numDims = 1;
/* 114 */     ArrayTypeSignature curr = this;
/* 115 */     while (curr.nestedType instanceof ArrayTypeSignature) {
/* 116 */       curr = (ArrayTypeSignature)curr.nestedType;
/* 117 */       numDims++;
/*     */     } 
/* 119 */     return numDims;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeSignature getNestedType() {
/* 129 */     return this.nestedType;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addTypeAnnotation(List<Classfile.TypePathNode> typePath, AnnotationInfo annotationInfo) {
/* 134 */     if (typePath.isEmpty()) {
/* 135 */       addTypeAnnotation(annotationInfo);
/*     */     } else {
/* 137 */       Classfile.TypePathNode head = typePath.get(0);
/* 138 */       if (head.typePathKind != 0 || head.typeArgumentIdx != 0) {
/* 139 */         throw new IllegalArgumentException("typePath element contains bad values: " + head);
/*     */       }
/* 141 */       this.nestedType.addTypeAnnotation(typePath.subList(1, typePath.size()), annotationInfo);
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
/*     */   public AnnotationInfoList getTypeAnnotationInfo() {
/* 155 */     return this.typeAnnotationInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getClassName() {
/* 165 */     if (this.className == null) {
/* 166 */       this.className = toString();
/*     */     }
/* 168 */     return this.className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassInfo getClassInfo() {
/* 176 */     return getArrayClassInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayClassInfo getArrayClassInfo() {
/* 185 */     if (this.arrayClassInfo == null) {
/* 186 */       if (this.scanResult != null) {
/* 187 */         String clsName = getClassName();
/*     */         
/* 189 */         this.arrayClassInfo = (ArrayClassInfo)this.scanResult.classNameToClassInfo.get(clsName);
/* 190 */         if (this.arrayClassInfo == null) {
/* 191 */           this.scanResult.classNameToClassInfo.put(clsName, this.arrayClassInfo = new ArrayClassInfo(this));
/* 192 */           this.arrayClassInfo.setScanResult(this.scanResult);
/*     */         } 
/*     */       } else {
/*     */         
/* 196 */         this.arrayClassInfo = new ArrayClassInfo(this);
/*     */       } 
/*     */     }
/* 199 */     return this.arrayClassInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/* 207 */     super.setScanResult(scanResult);
/* 208 */     this.nestedType.setScanResult(scanResult);
/* 209 */     if (this.arrayClassInfo != null) {
/* 210 */       this.arrayClassInfo.setScanResult(scanResult);
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
/*     */   protected void findReferencedClassNames(Set<String> refdClassNames) {
/* 222 */     this.nestedType.findReferencedClassNames(refdClassNames);
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
/*     */   public Class<?> loadElementClass(boolean ignoreExceptions) {
/* 237 */     if (this.elementClassRef == null) {
/*     */       
/* 239 */       TypeSignature elementTypeSignature = getElementTypeSignature();
/* 240 */       if (elementTypeSignature instanceof BaseTypeSignature) {
/* 241 */         this.elementClassRef = ((BaseTypeSignature)elementTypeSignature).getType();
/*     */       }
/* 243 */       else if (this.scanResult != null) {
/* 244 */         this.elementClassRef = elementTypeSignature.loadClass(ignoreExceptions);
/*     */       } else {
/*     */         
/* 247 */         String elementTypeName = elementTypeSignature.getClassName();
/*     */         try {
/* 249 */           this.elementClassRef = Class.forName(elementTypeName);
/* 250 */         } catch (Throwable t) {
/* 251 */           if (!ignoreExceptions) {
/* 252 */             throw new IllegalArgumentException("Could not load array element class " + elementTypeName, t);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 259 */     return this.elementClassRef;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> loadElementClass() {
/* 270 */     return loadElementClass(false);
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
/*     */   public Class<?> loadClass(boolean ignoreExceptions) {
/* 286 */     if (this.classRef == null) {
/*     */       
/* 288 */       Class<?> eltClassRef = null;
/* 289 */       if (ignoreExceptions) {
/*     */         try {
/* 291 */           eltClassRef = loadElementClass();
/* 292 */         } catch (IllegalArgumentException e) {
/* 293 */           return null;
/*     */         } 
/*     */       } else {
/* 296 */         eltClassRef = loadElementClass();
/*     */       } 
/* 298 */       if (eltClassRef == null) {
/* 299 */         throw new IllegalArgumentException("Could not load array element class " + 
/* 300 */             getElementTypeSignature());
/*     */       }
/*     */       
/* 303 */       Object eltArrayInstance = Array.newInstance(eltClassRef, new int[getNumDimensions()]);
/*     */       
/* 305 */       this.classRef = eltArrayInstance.getClass();
/*     */     } 
/* 307 */     return this.classRef;
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
/*     */   public Class<?> loadClass() {
/* 320 */     return loadClass(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 330 */     return 1 + this.nestedType.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 338 */     if (obj == this)
/* 339 */       return true; 
/* 340 */     if (!(obj instanceof ArrayTypeSignature)) {
/* 341 */       return false;
/*     */     }
/* 343 */     ArrayTypeSignature other = (ArrayTypeSignature)obj;
/* 344 */     return (Objects.equals(this.typeAnnotationInfo, other.typeAnnotationInfo) && this.nestedType
/* 345 */       .equals(other.nestedType));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equalsIgnoringTypeParams(TypeSignature other) {
/* 353 */     if (this == other) {
/* 354 */       return true;
/*     */     }
/* 356 */     if (!(other instanceof ArrayTypeSignature)) {
/* 357 */       return false;
/*     */     }
/* 359 */     ArrayTypeSignature o = (ArrayTypeSignature)other;
/* 360 */     return this.nestedType.equalsIgnoringTypeParams(o.nestedType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toStringInternal(boolean useSimpleNames, AnnotationInfoList annotationsToExclude, StringBuilder buf) {
/* 369 */     getElementTypeSignature().toStringInternal(useSimpleNames, annotationsToExclude, buf);
/*     */ 
/*     */     
/* 372 */     ArrayTypeSignature curr = this; while (true) {
/* 373 */       if (curr.typeAnnotationInfo != null && !curr.typeAnnotationInfo.isEmpty()) {
/* 374 */         for (AnnotationInfo annotationInfo : curr.typeAnnotationInfo) {
/* 375 */           if (buf.length() == 0 || buf.charAt(buf.length() - 1) != ' ') {
/* 376 */             buf.append(' ');
/*     */           }
/* 378 */           annotationInfo.toString(useSimpleNames, buf);
/*     */         } 
/* 380 */         buf.append(' ');
/*     */       } 
/*     */       
/* 383 */       buf.append("[]");
/*     */       
/* 385 */       if (curr.nestedType instanceof ArrayTypeSignature) {
/* 386 */         curr = (ArrayTypeSignature)curr.nestedType;
/*     */         continue;
/*     */       } 
/*     */       break;
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
/*     */   static ArrayTypeSignature parse(Parser parser, String definingClassName) throws ParseException {
/* 407 */     int numArrayDims = 0;
/* 408 */     int begin = parser.getPosition();
/* 409 */     while (parser.peek() == '[') {
/* 410 */       numArrayDims++;
/* 411 */       parser.next();
/*     */     } 
/* 413 */     if (numArrayDims > 0) {
/* 414 */       TypeSignature elementTypeSignature = TypeSignature.parse(parser, definingClassName);
/* 415 */       if (elementTypeSignature == null) {
/* 416 */         throw new ParseException(parser, "elementTypeSignature == null");
/*     */       }
/* 418 */       String typeSignatureStr = parser.getSubsequence(begin, parser.getPosition()).toString();
/* 419 */       return new ArrayTypeSignature(elementTypeSignature, numArrayDims, typeSignatureStr);
/*     */     } 
/* 421 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ArrayTypeSignature.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */