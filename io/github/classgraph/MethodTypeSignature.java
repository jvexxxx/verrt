/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.types.ParseException;
/*     */ import nonapi.io.github.classgraph.types.Parser;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MethodTypeSignature
/*     */   extends HierarchicalTypeSignature
/*     */ {
/*     */   final List<TypeParameter> typeParameters;
/*     */   private final List<TypeSignature> parameterTypeSignatures;
/*     */   private final TypeSignature resultType;
/*     */   private final List<ClassRefOrTypeVariableSignature> throwsSignatures;
/*     */   private AnnotationInfoList receiverTypeAnnotationInfo;
/*     */   
/*     */   private MethodTypeSignature(List<TypeParameter> typeParameters, List<TypeSignature> paramTypes, TypeSignature resultType, List<ClassRefOrTypeVariableSignature> throwsSignatures) {
/*  77 */     this.typeParameters = typeParameters;
/*  78 */     this.parameterTypeSignatures = paramTypes;
/*  79 */     this.resultType = resultType;
/*  80 */     this.throwsSignatures = throwsSignatures;
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
/*     */   public List<TypeParameter> getTypeParameters() {
/*  92 */     return this.typeParameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   List<TypeSignature> getParameterTypeSignatures() {
/* 103 */     return this.parameterTypeSignatures;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeSignature getResultType() {
/* 112 */     return this.resultType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ClassRefOrTypeVariableSignature> getThrowsSignatures() {
/* 121 */     return this.throwsSignatures;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addTypeAnnotation(List<Classfile.TypePathNode> typePath, AnnotationInfo annotationInfo) {
/* 127 */     throw new IllegalArgumentException("Cannot call this method on " + MethodTypeSignature.class
/* 128 */         .getSimpleName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addRecieverTypeAnnotation(AnnotationInfo annotationInfo) {
/* 138 */     if (this.receiverTypeAnnotationInfo == null) {
/* 139 */       this.receiverTypeAnnotationInfo = new AnnotationInfoList(1);
/*     */     }
/* 141 */     this.receiverTypeAnnotationInfo.add(annotationInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationInfoList getReceiverTypeAnnotationInfo() {
/* 150 */     return this.receiverTypeAnnotationInfo;
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
/* 161 */     throw new IllegalArgumentException("getClassName() cannot be called here");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassInfo getClassInfo() {
/* 169 */     throw new IllegalArgumentException("getClassInfo() cannot be called here");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/* 177 */     super.setScanResult(scanResult);
/* 178 */     if (this.typeParameters != null) {
/* 179 */       for (TypeParameter typeParameter : this.typeParameters) {
/* 180 */         typeParameter.setScanResult(scanResult);
/*     */       }
/*     */     }
/* 183 */     if (this.parameterTypeSignatures != null) {
/* 184 */       for (TypeSignature typeParameter : this.parameterTypeSignatures) {
/* 185 */         typeParameter.setScanResult(scanResult);
/*     */       }
/*     */     }
/* 188 */     if (this.resultType != null) {
/* 189 */       this.resultType.setScanResult(scanResult);
/*     */     }
/* 191 */     if (this.throwsSignatures != null) {
/* 192 */       for (ClassRefOrTypeVariableSignature throwsSignature : this.throwsSignatures) {
/* 193 */         throwsSignature.setScanResult(scanResult);
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
/*     */   protected void findReferencedClassNames(Set<String> refdClassNames) {
/* 205 */     for (TypeParameter typeParameter : this.typeParameters) {
/* 206 */       if (typeParameter != null) {
/* 207 */         typeParameter.findReferencedClassNames(refdClassNames);
/*     */       }
/*     */     } 
/* 210 */     for (TypeSignature typeSignature : this.parameterTypeSignatures) {
/* 211 */       if (typeSignature != null) {
/* 212 */         typeSignature.findReferencedClassNames(refdClassNames);
/*     */       }
/*     */     } 
/* 215 */     this.resultType.findReferencedClassNames(refdClassNames);
/* 216 */     for (ClassRefOrTypeVariableSignature typeSignature : this.throwsSignatures) {
/* 217 */       if (typeSignature != null) {
/* 218 */         typeSignature.findReferencedClassNames(refdClassNames);
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
/*     */   protected void findReferencedClassInfo(Map<String, ClassInfo> classNameToClassInfo, Set<ClassInfo> refdClassInfo, LogNode log) {
/* 234 */     Set<String> refdClassNames = new HashSet<>();
/* 235 */     findReferencedClassNames(refdClassNames);
/* 236 */     for (String refdClassName : refdClassNames) {
/* 237 */       ClassInfo classInfo = ClassInfo.getOrCreateClassInfo(refdClassName, classNameToClassInfo);
/* 238 */       classInfo.scanResult = this.scanResult;
/* 239 */       refdClassInfo.add(classInfo);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 250 */     return this.typeParameters.hashCode() + this.parameterTypeSignatures.hashCode() * 7 + this.resultType.hashCode() * 15 + this.throwsSignatures
/* 251 */       .hashCode() * 31;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 259 */     if (obj == this)
/* 260 */       return true; 
/* 261 */     if (!(obj instanceof MethodTypeSignature)) {
/* 262 */       return false;
/*     */     }
/* 264 */     MethodTypeSignature o = (MethodTypeSignature)obj;
/* 265 */     return (o.typeParameters.equals(this.typeParameters) && o.parameterTypeSignatures
/* 266 */       .equals(this.parameterTypeSignatures) && o.resultType
/* 267 */       .equals(this.resultType) && o.throwsSignatures.equals(this.throwsSignatures));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toStringInternal(boolean useSimpleNames, AnnotationInfoList annotationsToExclude, StringBuilder buf) {
/* 275 */     if (!this.typeParameters.isEmpty()) {
/* 276 */       buf.append('<');
/* 277 */       for (int j = 0; j < this.typeParameters.size(); j++) {
/* 278 */         if (j > 0) {
/* 279 */           buf.append(", ");
/*     */         }
/* 281 */         ((TypeParameter)this.typeParameters.get(j)).toString(useSimpleNames, buf);
/*     */       } 
/* 283 */       buf.append('>');
/*     */     } 
/*     */     
/* 286 */     if (buf.length() > 0) {
/* 287 */       buf.append(' ');
/*     */     }
/* 289 */     buf.append(this.resultType.toString());
/*     */     
/* 291 */     buf.append(" ("); int i;
/* 292 */     for (i = 0; i < this.parameterTypeSignatures.size(); i++) {
/* 293 */       if (i > 0) {
/* 294 */         buf.append(", ");
/*     */       }
/* 296 */       ((TypeSignature)this.parameterTypeSignatures.get(i)).toString(useSimpleNames, buf);
/*     */     } 
/* 298 */     buf.append(')');
/*     */     
/* 300 */     if (!this.throwsSignatures.isEmpty()) {
/* 301 */       buf.append(" throws ");
/* 302 */       for (i = 0; i < this.throwsSignatures.size(); i++) {
/* 303 */         if (i > 0) {
/* 304 */           buf.append(", ");
/*     */         }
/* 306 */         ((ClassRefOrTypeVariableSignature)this.throwsSignatures.get(i)).toString(useSimpleNames, buf);
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
/*     */   static MethodTypeSignature parse(String typeDescriptor, String definingClassName) throws ParseException {
/*     */     List<ClassRefOrTypeVariableSignature> throwsSignatures;
/* 326 */     if (typeDescriptor.equals("<init>"))
/*     */     {
/*     */       
/* 329 */       return new MethodTypeSignature(Collections.emptyList(), 
/* 330 */           Collections.emptyList(), new BaseTypeSignature('V'), 
/* 331 */           Collections.emptyList());
/*     */     }
/* 333 */     Parser parser = new Parser(typeDescriptor);
/* 334 */     List<TypeParameter> typeParameters = TypeParameter.parseList(parser, definingClassName);
/* 335 */     parser.expect('(');
/* 336 */     List<TypeSignature> paramTypes = new ArrayList<>();
/* 337 */     while (parser.peek() != ')') {
/* 338 */       if (!parser.hasMore()) {
/* 339 */         throw new ParseException(parser, "Ran out of input while parsing method signature");
/*     */       }
/* 341 */       TypeSignature paramType = TypeSignature.parse(parser, definingClassName);
/* 342 */       if (paramType == null) {
/* 343 */         throw new ParseException(parser, "Missing method parameter type signature");
/*     */       }
/* 345 */       paramTypes.add(paramType);
/*     */     } 
/* 347 */     parser.expect(')');
/* 348 */     TypeSignature resultType = TypeSignature.parse(parser, definingClassName);
/* 349 */     if (resultType == null) {
/* 350 */       throw new ParseException(parser, "Missing method result type signature");
/*     */     }
/*     */     
/* 353 */     if (parser.peek() == '^') {
/* 354 */       throwsSignatures = new ArrayList<>();
/* 355 */       while (parser.peek() == '^') {
/* 356 */         parser.expect('^');
/* 357 */         ClassRefTypeSignature classTypeSignature = ClassRefTypeSignature.parse(parser, definingClassName);
/*     */         
/* 359 */         if (classTypeSignature != null) {
/* 360 */           throwsSignatures.add(classTypeSignature); continue;
/*     */         } 
/* 362 */         TypeVariableSignature typeVariableSignature = TypeVariableSignature.parse(parser, definingClassName);
/*     */         
/* 364 */         if (typeVariableSignature != null) {
/* 365 */           throwsSignatures.add(typeVariableSignature); continue;
/*     */         } 
/* 367 */         throw new ParseException(parser, "Missing type variable signature");
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 372 */       throwsSignatures = Collections.emptyList();
/*     */     } 
/* 374 */     if (parser.hasMore()) {
/* 375 */       throw new ParseException(parser, "Extra characters at end of type descriptor");
/*     */     }
/* 377 */     MethodTypeSignature methodSignature = new MethodTypeSignature(typeParameters, paramTypes, resultType, throwsSignatures);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 382 */     List<TypeVariableSignature> typeVariableSignatures = (List<TypeVariableSignature>)parser.getState();
/* 383 */     if (typeVariableSignatures != null) {
/* 384 */       for (TypeVariableSignature typeVariableSignature : typeVariableSignatures) {
/* 385 */         typeVariableSignature.containingMethodSignature = methodSignature;
/*     */       }
/*     */     }
/* 388 */     return methodSignature;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\MethodTypeSignature.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */