/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.types.ParseException;
/*     */ import nonapi.io.github.classgraph.types.Parser;
/*     */ import nonapi.io.github.classgraph.types.TypeUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ClassRefTypeSignature
/*     */   extends ClassRefOrTypeVariableSignature
/*     */ {
/*     */   final String className;
/*     */   private final List<TypeArgument> typeArguments;
/*     */   private final List<String> suffixes;
/*     */   private final List<List<TypeArgument>> suffixTypeArguments;
/*     */   private List<AnnotationInfoList> suffixTypeAnnotations;
/*     */   
/*     */   private ClassRefTypeSignature(String className, List<TypeArgument> typeArguments, List<String> suffixes, List<List<TypeArgument>> suffixTypeArguments) {
/*  76 */     this.className = className;
/*  77 */     this.typeArguments = typeArguments;
/*  78 */     this.suffixes = suffixes;
/*  79 */     this.suffixTypeArguments = suffixTypeArguments;
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
/*     */   public String getBaseClassName() {
/*  91 */     return this.className;
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
/*     */   public String getFullyQualifiedClassName() {
/* 108 */     if (this.suffixes.isEmpty()) {
/* 109 */       return this.className;
/*     */     }
/* 111 */     StringBuilder buf = new StringBuilder();
/* 112 */     buf.append(this.className);
/* 113 */     for (String suffix : this.suffixes) {
/* 114 */       buf.append('$');
/* 115 */       buf.append(suffix);
/*     */     } 
/* 117 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<TypeArgument> getTypeArguments() {
/* 127 */     return this.typeArguments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getSuffixes() {
/* 136 */     return this.suffixes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<List<TypeArgument>> getSuffixTypeArguments() {
/* 146 */     return this.suffixTypeArguments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<AnnotationInfoList> getSuffixTypeAnnotationInfo() {
/* 156 */     return this.suffixTypeAnnotations;
/*     */   }
/*     */   
/*     */   private void addSuffixTypeAnnotation(int suffixIdx, AnnotationInfo annotationInfo) {
/* 160 */     if (this.suffixTypeAnnotations == null) {
/* 161 */       this.suffixTypeAnnotations = new ArrayList<>(this.suffixes.size());
/* 162 */       for (int i = 0; i < this.suffixes.size(); i++) {
/* 163 */         this.suffixTypeAnnotations.add(new AnnotationInfoList(1));
/*     */       }
/*     */     } 
/* 166 */     ((AnnotationInfoList)this.suffixTypeAnnotations.get(suffixIdx)).add(annotationInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addTypeAnnotation(List<Classfile.TypePathNode> typePath, AnnotationInfo annotationInfo) {
/* 172 */     int numDeeperNestedLevels = 0;
/* 173 */     int nextTypeArgIdx = -1;
/* 174 */     for (Classfile.TypePathNode typePathNode : typePath) {
/* 175 */       if (typePathNode.typePathKind == 1) {
/*     */ 
/*     */         
/* 178 */         numDeeperNestedLevels++; continue;
/* 179 */       }  if (typePathNode.typePathKind == 3) {
/*     */ 
/*     */         
/* 182 */         nextTypeArgIdx = typePathNode.typeArgumentIdx;
/*     */ 
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 188 */       throw new IllegalArgumentException("Bad typePathKind: " + typePathNode.typePathKind);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 193 */     int suffixIdx = -1;
/* 194 */     int nestingLevel = -1;
/* 195 */     String typePrefix = this.className;
/*     */     while (true) {
/*     */       boolean skipSuffix;
/* 198 */       if (suffixIdx >= this.suffixes.size())
/* 199 */         throw new IllegalArgumentException("Ran out of nested types while trying to add type annotation"); 
/* 200 */       if (suffixIdx == this.suffixes.size() - 1) {
/*     */ 
/*     */         
/* 203 */         skipSuffix = false;
/*     */       } else {
/*     */         
/* 206 */         ClassInfo outerClassInfo = this.scanResult.getClassInfo(typePrefix);
/* 207 */         typePrefix = typePrefix + '$' + (String)this.suffixes.get(suffixIdx + 1);
/* 208 */         ClassInfo innerClassInfo = this.scanResult.getClassInfo(typePrefix);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 213 */         skipSuffix = (outerClassInfo == null || innerClassInfo == null || outerClassInfo.isInterfaceOrAnnotation() || innerClassInfo.isInterfaceOrAnnotation() || innerClassInfo.isStatic() || !outerClassInfo.getInnerClasses().contains(innerClassInfo));
/*     */       } 
/* 215 */       if (!skipSuffix) {
/*     */         
/* 217 */         nestingLevel++;
/* 218 */         if (nestingLevel >= numDeeperNestedLevels) {
/*     */           break;
/*     */         }
/*     */       } 
/* 222 */       suffixIdx++;
/*     */     } 
/*     */     
/* 225 */     if (nextTypeArgIdx == -1) {
/*     */       
/* 227 */       if (suffixIdx == -1) {
/*     */         
/* 229 */         addTypeAnnotation(annotationInfo);
/*     */       } else {
/*     */         
/* 232 */         addSuffixTypeAnnotation(suffixIdx, annotationInfo);
/*     */       } 
/*     */     } else {
/*     */       
/* 236 */       List<TypeArgument> typeArgumentList = (suffixIdx == -1) ? this.typeArguments : this.suffixTypeArguments.get(suffixIdx);
/*     */ 
/*     */       
/* 239 */       if (nextTypeArgIdx < typeArgumentList.size()) {
/*     */ 
/*     */ 
/*     */         
/* 243 */         List<Classfile.TypePathNode> remainingTypePath = typePath.subList(numDeeperNestedLevels + 1, typePath
/* 244 */             .size());
/*     */         
/* 246 */         ((TypeArgument)typeArgumentList.get(nextTypeArgIdx)).addTypeAnnotation(remainingTypePath, annotationInfo);
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
/*     */   
/*     */   public Class<?> loadClass(boolean ignoreExceptions) {
/* 265 */     return super.loadClass(ignoreExceptions);
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
/*     */   public Class<?> loadClass() {
/* 278 */     return super.loadClass();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getClassName() {
/* 286 */     return getFullyQualifiedClassName();
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
/*     */   public ClassInfo getClassInfo() {
/* 299 */     return super.getClassInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setScanResult(ScanResult scanResult) {
/* 307 */     super.setScanResult(scanResult);
/* 308 */     for (TypeArgument typeArgument : this.typeArguments) {
/* 309 */       typeArgument.setScanResult(scanResult);
/*     */     }
/* 311 */     for (List<TypeArgument> typeArgumentList : this.suffixTypeArguments) {
/* 312 */       for (TypeArgument typeArgument : typeArgumentList) {
/* 313 */         typeArgument.setScanResult(scanResult);
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
/*     */   protected void findReferencedClassNames(Set<String> refdClassNames) {
/* 326 */     refdClassNames.add(getFullyQualifiedClassName());
/* 327 */     for (TypeArgument typeArgument : this.typeArguments) {
/* 328 */       typeArgument.findReferencedClassNames(refdClassNames);
/*     */     }
/* 330 */     for (List<TypeArgument> typeArgumentList : this.suffixTypeArguments) {
/* 331 */       for (TypeArgument typeArgument : typeArgumentList) {
/* 332 */         typeArgument.findReferencedClassNames(refdClassNames);
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
/* 344 */     return this.className.hashCode() + 7 * this.typeArguments.hashCode() + 15 * this.suffixTypeArguments.hashCode() + 31 * (
/* 345 */       (this.typeAnnotationInfo == null) ? 0 : this.typeAnnotationInfo.hashCode()) + 64 * (
/* 346 */       (this.suffixTypeAnnotations == null) ? 0 : this.suffixTypeAnnotations.hashCode());
/*     */   }
/*     */   
/*     */   private static boolean suffixesMatch(ClassRefTypeSignature a, ClassRefTypeSignature b) {
/* 350 */     return (a.suffixes.equals(b.suffixes) && a.suffixTypeArguments
/* 351 */       .equals(b.suffixTypeArguments) && 
/* 352 */       Objects.equals(a.suffixTypeAnnotations, b.suffixTypeAnnotations));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 360 */     if (obj == this)
/* 361 */       return true; 
/* 362 */     if (!(obj instanceof ClassRefTypeSignature)) {
/* 363 */       return false;
/*     */     }
/* 365 */     ClassRefTypeSignature o = (ClassRefTypeSignature)obj;
/* 366 */     return (o.className.equals(this.className) && o.typeArguments.equals(this.typeArguments) && 
/* 367 */       Objects.equals(this.typeAnnotationInfo, o.typeAnnotationInfo) && suffixesMatch(o, this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equalsIgnoringTypeParams(TypeSignature other) {
/* 375 */     if (other instanceof TypeVariableSignature)
/*     */     {
/*     */       
/* 378 */       return other.equalsIgnoringTypeParams(this);
/*     */     }
/* 380 */     if (!(other instanceof ClassRefTypeSignature)) {
/* 381 */       return false;
/*     */     }
/* 383 */     ClassRefTypeSignature o = (ClassRefTypeSignature)other;
/* 384 */     return (o.className.equals(this.className) && Objects.equals(this.typeAnnotationInfo, o.typeAnnotationInfo) && 
/* 385 */       suffixesMatch(o, this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void toStringInternal(boolean useSimpleNames, AnnotationInfoList annotationsToExclude, StringBuilder buf) {
/* 394 */     if (!useSimpleNames || this.suffixes.isEmpty()) {
/*     */       
/* 396 */       if (this.typeAnnotationInfo != null) {
/* 397 */         for (AnnotationInfo annotationInfo : this.typeAnnotationInfo) {
/* 398 */           if (annotationsToExclude == null || !annotationsToExclude.contains(annotationInfo)) {
/* 399 */             annotationInfo.toString(useSimpleNames, buf);
/* 400 */             buf.append(' ');
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 405 */       buf.append(useSimpleNames ? ClassInfo.getSimpleName(this.className) : this.className);
/*     */       
/* 407 */       if (!this.typeArguments.isEmpty()) {
/* 408 */         buf.append('<');
/* 409 */         for (int i = 0; i < this.typeArguments.size(); i++) {
/* 410 */           if (i > 0) {
/* 411 */             buf.append(", ");
/*     */           }
/* 413 */           ((TypeArgument)this.typeArguments.get(i)).toString(useSimpleNames, buf);
/*     */         } 
/* 415 */         buf.append('>');
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 420 */     if (!this.suffixes.isEmpty()) {
/* 421 */       for (int i = useSimpleNames ? (this.suffixes.size() - 1) : 0; i < this.suffixes.size(); i++) {
/* 422 */         if (!useSimpleNames)
/*     */         {
/* 424 */           buf.append('$');
/*     */         }
/*     */         
/* 427 */         AnnotationInfoList typeAnnotations = (this.suffixTypeAnnotations == null) ? null : this.suffixTypeAnnotations.get(i);
/*     */         
/* 429 */         if (typeAnnotations != null && !typeAnnotations.isEmpty()) {
/* 430 */           for (AnnotationInfo annotationInfo : typeAnnotations) {
/* 431 */             annotationInfo.toString(useSimpleNames, buf);
/* 432 */             buf.append(' ');
/*     */           } 
/*     */         }
/*     */         
/* 436 */         buf.append(this.suffixes.get(i));
/*     */         
/* 438 */         List<TypeArgument> suffixTypeArgumentsList = this.suffixTypeArguments.get(i);
/* 439 */         if (!suffixTypeArgumentsList.isEmpty()) {
/* 440 */           buf.append('<');
/* 441 */           for (int j = 0; j < suffixTypeArgumentsList.size(); j++) {
/* 442 */             if (j > 0) {
/* 443 */               buf.append(", ");
/*     */             }
/* 445 */             ((TypeArgument)suffixTypeArgumentsList.get(j)).toString(useSimpleNames, buf);
/*     */           } 
/* 447 */           buf.append('>');
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
/*     */ 
/*     */ 
/*     */   
/*     */   static ClassRefTypeSignature parse(Parser parser, String definingClassName) throws ParseException {
/* 467 */     if (parser.peek() == 'L') {
/* 468 */       List<String> suffixes; List<List<TypeArgument>> suffixTypeArguments; parser.next();
/* 469 */       int startParserPosition = parser.getPosition();
/* 470 */       if (!TypeUtils.getIdentifierToken(parser, true)) {
/* 471 */         throw new ParseException(parser, "Could not parse identifier token");
/*     */       }
/* 473 */       String className = parser.currToken();
/* 474 */       List<TypeArgument> typeArguments = TypeArgument.parseList(parser, definingClassName);
/*     */ 
/*     */       
/* 477 */       boolean dropSuffixes = false;
/* 478 */       if (parser.peek() == '.' || parser.peek() == '$') {
/* 479 */         suffixes = new ArrayList<>();
/* 480 */         suffixTypeArguments = new ArrayList<>();
/* 481 */         while (parser.peek() == '.' || parser.peek() == '$') {
/* 482 */           parser.advance(1);
/* 483 */           if (!TypeUtils.getIdentifierToken(parser, true)) {
/*     */             
/* 485 */             suffixes.add("");
/* 486 */             suffixTypeArguments.add(Collections.emptyList());
/* 487 */             dropSuffixes = true; continue;
/*     */           } 
/* 489 */           suffixes.add(parser.currToken());
/* 490 */           suffixTypeArguments.add(TypeArgument.parseList(parser, definingClassName));
/*     */         } 
/*     */         
/* 493 */         if (dropSuffixes) {
/*     */ 
/*     */           
/* 496 */           className = parser.getSubstring(startParserPosition, parser.getPosition()).replace('/', '.');
/* 497 */           suffixes = Collections.emptyList();
/* 498 */           suffixTypeArguments = Collections.emptyList();
/*     */         } 
/*     */       } else {
/* 501 */         suffixes = Collections.emptyList();
/* 502 */         suffixTypeArguments = Collections.emptyList();
/*     */       } 
/* 504 */       parser.expect(';');
/* 505 */       return new ClassRefTypeSignature(className, typeArguments, suffixes, suffixTypeArguments);
/*     */     } 
/* 507 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ClassRefTypeSignature.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */