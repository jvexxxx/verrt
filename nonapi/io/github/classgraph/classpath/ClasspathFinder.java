/*     */ package nonapi.io.github.classgraph.classpath;
/*     */ 
/*     */ import io.github.classgraph.ClassGraphClassLoader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ import nonapi.io.github.classgraph.utils.FastPathResolver;
/*     */ import nonapi.io.github.classgraph.utils.FileUtils;
/*     */ import nonapi.io.github.classgraph.utils.JarUtils;
/*     */ import nonapi.io.github.classgraph.utils.LogNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClasspathFinder
/*     */ {
/*     */   private final ClasspathOrder classpathOrder;
/*     */   private final ModuleFinder moduleFinder;
/*     */   private ClassLoader[] classLoaderOrderRespectingParentDelegation;
/*     */   private ClassGraphClassLoader delegateClassGraphClassLoader;
/*     */   
/*     */   public ClasspathOrder getClasspathOrder() {
/*  75 */     return this.classpathOrder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ModuleFinder getModuleFinder() {
/*  84 */     return this.moduleFinder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassLoader[] getClassLoaderOrderRespectingParentDelegation() {
/*  93 */     return this.classLoaderOrderRespectingParentDelegation;
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
/*     */   public ClassGraphClassLoader getDelegateClassGraphClassLoader() {
/* 105 */     return this.delegateClassGraphClassLoader;
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
/*     */   public ClasspathFinder(ScanSpec scanSpec, ReflectionUtils reflectionUtils, LogNode log) {
/*     */     boolean scanNonSystemModules;
/* 119 */     LogNode classpathFinderLog = (log == null) ? null : log.log("Finding classpath and modules");
/*     */ 
/*     */     
/* 122 */     boolean forceScanJavaClassPath = false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     if (scanSpec.overrideClasspath != null) {
/*     */       
/* 129 */       scanNonSystemModules = false;
/* 130 */     } else if (scanSpec.overrideClassLoaders != null) {
/*     */ 
/*     */       
/* 133 */       scanNonSystemModules = false;
/* 134 */       for (ClassLoader classLoader : scanSpec.overrideClassLoaders) {
/* 135 */         String classLoaderClassName = classLoader.getClass().getName();
/*     */ 
/*     */ 
/*     */         
/* 139 */         if (classLoaderClassName.equals("jdk.internal.loader.ClassLoaders$AppClassLoader") || classLoaderClassName
/* 140 */           .equals("jdk.internal.loader.ClassLoaders$PlatformClassLoader")) {
/* 141 */           if (!scanSpec.enableSystemJarsAndModules) {
/* 142 */             if (classpathFinderLog != null) {
/* 143 */               classpathFinderLog.log("overrideClassLoaders() was called with an instance of " + classLoaderClassName + ", which is a system classloader, so enableSystemJarsAndModules() was called automatically");
/*     */             }
/*     */ 
/*     */             
/* 147 */             scanSpec.enableSystemJarsAndModules = true;
/*     */           } 
/* 149 */           forceScanJavaClassPath = true;
/* 150 */           if (classpathFinderLog != null) {
/* 151 */             classpathFinderLog.log("overrideClassLoaders() was called with an instance of " + classLoaderClassName + ", which is a system classloader, so the `java.lang.path` classpath will also be scanned");
/*     */           }
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 160 */       scanNonSystemModules = scanSpec.scanModules;
/*     */     } 
/*     */ 
/*     */     
/* 164 */     this
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 169 */       .moduleFinder = (scanNonSystemModules || scanSpec.enableSystemJarsAndModules) ? new ModuleFinder((new CallStackReader(reflectionUtils)).getClassContext(classpathFinderLog), scanSpec, scanNonSystemModules, scanSpec.enableSystemJarsAndModules, reflectionUtils, classpathFinderLog) : null;
/*     */     
/* 171 */     this.classpathOrder = new ClasspathOrder(scanSpec, reflectionUtils);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 177 */     ClassLoaderFinder classLoaderFinder = (scanSpec.overrideClasspath == null && scanSpec.overrideClassLoaders == null) ? new ClassLoaderFinder(scanSpec, reflectionUtils, classpathFinderLog) : null;
/*     */     
/* 179 */     ClassLoader[] contextClassLoaders = (classLoaderFinder == null) ? new ClassLoader[0] : classLoaderFinder.getContextClassLoaders();
/* 180 */     ClassLoader defaultClassLoader = (contextClassLoaders.length > 0) ? contextClassLoaders[0] : null;
/* 181 */     if (scanSpec.overrideClasspath != null) {
/*     */       
/* 183 */       if (scanSpec.overrideClassLoaders != null && classpathFinderLog != null) {
/* 184 */         classpathFinderLog.log("It is not possible to override both the classpath and the ClassLoaders -- ignoring the ClassLoader override");
/*     */       }
/*     */ 
/*     */       
/* 188 */       LogNode overrideLog = (classpathFinderLog == null) ? null : classpathFinderLog.log("Overriding classpath with: " + scanSpec.overrideClasspath);
/* 189 */       this.classpathOrder.addClasspathEntries(scanSpec.overrideClasspath, defaultClassLoader, scanSpec, overrideLog);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 194 */       if (overrideLog != null) {
/* 195 */         overrideLog.log("WARNING: when the classpath is overridden, there is no guarantee that the classes found by classpath scanning will be the same as the classes loaded by the context classloader");
/*     */       }
/*     */ 
/*     */       
/* 199 */       this.classLoaderOrderRespectingParentDelegation = contextClassLoaders;
/*     */     } 
/*     */ 
/*     */     
/* 203 */     if (scanSpec.enableSystemJarsAndModules) {
/* 204 */       String jreRtJar = SystemJarFinder.getJreRtJarPath();
/*     */ 
/*     */ 
/*     */       
/* 208 */       LogNode systemJarsLog = (classpathFinderLog == null) ? null : classpathFinderLog.log("System jars:");
/* 209 */       if (jreRtJar != null) {
/* 210 */         if (scanSpec.enableSystemJarsAndModules) {
/* 211 */           this.classpathOrder.addSystemClasspathEntry(jreRtJar, defaultClassLoader);
/* 212 */           if (systemJarsLog != null) {
/* 213 */             systemJarsLog.log("Found rt.jar: " + jreRtJar);
/*     */           }
/* 215 */         } else if (systemJarsLog != null) {
/* 216 */           systemJarsLog.log((scanSpec.enableSystemJarsAndModules ? "" : "Scanning disabled for rt.jar: ") + jreRtJar);
/*     */         } 
/*     */       }
/*     */       
/* 220 */       boolean scanAllLibOrExtJars = !scanSpec.libOrExtJarAcceptReject.acceptAndRejectAreEmpty();
/* 221 */       for (String libOrExtJarPath : SystemJarFinder.getJreLibOrExtJars()) {
/* 222 */         if (scanAllLibOrExtJars || scanSpec.libOrExtJarAcceptReject
/* 223 */           .isSpecificallyAcceptedAndNotRejected(libOrExtJarPath)) {
/* 224 */           this.classpathOrder.addSystemClasspathEntry(libOrExtJarPath, defaultClassLoader);
/* 225 */           if (systemJarsLog != null)
/* 226 */             systemJarsLog.log("Found lib or ext jar: " + libOrExtJarPath);  continue;
/*     */         } 
/* 228 */         if (systemJarsLog != null) {
/* 229 */           systemJarsLog.log("Scanning disabled for lib or ext jar: " + libOrExtJarPath);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 234 */     if (scanSpec.overrideClasspath == null) {
/*     */       
/* 236 */       if (classpathFinderLog != null) {
/* 237 */         LogNode classLoaderHandlerLog = classpathFinderLog.log("ClassLoaderHandlers:");
/*     */         
/* 239 */         for (ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry classLoaderHandlerEntry : ClassLoaderHandlerRegistry.CLASS_LOADER_HANDLERS) {
/* 240 */           classLoaderHandlerLog.log(classLoaderHandlerEntry.classLoaderHandlerClass.getName());
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 246 */       LogNode classloaderOrderLog = (classpathFinderLog == null) ? null : classpathFinderLog.log("Finding unique classloaders in delegation order");
/* 247 */       ClassLoaderOrder classLoaderOrder = new ClassLoaderOrder(reflectionUtils);
/*     */ 
/*     */       
/* 250 */       ClassLoader[] origClassLoaderOrder = (scanSpec.overrideClassLoaders != null) ? (ClassLoader[])scanSpec.overrideClassLoaders.toArray((Object[])new ClassLoader[0]) : contextClassLoaders;
/* 251 */       if (origClassLoaderOrder != null) {
/* 252 */         for (ClassLoader classLoader : origClassLoaderOrder) {
/* 253 */           classLoaderOrder.delegateTo(classLoader, false, classloaderOrderLog);
/*     */         }
/*     */       }
/*     */ 
/*     */       
/* 258 */       Set<ClassLoader> allParentClassLoaders = classLoaderOrder.getAllParentClassLoaders();
/*     */ 
/*     */ 
/*     */       
/* 262 */       LogNode classloaderURLLog = (classpathFinderLog == null) ? null : classpathFinderLog.log("Obtaining URLs from classloaders in delegation order");
/* 263 */       List<ClassLoader> finalClassLoaderOrder = new ArrayList<>();
/* 264 */       for (Map.Entry<ClassLoader, ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry> ent : classLoaderOrder
/* 265 */         .getClassLoaderOrder()) {
/* 266 */         ClassLoader classLoader = ent.getKey();
/* 267 */         ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry classLoaderHandlerRegistryEntry = ent.getValue();
/*     */         
/* 269 */         if (!scanSpec.ignoreParentClassLoaders || !allParentClassLoaders.contains(classLoader)) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 274 */           LogNode classloaderHandlerLog = (classloaderURLLog == null) ? null : classloaderURLLog.log("Classloader " + classLoader.getClass().getName() + " is handled by " + classLoaderHandlerRegistryEntry.classLoaderHandlerClass
/* 275 */               .getName());
/* 276 */           classLoaderHandlerRegistryEntry.findClasspathOrder(classLoader, this.classpathOrder, scanSpec, classloaderHandlerLog);
/*     */           
/* 278 */           finalClassLoaderOrder.add(classLoader);
/* 279 */         } else if (classloaderURLLog != null) {
/* 280 */           classloaderURLLog.log("Ignoring parent classloader " + classLoader + ", normally handled by " + classLoaderHandlerRegistryEntry.classLoaderHandlerClass
/* 281 */               .getName());
/*     */         } 
/*     */         
/* 284 */         if (classLoader instanceof ClassGraphClassLoader) {
/* 285 */           this.delegateClassGraphClassLoader = (ClassGraphClassLoader)classLoader;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 291 */       this.classLoaderOrderRespectingParentDelegation = finalClassLoaderOrder.<ClassLoader>toArray(new ClassLoader[0]);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 298 */     if ((!scanSpec.ignoreParentClassLoaders && (scanSpec.overrideClassLoaders == null || forceScanJavaClassPath) && scanSpec.overrideClasspath == null) || (this.moduleFinder != null && this.moduleFinder
/*     */       
/* 300 */       .forceScanJavaClassPath())) {
/* 301 */       String[] pathElements = JarUtils.smartPathSplit(System.getProperty("java.class.path"), scanSpec);
/* 302 */       if (pathElements.length > 0) {
/*     */         
/* 304 */         LogNode sysPropLog = (classpathFinderLog == null) ? null : classpathFinderLog.log("Getting classpath entries from java.class.path");
/* 305 */         for (String pathElement : pathElements) {
/*     */           
/* 307 */           String pathElementResolved = FastPathResolver.resolve(FileUtils.currDirPath(), pathElement);
/*     */           
/* 309 */           this.classpathOrder.addClasspathEntry(pathElementResolved, defaultClassLoader, scanSpec, sysPropLog);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classpath\ClasspathFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */