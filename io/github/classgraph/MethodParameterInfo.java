/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Objects;
/*     */ import nonapi.io.github.classgraph.utils.Assert;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MethodParameterInfo
/*     */ {
/*     */   private final MethodInfo methodInfo;
/*     */   final AnnotationInfo[] annotationInfo;
/*     */   private final int modifiers;
/*     */   private final TypeSignature typeDescriptor;
/*     */   private final TypeSignature typeSignature;
/*     */   private final String name;
/*     */   private ScanResult scanResult;
/*     */   
/*     */   MethodParameterInfo(MethodInfo methodInfo, AnnotationInfo[] annotationInfo, int modifiers, TypeSignature typeDescriptor, TypeSignature typeSignature, String name) {
/*  87 */     this.methodInfo = methodInfo;
/*  88 */     this.name = name;
/*  89 */     this.modifiers = modifiers;
/*  90 */     this.typeDescriptor = typeDescriptor;
/*  91 */     this.typeSignature = typeSignature;
/*  92 */     this.annotationInfo = annotationInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodInfo getMethodInfo() {
/* 103 */     return this.methodInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 113 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getModifiers() {
/* 123 */     return this.modifiers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getModifiersStr() {
/* 133 */     StringBuilder buf = new StringBuilder();
/* 134 */     modifiersToString(this.modifiers, buf);
/* 135 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeSignature getTypeSignature() {
/* 145 */     return this.typeSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeSignature getTypeDescriptor() {
/* 154 */     return this.typeDescriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeSignature getTypeSignatureOrTypeDescriptor() {
/* 163 */     return (this.typeSignature != null) ? this.typeSignature : this.typeDescriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationInfoList getAnnotationInfo() {
/* 172 */     if (!this.scanResult.scanSpec.enableAnnotationInfo) {
/* 173 */       throw new IllegalArgumentException("Please call ClassGraph#enableAnnotationInfo() before #scan()");
/*     */     }
/* 175 */     if (this.annotationInfo == null || this.annotationInfo.length == 0) {
/* 176 */       return AnnotationInfoList.EMPTY_LIST;
/*     */     }
/* 178 */     AnnotationInfoList annotationInfoList = new AnnotationInfoList(this.annotationInfo.length);
/* 179 */     Collections.addAll(annotationInfoList, this.annotationInfo);
/* 180 */     return AnnotationInfoList.getIndirectAnnotations(annotationInfoList, (ClassInfo)null);
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
/*     */   public AnnotationInfo getAnnotationInfo(Class<? extends Annotation> annotation) {
/* 194 */     Assert.isAnnotation(annotation);
/* 195 */     return getAnnotationInfo(annotation.getName());
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
/*     */   public AnnotationInfo getAnnotationInfo(String annotationName) {
/* 209 */     return getAnnotationInfo().get(annotationName);
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
/*     */   public AnnotationInfoList getAnnotationInfoRepeatable(Class<? extends Annotation> annotation) {
/* 222 */     Assert.isAnnotation(annotation);
/* 223 */     return getAnnotationInfoRepeatable(annotation.getName());
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
/*     */   public AnnotationInfoList getAnnotationInfoRepeatable(String annotationName) {
/* 236 */     return getAnnotationInfo().getRepeatable(annotationName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAnnotation(Class<? extends Annotation> annotation) {
/* 247 */     Assert.isAnnotation(annotation);
/* 248 */     return hasAnnotation(annotation.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAnnotation(String annotationName) {
/* 259 */     return getAnnotationInfo().containsName(annotationName);
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
/*     */   protected void setScanResult(ScanResult scanResult) {
/* 271 */     this.scanResult = scanResult;
/* 272 */     if (this.annotationInfo != null) {
/* 273 */       for (AnnotationInfo ai : this.annotationInfo) {
/* 274 */         ai.setScanResult(scanResult);
/*     */       }
/*     */     }
/* 277 */     if (this.typeDescriptor != null) {
/* 278 */       this.typeDescriptor.setScanResult(scanResult);
/*     */     }
/* 280 */     if (this.typeSignature != null) {
/* 281 */       this.typeSignature.setScanResult(scanResult);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFinal() {
/* 291 */     return Modifier.isFinal(this.modifiers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSynthetic() {
/* 300 */     return ((this.modifiers & 0x1000) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMandated() {
/* 309 */     return ((this.modifiers & 0x8000) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 319 */     if (obj == this)
/* 320 */       return true; 
/* 321 */     if (!(obj instanceof MethodParameterInfo)) {
/* 322 */       return false;
/*     */     }
/* 324 */     MethodParameterInfo other = (MethodParameterInfo)obj;
/* 325 */     return (Objects.equals(this.methodInfo, other.methodInfo) && 
/* 326 */       Objects.deepEquals(this.annotationInfo, other.annotationInfo) && this.modifiers == other.modifiers && 
/* 327 */       Objects.equals(this.typeDescriptor, other.typeDescriptor) && 
/* 328 */       Objects.equals(this.typeSignature, other.typeSignature) && Objects.equals(this.name, other.name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 336 */     return Objects.hash(new Object[] { this.methodInfo, Integer.valueOf(Arrays.hashCode((Object[])this.annotationInfo)), this.typeDescriptor, this.typeSignature, this.name }) + this.modifiers;
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
/*     */   static void modifiersToString(int modifiers, StringBuilder buf) {
/* 349 */     if ((modifiers & 0x10) != 0) {
/* 350 */       buf.append("final ");
/*     */     }
/* 352 */     if ((modifiers & 0x1000) != 0) {
/* 353 */       buf.append("synthetic ");
/*     */     }
/* 355 */     if ((modifiers & 0x8000) != 0) {
/* 356 */       buf.append("mandated ");
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
/*     */   protected void toString(boolean useSimpleNames, StringBuilder buf) {
/* 371 */     if (this.annotationInfo != null) {
/* 372 */       for (AnnotationInfo anAnnotationInfo : this.annotationInfo) {
/* 373 */         anAnnotationInfo.toString(useSimpleNames, buf);
/* 374 */         buf.append(' ');
/*     */       } 
/*     */     }
/*     */     
/* 378 */     modifiersToString(this.modifiers, buf);
/*     */     
/* 380 */     getTypeSignatureOrTypeDescriptor().toString(useSimpleNames, buf);
/*     */     
/* 382 */     buf.append(' ');
/* 383 */     buf.append((this.name == null) ? "_unnamed_param" : this.name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringWithSimpleNames() {
/* 392 */     StringBuilder buf = new StringBuilder();
/* 393 */     toString(true, buf);
/* 394 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 404 */     StringBuilder buf = new StringBuilder();
/* 405 */     toString(false, buf);
/* 406 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\MethodParameterInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */