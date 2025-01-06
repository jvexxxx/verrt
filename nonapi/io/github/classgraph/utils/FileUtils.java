/*     */ package nonapi.io.github.classgraph.utils;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.InvalidPathException;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class FileUtils
/*     */ {
/*     */   private static Method directByteBufferCleanerMethod;
/*     */   private static Method cleanerCleanMethod;
/*     */   private static Method attachmentMethod;
/*     */   private static Object theUnsafe;
/*  76 */   private static AtomicBoolean initialized = new AtomicBoolean();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String currDirPath;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MAX_BUFFER_SIZE = 2147483639;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String currDirPath() {
/* 108 */     if (currDirPath == null) {
/*     */ 
/*     */       
/* 111 */       Path path = null;
/* 112 */       String currDirPathStr = System.getProperty("user.dir");
/* 113 */       if (currDirPathStr != null) {
/*     */         try {
/* 115 */           path = Paths.get(currDirPathStr, new String[0]);
/* 116 */         } catch (InvalidPathException invalidPathException) {}
/*     */       }
/*     */ 
/*     */       
/* 120 */       if (path == null) {
/*     */         
/*     */         try {
/*     */           
/* 124 */           path = Paths.get("", new String[0]);
/* 125 */         } catch (InvalidPathException invalidPathException) {}
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 132 */       currDirPath = FastPathResolver.resolve((path == null) ? "" : path.toString());
/*     */     } 
/* 134 */     return currDirPath;
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
/*     */   public static String sanitizeEntryPath(String path, boolean removeInitialSlash, boolean removeFinalSlash) {
/* 154 */     if (path.isEmpty()) {
/* 155 */       return "";
/*     */     }
/*     */ 
/*     */     
/* 159 */     boolean foundSegmentToSanitize = false;
/* 160 */     int pathLen = path.length();
/* 161 */     char[] pathChars = new char[pathLen];
/* 162 */     path.getChars(0, pathLen, pathChars, 0);
/*     */     
/* 164 */     int lastSepIdx = -1;
/* 165 */     char prevC = Character.MIN_VALUE;
/* 166 */     for (int i = 0, ii = pathLen + 1; i < ii; i++) {
/* 167 */       char c = (i == pathLen) ? Character.MIN_VALUE : pathChars[i];
/* 168 */       if (c == '/' || c == '!' || c == '\000') {
/* 169 */         int segmentLength = i - lastSepIdx + 1;
/* 170 */         if ((segmentLength == 0 && prevC == c) || (segmentLength == 1 && pathChars[i - 1] == '.') || (segmentLength == 2 && pathChars[i - 2] == '.' && pathChars[i - 1] == '.'))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 177 */           foundSegmentToSanitize = true;
/*     */         }
/* 179 */         lastSepIdx = i;
/*     */       } 
/* 181 */       prevC = c;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 186 */     boolean pathHasInitialSlash = (pathChars[0] == '/');
/* 187 */     boolean pathHasInitialSlashSlash = (pathHasInitialSlash && pathLen > 1 && pathChars[1] == '/');
/* 188 */     StringBuilder pathSanitized = new StringBuilder(pathLen + 16);
/* 189 */     if (foundSegmentToSanitize) {
/*     */       
/* 191 */       List<List<CharSequence>> allSectionSegments = new ArrayList<>();
/* 192 */       List<CharSequence> currSectionSegments = new ArrayList<>();
/* 193 */       allSectionSegments.add(currSectionSegments);
/* 194 */       int j = -1;
/* 195 */       for (int k = 0; k < pathLen + 1; k++) {
/* 196 */         char c = (k == pathLen) ? Character.MIN_VALUE : pathChars[k];
/* 197 */         if (c == '/' || c == '!' || c == '\000') {
/* 198 */           int segmentStartIdx = j + 1;
/* 199 */           int segmentLen = k - segmentStartIdx;
/* 200 */           if (segmentLen != 0 && (segmentLen != 1 || pathChars[segmentStartIdx] != '.'))
/*     */           {
/* 202 */             if (segmentLen == 2 && pathChars[segmentStartIdx] == '.' && pathChars[segmentStartIdx + 1] == '.') {
/*     */ 
/*     */               
/* 205 */               if (!currSectionSegments.isEmpty()) {
/* 206 */                 currSectionSegments.remove(currSectionSegments.size() - 1);
/*     */               }
/*     */             } else {
/*     */               
/* 210 */               currSectionSegments.add(path.subSequence(segmentStartIdx, segmentStartIdx + segmentLen));
/*     */             }  } 
/* 212 */           if (c == '!' && !currSectionSegments.isEmpty()) {
/*     */             
/* 214 */             currSectionSegments = new ArrayList<>();
/* 215 */             allSectionSegments.add(currSectionSegments);
/*     */           } 
/* 217 */           j = k;
/*     */         } 
/*     */       } 
/*     */       
/* 221 */       for (List<CharSequence> sectionSegments : allSectionSegments) {
/* 222 */         if (!sectionSegments.isEmpty()) {
/*     */           
/* 224 */           if (pathSanitized.length() > 0) {
/* 225 */             pathSanitized.append('!');
/*     */           }
/* 227 */           for (CharSequence sectionSegment : sectionSegments) {
/* 228 */             pathSanitized.append('/');
/* 229 */             pathSanitized.append(sectionSegment);
/*     */           } 
/*     */         } 
/*     */       } 
/* 233 */       if (pathSanitized.length() == 0 && pathHasInitialSlash) {
/* 234 */         pathSanitized.append('/');
/*     */       }
/*     */     } else {
/* 237 */       pathSanitized.append(path);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 242 */     if (VersionFinder.OS == VersionFinder.OperatingSystem.Windows && pathHasInitialSlashSlash) {
/* 243 */       pathSanitized.insert(0, '/');
/*     */     }
/*     */     
/* 246 */     int startIdx = 0;
/* 247 */     if (removeInitialSlash || !pathHasInitialSlash)
/*     */     {
/*     */ 
/*     */       
/* 251 */       while (startIdx < pathSanitized.length() && pathSanitized.charAt(startIdx) == '/') {
/* 252 */         startIdx++;
/*     */       }
/*     */     }
/* 255 */     if (removeFinalSlash) {
/* 256 */       while (pathSanitized.length() > 0 && pathSanitized.charAt(pathSanitized.length() - 1) == '/') {
/* 257 */         pathSanitized.setLength(pathSanitized.length() - 1);
/*     */       }
/*     */     }
/*     */     
/* 261 */     return pathSanitized.substring(startIdx);
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
/*     */   public static boolean isClassfile(String path) {
/* 274 */     int len = path.length();
/* 275 */     return (len > 6 && path.regionMatches(true, len - 6, ".class", 0, 6));
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
/*     */   public static boolean canRead(File file) {
/*     */     try {
/* 289 */       return file.canRead();
/* 290 */     } catch (SecurityException e) {
/* 291 */       return false;
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
/*     */   public static boolean canRead(Path path) {
/*     */     try {
/* 304 */       if (!Files.isReadable(path)) {
/* 305 */         return false;
/*     */       }
/* 307 */     } catch (SecurityException e) {
/* 308 */       return false;
/*     */     } 
/* 310 */     return Files.isRegularFile(path, new java.nio.file.LinkOption[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canReadAndIsFile(File file) {
/*     */     try {
/* 322 */       if (!file.canRead()) {
/* 323 */         return false;
/*     */       }
/* 325 */     } catch (SecurityException e) {
/* 326 */       return false;
/*     */     } 
/* 328 */     return file.isFile();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canReadAndIsFile(Path path) {
/*     */     try {
/* 340 */       if (!Files.isReadable(path)) {
/* 341 */         return false;
/*     */       }
/* 343 */     } catch (SecurityException e) {
/* 344 */       return false;
/*     */     } 
/* 346 */     return Files.isRegularFile(path, new java.nio.file.LinkOption[0]);
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
/*     */   public static void checkCanReadAndIsFile(File file) throws IOException {
/*     */     try {
/* 359 */       if (!file.canRead()) {
/* 360 */         throw new FileNotFoundException("File does not exist or cannot be read: " + file);
/*     */       }
/* 362 */     } catch (SecurityException e) {
/* 363 */       throw new FileNotFoundException("File " + file + " cannot be accessed: " + e);
/*     */     } 
/* 365 */     if (!file.isFile()) {
/* 366 */       throw new IOException("Not a regular file: " + file);
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
/*     */   public static void checkCanReadAndIsFile(Path path) throws IOException {
/*     */     try {
/* 380 */       if (!Files.isReadable(path)) {
/* 381 */         throw new FileNotFoundException("Path does not exist or cannot be read: " + path);
/*     */       }
/* 383 */     } catch (SecurityException e) {
/* 384 */       throw new FileNotFoundException("Path " + path + " cannot be accessed: " + e);
/*     */     } 
/* 386 */     if (!Files.isRegularFile(path, new java.nio.file.LinkOption[0])) {
/* 387 */       throw new IOException("Not a regular file: " + path);
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
/*     */   public static boolean canReadAndIsDir(File file) {
/*     */     try {
/* 400 */       if (!file.canRead()) {
/* 401 */         return false;
/*     */       }
/* 403 */     } catch (SecurityException e) {
/* 404 */       return false;
/*     */     } 
/* 406 */     return file.isDirectory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean canReadAndIsDir(Path path) {
/*     */     try {
/* 418 */       if (!Files.isReadable(path)) {
/* 419 */         return false;
/*     */       }
/* 421 */     } catch (SecurityException e) {
/* 422 */       return false;
/*     */     } 
/* 424 */     return Files.isDirectory(path, new java.nio.file.LinkOption[0]);
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
/*     */   public static void checkCanReadAndIsDir(File file) throws IOException {
/*     */     try {
/* 437 */       if (!file.canRead()) {
/* 438 */         throw new FileNotFoundException("Directory does not exist or cannot be read: " + file);
/*     */       }
/* 440 */     } catch (SecurityException e) {
/* 441 */       throw new FileNotFoundException("File " + file + " cannot be accessed: " + e);
/*     */     } 
/* 443 */     if (!file.isDirectory()) {
/* 444 */       throw new IOException("Not a directory: " + file);
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
/*     */   public static String getParentDirPath(String path, char separator) {
/* 460 */     int lastSlashIdx = path.lastIndexOf(separator);
/* 461 */     if (lastSlashIdx <= 0) {
/* 462 */       return "";
/*     */     }
/* 464 */     return path.substring(0, lastSlashIdx);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getParentDirPath(String path) {
/* 475 */     return getParentDirPath(path, '/');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void lookupCleanMethodPrivileged() {
/* 484 */     if (VersionFinder.JAVA_MAJOR_VERSION < 9) {
/*     */ 
/*     */       
/*     */       try {
/* 488 */         cleanerCleanMethod = Class.forName("sun.misc.Cleaner").getDeclaredMethod("clean", new Class[0]);
/* 489 */         cleanerCleanMethod.setAccessible(true);
/* 490 */         Class<?> directByteBufferClass = Class.forName("sun.nio.ch.DirectBuffer");
/* 491 */         directByteBufferCleanerMethod = directByteBufferClass.getDeclaredMethod("cleaner", new Class[0]);
/* 492 */         attachmentMethod = directByteBufferClass.getMethod("attachment", new Class[0]);
/* 493 */         attachmentMethod.setAccessible(true);
/* 494 */       } catch (SecurityException e) {
/* 495 */         throw new RuntimeException("You need to grant classgraph RuntimePermission(\"accessClassInPackage.sun.misc\") and ReflectPermission(\"suppressAccessChecks\")", e);
/*     */ 
/*     */       
/*     */       }
/* 499 */       catch (ReflectiveOperationException|LinkageError reflectiveOperationException) {}
/*     */     } else {
/*     */       try {
/*     */         Class<?> unsafeClass;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 529 */           unsafeClass = Class.forName("sun.misc.Unsafe");
/* 530 */         } catch (ReflectiveOperationException|LinkageError e) {
/* 531 */           throw new RuntimeException("Could not get class sun.misc.Unsafe", e);
/*     */         } 
/* 533 */         Field theUnsafeField = unsafeClass.getDeclaredField("theUnsafe");
/* 534 */         theUnsafeField.setAccessible(true);
/* 535 */         theUnsafe = theUnsafeField.get((Object)null);
/* 536 */         cleanerCleanMethod = unsafeClass.getMethod("invokeCleaner", new Class[] { ByteBuffer.class });
/* 537 */         cleanerCleanMethod.setAccessible(true);
/* 538 */       } catch (SecurityException e) {
/* 539 */         Class<?> unsafeClass; throw new RuntimeException("You need to grant classgraph RuntimePermission(\"accessClassInPackage.sun.misc\") and ReflectPermission(\"suppressAccessChecks\")", unsafeClass);
/*     */ 
/*     */       
/*     */       }
/* 543 */       catch (ReflectiveOperationException|LinkageError reflectiveOperationException) {}
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
/*     */   private static boolean closeDirectByteBufferPrivileged(ByteBuffer byteBuffer, LogNode log) {
/* 560 */     if (!byteBuffer.isDirect())
/*     */     {
/* 562 */       return true;
/*     */     }
/*     */     try {
/* 565 */       if (VersionFinder.JAVA_MAJOR_VERSION < 9) {
/* 566 */         if (attachmentMethod == null) {
/* 567 */           if (log != null) {
/* 568 */             log.log("Could not unmap ByteBuffer, attachmentMethod == null");
/*     */           }
/* 570 */           return false;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 576 */         if (attachmentMethod.invoke(byteBuffer, new Object[0]) != null)
/*     */         {
/* 578 */           return false;
/*     */         }
/*     */         
/* 581 */         if (directByteBufferCleanerMethod == null) {
/* 582 */           if (log != null) {
/* 583 */             log.log("Could not unmap ByteBuffer, cleanerMethod == null");
/*     */           }
/* 585 */           return false;
/*     */         } 
/*     */         try {
/* 588 */           directByteBufferCleanerMethod.setAccessible(true);
/* 589 */         } catch (Exception e) {
/* 590 */           if (log != null) {
/* 591 */             log.log("Could not unmap ByteBuffer, cleanerMethod.setAccessible(true) failed");
/*     */           }
/* 593 */           return false;
/*     */         } 
/* 595 */         Object cleanerInstance = directByteBufferCleanerMethod.invoke(byteBuffer, new Object[0]);
/* 596 */         if (cleanerInstance == null) {
/* 597 */           if (log != null) {
/* 598 */             log.log("Could not unmap ByteBuffer, cleaner == null");
/*     */           }
/* 600 */           return false;
/*     */         } 
/* 602 */         if (cleanerCleanMethod == null) {
/* 603 */           if (log != null) {
/* 604 */             log.log("Could not unmap ByteBuffer, cleanMethod == null");
/*     */           }
/* 606 */           return false;
/*     */         } 
/*     */         try {
/* 609 */           cleanerCleanMethod.invoke(cleanerInstance, new Object[0]);
/* 610 */           return true;
/* 611 */         } catch (Exception e) {
/* 612 */           if (log != null) {
/* 613 */             log.log("Could not unmap ByteBuffer, cleanMethod.invoke(cleaner) failed: " + e);
/*     */           }
/* 615 */           return false;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 629 */       if (theUnsafe == null) {
/* 630 */         if (log != null) {
/* 631 */           log.log("Could not unmap ByteBuffer, theUnsafe == null");
/*     */         }
/* 633 */         return false;
/*     */       } 
/* 635 */       if (cleanerCleanMethod == null) {
/* 636 */         if (log != null) {
/* 637 */           log.log("Could not unmap ByteBuffer, cleanMethod == null");
/*     */         }
/* 639 */         return false;
/*     */       } 
/*     */       try {
/* 642 */         cleanerCleanMethod.invoke(theUnsafe, new Object[] { byteBuffer });
/* 643 */         return true;
/* 644 */       } catch (IllegalArgumentException e) {
/*     */         
/* 646 */         return false;
/*     */       }
/*     */     
/* 649 */     } catch (ReflectiveOperationException|SecurityException e) {
/* 650 */       if (log != null) {
/* 651 */         log.log("Could not unmap ByteBuffer: " + e);
/*     */       }
/* 653 */       return false;
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
/*     */   public static boolean closeDirectByteBuffer(final ByteBuffer byteBuffer, ReflectionUtils reflectionUtils, final LogNode log) {
/* 668 */     if (byteBuffer != null && byteBuffer.isDirect()) {
/* 669 */       if (!initialized.get()) {
/*     */         try {
/* 671 */           reflectionUtils.doPrivileged(new Callable<Void>()
/*     */               {
/*     */                 public Void call() throws Exception {
/* 674 */                   FileUtils.lookupCleanMethodPrivileged();
/* 675 */                   return null;
/*     */                 }
/*     */               });
/* 678 */         } catch (Throwable e) {
/* 679 */           throw new RuntimeException("Cannot get buffer cleaner method", e);
/*     */         } 
/* 681 */         initialized.set(true);
/*     */       } 
/*     */       try {
/* 684 */         return ((Boolean)reflectionUtils.doPrivileged(new Callable<Boolean>()
/*     */             {
/*     */               public Boolean call() throws Exception {
/* 687 */                 return Boolean.valueOf(FileUtils.closeDirectByteBufferPrivileged(byteBuffer, log));
/*     */               }
/*     */             })).booleanValue();
/* 690 */       } catch (Throwable t) {
/* 691 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 695 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgrap\\utils\FileUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */