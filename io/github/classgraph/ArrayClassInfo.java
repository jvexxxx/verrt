/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class ArrayClassInfo
/*     */   extends ClassInfo
/*     */ {
/*     */   private ArrayTypeSignature arrayTypeSignature;
/*     */   private ClassInfo elementClassInfo;
/*     */   
/*     */   ArrayClassInfo() {}
/*     */   
/*     */   ArrayClassInfo(ArrayTypeSignature arrayTypeSignature) {
/*  65 */     super(arrayTypeSignature.getClassName(), 0, (Resource)null);
/*  66 */     this.arrayTypeSignature = arrayTypeSignature;
/*     */     
/*  68 */     getElementClassInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/*  76 */     super.setScanResult(scanResult);
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
/*     */   public String getTypeSignatureStr() {
/*  88 */     return this.arrayTypeSignature.getTypeSignatureStr();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassTypeSignature getTypeSignature() {
/*  99 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayTypeSignature getArrayTypeSignature() {
/* 108 */     return this.arrayTypeSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeSignature getElementTypeSignature() {
/* 117 */     return this.arrayTypeSignature.getElementTypeSignature();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumDimensions() {
/* 126 */     return this.arrayTypeSignature.getNumDimensions();
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
/*     */   public ClassInfo getElementClassInfo() {
/* 138 */     if (this.elementClassInfo == null) {
/* 139 */       TypeSignature elementTypeSignature = this.arrayTypeSignature.getElementTypeSignature();
/* 140 */       if (!(elementTypeSignature instanceof BaseTypeSignature)) {
/* 141 */         this.elementClassInfo = this.arrayTypeSignature.getElementTypeSignature().getClassInfo();
/* 142 */         if (this.elementClassInfo != null) {
/*     */           
/* 144 */           this.classpathElement = this.elementClassInfo.classpathElement;
/* 145 */           this.classfileResource = this.elementClassInfo.classfileResource;
/* 146 */           this.classLoader = this.elementClassInfo.classLoader;
/* 147 */           this.isScannedClass = this.elementClassInfo.isScannedClass;
/* 148 */           this.isExternalClass = this.elementClassInfo.isExternalClass;
/* 149 */           this.moduleInfo = this.elementClassInfo.moduleInfo;
/* 150 */           this.packageInfo = this.elementClassInfo.packageInfo;
/*     */         } 
/*     */       } 
/*     */     } 
/* 154 */     return this.elementClassInfo;
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
/* 169 */     return this.arrayTypeSignature.loadElementClass(ignoreExceptions);
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
/* 180 */     return this.arrayTypeSignature.loadElementClass();
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
/* 196 */     if (this.classRef == null) {
/* 197 */       this.classRef = this.arrayTypeSignature.loadClass(ignoreExceptions);
/*     */     }
/* 199 */     return this.classRef;
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
/* 212 */     if (this.classRef == null) {
/* 213 */       this.classRef = this.arrayTypeSignature.loadClass();
/*     */     }
/* 215 */     return this.classRef;
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
/*     */   protected void findReferencedClassInfo(Map<String, ClassInfo> classNameToClassInfo, Set<ClassInfo> refdClassInfo, LogNode log) {
/* 231 */     super.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 241 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 249 */     return super.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ArrayClassInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */