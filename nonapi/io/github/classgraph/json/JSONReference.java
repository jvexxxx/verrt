/*    */ package nonapi.io.github.classgraph.json;
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
/*    */ class JSONReference
/*    */ {
/*    */   Object idObject;
/*    */   
/*    */   public JSONReference(Object idObject) {
/* 43 */     if (idObject == null) {
/* 44 */       throw new IllegalArgumentException("idObject cannot be null");
/*    */     }
/* 46 */     this.idObject = idObject;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\JSONReference.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */