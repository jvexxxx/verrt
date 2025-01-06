/*     */ package nonapi.io.github.classgraph.reflection;
/*     */ 
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Constructor;
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
/*     */ 
/*     */ class StandardReflectionDriver
/*     */   extends ReflectionDriver
/*     */ {
/*     */   private static Method setAccessibleMethod;
/*     */   private static Method trySetAccessibleMethod;
/*     */   private static Class<?> accessControllerClass;
/*     */   private static Class<?> privilegedActionClass;
/*     */   private static Method accessControllerDoPrivileged;
/*     */   
/*     */   static {
/*     */     try {
/*  55 */       setAccessibleMethod = AccessibleObject.class.getDeclaredMethod("setAccessible", new Class[] { boolean.class });
/*  56 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/*     */     try {
/*  60 */       trySetAccessibleMethod = AccessibleObject.class.getDeclaredMethod("trySetAccessible", new Class[0]);
/*  61 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/*     */     try {
/*  65 */       accessControllerClass = Class.forName("java.security.AccessController");
/*  66 */       privilegedActionClass = Class.forName("java.security.PrivilegedAction");
/*  67 */       accessControllerDoPrivileged = accessControllerClass.getMethod("doPrivileged", new Class[] { privilegedActionClass });
/*  68 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private class PrivilegedActionInvocationHandler<T>
/*     */     implements InvocationHandler
/*     */   {
/*     */     private final Callable<T> callable;
/*     */ 
/*     */     
/*     */     public PrivilegedActionInvocationHandler(Callable<T> callable) {
/*  79 */       this.callable = callable;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
/*  84 */       return this.callable.call();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private <T> T doPrivileged(Callable<T> callable) throws Throwable {
/*  94 */     if (accessControllerDoPrivileged != null) {
/*  95 */       Object privilegedAction = Proxy.newProxyInstance(privilegedActionClass.getClassLoader(), new Class[] { privilegedActionClass }, new PrivilegedActionInvocationHandler<>(callable));
/*     */       
/*  97 */       return (T)accessControllerDoPrivileged.invoke(null, new Object[] { privilegedAction });
/*     */     } 
/*     */     
/* 100 */     return callable.call();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean tryMakeAccessible(AccessibleObject obj) {
/* 107 */     if (trySetAccessibleMethod != null) {
/*     */       
/*     */       try {
/* 110 */         return ((Boolean)trySetAccessibleMethod.invoke(obj, new Object[0])).booleanValue();
/* 111 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/* 115 */     if (setAccessibleMethod != null) {
/*     */       
/*     */       try {
/* 118 */         setAccessibleMethod.invoke(obj, new Object[] { Boolean.valueOf(true) });
/* 119 */         return true;
/* 120 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/* 124 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean makeAccessible(Object instance, final AccessibleObject obj) {
/* 129 */     if (isAccessible(instance, obj)) {
/* 130 */       return true;
/*     */     }
/*     */     try {
/* 133 */       return ((Boolean)doPrivileged(new Callable<Boolean>()
/*     */           {
/*     */             public Boolean call() throws Exception {
/* 136 */               return Boolean.valueOf(StandardReflectionDriver.tryMakeAccessible(obj));
/*     */             }
/*     */           })).booleanValue();
/* 139 */     } catch (Throwable t) {
/*     */       
/* 141 */       return tryMakeAccessible(obj);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   Class<?> findClass(String className) throws Exception {
/* 147 */     return Class.forName(className);
/*     */   }
/*     */ 
/*     */   
/*     */   Method[] getDeclaredMethods(Class<?> cls) throws Exception {
/* 152 */     return cls.getDeclaredMethods();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   <T> Constructor<T>[] getDeclaredConstructors(Class<T> cls) throws Exception {
/* 158 */     return (Constructor[])cls.getDeclaredConstructors();
/*     */   }
/*     */ 
/*     */   
/*     */   Field[] getDeclaredFields(Class<?> cls) throws Exception {
/* 163 */     return cls.getDeclaredFields();
/*     */   }
/*     */ 
/*     */   
/*     */   Object getField(Object object, Field field) throws Exception {
/* 168 */     makeAccessible(object, field);
/* 169 */     return field.get(object);
/*     */   }
/*     */ 
/*     */   
/*     */   void setField(Object object, Field field, Object value) throws Exception {
/* 174 */     makeAccessible(object, field);
/* 175 */     field.set(object, value);
/*     */   }
/*     */ 
/*     */   
/*     */   Object getStaticField(Field field) throws Exception {
/* 180 */     makeAccessible((Object)null, field);
/* 181 */     return field.get(null);
/*     */   }
/*     */ 
/*     */   
/*     */   void setStaticField(Field field, Object value) throws Exception {
/* 186 */     makeAccessible((Object)null, field);
/* 187 */     field.set(null, value);
/*     */   }
/*     */ 
/*     */   
/*     */   Object invokeMethod(Object object, Method method, Object... args) throws Exception {
/* 192 */     makeAccessible(object, method);
/* 193 */     return method.invoke(object, args);
/*     */   }
/*     */ 
/*     */   
/*     */   Object invokeStaticMethod(Method method, Object... args) throws Exception {
/* 198 */     makeAccessible((Object)null, method);
/* 199 */     return method.invoke(null, args);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\reflection\StandardReflectionDriver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */