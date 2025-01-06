/*    */ package nonapi.io.github.classgraph.utils;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CollectionUtils
/*    */ {
/*    */   public static <T extends Comparable<? super T>> void sortIfNotEmpty(List<T> list) {
/* 58 */     if (list.size() > 1) {
/* 59 */       Collections.sort(list);
/*    */     }
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
/*    */   public static <T> void sortIfNotEmpty(List<T> list, Comparator<? super T> comparator) {
/* 76 */     if (list.size() > 1) {
/* 77 */       Collections.sort(list, comparator);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static <T extends Comparable<T>> List<T> sortCopy(Collection<T> elts) {
/* 89 */     List<T> sortedCopy = new ArrayList<>(elts);
/* 90 */     if (sortedCopy.size() > 1) {
/* 91 */       Collections.sort(sortedCopy);
/*    */     }
/* 93 */     return sortedCopy;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgrap\\utils\CollectionUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */