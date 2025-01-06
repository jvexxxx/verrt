/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
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
/*     */ public class ClassInfoList
/*     */   extends MappableInfoList<ClassInfo>
/*     */ {
/*     */   private final transient Set<ClassInfo> directlyRelatedClasses;
/*     */   private final boolean sortByName;
/*     */   private static final long serialVersionUID = 1L;
/*  74 */   static final ClassInfoList EMPTY_LIST = new ClassInfoList();
/*     */   static {
/*  76 */     EMPTY_LIST.makeUnmodifiable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ClassInfoList emptyList() {
/*  85 */     return EMPTY_LIST;
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
/*     */   ClassInfoList(Set<ClassInfo> reachableClasses, Set<ClassInfo> directlyRelatedClasses, boolean sortByName) {
/* 101 */     super(reachableClasses);
/* 102 */     this.sortByName = sortByName;
/* 103 */     if (sortByName)
/*     */     {
/*     */       
/* 106 */       CollectionUtils.sortIfNotEmpty(this);
/*     */     }
/*     */     
/* 109 */     this.directlyRelatedClasses = (directlyRelatedClasses == null) ? reachableClasses : directlyRelatedClasses;
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
/*     */   ClassInfoList(ClassInfo.ReachableAndDirectlyRelatedClasses reachableAndDirectlyRelatedClasses, boolean sortByName) {
/* 122 */     this(reachableAndDirectlyRelatedClasses.reachableClasses, reachableAndDirectlyRelatedClasses.directlyRelatedClasses, sortByName);
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
/*     */   ClassInfoList(Set<ClassInfo> reachableClasses, boolean sortByName) {
/* 135 */     this(reachableClasses, (Set<ClassInfo>)null, sortByName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfoList() {
/* 142 */     super(1);
/* 143 */     this.sortByName = false;
/* 144 */     this.directlyRelatedClasses = new HashSet<>(2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfoList(int sizeHint) {
/* 154 */     super(sizeHint);
/* 155 */     this.sortByName = false;
/* 156 */     this.directlyRelatedClasses = new HashSet<>(2);
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
/*     */   public ClassInfoList(Collection<ClassInfo> classInfoCollection) {
/* 171 */     this((classInfoCollection instanceof Set) ? 
/* 172 */         (Set<ClassInfo>)classInfoCollection : 
/* 173 */         new HashSet<>(classInfoCollection), (Set<ClassInfo>)null, true);
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
/*     */   
/*     */   public <T> List<Class<T>> loadClasses(Class<T> superclassOrInterfaceType, boolean ignoreExceptions) {
/* 206 */     if (isEmpty()) {
/* 207 */       return Collections.emptyList();
/*     */     }
/* 209 */     List<Class<T>> classRefs = new ArrayList<>();
/* 210 */     for (ClassInfo classInfo : this) {
/* 211 */       Class<T> classRef = classInfo.loadClass(superclassOrInterfaceType, ignoreExceptions);
/* 212 */       if (classRef != null) {
/* 213 */         classRefs.add(classRef);
/*     */       }
/*     */     } 
/* 216 */     return classRefs.isEmpty() ? Collections.<Class<T>>emptyList() : classRefs;
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
/*     */   public <T> List<Class<T>> loadClasses(Class<T> superclassOrInterfaceType) {
/* 239 */     return loadClasses(superclassOrInterfaceType, false);
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
/*     */   public List<Class<?>> loadClasses(boolean ignoreExceptions) {
/* 257 */     if (isEmpty()) {
/* 258 */       return Collections.emptyList();
/*     */     }
/* 260 */     List<Class<?>> classRefs = new ArrayList<>();
/*     */     
/* 262 */     for (ClassInfo classInfo : this) {
/* 263 */       Class<?> classRef = classInfo.loadClass(ignoreExceptions);
/* 264 */       if (classRef != null) {
/* 265 */         classRefs.add(classRef);
/*     */       }
/*     */     } 
/* 268 */     return classRefs.isEmpty() ? Collections.<Class<?>>emptyList() : classRefs;
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
/*     */   public List<Class<?>> loadClasses() {
/* 281 */     return loadClasses(false);
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
/*     */   public ClassInfoList directOnly() {
/* 294 */     return new ClassInfoList(this.directlyRelatedClasses, this.directlyRelatedClasses, this.sortByName);
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
/*     */   public ClassInfoList union(ClassInfoList... others) {
/* 307 */     Set<ClassInfo> reachableClassesUnion = new LinkedHashSet<>(this);
/* 308 */     Set<ClassInfo> directlyRelatedClassesUnion = new LinkedHashSet<>(this.directlyRelatedClasses);
/* 309 */     for (ClassInfoList other : others) {
/* 310 */       reachableClassesUnion.addAll(other);
/* 311 */       directlyRelatedClassesUnion.addAll(other.directlyRelatedClasses);
/*     */     } 
/* 313 */     return new ClassInfoList(reachableClassesUnion, directlyRelatedClassesUnion, this.sortByName);
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
/*     */   public ClassInfoList intersect(ClassInfoList... others) {
/* 326 */     ArrayDeque<ClassInfoList> intersectionOrder = new ArrayDeque<>();
/* 327 */     intersectionOrder.add(this);
/* 328 */     boolean foundFirst = false;
/* 329 */     for (ClassInfoList other : others) {
/* 330 */       if (other.sortByName) {
/* 331 */         intersectionOrder.add(other);
/* 332 */       } else if (!foundFirst) {
/* 333 */         foundFirst = true;
/* 334 */         intersectionOrder.push(other);
/*     */       } else {
/* 336 */         intersectionOrder.add(other);
/*     */       } 
/*     */     } 
/* 339 */     ClassInfoList first = intersectionOrder.remove();
/* 340 */     Set<ClassInfo> reachableClassesIntersection = new LinkedHashSet<>(first);
/* 341 */     while (!intersectionOrder.isEmpty()) {
/* 342 */       reachableClassesIntersection.retainAll(intersectionOrder.remove());
/*     */     }
/* 344 */     Set<ClassInfo> directlyRelatedClassesIntersection = new LinkedHashSet<>(this.directlyRelatedClasses);
/* 345 */     for (ClassInfoList other : others) {
/* 346 */       directlyRelatedClassesIntersection.retainAll(other.directlyRelatedClasses);
/*     */     }
/* 348 */     return new ClassInfoList(reachableClassesIntersection, directlyRelatedClassesIntersection, first.sortByName);
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
/*     */   public ClassInfoList exclude(ClassInfoList other) {
/* 361 */     Set<ClassInfo> reachableClassesDifference = new LinkedHashSet<>(this);
/* 362 */     Set<ClassInfo> directlyRelatedClassesDifference = new LinkedHashSet<>(this.directlyRelatedClasses);
/* 363 */     reachableClassesDifference.removeAll(other);
/* 364 */     directlyRelatedClassesDifference.removeAll(other.directlyRelatedClasses);
/* 365 */     return new ClassInfoList(reachableClassesDifference, directlyRelatedClassesDifference, this.sortByName);
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
/*     */   public ClassInfoList filter(ClassInfoFilter filter) {
/* 395 */     Set<ClassInfo> reachableClassesFiltered = new LinkedHashSet<>(size());
/* 396 */     Set<ClassInfo> directlyRelatedClassesFiltered = new LinkedHashSet<>(this.directlyRelatedClasses.size());
/* 397 */     for (ClassInfo ci : this) {
/* 398 */       if (filter.accept(ci)) {
/* 399 */         reachableClassesFiltered.add(ci);
/* 400 */         if (this.directlyRelatedClasses.contains(ci)) {
/* 401 */           directlyRelatedClassesFiltered.add(ci);
/*     */         }
/*     */       } 
/*     */     } 
/* 405 */     return new ClassInfoList(reachableClassesFiltered, directlyRelatedClassesFiltered, this.sortByName);
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
/*     */   public ClassInfoList getStandardClasses() {
/* 417 */     return filter(new ClassInfoFilter()
/*     */         {
/*     */           public boolean accept(ClassInfo ci) {
/* 420 */             return ci.isStandardClass();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfoList getInterfaces() {
/* 432 */     return filter(new ClassInfoFilter()
/*     */         {
/*     */           public boolean accept(ClassInfo ci) {
/* 435 */             return ci.isInterface();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfoList getInterfacesAndAnnotations() {
/* 447 */     return filter(new ClassInfoFilter()
/*     */         {
/*     */           public boolean accept(ClassInfo ci) {
/* 450 */             return ci.isInterfaceOrAnnotation();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfoList getImplementedInterfaces() {
/* 462 */     return filter(new ClassInfoFilter()
/*     */         {
/*     */           public boolean accept(ClassInfo ci) {
/* 465 */             return ci.isImplementedInterface();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfoList getAnnotations() {
/* 476 */     return filter(new ClassInfoFilter()
/*     */         {
/*     */           public boolean accept(ClassInfo ci) {
/* 479 */             return ci.isAnnotation();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfoList getEnums() {
/* 490 */     return filter(new ClassInfoFilter()
/*     */         {
/*     */           public boolean accept(ClassInfo ci) {
/* 493 */             return ci.isEnum();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassInfoList getRecords() {
/* 504 */     return filter(new ClassInfoFilter()
/*     */         {
/*     */           public boolean accept(ClassInfo ci) {
/* 507 */             return ci.isRecord();
/*     */           }
/*     */         });
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
/*     */   public ClassInfoList getAssignableTo(ClassInfo superclassOrInterface) {
/* 527 */     if (superclassOrInterface == null) {
/* 528 */       throw new IllegalArgumentException("assignableToClass parameter cannot be null");
/*     */     }
/*     */     
/* 531 */     final Set<ClassInfo> allAssignableFromClasses = new HashSet<>();
/* 532 */     if (superclassOrInterface.isStandardClass()) {
/* 533 */       allAssignableFromClasses.addAll(superclassOrInterface.getSubclasses());
/* 534 */     } else if (superclassOrInterface.isInterfaceOrAnnotation()) {
/* 535 */       allAssignableFromClasses.addAll(superclassOrInterface.getClassesImplementing());
/*     */     } 
/*     */     
/* 538 */     allAssignableFromClasses.add(superclassOrInterface);
/*     */     
/* 540 */     return filter(new ClassInfoFilter()
/*     */         {
/*     */           public boolean accept(ClassInfo ci) {
/* 543 */             return allAssignableFromClasses.contains(ci);
/*     */           }
/*     */         });
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
/*     */   public String generateGraphVizDotFileFromInterClassDependencies(float sizeX, float sizeY, boolean includeExternalClasses) {
/* 570 */     if (isEmpty()) {
/* 571 */       throw new IllegalArgumentException("List is empty");
/*     */     }
/* 573 */     ScanSpec scanSpec = (get(0)).scanResult.scanSpec;
/* 574 */     if (!scanSpec.enableInterClassDependencies) {
/* 575 */       throw new IllegalArgumentException("Please call ClassGraph#enableInterClassDependencies() before #scan()");
/*     */     }
/*     */     
/* 578 */     return GraphvizDotfileGenerator.generateGraphVizDotFileFromInterClassDependencies(this, sizeX, sizeY, includeExternalClasses);
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
/*     */   public String generateGraphVizDotFileFromInterClassDependencies(float sizeX, float sizeY) {
/* 603 */     if (isEmpty()) {
/* 604 */       throw new IllegalArgumentException("List is empty");
/*     */     }
/* 606 */     ScanSpec scanSpec = (get(0)).scanResult.scanSpec;
/* 607 */     if (!scanSpec.enableInterClassDependencies) {
/* 608 */       throw new IllegalArgumentException("Please call ClassGraph#enableInterClassDependencies() before #scan()");
/*     */     }
/*     */     
/* 611 */     return GraphvizDotfileGenerator.generateGraphVizDotFileFromInterClassDependencies(this, sizeX, sizeY, scanSpec.enableExternalClasses);
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
/*     */   public String generateGraphVizDotFileFromInterClassDependencies() {
/* 632 */     if (isEmpty()) {
/* 633 */       throw new IllegalArgumentException("List is empty");
/*     */     }
/* 635 */     ScanSpec scanSpec = (get(0)).scanResult.scanSpec;
/* 636 */     if (!scanSpec.enableInterClassDependencies) {
/* 637 */       throw new IllegalArgumentException("Please call ClassGraph#enableInterClassDependencies() before #scan()");
/*     */     }
/*     */     
/* 640 */     return GraphvizDotfileGenerator.generateGraphVizDotFileFromInterClassDependencies(this, 10.5F, 8.0F, scanSpec.enableExternalClasses);
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
/*     */   @Deprecated
/*     */   public String generateGraphVizDotFileFromClassDependencies() {
/* 655 */     return generateGraphVizDotFileFromInterClassDependencies();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String generateGraphVizDotFile(float sizeX, float sizeY, boolean showFields, boolean showFieldTypeDependencyEdges, boolean showMethods, boolean showMethodTypeDependencyEdges, boolean showAnnotations, boolean useSimpleNames) {
/* 707 */     if (isEmpty()) {
/* 708 */       throw new IllegalArgumentException("List is empty");
/*     */     }
/* 710 */     ScanSpec scanSpec = (get(0)).scanResult.scanSpec;
/* 711 */     if (!scanSpec.enableClassInfo) {
/* 712 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*     */     }
/* 714 */     return GraphvizDotfileGenerator.generateGraphVizDotFile(this, sizeX, sizeY, showFields, showFieldTypeDependencyEdges, showMethods, showMethodTypeDependencyEdges, showAnnotations, useSimpleNames, scanSpec);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String generateGraphVizDotFile(float sizeX, float sizeY, boolean showFields, boolean showFieldTypeDependencyEdges, boolean showMethods, boolean showMethodTypeDependencyEdges, boolean showAnnotations) {
/* 766 */     return generateGraphVizDotFile(sizeX, sizeY, showFields, showFieldTypeDependencyEdges, showMethods, showMethodTypeDependencyEdges, showAnnotations, true);
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
/*     */   public String generateGraphVizDotFile(float sizeX, float sizeY) {
/* 792 */     return generateGraphVizDotFile(sizeX, sizeY, true, true, true, true, true);
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
/*     */   public String generateGraphVizDotFile() {
/* 815 */     return generateGraphVizDotFile(10.5F, 8.0F, true, true, true, true, true);
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
/*     */   public void generateGraphVizDotFile(File file) throws IOException {
/* 842 */     PrintWriter writer = new PrintWriter(file); try {
/* 843 */       writer.print(generateGraphVizDotFile());
/* 844 */       writer.close();
/*     */     } catch (Throwable throwable) {
/*     */       try {
/*     */         writer.close();
/*     */       } catch (Throwable throwable1) {
/*     */         throwable.addSuppressed(throwable1);
/*     */       } 
/*     */       throw throwable;
/*     */     } 
/*     */   } public boolean equals(Object obj) {
/* 854 */     if (this == obj)
/* 855 */       return true; 
/* 856 */     if (!(obj instanceof ClassInfoList)) {
/* 857 */       return false;
/*     */     }
/* 859 */     ClassInfoList other = (ClassInfoList)obj;
/* 860 */     if (((this.directlyRelatedClasses == null) ? true : false) != ((other.directlyRelatedClasses == null) ? true : false)) {
/* 861 */       return false;
/*     */     }
/* 863 */     if (this.directlyRelatedClasses == null) {
/* 864 */       return super.equals(other);
/*     */     }
/* 866 */     return (super.equals(other) && this.directlyRelatedClasses.equals(other.directlyRelatedClasses));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 874 */     return super.hashCode() ^ ((this.directlyRelatedClasses == null) ? 0 : this.directlyRelatedClasses.hashCode());
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ClassInfoFilter {
/*     */     boolean accept(ClassInfo param1ClassInfo);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ClassInfoList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */