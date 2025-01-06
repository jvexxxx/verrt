/*      */ package io.github.classgraph;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.lang.reflect.Array;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import nonapi.io.github.classgraph.concurrency.WorkQueue;
/*      */ import nonapi.io.github.classgraph.fileslice.reader.ClassfileReader;
/*      */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*      */ import nonapi.io.github.classgraph.types.ParseException;
/*      */ import nonapi.io.github.classgraph.utils.CollectionUtils;
/*      */ import nonapi.io.github.classgraph.utils.JarUtils;
/*      */ import nonapi.io.github.classgraph.utils.LogNode;
/*      */ import nonapi.io.github.classgraph.utils.StringUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class Classfile
/*      */ {
/*      */   private ClassfileReader reader;
/*      */   private final ClasspathElement classpathElement;
/*      */   private final List<ClasspathElement> classpathOrder;
/*      */   private final String relativePath;
/*      */   private final Resource classfileResource;
/*      */   private final ConcurrentHashMap<String, String> stringInternMap;
/*      */   private String className;
/*      */   private int minorVersion;
/*      */   private int majorVersion;
/*      */   private final boolean isExternalClass;
/*      */   private int classModifiers;
/*      */   private boolean isInterface;
/*      */   private boolean isRecord;
/*      */   private boolean isAnnotation;
/*      */   private String superclassName;
/*      */   private List<String> implementedInterfaces;
/*      */   private AnnotationInfoList classAnnotations;
/*      */   private String fullyQualifiedDefiningMethodName;
/*      */   private List<ClassContainment> classContainmentEntries;
/*      */   private AnnotationParameterValueList annotationParamDefaultValues;
/*      */   private Set<String> refdClassNames;
/*      */   private FieldInfoList fieldInfoList;
/*      */   private MethodInfoList methodInfoList;
/*      */   private String typeSignatureStr;
/*      */   private String sourceFile;
/*      */   private List<ClassTypeAnnotationDecorator> classTypeAnnotationDecorators;
/*      */   private final Set<String> acceptedClassNamesFound;
/*      */   private final Set<String> classNamesScheduledForExtendedScanning;
/*      */   private List<Scanner.ClassfileScanWorkUnit> additionalWorkUnits;
/*      */   private final ScanSpec scanSpec;
/*      */   private int cpCount;
/*      */   private int[] entryOffset;
/*      */   private int[] entryTag;
/*      */   private int[] indirectStringRefs;
/*  172 */   private static final AnnotationInfo[] NO_ANNOTATIONS = new AnnotationInfo[0];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class ClassContainment
/*      */   {
/*      */     public final String innerClassName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final int innerClassModifierBits;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public final String outerClassName;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ClassContainment(String innerClassName, int innerClassModifierBits, String outerClassName) {
/*  201 */       this.innerClassName = innerClassName;
/*  202 */       this.innerClassModifierBits = innerClassModifierBits;
/*  203 */       this.outerClassName = outerClassName;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class ClassfileFormatException
/*      */     extends IOException
/*      */   {
/*      */     static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ClassfileFormatException(String message) {
/*  221 */       super(message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ClassfileFormatException(String message, Throwable cause) {
/*  233 */       super(message, cause);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized Throwable fillInStackTrace() {
/*  243 */       return this;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class SkipClassException
/*      */     extends IOException
/*      */   {
/*      */     static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public SkipClassException(String message) {
/*  259 */       super(message);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public synchronized Throwable fillInStackTrace() {
/*  269 */       return this;
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
/*      */   private void scheduleScanningIfExternalClass(String className, String relationship, LogNode log) {
/*  288 */     if (className != null && !className.equals("java.lang.Object") && 
/*      */       
/*  290 */       !this.acceptedClassNamesFound.contains(className) && this.classNamesScheduledForExtendedScanning
/*      */       
/*  292 */       .add(className)) {
/*  293 */       if (this.scanSpec.classAcceptReject.isRejected(className)) {
/*  294 */         if (log != null) {
/*  295 */           log.log("Cannot extend scanning upwards to external " + relationship + " " + className + ", since it is rejected");
/*      */         
/*      */         }
/*      */       }
/*      */       else {
/*      */         
/*  301 */         String classfilePath = JarUtils.classNameToClassfilePath(className);
/*      */         
/*  303 */         Resource classResource = this.classpathElement.getResource(classfilePath);
/*  304 */         ClasspathElement foundInClasspathElt = null;
/*  305 */         if (classResource != null) {
/*      */           
/*  307 */           foundInClasspathElt = this.classpathElement;
/*      */         } else {
/*      */           
/*  310 */           for (ClasspathElement classpathOrderElt : this.classpathOrder) {
/*  311 */             if (classpathOrderElt != this.classpathElement) {
/*  312 */               classResource = classpathOrderElt.getResource(classfilePath);
/*  313 */               if (classResource != null) {
/*  314 */                 foundInClasspathElt = classpathOrderElt;
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*  320 */         if (classResource != null) {
/*      */           
/*  322 */           if (log != null)
/*      */           {
/*      */ 
/*      */             
/*  326 */             classResource
/*  327 */               .scanLog = log.log("Extending scanning to external " + relationship + (
/*  328 */                 (foundInClasspathElt == this.classpathElement) ? " in same classpath element" : (
/*  329 */                 " in classpath element " + foundInClasspathElt)) + ": " + className);
/*      */           }
/*      */           
/*  332 */           if (this.additionalWorkUnits == null) {
/*  333 */             this.additionalWorkUnits = new ArrayList<>();
/*      */           }
/*      */           
/*  336 */           this.additionalWorkUnits.add(new Scanner.ClassfileScanWorkUnit(foundInClasspathElt, classResource, true));
/*      */         
/*      */         }
/*  339 */         else if (log != null) {
/*  340 */           log.log("External " + relationship + " " + className + " was not found in non-rejected packages -- cannot extend scanning to this class");
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
/*      */   
/*      */   private void extendScanningUpwardsFromAnnotationParameterValues(Object annotationParamVal, LogNode log) {
/*  358 */     if (annotationParamVal != null)
/*      */     {
/*  360 */       if (annotationParamVal instanceof AnnotationInfo) {
/*  361 */         AnnotationInfo annotationInfo = (AnnotationInfo)annotationParamVal;
/*  362 */         scheduleScanningIfExternalClass(annotationInfo.getClassName(), "annotation class", log);
/*  363 */         for (AnnotationParameterValue apv : annotationInfo.getParameterValues()) {
/*  364 */           extendScanningUpwardsFromAnnotationParameterValues(apv.getValue(), log);
/*      */         }
/*  366 */       } else if (annotationParamVal instanceof AnnotationEnumValue) {
/*  367 */         scheduleScanningIfExternalClass(((AnnotationEnumValue)annotationParamVal).getClassName(), "enum class", log);
/*      */       }
/*  369 */       else if (annotationParamVal instanceof AnnotationClassRef) {
/*  370 */         scheduleScanningIfExternalClass(((AnnotationClassRef)annotationParamVal).getClassName(), "class ref", log);
/*      */       }
/*  372 */       else if (annotationParamVal.getClass().isArray()) {
/*  373 */         for (int i = 0, n = Array.getLength(annotationParamVal); i < n; i++) {
/*  374 */           extendScanningUpwardsFromAnnotationParameterValues(Array.get(annotationParamVal, i), log);
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
/*      */   private void extendScanningUpwards(LogNode log) {
/*  389 */     if (this.superclassName != null) {
/*  390 */       scheduleScanningIfExternalClass(this.superclassName, "superclass", log);
/*      */     }
/*      */     
/*  393 */     if (this.implementedInterfaces != null) {
/*  394 */       for (String interfaceName : this.implementedInterfaces) {
/*  395 */         scheduleScanningIfExternalClass(interfaceName, "interface", log);
/*      */       }
/*      */     }
/*      */     
/*  399 */     if (this.classAnnotations != null) {
/*  400 */       for (AnnotationInfo annotationInfo : this.classAnnotations) {
/*  401 */         scheduleScanningIfExternalClass(annotationInfo.getName(), "class annotation", log);
/*  402 */         extendScanningUpwardsFromAnnotationParameterValues(annotationInfo, log);
/*      */       } 
/*      */     }
/*      */     
/*  406 */     if (this.annotationParamDefaultValues != null) {
/*  407 */       for (AnnotationParameterValue apv : this.annotationParamDefaultValues) {
/*  408 */         extendScanningUpwardsFromAnnotationParameterValues(apv.getValue(), log);
/*      */       }
/*      */     }
/*      */     
/*  412 */     if (this.methodInfoList != null) {
/*  413 */       for (MethodInfo methodInfo : this.methodInfoList) {
/*  414 */         if (methodInfo.annotationInfo != null) {
/*  415 */           for (AnnotationInfo methodAnnotationInfo : methodInfo.annotationInfo) {
/*  416 */             scheduleScanningIfExternalClass(methodAnnotationInfo.getName(), "method annotation", log);
/*  417 */             extendScanningUpwardsFromAnnotationParameterValues(methodAnnotationInfo, log);
/*      */           } 
/*  419 */           if (methodInfo.parameterAnnotationInfo != null && methodInfo.parameterAnnotationInfo.length > 0)
/*      */           {
/*  421 */             for (AnnotationInfo[] paramAnnInfoArr : methodInfo.parameterAnnotationInfo) {
/*  422 */               if (paramAnnInfoArr != null && paramAnnInfoArr.length > 0) {
/*  423 */                 for (AnnotationInfo paramAnnInfo : paramAnnInfoArr) {
/*  424 */                   scheduleScanningIfExternalClass(paramAnnInfo.getName(), "method parameter annotation", log);
/*      */                   
/*  426 */                   extendScanningUpwardsFromAnnotationParameterValues(paramAnnInfo, log);
/*      */                 } 
/*      */               }
/*      */             } 
/*      */           }
/*      */         } 
/*  432 */         if (methodInfo.getThrownExceptionNames() != null) {
/*  433 */           for (String thrownExceptionName : methodInfo.getThrownExceptionNames()) {
/*  434 */             scheduleScanningIfExternalClass(thrownExceptionName, "method throws", log);
/*      */           }
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  440 */     if (this.fieldInfoList != null) {
/*  441 */       for (FieldInfo fieldInfo : this.fieldInfoList) {
/*  442 */         if (fieldInfo.annotationInfo != null) {
/*  443 */           for (AnnotationInfo fieldAnnotationInfo : fieldInfo.annotationInfo) {
/*  444 */             scheduleScanningIfExternalClass(fieldAnnotationInfo.getName(), "field annotation", log);
/*  445 */             extendScanningUpwardsFromAnnotationParameterValues(fieldAnnotationInfo, log);
/*      */           } 
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/*  451 */     if (this.classContainmentEntries != null) {
/*  452 */       for (ClassContainment classContainmentEntry : this.classContainmentEntries) {
/*  453 */         if (classContainmentEntry.innerClassName.equals(this.className)) {
/*  454 */           scheduleScanningIfExternalClass(classContainmentEntry.outerClassName, "outer class", log);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void link(Map<String, ClassInfo> classNameToClassInfo, Map<String, PackageInfo> packageNameToPackageInfo, Map<String, ModuleInfo> moduleNameToModuleInfo) {
/*  475 */     boolean isModuleDescriptor = false;
/*  476 */     boolean isPackageDescriptor = false;
/*  477 */     ClassInfo classInfo = null;
/*  478 */     if (this.className.equals("module-info")) {
/*  479 */       isModuleDescriptor = true;
/*      */     }
/*  481 */     else if (this.className.equals("package-info") || this.className.endsWith(".package-info")) {
/*  482 */       isPackageDescriptor = true;
/*      */     }
/*      */     else {
/*      */       
/*  486 */       classInfo = ClassInfo.addScannedClass(this.className, this.classModifiers, this.isExternalClass, classNameToClassInfo, this.classpathElement, this.classfileResource);
/*      */       
/*  488 */       classInfo.setClassfileVersion(this.minorVersion, this.majorVersion);
/*  489 */       classInfo.setModifiers(this.classModifiers);
/*  490 */       classInfo.setIsInterface(this.isInterface);
/*  491 */       classInfo.setIsAnnotation(this.isAnnotation);
/*  492 */       classInfo.setIsRecord(this.isRecord);
/*  493 */       classInfo.setSourceFile(this.sourceFile);
/*  494 */       if (this.superclassName != null) {
/*  495 */         classInfo.addSuperclass(this.superclassName, classNameToClassInfo);
/*      */       }
/*  497 */       if (this.implementedInterfaces != null) {
/*  498 */         for (String interfaceName : this.implementedInterfaces) {
/*  499 */           classInfo.addImplementedInterface(interfaceName, classNameToClassInfo);
/*      */         }
/*      */       }
/*  502 */       if (this.classAnnotations != null) {
/*  503 */         for (AnnotationInfo classAnnotation : this.classAnnotations) {
/*  504 */           classInfo.addClassAnnotation(classAnnotation, classNameToClassInfo);
/*      */         }
/*      */       }
/*  507 */       if (this.classContainmentEntries != null) {
/*  508 */         ClassInfo.addClassContainment(this.classContainmentEntries, classNameToClassInfo);
/*      */       }
/*  510 */       if (this.annotationParamDefaultValues != null) {
/*  511 */         classInfo.addAnnotationParamDefaultValues(this.annotationParamDefaultValues);
/*      */       }
/*  513 */       if (this.fullyQualifiedDefiningMethodName != null) {
/*  514 */         classInfo.addFullyQualifiedDefiningMethodName(this.fullyQualifiedDefiningMethodName);
/*      */       }
/*  516 */       if (this.fieldInfoList != null) {
/*  517 */         classInfo.addFieldInfo(this.fieldInfoList, classNameToClassInfo);
/*      */       }
/*  519 */       if (this.methodInfoList != null) {
/*  520 */         classInfo.addMethodInfo(this.methodInfoList, classNameToClassInfo);
/*      */       }
/*  522 */       if (this.typeSignatureStr != null) {
/*  523 */         classInfo.setTypeSignature(this.typeSignatureStr);
/*      */       }
/*  525 */       if (this.refdClassNames != null) {
/*  526 */         classInfo.addReferencedClassNames(this.refdClassNames);
/*      */       }
/*  528 */       if (this.classTypeAnnotationDecorators != null) {
/*  529 */         classInfo.addTypeDecorators(this.classTypeAnnotationDecorators);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  534 */     PackageInfo packageInfo = null;
/*  535 */     if (!isModuleDescriptor) {
/*      */       
/*  537 */       String packageName = PackageInfo.getParentPackageName(this.className);
/*  538 */       packageInfo = PackageInfo.getOrCreatePackage(packageName, packageNameToPackageInfo, this.scanSpec);
/*  539 */       if (isPackageDescriptor) {
/*      */         
/*  541 */         packageInfo.addAnnotations(this.classAnnotations);
/*  542 */       } else if (classInfo != null) {
/*      */         
/*  544 */         packageInfo.addClassInfo(classInfo);
/*  545 */         classInfo.packageInfo = packageInfo;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  550 */     String moduleName = this.classpathElement.getModuleName();
/*  551 */     if (moduleName != null) {
/*      */       
/*  553 */       ModuleInfo moduleInfo = moduleNameToModuleInfo.get(moduleName);
/*  554 */       if (moduleInfo == null) {
/*  555 */         moduleNameToModuleInfo.put(moduleName, 
/*  556 */             moduleInfo = new ModuleInfo(this.classfileResource.getModuleRef(), this.classpathElement));
/*      */       }
/*  558 */       if (isModuleDescriptor)
/*      */       {
/*  560 */         moduleInfo.addAnnotations(this.classAnnotations);
/*      */       }
/*  562 */       if (classInfo != null) {
/*      */         
/*  564 */         moduleInfo.addClassInfo(classInfo);
/*  565 */         classInfo.moduleInfo = moduleInfo;
/*      */       } 
/*  567 */       if (packageInfo != null)
/*      */       {
/*  569 */         moduleInfo.addPackageInfo(packageInfo);
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
/*      */   private String intern(String str) {
/*  584 */     if (str == null) {
/*  585 */       return null;
/*      */     }
/*  587 */     String interned = this.stringInternMap.putIfAbsent(str, str);
/*  588 */     if (interned != null) {
/*  589 */       return interned;
/*      */     }
/*  591 */     return str;
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
/*      */   private int getConstantPoolStringOffset(int cpIdx, int subFieldIdx) throws ClassfileFormatException {
/*      */     int cpIdxToUse;
/*  608 */     if (cpIdx < 1 || cpIdx >= this.cpCount) {
/*  609 */       throw new ClassfileFormatException("Constant pool index " + cpIdx + ", should be in range [1, " + (this.cpCount - 1) + "] -- cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */     }
/*      */ 
/*      */     
/*  613 */     int t = this.entryTag[cpIdx];
/*  614 */     if ((t != 12 && subFieldIdx != 0) || (t == 12 && subFieldIdx != 0 && subFieldIdx != 1)) {
/*  615 */       throw new ClassfileFormatException("Bad subfield index " + subFieldIdx + " for tag " + t + ", cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  620 */     if (t == 0)
/*      */     {
/*  622 */       return 0; } 
/*  623 */     if (t == 1) {
/*      */       
/*  625 */       cpIdxToUse = cpIdx;
/*  626 */     } else if (t == 7 || t == 8 || t == 19) {
/*      */ 
/*      */       
/*  629 */       int indirIdx = this.indirectStringRefs[cpIdx];
/*  630 */       if (indirIdx == -1)
/*      */       {
/*  632 */         throw new ClassfileFormatException("Bad string indirection index, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */       }
/*      */       
/*  635 */       if (indirIdx == 0)
/*      */       {
/*  637 */         return 0;
/*      */       }
/*  639 */       cpIdxToUse = indirIdx;
/*  640 */     } else if (t == 12) {
/*      */       
/*  642 */       int compoundIndirIdx = this.indirectStringRefs[cpIdx];
/*  643 */       if (compoundIndirIdx == -1)
/*      */       {
/*  645 */         throw new ClassfileFormatException("Bad string indirection index, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */       }
/*      */       
/*  648 */       int indirIdx = ((subFieldIdx == 0) ? (compoundIndirIdx >> 16) : compoundIndirIdx) & 0xFFFF;
/*  649 */       if (indirIdx == 0)
/*      */       {
/*  651 */         throw new ClassfileFormatException("Bad string indirection index, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */       }
/*      */       
/*  654 */       cpIdxToUse = indirIdx;
/*      */     } else {
/*  656 */       throw new ClassfileFormatException("Wrong tag number " + t + " at constant pool index " + cpIdx + ", cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */     } 
/*      */ 
/*      */     
/*  660 */     if (cpIdxToUse < 1 || cpIdxToUse >= this.cpCount) {
/*  661 */       throw new ClassfileFormatException("Constant pool index " + cpIdx + ", should be in range [1, " + (this.cpCount - 1) + "] -- cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */     }
/*      */ 
/*      */     
/*  665 */     return this.entryOffset[cpIdxToUse];
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
/*      */   private String getConstantPoolString(int cpIdx, boolean replaceSlashWithDot, boolean stripLSemicolon) throws ClassfileFormatException, IOException {
/*  686 */     int constantPoolStringOffset = getConstantPoolStringOffset(cpIdx, 0);
/*  687 */     if (constantPoolStringOffset == 0) {
/*  688 */       return null;
/*      */     }
/*  690 */     int utfLen = this.reader.readUnsignedShort(constantPoolStringOffset);
/*  691 */     if (utfLen == 0) {
/*  692 */       return "";
/*      */     }
/*  694 */     return intern(this.reader
/*  695 */         .readString(constantPoolStringOffset + 2L, utfLen, replaceSlashWithDot, stripLSemicolon));
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
/*      */   private String getConstantPoolString(int cpIdx, int subFieldIdx) throws ClassfileFormatException, IOException {
/*  714 */     int constantPoolStringOffset = getConstantPoolStringOffset(cpIdx, subFieldIdx);
/*  715 */     if (constantPoolStringOffset == 0) {
/*  716 */       return null;
/*      */     }
/*  718 */     int utfLen = this.reader.readUnsignedShort(constantPoolStringOffset);
/*  719 */     if (utfLen == 0) {
/*  720 */       return "";
/*      */     }
/*  722 */     return intern(this.reader.readString(constantPoolStringOffset + 2L, utfLen, false, false));
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
/*      */   private String getConstantPoolString(int cpIdx) throws ClassfileFormatException, IOException {
/*  738 */     return getConstantPoolString(cpIdx, 0);
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
/*      */   private byte getConstantPoolStringFirstByte(int cpIdx) throws ClassfileFormatException, IOException {
/*  753 */     int constantPoolStringOffset = getConstantPoolStringOffset(cpIdx, 0);
/*  754 */     if (constantPoolStringOffset == 0) {
/*  755 */       return 0;
/*      */     }
/*  757 */     int utfLen = this.reader.readUnsignedShort(constantPoolStringOffset);
/*  758 */     if (utfLen == 0) {
/*  759 */       return 0;
/*      */     }
/*  761 */     return this.reader.readByte(constantPoolStringOffset + 2L);
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
/*      */   private String getConstantPoolClassName(int cpIdx) throws ClassfileFormatException, IOException {
/*  776 */     return getConstantPoolString(cpIdx, true, false);
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
/*      */   private String getConstantPoolClassDescriptor(int cpIdx) throws ClassfileFormatException, IOException {
/*  793 */     return getConstantPoolString(cpIdx, true, true);
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
/*      */   private boolean constantPoolStringEquals(int cpIdx, String asciiStr) throws ClassfileFormatException, IOException {
/*  812 */     int cpStrOffset = getConstantPoolStringOffset(cpIdx, 0);
/*  813 */     if (cpStrOffset == 0)
/*  814 */       return (asciiStr == null); 
/*  815 */     if (asciiStr == null) {
/*  816 */       return false;
/*      */     }
/*  818 */     int cpStrLen = this.reader.readUnsignedShort(cpStrOffset);
/*  819 */     int asciiStrLen = asciiStr.length();
/*  820 */     if (cpStrLen != asciiStrLen) {
/*  821 */       return false;
/*      */     }
/*  823 */     int cpStrStart = cpStrOffset + 2;
/*  824 */     this.reader.bufferTo(cpStrStart + cpStrLen);
/*  825 */     byte[] buf = this.reader.buf();
/*  826 */     for (int i = 0; i < cpStrLen; i++) {
/*  827 */       if ((char)(buf[cpStrStart + i] & 0xFF) != asciiStr.charAt(i)) {
/*  828 */         return false;
/*      */       }
/*      */     } 
/*  831 */     return true;
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
/*      */   private int cpReadInt(int cpIdx) throws IOException {
/*  846 */     if (cpIdx < 1 || cpIdx >= this.cpCount) {
/*  847 */       throw new ClassfileFormatException("Constant pool index " + cpIdx + ", should be in range [1, " + (this.cpCount - 1) + "] -- cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */     }
/*      */ 
/*      */     
/*  851 */     return this.reader.readInt(this.entryOffset[cpIdx]);
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
/*      */   private long cpReadLong(int cpIdx) throws IOException {
/*  864 */     if (cpIdx < 1 || cpIdx >= this.cpCount) {
/*  865 */       throw new ClassfileFormatException("Constant pool index " + cpIdx + ", should be in range [1, " + (this.cpCount - 1) + "] -- cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */     }
/*      */ 
/*      */     
/*  869 */     return this.reader.readLong(this.entryOffset[cpIdx]);
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
/*      */   private Object getFieldConstantPoolValue(int tag, char fieldTypeDescriptorFirstChar, int cpIdx) throws ClassfileFormatException, IOException {
/*      */     int intVal;
/*  891 */     switch (tag) {
/*      */       
/*      */       case 1:
/*      */       case 7:
/*      */       case 8:
/*  896 */         return getConstantPoolString(cpIdx);
/*      */       case 3:
/*  898 */         intVal = cpReadInt(cpIdx);
/*  899 */         switch (fieldTypeDescriptorFirstChar) {
/*      */           case 'I':
/*  901 */             return Integer.valueOf(intVal);
/*      */           case 'S':
/*  903 */             return Short.valueOf((short)intVal);
/*      */           case 'C':
/*  905 */             return Character.valueOf((char)intVal);
/*      */           case 'B':
/*  907 */             return Byte.valueOf((byte)intVal);
/*      */           case 'Z':
/*  909 */             return Boolean.valueOf((intVal != 0));
/*      */         } 
/*      */ 
/*      */         
/*  913 */         throw new ClassfileFormatException("Unknown Constant_INTEGER type " + fieldTypeDescriptorFirstChar + ", cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */ 
/*      */       
/*      */       case 4:
/*  917 */         return Float.valueOf(Float.intBitsToFloat(cpReadInt(cpIdx)));
/*      */       case 5:
/*  919 */         return Long.valueOf(cpReadLong(cpIdx));
/*      */       case 6:
/*  921 */         return Double.valueOf(Double.longBitsToDouble(cpReadLong(cpIdx)));
/*      */     } 
/*      */ 
/*      */     
/*  925 */     throw new ClassfileFormatException("Unknown field constant pool tag " + tag + ", cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
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
/*      */   private AnnotationInfo readAnnotation() throws IOException {
/*  942 */     String annotationClassName = getConstantPoolClassDescriptor(this.reader.readUnsignedShort());
/*  943 */     int numElementValuePairs = this.reader.readUnsignedShort();
/*  944 */     AnnotationParameterValueList paramVals = null;
/*  945 */     if (numElementValuePairs > 0) {
/*  946 */       paramVals = new AnnotationParameterValueList(numElementValuePairs);
/*  947 */       for (int i = 0; i < numElementValuePairs; i++) {
/*  948 */         String paramName = getConstantPoolString(this.reader.readUnsignedShort());
/*  949 */         Object paramValue = readAnnotationElementValue();
/*  950 */         paramVals.add(new AnnotationParameterValue(paramName, paramValue));
/*      */       } 
/*      */     } 
/*  953 */     return new AnnotationInfo(annotationClassName, paramVals);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Object readAnnotationElementValue() throws IOException {
/*      */     String annotationClassName, classRefTypeDescriptor, annotationConstName;
/*      */     int count;
/*      */     Object[] arr;
/*  964 */     int i, tag = (char)this.reader.readUnsignedByte();
/*  965 */     switch (tag) {
/*      */       case 66:
/*  967 */         return Byte.valueOf((byte)cpReadInt(this.reader.readUnsignedShort()));
/*      */       case 67:
/*  969 */         return Character.valueOf((char)cpReadInt(this.reader.readUnsignedShort()));
/*      */       case 68:
/*  971 */         return Double.valueOf(Double.longBitsToDouble(cpReadLong(this.reader.readUnsignedShort())));
/*      */       case 70:
/*  973 */         return Float.valueOf(Float.intBitsToFloat(cpReadInt(this.reader.readUnsignedShort())));
/*      */       case 73:
/*  975 */         return Integer.valueOf(cpReadInt(this.reader.readUnsignedShort()));
/*      */       case 74:
/*  977 */         return Long.valueOf(cpReadLong(this.reader.readUnsignedShort()));
/*      */       case 83:
/*  979 */         return Short.valueOf((short)cpReadInt(this.reader.readUnsignedShort()));
/*      */       case 90:
/*  981 */         return Boolean.valueOf((cpReadInt(this.reader.readUnsignedShort()) != 0));
/*      */       case 115:
/*  983 */         return getConstantPoolString(this.reader.readUnsignedShort());
/*      */       
/*      */       case 101:
/*  986 */         annotationClassName = getConstantPoolClassDescriptor(this.reader.readUnsignedShort());
/*  987 */         annotationConstName = getConstantPoolString(this.reader.readUnsignedShort());
/*  988 */         return new AnnotationEnumValue(annotationClassName, annotationConstName);
/*      */ 
/*      */       
/*      */       case 99:
/*  992 */         classRefTypeDescriptor = getConstantPoolString(this.reader.readUnsignedShort());
/*  993 */         return new AnnotationClassRef(classRefTypeDescriptor);
/*      */       
/*      */       case 64:
/*  996 */         return readAnnotation();
/*      */       
/*      */       case 91:
/*  999 */         count = this.reader.readUnsignedShort();
/* 1000 */         arr = new Object[count];
/* 1001 */         for (i = 0; i < count; i++)
/*      */         {
/* 1003 */           arr[i] = readAnnotationElementValue();
/*      */         }
/* 1005 */         return arr;
/*      */     } 
/* 1007 */     throw new ClassfileFormatException("Class " + this.className + " has unknown annotation element type tag '" + (char)tag + "': element size unknown, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class TypePathNode
/*      */   {
/*      */     short typePathKind;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     short typeArgumentIdx;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public TypePathNode(int typePathKind, int typeArgumentIdx) {
/* 1032 */       this.typePathKind = (short)typePathKind;
/* 1033 */       this.typeArgumentIdx = (short)typeArgumentIdx;
/*      */     }
/*      */ 
/*      */     
/*      */     public String toString() {
/* 1038 */       return "(" + this.typePathKind + "," + this.typeArgumentIdx + ")";
/*      */     }
/*      */   }
/*      */   
/*      */   private List<TypePathNode> readTypePath() throws IOException {
/* 1043 */     int typePathLength = this.reader.readUnsignedByte();
/* 1044 */     if (typePathLength == 0) {
/* 1045 */       return Collections.emptyList();
/*      */     }
/* 1047 */     List<TypePathNode> list = new ArrayList<>(typePathLength);
/* 1048 */     for (int i = 0; i < typePathLength; i++) {
/* 1049 */       int typePathKind = this.reader.readUnsignedByte();
/* 1050 */       int typeArgumentIdx = this.reader.readUnsignedByte();
/* 1051 */       list.add(new TypePathNode(typePathKind, typeArgumentIdx));
/*      */     } 
/* 1053 */     return list;
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
/*      */   private void readConstantPoolEntries(LogNode log) throws IOException {
/* 1069 */     List<Integer> classNameCpIdxs = null;
/* 1070 */     List<Integer> typeSignatureIdxs = null;
/* 1071 */     if (this.scanSpec.enableInterClassDependencies) {
/* 1072 */       classNameCpIdxs = new ArrayList<>();
/* 1073 */       typeSignatureIdxs = new ArrayList<>();
/*      */     } 
/*      */ 
/*      */     
/* 1077 */     this.cpCount = this.reader.readUnsignedShort();
/*      */ 
/*      */     
/* 1080 */     this.entryOffset = new int[this.cpCount];
/* 1081 */     this.entryTag = new int[this.cpCount];
/* 1082 */     this.indirectStringRefs = new int[this.cpCount];
/* 1083 */     Arrays.fill(this.indirectStringRefs, 0, this.cpCount, -1);
/*      */ 
/*      */     
/* 1086 */     for (int i = 1, skipSlot = 0; i < this.cpCount; i++) {
/* 1087 */       if (skipSlot == 1) {
/*      */         
/* 1089 */         skipSlot = 0;
/*      */       } else {
/*      */         int strLen, nameRef, typeRef;
/* 1092 */         this.entryTag[i] = this.reader.readUnsignedByte();
/* 1093 */         this.entryOffset[i] = this.reader.currPos();
/* 1094 */         switch (this.entryTag[i]) {
/*      */           case 0:
/* 1096 */             throw new ClassfileFormatException("Invalid constant pool tag 0 in classfile " + this.relativePath + " (possible buffer underflow issue). Please report this at https://github.com/classgraph/classgraph/issues");
/*      */ 
/*      */           
/*      */           case 1:
/* 1100 */             strLen = this.reader.readUnsignedShort();
/* 1101 */             this.reader.skip(strLen);
/*      */             break;
/*      */           
/*      */           case 3:
/*      */           case 4:
/* 1106 */             this.reader.skip(4);
/*      */             break;
/*      */           case 5:
/*      */           case 6:
/* 1110 */             this.reader.skip(8);
/* 1111 */             skipSlot = 1;
/*      */             break;
/*      */           
/*      */           case 7:
/* 1115 */             this.indirectStringRefs[i] = this.reader.readUnsignedShort();
/* 1116 */             if (classNameCpIdxs != null)
/*      */             {
/* 1118 */               classNameCpIdxs.add(Integer.valueOf(this.indirectStringRefs[i]));
/*      */             }
/*      */             break;
/*      */           
/*      */           case 8:
/* 1123 */             this.indirectStringRefs[i] = this.reader.readUnsignedShort();
/*      */             break;
/*      */           
/*      */           case 9:
/* 1127 */             this.reader.skip(4);
/*      */             break;
/*      */           
/*      */           case 10:
/* 1131 */             this.reader.skip(4);
/*      */             break;
/*      */           
/*      */           case 11:
/* 1135 */             this.reader.skip(4);
/*      */             break;
/*      */           case 12:
/* 1138 */             nameRef = this.reader.readUnsignedShort();
/* 1139 */             typeRef = this.reader.readUnsignedShort();
/* 1140 */             if (typeSignatureIdxs != null) {
/* 1141 */               typeSignatureIdxs.add(Integer.valueOf(typeRef));
/*      */             }
/* 1143 */             this.indirectStringRefs[i] = nameRef << 16 | typeRef;
/*      */             break;
/*      */           
/*      */           case 15:
/* 1147 */             this.reader.skip(3);
/*      */             break;
/*      */           case 16:
/* 1150 */             this.reader.skip(2);
/*      */             break;
/*      */           case 17:
/* 1153 */             this.reader.skip(4);
/*      */             break;
/*      */           case 18:
/* 1156 */             this.reader.skip(4);
/*      */             break;
/*      */           
/*      */           case 19:
/* 1160 */             this.indirectStringRefs[i] = this.reader.readUnsignedShort();
/*      */             break;
/*      */           
/*      */           case 20:
/* 1164 */             this.reader.skip(2);
/*      */             break;
/*      */           default:
/* 1167 */             throw new ClassfileFormatException("Unknown constant pool tag " + this.entryTag[i] + " (element size unknown, cannot continue reading class). Please report this at https://github.com/classgraph/classgraph/issues");
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/*      */     } 
/* 1178 */     if (classNameCpIdxs != null) {
/* 1179 */       this.refdClassNames = new HashSet<>();
/*      */       
/* 1181 */       for (Iterator<Integer> iterator = classNameCpIdxs.iterator(); iterator.hasNext(); ) { int cpIdx = ((Integer)iterator.next()).intValue();
/* 1182 */         String refdClassName = getConstantPoolString(cpIdx, true, false);
/*      */         
/* 1184 */         if (refdClassName != null) {
/* 1185 */           if (refdClassName.startsWith("[")) {
/*      */             
/*      */             try {
/* 1188 */               TypeSignature typeSig = TypeSignature.parse(refdClassName.replace('.', '/'), (String)null);
/*      */               
/* 1190 */               typeSig.findReferencedClassNames(this.refdClassNames);
/* 1191 */             } catch (ParseException e) {
/*      */               
/* 1193 */               throw new ClassfileFormatException("Could not parse class name: " + refdClassName, e);
/*      */             }  continue;
/*      */           } 
/* 1196 */           this.refdClassNames.add(refdClassName);
/*      */         }  }
/*      */     
/*      */     } 
/*      */     
/* 1201 */     if (typeSignatureIdxs != null)
/*      */     {
/* 1203 */       for (Iterator<Integer> iterator = typeSignatureIdxs.iterator(); iterator.hasNext(); ) { int cpIdx = ((Integer)iterator.next()).intValue();
/* 1204 */         String typeSigStr = getConstantPoolString(cpIdx);
/* 1205 */         if (typeSigStr != null) {
/*      */           try {
/* 1207 */             if (typeSigStr.startsWith("L") && typeSigStr.endsWith(";")) {
/*      */               
/* 1209 */               TypeSignature typeSig = TypeSignature.parse(typeSigStr, (String)null);
/*      */ 
/*      */               
/* 1212 */               typeSig.findReferencedClassNames(this.refdClassNames); continue;
/* 1213 */             }  if (typeSigStr.indexOf('(') >= 0 || "<init>".equals(typeSigStr)) {
/*      */               
/* 1215 */               MethodTypeSignature typeSig = MethodTypeSignature.parse(typeSigStr, null);
/*      */ 
/*      */               
/* 1218 */               typeSig.findReferencedClassNames(this.refdClassNames); continue;
/*      */             } 
/* 1220 */             if (log != null) {
/* 1221 */               log.log("Could not extract referenced class names from constant pool string: " + typeSigStr);
/*      */             
/*      */             }
/*      */           }
/* 1225 */           catch (ParseException e) {
/* 1226 */             if (log != null) {
/* 1227 */               log.log("Could not extract referenced class names from constant pool string: " + typeSigStr + " : " + e);
/*      */             }
/*      */           } 
/*      */         } }
/*      */     
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
/*      */   private void readBasicClassInfo() throws IOException, ClassfileFormatException, SkipClassException {
/* 1251 */     this.classModifiers = this.reader.readUnsignedShort();
/*      */     
/* 1253 */     this.isInterface = ((this.classModifiers & 0x200) != 0);
/* 1254 */     this.isAnnotation = ((this.classModifiers & 0x2000) != 0);
/*      */ 
/*      */     
/* 1257 */     String classNamePath = getConstantPoolString(this.reader.readUnsignedShort());
/* 1258 */     if (classNamePath == null) {
/* 1259 */       throw new ClassfileFormatException("Class name is null");
/*      */     }
/* 1261 */     this.className = classNamePath.replace('/', '.');
/* 1262 */     if ("java.lang.Object".equals(this.className))
/*      */     {
/*      */       
/* 1265 */       throw new SkipClassException("No need to scan java.lang.Object");
/*      */     }
/*      */ 
/*      */     
/* 1269 */     boolean isModule = ((this.classModifiers & 0x8000) != 0);
/* 1270 */     boolean isPackage = this.relativePath.regionMatches(this.relativePath.lastIndexOf('/') + 1, "package-info.class", 0, 18);
/*      */     
/* 1272 */     if (!this.scanSpec.ignoreClassVisibility && !Modifier.isPublic(this.classModifiers) && !isModule && !isPackage) {
/* 1273 */       throw new SkipClassException("Class is not public, and ignoreClassVisibility() was not called");
/*      */     }
/*      */ 
/*      */     
/* 1277 */     if (!this.relativePath.endsWith(".class"))
/*      */     {
/* 1279 */       throw new SkipClassException("Classfile filename " + this.relativePath + " does not end in \".class\"");
/*      */     }
/* 1281 */     int len = classNamePath.length();
/* 1282 */     if (this.relativePath.length() != len + 6 || !classNamePath.regionMatches(0, this.relativePath, 0, len)) {
/* 1283 */       throw new SkipClassException("Relative path " + this.relativePath + " does not match class name " + this.className);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1288 */     int superclassNameCpIdx = this.reader.readUnsignedShort();
/* 1289 */     if (superclassNameCpIdx > 0) {
/* 1290 */       this.superclassName = getConstantPoolClassName(superclassNameCpIdx);
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
/*      */   private void readInterfaces() throws IOException {
/* 1304 */     int interfaceCount = this.reader.readUnsignedShort();
/* 1305 */     for (int i = 0; i < interfaceCount; i++) {
/* 1306 */       String interfaceName = getConstantPoolClassName(this.reader.readUnsignedShort());
/* 1307 */       if (this.implementedInterfaces == null) {
/* 1308 */         this.implementedInterfaces = new ArrayList<>();
/*      */       }
/* 1310 */       this.implementedInterfaces.add(interfaceName);
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
/*      */   private void readFields() throws IOException, ClassfileFormatException {
/* 1326 */     int fieldCount = this.reader.readUnsignedShort();
/* 1327 */     for (int i = 0; i < fieldCount; i++) {
/*      */       
/* 1329 */       int fieldModifierFlags = this.reader.readUnsignedShort();
/* 1330 */       boolean isPublicField = ((fieldModifierFlags & 0x1) == 1);
/* 1331 */       boolean fieldIsVisible = (isPublicField || this.scanSpec.ignoreFieldVisibility);
/* 1332 */       boolean getStaticFinalFieldConstValue = (this.scanSpec.enableStaticFinalFieldConstantInitializerValues && fieldIsVisible);
/*      */       
/* 1334 */       List<TypeAnnotationDecorator> fieldTypeAnnotationDecorators = null;
/* 1335 */       if (!fieldIsVisible || (!this.scanSpec.enableFieldInfo && !getStaticFinalFieldConstValue)) {
/*      */         
/* 1337 */         this.reader.readUnsignedShort();
/* 1338 */         this.reader.readUnsignedShort();
/* 1339 */         int attributesCount = this.reader.readUnsignedShort();
/* 1340 */         for (int j = 0; j < attributesCount; j++) {
/* 1341 */           this.reader.readUnsignedShort();
/* 1342 */           int attributeLength = this.reader.readInt();
/* 1343 */           this.reader.skip(attributeLength);
/*      */         } 
/*      */       } else {
/* 1346 */         int fieldNameCpIdx = this.reader.readUnsignedShort();
/* 1347 */         String fieldName = getConstantPoolString(fieldNameCpIdx);
/* 1348 */         int fieldTypeDescriptorCpIdx = this.reader.readUnsignedShort();
/* 1349 */         char fieldTypeDescriptorFirstChar = (char)getConstantPoolStringFirstByte(fieldTypeDescriptorCpIdx);
/*      */ 
/*      */         
/* 1352 */         String fieldTypeSignatureStr = null;
/* 1353 */         String fieldTypeDescriptor = getConstantPoolString(fieldTypeDescriptorCpIdx);
/*      */         
/* 1355 */         Object fieldConstValue = null;
/* 1356 */         AnnotationInfoList fieldAnnotationInfo = null;
/* 1357 */         int attributesCount = this.reader.readUnsignedShort();
/* 1358 */         for (int j = 0; j < attributesCount; j++) {
/* 1359 */           int attributeNameCpIdx = this.reader.readUnsignedShort();
/* 1360 */           int attributeLength = this.reader.readInt();
/*      */ 
/*      */           
/* 1363 */           if (getStaticFinalFieldConstValue && 
/* 1364 */             constantPoolStringEquals(attributeNameCpIdx, "ConstantValue")) {
/*      */             
/* 1366 */             int cpIdx = this.reader.readUnsignedShort();
/* 1367 */             if (cpIdx < 1 || cpIdx >= this.cpCount) {
/* 1368 */               throw new ClassfileFormatException("Constant pool index " + cpIdx + ", should be in range [1, " + (this.cpCount - 1) + "] -- cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1373 */             fieldConstValue = getFieldConstantPoolValue(this.entryTag[cpIdx], fieldTypeDescriptorFirstChar, cpIdx);
/*      */           }
/* 1375 */           else if (fieldIsVisible && constantPoolStringEquals(attributeNameCpIdx, "Signature")) {
/* 1376 */             fieldTypeSignatureStr = getConstantPoolString(this.reader.readUnsignedShort());
/* 1377 */           } else if (this.scanSpec.enableAnnotationInfo && (
/* 1378 */             constantPoolStringEquals(attributeNameCpIdx, "RuntimeVisibleAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && 
/* 1379 */             constantPoolStringEquals(attributeNameCpIdx, "RuntimeInvisibleAnnotations")))) {
/*      */ 
/*      */             
/* 1382 */             int fieldAnnotationCount = this.reader.readUnsignedShort();
/* 1383 */             if (fieldAnnotationCount > 0) {
/* 1384 */               if (fieldAnnotationInfo == null) {
/* 1385 */                 fieldAnnotationInfo = new AnnotationInfoList(1);
/*      */               }
/* 1387 */               for (int k = 0; k < fieldAnnotationCount; k++) {
/* 1388 */                 AnnotationInfo fieldAnnotation = readAnnotation();
/* 1389 */                 fieldAnnotationInfo.add(fieldAnnotation);
/*      */               } 
/*      */             } 
/* 1392 */           } else if (this.scanSpec.enableAnnotationInfo && (
/* 1393 */             constantPoolStringEquals(attributeNameCpIdx, "RuntimeVisibleTypeAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && 
/* 1394 */             constantPoolStringEquals(attributeNameCpIdx, "RuntimeInvisibleTypeAnnotations")))) {
/*      */             
/* 1396 */             int annotationCount = this.reader.readUnsignedShort();
/* 1397 */             if (annotationCount > 0) {
/* 1398 */               fieldTypeAnnotationDecorators = new ArrayList<>();
/* 1399 */               for (int m = 0; m < annotationCount; m++) {
/* 1400 */                 int targetType = this.reader.readUnsignedByte();
/* 1401 */                 if (targetType != 19) {
/* 1402 */                   throw new ClassfileFormatException("Class " + this.className + " has unknown field type annotation target 0x" + 
/*      */                       
/* 1404 */                       Integer.toHexString(targetType) + ": element size unknown, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */                 }
/*      */ 
/*      */ 
/*      */                 
/* 1409 */                 final List<TypePathNode> typePath = readTypePath();
/* 1410 */                 final AnnotationInfo annotationInfo = readAnnotation();
/* 1411 */                 fieldTypeAnnotationDecorators.add(new TypeAnnotationDecorator()
/*      */                     {
/*      */                       public void decorate(TypeSignature typeSignature) {
/* 1414 */                         typeSignature.addTypeAnnotation(typePath, annotationInfo);
/*      */                       }
/*      */                     });
/*      */               } 
/*      */             } 
/*      */           } else {
/*      */             
/* 1421 */             this.reader.skip(attributeLength);
/*      */           } 
/*      */         } 
/* 1424 */         if (this.scanSpec.enableFieldInfo && fieldIsVisible) {
/* 1425 */           if (this.fieldInfoList == null) {
/* 1426 */             this.fieldInfoList = new FieldInfoList();
/*      */           }
/* 1428 */           this.fieldInfoList.add(new FieldInfo(this.className, fieldName, fieldModifierFlags, fieldTypeDescriptor, fieldTypeSignatureStr, fieldConstValue, fieldAnnotationInfo, fieldTypeAnnotationDecorators));
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
/*      */ 
/*      */ 
/*      */   
/*      */   private void readMethods() throws IOException, ClassfileFormatException {
/* 1448 */     int methodCount = this.reader.readUnsignedShort();
/* 1449 */     for (int i = 0; i < methodCount; i++) {
/*      */       
/* 1451 */       int methodModifierFlags = this.reader.readUnsignedShort();
/* 1452 */       boolean isPublicMethod = ((methodModifierFlags & 0x1) == 1);
/* 1453 */       boolean methodIsVisible = (isPublicMethod || this.scanSpec.ignoreMethodVisibility);
/* 1454 */       List<MethodTypeAnnotationDecorator> methodTypeAnnotationDecorators = null;
/* 1455 */       String methodName = null;
/* 1456 */       String methodTypeDescriptor = null;
/* 1457 */       String methodTypeSignatureStr = null;
/*      */       
/* 1459 */       boolean enableMethodInfo = (this.scanSpec.enableMethodInfo || this.isAnnotation);
/* 1460 */       if (enableMethodInfo || this.isAnnotation) {
/* 1461 */         int methodNameCpIdx = this.reader.readUnsignedShort();
/* 1462 */         methodName = getConstantPoolString(methodNameCpIdx);
/* 1463 */         int methodTypeDescriptorCpIdx = this.reader.readUnsignedShort();
/* 1464 */         methodTypeDescriptor = getConstantPoolString(methodTypeDescriptorCpIdx);
/*      */       } else {
/* 1466 */         this.reader.skip(4);
/*      */       } 
/* 1468 */       int attributesCount = this.reader.readUnsignedShort();
/* 1469 */       String[] methodParameterNames = null;
/* 1470 */       String[] thrownExceptionNames = null;
/* 1471 */       int[] methodParameterModifiers = null;
/* 1472 */       AnnotationInfo[][] methodParameterAnnotations = null;
/* 1473 */       AnnotationInfoList methodAnnotationInfo = null;
/* 1474 */       boolean methodHasBody = false;
/* 1475 */       int minLineNum = 0;
/* 1476 */       int maxLineNum = 0;
/* 1477 */       if (!methodIsVisible || (!enableMethodInfo && !this.isAnnotation)) {
/*      */         
/* 1479 */         for (int j = 0; j < attributesCount; j++) {
/* 1480 */           this.reader.skip(2);
/* 1481 */           int attributeLength = this.reader.readInt();
/* 1482 */           this.reader.skip(attributeLength);
/*      */         } 
/*      */       } else {
/*      */         
/* 1486 */         for (int j = 0; j < attributesCount; j++) {
/* 1487 */           int attributeNameCpIdx = this.reader.readUnsignedShort();
/* 1488 */           int attributeLength = this.reader.readInt();
/* 1489 */           if (this.scanSpec.enableAnnotationInfo && (
/* 1490 */             constantPoolStringEquals(attributeNameCpIdx, "RuntimeVisibleAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && 
/* 1491 */             constantPoolStringEquals(attributeNameCpIdx, "RuntimeInvisibleAnnotations")))) {
/*      */             
/* 1493 */             int methodAnnotationCount = this.reader.readUnsignedShort();
/* 1494 */             if (methodAnnotationCount > 0) {
/* 1495 */               if (methodAnnotationInfo == null) {
/* 1496 */                 methodAnnotationInfo = new AnnotationInfoList(1);
/*      */               }
/* 1498 */               for (int k = 0; k < methodAnnotationCount; k++) {
/* 1499 */                 final AnnotationInfo annotationInfo = readAnnotation();
/* 1500 */                 methodAnnotationInfo.add(annotationInfo);
/*      */               } 
/*      */             } 
/* 1503 */           } else if (this.scanSpec.enableAnnotationInfo && (
/* 1504 */             constantPoolStringEquals(attributeNameCpIdx, "RuntimeVisibleParameterAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && 
/* 1505 */             constantPoolStringEquals(attributeNameCpIdx, "RuntimeInvisibleParameterAnnotations")))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1512 */             int numParams = this.reader.readUnsignedByte();
/* 1513 */             if (methodParameterAnnotations == null) {
/* 1514 */               methodParameterAnnotations = new AnnotationInfo[numParams][];
/* 1515 */             } else if (methodParameterAnnotations.length != numParams) {
/* 1516 */               throw new ClassfileFormatException("Mismatch in number of parameters between RuntimeVisibleParameterAnnotations and RuntimeInvisibleParameterAnnotations");
/*      */             } 
/*      */ 
/*      */             
/* 1520 */             for (int paramIdx = 0; paramIdx < numParams; paramIdx++) {
/* 1521 */               int numAnnotations = this.reader.readUnsignedShort();
/* 1522 */               if (numAnnotations > 0) {
/* 1523 */                 int annStartIdx = 0;
/* 1524 */                 if (methodParameterAnnotations[paramIdx] != null) {
/* 1525 */                   annStartIdx = (methodParameterAnnotations[paramIdx]).length;
/* 1526 */                   methodParameterAnnotations[paramIdx] = Arrays.<AnnotationInfo>copyOf(methodParameterAnnotations[paramIdx], annStartIdx + numAnnotations);
/*      */                 } else {
/*      */                   
/* 1529 */                   methodParameterAnnotations[paramIdx] = new AnnotationInfo[numAnnotations];
/*      */                 } 
/* 1531 */                 for (int annIdx = 0; annIdx < numAnnotations; annIdx++) {
/* 1532 */                   methodParameterAnnotations[paramIdx][annStartIdx + annIdx] = readAnnotation();
/*      */                 }
/* 1534 */               } else if (methodParameterAnnotations[paramIdx] == null) {
/* 1535 */                 methodParameterAnnotations[paramIdx] = NO_ANNOTATIONS;
/*      */               } 
/*      */             } 
/* 1538 */           } else if (this.scanSpec.enableAnnotationInfo && (
/* 1539 */             constantPoolStringEquals(attributeNameCpIdx, "RuntimeVisibleTypeAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && 
/* 1540 */             constantPoolStringEquals(attributeNameCpIdx, "RuntimeInvisibleTypeAnnotations")))) {
/*      */             
/* 1542 */             int annotationCount = this.reader.readUnsignedShort();
/* 1543 */             if (annotationCount > 0) {
/* 1544 */               methodTypeAnnotationDecorators = new ArrayList<>(annotationCount);
/* 1545 */               for (int m = 0; m < annotationCount; m++) {
/* 1546 */                 final int typeParameterIndex, boundIndex, formalParameterIndex, throwsTypeIndex, targetType = this.reader.readUnsignedByte();
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1551 */                 if (targetType == 1) {
/*      */                   
/* 1553 */                   typeParameterIndex = this.reader.readUnsignedByte();
/* 1554 */                   boundIndex = -1;
/* 1555 */                   formalParameterIndex = -1;
/* 1556 */                   throwsTypeIndex = -1;
/* 1557 */                 } else if (targetType == 18) {
/*      */ 
/*      */                   
/* 1560 */                   typeParameterIndex = this.reader.readUnsignedByte();
/* 1561 */                   boundIndex = this.reader.readUnsignedByte();
/* 1562 */                   formalParameterIndex = -1;
/* 1563 */                   throwsTypeIndex = -1;
/* 1564 */                 } else if (targetType == 19) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/* 1571 */                   typeParameterIndex = -1;
/* 1572 */                   boundIndex = -1;
/* 1573 */                   formalParameterIndex = -1;
/* 1574 */                   throwsTypeIndex = -1;
/* 1575 */                 } else if (targetType == 20) {
/*      */ 
/*      */                   
/* 1578 */                   typeParameterIndex = -1;
/* 1579 */                   boundIndex = -1;
/* 1580 */                   formalParameterIndex = -1;
/* 1581 */                   throwsTypeIndex = -1;
/* 1582 */                 } else if (targetType == 21) {
/*      */ 
/*      */                   
/* 1585 */                   typeParameterIndex = -1;
/* 1586 */                   boundIndex = -1;
/* 1587 */                   formalParameterIndex = -1;
/* 1588 */                   throwsTypeIndex = -1;
/* 1589 */                 } else if (targetType == 22) {
/*      */ 
/*      */                   
/* 1592 */                   typeParameterIndex = -1;
/* 1593 */                   boundIndex = -1;
/* 1594 */                   formalParameterIndex = this.reader.readUnsignedByte();
/* 1595 */                   throwsTypeIndex = -1;
/* 1596 */                 } else if (targetType == 23) {
/*      */                   
/* 1598 */                   typeParameterIndex = -1;
/* 1599 */                   boundIndex = -1;
/* 1600 */                   formalParameterIndex = -1;
/* 1601 */                   throwsTypeIndex = this.reader.readUnsignedShort();
/*      */                 } else {
/* 1603 */                   throw new ClassfileFormatException("Class " + this.className + " has unknown method type annotation target 0x" + 
/*      */                       
/* 1605 */                       Integer.toHexString(targetType) + ": element size unknown, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */                 } 
/*      */ 
/*      */ 
/*      */                 
/* 1610 */                 final List<TypePathNode> typePath = readTypePath();
/* 1611 */                 final AnnotationInfo annotationInfo = readAnnotation();
/* 1612 */                 methodTypeAnnotationDecorators.add(new MethodTypeAnnotationDecorator()
/*      */                     {
/*      */                       public void decorate(MethodTypeSignature methodTypeSignature) {
/* 1615 */                         if (targetType == 1) {
/*      */ 
/*      */                           
/* 1618 */                           List<TypeParameter> typeParameters = methodTypeSignature.getTypeParameters();
/* 1619 */                           if (typeParameters != null && typeParameterIndex < typeParameters
/* 1620 */                             .size()) {
/* 1621 */                             ((TypeParameter)typeParameters.get(typeParameterIndex)).addTypeAnnotation(typePath, annotationInfo);
/*      */                           
/*      */                           }
/*      */                         
/*      */                         }
/* 1626 */                         else if (targetType == 18) {
/*      */ 
/*      */ 
/*      */                           
/* 1630 */                           List<TypeParameter> typeParameters = methodTypeSignature.getTypeParameters();
/* 1631 */                           if (typeParameters != null && typeParameterIndex < typeParameters
/* 1632 */                             .size())
/*      */                           {
/* 1634 */                             TypeParameter typeParameter = typeParameters.get(typeParameterIndex);
/*      */                             
/* 1636 */                             if (boundIndex == 0) {
/*      */                               
/* 1638 */                               ReferenceTypeSignature classBound = typeParameter.getClassBound();
/* 1639 */                               if (classBound != null) {
/* 1640 */                                 classBound.addTypeAnnotation(typePath, annotationInfo);
/*      */                               }
/*      */                             } else {
/*      */                               
/* 1644 */                               List<ReferenceTypeSignature> interfaceBounds = typeParameter.getInterfaceBounds();
/* 1645 */                               if (interfaceBounds != null && boundIndex - 1 < interfaceBounds
/* 1646 */                                 .size()) {
/* 1647 */                                 ((ReferenceTypeSignature)interfaceBounds.get(boundIndex - 1))
/* 1648 */                                   .addTypeAnnotation(typePath, annotationInfo);
/*      */                               }
/*      */                             }
/*      */                           
/*      */                           }
/*      */                         
/* 1654 */                         } else if (targetType == 20) {
/*      */                           
/* 1656 */                           methodTypeSignature.getResultType().addTypeAnnotation(typePath, annotationInfo);
/*      */                         }
/* 1658 */                         else if (targetType == 21) {
/*      */                           
/* 1660 */                           methodTypeSignature.addRecieverTypeAnnotation(annotationInfo);
/* 1661 */                         } else if (targetType == 22) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                           
/* 1676 */                           List<TypeSignature> parameterTypeSignatures = methodTypeSignature.getParameterTypeSignatures();
/* 1677 */                           if (formalParameterIndex < parameterTypeSignatures.size()) {
/* 1678 */                             ((TypeSignature)parameterTypeSignatures.get(formalParameterIndex))
/* 1679 */                               .addTypeAnnotation(typePath, annotationInfo);
/*      */                           }
/* 1681 */                         } else if (targetType == 23) {
/*      */ 
/*      */                           
/* 1684 */                           List<ClassRefOrTypeVariableSignature> throwsSignatures = methodTypeSignature.getThrowsSignatures();
/* 1685 */                           if (throwsSignatures != null && throwsTypeIndex < throwsSignatures
/* 1686 */                             .size()) {
/* 1687 */                             ((ClassRefOrTypeVariableSignature)throwsSignatures.get(throwsTypeIndex)).addTypeAnnotation(typePath, annotationInfo);
/*      */                           }
/*      */                         }
/*      */                       
/*      */                       }
/*      */                     });
/*      */               } 
/*      */             } 
/* 1695 */           } else if (constantPoolStringEquals(attributeNameCpIdx, "MethodParameters")) {
/*      */ 
/*      */             
/* 1698 */             int paramCount = this.reader.readUnsignedByte();
/* 1699 */             methodParameterNames = new String[paramCount];
/* 1700 */             methodParameterModifiers = new int[paramCount];
/* 1701 */             for (int k = 0; k < paramCount; k++) {
/* 1702 */               int cpIdx = this.reader.readUnsignedShort();
/*      */               
/* 1704 */               methodParameterNames[k] = (cpIdx == 0) ? null : getConstantPoolString(cpIdx);
/* 1705 */               methodParameterModifiers[k] = this.reader.readUnsignedShort();
/*      */             } 
/* 1707 */           } else if (constantPoolStringEquals(attributeNameCpIdx, "Signature")) {
/*      */             
/* 1709 */             methodTypeSignatureStr = getConstantPoolString(this.reader.readUnsignedShort());
/* 1710 */           } else if (constantPoolStringEquals(attributeNameCpIdx, "AnnotationDefault")) {
/* 1711 */             if (this.annotationParamDefaultValues == null) {
/* 1712 */               this.annotationParamDefaultValues = new AnnotationParameterValueList();
/*      */             }
/* 1714 */             this.annotationParamDefaultValues.add(new AnnotationParameterValue(methodName, 
/*      */                   
/* 1716 */                   readAnnotationElementValue()));
/* 1717 */           } else if (constantPoolStringEquals(attributeNameCpIdx, "Exceptions")) {
/* 1718 */             int exceptionCount = this.reader.readUnsignedShort();
/* 1719 */             thrownExceptionNames = new String[exceptionCount];
/* 1720 */             for (int k = 0; k < exceptionCount; k++) {
/* 1721 */               int cpIdx = this.reader.readUnsignedShort();
/* 1722 */               thrownExceptionNames[k] = getConstantPoolClassName(cpIdx);
/*      */             } 
/* 1724 */           } else if (constantPoolStringEquals(attributeNameCpIdx, "Code")) {
/* 1725 */             methodHasBody = true;
/* 1726 */             this.reader.skip(4);
/* 1727 */             int codeLength = this.reader.readInt();
/* 1728 */             this.reader.skip(codeLength);
/* 1729 */             int exceptionTableLength = this.reader.readUnsignedShort();
/* 1730 */             this.reader.skip(8 * exceptionTableLength);
/* 1731 */             int codeAttrCount = this.reader.readUnsignedShort();
/* 1732 */             for (int k = 0; k < codeAttrCount; k++) {
/* 1733 */               int codeAttrCpIdx = this.reader.readUnsignedShort();
/* 1734 */               int codeAttrLen = this.reader.readInt();
/* 1735 */               if (constantPoolStringEquals(codeAttrCpIdx, "LineNumberTable")) {
/* 1736 */                 int lineNumTableLen = this.reader.readUnsignedShort();
/* 1737 */                 for (int l = 0; l < lineNumTableLen; l++) {
/* 1738 */                   this.reader.skip(2);
/* 1739 */                   int lineNum = this.reader.readUnsignedShort();
/* 1740 */                   minLineNum = (minLineNum == 0) ? lineNum : Math.min(minLineNum, lineNum);
/* 1741 */                   maxLineNum = (maxLineNum == 0) ? lineNum : Math.max(maxLineNum, lineNum);
/*      */                 } 
/*      */               } else {
/* 1744 */                 this.reader.skip(codeAttrLen);
/*      */               } 
/*      */             } 
/*      */           } else {
/* 1748 */             this.reader.skip(attributeLength);
/*      */           } 
/*      */         } 
/*      */         
/* 1752 */         if (enableMethodInfo) {
/* 1753 */           if (this.methodInfoList == null) {
/* 1754 */             this.methodInfoList = new MethodInfoList();
/*      */           }
/* 1756 */           this.methodInfoList.add(new MethodInfo(this.className, methodName, methodAnnotationInfo, methodModifierFlags, methodTypeDescriptor, methodTypeSignatureStr, methodParameterNames, methodParameterModifiers, methodParameterAnnotations, methodHasBody, minLineNum, maxLineNum, methodTypeAnnotationDecorators, thrownExceptionNames));
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
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readClassAttributes() throws IOException, ClassfileFormatException {
/* 1777 */     int attributesCount = this.reader.readUnsignedShort();
/* 1778 */     for (int i = 0; i < attributesCount; i++) {
/* 1779 */       int attributeNameCpIdx = this.reader.readUnsignedShort();
/* 1780 */       int attributeLength = this.reader.readInt();
/* 1781 */       if (this.scanSpec.enableAnnotationInfo && (
/* 1782 */         constantPoolStringEquals(attributeNameCpIdx, "RuntimeVisibleAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && 
/* 1783 */         constantPoolStringEquals(attributeNameCpIdx, "RuntimeInvisibleAnnotations")))) {
/*      */         
/* 1785 */         int annotationCount = this.reader.readUnsignedShort();
/* 1786 */         if (annotationCount > 0) {
/* 1787 */           if (this.classAnnotations == null) {
/* 1788 */             this.classAnnotations = new AnnotationInfoList();
/*      */           }
/* 1790 */           for (int m = 0; m < annotationCount; m++) {
/* 1791 */             this.classAnnotations.add(readAnnotation());
/*      */           }
/*      */         } 
/* 1794 */       } else if (this.scanSpec.enableAnnotationInfo && (
/* 1795 */         constantPoolStringEquals(attributeNameCpIdx, "RuntimeVisibleTypeAnnotations") || (!this.scanSpec.disableRuntimeInvisibleAnnotations && 
/* 1796 */         constantPoolStringEquals(attributeNameCpIdx, "RuntimeInvisibleTypeAnnotations")))) {
/*      */         
/* 1798 */         int annotationCount = this.reader.readUnsignedShort();
/* 1799 */         if (annotationCount > 0) {
/* 1800 */           this.classTypeAnnotationDecorators = new ArrayList<>(annotationCount);
/* 1801 */           for (int m = 0; m < annotationCount; m++) {
/* 1802 */             final int typeParameterIndex, supertypeIndex, boundIndex, targetType = this.reader.readUnsignedByte();
/*      */ 
/*      */ 
/*      */             
/* 1806 */             if (targetType == 0) {
/*      */               
/* 1808 */               typeParameterIndex = this.reader.readUnsignedByte();
/* 1809 */               supertypeIndex = -1;
/* 1810 */               boundIndex = -1;
/* 1811 */             } else if (targetType == 16) {
/*      */ 
/*      */ 
/*      */               
/* 1815 */               supertypeIndex = this.reader.readUnsignedShort();
/* 1816 */               typeParameterIndex = -1;
/* 1817 */               boundIndex = -1;
/* 1818 */             } else if (targetType == 17) {
/*      */               
/* 1820 */               typeParameterIndex = this.reader.readUnsignedByte();
/* 1821 */               boundIndex = this.reader.readUnsignedByte();
/* 1822 */               supertypeIndex = -1;
/*      */             } else {
/* 1824 */               throw new ClassfileFormatException("Class " + this.className + " has unknown class type annotation target 0x" + 
/*      */                   
/* 1826 */                   Integer.toHexString(targetType) + ": element size unknown, cannot continue reading class. Please report this at https://github.com/classgraph/classgraph/issues");
/*      */             } 
/*      */ 
/*      */             
/* 1830 */             final List<TypePathNode> typePath = readTypePath();
/* 1831 */             final AnnotationInfo annotationInfo = readAnnotation();
/* 1832 */             this.classTypeAnnotationDecorators.add(new ClassTypeAnnotationDecorator()
/*      */                 {
/*      */                   public void decorate(ClassTypeSignature classTypeSignature) {
/* 1835 */                     if (targetType == 0) {
/*      */ 
/*      */                       
/* 1838 */                       List<TypeParameter> typeParameters = classTypeSignature.getTypeParameters();
/* 1839 */                       if (typeParameters != null && typeParameterIndex < typeParameters.size()) {
/* 1840 */                         ((TypeParameter)typeParameters.get(typeParameterIndex)).addTypeAnnotation(typePath, annotationInfo);
/*      */                       }
/*      */                     }
/* 1843 */                     else if (targetType == 16) {
/*      */ 
/*      */ 
/*      */                       
/* 1847 */                       if (supertypeIndex == 65535) {
/*      */                         
/* 1849 */                         classTypeSignature.getSuperclassSignature().addTypeAnnotation(typePath, annotationInfo);
/*      */                       }
/*      */                       else {
/*      */                         
/* 1853 */                         ((ClassRefTypeSignature)classTypeSignature.getSuperinterfaceSignatures().get(supertypeIndex))
/* 1854 */                           .addTypeAnnotation(typePath, annotationInfo);
/*      */                       } 
/* 1856 */                     } else if (targetType == 17) {
/*      */ 
/*      */                       
/* 1859 */                       List<TypeParameter> typeParameters = classTypeSignature.getTypeParameters();
/* 1860 */                       if (typeParameters != null && typeParameterIndex < typeParameters.size()) {
/* 1861 */                         TypeParameter typeParameter = typeParameters.get(typeParameterIndex);
/*      */                         
/* 1863 */                         if (boundIndex == 0) {
/* 1864 */                           ReferenceTypeSignature classBound = typeParameter.getClassBound();
/* 1865 */                           if (classBound != null) {
/* 1866 */                             classBound.addTypeAnnotation(typePath, annotationInfo);
/*      */                           }
/*      */                         } else {
/*      */                           
/* 1870 */                           List<ReferenceTypeSignature> interfaceBounds = typeParameter.getInterfaceBounds();
/* 1871 */                           if (interfaceBounds != null && boundIndex - 1 < interfaceBounds
/* 1872 */                             .size()) {
/* 1873 */                             ((ReferenceTypeSignature)typeParameter.getInterfaceBounds().get(boundIndex - 1))
/* 1874 */                               .addTypeAnnotation(typePath, annotationInfo);
/*      */                           }
/*      */                         } 
/*      */                       } 
/*      */                     } 
/*      */                   }
/*      */                 });
/*      */           } 
/*      */         } 
/* 1883 */       } else if (constantPoolStringEquals(attributeNameCpIdx, "Record")) {
/* 1884 */         this.isRecord = true;
/*      */ 
/*      */ 
/*      */         
/* 1888 */         this.reader.skip(attributeLength);
/* 1889 */       } else if (constantPoolStringEquals(attributeNameCpIdx, "InnerClasses")) {
/* 1890 */         int numInnerClasses = this.reader.readUnsignedShort();
/* 1891 */         for (int j = 0; j < numInnerClasses; j++) {
/* 1892 */           int innerClassInfoCpIdx = this.reader.readUnsignedShort();
/* 1893 */           int outerClassInfoCpIdx = this.reader.readUnsignedShort();
/* 1894 */           this.reader.skip(2);
/* 1895 */           int innerClassAccessFlags = this.reader.readUnsignedShort();
/* 1896 */           if (innerClassInfoCpIdx != 0 && outerClassInfoCpIdx != 0) {
/* 1897 */             String innerClassName = getConstantPoolClassName(innerClassInfoCpIdx);
/* 1898 */             String outerClassName = getConstantPoolClassName(outerClassInfoCpIdx);
/* 1899 */             if (innerClassName == null || outerClassName == null)
/*      */             {
/* 1901 */               throw new ClassfileFormatException("Inner and/or outer class name is null");
/*      */             }
/* 1903 */             if (innerClassName.equals(outerClassName))
/*      */             {
/* 1905 */               throw new ClassfileFormatException("Inner and outer class name cannot be the same");
/*      */             }
/*      */             
/* 1908 */             if (!"java.lang.invoke.MethodHandles$Lookup".equals(innerClassName) || 
/* 1909 */               !"java.lang.invoke.MethodHandles".equals(outerClassName))
/*      */             {
/* 1911 */               if (this.classContainmentEntries == null) {
/* 1912 */                 this.classContainmentEntries = new ArrayList<>();
/*      */               }
/* 1914 */               this.classContainmentEntries.add(new ClassContainment(innerClassName, innerClassAccessFlags, outerClassName));
/*      */             }
/*      */           
/*      */           } 
/*      */         } 
/* 1919 */       } else if (constantPoolStringEquals(attributeNameCpIdx, "Signature")) {
/*      */         
/* 1921 */         this.typeSignatureStr = getConstantPoolString(this.reader.readUnsignedShort());
/* 1922 */       } else if (constantPoolStringEquals(attributeNameCpIdx, "SourceFile")) {
/* 1923 */         this.sourceFile = getConstantPoolString(this.reader.readUnsignedShort());
/* 1924 */       } else if (constantPoolStringEquals(attributeNameCpIdx, "EnclosingMethod")) {
/* 1925 */         String definingMethodName, innermostEnclosingClassName = getConstantPoolClassName(this.reader.readUnsignedShort());
/* 1926 */         int enclosingMethodCpIdx = this.reader.readUnsignedShort();
/*      */         
/* 1928 */         if (enclosingMethodCpIdx == 0) {
/*      */ 
/*      */           
/* 1931 */           definingMethodName = "<clinit>";
/*      */         } else {
/* 1933 */           definingMethodName = getConstantPoolString(enclosingMethodCpIdx, 0);
/*      */         } 
/*      */ 
/*      */         
/* 1937 */         if (this.classContainmentEntries == null) {
/* 1938 */           this.classContainmentEntries = new ArrayList<>();
/*      */         }
/* 1940 */         this.classContainmentEntries
/* 1941 */           .add(new ClassContainment(this.className, this.classModifiers, innermostEnclosingClassName));
/*      */ 
/*      */         
/* 1944 */         this.fullyQualifiedDefiningMethodName = innermostEnclosingClassName + "." + definingMethodName;
/* 1945 */       } else if (constantPoolStringEquals(attributeNameCpIdx, "Module")) {
/* 1946 */         int moduleNameCpIdx = this.reader.readUnsignedShort();
/* 1947 */         this.classpathElement.moduleNameFromModuleDescriptor = getConstantPoolString(moduleNameCpIdx);
/*      */ 
/*      */         
/* 1950 */         this.reader.skip(attributeLength - 2);
/*      */       } else {
/* 1952 */         this.reader.skip(attributeLength);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Classfile(ClasspathElement classpathElement, List<ClasspathElement> classpathOrder, Set<String> acceptedClassNamesFound, Set<String> classNamesScheduledForExtendedScanning, String relativePath, Resource classfileResource, boolean isExternalClass, ConcurrentHashMap<String, String> stringInternMap, WorkQueue<Scanner.ClassfileScanWorkUnit> workQueue, ScanSpec scanSpec, LogNode log) throws IOException, ClassfileFormatException, SkipClassException {
/* 2002 */     this.classpathElement = classpathElement;
/* 2003 */     this.classpathOrder = classpathOrder;
/* 2004 */     this.relativePath = relativePath;
/* 2005 */     this.acceptedClassNamesFound = acceptedClassNamesFound;
/* 2006 */     this.classNamesScheduledForExtendedScanning = classNamesScheduledForExtendedScanning;
/* 2007 */     this.classfileResource = classfileResource;
/* 2008 */     this.isExternalClass = isExternalClass;
/* 2009 */     this.stringInternMap = stringInternMap;
/* 2010 */     this.scanSpec = scanSpec;
/*      */ 
/*      */     
/* 2013 */     ClassfileReader classfileReader = classfileResource.openClassfile(); 
/* 2014 */     try { this.reader = classfileReader;
/*      */ 
/*      */       
/* 2017 */       if (this.reader.readInt() != -889275714) {
/* 2018 */         throw new ClassfileFormatException("Classfile does not have correct magic number");
/*      */       }
/*      */ 
/*      */       
/* 2022 */       this.minorVersion = this.reader.readUnsignedShort();
/* 2023 */       this.majorVersion = this.reader.readUnsignedShort();
/*      */ 
/*      */       
/* 2026 */       readConstantPoolEntries(log);
/*      */ 
/*      */       
/* 2029 */       readBasicClassInfo();
/*      */ 
/*      */       
/* 2032 */       readInterfaces();
/*      */ 
/*      */       
/* 2035 */       readFields();
/*      */ 
/*      */       
/* 2038 */       readMethods();
/*      */ 
/*      */       
/* 2041 */       readClassAttributes();
/*      */       
/* 2043 */       this.reader = null;
/* 2044 */       if (classfileReader != null) classfileReader.close();  } catch (Throwable throwable) { if (classfileReader != null)
/*      */         try { classfileReader.close(); }
/*      */         catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */           throw throwable; }
/* 2048 */      LogNode subLog = (log == null) ? null : log.log("Found " + (
/* 2049 */         this.isAnnotation ? "annotation class" : (this.isInterface ? "interface class" : "class")) + " " + this.className);
/*      */     
/* 2051 */     if (subLog != null) {
/* 2052 */       if (this.superclassName != null) {
/* 2053 */         subLog.log("Super" + ((
/* 2054 */             this.isInterface && !this.isAnnotation) ? "interface" : "class") + ": " + this.superclassName);
/*      */       }
/* 2056 */       if (this.implementedInterfaces != null) {
/* 2057 */         subLog.log("Interfaces: " + StringUtils.join(", ", this.implementedInterfaces));
/*      */       }
/* 2059 */       if (this.classAnnotations != null) {
/* 2060 */         subLog.log("Class annotations: " + StringUtils.join(", ", this.classAnnotations));
/*      */       }
/* 2062 */       if (this.annotationParamDefaultValues != null) {
/* 2063 */         for (AnnotationParameterValue apv : this.annotationParamDefaultValues) {
/* 2064 */           subLog.log("Annotation default param value: " + apv);
/*      */         }
/*      */       }
/* 2067 */       if (this.fieldInfoList != null) {
/* 2068 */         for (FieldInfo fieldInfo : this.fieldInfoList) {
/* 2069 */           String modifierStr = fieldInfo.getModifiersStr();
/* 2070 */           subLog.log("Field: " + modifierStr + (modifierStr.isEmpty() ? "" : " ") + fieldInfo.getName());
/*      */         } 
/*      */       }
/* 2073 */       if (this.methodInfoList != null) {
/* 2074 */         for (MethodInfo methodInfo : this.methodInfoList) {
/* 2075 */           String modifierStr = methodInfo.getModifiersStr();
/* 2076 */           subLog.log("Method: " + modifierStr + (
/* 2077 */               modifierStr.isEmpty() ? "" : " ") + methodInfo.getName());
/*      */         } 
/*      */       }
/* 2080 */       if (this.typeSignatureStr != null) {
/* 2081 */         subLog.log("Class type signature: " + this.typeSignatureStr);
/*      */       }
/* 2083 */       if (this.refdClassNames != null) {
/* 2084 */         List<String> refdClassNamesSorted = new ArrayList<>(this.refdClassNames);
/* 2085 */         CollectionUtils.sortIfNotEmpty(refdClassNamesSorted);
/* 2086 */         subLog.log("Additional referenced class names: " + StringUtils.join(", ", refdClassNamesSorted));
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2094 */     if (scanSpec.extendScanningUpwardsToExternalClasses) {
/* 2095 */       extendScanningUpwards(subLog);
/*      */       
/* 2097 */       if (this.additionalWorkUnits != null)
/* 2098 */         workQueue.addWorkUnits(this.additionalWorkUnits); 
/*      */     } 
/*      */   }
/*      */   
/*      */   static interface TypeAnnotationDecorator {
/*      */     void decorate(TypeSignature param1TypeSignature);
/*      */   }
/*      */   
/*      */   static interface MethodTypeAnnotationDecorator {
/*      */     void decorate(MethodTypeSignature param1MethodTypeSignature);
/*      */   }
/*      */   
/*      */   static interface ClassTypeAnnotationDecorator {
/*      */     void decorate(ClassTypeSignature param1ClassTypeSignature);
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\Classfile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */