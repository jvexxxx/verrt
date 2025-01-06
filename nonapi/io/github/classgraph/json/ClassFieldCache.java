/*     */ package nonapi.io.github.classgraph.json;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.AbstractList;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.AbstractQueue;
/*     */ import java.util.AbstractSequentialList;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Deque;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.NavigableMap;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import java.util.concurrent.BlockingDeque;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.concurrent.ConcurrentNavigableMap;
/*     */ import java.util.concurrent.ConcurrentSkipListMap;
/*     */ import java.util.concurrent.LinkedBlockingDeque;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.LinkedTransferQueue;
/*     */ import java.util.concurrent.TransferQueue;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ClassFieldCache
/*     */ {
/*  73 */   private final Map<Class<?>, ClassFields> classToClassFields = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean resolveTypes;
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean onlySerializePublicFields;
/*     */ 
/*     */ 
/*     */   
/*  85 */   private final Map<Class<?>, Constructor<?>> defaultConstructorForConcreteType = new HashMap<>();
/*     */ 
/*     */   
/*  88 */   private final Map<Class<?>, Constructor<?>> constructorForConcreteTypeWithSizeHint = new HashMap<>();
/*     */   
/*     */   private static final Constructor<?> NO_CONSTRUCTOR;
/*     */   
/*     */   ReflectionUtils reflectionUtils;
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  97 */       NO_CONSTRUCTOR = NoConstructor.class.getDeclaredConstructor(new Class[0]);
/*  98 */     } catch (NoSuchMethodException|SecurityException e) {
/*     */       
/* 100 */       throw new RuntimeException("Could not find or access constructor for " + NoConstructor.class.getName(), e);
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
/*     */   private static class NoConstructor {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ClassFieldCache(boolean forDeserialization, boolean onlySerializePublicFields, ReflectionUtils reflectionUtils) {
/* 125 */     this.resolveTypes = forDeserialization;
/* 126 */     this.onlySerializePublicFields = (!forDeserialization && onlySerializePublicFields);
/* 127 */     this.reflectionUtils = reflectionUtils;
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
/*     */   ClassFields get(Class<?> cls) {
/* 139 */     ClassFields classFields = this.classToClassFields.get(cls);
/* 140 */     if (classFields == null) {
/* 141 */       this.classToClassFields.put(cls, classFields = new ClassFields(cls, this.resolveTypes, this.onlySerializePublicFields, this, this.reflectionUtils));
/*     */     }
/*     */     
/* 144 */     return classFields;
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
/*     */   private static Class<?> getConcreteType(Class<?> rawType, boolean returnNullIfNotMapOrCollection) {
/* 159 */     if (rawType == Map.class || rawType == AbstractMap.class || rawType == HashMap.class)
/* 160 */       return HashMap.class; 
/* 161 */     if (rawType == ConcurrentMap.class || rawType == ConcurrentHashMap.class)
/* 162 */       return ConcurrentHashMap.class; 
/* 163 */     if (rawType == SortedMap.class || rawType == NavigableMap.class || rawType == TreeMap.class)
/* 164 */       return TreeMap.class; 
/* 165 */     if (rawType == ConcurrentNavigableMap.class || rawType == ConcurrentSkipListMap.class)
/* 166 */       return ConcurrentSkipListMap.class; 
/* 167 */     if (rawType == List.class || rawType == AbstractList.class || rawType == ArrayList.class || rawType == Collection.class)
/*     */     {
/* 169 */       return ArrayList.class; } 
/* 170 */     if (rawType == AbstractSequentialList.class || rawType == LinkedList.class)
/* 171 */       return LinkedList.class; 
/* 172 */     if (rawType == Set.class || rawType == AbstractSet.class || rawType == HashSet.class)
/* 173 */       return HashSet.class; 
/* 174 */     if (rawType == SortedSet.class || rawType == TreeSet.class)
/* 175 */       return TreeSet.class; 
/* 176 */     if (rawType == Queue.class || rawType == AbstractQueue.class || rawType == Deque.class || rawType == ArrayDeque.class)
/*     */     {
/* 178 */       return ArrayDeque.class; } 
/* 179 */     if (rawType == BlockingQueue.class || rawType == LinkedBlockingQueue.class)
/* 180 */       return LinkedBlockingQueue.class; 
/* 181 */     if (rawType == BlockingDeque.class || rawType == LinkedBlockingDeque.class)
/* 182 */       return LinkedBlockingDeque.class; 
/* 183 */     if (rawType == TransferQueue.class || rawType == LinkedTransferQueue.class) {
/* 184 */       return LinkedTransferQueue.class;
/*     */     }
/* 186 */     return returnNullIfNotMapOrCollection ? null : rawType;
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
/*     */   Constructor<?> getDefaultConstructorForConcreteTypeOf(Class<?> cls) {
/* 200 */     if (cls == null) {
/* 201 */       throw new IllegalArgumentException("Class reference cannot be null");
/*     */     }
/*     */     
/* 204 */     Constructor<?> constructor = this.defaultConstructorForConcreteType.get(cls);
/* 205 */     if (constructor != null) {
/* 206 */       return constructor;
/*     */     }
/* 208 */     Class<?> concreteType = getConcreteType(cls, false);
/* 209 */     Class<?> c = concreteType;
/* 210 */     for (; c != null && (c != Object.class || cls == Object.class); c = c.getSuperclass()) {
/*     */       try {
/* 212 */         Constructor<?> defaultConstructor = c.getDeclaredConstructor(new Class[0]);
/* 213 */         JSONUtils.makeAccessible(defaultConstructor, this.reflectionUtils);
/*     */         
/* 215 */         this.defaultConstructorForConcreteType.put(cls, defaultConstructor);
/* 216 */         return defaultConstructor;
/* 217 */       } catch (ReflectiveOperationException|SecurityException reflectiveOperationException) {}
/*     */     } 
/*     */ 
/*     */     
/* 221 */     throw new IllegalArgumentException("Class " + cls.getName() + " does not have an accessible default (no-arg) constructor");
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
/*     */   Constructor<?> getConstructorWithSizeHintForConcreteTypeOf(Class<?> cls) {
/* 236 */     Constructor<?> constructor = this.constructorForConcreteTypeWithSizeHint.get(cls);
/* 237 */     if (constructor == NO_CONSTRUCTOR)
/* 238 */       return null; 
/* 239 */     if (constructor != null) {
/* 240 */       return constructor;
/*     */     }
/* 242 */     Class<?> concreteType = getConcreteType(cls, true);
/* 243 */     if (concreteType != null) {
/* 244 */       Class<?> c = concreteType;
/* 245 */       for (; c != null && (c != Object.class || cls == Object.class); c = c.getSuperclass()) {
/*     */         try {
/* 247 */           Constructor<?> constructorWithSizeHint = c.getDeclaredConstructor(new Class[] { int.class });
/* 248 */           JSONUtils.makeAccessible(constructorWithSizeHint, this.reflectionUtils);
/*     */           
/* 250 */           this.constructorForConcreteTypeWithSizeHint.put(cls, constructorWithSizeHint);
/* 251 */           return constructorWithSizeHint;
/* 252 */         } catch (ReflectiveOperationException|SecurityException reflectiveOperationException) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 257 */     this.constructorForConcreteTypeWithSizeHint.put(cls, NO_CONSTRUCTOR);
/* 258 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\ClassFieldCache.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */