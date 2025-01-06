/*     */ package nonapi.io.github.classgraph.reflection;
/*     */ 
/*     */ import java.lang.reflect.AccessibleObject;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JVMDriverReflectionDriver
/*     */   extends ReflectionDriver
/*     */ {
/*     */   private Object driver;
/*     */   private final Method getDeclaredMethods;
/*     */   private final Method getDeclaredConstructors;
/*     */   private final Method getDeclaredFields;
/*     */   private final Method getField;
/*     */   private final Method setField;
/*     */   private final Method invokeMethod;
/*     */   private final Method setAccessibleMethod;
/*     */   private ClassFinder classFinder;
/*     */   
/*     */   JVMDriverReflectionDriver() throws Exception {
/*  59 */     StandardReflectionDriver drv = new StandardReflectionDriver();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     Class<?> driverClass = drv.findClass("io.github.toolfactory.jvm.DefaultDriver");
/*  67 */     for (Constructor<?> constructor : drv.getDeclaredConstructors(driverClass)) {
/*  68 */       if ((constructor.getParameterTypes()).length == 0) {
/*  69 */         this.driver = constructor.newInstance(new Object[0]);
/*     */         break;
/*     */       } 
/*     */     } 
/*  73 */     if (this.driver == null) {
/*  74 */       throw new IllegalArgumentException("Could not instantiate jvm.DefaultDriver");
/*     */     }
/*     */ 
/*     */     
/*  78 */     this.getDeclaredMethods = drv.findInstanceMethod(this.driver, "getDeclaredMethods", new Class[] { Class.class });
/*  79 */     this.getDeclaredConstructors = drv.findInstanceMethod(this.driver, "getDeclaredConstructors", new Class[] { Class.class });
/*  80 */     this.getDeclaredFields = drv.findInstanceMethod(this.driver, "getDeclaredFields", new Class[] { Class.class });
/*  81 */     this.getField = drv.findInstanceMethod(this.driver, "getFieldValue", new Class[] { Object.class, Field.class });
/*  82 */     this.setField = drv.findInstanceMethod(this.driver, "setFieldValue", new Class[] { Object.class, Field.class, Object.class });
/*  83 */     this.invokeMethod = drv.findInstanceMethod(this.driver, "invoke", new Class[] { Object.class, Method.class, Object[].class });
/*  84 */     this.setAccessibleMethod = drv.findInstanceMethod(this.driver, "setAccessible", new Class[] { AccessibleObject.class, boolean.class });
/*     */ 
/*     */     
/*     */     try {
/*  88 */       final Method forName0_method = findStaticMethod(Class.class, "forName0", new Class[] { String.class, boolean.class, ClassLoader.class });
/*     */       
/*  90 */       this.classFinder = new ClassFinder()
/*     */         {
/*     */           public Class<?> findClass(String className) throws Exception {
/*  93 */             return (Class)forName0_method.invoke(null, new Object[] { className, Boolean.valueOf(true), 
/*  94 */                   Thread.currentThread().getContextClassLoader() });
/*     */           }
/*     */         };
/*  97 */     } catch (Throwable throwable) {}
/*     */ 
/*     */     
/* 100 */     if (this.classFinder == null) {
/*     */       
/*     */       try {
/* 103 */         final Method forName0_method = findStaticMethod(Class.class, "forName0", new Class[] { String.class, boolean.class, ClassLoader.class, Class.class });
/*     */         
/* 105 */         this.classFinder = new ClassFinder()
/*     */           {
/*     */             public Class<?> findClass(String className) throws Exception {
/* 108 */               return (Class)forName0_method.invoke(null, new Object[] { className, Boolean.valueOf(true), 
/* 109 */                     Thread.currentThread().getContextClassLoader(), JVMDriverReflectionDriver.class });
/*     */             }
/*     */           };
/* 112 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/* 116 */     if (this.classFinder == null) {
/*     */       
/*     */       try {
/* 119 */         final Method forNameImpl_method = findStaticMethod(Class.class, "forNameImpl", new Class[] { String.class, boolean.class, ClassLoader.class });
/*     */         
/* 121 */         this.classFinder = new ClassFinder()
/*     */           {
/*     */             public Class<?> findClass(String className) throws Exception {
/* 124 */               return (Class)forNameImpl_method.invoke(null, new Object[] { className, Boolean.valueOf(true), 
/* 125 */                     Thread.currentThread().getContextClassLoader() });
/*     */             }
/*     */           };
/* 128 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */     
/* 132 */     if (this.classFinder == null) {
/*     */ 
/*     */       
/* 135 */       final Method forName_method = findStaticMethod(Class.class, "forName", new Class[] { String.class });
/* 136 */       this.classFinder = new ClassFinder()
/*     */         {
/*     */           public Class<?> findClass(String className) throws Exception {
/* 139 */             return (Class)forName_method.invoke(null, new Object[] { className });
/*     */           }
/*     */         };
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean makeAccessible(Object instance, AccessibleObject accessibleObject) {
/*     */     try {
/* 148 */       this.setAccessibleMethod.invoke(this.driver, new Object[] { accessibleObject, Boolean.valueOf(true) });
/* 149 */     } catch (Throwable t) {
/* 150 */       return false;
/*     */     } 
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   Class<?> findClass(String className) throws Exception {
/* 157 */     return this.classFinder.findClass(className);
/*     */   }
/*     */ 
/*     */   
/*     */   Method[] getDeclaredMethods(Class<?> cls) throws Exception {
/* 162 */     return (Method[])this.getDeclaredMethods.invoke(this.driver, new Object[] { cls });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   <T> Constructor<T>[] getDeclaredConstructors(Class<T> cls) throws Exception {
/* 168 */     return (Constructor<T>[])this.getDeclaredConstructors.invoke(this.driver, new Object[] { cls });
/*     */   }
/*     */ 
/*     */   
/*     */   Field[] getDeclaredFields(Class<?> cls) throws Exception {
/* 173 */     return (Field[])this.getDeclaredFields.invoke(this.driver, new Object[] { cls });
/*     */   }
/*     */ 
/*     */   
/*     */   Object getField(Object object, Field field) throws Exception {
/* 178 */     return this.getField.invoke(this.driver, new Object[] { object, field });
/*     */   }
/*     */ 
/*     */   
/*     */   void setField(Object object, Field field, Object value) throws Exception {
/* 183 */     this.setField.invoke(this.driver, new Object[] { object, field, value });
/*     */   }
/*     */ 
/*     */   
/*     */   Object getStaticField(Field field) throws Exception {
/* 188 */     return this.getField.invoke(this.driver, new Object[] { null, field });
/*     */   }
/*     */ 
/*     */   
/*     */   void setStaticField(Field field, Object value) throws Exception {
/* 193 */     this.setField.invoke(this.driver, new Object[] { null, field, value });
/*     */   }
/*     */ 
/*     */   
/*     */   Object invokeMethod(Object object, Method method, Object... args) throws Exception {
/* 198 */     return this.invokeMethod.invoke(this.driver, new Object[] { object, method, args });
/*     */   }
/*     */ 
/*     */   
/*     */   Object invokeStaticMethod(Method method, Object... args) throws Exception {
/* 203 */     return this.invokeMethod.invoke(this.driver, new Object[] { null, method, args });
/*     */   }
/*     */   
/*     */   private static interface ClassFinder {
/*     */     Class<?> findClass(String param1String) throws Exception;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\reflection\JVMDriverReflectionDriver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */