/*     */ package nonapi.io.github.classgraph.classpath;
/*     */ 
/*     */ import java.util.LinkedHashSet;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
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
/*     */ public class ClassLoaderFinder
/*     */ {
/*     */   private final ClassLoader[] contextClassLoaders;
/*     */   
/*     */   public ClassLoader[] getContextClassLoaders() {
/*  50 */     return this.contextClassLoaders;
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
/*     */   ClassLoaderFinder(ScanSpec scanSpec, ReflectionUtils reflectionUtils, LogNode log) {
/*     */     LinkedHashSet<ClassLoader> classLoadersUnique;
/*     */     LogNode classLoadersFoundLog;
/*  66 */     if (scanSpec.overrideClassLoaders == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  75 */       classLoadersUnique = new LinkedHashSet<>();
/*  76 */       ClassLoader threadClassLoader = Thread.currentThread().getContextClassLoader();
/*  77 */       if (threadClassLoader != null) {
/*  78 */         classLoadersUnique.add(threadClassLoader);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  84 */       ClassLoader currClassClassLoader = getClass().getClassLoader();
/*  85 */       if (currClassClassLoader != null) {
/*  86 */         classLoadersUnique.add(currClassClassLoader);
/*     */       }
/*     */ 
/*     */       
/*  90 */       ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
/*  91 */       if (systemClassLoader != null) {
/*  92 */         classLoadersUnique.add(systemClassLoader);
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
/*     */       try {
/* 105 */         Class<?>[] callStack = (new CallStackReader(reflectionUtils)).getClassContext(log);
/* 106 */         for (int i = callStack.length - 1; i >= 0; i--) {
/* 107 */           ClassLoader callerClassLoader = callStack[i].getClassLoader();
/* 108 */           if (callerClassLoader != null) {
/* 109 */             classLoadersUnique.add(callerClassLoader);
/*     */           }
/*     */         } 
/* 112 */       } catch (IllegalArgumentException e) {
/* 113 */         if (log != null) {
/* 114 */           log.log("Could not get call stack", e);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 119 */       if (scanSpec.addedClassLoaders != null) {
/* 120 */         classLoadersUnique.addAll(scanSpec.addedClassLoaders);
/*     */       }
/* 122 */       classLoadersFoundLog = (log == null) ? null : log.log("Found ClassLoaders:");
/*     */     }
/*     */     else {
/*     */       
/* 126 */       classLoadersUnique = new LinkedHashSet<>(scanSpec.overrideClassLoaders);
/* 127 */       classLoadersFoundLog = (log == null) ? null : log.log("Override ClassLoaders:");
/*     */     } 
/*     */ 
/*     */     
/* 131 */     if (classLoadersFoundLog != null) {
/* 132 */       for (ClassLoader classLoader : classLoadersUnique) {
/* 133 */         classLoadersFoundLog.log(classLoader.getClass().getName());
/*     */       }
/*     */     }
/*     */     
/* 137 */     this.contextClassLoaders = (ClassLoader[])classLoadersUnique.toArray((Object[])new ClassLoader[0]);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classpath\ClassLoaderFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */