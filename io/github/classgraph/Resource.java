/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.attribute.PosixFilePermission;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.fileslice.reader.ClassfileReader;
/*     */ import nonapi.io.github.classgraph.utils.LogNode;
/*     */ import nonapi.io.github.classgraph.utils.URLPathEncoder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Resource
/*     */   implements Closeable, Comparable<Resource>
/*     */ {
/*     */   private final ClasspathElement classpathElement;
/*     */   protected InputStream inputStream;
/*     */   protected ByteBuffer byteBuffer;
/*     */   protected long length;
/*     */   private String toString;
/*     */   LogNode scanLog;
/*     */   
/*     */   public Resource(ClasspathElement classpathElement, long length) {
/*  86 */     this.classpathElement = classpathElement;
/*  87 */     this.length = length;
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
/*     */   private static URL uriToURL(URI uri) {
/*     */     try {
/* 103 */       return uri.toURL();
/* 104 */     } catch (IllegalArgumentException|java.net.MalformedURLException e) {
/* 105 */       if (uri.getScheme().equals("jrt"))
/*     */       {
/* 107 */         throw new IllegalArgumentException("Could not create URL from URI with \"jrt:\" scheme (\"jrt:\" is not supported by the URL class without a custom URL protocol handler): " + uri);
/*     */       }
/*     */ 
/*     */       
/* 111 */       throw new IllegalArgumentException("Could not create URL from URI: " + uri + " -- " + e);
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
/*     */   public URI getURI() {
/* 124 */     URI locationURI = getClasspathElementURI();
/* 125 */     String locationURIStr = locationURI.toString();
/* 126 */     String resourcePath = getPathRelativeToClasspathElement();
/*     */     
/* 128 */     boolean isDir = locationURIStr.endsWith("/");
/*     */     try {
/* 130 */       return new URI(((
/* 131 */           isDir || locationURIStr.startsWith("jar:") || locationURIStr.startsWith("jrt:")) ? "" : "jar:") + locationURIStr + (
/* 132 */           isDir ? "" : (locationURIStr.startsWith("jrt:") ? "/" : "!/")) + 
/* 133 */           URLPathEncoder.encodePath(resourcePath));
/* 134 */     } catch (URISyntaxException e) {
/* 135 */       throw new IllegalArgumentException("Could not form URL for classpath element: " + locationURIStr + " ; path: " + resourcePath + " : " + e);
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
/*     */   public URL getURL() {
/* 152 */     return uriToURL(getURI());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URI getClasspathElementURI() {
/* 163 */     return this.classpathElement.getURI();
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
/*     */   public URL getClasspathElementURL() {
/* 178 */     return uriToURL(getClasspathElementURI());
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
/*     */   public File getClasspathElementFile() {
/* 191 */     return this.classpathElement.getFile();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModuleRef getModuleRef() {
/* 201 */     return (this.classpathElement instanceof ClasspathElementModule) ? 
/* 202 */       ((ClasspathElementModule)this.classpathElement).moduleRef : 
/* 203 */       null;
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
/*     */   public String getContentAsString() throws IOException {
/* 215 */     String content = new String(load(), StandardCharsets.UTF_8);
/* 216 */     close();
/* 217 */     return content;
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
/*     */   public String getPathRelativeToClasspathElement() {
/* 242 */     return getPath();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CloseableByteBuffer readCloseable() throws IOException {
/* 282 */     return new CloseableByteBuffer(read(), new Runnable()
/*     */         {
/*     */           public void run() {
/* 285 */             Resource.this.close();
/*     */           }
/*     */         });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLength() {
/* 319 */     return this.length;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 358 */     if (this.toString != null) {
/* 359 */       return this.toString;
/*     */     }
/* 361 */     return this.toString = getURI().toString();
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
/*     */   public int hashCode() {
/* 375 */     return toString().hashCode();
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
/*     */   public boolean equals(Object obj) {
/* 390 */     if (obj == this)
/* 391 */       return true; 
/* 392 */     if (!(obj instanceof Resource)) {
/* 393 */       return false;
/*     */     }
/* 395 */     return toString().equals(obj.toString());
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
/*     */   public int compareTo(Resource o) {
/* 410 */     return toString().compareTo(o.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 419 */     if (this.inputStream != null) {
/*     */       try {
/* 421 */         this.inputStream.close();
/* 422 */       } catch (IOException iOException) {}
/*     */ 
/*     */       
/* 425 */       this.inputStream = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract String getPath();
/*     */   
/*     */   public abstract InputStream open() throws IOException;
/*     */   
/*     */   public abstract ByteBuffer read() throws IOException;
/*     */   
/*     */   public abstract byte[] load() throws IOException;
/*     */   
/*     */   abstract ClassfileReader openClassfile() throws IOException;
/*     */   
/*     */   public abstract long getLastModified();
/*     */   
/*     */   public abstract Set<PosixFilePermission> getPosixFilePermissions();
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\Resource.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */