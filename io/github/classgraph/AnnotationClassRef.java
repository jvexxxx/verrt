/*     */ package io.github.classgraph;
/*     */ 
/*     */ import nonapi.io.github.classgraph.types.ParseException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnnotationClassRef
/*     */   extends ScanResultObject
/*     */ {
/*     */   private String typeDescriptorStr;
/*     */   private transient TypeSignature typeSignature;
/*     */   private transient String className;
/*     */   
/*     */   AnnotationClassRef() {}
/*     */   
/*     */   AnnotationClassRef(String typeDescriptorStr) {
/*  61 */     this.typeDescriptorStr = typeDescriptorStr;
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
/*  72 */     return getClassName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private TypeSignature getTypeSignature() {
/*  82 */     if (this.typeSignature == null) {
/*     */       
/*     */       try {
/*     */         
/*  86 */         this.typeSignature = TypeSignature.parse(this.typeDescriptorStr, (String)null);
/*  87 */         this.typeSignature.setScanResult(this.scanResult);
/*  88 */       } catch (ParseException e) {
/*  89 */         throw new IllegalArgumentException(e);
/*     */       } 
/*     */     }
/*  92 */     return this.typeSignature;
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
/*     */   public Class<?> loadClass(boolean ignoreExceptions) {
/* 106 */     getTypeSignature();
/* 107 */     if (this.typeSignature instanceof BaseTypeSignature)
/* 108 */       return ((BaseTypeSignature)this.typeSignature).getType(); 
/* 109 */     if (this.typeSignature instanceof ClassRefTypeSignature)
/* 110 */       return this.typeSignature.loadClass(ignoreExceptions); 
/* 111 */     if (this.typeSignature instanceof ArrayTypeSignature) {
/* 112 */       return this.typeSignature.loadClass(ignoreExceptions);
/*     */     }
/* 114 */     throw new IllegalArgumentException("Got unexpected type " + this.typeSignature.getClass().getName() + " for ref type signature: " + this.typeDescriptorStr);
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
/*     */   public Class<?> loadClass() {
/* 128 */     return loadClass(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getClassName() {
/* 138 */     if (this.className == null) {
/* 139 */       getTypeSignature();
/* 140 */       if (this.typeSignature instanceof BaseTypeSignature) {
/* 141 */         this.className = ((BaseTypeSignature)this.typeSignature).getTypeStr();
/* 142 */       } else if (this.typeSignature instanceof ClassRefTypeSignature) {
/* 143 */         this.className = ((ClassRefTypeSignature)this.typeSignature).getFullyQualifiedClassName();
/* 144 */       } else if (this.typeSignature instanceof ArrayTypeSignature) {
/* 145 */         this.className = this.typeSignature.getClassName();
/*     */       } else {
/* 147 */         throw new IllegalArgumentException("Got unexpected type " + this.typeSignature.getClass().getName() + " for ref type signature: " + this.typeDescriptorStr);
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     return this.className;
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
/*     */   public ClassInfo getClassInfo() {
/* 164 */     getTypeSignature();
/* 165 */     return this.typeSignature.getClassInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/* 173 */     super.setScanResult(scanResult);
/* 174 */     if (this.typeSignature != null) {
/* 175 */       this.typeSignature.setScanResult(scanResult);
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
/* 186 */     return getTypeSignature().hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 194 */     if (obj == this)
/* 195 */       return true; 
/* 196 */     if (!(obj instanceof AnnotationClassRef)) {
/* 197 */       return false;
/*     */     }
/* 199 */     return getTypeSignature().equals(((AnnotationClassRef)obj).getTypeSignature());
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
/*     */   protected void toString(boolean useSimpleNames, StringBuilder buf) {
/* 218 */     buf.append(getTypeSignature().toString(useSimpleNames)).append(".class");
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\AnnotationClassRef.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */