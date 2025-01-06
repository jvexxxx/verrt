/*      */ package io.github.classgraph;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URI;
/*      */ import java.net.URISyntaxException;
/*      */ import java.net.URL;
/*      */ import java.nio.file.FileSystemNotFoundException;
/*      */ import java.nio.file.InvalidPathException;
/*      */ import java.nio.file.Path;
/*      */ import java.nio.file.Paths;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Queue;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.Callable;
/*      */ import java.util.concurrent.CancellationException;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import java.util.concurrent.ConcurrentLinkedQueue;
/*      */ import java.util.concurrent.ExecutionException;
/*      */ import java.util.concurrent.ExecutorService;
/*      */ import nonapi.io.github.classgraph.classpath.ClasspathFinder;
/*      */ import nonapi.io.github.classgraph.classpath.ClasspathOrder;
/*      */ import nonapi.io.github.classgraph.classpath.ModuleFinder;
/*      */ import nonapi.io.github.classgraph.concurrency.AutoCloseableExecutorService;
/*      */ import nonapi.io.github.classgraph.concurrency.InterruptionChecker;
/*      */ import nonapi.io.github.classgraph.concurrency.SingletonMap;
/*      */ import nonapi.io.github.classgraph.concurrency.WorkQueue;
/*      */ import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
/*      */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*      */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*      */ import nonapi.io.github.classgraph.utils.CollectionUtils;
/*      */ import nonapi.io.github.classgraph.utils.FastPathResolver;
/*      */ import nonapi.io.github.classgraph.utils.FileUtils;
/*      */ import nonapi.io.github.classgraph.utils.JarUtils;
/*      */ import nonapi.io.github.classgraph.utils.LogNode;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class Scanner
/*      */   implements Callable<ScanResult>
/*      */ {
/*      */   private final ScanSpec scanSpec;
/*      */   public boolean performScan;
/*      */   private final NestedJarHandler nestedJarHandler;
/*      */   private final ExecutorService executorService;
/*      */   private final InterruptionChecker interruptionChecker;
/*      */   private final int numParallelTasks;
/*      */   private final ClassGraph.ScanResultProcessor scanResultProcessor;
/*      */   private final ClassGraph.FailureHandler failureHandler;
/*      */   private final LogNode topLevelLog;
/*      */   private final ClasspathFinder classpathFinder;
/*      */   private final List<ClasspathElementModule> moduleOrder;
/*      */   private final SingletonMap<Object, ClasspathElement, IOException> classpathEntryObjToClasspathEntrySingletonMap;
/*      */   
/*      */   Scanner(boolean performScan, ScanSpec scanSpec, ExecutorService executorService, int numParallelTasks, ClassGraph.ScanResultProcessor scanResultProcessor, ClassGraph.FailureHandler failureHandler, ReflectionUtils reflectionUtils, LogNode topLevelLog) throws InterruptedException {
/*  503 */     this.classpathEntryObjToClasspathEntrySingletonMap = new SingletonMap<Object, ClasspathElement, IOException>()
/*      */       {
/*      */ 
/*      */ 
/*      */         
/*      */         public ClasspathElement newInstance(Object classpathEntryObj, LogNode log) throws IOException, InterruptedException
/*      */         {
/*  510 */           throw new IOException("Should not reach here"); }
/*      */       }; this.scanSpec = scanSpec; this.performScan = performScan; scanSpec.sortPrefixes(); scanSpec.log(topLevelLog); if (topLevelLog != null) { if (scanSpec.pathAcceptReject != null && scanSpec.packagePrefixAcceptReject.isSpecificallyAccepted(""))
/*      */         topLevelLog.log("Note: There is no need to accept the root package (\"\") -- not accepting anything will have the same effect of causing all packages to be scanned");  topLevelLog.log("Number of worker threads: " + numParallelTasks); }  this.executorService = executorService; this.interruptionChecker = (executorService instanceof AutoCloseableExecutorService) ? ((AutoCloseableExecutorService)executorService).interruptionChecker : new InterruptionChecker(); this.nestedJarHandler = new NestedJarHandler(scanSpec, this.interruptionChecker, reflectionUtils); this.numParallelTasks = numParallelTasks; this.scanResultProcessor = scanResultProcessor; this.failureHandler = failureHandler; this.topLevelLog = topLevelLog; LogNode classpathFinderLog = (topLevelLog == null) ? null : topLevelLog.log("Finding classpath"); this.classpathFinder = new ClasspathFinder(scanSpec, reflectionUtils, classpathFinderLog); try { this.moduleOrder = new ArrayList<>(); ModuleFinder moduleFinder = this.classpathFinder.getModuleFinder(); if (moduleFinder != null) { List<ModuleRef> systemModuleRefs = moduleFinder.getSystemModuleRefs(); ClassLoader[] classLoaderOrderRespectingParentDelegation = this.classpathFinder.getClassLoaderOrderRespectingParentDelegation(); ClassLoader defaultClassLoader = (classLoaderOrderRespectingParentDelegation != null && classLoaderOrderRespectingParentDelegation.length != 0) ? classLoaderOrderRespectingParentDelegation[0] : null; if (systemModuleRefs != null)
/*      */           for (ModuleRef systemModuleRef : systemModuleRefs) { String moduleName = systemModuleRef.getName(); if ((scanSpec.enableSystemJarsAndModules && scanSpec.moduleAcceptReject.acceptAndRejectAreEmpty()) || scanSpec.moduleAcceptReject.isSpecificallyAcceptedAndNotRejected(moduleName)) { ClasspathElementModule classpathElementModule = new ClasspathElementModule(systemModuleRef, this.nestedJarHandler.moduleRefToModuleReaderProxyRecyclerMap, new ClasspathEntryWorkUnit(null, defaultClassLoader, null, this.moduleOrder.size(), ""), scanSpec); this.moduleOrder.add(classpathElementModule); classpathElementModule.open((WorkQueue<ClasspathEntryWorkUnit>)null, classpathFinderLog); continue; }
/*      */              if (classpathFinderLog != null)
/*      */               classpathFinderLog.log("Skipping non-accepted or rejected system module: " + moduleName);  }
/*      */             List<ModuleRef> nonSystemModuleRefs = moduleFinder.getNonSystemModuleRefs(); if (nonSystemModuleRefs != null)
/*      */           for (ModuleRef nonSystemModuleRef : nonSystemModuleRefs) { String moduleName = nonSystemModuleRef.getName(); if (moduleName == null)
/*      */               moduleName = "";  if (scanSpec.moduleAcceptReject.isAcceptedAndNotRejected(moduleName)) { ClasspathElementModule classpathElementModule = new ClasspathElementModule(nonSystemModuleRef, this.nestedJarHandler.moduleRefToModuleReaderProxyRecyclerMap, new ClasspathEntryWorkUnit(null, defaultClassLoader, null, this.moduleOrder.size(), ""), scanSpec); this.moduleOrder.add(classpathElementModule); classpathElementModule.open((WorkQueue<ClasspathEntryWorkUnit>)null, classpathFinderLog); continue; }
/*      */              if (classpathFinderLog != null)
/*      */               classpathFinderLog.log("Skipping non-accepted or rejected module: " + moduleName);  }
/*      */             }
/*      */        }
/*      */     catch (InterruptedException e) { this.nestedJarHandler.close(null); throw e; }
/*      */   
/*      */   } private static void findClasspathOrderRec(ClasspathElement currClasspathElement, Set<ClasspathElement> visitedClasspathElts, List<ClasspathElement> order) { if (visitedClasspathElts.add(currClasspathElement)) { if (!currClasspathElement.skipClasspathElement)
/*      */         order.add(currClasspathElement);  List<ClasspathElement> childClasspathElementsSorted = CollectionUtils.sortCopy(currClasspathElement.childClasspathElements); for (ClasspathElement childClasspathElt : childClasspathElementsSorted)
/*      */         findClasspathOrderRec(childClasspathElt, visitedClasspathElts, order);  }
/*      */      } private List<ClasspathElement> findClasspathOrder(Set<ClasspathElement> toplevelClasspathElts) { List<ClasspathElement> toplevelClasspathEltsSorted = CollectionUtils.sortCopy(toplevelClasspathElts); Set<ClasspathElement> visitedClasspathElts = new HashSet<>(); List<ClasspathElement> order = new ArrayList<>(); for (ClasspathElement elt : toplevelClasspathEltsSorted)
/*  529 */       findClasspathOrderRec(elt, visitedClasspathElts, order);  return order; } private WorkQueue.WorkUnitProcessor<ClasspathEntryWorkUnit> newClasspathEntryWorkUnitProcessor(final Set<ClasspathElement> allClasspathEltsOut, final Set<ClasspathElement> toplevelClasspathEltsOut) { return new WorkQueue.WorkUnitProcessor<ClasspathEntryWorkUnit>()
/*      */       {
/*      */         public void processWorkUnit(final Scanner.ClasspathEntryWorkUnit workUnit, final WorkQueue<Scanner.ClasspathEntryWorkUnit> workQueue, final LogNode log) throws InterruptedException
/*      */         {
/*      */           try {
/*      */             final boolean isJar;
/*      */             
/*  536 */             workUnit.classpathEntryObj = Scanner.normalizeClasspathEntry(workUnit.classpathEntryObj);
/*      */ 
/*      */ 
/*      */             
/*  540 */             if (workUnit.classpathEntryObj instanceof URL || workUnit.classpathEntryObj instanceof URI) {
/*      */               
/*  542 */               isJar = true;
/*  543 */             } else if (workUnit.classpathEntryObj instanceof Path) {
/*  544 */               Path path = (Path)workUnit.classpathEntryObj;
/*  545 */               if ("JrtFileSystem".equals(path.getFileSystem().getClass().getSimpleName()))
/*      */               {
/*      */                 
/*  548 */                 throw new IOException("Ignoring JrtFS filesystem path (modules are scanned using the JPMS API): " + path);
/*      */               }
/*      */               
/*  551 */               if (FileUtils.canReadAndIsFile(path))
/*      */               
/*  553 */               { isJar = true; }
/*  554 */               else if (FileUtils.canReadAndIsDir(path))
/*      */               
/*  556 */               { isJar = false; }
/*  557 */               else { if (!FileUtils.canRead(path)) {
/*  558 */                   throw new IOException("Cannot read path: " + path);
/*      */                 }
/*  560 */                 throw new IOException("Not a file or directory: " + path); }
/*      */             
/*      */             } else {
/*      */               
/*  564 */               throw new IOException("Got unexpected classpath entry object type " + workUnit.classpathEntryObj
/*  565 */                   .getClass().getName() + " : " + workUnit.classpathEntryObj);
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  572 */             Scanner.this.classpathEntryObjToClasspathEntrySingletonMap.get(workUnit.classpathEntryObj, log, new SingletonMap.NewInstanceFactory<ClasspathElement, IOException>()
/*      */                 {
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*      */                   public ClasspathElement newInstance() throws IOException, InterruptedException
/*      */                   {
/*  580 */                     ClasspathElement classpathElement = isJar ? new ClasspathElementZip(workUnit, Scanner.this.nestedJarHandler, Scanner.this.scanSpec) : new ClasspathElementDir(workUnit, Scanner.this.nestedJarHandler, Scanner.this.scanSpec);
/*      */                     
/*  582 */                     allClasspathEltsOut.add(classpathElement);
/*      */ 
/*      */ 
/*      */                     
/*  586 */                     LogNode subLog = (log == null) ? null : log.log("Opening classpath element " + classpathElement);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/*  593 */                     classpathElement.open(workQueue, subLog);
/*      */                     
/*  595 */                     if (workUnit.parentClasspathElement != null) {
/*      */                       
/*  597 */                       workUnit.parentClasspathElement.childClasspathElements
/*  598 */                         .add(classpathElement);
/*      */                     } else {
/*  600 */                       toplevelClasspathEltsOut.add(classpathElement);
/*      */                     } 
/*      */                     
/*  603 */                     return classpathElement;
/*      */                   }
/*      */                 });
/*      */           }
/*  607 */           catch (Exception e) {
/*  608 */             Exception exception1; if (log != null)
/*  609 */               log.log("Skipping invalid classpath entry " + workUnit.classpathEntryObj + " : " + (
/*  610 */                   (exception1.getCause() == null) ? (String)exception1 : (String)exception1.getCause())); 
/*      */           } 
/*      */         }
/*      */       }; } private <W> void processWorkUnits(Collection<W> workUnits, LogNode log, WorkQueue.WorkUnitProcessor<W> workUnitProcessor) throws InterruptedException, ExecutionException { WorkQueue.runWorkQueue(workUnits, this.executorService, this.interruptionChecker, this.numParallelTasks, log, workUnitProcessor); if (log != null)
/*      */       log.addElapsedTime();  this.interruptionChecker.check(); } static class ClasspathEntryWorkUnit {
/*      */     Object classpathEntryObj; final ClassLoader classLoader; final ClasspathElement parentClasspathElement; final int classpathElementIdxWithinParent; final String packageRootPrefix; public ClasspathEntryWorkUnit(Object classpathEntryObj, ClassLoader classLoader, ClasspathElement parentClasspathElement, int classpathElementIdxWithinParent, String packageRootPrefix) { this.classpathEntryObj = classpathEntryObj; this.classLoader = classLoader; this.parentClasspathElement = parentClasspathElement; this.classpathElementIdxWithinParent = classpathElementIdxWithinParent; this.packageRootPrefix = packageRootPrefix; } } private static Object normalizeClasspathEntry(Object classpathEntryObj) throws IOException { if (classpathEntryObj == null)
/*      */       throw new IOException("Got null classpath entry object");  Object classpathEntryObjNormalized = classpathEntryObj; if (!(classpathEntryObjNormalized instanceof Path))
/*      */       classpathEntryObjNormalized = FastPathResolver.resolve(FileUtils.currDirPath(), classpathEntryObjNormalized.toString());  if (classpathEntryObjNormalized instanceof String) { String classpathEntStr = (String)classpathEntryObjNormalized; boolean isURL = JarUtils.URL_SCHEME_PATTERN.matcher(classpathEntStr).matches(); boolean isMultiSection = classpathEntStr.contains("!"); if (isURL || isMultiSection) { classpathEntStr = classpathEntStr.replace(" ", "%20").replace("#", "%23"); if (!isURL)
/*      */           classpathEntStr = "file:" + classpathEntStr;  if (isMultiSection) { classpathEntStr = "jar:" + classpathEntStr; classpathEntStr = classpathEntStr.replaceAll("!([^/])", "!/$1"); }
/*      */          try { URL classpathEntryURL = new URL(classpathEntStr); classpathEntryObjNormalized = classpathEntryURL; if (!isMultiSection)
/*      */             try { String scheme = classpathEntryURL.getProtocol(); if (!"http".equals(scheme) && !"https".equals(scheme)) { URI classpathEntryURI = classpathEntryURL.toURI(); classpathEntryObjNormalized = Paths.get(classpathEntryURI); }
/*      */                }
/*      */             catch (URISyntaxException|IllegalArgumentException|SecurityException uRISyntaxException) {  }
/*      */             catch (FileSystemNotFoundException fileSystemNotFoundException) {}  }
/*      */         catch (MalformedURLException e) { try { URI classpathEntryURI = new URI(classpathEntStr); classpathEntryObjNormalized = classpathEntryURI; String scheme = classpathEntryURI.getScheme(); if (!"http".equals(scheme) && !"https".equals(scheme))
/*      */               classpathEntryObjNormalized = Paths.get(classpathEntryURI);  }
/*      */           catch (URISyntaxException e1) { throw new IOException("Malformed URI: " + classpathEntryObjNormalized + " : " + e1); }
/*      */           catch (IllegalArgumentException|SecurityException illegalArgumentException) {  }
/*      */           catch (FileSystemNotFoundException fileSystemNotFoundException) {} }
/*      */          }
/*      */        if (classpathEntryObjNormalized instanceof String)
/*      */         try { classpathEntryObjNormalized = (new File((String)classpathEntryObjNormalized)).toPath(); }
/*      */         catch (Exception e)
/*      */         { try {
/*      */             classpathEntryObjNormalized = Paths.get((String)classpathEntryObjNormalized, new String[0]);
/*      */           } catch (InvalidPathException e2) {
/*      */             throw new IOException("Malformed path: " + classpathEntryObj + " : " + e2);
/*      */           }  }
/*      */           }
/*      */      if (classpathEntryObjNormalized instanceof Path)
/*      */       try {
/*      */         classpathEntryObjNormalized = ((Path)classpathEntryObjNormalized).toRealPath(new java.nio.file.LinkOption[0]);
/*      */       } catch (IOException|SecurityException iOException) {}  return classpathEntryObjNormalized; } static class ClassfileScanWorkUnit {
/*  643 */     private final ClasspathElement classpathElement; ClassfileScanWorkUnit(ClasspathElement classpathElement, Resource classfileResource, boolean isExternalClass) { this.classpathElement = classpathElement;
/*  644 */       this.classfileResource = classfileResource;
/*  645 */       this.isExternalClass = isExternalClass; }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     private final Resource classfileResource;
/*      */ 
/*      */     
/*      */     private final boolean isExternalClass;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static class ClassfileScannerWorkUnitProcessor
/*      */     implements WorkQueue.WorkUnitProcessor<ClassfileScanWorkUnit>
/*      */   {
/*      */     private final ScanSpec scanSpec;
/*      */     
/*      */     private final List<ClasspathElement> classpathOrder;
/*      */     
/*      */     private final Set<String> acceptedClassNamesFound;
/*      */     
/*  667 */     private final Set<String> classNamesScheduledForExtendedScanning = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*      */ 
/*      */     
/*      */     private final Queue<Classfile> scannedClassfiles;
/*      */ 
/*      */     
/*  673 */     private final ConcurrentHashMap<String, String> stringInternMap = new ConcurrentHashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ClassfileScannerWorkUnitProcessor(ScanSpec scanSpec, List<ClasspathElement> classpathOrder, Set<String> acceptedClassNamesFound, Queue<Classfile> scannedClassfiles) {
/*  691 */       this.scanSpec = scanSpec;
/*  692 */       this.classpathOrder = classpathOrder;
/*  693 */       this.acceptedClassNamesFound = acceptedClassNamesFound;
/*  694 */       this.scannedClassfiles = scannedClassfiles;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void processWorkUnit(Scanner.ClassfileScanWorkUnit workUnit, WorkQueue<Scanner.ClassfileScanWorkUnit> workQueue, LogNode log) throws InterruptedException {
/*  721 */       LogNode subLog = (workUnit.classfileResource.scanLog == null) ? null : workUnit.classfileResource.scanLog.log(workUnit.classfileResource.getPath(), "Parsing classfile");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  728 */         Classfile classfile = new Classfile(workUnit.classpathElement, this.classpathOrder, this.acceptedClassNamesFound, this.classNamesScheduledForExtendedScanning, workUnit.classfileResource.getPath(), workUnit.classfileResource, workUnit.isExternalClass, this.stringInternMap, workQueue, this.scanSpec, subLog);
/*      */ 
/*      */ 
/*      */         
/*  732 */         this.scannedClassfiles.add(classfile);
/*      */         
/*  734 */         if (subLog != null) {
/*  735 */           subLog.addElapsedTime();
/*      */         }
/*  737 */       } catch (SkipClassException e) {
/*  738 */         if (subLog != null) {
/*  739 */           subLog.log(workUnit.classfileResource.getPath(), "Skipping classfile: " + e.getMessage());
/*  740 */           subLog.addElapsedTime();
/*      */         } 
/*  742 */       } catch (ClassfileFormatException e) {
/*  743 */         if (subLog != null) {
/*  744 */           subLog.log(workUnit.classfileResource.getPath(), "Invalid classfile: " + e.getMessage());
/*  745 */           subLog.addElapsedTime();
/*      */         } 
/*  747 */       } catch (IOException e) {
/*  748 */         if (subLog != null) {
/*  749 */           subLog.log(workUnit.classfileResource.getPath(), "Could not read classfile: " + e);
/*  750 */           subLog.addElapsedTime();
/*      */         } 
/*  752 */       } catch (Exception e) {
/*  753 */         if (subLog != null) {
/*  754 */           subLog.log(workUnit.classfileResource.getPath(), "Could not read classfile", e);
/*  755 */           subLog.addElapsedTime();
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void findNestedClasspathElements(List<AbstractMap.SimpleEntry<String, ClasspathElement>> classpathElts, LogNode log) {
/*  774 */     CollectionUtils.sortIfNotEmpty(classpathElts, new Comparator<AbstractMap.SimpleEntry<String, ClasspathElement>>()
/*      */         {
/*      */           public int compare(AbstractMap.SimpleEntry<String, ClasspathElement> o1, AbstractMap.SimpleEntry<String, ClasspathElement> o2)
/*      */           {
/*  778 */             return ((String)o1.getKey()).compareTo(o2.getKey());
/*      */           }
/*      */         });
/*      */     
/*  782 */     for (int i = 0; i < classpathElts.size(); i++) {
/*      */ 
/*      */       
/*  785 */       AbstractMap.SimpleEntry<String, ClasspathElement> ei = classpathElts.get(i);
/*  786 */       String basePath = ei.getKey();
/*  787 */       int basePathLen = basePath.length();
/*  788 */       for (int j = i + 1; j < classpathElts.size(); j++) {
/*  789 */         AbstractMap.SimpleEntry<String, ClasspathElement> ej = classpathElts.get(j);
/*  790 */         String comparePath = ej.getKey();
/*  791 */         int comparePathLen = comparePath.length();
/*  792 */         boolean foundNestedClasspathRoot = false;
/*  793 */         if (comparePath.startsWith(basePath) && comparePathLen > basePathLen) {
/*      */           
/*  795 */           char nextChar = comparePath.charAt(basePathLen);
/*  796 */           if (nextChar == '/' || nextChar == '!') {
/*      */ 
/*      */ 
/*      */             
/*  800 */             String nestedClasspathRelativePath = comparePath.substring(basePathLen + 1);
/*  801 */             if (nestedClasspathRelativePath.indexOf('!') < 0) {
/*      */               
/*  803 */               foundNestedClasspathRoot = true;
/*      */               
/*  805 */               ClasspathElement baseElement = ei.getValue();
/*  806 */               if (baseElement.nestedClasspathRootPrefixes == null) {
/*  807 */                 baseElement.nestedClasspathRootPrefixes = new ArrayList<>();
/*      */               }
/*  809 */               baseElement.nestedClasspathRootPrefixes.add(nestedClasspathRelativePath + "/");
/*  810 */               if (log != null) {
/*  811 */                 log.log(basePath + " is a prefix of the nested element " + comparePath);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*  816 */         if (!foundNestedClasspathRoot) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void preprocessClasspathElementsByType(List<ClasspathElement> finalTraditionalClasspathEltOrder, LogNode classpathFinderLog) {
/*  834 */     List<AbstractMap.SimpleEntry<String, ClasspathElement>> classpathEltDirs = new ArrayList<>();
/*  835 */     List<AbstractMap.SimpleEntry<String, ClasspathElement>> classpathEltZips = new ArrayList<>();
/*  836 */     for (ClasspathElement classpathElt : finalTraditionalClasspathEltOrder) {
/*  837 */       if (classpathElt instanceof ClasspathElementDir) {
/*      */         
/*  839 */         File file = classpathElt.getFile();
/*  840 */         String path = (file == null) ? classpathElt.toString() : file.getPath();
/*  841 */         classpathEltDirs.add(new AbstractMap.SimpleEntry<>(path, classpathElt)); continue;
/*      */       } 
/*  843 */       if (classpathElt instanceof ClasspathElementZip) {
/*      */         
/*  845 */         ClasspathElementZip classpathEltZip = (ClasspathElementZip)classpathElt;
/*  846 */         classpathEltZips.add(new AbstractMap.SimpleEntry<>(classpathEltZip.getZipFilePath(), classpathElt));
/*      */ 
/*      */         
/*  849 */         if (classpathEltZip.logicalZipFile != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  855 */           if (classpathEltZip.logicalZipFile.addExportsManifestEntryValue != null) {
/*  856 */             for (String addExports : JarUtils.smartPathSplit(classpathEltZip.logicalZipFile.addExportsManifestEntryValue, ' ', this.scanSpec))
/*      */             {
/*  858 */               this.scanSpec.modulePathInfo.addExports.add(addExports + "=ALL-UNNAMED");
/*      */             }
/*      */           }
/*  861 */           if (classpathEltZip.logicalZipFile.addOpensManifestEntryValue != null) {
/*  862 */             for (String addOpens : JarUtils.smartPathSplit(classpathEltZip.logicalZipFile.addOpensManifestEntryValue, ' ', this.scanSpec))
/*      */             {
/*  864 */               this.scanSpec.modulePathInfo.addOpens.add(addOpens + "=ALL-UNNAMED");
/*      */             }
/*      */           }
/*      */           
/*  868 */           if (classpathEltZip.logicalZipFile.automaticModuleNameManifestEntryValue != null) {
/*  869 */             classpathEltZip.moduleNameFromManifestFile = classpathEltZip.logicalZipFile.automaticModuleNameManifestEntryValue;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  877 */     findNestedClasspathElements(classpathEltDirs, classpathFinderLog);
/*  878 */     findNestedClasspathElements(classpathEltZips, classpathFinderLog);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void maskClassfiles(List<ClasspathElement> classpathElementOrder, LogNode maskLog) {
/*  893 */     Set<String> acceptedClasspathRelativePathsFound = new HashSet<>();
/*  894 */     for (int classpathIdx = 0; classpathIdx < classpathElementOrder.size(); classpathIdx++) {
/*  895 */       ClasspathElement classpathElement = classpathElementOrder.get(classpathIdx);
/*  896 */       classpathElement.maskClassfiles(classpathIdx, acceptedClasspathRelativePathsFound, maskLog);
/*      */     } 
/*  898 */     if (maskLog != null) {
/*  899 */       maskLog.addElapsedTime();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ScanResult performScan(List<ClasspathElement> finalClasspathEltOrder, List<String> finalClasspathEltOrderStrs, ClasspathFinder classpathFinder) throws InterruptedException, ExecutionException {
/*  925 */     if (this.scanSpec.enableClassInfo) {
/*  926 */       maskClassfiles(finalClasspathEltOrder, 
/*  927 */           (this.topLevelLog == null) ? null : this.topLevelLog.log("Masking classfiles"));
/*      */     }
/*      */ 
/*      */     
/*  931 */     Map<File, Long> fileToLastModified = new HashMap<>();
/*  932 */     for (ClasspathElement classpathElement : finalClasspathEltOrder) {
/*  933 */       fileToLastModified.putAll(classpathElement.fileToLastModified);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  939 */     Map<String, ClassInfo> classNameToClassInfo = new ConcurrentHashMap<>();
/*  940 */     Map<String, PackageInfo> packageNameToPackageInfo = new HashMap<>();
/*  941 */     Map<String, ModuleInfo> moduleNameToModuleInfo = new HashMap<>();
/*  942 */     if (this.scanSpec.enableClassInfo) {
/*      */       
/*  944 */       List<ClassfileScanWorkUnit> classfileScanWorkItems = new ArrayList<>();
/*  945 */       Set<String> acceptedClassNamesFound = new HashSet<>();
/*  946 */       for (ClasspathElement classpathElement : finalClasspathEltOrder) {
/*      */         
/*  948 */         for (Resource resource : classpathElement.acceptedClassfileResources) {
/*      */ 
/*      */           
/*  951 */           String className = JarUtils.classfilePathToClassName(resource.getPath());
/*  952 */           if (!acceptedClassNamesFound.add(className) && !className.equals("module-info") && 
/*  953 */             !className.equals("package-info") && !className.endsWith(".package-info"))
/*      */           {
/*      */             
/*  956 */             throw new IllegalArgumentException("Class " + className + " should not have been scheduled more than once for scanning due to classpath masking -- please report this bug at: https://github.com/classgraph/classgraph/issues");
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  962 */           classfileScanWorkItems
/*  963 */             .add(new ClassfileScanWorkUnit(classpathElement, resource, false));
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  968 */       Queue<Classfile> scannedClassfiles = new ConcurrentLinkedQueue<>();
/*      */ 
/*      */       
/*  971 */       ClassfileScannerWorkUnitProcessor classfileWorkUnitProcessor = new ClassfileScannerWorkUnitProcessor(this.scanSpec, finalClasspathEltOrder, Collections.unmodifiableSet(acceptedClassNamesFound), scannedClassfiles);
/*  972 */       processWorkUnits(classfileScanWorkItems, 
/*  973 */           (this.topLevelLog == null) ? null : this.topLevelLog.log("Scanning classfiles"), classfileWorkUnitProcessor);
/*      */ 
/*      */ 
/*      */       
/*  977 */       LogNode linkLog = (this.topLevelLog == null) ? null : this.topLevelLog.log("Linking related classfiles");
/*  978 */       while (!scannedClassfiles.isEmpty()) {
/*  979 */         Classfile c = scannedClassfiles.remove();
/*  980 */         c.link(classNameToClassInfo, packageNameToPackageInfo, moduleNameToModuleInfo);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1005 */       if (linkLog != null) {
/* 1006 */         linkLog.addElapsedTime();
/*      */       }
/*      */     }
/* 1009 */     else if (this.topLevelLog != null) {
/* 1010 */       this.topLevelLog.log("Classfile scanning is disabled");
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1015 */     return new ScanResult(this.scanSpec, finalClasspathEltOrder, finalClasspathEltOrderStrs, classpathFinder, classNameToClassInfo, packageNameToPackageInfo, moduleNameToModuleInfo, fileToLastModified, this.nestedJarHandler, this.topLevelLog);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ScanResult openClasspathElementsThenScan() throws InterruptedException, ExecutionException {
/* 1035 */     List<ClasspathEntryWorkUnit> rawClasspathEntryWorkUnits = new ArrayList<>();
/* 1036 */     List<ClasspathOrder.ClasspathEntry> rawClasspathOrder = this.classpathFinder.getClasspathOrder().getOrder();
/* 1037 */     for (ClasspathOrder.ClasspathEntry rawClasspathEntry : rawClasspathOrder) {
/* 1038 */       rawClasspathEntryWorkUnits.add(new ClasspathEntryWorkUnit(rawClasspathEntry.classpathEntryObj, rawClasspathEntry.classLoader, null, rawClasspathEntryWorkUnits
/*      */ 
/*      */ 
/*      */             
/* 1042 */             .size(), ""));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1050 */     Set<ClasspathElement> allClasspathElts = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*      */     
/* 1052 */     Set<ClasspathElement> toplevelClasspathElts = Collections.newSetFromMap(new ConcurrentHashMap<>());
/* 1053 */     processWorkUnits(rawClasspathEntryWorkUnits, 
/* 1054 */         (this.topLevelLog == null) ? null : this.topLevelLog.log("Opening classpath elements"), 
/* 1055 */         newClasspathEntryWorkUnitProcessor(allClasspathElts, toplevelClasspathElts));
/*      */ 
/*      */ 
/*      */     
/* 1059 */     List<ClasspathElement> classpathEltOrder = findClasspathOrder(toplevelClasspathElts);
/*      */ 
/*      */ 
/*      */     
/* 1063 */     preprocessClasspathElementsByType(classpathEltOrder, 
/* 1064 */         (this.topLevelLog == null) ? null : this.topLevelLog.log("Finding nested classpath elements"));
/*      */ 
/*      */ 
/*      */     
/* 1068 */     LogNode classpathOrderLog = (this.topLevelLog == null) ? null : this.topLevelLog.log("Final classpath element order:");
/* 1069 */     int numElts = this.moduleOrder.size() + classpathEltOrder.size();
/* 1070 */     List<ClasspathElement> finalClasspathEltOrder = new ArrayList<>(numElts);
/* 1071 */     List<String> finalClasspathEltOrderStrs = new ArrayList<>(numElts);
/* 1072 */     int classpathOrderIdx = 0;
/* 1073 */     for (ClasspathElementModule classpathElt : this.moduleOrder) {
/* 1074 */       classpathElt.classpathElementIdx = classpathOrderIdx++;
/* 1075 */       finalClasspathEltOrder.add(classpathElt);
/* 1076 */       finalClasspathEltOrderStrs.add(classpathElt.toString());
/* 1077 */       if (classpathOrderLog != null) {
/* 1078 */         ModuleRef moduleRef = classpathElt.getModuleRef();
/* 1079 */         classpathOrderLog.log(moduleRef.toString());
/*      */       } 
/*      */     } 
/* 1082 */     for (ClasspathElement classpathElt : classpathEltOrder) {
/* 1083 */       classpathElt.classpathElementIdx = classpathOrderIdx++;
/* 1084 */       finalClasspathEltOrder.add(classpathElt);
/* 1085 */       finalClasspathEltOrderStrs.add(classpathElt.toString());
/* 1086 */       if (classpathOrderLog != null) {
/* 1087 */         classpathOrderLog.log(classpathElt.toString());
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1092 */     processWorkUnits(finalClasspathEltOrder, 
/* 1093 */         (this.topLevelLog == null) ? null : this.topLevelLog.log("Scanning classpath elements"), new WorkQueue.WorkUnitProcessor<ClasspathElement>()
/*      */         {
/*      */ 
/*      */ 
/*      */           
/*      */           public void processWorkUnit(ClasspathElement classpathElement, WorkQueue<ClasspathElement> workQueueIgnored, LogNode pathScanLog) throws InterruptedException
/*      */           {
/* 1100 */             classpathElement.scanPaths(pathScanLog);
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1105 */     List<ClasspathElement> finalClasspathEltOrderFiltered = finalClasspathEltOrder;
/* 1106 */     if (!this.scanSpec.classpathElementResourcePathAcceptReject.acceptIsEmpty()) {
/* 1107 */       finalClasspathEltOrderFiltered = new ArrayList<>(finalClasspathEltOrder.size());
/* 1108 */       for (ClasspathElement classpathElement : finalClasspathEltOrder) {
/* 1109 */         if (classpathElement.containsSpecificallyAcceptedClasspathElementResourcePath) {
/* 1110 */           finalClasspathEltOrderFiltered.add(classpathElement);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/* 1115 */     if (this.performScan)
/*      */     {
/* 1117 */       return performScan(finalClasspathEltOrderFiltered, finalClasspathEltOrderStrs, this.classpathFinder);
/*      */     }
/*      */     
/* 1120 */     if (this.topLevelLog != null) {
/* 1121 */       this.topLevelLog.log("Only returning classpath elements (not performing a scan)");
/*      */     }
/* 1123 */     return new ScanResult(this.scanSpec, finalClasspathEltOrderFiltered, finalClasspathEltOrderStrs, this.classpathFinder, null, null, null, null, this.nestedJarHandler, this.topLevelLog);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ScanResult call() throws InterruptedException, CancellationException, ExecutionException {
/* 1146 */     ScanResult scanResult = null;
/* 1147 */     long scanStart = System.currentTimeMillis();
/* 1148 */     boolean removeTemporaryFilesAfterScan = this.scanSpec.removeTemporaryFilesAfterScan;
/*      */     
/*      */     try {
/* 1151 */       scanResult = openClasspathElementsThenScan();
/*      */ 
/*      */       
/* 1154 */       if (this.topLevelLog != null) {
/* 1155 */         this.topLevelLog.log("~", 
/* 1156 */             String.format("Total time: %.3f sec", new Object[] { Double.valueOf((System.currentTimeMillis() - scanStart) * 0.001D) }));
/* 1157 */         this.topLevelLog.flush();
/*      */       } 
/*      */ 
/*      */       
/* 1161 */       if (this.scanResultProcessor != null) {
/*      */         try {
/* 1163 */           this.scanResultProcessor.processScanResult(scanResult);
/* 1164 */         } catch (Exception e) {
/* 1165 */           scanResult.close();
/* 1166 */           throw new ExecutionException(e);
/*      */         } 
/* 1168 */         scanResult.close();
/*      */       }
/*      */     
/* 1171 */     } catch (Throwable e) {
/* 1172 */       if (this.topLevelLog != null) {
/* 1173 */         this.topLevelLog.log("~", (
/* 1174 */             e instanceof InterruptedException || e instanceof CancellationException) ? 
/* 1175 */             "Scan interrupted or canceled" : (
/* 1176 */             (e instanceof ExecutionException || e instanceof RuntimeException) ? 
/* 1177 */             "Uncaught exception during scan" : 
/* 1178 */             e.getMessage()), 
/* 1179 */             InterruptionChecker.getCause(e));
/*      */         
/* 1181 */         this.topLevelLog.flush();
/*      */       } 
/*      */ 
/*      */       
/* 1185 */       removeTemporaryFilesAfterScan = true;
/*      */ 
/*      */       
/* 1188 */       this.interruptionChecker.interrupt();
/*      */       
/* 1190 */       if (this.failureHandler == null) {
/* 1191 */         if (removeTemporaryFilesAfterScan)
/*      */         {
/*      */           
/* 1194 */           this.nestedJarHandler.close(this.topLevelLog);
/*      */         }
/*      */         
/* 1197 */         throw e;
/*      */       } 
/*      */       
/*      */       try {
/* 1201 */         this.failureHandler.onFailure(e);
/* 1202 */       } catch (Exception f) {
/*      */         
/* 1204 */         if (this.topLevelLog != null) {
/* 1205 */           this.topLevelLog.log("~", "The failure handler threw an exception:", f);
/* 1206 */           this.topLevelLog.flush();
/*      */         } 
/*      */ 
/*      */         
/* 1210 */         ExecutionException failureHandlerException = new ExecutionException("Exception while calling failure handler", f);
/*      */         
/* 1212 */         failureHandlerException.addSuppressed(e);
/* 1213 */         if (removeTemporaryFilesAfterScan)
/*      */         {
/*      */           
/* 1216 */           this.nestedJarHandler.close(this.topLevelLog);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1221 */         throw failureHandlerException;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1226 */     if (removeTemporaryFilesAfterScan)
/*      */     {
/*      */       
/* 1229 */       this.nestedJarHandler.close(this.topLevelLog);
/*      */     }
/* 1231 */     return scanResult;
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\Scanner.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */