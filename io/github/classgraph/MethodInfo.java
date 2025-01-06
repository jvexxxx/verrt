/*      */ package io.github.classgraph;
/*      */ 
/*      */ import java.lang.annotation.Annotation;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.Method;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import nonapi.io.github.classgraph.types.ParseException;
/*      */ import nonapi.io.github.classgraph.types.TypeUtils;
/*      */ import nonapi.io.github.classgraph.utils.Assert;
/*      */ import nonapi.io.github.classgraph.utils.LogNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MethodInfo
/*      */   extends ClassMemberInfo
/*      */   implements Comparable<MethodInfo>
/*      */ {
/*      */   private transient MethodTypeSignature typeDescriptor;
/*      */   private transient MethodTypeSignature typeSignature;
/*      */   private String[] parameterNames;
/*      */   private int[] parameterModifiers;
/*      */   AnnotationInfo[][] parameterAnnotationInfo;
/*      */   private transient MethodParameterInfo[] parameterInfo;
/*      */   private boolean hasBody;
/*      */   private int minLineNum;
/*      */   private int maxLineNum;
/*      */   private transient List<Classfile.MethodTypeAnnotationDecorator> typeAnnotationDecorators;
/*      */   private String[] thrownExceptionNames;
/*      */   private transient ClassInfoList thrownExceptions;
/*      */   
/*      */   MethodInfo() {}
/*      */   
/*      */   MethodInfo(String definingClassName, String methodName, AnnotationInfoList methodAnnotationInfo, int modifiers, String typeDescriptorStr, String typeSignatureStr, String[] parameterNames, int[] parameterModifiers, AnnotationInfo[][] parameterAnnotationInfo, boolean hasBody, int minLineNum, int maxLineNum, List<Classfile.MethodTypeAnnotationDecorator> methodTypeAnnotationDecorators, String[] thrownExceptionNames) {
/*  140 */     super(definingClassName, methodName, modifiers, typeDescriptorStr, typeSignatureStr, methodAnnotationInfo);
/*  141 */     this.parameterNames = parameterNames;
/*  142 */     this.parameterModifiers = parameterModifiers;
/*  143 */     this.parameterAnnotationInfo = parameterAnnotationInfo;
/*  144 */     this.hasBody = hasBody;
/*  145 */     this.minLineNum = minLineNum;
/*  146 */     this.maxLineNum = maxLineNum;
/*  147 */     this.typeAnnotationDecorators = methodTypeAnnotationDecorators;
/*  148 */     this.thrownExceptionNames = thrownExceptionNames;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getName() {
/*  161 */     return this.name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getModifiersStr() {
/*  172 */     StringBuilder buf = new StringBuilder();
/*  173 */     TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.METHOD, isDefault(), buf);
/*  174 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodTypeSignature getTypeDescriptor() {
/*  185 */     if (this.typeDescriptor == null) {
/*      */       try {
/*  187 */         this.typeDescriptor = MethodTypeSignature.parse(this.typeDescriptorStr, this.declaringClassName);
/*  188 */         this.typeDescriptor.setScanResult(this.scanResult);
/*  189 */         if (this.typeAnnotationDecorators != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  194 */           int sigNumParam = 0;
/*  195 */           MethodTypeSignature sig = getTypeSignature();
/*  196 */           if (sig == null) {
/*      */             
/*  198 */             for (Classfile.MethodTypeAnnotationDecorator decorator : this.typeAnnotationDecorators) {
/*  199 */               decorator.decorate(this.typeDescriptor);
/*      */             }
/*      */           } else {
/*      */             
/*  203 */             sigNumParam = sig.getParameterTypeSignatures().size();
/*  204 */             int descNumParam = this.typeDescriptor.getParameterTypeSignatures().size();
/*  205 */             int numImplicitPrefixParams = descNumParam - sigNumParam;
/*  206 */             if (numImplicitPrefixParams < 0)
/*      */             {
/*  208 */               throw new IllegalArgumentException("Fewer params in method type descriptor than in method type signature");
/*      */             }
/*  210 */             if (numImplicitPrefixParams == 0) {
/*      */               
/*  212 */               for (Classfile.MethodTypeAnnotationDecorator decorator : this.typeAnnotationDecorators) {
/*  213 */                 decorator.decorate(this.typeDescriptor);
/*      */               }
/*      */             }
/*      */             else {
/*      */               
/*  218 */               List<TypeSignature> paramSigs = this.typeDescriptor.getParameterTypeSignatures();
/*  219 */               List<TypeSignature> strippedParamSigs = paramSigs.subList(0, numImplicitPrefixParams);
/*      */               int i;
/*  221 */               for (i = 0; i < numImplicitPrefixParams; i++) {
/*  222 */                 paramSigs.remove(0);
/*      */               }
/*  224 */               for (Classfile.MethodTypeAnnotationDecorator decorator : this.typeAnnotationDecorators) {
/*  225 */                 decorator.decorate(this.typeDescriptor);
/*      */               }
/*  227 */               for (i = numImplicitPrefixParams - 1; i >= 0; i--) {
/*  228 */                 paramSigs.add(0, strippedParamSigs.get(i));
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*  233 */       } catch (ParseException e) {
/*  234 */         throw new IllegalArgumentException(e);
/*      */       } 
/*      */     }
/*  237 */     return this.typeDescriptor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodTypeSignature getTypeSignature() {
/*  253 */     if (this.typeSignature == null && this.typeSignatureStr != null) {
/*      */       try {
/*  255 */         this.typeSignature = MethodTypeSignature.parse(this.typeSignatureStr, this.declaringClassName);
/*  256 */         this.typeSignature.setScanResult(this.scanResult);
/*  257 */         if (this.typeAnnotationDecorators != null) {
/*  258 */           for (Classfile.MethodTypeAnnotationDecorator decorator : this.typeAnnotationDecorators) {
/*  259 */             decorator.decorate(this.typeSignature);
/*      */           }
/*      */         }
/*  262 */       } catch (ParseException e) {
/*  263 */         throw new IllegalArgumentException("Invalid type signature for method " + 
/*  264 */             getClassName() + "." + getName() + (
/*  265 */             (getClassInfo() != null) ? (
/*  266 */             " in classpath element " + getClassInfo().getClasspathElementURI()) : 
/*  267 */             "") + " : " + this.typeSignatureStr, e);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  272 */     return this.typeSignature;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodTypeSignature getTypeSignatureOrTypeDescriptor() {
/*  285 */     MethodTypeSignature typeSig = null;
/*      */     try {
/*  287 */       typeSig = getTypeSignature();
/*  288 */       if (typeSig != null) {
/*  289 */         return typeSig;
/*      */       }
/*  291 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/*  294 */     return getTypeDescriptor();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassInfoList getThrownExceptions() {
/*  303 */     if (this.thrownExceptions == null && this.thrownExceptionNames != null) {
/*  304 */       this.thrownExceptions = new ClassInfoList(this.thrownExceptionNames.length);
/*  305 */       for (String thrownExceptionName : this.thrownExceptionNames) {
/*  306 */         ClassInfo classInfo = this.scanResult.getClassInfo(thrownExceptionName);
/*  307 */         if (classInfo != null) {
/*  308 */           this.thrownExceptions.add(classInfo);
/*  309 */           classInfo.setScanResult(this.scanResult);
/*      */         } 
/*      */       } 
/*      */     } 
/*  313 */     return (this.thrownExceptions == null) ? ClassInfoList.EMPTY_LIST : this.thrownExceptions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getThrownExceptionNames() {
/*  322 */     return (this.thrownExceptionNames == null) ? new String[0] : this.thrownExceptionNames;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isConstructor() {
/*  335 */     return "<init>".equals(this.name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSynchronized() {
/*  344 */     return Modifier.isSynchronized(this.modifiers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isBridge() {
/*  353 */     return ((this.modifiers & 0x40) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isVarArgs() {
/*  362 */     return ((this.modifiers & 0x80) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNative() {
/*  371 */     return Modifier.isNative(this.modifiers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAbstract() {
/*  380 */     return Modifier.isAbstract(this.modifiers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStrict() {
/*  389 */     return Modifier.isStrict(this.modifiers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasBody() {
/*  398 */     return this.hasBody;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMinLineNum() {
/*  407 */     return this.minLineNum;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxLineNum() {
/*  416 */     return this.maxLineNum;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isDefault() {
/*  426 */     ClassInfo classInfo = getClassInfo();
/*  427 */     return (classInfo != null && classInfo.isInterface() && this.hasBody);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodParameterInfo[] getParameterInfo() {
/*  455 */     if (this.parameterInfo == null) {
/*      */       
/*  457 */       List<TypeSignature> paramTypeSignatures = null;
/*  458 */       MethodTypeSignature typeSig = getTypeSignature();
/*  459 */       if (typeSig != null) {
/*  460 */         paramTypeSignatures = typeSig.getParameterTypeSignatures();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  468 */       List<TypeSignature> paramTypeDescriptors = null;
/*      */       try {
/*  470 */         MethodTypeSignature typeDesc = getTypeDescriptor();
/*  471 */         if (typeDesc != null) {
/*  472 */           paramTypeDescriptors = typeDesc.getParameterTypeSignatures();
/*      */         }
/*  474 */       } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  481 */       int numParams = (paramTypeSignatures == null) ? 0 : paramTypeSignatures.size();
/*  482 */       if (paramTypeDescriptors != null && paramTypeDescriptors.size() > numParams) {
/*  483 */         numParams = paramTypeDescriptors.size();
/*      */       }
/*  485 */       if (this.parameterNames != null && this.parameterNames.length > numParams) {
/*  486 */         numParams = this.parameterNames.length;
/*      */       }
/*  488 */       if (this.parameterModifiers != null && this.parameterModifiers.length > numParams) {
/*  489 */         numParams = this.parameterModifiers.length;
/*      */       }
/*  491 */       if (this.parameterAnnotationInfo != null && this.parameterAnnotationInfo.length > numParams) {
/*  492 */         numParams = this.parameterAnnotationInfo.length;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  498 */       String[] paramNamesAligned = null;
/*  499 */       if (this.parameterNames != null && this.parameterNames.length > 0) {
/*  500 */         if (this.parameterNames.length == numParams) {
/*      */           
/*  502 */           paramNamesAligned = this.parameterNames;
/*      */         } else {
/*      */           
/*  505 */           paramNamesAligned = new String[numParams];
/*  506 */           for (int j = 0, lenDiff = numParams - this.parameterNames.length; j < this.parameterNames.length; j++) {
/*  507 */             paramNamesAligned[lenDiff + j] = this.parameterNames[j];
/*      */           }
/*      */         } 
/*      */       }
/*  511 */       int[] paramModifiersAligned = null;
/*  512 */       if (this.parameterModifiers != null && this.parameterModifiers.length > 0) {
/*  513 */         if (this.parameterModifiers.length == numParams) {
/*      */           
/*  515 */           paramModifiersAligned = this.parameterModifiers;
/*      */         } else {
/*      */           
/*  518 */           paramModifiersAligned = new int[numParams];
/*  519 */           int j = 0;
/*  520 */           for (int lenDiff = numParams - this.parameterModifiers.length; j < this.parameterModifiers.length; j++) {
/*  521 */             paramModifiersAligned[lenDiff + j] = this.parameterModifiers[j];
/*      */           }
/*      */         } 
/*      */       }
/*  525 */       AnnotationInfo[][] paramAnnotationInfoAligned = null;
/*  526 */       if (this.parameterAnnotationInfo != null && this.parameterAnnotationInfo.length > 0) {
/*  527 */         if (this.parameterAnnotationInfo.length == numParams) {
/*      */           
/*  529 */           paramAnnotationInfoAligned = this.parameterAnnotationInfo;
/*      */         } else {
/*      */           
/*  532 */           paramAnnotationInfoAligned = new AnnotationInfo[numParams][];
/*  533 */           int j = 0, lenDiff = numParams - this.parameterAnnotationInfo.length;
/*  534 */           for (; j < this.parameterAnnotationInfo.length; j++) {
/*  535 */             paramAnnotationInfoAligned[lenDiff + j] = this.parameterAnnotationInfo[j];
/*      */           }
/*      */         } 
/*      */       }
/*  539 */       List<TypeSignature> paramTypeSignaturesAligned = null;
/*  540 */       if (paramTypeSignatures != null && paramTypeSignatures.size() > 0) {
/*  541 */         if (paramTypeSignatures.size() == numParams) {
/*      */           
/*  543 */           paramTypeSignaturesAligned = paramTypeSignatures;
/*      */         } else {
/*      */           
/*  546 */           paramTypeSignaturesAligned = new ArrayList<>(numParams);
/*  547 */           for (int j = 0, lenDiff = numParams - paramTypeSignatures.size(); j < lenDiff; j++)
/*      */           {
/*  549 */             paramTypeSignaturesAligned.add(null);
/*      */           }
/*  551 */           paramTypeSignaturesAligned.addAll(paramTypeSignatures);
/*      */         } 
/*      */       }
/*  554 */       List<TypeSignature> paramTypeDescriptorsAligned = null;
/*  555 */       if (paramTypeDescriptors != null && paramTypeDescriptors.size() > 0) {
/*  556 */         if (paramTypeDescriptors.size() == numParams) {
/*      */           
/*  558 */           paramTypeDescriptorsAligned = paramTypeDescriptors;
/*      */         } else {
/*      */           
/*  561 */           paramTypeDescriptorsAligned = new ArrayList<>(numParams);
/*  562 */           for (int j = 0, lenDiff = numParams - paramTypeDescriptors.size(); j < lenDiff; j++)
/*      */           {
/*  564 */             paramTypeDescriptorsAligned.add(null);
/*      */           }
/*  566 */           paramTypeDescriptorsAligned.addAll(paramTypeDescriptors);
/*      */         } 
/*      */       }
/*      */ 
/*      */       
/*  571 */       this.parameterInfo = new MethodParameterInfo[numParams];
/*  572 */       for (int i = 0; i < numParams; i++) {
/*  573 */         this.parameterInfo[i] = new MethodParameterInfo(this, 
/*  574 */             (paramAnnotationInfoAligned == null) ? null : paramAnnotationInfoAligned[i], 
/*  575 */             (paramModifiersAligned == null) ? 0 : paramModifiersAligned[i], 
/*  576 */             (paramTypeDescriptorsAligned == null) ? null : paramTypeDescriptorsAligned.get(i), 
/*  577 */             (paramTypeSignaturesAligned == null) ? null : paramTypeSignaturesAligned.get(i), 
/*  578 */             (paramNamesAligned == null) ? null : paramNamesAligned[i]);
/*  579 */         this.parameterInfo[i].setScanResult(this.scanResult);
/*      */       } 
/*      */     } 
/*  582 */     return this.parameterInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasParameterAnnotation(Class<? extends Annotation> annotation) {
/*  595 */     Assert.isAnnotation(annotation);
/*  596 */     return hasParameterAnnotation(annotation.getName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasParameterAnnotation(String annotationName) {
/*  607 */     for (MethodParameterInfo methodParameterInfo : getParameterInfo()) {
/*  608 */       if (methodParameterInfo.hasAnnotation(annotationName)) {
/*  609 */         return true;
/*      */       }
/*      */     } 
/*  612 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Class<?>[] loadParameterClasses() {
/*  623 */     MethodParameterInfo[] allParameterInfo = getParameterInfo();
/*  624 */     List<Class<?>> parameterClasses = new ArrayList<>(allParameterInfo.length);
/*  625 */     for (MethodParameterInfo mpi : allParameterInfo) {
/*  626 */       TypeSignature actualParameterType, parameterType = mpi.getTypeSignatureOrTypeDescriptor();
/*      */       
/*  628 */       if (parameterType instanceof TypeVariableSignature) {
/*  629 */         TypeVariableSignature tvs = (TypeVariableSignature)parameterType;
/*  630 */         TypeParameter t = tvs.resolve();
/*  631 */         if (t.classBound != null) {
/*      */ 
/*      */ 
/*      */           
/*  635 */           actualParameterType = t.classBound;
/*  636 */         } else if (t.interfaceBounds != null && !t.interfaceBounds.isEmpty()) {
/*      */ 
/*      */           
/*  639 */           actualParameterType = t.interfaceBounds.get(0);
/*      */         } else {
/*      */           
/*  642 */           throw new IllegalArgumentException("TypeVariableSignature has no bounds");
/*      */         } 
/*      */       } else {
/*  645 */         actualParameterType = parameterType;
/*      */       } 
/*  647 */       parameterClasses.add(actualParameterType.loadClass());
/*      */     } 
/*  649 */     return (Class[])parameterClasses.<Class<?>[]>toArray((Class<?>[][])new Class[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Method loadClassAndGetMethod() throws IllegalArgumentException {
/*  662 */     if (isConstructor()) {
/*  663 */       throw new IllegalArgumentException("Need to call loadClassAndGetConstructor() for constructors, not loadClassAndGetMethod()");
/*      */     }
/*      */     
/*  666 */     Class<?>[] parameterClassesArr = loadParameterClasses();
/*      */     try {
/*  668 */       return loadClass().getMethod(getName(), parameterClassesArr);
/*  669 */     } catch (NoSuchMethodException e1) {
/*      */       try {
/*  671 */         return loadClass().getDeclaredMethod(getName(), parameterClassesArr);
/*  672 */       } catch (NoSuchMethodException es2) {
/*  673 */         throw new IllegalArgumentException("Method not found: " + getClassName() + "." + getName());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Constructor<?> loadClassAndGetConstructor() throws IllegalArgumentException {
/*  689 */     if (!isConstructor()) {
/*  690 */       throw new IllegalArgumentException("Need to call loadClassAndGetMethod() for non-constructor methods, not loadClassAndGetConstructor()");
/*      */     }
/*      */ 
/*      */     
/*  694 */     Class<?>[] parameterClassesArr = loadParameterClasses();
/*      */     try {
/*  696 */       return loadClass().getConstructor(parameterClassesArr);
/*  697 */     } catch (NoSuchMethodException e1) {
/*      */       try {
/*  699 */         return loadClass().getDeclaredConstructor(parameterClassesArr);
/*  700 */       } catch (NoSuchMethodException es2) {
/*  701 */         throw new IllegalArgumentException("Constructor not found for class " + getClassName());
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void handleRepeatableAnnotations(Set<String> allRepeatableAnnotationNames) {
/*  715 */     if (this.annotationInfo != null) {
/*  716 */       this.annotationInfo.handleRepeatableAnnotations(allRepeatableAnnotationNames, getClassInfo(), ClassInfo.RelType.METHOD_ANNOTATIONS, ClassInfo.RelType.CLASSES_WITH_METHOD_ANNOTATION, ClassInfo.RelType.CLASSES_WITH_NONPRIVATE_METHOD_ANNOTATION);
/*      */     }
/*      */ 
/*      */     
/*  720 */     if (this.parameterAnnotationInfo != null) {
/*  721 */       for (int i = 0; i < this.parameterAnnotationInfo.length; i++) {
/*  722 */         AnnotationInfo[] pai = this.parameterAnnotationInfo[i];
/*  723 */         if (pai != null && pai.length > 0) {
/*  724 */           boolean hasRepeatableAnnotation = false;
/*  725 */           for (AnnotationInfo ai : pai) {
/*  726 */             if (allRepeatableAnnotationNames.contains(ai.getName())) {
/*  727 */               hasRepeatableAnnotation = true;
/*      */               break;
/*      */             } 
/*      */           } 
/*  731 */           if (hasRepeatableAnnotation) {
/*  732 */             AnnotationInfoList aiList = new AnnotationInfoList(pai.length);
/*  733 */             aiList.addAll(Arrays.asList(pai));
/*  734 */             aiList.handleRepeatableAnnotations(allRepeatableAnnotationNames, getClassInfo(), ClassInfo.RelType.METHOD_PARAMETER_ANNOTATIONS, ClassInfo.RelType.CLASSES_WITH_METHOD_PARAMETER_ANNOTATION, ClassInfo.RelType.CLASSES_WITH_NONPRIVATE_METHOD_PARAMETER_ANNOTATION);
/*      */ 
/*      */ 
/*      */             
/*  738 */             this.parameterAnnotationInfo[i] = (AnnotationInfo[])aiList.toArray((Object[])new AnnotationInfo[0]);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setScanResult(ScanResult scanResult) {
/*  752 */     super.setScanResult(scanResult);
/*  753 */     if (this.typeDescriptor != null) {
/*  754 */       this.typeDescriptor.setScanResult(scanResult);
/*      */     }
/*  756 */     if (this.typeSignature != null) {
/*  757 */       this.typeSignature.setScanResult(scanResult);
/*      */     }
/*  759 */     if (this.annotationInfo != null) {
/*  760 */       for (AnnotationInfo ai : this.annotationInfo) {
/*  761 */         ai.setScanResult(scanResult);
/*      */       }
/*      */     }
/*  764 */     if (this.parameterAnnotationInfo != null) {
/*  765 */       for (AnnotationInfo[] pai : this.parameterAnnotationInfo) {
/*  766 */         if (pai != null) {
/*  767 */           for (AnnotationInfo ai : pai) {
/*  768 */             ai.setScanResult(scanResult);
/*      */           }
/*      */         }
/*      */       } 
/*      */     }
/*  773 */     if (this.parameterInfo != null) {
/*  774 */       for (MethodParameterInfo mpi : this.parameterInfo) {
/*  775 */         mpi.setScanResult(scanResult);
/*      */       }
/*      */     }
/*  778 */     if (this.thrownExceptions != null) {
/*  779 */       for (ClassInfo thrownException : this.thrownExceptions) {
/*  780 */         if (thrownException.scanResult == null) {
/*  781 */           thrownException.setScanResult(scanResult);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void findReferencedClassInfo(Map<String, ClassInfo> classNameToClassInfo, Set<ClassInfo> refdClassInfo, LogNode log) {
/*      */     try {
/*  799 */       MethodTypeSignature methodSig = getTypeSignature();
/*  800 */       if (methodSig != null) {
/*  801 */         methodSig.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/*      */       }
/*  803 */     } catch (IllegalArgumentException e) {
/*  804 */       if (log != null) {
/*  805 */         log.log("Illegal type signature for method " + getClassName() + "." + getName() + ": " + 
/*  806 */             getTypeSignatureStr());
/*      */       }
/*      */     } 
/*      */     try {
/*  810 */       MethodTypeSignature methodDesc = getTypeDescriptor();
/*  811 */       if (methodDesc != null) {
/*  812 */         methodDesc.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/*      */       }
/*  814 */     } catch (IllegalArgumentException e) {
/*  815 */       if (log != null) {
/*  816 */         log.log("Illegal type descriptor for method " + getClassName() + "." + getName() + ": " + 
/*  817 */             getTypeDescriptorStr());
/*      */       }
/*      */     } 
/*  820 */     if (this.annotationInfo != null) {
/*  821 */       for (AnnotationInfo ai : this.annotationInfo) {
/*  822 */         ai.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/*      */       }
/*      */     }
/*  825 */     for (MethodParameterInfo mpi : getParameterInfo()) {
/*  826 */       AnnotationInfo[] aiArr = mpi.annotationInfo;
/*  827 */       if (aiArr != null) {
/*  828 */         for (AnnotationInfo ai : aiArr) {
/*  829 */           ai.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/*      */         }
/*      */       }
/*      */     } 
/*  833 */     if (this.thrownExceptionNames != null) {
/*  834 */       ClassInfoList thrownExceptions = getThrownExceptions();
/*  835 */       if (thrownExceptions != null) {
/*  836 */         for (int i = 0; i < thrownExceptions.size(); i++) {
/*  837 */           classNameToClassInfo.put(this.thrownExceptionNames[i], thrownExceptions.get(i));
/*      */         }
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  854 */     if (obj == this)
/*  855 */       return true; 
/*  856 */     if (!(obj instanceof MethodInfo)) {
/*  857 */       return false;
/*      */     }
/*  859 */     MethodInfo other = (MethodInfo)obj;
/*  860 */     return (this.declaringClassName.equals(other.declaringClassName) && this.typeDescriptorStr
/*  861 */       .equals(other.typeDescriptorStr) && this.name.equals(other.name));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  871 */     return this.name.hashCode() + this.typeDescriptorStr.hashCode() * 11 + this.declaringClassName.hashCode() * 57;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int compareTo(MethodInfo other) {
/*  883 */     int diff0 = this.declaringClassName.compareTo(other.declaringClassName);
/*  884 */     if (diff0 != 0) {
/*  885 */       return diff0;
/*      */     }
/*  887 */     int diff1 = this.name.compareTo(other.name);
/*  888 */     if (diff1 != 0) {
/*  889 */       return diff1;
/*      */     }
/*  891 */     return this.typeDescriptorStr.compareTo(other.typeDescriptorStr);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void toString(boolean useSimpleNames, StringBuilder buf) {
/*  907 */     MethodTypeSignature methodType = getTypeSignatureOrTypeDescriptor();
/*      */     
/*  909 */     if (this.annotationInfo != null) {
/*  910 */       for (AnnotationInfo annotation : this.annotationInfo) {
/*  911 */         if (buf.length() > 0) {
/*  912 */           buf.append(' ');
/*      */         }
/*  914 */         annotation.toString(useSimpleNames, buf);
/*      */       } 
/*      */     }
/*      */     
/*  918 */     if (this.modifiers != 0) {
/*  919 */       if (buf.length() > 0) {
/*  920 */         buf.append(' ');
/*      */       }
/*  922 */       TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.METHOD, isDefault(), buf);
/*      */     } 
/*      */     
/*  925 */     List<TypeParameter> typeParameters = methodType.getTypeParameters();
/*  926 */     if (!typeParameters.isEmpty()) {
/*  927 */       if (buf.length() > 0) {
/*  928 */         buf.append(' ');
/*      */       }
/*  930 */       buf.append('<');
/*  931 */       for (int j = 0; j < typeParameters.size(); j++) {
/*  932 */         if (j > 0) {
/*  933 */           buf.append(", ");
/*      */         }
/*  935 */         ((TypeParameter)typeParameters.get(j)).toString(useSimpleNames, buf);
/*      */       } 
/*  937 */       buf.append('>');
/*      */     } 
/*      */     
/*  940 */     if (!isConstructor()) {
/*  941 */       if (buf.length() > 0) {
/*  942 */         buf.append(' ');
/*      */       }
/*  944 */       methodType.getResultType().toStringInternal(useSimpleNames, this.annotationInfo, buf);
/*      */     } 
/*      */ 
/*      */     
/*  948 */     if (buf.length() > 0) {
/*  949 */       buf.append(' ');
/*      */     }
/*  951 */     if (this.name != null) {
/*  952 */       buf.append(useSimpleNames ? ClassInfo.getSimpleName(this.name) : this.name);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  957 */     MethodParameterInfo[] allParamInfo = getParameterInfo();
/*  958 */     boolean hasParamNames = false;
/*  959 */     for (MethodParameterInfo methodParamInfo : allParamInfo) {
/*  960 */       if (methodParamInfo.getName() != null) {
/*  961 */         hasParamNames = true;
/*      */ 
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  969 */     int varArgsParamIndex = -1;
/*  970 */     if (isVarArgs()) {
/*  971 */       for (int j = allParamInfo.length - 1; j >= 0; j--) {
/*  972 */         int mods = allParamInfo[j].getModifiers();
/*  973 */         if ((mods & 0x1000) == 0 && (mods & 0x8000) == 0) {
/*  974 */           TypeSignature paramType = allParamInfo[j].getTypeSignatureOrTypeDescriptor();
/*  975 */           if (paramType instanceof ArrayTypeSignature) {
/*  976 */             varArgsParamIndex = j;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*  983 */     buf.append('('); int i, numParams;
/*  984 */     for (i = 0, numParams = allParamInfo.length; i < numParams; i++) {
/*  985 */       MethodParameterInfo paramInfo = allParamInfo[i];
/*  986 */       if (i > 0) {
/*  987 */         buf.append(", ");
/*      */       }
/*      */       
/*  990 */       if (paramInfo.annotationInfo != null) {
/*  991 */         for (AnnotationInfo ai : paramInfo.annotationInfo) {
/*  992 */           ai.toString(useSimpleNames, buf);
/*  993 */           buf.append(' ');
/*      */         } 
/*      */       }
/*      */       
/*  997 */       MethodParameterInfo.modifiersToString(paramInfo.getModifiers(), buf);
/*      */       
/*  999 */       TypeSignature paramTypeSignature = paramInfo.getTypeSignatureOrTypeDescriptor();
/*      */ 
/*      */       
/* 1002 */       if (paramTypeSignature != null) {
/* 1003 */         if (i == varArgsParamIndex) {
/*      */           
/* 1005 */           if (!(paramTypeSignature instanceof ArrayTypeSignature)) {
/* 1006 */             throw new IllegalArgumentException("Got non-array type for last parameter of varargs method " + this.name);
/*      */           }
/*      */           
/* 1009 */           ArrayTypeSignature arrayType = (ArrayTypeSignature)paramTypeSignature;
/* 1010 */           if (arrayType.getNumDimensions() == 0) {
/* 1011 */             throw new IllegalArgumentException("Got a zero-dimension array type for last parameter of varargs method " + this.name);
/*      */           }
/*      */           
/* 1014 */           arrayType.getElementTypeSignature().toString(useSimpleNames, buf);
/* 1015 */           for (int j = 0; j < arrayType.getNumDimensions() - 1; j++) {
/* 1016 */             buf.append("[]");
/*      */           }
/* 1018 */           buf.append("...");
/*      */         } else {
/*      */           AnnotationInfoList annotationsToExclude;
/*      */ 
/*      */           
/* 1023 */           if (paramInfo.annotationInfo == null || paramInfo.annotationInfo.length == 0) {
/* 1024 */             annotationsToExclude = null;
/*      */           } else {
/* 1026 */             annotationsToExclude = new AnnotationInfoList(paramInfo.annotationInfo.length);
/* 1027 */             annotationsToExclude.addAll(Arrays.asList(paramInfo.annotationInfo));
/*      */           } 
/* 1029 */           paramTypeSignature.toStringInternal(useSimpleNames, annotationsToExclude, buf);
/*      */         } 
/*      */       }
/*      */       
/* 1033 */       if (hasParamNames) {
/* 1034 */         String paramName = paramInfo.getName();
/* 1035 */         if (paramName != null) {
/* 1036 */           if (buf.charAt(buf.length() - 1) != ' ') {
/* 1037 */             buf.append(' ');
/*      */           }
/* 1039 */           buf.append(paramName);
/*      */         } 
/*      */       } 
/*      */     } 
/* 1043 */     buf.append(')');
/*      */ 
/*      */     
/* 1046 */     if (!methodType.getThrowsSignatures().isEmpty()) {
/* 1047 */       buf.append(" throws ");
/* 1048 */       for (i = 0; i < methodType.getThrowsSignatures().size(); i++) {
/* 1049 */         if (i > 0) {
/* 1050 */           buf.append(", ");
/*      */         }
/* 1052 */         ((ClassRefOrTypeVariableSignature)methodType.getThrowsSignatures().get(i)).toString(useSimpleNames, buf);
/*      */       }
/*      */     
/* 1055 */     } else if (this.thrownExceptionNames != null && this.thrownExceptionNames.length > 0) {
/* 1056 */       buf.append(" throws ");
/* 1057 */       for (i = 0; i < this.thrownExceptionNames.length; i++) {
/* 1058 */         if (i > 0) {
/* 1059 */           buf.append(", ");
/*      */         }
/* 1061 */         buf.append(useSimpleNames ? ClassInfo.getSimpleName(this.thrownExceptionNames[i]) : 
/* 1062 */             this.thrownExceptionNames[i]);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\MethodInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */