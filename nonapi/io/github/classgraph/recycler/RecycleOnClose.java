/*    */ package nonapi.io.github.classgraph.recycler;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecycleOnClose<T, E extends Exception>
/*    */   implements AutoCloseable
/*    */ {
/*    */   private final Recycler<T, E> recycler;
/*    */   private final T instance;
/*    */   
/*    */   RecycleOnClose(Recycler<T, E> recycler, T instance) {
/* 59 */     this.recycler = recycler;
/* 60 */     this.instance = instance;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public T get() {
/* 69 */     return this.instance;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() {
/* 75 */     this.recycler.recycle(this.instance);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\recycler\RecycleOnClose.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */