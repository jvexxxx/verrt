/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.Collection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PackageInfoList
/*     */   extends MappableInfoList<PackageInfo>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   PackageInfoList() {}
/*     */   
/*     */   PackageInfoList(int sizeHint) {
/*  52 */     super(sizeHint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PackageInfoList(Collection<PackageInfo> packageInfoCollection) {
/*  62 */     super(packageInfoCollection);
/*     */   }
/*     */ 
/*     */   
/*  66 */   static final PackageInfoList EMPTY_LIST = new PackageInfoList()
/*     */     {
/*     */       private static final long serialVersionUID = 1L;
/*     */ 
/*     */       
/*     */       public boolean add(PackageInfo e) {
/*  72 */         throw new IllegalArgumentException("List is immutable");
/*     */       }
/*     */ 
/*     */       
/*     */       public void add(int index, PackageInfo element) {
/*  77 */         throw new IllegalArgumentException("List is immutable");
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean remove(Object o) {
/*  82 */         throw new IllegalArgumentException("List is immutable");
/*     */       }
/*     */ 
/*     */       
/*     */       public PackageInfo remove(int index) {
/*  87 */         throw new IllegalArgumentException("List is immutable");
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean addAll(Collection<? extends PackageInfo> c) {
/*  92 */         throw new IllegalArgumentException("List is immutable");
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean addAll(int index, Collection<? extends PackageInfo> c) {
/*  97 */         throw new IllegalArgumentException("List is immutable");
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean removeAll(Collection<?> c) {
/* 102 */         throw new IllegalArgumentException("List is immutable");
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean retainAll(Collection<?> c) {
/* 107 */         throw new IllegalArgumentException("List is immutable");
/*     */       }
/*     */ 
/*     */       
/*     */       public void clear() {
/* 112 */         throw new IllegalArgumentException("List is immutable");
/*     */       }
/*     */ 
/*     */       
/*     */       public PackageInfo set(int index, PackageInfo element) {
/* 117 */         throw new IllegalArgumentException("List is immutable");
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface PackageInfoFilter
/*     */   {
/*     */     boolean accept(PackageInfo param1PackageInfo);
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
/*     */   public PackageInfoList filter(PackageInfoFilter filter) {
/* 149 */     PackageInfoList packageInfoFiltered = new PackageInfoList();
/* 150 */     for (PackageInfo resource : this) {
/* 151 */       if (filter.accept(resource)) {
/* 152 */         packageInfoFiltered.add(resource);
/*     */       }
/*     */     } 
/* 155 */     return packageInfoFiltered;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\PackageInfoList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */