/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MappableInfoList<T extends HasName>
/*     */   extends InfoList<T>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   MappableInfoList() {}
/*     */   
/*     */   MappableInfoList(int sizeHint) {
/*  59 */     super(sizeHint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   MappableInfoList(Collection<T> infoCollection) {
/*  69 */     super(infoCollection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, T> asMap() {
/*  80 */     Map<String, T> nameToInfoObject = new HashMap<>();
/*  81 */     for (HasName hasName : this) {
/*  82 */       if (hasName != null) {
/*  83 */         nameToInfoObject.put(hasName.getName(), (T)hasName);
/*     */       }
/*     */     } 
/*  86 */     return nameToInfoObject;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsName(String name) {
/*  97 */     for (HasName hasName : this) {
/*  98 */       if (hasName != null && hasName.getName().equals(name)) {
/*  99 */         return true;
/*     */       }
/*     */     } 
/* 102 */     return false;
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
/*     */   public T get(String name) {
/* 114 */     for (HasName hasName : this) {
/* 115 */       if (hasName != null && hasName.getName().equals(name)) {
/* 116 */         return (T)hasName;
/*     */       }
/*     */     } 
/* 119 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\MappableInfoList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */