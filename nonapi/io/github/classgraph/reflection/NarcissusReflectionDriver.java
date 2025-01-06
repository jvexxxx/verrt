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
/*     */ class NarcissusReflectionDriver
/*     */   extends ReflectionDriver
/*     */ {
/*     */   private final Class<?> narcissusClass;
/*     */   private final Method getDeclaredMethods;
/*     */   private final Method findClass;
/*     */   private final Method getDeclaredConstructors;
/*     */   private final Method getDeclaredFields;
/*     */   private final Method getField;
/*     */   private final Method setField;
/*     */   private final Method getStaticField;
/*     */   private final Method setStaticField;
/*     */   private final Method invokeMethod;
/*     */   private final Method invokeStaticMethod;
/*     */   
/*     */   NarcissusReflectionDriver() throws Exception {
/*  56 */     StandardReflectionDriver drv = new StandardReflectionDriver();
/*  57 */     this.narcissusClass = drv.findClass("io.github.toolfactory.narcissus.Narcissus");
/*  58 */     if (!((Boolean)drv.getStaticField(drv.findStaticField(this.narcissusClass, "libraryLoaded"))).booleanValue()) {
/*  59 */       throw new IllegalArgumentException("Could not load Narcissus native library");
/*     */     }
/*     */ 
/*     */     
/*  63 */     this.findClass = drv.findStaticMethod(this.narcissusClass, "findClass", new Class[] { String.class });
/*  64 */     this.getDeclaredMethods = drv.findStaticMethod(this.narcissusClass, "getDeclaredMethods", new Class[] { Class.class });
/*  65 */     this.getDeclaredConstructors = drv.findStaticMethod(this.narcissusClass, "getDeclaredConstructors", new Class[] { Class.class });
/*  66 */     this.getDeclaredFields = drv.findStaticMethod(this.narcissusClass, "getDeclaredFields", new Class[] { Class.class });
/*  67 */     this.getField = drv.findStaticMethod(this.narcissusClass, "getField", new Class[] { Object.class, Field.class });
/*  68 */     this.setField = drv.findStaticMethod(this.narcissusClass, "setField", new Class[] { Object.class, Field.class, Object.class });
/*  69 */     this.getStaticField = drv.findStaticMethod(this.narcissusClass, "getStaticField", new Class[] { Field.class });
/*  70 */     this.setStaticField = drv.findStaticMethod(this.narcissusClass, "setStaticField", new Class[] { Field.class, Object.class });
/*  71 */     this.invokeMethod = drv.findStaticMethod(this.narcissusClass, "invokeMethod", new Class[] { Object.class, Method.class, Object[].class });
/*     */     
/*  73 */     this.invokeStaticMethod = drv.findStaticMethod(this.narcissusClass, "invokeStaticMethod", new Class[] { Method.class, Object[].class });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAccessible(Object instance, AccessibleObject obj) {
/*  79 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean makeAccessible(Object instance, AccessibleObject accessibleObject) {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   Class<?> findClass(String className) throws Exception {
/*  89 */     return (Class)this.findClass.invoke(null, new Object[] { className });
/*     */   }
/*     */ 
/*     */   
/*     */   Method[] getDeclaredMethods(Class<?> cls) throws Exception {
/*  94 */     return (Method[])this.getDeclaredMethods.invoke(null, new Object[] { cls });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   <T> Constructor<T>[] getDeclaredConstructors(Class<T> cls) throws Exception {
/* 100 */     return (Constructor<T>[])this.getDeclaredConstructors.invoke(null, new Object[] { cls });
/*     */   }
/*     */ 
/*     */   
/*     */   Field[] getDeclaredFields(Class<?> cls) throws Exception {
/* 105 */     return (Field[])this.getDeclaredFields.invoke(null, new Object[] { cls });
/*     */   }
/*     */ 
/*     */   
/*     */   Object getField(Object object, Field field) throws Exception {
/* 110 */     return this.getField.invoke(null, new Object[] { object, field });
/*     */   }
/*     */ 
/*     */   
/*     */   void setField(Object object, Field field, Object value) throws Exception {
/* 115 */     this.setField.invoke(null, new Object[] { object, field, value });
/*     */   }
/*     */ 
/*     */   
/*     */   Object getStaticField(Field field) throws Exception {
/* 120 */     return this.getStaticField.invoke(null, new Object[] { field });
/*     */   }
/*     */ 
/*     */   
/*     */   void setStaticField(Field field, Object value) throws Exception {
/* 125 */     this.setStaticField.invoke(null, new Object[] { field, value });
/*     */   }
/*     */ 
/*     */   
/*     */   Object invokeMethod(Object object, Method method, Object... args) throws Exception {
/* 130 */     return this.invokeMethod.invoke(null, new Object[] { object, method, args });
/*     */   }
/*     */ 
/*     */   
/*     */   Object invokeStaticMethod(Method method, Object... args) throws Exception {
/* 135 */     return this.invokeStaticMethod.invoke(null, new Object[] { method, args });
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\reflection\NarcissusReflectionDriver.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */