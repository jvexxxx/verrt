/*     */ package nonapi.io.github.classgraph.fileslice;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.file.OpenOption;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.StandardOpenOption;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.RandomAccessFileChannelReader;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader;
/*     */ import nonapi.io.github.classgraph.utils.FileUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PathSlice
/*     */   extends Slice
/*     */ {
/*     */   public final Path path;
/*     */   private final long fileLength;
/*     */   private FileChannel fileChannel;
/*     */   private final boolean isTopLevelFileSlice;
/*  61 */   private final AtomicBoolean isClosed = new AtomicBoolean();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PathSlice(PathSlice parentSlice, long offset, long length, boolean isDeflatedZipEntry, long inflatedLengthHint, NestedJarHandler nestedJarHandler) {
/*  84 */     super(parentSlice, offset, length, isDeflatedZipEntry, inflatedLengthHint, nestedJarHandler);
/*     */     
/*  86 */     this.path = parentSlice.path;
/*  87 */     this.fileChannel = parentSlice.fileChannel;
/*  88 */     this.fileLength = parentSlice.fileLength;
/*  89 */     this.isTopLevelFileSlice = false;
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
/*     */   
/*     */   public PathSlice(Path path, boolean isDeflatedZipEntry, long inflatedLengthHint, NestedJarHandler nestedJarHandler) throws IOException {
/* 115 */     super(0L, isDeflatedZipEntry, inflatedLengthHint, nestedJarHandler);
/*     */ 
/*     */     
/* 118 */     FileUtils.checkCanReadAndIsFile(path);
/*     */     
/* 120 */     this.path = path;
/* 121 */     this.fileChannel = FileChannel.open(path, new OpenOption[] { StandardOpenOption.READ });
/* 122 */     this.fileLength = this.fileChannel.size();
/* 123 */     this.isTopLevelFileSlice = true;
/*     */ 
/*     */ 
/*     */     
/* 127 */     this.sliceLength = this.fileLength;
/*     */ 
/*     */     
/* 130 */     nestedJarHandler.markSliceAsOpen(this);
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
/*     */   public PathSlice(Path path, NestedJarHandler nestedJarHandler) throws IOException {
/* 144 */     this(path, false, 0L, nestedJarHandler);
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
/*     */   public Slice slice(long offset, long length, boolean isDeflatedZipEntry, long inflatedLengthHint) {
/* 165 */     if (this.isDeflatedZipEntry) {
/* 166 */       throw new IllegalArgumentException("Cannot slice a deflated zip entry");
/*     */     }
/* 168 */     return new PathSlice(this, offset, length, isDeflatedZipEntry, inflatedLengthHint, this.nestedJarHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomAccessReader randomAccessReader() {
/* 179 */     return (RandomAccessReader)new RandomAccessFileChannelReader(this.fileChannel, this.sliceStartPos, this.sliceLength);
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
/*     */   public byte[] load() throws IOException {
/* 191 */     if (this.isDeflatedZipEntry) {
/*     */       
/* 193 */       if (this.inflatedLengthHint > 2147483639L) {
/* 194 */         throw new IOException("Uncompressed size is larger than 2GB");
/*     */       }
/* 196 */       InputStream inputStream = open(); 
/* 197 */       try { byte[] arrayOfByte = NestedJarHandler.readAllBytesAsArray(inputStream, this.inflatedLengthHint);
/* 198 */         if (inputStream != null) inputStream.close();  return arrayOfByte; } catch (Throwable throwable) { if (inputStream != null)
/*     */           try { inputStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }
/*     */     
/* 201 */     }  if (this.sliceLength > 2147483639L) {
/* 202 */       throw new IOException("File is larger than 2GB");
/*     */     }
/* 204 */     RandomAccessReader reader = randomAccessReader();
/* 205 */     byte[] content = new byte[(int)this.sliceLength];
/* 206 */     if (reader.read(0L, content, 0, content.length) < content.length)
/*     */     {
/* 208 */       throw new IOException("File is truncated");
/*     */     }
/* 210 */     return content;
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
/*     */   public ByteBuffer read() throws IOException {
/* 225 */     if (this.isDeflatedZipEntry) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 230 */       if (this.inflatedLengthHint > 2147483639L) {
/* 231 */         throw new IOException("Uncompressed size is larger than 2GB");
/*     */       }
/* 233 */       return ByteBuffer.wrap(load());
/*     */     } 
/*     */     
/* 236 */     if (this.sliceLength > 2147483639L) {
/* 237 */       throw new IOException("File is larger than 2GB");
/*     */     }
/* 239 */     return ByteBuffer.wrap(load());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 244 */     return super.equals(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 249 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 255 */     if (!this.isClosed.getAndSet(true)) {
/* 256 */       if (this.isTopLevelFileSlice && this.fileChannel != null) {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 261 */           this.fileChannel.close();
/* 262 */         } catch (IOException iOException) {}
/*     */ 
/*     */         
/* 265 */         this.fileChannel = null;
/*     */       } 
/* 267 */       this.fileChannel = null;
/* 268 */       this.nestedJarHandler.markSliceAsClosed(this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fileslice\PathSlice.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */