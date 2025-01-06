/*      */ package io.github.classgraph;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.lang.annotation.Annotation;
/*      */ import java.lang.annotation.Inherited;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.net.URI;
/*      */ import java.net.URL;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.EnumMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.LinkedHashSet;
/*      */ import java.util.LinkedList;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import nonapi.io.github.classgraph.json.Id;
/*      */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*      */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*      */ import nonapi.io.github.classgraph.types.ParseException;
/*      */ import nonapi.io.github.classgraph.types.Parser;
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
/*      */ public class ClassInfo
/*      */   extends ScanResultObject
/*      */   implements Comparable<ClassInfo>, HasName
/*      */ {
/*      */   @Id
/*      */   protected String name;
/*      */   private int modifiers;
/*      */   private boolean isRecord;
/*      */   boolean isInherited;
/*      */   private int classfileMinorVersion;
/*      */   private int classfileMajorVersion;
/*      */   protected String typeSignatureStr;
/*      */   private transient ClassTypeSignature typeSignature;
/*      */   private transient ClassTypeSignature typeDescriptor;
/*      */   private String sourceFile;
/*      */   private String fullyQualifiedDefiningMethodName;
/*      */   protected boolean isExternalClass = true;
/*      */   protected boolean isScannedClass;
/*      */   transient ClasspathElement classpathElement;
/*      */   protected transient Resource classfileResource;
/*      */   transient ClassLoader classLoader;
/*      */   ModuleInfo moduleInfo;
/*      */   PackageInfo packageInfo;
/*      */   AnnotationInfoList annotationInfo;
/*      */   FieldInfoList fieldInfo;
/*      */   MethodInfoList methodInfo;
/*      */   AnnotationParameterValueList annotationDefaultParamValues;
/*      */   transient List<Classfile.ClassTypeAnnotationDecorator> typeAnnotationDecorators;
/*      */   private Set<String> referencedClassNames;
/*      */   private ClassInfoList referencedClasses;
/*      */   transient boolean annotationDefaultParamValuesHasBeenConvertedToPrimitive;
/*      */   private Map<RelType, Set<ClassInfo>> relatedClasses;
/*      */   private transient List<ClassInfo> overrideOrder;
/*      */   private transient List<ClassInfo> methodOverrideOrder;
/*      */   private static final int ANNOTATION_CLASS_MODIFIER = 8192;
/*  188 */   private static final ReachableAndDirectlyRelatedClasses NO_REACHABLE_CLASSES = new ReachableAndDirectlyRelatedClasses(
/*  189 */       Collections.emptySet(), 
/*  190 */       Collections.emptySet());
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
/*      */   protected ClassInfo(String name, int classModifiers, Resource classfileResource) {
/*  212 */     this.name = name;
/*  213 */     if (name.endsWith(";"))
/*      */     {
/*  215 */       throw new IllegalArgumentException("Bad class name");
/*      */     }
/*  217 */     setModifiers(classModifiers);
/*  218 */     this.classfileResource = classfileResource;
/*  219 */     this.relatedClasses = new EnumMap<>(RelType.class);
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
/*      */   enum RelType
/*      */   {
/*  235 */     SUPERCLASSES,
/*      */ 
/*      */     
/*  238 */     SUBCLASSES,
/*      */ 
/*      */     
/*  241 */     CONTAINS_INNER_CLASS,
/*      */ 
/*      */     
/*  244 */     CONTAINED_WITHIN_OUTER_CLASS,
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
/*  255 */     IMPLEMENTED_INTERFACES,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  260 */     CLASSES_IMPLEMENTING,
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  268 */     CLASS_ANNOTATIONS,
/*      */ 
/*      */     
/*  271 */     CLASSES_WITH_ANNOTATION,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  276 */     METHOD_ANNOTATIONS,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  281 */     CLASSES_WITH_METHOD_ANNOTATION,
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  287 */     CLASSES_WITH_NONPRIVATE_METHOD_ANNOTATION,
/*      */ 
/*      */     
/*  290 */     METHOD_PARAMETER_ANNOTATIONS,
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  296 */     CLASSES_WITH_METHOD_PARAMETER_ANNOTATION,
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  302 */     CLASSES_WITH_NONPRIVATE_METHOD_PARAMETER_ANNOTATION,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  307 */     FIELD_ANNOTATIONS,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  312 */     CLASSES_WITH_FIELD_ANNOTATION,
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  318 */     CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION;
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
/*      */   boolean addRelatedClass(RelType relType, ClassInfo classInfo) {
/*  331 */     Set<ClassInfo> classInfoSet = this.relatedClasses.get(relType);
/*  332 */     if (classInfoSet == null) {
/*  333 */       this.relatedClasses.put(relType, classInfoSet = new LinkedHashSet<>(4));
/*      */     }
/*  335 */     return classInfoSet.add(classInfo);
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
/*      */   static ClassInfo getOrCreateClassInfo(String className, Map<String, ClassInfo> classNameToClassInfo) {
/*  353 */     int numArrayDims = 0;
/*  354 */     String baseClassName = className;
/*  355 */     while (baseClassName.endsWith("[]")) {
/*  356 */       numArrayDims++;
/*  357 */       baseClassName = baseClassName.substring(0, baseClassName.length() - 2);
/*      */     } 
/*      */     
/*  360 */     while (baseClassName.startsWith("[")) {
/*  361 */       numArrayDims++;
/*  362 */       baseClassName = baseClassName.substring(1);
/*      */     } 
/*  364 */     if (baseClassName.endsWith(";")) {
/*  365 */       baseClassName = baseClassName.substring(baseClassName.length() - 1);
/*      */     }
/*  367 */     baseClassName = baseClassName.replace('/', '.');
/*      */     
/*  369 */     ClassInfo classInfo = classNameToClassInfo.get(className);
/*  370 */     if (classInfo == null) {
/*  371 */       if (numArrayDims == 0) {
/*  372 */         classInfo = new ClassInfo(baseClassName, 0, null);
/*      */       } else {
/*  374 */         TypeSignature elementTypeSignature; StringBuilder arrayTypeSigStrBuf = new StringBuilder();
/*  375 */         for (int i = 0; i < numArrayDims; i++) {
/*  376 */           arrayTypeSigStrBuf.append('[');
/*      */         }
/*      */         
/*  379 */         char baseTypeChar = BaseTypeSignature.getTypeChar(baseClassName);
/*  380 */         if (baseTypeChar != '\000') {
/*      */           
/*  382 */           arrayTypeSigStrBuf.append(baseTypeChar);
/*  383 */           elementTypeSignature = new BaseTypeSignature(baseTypeChar);
/*      */         } else {
/*      */           
/*  386 */           String eltTypeSigStr = "L" + baseClassName.replace('.', '/') + ";";
/*  387 */           arrayTypeSigStrBuf.append(eltTypeSigStr);
/*      */           try {
/*  389 */             elementTypeSignature = ClassRefTypeSignature.parse(new Parser(eltTypeSigStr), null);
/*      */ 
/*      */             
/*  392 */             if (elementTypeSignature == null) {
/*  393 */               throw new IllegalArgumentException("Could not form array base type signature for class " + baseClassName);
/*      */             }
/*      */           }
/*  396 */           catch (ParseException e) {
/*  397 */             throw new IllegalArgumentException("Could not form array base type signature for class " + baseClassName);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  402 */         classInfo = new ArrayClassInfo(new ArrayTypeSignature(elementTypeSignature, numArrayDims, arrayTypeSigStrBuf.toString()));
/*      */       } 
/*  404 */       classNameToClassInfo.put(className, classInfo);
/*      */     } 
/*  406 */     return classInfo;
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
/*      */   void setClassfileVersion(int minorVersion, int majorVersion) {
/*  418 */     this.classfileMinorVersion = minorVersion;
/*  419 */     this.classfileMajorVersion = majorVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setModifiers(int modifiers) {
/*  429 */     this.modifiers |= modifiers;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setIsInterface(boolean isInterface) {
/*  439 */     if (isInterface) {
/*  440 */       this.modifiers |= 0x200;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setIsAnnotation(boolean isAnnotation) {
/*  451 */     if (isAnnotation) {
/*  452 */       this.modifiers |= 0x2000;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setIsRecord(boolean isRecord) {
/*  463 */     if (isRecord) {
/*  464 */       this.isRecord = isRecord;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSourceFile(String sourceFile) {
/*  475 */     this.sourceFile = sourceFile;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addTypeDecorators(List<Classfile.ClassTypeAnnotationDecorator> classTypeAnnotationDecorators) {
/*  485 */     if (this.typeAnnotationDecorators == null) {
/*  486 */       this.typeAnnotationDecorators = new ArrayList<>();
/*      */     }
/*  488 */     this.typeAnnotationDecorators.addAll(classTypeAnnotationDecorators);
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
/*      */   void addSuperclass(String superclassName, Map<String, ClassInfo> classNameToClassInfo) {
/*  502 */     if (superclassName != null && !superclassName.equals("java.lang.Object")) {
/*  503 */       ClassInfo superclassClassInfo = getOrCreateClassInfo(superclassName, classNameToClassInfo);
/*  504 */       addRelatedClass(RelType.SUPERCLASSES, superclassClassInfo);
/*  505 */       superclassClassInfo.addRelatedClass(RelType.SUBCLASSES, this);
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
/*      */   void addImplementedInterface(String interfaceName, Map<String, ClassInfo> classNameToClassInfo) {
/*  518 */     ClassInfo interfaceClassInfo = getOrCreateClassInfo(interfaceName, classNameToClassInfo);
/*  519 */     interfaceClassInfo.setIsInterface(true);
/*  520 */     addRelatedClass(RelType.IMPLEMENTED_INTERFACES, interfaceClassInfo);
/*  521 */     interfaceClassInfo.addRelatedClass(RelType.CLASSES_IMPLEMENTING, this);
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
/*      */   static void addClassContainment(List<Classfile.ClassContainment> classContainmentEntries, Map<String, ClassInfo> classNameToClassInfo) {
/*  534 */     for (Classfile.ClassContainment classContainment : classContainmentEntries) {
/*  535 */       ClassInfo innerClassInfo = getOrCreateClassInfo(classContainment.innerClassName, classNameToClassInfo);
/*      */       
/*  537 */       innerClassInfo.setModifiers(classContainment.innerClassModifierBits);
/*  538 */       ClassInfo outerClassInfo = getOrCreateClassInfo(classContainment.outerClassName, classNameToClassInfo);
/*      */       
/*  540 */       innerClassInfo.addRelatedClass(RelType.CONTAINED_WITHIN_OUTER_CLASS, outerClassInfo);
/*  541 */       outerClassInfo.addRelatedClass(RelType.CONTAINS_INNER_CLASS, innerClassInfo);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addFullyQualifiedDefiningMethodName(String fullyQualifiedDefiningMethodName) {
/*  552 */     this.fullyQualifiedDefiningMethodName = fullyQualifiedDefiningMethodName;
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
/*      */   void addClassAnnotation(AnnotationInfo classAnnotationInfo, Map<String, ClassInfo> classNameToClassInfo) {
/*  565 */     ClassInfo annotationClassInfo = getOrCreateClassInfo(classAnnotationInfo.getName(), classNameToClassInfo);
/*      */     
/*  567 */     annotationClassInfo.setModifiers(8192);
/*  568 */     if (this.annotationInfo == null) {
/*  569 */       this.annotationInfo = new AnnotationInfoList(2);
/*      */     }
/*  571 */     this.annotationInfo.add(classAnnotationInfo);
/*      */     
/*  573 */     addRelatedClass(RelType.CLASS_ANNOTATIONS, annotationClassInfo);
/*  574 */     annotationClassInfo.addRelatedClass(RelType.CLASSES_WITH_ANNOTATION, this);
/*      */ 
/*      */     
/*  577 */     if (classAnnotationInfo.getName().equals(Inherited.class.getName())) {
/*  578 */       this.isInherited = true;
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
/*      */ 
/*      */ 
/*      */   
/*      */   private void addFieldOrMethodAnnotationInfo(AnnotationInfoList annotationInfoList, boolean isField, int modifiers, Map<String, ClassInfo> classNameToClassInfo) {
/*  596 */     if (annotationInfoList != null) {
/*  597 */       for (AnnotationInfo fieldAnnotationInfo : annotationInfoList) {
/*  598 */         ClassInfo annotationClassInfo = getOrCreateClassInfo(fieldAnnotationInfo.getName(), classNameToClassInfo);
/*      */         
/*  600 */         annotationClassInfo.setModifiers(8192);
/*      */         
/*  602 */         addRelatedClass(isField ? RelType.FIELD_ANNOTATIONS : RelType.METHOD_ANNOTATIONS, annotationClassInfo);
/*      */         
/*  604 */         annotationClassInfo.addRelatedClass(
/*  605 */             isField ? RelType.CLASSES_WITH_FIELD_ANNOTATION : RelType.CLASSES_WITH_METHOD_ANNOTATION, this);
/*      */ 
/*      */         
/*  608 */         if (!Modifier.isPrivate(modifiers)) {
/*  609 */           annotationClassInfo.addRelatedClass(isField ? RelType.CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION : 
/*  610 */               RelType.CLASSES_WITH_NONPRIVATE_METHOD_ANNOTATION, this);
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
/*      */   void addFieldInfo(FieldInfoList fieldInfoList, Map<String, ClassInfo> classNameToClassInfo) {
/*  625 */     for (FieldInfo fi : fieldInfoList)
/*      */     {
/*  627 */       addFieldOrMethodAnnotationInfo(fi.annotationInfo, true, fi.getModifiers(), classNameToClassInfo);
/*      */     }
/*      */     
/*  630 */     if (this.fieldInfo == null) {
/*  631 */       this.fieldInfo = fieldInfoList;
/*      */     } else {
/*  633 */       this.fieldInfo.addAll(fieldInfoList);
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
/*      */   void addMethodInfo(MethodInfoList methodInfoList, Map<String, ClassInfo> classNameToClassInfo) {
/*  646 */     for (MethodInfo mi : methodInfoList) {
/*      */       
/*  648 */       addFieldOrMethodAnnotationInfo(mi.annotationInfo, false, mi.getModifiers(), classNameToClassInfo);
/*      */ 
/*      */ 
/*      */       
/*  652 */       if (mi.parameterAnnotationInfo != null) {
/*  653 */         for (int i = 0; i < mi.parameterAnnotationInfo.length; i++) {
/*  654 */           AnnotationInfo[] paramAnnotationInfoArr = mi.parameterAnnotationInfo[i];
/*  655 */           if (paramAnnotationInfoArr != null) {
/*  656 */             for (AnnotationInfo methodParamAnnotationInfo : paramAnnotationInfoArr) {
/*  657 */               ClassInfo annotationClassInfo = getOrCreateClassInfo(methodParamAnnotationInfo
/*  658 */                   .getName(), classNameToClassInfo);
/*  659 */               annotationClassInfo.setModifiers(8192);
/*  660 */               addRelatedClass(RelType.METHOD_PARAMETER_ANNOTATIONS, annotationClassInfo);
/*  661 */               annotationClassInfo.addRelatedClass(RelType.CLASSES_WITH_METHOD_PARAMETER_ANNOTATION, this);
/*      */ 
/*      */               
/*  664 */               if (!Modifier.isPrivate(mi.getModifiers())) {
/*  665 */                 annotationClassInfo.addRelatedClass(RelType.CLASSES_WITH_NONPRIVATE_METHOD_PARAMETER_ANNOTATION, this);
/*      */               }
/*      */             } 
/*      */           }
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  673 */     if (this.methodInfo == null) {
/*  674 */       this.methodInfo = methodInfoList;
/*      */     } else {
/*  676 */       this.methodInfo.addAll(methodInfoList);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTypeSignature(String typeSignatureStr) {
/*  687 */     this.typeSignatureStr = typeSignatureStr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void addAnnotationParamDefaultValues(AnnotationParameterValueList paramNamesAndValues) {
/*  698 */     setIsAnnotation(true);
/*  699 */     if (this.annotationDefaultParamValues == null) {
/*  700 */       this.annotationDefaultParamValues = paramNamesAndValues;
/*      */     } else {
/*  702 */       this.annotationDefaultParamValues.addAll(paramNamesAndValues);
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
/*      */   static ClassInfo addScannedClass(String className, int classModifiers, boolean isExternalClass, Map<String, ClassInfo> classNameToClassInfo, ClasspathElement classpathElement, Resource classfileResource) {
/*  729 */     ClassInfo classInfo = classNameToClassInfo.get(className);
/*  730 */     if (classInfo == null) {
/*      */       
/*  732 */       classNameToClassInfo.put(className, classInfo = new ClassInfo(className, classModifiers, classfileResource));
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  738 */       if (classInfo.isScannedClass)
/*      */       {
/*  740 */         throw new IllegalArgumentException("Class " + className + " should not have been encountered more than once due to classpath masking -- please report this bug at: https://github.com/classgraph/classgraph/issues");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  746 */       classInfo.classfileResource = classfileResource;
/*      */ 
/*      */       
/*  749 */       classInfo.modifiers |= classModifiers;
/*      */     } 
/*      */ 
/*      */     
/*  753 */     classInfo.isScannedClass = true;
/*      */ 
/*      */     
/*  756 */     classInfo.isExternalClass = isExternalClass;
/*      */ 
/*      */     
/*  759 */     classInfo.classpathElement = classpathElement;
/*      */ 
/*      */     
/*  762 */     classInfo.classLoader = classpathElement.getClassLoader();
/*      */     
/*  764 */     return classInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private enum ClassType
/*      */   {
/*  772 */     ALL,
/*      */     
/*  774 */     STANDARD_CLASS,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  779 */     IMPLEMENTED_INTERFACE,
/*      */     
/*  781 */     ANNOTATION,
/*      */     
/*  783 */     INTERFACE_OR_ANNOTATION,
/*      */     
/*  785 */     ENUM,
/*      */     
/*  787 */     RECORD;
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
/*      */   private static Set<ClassInfo> filterClassInfo(Collection<ClassInfo> classes, ScanSpec scanSpec, boolean strictAccept, ClassType... classTypes) {
/*  805 */     if (classes == null) {
/*  806 */       return Collections.emptySet();
/*      */     }
/*  808 */     boolean includeAllTypes = (classTypes.length == 0);
/*  809 */     boolean includeStandardClasses = false;
/*  810 */     boolean includeImplementedInterfaces = false;
/*  811 */     boolean includeAnnotations = false;
/*  812 */     boolean includeEnums = false;
/*  813 */     boolean includeRecords = false;
/*  814 */     for (ClassType classType : classTypes) {
/*  815 */       switch (classType) {
/*      */         case ALL:
/*  817 */           includeAllTypes = true;
/*      */           break;
/*      */         case STANDARD_CLASS:
/*  820 */           includeStandardClasses = true;
/*      */           break;
/*      */         case IMPLEMENTED_INTERFACE:
/*  823 */           includeImplementedInterfaces = true;
/*      */           break;
/*      */         case ANNOTATION:
/*  826 */           includeAnnotations = true;
/*      */           break;
/*      */         case INTERFACE_OR_ANNOTATION:
/*  829 */           includeImplementedInterfaces = includeAnnotations = true;
/*      */           break;
/*      */         case ENUM:
/*  832 */           includeEnums = true;
/*      */           break;
/*      */         case RECORD:
/*  835 */           includeRecords = true;
/*      */           break;
/*      */         default:
/*  838 */           throw new IllegalArgumentException("Unknown ClassType: " + classType);
/*      */       } 
/*      */     } 
/*  841 */     if (includeStandardClasses && includeImplementedInterfaces && includeAnnotations) {
/*  842 */       includeAllTypes = true;
/*      */     }
/*  844 */     Set<ClassInfo> classInfoSetFiltered = new LinkedHashSet<>(classes.size());
/*  845 */     for (ClassInfo classInfo : classes) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  852 */       boolean includeType = (includeAllTypes || (includeStandardClasses && classInfo.isStandardClass()) || (includeImplementedInterfaces && classInfo.isImplementedInterface()) || (includeAnnotations && classInfo.isAnnotation()) || (includeEnums && classInfo.isEnum()) || (includeRecords && classInfo.isRecord()));
/*      */       
/*  854 */       boolean acceptClass = (!classInfo.isExternalClass || scanSpec.enableExternalClasses || !strictAccept);
/*      */ 
/*      */       
/*  857 */       if (includeType && acceptClass && !scanSpec.classOrPackageIsRejected(classInfo.name))
/*      */       {
/*  859 */         classInfoSetFiltered.add(classInfo);
/*      */       }
/*      */     } 
/*  862 */     return classInfoSetFiltered;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class ReachableAndDirectlyRelatedClasses
/*      */   {
/*      */     final Set<ClassInfo> reachableClasses;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     final Set<ClassInfo> directlyRelatedClasses;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private ReachableAndDirectlyRelatedClasses(Set<ClassInfo> reachableClasses, Set<ClassInfo> directlyRelatedClasses) {
/*  887 */       this.reachableClasses = reachableClasses;
/*  888 */       this.directlyRelatedClasses = directlyRelatedClasses;
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
/*      */ 
/*      */ 
/*      */   
/*      */   private ReachableAndDirectlyRelatedClasses filterClassInfo(RelType relType, boolean strictAccept, ClassType... classTypes) {
/*  906 */     Set<ClassInfo> directlyRelatedClasses = this.relatedClasses.get(relType);
/*  907 */     if (directlyRelatedClasses == null) {
/*  908 */       return NO_REACHABLE_CLASSES;
/*      */     }
/*      */     
/*  911 */     directlyRelatedClasses = new LinkedHashSet<>(directlyRelatedClasses);
/*      */     
/*  913 */     Set<ClassInfo> reachableClasses = new LinkedHashSet<>(directlyRelatedClasses);
/*  914 */     if (relType == RelType.METHOD_ANNOTATIONS || relType == RelType.METHOD_PARAMETER_ANNOTATIONS || relType == RelType.FIELD_ANNOTATIONS) {
/*      */ 
/*      */       
/*  917 */       for (ClassInfo annotation : directlyRelatedClasses) {
/*  918 */         reachableClasses.addAll(
/*  919 */             (annotation.filterClassInfo(RelType.CLASS_ANNOTATIONS, strictAccept, new ClassType[0])).reachableClasses);
/*      */       }
/*  921 */     } else if (relType == RelType.CLASSES_WITH_METHOD_ANNOTATION || relType == RelType.CLASSES_WITH_NONPRIVATE_METHOD_ANNOTATION || relType == RelType.CLASSES_WITH_METHOD_PARAMETER_ANNOTATION || relType == RelType.CLASSES_WITH_NONPRIVATE_METHOD_PARAMETER_ANNOTATION || relType == RelType.CLASSES_WITH_FIELD_ANNOTATION || relType == RelType.CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  929 */       for (ClassInfo subAnnotation : (filterClassInfo(RelType.CLASSES_WITH_ANNOTATION, strictAccept, new ClassType[] { ClassType.ANNOTATION })).reachableClasses)
/*      */       {
/*  931 */         Set<ClassInfo> annotatedClasses = subAnnotation.relatedClasses.get(relType);
/*  932 */         if (annotatedClasses != null) {
/*  933 */           reachableClasses.addAll(annotatedClasses);
/*      */         }
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/*  939 */       LinkedList<ClassInfo> queue = new LinkedList<>(directlyRelatedClasses);
/*  940 */       while (!queue.isEmpty()) {
/*  941 */         ClassInfo head = queue.removeFirst();
/*  942 */         Set<ClassInfo> headRelatedClasses = head.relatedClasses.get(relType);
/*  943 */         if (headRelatedClasses != null) {
/*  944 */           for (ClassInfo directlyReachableFromHead : headRelatedClasses) {
/*      */             
/*  946 */             if (reachableClasses.add(directlyReachableFromHead)) {
/*  947 */               queue.add(directlyReachableFromHead);
/*      */             }
/*      */           } 
/*      */         }
/*      */       } 
/*      */     } 
/*  953 */     if (reachableClasses.isEmpty()) {
/*  954 */       return NO_REACHABLE_CLASSES;
/*      */     }
/*      */     
/*  957 */     if (relType == RelType.CLASS_ANNOTATIONS || relType == RelType.METHOD_ANNOTATIONS || relType == RelType.METHOD_PARAMETER_ANNOTATIONS || relType == RelType.FIELD_ANNOTATIONS) {
/*      */ 
/*      */ 
/*      */       
/*  961 */       Set<ClassInfo> reachableClassesToRemove = null;
/*  962 */       for (ClassInfo reachableClassInfo : reachableClasses) {
/*      */         
/*  964 */         if (reachableClassInfo.getName().startsWith("java.lang.annotation.") && 
/*  965 */           !directlyRelatedClasses.contains(reachableClassInfo)) {
/*  966 */           if (reachableClassesToRemove == null) {
/*  967 */             reachableClassesToRemove = new LinkedHashSet<>();
/*      */           }
/*  969 */           reachableClassesToRemove.add(reachableClassInfo);
/*      */         } 
/*      */       } 
/*  972 */       if (reachableClassesToRemove != null) {
/*  973 */         reachableClasses.removeAll(reachableClassesToRemove);
/*      */       }
/*      */     } 
/*      */     
/*  977 */     return new ReachableAndDirectlyRelatedClasses(
/*  978 */         filterClassInfo(reachableClasses, this.scanResult.scanSpec, strictAccept, classTypes), 
/*  979 */         filterClassInfo(directlyRelatedClasses, this.scanResult.scanSpec, strictAccept, classTypes));
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
/*      */   static ClassInfoList getAllClasses(Collection<ClassInfo> classes, ScanSpec scanSpec) {
/*  995 */     return new ClassInfoList(
/*  996 */         filterClassInfo(classes, scanSpec, true, new ClassType[] { ClassType.ALL }), true);
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
/*      */   static ClassInfoList getAllEnums(Collection<ClassInfo> classes, ScanSpec scanSpec) {
/* 1010 */     return new ClassInfoList(
/* 1011 */         filterClassInfo(classes, scanSpec, true, new ClassType[] { ClassType.ENUM }), true);
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
/*      */   static ClassInfoList getAllRecords(Collection<ClassInfo> classes, ScanSpec scanSpec) {
/* 1025 */     return new ClassInfoList(
/* 1026 */         filterClassInfo(classes, scanSpec, true, new ClassType[] { ClassType.RECORD }), true);
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
/*      */   static ClassInfoList getAllStandardClasses(Collection<ClassInfo> classes, ScanSpec scanSpec) {
/* 1040 */     return new ClassInfoList(
/* 1041 */         filterClassInfo(classes, scanSpec, true, new ClassType[] { ClassType.STANDARD_CLASS }), true);
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
/*      */   static ClassInfoList getAllImplementedInterfaceClasses(Collection<ClassInfo> classes, ScanSpec scanSpec) {
/* 1056 */     return new ClassInfoList(filterClassInfo(classes, scanSpec, true, new ClassType[] { ClassType.IMPLEMENTED_INTERFACE }), true);
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
/*      */   static ClassInfoList getAllAnnotationClasses(Collection<ClassInfo> classes, ScanSpec scanSpec) {
/* 1071 */     return new ClassInfoList(
/* 1072 */         filterClassInfo(classes, scanSpec, true, new ClassType[] { ClassType.ANNOTATION }), true);
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
/*      */   static ClassInfoList getAllInterfacesOrAnnotationClasses(Collection<ClassInfo> classes, ScanSpec scanSpec) {
/* 1088 */     return new ClassInfoList(filterClassInfo(classes, scanSpec, true, new ClassType[] { ClassType.INTERFACE_OR_ANNOTATION }), true);
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
/*      */   public String getName() {
/* 1102 */     return this.name;
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
/*      */   static String getSimpleName(String className) {
/* 1115 */     return className.substring(Math.max(className.lastIndexOf('.'), className.lastIndexOf('$')) + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSimpleName() {
/* 1126 */     return getSimpleName(this.name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ModuleInfo getModuleInfo() {
/* 1135 */     return this.moduleInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PackageInfo getPackageInfo() {
/* 1144 */     return this.packageInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getPackageName() {
/* 1153 */     return PackageInfo.getParentPackageName(this.name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isExternalClass() {
/* 1163 */     return this.isExternalClass;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getClassfileMinorVersion() {
/* 1173 */     return this.classfileMinorVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getClassfileMajorVersion() {
/* 1183 */     return this.classfileMajorVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getModifiers() {
/* 1192 */     return this.modifiers;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getModifiersStr() {
/* 1202 */     StringBuilder buf = new StringBuilder();
/* 1203 */     TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.CLASS, false, buf);
/* 1204 */     return buf.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPublic() {
/* 1213 */     return Modifier.isPublic(this.modifiers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPrivate() {
/* 1222 */     return Modifier.isPrivate(this.modifiers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isProtected() {
/* 1231 */     return Modifier.isProtected(this.modifiers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPackageVisible() {
/* 1240 */     return (!isPublic() && !isPrivate() && !isProtected());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAbstract() {
/* 1249 */     return Modifier.isAbstract(this.modifiers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSynthetic() {
/* 1258 */     return ((this.modifiers & 0x1000) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFinal() {
/* 1267 */     return Modifier.isFinal(this.modifiers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStatic() {
/* 1276 */     return Modifier.isStatic(this.modifiers);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAnnotation() {
/* 1285 */     return ((this.modifiers & 0x2000) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInterface() {
/* 1295 */     return (isInterfaceOrAnnotation() && !isAnnotation());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInterfaceOrAnnotation() {
/* 1305 */     return ((this.modifiers & 0x200) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEnum() {
/* 1314 */     return ((this.modifiers & 0x4000) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isRecord() {
/* 1323 */     return this.isRecord;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isStandardClass() {
/* 1332 */     return (!isAnnotation() && !isInterface());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isArrayClass() {
/* 1342 */     return this instanceof ArrayClassInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean extendsSuperclass(Class<?> superclass) {
/* 1353 */     return extendsSuperclass(superclass.getName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean extendsSuperclass(String superclassName) {
/* 1364 */     return ((superclassName.equals("java.lang.Object") && isStandardClass()) || 
/* 1365 */       getSuperclasses().containsName(superclassName));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isInnerClass() {
/* 1375 */     return !getOuterClasses().isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isOuterClass() {
/* 1385 */     return !getInnerClasses().isEmpty();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAnonymousInnerClass() {
/* 1395 */     return (this.fullyQualifiedDefiningMethodName != null);
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
/*      */   public boolean isImplementedInterface() {
/* 1410 */     return (this.relatedClasses.get(RelType.CLASSES_IMPLEMENTING) != null || isInterface());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean implementsInterface(Class<?> interfaceClazz) {
/* 1421 */     Assert.isInterface(interfaceClazz);
/* 1422 */     return implementsInterface(interfaceClazz.getName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean implementsInterface(String interfaceName) {
/* 1433 */     return getInterfaces().containsName(interfaceName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasAnnotation(Class<? extends Annotation> annotation) {
/* 1444 */     Assert.isAnnotation(annotation);
/* 1445 */     return hasAnnotation(annotation.getName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasAnnotation(String annotationName) {
/* 1456 */     return getAnnotations().containsName(annotationName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasDeclaredField(String fieldName) {
/* 1467 */     return getDeclaredFieldInfo().containsName(fieldName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasField(String fieldName) {
/* 1478 */     for (ClassInfo ci : getFieldOverrideOrder()) {
/* 1479 */       if (ci.hasDeclaredField(fieldName)) {
/* 1480 */         return true;
/*      */       }
/*      */     } 
/* 1483 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasDeclaredFieldAnnotation(Class<? extends Annotation> annotation) {
/* 1494 */     Assert.isAnnotation(annotation);
/* 1495 */     return hasDeclaredFieldAnnotation(annotation.getName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasDeclaredFieldAnnotation(String fieldAnnotationName) {
/* 1506 */     for (FieldInfo fi : getDeclaredFieldInfo()) {
/* 1507 */       if (fi.hasAnnotation(fieldAnnotationName)) {
/* 1508 */         return true;
/*      */       }
/*      */     } 
/* 1511 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasFieldAnnotation(Class<? extends Annotation> fieldAnnotation) {
/* 1522 */     Assert.isAnnotation(fieldAnnotation);
/* 1523 */     return hasFieldAnnotation(fieldAnnotation.getName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasFieldAnnotation(String fieldAnnotationName) {
/* 1534 */     for (ClassInfo ci : getFieldOverrideOrder()) {
/* 1535 */       if (ci.hasDeclaredFieldAnnotation(fieldAnnotationName)) {
/* 1536 */         return true;
/*      */       }
/*      */     } 
/* 1539 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasDeclaredMethod(String methodName) {
/* 1550 */     return getDeclaredMethodInfo().containsName(methodName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasMethod(String methodName) {
/* 1561 */     for (ClassInfo ci : getMethodOverrideOrder()) {
/* 1562 */       if (ci.hasDeclaredMethod(methodName)) {
/* 1563 */         return true;
/*      */       }
/*      */     } 
/* 1566 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasDeclaredMethodAnnotation(Class<? extends Annotation> methodAnnotation) {
/* 1577 */     Assert.isAnnotation(methodAnnotation);
/* 1578 */     return hasDeclaredMethodAnnotation(methodAnnotation.getName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasDeclaredMethodAnnotation(String methodAnnotationName) {
/* 1589 */     for (MethodInfo mi : getDeclaredMethodInfo()) {
/* 1590 */       if (mi.hasAnnotation(methodAnnotationName)) {
/* 1591 */         return true;
/*      */       }
/*      */     } 
/* 1594 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasMethodAnnotation(Class<? extends Annotation> methodAnnotation) {
/* 1605 */     Assert.isAnnotation(methodAnnotation);
/* 1606 */     return hasMethodAnnotation(methodAnnotation.getName());
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
/*      */   public boolean hasMethodAnnotation(String methodAnnotationName) {
/* 1619 */     for (ClassInfo ci : getMethodOverrideOrder()) {
/* 1620 */       if (ci.hasDeclaredMethodAnnotation(methodAnnotationName)) {
/* 1621 */         return true;
/*      */       }
/*      */     } 
/* 1624 */     return false;
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
/*      */   public boolean hasDeclaredMethodParameterAnnotation(Class<? extends Annotation> methodParameterAnnotation) {
/* 1636 */     Assert.isAnnotation(methodParameterAnnotation);
/* 1637 */     return hasDeclaredMethodParameterAnnotation(methodParameterAnnotation.getName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasDeclaredMethodParameterAnnotation(String methodParameterAnnotationName) {
/* 1648 */     for (MethodInfo mi : getDeclaredMethodInfo()) {
/* 1649 */       if (mi.hasParameterAnnotation(methodParameterAnnotationName)) {
/* 1650 */         return true;
/*      */       }
/*      */     } 
/* 1653 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasMethodParameterAnnotation(Class<? extends Annotation> methodParameterAnnotation) {
/* 1664 */     Assert.isAnnotation(methodParameterAnnotation);
/* 1665 */     return hasMethodParameterAnnotation(methodParameterAnnotation.getName());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasMethodParameterAnnotation(String methodParameterAnnotationName) {
/* 1676 */     for (ClassInfo ci : getMethodOverrideOrder()) {
/* 1677 */       if (ci.hasDeclaredMethodParameterAnnotation(methodParameterAnnotationName)) {
/* 1678 */         return true;
/*      */       }
/*      */     } 
/* 1681 */     return false;
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
/*      */   private List<ClassInfo> getFieldOverrideOrder(Set<ClassInfo> visited, List<ClassInfo> overrideOrderOut) {
/* 1697 */     if (visited.add(this)) {
/* 1698 */       overrideOrderOut.add(this);
/* 1699 */       for (ClassInfo iface : getInterfaces()) {
/* 1700 */         iface.getFieldOverrideOrder(visited, overrideOrderOut);
/*      */       }
/* 1702 */       ClassInfo superclass = getSuperclass();
/* 1703 */       if (superclass != null) {
/* 1704 */         superclass.getFieldOverrideOrder(visited, overrideOrderOut);
/*      */       }
/*      */     } 
/* 1707 */     return overrideOrderOut;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<ClassInfo> getFieldOverrideOrder() {
/* 1716 */     if (this.overrideOrder == null) {
/* 1717 */       this.overrideOrder = getFieldOverrideOrder(new HashSet<>(), new ArrayList<>());
/*      */     }
/* 1719 */     return this.overrideOrder;
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
/*      */   private List<ClassInfo> getMethodOverrideOrder(Set<ClassInfo> visited, List<ClassInfo> overrideOrderOut) {
/* 1739 */     if (!visited.add(this)) {
/* 1740 */       return overrideOrderOut;
/*      */     }
/*      */     
/* 1743 */     if (!isInterfaceOrAnnotation()) {
/* 1744 */       overrideOrderOut.add(this);
/*      */       
/* 1746 */       ClassInfo superclass = getSuperclass();
/* 1747 */       if (superclass != null) {
/* 1748 */         superclass.getMethodOverrideOrder(visited, overrideOrderOut);
/*      */       }
/* 1750 */       for (ClassInfo iface : getInterfaces()) {
/* 1751 */         iface.getMethodOverrideOrder(visited, overrideOrderOut);
/*      */       }
/* 1753 */       return overrideOrderOut;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1762 */     ClassInfoList interfaces = getInterfaces();
/* 1763 */     int minIndex = Integer.MAX_VALUE;
/* 1764 */     for (ClassInfo iface : interfaces) {
/* 1765 */       if (!visited.contains(iface)) {
/*      */         continue;
/*      */       }
/* 1768 */       int currIdx = overrideOrderOut.indexOf(iface);
/* 1769 */       minIndex = (currIdx >= 0 && currIdx < minIndex) ? currIdx : minIndex;
/*      */     } 
/* 1771 */     if (minIndex == Integer.MAX_VALUE) {
/* 1772 */       overrideOrderOut.add(this);
/*      */     } else {
/* 1774 */       overrideOrderOut.add(minIndex, this);
/*      */     } 
/*      */     
/* 1777 */     for (ClassInfo iface : interfaces) {
/* 1778 */       iface.getMethodOverrideOrder(visited, overrideOrderOut);
/*      */     }
/* 1780 */     return overrideOrderOut;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private List<ClassInfo> getMethodOverrideOrder() {
/* 1789 */     if (this.methodOverrideOrder == null) {
/* 1790 */       this.methodOverrideOrder = getMethodOverrideOrder(new HashSet<>(), new ArrayList<>());
/*      */     }
/* 1792 */     return this.methodOverrideOrder;
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
/*      */   public ClassInfoList getSubclasses() {
/* 1808 */     if (getName().equals("java.lang.Object"))
/*      */     {
/* 1810 */       return this.scanResult.getAllStandardClasses();
/*      */     }
/* 1812 */     return new ClassInfoList(
/* 1813 */         filterClassInfo(RelType.SUBCLASSES, !this.isExternalClass, new ClassType[0]), true);
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
/*      */   public ClassInfoList getSuperclasses() {
/* 1828 */     return new ClassInfoList(filterClassInfo(RelType.SUPERCLASSES, false, new ClassType[0]), false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassInfo getSuperclass() {
/* 1839 */     Set<ClassInfo> superClasses = this.relatedClasses.get(RelType.SUPERCLASSES);
/* 1840 */     if (superClasses == null || superClasses.isEmpty())
/* 1841 */       return null; 
/* 1842 */     if (superClasses.size() > 2) {
/* 1843 */       throw new IllegalArgumentException("More than one superclass: " + superClasses);
/*      */     }
/* 1845 */     ClassInfo superclass = superClasses.iterator().next();
/* 1846 */     if (superclass.getName().equals("java.lang.Object")) {
/* 1847 */       return null;
/*      */     }
/* 1849 */     return superclass;
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
/*      */   public ClassInfoList getOuterClasses() {
/* 1862 */     return new ClassInfoList(
/* 1863 */         filterClassInfo(RelType.CONTAINED_WITHIN_OUTER_CLASS, false, new ClassType[0]), false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassInfoList getInnerClasses() {
/* 1873 */     return new ClassInfoList(filterClassInfo(RelType.CONTAINS_INNER_CLASS, false, new ClassType[0]), true);
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
/*      */   public String getFullyQualifiedDefiningMethodName() {
/* 1885 */     return this.fullyQualifiedDefiningMethodName;
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
/*      */   public ClassInfoList getInterfaces() {
/* 1902 */     ReachableAndDirectlyRelatedClasses implementedInterfaces = filterClassInfo(RelType.IMPLEMENTED_INTERFACES, false, new ClassType[0]);
/* 1903 */     Set<ClassInfo> allInterfaces = new LinkedHashSet<>(implementedInterfaces.reachableClasses);
/* 1904 */     for (ClassInfo superclass : (filterClassInfo(RelType.SUPERCLASSES, false, new ClassType[0])).reachableClasses) {
/*      */ 
/*      */       
/* 1907 */       Set<ClassInfo> superclassImplementedInterfaces = (superclass.filterClassInfo(RelType.IMPLEMENTED_INTERFACES, false, new ClassType[0])).reachableClasses;
/* 1908 */       allInterfaces.addAll(superclassImplementedInterfaces);
/*      */     } 
/*      */     
/* 1911 */     return new ClassInfoList(allInterfaces, implementedInterfaces.directlyRelatedClasses, false);
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
/*      */   public ClassInfoList getClassesImplementing() {
/* 1924 */     ReachableAndDirectlyRelatedClasses implementingClasses = filterClassInfo(RelType.CLASSES_IMPLEMENTING, !this.isExternalClass, new ClassType[0]);
/* 1925 */     Set<ClassInfo> allImplementingClasses = new LinkedHashSet<>(implementingClasses.reachableClasses);
/* 1926 */     for (ClassInfo implementingClass : implementingClasses.reachableClasses) {
/* 1927 */       Set<ClassInfo> implementingSubclasses = (implementingClass.filterClassInfo(RelType.SUBCLASSES, !implementingClass.isExternalClass, new ClassType[0])).reachableClasses;
/*      */       
/* 1929 */       allImplementingClasses.addAll(implementingSubclasses);
/*      */     } 
/* 1931 */     return new ClassInfoList(allImplementingClasses, implementingClasses.directlyRelatedClasses, true);
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
/*      */   public ClassInfoList getAnnotations() {
/* 1952 */     if (!this.scanResult.scanSpec.enableAnnotationInfo) {
/* 1953 */       throw new IllegalArgumentException("Please call ClassGraph#enableAnnotationInfo() before #scan()");
/*      */     }
/*      */ 
/*      */     
/* 1957 */     ReachableAndDirectlyRelatedClasses annotationClasses = filterClassInfo(RelType.CLASS_ANNOTATIONS, false, new ClassType[0]);
/*      */ 
/*      */     
/* 1960 */     Set<ClassInfo> inheritedSuperclassAnnotations = null;
/* 1961 */     for (ClassInfo superclass : getSuperclasses()) {
/* 1962 */       for (ClassInfo superclassAnnotation : (superclass.filterClassInfo(RelType.CLASS_ANNOTATIONS, false, new ClassType[0])).reachableClasses) {
/*      */ 
/*      */ 
/*      */         
/* 1966 */         if (superclassAnnotation != null && superclassAnnotation.isInherited) {
/*      */           
/* 1968 */           if (inheritedSuperclassAnnotations == null) {
/* 1969 */             inheritedSuperclassAnnotations = new LinkedHashSet<>();
/*      */           }
/* 1971 */           inheritedSuperclassAnnotations.add(superclassAnnotation);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1976 */     if (inheritedSuperclassAnnotations == null)
/*      */     {
/* 1978 */       return new ClassInfoList(annotationClasses, true);
/*      */     }
/*      */     
/* 1981 */     inheritedSuperclassAnnotations.addAll(annotationClasses.reachableClasses);
/* 1982 */     return new ClassInfoList(inheritedSuperclassAnnotations, annotationClasses.directlyRelatedClasses, true);
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
/*      */   private ClassInfoList getFieldOrMethodAnnotations(RelType relType) {
/* 1999 */     boolean isField = (relType == RelType.FIELD_ANNOTATIONS);
/* 2000 */     if ((isField ? this.scanResult.scanSpec.enableFieldInfo : this.scanResult.scanSpec.enableMethodInfo) || !this.scanResult.scanSpec.enableAnnotationInfo)
/*      */     {
/* 2002 */       throw new IllegalArgumentException("Please call ClassGraph#enable" + (isField ? "Field" : "Method") + "Info() and #enableAnnotationInfo() before #scan()");
/*      */     }
/*      */     
/* 2005 */     ReachableAndDirectlyRelatedClasses fieldOrMethodAnnotations = filterClassInfo(relType, false, new ClassType[] { ClassType.ANNOTATION });
/*      */     
/* 2007 */     Set<ClassInfo> fieldOrMethodAnnotationsAndMetaAnnotations = new LinkedHashSet<>(fieldOrMethodAnnotations.reachableClasses);
/*      */     
/* 2009 */     return new ClassInfoList(fieldOrMethodAnnotationsAndMetaAnnotations, fieldOrMethodAnnotations.directlyRelatedClasses, true);
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
/*      */   private ClassInfoList getClassesWithFieldOrMethodAnnotation(RelType relType) {
/* 2027 */     boolean isField = (relType == RelType.CLASSES_WITH_FIELD_ANNOTATION || relType == RelType.CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION);
/*      */     
/* 2029 */     if (isField ? this.scanResult.scanSpec.enableFieldInfo : this.scanResult.scanSpec.enableMethodInfo) if (this.scanResult.scanSpec.enableAnnotationInfo) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2035 */         ReachableAndDirectlyRelatedClasses classesWithDirectlyAnnotatedFieldsOrMethods = filterClassInfo(relType, !this.isExternalClass, new ClassType[0]);
/* 2036 */         ReachableAndDirectlyRelatedClasses annotationsWithThisMetaAnnotation = filterClassInfo(RelType.CLASSES_WITH_ANNOTATION, !this.isExternalClass, new ClassType[] { ClassType.ANNOTATION });
/*      */         
/* 2038 */         if (annotationsWithThisMetaAnnotation.reachableClasses.isEmpty())
/*      */         {
/* 2040 */           return new ClassInfoList(classesWithDirectlyAnnotatedFieldsOrMethods, true);
/*      */         }
/*      */ 
/*      */         
/* 2044 */         Set<ClassInfo> allClassesWithAnnotatedOrMetaAnnotatedFieldsOrMethods = new LinkedHashSet<>(classesWithDirectlyAnnotatedFieldsOrMethods.reachableClasses);
/*      */         
/* 2046 */         for (ClassInfo metaAnnotatedAnnotation : annotationsWithThisMetaAnnotation.reachableClasses) {
/* 2047 */           allClassesWithAnnotatedOrMetaAnnotatedFieldsOrMethods
/* 2048 */             .addAll((metaAnnotatedAnnotation.filterClassInfo(relType, !metaAnnotatedAnnotation.isExternalClass, new ClassType[0])).reachableClasses);
/*      */         }
/*      */         
/* 2051 */         return new ClassInfoList(allClassesWithAnnotatedOrMetaAnnotatedFieldsOrMethods, classesWithDirectlyAnnotatedFieldsOrMethods.directlyRelatedClasses, true);
/*      */       } 
/*      */     
/*      */     throw new IllegalArgumentException("Please call ClassGraph#enable" + (isField ? "Field" : "Method") + "Info() and #enableAnnotationInfo() before #scan()");
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
/*      */   public AnnotationInfoList getAnnotationInfo() {
/* 2067 */     if (!this.scanResult.scanSpec.enableAnnotationInfo) {
/* 2068 */       throw new IllegalArgumentException("Please call ClassGraph#enableAnnotationInfo() before #scan()");
/*      */     }
/* 2070 */     return AnnotationInfoList.getIndirectAnnotations(this.annotationInfo, this);
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
/*      */   public AnnotationInfo getAnnotationInfo(Class<? extends Annotation> annotation) {
/* 2092 */     Assert.isAnnotation(annotation);
/* 2093 */     return getAnnotationInfo(annotation.getName());
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
/*      */   public AnnotationInfo getAnnotationInfo(String annotationName) {
/* 2115 */     return getAnnotationInfo().get(annotationName);
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
/*      */   public AnnotationInfoList getAnnotationInfoRepeatable(Class<? extends Annotation> annotation) {
/* 2137 */     Assert.isAnnotation(annotation);
/* 2138 */     return getAnnotationInfoRepeatable(annotation.getName());
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
/*      */   public AnnotationInfoList getAnnotationInfoRepeatable(String annotationName) {
/* 2160 */     return getAnnotationInfo().getRepeatable(annotationName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AnnotationParameterValueList getAnnotationDefaultParameterValues() {
/* 2170 */     if (!this.scanResult.scanSpec.enableAnnotationInfo) {
/* 2171 */       throw new IllegalArgumentException("Please call ClassGraph#enableAnnotationInfo() before #scan()");
/*      */     }
/* 2173 */     if (!isAnnotation()) {
/* 2174 */       throw new IllegalArgumentException("Class is not an annotation: " + getName());
/*      */     }
/* 2176 */     if (this.annotationDefaultParamValues == null) {
/* 2177 */       return AnnotationParameterValueList.EMPTY_LIST;
/*      */     }
/* 2179 */     if (!this.annotationDefaultParamValuesHasBeenConvertedToPrimitive) {
/* 2180 */       this.annotationDefaultParamValues.convertWrapperArraysToPrimitiveArrays(this);
/* 2181 */       this.annotationDefaultParamValuesHasBeenConvertedToPrimitive = true;
/*      */     } 
/* 2183 */     return this.annotationDefaultParamValues;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassInfoList getClassesWithAnnotation() {
/* 2194 */     if (!this.scanResult.scanSpec.enableAnnotationInfo) {
/* 2195 */       throw new IllegalArgumentException("Please call ClassGraph#enableAnnotationInfo() before #scan()");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2200 */     ReachableAndDirectlyRelatedClasses classesWithAnnotation = filterClassInfo(RelType.CLASSES_WITH_ANNOTATION, !this.isExternalClass, new ClassType[0]);
/*      */     
/* 2202 */     if (this.isInherited) {
/*      */       
/* 2204 */       Set<ClassInfo> classesWithAnnotationAndTheirSubclasses = new LinkedHashSet<>(classesWithAnnotation.reachableClasses);
/*      */       
/* 2206 */       for (ClassInfo classWithAnnotation : classesWithAnnotation.reachableClasses) {
/* 2207 */         classesWithAnnotationAndTheirSubclasses.addAll(classWithAnnotation.getSubclasses());
/*      */       }
/* 2209 */       return new ClassInfoList(classesWithAnnotationAndTheirSubclasses, classesWithAnnotation.directlyRelatedClasses, true);
/*      */     } 
/*      */ 
/*      */     
/* 2213 */     return new ClassInfoList(classesWithAnnotation, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ClassInfoList getClassesWithAnnotationDirectOnly() {
/* 2224 */     return new ClassInfoList(
/* 2225 */         filterClassInfo(RelType.CLASSES_WITH_ANNOTATION, !this.isExternalClass, new ClassType[0]), true);
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
/*      */   private MethodInfoList getDeclaredMethodInfo(String methodName, boolean getNormalMethods, boolean getConstructorMethods, boolean getStaticInitializerMethods) {
/* 2247 */     if (!this.scanResult.scanSpec.enableMethodInfo) {
/* 2248 */       throw new IllegalArgumentException("Please call ClassGraph#enableMethodInfo() before #scan()");
/*      */     }
/* 2250 */     if (this.methodInfo == null) {
/* 2251 */       return MethodInfoList.EMPTY_LIST;
/*      */     }
/* 2253 */     if (methodName == null) {
/*      */ 
/*      */       
/* 2256 */       MethodInfoList methodInfoList1 = new MethodInfoList();
/* 2257 */       for (MethodInfo mi : this.methodInfo) {
/* 2258 */         String miName = mi.getName();
/* 2259 */         boolean isConstructor = "<init>".equals(miName);
/*      */         
/* 2261 */         boolean isStaticInitializer = "<clinit>".equals(miName);
/* 2262 */         if ((isConstructor && getConstructorMethods) || (isStaticInitializer && getStaticInitializerMethods) || (!isConstructor && !isStaticInitializer && getNormalMethods))
/*      */         {
/* 2264 */           methodInfoList1.add(mi);
/*      */         }
/*      */       } 
/* 2267 */       return methodInfoList1;
/*      */     } 
/*      */     
/* 2270 */     boolean hasMethodWithName = false;
/* 2271 */     for (MethodInfo f : this.methodInfo) {
/* 2272 */       if (f.getName().equals(methodName)) {
/* 2273 */         hasMethodWithName = true;
/*      */         break;
/*      */       } 
/*      */     } 
/* 2277 */     if (!hasMethodWithName) {
/* 2278 */       return MethodInfoList.EMPTY_LIST;
/*      */     }
/* 2280 */     MethodInfoList methodInfoList = new MethodInfoList();
/* 2281 */     for (MethodInfo mi : this.methodInfo) {
/* 2282 */       if (mi.getName().equals(methodName)) {
/* 2283 */         methodInfoList.add(mi);
/*      */       }
/*      */     } 
/* 2286 */     return methodInfoList;
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
/*      */   private MethodInfoList getMethodInfo(String methodName, boolean getNormalMethods, boolean getConstructorMethods, boolean getStaticInitializerMethods) {
/* 2305 */     if (!this.scanResult.scanSpec.enableMethodInfo) {
/* 2306 */       throw new IllegalArgumentException("Please call ClassGraph#enableMethodInfo() before #scan()");
/*      */     }
/*      */     
/* 2309 */     MethodInfoList methodInfoList = new MethodInfoList();
/* 2310 */     Set<Map.Entry<String, String>> nameAndTypeDescriptorSet = new HashSet<>();
/* 2311 */     for (ClassInfo ci : getMethodOverrideOrder()) {
/* 2312 */       for (MethodInfo mi : ci.getDeclaredMethodInfo(methodName, getNormalMethods, getConstructorMethods, getStaticInitializerMethods)) {
/*      */ 
/*      */         
/* 2315 */         if (nameAndTypeDescriptorSet.add(new AbstractMap.SimpleEntry<>(mi.getName(), mi.getTypeDescriptorStr())))
/*      */         {
/* 2317 */           methodInfoList.add(mi);
/*      */         }
/*      */       } 
/*      */     } 
/* 2321 */     return methodInfoList;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodInfoList getDeclaredMethodInfo() {
/* 2355 */     return getDeclaredMethodInfo(null, true, false, false);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodInfoList getMethodInfo() {
/* 2390 */     return getMethodInfo(null, true, false, false);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodInfoList getDeclaredConstructorInfo() {
/* 2425 */     return getDeclaredMethodInfo(null, false, true, false);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodInfoList getConstructorInfo() {
/* 2460 */     return getMethodInfo(null, false, true, false);
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
/*      */   public MethodInfoList getDeclaredMethodAndConstructorInfo() {
/* 2499 */     return getDeclaredMethodInfo(null, true, true, false);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodInfoList getMethodAndConstructorInfo() {
/* 2535 */     return getMethodInfo(null, true, true, false);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodInfoList getDeclaredMethodInfo(String methodName) {
/* 2572 */     return getDeclaredMethodInfo(methodName, false, false, false);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public MethodInfoList getMethodInfo(String methodName) {
/* 2608 */     return getMethodInfo(methodName, false, false, false);
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
/*      */   public ClassInfoList getMethodAnnotations() {
/* 2621 */     return getFieldOrMethodAnnotations(RelType.METHOD_ANNOTATIONS);
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
/*      */   public ClassInfoList getMethodParameterAnnotations() {
/* 2634 */     return getFieldOrMethodAnnotations(RelType.METHOD_PARAMETER_ANNOTATIONS);
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
/*      */   public ClassInfoList getClassesWithMethodAnnotation() {
/* 2647 */     Set<ClassInfo> classesWithMethodAnnotation = new HashSet<>(getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_METHOD_ANNOTATION));
/*      */ 
/*      */ 
/*      */     
/* 2651 */     for (ClassInfo classWithNonprivateMethodAnnotationOrMetaAnnotation : getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_NONPRIVATE_METHOD_ANNOTATION)) {
/* 2652 */       classesWithMethodAnnotation.addAll(classWithNonprivateMethodAnnotationOrMetaAnnotation.getSubclasses());
/*      */     }
/* 2654 */     return new ClassInfoList(classesWithMethodAnnotation, new HashSet<>(
/* 2655 */           getClassesWithMethodAnnotationDirectOnly()), true);
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
/*      */   public ClassInfoList getClassesWithMethodParameterAnnotation() {
/* 2668 */     Set<ClassInfo> classesWithMethodParameterAnnotation = new HashSet<>(getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_METHOD_PARAMETER_ANNOTATION));
/*      */ 
/*      */ 
/*      */     
/* 2672 */     for (ClassInfo classWithNonprivateMethodParameterAnnotationOrMetaAnnotation : getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_NONPRIVATE_METHOD_PARAMETER_ANNOTATION)) {
/* 2673 */       classesWithMethodParameterAnnotation
/* 2674 */         .addAll(classWithNonprivateMethodParameterAnnotationOrMetaAnnotation.getSubclasses());
/*      */     }
/* 2676 */     return new ClassInfoList(classesWithMethodParameterAnnotation, new HashSet<>(
/* 2677 */           getClassesWithMethodParameterAnnotationDirectOnly()), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ClassInfoList getClassesWithMethodAnnotationDirectOnly() {
/* 2687 */     return new ClassInfoList(
/* 2688 */         filterClassInfo(RelType.CLASSES_WITH_METHOD_ANNOTATION, !this.isExternalClass, new ClassType[0]), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ClassInfoList getClassesWithMethodParameterAnnotationDirectOnly() {
/* 2699 */     return new ClassInfoList(filterClassInfo(RelType.CLASSES_WITH_METHOD_PARAMETER_ANNOTATION, !this.isExternalClass, new ClassType[0]), true);
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
/*      */ 
/*      */   
/*      */   public FieldInfoList getDeclaredFieldInfo() {
/* 2729 */     if (!this.scanResult.scanSpec.enableFieldInfo) {
/* 2730 */       throw new IllegalArgumentException("Please call ClassGraph#enableFieldInfo() before #scan()");
/*      */     }
/* 2732 */     return (this.fieldInfo == null) ? FieldInfoList.EMPTY_LIST : this.fieldInfo;
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
/*      */   public FieldInfoList getFieldInfo() {
/* 2758 */     if (!this.scanResult.scanSpec.enableFieldInfo) {
/* 2759 */       throw new IllegalArgumentException("Please call ClassGraph#enableFieldInfo() before #scan()");
/*      */     }
/*      */     
/* 2762 */     FieldInfoList fieldInfoList = new FieldInfoList();
/* 2763 */     Set<String> fieldNameSet = new HashSet<>();
/* 2764 */     for (ClassInfo ci : getFieldOverrideOrder()) {
/* 2765 */       for (FieldInfo fi : ci.getDeclaredFieldInfo()) {
/*      */         
/* 2767 */         if (fieldNameSet.add(fi.getName()))
/*      */         {
/* 2769 */           fieldInfoList.add(fi);
/*      */         }
/*      */       } 
/*      */     } 
/* 2773 */     return fieldInfoList;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FieldInfoList getEnumConstants() {
/* 2781 */     if (!isEnum()) {
/* 2782 */       throw new IllegalArgumentException("Class " + getName() + " is not an enum");
/*      */     }
/* 2784 */     return getFieldInfo().filter(new FieldInfoList.FieldInfoFilter()
/*      */         {
/*      */           public boolean accept(FieldInfo fieldInfo) {
/* 2787 */             return fieldInfo.isEnum();
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */   
/*      */   public List<Object> getEnumConstantObjects() {
/* 2794 */     if (!isEnum()) {
/* 2795 */       throw new IllegalArgumentException("Class " + getName() + " is not an enum");
/*      */     }
/* 2797 */     Class<?> enumClass = loadClass();
/* 2798 */     FieldInfoList consts = getEnumConstants();
/* 2799 */     List<Object> constObjs = new ArrayList(consts.size());
/*      */     
/* 2801 */     ReflectionUtils reflectionUtils = (this.scanResult == null) ? new ReflectionUtils() : this.scanResult.reflectionUtils;
/* 2802 */     for (FieldInfo constFieldInfo : consts) {
/* 2803 */       Object constObj = reflectionUtils.getStaticFieldVal(true, enumClass, constFieldInfo.getName());
/* 2804 */       if (constObj == null) {
/* 2805 */         throw new IllegalArgumentException("Could not read enum constant objects");
/*      */       }
/* 2807 */       constObjs.add(constObj);
/*      */     } 
/* 2809 */     return constObjs;
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
/*      */   public FieldInfo getDeclaredFieldInfo(String fieldName) {
/* 2837 */     if (!this.scanResult.scanSpec.enableFieldInfo) {
/* 2838 */       throw new IllegalArgumentException("Please call ClassGraph#enableFieldInfo() before #scan()");
/*      */     }
/* 2840 */     if (this.fieldInfo == null) {
/* 2841 */       return null;
/*      */     }
/* 2843 */     for (FieldInfo fi : this.fieldInfo) {
/* 2844 */       if (fi.getName().equals(fieldName)) {
/* 2845 */         return fi;
/*      */       }
/*      */     } 
/* 2848 */     return null;
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
/*      */   public FieldInfo getFieldInfo(String fieldName) {
/* 2876 */     if (!this.scanResult.scanSpec.enableFieldInfo) {
/* 2877 */       throw new IllegalArgumentException("Please call ClassGraph#enableFieldInfo() before #scan()");
/*      */     }
/*      */     
/* 2880 */     for (ClassInfo ci : getFieldOverrideOrder()) {
/* 2881 */       FieldInfo fi = ci.getDeclaredFieldInfo(fieldName);
/* 2882 */       if (fi != null) {
/* 2883 */         return fi;
/*      */       }
/*      */     } 
/* 2886 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassInfoList getFieldAnnotations() {
/* 2897 */     return getFieldOrMethodAnnotations(RelType.FIELD_ANNOTATIONS);
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
/*      */   public ClassInfoList getClassesWithFieldAnnotation() {
/* 2909 */     Set<ClassInfo> classesWithMethodAnnotation = new HashSet<>(getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_FIELD_ANNOTATION));
/*      */ 
/*      */ 
/*      */     
/* 2913 */     for (ClassInfo classWithNonprivateMethodAnnotationOrMetaAnnotation : getClassesWithFieldOrMethodAnnotation(RelType.CLASSES_WITH_NONPRIVATE_FIELD_ANNOTATION)) {
/* 2914 */       classesWithMethodAnnotation.addAll(classWithNonprivateMethodAnnotationOrMetaAnnotation.getSubclasses());
/*      */     }
/* 2916 */     return new ClassInfoList(classesWithMethodAnnotation, new HashSet<>(
/* 2917 */           getClassesWithMethodAnnotationDirectOnly()), true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ClassInfoList getClassesWithFieldAnnotationDirectOnly() {
/* 2927 */     return new ClassInfoList(
/* 2928 */         filterClassInfo(RelType.CLASSES_WITH_FIELD_ANNOTATION, !this.isExternalClass, new ClassType[0]), true);
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
/*      */   public ClassTypeSignature getTypeSignature() {
/* 2945 */     if (this.typeSignatureStr == null) {
/* 2946 */       return null;
/*      */     }
/* 2948 */     if (this.typeSignature == null) {
/*      */       try {
/* 2950 */         this.typeSignature = ClassTypeSignature.parse(this.typeSignatureStr, this);
/* 2951 */         this.typeSignature.setScanResult(this.scanResult);
/* 2952 */         if (this.typeAnnotationDecorators != null) {
/* 2953 */           for (Classfile.ClassTypeAnnotationDecorator decorator : this.typeAnnotationDecorators) {
/* 2954 */             decorator.decorate(this.typeSignature);
/*      */           }
/*      */         }
/* 2957 */       } catch (ParseException e) {
/* 2958 */         throw new IllegalArgumentException("Invalid type signature for class " + getName() + " in classpath element " + 
/* 2959 */             getClasspathElementURI() + " : " + this.typeSignatureStr, e);
/*      */       } 
/*      */     }
/* 2962 */     return this.typeSignature;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTypeSignatureStr() {
/* 2972 */     return this.typeSignatureStr;
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
/*      */   public ClassTypeSignature getTypeSignatureOrTypeDescriptor() {
/* 2985 */     ClassTypeSignature typeSig = null;
/*      */     try {
/* 2987 */       typeSig = getTypeSignature();
/* 2988 */       if (typeSig != null) {
/* 2989 */         return typeSig;
/*      */       }
/* 2991 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/* 2994 */     return getTypeDescriptor();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassTypeSignature getTypeDescriptor() {
/* 3004 */     if (this.typeDescriptor == null) {
/* 3005 */       this.typeDescriptor = new ClassTypeSignature(this, getSuperclass(), getInterfaces());
/* 3006 */       this.typeDescriptor.setScanResult(this.scanResult);
/* 3007 */       if (this.typeAnnotationDecorators != null) {
/* 3008 */         for (Classfile.ClassTypeAnnotationDecorator decorator : this.typeAnnotationDecorators) {
/* 3009 */           decorator.decorate(this.typeDescriptor);
/*      */         }
/*      */       }
/*      */     } 
/* 3013 */     return this.typeDescriptor;
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
/*      */   public String getSourceFile() {
/* 3026 */     return this.sourceFile;
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
/*      */   public URI getClasspathElementURI() {
/* 3041 */     return this.classfileResource.getClasspathElementURI();
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
/*      */   public URL getClasspathElementURL() {
/*      */     try {
/* 3057 */       return getClasspathElementURI().toURL();
/* 3058 */     } catch (IllegalArgumentException|java.net.MalformedURLException e) {
/* 3059 */       throw new IllegalArgumentException("Could not get classpath element URL", e);
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
/*      */   public File getClasspathElementFile() {
/* 3073 */     if (this.classpathElement == null) {
/* 3074 */       throw new IllegalArgumentException("Classpath element is not known for this classpath element");
/*      */     }
/* 3076 */     return this.classpathElement.getFile();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ModuleRef getModuleRef() {
/* 3087 */     if (this.classpathElement == null) {
/* 3088 */       throw new IllegalArgumentException("Classpath element is not known for this classpath element");
/*      */     }
/* 3090 */     return (this.classpathElement instanceof ClasspathElementModule) ? (
/* 3091 */       (ClasspathElementModule)this.classpathElement).getModuleRef() : 
/* 3092 */       null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Resource getResource() {
/* 3103 */     return this.classfileResource;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> Class<T> loadClass(Class<T> superclassOrInterfaceType, boolean ignoreExceptions) {
/* 3135 */     return super.loadClass(superclassOrInterfaceType, ignoreExceptions);
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
/*      */   public <T> Class<T> loadClass(Class<T> superclassOrInterfaceType) {
/* 3158 */     return super.loadClass(superclassOrInterfaceType, false);
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
/*      */   public Class<?> loadClass(boolean ignoreExceptions) {
/* 3174 */     return super.loadClass(ignoreExceptions);
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
/*      */   public Class<?> loadClass() {
/* 3187 */     return super.loadClass(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getClassName() {
/* 3197 */     return this.name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ClassInfo getClassInfo() {
/* 3205 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setScanResult(ScanResult scanResult) {
/* 3213 */     super.setScanResult(scanResult);
/* 3214 */     if (this.typeSignature != null) {
/* 3215 */       this.typeSignature.setScanResult(scanResult);
/*      */     }
/* 3217 */     if (this.annotationInfo != null) {
/* 3218 */       for (AnnotationInfo ai : this.annotationInfo) {
/* 3219 */         ai.setScanResult(scanResult);
/*      */       }
/*      */     }
/* 3222 */     if (this.fieldInfo != null) {
/* 3223 */       for (FieldInfo fi : this.fieldInfo) {
/* 3224 */         fi.setScanResult(scanResult);
/*      */       }
/*      */     }
/* 3227 */     if (this.methodInfo != null) {
/* 3228 */       for (MethodInfo mi : this.methodInfo) {
/* 3229 */         mi.setScanResult(scanResult);
/*      */       }
/*      */     }
/* 3232 */     if (this.annotationDefaultParamValues != null) {
/* 3233 */       for (AnnotationParameterValue apv : this.annotationDefaultParamValues) {
/* 3234 */         apv.setScanResult(scanResult);
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
/* 3248 */     if (this.annotationInfo != null) {
/* 3249 */       this.annotationInfo.handleRepeatableAnnotations(allRepeatableAnnotationNames, this, RelType.CLASS_ANNOTATIONS, RelType.CLASSES_WITH_ANNOTATION, (RelType)null);
/*      */     }
/*      */     
/* 3252 */     if (this.fieldInfo != null) {
/* 3253 */       for (FieldInfo fi : this.fieldInfo) {
/* 3254 */         fi.handleRepeatableAnnotations(allRepeatableAnnotationNames);
/*      */       }
/*      */     }
/* 3257 */     if (this.methodInfo != null) {
/* 3258 */       for (MethodInfo mi : this.methodInfo) {
/* 3259 */         mi.handleRepeatableAnnotations(allRepeatableAnnotationNames);
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
/*      */   void addReferencedClassNames(Set<String> refdClassNames) {
/* 3273 */     if (this.referencedClassNames == null) {
/* 3274 */       this.referencedClassNames = refdClassNames;
/*      */     } else {
/* 3276 */       this.referencedClassNames.addAll(refdClassNames);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void findReferencedClassInfo(Map<String, ClassInfo> classNameToClassInfo, Set<ClassInfo> refdClassInfo, LogNode log) {
/* 3295 */     super.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/* 3296 */     if (this.referencedClassNames != null) {
/* 3297 */       for (String refdClassName : this.referencedClassNames) {
/* 3298 */         ClassInfo classInfo = getOrCreateClassInfo(refdClassName, classNameToClassInfo);
/* 3299 */         classInfo.setScanResult(this.scanResult);
/* 3300 */         refdClassInfo.add(classInfo);
/*      */       } 
/*      */     }
/* 3303 */     getMethodInfo().findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/* 3304 */     getFieldInfo().findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/* 3305 */     getAnnotationInfo().findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/* 3306 */     if (this.annotationDefaultParamValues != null) {
/* 3307 */       this.annotationDefaultParamValues.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/*      */     }
/*      */     try {
/* 3310 */       ClassTypeSignature classSig = getTypeSignature();
/* 3311 */       if (classSig != null) {
/* 3312 */         classSig.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
/*      */       }
/* 3314 */     } catch (IllegalArgumentException e) {
/* 3315 */       if (log != null) {
/* 3316 */         log.log("Illegal type signature for class " + getClassName() + ": " + getTypeSignatureStr());
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
/*      */   void setReferencedClasses(ClassInfoList refdClasses) {
/* 3330 */     this.referencedClasses = refdClasses;
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
/*      */   public ClassInfoList getClassDependencies() {
/* 3343 */     if (!this.scanResult.scanSpec.enableInterClassDependencies) {
/* 3344 */       throw new IllegalArgumentException("Please call ClassGraph#enableInterClassDependencies() before #scan()");
/*      */     }
/*      */     
/* 3347 */     return (this.referencedClasses == null) ? ClassInfoList.EMPTY_LIST : this.referencedClasses;
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
/*      */   public int compareTo(ClassInfo o) {
/* 3361 */     return this.name.compareTo(o.name);
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
/*      */   public boolean equals(Object obj) {
/* 3373 */     if (obj == this)
/* 3374 */       return true; 
/* 3375 */     if (!(obj instanceof ClassInfo)) {
/* 3376 */       return false;
/*      */     }
/* 3378 */     ClassInfo other = (ClassInfo)obj;
/* 3379 */     return this.name.equals(other.name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int hashCode() {
/* 3389 */     return (this.name == null) ? 0 : this.name.hashCode();
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
/*      */   protected void toString(boolean useSimpleNames, StringBuilder buf) {
/* 3404 */     boolean initialBufEmpty = (buf.length() == 0);
/* 3405 */     if (this.annotationInfo != null) {
/* 3406 */       for (AnnotationInfo annotation : this.annotationInfo) {
/* 3407 */         if (buf.length() > 0 && buf.charAt(buf.length() - 1) != ' ' && buf
/* 3408 */           .charAt(buf.length() - 1) != '(') {
/* 3409 */           buf.append(' ');
/*      */         }
/* 3411 */         annotation.toString(useSimpleNames, buf);
/*      */       } 
/*      */     }
/* 3414 */     ClassTypeSignature typeSig = null;
/*      */     try {
/* 3416 */       typeSig = getTypeSignature();
/* 3417 */     } catch (Exception exception) {}
/*      */ 
/*      */     
/* 3420 */     if (typeSig != null) {
/*      */       
/* 3422 */       typeSig.toStringInternal(useSimpleNames ? getSimpleName(this.name) : this.name, false, this.modifiers, 
/* 3423 */           isAnnotation(), isInterface(), this.annotationInfo, buf);
/*      */     }
/*      */     else {
/*      */       
/* 3427 */       TypeUtils.modifiersToString(this.modifiers, TypeUtils.ModifierType.CLASS, false, buf);
/* 3428 */       if (buf.length() > 0 && buf.charAt(buf.length() - 1) != ' ' && buf.charAt(buf.length() - 1) != '(') {
/* 3429 */         buf.append(' ');
/*      */       }
/*      */       
/* 3432 */       if (initialBufEmpty) {
/* 3433 */         buf.append(isRecord() ? "record " : (
/* 3434 */             isEnum() ? "enum " : (
/* 3435 */             isAnnotation() ? "@interface " : (
/* 3436 */             isInterface() ? "interface " : 
/* 3437 */             "class "))));
/*      */       }
/* 3439 */       buf.append(useSimpleNames ? getSimpleName(this.name) : this.name);
/* 3440 */       if (this.isRecord) {
/*      */         
/* 3442 */         buf.append('(');
/* 3443 */         boolean isFirstParam = true;
/* 3444 */         for (FieldInfo fieldInfo : getFieldInfo()) {
/* 3445 */           if (!isFirstParam) {
/* 3446 */             buf.append(", ");
/*      */           } else {
/* 3448 */             isFirstParam = false;
/*      */           } 
/* 3450 */           fieldInfo.toString(false, false, buf);
/*      */         } 
/* 3452 */         buf.append(')');
/*      */       } 
/* 3454 */       ClassInfo superclass = getSuperclass();
/* 3455 */       if (superclass != null && !superclass.getName().equals("java.lang.Object")) {
/* 3456 */         buf.append(" extends ");
/* 3457 */         superclass.toString(useSimpleNames, buf);
/*      */       } 
/* 3459 */       Set<ClassInfo> interfaces = (filterClassInfo(RelType.IMPLEMENTED_INTERFACES, false, new ClassType[0])).directlyRelatedClasses;
/*      */       
/* 3461 */       if (!interfaces.isEmpty()) {
/* 3462 */         buf.append(isInterface() ? " extends " : " implements ");
/* 3463 */         boolean first = true;
/* 3464 */         for (ClassInfo iface : interfaces) {
/* 3465 */           if (first) {
/* 3466 */             first = false;
/*      */           } else {
/* 3468 */             buf.append(", ");
/*      */           } 
/* 3470 */           iface.toString(useSimpleNames, buf);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   ClassInfo() {}
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ClassInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */