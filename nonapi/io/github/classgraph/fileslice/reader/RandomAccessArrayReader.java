/*     */ package nonapi.io.github.classgraph.fileslice.reader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.BufferUnderflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import nonapi.io.github.classgraph.utils.StringUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomAccessArrayReader
/*     */   implements RandomAccessReader
/*     */ {
/*     */   private final byte[] arr;
/*     */   private final int sliceStartPos;
/*     */   private final int sliceLength;
/*     */   
/*     */   public RandomAccessArrayReader(byte[] arr, int sliceStartPos, int sliceLength) {
/*  64 */     this.arr = arr;
/*  65 */     this.sliceStartPos = sliceStartPos;
/*  66 */     this.sliceLength = sliceLength;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(long srcOffset, byte[] dstArr, int dstArrStart, int numBytes) throws IOException {
/*  72 */     if (numBytes == 0) {
/*  73 */       return 0;
/*     */     }
/*  75 */     if (srcOffset < 0L || numBytes < 0 || numBytes > this.sliceLength - srcOffset) {
/*  76 */       throw new IOException("Read index out of bounds");
/*     */     }
/*     */     try {
/*  79 */       int numBytesToRead = Math.max(Math.min(numBytes, dstArr.length - dstArrStart), 0);
/*  80 */       if (numBytesToRead == 0) {
/*  81 */         return -1;
/*     */       }
/*  83 */       int srcStart = (int)(this.sliceStartPos + srcOffset);
/*  84 */       System.arraycopy(this.arr, srcStart, dstArr, dstArrStart, numBytesToRead);
/*  85 */       return numBytesToRead;
/*  86 */     } catch (IndexOutOfBoundsException e) {
/*  87 */       throw new IOException("Read index out of bounds");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(long srcOffset, ByteBuffer dstBuf, int dstBufStart, int numBytes) throws IOException {
/*  94 */     if (numBytes == 0) {
/*  95 */       return 0;
/*     */     }
/*  97 */     if (srcOffset < 0L || numBytes < 0 || numBytes > this.sliceLength - srcOffset) {
/*  98 */       throw new IOException("Read index out of bounds");
/*     */     }
/*     */     try {
/* 101 */       int numBytesToRead = Math.max(Math.min(numBytes, dstBuf.capacity() - dstBufStart), 0);
/* 102 */       if (numBytesToRead == 0) {
/* 103 */         return -1;
/*     */       }
/* 105 */       int srcStart = (int)(this.sliceStartPos + srcOffset);
/* 106 */       dstBuf.position(dstBufStart);
/* 107 */       dstBuf.limit(dstBufStart + numBytesToRead);
/* 108 */       dstBuf.put(this.arr, srcStart, numBytesToRead);
/* 109 */       return numBytesToRead;
/* 110 */     } catch (BufferUnderflowException|IndexOutOfBoundsException|java.nio.ReadOnlyBufferException e) {
/* 111 */       throw new IOException("Read index out of bounds");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public byte readByte(long offset) throws IOException {
/* 117 */     int idx = this.sliceStartPos + (int)offset;
/* 118 */     return this.arr[idx];
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedByte(long offset) throws IOException {
/* 123 */     int idx = this.sliceStartPos + (int)offset;
/* 124 */     return this.arr[idx] & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public short readShort(long offset) throws IOException {
/* 129 */     return (short)readUnsignedShort(offset);
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedShort(long offset) throws IOException {
/* 134 */     int idx = this.sliceStartPos + (int)offset;
/* 135 */     return (this.arr[idx + 1] & 0xFF) << 8 | this.arr[idx] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int readInt(long offset) throws IOException {
/* 141 */     int idx = this.sliceStartPos + (int)offset;
/* 142 */     return (this.arr[idx + 3] & 0xFF) << 24 | (this.arr[idx + 2] & 0xFF) << 16 | (this.arr[idx + 1] & 0xFF) << 8 | this.arr[idx] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long readUnsignedInt(long offset) throws IOException {
/* 150 */     return readInt(offset) & 0xFFFFFFFFL;
/*     */   }
/*     */ 
/*     */   
/*     */   public long readLong(long offset) throws IOException {
/* 155 */     int idx = this.sliceStartPos + (int)offset;
/* 156 */     return (this.arr[idx + 7] & 0xFFL) << 56L | (this.arr[idx + 6] & 0xFFL) << 48L | (this.arr[idx + 5] & 0xFFL) << 40L | (this.arr[idx + 4] & 0xFFL) << 32L | (this.arr[idx + 3] & 0xFFL) << 24L | (this.arr[idx + 2] & 0xFFL) << 16L | (this.arr[idx + 1] & 0xFFL) << 8L | this.arr[idx] & 0xFFL;
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
/*     */   public String readString(long offset, int numBytes, boolean replaceSlashWithDot, boolean stripLSemicolon) throws IOException {
/* 169 */     int idx = this.sliceStartPos + (int)offset;
/* 170 */     return StringUtils.readString(this.arr, idx, numBytes, replaceSlashWithDot, stripLSemicolon);
/*     */   }
/*     */ 
/*     */   
/*     */   public String readString(long offset, int numBytes) throws IOException {
/* 175 */     return readString(offset, numBytes, false, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fileslice\reader\RandomAccessArrayReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */