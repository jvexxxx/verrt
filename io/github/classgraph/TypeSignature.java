/*     */ package io.github.classgraph;
/*     */ 
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
/*     */ public abstract class TypeSignature
/*     */   extends HierarchicalTypeSignature
/*     */ {
/*     */   protected void findReferencedClassNames(Set<String> refdClassNames) {
/*  59 */     String className = getClassName();
/*  60 */     if (className != null && !className.isEmpty()) {
/*  61 */       refdClassNames.add(getClassName());
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
/*     */   protected final void findReferencedClassInfo(Map<String, ClassInfo> classNameToClassInfo, Set<ClassInfo> refdClassInfo, LogNode log) {
/*  76 */     Set<String> refdClassNames = new HashSet<>();
/*  77 */     findReferencedClassNames(refdClassNames);
/*  78 */     for (String refdClassName : refdClassNames) {
/*  79 */       ClassInfo classInfo = ClassInfo.getOrCreateClassInfo(refdClassName, classNameToClassInfo);
/*  80 */       classInfo.scanResult = this.scanResult;
/*  81 */       refdClassInfo.add(classInfo);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationInfoList getTypeAnnotationInfo() {
/*  91 */     return this.typeAnnotationInfo;
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
/*     */   public abstract boolean equalsIgnoringTypeParams(TypeSignature paramTypeSignature);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static TypeSignature parse(Parser parser, String definingClass) throws ParseException {
/* 116 */     ReferenceTypeSignature referenceTypeSignature = ReferenceTypeSignature.parseReferenceTypeSignature(parser, definingClass);
/* 117 */     if (referenceTypeSignature != null) {
/* 118 */       return referenceTypeSignature;
/*     */     }
/* 120 */     BaseTypeSignature baseTypeSignature = BaseTypeSignature.parse(parser);
/* 121 */     if (baseTypeSignature != null) {
/* 122 */       return baseTypeSignature;
/*     */     }
/* 124 */     return null;
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
/*     */   static TypeSignature parse(String typeDescriptor, String definingClass) throws ParseException {
/* 139 */     Parser parser = new Parser(typeDescriptor);
/*     */     
/* 141 */     TypeSignature typeSignature = parse(parser, definingClass);
/* 142 */     if (typeSignature == null) {
/* 143 */       throw new ParseException(parser, "Could not parse type signature");
/*     */     }
/* 145 */     if (parser.hasMore()) {
/* 146 */       throw new ParseException(parser, "Extra characters at end of type descriptor");
/*     */     }
/* 148 */     return typeSignature;
/*     */   }
/*     */   
/*     */   protected abstract void addTypeAnnotation(List<Classfile.TypePathNode> paramList, AnnotationInfo paramAnnotationInfo);
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\TypeSignature.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */