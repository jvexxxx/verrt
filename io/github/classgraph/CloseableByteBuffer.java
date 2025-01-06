/*    */ package io.github.classgraph;
/*    */ 
/*    */ import java.io.Closeable;
/*    */ import java.io.IOException;
/*    */ import java.nio.ByteBuffer;
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
/*    */ public class CloseableByteBuffer
/*    */   implements Closeable
/*    */ {
/*    */   private ByteBuffer byteBuffer;
/*    */   private Runnable onClose;
/*    */   
/*    */   CloseableByteBuffer(ByteBuffer byteBuffer, Runnable onClose) {
/* 53 */     this.byteBuffer = byteBuffer;
/* 54 */     this.onClose = onClose;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ByteBuffer getByteBuffer() {
/* 61 */     return this.byteBuffer;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void close() throws IOException {
/* 67 */     if (this.onClose != null) {
/*    */       try {
/* 69 */         this.onClose.run();
/* 70 */       } catch (Exception exception) {}
/*    */ 
/*    */       
/* 73 */       this.onClose = null;
/*    */     } 
/* 75 */     this.byteBuffer = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\CloseableByteBuffer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */