/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ import nonapi.io.github.classgraph.utils.CollectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModuleRef
/*     */   implements Comparable<ModuleRef>
/*     */ {
/*     */   private final String name;
/*     */   private final Object reference;
/*     */   private final Object layer;
/*     */   private final Object descriptor;
/*     */   private final List<String> packages;
/*     */   private final URI location;
/*     */   private String locationStr;
/*     */   private File locationFile;
/*     */   private String rawVersion;
/*     */   private final ClassLoader classLoader;
/*     */   ReflectionUtils reflectionUtils;
/*     */   
/*     */   public ModuleRef(Object moduleReference, Object moduleLayer, ReflectionUtils reflectionUtils) {
/*  87 */     if (moduleReference == null) {
/*  88 */       throw new IllegalArgumentException("moduleReference cannot be null");
/*     */     }
/*  90 */     if (moduleLayer == null) {
/*  91 */       throw new IllegalArgumentException("moduleLayer cannot be null");
/*     */     }
/*  93 */     this.reference = moduleReference;
/*  94 */     this.layer = moduleLayer;
/*  95 */     this.reflectionUtils = reflectionUtils;
/*     */     
/*  97 */     this.descriptor = reflectionUtils.invokeMethod(true, moduleReference, "descriptor");
/*  98 */     if (this.descriptor == null)
/*     */     {
/* 100 */       throw new IllegalArgumentException("moduleReference.descriptor() should not return null");
/*     */     }
/* 102 */     this.name = (String)reflectionUtils.invokeMethod(true, this.descriptor, "name");
/*     */     
/* 104 */     Set<String> modulePackages = (Set<String>)reflectionUtils.invokeMethod(true, this.descriptor, "packages");
/*     */     
/* 106 */     if (modulePackages == null)
/*     */     {
/* 108 */       throw new IllegalArgumentException("moduleReference.descriptor().packages() should not return null");
/*     */     }
/* 110 */     this.packages = new ArrayList<>(modulePackages);
/* 111 */     CollectionUtils.sortIfNotEmpty(this.packages);
/* 112 */     Object optionalRawVersion = reflectionUtils.invokeMethod(true, this.descriptor, "rawVersion");
/*     */     
/* 114 */     if (optionalRawVersion != null) {
/* 115 */       Boolean isPresent = (Boolean)reflectionUtils.invokeMethod(true, optionalRawVersion, "isPresent");
/*     */       
/* 117 */       if (isPresent != null && isPresent.booleanValue()) {
/* 118 */         this.rawVersion = (String)reflectionUtils.invokeMethod(true, optionalRawVersion, "get");
/*     */       }
/*     */     } 
/*     */     
/* 122 */     Object moduleLocationOptional = reflectionUtils.invokeMethod(true, moduleReference, "location");
/*     */     
/* 124 */     if (moduleLocationOptional == null)
/*     */     {
/* 126 */       throw new IllegalArgumentException("moduleReference.location() should not return null");
/*     */     }
/* 128 */     Object moduleLocationIsPresent = reflectionUtils.invokeMethod(true, moduleLocationOptional, "isPresent");
/*     */     
/* 130 */     if (moduleLocationIsPresent == null)
/*     */     {
/* 132 */       throw new IllegalArgumentException("moduleReference.location().isPresent() should not return null");
/*     */     }
/* 134 */     if (((Boolean)moduleLocationIsPresent).booleanValue()) {
/* 135 */       this.location = (URI)reflectionUtils.invokeMethod(true, moduleLocationOptional, "get");
/*     */       
/* 137 */       if (this.location == null)
/*     */       {
/* 139 */         throw new IllegalArgumentException("moduleReference.location().get() should not return null");
/*     */       }
/*     */     } else {
/* 142 */       this.location = null;
/*     */     } 
/*     */ 
/*     */     
/* 146 */     this.classLoader = (ClassLoader)reflectionUtils.invokeMethod(true, moduleLayer, "findLoader", String.class, this.name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 156 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getReference() {
/* 165 */     return this.reference;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getLayer() {
/* 174 */     return this.layer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getDescriptor() {
/* 183 */     return this.descriptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getPackages() {
/* 192 */     return this.packages;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URI getLocation() {
/* 203 */     return this.location;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocationStr() {
/* 214 */     if (this.locationStr == null && this.location != null) {
/* 215 */       this.locationStr = this.location.toString();
/*     */     }
/* 217 */     return this.locationStr;
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
/*     */   public File getLocationFile() {
/* 229 */     if (this.locationFile == null && this.location != null && "file".equals(this.location.getScheme())) {
/* 230 */       this.locationFile = new File(this.location);
/*     */     }
/* 232 */     return this.locationFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRawVersion() {
/* 241 */     return this.rawVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSystemModule() {
/* 250 */     if (this.name == null || this.name.isEmpty()) {
/* 251 */       return false;
/*     */     }
/* 253 */     return (this.name.startsWith("java.") || this.name.startsWith("jdk.") || this.name.startsWith("javafx.") || this.name
/* 254 */       .startsWith("oracle."));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassLoader getClassLoader() {
/* 264 */     return this.classLoader;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 272 */     if (obj == this)
/* 273 */       return true; 
/* 274 */     if (!(obj instanceof ModuleRef)) {
/* 275 */       return false;
/*     */     }
/* 277 */     ModuleRef modRef = (ModuleRef)obj;
/* 278 */     return (modRef.reference.equals(this.reference) && modRef.layer.equals(this.layer));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 286 */     return this.reference.hashCode() * this.layer.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 294 */     return this.reference.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(ModuleRef o) {
/* 302 */     int diff = this.name.compareTo(o.name);
/* 303 */     return (diff != 0) ? diff : (hashCode() - o.hashCode());
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
/*     */   public ModuleReaderProxy open() throws IOException {
/* 316 */     return new ModuleReaderProxy(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ModuleRef.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */