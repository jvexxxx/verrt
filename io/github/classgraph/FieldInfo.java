/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.types.ParseException;
/*     */ import nonapi.io.github.classgraph.types.TypeUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FieldInfo
/*     */   extends ClassMemberInfo
/*     */   implements Comparable<FieldInfo>
/*     */ {
/*     */   private transient TypeSignature typeSignature;
/*     */   private transient TypeSignature typeDescriptor;
/*     */   private ObjectTypedValueWrapper constantInitializerValue;
/*     */   private transient List<Classfile.TypeAnnotationDecorator> typeAnnotationDecorators;
/*     */   
/*     */   FieldInfo() {}
/*     */   
/*     */   FieldInfo(String definingClassName, String fieldName, int modifiers, String typeDescriptorStr, String typeSignatureStr, Object constantInitializerValue, AnnotationInfoList annotationInfo, List<Classfile.TypeAnnotationDecorator> typeAnnotationDecorators) {
/*  91 */     super(definingClassName, fieldName, modifiers, typeDescriptorStr, typeSignatureStr, annotationInfo);
/*  92 */     if (fieldName == null) {
/*  93 */       throw new IllegalArgumentException("fieldName must not be null");
/*     */     }
/*  95 */     this
/*  96 */       .constantInitializerValue = (constantInitializerValue == null) ? null : new ObjectTypedValueWrapper(constantInitializerValue);
/*  97 */     this.typeAnnotationDecorators = typeAnnotationDecorators;
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
/*     */   @Deprecated
/*     */   public String getModifierStr() {
/* 110 */     return getModifiersStr();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getModifiersStr() {
/* 120 */     StringBuilder buf = new StringBuilder();
/* 121 */     TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.FIELD, false, buf);
/* 122 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransient() {
/* 131 */     return Modifier.isTransient(this.modifiers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEnum() {
/* 140 */     return ((this.modifiers & 0x4000) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeSignature getTypeDescriptor() {
/* 151 */     if (this.typeDescriptorStr == null) {
/* 152 */       return null;
/*     */     }
/* 154 */     if (this.typeDescriptor == null) {
/*     */       try {
/* 156 */         this.typeDescriptor = TypeSignature.parse(this.typeDescriptorStr, this.declaringClassName);
/* 157 */         this.typeDescriptor.setScanResult(this.scanResult);
/* 158 */         if (this.typeAnnotationDecorators != null) {
/* 159 */           for (Classfile.TypeAnnotationDecorator decorator : this.typeAnnotationDecorators) {
/* 160 */             decorator.decorate(this.typeDescriptor);
/*     */           }
/*     */         }
/* 163 */       } catch (ParseException e) {
/* 164 */         throw new IllegalArgumentException(e);
/*     */       } 
/*     */     }
/* 167 */     return this.typeDescriptor;
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
/*     */   public TypeSignature getTypeSignature() {
/* 183 */     if (this.typeSignatureStr == null) {
/* 184 */       return null;
/*     */     }
/* 186 */     if (this.typeSignature == null) {
/*     */       try {
/* 188 */         this.typeSignature = TypeSignature.parse(this.typeSignatureStr, this.declaringClassName);
/* 189 */         this.typeSignature.setScanResult(this.scanResult);
/* 190 */         if (this.typeAnnotationDecorators != null) {
/* 191 */           for (Classfile.TypeAnnotationDecorator decorator : this.typeAnnotationDecorators) {
/* 192 */             decorator.decorate(this.typeSignature);
/*     */           }
/*     */         }
/* 195 */       } catch (ParseException e) {
/* 196 */         throw new IllegalArgumentException("Invalid type signature for field " + 
/* 197 */             getClassName() + "." + getName() + (
/* 198 */             (getClassInfo() != null) ? (
/* 199 */             " in classpath element " + getClassInfo().getClasspathElementURI()) : 
/* 200 */             "") + " : " + this.typeSignatureStr, e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 205 */     return this.typeSignature;
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
/*     */   public TypeSignature getTypeSignatureOrTypeDescriptor() {
/* 218 */     TypeSignature typeSig = null;
/*     */     try {
/* 220 */       typeSig = getTypeSignature();
/* 221 */       if (typeSig != null) {
/* 222 */         return typeSig;
/*     */       }
/* 224 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 227 */     return getTypeDescriptor();
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
/*     */   public Object getConstantInitializerValue() {
/* 242 */     if (!this.scanResult.scanSpec.enableStaticFinalFieldConstantInitializerValues) {
/* 243 */       throw new IllegalArgumentException("Please call ClassGraph#enableStaticFinalFieldConstantInitializerValues() before #scan()");
/*     */     }
/*     */     
/* 246 */     return (this.constantInitializerValue == null) ? null : this.constantInitializerValue.get();
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
/*     */   public Field loadClassAndGetField() throws IllegalArgumentException {
/*     */     try {
/* 260 */       return loadClass().getField(getName());
/* 261 */     } catch (NoSuchFieldException e1) {
/*     */       try {
/* 263 */         return loadClass().getDeclaredField(getName());
/* 264 */       } catch (NoSuchFieldException e2) {
/* 265 */         throw new IllegalArgumentException("No such field: " + getClassName() + "." + getName());
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
/*     */ 
/*     */   
/*     */   void handleRepeatableAnnotations(Set<String> allRepeatableAnnotationNames) {
/* 279 */     if (this.annotationInfo != null) {
/* 280 */       this.annotationInfo.handleRepeatableAnnotations(allRepeatableAnnotationNames, getClassInfo(), ClassInfo.RelType.FIELD_ANNOTATIONS, ClassInfo.RelType.CLASSES_WITH_FIELD_ANNOTATION, ClassInfo.RelType.CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION);
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
/*     */   void setScanResult(ScanResult scanResult) {
/* 293 */     super.setScanResult(scanResult);
/* 294 */     if (this.typeSignature != null) {
/* 295 */       this.typeSignature.setScanResult(scanResult);
/*     */     }
/* 297 */     if (this.typeDescriptor != null) {
/* 298 */       this.typeDescriptor.setScanResult(scanResult);
/*     */     }
/* 300 */     if (this.annotationInfo != null) {
/* 301 */       for (AnnotationInfo ai : this.annotationInfo) {
/* 302 */         ai.setScanResult(scanResult);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void findReferencedClassInfo(Map<String, ClassInfo> classNameToClassInfo, Set<ClassInfo> refdClassInfo, LogNode log) {
/*     */     try {
/* 319 */       TypeSignature fieldSig = getTypeSignature();
/* 320 */       if (fieldSig != null) {
/* 321 */         fieldSig.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/*     */       }
/* 323 */     } catch (IllegalArgumentException e) {
/* 324 */       if (log != null) {
/* 325 */         log.log("Illegal type signature for field " + getClassName() + "." + getName() + ": " + 
/* 326 */             getTypeSignatureStr());
/*     */       }
/*     */     } 
/*     */     try {
/* 330 */       TypeSignature fieldDesc = getTypeDescriptor();
/* 331 */       if (fieldDesc != null) {
/* 332 */         fieldDesc.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/*     */       }
/* 334 */     } catch (IllegalArgumentException e) {
/* 335 */       if (log != null) {
/* 336 */         log.log("Illegal type descriptor for field " + getClassName() + "." + getName() + ": " + 
/* 337 */             getTypeDescriptorStr());
/*     */       }
/*     */     } 
/* 340 */     if (this.annotationInfo != null) {
/* 341 */       for (AnnotationInfo ai : this.annotationInfo) {
/* 342 */         ai.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 358 */     if (obj == this)
/* 359 */       return true; 
/* 360 */     if (!(obj instanceof FieldInfo)) {
/* 361 */       return false;
/*     */     }
/* 363 */     FieldInfo other = (FieldInfo)obj;
/* 364 */     return (this.declaringClassName.equals(other.declaringClassName) && this.name.equals(other.name));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 374 */     return this.name.hashCode() + this.declaringClassName.hashCode() * 11;
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
/*     */   public int compareTo(FieldInfo other) {
/* 386 */     int diff = this.declaringClassName.compareTo(other.declaringClassName);
/* 387 */     if (diff != 0) {
/* 388 */       return diff;
/*     */     }
/* 390 */     return this.name.compareTo(other.name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void toString(boolean includeModifiers, boolean useSimpleNames, StringBuilder buf) {
/* 396 */     if (this.annotationInfo != null) {
/* 397 */       for (AnnotationInfo annotation : this.annotationInfo) {
/*     */         
/* 399 */         if (buf.length() > 0 && buf.charAt(buf.length() - 1) != ' ' && buf
/* 400 */           .charAt(buf.length() - 1) != '(') {
/* 401 */           buf.append(' ');
/*     */         }
/* 403 */         annotation.toString(useSimpleNames, buf);
/*     */       } 
/*     */     }
/*     */     
/* 407 */     if (this.modifiers != 0 && includeModifiers) {
/* 408 */       if (buf.length() > 0 && buf.charAt(buf.length() - 1) != ' ' && buf.charAt(buf.length() - 1) != '(') {
/* 409 */         buf.append(' ');
/*     */       }
/* 411 */       TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.FIELD, false, buf);
/*     */     } 
/*     */     
/* 414 */     if (buf.length() > 0 && buf.charAt(buf.length() - 1) != ' ' && buf.charAt(buf.length() - 1) != '(') {
/* 415 */       buf.append(' ');
/*     */     }
/* 417 */     TypeSignature typeSig = getTypeSignatureOrTypeDescriptor();
/* 418 */     typeSig.toStringInternal(useSimpleNames, this.annotationInfo, buf);
/*     */     
/* 420 */     buf.append(' ');
/* 421 */     buf.append(this.name);
/*     */     
/* 423 */     if (this.constantInitializerValue != null) {
/* 424 */       Object val = this.constantInitializerValue.get();
/* 425 */       buf.append(" = ");
/* 426 */       if (val instanceof String) {
/* 427 */         buf.append('"').append(((String)val).replace("\\", "\\\\").replace("\"", "\\\"")).append('"');
/* 428 */       } else if (val instanceof Character) {
/* 429 */         buf.append('\'').append(((Character)val).toString().replace("\\", "\\\\").replaceAll("'", "\\'"))
/* 430 */           .append('\'');
/*     */       } else {
/* 432 */         buf.append((val == null) ? "null" : val.toString());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void toString(boolean useSimpleNames, StringBuilder buf) {
/* 439 */     toString(true, useSimpleNames, buf);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\FieldInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */