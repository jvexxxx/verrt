/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.lang.annotation.IncompleteAnnotationException;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
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
/*     */ public class AnnotationInfo
/*     */   extends ScanResultObject
/*     */   implements Comparable<AnnotationInfo>, HasName
/*     */ {
/*     */   private String name;
/*     */   private AnnotationParameterValueList annotationParamValues;
/*     */   private transient boolean annotationParamValuesHasBeenConvertedToPrimitive;
/*     */   private transient AnnotationParameterValueList annotationParamValuesWithDefaults;
/*     */   
/*     */   AnnotationInfo() {}
/*     */   
/*     */   AnnotationInfo(String name, AnnotationParameterValueList annotationParamValues) {
/*  80 */     this.name = name;
/*  81 */     this.annotationParamValues = annotationParamValues;
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
/*     */   public String getName() {
/*  93 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInherited() {
/* 102 */     return (getClassInfo()).isInherited;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationParameterValueList getDefaultParameterValues() {
/* 111 */     return getClassInfo().getAnnotationDefaultParameterValues();
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
/*     */   public AnnotationParameterValueList getParameterValues(boolean includeDefaultValues) {
/* 123 */     ClassInfo classInfo = getClassInfo();
/* 124 */     if (classInfo == null)
/*     */     {
/*     */       
/* 127 */       return (this.annotationParamValues == null) ? AnnotationParameterValueList.EMPTY_LIST : this.annotationParamValues;
/*     */     }
/*     */     
/* 130 */     if (this.annotationParamValues != null && !this.annotationParamValuesHasBeenConvertedToPrimitive) {
/* 131 */       this.annotationParamValues.convertWrapperArraysToPrimitiveArrays(classInfo);
/* 132 */       this.annotationParamValuesHasBeenConvertedToPrimitive = true;
/*     */     } 
/* 134 */     if (!includeDefaultValues)
/*     */     {
/* 136 */       return (this.annotationParamValues == null) ? AnnotationParameterValueList.EMPTY_LIST : this.annotationParamValues;
/*     */     }
/* 138 */     if (this.annotationParamValuesWithDefaults == null) {
/* 139 */       if (classInfo.annotationDefaultParamValues != null && !classInfo.annotationDefaultParamValuesHasBeenConvertedToPrimitive) {
/*     */         
/* 141 */         classInfo.annotationDefaultParamValues.convertWrapperArraysToPrimitiveArrays(classInfo);
/* 142 */         classInfo.annotationDefaultParamValuesHasBeenConvertedToPrimitive = true;
/*     */       } 
/*     */ 
/*     */       
/* 146 */       AnnotationParameterValueList defaultParamValues = classInfo.annotationDefaultParamValues;
/* 147 */       if (defaultParamValues == null && this.annotationParamValues == null)
/* 148 */         return AnnotationParameterValueList.EMPTY_LIST; 
/* 149 */       if (defaultParamValues == null)
/* 150 */         return this.annotationParamValues; 
/* 151 */       if (this.annotationParamValues == null) {
/* 152 */         return defaultParamValues;
/*     */       }
/*     */ 
/*     */       
/* 156 */       Map<String, Object> allParamValues = new HashMap<>();
/* 157 */       for (AnnotationParameterValue defaultParamValue : defaultParamValues) {
/* 158 */         allParamValues.put(defaultParamValue.getName(), defaultParamValue.getValue());
/*     */       }
/* 160 */       for (AnnotationParameterValue annotationParamValue : this.annotationParamValues) {
/* 161 */         allParamValues.put(annotationParamValue.getName(), annotationParamValue.getValue());
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 166 */       if (classInfo.methodInfo == null)
/*     */       {
/*     */         
/* 169 */         throw new IllegalArgumentException("Could not find methods for annotation " + classInfo.getName());
/*     */       }
/* 171 */       this.annotationParamValuesWithDefaults = new AnnotationParameterValueList();
/* 172 */       for (MethodInfo mi : classInfo.methodInfo) {
/* 173 */         String paramName = mi.getName();
/* 174 */         switch (paramName) {
/*     */           case "<init>":
/*     */           case "<clinit>":
/*     */           case "hashCode":
/*     */           case "equals":
/*     */           case "toString":
/*     */           case "annotationType":
/*     */             continue;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 188 */         Object paramValue = allParamValues.get(paramName);
/*     */         
/* 190 */         if (paramValue != null) {
/* 191 */           this.annotationParamValuesWithDefaults.add(new AnnotationParameterValue(paramName, paramValue));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 197 */     return this.annotationParamValuesWithDefaults;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationParameterValueList getParameterValues() {
/* 207 */     return getParameterValues(true);
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
/*     */   protected String getClassName() {
/* 219 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/* 227 */     super.setScanResult(scanResult);
/* 228 */     if (this.annotationParamValues != null) {
/* 229 */       for (AnnotationParameterValue a : this.annotationParamValues) {
/* 230 */         a.setScanResult(scanResult);
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
/* 246 */     super.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/* 247 */     if (this.annotationParamValues != null) {
/* 248 */       for (AnnotationParameterValue annotationParamValue : this.annotationParamValues) {
/* 249 */         annotationParamValue.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfo getClassInfo() {
/* 259 */     return super.getClassInfo();
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
/*     */   public Annotation loadClassAndInstantiate() {
/* 289 */     Class<? extends Annotation> annotationClass = getClassInfo().loadClass(Annotation.class);
/* 290 */     return (Annotation)Proxy.newProxyInstance(annotationClass.getClassLoader(), new Class[] { annotationClass }, new AnnotationInvocationHandler(annotationClass, this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class AnnotationInvocationHandler
/*     */     implements InvocationHandler
/*     */   {
/*     */     private final Class<? extends Annotation> annotationClass;
/*     */ 
/*     */     
/*     */     private final AnnotationInfo annotationInfo;
/*     */ 
/*     */     
/* 304 */     private final Map<String, Object> annotationParameterValuesInstantiated = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     AnnotationInvocationHandler(Class<? extends Annotation> annotationClass, AnnotationInfo annotationInfo) {
/* 316 */       this.annotationClass = annotationClass;
/* 317 */       this.annotationInfo = annotationInfo;
/*     */ 
/*     */ 
/*     */       
/* 321 */       for (AnnotationParameterValue apv : annotationInfo.getParameterValues()) {
/* 322 */         Object instantiatedValue = apv.instantiate(annotationInfo.getClassInfo());
/* 323 */         if (instantiatedValue == null)
/*     */         {
/* 325 */           throw new IllegalArgumentException("Got null value for annotation parameter " + apv.getName() + " of annotation " + annotationInfo
/* 326 */               .name);
/*     */         }
/* 328 */         this.annotationParameterValuesInstantiated.put(apv.getName(), instantiatedValue);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object invoke(Object proxy, Method method, Object[] args) {
/* 338 */       String methodName = method.getName();
/* 339 */       Class<?>[] paramTypes = method.getParameterTypes();
/* 340 */       if (((args == null) ? 0 : args.length) != paramTypes.length) {
/* 341 */         throw new IllegalArgumentException("Wrong number of arguments for " + this.annotationClass
/* 342 */             .getName() + "." + methodName + ": got " + (
/* 343 */             (args == null) ? 0 : args.length) + ", expected " + paramTypes.length);
/*     */       }
/* 345 */       if (args != null && paramTypes.length == 1) {
/* 346 */         if ("equals".equals(methodName) && paramTypes[0] == Object.class) {
/*     */ 
/*     */           
/* 349 */           if (this == args[0])
/* 350 */             return Boolean.valueOf(true); 
/* 351 */           if (!this.annotationClass.isInstance(args[0])) {
/* 352 */             return Boolean.valueOf(false);
/*     */           }
/*     */ 
/*     */           
/* 356 */           ReflectionUtils reflectionUtils = (this.annotationInfo.scanResult == null) ? new ReflectionUtils() : this.annotationInfo.scanResult.reflectionUtils;
/* 357 */           for (Map.Entry<String, Object> ent : this.annotationParameterValuesInstantiated.entrySet()) {
/* 358 */             String paramName = ent.getKey();
/* 359 */             Object paramVal = ent.getValue();
/* 360 */             Object otherParamVal = reflectionUtils.invokeMethod(false, args[0], paramName);
/*     */             
/* 362 */             if (((paramVal == null) ? true : false) != ((otherParamVal == null) ? true : false))
/*     */             {
/* 364 */               return Boolean.valueOf(false); } 
/* 365 */             if (paramVal == null && otherParamVal == null)
/* 366 */               return Boolean.valueOf(true); 
/* 367 */             if (paramVal == null || !paramVal.equals(otherParamVal)) {
/* 368 */               return Boolean.valueOf(false);
/*     */             }
/*     */           } 
/* 371 */           return Boolean.valueOf(true);
/*     */         } 
/*     */         
/* 374 */         throw new IllegalArgumentException();
/*     */       } 
/* 376 */       if (paramTypes.length == 0) {
/*     */         int result;
/* 378 */         switch (methodName) {
/*     */           case "toString":
/* 380 */             return this.annotationInfo.toString();
/*     */ 
/*     */           
/*     */           case "hashCode":
/* 384 */             result = 0;
/* 385 */             for (Map.Entry<String, Object> ent : this.annotationParameterValuesInstantiated.entrySet()) {
/* 386 */               int paramValHashCode; String paramName = ent.getKey();
/* 387 */               Object paramVal = ent.getValue();
/*     */               
/* 389 */               if (paramVal == null) {
/*     */                 
/* 391 */                 paramValHashCode = 0;
/*     */               } else {
/* 393 */                 Class<?> type = paramVal.getClass();
/* 394 */                 if (!type.isArray()) {
/* 395 */                   paramValHashCode = paramVal.hashCode();
/* 396 */                 } else if (type == byte[].class) {
/* 397 */                   paramValHashCode = Arrays.hashCode((byte[])paramVal);
/* 398 */                 } else if (type == char[].class) {
/* 399 */                   paramValHashCode = Arrays.hashCode((char[])paramVal);
/* 400 */                 } else if (type == double[].class) {
/* 401 */                   paramValHashCode = Arrays.hashCode((double[])paramVal);
/* 402 */                 } else if (type == float[].class) {
/* 403 */                   paramValHashCode = Arrays.hashCode((float[])paramVal);
/* 404 */                 } else if (type == int[].class) {
/* 405 */                   paramValHashCode = Arrays.hashCode((int[])paramVal);
/* 406 */                 } else if (type == long[].class) {
/* 407 */                   paramValHashCode = Arrays.hashCode((long[])paramVal);
/* 408 */                 } else if (type == short[].class) {
/* 409 */                   paramValHashCode = Arrays.hashCode((short[])paramVal);
/* 410 */                 } else if (type == boolean[].class) {
/* 411 */                   paramValHashCode = Arrays.hashCode((boolean[])paramVal);
/*     */                 } else {
/* 413 */                   paramValHashCode = Arrays.hashCode((Object[])paramVal);
/*     */                 } 
/*     */               } 
/* 416 */               result += 127 * paramName.hashCode() ^ paramValHashCode;
/*     */             } 
/* 418 */             return Integer.valueOf(result);
/*     */           
/*     */           case "annotationType":
/* 421 */             return this.annotationClass;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       } else {
/* 428 */         throw new IllegalArgumentException();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 433 */       Object annotationParameterValue = this.annotationParameterValuesInstantiated.get(methodName);
/* 434 */       if (annotationParameterValue == null)
/*     */       {
/* 436 */         throw new IncompleteAnnotationException(this.annotationClass, methodName);
/*     */       }
/*     */ 
/*     */       
/* 440 */       Class<?> annotationParameterValueClass = annotationParameterValue.getClass();
/* 441 */       if (annotationParameterValueClass.isArray()) {
/*     */         
/* 443 */         if (annotationParameterValueClass == String[].class)
/* 444 */           return ((String[])annotationParameterValue).clone(); 
/* 445 */         if (annotationParameterValueClass == byte[].class)
/* 446 */           return ((byte[])annotationParameterValue).clone(); 
/* 447 */         if (annotationParameterValueClass == char[].class)
/* 448 */           return ((char[])annotationParameterValue).clone(); 
/* 449 */         if (annotationParameterValueClass == double[].class)
/* 450 */           return ((double[])annotationParameterValue).clone(); 
/* 451 */         if (annotationParameterValueClass == float[].class)
/* 452 */           return ((float[])annotationParameterValue).clone(); 
/* 453 */         if (annotationParameterValueClass == int[].class)
/* 454 */           return ((int[])annotationParameterValue).clone(); 
/* 455 */         if (annotationParameterValueClass == long[].class)
/* 456 */           return ((long[])annotationParameterValue).clone(); 
/* 457 */         if (annotationParameterValueClass == short[].class)
/* 458 */           return ((short[])annotationParameterValue).clone(); 
/* 459 */         if (annotationParameterValueClass == boolean[].class) {
/* 460 */           return ((boolean[])annotationParameterValue).clone();
/*     */         }
/*     */         
/* 463 */         Object[] arr = (Object[])annotationParameterValue;
/* 464 */         return arr.clone();
/*     */       } 
/*     */       
/* 467 */       return annotationParameterValue;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void convertWrapperArraysToPrimitiveArrays() {
/* 475 */     if (this.annotationParamValues != null) {
/* 476 */       this.annotationParamValues.convertWrapperArraysToPrimitiveArrays(getClassInfo());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(AnnotationInfo o) {
/* 487 */     int diff = this.name.compareTo(o.name);
/* 488 */     if (diff != 0) {
/* 489 */       return diff;
/*     */     }
/* 491 */     if (this.annotationParamValues == null && o.annotationParamValues == null)
/* 492 */       return 0; 
/* 493 */     if (this.annotationParamValues == null)
/* 494 */       return -1; 
/* 495 */     if (o.annotationParamValues == null) {
/* 496 */       return 1;
/*     */     }
/* 498 */     int i = 0;
/* 499 */     for (int max = Math.max(this.annotationParamValues.size(), o.annotationParamValues.size()); i < max; i++) {
/* 500 */       if (i >= this.annotationParamValues.size())
/* 501 */         return -1; 
/* 502 */       if (i >= o.annotationParamValues.size()) {
/* 503 */         return 1;
/*     */       }
/* 505 */       int diff2 = this.annotationParamValues.get(i).compareTo(o.annotationParamValues.get(i));
/* 506 */       if (diff2 != 0) {
/* 507 */         return diff2;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 512 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 520 */     if (obj == this)
/* 521 */       return true; 
/* 522 */     if (!(obj instanceof AnnotationInfo)) {
/* 523 */       return false;
/*     */     }
/* 525 */     AnnotationInfo other = (AnnotationInfo)obj;
/* 526 */     return (compareTo(other) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 534 */     int h = this.name.hashCode();
/* 535 */     if (this.annotationParamValues != null) {
/* 536 */       for (AnnotationParameterValue e : this.annotationParamValues) {
/* 537 */         h = h * 7 + e.getName().hashCode() * 3 + e.getValue().hashCode();
/*     */       }
/*     */     }
/* 540 */     return h;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void toString(boolean useSimpleNames, StringBuilder buf) {
/* 545 */     buf.append('@').append(useSimpleNames ? ClassInfo.getSimpleName(this.name) : this.name);
/* 546 */     AnnotationParameterValueList paramVals = getParameterValues();
/* 547 */     if (!paramVals.isEmpty()) {
/* 548 */       buf.append('(');
/* 549 */       for (int i = 0; i < paramVals.size(); i++) {
/* 550 */         if (i > 0) {
/* 551 */           buf.append(", ");
/*     */         }
/* 553 */         AnnotationParameterValue paramVal = paramVals.get(i);
/* 554 */         if (paramVals.size() > 1 || !"value".equals(paramVal.getName())) {
/* 555 */           paramVal.toString(useSimpleNames, buf);
/*     */         } else {
/* 557 */           paramVal.toStringParamValueOnly(useSimpleNames, buf);
/*     */         } 
/*     */       } 
/* 560 */       buf.append(')');
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\AnnotationInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */