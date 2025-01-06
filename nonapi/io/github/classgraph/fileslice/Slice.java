/*     */ package nonapi.io.github.classgraph.fileslice;
/*     */ 
/*     */ import io.github.classgraph.Resource;
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader;
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
/*     */ public abstract class Slice
/*     */   implements Closeable
/*     */ {
/*     */   protected final NestedJarHandler nestedJarHandler;
/*     */   protected final Slice parentSlice;
/*     */   public final long sliceStartPos;
/*     */   public long sliceLength;
/*     */   public final boolean isDeflatedZipEntry;
/*     */   public final long inflatedLengthHint;
/*     */   private int hashCode;
/*     */   
/*     */   protected Slice(Slice parentSlice, long offset, long length, boolean isDeflatedZipEntry, long inflatedLengthHint, NestedJarHandler nestedJarHandler) {
/*  90 */     this.parentSlice = parentSlice;
/*  91 */     long parentSliceStartPos = (parentSlice == null) ? 0L : parentSlice.sliceStartPos;
/*  92 */     this.sliceStartPos = parentSliceStartPos + offset;
/*  93 */     this.sliceLength = length;
/*  94 */     this.isDeflatedZipEntry = isDeflatedZipEntry;
/*  95 */     this.inflatedLengthHint = inflatedLengthHint;
/*  96 */     this.nestedJarHandler = nestedJarHandler;
/*     */     
/*  98 */     if (this.sliceStartPos < 0L) {
/*  99 */       throw new IllegalArgumentException("Invalid startPos");
/*     */     }
/* 101 */     if (length < 0L) {
/* 102 */       throw new IllegalArgumentException("Invalid length");
/*     */     }
/* 104 */     if (parentSlice != null && (this.sliceStartPos < parentSliceStartPos || this.sliceStartPos + length > parentSliceStartPos + parentSlice.sliceLength))
/*     */     {
/* 106 */       throw new IllegalArgumentException("Child slice is not completely contained within parent slice");
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected Slice(long length, boolean isDeflatedZipEntry, long inflatedLengthHint, NestedJarHandler nestedJarHandler) {
/* 125 */     this(null, 0L, length, isDeflatedZipEntry, inflatedLengthHint, nestedJarHandler);
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
/*     */   
/*     */   public abstract Slice slice(long paramLong1, long paramLong2, boolean paramBoolean, long paramLong3);
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
/*     */   public InputStream open() throws IOException {
/* 154 */     return open(null);
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
/*     */   public InputStream open(final Resource resourceToClose) throws IOException {
/* 167 */     InputStream rawInputStream = new InputStream() {
/* 168 */         RandomAccessReader randomAccessReader = Slice.this.randomAccessReader();
/*     */         private long currOff;
/*     */         private long markOff;
/* 171 */         private final byte[] byteBuf = new byte[1];
/* 172 */         private final AtomicBoolean closed = new AtomicBoolean();
/*     */ 
/*     */         
/*     */         public int read() throws IOException {
/* 176 */           if (this.closed.get()) {
/* 177 */             throw new IOException("Already closed");
/*     */           }
/* 179 */           return read(this.byteBuf, 0, 1);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public int read(byte[] buf, int off, int len) throws IOException {
/* 186 */           if (this.closed.get())
/* 187 */             throw new IOException("Already closed"); 
/* 188 */           if (len == 0) {
/* 189 */             return 0;
/*     */           }
/* 191 */           int numBytesToRead = Math.min(len, available());
/* 192 */           if (numBytesToRead < 1) {
/* 193 */             return -1;
/*     */           }
/* 195 */           int numBytesRead = this.randomAccessReader.read(this.currOff, buf, off, numBytesToRead);
/* 196 */           if (numBytesRead > 0) {
/* 197 */             this.currOff += numBytesRead;
/*     */           }
/* 199 */           return numBytesRead;
/*     */         }
/*     */ 
/*     */         
/*     */         public long skip(long n) throws IOException {
/* 204 */           if (this.closed.get()) {
/* 205 */             throw new IOException("Already closed");
/*     */           }
/* 207 */           long newOff = Math.min(this.currOff + n, Slice.this.sliceLength);
/* 208 */           long skipped = newOff - this.currOff;
/* 209 */           this.currOff = newOff;
/* 210 */           return skipped;
/*     */         }
/*     */ 
/*     */         
/*     */         public int available() {
/* 215 */           return (int)Math.min(Math.max(Slice.this.sliceLength - this.currOff, 0L), 2147483639L);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public synchronized void mark(int readlimit) {
/* 221 */           this.markOff = this.currOff;
/*     */         }
/*     */ 
/*     */         
/*     */         public synchronized void reset() {
/* 226 */           this.currOff = this.markOff;
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean markSupported() {
/* 231 */           return true;
/*     */         }
/*     */ 
/*     */         
/*     */         public void close() {
/* 236 */           if (resourceToClose != null) {
/*     */             try {
/* 238 */               resourceToClose.close();
/* 239 */             } catch (Exception exception) {}
/*     */           }
/*     */ 
/*     */           
/* 243 */           this.closed.getAndSet(true);
/*     */         }
/*     */       };
/* 246 */     return this.isDeflatedZipEntry ? this.nestedJarHandler.openInflaterInputStream(rawInputStream) : rawInputStream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract RandomAccessReader randomAccessReader();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract byte[] load() throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String loadAsString() throws IOException {
/* 273 */     return new String(load(), StandardCharsets.UTF_8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBuffer read() throws IOException {
/* 284 */     return ByteBuffer.wrap(load());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {}
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 293 */     if (this.hashCode == 0) {
/* 294 */       this.hashCode = ((this.parentSlice == null) ? 1 : this.parentSlice.hashCode()) ^ (int)this.sliceStartPos * 7 ^ (int)this.sliceLength * 15;
/*     */       
/* 296 */       if (this.hashCode == 0) {
/* 297 */         this.hashCode = 1;
/*     */       }
/*     */     } 
/* 300 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 305 */     if (o == this)
/* 306 */       return true; 
/* 307 */     if (!(o instanceof Slice)) {
/* 308 */       return false;
/*     */     }
/* 310 */     Slice other = (Slice)o;
/* 311 */     return (this.parentSlice == other.parentSlice && this.sliceStartPos == other.sliceStartPos && this.sliceLength == other.sliceLength);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fileslice\Slice.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */