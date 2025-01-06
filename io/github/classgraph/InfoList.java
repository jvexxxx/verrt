/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InfoList<T extends HasName>
/*     */   extends PotentiallyUnmodifiableList<T>
/*     */ {
/*     */   static final long serialVersionUID = 1L;
/*     */   
/*     */   InfoList() {}
/*     */   
/*     */   InfoList(int sizeHint) {
/*  60 */     super(sizeHint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   InfoList(Collection<T> infoCollection) {
/*  70 */     super(infoCollection);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  76 */     return super.equals(o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  82 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getNames() {
/*  93 */     if (isEmpty()) {
/*  94 */       return Collections.emptyList();
/*     */     }
/*  96 */     List<String> names = new ArrayList<>(size());
/*  97 */     for (HasName hasName : this) {
/*  98 */       if (hasName != null) {
/*  99 */         names.add(hasName.getName());
/*     */       }
/*     */     } 
/* 102 */     return names;
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
/*     */   public List<String> getAsStrings() {
/* 114 */     if (isEmpty()) {
/* 115 */       return Collections.emptyList();
/*     */     }
/* 117 */     List<String> toStringVals = new ArrayList<>(size());
/* 118 */     for (HasName hasName : this) {
/* 119 */       toStringVals.add((hasName == null) ? "null" : hasName.toString());
/*     */     }
/* 121 */     return toStringVals;
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
/*     */   public List<String> getAsStringsWithSimpleNames() {
/* 137 */     if (isEmpty()) {
/* 138 */       return Collections.emptyList();
/*     */     }
/* 140 */     List<String> toStringVals = new ArrayList<>(size());
/* 141 */     for (HasName hasName : this) {
/* 142 */       toStringVals.add((hasName == null) ? "null" : (
/* 143 */           (hasName instanceof ScanResultObject) ? ((ScanResultObject)hasName).toStringWithSimpleNames() : 
/* 144 */           hasName.toString()));
/*     */     }
/* 146 */     return toStringVals;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\InfoList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */