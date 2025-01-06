/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.security.ProtectionDomain;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ import nonapi.io.github.classgraph.utils.JarUtils;
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
/*     */ public class ClassGraphClassLoader
/*     */   extends ClassLoader
/*     */ {
/*     */   private final ScanResult scanResult;
/*     */   private final boolean initializeLoadedClasses;
/*     */   private Set<ClassLoader> environmentClassLoaderDelegationOrder;
/*     */   private List<ClassLoader> overrideClassLoaders;
/*     */   private final ClassLoader classpathClassLoader;
/*     */   private Set<ClassLoader> addedClassLoaderDelegationOrder;
/*     */   
/*     */   ClassGraphClassLoader(ScanResult scanResult) {
/*  76 */     super(null);
/*  77 */     registerAsParallelCapable();
/*     */     
/*  79 */     this.scanResult = scanResult;
/*  80 */     ScanSpec scanSpec = scanResult.scanSpec;
/*  81 */     this.initializeLoadedClasses = scanSpec.initializeLoadedClasses;
/*     */ 
/*     */     
/*  84 */     boolean classpathOverridden = (scanSpec.overrideClasspath != null && !scanSpec.overrideClasspath.isEmpty());
/*     */     
/*  86 */     boolean classloadersOverridden = (scanSpec.overrideClassLoaders != null && !scanSpec.overrideClassLoaders.isEmpty());
/*     */     
/*  88 */     boolean clasloadersAdded = (scanSpec.addedClassLoaders != null && !scanSpec.addedClassLoaders.isEmpty());
/*     */ 
/*     */     
/*  91 */     if (!classpathOverridden && !classloadersOverridden) {
/*     */ 
/*     */       
/*  94 */       this.environmentClassLoaderDelegationOrder = new LinkedHashSet<>();
/*  95 */       this.environmentClassLoaderDelegationOrder.add(null);
/*     */ 
/*     */       
/*  98 */       ClassLoader[] envClassLoaderOrder = scanResult.getClassLoaderOrderRespectingParentDelegation();
/*  99 */       if (envClassLoaderOrder != null)
/*     */       {
/* 101 */         this.environmentClassLoaderDelegationOrder.addAll(Arrays.asList(envClassLoaderOrder));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 106 */     List<URL> classpathURLs = scanResult.getClasspathURLs();
/* 107 */     this
/* 108 */       .classpathClassLoader = classpathURLs.isEmpty() ? null : new URLClassLoader(classpathURLs.<URL>toArray(new URL[0]));
/*     */ 
/*     */ 
/*     */     
/* 112 */     this.overrideClassLoaders = classloadersOverridden ? scanSpec.overrideClassLoaders : null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     if (this.overrideClassLoaders == null && classpathOverridden && this.classpathClassLoader != null) {
/* 120 */       this.overrideClassLoaders = Collections.singletonList(this.classpathClassLoader);
/*     */     }
/*     */ 
/*     */     
/* 124 */     if (clasloadersAdded) {
/* 125 */       this.addedClassLoaderDelegationOrder = new LinkedHashSet<>();
/* 126 */       this.addedClassLoaderDelegationOrder.addAll(scanSpec.addedClassLoaders);
/*     */       
/* 128 */       if (this.environmentClassLoaderDelegationOrder != null) {
/* 129 */         this.addedClassLoaderDelegationOrder.removeAll(this.environmentClassLoaderDelegationOrder);
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
/*     */   protected Class<?> findClass(String className) throws ClassNotFoundException, LinkageError, SecurityException {
/* 142 */     ClassGraphClassLoader delegateClassGraphClassLoader = this.scanResult.classpathFinder.getDelegateClassGraphClassLoader();
/* 143 */     LinkageError linkageError = null;
/* 144 */     if (delegateClassGraphClassLoader != null) {
/*     */       try {
/* 146 */         return Class.forName(className, this.initializeLoadedClasses, delegateClassGraphClassLoader);
/* 147 */       } catch (ClassNotFoundException classNotFoundException) {
/*     */       
/* 149 */       } catch (LinkageError e) {
/* 150 */         linkageError = e;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 155 */     if (this.overrideClassLoaders != null) {
/* 156 */       for (ClassLoader overrideClassLoader : this.overrideClassLoaders) {
/*     */         try {
/* 158 */           return Class.forName(className, this.initializeLoadedClasses, overrideClassLoader);
/* 159 */         } catch (ClassNotFoundException classNotFoundException) {
/*     */         
/* 161 */         } catch (LinkageError e) {
/* 162 */           if (linkageError == null) {
/* 163 */             linkageError = e;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 170 */     if (this.overrideClassLoaders == null && this.environmentClassLoaderDelegationOrder != null && 
/* 171 */       !this.environmentClassLoaderDelegationOrder.isEmpty()) {
/* 172 */       for (ClassLoader envClassLoader : this.environmentClassLoaderDelegationOrder) {
/*     */         try {
/* 174 */           return Class.forName(className, this.initializeLoadedClasses, envClassLoader);
/* 175 */         } catch (ClassNotFoundException classNotFoundException) {
/*     */         
/* 177 */         } catch (LinkageError e) {
/* 178 */           if (linkageError == null) {
/* 179 */             linkageError = e;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 189 */     ClassLoader classInfoClassLoader = null;
/*     */     
/* 191 */     ClassInfo classInfo = (this.scanResult.classNameToClassInfo == null) ? null : this.scanResult.classNameToClassInfo.get(className);
/* 192 */     if (classInfo != null) {
/* 193 */       classInfoClassLoader = classInfo.classLoader;
/*     */ 
/*     */       
/* 196 */       if (classInfoClassLoader != null && (this.environmentClassLoaderDelegationOrder == null || 
/* 197 */         !this.environmentClassLoaderDelegationOrder.contains(classInfoClassLoader))) {
/*     */         try {
/* 199 */           return Class.forName(className, this.initializeLoadedClasses, classInfoClassLoader);
/* 200 */         } catch (ClassNotFoundException classNotFoundException) {
/*     */         
/* 202 */         } catch (LinkageError e) {
/* 203 */           if (linkageError == null) {
/* 204 */             linkageError = e;
/*     */           }
/*     */         } 
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 214 */       if (classInfo.classpathElement instanceof ClasspathElementModule && !classInfo.isPublic()) {
/* 215 */         throw new ClassNotFoundException("Classfile for class " + className + " was found in a module, but the context and system classloaders could not load the class, probably because the class is not public.");
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 222 */     if (this.overrideClassLoaders == null && this.classpathClassLoader != null) {
/*     */       try {
/* 224 */         return Class.forName(className, this.initializeLoadedClasses, this.classpathClassLoader);
/* 225 */       } catch (ClassNotFoundException classNotFoundException) {
/*     */       
/* 227 */       } catch (LinkageError e) {
/* 228 */         if (linkageError == null) {
/* 229 */           linkageError = e;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 235 */     if (this.addedClassLoaderDelegationOrder != null && !this.addedClassLoaderDelegationOrder.isEmpty()) {
/* 236 */       for (ClassLoader additionalClassLoader : this.addedClassLoaderDelegationOrder) {
/* 237 */         if (additionalClassLoader != classInfoClassLoader) {
/*     */           try {
/* 239 */             return Class.forName(className, this.initializeLoadedClasses, additionalClassLoader);
/* 240 */           } catch (ClassNotFoundException classNotFoundException) {
/*     */           
/* 242 */           } catch (LinkageError e) {
/* 243 */             if (linkageError == null) {
/* 244 */               linkageError = e;
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     ResourceList classfileResources = this.scanResult.getResourcesWithPath(JarUtils.classNameToClassfilePath(className));
/* 262 */     if (classfileResources != null) {
/* 263 */       for (Resource resource : classfileResources) {
/*     */ 
/*     */         
/* 266 */         try { Resource resourceToClose = resource;
/*     */ 
/*     */ 
/*     */           
/* 270 */           try { Class<?> clazz = defineClass(className, resourceToClose.read(), (ProtectionDomain)null);
/* 271 */             if (resourceToClose != null) resourceToClose.close();  return clazz; } catch (Throwable throwable) { if (resourceToClose != null) try { resourceToClose.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/* 272 */         { throw new ClassNotFoundException("Could not load classfile for class " + className + " : " + e); }
/* 273 */         catch (LinkageError e)
/* 274 */         { if (linkageError == null) {
/* 275 */             linkageError = e;
/*     */           } }
/*     */       
/*     */       } 
/*     */     }
/*     */     
/* 281 */     if (linkageError != null) {
/* 282 */       if (VersionFinder.OS == VersionFinder.OperatingSystem.Windows) {
/*     */ 
/*     */ 
/*     */         
/* 286 */         String msg = linkageError.getMessage();
/* 287 */         if (msg != null) {
/* 288 */           String wrongName = "(wrong name: ";
/* 289 */           int wrongNameIdx = msg.indexOf("(wrong name: ");
/* 290 */           if (wrongNameIdx > -1) {
/* 291 */             String theWrongName = msg.substring(wrongNameIdx + "(wrong name: ".length(), msg
/* 292 */                 .length() - 1);
/* 293 */             if (theWrongName.replace('/', '.').equalsIgnoreCase(className)) {
/* 294 */               throw new LinkageError("You appear to have two classfiles with the same case-insensitive name in the same directory on a case-insensitive filesystem -- this is not allowed on Windows, and therefore your code is not portable. Class name: " + className, linkageError);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 302 */       throw linkageError;
/*     */     } 
/*     */     
/* 305 */     throw new ClassNotFoundException("Could not find or load classfile for class " + className);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL[] getURLs() {
/* 314 */     return this.scanResult.getClasspathURLs().<URL>toArray(new URL[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getResource(String path) {
/* 325 */     if (!this.environmentClassLoaderDelegationOrder.isEmpty()) {
/* 326 */       for (ClassLoader envClassLoader : this.environmentClassLoaderDelegationOrder) {
/* 327 */         URL resource = envClassLoader.getResource(path);
/* 328 */         if (resource != null) {
/* 329 */           return resource;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 335 */     if (!this.addedClassLoaderDelegationOrder.isEmpty()) {
/* 336 */       for (ClassLoader additionalClassLoader : this.addedClassLoaderDelegationOrder) {
/* 337 */         URL resource = additionalClassLoader.getResource(path);
/* 338 */         if (resource != null) {
/* 339 */           return resource;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 346 */     ResourceList resourceList = this.scanResult.getResourcesWithPath(path);
/* 347 */     if (resourceList == null || resourceList.isEmpty()) {
/* 348 */       return super.getResource(path);
/*     */     }
/* 350 */     return resourceList.get(0).getURL();
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
/*     */   public Enumeration<URL> getResources(String path) throws IOException {
/* 362 */     if (!this.environmentClassLoaderDelegationOrder.isEmpty()) {
/* 363 */       for (ClassLoader envClassLoader : this.environmentClassLoaderDelegationOrder) {
/* 364 */         Enumeration<URL> resources = envClassLoader.getResources(path);
/* 365 */         if (resources != null && resources.hasMoreElements()) {
/* 366 */           return resources;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 372 */     if (!this.addedClassLoaderDelegationOrder.isEmpty()) {
/* 373 */       for (ClassLoader additionalClassLoader : this.addedClassLoaderDelegationOrder) {
/* 374 */         Enumeration<URL> resources = additionalClassLoader.getResources(path);
/* 375 */         if (resources != null && resources.hasMoreElements()) {
/* 376 */           return resources;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 383 */     final ResourceList resourceList = this.scanResult.getResourcesWithPath(path);
/* 384 */     if (resourceList == null || resourceList.isEmpty()) {
/* 385 */       return Collections.emptyEnumeration();
/*     */     }
/* 387 */     return new Enumeration<URL>()
/*     */       {
/*     */         int idx;
/*     */ 
/*     */         
/*     */         public boolean hasMoreElements() {
/* 393 */           return (this.idx < resourceList.size());
/*     */         }
/*     */ 
/*     */         
/*     */         public URL nextElement() {
/* 398 */           return resourceList.get(this.idx++).getURL();
/*     */         }
/*     */       };
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
/*     */   public InputStream getResourceAsStream(String path) {
/* 412 */     if (!this.environmentClassLoaderDelegationOrder.isEmpty()) {
/* 413 */       for (ClassLoader envClassLoader : this.environmentClassLoaderDelegationOrder) {
/* 414 */         InputStream inputStream = envClassLoader.getResourceAsStream(path);
/* 415 */         if (inputStream != null) {
/* 416 */           return inputStream;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 422 */     if (!this.addedClassLoaderDelegationOrder.isEmpty()) {
/* 423 */       for (ClassLoader additionalClassLoader : this.addedClassLoaderDelegationOrder) {
/* 424 */         InputStream inputStream = additionalClassLoader.getResourceAsStream(path);
/* 425 */         if (inputStream != null) {
/* 426 */           return inputStream;
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 433 */     ResourceList resourceList = this.scanResult.getResourcesWithPath(path);
/* 434 */     if (resourceList == null || resourceList.isEmpty()) {
/* 435 */       return super.getResourceAsStream(path);
/*     */     }
/*     */     try {
/* 438 */       return resourceList.get(0).open();
/* 439 */     } catch (IOException e) {
/* 440 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ClassGraphClassLoader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */