/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.types.ParseException;
/*     */ import nonapi.io.github.classgraph.types.Parser;
/*     */ import nonapi.io.github.classgraph.types.TypeUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TypeVariableSignature
/*     */   extends ClassRefOrTypeVariableSignature
/*     */ {
/*     */   private final String name;
/*     */   private final String definingClassName;
/*     */   MethodTypeSignature containingMethodSignature;
/*     */   private TypeParameter typeParameterCached;
/*     */   
/*     */   private TypeVariableSignature(String typeVariableName, String definingClassName) {
/*  68 */     this.name = typeVariableName;
/*  69 */     this.definingClassName = definingClassName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  80 */     return this.name;
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
/*     */   public TypeParameter resolve() {
/*  94 */     if (this.typeParameterCached != null) {
/*  95 */       return this.typeParameterCached;
/*     */     }
/*     */     
/*  98 */     if (this.containingMethodSignature != null && this.containingMethodSignature.typeParameters != null && 
/*  99 */       !this.containingMethodSignature.typeParameters.isEmpty()) {
/* 100 */       for (TypeParameter typeParameter1 : this.containingMethodSignature.typeParameters) {
/* 101 */         if (typeParameter1.name.equals(this.name)) {
/* 102 */           this.typeParameterCached = typeParameter1;
/* 103 */           return typeParameter1;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 108 */     if (getClassName() != null) {
/* 109 */       ClassInfo containingClassInfo = getClassInfo();
/* 110 */       if (containingClassInfo == null) {
/* 111 */         throw new IllegalArgumentException("Could not find ClassInfo object for " + this.definingClassName);
/*     */       }
/* 113 */       ClassTypeSignature containingClassSignature = null;
/*     */       try {
/* 115 */         containingClassSignature = containingClassInfo.getTypeSignature();
/* 116 */       } catch (Exception exception) {}
/*     */ 
/*     */       
/* 119 */       if (containingClassSignature != null && containingClassSignature.typeParameters != null && 
/* 120 */         !containingClassSignature.typeParameters.isEmpty()) {
/* 121 */         for (TypeParameter typeParameter1 : containingClassSignature.typeParameters) {
/* 122 */           if (typeParameter1.name.equals(this.name)) {
/* 123 */             this.typeParameterCached = typeParameter1;
/* 124 */             return typeParameter1;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 132 */     TypeParameter typeParameter = new TypeParameter(this.name, null, Collections.emptyList());
/* 133 */     typeParameter.setScanResult(this.scanResult);
/* 134 */     this.typeParameterCached = typeParameter;
/* 135 */     return typeParameter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addTypeAnnotation(List<Classfile.TypePathNode> typePath, AnnotationInfo annotationInfo) {
/* 142 */     if (typePath.isEmpty()) {
/* 143 */       addTypeAnnotation(annotationInfo);
/*     */     } else {
/* 145 */       throw new IllegalArgumentException("Type variable should have empty typePath");
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
/*     */   static TypeVariableSignature parse(Parser parser, String definingClassName) throws ParseException {
/* 163 */     char peek = parser.peek();
/* 164 */     if (peek == 'T') {
/* 165 */       parser.next();
/*     */       
/* 167 */       if (!TypeUtils.getIdentifierToken(parser, false)) {
/* 168 */         throw new ParseException(parser, "Could not parse type variable signature");
/*     */       }
/* 170 */       parser.expect(';');
/* 171 */       TypeVariableSignature typeVariableSignature = new TypeVariableSignature(parser.currToken(), definingClassName);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 177 */       List<TypeVariableSignature> typeVariableSignatures = (List<TypeVariableSignature>)parser.getState();
/* 178 */       if (typeVariableSignatures == null) {
/* 179 */         parser.setState(typeVariableSignatures = new ArrayList<>());
/*     */       }
/* 181 */       typeVariableSignatures.add(typeVariableSignature);
/*     */       
/* 183 */       return typeVariableSignature;
/*     */     } 
/* 185 */     return null;
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
/*     */   protected String getClassName() {
/* 199 */     return this.definingClassName;
/*     */   }
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
/*     */   void setScanResult(ScanResult scanResult) {
/* 216 */     super.setScanResult(scanResult);
/* 217 */     if (this.typeParameterCached != null) {
/* 218 */       this.typeParameterCached.setScanResult(scanResult);
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
/* 229 */     return this.name.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 237 */     if (obj == this)
/* 238 */       return true; 
/* 239 */     if (!(obj instanceof TypeVariableSignature)) {
/* 240 */       return false;
/*     */     }
/* 242 */     TypeVariableSignature other = (TypeVariableSignature)obj;
/* 243 */     return (other.name.equals(this.name) && Objects.equals(other.typeAnnotationInfo, this.typeAnnotationInfo));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equalsIgnoringTypeParams(TypeSignature other) {
/* 251 */     if (other instanceof ClassRefTypeSignature) {
/* 252 */       TypeParameter typeParameter; if (((ClassRefTypeSignature)other).className.equals("java.lang.Object"))
/*     */       {
/*     */         
/* 255 */         return true;
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 260 */         typeParameter = resolve();
/* 261 */       } catch (IllegalArgumentException e) {
/*     */ 
/*     */         
/* 264 */         return true;
/*     */       } 
/* 266 */       if (typeParameter.classBound == null && (typeParameter.interfaceBounds == null || typeParameter.interfaceBounds
/* 267 */         .isEmpty()))
/*     */       {
/*     */         
/* 270 */         return true;
/*     */       }
/* 272 */       if (typeParameter.classBound != null) {
/* 273 */         if (typeParameter.classBound instanceof ClassRefTypeSignature) {
/* 274 */           if (typeParameter.classBound.equals(other))
/*     */           {
/* 276 */             return true; } 
/*     */         } else {
/* 278 */           if (typeParameter.classBound instanceof TypeVariableSignature)
/*     */           {
/* 280 */             return equalsIgnoringTypeParams(typeParameter.classBound);
/*     */           }
/* 282 */           return false;
/*     */         } 
/*     */       }
/* 285 */       if (typeParameter.interfaceBounds != null) {
/* 286 */         for (ReferenceTypeSignature interfaceBound : typeParameter.interfaceBounds) {
/* 287 */           if (interfaceBound instanceof ClassRefTypeSignature) {
/* 288 */             if (interfaceBound.equals(other))
/*     */             {
/* 290 */               return true; }  continue;
/*     */           } 
/* 292 */           if (interfaceBound instanceof TypeVariableSignature)
/*     */           {
/* 294 */             return equalsIgnoringTypeParams(interfaceBound);
/*     */           }
/* 296 */           return false;
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 304 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 310 */     return equals(other);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringWithTypeBound() {
/*     */     try {
/* 322 */       return resolve().toString();
/* 323 */     } catch (IllegalArgumentException e) {
/*     */       
/* 325 */       return this.name;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toStringInternal(boolean useSimpleNames, AnnotationInfoList annotationsToExclude, StringBuilder buf) {
/* 332 */     if (this.typeAnnotationInfo != null) {
/* 333 */       for (AnnotationInfo annotationInfo : this.typeAnnotationInfo) {
/* 334 */         if (annotationsToExclude == null || !annotationsToExclude.contains(annotationInfo)) {
/* 335 */           annotationInfo.toString(useSimpleNames, buf);
/* 336 */           buf.append(' ');
/*     */         } 
/*     */       } 
/*     */     }
/* 340 */     buf.append(this.name);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\TypeVariableSignature.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */