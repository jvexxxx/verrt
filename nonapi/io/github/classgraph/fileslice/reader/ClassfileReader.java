/*     */ package nonapi.io.github.classgraph.fileslice.reader;
/*     */ 
/*     */ import io.github.classgraph.Resource;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.BufferUnderflowException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Arrays;
/*     */ import nonapi.io.github.classgraph.fileslice.ArraySlice;
/*     */ import nonapi.io.github.classgraph.fileslice.Slice;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClassfileReader
/*     */   implements RandomAccessReader, SequentialReader, Closeable
/*     */ {
/*     */   private Resource resourceToClose;
/*     */   private InputStream inflaterInputStream;
/*     */   private RandomAccessReader randomAccessReader;
/*     */   private byte[] arr;
/*     */   private int arrUsed;
/*     */   private int currIdx;
/*  78 */   private int classfileLengthHint = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int INITIAL_BUF_SIZE = 16384;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int BUF_CHUNK_SIZE = 8184;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassfileReader(Slice slice, Resource resourceToClose) throws IOException {
/* 106 */     this.classfileLengthHint = (int)slice.sliceLength;
/* 107 */     this.resourceToClose = resourceToClose;
/* 108 */     if (slice.isDeflatedZipEntry) {
/*     */       
/* 110 */       this.inflaterInputStream = slice.open();
/* 111 */       this.arr = new byte[16384];
/* 112 */       this.classfileLengthHint = (int)Math.min(slice.inflatedLengthHint, 2147483639L);
/*     */     }
/* 114 */     else if (slice instanceof ArraySlice) {
/*     */ 
/*     */       
/* 117 */       ArraySlice arraySlice = (ArraySlice)slice;
/* 118 */       if (arraySlice.sliceStartPos == 0L && arraySlice.sliceLength == arraySlice.arr.length) {
/*     */         
/* 120 */         this.arr = arraySlice.arr;
/*     */       }
/*     */       else {
/*     */         
/* 124 */         this.arr = Arrays.copyOfRange(arraySlice.arr, (int)arraySlice.sliceStartPos, (int)(arraySlice.sliceStartPos + arraySlice.sliceLength));
/*     */       } 
/*     */       
/* 127 */       this.arrUsed = this.arr.length;
/* 128 */       this.classfileLengthHint = this.arr.length;
/*     */     } else {
/*     */       
/* 131 */       this.randomAccessReader = slice.randomAccessReader();
/* 132 */       this.arr = new byte[16384];
/* 133 */       this.classfileLengthHint = (int)Math.min(slice.sliceLength, 2147483639L);
/*     */     } 
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
/*     */   
/*     */   public ClassfileReader(InputStream inputStream, Resource resourceToClose) throws IOException {
/* 149 */     this.inflaterInputStream = inputStream;
/* 150 */     this.arr = new byte[16384];
/* 151 */     this.resourceToClose = resourceToClose;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int currPos() {
/* 160 */     return this.currIdx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] buf() {
/* 169 */     return this.arr;
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
/*     */   
/*     */   private void readTo(int targetArrUsed) throws IOException {
/* 184 */     int maxArrLen = (this.classfileLengthHint == -1) ? 2147483639 : this.classfileLengthHint;
/* 185 */     if (this.inflaterInputStream == null && this.randomAccessReader == null)
/*     */     {
/*     */       
/* 188 */       throw new IOException("Tried to read past end of fixed array buffer");
/*     */     }
/* 190 */     if (targetArrUsed > 2147483639 || targetArrUsed < 0 || this.arrUsed == maxArrLen) {
/* 191 */       throw new IOException("Hit 2GB limit while trying to grow buffer array");
/*     */     }
/*     */ 
/*     */     
/* 195 */     int maxNewArrUsed = (int)Math.min(Math.max(targetArrUsed, (this.arrUsed + 8184)), maxArrLen);
/*     */ 
/*     */ 
/*     */     
/* 199 */     long newArrLength = this.arr.length;
/* 200 */     while (newArrLength < maxNewArrUsed) {
/* 201 */       newArrLength = Math.min(maxNewArrUsed, newArrLength * 2L);
/*     */     }
/* 203 */     if (newArrLength > 2147483639L) {
/* 204 */       throw new IOException("Hit 2GB limit while trying to grow buffer array");
/*     */     }
/* 206 */     this.arr = Arrays.copyOf(this.arr, (int)Math.min(newArrLength, maxArrLen));
/*     */ 
/*     */     
/* 209 */     int maxBytesToRead = this.arr.length - this.arrUsed;
/*     */ 
/*     */     
/* 212 */     if (this.inflaterInputStream != null) {
/*     */       
/* 214 */       int numRead = this.inflaterInputStream.read(this.arr, this.arrUsed, maxBytesToRead);
/* 215 */       if (numRead > 0) {
/* 216 */         this.arrUsed += numRead;
/*     */       }
/*     */     } else {
/*     */       
/* 220 */       int bytesToRead = Math.min(maxBytesToRead, maxArrLen - this.arrUsed);
/*     */       
/* 222 */       int numBytesRead = this.randomAccessReader.read(this.arrUsed, this.arr, this.arrUsed, bytesToRead);
/*     */       
/* 224 */       if (numBytesRead > 0) {
/* 225 */         this.arrUsed += numBytesRead;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 230 */     if (this.arrUsed < targetArrUsed) {
/* 231 */       throw new IOException("Buffer underflow");
/*     */     }
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
/*     */   public void bufferTo(int numBytes) throws IOException {
/* 244 */     if (numBytes > this.arrUsed) {
/* 245 */       readTo(numBytes);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(long srcOffset, byte[] dstArr, int dstArrStart, int numBytes) throws IOException {
/* 252 */     if (numBytes == 0) {
/* 253 */       return 0;
/*     */     }
/* 255 */     int idx = (int)srcOffset;
/* 256 */     if (idx + numBytes > this.arrUsed) {
/* 257 */       readTo(idx + numBytes);
/*     */     }
/* 259 */     int numBytesToRead = Math.max(Math.min(numBytes, dstArr.length - dstArrStart), 0);
/* 260 */     if (numBytesToRead == 0) {
/* 261 */       return -1;
/*     */     }
/*     */     try {
/* 264 */       System.arraycopy(this.arr, idx, dstArr, dstArrStart, numBytesToRead);
/* 265 */       return numBytesToRead;
/* 266 */     } catch (IndexOutOfBoundsException e) {
/* 267 */       throw new IOException("Read index out of bounds");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int read(long srcOffset, ByteBuffer dstBuf, int dstBufStart, int numBytes) throws IOException {
/* 274 */     if (numBytes == 0) {
/* 275 */       return 0;
/*     */     }
/* 277 */     int idx = (int)srcOffset;
/* 278 */     if (idx + numBytes > this.arrUsed) {
/* 279 */       readTo(idx + numBytes);
/*     */     }
/* 281 */     int numBytesToRead = Math.max(Math.min(numBytes, dstBuf.capacity() - dstBufStart), 0);
/* 282 */     if (numBytesToRead == 0) {
/* 283 */       return -1;
/*     */     }
/*     */     try {
/* 286 */       dstBuf.position(dstBufStart);
/* 287 */       dstBuf.limit(dstBufStart + numBytesToRead);
/* 288 */       dstBuf.put(this.arr, idx, numBytesToRead);
/* 289 */       return numBytesToRead;
/* 290 */     } catch (BufferUnderflowException|IndexOutOfBoundsException|java.nio.ReadOnlyBufferException e) {
/* 291 */       throw new IOException("Read index out of bounds");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public byte readByte(long offset) throws IOException {
/* 297 */     int idx = (int)offset;
/* 298 */     if (idx + 1 > this.arrUsed) {
/* 299 */       readTo(idx + 1);
/*     */     }
/* 301 */     return this.arr[idx];
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedByte(long offset) throws IOException {
/* 306 */     int idx = (int)offset;
/* 307 */     if (idx + 1 > this.arrUsed) {
/* 308 */       readTo(idx + 1);
/*     */     }
/* 310 */     return this.arr[idx] & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public short readShort(long offset) throws IOException {
/* 315 */     return (short)readUnsignedShort(offset);
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedShort(long offset) throws IOException {
/* 320 */     int idx = (int)offset;
/* 321 */     if (idx + 2 > this.arrUsed) {
/* 322 */       readTo(idx + 2);
/*     */     }
/* 324 */     return (this.arr[idx] & 0xFF) << 8 | this.arr[idx + 1] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int readInt(long offset) throws IOException {
/* 330 */     int idx = (int)offset;
/* 331 */     if (idx + 4 > this.arrUsed) {
/* 332 */       readTo(idx + 4);
/*     */     }
/* 334 */     return (this.arr[idx] & 0xFF) << 24 | (this.arr[idx + 1] & 0xFF) << 16 | (this.arr[idx + 2] & 0xFF) << 8 | this.arr[idx + 3] & 0xFF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long readUnsignedInt(long offset) throws IOException {
/* 342 */     return readInt(offset) & 0xFFFFFFFFL;
/*     */   }
/*     */ 
/*     */   
/*     */   public long readLong(long offset) throws IOException {
/* 347 */     int idx = (int)offset;
/* 348 */     if (idx + 8 > this.arrUsed) {
/* 349 */       readTo(idx + 8);
/*     */     }
/* 351 */     return (this.arr[idx] & 0xFFL) << 56L | (this.arr[idx + 1] & 0xFFL) << 48L | (this.arr[idx + 2] & 0xFFL) << 40L | (this.arr[idx + 3] & 0xFFL) << 32L | (this.arr[idx + 4] & 0xFFL) << 24L | (this.arr[idx + 5] & 0xFFL) << 16L | (this.arr[idx + 6] & 0xFFL) << 8L | this.arr[idx + 7] & 0xFFL;
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
/*     */   public byte readByte() throws IOException {
/* 363 */     byte val = readByte(this.currIdx);
/* 364 */     this.currIdx++;
/* 365 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedByte() throws IOException {
/* 370 */     int val = readUnsignedByte(this.currIdx);
/* 371 */     this.currIdx++;
/* 372 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public short readShort() throws IOException {
/* 377 */     short val = readShort(this.currIdx);
/* 378 */     this.currIdx += 2;
/* 379 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public int readUnsignedShort() throws IOException {
/* 384 */     int val = readUnsignedShort(this.currIdx);
/* 385 */     this.currIdx += 2;
/* 386 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public int readInt() throws IOException {
/* 391 */     int val = readInt(this.currIdx);
/* 392 */     this.currIdx += 4;
/* 393 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public long readUnsignedInt() throws IOException {
/* 398 */     long val = readUnsignedInt(this.currIdx);
/* 399 */     this.currIdx += 4;
/* 400 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public long readLong() throws IOException {
/* 405 */     long val = readLong(this.currIdx);
/* 406 */     this.currIdx += 8;
/* 407 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public void skip(int bytesToSkip) throws IOException {
/* 412 */     if (bytesToSkip < 0) {
/* 413 */       throw new IllegalArgumentException("Tried to skip a negative number of bytes");
/*     */     }
/* 415 */     int idx = this.currIdx;
/* 416 */     if (idx + bytesToSkip > this.arrUsed) {
/* 417 */       readTo(idx + bytesToSkip);
/*     */     }
/* 419 */     this.currIdx += bytesToSkip;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String readString(long offset, int numBytes, boolean replaceSlashWithDot, boolean stripLSemicolon) throws IOException {
/* 425 */     int idx = (int)offset;
/* 426 */     if (idx + numBytes > this.arrUsed) {
/* 427 */       readTo(idx + numBytes);
/*     */     }
/* 429 */     return StringUtils.readString(this.arr, idx, numBytes, replaceSlashWithDot, stripLSemicolon);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String readString(int numBytes, boolean replaceSlashWithDot, boolean stripLSemicolon) throws IOException {
/* 435 */     String val = StringUtils.readString(this.arr, this.currIdx, numBytes, replaceSlashWithDot, stripLSemicolon);
/* 436 */     this.currIdx += numBytes;
/* 437 */     return val;
/*     */   }
/*     */ 
/*     */   
/*     */   public String readString(long offset, int numBytes) throws IOException {
/* 442 */     return readString(offset, numBytes, false, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public String readString(int numBytes) throws IOException {
/* 447 */     return readString(numBytes, false, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/* 453 */       if (this.inflaterInputStream != null) {
/* 454 */         this.inflaterInputStream.close();
/* 455 */         this.inflaterInputStream = null;
/*     */       } 
/* 457 */       if (this.resourceToClose != null) {
/* 458 */         this.resourceToClose.close();
/* 459 */         this.resourceToClose = null;
/*     */       } 
/* 461 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fileslice\reader\ClassfileReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */