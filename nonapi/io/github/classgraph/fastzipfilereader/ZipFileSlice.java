/*     */ package nonapi.io.github.classgraph.fastzipfilereader;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Path;
/*     */ import java.util.Objects;
/*     */ import nonapi.io.github.classgraph.fileslice.Slice;
/*     */ import nonapi.io.github.classgraph.scanspec.AcceptReject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ZipFileSlice
/*     */ {
/*     */   private final ZipFileSlice parentZipFileSlice;
/*     */   protected final PhysicalZipFile physicalZipFile;
/*     */   private final String pathWithinParentZipFileSlice;
/*     */   public Slice slice;
/*     */   
/*     */   ZipFileSlice(PhysicalZipFile physicalZipFile) {
/*  57 */     this.parentZipFileSlice = null;
/*  58 */     this.physicalZipFile = physicalZipFile;
/*  59 */     this.slice = physicalZipFile.slice;
/*  60 */     this.pathWithinParentZipFileSlice = physicalZipFile.getPathStr();
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
/*     */   ZipFileSlice(PhysicalZipFile physicalZipFile, FastZipEntry zipEntry) {
/*  73 */     this.parentZipFileSlice = zipEntry.parentLogicalZipFile;
/*  74 */     this.physicalZipFile = physicalZipFile;
/*  75 */     this.slice = physicalZipFile.slice;
/*  76 */     this.pathWithinParentZipFileSlice = zipEntry.entryName;
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
/*     */   ZipFileSlice(FastZipEntry zipEntry) throws IOException, InterruptedException {
/*  90 */     this.parentZipFileSlice = zipEntry.parentLogicalZipFile;
/*  91 */     this.physicalZipFile = zipEntry.parentLogicalZipFile.physicalZipFile;
/*  92 */     this.slice = zipEntry.getSlice();
/*  93 */     this.pathWithinParentZipFileSlice = zipEntry.entryName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ZipFileSlice(ZipFileSlice other) {
/* 103 */     this.parentZipFileSlice = other.parentZipFileSlice;
/* 104 */     this.physicalZipFile = other.physicalZipFile;
/* 105 */     this.slice = other.slice;
/* 106 */     this.pathWithinParentZipFileSlice = other.pathWithinParentZipFileSlice;
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
/*     */   public boolean isAcceptedAndNotRejected(AcceptReject.AcceptRejectLeafname jarAcceptReject) {
/* 119 */     return (jarAcceptReject.isAcceptedAndNotRejected(this.pathWithinParentZipFileSlice) && (this.parentZipFileSlice == null || this.parentZipFileSlice
/* 120 */       .isAcceptedAndNotRejected(jarAcceptReject)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ZipFileSlice getParentZipFileSlice() {
/* 130 */     return this.parentZipFileSlice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPathWithinParentZipFileSlice() {
/* 140 */     return this.pathWithinParentZipFileSlice;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void appendPath(StringBuilder buf) {
/* 150 */     if (this.parentZipFileSlice != null) {
/* 151 */       this.parentZipFileSlice.appendPath(buf);
/* 152 */       if (buf.length() > 0) {
/* 153 */         buf.append("!/");
/*     */       }
/*     */     } 
/* 156 */     buf.append(this.pathWithinParentZipFileSlice);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPath() {
/* 165 */     StringBuilder buf = new StringBuilder();
/* 166 */     appendPath(buf);
/* 167 */     return buf.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File getPhysicalFile() {
/* 177 */     Path path = this.physicalZipFile.getPath();
/* 178 */     if (path != null) {
/*     */       try {
/* 180 */         return path.toFile();
/* 181 */       } catch (UnsupportedOperationException e) {
/*     */         
/* 183 */         return null;
/*     */       } 
/*     */     }
/* 186 */     return this.physicalZipFile.getFile();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 195 */     if (o == this)
/* 196 */       return true; 
/* 197 */     if (!(o instanceof ZipFileSlice)) {
/* 198 */       return false;
/*     */     }
/* 200 */     ZipFileSlice other = (ZipFileSlice)o;
/* 201 */     return (Objects.equals(this.physicalZipFile, other.physicalZipFile) && Objects.equals(this.slice, other.slice) && 
/* 202 */       Objects.equals(this.pathWithinParentZipFileSlice, other.pathWithinParentZipFileSlice));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 211 */     return Objects.hash(new Object[] { this.physicalZipFile, this.slice, this.pathWithinParentZipFileSlice });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 219 */     String path = getPath();
/* 220 */     String fileStr = (this.physicalZipFile.getPath() == null) ? null : this.physicalZipFile.getPath().toString();
/* 221 */     if (fileStr == null) {
/* 222 */       fileStr = (this.physicalZipFile.getFile() == null) ? null : this.physicalZipFile.getFile().toString();
/*     */     }
/* 224 */     return "[" + ((fileStr != null && !fileStr.equals(path)) ? (path + " -> " + fileStr) : path) + " ; byte range: " + this.slice.sliceStartPos + ".." + (this.slice.sliceStartPos + this.slice.sliceLength) + " / " + this.physicalZipFile
/*     */       
/* 226 */       .length() + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fastzipfilereader\ZipFileSlice.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */