/*     */ package nonapi.io.github.classgraph.reflection;
/*     */ 
/*     */ import io.github.classgraph.ClassGraph;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.concurrent.Callable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ReflectionUtils
/*     */ {
/*     */   public ReflectionDriver reflectionDriver;
/*     */   private Class<?> accessControllerClass;
/*     */   private Class<?> privilegedActionClass;
/*     */   private Method accessControllerDoPrivileged;
/*     */   
/*     */   public ReflectionUtils() {
/*  50 */     if (ClassGraph.CIRCUMVENT_ENCAPSULATION == ClassGraph.CircumventEncapsulationMethod.NARCISSUS) {
/*     */       try {
/*  52 */         this.reflectionDriver = new NarcissusReflectionDriver();
/*  53 */       } catch (Throwable t) {
/*  54 */         System.err.println("Could not load Narcissus reflection driver: " + t);
/*     */       }
/*     */     
/*  57 */     } else if (ClassGraph.CIRCUMVENT_ENCAPSULATION == ClassGraph.CircumventEncapsulationMethod.JVM_DRIVER) {
/*     */       try {
/*  59 */         this.reflectionDriver = new JVMDriverReflectionDriver();
/*  60 */       } catch (Throwable t) {
/*  61 */         System.err.println("Could not load JVM-Driver reflection driver: " + t);
/*     */       } 
/*     */     } 
/*     */     
/*  65 */     if (this.reflectionDriver == null) {
/*  66 */       this.reflectionDriver = new StandardReflectionDriver();
/*     */     }
/*     */     try {
/*  69 */       this.accessControllerClass = this.reflectionDriver.findClass("java.security.AccessController");
/*  70 */       this.privilegedActionClass = this.reflectionDriver.findClass("java.security.PrivilegedAction");
/*  71 */       this.accessControllerDoPrivileged = this.reflectionDriver.findMethod(this.accessControllerClass, null, "doPrivileged", new Class[] { this.privilegedActionClass });
/*     */     }
/*  73 */     catch (Throwable throwable) {}
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
/*     */   public Object getFieldVal(boolean throwException, Object obj, Field field) throws IllegalArgumentException {
/*  97 */     if (this.reflectionDriver == null) {
/*  98 */       throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
/*     */     }
/* 100 */     if (obj == null || field == null) {
/* 101 */       if (throwException) {
/* 102 */         throw new NullPointerException();
/*     */       }
/* 104 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 108 */       return this.reflectionDriver.getField(obj, field);
/* 109 */     } catch (Throwable e) {
/* 110 */       if (throwException) {
/* 111 */         throw new IllegalArgumentException("Can't read field " + obj
/* 112 */             .getClass().getName() + "." + field.getName(), e);
/*     */       }
/*     */       
/* 115 */       return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getFieldVal(boolean throwException, Object obj, String fieldName) throws IllegalArgumentException {
/* 137 */     if (this.reflectionDriver == null) {
/* 138 */       throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
/*     */     }
/* 140 */     if (obj == null || fieldName == null) {
/* 141 */       if (throwException) {
/* 142 */         throw new NullPointerException();
/*     */       }
/* 144 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 148 */       return this.reflectionDriver.getField(obj, this.reflectionDriver.findInstanceField(obj, fieldName));
/* 149 */     } catch (Throwable e) {
/* 150 */       if (throwException) {
/* 151 */         throw new IllegalArgumentException("Can't read field " + obj.getClass().getName() + "." + fieldName, e);
/*     */       }
/*     */ 
/*     */       
/* 155 */       return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getStaticFieldVal(boolean throwException, Class<?> cls, String fieldName) throws IllegalArgumentException {
/* 177 */     if (this.reflectionDriver == null) {
/* 178 */       throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
/*     */     }
/* 180 */     if (cls == null || fieldName == null) {
/* 181 */       if (throwException) {
/* 182 */         throw new NullPointerException();
/*     */       }
/* 184 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 188 */       return this.reflectionDriver.getStaticField(this.reflectionDriver.findStaticField(cls, fieldName));
/* 189 */     } catch (Throwable e) {
/* 190 */       if (throwException) {
/* 191 */         throw new IllegalArgumentException("Can't read field " + cls.getName() + "." + fieldName, e);
/*     */       }
/*     */       
/* 194 */       return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object invokeMethod(boolean throwException, Object obj, String methodName) throws IllegalArgumentException {
/* 216 */     if (this.reflectionDriver == null) {
/* 217 */       throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
/*     */     }
/* 219 */     if (obj == null || methodName == null) {
/* 220 */       if (throwException) {
/* 221 */         throw new IllegalArgumentException("Unexpected null argument");
/*     */       }
/* 223 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 227 */       return this.reflectionDriver.invokeMethod(obj, this.reflectionDriver.findInstanceMethod(obj, methodName, new Class[0]), new Object[0]);
/* 228 */     } catch (Throwable e) {
/* 229 */       if (throwException) {
/* 230 */         throw new IllegalArgumentException("Method \"" + methodName + "\" could not be invoked", e);
/*     */       }
/* 232 */       return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object invokeMethod(boolean throwException, Object obj, String methodName, Class<?> argType, Object param) throws IllegalArgumentException {
/* 259 */     if (this.reflectionDriver == null) {
/* 260 */       throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
/*     */     }
/* 262 */     if (obj == null || methodName == null || argType == null) {
/* 263 */       if (throwException) {
/* 264 */         throw new IllegalArgumentException("Unexpected null argument");
/*     */       }
/* 266 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 270 */       return this.reflectionDriver.invokeMethod(obj, this.reflectionDriver.findInstanceMethod(obj, methodName, new Class[] { argType }), new Object[] { param });
/*     */     }
/* 272 */     catch (Throwable e) {
/* 273 */       if (throwException) {
/* 274 */         throw new IllegalArgumentException("Method \"" + methodName + "\" could not be invoked", e);
/*     */       }
/* 276 */       return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object invokeStaticMethod(boolean throwException, Class<?> cls, String methodName) throws IllegalArgumentException {
/* 298 */     if (this.reflectionDriver == null) {
/* 299 */       throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
/*     */     }
/* 301 */     if (cls == null || methodName == null) {
/* 302 */       if (throwException) {
/* 303 */         throw new IllegalArgumentException("Unexpected null argument");
/*     */       }
/* 305 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 309 */       return this.reflectionDriver.invokeStaticMethod(this.reflectionDriver.findStaticMethod(cls, methodName, new Class[0]), new Object[0]);
/* 310 */     } catch (Throwable e) {
/* 311 */       if (throwException) {
/* 312 */         throw new IllegalArgumentException("Method \"" + methodName + "\" could not be invoked", e);
/*     */       }
/* 314 */       return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object invokeStaticMethod(boolean throwException, Class<?> cls, String methodName, Class<?> argType, Object param) throws IllegalArgumentException {
/* 340 */     if (this.reflectionDriver == null) {
/* 341 */       throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
/*     */     }
/* 343 */     if (cls == null || methodName == null || argType == null) {
/* 344 */       if (throwException) {
/* 345 */         throw new IllegalArgumentException("Unexpected null argument");
/*     */       }
/* 347 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 351 */       return this.reflectionDriver.invokeStaticMethod(this.reflectionDriver.findStaticMethod(cls, methodName, new Class[] { argType }), new Object[] { param });
/*     */     }
/* 353 */     catch (Throwable e) {
/* 354 */       if (throwException) {
/* 355 */         throw new IllegalArgumentException("Fethod \"" + methodName + "\" could not be invoked", e);
/*     */       }
/* 357 */       return null;
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
/*     */   public Class<?> classForNameOrNull(String className) {
/* 369 */     if (this.reflectionDriver == null) {
/* 370 */       throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
/*     */     }
/*     */     try {
/* 373 */       return this.reflectionDriver.findClass(className);
/* 374 */     } catch (Throwable e) {
/* 375 */       return null;
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
/*     */   public Method staticMethodForNameOrNull(String className, String staticMethodName) {
/* 387 */     if (this.reflectionDriver == null) {
/* 388 */       throw new RuntimeException("Cannot use reflection after ScanResult has been closed");
/*     */     }
/*     */     try {
/* 391 */       return this.reflectionDriver.findStaticMethod(this.reflectionDriver.findClass(className), staticMethodName, new Class[0]);
/* 392 */     } catch (Throwable e) {
/* 393 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private class PrivilegedActionInvocationHandler<T>
/*     */     implements InvocationHandler
/*     */   {
/*     */     private final Callable<T> callable;
/*     */     
/*     */     public PrivilegedActionInvocationHandler(Callable<T> callable) {
/* 403 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/* 408 */       return this.callable.call();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> T doPrivileged(Callable<T> callable) throws Throwable {
/* 418 */     if (this.accessControllerDoPrivileged != null) {
/* 419 */       Object privilegedAction = Proxy.newProxyInstance(this.privilegedActionClass.getClassLoader(), new Class[] { this.privilegedActionClass }, new PrivilegedActionInvocationHandler<>(callable));
/*     */       
/* 421 */       return (T)this.accessControllerDoPrivileged.invoke(null, new Object[] { privilegedAction });
/*     */     } 
/*     */     
/* 424 */     return callable.call();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\reflection\ReflectionUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */