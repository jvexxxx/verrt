/*      */ package io.github.classgraph;
/*      */ 
/*      */ import java.io.Closeable;
/*      */ import java.io.File;
/*      */ import java.lang.annotation.Annotation;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.net.URI;
/*      */ import java.net.URL;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import java.util.concurrent.atomic.AtomicBoolean;
/*      */ import java.util.concurrent.atomic.AtomicInteger;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import nonapi.io.github.classgraph.classpath.ClasspathFinder;
/*      */ import nonapi.io.github.classgraph.concurrency.AutoCloseableExecutorService;
/*      */ import nonapi.io.github.classgraph.fastzipfilereader.NestedJarHandler;
/*      */ import nonapi.io.github.classgraph.json.JSONDeserializer;
/*      */ import nonapi.io.github.classgraph.json.JSONSerializer;
/*      */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*      */ import nonapi.io.github.classgraph.scanspec.AcceptReject;
/*      */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*      */ import nonapi.io.github.classgraph.utils.Assert;
/*      */ import nonapi.io.github.classgraph.utils.CollectionUtils;
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
/*      */ public final class ScanResult
/*      */   implements Closeable
/*      */ {
/*      */   private List<String> rawClasspathEltOrderStrs;
/*      */   private List<ClasspathElement> classpathOrder;
/*      */   private ResourceList allAcceptedResourcesCached;
/*   89 */   private final AtomicInteger getResourcesWithPathCallCount = new AtomicInteger();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Map<String, ResourceList> pathToAcceptedResourcesCached;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Map<String, ClassInfo> classNameToClassInfo;
/*      */ 
/*      */ 
/*      */   
/*      */   private Map<String, PackageInfo> packageNameToPackageInfo;
/*      */ 
/*      */ 
/*      */   
/*      */   private Map<String, ModuleInfo> moduleNameToModuleInfo;
/*      */ 
/*      */ 
/*      */   
/*      */   private Map<File, Long> fileToLastModified;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isObtainedFromDeserialization;
/*      */ 
/*      */ 
/*      */   
/*      */   private ClassGraphClassLoader classGraphClassLoader;
/*      */ 
/*      */ 
/*      */   
/*      */   ClasspathFinder classpathFinder;
/*      */ 
/*      */ 
/*      */   
/*      */   private NestedJarHandler nestedJarHandler;
/*      */ 
/*      */ 
/*      */   
/*      */   ScanSpec scanSpec;
/*      */ 
/*      */ 
/*      */   
/*  135 */   private final AtomicBoolean closed = new AtomicBoolean(false);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected ReflectionUtils reflectionUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final LogNode topLevelLog;
/*      */ 
/*      */ 
/*      */   
/*      */   private final WeakReference<ScanResult> weakReference;
/*      */ 
/*      */ 
/*      */   
/*  153 */   private static Set<WeakReference<ScanResult>> nonClosedWeakReferences = Collections.newSetFromMap(new ConcurrentHashMap<>());
/*      */ 
/*      */   
/*  156 */   private static final AtomicBoolean initialized = new AtomicBoolean(false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final String CURRENT_SERIALIZATION_FORMAT = "10";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static class SerializationFormat
/*      */   {
/*      */     public String format;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ScanSpec scanSpec;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<String> classpath;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<ClassInfo> classInfo;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<PackageInfo> packageInfo;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public List<ModuleInfo> moduleInfo;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public SerializationFormat() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public SerializationFormat(String serializationFormatStr, ScanSpec scanSpec, List<ClassInfo> classInfo, List<PackageInfo> packageInfo, List<ModuleInfo> moduleInfo, List<String> classpath) {
/*  213 */       this.format = serializationFormatStr;
/*  214 */       this.scanSpec = scanSpec;
/*  215 */       this.classpath = classpath;
/*  216 */       this.classInfo = classInfo;
/*  217 */       this.packageInfo = packageInfo;
/*  218 */       this.moduleInfo = moduleInfo;
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
/*      */   static void init(ReflectionUtils reflectionUtils) {
/*  230 */     if (!initialized.getAndSet(true))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  243 */       FileUtils.closeDirectByteBuffer(ByteBuffer.allocateDirect(32), reflectionUtils, null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   ScanResult(ScanSpec scanSpec, List<ClasspathElement> classpathOrder, List<String> rawClasspathEltOrderStrs, ClasspathFinder classpathFinder, Map<String, ClassInfo> classNameToClassInfo, Map<String, PackageInfo> packageNameToPackageInfo, Map<String, ModuleInfo> moduleNameToModuleInfo, Map<File, Long> fileToLastModified, NestedJarHandler nestedJarHandler, LogNode topLevelLog) {
/*  281 */     this.scanSpec = scanSpec;
/*  282 */     this.rawClasspathEltOrderStrs = rawClasspathEltOrderStrs;
/*  283 */     this.classpathOrder = classpathOrder;
/*  284 */     this.classpathFinder = classpathFinder;
/*  285 */     this.fileToLastModified = fileToLastModified;
/*  286 */     this.classNameToClassInfo = classNameToClassInfo;
/*  287 */     this.packageNameToPackageInfo = packageNameToPackageInfo;
/*  288 */     this.moduleNameToModuleInfo = moduleNameToModuleInfo;
/*  289 */     this.nestedJarHandler = nestedJarHandler;
/*  290 */     this.reflectionUtils = nestedJarHandler.reflectionUtils;
/*  291 */     this.topLevelLog = topLevelLog;
/*      */     
/*  293 */     if (classNameToClassInfo != null) {
/*  294 */       indexResourcesAndClassInfo(topLevelLog);
/*      */     }
/*      */     
/*  297 */     if (classNameToClassInfo != null) {
/*      */       
/*  299 */       Set<String> allRepeatableAnnotationNames = new HashSet<>();
/*  300 */       for (ClassInfo classInfo : classNameToClassInfo.values()) {
/*  301 */         if (classInfo.isAnnotation() && classInfo.annotationInfo != null) {
/*      */           
/*  303 */           AnnotationInfo repeatableMetaAnnotation = classInfo.annotationInfo.get("java.lang.annotation.Repeatable");
/*  304 */           if (repeatableMetaAnnotation != null) {
/*  305 */             AnnotationParameterValueList vals = repeatableMetaAnnotation.getParameterValues();
/*  306 */             if (!vals.isEmpty()) {
/*  307 */               Object val = vals.getValue("value");
/*  308 */               if (val instanceof AnnotationClassRef) {
/*  309 */                 AnnotationClassRef classRef = (AnnotationClassRef)val;
/*  310 */                 String repeatableAnnotationName = classRef.getName();
/*  311 */                 if (repeatableAnnotationName != null) {
/*  312 */                   allRepeatableAnnotationNames.add(repeatableAnnotationName);
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  319 */       if (!allRepeatableAnnotationNames.isEmpty()) {
/*  320 */         for (ClassInfo classInfo : classNameToClassInfo.values()) {
/*  321 */           classInfo.handleRepeatableAnnotations(allRepeatableAnnotationNames);
/*      */         }
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  327 */     this.classGraphClassLoader = new ClassGraphClassLoader(this);
/*      */ 
/*      */     
/*  330 */     this.weakReference = new WeakReference<>(this);
/*  331 */     nonClosedWeakReferences.add(this.weakReference);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void indexResourcesAndClassInfo(LogNode log) {
/*  342 */     Collection<ClassInfo> allClassInfo = this.classNameToClassInfo.values();
/*  343 */     for (ClassInfo classInfo : allClassInfo) {
/*  344 */       classInfo.setScanResult(this);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  350 */     if (this.scanSpec.enableInterClassDependencies) {
/*  351 */       for (ClassInfo ci : new ArrayList(this.classNameToClassInfo.values())) {
/*  352 */         Set<ClassInfo> refdClassesFiltered = new HashSet<>();
/*  353 */         for (ClassInfo refdClassInfo : ci.findReferencedClassInfo(log)) {
/*      */           
/*  355 */           if (refdClassInfo != null && !ci.equals(refdClassInfo) && 
/*  356 */             !refdClassInfo.getName().equals("java.lang.Object") && (
/*      */             
/*  358 */             !refdClassInfo.isExternalClass() || this.scanSpec.enableExternalClasses)) {
/*  359 */             refdClassInfo.setScanResult(this);
/*  360 */             refdClassesFiltered.add(refdClassInfo);
/*      */           } 
/*      */         } 
/*  363 */         ci.setReferencedClasses(new ClassInfoList(refdClassesFiltered, true));
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
/*      */   public List<File> getClasspathFiles() {
/*  379 */     if (this.closed.get()) {
/*  380 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  382 */     List<File> classpathElementOrderFiles = new ArrayList<>();
/*  383 */     for (ClasspathElement classpathElement : this.classpathOrder) {
/*  384 */       File file = classpathElement.getFile();
/*  385 */       if (file != null) {
/*  386 */         classpathElementOrderFiles.add(file);
/*      */       }
/*      */     } 
/*  389 */     return classpathElementOrderFiles;
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
/*      */   public String getClasspath() {
/*  402 */     if (this.closed.get()) {
/*  403 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  405 */     return JarUtils.pathElementsToPathStr(getClasspathFiles());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<URI> getClasspathURIs() {
/*  414 */     if (this.closed.get()) {
/*  415 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  417 */     List<URI> classpathElementOrderURIs = new ArrayList<>();
/*  418 */     for (ClasspathElement classpathElement : this.classpathOrder) {
/*      */       try {
/*  420 */         for (URI uri : classpathElement.getAllURIs()) {
/*  421 */           if (uri != null) {
/*  422 */             classpathElementOrderURIs.add(uri);
/*      */           }
/*      */         } 
/*  425 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*      */     } 
/*      */ 
/*      */     
/*  429 */     return classpathElementOrderURIs;
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
/*      */   public List<URL> getClasspathURLs() {
/*  442 */     if (this.closed.get()) {
/*  443 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  445 */     List<URL> classpathElementOrderURLs = new ArrayList<>();
/*  446 */     for (URI uri : getClasspathURIs()) {
/*      */       try {
/*  448 */         classpathElementOrderURLs.add(uri.toURL());
/*  449 */       } catch (IllegalArgumentException|java.net.MalformedURLException illegalArgumentException) {}
/*      */     } 
/*      */ 
/*      */     
/*  453 */     return classpathElementOrderURLs;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<ModuleRef> getModules() {
/*  462 */     if (this.closed.get()) {
/*  463 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  465 */     List<ModuleRef> moduleRefs = new ArrayList<>();
/*  466 */     for (ClasspathElement classpathElement : this.classpathOrder) {
/*  467 */       if (classpathElement instanceof ClasspathElementModule) {
/*  468 */         moduleRefs.add(((ClasspathElementModule)classpathElement).getModuleRef());
/*      */       }
/*      */     } 
/*  471 */     return moduleRefs;
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
/*      */   public ModulePathInfo getModulePathInfo() {
/*  492 */     this.scanSpec.modulePathInfo.getRuntimeInfo(this.reflectionUtils);
/*  493 */     return this.scanSpec.modulePathInfo;
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
/*      */   public ResourceList getAllResources() {
/*  506 */     if (this.allAcceptedResourcesCached == null) {
/*      */       
/*  508 */       ResourceList acceptedResourcesList = new ResourceList();
/*  509 */       for (ClasspathElement classpathElt : this.classpathOrder) {
/*  510 */         acceptedResourcesList.addAll(classpathElt.acceptedResources);
/*      */       }
/*      */       
/*  513 */       this.allAcceptedResourcesCached = acceptedResourcesList;
/*      */     } 
/*  515 */     return this.allAcceptedResourcesCached;
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
/*      */   public Map<String, ResourceList> getAllResourcesAsMap() {
/*  528 */     if (this.pathToAcceptedResourcesCached == null) {
/*  529 */       Map<String, ResourceList> pathToAcceptedResourceListMap = new HashMap<>();
/*  530 */       for (Resource res : getAllResources()) {
/*  531 */         ResourceList resList = pathToAcceptedResourceListMap.get(res.getPath());
/*  532 */         if (resList == null) {
/*  533 */           pathToAcceptedResourceListMap.put(res.getPath(), resList = new ResourceList());
/*      */         }
/*  535 */         resList.add(res);
/*      */       } 
/*      */       
/*  538 */       this.pathToAcceptedResourcesCached = pathToAcceptedResourceListMap;
/*      */     } 
/*  540 */     return this.pathToAcceptedResourcesCached;
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
/*      */   public ResourceList getResourcesWithPath(String resourcePath) {
/*  558 */     if (this.closed.get()) {
/*  559 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  561 */     String path = FileUtils.sanitizeEntryPath(resourcePath, true, true);
/*      */     
/*  563 */     ResourceList matchingResources = null;
/*  564 */     if (this.getResourcesWithPathCallCount.incrementAndGet() > 3) {
/*      */ 
/*      */       
/*  567 */       matchingResources = getAllResourcesAsMap().get(path);
/*      */     }
/*      */     else {
/*      */       
/*  571 */       for (ClasspathElement classpathElt : this.classpathOrder) {
/*  572 */         for (Resource res : classpathElt.acceptedResources) {
/*  573 */           if (res.getPath().equals(path)) {
/*  574 */             if (matchingResources == null) {
/*  575 */               matchingResources = new ResourceList();
/*      */             }
/*  577 */             matchingResources.add(res);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*  582 */     return (matchingResources == null) ? ResourceList.EMPTY_LIST : matchingResources;
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
/*      */ 
/*      */   
/*      */   public ResourceList getResourcesWithPathIgnoringAccept(String resourcePath) {
/*  609 */     if (this.closed.get()) {
/*  610 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  612 */     String path = FileUtils.sanitizeEntryPath(resourcePath, true, true);
/*      */     
/*  614 */     ResourceList matchingResources = new ResourceList();
/*  615 */     for (ClasspathElement classpathElt : this.classpathOrder) {
/*  616 */       Resource matchingResource = classpathElt.getResource(path);
/*  617 */       if (matchingResource != null) {
/*  618 */         matchingResources.add(matchingResource);
/*      */       }
/*      */     } 
/*  621 */     return matchingResources;
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
/*      */   @Deprecated
/*      */   public ResourceList getResourcesWithPathIgnoringWhitelist(String resourcePath) {
/*  640 */     return getResourcesWithPathIgnoringAccept(resourcePath);
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
/*      */   public ResourceList getResourcesWithLeafName(String leafName) {
/*  653 */     if (this.closed.get()) {
/*  654 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  656 */     ResourceList allAcceptedResources = getAllResources();
/*  657 */     if (allAcceptedResources.isEmpty()) {
/*  658 */       return ResourceList.EMPTY_LIST;
/*      */     }
/*  660 */     ResourceList filteredResources = new ResourceList();
/*  661 */     for (Resource classpathResource : allAcceptedResources) {
/*  662 */       String relativePath = classpathResource.getPath();
/*  663 */       int lastSlashIdx = relativePath.lastIndexOf('/');
/*  664 */       if (relativePath.substring(lastSlashIdx + 1).equals(leafName)) {
/*  665 */         filteredResources.add(classpathResource);
/*      */       }
/*      */     } 
/*  668 */     return filteredResources;
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
/*      */   public ResourceList getResourcesWithExtension(String extension) {
/*  683 */     if (this.closed.get()) {
/*  684 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  686 */     ResourceList allAcceptedResources = getAllResources();
/*  687 */     if (allAcceptedResources.isEmpty()) {
/*  688 */       return ResourceList.EMPTY_LIST;
/*      */     }
/*  690 */     String bareExtension = extension;
/*  691 */     while (bareExtension.startsWith(".")) {
/*  692 */       bareExtension = bareExtension.substring(1);
/*      */     }
/*  694 */     ResourceList filteredResources = new ResourceList();
/*  695 */     for (Resource classpathResource : allAcceptedResources) {
/*  696 */       String relativePath = classpathResource.getPath();
/*  697 */       int lastSlashIdx = relativePath.lastIndexOf('/');
/*  698 */       int lastDotIdx = relativePath.lastIndexOf('.');
/*  699 */       if (lastDotIdx > lastSlashIdx && relativePath
/*  700 */         .substring(lastDotIdx + 1).equalsIgnoreCase(bareExtension)) {
/*  701 */         filteredResources.add(classpathResource);
/*      */       }
/*      */     } 
/*  704 */     return filteredResources;
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
/*      */   public ResourceList getResourcesMatchingPattern(Pattern pattern) {
/*  719 */     if (this.closed.get()) {
/*  720 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  722 */     ResourceList allAcceptedResources = getAllResources();
/*  723 */     if (allAcceptedResources.isEmpty()) {
/*  724 */       return ResourceList.EMPTY_LIST;
/*      */     }
/*  726 */     ResourceList filteredResources = new ResourceList();
/*  727 */     for (Resource classpathResource : allAcceptedResources) {
/*  728 */       String relativePath = classpathResource.getPath();
/*  729 */       if (pattern.matcher(relativePath).matches()) {
/*  730 */         filteredResources.add(classpathResource);
/*      */       }
/*      */     } 
/*  733 */     return filteredResources;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResourceList getResourcesMatchingWildcard(String wildcardString) {
/*  769 */     if (this.closed.get()) {
/*  770 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  772 */     return getResourcesMatchingPattern(AcceptReject.globToPattern(wildcardString, false));
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
/*      */   public ModuleInfo getModuleInfo(String moduleName) {
/*  789 */     if (this.closed.get()) {
/*  790 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  792 */     if (!this.scanSpec.enableClassInfo) {
/*  793 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/*  795 */     return this.moduleNameToModuleInfo.get(moduleName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ModuleInfoList getModuleInfo() {
/*  805 */     if (this.closed.get()) {
/*  806 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  808 */     if (!this.scanSpec.enableClassInfo) {
/*  809 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/*  811 */     return new ModuleInfoList(this.moduleNameToModuleInfo.values());
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
/*      */   public PackageInfo getPackageInfo(String packageName) {
/*  828 */     if (this.closed.get()) {
/*  829 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  831 */     if (!this.scanSpec.enableClassInfo) {
/*  832 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/*  834 */     return this.packageNameToPackageInfo.get(packageName);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PackageInfoList getPackageInfo() {
/*  844 */     if (this.closed.get()) {
/*  845 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  847 */     if (!this.scanSpec.enableClassInfo) {
/*  848 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/*  850 */     return new PackageInfoList(this.packageNameToPackageInfo.values());
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
/*      */ 
/*      */   
/*      */   public Map<ClassInfo, ClassInfoList> getClassDependencyMap() {
/*  877 */     Map<ClassInfo, ClassInfoList> map = new HashMap<>();
/*  878 */     for (ClassInfo ci : getAllClasses()) {
/*  879 */       map.put(ci, ci.getClassDependencies());
/*      */     }
/*  881 */     return map;
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
/*      */   public Map<ClassInfo, ClassInfoList> getReverseClassDependencyMap() {
/*  904 */     Map<ClassInfo, Set<ClassInfo>> revMapSet = new HashMap<>();
/*  905 */     for (ClassInfo ci : getAllClasses()) {
/*  906 */       for (ClassInfo dep : ci.getClassDependencies()) {
/*  907 */         Set<ClassInfo> set = revMapSet.get(dep);
/*  908 */         if (set == null) {
/*  909 */           revMapSet.put(dep, set = new HashSet<>());
/*      */         }
/*  911 */         set.add(ci);
/*      */       } 
/*      */     } 
/*  914 */     Map<ClassInfo, ClassInfoList> revMapList = new HashMap<>();
/*  915 */     for (Map.Entry<ClassInfo, Set<ClassInfo>> ent : revMapSet.entrySet()) {
/*  916 */       revMapList.put(ent.getKey(), new ClassInfoList(ent.getValue(), true));
/*      */     }
/*  918 */     return revMapList;
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
/*      */   public ClassInfo getClassInfo(String className) {
/*  935 */     if (this.closed.get()) {
/*  936 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  938 */     if (!this.scanSpec.enableClassInfo) {
/*  939 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/*  941 */     return this.classNameToClassInfo.get(className);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassInfoList getAllClasses() {
/*  951 */     if (this.closed.get()) {
/*  952 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  954 */     if (!this.scanSpec.enableClassInfo) {
/*  955 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/*  957 */     return ClassInfo.getAllClasses(this.classNameToClassInfo.values(), this.scanSpec);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassInfoList getAllEnums() {
/*  967 */     if (this.closed.get()) {
/*  968 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  970 */     if (!this.scanSpec.enableClassInfo) {
/*  971 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/*  973 */     return ClassInfo.getAllEnums(this.classNameToClassInfo.values(), this.scanSpec);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassInfoList getAllRecords() {
/*  983 */     if (this.closed.get()) {
/*  984 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/*  986 */     if (!this.scanSpec.enableClassInfo) {
/*  987 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/*  989 */     return ClassInfo.getAllRecords(this.classNameToClassInfo.values(), this.scanSpec);
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
/*      */   public Map<String, ClassInfo> getAllClassesAsMap() {
/* 1002 */     if (this.closed.get()) {
/* 1003 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1005 */     if (!this.scanSpec.enableClassInfo) {
/* 1006 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/* 1008 */     return this.classNameToClassInfo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassInfoList getAllStandardClasses() {
/* 1019 */     if (this.closed.get()) {
/* 1020 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1022 */     if (!this.scanSpec.enableClassInfo) {
/* 1023 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/* 1025 */     return ClassInfo.getAllStandardClasses(this.classNameToClassInfo.values(), this.scanSpec);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ClassInfoList getSubclasses(Class<?> superclass) {
/* 1036 */     return getSubclasses(superclass.getName());
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
/*      */   public ClassInfoList getSubclasses(String superclassName) {
/* 1048 */     if (this.closed.get()) {
/* 1049 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1051 */     if (!this.scanSpec.enableClassInfo) {
/* 1052 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/* 1054 */     if (superclassName.equals("java.lang.Object"))
/*      */     {
/* 1056 */       return getAllStandardClasses();
/*      */     }
/* 1058 */     ClassInfo superclass = this.classNameToClassInfo.get(superclassName);
/* 1059 */     return (superclass == null) ? ClassInfoList.EMPTY_LIST : superclass.getSubclasses();
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
/*      */   public ClassInfoList getSuperclasses(String subclassName) {
/* 1072 */     if (this.closed.get()) {
/* 1073 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1075 */     if (!this.scanSpec.enableClassInfo) {
/* 1076 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/* 1078 */     ClassInfo subclass = this.classNameToClassInfo.get(subclassName);
/* 1079 */     return (subclass == null) ? ClassInfoList.EMPTY_LIST : subclass.getSuperclasses();
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
/*      */   public ClassInfoList getSuperclasses(Class<?> subclass) {
/* 1091 */     return getSuperclasses(subclass.getName());
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
/*      */   public ClassInfoList getClassesWithMethodAnnotation(Class<? extends Annotation> methodAnnotation) {
/* 1103 */     Assert.isAnnotation(methodAnnotation);
/* 1104 */     return getClassesWithMethodAnnotation(methodAnnotation.getName());
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
/*      */   public ClassInfoList getClassesWithMethodAnnotation(String methodAnnotationName) {
/* 1116 */     if (this.closed.get()) {
/* 1117 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1119 */     if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableMethodInfo || !this.scanSpec.enableAnnotationInfo) {
/* 1120 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo(), #enableMethodInfo(), and #enableAnnotationInfo() before #scan()");
/*      */     }
/*      */     
/* 1123 */     ClassInfo classInfo = this.classNameToClassInfo.get(methodAnnotationName);
/* 1124 */     return (classInfo == null) ? ClassInfoList.EMPTY_LIST : classInfo.getClassesWithMethodAnnotation();
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
/*      */   public ClassInfoList getClassesWithMethodParameterAnnotation(Class<? extends Annotation> methodParameterAnnotation) {
/* 1139 */     Assert.isAnnotation(methodParameterAnnotation);
/* 1140 */     return getClassesWithMethodParameterAnnotation(methodParameterAnnotation.getName());
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
/*      */   public ClassInfoList getClassesWithMethodParameterAnnotation(String methodParameterAnnotationName) {
/* 1155 */     if (this.closed.get()) {
/* 1156 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1158 */     if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableMethodInfo || !this.scanSpec.enableAnnotationInfo) {
/* 1159 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo(), #enableMethodInfo(), and #enableAnnotationInfo() before #scan()");
/*      */     }
/*      */     
/* 1162 */     ClassInfo classInfo = this.classNameToClassInfo.get(methodParameterAnnotationName);
/* 1163 */     return (classInfo == null) ? ClassInfoList.EMPTY_LIST : classInfo.getClassesWithMethodParameterAnnotation();
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
/*      */   public ClassInfoList getClassesWithFieldAnnotation(Class<? extends Annotation> fieldAnnotation) {
/* 1175 */     Assert.isAnnotation(fieldAnnotation);
/* 1176 */     return getClassesWithFieldAnnotation(fieldAnnotation.getName());
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
/*      */   public ClassInfoList getClassesWithFieldAnnotation(String fieldAnnotationName) {
/* 1188 */     if (this.closed.get()) {
/* 1189 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1191 */     if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableFieldInfo || !this.scanSpec.enableAnnotationInfo) {
/* 1192 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo(), #enableFieldInfo(), and #enableAnnotationInfo() before #scan()");
/*      */     }
/*      */     
/* 1195 */     ClassInfo classInfo = this.classNameToClassInfo.get(fieldAnnotationName);
/* 1196 */     return (classInfo == null) ? ClassInfoList.EMPTY_LIST : classInfo.getClassesWithFieldAnnotation();
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
/*      */   public ClassInfoList getAllInterfaces() {
/* 1211 */     if (this.closed.get()) {
/* 1212 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1214 */     if (!this.scanSpec.enableClassInfo) {
/* 1215 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/* 1217 */     return ClassInfo.getAllImplementedInterfaceClasses(this.classNameToClassInfo.values(), this.scanSpec);
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
/*      */   public ClassInfoList getInterfaces(String className) {
/* 1233 */     if (this.closed.get()) {
/* 1234 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1236 */     if (!this.scanSpec.enableClassInfo) {
/* 1237 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/* 1239 */     ClassInfo classInfo = this.classNameToClassInfo.get(className);
/* 1240 */     return (classInfo == null) ? ClassInfoList.EMPTY_LIST : classInfo.getInterfaces();
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
/*      */   public ClassInfoList getInterfaces(Class<?> classRef) {
/* 1256 */     return getInterfaces(classRef.getName());
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
/*      */   public ClassInfoList getClassesImplementing(Class<?> interfaceClass) {
/* 1270 */     Assert.isInterface(interfaceClass);
/* 1271 */     return getClassesImplementing(interfaceClass.getName());
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
/*      */   public ClassInfoList getClassesImplementing(String interfaceName) {
/* 1285 */     if (this.closed.get()) {
/* 1286 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1288 */     if (!this.scanSpec.enableClassInfo) {
/* 1289 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/* 1291 */     ClassInfo classInfo = this.classNameToClassInfo.get(interfaceName);
/* 1292 */     return (classInfo == null) ? ClassInfoList.EMPTY_LIST : classInfo.getClassesImplementing();
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
/*      */   public ClassInfoList getAllAnnotations() {
/* 1306 */     if (this.closed.get()) {
/* 1307 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1309 */     if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableAnnotationInfo) {
/* 1310 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() and #enableAnnotationInfo() before #scan()");
/*      */     }
/*      */     
/* 1313 */     return ClassInfo.getAllAnnotationClasses(this.classNameToClassInfo.values(), this.scanSpec);
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
/*      */   public ClassInfoList getAllInterfacesAndAnnotations() {
/* 1325 */     if (this.closed.get()) {
/* 1326 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1328 */     if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableAnnotationInfo) {
/* 1329 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() and #enableAnnotationInfo() before #scan()");
/*      */     }
/*      */     
/* 1332 */     return ClassInfo.getAllInterfacesOrAnnotationClasses(this.classNameToClassInfo.values(), this.scanSpec);
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
/*      */   public ClassInfoList getClassesWithAnnotation(Class<? extends Annotation> annotation) {
/* 1345 */     Assert.isAnnotation(annotation);
/* 1346 */     return getClassesWithAnnotation(annotation.getName());
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
/*      */   public ClassInfoList getClassesWithAnnotation(String annotationName) {
/* 1359 */     if (this.closed.get()) {
/* 1360 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1362 */     if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableAnnotationInfo) {
/* 1363 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() and #enableAnnotationInfo() before #scan()");
/*      */     }
/*      */     
/* 1366 */     ClassInfo classInfo = this.classNameToClassInfo.get(annotationName);
/* 1367 */     return (classInfo == null) ? ClassInfoList.EMPTY_LIST : classInfo.getClassesWithAnnotation();
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
/*      */   public ClassInfoList getAnnotationsOnClass(String className) {
/* 1386 */     if (this.closed.get()) {
/* 1387 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1389 */     if (!this.scanSpec.enableClassInfo || !this.scanSpec.enableAnnotationInfo) {
/* 1390 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() and #enableAnnotationInfo() before #scan()");
/*      */     }
/*      */     
/* 1393 */     ClassInfo classInfo = this.classNameToClassInfo.get(className);
/* 1394 */     return (classInfo == null) ? ClassInfoList.EMPTY_LIST : classInfo.getAnnotations();
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
/*      */   public boolean classpathContentsModifiedSinceScan() {
/* 1413 */     if (this.closed.get()) {
/* 1414 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1416 */     if (this.fileToLastModified == null) {
/* 1417 */       return true;
/*      */     }
/* 1419 */     for (Map.Entry<File, Long> ent : this.fileToLastModified.entrySet()) {
/* 1420 */       if (((File)ent.getKey()).lastModified() != ((Long)ent.getValue()).longValue()) {
/* 1421 */         return true;
/*      */       }
/*      */     } 
/* 1424 */     return false;
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
/*      */   public long classpathContentsLastModifiedTime() {
/* 1446 */     if (this.closed.get()) {
/* 1447 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1449 */     long maxLastModifiedTime = 0L;
/* 1450 */     if (this.fileToLastModified != null) {
/* 1451 */       long currTime = System.currentTimeMillis();
/* 1452 */       for (Iterator<Long> iterator = this.fileToLastModified.values().iterator(); iterator.hasNext(); ) { long timestamp = ((Long)iterator.next()).longValue();
/* 1453 */         if (timestamp > maxLastModifiedTime && timestamp < currTime) {
/* 1454 */           maxLastModifiedTime = timestamp;
/*      */         } }
/*      */     
/*      */     } 
/* 1458 */     return maxLastModifiedTime;
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
/*      */   ClassLoader[] getClassLoaderOrderRespectingParentDelegation() {
/* 1471 */     return this.classpathFinder.getClassLoaderOrderRespectingParentDelegation();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Class<?> loadClass(String className, boolean returnNullIfClassNotFound) throws IllegalArgumentException {
/* 1512 */     if (this.closed.get()) {
/* 1513 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1515 */     if (className == null || className.isEmpty()) {
/* 1516 */       throw new NullPointerException("className cannot be null or empty");
/*      */     }
/*      */     try {
/* 1519 */       return Class.forName(className, this.scanSpec.initializeLoadedClasses, this.classGraphClassLoader);
/* 1520 */     } catch (ClassNotFoundException|LinkageError e) {
/* 1521 */       if (returnNullIfClassNotFound) {
/* 1522 */         return null;
/*      */       }
/* 1524 */       throw new IllegalArgumentException("Could not load class " + className + " : " + e, e);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public <T> Class<T> loadClass(String className, Class<T> superclassOrInterfaceType, boolean returnNullIfClassNotFound) throws IllegalArgumentException {
/*      */     Class<?> loadedClass;
/* 1572 */     if (this.closed.get()) {
/* 1573 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1575 */     if (className == null || className.isEmpty()) {
/* 1576 */       throw new NullPointerException("className cannot be null or empty");
/*      */     }
/* 1578 */     if (superclassOrInterfaceType == null) {
/* 1579 */       throw new NullPointerException("superclassOrInterfaceType parameter cannot be null");
/*      */     }
/*      */     
/*      */     try {
/* 1583 */       loadedClass = Class.forName(className, this.scanSpec.initializeLoadedClasses, this.classGraphClassLoader);
/* 1584 */     } catch (ClassNotFoundException|LinkageError e) {
/* 1585 */       if (returnNullIfClassNotFound) {
/* 1586 */         return null;
/*      */       }
/* 1588 */       throw new IllegalArgumentException("Could not load class " + className + " : " + e);
/*      */     } 
/*      */     
/* 1591 */     if (loadedClass != null && !superclassOrInterfaceType.isAssignableFrom(loadedClass)) {
/* 1592 */       if (returnNullIfClassNotFound) {
/* 1593 */         return null;
/*      */       }
/* 1595 */       throw new IllegalArgumentException("Loaded class " + loadedClass.getName() + " cannot be cast to " + superclassOrInterfaceType
/* 1596 */           .getName());
/*      */     } 
/*      */ 
/*      */     
/* 1600 */     Class<T> castClass = (Class)loadedClass;
/* 1601 */     return castClass;
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
/*      */   public static ScanResult fromJSON(String json) {
/*      */     ScanResult scanResult;
/* 1617 */     Matcher matcher = Pattern.compile("\\{[\\n\\r ]*\"format\"[ ]?:[ ]?\"([^\"]+)\"").matcher(json);
/* 1618 */     if (!matcher.find()) {
/* 1619 */       throw new IllegalArgumentException("JSON is not in correct format");
/*      */     }
/* 1621 */     if (!"10".equals(matcher.group(1))) {
/* 1622 */       throw new IllegalArgumentException("JSON was serialized in a different format from the format used by the current version of ClassGraph -- please serialize and deserialize your ScanResult using the same version of ClassGraph");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1629 */     SerializationFormat deserialized = (SerializationFormat)JSONDeserializer.deserializeObject(SerializationFormat.class, json);
/*      */     
/* 1631 */     if (deserialized == null || !deserialized.format.equals("10"))
/*      */     {
/*      */       
/* 1634 */       throw new IllegalArgumentException("JSON was serialized by newer version of ClassGraph");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1641 */     ClassGraph classGraph = new ClassGraph();
/* 1642 */     classGraph.scanSpec = deserialized.scanSpec;
/*      */     
/* 1644 */     AutoCloseableExecutorService executorService = new AutoCloseableExecutorService(ClassGraph.DEFAULT_NUM_WORKER_THREADS);
/*      */     
/* 1646 */     try { scanResult = classGraph.getClasspathScanResult(executorService);
/* 1647 */       executorService.close(); } catch (Throwable throwable) { try { executorService.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }
/* 1648 */      scanResult.rawClasspathEltOrderStrs = deserialized.classpath;
/*      */ 
/*      */ 
/*      */     
/* 1652 */     scanResult.scanSpec = deserialized.scanSpec;
/* 1653 */     scanResult.classNameToClassInfo = new HashMap<>();
/* 1654 */     if (deserialized.classInfo != null) {
/* 1655 */       for (ClassInfo ci : deserialized.classInfo) {
/* 1656 */         scanResult.classNameToClassInfo.put(ci.getName(), ci);
/* 1657 */         ci.setScanResult(scanResult);
/*      */       } 
/*      */     }
/* 1660 */     scanResult.moduleNameToModuleInfo = new HashMap<>();
/* 1661 */     if (deserialized.moduleInfo != null) {
/* 1662 */       for (ModuleInfo mi : deserialized.moduleInfo) {
/* 1663 */         scanResult.moduleNameToModuleInfo.put(mi.getName(), mi);
/*      */       }
/*      */     }
/* 1666 */     scanResult.packageNameToPackageInfo = new HashMap<>();
/* 1667 */     if (deserialized.packageInfo != null) {
/* 1668 */       for (PackageInfo pi : deserialized.packageInfo) {
/* 1669 */         scanResult.packageNameToPackageInfo.put(pi.getName(), pi);
/*      */       }
/*      */     }
/*      */ 
/*      */     
/* 1674 */     scanResult.indexResourcesAndClassInfo(null);
/*      */     
/* 1676 */     scanResult.isObtainedFromDeserialization = true;
/* 1677 */     return scanResult;
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
/*      */   public String toJSON(int indentWidth) {
/* 1689 */     if (this.closed.get()) {
/* 1690 */       throw new IllegalArgumentException("Cannot use a ScanResult after it has been closed");
/*      */     }
/* 1692 */     if (!this.scanSpec.enableClassInfo) {
/* 1693 */       throw new IllegalArgumentException("Please call ClassGraph#enableClassInfo() before #scan()");
/*      */     }
/* 1695 */     List<ClassInfo> allClassInfo = new ArrayList<>(this.classNameToClassInfo.values());
/* 1696 */     CollectionUtils.sortIfNotEmpty(allClassInfo);
/* 1697 */     List<PackageInfo> allPackageInfo = new ArrayList<>(this.packageNameToPackageInfo.values());
/* 1698 */     CollectionUtils.sortIfNotEmpty(allPackageInfo);
/* 1699 */     List<ModuleInfo> allModuleInfo = new ArrayList<>(this.moduleNameToModuleInfo.values());
/* 1700 */     CollectionUtils.sortIfNotEmpty(allModuleInfo);
/* 1701 */     return JSONSerializer.serializeObject(new SerializationFormat("10", this.scanSpec, allClassInfo, allPackageInfo, allModuleInfo, this.rawClasspathEltOrderStrs), indentWidth, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toJSON() {
/* 1711 */     return toJSON(0);
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
/*      */   public boolean isObtainedFromDeserialization() {
/* 1723 */     return this.isObtainedFromDeserialization;
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
/*      */   public void close() {
/* 1739 */     if (!this.closed.getAndSet(true)) {
/* 1740 */       nonClosedWeakReferences.remove(this.weakReference);
/* 1741 */       if (this.classpathOrder != null) {
/* 1742 */         this.classpathOrder.clear();
/* 1743 */         this.classpathOrder = null;
/*      */       } 
/* 1745 */       if (this.allAcceptedResourcesCached != null) {
/* 1746 */         for (Resource classpathResource : this.allAcceptedResourcesCached) {
/* 1747 */           classpathResource.close();
/*      */         }
/* 1749 */         this.allAcceptedResourcesCached.clear();
/* 1750 */         this.allAcceptedResourcesCached = null;
/*      */       } 
/* 1752 */       if (this.pathToAcceptedResourcesCached != null) {
/* 1753 */         this.pathToAcceptedResourcesCached.clear();
/* 1754 */         this.pathToAcceptedResourcesCached = null;
/*      */       } 
/* 1756 */       this.classGraphClassLoader = null;
/* 1757 */       if (this.classNameToClassInfo != null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1765 */       if (this.packageNameToPackageInfo != null) {
/* 1766 */         this.packageNameToPackageInfo.clear();
/* 1767 */         this.packageNameToPackageInfo = null;
/*      */       } 
/* 1769 */       if (this.moduleNameToModuleInfo != null) {
/* 1770 */         this.moduleNameToModuleInfo.clear();
/* 1771 */         this.moduleNameToModuleInfo = null;
/*      */       } 
/* 1773 */       if (this.fileToLastModified != null) {
/* 1774 */         this.fileToLastModified.clear();
/* 1775 */         this.fileToLastModified = null;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1781 */       if (this.nestedJarHandler != null) {
/* 1782 */         this.nestedJarHandler.close(this.topLevelLog);
/* 1783 */         this.nestedJarHandler = null;
/*      */       } 
/* 1785 */       this.classGraphClassLoader = null;
/* 1786 */       this.classpathFinder = null;
/* 1787 */       this.reflectionUtils = null;
/*      */ 
/*      */       
/* 1790 */       if (this.topLevelLog != null) {
/* 1791 */         this.topLevelLog.flush();
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
/*      */   
/*      */   public static void closeAll() {
/* 1810 */     for (WeakReference<ScanResult> nonClosedWeakReference : (Iterable<WeakReference<ScanResult>>)new ArrayList(nonClosedWeakReferences)) {
/* 1811 */       ScanResult scanResult = nonClosedWeakReference.get();
/* 1812 */       if (scanResult != null)
/* 1813 */         scanResult.close(); 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ScanResult.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */