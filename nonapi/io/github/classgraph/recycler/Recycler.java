/*     */ package nonapi.io.github.classgraph.recycler;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Recycler<T, E extends Exception>
/*     */   implements AutoCloseable
/*     */ {
/*  49 */   private final Set<T> usedInstances = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*     */ 
/*     */   
/*  52 */   private final Queue<T> unusedInstances = new ConcurrentLinkedQueue<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract T newInstance() throws E;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T acquire() throws E {
/*  76 */     T instance, recycledInstance = this.unusedInstances.poll();
/*  77 */     if (recycledInstance == null) {
/*     */       
/*  79 */       T newInstance = newInstance();
/*  80 */       if (newInstance == null) {
/*  81 */         throw (E)new NullPointerException("Failed to allocate a new recyclable instance");
/*     */       }
/*  83 */       instance = newInstance;
/*     */     } else {
/*     */       
/*  86 */       instance = recycledInstance;
/*     */     } 
/*  88 */     this.usedInstances.add(instance);
/*  89 */     return instance;
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
/*     */   public RecycleOnClose<T, E> acquireRecycleOnClose() throws E {
/* 101 */     return new RecycleOnClose<>(this, acquire());
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
/*     */   public final void recycle(T instance) {
/* 114 */     if (instance != null) {
/* 115 */       if (!this.usedInstances.remove(instance)) {
/* 116 */         throw new IllegalArgumentException("Tried to recycle an instance that was not in use");
/*     */       }
/* 118 */       if (instance instanceof Resettable) {
/* 119 */         ((Resettable)instance).reset();
/*     */       }
/* 121 */       if (!this.unusedInstances.add(instance)) {
/* 122 */         throw new IllegalArgumentException("Tried to recycle an instance twice");
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
/*     */   public void close() {
/*     */     T unusedInstance;
/* 138 */     while ((unusedInstance = this.unusedInstances.poll()) != null) {
/* 139 */       if (unusedInstance instanceof AutoCloseable) {
/*     */         try {
/* 141 */           ((AutoCloseable)unusedInstance).close();
/* 142 */         } catch (Exception exception) {}
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
/*     */   public void forceClose() {
/* 156 */     for (T usedInstance : new ArrayList(this.usedInstances)) {
/* 157 */       if (this.usedInstances.remove(usedInstance)) {
/* 158 */         this.unusedInstances.add(usedInstance);
/*     */       }
/*     */     } 
/* 161 */     close();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\recycler\Recycler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */