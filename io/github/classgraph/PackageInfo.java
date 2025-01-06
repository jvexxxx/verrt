/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ import nonapi.io.github.classgraph.utils.Assert;
/*     */ import nonapi.io.github.classgraph.utils.CollectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PackageInfo
/*     */   implements Comparable<PackageInfo>, HasName
/*     */ {
/*     */   private String name;
/*     */   private Set<AnnotationInfo> annotationInfoSet;
/*     */   private AnnotationInfoList annotationInfo;
/*     */   private PackageInfo parent;
/*     */   private Set<PackageInfo> children;
/*     */   private Map<String, ClassInfo> memberClassNameToClassInfo;
/*     */   
/*     */   PackageInfo() {}
/*     */   
/*     */   PackageInfo(String packageName) {
/*  80 */     this.name = packageName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  90 */     return this.name;
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
/*     */   void addAnnotations(AnnotationInfoList packageAnnotations) {
/* 103 */     if (packageAnnotations != null && !packageAnnotations.isEmpty()) {
/* 104 */       if (this.annotationInfoSet == null) {
/* 105 */         this.annotationInfoSet = new LinkedHashSet<>();
/*     */       }
/* 107 */       this.annotationInfoSet.addAll(packageAnnotations);
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
/*     */   void addClassInfo(ClassInfo classInfo) {
/* 119 */     if (this.memberClassNameToClassInfo == null) {
/* 120 */       this.memberClassNameToClassInfo = new HashMap<>();
/*     */     }
/* 122 */     this.memberClassNameToClassInfo.put(classInfo.getName(), classInfo);
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
/*     */   public AnnotationInfo getAnnotationInfo(Class<? extends Annotation> annotation) {
/* 136 */     Assert.isAnnotation(annotation);
/* 137 */     return getAnnotationInfo(annotation.getName());
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
/*     */   public AnnotationInfo getAnnotationInfo(String annotationName) {
/* 149 */     return getAnnotationInfo().get(annotationName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationInfoList getAnnotationInfo() {
/* 158 */     if (this.annotationInfo == null) {
/* 159 */       if (this.annotationInfoSet == null) {
/* 160 */         this.annotationInfo = AnnotationInfoList.EMPTY_LIST;
/*     */       } else {
/* 162 */         this.annotationInfo = new AnnotationInfoList();
/* 163 */         this.annotationInfo.addAll(this.annotationInfoSet);
/*     */       } 
/*     */     }
/* 166 */     return this.annotationInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAnnotation(Class<? extends Annotation> annotation) {
/* 177 */     Assert.isAnnotation(annotation);
/* 178 */     return hasAnnotation(annotation.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasAnnotation(String annotationName) {
/* 189 */     return getAnnotationInfo().containsName(annotationName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PackageInfo getParent() {
/* 200 */     return this.parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PackageInfoList getChildren() {
/* 209 */     if (this.children == null) {
/* 210 */       return PackageInfoList.EMPTY_LIST;
/*     */     }
/* 212 */     PackageInfoList childrenSorted = new PackageInfoList(this.children);
/*     */     
/* 214 */     CollectionUtils.sortIfNotEmpty(childrenSorted, new Comparator<PackageInfo>()
/*     */         {
/*     */           public int compare(PackageInfo o1, PackageInfo o2) {
/* 217 */             return o1.name.compareTo(o2.name);
/*     */           }
/*     */         });
/* 220 */     return childrenSorted;
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
/*     */   public ClassInfo getClassInfo(String className) {
/* 235 */     return (this.memberClassNameToClassInfo == null) ? null : this.memberClassNameToClassInfo.get(className);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfoList getClassInfo() {
/* 244 */     return (this.memberClassNameToClassInfo == null) ? ClassInfoList.EMPTY_LIST : 
/* 245 */       new ClassInfoList(new HashSet<>(this.memberClassNameToClassInfo.values()), true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void obtainClassInfoRecursive(Set<ClassInfo> reachableClassInfo) {
/* 255 */     if (this.memberClassNameToClassInfo != null) {
/* 256 */       reachableClassInfo.addAll(this.memberClassNameToClassInfo.values());
/*     */     }
/* 258 */     for (PackageInfo subPackageInfo : getChildren()) {
/* 259 */       subPackageInfo.obtainClassInfoRecursive(reachableClassInfo);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfoList getClassInfoRecursive() {
/* 269 */     Set<ClassInfo> reachableClassInfo = new HashSet<>();
/* 270 */     obtainClassInfoRecursive(reachableClassInfo);
/* 271 */     return new ClassInfoList(reachableClassInfo, true);
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
/*     */   static String getParentPackageName(String packageOrClassName) {
/* 285 */     if (packageOrClassName.isEmpty()) {
/* 286 */       return null;
/*     */     }
/* 288 */     int lastDotIdx = packageOrClassName.lastIndexOf('.');
/* 289 */     return (lastDotIdx < 0) ? "" : packageOrClassName.substring(0, lastDotIdx);
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
/*     */   static PackageInfo getOrCreatePackage(String packageName, Map<String, PackageInfo> packageNameToPackageInfo, ScanSpec scanSpec) {
/* 308 */     PackageInfo packageInfo = packageNameToPackageInfo.get(packageName);
/* 309 */     if (packageInfo != null)
/*     */     {
/* 311 */       return packageInfo;
/*     */     }
/*     */ 
/*     */     
/* 315 */     packageNameToPackageInfo.put(packageName, packageInfo = new PackageInfo(packageName));
/*     */ 
/*     */     
/* 318 */     if (!packageName.isEmpty()) {
/*     */ 
/*     */       
/* 321 */       String parentPackageName = getParentPackageName(packageInfo.name);
/* 322 */       if (scanSpec.packageAcceptReject.isAcceptedAndNotRejected(parentPackageName) || scanSpec.packagePrefixAcceptReject
/* 323 */         .isAcceptedAndNotRejected(parentPackageName)) {
/* 324 */         PackageInfo parentPackageInfo = getOrCreatePackage(parentPackageName, packageNameToPackageInfo, scanSpec);
/*     */         
/* 326 */         if (parentPackageInfo != null) {
/*     */           
/* 328 */           if (parentPackageInfo.children == null) {
/* 329 */             parentPackageInfo.children = new HashSet<>();
/*     */           }
/* 331 */           parentPackageInfo.children.add(packageInfo);
/* 332 */           packageInfo.parent = parentPackageInfo;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 338 */     return packageInfo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(PackageInfo o) {
/* 348 */     return this.name.compareTo(o.name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 356 */     return this.name.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 364 */     if (obj == this)
/* 365 */       return true; 
/* 366 */     if (!(obj instanceof PackageInfo)) {
/* 367 */       return false;
/*     */     }
/* 369 */     return this.name.equals(((PackageInfo)obj).name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 377 */     return this.name;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\PackageInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */