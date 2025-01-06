/*    */ package nonapi.io.github.classgraph.concurrency;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ import java.util.concurrent.atomic.AtomicInteger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SimpleThreadFactory
/*    */   implements ThreadFactory
/*    */ {
/*    */   private final String threadNamePrefix;
/* 44 */   private static final AtomicInteger threadIdx = new AtomicInteger();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final boolean daemon;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   SimpleThreadFactory(String threadNamePrefix, boolean daemon) {
/* 58 */     this.threadNamePrefix = threadNamePrefix;
/* 59 */     this.daemon = daemon;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Thread newThread(Runnable runnable) {
/* 72 */     ThreadGroup securityManagerThreadGroup = null;
/*    */     try {
/* 74 */       Method getSecurityManager = System.class.getDeclaredMethod("getSecurityManager", new Class[0]);
/* 75 */       Object securityManager = getSecurityManager.invoke(null, new Object[0]);
/* 76 */       if (securityManager != null) {
/* 77 */         Method getThreadGroup = securityManager.getClass().getDeclaredMethod("getThreadGroup", new Class[0]);
/* 78 */         securityManagerThreadGroup = (ThreadGroup)getThreadGroup.invoke(securityManager, new Object[0]);
/*    */       } 
/* 80 */     } catch (Throwable throwable) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 86 */     Thread thread = new Thread((securityManagerThreadGroup != null) ? securityManagerThreadGroup : new ThreadGroup("ClassGraph-thread-group"), runnable, this.threadNamePrefix + threadIdx.getAndIncrement());
/* 87 */     thread.setDaemon(this.daemon);
/* 88 */     return thread;
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\concurrency\SimpleThreadFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */