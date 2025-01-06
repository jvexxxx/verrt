/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
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
/*     */ public final class TypeArgument
/*     */   extends HierarchicalTypeSignature
/*     */ {
/*     */   private final Wildcard wildcard;
/*     */   private final ReferenceTypeSignature typeSignature;
/*     */   
/*     */   public enum Wildcard
/*     */   {
/*  46 */     NONE,
/*     */ 
/*     */     
/*  49 */     ANY,
/*     */ 
/*     */     
/*  52 */     EXTENDS,
/*     */ 
/*     */     
/*  55 */     SUPER;
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
/*     */   private TypeArgument(Wildcard wildcard, ReferenceTypeSignature typeSignature) {
/*  76 */     this.wildcard = wildcard;
/*  77 */     this.typeSignature = typeSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Wildcard getWildcard() {
/*  88 */     return this.wildcard;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReferenceTypeSignature getTypeSignature() {
/*  97 */     return this.typeSignature;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addTypeAnnotation(List<Classfile.TypePathNode> typePath, AnnotationInfo annotationInfo) {
/* 102 */     if (typePath.size() == 0 && this.wildcard != Wildcard.NONE) {
/*     */       
/* 104 */       addTypeAnnotation(annotationInfo);
/* 105 */     } else if (typePath.size() > 0 && ((Classfile.TypePathNode)typePath.get(0)).typePathKind == 2) {
/*     */ 
/*     */       
/* 108 */       if (this.typeSignature != null) {
/* 109 */         this.typeSignature.addTypeAnnotation(typePath.subList(1, typePath.size()), annotationInfo);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 114 */     else if (this.typeSignature != null) {
/* 115 */       this.typeSignature.addTypeAnnotation(typePath, annotationInfo);
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
/*     */   private static TypeArgument parse(Parser parser, String definingClassName) throws ParseException {
/* 134 */     char peek = parser.peek();
/* 135 */     if (peek == '*') {
/* 136 */       parser.expect('*');
/* 137 */       return new TypeArgument(Wildcard.ANY, null);
/* 138 */     }  if (peek == '+') {
/* 139 */       parser.expect('+');
/* 140 */       ReferenceTypeSignature referenceTypeSignature = ReferenceTypeSignature.parseReferenceTypeSignature(parser, definingClassName);
/*     */       
/* 142 */       if (referenceTypeSignature == null) {
/* 143 */         throw new ParseException(parser, "Missing '+' type bound");
/*     */       }
/* 145 */       return new TypeArgument(Wildcard.EXTENDS, referenceTypeSignature);
/* 146 */     }  if (peek == '-') {
/* 147 */       parser.expect('-');
/* 148 */       ReferenceTypeSignature referenceTypeSignature = ReferenceTypeSignature.parseReferenceTypeSignature(parser, definingClassName);
/*     */       
/* 150 */       if (referenceTypeSignature == null) {
/* 151 */         throw new ParseException(parser, "Missing '-' type bound");
/*     */       }
/* 153 */       return new TypeArgument(Wildcard.SUPER, referenceTypeSignature);
/*     */     } 
/* 155 */     ReferenceTypeSignature typeSignature = ReferenceTypeSignature.parseReferenceTypeSignature(parser, definingClassName);
/*     */     
/* 157 */     if (typeSignature == null) {
/* 158 */       throw new ParseException(parser, "Missing type bound");
/*     */     }
/* 160 */     return new TypeArgument(Wildcard.NONE, typeSignature);
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
/*     */   static List<TypeArgument> parseList(Parser parser, String definingClassName) throws ParseException {
/* 176 */     if (parser.peek() == '<') {
/* 177 */       parser.expect('<');
/* 178 */       List<TypeArgument> typeArguments = new ArrayList<>(2);
/* 179 */       while (parser.peek() != '>') {
/* 180 */         if (!parser.hasMore()) {
/* 181 */           throw new ParseException(parser, "Missing '>'");
/*     */         }
/* 183 */         typeArguments.add(parse(parser, definingClassName));
/*     */       } 
/* 185 */       parser.expect('>');
/* 186 */       return typeArguments;
/*     */     } 
/* 188 */     return Collections.emptyList();
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
/*     */   protected String getClassName() {
/* 200 */     throw new IllegalArgumentException("getClassName() cannot be called here");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassInfo getClassInfo() {
/* 208 */     throw new IllegalArgumentException("getClassInfo() cannot be called here");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/* 216 */     super.setScanResult(scanResult);
/* 217 */     if (this.typeSignature != null) {
/* 218 */       this.typeSignature.setScanResult(scanResult);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void findReferencedClassNames(Set<String> refdClassNames) {
/* 229 */     if (this.typeSignature != null) {
/* 230 */       this.typeSignature.findReferencedClassNames(refdClassNames);
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
/* 241 */     return ((this.typeSignature != null) ? this.typeSignature.hashCode() : 0) + 7 * this.wildcard.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 249 */     if (obj == this)
/* 250 */       return true; 
/* 251 */     if (!(obj instanceof TypeArgument)) {
/* 252 */       return false;
/*     */     }
/* 254 */     TypeArgument other = (TypeArgument)obj;
/* 255 */     return (Objects.equals(this.typeAnnotationInfo, other.typeAnnotationInfo) && 
/* 256 */       Objects.equals(this.typeSignature, other.typeSignature) && other.wildcard
/* 257 */       .equals(this.wildcard));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toStringInternal(boolean useSimpleNames, AnnotationInfoList annotationsToExclude, StringBuilder buf) {
/*     */     String typeSigStr;
/* 265 */     if (this.typeAnnotationInfo != null) {
/* 266 */       for (AnnotationInfo annotationInfo : this.typeAnnotationInfo) {
/* 267 */         if (annotationsToExclude == null || !annotationsToExclude.contains(annotationInfo)) {
/* 268 */           annotationInfo.toString(useSimpleNames, buf);
/* 269 */           buf.append(' ');
/*     */         } 
/*     */       } 
/*     */     }
/* 273 */     switch (this.wildcard) {
/*     */       case ANY:
/* 275 */         buf.append('?');
/*     */         return;
/*     */       case EXTENDS:
/* 278 */         typeSigStr = this.typeSignature.toString(useSimpleNames);
/* 279 */         buf.append(typeSigStr.equals("java.lang.Object") ? "?" : ("? extends " + typeSigStr));
/*     */         return;
/*     */       case SUPER:
/* 282 */         buf.append("? super ");
/* 283 */         this.typeSignature.toString(useSimpleNames, buf);
/*     */         return;
/*     */     } 
/*     */     
/* 287 */     this.typeSignature.toString(useSimpleNames, buf);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\TypeArgument.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */