/*     */ package nonapi.io.github.classgraph.fastzipfilereader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import java.util.TimeZone;
/*     */ import nonapi.io.github.classgraph.fileslice.Slice;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader;
/*     */ import nonapi.io.github.classgraph.utils.VersionFinder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FastZipEntry
/*     */   implements Comparable<FastZipEntry>
/*     */ {
/*     */   final LogicalZipFile parentLogicalZipFile;
/*     */   private final long locHeaderPos;
/*     */   public final String entryName;
/*     */   final boolean isDeflated;
/*     */   public final long compressedSize;
/*     */   public final long uncompressedSize;
/*     */   private long lastModifiedTimeMillis;
/*     */   private final int lastModifiedTimeMSDOS;
/*     */   private final int lastModifiedDateMSDOS;
/*     */   public final int fileAttributes;
/*     */   private Slice slice;
/*     */   final int version;
/*     */   public final String entryNameUnversioned;
/*     */   
/*     */   FastZipEntry(LogicalZipFile parentLogicalZipFile, long locHeaderPos, String entryName, boolean isDeflated, long compressedSize, long uncompressedSize, long lastModifiedTimeMillis, int lastModifiedTimeMSDOS, int lastModifiedDateMSDOS, int fileAttributes, boolean enableMultiReleaseVersions) {
/* 115 */     this.parentLogicalZipFile = parentLogicalZipFile;
/* 116 */     this.locHeaderPos = locHeaderPos;
/* 117 */     this.entryName = entryName;
/* 118 */     this.isDeflated = isDeflated;
/* 119 */     this.compressedSize = compressedSize;
/* 120 */     this.uncompressedSize = (!isDeflated && uncompressedSize < 0L) ? compressedSize : uncompressedSize;
/* 121 */     this.lastModifiedTimeMillis = lastModifiedTimeMillis;
/* 122 */     this.lastModifiedTimeMSDOS = lastModifiedTimeMSDOS;
/* 123 */     this.lastModifiedDateMSDOS = lastModifiedDateMSDOS;
/* 124 */     this.fileAttributes = fileAttributes;
/*     */ 
/*     */     
/* 127 */     int entryVersion = 8;
/* 128 */     String entryNameWithoutVersionPrefix = entryName;
/* 129 */     if (entryName.startsWith("META-INF/versions/") && entryName
/* 130 */       .length() > "META-INF/versions/".length() + 1) {
/*     */       
/* 132 */       int nextSlashIdx = entryName.indexOf('/', "META-INF/versions/".length());
/* 133 */       if (nextSlashIdx > 0) {
/*     */         
/* 135 */         String versionStr = entryName.substring("META-INF/versions/".length(), nextSlashIdx);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 140 */         int versionInt = 0;
/* 141 */         if (versionStr.length() < 6 && !versionStr.isEmpty()) {
/* 142 */           for (int i = 0; i < versionStr.length(); i++) {
/* 143 */             char c = versionStr.charAt(i);
/* 144 */             if (c < '0' || c > '9') {
/* 145 */               versionInt = 0;
/*     */               break;
/*     */             } 
/* 148 */             if (versionInt == 0) {
/* 149 */               versionInt = c - 48;
/*     */             } else {
/* 151 */               versionInt = versionInt * 10 + c - 48;
/*     */             } 
/*     */           } 
/*     */         }
/* 155 */         if (versionInt != 0) {
/* 156 */           entryVersion = versionInt;
/*     */         }
/*     */         
/* 159 */         if (entryVersion < 9 || entryVersion > VersionFinder.JAVA_MAJOR_VERSION) {
/* 160 */           entryVersion = 8;
/*     */         }
/* 162 */         if (!enableMultiReleaseVersions && entryVersion > 8) {
/*     */           
/* 164 */           entryNameWithoutVersionPrefix = entryName.substring(nextSlashIdx + 1);
/*     */ 
/*     */ 
/*     */           
/* 168 */           if (entryNameWithoutVersionPrefix.startsWith("META-INF/")) {
/* 169 */             entryVersion = 8;
/* 170 */             entryNameWithoutVersionPrefix = entryName;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 175 */     this.version = entryVersion;
/* 176 */     this.entryNameUnversioned = entryNameWithoutVersionPrefix;
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
/*     */   public Slice getSlice() throws IOException {
/* 190 */     if (this.slice == null) {
/* 191 */       RandomAccessReader randomAccessReader = this.parentLogicalZipFile.slice.randomAccessReader();
/*     */ 
/*     */       
/* 194 */       if (randomAccessReader.readInt(this.locHeaderPos) != 67324752) {
/* 195 */         throw new IOException("Zip entry has bad LOC header: " + this.entryName);
/*     */       }
/*     */       
/* 198 */       long dataStartPos = this.locHeaderPos + 30L + randomAccessReader.readShort(this.locHeaderPos + 26L) + randomAccessReader.readShort(this.locHeaderPos + 28L);
/* 199 */       if (dataStartPos > this.parentLogicalZipFile.slice.sliceLength) {
/* 200 */         throw new IOException("Unexpected EOF when trying to read zip entry data: " + this.entryName);
/*     */       }
/*     */ 
/*     */       
/* 204 */       this.slice = this.parentLogicalZipFile.slice.slice(dataStartPos, this.compressedSize, this.isDeflated, this.uncompressedSize);
/*     */     } 
/* 206 */     return this.slice;
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
/*     */   public String getPath() {
/* 218 */     return this.parentLogicalZipFile.getPath() + "!/" + this.entryName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLastModifiedTimeMillis() {
/* 228 */     if (this.lastModifiedTimeMillis == 0L && (this.lastModifiedDateMSDOS != 0 || this.lastModifiedTimeMSDOS != 0)) {
/*     */       
/* 230 */       int lastModifiedSecond = (this.lastModifiedTimeMSDOS & 0x1F) * 2;
/* 231 */       int lastModifiedMinute = this.lastModifiedTimeMSDOS >> 5 & 0x3F;
/* 232 */       int lastModifiedHour = this.lastModifiedTimeMSDOS >> 11;
/* 233 */       int lastModifiedDay = this.lastModifiedDateMSDOS & 0x1F;
/* 234 */       int lastModifiedMonth = (this.lastModifiedDateMSDOS >> 5 & 0x7) - 1;
/* 235 */       int lastModifiedYear = (this.lastModifiedDateMSDOS >> 9) + 1980;
/*     */       
/* 237 */       Calendar lastModifiedCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
/* 238 */       lastModifiedCalendar.set(lastModifiedYear, lastModifiedMonth, lastModifiedDay, lastModifiedHour, lastModifiedMinute, lastModifiedSecond);
/*     */       
/* 240 */       lastModifiedCalendar.set(14, 0);
/*     */ 
/*     */       
/* 243 */       this.lastModifiedTimeMillis = lastModifiedCalendar.getTimeInMillis();
/*     */     } 
/*     */ 
/*     */     
/* 247 */     return this.lastModifiedTimeMillis;
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
/*     */   public int compareTo(FastZipEntry o) {
/* 260 */     int diff0 = o.version - this.version;
/* 261 */     if (diff0 != 0) {
/* 262 */       return diff0;
/*     */     }
/* 264 */     int diff1 = this.entryNameUnversioned.compareTo(o.entryNameUnversioned);
/* 265 */     if (diff1 != 0) {
/* 266 */       return diff1;
/*     */     }
/* 268 */     int diff2 = this.entryName.compareTo(o.entryName);
/* 269 */     if (diff2 != 0) {
/* 270 */       return diff2;
/*     */     }
/*     */ 
/*     */     
/* 274 */     long diff3 = this.locHeaderPos - o.locHeaderPos;
/* 275 */     return (diff3 < 0L) ? -1 : ((diff3 > 0L) ? 1 : 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 283 */     return this.parentLogicalZipFile.hashCode() ^ this.version ^ this.entryName.hashCode() ^ (int)this.locHeaderPos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 291 */     if (this == obj)
/* 292 */       return true; 
/* 293 */     if (!(obj instanceof FastZipEntry)) {
/* 294 */       return false;
/*     */     }
/* 296 */     FastZipEntry other = (FastZipEntry)obj;
/* 297 */     return (this.parentLogicalZipFile.equals(other.parentLogicalZipFile) && compareTo(other) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 305 */     return "jar:file:" + getPath();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fastzipfilereader\FastZipEntry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */