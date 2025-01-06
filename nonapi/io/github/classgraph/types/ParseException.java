/*    */ package nonapi.io.github.classgraph.types;
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
/*    */ public class ParseException
/*    */   extends Exception
/*    */ {
/*    */   static final long serialVersionUID = 1L;
/*    */   
/*    */   public ParseException(Parser parser, String msg) {
/* 47 */     super((parser == null) ? msg : (msg + " (" + parser.getPositionInfo() + ")"));
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\types\ParseException.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */