/*     */ package nonapi.io.github.classgraph.fastzipfilereader;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import nonapi.io.github.classgraph.fileslice.ArraySlice;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.RandomAccessReader;
/*     */ import nonapi.io.github.classgraph.utils.CollectionUtils;
/*     */ import nonapi.io.github.classgraph.utils.FileUtils;
/*     */ import nonapi.io.github.classgraph.utils.LogNode;
/*     */ import nonapi.io.github.classgraph.utils.StringUtils;
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
/*     */ public class LogicalZipFile
/*     */   extends ZipFileSlice
/*     */ {
/*     */   public List<FastZipEntry> entries;
/*     */   private boolean isMultiReleaseJar;
/*  66 */   Set<String> classpathRoots = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*     */ 
/*     */ 
/*     */   
/*     */   public String classPathManifestEntryValue;
/*     */ 
/*     */ 
/*     */   
/*     */   public String bundleClassPathManifestEntryValue;
/*     */ 
/*     */   
/*     */   public String addExportsManifestEntryValue;
/*     */ 
/*     */   
/*     */   public String addOpensManifestEntryValue;
/*     */ 
/*     */   
/*     */   public String automaticModuleNameManifestEntryValue;
/*     */ 
/*     */   
/*     */   public boolean isJREJar;
/*     */ 
/*     */   
/*     */   private final boolean enableMultiReleaseVersions;
/*     */ 
/*     */   
/*     */   static final String META_INF_PATH_PREFIX = "META-INF/";
/*     */ 
/*     */   
/*     */   private static final String MANIFEST_PATH = "META-INF/MANIFEST.MF";
/*     */ 
/*     */   
/*     */   public static final String MULTI_RELEASE_PATH_PREFIX = "META-INF/versions/";
/*     */ 
/*     */   
/* 101 */   private static final byte[] IMPLEMENTATION_TITLE_KEY = manifestKeyToBytes("Implementation-Title");
/*     */ 
/*     */   
/* 104 */   private static final byte[] SPECIFICATION_TITLE_KEY = manifestKeyToBytes("Specification-Title");
/*     */ 
/*     */   
/* 107 */   private static final byte[] CLASS_PATH_KEY = manifestKeyToBytes("Class-Path");
/*     */ 
/*     */   
/* 110 */   private static final byte[] BUNDLE_CLASSPATH_KEY = manifestKeyToBytes("Bundle-ClassPath");
/*     */ 
/*     */   
/* 113 */   private static final byte[] SPRING_BOOT_CLASSES_KEY = manifestKeyToBytes("Spring-Boot-Classes");
/*     */ 
/*     */   
/* 116 */   private static final byte[] SPRING_BOOT_LIB_KEY = manifestKeyToBytes("Spring-Boot-Lib");
/*     */ 
/*     */   
/* 119 */   private static final byte[] MULTI_RELEASE_KEY = manifestKeyToBytes("Multi-Release");
/*     */ 
/*     */   
/* 122 */   private static final byte[] ADD_EXPORTS_KEY = manifestKeyToBytes("Add-Exports");
/*     */ 
/*     */   
/* 125 */   private static final byte[] ADD_OPENS_KEY = manifestKeyToBytes("Add-Opens");
/*     */ 
/*     */   
/* 128 */   private static final byte[] AUTOMATIC_MODULE_NAME_KEY = manifestKeyToBytes("Automatic-Module-Name");
/*     */ 
/*     */   
/* 131 */   private static byte[] toLowerCase = new byte[256];
/*     */   static {
/* 133 */     for (int i = 32; i < 127; i++) {
/* 134 */       toLowerCase[i] = (byte)Character.toLowerCase((char)i);
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
/*     */   LogicalZipFile(ZipFileSlice zipFileSlice, NestedJarHandler nestedJarHandler, LogNode log, boolean enableMultiReleaseVersions) throws IOException, InterruptedException {
/* 156 */     super(zipFileSlice);
/* 157 */     this.enableMultiReleaseVersions = enableMultiReleaseVersions;
/* 158 */     readCentralDirectory(nestedJarHandler, log);
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
/*     */   private static Map.Entry<String, Integer> getManifestValue(byte[] manifest, int startIdx) {
/*     */     String val;
/* 176 */     int curr = startIdx;
/* 177 */     int len = manifest.length;
/* 178 */     while (curr < len && manifest[curr] == 32)
/*     */     {
/* 180 */       curr++;
/*     */     }
/* 182 */     int firstNonSpaceIdx = curr;
/* 183 */     boolean isMultiLine = false;
/* 184 */     for (; curr < len && !isMultiLine; curr++) {
/* 185 */       byte b = manifest[curr];
/* 186 */       if (b == 13 && curr < len - 1 && manifest[curr + 1] == 10) {
/* 187 */         if (curr < len - 2 && manifest[curr + 2] == 32)
/* 188 */           isMultiLine = true; 
/*     */         break;
/*     */       } 
/* 191 */       if (b == 13 || b == 10) {
/* 192 */         if (curr < len - 1 && manifest[curr + 1] == 32) {
/* 193 */           isMultiLine = true;
/*     */         }
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 199 */     if (!isMultiLine) {
/*     */       
/* 201 */       val = new String(manifest, firstNonSpaceIdx, curr - firstNonSpaceIdx, StandardCharsets.UTF_8);
/*     */     } else {
/*     */       
/* 204 */       ByteArrayOutputStream buf = new ByteArrayOutputStream();
/* 205 */       curr = firstNonSpaceIdx;
/* 206 */       for (; curr < len; curr++) {
/* 207 */         boolean isLineEnd; byte b = manifest[curr];
/*     */         
/* 209 */         if (b == 13 && curr < len - 1 && manifest[curr + 1] == 10) {
/*     */           
/* 211 */           curr += 2;
/* 212 */           isLineEnd = true;
/* 213 */         } else if (b == 13 || b == 10) {
/*     */           
/* 215 */           curr++;
/* 216 */           isLineEnd = true;
/*     */         } else {
/* 218 */           buf.write(b);
/* 219 */           isLineEnd = false;
/*     */         } 
/* 221 */         if (isLineEnd && curr < len && manifest[curr] != 32) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 228 */         val = buf.toString("UTF-8");
/* 229 */       } catch (UnsupportedEncodingException e) {
/*     */         
/* 231 */         throw new RuntimeException("UTF-8 encoding is not supported in your JRE", e);
/*     */       } 
/*     */     } 
/* 234 */     return new AbstractMap.SimpleEntry<>(val.endsWith(" ") ? val.trim() : val, Integer.valueOf(curr));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static byte[] manifestKeyToBytes(String key) {
/* 245 */     byte[] bytes = new byte[key.length()];
/* 246 */     for (int i = 0; i < key.length(); i++) {
/* 247 */       bytes[i] = (byte)Character.toLowerCase(key.charAt(i));
/*     */     }
/* 249 */     return bytes;
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
/*     */   private static boolean keyMatchesAtPosition(byte[] manifest, byte[] key, int pos) {
/* 264 */     if (pos + key.length + 1 > manifest.length || manifest[pos + key.length] != 58) {
/* 265 */       return false;
/*     */     }
/* 267 */     for (int i = 0; i < key.length; i++) {
/*     */       
/* 269 */       if (toLowerCase[manifest[i + pos]] != key[i]) {
/* 270 */         return false;
/*     */       }
/*     */     } 
/* 273 */     return true;
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
/*     */   private void parseManifest(FastZipEntry manifestZipEntry, LogNode log) throws IOException, InterruptedException {
/* 291 */     byte[] manifest = manifestZipEntry.getSlice().load();
/*     */ 
/*     */     
/* 294 */     for (int i = 0; i < manifest.length; ) {
/*     */       
/* 296 */       boolean skip = false;
/* 297 */       if (manifest[i] == 10 || manifest[i] == 13) {
/*     */         
/* 299 */         skip = true;
/*     */       }
/* 301 */       else if (keyMatchesAtPosition(manifest, IMPLEMENTATION_TITLE_KEY, i)) {
/* 302 */         Map.Entry<String, Integer> manifestValueAndEndIdx = getManifestValue(manifest, i + IMPLEMENTATION_TITLE_KEY.length + 1);
/*     */         
/* 304 */         if (((String)manifestValueAndEndIdx.getKey()).equalsIgnoreCase("Java Runtime Environment")) {
/* 305 */           this.isJREJar = true;
/*     */         }
/* 307 */         i = ((Integer)manifestValueAndEndIdx.getValue()).intValue();
/*     */       }
/* 309 */       else if (keyMatchesAtPosition(manifest, SPECIFICATION_TITLE_KEY, i)) {
/* 310 */         Map.Entry<String, Integer> manifestValueAndEndIdx = getManifestValue(manifest, i + SPECIFICATION_TITLE_KEY.length + 1);
/*     */         
/* 312 */         if (((String)manifestValueAndEndIdx.getKey()).equalsIgnoreCase("Java Platform API Specification")) {
/* 313 */           this.isJREJar = true;
/*     */         }
/* 315 */         i = ((Integer)manifestValueAndEndIdx.getValue()).intValue();
/*     */       }
/* 317 */       else if (keyMatchesAtPosition(manifest, CLASS_PATH_KEY, i)) {
/* 318 */         Map.Entry<String, Integer> manifestValueAndEndIdx = getManifestValue(manifest, i + CLASS_PATH_KEY.length + 1);
/*     */ 
/*     */         
/* 321 */         this.classPathManifestEntryValue = manifestValueAndEndIdx.getKey();
/* 322 */         if (log != null) {
/* 323 */           log.log("Found Class-Path entry in manifest file: " + this.classPathManifestEntryValue);
/*     */         }
/* 325 */         i = ((Integer)manifestValueAndEndIdx.getValue()).intValue();
/*     */       }
/* 327 */       else if (keyMatchesAtPosition(manifest, BUNDLE_CLASSPATH_KEY, i)) {
/* 328 */         Map.Entry<String, Integer> manifestValueAndEndIdx = getManifestValue(manifest, i + BUNDLE_CLASSPATH_KEY.length + 1);
/*     */ 
/*     */         
/* 331 */         this.bundleClassPathManifestEntryValue = manifestValueAndEndIdx.getKey();
/* 332 */         if (log != null) {
/* 333 */           log.log("Found Bundle-ClassPath entry in manifest file: " + this.bundleClassPathManifestEntryValue);
/*     */         }
/* 335 */         i = ((Integer)manifestValueAndEndIdx.getValue()).intValue();
/*     */       }
/* 337 */       else if (keyMatchesAtPosition(manifest, SPRING_BOOT_CLASSES_KEY, i)) {
/* 338 */         Map.Entry<String, Integer> manifestValueAndEndIdx = getManifestValue(manifest, i + SPRING_BOOT_CLASSES_KEY.length + 1);
/*     */         
/* 340 */         String springBootClassesFieldVal = manifestValueAndEndIdx.getKey();
/* 341 */         if (!springBootClassesFieldVal.equals("BOOT-INF/classes") && 
/* 342 */           !springBootClassesFieldVal.equals("BOOT-INF/classes/") && 
/* 343 */           !springBootClassesFieldVal.equals("WEB-INF/classes") && 
/* 344 */           !springBootClassesFieldVal.equals("WEB-INF/classes/")) {
/* 345 */           throw new IOException("Spring boot classes are at \"" + springBootClassesFieldVal + "\" rather than the standard location \"BOOT-INF/classes/\" or \"WEB-INF/classes/\" -- please report this at https://github.com/classgraph/classgraph/issues");
/*     */         }
/*     */ 
/*     */         
/* 349 */         i = ((Integer)manifestValueAndEndIdx.getValue()).intValue();
/*     */       }
/* 351 */       else if (keyMatchesAtPosition(manifest, SPRING_BOOT_LIB_KEY, i)) {
/* 352 */         Map.Entry<String, Integer> manifestValueAndEndIdx = getManifestValue(manifest, i + SPRING_BOOT_LIB_KEY.length + 1);
/*     */         
/* 354 */         String springBootLibFieldVal = manifestValueAndEndIdx.getKey();
/* 355 */         if (!springBootLibFieldVal.equals("BOOT-INF/lib") && !springBootLibFieldVal.equals("BOOT-INF/lib/") && 
/* 356 */           !springBootLibFieldVal.equals("WEB-INF/lib") && 
/* 357 */           !springBootLibFieldVal.equals("WEB-INF/lib/")) {
/* 358 */           throw new IOException("Spring boot lib jars are at \"" + springBootLibFieldVal + "\" rather than the standard location \"BOOT-INF/lib/\" or \"WEB-INF/lib/\" -- please report this at https://github.com/classgraph/classgraph/issues");
/*     */         }
/*     */ 
/*     */         
/* 362 */         i = ((Integer)manifestValueAndEndIdx.getValue()).intValue();
/*     */       }
/* 364 */       else if (keyMatchesAtPosition(manifest, MULTI_RELEASE_KEY, i)) {
/* 365 */         Map.Entry<String, Integer> manifestValueAndEndIdx = getManifestValue(manifest, i + MULTI_RELEASE_KEY.length + 1);
/*     */         
/* 367 */         if (((String)manifestValueAndEndIdx.getKey()).equalsIgnoreCase("true")) {
/* 368 */           this.isMultiReleaseJar = true;
/*     */         }
/* 370 */         i = ((Integer)manifestValueAndEndIdx.getValue()).intValue();
/*     */       }
/* 372 */       else if (keyMatchesAtPosition(manifest, ADD_EXPORTS_KEY, i)) {
/* 373 */         Map.Entry<String, Integer> manifestValueAndEndIdx = getManifestValue(manifest, i + ADD_EXPORTS_KEY.length + 1);
/*     */         
/* 375 */         this.addExportsManifestEntryValue = manifestValueAndEndIdx.getKey();
/* 376 */         if (log != null) {
/* 377 */           log.log("Found Add-Exports entry in manifest file: " + this.addExportsManifestEntryValue);
/*     */         }
/* 379 */         i = ((Integer)manifestValueAndEndIdx.getValue()).intValue();
/*     */       }
/* 381 */       else if (keyMatchesAtPosition(manifest, ADD_OPENS_KEY, i)) {
/* 382 */         Map.Entry<String, Integer> manifestValueAndEndIdx = getManifestValue(manifest, i + ADD_OPENS_KEY.length + 1);
/*     */         
/* 384 */         this.addExportsManifestEntryValue = manifestValueAndEndIdx.getKey();
/* 385 */         if (log != null) {
/* 386 */           log.log("Found Add-Opens entry in manifest file: " + this.addOpensManifestEntryValue);
/*     */         }
/* 388 */         i = ((Integer)manifestValueAndEndIdx.getValue()).intValue();
/*     */       }
/* 390 */       else if (keyMatchesAtPosition(manifest, AUTOMATIC_MODULE_NAME_KEY, i)) {
/* 391 */         Map.Entry<String, Integer> manifestValueAndEndIdx = getManifestValue(manifest, i + AUTOMATIC_MODULE_NAME_KEY.length + 1);
/*     */         
/* 393 */         this.automaticModuleNameManifestEntryValue = manifestValueAndEndIdx.getKey();
/* 394 */         if (log != null) {
/* 395 */           log.log("Found Automatic-Module-Name entry in manifest file: " + this.automaticModuleNameManifestEntryValue);
/*     */         }
/*     */         
/* 398 */         i = ((Integer)manifestValueAndEndIdx.getValue()).intValue();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 403 */         skip = true;
/*     */       } 
/*     */       
/* 406 */       if (skip) {
/*     */         
/* 408 */         for (; i < manifest.length - 2; i++) {
/* 409 */           if (manifest[i] == 13 && manifest[i + 1] == 10 && manifest[i + 2] != 32) {
/*     */             
/* 411 */             i += 2; break;
/*     */           } 
/* 413 */           if ((manifest[i] == 13 || manifest[i] == 10) && manifest[i + 1] != 32) {
/*     */             
/* 415 */             i++;
/*     */             break;
/*     */           } 
/*     */         } 
/* 419 */         if (i >= manifest.length - 2) {
/*     */           break;
/*     */         }
/*     */       } 
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
/*     */   private void readCentralDirectory(NestedJarHandler nestedJarHandler, LogNode log) throws IOException, InterruptedException {
/*     */     RandomAccessReader cenReader;
/* 443 */     if (this.slice.sliceLength < 22L) {
/* 444 */       throw new IOException("Zipfile too short to have a central directory");
/*     */     }
/*     */     
/* 447 */     RandomAccessReader reader = this.slice.randomAccessReader();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 452 */     long eocdPos = -1L; long i, iMin;
/* 453 */     for (i = this.slice.sliceLength - 22L, iMin = this.slice.sliceLength - 22L - 32L; i >= iMin && i >= 0L; i--) {
/* 454 */       if (reader.readUnsignedInt(i) == 101010256L) {
/* 455 */         eocdPos = i;
/*     */         break;
/*     */       } 
/*     */     } 
/* 459 */     if (eocdPos < 0L && this.slice.sliceLength > 54L) {
/*     */ 
/*     */       
/* 462 */       int bytesToRead = (int)Math.min(this.slice.sliceLength, 65536L);
/* 463 */       byte[] eocdBytes = new byte[bytesToRead];
/* 464 */       long readStartOff = this.slice.sliceLength - bytesToRead;
/* 465 */       if (reader.read(readStartOff, eocdBytes, 0, bytesToRead) < bytesToRead)
/*     */       {
/* 467 */         throw new IOException("Zipfile is truncated");
/*     */       }
/* 469 */       ArraySlice arraySlice = new ArraySlice(eocdBytes, false, 0L, nestedJarHandler);
/*     */       
/* 471 */       try { RandomAccessReader eocdReader = arraySlice.randomAccessReader(); long l;
/* 472 */         for (l = eocdBytes.length - 22L; l >= 0L; l--) {
/* 473 */           if (eocdReader.readUnsignedInt(l) == 101010256L) {
/* 474 */             eocdPos = l + readStartOff;
/*     */             break;
/*     */           } 
/*     */         } 
/* 478 */         arraySlice.close(); } catch (Throwable throwable) { try { arraySlice.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }
/*     */     
/* 480 */     }  if (eocdPos < 0L) {
/* 481 */       throw new IOException("Jarfile central directory signature not found: " + getPath());
/*     */     }
/* 483 */     long numEnt = reader.readUnsignedShort(eocdPos + 8L);
/* 484 */     if (reader.readUnsignedShort(eocdPos + 4L) > 0 || reader.readUnsignedShort(eocdPos + 6L) > 0 || numEnt != reader
/* 485 */       .readUnsignedShort(eocdPos + 10L)) {
/* 486 */       throw new IOException("Multi-disk jarfiles not supported: " + getPath());
/*     */     }
/* 488 */     long cenSize = reader.readUnsignedInt(eocdPos + 12L);
/* 489 */     if (cenSize > eocdPos) {
/* 490 */       throw new IOException("Central directory size out of range: " + cenSize + " vs. " + eocdPos + ": " + 
/* 491 */           getPath());
/*     */     }
/* 493 */     long cenOff = reader.readUnsignedInt(eocdPos + 16L);
/* 494 */     long cenPos = eocdPos - cenSize;
/*     */ 
/*     */     
/* 497 */     long zip64cdLocIdx = eocdPos - 20L;
/* 498 */     if (zip64cdLocIdx >= 0L && reader.readUnsignedInt(zip64cdLocIdx) == 117853008L) {
/* 499 */       if (reader.readUnsignedInt(zip64cdLocIdx + 4L) > 0L || reader.readUnsignedInt(zip64cdLocIdx + 16L) > 1L) {
/* 500 */         throw new IOException("Multi-disk jarfiles not supported: " + getPath());
/*     */       }
/* 502 */       long eocdPos64 = reader.readLong(zip64cdLocIdx + 8L);
/* 503 */       if (reader.readUnsignedInt(eocdPos64) != 101075792L) {
/* 504 */         throw new IOException("Zip64 central directory at location " + eocdPos64 + " does not have Zip64 central directory header: " + 
/* 505 */             getPath());
/*     */       }
/* 507 */       long numEnt64 = reader.readLong(eocdPos64 + 24L);
/* 508 */       if (reader.readUnsignedInt(eocdPos64 + 16L) > 0L || reader.readUnsignedInt(eocdPos64 + 20L) > 0L || numEnt64 != reader
/* 509 */         .readLong(eocdPos64 + 32L)) {
/* 510 */         throw new IOException("Multi-disk jarfiles not supported: " + getPath());
/*     */       }
/* 512 */       if (numEnt == 65535L) {
/* 513 */         numEnt = numEnt64;
/* 514 */       } else if (numEnt != numEnt64) {
/*     */         
/* 516 */         numEnt = -1L;
/*     */       } 
/*     */       
/* 519 */       long cenSize64 = reader.readLong(eocdPos64 + 40L);
/* 520 */       if (cenSize == 4294967295L) {
/* 521 */         cenSize = cenSize64;
/* 522 */       } else if (cenSize != cenSize64) {
/* 523 */         throw new IOException("Mismatch in central directory size: " + cenSize + " vs. " + cenSize64 + ": " + 
/* 524 */             getPath());
/*     */       } 
/*     */ 
/*     */       
/* 528 */       cenPos = eocdPos64 - cenSize;
/*     */       
/* 530 */       long cenOff64 = reader.readLong(eocdPos64 + 48L);
/* 531 */       if (cenOff == 4294967295L) {
/* 532 */         cenOff = cenOff64;
/* 533 */       } else if (cenOff != cenOff64) {
/* 534 */         throw new IOException("Mismatch in central directory offset: " + cenOff + " vs. " + cenOff64 + ": " + 
/* 535 */             getPath());
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 540 */     long locPos = cenPos - cenOff;
/* 541 */     if (locPos < 0L) {
/* 542 */       throw new IOException("Local file header offset out of range: " + locPos + ": " + getPath());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 548 */     if (cenSize > 2147483639L) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 555 */       cenReader = this.slice.slice(cenPos, cenSize, false, 0L).randomAccessReader();
/*     */     }
/*     */     else {
/*     */       
/* 559 */       byte[] entryBytes = new byte[(int)cenSize];
/* 560 */       if (reader.read(cenPos, entryBytes, 0, (int)cenSize) < cenSize)
/*     */       {
/* 562 */         throw new IOException("Zipfile is truncated");
/*     */       }
/*     */       
/* 565 */       cenReader = (new ArraySlice(entryBytes, false, 0L, nestedJarHandler)).randomAccessReader();
/*     */     } 
/*     */     
/* 568 */     if (numEnt == -1L) {
/*     */       
/* 570 */       numEnt = 0L;
/* 571 */       for (long entOff = 0L; entOff + 46L <= cenSize; ) {
/* 572 */         long sig = cenReader.readUnsignedInt(entOff);
/* 573 */         if (sig != 33639248L) {
/* 574 */           throw new IOException("Invalid central directory signature: 0x" + 
/* 575 */               Integer.toString((int)sig, 16) + ": " + getPath());
/*     */         }
/* 577 */         int filenameLen = cenReader.readUnsignedShort(entOff + 28L);
/* 578 */         int extraFieldLen = cenReader.readUnsignedShort(entOff + 30L);
/* 579 */         int commentLen = cenReader.readUnsignedShort(entOff + 32L);
/* 580 */         entOff += (46 + filenameLen + extraFieldLen + commentLen);
/* 581 */         numEnt++;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 586 */     if (numEnt > 2147483639L)
/*     */     {
/* 588 */       throw new IOException("Too many zipfile entries: " + numEnt);
/*     */     }
/*     */ 
/*     */     
/* 592 */     if (numEnt > cenSize / 46L)
/*     */     {
/* 594 */       throw new IOException("Too many zipfile entries: " + numEnt + " (expected a max of " + (cenSize / 46L) + " based on central directory size)");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 599 */     this.entries = new ArrayList<>((int)numEnt);
/* 600 */     FastZipEntry manifestZipEntry = null;
/*     */     try {
/* 602 */       int entSize = 0; long entOff;
/* 603 */       for (entOff = 0L; entOff + 46L <= cenSize; entOff += entSize) {
/* 604 */         long sig = cenReader.readUnsignedInt(entOff);
/* 605 */         if (sig != 33639248L) {
/* 606 */           throw new IOException("Invalid central directory signature: 0x" + 
/* 607 */               Integer.toString((int)sig, 16) + ": " + getPath());
/*     */         }
/* 609 */         int filenameLen = cenReader.readUnsignedShort(entOff + 28L);
/* 610 */         int extraFieldLen = cenReader.readUnsignedShort(entOff + 30L);
/* 611 */         int commentLen = cenReader.readUnsignedShort(entOff + 32L);
/* 612 */         entSize = 46 + filenameLen + extraFieldLen + commentLen;
/*     */ 
/*     */         
/* 615 */         long filenameStartOff = entOff + 46L;
/* 616 */         long filenameEndOff = filenameStartOff + filenameLen;
/* 617 */         if (filenameEndOff > cenSize) {
/* 618 */           if (log != null) {
/* 619 */             log.log("Filename extends past end of entry -- skipping entry at offset " + entOff);
/*     */           }
/*     */           break;
/*     */         } 
/* 623 */         String entryName = cenReader.readString(filenameStartOff, filenameLen);
/* 624 */         String entryNameSanitized = FileUtils.sanitizeEntryPath(entryName, true, false);
/*     */         
/* 626 */         if (!entryNameSanitized.isEmpty() && !entryName.endsWith("/")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 632 */           int flags = cenReader.readUnsignedShort(entOff + 8L);
/* 633 */           if ((flags & 0x1) != 0)
/* 634 */           { if (log != null) {
/* 635 */               log.log("Skipping encrypted zip entry: " + entryNameSanitized);
/*     */             
/*     */             } }
/*     */           
/*     */           else
/*     */           
/* 641 */           { int compressionMethod = cenReader.readUnsignedShort(entOff + 10L);
/* 642 */             if (compressionMethod != 0 && compressionMethod != 8)
/* 643 */             { if (log != null) {
/* 644 */                 log.log("Skipping zip entry with invalid compression method " + compressionMethod + ": " + entryNameSanitized);
/*     */               } }
/*     */             
/*     */             else
/*     */             
/* 649 */             { boolean isDeflated = (compressionMethod == 8);
/*     */ 
/*     */               
/* 652 */               long compressedSize = cenReader.readUnsignedInt(entOff + 20L);
/* 653 */               long uncompressedSize = cenReader.readUnsignedInt(entOff + 24L);
/*     */ 
/*     */               
/* 656 */               int fileAttributes = cenReader.readUnsignedShort(entOff + 40L);
/*     */               
/* 658 */               long pos = cenReader.readUnsignedInt(entOff + 42L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 664 */               long lastModifiedMillis = 0L;
/* 665 */               if (extraFieldLen > 0) {
/* 666 */                 int extraFieldOff; for (extraFieldOff = 0; extraFieldOff + 4 < extraFieldLen; ) {
/* 667 */                   long tagOff = filenameEndOff + extraFieldOff;
/* 668 */                   int tag = cenReader.readUnsignedShort(tagOff);
/* 669 */                   int size = cenReader.readUnsignedShort(tagOff + 2L);
/* 670 */                   if (extraFieldOff + 4 + size > extraFieldLen) {
/*     */                     
/* 672 */                     if (log != null) {
/* 673 */                       log.log("Skipping zip entry with invalid extra field size: " + entryNameSanitized);
/*     */                     }
/*     */                     break;
/*     */                   } 
/* 677 */                   if (tag == 1 && size >= 20) {
/*     */                     
/* 679 */                     long uncompressedSize64 = cenReader.readLong(tagOff + 4L + 0L);
/* 680 */                     if (uncompressedSize == 4294967295L) {
/* 681 */                       uncompressedSize = uncompressedSize64;
/* 682 */                     } else if (uncompressedSize != uncompressedSize64) {
/* 683 */                       throw new IOException("Mismatch in uncompressed size: " + uncompressedSize + " vs. " + uncompressedSize64 + ": " + entryNameSanitized);
/*     */                     } 
/*     */                     
/* 686 */                     long compressedSize64 = cenReader.readLong(tagOff + 4L + 8L);
/* 687 */                     if (compressedSize == 4294967295L) {
/* 688 */                       compressedSize = compressedSize64;
/* 689 */                     } else if (compressedSize != compressedSize64) {
/* 690 */                       throw new IOException("Mismatch in compressed size: " + compressedSize + " vs. " + compressedSize64 + ": " + entryNameSanitized);
/*     */                     } 
/*     */ 
/*     */                     
/* 694 */                     if (size >= 28) {
/* 695 */                       long pos64 = cenReader.readLong(tagOff + 4L + 16L);
/* 696 */                       if (pos == 4294967295L) {
/* 697 */                         pos = pos64; break;
/* 698 */                       }  if (pos != pos64) {
/* 699 */                         throw new IOException("Mismatch in entry pos: " + pos + " vs. " + pos64 + ": " + entryNameSanitized);
/*     */                       }
/*     */                     } 
/*     */                     
/*     */                     break;
/*     */                   } 
/* 705 */                   if (tag == 21589 && size >= 5) {
/*     */                     
/* 707 */                     int bits = cenReader.readUnsignedByte(tagOff + 4L + 0L);
/* 708 */                     if ((bits & 0x1) == 1 && size >= 13) {
/* 709 */                       lastModifiedMillis = cenReader.readLong(tagOff + 4L + 1L) * 1000L;
/*     */                     }
/*     */                   }
/* 712 */                   else if (tag == 22613 && size >= 20) {
/*     */                     
/* 714 */                     lastModifiedMillis = cenReader.readLong(tagOff + 4L + 8L) * 1000L;
/*     */                   
/*     */                   }
/* 717 */                   else if (tag != 30805) {
/*     */ 
/*     */                     
/* 720 */                     if (tag == 28789) {
/*     */                       
/* 722 */                       int version = cenReader.readUnsignedByte(tagOff + 4L + 0L);
/* 723 */                       if (version != 1) {
/* 724 */                         throw new IOException("Unknown Unicode entry name format " + version + " in extra field: " + entryNameSanitized);
/*     */                       }
/* 726 */                       if (size > 9) {
/*     */                         
/*     */                         try {
/* 729 */                           entryNameSanitized = cenReader.readString(tagOff + 9L, size - 9);
/* 730 */                         } catch (IllegalArgumentException e) {
/* 731 */                           throw new IOException("Malformed extended Unicode entry name for entry: " + entryNameSanitized);
/*     */                         } 
/*     */                       }
/*     */                     } 
/*     */                   } 
/* 736 */                   extraFieldOff += 4 + size;
/*     */                 } 
/*     */               } 
/*     */               
/* 740 */               int lastModifiedTimeMSDOS = 0;
/* 741 */               int lastModifiedDateMSDOS = 0;
/* 742 */               if (lastModifiedMillis == 0L) {
/*     */                 
/* 744 */                 lastModifiedTimeMSDOS = cenReader.readUnsignedShort(entOff + 12L);
/* 745 */                 lastModifiedDateMSDOS = cenReader.readUnsignedShort(entOff + 14L);
/*     */               } 
/*     */               
/* 748 */               if (compressedSize < 0L)
/* 749 */               { if (log != null) {
/* 750 */                   log.log("Skipping zip entry with invalid compressed size (" + compressedSize + "): " + entryNameSanitized);
/*     */                 
/*     */                 }
/*     */                  }
/*     */               
/* 755 */               else if (uncompressedSize < 0L)
/* 756 */               { if (log != null) {
/* 757 */                   log.log("Skipping zip entry with invalid uncompressed size (" + uncompressedSize + "): " + entryNameSanitized);
/*     */                 
/*     */                 }
/*     */                  }
/*     */               
/* 762 */               else if (pos < 0L)
/* 763 */               { if (log != null) {
/* 764 */                   log.log("Skipping zip entry with invalid pos (" + pos + "): " + entryNameSanitized);
/*     */                 } }
/*     */               
/*     */               else
/*     */               
/* 769 */               { long locHeaderPos = locPos + pos;
/* 770 */                 if (locHeaderPos < 0L)
/* 771 */                 { if (log != null) {
/* 772 */                     log.log("Skipping zip entry with invalid loc header position (" + locHeaderPos + "): " + entryNameSanitized);
/*     */                   
/*     */                   }
/*     */                    }
/*     */                 
/* 777 */                 else if (locHeaderPos + 4L >= this.slice.sliceLength)
/* 778 */                 { if (log != null) {
/* 779 */                     log.log("Unexpected EOF when trying to read LOC header: " + entryNameSanitized);
/*     */                   
/*     */                   } }
/*     */                 
/*     */                 else
/*     */                 
/* 785 */                 { FastZipEntry entry = new FastZipEntry(this, locHeaderPos, entryNameSanitized, isDeflated, compressedSize, uncompressedSize, lastModifiedMillis, lastModifiedTimeMSDOS, lastModifiedDateMSDOS, fileAttributes, this.enableMultiReleaseVersions);
/*     */ 
/*     */                   
/* 788 */                   this.entries.add(entry);
/*     */ 
/*     */                   
/* 791 */                   if (entry.entryName.equals("META-INF/MANIFEST.MF"))
/* 792 */                     manifestZipEntry = entry;  }  }  }  } 
/*     */         } 
/*     */       } 
/* 795 */     } catch (EOFException|IndexOutOfBoundsException e) {
/*     */       
/* 797 */       if (log != null) {
/* 798 */         log.log("Reached premature EOF" + (
/* 799 */             this.entries.isEmpty() ? "" : (" after reading zip entry " + this.entries.get(this.entries.size() - 1))));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 804 */     if (manifestZipEntry != null) {
/* 805 */       parseManifest(manifestZipEntry, log);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 810 */     if (this.isMultiReleaseJar) {
/* 811 */       if (VersionFinder.JAVA_MAJOR_VERSION < 9) {
/* 812 */         if (log != null) {
/* 813 */           log.log("This is a multi-release jar, but JRE version " + VersionFinder.JAVA_MAJOR_VERSION + " does not support multi-release jars");
/*     */         }
/*     */       } else {
/*     */         
/* 817 */         if (log != null) {
/*     */           
/* 819 */           Set<Integer> versionsFound = new HashSet<>();
/* 820 */           for (FastZipEntry entry : this.entries) {
/* 821 */             if (entry.version > 8) {
/* 822 */               versionsFound.add(Integer.valueOf(entry.version));
/*     */             }
/*     */           } 
/* 825 */           List<Integer> versionsFoundSorted = new ArrayList<>(versionsFound);
/* 826 */           CollectionUtils.sortIfNotEmpty(versionsFoundSorted);
/* 827 */           log.log("This is a multi-release jar, with versions: " + 
/* 828 */               StringUtils.join(", ", versionsFoundSorted));
/*     */         } 
/*     */ 
/*     */         
/* 832 */         CollectionUtils.sortIfNotEmpty(this.entries);
/*     */ 
/*     */ 
/*     */         
/* 836 */         List<FastZipEntry> unversionedZipEntriesMasked = new ArrayList<>(this.entries.size());
/* 837 */         Map<String, String> unversionedPathToVersionedPath = new HashMap<>();
/* 838 */         for (FastZipEntry versionedZipEntry : this.entries) {
/* 839 */           if (!unversionedPathToVersionedPath.containsKey(versionedZipEntry.entryNameUnversioned)) {
/*     */             
/* 841 */             unversionedPathToVersionedPath.put(versionedZipEntry.entryNameUnversioned, versionedZipEntry.entryName);
/*     */             
/* 843 */             unversionedZipEntriesMasked.add(versionedZipEntry); continue;
/* 844 */           }  if (log != null) {
/* 845 */             log.log((String)unversionedPathToVersionedPath.get(versionedZipEntry.entryNameUnversioned) + " masks " + versionedZipEntry.entryName);
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 851 */         this.entries = unversionedZipEntriesMasked;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 860 */     return super.equals(o);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 865 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 873 */     return getPath();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fastzipfilereader\LogicalZipFile.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */