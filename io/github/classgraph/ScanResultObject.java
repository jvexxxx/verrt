/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.LinkedHashSet;
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
/*     */ abstract class ScanResultObject
/*     */ {
/*     */   protected transient ScanResult scanResult;
/*     */   private transient ClassInfo classInfo;
/*     */   protected transient Class<?> classRef;
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/*  59 */     this.scanResult = scanResult;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final Set<ClassInfo> findReferencedClassInfo(LogNode log) {
/*  70 */     Set<ClassInfo> refdClassInfo = new LinkedHashSet<>();
/*  71 */     if (this.scanResult != null) {
/*  72 */       findReferencedClassInfo(this.scanResult.classNameToClassInfo, refdClassInfo, log);
/*     */     }
/*  74 */     return refdClassInfo;
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
/*     */   protected void findReferencedClassInfo(Map<String, ClassInfo> classNameToClassInfo, Set<ClassInfo> refdClassInfo, LogNode log) {
/*  89 */     ClassInfo ci = getClassInfo();
/*  90 */     if (ci != null) {
/*  91 */       refdClassInfo.add(ci);
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
/*     */   protected abstract String getClassName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ClassInfo getClassInfo() {
/* 112 */     if (this.classInfo == null) {
/* 113 */       if (this.scanResult == null) {
/* 114 */         return null;
/*     */       }
/* 116 */       String className = getClassName();
/* 117 */       if (className == null) {
/* 118 */         throw new IllegalArgumentException("Class name is not set");
/*     */       }
/* 120 */       this.classInfo = this.scanResult.getClassInfo(className);
/*     */     } 
/* 122 */     return this.classInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getClassInfoNameOrClassName() {
/*     */     String className;
/* 132 */     ClassInfo ci = null;
/*     */     try {
/* 134 */       ci = getClassInfo();
/* 135 */     } catch (IllegalArgumentException illegalArgumentException) {}
/*     */ 
/*     */     
/* 138 */     if (ci == null) {
/* 139 */       ci = this.classInfo;
/*     */     }
/* 141 */     if (ci != null) {
/*     */       
/* 143 */       className = ci.getName();
/*     */     } else {
/*     */       
/* 146 */       className = getClassName();
/*     */     } 
/* 148 */     if (className == null) {
/* 149 */       throw new IllegalArgumentException("Class name is not set");
/*     */     }
/* 151 */     return className;
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
/*     */   <T> Class<T> loadClass(Class<T> superclassOrInterfaceType, boolean ignoreExceptions) {
/* 173 */     if (this.classRef == null) {
/* 174 */       String className = getClassInfoNameOrClassName();
/* 175 */       if (this.scanResult != null) {
/* 176 */         this.classRef = this.scanResult.loadClass(className, superclassOrInterfaceType, ignoreExceptions);
/*     */       } else {
/*     */         
/*     */         try {
/* 180 */           this.classRef = Class.forName(className);
/* 181 */         } catch (Throwable t) {
/* 182 */           if (!ignoreExceptions) {
/* 183 */             throw new IllegalArgumentException("Could not load class " + className, t);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 189 */     Class<T> classT = (Class)this.classRef;
/* 190 */     return classT;
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
/*     */   <T> Class<T> loadClass(Class<T> superclassOrInterfaceType) {
/* 208 */     return loadClass(superclassOrInterfaceType, false);
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
/*     */   Class<?> loadClass(boolean ignoreExceptions) {
/* 223 */     if (this.classRef == null) {
/* 224 */       String className = getClassInfoNameOrClassName();
/* 225 */       if (this.scanResult != null) {
/* 226 */         this.classRef = this.scanResult.loadClass(className, ignoreExceptions);
/*     */       } else {
/*     */         
/*     */         try {
/* 230 */           this.classRef = Class.forName(className);
/* 231 */         } catch (Throwable t) {
/* 232 */           if (!ignoreExceptions) {
/* 233 */             throw new IllegalArgumentException("Could not load class " + className, t);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 238 */     return this.classRef;
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
/*     */   Class<?> loadClass() {
/* 250 */     return loadClass(false);
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
/*     */   protected abstract void toString(boolean paramBoolean, StringBuilder paramStringBuilder);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String toString(boolean useSimpleNames) {
/* 273 */     StringBuilder buf = new StringBuilder();
/* 274 */     toString(useSimpleNames, buf);
/* 275 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringWithSimpleNames() {
/* 286 */     StringBuilder buf = new StringBuilder();
/* 287 */     toString(true, buf);
/* 288 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 298 */     StringBuilder buf = new StringBuilder();
/* 299 */     toString(false, buf);
/* 300 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ScanResultObject.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */