/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.utils.Assert;
/*     */ import nonapi.io.github.classgraph.utils.CollectionUtils;
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
/*     */ public class AnnotationInfoList
/*     */   extends MappableInfoList<AnnotationInfo>
/*     */ {
/*     */   private AnnotationInfoList directlyRelatedAnnotations;
/*     */   private static final long serialVersionUID = 1L;
/*  57 */   static final AnnotationInfoList EMPTY_LIST = new AnnotationInfoList();
/*     */   static {
/*  59 */     EMPTY_LIST.makeUnmodifiable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AnnotationInfoList emptyList() {
/*  68 */     return EMPTY_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationInfoList() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationInfoList(int sizeHint) {
/*  85 */     super(sizeHint);
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
/*     */   public AnnotationInfoList(AnnotationInfoList reachableAnnotations) {
/*  97 */     this(reachableAnnotations, reachableAnnotations);
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
/*     */   AnnotationInfoList(AnnotationInfoList reachableAnnotations, AnnotationInfoList directlyRelatedAnnotations) {
/* 110 */     super(reachableAnnotations);
/* 111 */     this.directlyRelatedAnnotations = directlyRelatedAnnotations;
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
/*     */ 
/*     */   
/*     */   public AnnotationInfoList filter(AnnotationInfoFilter filter) {
/* 143 */     AnnotationInfoList annotationInfoFiltered = new AnnotationInfoList();
/* 144 */     for (AnnotationInfo resource : this) {
/* 145 */       if (filter.accept(resource)) {
/* 146 */         annotationInfoFiltered.add(resource);
/*     */       }
/*     */     } 
/* 149 */     return annotationInfoFiltered;
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
/*     */   protected void findReferencedClassInfo(Map<String, ClassInfo> classNameToClassInfo, Set<ClassInfo> refdClassInfo, LogNode log) {
/* 166 */     for (AnnotationInfo ai : this) {
/* 167 */       ai.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void handleRepeatableAnnotations(Set<String> allRepeatableAnnotationNames, ClassInfo containingClassInfo, ClassInfo.RelType forwardRelType, ClassInfo.RelType reverseRelType0, ClassInfo.RelType reverseRelType1) {
/* 190 */     List<AnnotationInfo> repeatableAnnotations = null;
/* 191 */     for (int i = size() - 1; i >= 0; i--) {
/* 192 */       AnnotationInfo ai = get(i);
/* 193 */       if (allRepeatableAnnotationNames.contains(ai.getName())) {
/* 194 */         if (repeatableAnnotations == null) {
/* 195 */           repeatableAnnotations = new ArrayList<>();
/*     */         }
/* 197 */         repeatableAnnotations.add(ai);
/*     */         
/* 199 */         remove(i);
/*     */       } 
/*     */     } 
/*     */     
/* 203 */     if (repeatableAnnotations != null) {
/* 204 */       for (AnnotationInfo repeatableAnnotation : repeatableAnnotations) {
/* 205 */         AnnotationParameterValueList values = repeatableAnnotation.getParameterValues();
/* 206 */         if (!values.isEmpty()) {
/* 207 */           AnnotationParameterValue apv = values.get("value");
/* 208 */           if (apv != null) {
/* 209 */             Object arr = apv.getValue();
/* 210 */             if (arr instanceof Object[]) {
/* 211 */               for (Object value : (Object[])arr) {
/* 212 */                 if (value instanceof AnnotationInfo) {
/* 213 */                   AnnotationInfo ai = (AnnotationInfo)value;
/* 214 */                   add(ai);
/*     */ 
/*     */                   
/* 217 */                   if (forwardRelType != null && (reverseRelType0 != null || reverseRelType1 != null)) {
/*     */                     
/* 219 */                     ClassInfo annotationClass = ai.getClassInfo();
/* 220 */                     if (annotationClass != null) {
/* 221 */                       containingClassInfo.addRelatedClass(forwardRelType, annotationClass);
/* 222 */                       if (reverseRelType0 != null) {
/* 223 */                         annotationClass.addRelatedClass(reverseRelType0, containingClassInfo);
/*     */                       }
/*     */                       
/* 226 */                       if (reverseRelType1 != null) {
/* 227 */                         annotationClass.addRelatedClass(reverseRelType1, containingClassInfo);
/*     */                       }
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             }
/*     */           } 
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
/*     */   
/*     */   private static void findMetaAnnotations(AnnotationInfo ai, AnnotationInfoList allAnnotationsOut, Set<ClassInfo> visited) {
/* 255 */     ClassInfo annotationClassInfo = ai.getClassInfo();
/* 256 */     if (annotationClassInfo != null && annotationClassInfo.annotationInfo != null && visited
/*     */       
/* 258 */       .add(annotationClassInfo)) {
/* 259 */       for (AnnotationInfo metaAnnotationInfo : annotationClassInfo.annotationInfo) {
/* 260 */         ClassInfo metaAnnotationClassInfo = metaAnnotationInfo.getClassInfo();
/* 261 */         String metaAnnotationClassName = metaAnnotationClassInfo.getName();
/*     */         
/* 263 */         if (!metaAnnotationClassName.startsWith("java.lang.annotation.")) {
/*     */           
/* 265 */           allAnnotationsOut.add(metaAnnotationInfo);
/*     */           
/* 267 */           findMetaAnnotations(metaAnnotationInfo, allAnnotationsOut, visited);
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
/*     */   static AnnotationInfoList getIndirectAnnotations(AnnotationInfoList directAnnotationInfo, ClassInfo annotatedClass) {
/* 285 */     Set<ClassInfo> directOrInheritedAnnotationClasses = new HashSet<>();
/* 286 */     Set<ClassInfo> reachedAnnotationClasses = new HashSet<>();
/*     */     
/* 288 */     AnnotationInfoList reachableAnnotationInfo = new AnnotationInfoList((directAnnotationInfo == null) ? 2 : directAnnotationInfo.size());
/* 289 */     if (directAnnotationInfo != null) {
/* 290 */       for (AnnotationInfo dai : directAnnotationInfo) {
/* 291 */         directOrInheritedAnnotationClasses.add(dai.getClassInfo());
/* 292 */         reachableAnnotationInfo.add(dai);
/* 293 */         findMetaAnnotations(dai, reachableAnnotationInfo, reachedAnnotationClasses);
/*     */       } 
/*     */     }
/* 296 */     if (annotatedClass != null)
/*     */     {
/* 298 */       for (ClassInfo superclass : annotatedClass.getSuperclasses()) {
/* 299 */         if (superclass.annotationInfo != null) {
/* 300 */           for (AnnotationInfo sai : superclass.annotationInfo) {
/*     */             
/* 302 */             if (sai.isInherited() && directOrInheritedAnnotationClasses.add(sai.getClassInfo())) {
/* 303 */               reachableAnnotationInfo.add(sai);
/* 304 */               AnnotationInfoList reachableMetaAnnotationInfo = new AnnotationInfoList(2);
/* 305 */               findMetaAnnotations(sai, reachableMetaAnnotationInfo, reachedAnnotationClasses);
/*     */               
/* 307 */               for (AnnotationInfo rmai : reachableMetaAnnotationInfo) {
/* 308 */                 if (rmai.isInherited()) {
/* 309 */                   reachableAnnotationInfo.add(rmai);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 320 */     AnnotationInfoList directAnnotationInfoSorted = (directAnnotationInfo == null) ? EMPTY_LIST : new AnnotationInfoList(directAnnotationInfo);
/* 321 */     CollectionUtils.sortIfNotEmpty(directAnnotationInfoSorted);
/* 322 */     AnnotationInfoList annotationInfoList = new AnnotationInfoList(reachableAnnotationInfo, directAnnotationInfoSorted);
/*     */     
/* 324 */     CollectionUtils.sortIfNotEmpty(annotationInfoList);
/* 325 */     return annotationInfoList;
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
/*     */   public AnnotationInfoList directOnly() {
/* 342 */     return (this.directlyRelatedAnnotations == null) ? this : 
/*     */       
/* 344 */       new AnnotationInfoList(this.directlyRelatedAnnotations, null);
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
/*     */   public AnnotationInfoList getRepeatable(Class<? extends Annotation> annotationClass) {
/* 357 */     Assert.isAnnotation(annotationClass);
/* 358 */     return getRepeatable(annotationClass.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationInfoList getRepeatable(String name) {
/* 369 */     boolean hasNamedAnnotation = false;
/* 370 */     for (AnnotationInfo ai : this) {
/* 371 */       if (ai.getName().equals(name)) {
/* 372 */         hasNamedAnnotation = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 376 */     if (!hasNamedAnnotation) {
/* 377 */       return EMPTY_LIST;
/*     */     }
/* 379 */     AnnotationInfoList matchingAnnotations = new AnnotationInfoList(size());
/* 380 */     for (AnnotationInfo ai : this) {
/* 381 */       if (ai.getName().equals(name)) {
/* 382 */         matchingAnnotations.add(ai);
/*     */       }
/*     */     } 
/* 385 */     return matchingAnnotations;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 395 */     if (this == obj)
/* 396 */       return true; 
/* 397 */     if (!(obj instanceof AnnotationInfoList)) {
/* 398 */       return false;
/*     */     }
/* 400 */     AnnotationInfoList other = (AnnotationInfoList)obj;
/* 401 */     if (((this.directlyRelatedAnnotations == null) ? true : false) != ((other.directlyRelatedAnnotations == null) ? true : false)) {
/* 402 */       return false;
/*     */     }
/* 404 */     if (this.directlyRelatedAnnotations == null) {
/* 405 */       return super.equals(other);
/*     */     }
/* 407 */     return (super.equals(other) && this.directlyRelatedAnnotations.equals(other.directlyRelatedAnnotations));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 415 */     return super.hashCode() ^ ((this.directlyRelatedAnnotations == null) ? 0 : this.directlyRelatedAnnotations.hashCode());
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface AnnotationInfoFilter {
/*     */     boolean accept(AnnotationInfo param1AnnotationInfo);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\AnnotationInfoList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */