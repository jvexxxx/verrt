/*     */ package nonapi.io.github.classgraph.fileslice;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.RandomAccessArrayReader;
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
/*     */ public class ArraySlice
/*     */   extends Slice
/*     */ {
/*     */   public byte[] arr;
/*     */   
/*     */   private ArraySlice(ArraySlice parentSlice, long offset, long length, boolean isDeflatedZipEntry, long inflatedLengthHint, NestedJarHandler nestedJarHandler) {
/*  64 */     super(parentSlice, offset, length, isDeflatedZipEntry, inflatedLengthHint, nestedJarHandler);
/*  65 */     this.arr = parentSlice.arr;
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
/*     */   public ArraySlice(byte[] arr, boolean isDeflatedZipEntry, long inflatedLengthHint, NestedJarHandler nestedJarHandler) {
/*  83 */     super(arr.length, isDeflatedZipEntry, inflatedLengthHint, nestedJarHandler);
/*  84 */     this.arr = arr;
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
/* 104 */     if (this.isDeflatedZipEntry) {
/* 105 */       throw new IllegalArgumentException("Cannot slice a deflated zip entry");
/*     */     }
/* 107 */     return new ArraySlice(this, offset, length, isDeflatedZipEntry, inflatedLengthHint, this.nestedJarHandler);
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
/* 119 */     if (this.isDeflatedZipEntry)
/*     */     
/* 121 */     { InputStream inputStream = open(); 
/* 122 */       try { byte[] arrayOfByte = NestedJarHandler.readAllBytesAsArray(inputStream, this.inflatedLengthHint);
/* 123 */         if (inputStream != null) inputStream.close();  return arrayOfByte; } catch (Throwable throwable) { if (inputStream != null)
/* 124 */           try { inputStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  }  if (this.sliceStartPos == 0L && this.sliceLength == this.arr.length)
/*     */     {
/* 126 */       return this.arr;
/*     */     }
/*     */     
/* 129 */     return Arrays.copyOfRange(this.arr, (int)this.sliceStartPos, (int)(this.sliceStartPos + this.sliceLength));
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
/* 140 */     return (RandomAccessReader)new RandomAccessArrayReader(this.arr, (int)this.sliceStartPos, (int)this.sliceLength);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 145 */     return super.equals(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 150 */     return super.hashCode();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fileslice\ArraySlice.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */