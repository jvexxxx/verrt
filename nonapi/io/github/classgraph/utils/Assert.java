/*    */ package nonapi.io.github.classgraph.utils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Assert
/*    */ {
/*    */   public static void isAnnotation(Class<?> clazz) {
/* 14 */     if (!clazz.isAnnotation()) {
/* 15 */       throw new IllegalArgumentException(clazz + " is not an annotation");
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
/*    */   public static void isInterface(Class<?> clazz) {
/* 28 */     if (!clazz.isInterface())
/* 29 */       throw new IllegalArgumentException(clazz + " is not an interface"); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgrap\\utils\Assert.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */