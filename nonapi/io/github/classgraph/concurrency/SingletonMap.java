/*     */ package nonapi.io.github.classgraph.concurrency;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.concurrent.CountDownLatch;
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
/*     */ public abstract class SingletonMap<K, V, E extends Exception>
/*     */ {
/*  55 */   private final ConcurrentMap<K, SingletonHolder<V>> map = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract V newInstance(K paramK, LogNode paramLogNode) throws E, InterruptedException;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class NullSingletonException
/*     */     extends Exception
/*     */   {
/*     */     static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */     
/*     */     public <K> NullSingletonException(K key) {
/*  73 */       super("newInstance returned null for key " + key);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class NewInstanceException
/*     */     extends Exception
/*     */   {
/*     */     static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public <K> NewInstanceException(K key, Throwable t) {
/*  93 */       super("newInstance threw an exception for key " + key + " : " + t, t);
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
/*     */   private static class SingletonHolder<V>
/*     */   {
/*     */     private volatile V singleton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     private final CountDownLatch initialized = new CountDownLatch(1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void set(V singleton) throws IllegalArgumentException {
/* 125 */       if (this.initialized.getCount() < 1L)
/*     */       {
/* 127 */         throw new IllegalArgumentException("Singleton already initialized");
/*     */       }
/* 129 */       this.singleton = singleton;
/* 130 */       this.initialized.countDown();
/* 131 */       if (this.initialized.getCount() != 0L)
/*     */       {
/* 133 */         throw new IllegalArgumentException("Singleton initialized more than once");
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
/*     */     V get() throws InterruptedException {
/* 145 */       this.initialized.await();
/* 146 */       return this.singleton;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private SingletonHolder() {}
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
/*     */   public V get(K key, LogNode log, NewInstanceFactory<V, E> newInstanceFactory) throws E, InterruptedException, NullSingletonException, NewInstanceException {
/* 214 */     SingletonHolder<V> singletonHolder = this.map.get(key);
/*     */     
/* 216 */     V instance = null;
/* 217 */     if (singletonHolder != null) {
/*     */       
/* 219 */       instance = singletonHolder.get();
/*     */     }
/*     */     else {
/*     */       
/* 223 */       SingletonHolder<V> newSingletonHolder = new SingletonHolder<>();
/* 224 */       SingletonHolder<V> oldSingletonHolder = this.map.putIfAbsent(key, newSingletonHolder);
/* 225 */       if (oldSingletonHolder != null) {
/*     */ 
/*     */         
/* 228 */         instance = oldSingletonHolder.get();
/*     */       } else {
/*     */         
/*     */         try {
/* 232 */           if (newInstanceFactory != null) {
/*     */             
/* 234 */             instance = newInstanceFactory.newInstance();
/*     */           } else {
/*     */             
/* 237 */             instance = newInstance(key, log);
/*     */           }
/*     */         
/* 240 */         } catch (Throwable t) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 245 */           newSingletonHolder.set(instance);
/* 246 */           throw new NewInstanceException(key, t);
/*     */         } 
/* 248 */         newSingletonHolder.set(instance);
/*     */       } 
/*     */     } 
/* 251 */     if (instance == null) {
/* 252 */       throw new NullSingletonException(key);
/*     */     }
/* 254 */     return instance;
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
/*     */   public V get(K key, LogNode log) throws E, InterruptedException, NullSingletonException, NewInstanceException {
/* 286 */     return get(key, log, null);
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
/*     */   public List<V> values() throws InterruptedException {
/* 298 */     List<V> entries = new ArrayList<>(this.map.size());
/* 299 */     for (Map.Entry<K, SingletonHolder<V>> ent : this.map.entrySet()) {
/* 300 */       V entryValue = ((SingletonHolder<V>)ent.getValue()).get();
/* 301 */       if (entryValue != null) {
/* 302 */         entries.add(entryValue);
/*     */       }
/*     */     } 
/* 305 */     return entries;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 314 */     return this.map.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Map.Entry<K, V>> entries() throws InterruptedException {
/* 325 */     List<Map.Entry<K, V>> entries = new ArrayList<>(this.map.size());
/* 326 */     for (Map.Entry<K, SingletonHolder<V>> ent : this.map.entrySet()) {
/* 327 */       entries.add(new AbstractMap.SimpleEntry<>(ent.getKey(), ((SingletonHolder<V>)ent.getValue()).get()));
/*     */     }
/* 329 */     return entries;
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
/*     */   public V remove(K key) throws InterruptedException {
/* 343 */     SingletonHolder<V> val = this.map.remove(key);
/* 344 */     return (val == null) ? null : val.get();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 349 */     this.map.clear();
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface NewInstanceFactory<V, E extends Exception> {
/*     */     V newInstance() throws E, InterruptedException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\concurrency\SingletonMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */