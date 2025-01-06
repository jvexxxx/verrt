/*      */ package nonapi.io.github.classgraph.fastzipfilereader;
/*      */ 
/*      */ import io.github.classgraph.ModuleReaderProxy;
/*      */ import io.github.classgraph.ModuleRef;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.lang.reflect.Method;
/*      */ import java.net.HttpURLConnection;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URI;
/*      */ import java.net.URL;
/*      */ import java.net.URLConnection;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.file.FileSystem;
/*      */ import java.nio.file.FileSystemNotFoundException;
/*      */ import java.nio.file.Files;
/*      */ import java.nio.file.Path;
/*      */ import java.nio.file.Paths;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import java.util.concurrent.atomic.AtomicBoolean;
/*      */ import java.util.zip.DataFormatException;
/*      */ import java.util.zip.Inflater;
/*      */ import java.util.zip.ZipException;
/*      */ import nonapi.io.github.classgraph.concurrency.InterruptionChecker;
/*      */ import nonapi.io.github.classgraph.concurrency.SingletonMap;
/*      */ import nonapi.io.github.classgraph.fileslice.ArraySlice;
/*      */ import nonapi.io.github.classgraph.fileslice.FileSlice;
/*      */ import nonapi.io.github.classgraph.fileslice.Slice;
/*      */ import nonapi.io.github.classgraph.recycler.Recycler;
/*      */ import nonapi.io.github.classgraph.recycler.Resettable;
/*      */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*      */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*      */ import nonapi.io.github.classgraph.utils.FastPathResolver;
/*      */ import nonapi.io.github.classgraph.utils.FileUtils;
/*      */ import nonapi.io.github.classgraph.utils.JarUtils;
/*      */ import nonapi.io.github.classgraph.utils.LogNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class NestedJarHandler
/*      */ {
/*      */   public final ScanSpec scanSpec;
/*      */   public ReflectionUtils reflectionUtils;
/*      */   
/*   93 */   private SingletonMap<File, PhysicalZipFile, IOException> canonicalFileToPhysicalZipFileMap = new SingletonMap<File, PhysicalZipFile, IOException>()
/*      */     {
/*      */       public PhysicalZipFile newInstance(File canonicalFile, LogNode log) throws IOException
/*      */       {
/*   97 */         return new PhysicalZipFile(canonicalFile, NestedJarHandler.this, log);
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  106 */   private SingletonMap<FastZipEntry, ZipFileSlice, IOException> fastZipEntryToZipFileSliceMap = new SingletonMap<FastZipEntry, ZipFileSlice, IOException>()
/*      */     {
/*      */       public ZipFileSlice newInstance(FastZipEntry childZipEntry, LogNode log) throws IOException, InterruptedException
/*      */       {
/*      */         ZipFileSlice childZipEntrySlice;
/*      */         
/*  112 */         if (!childZipEntry.isDeflated) {
/*      */ 
/*      */           
/*  115 */           childZipEntrySlice = new ZipFileSlice(childZipEntry);
/*      */         
/*      */         }
/*      */         else {
/*      */ 
/*      */           
/*  121 */           if (log != null) {
/*  122 */             log.log("Inflating nested zip entry: " + childZipEntry + " ; uncompressed size: " + childZipEntry.uncompressedSize);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  131 */           PhysicalZipFile physicalZipFile = new PhysicalZipFile(childZipEntry.getSlice().open(), (childZipEntry.uncompressedSize >= 0L && childZipEntry.uncompressedSize <= 2147483639L) ? (int)childZipEntry.uncompressedSize : -1L, childZipEntry.entryName, NestedJarHandler.this, log);
/*      */ 
/*      */ 
/*      */           
/*  135 */           childZipEntrySlice = new ZipFileSlice(physicalZipFile, childZipEntry);
/*      */         } 
/*  137 */         return childZipEntrySlice;
/*      */       }
/*      */     };
/*      */ 
/*      */   
/*  142 */   private SingletonMap<ZipFileSlice, LogicalZipFile, IOException> zipFileSliceToLogicalZipFileMap = new SingletonMap<ZipFileSlice, LogicalZipFile, IOException>()
/*      */     {
/*      */ 
/*      */       
/*      */       public LogicalZipFile newInstance(ZipFileSlice zipFileSlice, LogNode log) throws IOException, InterruptedException
/*      */       {
/*  148 */         return new LogicalZipFile(zipFileSlice, NestedJarHandler.this, log, NestedJarHandler.this.scanSpec.enableMultiReleaseVersions);
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  157 */   public SingletonMap<String, Map.Entry<LogicalZipFile, String>, IOException> nestedPathToLogicalZipFileAndPackageRootMap = new SingletonMap<String, Map.Entry<LogicalZipFile, String>, IOException>()
/*      */     {
/*      */       public Map.Entry<LogicalZipFile, String> newInstance(String nestedJarPathRaw, LogNode log) throws IOException, InterruptedException {
/*      */         Map.Entry<LogicalZipFile, String> parentLogicalZipFileAndPackageRoot;
/*      */         ZipFileSlice childZipEntrySlice;
/*      */         LogicalZipFile childLogicalZipFile;
/*  163 */         String nestedJarPath = FastPathResolver.resolve(nestedJarPathRaw);
/*  164 */         int lastPlingIdx = nestedJarPath.lastIndexOf('!');
/*  165 */         if (lastPlingIdx < 0) {
/*      */           PhysicalZipFile physicalZipFile;
/*      */ 
/*      */           
/*      */           LogicalZipFile logicalZipFile;
/*      */ 
/*      */           
/*  172 */           boolean isURL = JarUtils.URL_SCHEME_PATTERN.matcher(nestedJarPath).matches();
/*      */           
/*  174 */           if (isURL) {
/*  175 */             String scheme = nestedJarPath.substring(0, nestedJarPath.indexOf(':'));
/*  176 */             if (NestedJarHandler.this.scanSpec.allowedURLSchemes == null || 
/*  177 */               !NestedJarHandler.this.scanSpec.allowedURLSchemes.contains(scheme))
/*      */             {
/*      */               
/*  180 */               throw new IOException("Scanning of URL scheme \"" + scheme + "\" has not been enabled -- cannot scan classpath element: " + nestedJarPath);
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  186 */             physicalZipFile = NestedJarHandler.this.downloadJarFromURL(nestedJarPath, log);
/*      */           } else {
/*      */ 
/*      */             
/*      */             try {
/*      */               
/*  192 */               File canonicalFile = (new File(nestedJarPath)).getCanonicalFile();
/*      */               
/*  194 */               physicalZipFile = (PhysicalZipFile)NestedJarHandler.this.canonicalFileToPhysicalZipFileMap.get(canonicalFile, log);
/*  195 */             } catch (nonapi.io.github.classgraph.concurrency.SingletonMap.NullSingletonException|nonapi.io.github.classgraph.concurrency.SingletonMap.NewInstanceException e) {
/*      */               
/*  197 */               throw new IOException("Could not get PhysicalZipFile for path " + nestedJarPath + " : " + (
/*  198 */                   (e.getCause() == null) ? e : e.getCause()));
/*  199 */             } catch (SecurityException e) {
/*      */               
/*  201 */               throw new IOException("Path component " + nestedJarPath + " could not be canonicalized: " + e);
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  207 */           ZipFileSlice topLevelSlice = new ZipFileSlice(physicalZipFile);
/*      */           
/*      */           try {
/*  210 */             logicalZipFile = (LogicalZipFile)NestedJarHandler.this.zipFileSliceToLogicalZipFileMap.get(topLevelSlice, log);
/*  211 */           } catch (nonapi.io.github.classgraph.concurrency.SingletonMap.NullSingletonException e) {
/*  212 */             throw new IOException("Could not get toplevel slice " + topLevelSlice + " : " + e);
/*  213 */           } catch (nonapi.io.github.classgraph.concurrency.SingletonMap.NewInstanceException e) {
/*  214 */             throw new IOException("Could not get toplevel slice " + topLevelSlice, e);
/*      */           } 
/*      */ 
/*      */           
/*  218 */           return new AbstractMap.SimpleEntry<>(logicalZipFile, "");
/*      */         } 
/*      */ 
/*      */         
/*  222 */         String parentPath = nestedJarPath.substring(0, lastPlingIdx);
/*  223 */         String childPath = nestedJarPath.substring(lastPlingIdx + 1);
/*      */         
/*  225 */         String str1 = FileUtils.sanitizeEntryPath(childPath, true, true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  235 */           parentLogicalZipFileAndPackageRoot = (Map.Entry<LogicalZipFile, String>)NestedJarHandler.this.nestedPathToLogicalZipFileAndPackageRootMap.get(parentPath, log);
/*  236 */         } catch (nonapi.io.github.classgraph.concurrency.SingletonMap.NullSingletonException e) {
/*  237 */           LogicalZipFile logicalZipFile; throw new IOException("Could not get parent logical zipfile " + parentPath + " : " + logicalZipFile);
/*  238 */         } catch (nonapi.io.github.classgraph.concurrency.SingletonMap.NewInstanceException e) {
/*  239 */           throw new IOException("Could not get parent logical zipfile " + parentPath, e);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  244 */         LogicalZipFile parentLogicalZipFile = parentLogicalZipFileAndPackageRoot.getKey();
/*      */ 
/*      */         
/*  247 */         boolean isDirectory = false;
/*  248 */         while (str1.endsWith("/")) {
/*      */           
/*  250 */           isDirectory = true;
/*  251 */           str1 = str1.substring(0, str1.length() - 1);
/*      */         } 
/*  253 */         FastZipEntry childZipEntry = null;
/*  254 */         if (!isDirectory)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  264 */           for (FastZipEntry entry : parentLogicalZipFile.entries) {
/*  265 */             if (entry.entryName.equals(str1)) {
/*  266 */               childZipEntry = entry;
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         }
/*  271 */         if (childZipEntry == null) {
/*      */ 
/*      */           
/*  274 */           String childPathPrefix = str1 + "/";
/*  275 */           for (FastZipEntry entry : parentLogicalZipFile.entries) {
/*  276 */             if (entry.entryName.startsWith(childPathPrefix)) {
/*  277 */               isDirectory = true;
/*      */ 
/*      */               
/*      */               break;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/*  285 */         if (isDirectory) {
/*  286 */           if (!str1.isEmpty()) {
/*      */ 
/*      */ 
/*      */             
/*  290 */             if (log != null) {
/*  291 */               log.log("Path " + str1 + " in jarfile " + parentLogicalZipFile + " is a directory, not a file -- using as package root");
/*      */             }
/*      */             
/*  294 */             parentLogicalZipFile.classpathRoots.add(str1);
/*      */           } 
/*      */           
/*  297 */           return new AbstractMap.SimpleEntry<>(parentLogicalZipFile, str1);
/*      */         } 
/*      */         
/*  300 */         if (childZipEntry == null) {
/*  301 */           throw new IOException("Path " + str1 + " does not exist in jarfile " + parentLogicalZipFile);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  306 */         if (!NestedJarHandler.this.scanSpec.scanNestedJars) {
/*  307 */           throw new IOException("Nested jar scanning is disabled -- skipping nested jar " + nestedJarPath);
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  320 */           childZipEntrySlice = (ZipFileSlice)NestedJarHandler.this.fastZipEntryToZipFileSliceMap.get(childZipEntry, log);
/*  321 */         } catch (nonapi.io.github.classgraph.concurrency.SingletonMap.NullSingletonException e) {
/*  322 */           throw new IOException("Could not get child zip entry slice " + childZipEntry + " : " + e);
/*      */         }
/*  324 */         catch (nonapi.io.github.classgraph.concurrency.SingletonMap.NewInstanceException e) {
/*  325 */           throw new IOException("Could not get child zip entry slice " + childZipEntry, e);
/*      */         } 
/*      */ 
/*      */         
/*  329 */         LogNode zipSliceLog = (log == null) ? null : log.log("Getting zipfile slice " + childZipEntrySlice + " for nested jar " + childZipEntry.entryName);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  335 */           childLogicalZipFile = (LogicalZipFile)NestedJarHandler.this.zipFileSliceToLogicalZipFileMap.get(childZipEntrySlice, zipSliceLog);
/*      */         }
/*  337 */         catch (nonapi.io.github.classgraph.concurrency.SingletonMap.NullSingletonException e) {
/*  338 */           throw new IOException("Could not get child logical zipfile " + childZipEntrySlice + " : " + e);
/*      */         }
/*  340 */         catch (nonapi.io.github.classgraph.concurrency.SingletonMap.NewInstanceException e) {
/*  341 */           throw new IOException("Could not get child logical zipfile " + childZipEntrySlice, e);
/*      */         } 
/*      */ 
/*      */         
/*  345 */         return new AbstractMap.SimpleEntry<>(childLogicalZipFile, "");
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */   
/*  351 */   public SingletonMap<ModuleRef, Recycler<ModuleReaderProxy, IOException>, IOException> moduleRefToModuleReaderProxyRecyclerMap = new SingletonMap<ModuleRef, Recycler<ModuleReaderProxy, IOException>, IOException>()
/*      */     {
/*      */ 
/*      */       
/*      */       public Recycler<ModuleReaderProxy, IOException> newInstance(final ModuleRef moduleRef, LogNode ignored)
/*      */       {
/*  357 */         return new Recycler<ModuleReaderProxy, IOException>()
/*      */           {
/*      */             public ModuleReaderProxy newInstance() throws IOException {
/*  360 */               return moduleRef.open();
/*      */             }
/*      */           };
/*      */       }
/*      */     };
/*      */ 
/*      */   
/*  367 */   private Recycler<RecyclableInflater, RuntimeException> inflaterRecycler = new Recycler<RecyclableInflater, RuntimeException>()
/*      */     {
/*      */       public NestedJarHandler.RecyclableInflater newInstance() throws RuntimeException
/*      */       {
/*  371 */         return new NestedJarHandler.RecyclableInflater();
/*      */       }
/*      */     };
/*      */ 
/*      */   
/*  376 */   private Set<Slice> openSlices = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*      */ 
/*      */   
/*  379 */   private Set<File> tempFiles = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*      */ 
/*      */   
/*      */   public static final String TEMP_FILENAME_LEAF_SEPARATOR = "---";
/*      */ 
/*      */   
/*  385 */   private final AtomicBoolean closed = new AtomicBoolean(false);
/*      */ 
/*      */ 
/*      */   
/*      */   public InterruptionChecker interruptionChecker;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int DEFAULT_BUFFER_SIZE = 16384;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int MAX_INITIAL_BUFFER_SIZE = 16777216;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int HTTP_TIMEOUT = 5000;
/*      */ 
/*      */ 
/*      */   
/*      */   private static Method runFinalizationMethod;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public NestedJarHandler(ScanSpec scanSpec, InterruptionChecker interruptionChecker, ReflectionUtils reflectionUtils) {
/*  411 */     this.scanSpec = scanSpec;
/*  412 */     this.interruptionChecker = interruptionChecker;
/*  413 */     this.reflectionUtils = reflectionUtils;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String leafname(String path) {
/*  426 */     return path.substring(path.lastIndexOf('/') + 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String sanitizeFilename(String filename) {
/*  437 */     return filename.replace('/', '_').replace('\\', '_').replace(':', '_').replace('?', '_').replace('&', '_')
/*  438 */       .replace('=', '_').replace(' ', '_');
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public File makeTempFile(String filePathBase, boolean onlyUseLeafname) throws IOException {
/*  453 */     File tempFile = File.createTempFile("ClassGraph--", "---" + 
/*  454 */         sanitizeFilename(onlyUseLeafname ? leafname(filePathBase) : filePathBase));
/*  455 */     tempFile.deleteOnExit();
/*  456 */     this.tempFiles.add(tempFile);
/*  457 */     return tempFile;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removeTempFile(File tempFile) throws IOException, SecurityException {
/*  471 */     if (this.tempFiles.remove(tempFile)) {
/*  472 */       Files.delete(tempFile.toPath());
/*      */     } else {
/*  474 */       throw new IOException("Not a temp file: " + tempFile);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void markSliceAsOpen(Slice slice) throws IOException {
/*  487 */     this.openSlices.add(slice);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void markSliceAsClosed(Slice slice) {
/*  497 */     this.openSlices.remove(slice);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PhysicalZipFile downloadJarFromURL(String jarURL, LogNode log) throws IOException, InterruptedException
/*      */   {
/*  521 */     URL url = null;
/*      */     try {
/*  523 */       url = new URL(jarURL);
/*  524 */     } catch (MalformedURLException e1) {
/*      */       try {
/*  526 */         url = (new URI(jarURL)).toURL();
/*  527 */       } catch (MalformedURLException|IllegalArgumentException|java.net.URISyntaxException e2) {
/*  528 */         throw new IOException("Could not parse URL: " + jarURL);
/*      */       } 
/*      */     } 
/*      */     
/*  532 */     String scheme = url.getProtocol();
/*  533 */     if (!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https")) {
/*      */       
/*      */       try {
/*      */         
/*  537 */         Path path = Paths.get(url.toURI());
/*      */         
/*  539 */         FileSystem fs = path.getFileSystem();
/*  540 */         if (log != null) {
/*  541 */           log.log("URL " + jarURL + " is backed by filesystem " + fs.getClass().getName());
/*      */         }
/*      */         
/*  544 */         return new PhysicalZipFile(path, this, log);
/*  545 */       } catch (IllegalArgumentException|SecurityException|java.net.URISyntaxException e) {
/*  546 */         throw new IOException("Could not convert URL to URI (" + e + "): " + url);
/*  547 */       } catch (FileSystemNotFoundException fileSystemNotFoundException) {}
/*      */     }
/*      */ 
/*      */     
/*  551 */     CloseableUrlConnection urlConn = new CloseableUrlConnection(url); 
/*  552 */     try { long contentLengthHint = -1L;
/*  553 */       urlConn.conn.setConnectTimeout(5000);
/*  554 */       urlConn.conn.connect();
/*  555 */       if (urlConn.httpConn != null)
/*      */       
/*  557 */       { if (urlConn.httpConn.getResponseCode() != 200) {
/*  558 */           throw new IOException("Got response code " + urlConn.httpConn
/*  559 */               .getResponseCode() + " for URL " + url);
/*      */         } }
/*  561 */       else if (url.getProtocol().equalsIgnoreCase("file"))
/*      */       
/*      */       { 
/*      */         
/*      */         try { 
/*      */           
/*  567 */           File file = Paths.get(url.toURI()).toFile();
/*  568 */           PhysicalZipFile physicalZipFile = new PhysicalZipFile(file, this, log);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  597 */           urlConn.close(); return physicalZipFile; } catch (Exception exception) {} }  contentLengthHint = urlConn.conn.getContentLengthLong(); if (contentLengthHint < -1L) contentLengthHint = -1L;  LogNode subLog = (log == null) ? null : log.log("Downloading jar from URL " + jarURL); try { InputStream inputStream = urlConn.conn.getInputStream(); try { PhysicalZipFile physicalZipFile = new PhysicalZipFile(inputStream, contentLengthHint, jarURL, this, subLog); if (subLog != null) { subLog.addElapsedTime(); subLog.log("***** Note that it is time-consuming to scan jars at non-\"file:\" URLs, the URL must be opened (possibly after an http(s) fetch) for every scan, and the same URL must also be separately opened by the ClassLoader *****"); }  PhysicalZipFile physicalZipFile1 = physicalZipFile; if (inputStream != null) inputStream.close();  urlConn.close(); return physicalZipFile1; } catch (Throwable throwable) { if (inputStream != null)
/*      */             try { inputStream.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }
/*      */          }
/*      */       catch (MalformedURLException e) { throw new IOException("Malformed URL: " + jarURL); }
/*      */        }
/*      */     catch (Throwable throwable) { try { urlConn.close(); }
/*      */       catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */        throw throwable; }
/*  605 */      } private static class CloseableUrlConnection implements AutoCloseable { public final URLConnection conn; public CloseableUrlConnection(URL url) throws IOException { this.conn = url.openConnection();
/*  606 */       this.httpConn = (this.conn instanceof HttpURLConnection) ? (HttpURLConnection)this.conn : null; }
/*      */     
/*      */     public final HttpURLConnection httpConn;
/*      */     
/*      */     public void close() {
/*  611 */       if (this.httpConn != null) {
/*  612 */         this.httpConn.disconnect();
/*      */       }
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class RecyclableInflater
/*      */     implements Resettable, AutoCloseable
/*      */   {
/*  627 */     private final Inflater inflater = new Inflater(true);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Inflater getInflater() {
/*  635 */       return this.inflater;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void reset() {
/*  643 */       this.inflater.reset();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void close() {
/*  649 */       this.inflater.end();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private RecyclableInflater() {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public InputStream openInflaterInputStream(final InputStream rawInputStream) throws IOException {
/*  663 */     return new InputStream()
/*      */       {
/*  665 */         private final NestedJarHandler.RecyclableInflater recyclableInflater = (NestedJarHandler.RecyclableInflater)NestedJarHandler.this.inflaterRecycler.acquire();
/*  666 */         private final Inflater inflater = this.recyclableInflater.getInflater();
/*  667 */         private final AtomicBoolean closed = new AtomicBoolean();
/*  668 */         private final byte[] buf = new byte[8192];
/*      */         
/*      */         private static final int INFLATE_BUF_SIZE = 8192;
/*      */         
/*      */         public int read() throws IOException {
/*  673 */           if (this.closed.get())
/*  674 */             throw new IOException("Already closed"); 
/*  675 */           if (this.inflater.finished()) {
/*  676 */             return -1;
/*      */           }
/*  678 */           int numDeflatedBytesRead = read(this.buf, 0, 1);
/*  679 */           if (numDeflatedBytesRead < 0) {
/*  680 */             return -1;
/*      */           }
/*  682 */           return this.buf[0] & 0xFF;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*      */         public int read(byte[] outBuf, int off, int len) throws IOException {
/*  688 */           if (this.closed.get())
/*  689 */             throw new IOException("Already closed"); 
/*  690 */           if (len < 0)
/*  691 */             throw new IllegalArgumentException("len cannot be negative"); 
/*  692 */           if (len == 0) {
/*  693 */             return 0;
/*      */           }
/*      */           
/*      */           try {
/*  697 */             int totInflatedBytes = 0;
/*  698 */             while (!this.inflater.finished() && totInflatedBytes < len) {
/*  699 */               int numInflatedBytes = this.inflater.inflate(outBuf, off + totInflatedBytes, len - totInflatedBytes);
/*      */               
/*  701 */               if (numInflatedBytes == 0) {
/*  702 */                 if (this.inflater.needsDictionary())
/*      */                 {
/*  704 */                   throw new IOException("Inflater needs preset dictionary"); } 
/*  705 */                 if (this.inflater.needsInput()) {
/*      */                   
/*  707 */                   int numRawBytesRead = rawInputStream.read(this.buf, 0, this.buf.length);
/*  708 */                   if (numRawBytesRead == -1) {
/*      */ 
/*      */ 
/*      */                     
/*  712 */                     this.buf[0] = 0;
/*  713 */                     this.inflater.setInput(this.buf, 0, 1);
/*      */                     continue;
/*      */                   } 
/*  716 */                   this.inflater.setInput(this.buf, 0, numRawBytesRead);
/*      */                 } 
/*      */                 continue;
/*      */               } 
/*  720 */               totInflatedBytes += numInflatedBytes;
/*      */             } 
/*      */             
/*  723 */             if (totInflatedBytes == 0)
/*      */             {
/*  725 */               return -1;
/*      */             }
/*  727 */             return totInflatedBytes;
/*      */           }
/*  729 */           catch (DataFormatException e) {
/*  730 */             throw new ZipException(
/*  731 */                 (e.getMessage() != null) ? e.getMessage() : "Invalid deflated zip entry data");
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/*      */         public long skip(long numToSkip) throws IOException {
/*  737 */           if (this.closed.get())
/*  738 */             throw new IOException("Already closed"); 
/*  739 */           if (numToSkip < 0L)
/*  740 */             throw new IllegalArgumentException("numToSkip cannot be negative"); 
/*  741 */           if (numToSkip == 0L)
/*  742 */             return 0L; 
/*  743 */           if (this.inflater.finished()) {
/*  744 */             return -1L;
/*      */           }
/*  746 */           long totBytesSkipped = 0L;
/*      */           while (true) {
/*  748 */             int readLen = (int)Math.min(numToSkip - totBytesSkipped, this.buf.length);
/*  749 */             int numBytesRead = read(this.buf, 0, readLen);
/*  750 */             if (numBytesRead > 0) {
/*  751 */               totBytesSkipped -= numBytesRead;
/*      */               continue;
/*      */             } 
/*      */             break;
/*      */           } 
/*  756 */           return totBytesSkipped;
/*      */         }
/*      */ 
/*      */         
/*      */         public int available() throws IOException {
/*  761 */           if (this.closed.get()) {
/*  762 */             throw new IOException("Already closed");
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  767 */           return this.inflater.finished() ? 0 : 1;
/*      */         }
/*      */ 
/*      */         
/*      */         public synchronized void mark(int readlimit) {
/*  772 */           throw new IllegalArgumentException("Not supported");
/*      */         }
/*      */ 
/*      */         
/*      */         public synchronized void reset() throws IOException {
/*  777 */           throw new IllegalArgumentException("Not supported");
/*      */         }
/*      */ 
/*      */         
/*      */         public boolean markSupported() {
/*  782 */           return false;
/*      */         }
/*      */ 
/*      */         
/*      */         public void close() {
/*  787 */           if (!this.closed.getAndSet(true)) {
/*      */             try {
/*  789 */               rawInputStream.close();
/*  790 */             } catch (Exception exception) {}
/*      */ 
/*      */ 
/*      */             
/*  794 */             NestedJarHandler.this.inflaterRecycler.recycle(this.recyclableInflater);
/*      */           } 
/*      */         }
/*      */       };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Slice readAllBytesWithSpilloverToDisk(InputStream inputStream, String tempFileBaseName, long inputStreamLengthHint, LogNode log) throws IOException {
/*  825 */     InputStream inptStream = inputStream; try {
/*  826 */       if (inputStreamLengthHint <= this.scanSpec.maxBufferedJarRAMSize)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  834 */         int bufSize = (inputStreamLengthHint == -1L) ? this.scanSpec.maxBufferedJarRAMSize : ((inputStreamLengthHint == 0L) ? 16384 : Math.min((int)inputStreamLengthHint, this.scanSpec.maxBufferedJarRAMSize));
/*  835 */         byte[] buf = new byte[bufSize];
/*  836 */         int bufLength = buf.length;
/*      */         
/*  838 */         int bufBytesUsed = 0;
/*  839 */         int bytesRead = 0;
/*  840 */         while ((bytesRead = inptStream.read(buf, bufBytesUsed, bufLength - bufBytesUsed)) > 0)
/*      */         {
/*  842 */           bufBytesUsed += bytesRead;
/*      */         }
/*  844 */         if (bytesRead == 0)
/*      */         
/*      */         { 
/*  847 */           byte[] overflowBuf = new byte[1];
/*  848 */           int overflowBufBytesUsed = inptStream.read(overflowBuf, 0, 1);
/*  849 */           if (overflowBufBytesUsed == 1)
/*      */           
/*      */           { 
/*  852 */             FileSlice fileSlice1 = spillToDisk(inptStream, tempFileBaseName, buf, overflowBuf, log);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  869 */             if (inptStream != null) inptStream.close();  return (Slice)fileSlice1; }  }  if (bufBytesUsed < buf.length) buf = Arrays.copyOf(buf, bufBytesUsed);  ArraySlice arraySlice = new ArraySlice(buf, false, 0L, this); if (inptStream != null) inptStream.close();  return (Slice)arraySlice; }  FileSlice fileSlice = spillToDisk(inptStream, tempFileBaseName, null, null, log); if (inptStream != null) inptStream.close();
/*      */       
/*      */       return (Slice)fileSlice;
/*      */     } catch (Throwable throwable) {
/*      */       if (inptStream != null) {
/*      */         try {
/*      */           inptStream.close();
/*      */         } catch (Throwable throwable1) {
/*      */           throwable.addSuppressed(throwable1);
/*      */         } 
/*      */       }
/*      */       throw throwable;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private FileSlice spillToDisk(InputStream inputStream, String tempFileBaseName, byte[] buf, byte[] overflowBuf, LogNode log) throws IOException {
/*      */     File tempFile;
/*      */     try {
/*  895 */       tempFile = makeTempFile(tempFileBaseName, true);
/*  896 */     } catch (IOException e) {
/*  897 */       throw new IOException("Could not create temporary file: " + e.getMessage());
/*      */     } 
/*  899 */     if (log != null) {
/*  900 */       log.log("Could not fit InputStream content into max RAM buffer size, saving to temporary file: " + tempFileBaseName + " -> " + tempFile);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  905 */     OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
/*      */     
/*  907 */     try { if (buf != null) {
/*  908 */         outputStream.write(buf);
/*  909 */         outputStream.write(overflowBuf);
/*      */       } 
/*      */       
/*  912 */       byte[] copyBuf = new byte[8192]; int bytesRead;
/*  913 */       while ((bytesRead = inputStream.read(copyBuf, 0, copyBuf.length)) > 0) {
/*  914 */         outputStream.write(copyBuf, 0, bytesRead);
/*      */       }
/*  916 */       outputStream.close(); } catch (Throwable throwable) { try { outputStream.close(); }
/*      */       catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }
/*      */        throw throwable; }
/*  919 */      return new FileSlice(tempFile, this, log);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] readAllBytesAsArray(InputStream inputStream, long uncompressedLengthHint) throws IOException {
/*  935 */     if (uncompressedLengthHint > 2147483639L) {
/*  936 */       throw new IOException("InputStream is too large to read");
/*      */     }
/*  938 */     InputStream inptStream = inputStream;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  944 */       int bufferSize = (uncompressedLengthHint < 1L) ? 16384 : Math.min((int)uncompressedLengthHint, 16777216);
/*  945 */       byte[] buf = new byte[bufferSize];
/*  946 */       int totBytesRead = 0; while (true) {
/*      */         int bytesRead;
/*  948 */         while ((bytesRead = inptStream.read(buf, totBytesRead, buf.length - totBytesRead)) > 0)
/*      */         {
/*  950 */           totBytesRead += bytesRead;
/*      */         }
/*  952 */         if (bytesRead < 0) {
/*      */           break;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  960 */         int extraByte = inptStream.read();
/*  961 */         if (extraByte == -1) {
/*      */           break;
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  968 */         if (buf.length == 2147483639) {
/*  969 */           throw new IOException("InputStream too large to read into array");
/*      */         }
/*  971 */         buf = Arrays.copyOf(buf, (int)Math.min(buf.length * 2L, 2147483639L));
/*  972 */         buf[totBytesRead++] = (byte)extraByte;
/*      */       } 
/*      */       
/*  975 */       byte[] arrayOfByte1 = (totBytesRead == buf.length) ? buf : Arrays.copyOf(buf, totBytesRead);
/*  976 */       if (inptStream != null) inptStream.close(); 
/*      */       return arrayOfByte1;
/*      */     } catch (Throwable throwable) {
/*      */       if (inptStream != null)
/*      */         try {
/*      */           inptStream.close();
/*      */         } catch (Throwable throwable1) {
/*      */           throwable.addSuppressed(throwable1);
/*      */         }  
/*      */       throw throwable;
/*      */     } 
/*      */   } public void close(LogNode log) {
/*  988 */     if (!this.closed.getAndSet(true)) {
/*  989 */       boolean interrupted = false;
/*  990 */       if (this.moduleRefToModuleReaderProxyRecyclerMap != null) {
/*  991 */         boolean completedWithoutInterruption = false;
/*  992 */         while (!completedWithoutInterruption) {
/*      */           
/*      */           try {
/*  995 */             for (Recycler<ModuleReaderProxy, IOException> recycler : (Iterable<Recycler<ModuleReaderProxy, IOException>>)this.moduleRefToModuleReaderProxyRecyclerMap.values()) {
/*  996 */               recycler.forceClose();
/*      */             }
/*  998 */             completedWithoutInterruption = true;
/*  999 */           } catch (InterruptedException e) {
/*      */             
/* 1001 */             interrupted = true;
/*      */           } 
/*      */         } 
/* 1004 */         this.moduleRefToModuleReaderProxyRecyclerMap.clear();
/* 1005 */         this.moduleRefToModuleReaderProxyRecyclerMap = null;
/*      */       } 
/* 1007 */       if (this.zipFileSliceToLogicalZipFileMap != null) {
/* 1008 */         this.zipFileSliceToLogicalZipFileMap.clear();
/* 1009 */         this.zipFileSliceToLogicalZipFileMap = null;
/*      */       } 
/* 1011 */       if (this.nestedPathToLogicalZipFileAndPackageRootMap != null) {
/* 1012 */         this.nestedPathToLogicalZipFileAndPackageRootMap.clear();
/* 1013 */         this.nestedPathToLogicalZipFileAndPackageRootMap = null;
/*      */       } 
/* 1015 */       if (this.canonicalFileToPhysicalZipFileMap != null) {
/* 1016 */         this.canonicalFileToPhysicalZipFileMap.clear();
/* 1017 */         this.canonicalFileToPhysicalZipFileMap = null;
/*      */       } 
/* 1019 */       if (this.fastZipEntryToZipFileSliceMap != null) {
/* 1020 */         this.fastZipEntryToZipFileSliceMap.clear();
/* 1021 */         this.fastZipEntryToZipFileSliceMap = null;
/*      */       } 
/* 1023 */       if (this.openSlices != null) {
/* 1024 */         while (!this.openSlices.isEmpty()) {
/* 1025 */           for (Slice slice : new ArrayList(this.openSlices)) {
/*      */             try {
/* 1027 */               slice.close();
/* 1028 */             } catch (IOException iOException) {}
/*      */ 
/*      */             
/* 1031 */             markSliceAsClosed(slice);
/*      */           } 
/*      */         } 
/* 1034 */         this.openSlices.clear();
/* 1035 */         this.openSlices = null;
/*      */       } 
/* 1037 */       if (this.inflaterRecycler != null) {
/* 1038 */         this.inflaterRecycler.forceClose();
/* 1039 */         this.inflaterRecycler = null;
/*      */       } 
/*      */       
/* 1042 */       if (this.tempFiles != null) {
/*      */         
/* 1044 */         LogNode rmLog = (this.tempFiles.isEmpty() || log == null) ? null : log.log("Removing temporary files");
/* 1045 */         while (!this.tempFiles.isEmpty()) {
/* 1046 */           for (File tempFile : new ArrayList(this.tempFiles)) {
/*      */             try {
/* 1048 */               removeTempFile(tempFile);
/* 1049 */             } catch (IOException|SecurityException e) {
/* 1050 */               if (rmLog != null) {
/* 1051 */                 rmLog.log("Removing temporary file failed: " + tempFile);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/* 1056 */         this.tempFiles = null;
/*      */       } 
/* 1058 */       if (interrupted) {
/* 1059 */         this.interruptionChecker.interrupt();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void runFinalizationMethod() {
/* 1068 */     if (runFinalizationMethod == null) {
/* 1069 */       runFinalizationMethod = this.reflectionUtils.staticMethodForNameOrNull("System", "runFinalization");
/*      */     }
/* 1071 */     if (runFinalizationMethod != null) {
/*      */       
/*      */       try {
/* 1074 */         runFinalizationMethod.invoke(null, new Object[0]);
/* 1075 */       } catch (Throwable throwable) {}
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void closeDirectByteBuffer(ByteBuffer backingByteBuffer) {
/* 1082 */     FileUtils.closeDirectByteBuffer(backingByteBuffer, this.reflectionUtils, null);
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fastzipfilereader\NestedJarHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */