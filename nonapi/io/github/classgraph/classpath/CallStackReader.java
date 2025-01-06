/*     */ package nonapi.io.github.classgraph.classpath;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Callable;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ import nonapi.io.github.classgraph.utils.LogNode;
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
/*     */ class CallStackReader
/*     */ {
/*     */   ReflectionUtils reflectionUtils;
/*     */   
/*     */   public CallStackReader(ReflectionUtils reflectionUtils) {
/*  51 */     this.reflectionUtils = reflectionUtils;
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
/*     */   private static Class<?>[] getCallStackViaStackWalker() {
/*     */     try {
/*  66 */       Class<?> consumerClass = Class.forName("java.util.function.Consumer");
/*  67 */       final List<Class<?>> stackFrameClasses = new ArrayList<>();
/*  68 */       Class<?> stackWalkerOptionClass = Class.forName("java.lang.StackWalker$Option");
/*     */ 
/*     */       
/*  71 */       Object retainClassReference = Class.forName("java.lang.Enum").getMethod("valueOf", new Class[] { Class.class, String.class }).invoke((Object)null, new Object[] { stackWalkerOptionClass, "RETAIN_CLASS_REFERENCE" });
/*  72 */       Class<?> stackWalkerClass = Class.forName("java.lang.StackWalker");
/*     */       
/*  74 */       Object stackWalkerInstance = stackWalkerClass.getMethod("getInstance", new Class[] { stackWalkerOptionClass }).invoke((Object)null, new Object[] { retainClassReference });
/*     */       
/*  76 */       final Method stackFrameGetDeclaringClassMethod = Class.forName("java.lang.StackWalker$StackFrame").getMethod("getDeclaringClass", new Class[0]);
/*  77 */       stackWalkerClass.getMethod("forEach", new Class[] { consumerClass }).invoke(stackWalkerInstance, new Object[] {
/*     */             
/*  79 */             Proxy.newProxyInstance(consumerClass.getClassLoader(), new Class[] { consumerClass }, new InvocationHandler()
/*     */               {
/*     */ 
/*     */ 
/*     */                 
/*     */                 public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
/*     */                 {
/*  86 */                   Class<?> declaringClass = (Class)stackFrameGetDeclaringClassMethod.invoke(args[0], new Object[0]);
/*  87 */                   stackFrameClasses.add(declaringClass);
/*  88 */                   return null;
/*     */                 }
/*     */               }) });
/*  91 */       return (Class[])stackFrameClasses.<Class<?>[]>toArray((Class<?>[][])new Class[0]);
/*  92 */     } catch (Exception|LinkageError e) {
/*  93 */       return null;
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
/*     */   private static Class<?>[] getCallStackViaSecurityManager(LogNode log) {
/*     */     try {
/* 109 */       Class<?> securityManagerClass = Class.forName("java.lang.SecurityManager");
/* 110 */       Object securityManager = null;
/* 111 */       for (Constructor<?> constructor : securityManagerClass.getDeclaredConstructors()) {
/* 112 */         if ((constructor.getParameterTypes()).length == 0) {
/* 113 */           securityManager = constructor.newInstance(new Object[0]);
/*     */           break;
/*     */         } 
/*     */       } 
/* 117 */       if (securityManager != null) {
/* 118 */         Method getClassContext = securityManager.getClass().getDeclaredMethod("getClassContext", new Class[0]);
/* 119 */         getClassContext.setAccessible(true);
/* 120 */         return (Class[])getClassContext.invoke(securityManager, new Object[0]);
/*     */       } 
/* 122 */       return null;
/*     */     }
/* 124 */     catch (Throwable t) {
/*     */ 
/*     */       
/* 127 */       if (log != null) {
/* 128 */         log.log("Exception while trying to obtain call stack via SecurityManager", t);
/*     */       }
/* 130 */       return null;
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
/*     */   Class<?>[] getClassContext(final LogNode log) {
/* 144 */     Class<?>[] callStack = null;
/*     */ 
/*     */     
/* 147 */     if (VersionFinder.JAVA_MAJOR_VERSION != 9 && VersionFinder.JAVA_MAJOR_VERSION != 10 && (VersionFinder.JAVA_MAJOR_VERSION != 11 || VersionFinder.JAVA_MINOR_VERSION != 0 || (VersionFinder.JAVA_SUB_VERSION >= 4 && (VersionFinder.JAVA_SUB_VERSION != 4 || !VersionFinder.JAVA_IS_EA_VERSION))) && (VersionFinder.JAVA_MAJOR_VERSION != 12 || VersionFinder.JAVA_MINOR_VERSION != 0 || (VersionFinder.JAVA_SUB_VERSION >= 2 && (VersionFinder.JAVA_SUB_VERSION != 2 || !VersionFinder.JAVA_IS_EA_VERSION)))) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 167 */         callStack = (Class[])this.reflectionUtils.doPrivileged(new Callable<Class<?>[]>()
/*     */             {
/*     */               public Class<?>[] call() throws Exception {
/* 170 */                 return CallStackReader.getCallStackViaStackWalker();
/*     */               }
/*     */             });
/* 173 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     if (VersionFinder.JAVA_MAJOR_VERSION < 9 && (callStack == null || callStack.length == 0)) {
/*     */       try {
/* 182 */         callStack = (Class[])this.reflectionUtils.doPrivileged(new Callable<Class<?>[]>()
/*     */             {
/*     */               public Class<?>[] call() throws Exception {
/* 185 */                 return CallStackReader.getCallStackViaSecurityManager(log);
/*     */               }
/*     */             });
/* 188 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 194 */     if (callStack == null || callStack.length == 0) {
/* 195 */       StackTraceElement[] stackTrace = null;
/*     */       try {
/* 197 */         stackTrace = Thread.currentThread().getStackTrace();
/* 198 */       } catch (SecurityException securityException) {}
/*     */ 
/*     */       
/* 201 */       if (stackTrace == null || stackTrace.length == 0) {
/*     */         
/*     */         try {
/* 204 */           throw new Exception();
/* 205 */         } catch (Exception e) {
/* 206 */           stackTrace = e.getStackTrace();
/*     */         } 
/*     */       }
/* 209 */       List<Class<?>> stackClassesList = new ArrayList<>();
/* 210 */       for (StackTraceElement elt : stackTrace) {
/*     */         try {
/* 212 */           stackClassesList.add(Class.forName(elt.getClassName()));
/* 213 */         } catch (ClassNotFoundException|LinkageError classNotFoundException) {}
/*     */       } 
/*     */ 
/*     */       
/* 217 */       if (!stackClassesList.isEmpty()) {
/* 218 */         callStack = (Class[])stackClassesList.<Class<?>[]>toArray((Class<?>[][])new Class[0]);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 223 */     if (callStack == null || callStack.length == 0) {
/* 224 */       callStack = new Class[] { CallStackReader.class };
/*     */     }
/*     */     
/* 227 */     return callStack;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classpath\CallStackReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */