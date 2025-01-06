/*     */ package nonapi.io.github.classgraph.concurrency;
/*     */ 
/*     */ import java.util.concurrent.CancellationException;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutoCloseableExecutorService
/*     */   extends ThreadPoolExecutor
/*     */   implements AutoCloseable
/*     */ {
/*  41 */   public final InterruptionChecker interruptionChecker = new InterruptionChecker();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AutoCloseableExecutorService(int numThreads) {
/*  50 */     super(numThreads, numThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new SimpleThreadFactory("ClassGraph-worker-", true));
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
/*     */   public void afterExecute(Runnable runnable, Throwable throwable) {
/*  65 */     super.afterExecute(runnable, throwable);
/*  66 */     if (throwable != null) {
/*     */       
/*  68 */       this.interruptionChecker.setExecutionException(new ExecutionException("Uncaught exception", throwable));
/*     */       
/*  70 */       this.interruptionChecker.interrupt();
/*  71 */     } else if (runnable instanceof Future) {
/*     */ 
/*     */       
/*     */       try {
/*  75 */         ((Future)runnable).get();
/*  76 */       } catch (CancellationException|InterruptedException e) {
/*     */         
/*  78 */         this.interruptionChecker.interrupt();
/*  79 */       } catch (ExecutionException e) {
/*     */         
/*  81 */         this.interruptionChecker.setExecutionException(e);
/*     */         
/*  83 */         this.interruptionChecker.interrupt();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/*     */     try {
/*  93 */       shutdown();
/*  94 */     } catch (SecurityException securityException) {}
/*     */ 
/*     */     
/*  97 */     boolean terminated = false;
/*     */     
/*     */     try {
/* 100 */       terminated = awaitTermination(2500L, TimeUnit.MILLISECONDS);
/* 101 */     } catch (InterruptedException e) {
/* 102 */       this.interruptionChecker.interrupt();
/*     */     } 
/* 104 */     if (!terminated)
/*     */       
/*     */       try {
/* 107 */         shutdownNow();
/* 108 */       } catch (SecurityException e) {
/* 109 */         throw new RuntimeException("Could not shut down ExecutorService -- need java.lang.RuntimePermission(\"modifyThread\"), or the security manager's checkAccess method denies access", e);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\concurrency\AutoCloseableExecutorService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */