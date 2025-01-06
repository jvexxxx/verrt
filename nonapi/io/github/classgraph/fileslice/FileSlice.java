/*     */ package nonapi.io.github.classgraph.fileslice;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.RandomAccessByteBufferReader;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.RandomAccessFileChannelReader;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader;
/*     */ import nonapi.io.github.classgraph.utils.FileUtils;
/*     */ import nonapi.io.github.classgraph.utils.LogNode;
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
/*     */ public class FileSlice
/*     */   extends Slice
/*     */ {
/*     */   public final File file;
/*     */   public RandomAccessFile raf;
/*     */   private final long fileLength;
/*     */   private FileChannel fileChannel;
/*     */   private ByteBuffer backingByteBuffer;
/*     */   private final boolean isTopLevelFileSlice;
/*  71 */   private final AtomicBoolean isClosed = new AtomicBoolean();
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
/*     */   private FileSlice(FileSlice parentSlice, long offset, long length, boolean isDeflatedZipEntry, long inflatedLengthHint, NestedJarHandler nestedJarHandler) {
/*  93 */     super(parentSlice, offset, length, isDeflatedZipEntry, inflatedLengthHint, nestedJarHandler);
/*  94 */     this.file = parentSlice.file;
/*  95 */     this.raf = parentSlice.raf;
/*  96 */     this.fileChannel = parentSlice.fileChannel;
/*  97 */     this.fileLength = parentSlice.fileLength;
/*  98 */     this.isTopLevelFileSlice = false;
/*     */     
/* 100 */     if (parentSlice.backingByteBuffer != null) {
/*     */       
/* 102 */       this.backingByteBuffer = parentSlice.backingByteBuffer.duplicate();
/* 103 */       this.backingByteBuffer.position((int)this.sliceStartPos);
/* 104 */       this.backingByteBuffer.limit((int)(this.sliceStartPos + this.sliceLength));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FileSlice(File file, boolean isDeflatedZipEntry, long inflatedLengthHint, NestedJarHandler nestedJarHandler, LogNode log) throws IOException {
/* 130 */     super(file.length(), isDeflatedZipEntry, inflatedLengthHint, nestedJarHandler);
/*     */     
/* 132 */     FileUtils.checkCanReadAndIsFile(file);
/* 133 */     this.file = file;
/* 134 */     this.raf = new RandomAccessFile(file, "r");
/* 135 */     this.fileChannel = this.raf.getChannel();
/* 136 */     this.fileLength = file.length();
/* 137 */     this.isTopLevelFileSlice = true;
/*     */     
/* 139 */     if (nestedJarHandler.scanSpec.enableMemoryMapping) {
/*     */       
/*     */       try {
/*     */         
/* 143 */         this.backingByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, this.fileLength);
/* 144 */       } catch (IOException|OutOfMemoryError e) {
/*     */         
/* 146 */         System.gc();
/* 147 */         nestedJarHandler.runFinalizationMethod();
/*     */         try {
/* 149 */           this.backingByteBuffer = this.fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, this.fileLength);
/* 150 */         } catch (IOException|OutOfMemoryError e2) {
/* 151 */           if (log != null) {
/* 152 */             log.log("File " + file + " cannot be memory mapped: " + e2 + " (using RandomAccessFile API instead)");
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 161 */     nestedJarHandler.markSliceAsOpen(this);
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
/*     */   public FileSlice(File file, NestedJarHandler nestedJarHandler, LogNode log) throws IOException {
/* 178 */     this(file, false, 0L, nestedJarHandler, log);
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
/*     */ 
/*     */   
/*     */   public Slice slice(long offset, long length, boolean isDeflatedZipEntry, long inflatedLengthHint) {
/* 198 */     if (this.isDeflatedZipEntry) {
/* 199 */       throw new IllegalArgumentException("Cannot slice a deflated zip entry");
/*     */     }
/* 201 */     return new FileSlice(this, offset, length, isDeflatedZipEntry, inflatedLengthHint, this.nestedJarHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomAccessReader randomAccessReader() {
/* 211 */     if (this.backingByteBuffer == null)
/*     */     {
/* 213 */       return (RandomAccessReader)new RandomAccessFileChannelReader(this.fileChannel, this.sliceStartPos, this.sliceLength);
/*     */     }
/*     */     
/* 216 */     return (RandomAccessReader)new RandomAccessByteBufferReader(this.backingByteBuffer, this.sliceStartPos, this.sliceLength);
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
/*     */   public byte[] load() throws IOException {
/* 229 */     if (this.isDeflatedZipEntry) {
/*     */       
/* 231 */       if (this.inflatedLengthHint > 2147483639L) {
/* 232 */         throw new IOException("Uncompressed size is larger than 2GB");
/*     */       }
/* 234 */       InputStream inputStream = open(); 
/* 235 */       try { byte[] arrayOfByte = NestedJarHandler.readAllBytesAsArray(inputStream, this.inflatedLengthHint);
/* 236 */         if (inputStream != null) inputStream.close();  return arrayOfByte; } catch (Throwable throwable) { if (inputStream != null)
/*     */           try { inputStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }
/*     */     
/* 239 */     }  if (this.sliceLength > 2147483639L) {
/* 240 */       throw new IOException("File is larger than 2GB");
/*     */     }
/* 242 */     RandomAccessReader reader = randomAccessReader();
/* 243 */     byte[] content = new byte[(int)this.sliceLength];
/* 244 */     if (reader.read(0L, content, 0, content.length) < content.length)
/*     */     {
/* 246 */       throw new IOException("File is truncated");
/*     */     }
/* 248 */     return content;
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
/*     */   public ByteBuffer read() throws IOException {
/* 262 */     if (this.isDeflatedZipEntry) {
/*     */ 
/*     */       
/* 265 */       if (this.inflatedLengthHint > 2147483639L) {
/* 266 */         throw new IOException("Uncompressed size is larger than 2GB");
/*     */       }
/* 268 */       return ByteBuffer.wrap(load());
/* 269 */     }  if (this.backingByteBuffer == null) {
/*     */       
/* 271 */       if (this.sliceLength > 2147483639L) {
/* 272 */         throw new IOException("File is larger than 2GB");
/*     */       }
/* 274 */       return ByteBuffer.wrap(load());
/*     */     } 
/*     */     
/* 277 */     return this.backingByteBuffer.duplicate();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 283 */     return super.equals(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 288 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 294 */     if (!this.isClosed.getAndSet(true)) {
/* 295 */       if (this.isTopLevelFileSlice && this.backingByteBuffer != null)
/*     */       {
/*     */         
/* 298 */         this.nestedJarHandler.closeDirectByteBuffer(this.backingByteBuffer);
/*     */       }
/* 300 */       this.backingByteBuffer = null;
/* 301 */       this.fileChannel = null;
/*     */       
/*     */       try {
/* 304 */         this.raf.close();
/* 305 */       } catch (IOException iOException) {}
/*     */ 
/*     */       
/* 308 */       this.raf = null;
/* 309 */       this.nestedJarHandler.markSliceAsClosed(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fileslice\FileSlice.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */