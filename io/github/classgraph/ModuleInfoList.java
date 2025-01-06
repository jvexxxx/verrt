/*    */ package io.github.classgraph;
/*    */ 
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModuleInfoList
/*    */   extends MappableInfoList<ModuleInfo>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   ModuleInfoList() {}
/*    */   
/*    */   ModuleInfoList(int sizeHint) {
/* 52 */     super(sizeHint);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   ModuleInfoList(Collection<ModuleInfo> moduleInfoCollection) {
/* 62 */     super(moduleInfoCollection);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ModuleInfoList filter(ModuleInfoFilter filter) {
/* 93 */     ModuleInfoList moduleInfoFiltered = new ModuleInfoList();
/* 94 */     for (ModuleInfo resource : this) {
/* 95 */       if (filter.accept(resource)) {
/* 96 */         moduleInfoFiltered.add(resource);
/*    */       }
/*    */     } 
/* 99 */     return moduleInfoFiltered;
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface ModuleInfoFilter {
/*    */     boolean accept(ModuleInfo param1ModuleInfo);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ModuleInfoList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */