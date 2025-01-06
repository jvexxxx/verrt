/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnnotationEnumValue
/*     */   extends ScanResultObject
/*     */   implements Comparable<AnnotationEnumValue>
/*     */ {
/*     */   private String className;
/*     */   private String valueName;
/*     */   
/*     */   AnnotationEnumValue() {}
/*     */   
/*     */   AnnotationEnumValue(String className, String constValueName) {
/*  59 */     this.className = className;
/*  60 */     this.valueName = constValueName;
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
/*     */   public String getClassName() {
/*  72 */     return this.className;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValueName() {
/*  81 */     return this.valueName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  91 */     return this.className + "." + this.valueName;
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
/*     */   public Object loadClassAndReturnEnumValue(boolean ignoreExceptions) throws IllegalArgumentException {
/*     */     Field field;
/* 106 */     Class<?> classRef = loadClass(ignoreExceptions);
/* 107 */     if (classRef == null) {
/* 108 */       if (ignoreExceptions) {
/* 109 */         return null;
/*     */       }
/* 111 */       throw new IllegalArgumentException("Enum class " + this.className + " could not be loaded");
/*     */     } 
/*     */     
/* 114 */     if (!classRef.isEnum()) {
/* 115 */       throw new IllegalArgumentException("Class " + this.className + " is not an enum");
/*     */     }
/*     */     
/*     */     try {
/* 119 */       field = classRef.getDeclaredField(this.valueName);
/* 120 */     } catch (ReflectiveOperationException|SecurityException e) {
/* 121 */       throw new IllegalArgumentException("Could not find enum constant " + this, e);
/*     */     } 
/* 123 */     if (!field.isEnumConstant()) {
/* 124 */       throw new IllegalArgumentException("Field " + this + " is not an enum constant");
/*     */     }
/*     */     try {
/* 127 */       return field.get(null);
/* 128 */     } catch (ReflectiveOperationException|SecurityException e) {
/* 129 */       throw new IllegalArgumentException("Field " + this + " is not accessible", e);
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
/*     */   public Object loadClassAndReturnEnumValue() throws IllegalArgumentException {
/* 142 */     return loadClassAndReturnEnumValue(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(AnnotationEnumValue o) {
/* 152 */     int diff = this.className.compareTo(o.className);
/* 153 */     return (diff == 0) ? this.valueName.compareTo(o.valueName) : diff;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 161 */     if (obj == this)
/* 162 */       return true; 
/* 163 */     if (!(obj instanceof AnnotationEnumValue)) {
/* 164 */       return false;
/*     */     }
/* 166 */     return (compareTo((AnnotationEnumValue)obj) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 174 */     return this.className.hashCode() * 11 + this.valueName.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void toString(boolean useSimpleNames, StringBuilder buf) {
/* 179 */     buf.append(useSimpleNames ? ClassInfo.getSimpleName(this.className) : this.className);
/* 180 */     buf.append('.');
/* 181 */     buf.append(this.valueName);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\AnnotationEnumValue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */