/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class HierarchicalTypeSignature
/*     */   extends ScanResultObject
/*     */ {
/*     */   protected AnnotationInfoList typeAnnotationInfo;
/*     */   
/*     */   protected void addTypeAnnotation(AnnotationInfo annotationInfo) {
/*  48 */     if (this.typeAnnotationInfo == null) {
/*  49 */       this.typeAnnotationInfo = new AnnotationInfoList(1);
/*     */     }
/*  51 */     this.typeAnnotationInfo.add(annotationInfo);
/*     */   }
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/*  56 */     super.setScanResult(scanResult);
/*  57 */     if (this.typeAnnotationInfo != null) {
/*  58 */       for (AnnotationInfo annotationInfo : this.typeAnnotationInfo) {
/*  59 */         annotationInfo.setScanResult(scanResult);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationInfoList getTypeAnnotationInfo() {
/*  70 */     return this.typeAnnotationInfo;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toString(boolean useSimpleNames, StringBuilder buf) {
/* 107 */     toStringInternal(useSimpleNames, null, buf);
/*     */   }
/*     */   
/*     */   protected abstract void addTypeAnnotation(List<Classfile.TypePathNode> paramList, AnnotationInfo paramAnnotationInfo);
/*     */   
/*     */   protected abstract void toStringInternal(boolean paramBoolean, AnnotationInfoList paramAnnotationInfoList, StringBuilder paramStringBuilder);
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\HierarchicalTypeSignature.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */