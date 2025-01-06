/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.types.ParseException;
/*     */ import nonapi.io.github.classgraph.types.Parser;
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
/*     */ public final class ClassTypeSignature
/*     */   extends HierarchicalTypeSignature
/*     */ {
/*     */   private final ClassInfo classInfo;
/*     */   final List<TypeParameter> typeParameters;
/*     */   private final ClassRefTypeSignature superclassSignature;
/*     */   private final List<ClassRefTypeSignature> superinterfaceSignatures;
/*     */   private final List<ClassRefOrTypeVariableSignature> throwsSignatures;
/*     */   
/*     */   private ClassTypeSignature(ClassInfo classInfo, List<TypeParameter> typeParameters, ClassRefTypeSignature superclassSignature, List<ClassRefTypeSignature> superinterfaceSignatures, List<ClassRefOrTypeVariableSignature> throwsSignatures) {
/*  88 */     this.classInfo = classInfo;
/*  89 */     this.typeParameters = typeParameters;
/*  90 */     this.superclassSignature = superclassSignature;
/*  91 */     this.superinterfaceSignatures = superinterfaceSignatures;
/*  92 */     this.throwsSignatures = throwsSignatures;
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
/*     */   ClassTypeSignature(ClassInfo classInfo, ClassInfo superclass, ClassInfoList interfaces) {
/* 107 */     this.classInfo = classInfo;
/* 108 */     this.typeParameters = Collections.emptyList();
/* 109 */     ClassRefTypeSignature superclassSignature = null;
/*     */ 
/*     */     
/*     */     try {
/* 113 */       superclassSignature = (superclass == null) ? null : (ClassRefTypeSignature)TypeSignature.parse("L" + superclass.getName().replace('.', '/') + ";", classInfo.getName());
/* 114 */     } catch (ParseException parseException) {}
/*     */ 
/*     */     
/* 117 */     this.superclassSignature = superclassSignature;
/* 118 */     this
/*     */       
/* 120 */       .superinterfaceSignatures = (interfaces == null || interfaces.isEmpty()) ? Collections.<ClassRefTypeSignature>emptyList() : new ArrayList<>(interfaces.size());
/* 121 */     if (interfaces != null) {
/* 122 */       for (ClassInfo iface : interfaces) {
/*     */         
/*     */         try {
/* 125 */           ClassRefTypeSignature ifaceSignature = (ClassRefTypeSignature)TypeSignature.parse("L" + iface.getName().replace('.', '/') + ";", classInfo.getName());
/* 126 */           this.superinterfaceSignatures.add(ifaceSignature);
/* 127 */         } catch (ParseException parseException) {}
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 132 */     this.throwsSignatures = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<TypeParameter> getTypeParameters() {
/* 143 */     return this.typeParameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassRefTypeSignature getSuperclassSignature() {
/* 153 */     return this.superclassSignature;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ClassRefTypeSignature> getSuperinterfaceSignatures() {
/* 162 */     return this.superinterfaceSignatures;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   List<ClassRefOrTypeVariableSignature> getThrowsSignatures() {
/* 172 */     return this.throwsSignatures;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addTypeAnnotation(List<Classfile.TypePathNode> typePath, AnnotationInfo annotationInfo) {
/* 178 */     throw new IllegalArgumentException("Cannot call this method on " + ClassTypeSignature.class
/* 179 */         .getSimpleName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getClassName() {
/* 189 */     return (this.classInfo != null) ? this.classInfo.getName() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassInfo getClassInfo() {
/* 197 */     return this.classInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/* 205 */     super.setScanResult(scanResult);
/* 206 */     if (this.typeParameters != null) {
/* 207 */       for (TypeParameter typeParameter : this.typeParameters) {
/* 208 */         typeParameter.setScanResult(scanResult);
/*     */       }
/*     */     }
/* 211 */     if (this.superclassSignature != null) {
/* 212 */       this.superclassSignature.setScanResult(scanResult);
/*     */     }
/* 214 */     if (this.superinterfaceSignatures != null) {
/* 215 */       for (ClassRefTypeSignature classRefTypeSignature : this.superinterfaceSignatures) {
/* 216 */         classRefTypeSignature.setScanResult(scanResult);
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
/*     */   protected void findReferencedClassNames(Set<String> refdClassNames) {
/* 228 */     for (TypeParameter typeParameter : this.typeParameters) {
/* 229 */       typeParameter.findReferencedClassNames(refdClassNames);
/*     */     }
/* 231 */     if (this.superclassSignature != null) {
/* 232 */       this.superclassSignature.findReferencedClassNames(refdClassNames);
/*     */     }
/* 234 */     if (this.superinterfaceSignatures != null) {
/* 235 */       for (ClassRefTypeSignature typeSignature : this.superinterfaceSignatures) {
/* 236 */         typeSignature.findReferencedClassNames(refdClassNames);
/*     */       }
/*     */     }
/* 239 */     if (this.throwsSignatures != null) {
/* 240 */       for (ClassRefOrTypeVariableSignature typeSignature : this.throwsSignatures) {
/* 241 */         typeSignature.findReferencedClassNames(refdClassNames);
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
/* 257 */     Set<String> refdClassNames = new HashSet<>();
/* 258 */     findReferencedClassNames(refdClassNames);
/* 259 */     for (String refdClassName : refdClassNames) {
/* 260 */       ClassInfo clsInfo = ClassInfo.getOrCreateClassInfo(refdClassName, classNameToClassInfo);
/* 261 */       clsInfo.scanResult = this.scanResult;
/* 262 */       refdClassInfo.add(clsInfo);
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
/* 273 */     return this.typeParameters.hashCode() + ((this.superclassSignature == null) ? 1 : this.superclassSignature.hashCode()) * 7 + (
/* 274 */       (this.superinterfaceSignatures == null) ? 1 : this.superinterfaceSignatures.hashCode()) * 15;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 282 */     if (obj == this)
/* 283 */       return true; 
/* 284 */     if (!(obj instanceof ClassTypeSignature)) {
/* 285 */       return false;
/*     */     }
/* 287 */     ClassTypeSignature o = (ClassTypeSignature)obj;
/* 288 */     return (Objects.equals(o.typeParameters, this.typeParameters) && 
/* 289 */       Objects.equals(o.superclassSignature, this.superclassSignature) && 
/* 290 */       Objects.equals(o.superinterfaceSignatures, this.superinterfaceSignatures));
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
/*     */   void toStringInternal(String className, boolean useSimpleNames, int modifiers, boolean isAnnotation, boolean isInterface, AnnotationInfoList annotationsToExclude, StringBuilder buf) {
/* 316 */     if (this.throwsSignatures != null) {
/* 317 */       for (ClassRefOrTypeVariableSignature throwsSignature : this.throwsSignatures) {
/* 318 */         if (buf.length() > 0) {
/* 319 */           buf.append(' ');
/*     */         }
/* 321 */         buf.append("@throws(").append(throwsSignature).append(")");
/*     */       } 
/*     */     }
/* 324 */     if (modifiers != 0) {
/* 325 */       if (buf.length() > 0) {
/* 326 */         buf.append(' ');
/*     */       }
/* 328 */       TypeUtils.modifiersToString(modifiers, TypeUtils.ModifierType.CLASS, false, buf);
/*     */     } 
/* 330 */     if (buf.length() > 0) {
/* 331 */       buf.append(' ');
/*     */     }
/* 333 */     buf.append(isAnnotation ? "@interface" : (
/* 334 */         isInterface ? "interface" : (((modifiers & 0x4000) != 0) ? "enum" : "class")));
/* 335 */     buf.append(' ');
/* 336 */     if (className != null) {
/* 337 */       buf.append(useSimpleNames ? ClassInfo.getSimpleName(className) : className);
/*     */     }
/* 339 */     if (!this.typeParameters.isEmpty()) {
/* 340 */       buf.append('<');
/* 341 */       for (int i = 0; i < this.typeParameters.size(); i++) {
/* 342 */         if (i > 0) {
/* 343 */           buf.append(", ");
/*     */         }
/* 345 */         ((TypeParameter)this.typeParameters.get(i)).toStringInternal(useSimpleNames, null, buf);
/*     */       } 
/* 347 */       buf.append('>');
/*     */     } 
/* 349 */     if (this.superclassSignature != null) {
/* 350 */       String superSig = this.superclassSignature.toString(useSimpleNames);
/*     */       
/* 352 */       if (!superSig.equals("java.lang.Object") && (
/* 353 */         !superSig.equals("Object") || !this.superclassSignature.className.equals("java.lang.Object"))) {
/* 354 */         buf.append(" extends ");
/* 355 */         buf.append(superSig);
/*     */       } 
/*     */     } 
/* 358 */     if (this.superinterfaceSignatures != null && !this.superinterfaceSignatures.isEmpty()) {
/* 359 */       buf.append(isInterface ? " extends " : " implements ");
/* 360 */       for (int i = 0; i < this.superinterfaceSignatures.size(); i++) {
/* 361 */         if (i > 0) {
/* 362 */           buf.append(", ");
/*     */         }
/* 364 */         ((ClassRefTypeSignature)this.superinterfaceSignatures.get(i)).toStringInternal(useSimpleNames, (AnnotationInfoList)null, buf);
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
/*     */   
/*     */   protected void toStringInternal(boolean useSimpleNames, AnnotationInfoList annotationsToExclude, StringBuilder buf) {
/* 382 */     toStringInternal(this.classInfo.getName(), useSimpleNames, this.classInfo.getModifiers(), this.classInfo.isAnnotation(), this.classInfo
/* 383 */         .isInterface(), annotationsToExclude, buf);
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
/*     */   static ClassTypeSignature parse(String typeDescriptor, ClassInfo classInfo) throws ParseException {
/*     */     List<ClassRefTypeSignature> superinterfaceSignatures;
/*     */     List<ClassRefOrTypeVariableSignature> throwsSignatures;
/* 400 */     Parser parser = new Parser(typeDescriptor);
/*     */ 
/*     */ 
/*     */     
/* 404 */     String definingClassNameNull = null;
/* 405 */     List<TypeParameter> typeParameters = TypeParameter.parseList(parser, definingClassNameNull);
/* 406 */     ClassRefTypeSignature superclassSignature = ClassRefTypeSignature.parse(parser, definingClassNameNull);
/*     */ 
/*     */     
/* 409 */     if (parser.hasMore()) {
/* 410 */       superinterfaceSignatures = new ArrayList<>();
/* 411 */       while (parser.hasMore() && 
/* 412 */         parser.peek() != '^') {
/*     */ 
/*     */ 
/*     */         
/* 416 */         ClassRefTypeSignature superinterfaceSignature = ClassRefTypeSignature.parse(parser, definingClassNameNull);
/*     */         
/* 418 */         if (superinterfaceSignature == null) {
/* 419 */           throw new ParseException(parser, "Could not parse superinterface signature");
/*     */         }
/* 421 */         superinterfaceSignatures.add(superinterfaceSignature);
/*     */       } 
/*     */     } else {
/* 424 */       superinterfaceSignatures = Collections.emptyList();
/*     */     } 
/*     */     
/* 427 */     if (parser.peek() == '^') {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 438 */       throwsSignatures = new ArrayList<>();
/* 439 */       while (parser.peek() == '^') {
/* 440 */         parser.expect('^');
/* 441 */         ClassRefTypeSignature classTypeSignature = ClassRefTypeSignature.parse(parser, classInfo
/* 442 */             .getName());
/* 443 */         if (classTypeSignature != null) {
/* 444 */           throwsSignatures.add(classTypeSignature); continue;
/*     */         } 
/* 446 */         TypeVariableSignature typeVariableSignature = TypeVariableSignature.parse(parser, classInfo
/* 447 */             .getName());
/* 448 */         if (typeVariableSignature != null) {
/* 449 */           throwsSignatures.add(typeVariableSignature); continue;
/*     */         } 
/* 451 */         throw new ParseException(parser, "Missing type variable signature");
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 456 */       throwsSignatures = null;
/*     */     } 
/* 458 */     if (parser.hasMore()) {
/* 459 */       throw new ParseException(parser, "Extra characters at end of type descriptor");
/*     */     }
/* 461 */     return new ClassTypeSignature(classInfo, typeParameters, superclassSignature, superinterfaceSignatures, throwsSignatures);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ClassTypeSignature.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */