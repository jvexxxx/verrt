/*     */ package nonapi.io.github.classgraph.fastzipfilereader;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Objects;
/*     */ import nonapi.io.github.classgraph.fileslice.ArraySlice;
/*     */ import nonapi.io.github.classgraph.fileslice.FileSlice;
/*     */ import nonapi.io.github.classgraph.fileslice.PathSlice;
/*     */ import nonapi.io.github.classgraph.fileslice.Slice;
/*     */ import nonapi.io.github.classgraph.utils.FastPathResolver;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class PhysicalZipFile
/*     */ {
/*     */   private Path path;
/*     */   private File file;
/*     */   private final String pathStr;
/*     */   Slice slice;
/*     */   NestedJarHandler nestedJarHandler;
/*     */   private int hashCode;
/*     */   
/*     */   PhysicalZipFile(File file, NestedJarHandler nestedJarHandler, LogNode log) throws IOException {
/*  80 */     this.nestedJarHandler = nestedJarHandler;
/*     */ 
/*     */     
/*  83 */     FileUtils.checkCanReadAndIsFile(file);
/*     */     
/*  85 */     this.file = file;
/*  86 */     this.pathStr = FastPathResolver.resolve(FileUtils.currDirPath(), file.getPath());
/*  87 */     this.slice = (Slice)new FileSlice(file, nestedJarHandler, log);
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
/*     */   PhysicalZipFile(Path path, NestedJarHandler nestedJarHandler, LogNode log) throws IOException {
/* 104 */     this.nestedJarHandler = nestedJarHandler;
/*     */ 
/*     */     
/* 107 */     FileUtils.checkCanReadAndIsFile(path);
/*     */     
/* 109 */     this.path = path;
/* 110 */     this.pathStr = FastPathResolver.resolve(FileUtils.currDirPath(), path.toString());
/* 111 */     this.slice = (Slice)new PathSlice(path, nestedJarHandler);
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
/*     */   PhysicalZipFile(byte[] arr, File outermostFile, String pathStr, NestedJarHandler nestedJarHandler) throws IOException {
/* 130 */     this.nestedJarHandler = nestedJarHandler;
/* 131 */     this.file = outermostFile;
/* 132 */     this.pathStr = pathStr;
/* 133 */     this.slice = (Slice)new ArraySlice(arr, false, 0L, nestedJarHandler);
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
/*     */   PhysicalZipFile(InputStream inputStream, long inputStreamLengthHint, String pathStr, NestedJarHandler nestedJarHandler, LogNode log) throws IOException {
/* 157 */     this.nestedJarHandler = nestedJarHandler;
/* 158 */     this.pathStr = pathStr;
/*     */ 
/*     */     
/* 161 */     this.slice = nestedJarHandler.readAllBytesWithSpilloverToDisk(inputStream, pathStr, inputStreamLengthHint, log);
/*     */     
/* 163 */     this.file = (this.slice instanceof FileSlice) ? ((FileSlice)this.slice).file : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Path getPath() {
/* 173 */     return this.path;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getFile() {
/* 183 */     return this.file;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPathStr() {
/* 194 */     return this.pathStr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long length() {
/* 204 */     return this.slice.sliceLength;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 212 */     if (this.hashCode == 0) {
/* 213 */       this.hashCode = (this.file == null) ? 0 : this.file.hashCode();
/* 214 */       if (this.hashCode == 0) {
/* 215 */         this.hashCode = 1;
/*     */       }
/*     */     } 
/* 218 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 226 */     if (o == this)
/* 227 */       return true; 
/* 228 */     if (!(o instanceof PhysicalZipFile)) {
/* 229 */       return false;
/*     */     }
/* 231 */     PhysicalZipFile other = (PhysicalZipFile)o;
/* 232 */     return Objects.equals(this.file, other.file);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 240 */     return this.pathStr;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fastzipfilereader\PhysicalZipFile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */