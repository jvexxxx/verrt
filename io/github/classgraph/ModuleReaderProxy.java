/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.List;
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
/*     */ public class ModuleReaderProxy
/*     */   implements Closeable
/*     */ {
/*     */   private final AutoCloseable moduleReader;
/*     */   private static Class<?> collectorClass;
/*     */   private static Object collectorsToList;
/*     */   private ReflectionUtils reflectionUtils;
/*     */   
/*     */   ModuleReaderProxy(ModuleRef moduleRef) throws IOException {
/*     */     try {
/*  63 */       this.reflectionUtils = moduleRef.reflectionUtils;
/*  64 */       if (collectorClass == null || collectorsToList == null) {
/*  65 */         collectorClass = this.reflectionUtils.classForNameOrNull("java.util.stream.Collector");
/*  66 */         Class<?> collectorsClass = this.reflectionUtils.classForNameOrNull("java.util.stream.Collectors");
/*  67 */         if (collectorsClass != null) {
/*  68 */           collectorsToList = this.reflectionUtils.invokeStaticMethod(true, collectorsClass, "toList");
/*     */         }
/*     */       } 
/*     */       
/*  72 */       this.moduleReader = (AutoCloseable)this.reflectionUtils.invokeMethod(true, moduleRef
/*  73 */           .getReference(), "open");
/*  74 */       if (this.moduleReader == null) {
/*  75 */         throw new IllegalArgumentException("moduleReference.open() should not return null");
/*     */       }
/*  77 */     } catch (SecurityException e) {
/*  78 */       throw new IOException("Could not open module " + moduleRef.getName(), e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  86 */       this.moduleReader.close();
/*  87 */     } catch (Exception exception) {}
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
/*     */   public List<String> list() throws SecurityException {
/* 107 */     if (collectorsToList == null) {
/* 108 */       throw new IllegalArgumentException("Could not call Collectors.toList()");
/*     */     }
/*     */     
/* 111 */     Object resourcesStream = this.reflectionUtils.invokeMethod(true, this.moduleReader, "list");
/* 112 */     if (resourcesStream == null) {
/* 113 */       throw new IllegalArgumentException("Could not call moduleReader.list()");
/*     */     }
/* 115 */     Object resourcesList = this.reflectionUtils.invokeMethod(true, resourcesStream, "collect", collectorClass, collectorsToList);
/*     */     
/* 117 */     if (resourcesList == null) {
/* 118 */       throw new IllegalArgumentException("Could not call moduleReader.list().collect(Collectors.toList())");
/*     */     }
/*     */     
/* 121 */     List<String> resourcesListTyped = (List<String>)resourcesList;
/* 122 */     return resourcesListTyped;
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
/*     */   public InputStream open(String path) throws SecurityException {
/* 139 */     Object optionalInputStream = this.reflectionUtils.invokeMethod(true, this.moduleReader, "open", String.class, path);
/* 140 */     if (optionalInputStream == null) {
/* 141 */       throw new IllegalArgumentException("Got null result from ModuleReader#open for path " + path);
/*     */     }
/* 143 */     InputStream inputStream = (InputStream)this.reflectionUtils.invokeMethod(true, optionalInputStream, "get");
/*     */     
/* 145 */     if (inputStream == null) {
/* 146 */       throw new IllegalArgumentException("Got null result from ModuleReader#open(String)#get()");
/*     */     }
/* 148 */     return inputStream;
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
/*     */   public ByteBuffer read(String path) throws SecurityException, OutOfMemoryError {
/* 165 */     Object optionalByteBuffer = this.reflectionUtils.invokeMethod(true, this.moduleReader, "read", String.class, path);
/* 166 */     if (optionalByteBuffer == null) {
/* 167 */       throw new IllegalArgumentException("Got null result from ModuleReader#read(String)");
/*     */     }
/* 169 */     ByteBuffer byteBuffer = (ByteBuffer)this.reflectionUtils.invokeMethod(true, optionalByteBuffer, "get");
/*     */     
/* 171 */     if (byteBuffer == null) {
/* 172 */       throw new IllegalArgumentException("Got null result from ModuleReader#read(String).get()");
/*     */     }
/* 174 */     return byteBuffer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void release(ByteBuffer byteBuffer) {
/* 184 */     this.reflectionUtils.invokeMethod(true, this.moduleReader, "release", ByteBuffer.class, byteBuffer);
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
/*     */   public URI find(String path) {
/* 198 */     Object optionalURI = this.reflectionUtils.invokeMethod(true, this.moduleReader, "find", String.class, path);
/*     */     
/* 200 */     if (optionalURI == null) {
/* 201 */       throw new IllegalArgumentException("Got null result from ModuleReader#find(String)");
/*     */     }
/* 203 */     URI uri = (URI)this.reflectionUtils.invokeMethod(true, optionalURI, "get");
/* 204 */     if (uri == null) {
/* 205 */       throw new IllegalArgumentException("Got null result from ModuleReader#find(String).get()");
/*     */     }
/* 207 */     return uri;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ModuleReaderProxy.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */