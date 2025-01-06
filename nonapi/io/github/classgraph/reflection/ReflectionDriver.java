/*     */ package nonapi.io.github.classgraph.reflection;
/*     */ 
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.concurrency.SingletonMap;
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
/*     */ abstract class ReflectionDriver
/*     */ {
/*  49 */   private final SingletonMap<Class<?>, ClassMemberCache, Exception> classToClassMemberCache = new SingletonMap<Class<?>, ClassMemberCache, Exception>()
/*     */     {
/*     */       
/*     */       public ReflectionDriver.ClassMemberCache newInstance(Class<?> cls, LogNode log) throws Exception, InterruptedException
/*     */       {
/*  54 */         return new ReflectionDriver.ClassMemberCache(cls);
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   private static Method isAccessibleMethod;
/*     */   
/*     */   private static Method canAccessMethod;
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  66 */       isAccessibleMethod = AccessibleObject.class.getDeclaredMethod("isAccessible", new Class[0]);
/*  67 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/*     */     try {
/*  71 */       canAccessMethod = AccessibleObject.class.getDeclaredMethod("canAccess", new Class[] { Object.class });
/*  72 */     } catch (Throwable throwable) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class ClassMemberCache
/*     */   {
/*  79 */     private final Map<String, List<Method>> methodNameToMethods = new HashMap<>();
/*  80 */     private final Map<String, Field> fieldNameToField = new HashMap<>();
/*     */ 
/*     */     
/*     */     private ClassMemberCache(Class<?> cls) throws Exception {
/*  84 */       Set<Class<?>> visited = new HashSet<>();
/*  85 */       LinkedList<Class<?>> interfaceQueue = new LinkedList<>();
/*  86 */       for (Class<?> c = cls; c != null; c = c.getSuperclass()) {
/*     */         
/*     */         try {
/*  89 */           for (Method m : ReflectionDriver.this.getDeclaredMethods(c)) {
/*  90 */             cacheMethod(m);
/*     */           }
/*  92 */           for (Field f : ReflectionDriver.this.getDeclaredFields(c)) {
/*  93 */             cacheField(f);
/*     */           }
/*     */           
/*  96 */           if (c.isInterface() && visited.add(c)) {
/*  97 */             interfaceQueue.add(c);
/*     */           }
/*  99 */           for (Class<?> iface : c.getInterfaces()) {
/* 100 */             if (visited.add(iface)) {
/* 101 */               interfaceQueue.add(iface);
/*     */             }
/*     */           } 
/* 104 */         } catch (Exception exception) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 109 */       while (!interfaceQueue.isEmpty()) {
/* 110 */         Class<?> iface = interfaceQueue.remove();
/*     */         try {
/* 112 */           for (Method m : ReflectionDriver.this.getDeclaredMethods(iface)) {
/* 113 */             cacheMethod(m);
/*     */           }
/* 115 */         } catch (Exception exception) {}
/*     */ 
/*     */         
/* 118 */         for (Class<?> superIface : iface.getInterfaces()) {
/* 119 */           if (visited.add(superIface)) {
/* 120 */             interfaceQueue.add(superIface);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private void cacheMethod(Method method) {
/* 127 */       List<Method> methodsForName = this.methodNameToMethods.get(method.getName());
/* 128 */       if (methodsForName == null) {
/* 129 */         this.methodNameToMethods.put(method.getName(), methodsForName = new ArrayList<>());
/*     */       }
/* 131 */       methodsForName.add(method);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private void cacheField(Field field) {
/* 137 */       if (!this.fieldNameToField.containsKey(field.getName())) {
/* 138 */         this.fieldNameToField.put(field.getName(), field);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isAccessible(Object instance, AccessibleObject fieldOrMethod) {
/* 273 */     if (canAccessMethod != null) {
/*     */       
/*     */       try {
/* 276 */         return ((Boolean)canAccessMethod.invoke(fieldOrMethod, new Object[] { instance })).booleanValue();
/* 277 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/* 281 */     if (isAccessibleMethod != null) {
/*     */       
/*     */       try {
/* 284 */         return ((Boolean)isAccessibleMethod.invoke(fieldOrMethod, new Object[0])).booleanValue();
/* 285 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/* 289 */     return false;
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
/*     */   protected Field findField(Class<?> cls, Object obj, String fieldName) throws Exception {
/* 307 */     Field field = (Field)((ClassMemberCache)this.classToClassMemberCache.get(cls, null)).fieldNameToField.get(fieldName);
/* 308 */     if (field != null) {
/* 309 */       if (!isAccessible(obj, field))
/*     */       {
/*     */         
/* 312 */         makeAccessible(obj, field);
/*     */       }
/* 314 */       return field;
/*     */     } 
/* 316 */     throw new NoSuchFieldException("Could not find field " + cls.getName() + "." + fieldName);
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
/*     */   protected Field findStaticField(Class<?> cls, String fieldName) throws Exception {
/* 332 */     return findField(cls, null, fieldName);
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
/*     */   protected Field findInstanceField(Object obj, String fieldName) throws Exception {
/* 348 */     if (obj == null) {
/* 349 */       throw new IllegalArgumentException("obj cannot be null");
/*     */     }
/* 351 */     return findField(obj.getClass(), obj, fieldName);
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
/*     */   protected Method findMethod(Class<?> cls, Object obj, String methodName, Class<?>... paramTypes) throws Exception {
/* 372 */     List<Method> methodsForName = (List<Method>)((ClassMemberCache)this.classToClassMemberCache.get(cls, null)).methodNameToMethods.get(methodName);
/* 373 */     if (methodsForName != null) {
/*     */       
/* 375 */       boolean found = false;
/* 376 */       for (Method method : methodsForName) {
/* 377 */         if (Arrays.equals((Object[])method.getParameterTypes(), (Object[])paramTypes)) {
/* 378 */           found = true;
/* 379 */           if (isAccessible(obj, method)) {
/* 380 */             return method;
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 386 */       if (found) {
/* 387 */         for (Method method : methodsForName) {
/* 388 */           if (Arrays.equals((Object[])method.getParameterTypes(), (Object[])paramTypes) && makeAccessible(obj, method)) {
/* 389 */             return method;
/*     */           }
/*     */         } 
/*     */       }
/* 393 */       throw new NoSuchMethodException("Could not make method accessible: " + cls
/* 394 */           .getName() + "." + methodName);
/*     */     } 
/* 396 */     throw new NoSuchMethodException("Could not find method " + cls.getName() + "." + methodName);
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
/*     */   protected Method findStaticMethod(Class<?> cls, String methodName, Class<?>... paramTypes) throws Exception {
/* 414 */     return findMethod(cls, null, methodName, paramTypes);
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
/*     */   protected Method findInstanceMethod(Object obj, String methodName, Class<?>... paramTypes) throws Exception {
/* 432 */     if (obj == null) {
/* 433 */       throw new IllegalArgumentException("obj cannot be null");
/*     */     }
/* 435 */     return findMethod(obj.getClass(), obj, methodName, paramTypes);
/*     */   }
/*     */   
/*     */   abstract Class<?> findClass(String paramString) throws Exception;
/*     */   
/*     */   abstract Method[] getDeclaredMethods(Class<?> paramClass) throws Exception;
/*     */   
/*     */   abstract <T> Constructor<T>[] getDeclaredConstructors(Class<T> paramClass) throws Exception;
/*     */   
/*     */   abstract Field[] getDeclaredFields(Class<?> paramClass) throws Exception;
/*     */   
/*     */   abstract Object getField(Object paramObject, Field paramField) throws Exception;
/*     */   
/*     */   abstract void setField(Object paramObject1, Field paramField, Object paramObject2) throws Exception;
/*     */   
/*     */   abstract Object getStaticField(Field paramField) throws Exception;
/*     */   
/*     */   abstract void setStaticField(Field paramField, Object paramObject) throws Exception;
/*     */   
/*     */   abstract Object invokeMethod(Object paramObject, Method paramMethod, Object... paramVarArgs) throws Exception;
/*     */   
/*     */   abstract Object invokeStaticMethod(Method paramMethod, Object... paramVarArgs) throws Exception;
/*     */   
/*     */   abstract boolean makeAccessible(Object paramObject, AccessibleObject paramAccessibleObject);
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\reflection\ReflectionDriver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */