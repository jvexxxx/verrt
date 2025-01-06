/*     */ package nonapi.io.github.classgraph.concurrency;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.CancellationException;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
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
/*     */ public class WorkQueue<T>
/*     */   implements AutoCloseable
/*     */ {
/*     */   private final WorkUnitProcessor<T> workUnitProcessor;
/*  55 */   private final BlockingQueue<WorkUnitWrapper<T>> workUnits = new LinkedBlockingQueue<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int numWorkers;
/*     */ 
/*     */ 
/*     */   
/*  64 */   private final AtomicInteger numIncompleteWorkUnits = new AtomicInteger();
/*     */ 
/*     */   
/*  67 */   private final ConcurrentLinkedQueue<Future<?>> workerFutures = new ConcurrentLinkedQueue<>();
/*     */ 
/*     */ 
/*     */   
/*     */   private final InterruptionChecker interruptionChecker;
/*     */ 
/*     */ 
/*     */   
/*     */   private final LogNode log;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static interface WorkUnitProcessor<T>
/*     */   {
/*     */     void processWorkUnit(T param1T, WorkQueue<T> param1WorkQueue, LogNode param1LogNode) throws InterruptedException;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class WorkUnitWrapper<T>
/*     */   {
/*     */     final T workUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public WorkUnitWrapper(T workUnit) {
/*  96 */       this.workUnit = workUnit;
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
/*     */   public static <U> void runWorkQueue(Collection<U> elements, ExecutorService executorService, InterruptionChecker interruptionChecker, int numParallelTasks, LogNode log, WorkUnitProcessor<U> workUnitProcessor) throws InterruptedException, ExecutionException {
/* 148 */     if (elements.isEmpty()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 154 */     WorkQueue<U> workQueue = new WorkQueue<>(elements, workUnitProcessor, numParallelTasks, interruptionChecker, log);
/*     */     
/*     */     try {
/* 157 */       workQueue.startWorkers(executorService, numParallelTasks - 1);
/*     */ 
/*     */ 
/*     */       
/* 161 */       workQueue.runWorkLoop();
/* 162 */       workQueue.close();
/*     */     } catch (Throwable throwable) {
/*     */       try {
/*     */         workQueue.close();
/*     */       } catch (Throwable throwable1) {
/*     */         throwable.addSuppressed(throwable1);
/*     */       } 
/*     */       throw throwable;
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
/*     */   private WorkQueue(Collection<T> initialWorkUnits, WorkUnitProcessor<T> workUnitProcessor, int numWorkers, InterruptionChecker interruptionChecker, LogNode log) {
/* 181 */     this.workUnitProcessor = workUnitProcessor;
/* 182 */     this.numWorkers = numWorkers;
/* 183 */     this.interruptionChecker = interruptionChecker;
/* 184 */     this.log = log;
/* 185 */     addWorkUnits(initialWorkUnits);
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
/*     */   private void startWorkers(ExecutorService executorService, int numTasks) {
/* 197 */     for (int i = 0; i < numTasks; i++) {
/* 198 */       this.workerFutures.add(executorService.submit(new Callable<Void>()
/*     */             {
/*     */               public Void call() throws Exception {
/* 201 */                 WorkQueue.this.runWorkLoop();
/* 202 */                 return null;
/*     */               }
/*     */             }));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void sendPoisonPills() {
/* 213 */     for (int i = 0; i < this.numWorkers; i++) {
/* 214 */       this.workUnits.add(new WorkUnitWrapper<>(null));
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
/*     */   private void runWorkLoop() throws InterruptedException, ExecutionException {
/*     */     while (true) {
/*     */       try {
/* 235 */         this.interruptionChecker.check();
/*     */ 
/*     */         
/* 238 */         WorkUnitWrapper<T> workUnitWrapper = this.workUnits.take();
/*     */         
/* 240 */         if (workUnitWrapper.workUnit == null) {
/*     */           break;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 246 */         this.workUnitProcessor.processWorkUnit(workUnitWrapper.workUnit, this, this.log);
/*     */       }
/* 248 */       catch (InterruptedException|Error e) {
/*     */         
/* 250 */         this.workUnits.clear();
/* 251 */         this.numIncompleteWorkUnits.set(0);
/* 252 */         sendPoisonPills();
/* 253 */         throw e;
/*     */       }
/* 255 */       catch (RuntimeException e) {
/*     */         
/* 257 */         this.workUnits.clear();
/* 258 */         this.numIncompleteWorkUnits.set(0);
/* 259 */         sendPoisonPills();
/* 260 */         throw new ExecutionException("Worker thread threw unchecked exception", e);
/*     */       } 
/*     */       
/* 263 */       if (this.numIncompleteWorkUnits.decrementAndGet() == 0)
/*     */       {
/* 265 */         sendPoisonPills();
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
/*     */   public void addWorkUnit(T workUnit) {
/* 279 */     if (workUnit == null) {
/* 280 */       throw new NullPointerException("workUnit cannot be null");
/*     */     }
/* 282 */     this.numIncompleteWorkUnits.incrementAndGet();
/* 283 */     this.workUnits.add(new WorkUnitWrapper<>(workUnit));
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
/*     */   public void addWorkUnits(Collection<T> workUnits) {
/* 295 */     for (T workUnit : workUnits) {
/* 296 */       addWorkUnit(workUnit);
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
/*     */   public void close() throws ExecutionException {
/*     */     Future<?> future;
/* 309 */     while ((future = this.workerFutures.poll()) != null) {
/*     */       
/*     */       try {
/* 312 */         future.get();
/* 313 */       } catch (CancellationException e) {
/* 314 */         if (this.log != null) {
/* 315 */           this.log.log("~", "Worker thread was cancelled");
/*     */         }
/* 317 */       } catch (InterruptedException e) {
/* 318 */         if (this.log != null) {
/* 319 */           this.log.log("~", "Worker thread was interrupted");
/*     */         }
/*     */         
/* 322 */         this.interruptionChecker.interrupt();
/* 323 */       } catch (ExecutionException e) {
/* 324 */         this.interruptionChecker.setExecutionException(e);
/* 325 */         this.interruptionChecker.interrupt();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\concurrency\WorkQueue.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */