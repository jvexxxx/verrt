/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Arrays;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ObjectTypedValueWrapper
/*     */   extends ScanResultObject
/*     */ {
/*     */   private AnnotationEnumValue annotationEnumValue;
/*     */   private AnnotationClassRef annotationClassRef;
/*     */   private AnnotationInfo annotationInfo;
/*     */   private String stringValue;
/*     */   private Integer integerValue;
/*     */   private Long longValue;
/*     */   private Short shortValue;
/*     */   private Boolean booleanValue;
/*     */   private Character characterValue;
/*     */   private Float floatValue;
/*     */   private Double doubleValue;
/*     */   private Byte byteValue;
/*     */   private String[] stringArrayValue;
/*     */   private int[] intArrayValue;
/*     */   private long[] longArrayValue;
/*     */   private short[] shortArrayValue;
/*     */   private boolean[] booleanArrayValue;
/*     */   private char[] charArrayValue;
/*     */   private float[] floatArrayValue;
/*     */   private double[] doubleArrayValue;
/*     */   private byte[] byteArrayValue;
/*     */   private ObjectTypedValueWrapper[] objectArrayValue;
/*     */   
/*     */   public ObjectTypedValueWrapper() {}
/*     */   
/*     */   public ObjectTypedValueWrapper(Object annotationParamValue) {
/* 128 */     if (annotationParamValue != null) {
/* 129 */       Class<?> annotationParameterValueClass = annotationParamValue.getClass();
/* 130 */       if (annotationParameterValueClass.isArray()) {
/*     */         
/* 132 */         if (annotationParameterValueClass == String[].class) {
/* 133 */           this.stringArrayValue = (String[])annotationParamValue;
/* 134 */         } else if (annotationParameterValueClass == int[].class) {
/* 135 */           this.intArrayValue = (int[])annotationParamValue;
/* 136 */         } else if (annotationParameterValueClass == long[].class) {
/* 137 */           this.longArrayValue = (long[])annotationParamValue;
/* 138 */         } else if (annotationParameterValueClass == short[].class) {
/* 139 */           this.shortArrayValue = (short[])annotationParamValue;
/* 140 */         } else if (annotationParameterValueClass == boolean[].class) {
/* 141 */           this.booleanArrayValue = (boolean[])annotationParamValue;
/* 142 */         } else if (annotationParameterValueClass == char[].class) {
/* 143 */           this.charArrayValue = (char[])annotationParamValue;
/* 144 */         } else if (annotationParameterValueClass == float[].class) {
/* 145 */           this.floatArrayValue = (float[])annotationParamValue;
/* 146 */         } else if (annotationParameterValueClass == double[].class) {
/* 147 */           this.doubleArrayValue = (double[])annotationParamValue;
/* 148 */         } else if (annotationParameterValueClass == byte[].class) {
/* 149 */           this.byteArrayValue = (byte[])annotationParamValue;
/*     */         } else {
/*     */           
/* 152 */           int n = Array.getLength(annotationParamValue);
/* 153 */           this.objectArrayValue = new ObjectTypedValueWrapper[n];
/* 154 */           for (int i = 0; i < n; i++) {
/* 155 */             this.objectArrayValue[i] = new ObjectTypedValueWrapper(Array.get(annotationParamValue, i));
/*     */           }
/*     */         } 
/* 158 */       } else if (annotationParamValue instanceof AnnotationEnumValue) {
/* 159 */         this.annotationEnumValue = (AnnotationEnumValue)annotationParamValue;
/* 160 */       } else if (annotationParamValue instanceof AnnotationClassRef) {
/* 161 */         this.annotationClassRef = (AnnotationClassRef)annotationParamValue;
/* 162 */       } else if (annotationParamValue instanceof AnnotationInfo) {
/* 163 */         this.annotationInfo = (AnnotationInfo)annotationParamValue;
/* 164 */       } else if (annotationParamValue instanceof String) {
/* 165 */         this.stringValue = (String)annotationParamValue;
/* 166 */       } else if (annotationParamValue instanceof Integer) {
/* 167 */         this.integerValue = (Integer)annotationParamValue;
/* 168 */       } else if (annotationParamValue instanceof Long) {
/* 169 */         this.longValue = (Long)annotationParamValue;
/* 170 */       } else if (annotationParamValue instanceof Short) {
/* 171 */         this.shortValue = (Short)annotationParamValue;
/* 172 */       } else if (annotationParamValue instanceof Boolean) {
/* 173 */         this.booleanValue = (Boolean)annotationParamValue;
/* 174 */       } else if (annotationParamValue instanceof Character) {
/* 175 */         this.characterValue = (Character)annotationParamValue;
/* 176 */       } else if (annotationParamValue instanceof Float) {
/* 177 */         this.floatValue = (Float)annotationParamValue;
/* 178 */       } else if (annotationParamValue instanceof Double) {
/* 179 */         this.doubleValue = (Double)annotationParamValue;
/* 180 */       } else if (annotationParamValue instanceof Byte) {
/* 181 */         this.byteValue = (Byte)annotationParamValue;
/*     */       } else {
/* 183 */         throw new IllegalArgumentException("Unsupported annotation parameter value type: " + annotationParameterValueClass
/* 184 */             .getName());
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
/*     */   
/*     */   Object instantiateOrGet(ClassInfo annotationClassInfo, String paramName) {
/* 201 */     boolean instantiate = (annotationClassInfo != null);
/* 202 */     if (this.annotationEnumValue != null)
/* 203 */       return instantiate ? this.annotationEnumValue.loadClassAndReturnEnumValue() : this.annotationEnumValue; 
/* 204 */     if (this.annotationClassRef != null)
/* 205 */       return instantiate ? this.annotationClassRef.loadClass() : this.annotationClassRef; 
/* 206 */     if (this.annotationInfo != null)
/* 207 */       return instantiate ? this.annotationInfo.loadClassAndInstantiate() : this.annotationInfo; 
/* 208 */     if (this.stringValue != null)
/* 209 */       return this.stringValue; 
/* 210 */     if (this.integerValue != null)
/* 211 */       return this.integerValue; 
/* 212 */     if (this.longValue != null)
/* 213 */       return this.longValue; 
/* 214 */     if (this.shortValue != null)
/* 215 */       return this.shortValue; 
/* 216 */     if (this.booleanValue != null)
/* 217 */       return this.booleanValue; 
/* 218 */     if (this.characterValue != null)
/* 219 */       return this.characterValue; 
/* 220 */     if (this.floatValue != null)
/* 221 */       return this.floatValue; 
/* 222 */     if (this.doubleValue != null)
/* 223 */       return this.doubleValue; 
/* 224 */     if (this.byteValue != null)
/* 225 */       return this.byteValue; 
/* 226 */     if (this.stringArrayValue != null)
/* 227 */       return this.stringArrayValue; 
/* 228 */     if (this.intArrayValue != null)
/* 229 */       return this.intArrayValue; 
/* 230 */     if (this.longArrayValue != null)
/* 231 */       return this.longArrayValue; 
/* 232 */     if (this.shortArrayValue != null)
/* 233 */       return this.shortArrayValue; 
/* 234 */     if (this.booleanArrayValue != null)
/* 235 */       return this.booleanArrayValue; 
/* 236 */     if (this.charArrayValue != null)
/* 237 */       return this.charArrayValue; 
/* 238 */     if (this.floatArrayValue != null)
/* 239 */       return this.floatArrayValue; 
/* 240 */     if (this.doubleArrayValue != null)
/* 241 */       return this.doubleArrayValue; 
/* 242 */     if (this.byteArrayValue != null)
/* 243 */       return this.byteArrayValue; 
/* 244 */     if (this.objectArrayValue != null) {
/*     */ 
/*     */ 
/*     */       
/* 248 */       Class<?> eltClass = instantiate ? (Class)getArrayValueClassOrName(annotationClassInfo, paramName, true) : null;
/*     */ 
/*     */ 
/*     */       
/* 252 */       Object annotationValueObjectArray = (eltClass == null) ? new Object[this.objectArrayValue.length] : Array.newInstance(eltClass, this.objectArrayValue.length);
/*     */       
/* 254 */       for (int i = 0; i < this.objectArrayValue.length; i++) {
/* 255 */         if (this.objectArrayValue[i] != null) {
/*     */           
/* 257 */           Object eltValue = this.objectArrayValue[i].instantiateOrGet(annotationClassInfo, paramName);
/*     */           
/* 259 */           Array.set(annotationValueObjectArray, i, eltValue);
/*     */         } 
/*     */       } 
/* 262 */       return annotationValueObjectArray;
/*     */     } 
/* 264 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get() {
/* 274 */     return instantiateOrGet(null, null);
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
/*     */   private Object getArrayValueClassOrName(ClassInfo annotationClassInfo, String paramName, boolean getClass) {
/* 295 */     MethodInfoList annotationMethodList = (annotationClassInfo == null || annotationClassInfo.methodInfo == null) ? null : annotationClassInfo.methodInfo.get(paramName);
/* 296 */     if (annotationClassInfo != null && annotationMethodList != null && !annotationMethodList.isEmpty()) {
/* 297 */       if (annotationMethodList.size() > 1)
/*     */       {
/* 299 */         throw new IllegalArgumentException("Duplicated annotation parameter method " + paramName + "() in annotation class " + annotationClassInfo
/* 300 */             .getName());
/*     */       }
/*     */ 
/*     */       
/* 304 */       TypeSignature annotationMethodResultTypeSig = annotationMethodList.get(0).getTypeSignatureOrTypeDescriptor().getResultType();
/*     */       
/* 306 */       if (!(annotationMethodResultTypeSig instanceof ArrayTypeSignature)) {
/* 307 */         throw new IllegalArgumentException("Annotation parameter " + paramName + " in annotation class " + annotationClassInfo
/* 308 */             .getName() + " holds an array, but does not have an array type signature");
/*     */       }
/*     */       
/* 311 */       ArrayTypeSignature arrayTypeSig = (ArrayTypeSignature)annotationMethodResultTypeSig;
/* 312 */       if (arrayTypeSig.getNumDimensions() != 1) {
/* 313 */         throw new IllegalArgumentException("Annotations only support 1-dimensional arrays");
/*     */       }
/* 315 */       TypeSignature elementTypeSig = arrayTypeSig.getElementTypeSignature();
/* 316 */       if (elementTypeSig instanceof ClassRefTypeSignature) {
/*     */         
/* 318 */         ClassRefTypeSignature classRefTypeSignature = (ClassRefTypeSignature)elementTypeSig;
/* 319 */         return getClass ? classRefTypeSignature.loadClass() : classRefTypeSignature.getClassName();
/* 320 */       }  if (elementTypeSig instanceof BaseTypeSignature)
/*     */       {
/* 322 */         BaseTypeSignature baseTypeSignature = (BaseTypeSignature)elementTypeSig;
/* 323 */         return getClass ? baseTypeSignature.getType() : baseTypeSignature.getTypeStr();
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 328 */       for (ObjectTypedValueWrapper elt : this.objectArrayValue) {
/* 329 */         if (elt != null)
/*     */         {
/* 331 */           return (elt.integerValue != null) ? (getClass ? Integer.class : "int") : (
/* 332 */             (elt.longValue != null) ? (getClass ? Long.class : "long") : (
/* 333 */             (elt.shortValue != null) ? (getClass ? Short.class : "short") : (
/* 334 */             (elt.characterValue != null) ? (getClass ? Character.class : "char") : (
/* 335 */             (elt.byteValue != null) ? (getClass ? Byte.class : "byte") : (
/* 336 */             (elt.booleanValue != null) ? (
/* 337 */             getClass ? Boolean.class : "boolean") : (
/* 338 */             (elt.doubleValue != null) ? (
/* 339 */             getClass ? Double.class : "double") : (
/* 340 */             (elt.floatValue != null) ? (
/* 341 */             getClass ? Float.class : 
/* 342 */             "float") : (
/* 343 */             getClass ? elt.getClass() : 
/*     */             
/* 345 */             elt.getClass().getName()))))))));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 350 */     return getClass ? Object.class : "java.lang.Object";
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
/*     */   void convertWrapperArraysToPrimitiveArrays(ClassInfo annotationClassInfo, String paramName) {
/* 362 */     if (this.annotationInfo != null) {
/*     */       
/* 364 */       this.annotationInfo.convertWrapperArraysToPrimitiveArrays();
/* 365 */     } else if (this.objectArrayValue != null) {
/* 366 */       int j; for (ObjectTypedValueWrapper elt : this.objectArrayValue) {
/* 367 */         if (elt.annotationInfo != null)
/*     */         {
/* 369 */           elt.annotationInfo.convertWrapperArraysToPrimitiveArrays();
/*     */         }
/*     */       } 
/*     */       
/* 373 */       if (this.objectArrayValue.getClass().getComponentType().isArray()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 379 */       String targetElementTypeName = (String)getArrayValueClassOrName(annotationClassInfo, paramName, false);
/*     */ 
/*     */ 
/*     */       
/* 383 */       switch (targetElementTypeName) {
/*     */         
/*     */         case "java.lang.String":
/* 386 */           this.stringArrayValue = new String[this.objectArrayValue.length];
/* 387 */           for (j = 0; j < this.objectArrayValue.length; j++) {
/* 388 */             this.stringArrayValue[j] = (this.objectArrayValue[j]).stringValue;
/*     */           }
/* 390 */           this.objectArrayValue = null;
/*     */           break;
/*     */         case "int":
/* 393 */           this.intArrayValue = new int[this.objectArrayValue.length];
/* 394 */           for (j = 0; j < this.objectArrayValue.length; j++) {
/* 395 */             ObjectTypedValueWrapper elt = this.objectArrayValue[j];
/* 396 */             if (elt == null) {
/* 397 */               throw new IllegalArgumentException("Illegal null value for array of element type " + targetElementTypeName + " in parameter " + paramName + " of annotation class " + (
/*     */                   
/* 399 */                   (annotationClassInfo == null) ? "<class outside accept>" : 
/* 400 */                   annotationClassInfo.getName()));
/*     */             }
/* 402 */             this.intArrayValue[j] = (this.objectArrayValue[j]).integerValue.intValue();
/*     */           } 
/* 404 */           this.objectArrayValue = null;
/*     */           break;
/*     */         case "long":
/* 407 */           this.longArrayValue = new long[this.objectArrayValue.length];
/* 408 */           for (j = 0; j < this.objectArrayValue.length; j++) {
/* 409 */             ObjectTypedValueWrapper elt = this.objectArrayValue[j];
/* 410 */             if (elt == null) {
/* 411 */               throw new IllegalArgumentException("Illegal null value for array of element type " + targetElementTypeName + " in parameter " + paramName + " of annotation class " + (
/*     */                   
/* 413 */                   (annotationClassInfo == null) ? "<class outside accept>" : 
/* 414 */                   annotationClassInfo.getName()));
/*     */             }
/* 416 */             this.longArrayValue[j] = (this.objectArrayValue[j]).longValue.longValue();
/*     */           } 
/* 418 */           this.objectArrayValue = null;
/*     */           break;
/*     */         case "short":
/* 421 */           this.shortArrayValue = new short[this.objectArrayValue.length];
/* 422 */           for (j = 0; j < this.objectArrayValue.length; j++) {
/* 423 */             ObjectTypedValueWrapper elt = this.objectArrayValue[j];
/* 424 */             if (elt == null) {
/* 425 */               throw new IllegalArgumentException("Illegal null value for array of element type " + targetElementTypeName + " in parameter " + paramName + " of annotation class " + (
/*     */                   
/* 427 */                   (annotationClassInfo == null) ? "<class outside accept>" : 
/* 428 */                   annotationClassInfo.getName()));
/*     */             }
/* 430 */             this.shortArrayValue[j] = (this.objectArrayValue[j]).shortValue.shortValue();
/*     */           } 
/* 432 */           this.objectArrayValue = null;
/*     */           break;
/*     */         case "char":
/* 435 */           this.charArrayValue = new char[this.objectArrayValue.length];
/* 436 */           for (j = 0; j < this.objectArrayValue.length; j++) {
/* 437 */             ObjectTypedValueWrapper elt = this.objectArrayValue[j];
/* 438 */             if (elt == null) {
/* 439 */               throw new IllegalArgumentException("Illegal null value for array of element type " + targetElementTypeName + " in parameter " + paramName + " of annotation class " + (
/*     */                   
/* 441 */                   (annotationClassInfo == null) ? "<class outside accept>" : 
/* 442 */                   annotationClassInfo.getName()));
/*     */             }
/* 444 */             this.charArrayValue[j] = (this.objectArrayValue[j]).characterValue.charValue();
/*     */           } 
/* 446 */           this.objectArrayValue = null;
/*     */           break;
/*     */         case "float":
/* 449 */           this.floatArrayValue = new float[this.objectArrayValue.length];
/* 450 */           for (j = 0; j < this.objectArrayValue.length; j++) {
/* 451 */             ObjectTypedValueWrapper elt = this.objectArrayValue[j];
/* 452 */             if (elt == null) {
/* 453 */               throw new IllegalArgumentException("Illegal null value for array of element type " + targetElementTypeName + " in parameter " + paramName + " of annotation class " + (
/*     */                   
/* 455 */                   (annotationClassInfo == null) ? "<class outside accept>" : 
/* 456 */                   annotationClassInfo.getName()));
/*     */             }
/* 458 */             this.floatArrayValue[j] = (this.objectArrayValue[j]).floatValue.floatValue();
/*     */           } 
/* 460 */           this.objectArrayValue = null;
/*     */           break;
/*     */         case "double":
/* 463 */           this.doubleArrayValue = new double[this.objectArrayValue.length];
/* 464 */           for (j = 0; j < this.objectArrayValue.length; j++) {
/* 465 */             ObjectTypedValueWrapper elt = this.objectArrayValue[j];
/* 466 */             if (elt == null) {
/* 467 */               throw new IllegalArgumentException("Illegal null value for array of element type " + targetElementTypeName + " in parameter " + paramName + " of annotation class " + (
/*     */                   
/* 469 */                   (annotationClassInfo == null) ? "<class outside accept>" : 
/* 470 */                   annotationClassInfo.getName()));
/*     */             }
/* 472 */             this.doubleArrayValue[j] = (this.objectArrayValue[j]).doubleValue.doubleValue();
/*     */           } 
/* 474 */           this.objectArrayValue = null;
/*     */           break;
/*     */         case "boolean":
/* 477 */           this.booleanArrayValue = new boolean[this.objectArrayValue.length];
/* 478 */           for (j = 0; j < this.objectArrayValue.length; j++) {
/* 479 */             ObjectTypedValueWrapper elt = this.objectArrayValue[j];
/* 480 */             if (elt == null) {
/* 481 */               throw new IllegalArgumentException("Illegal null value for array of element type " + targetElementTypeName + " in parameter " + paramName + " of annotation class " + (
/*     */                   
/* 483 */                   (annotationClassInfo == null) ? "<class outside accept>" : 
/* 484 */                   annotationClassInfo.getName()));
/*     */             }
/* 486 */             this.booleanArrayValue[j] = (this.objectArrayValue[j]).booleanValue.booleanValue();
/*     */           } 
/* 488 */           this.objectArrayValue = null;
/*     */           break;
/*     */         case "byte":
/* 491 */           this.byteArrayValue = new byte[this.objectArrayValue.length];
/* 492 */           for (j = 0; j < this.objectArrayValue.length; j++) {
/* 493 */             ObjectTypedValueWrapper elt = this.objectArrayValue[j];
/* 494 */             if (elt == null) {
/* 495 */               throw new IllegalArgumentException("Illegal null value for array of element type " + targetElementTypeName + " in parameter " + paramName + " of annotation class " + (
/*     */                   
/* 497 */                   (annotationClassInfo == null) ? "<class outside accept>" : 
/* 498 */                   annotationClassInfo.getName()));
/*     */             }
/* 500 */             this.byteArrayValue[j] = (this.objectArrayValue[j]).byteValue.byteValue();
/*     */           } 
/* 502 */           this.objectArrayValue = null;
/*     */           break;
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
/*     */   protected String getClassName() {
/* 519 */     throw new IllegalArgumentException("getClassName() cannot be called here");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassInfo getClassInfo() {
/* 527 */     throw new IllegalArgumentException("getClassInfo() cannot be called here");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/* 535 */     super.setScanResult(scanResult);
/* 536 */     if (this.annotationEnumValue != null) {
/* 537 */       this.annotationEnumValue.setScanResult(scanResult);
/* 538 */     } else if (this.annotationClassRef != null) {
/* 539 */       this.annotationClassRef.setScanResult(scanResult);
/* 540 */     } else if (this.annotationInfo != null) {
/* 541 */       this.annotationInfo.setScanResult(scanResult);
/* 542 */     } else if (this.objectArrayValue != null) {
/* 543 */       for (ObjectTypedValueWrapper anObjectArrayValue : this.objectArrayValue) {
/* 544 */         if (anObjectArrayValue != null) {
/* 545 */           anObjectArrayValue.setScanResult(scanResult);
/*     */         }
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
/* 562 */     if (this.annotationEnumValue != null) {
/* 563 */       this.annotationEnumValue.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/* 564 */     } else if (this.annotationClassRef != null) {
/* 565 */       ClassInfo classInfo = this.annotationClassRef.getClassInfo();
/* 566 */       if (classInfo != null) {
/* 567 */         refdClassInfo.add(classInfo);
/*     */       }
/* 569 */     } else if (this.annotationInfo != null) {
/* 570 */       this.annotationInfo.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/* 571 */     } else if (this.objectArrayValue != null) {
/* 572 */       for (ObjectTypedValueWrapper item : this.objectArrayValue) {
/* 573 */         item.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
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
/*     */   public int hashCode() {
/* 585 */     return Objects.hash(new Object[] { this.annotationEnumValue, this.annotationClassRef, this.annotationInfo, this.stringValue, this.integerValue, this.longValue, this.shortValue, this.booleanValue, this.characterValue, this.floatValue, this.doubleValue, this.byteValue, 
/*     */           
/* 587 */           Integer.valueOf(Arrays.hashCode((Object[])this.stringArrayValue)), Integer.valueOf(Arrays.hashCode(this.intArrayValue)), Integer.valueOf(Arrays.hashCode(this.longArrayValue)), 
/* 588 */           Integer.valueOf(Arrays.hashCode(this.shortArrayValue)), Integer.valueOf(Arrays.hashCode(this.booleanArrayValue)), 
/* 589 */           Integer.valueOf(Arrays.hashCode(this.charArrayValue)), Integer.valueOf(Arrays.hashCode(this.floatArrayValue)), 
/* 590 */           Integer.valueOf(Arrays.hashCode(this.doubleArrayValue)), Integer.valueOf(Arrays.hashCode(this.byteArrayValue)), 
/* 591 */           Integer.valueOf(Arrays.hashCode((Object[])this.objectArrayValue)) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 599 */     if (other == this)
/* 600 */       return true; 
/* 601 */     if (!(other instanceof ObjectTypedValueWrapper)) {
/* 602 */       return false;
/*     */     }
/* 604 */     ObjectTypedValueWrapper o = (ObjectTypedValueWrapper)other;
/* 605 */     return (Objects.equals(this.annotationEnumValue, o.annotationEnumValue) && 
/* 606 */       Objects.equals(this.annotationClassRef, o.annotationClassRef) && 
/* 607 */       Objects.equals(this.annotationInfo, o.annotationInfo) && Objects.equals(this.stringValue, o.stringValue) && 
/* 608 */       Objects.equals(this.integerValue, o.integerValue) && Objects.equals(this.longValue, o.longValue) && 
/* 609 */       Objects.equals(this.shortValue, o.shortValue) && Objects.equals(this.booleanValue, o.booleanValue) && 
/* 610 */       Objects.equals(this.characterValue, o.characterValue) && Objects.equals(this.floatValue, o.floatValue) && 
/* 611 */       Objects.equals(this.doubleValue, o.doubleValue) && Objects.equals(this.byteValue, o.byteValue) && 
/* 612 */       Arrays.equals((Object[])this.stringArrayValue, (Object[])o.stringArrayValue) && 
/* 613 */       Arrays.equals(this.intArrayValue, o.intArrayValue) && Arrays.equals(this.longArrayValue, o.longArrayValue) && 
/* 614 */       Arrays.equals(this.shortArrayValue, o.shortArrayValue) && 
/* 615 */       Arrays.equals(this.floatArrayValue, o.floatArrayValue) && 
/* 616 */       Arrays.equals(this.byteArrayValue, o.byteArrayValue) && 
/* 617 */       Arrays.deepEquals((Object[])this.objectArrayValue, (Object[])o.objectArrayValue));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toString(boolean useSimpleNames, StringBuilder buf) {
/* 624 */     if (this.annotationEnumValue != null) {
/* 625 */       this.annotationEnumValue.toString(useSimpleNames, buf);
/* 626 */     } else if (this.annotationClassRef != null) {
/* 627 */       this.annotationClassRef.toString(useSimpleNames, buf);
/* 628 */     } else if (this.annotationInfo != null) {
/* 629 */       this.annotationInfo.toString(useSimpleNames, buf);
/* 630 */     } else if (this.stringValue != null) {
/* 631 */       buf.append(this.stringValue);
/* 632 */     } else if (this.integerValue != null) {
/* 633 */       buf.append(this.integerValue);
/* 634 */     } else if (this.longValue != null) {
/* 635 */       buf.append(this.longValue);
/* 636 */     } else if (this.shortValue != null) {
/* 637 */       buf.append(this.shortValue);
/* 638 */     } else if (this.booleanValue != null) {
/* 639 */       buf.append(this.booleanValue);
/* 640 */     } else if (this.characterValue != null) {
/* 641 */       buf.append(this.characterValue);
/* 642 */     } else if (this.floatValue != null) {
/* 643 */       buf.append(this.floatValue);
/* 644 */     } else if (this.doubleValue != null) {
/* 645 */       buf.append(this.doubleValue);
/* 646 */     } else if (this.byteValue != null) {
/* 647 */       buf.append(this.byteValue);
/* 648 */     } else if (this.stringArrayValue != null) {
/* 649 */       buf.append(Arrays.toString((Object[])this.stringArrayValue));
/* 650 */     } else if (this.intArrayValue != null) {
/* 651 */       buf.append(Arrays.toString(this.intArrayValue));
/* 652 */     } else if (this.longArrayValue != null) {
/* 653 */       buf.append(Arrays.toString(this.longArrayValue));
/* 654 */     } else if (this.shortArrayValue != null) {
/* 655 */       buf.append(Arrays.toString(this.shortArrayValue));
/* 656 */     } else if (this.booleanArrayValue != null) {
/* 657 */       buf.append(Arrays.toString(this.booleanArrayValue));
/* 658 */     } else if (this.charArrayValue != null) {
/* 659 */       buf.append(Arrays.toString(this.charArrayValue));
/* 660 */     } else if (this.floatArrayValue != null) {
/* 661 */       buf.append(Arrays.toString(this.floatArrayValue));
/* 662 */     } else if (this.doubleArrayValue != null) {
/* 663 */       buf.append(Arrays.toString(this.doubleArrayValue));
/* 664 */     } else if (this.byteArrayValue != null) {
/* 665 */       buf.append(Arrays.toString(this.byteArrayValue));
/* 666 */     } else if (this.objectArrayValue != null) {
/*     */       
/* 668 */       buf.append(Arrays.toString((Object[])this.objectArrayValue));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ObjectTypedValueWrapper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */