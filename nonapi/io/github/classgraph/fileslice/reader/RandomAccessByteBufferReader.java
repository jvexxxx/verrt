/*     */ package nonapi.io.github.classgraph.fileslice.reader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.BufferUnderflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
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
/*     */ 
/*     */ public class RandomAccessByteBufferReader
/*     */   implements RandomAccessReader
/*     */ {
/*     */   private final ByteBuffer byteBuffer;
/*     */   private final int sliceStartPos;
/*     */   private final int sliceLength;
/*     */   
/*     */   public RandomAccessByteBufferReader(ByteBuffer byteBuffer, long sliceStartPos, long sliceLength) {
/*  66 */     this.byteBuffer = byteBuffer.duplicate();
/*  67 */     this.byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
/*  68 */     this.sliceStartPos = (int)sliceStartPos;
/*  69 */     this.sliceLength = (int)sliceLength;
/*  70 */     this.byteBuffer.position(this.sliceStartPos);
/*  71 */     this.byteBuffer.limit(this.sliceStartPos + this.sliceLength);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(long srcOffset, byte[] dstArr, int dstArrStart, int numBytes) throws IOException {
/*  77 */     if (numBytes == 0) {
/*  78 */       return 0;
/*     */     }
/*  80 */     if (srcOffset < 0L || numBytes < 0 || numBytes > this.sliceLength - srcOffset) {
/*  81 */       throw new IOException("Read index out of bounds");
/*     */     }
/*     */     try {
/*  84 */       int numBytesToRead = Math.max(Math.min(numBytes, dstArr.length - dstArrStart), 0);
/*  85 */       if (numBytesToRead == 0) {
/*  86 */         return -1;
/*     */       }
/*  88 */       int srcStart = (int)srcOffset;
/*  89 */       this.byteBuffer.position(this.sliceStartPos + srcStart);
/*  90 */       this.byteBuffer.get(dstArr, dstArrStart, numBytesToRead);
/*  91 */       this.byteBuffer.position(this.sliceStartPos);
/*  92 */       return numBytesToRead;
/*  93 */     } catch (IndexOutOfBoundsException e) {
/*  94 */       throw new IOException("Read index out of bounds");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(long srcOffset, ByteBuffer dstBuf, int dstBufStart, int numBytes) throws IOException {
/* 101 */     if (numBytes == 0) {
/* 102 */       return 0;
/*     */     }
/* 104 */     if (srcOffset < 0L || numBytes < 0 || numBytes > this.sliceLength - srcOffset) {
/* 105 */       throw new IOException("Read index out of bounds");
/*     */     }
/*     */     try {
/* 108 */       int numBytesToRead = Math.max(Math.min(numBytes, dstBuf.capacity() - dstBufStart), 0);
/* 109 */       if (numBytesToRead == 0) {
/* 110 */         return -1;
/*     */       }
/* 112 */       int srcStart = (int)(this.sliceStartPos + srcOffset);
/* 113 */       this.byteBuffer.position(srcStart);
/* 114 */       dstBuf.position(dstBufStart);
/* 115 */       dstBuf.limit(dstBufStart + numBytesToRead);
/* 116 */       dstBuf.put(this.byteBuffer);
/* 117 */       this.byteBuffer.limit(this.sliceStartPos + this.sliceLength);
/* 118 */       this.byteBuffer.position(this.sliceStartPos);
/* 119 */       return numBytesToRead;
/* 120 */     } catch (BufferUnderflowException|IndexOutOfBoundsException|java.nio.ReadOnlyBufferException e) {
/* 121 */       throw new IOException("Read index out of bounds");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public byte readByte(long offset) throws IOException {
/* 127 */     int idx = (int)(this.sliceStartPos + offset);
/* 128 */     return this.byteBuffer.get(idx);
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedByte(long offset) throws IOException {
/* 133 */     int idx = (int)(this.sliceStartPos + offset);
/* 134 */     return this.byteBuffer.get(idx) & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedShort(long offset) throws IOException {
/* 139 */     int idx = (int)(this.sliceStartPos + offset);
/* 140 */     return this.byteBuffer.getShort(idx) & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public short readShort(long offset) throws IOException {
/* 145 */     return (short)readUnsignedShort(offset);
/*     */   }
/*     */ 
/*     */   
/*     */   public int readInt(long offset) throws IOException {
/* 150 */     int idx = (int)(this.sliceStartPos + offset);
/* 151 */     return this.byteBuffer.getInt(idx);
/*     */   }
/*     */ 
/*     */   
/*     */   public long readUnsignedInt(long offset) throws IOException {
/* 156 */     return readInt(offset) & 0xFFFFFFFFL;
/*     */   }
/*     */ 
/*     */   
/*     */   public long readLong(long offset) throws IOException {
/* 161 */     int idx = (int)(this.sliceStartPos + offset);
/* 162 */     return this.byteBuffer.getLong(idx);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String readString(long offset, int numBytes, boolean replaceSlashWithDot, boolean stripLSemicolon) throws IOException {
/* 168 */     int idx = (int)(this.sliceStartPos + offset);
/* 169 */     byte[] arr = new byte[numBytes];
/* 170 */     if (read(offset, arr, 0, numBytes) < numBytes) {
/* 171 */       throw new IOException("Premature EOF while reading string");
/*     */     }
/* 173 */     return StringUtils.readString(arr, idx, numBytes, replaceSlashWithDot, stripLSemicolon);
/*     */   }
/*     */ 
/*     */   
/*     */   public String readString(long offset, int numBytes) throws IOException {
/* 178 */     return readString(offset, numBytes, false, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fileslice\reader\RandomAccessByteBufferReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */