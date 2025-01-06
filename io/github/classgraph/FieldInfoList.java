/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class FieldInfoList
/*     */   extends MappableInfoList<FieldInfo>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  43 */   static final FieldInfoList EMPTY_LIST = new FieldInfoList();
/*     */   static {
/*  45 */     EMPTY_LIST.makeUnmodifiable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static FieldInfoList emptyList() {
/*  54 */     return EMPTY_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldInfoList() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldInfoList(int sizeHint) {
/*  71 */     super(sizeHint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldInfoList(Collection<FieldInfo> fieldInfoCollection) {
/*  81 */     super(fieldInfoCollection);
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
/*  98 */     for (FieldInfo fi : this) {
/*  99 */       fi.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FieldInfoList filter(FieldInfoFilter filter) {
/* 131 */     FieldInfoList fieldInfoFiltered = new FieldInfoList();
/* 132 */     for (FieldInfo resource : this) {
/* 133 */       if (filter.accept(resource)) {
/* 134 */         fieldInfoFiltered.add(resource);
/*     */       }
/*     */     } 
/* 137 */     return fieldInfoFiltered;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface FieldInfoFilter {
/*     */     boolean accept(FieldInfo param1FieldInfo);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\FieldInfoList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */