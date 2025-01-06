/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.lang.annotation.Annotation;
/*     */ import java.net.URI;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModuleInfo
/*     */   implements Comparable<ModuleInfo>, HasName
/*     */ {
/*     */   private String name;
/*     */   private transient ClasspathElement classpathElement;
/*     */   private transient ModuleRef moduleRef;
/*     */   private transient URI locationURI;
/*     */   private Set<AnnotationInfo> annotationInfoSet;
/*     */   private AnnotationInfoList annotationInfo;
/*     */   private Set<PackageInfo> packageInfoSet;
/*     */   private Set<ClassInfo> classInfoSet;
/*     */   
/*     */   ModuleInfo() {}
/*     */   
/*     */   ModuleInfo(ModuleRef moduleRef, ClasspathElement classpathElement) {
/*  85 */     this.moduleRef = moduleRef;
/*  86 */     this.classpathElement = classpathElement;
/*  87 */     this.name = classpathElement.getModuleName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  97 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URI getLocation() {
/* 106 */     if (this.locationURI == null) {
/* 107 */       this.locationURI = (this.moduleRef != null) ? this.moduleRef.getLocation() : null;
/* 108 */       if (this.locationURI == null) {
/* 109 */         this.locationURI = this.classpathElement.getURI();
/*     */       }
/*     */     } 
/* 112 */     return this.locationURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModuleRef getModuleRef() {
/* 123 */     return this.moduleRef;
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
/*     */   void addClassInfo(ClassInfo classInfo) {
/* 135 */     if (this.classInfoSet == null) {
/* 136 */       this.classInfoSet = new HashSet<>();
/*     */     }
/* 138 */     this.classInfoSet.add(classInfo);
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
/*     */   public ClassInfo getClassInfo(String className) {
/* 151 */     for (ClassInfo ci : this.classInfoSet) {
/* 152 */       if (ci.getName().equals(className)) {
/* 153 */         return ci;
/*     */       }
/*     */     } 
/* 156 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfoList getClassInfo() {
/* 165 */     return new ClassInfoList(this.classInfoSet, true);
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
/*     */   void addPackageInfo(PackageInfo packageInfo) {
/* 177 */     if (this.packageInfoSet == null) {
/* 178 */       this.packageInfoSet = new HashSet<>();
/*     */     }
/* 180 */     this.packageInfoSet.add(packageInfo);
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
/*     */   public PackageInfo getPackageInfo(String packageName) {
/* 193 */     if (this.packageInfoSet == null) {
/* 194 */       return null;
/*     */     }
/* 196 */     for (PackageInfo pi : this.packageInfoSet) {
/* 197 */       if (pi.getName().equals(packageName)) {
/* 198 */         return pi;
/*     */       }
/*     */     } 
/* 201 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PackageInfoList getPackageInfo() {
/* 210 */     if (this.packageInfoSet == null) {
/* 211 */       return new PackageInfoList(1);
/*     */     }
/* 213 */     PackageInfoList packageInfoList = new PackageInfoList(this.packageInfoSet);
/* 214 */     CollectionUtils.sortIfNotEmpty(packageInfoList);
/* 215 */     return packageInfoList;
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
/*     */   void addAnnotations(AnnotationInfoList moduleAnnotations) {
/* 228 */     if (moduleAnnotations != null && !moduleAnnotations.isEmpty()) {
/* 229 */       if (this.annotationInfoSet == null) {
/* 230 */         this.annotationInfoSet = new LinkedHashSet<>();
/*     */       }
/* 232 */       this.annotationInfoSet.addAll(moduleAnnotations);
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
/*     */   public AnnotationInfo getAnnotationInfo(Class<? extends Annotation> annotation) {
/* 245 */     Assert.isAnnotation(annotation);
/* 246 */     return getAnnotationInfo(annotation.getName());
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
/* 258 */     return getAnnotationInfo().get(annotationName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationInfoList getAnnotationInfo() {
/* 267 */     if (this.annotationInfo == null) {
/* 268 */       if (this.annotationInfoSet == null) {
/* 269 */         this.annotationInfo = AnnotationInfoList.EMPTY_LIST;
/*     */       } else {
/* 271 */         this.annotationInfo = new AnnotationInfoList();
/* 272 */         this.annotationInfo.addAll(this.annotationInfoSet);
/*     */       } 
/*     */     }
/* 275 */     return this.annotationInfo;
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
/* 286 */     Assert.isAnnotation(annotation);
/* 287 */     return hasAnnotation(annotation.getName());
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
/* 298 */     return getAnnotationInfo().containsName(annotationName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(ModuleInfo other) {
/* 308 */     int diff = this.name.compareTo(other.name);
/* 309 */     if (diff != 0) {
/* 310 */       return diff;
/*     */     }
/* 312 */     URI thisLoc = getLocation();
/* 313 */     URI otherLoc = other.getLocation();
/* 314 */     if (thisLoc != null && otherLoc != null) {
/* 315 */       return thisLoc.compareTo(otherLoc);
/*     */     }
/* 317 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 325 */     return this.name.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 333 */     if (obj == this)
/* 334 */       return true; 
/* 335 */     if (!(obj instanceof ModuleInfo)) {
/* 336 */       return false;
/*     */     }
/* 338 */     return (compareTo((ModuleInfo)obj) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 346 */     return this.name;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ModuleInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */