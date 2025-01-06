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
/*     */ 
/*     */ public final class TypeParameter
/*     */   extends HierarchicalTypeSignature
/*     */ {
/*     */   final String name;
/*     */   final ReferenceTypeSignature classBound;
/*     */   final List<ReferenceTypeSignature> interfaceBounds;
/*     */   
/*     */   protected TypeParameter(String identifier, ReferenceTypeSignature classBound, List<ReferenceTypeSignature> interfaceBounds) {
/*  68 */     this.name = identifier;
/*  69 */     this.classBound = classBound;
/*  70 */     this.interfaceBounds = interfaceBounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  79 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReferenceTypeSignature getClassBound() {
/*  88 */     return this.classBound;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ReferenceTypeSignature> getInterfaceBounds() {
/*  97 */     return this.interfaceBounds;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addTypeAnnotation(List<Classfile.TypePathNode> typePath, AnnotationInfo annotationInfo) {
/* 102 */     if (typePath.isEmpty()) {
/* 103 */       addTypeAnnotation(annotationInfo);
/*     */     } else {
/* 105 */       throw new IllegalArgumentException("Type parameter should have empty typePath");
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
/*     */   static List<TypeParameter> parseList(Parser parser, String definingClassName) throws ParseException {
/* 124 */     if (parser.peek() != '<') {
/* 125 */       return Collections.emptyList();
/*     */     }
/* 127 */     parser.expect('<');
/* 128 */     List<TypeParameter> typeParams = new ArrayList<>(1);
/* 129 */     while (parser.peek() != '>') {
/* 130 */       List<ReferenceTypeSignature> interfaceBounds; if (!parser.hasMore()) {
/* 131 */         throw new ParseException(parser, "Missing '>'");
/*     */       }
/*     */       
/* 134 */       if (!TypeUtils.getIdentifierToken(parser, false)) {
/* 135 */         throw new ParseException(parser, "Could not parse identifier token");
/*     */       }
/* 137 */       String identifier = parser.currToken();
/*     */       
/* 139 */       ReferenceTypeSignature classBound = ReferenceTypeSignature.parseClassBound(parser, definingClassName);
/*     */ 
/*     */       
/* 142 */       if (parser.peek() == ':') {
/* 143 */         interfaceBounds = new ArrayList<>();
/* 144 */         while (parser.peek() == ':') {
/* 145 */           parser.expect(':');
/*     */           
/* 147 */           ReferenceTypeSignature interfaceTypeSignature = ReferenceTypeSignature.parseReferenceTypeSignature(parser, definingClassName);
/* 148 */           if (interfaceTypeSignature == null) {
/* 149 */             throw new ParseException(parser, "Missing interface type signature");
/*     */           }
/* 151 */           interfaceBounds.add(interfaceTypeSignature);
/*     */         } 
/*     */       } else {
/* 154 */         interfaceBounds = Collections.emptyList();
/*     */       } 
/* 156 */       typeParams.add(new TypeParameter(identifier, classBound, interfaceBounds));
/*     */     } 
/* 158 */     parser.expect('>');
/* 159 */     return typeParams;
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
/* 170 */     throw new IllegalArgumentException("getClassName() cannot be called here");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassInfo getClassInfo() {
/* 178 */     throw new IllegalArgumentException("getClassInfo() cannot be called here");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/* 186 */     super.setScanResult(scanResult);
/* 187 */     if (this.classBound != null) {
/* 188 */       this.classBound.setScanResult(scanResult);
/*     */     }
/* 190 */     if (this.interfaceBounds != null) {
/* 191 */       for (ReferenceTypeSignature referenceTypeSignature : this.interfaceBounds) {
/* 192 */         referenceTypeSignature.setScanResult(scanResult);
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
/* 204 */     if (this.classBound != null) {
/* 205 */       this.classBound.findReferencedClassNames(refdClassNames);
/*     */     }
/* 207 */     for (ReferenceTypeSignature typeSignature : this.interfaceBounds) {
/* 208 */       typeSignature.findReferencedClassNames(refdClassNames);
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
/* 219 */     return this.name.hashCode() + ((this.classBound == null) ? 0 : (this.classBound.hashCode() * 7)) + this.interfaceBounds
/* 220 */       .hashCode() * 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 228 */     if (obj == this)
/* 229 */       return true; 
/* 230 */     if (!(obj instanceof TypeParameter)) {
/* 231 */       return false;
/*     */     }
/* 233 */     TypeParameter other = (TypeParameter)obj;
/* 234 */     return (other.name.equals(this.name) && Objects.equals(other.typeAnnotationInfo, this.typeAnnotationInfo) && ((other.classBound == null && this.classBound == null) || (other.classBound != null && other.classBound
/*     */       
/* 236 */       .equals(this.classBound))) && other.interfaceBounds
/* 237 */       .equals(this.interfaceBounds));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toStringInternal(boolean useSimpleNames, AnnotationInfoList annotationsToExclude, StringBuilder buf) {
/*     */     String classBoundStr;
/* 245 */     if (this.typeAnnotationInfo != null) {
/* 246 */       for (AnnotationInfo annotationInfo : this.typeAnnotationInfo) {
/* 247 */         if (annotationsToExclude == null || !annotationsToExclude.contains(annotationInfo)) {
/* 248 */           annotationInfo.toString(useSimpleNames, buf);
/* 249 */           buf.append(' ');
/*     */         } 
/*     */       } 
/*     */     }
/* 253 */     buf.append(useSimpleNames ? ClassInfo.getSimpleName(this.name) : this.name);
/*     */     
/* 255 */     if (this.classBound == null) {
/* 256 */       classBoundStr = null;
/*     */     } else {
/* 258 */       classBoundStr = this.classBound.toString(useSimpleNames);
/* 259 */       if (classBoundStr.equals("java.lang.Object") || (classBoundStr.equals("Object") && ((ClassRefTypeSignature)this.classBound).className
/* 260 */         .equals("java.lang.Object")))
/*     */       {
/* 262 */         classBoundStr = null;
/*     */       }
/*     */     } 
/* 265 */     if (classBoundStr != null || !this.interfaceBounds.isEmpty()) {
/* 266 */       buf.append(" extends");
/*     */     }
/* 268 */     if (classBoundStr != null) {
/* 269 */       buf.append(' ');
/* 270 */       buf.append(classBoundStr);
/*     */     } 
/* 272 */     for (int i = 0; i < this.interfaceBounds.size(); i++) {
/* 273 */       if (i > 0 || classBoundStr != null) {
/* 274 */         buf.append(" &");
/*     */       }
/* 276 */       buf.append(' ');
/* 277 */       ((ReferenceTypeSignature)this.interfaceBounds.get(i)).toString(useSimpleNames, buf);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\TypeParameter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */