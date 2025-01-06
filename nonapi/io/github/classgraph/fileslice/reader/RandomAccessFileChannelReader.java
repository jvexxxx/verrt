/*     */ package nonapi.io.github.classgraph.fileslice.reader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.BufferUnderflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
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
/*     */ public class RandomAccessFileChannelReader
/*     */   implements RandomAccessReader
/*     */ {
/*     */   private final FileChannel fileChannel;
/*     */   private final long sliceStartPos;
/*     */   private final long sliceLength;
/*     */   private ByteBuffer reusableByteBuffer;
/*  59 */   private final byte[] scratchArr = new byte[8];
/*     */ 
/*     */   
/*  62 */   private final ByteBuffer scratchByteBuf = ByteBuffer.wrap(this.scratchArr);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] utf8Bytes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomAccessFileChannelReader(FileChannel fileChannel, long sliceStartPos, long sliceLength) {
/*  79 */     this.fileChannel = fileChannel;
/*  80 */     this.sliceStartPos = sliceStartPos;
/*  81 */     this.sliceLength = sliceLength;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(long srcOffset, ByteBuffer dstBuf, int dstBufStart, int numBytes) throws IOException {
/*  87 */     if (numBytes == 0) {
/*  88 */       return 0;
/*     */     }
/*     */     try {
/*  91 */       if (srcOffset < 0L || numBytes < 0 || numBytes > this.sliceLength - srcOffset) {
/*  92 */         throw new IOException("Read index out of bounds");
/*     */       }
/*  94 */       long srcStart = this.sliceStartPos + srcOffset;
/*  95 */       dstBuf.position(dstBufStart);
/*  96 */       dstBuf.limit(dstBufStart + numBytes);
/*  97 */       int numBytesRead = this.fileChannel.read(dstBuf, srcStart);
/*  98 */       return (numBytesRead == 0) ? -1 : numBytesRead;
/*     */     }
/* 100 */     catch (BufferUnderflowException|IndexOutOfBoundsException e) {
/* 101 */       throw new IOException("Read index out of bounds");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(long srcOffset, byte[] dstArr, int dstArrStart, int numBytes) throws IOException {
/* 108 */     if (numBytes == 0) {
/* 109 */       return 0;
/*     */     }
/*     */     try {
/* 112 */       if (srcOffset < 0L || numBytes < 0 || numBytes > this.sliceLength - srcOffset) {
/* 113 */         throw new IOException("Read index out of bounds");
/*     */       }
/* 115 */       if (this.reusableByteBuffer == null || this.reusableByteBuffer.array() != dstArr)
/*     */       {
/*     */         
/* 118 */         this.reusableByteBuffer = ByteBuffer.wrap(dstArr);
/*     */       }
/*     */       
/* 121 */       return read(srcOffset, this.reusableByteBuffer, dstArrStart, numBytes);
/*     */     }
/* 123 */     catch (BufferUnderflowException|IndexOutOfBoundsException e) {
/* 124 */       throw new IOException("Read index out of bounds");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public byte readByte(long offset) throws IOException {
/* 130 */     if (read(offset, this.scratchByteBuf, 0, 1) < 1) {
/* 131 */       throw new IOException("Premature EOF");
/*     */     }
/* 133 */     return this.scratchArr[0];
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedByte(long offset) throws IOException {
/* 138 */     if (read(offset, this.scratchByteBuf, 0, 1) < 1) {
/* 139 */       throw new IOException("Premature EOF");
/*     */     }
/* 141 */     return this.scratchArr[0] & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public short readShort(long offset) throws IOException {
/* 146 */     return (short)readUnsignedShort(offset);
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedShort(long offset) throws IOException {
/* 151 */     if (read(offset, this.scratchByteBuf, 0, 2) < 2) {
/* 152 */       throw new IOException("Premature EOF");
/*     */     }
/* 154 */     return (this.scratchArr[1] & 0xFF) << 8 | this.scratchArr[0] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int readInt(long offset) throws IOException {
/* 160 */     if (read(offset, this.scratchByteBuf, 0, 4) < 4) {
/* 161 */       throw new IOException("Premature EOF");
/*     */     }
/* 163 */     return (this.scratchArr[3] & 0xFF) << 24 | (this.scratchArr[2] & 0xFF) << 16 | (this.scratchArr[1] & 0xFF) << 8 | this.scratchArr[0] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long readUnsignedInt(long offset) throws IOException {
/* 171 */     return readInt(offset) & 0xFFFFFFFFL;
/*     */   }
/*     */ 
/*     */   
/*     */   public long readLong(long offset) throws IOException {
/* 176 */     if (read(offset, this.scratchByteBuf, 0, 8) < 8) {
/* 177 */       throw new IOException("Premature EOF");
/*     */     }
/* 179 */     return (this.scratchArr[7] & 0xFFL) << 56L | (this.scratchArr[6] & 0xFFL) << 48L | (this.scratchArr[5] & 0xFFL) << 40L | (this.scratchArr[4] & 0xFFL) << 32L | (this.scratchArr[3] & 0xFFL) << 24L | (this.scratchArr[2] & 0xFFL) << 16L | (this.scratchArr[1] & 0xFFL) << 8L | this.scratchArr[0] & 0xFFL;
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
/*     */   
/*     */   public String readString(long offset, int numBytes, boolean replaceSlashWithDot, boolean stripLSemicolon) throws IOException {
/* 193 */     if (this.utf8Bytes == null || this.utf8Bytes.length < numBytes) {
/* 194 */       this.utf8Bytes = new byte[numBytes];
/*     */     }
/* 196 */     if (read(offset, this.utf8Bytes, 0, numBytes) < numBytes) {
/* 197 */       throw new IOException("Premature EOF");
/*     */     }
/* 199 */     return StringUtils.readString(this.utf8Bytes, 0, numBytes, replaceSlashWithDot, stripLSemicolon);
/*     */   }
/*     */ 
/*     */   
/*     */   public String readString(long offset, int numBytes) throws IOException {
/* 204 */     return readString(offset, numBytes, false, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fileslice\reader\RandomAccessFileChannelReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */