/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
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
/*     */ public class AnnotationParameterValue
/*     */   extends ScanResultObject
/*     */   implements HasName, Comparable<AnnotationParameterValue>
/*     */ {
/*     */   private String name;
/*     */   private ObjectTypedValueWrapper value;
/*     */   
/*     */   AnnotationParameterValue() {}
/*     */   
/*     */   AnnotationParameterValue(String name, Object value) {
/*  62 */     this.name = name;
/*  63 */     this.value = new ObjectTypedValueWrapper(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  73 */     return this.name;
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
/*     */   public Object getValue() {
/*  96 */     return (this.value == null) ? null : this.value.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setValue(Object newValue) {
/* 107 */     this.value = new ObjectTypedValueWrapper(newValue);
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
/* 118 */     throw new IllegalArgumentException("getClassName() cannot be called here");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassInfo getClassInfo() {
/* 126 */     throw new IllegalArgumentException("getClassInfo() cannot be called here");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/* 134 */     super.setScanResult(scanResult);
/* 135 */     if (this.value != null) {
/* 136 */       this.value.setScanResult(scanResult);
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
/*     */   protected void findReferencedClassInfo(Map<String, ClassInfo> classNameToClassInfo, Set<ClassInfo> refdClassInfo, LogNode log) {
/* 151 */     if (this.value != null) {
/* 152 */       this.value.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
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
/*     */   void convertWrapperArraysToPrimitiveArrays(ClassInfo annotationClassInfo) {
/* 166 */     if (this.value != null) {
/* 167 */       this.value.convertWrapperArraysToPrimitiveArrays(annotationClassInfo, this.name);
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
/*     */   Object instantiate(ClassInfo annotationClassInfo) {
/* 179 */     return this.value.instantiateOrGet(annotationClassInfo, this.name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(AnnotationParameterValue other) {
/* 189 */     if (other == this) {
/* 190 */       return 0;
/*     */     }
/* 192 */     int diff = this.name.compareTo(other.getName());
/* 193 */     if (diff != 0) {
/* 194 */       return diff;
/*     */     }
/* 196 */     if (this.value.equals(other.value)) {
/* 197 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 201 */     Object p0 = getValue();
/* 202 */     Object p1 = other.getValue();
/* 203 */     return (p0 == null || p1 == null) ? (((p0 == null) ? 0 : 1) - ((p1 == null) ? 0 : 1)) : 
/* 204 */       toStringParamValueOnly().compareTo(other.toStringParamValueOnly());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 212 */     if (obj == this)
/* 213 */       return true; 
/* 214 */     if (!(obj instanceof AnnotationParameterValue)) {
/* 215 */       return false;
/*     */     }
/* 217 */     AnnotationParameterValue other = (AnnotationParameterValue)obj;
/* 218 */     if (this.name.equals(other.name)) if (((this.value == null) ? true : false) == ((other.value == null) ? true : false) && (this.value == null || this.value
/* 219 */         .equals(other.value)));
/*     */     
/*     */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 227 */     return Objects.hash(new Object[] { this.name, this.value });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toString(boolean useSimpleNames, StringBuilder buf) {
/* 234 */     buf.append(this.name);
/* 235 */     buf.append("=");
/* 236 */     toStringParamValueOnly(useSimpleNames, buf);
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
/*     */   private static void toString(Object val, boolean useSimpleNames, StringBuilder buf) {
/* 250 */     if (val == null) {
/* 251 */       buf.append("null");
/* 252 */     } else if (val instanceof ScanResultObject) {
/* 253 */       ((ScanResultObject)val).toString(useSimpleNames, buf);
/*     */     } else {
/* 255 */       buf.append(val);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void toStringParamValueOnly(boolean useSimpleNames, StringBuilder buf) {
/* 266 */     if (this.value == null) {
/* 267 */       buf.append("null");
/*     */     } else {
/* 269 */       Object paramVal = this.value.get();
/* 270 */       Class<?> valClass = paramVal.getClass();
/* 271 */       if (valClass.isArray()) {
/* 272 */         buf.append('{');
/* 273 */         for (int j = 0, n = Array.getLength(paramVal); j < n; j++) {
/* 274 */           if (j > 0) {
/* 275 */             buf.append(", ");
/*     */           }
/* 277 */           Object elt = Array.get(paramVal, j);
/* 278 */           toString(elt, useSimpleNames, buf);
/*     */         } 
/* 280 */         buf.append('}');
/* 281 */       } else if (paramVal instanceof String) {
/* 282 */         buf.append('"');
/* 283 */         buf.append(paramVal.toString().replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r"));
/* 284 */         buf.append('"');
/* 285 */       } else if (paramVal instanceof Character) {
/* 286 */         buf.append('\'');
/* 287 */         buf.append(paramVal.toString().replace("'", "\\'").replace("\n", "\\n").replace("\r", "\\r"));
/* 288 */         buf.append('\'');
/*     */       } else {
/* 290 */         toString(paramVal, useSimpleNames, buf);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String toStringParamValueOnly() {
/* 301 */     StringBuilder buf = new StringBuilder();
/* 302 */     toStringParamValueOnly(false, buf);
/* 303 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\AnnotationParameterValue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */