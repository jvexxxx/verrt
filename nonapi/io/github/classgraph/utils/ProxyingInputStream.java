/*     */ package nonapi.io.github.classgraph.utils;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.Method;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ProxyingInputStream
/*     */   extends InputStream
/*     */ {
/*     */   private InputStream inputStream;
/*     */   private static Method readAllBytes;
/*     */   private static Method readNBytes1;
/*     */   private static Method readNBytes3;
/*     */   private static Method skipNBytes;
/*     */   private static Method transferTo;
/*     */   
/*     */   static {
/*     */     try {
/*  53 */       readAllBytes = InputStream.class.getDeclaredMethod("readAllBytes", new Class[0]);
/*  54 */     } catch (NoSuchMethodException|SecurityException noSuchMethodException) {}
/*     */ 
/*     */     
/*     */     try {
/*  58 */       readNBytes1 = InputStream.class.getDeclaredMethod("readNBytes", new Class[] { int.class });
/*  59 */     } catch (NoSuchMethodException|SecurityException noSuchMethodException) {}
/*     */ 
/*     */     
/*     */     try {
/*  63 */       readNBytes3 = InputStream.class.getDeclaredMethod("readNBytes", new Class[] { byte[].class, int.class, int.class });
/*  64 */     } catch (NoSuchMethodException|SecurityException noSuchMethodException) {}
/*     */ 
/*     */     
/*     */     try {
/*  68 */       skipNBytes = InputStream.class.getDeclaredMethod("skipNBytes", new Class[] { long.class });
/*  69 */     } catch (NoSuchMethodException|SecurityException noSuchMethodException) {}
/*     */ 
/*     */     
/*     */     try {
/*  73 */       transferTo = InputStream.class.getDeclaredMethod("transferTo", new Class[] { OutputStream.class });
/*  74 */     } catch (NoSuchMethodException|SecurityException noSuchMethodException) {}
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
/*     */   public ProxyingInputStream(InputStream inputStream) {
/*  87 */     this.inputStream = inputStream;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/*  92 */     return this.inputStream.read();
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/*  97 */     return this.inputStream.read(b);
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 102 */     return this.inputStream.read(b, off, len);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] readAllBytes() throws IOException {
/* 107 */     if (readAllBytes == null) {
/* 108 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     try {
/* 111 */       return (byte[])readAllBytes.invoke(this.inputStream, new Object[0]);
/* 112 */     } catch (Exception e) {
/* 113 */       throw new IOException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] readNBytes(int len) throws IOException {
/* 119 */     if (readNBytes1 == null) {
/* 120 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     try {
/* 123 */       return (byte[])readNBytes1.invoke(this.inputStream, new Object[] { Integer.valueOf(len) });
/* 124 */     } catch (Exception e) {
/* 125 */       throw new IOException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int readNBytes(byte[] b, int off, int len) throws IOException {
/* 131 */     if (readNBytes3 == null) {
/* 132 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     try {
/* 135 */       return ((Integer)readNBytes3.invoke(this.inputStream, new Object[] { b, Integer.valueOf(off), Integer.valueOf(len) })).intValue();
/* 136 */     } catch (Exception e) {
/* 137 */       throw new IOException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int available() throws IOException {
/* 143 */     return this.inputStream.available();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean markSupported() {
/* 148 */     return this.inputStream.markSupported();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void mark(int readlimit) {
/* 153 */     this.inputStream.mark(readlimit);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void reset() throws IOException {
/* 158 */     this.inputStream.reset();
/*     */   }
/*     */ 
/*     */   
/*     */   public long skip(long n) throws IOException {
/* 163 */     return this.inputStream.skip(n);
/*     */   }
/*     */ 
/*     */   
/*     */   public void skipNBytes(long n) throws IOException {
/* 168 */     if (skipNBytes == null) {
/* 169 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     try {
/* 172 */       skipNBytes.invoke(this.inputStream, new Object[] { Long.valueOf(n) });
/* 173 */     } catch (Exception e) {
/* 174 */       throw new IOException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public long transferTo(OutputStream out) throws IOException {
/* 180 */     if (transferTo == null) {
/* 181 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     try {
/* 184 */       return ((Long)transferTo.invoke(this.inputStream, new Object[] { out })).longValue();
/* 185 */     } catch (Exception e) {
/* 186 */       throw new IOException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 192 */     return this.inputStream.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 197 */     if (this.inputStream != null)
/*     */       try {
/* 199 */         this.inputStream.close();
/*     */       } finally {
/* 201 */         this.inputStream = null;
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgrap\\utils\ProxyingInputStream.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */