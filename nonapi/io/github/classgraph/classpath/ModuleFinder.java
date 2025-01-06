/*     */ package nonapi.io.github.classgraph.classpath;
/*     */ 
/*     */ import io.github.classgraph.ModuleRef;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Deque;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ import nonapi.io.github.classgraph.utils.CollectionUtils;
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
/*     */ 
/*     */ 
/*     */ public class ModuleFinder
/*     */ {
/*     */   private List<ModuleRef> systemModuleRefs;
/*     */   private List<ModuleRef> nonSystemModuleRefs;
/*     */   private boolean forceScanJavaClassPath;
/*     */   private final ReflectionUtils reflectionUtils;
/*     */   
/*     */   public List<ModuleRef> getSystemModuleRefs() {
/*  68 */     return this.systemModuleRefs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ModuleRef> getNonSystemModuleRefs() {
/*  78 */     return this.nonSystemModuleRefs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean forceScanJavaClassPath() {
/*  87 */     return this.forceScanJavaClassPath;
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
/*     */   private void findLayerOrder(Object layer, Set<Object> layerVisited, Set<Object> parentLayers, Deque<Object> layerOrderOut) {
/* 113 */     if (layerVisited.add(layer)) {
/*     */ 
/*     */       
/* 116 */       List<Object> parents = (List<Object>)this.reflectionUtils.invokeMethod(true, layer, "parents");
/* 117 */       if (parents != null) {
/* 118 */         parentLayers.addAll(parents);
/* 119 */         for (Object parent : parents) {
/* 120 */           findLayerOrder(parent, layerVisited, parentLayers, layerOrderOut);
/*     */         }
/*     */       } 
/* 123 */       layerOrderOut.push(layer);
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
/*     */   private List<ModuleRef> findModuleRefs(LinkedHashSet<Object> layers, ScanSpec scanSpec, LogNode log) {
/*     */     List<Object> layerOrderFinal;
/* 140 */     if (layers.isEmpty()) {
/* 141 */       return Collections.emptyList();
/*     */     }
/*     */ 
/*     */     
/* 145 */     Deque<Object> layerOrder = new ArrayDeque();
/* 146 */     Set<Object> parentLayers = new HashSet();
/* 147 */     for (Object layer : layers) {
/* 148 */       if (layer != null) {
/* 149 */         findLayerOrder(layer, new HashSet(), parentLayers, layerOrder);
/*     */       }
/*     */     } 
/* 152 */     if (scanSpec.addedModuleLayers != null) {
/* 153 */       for (Object layer : scanSpec.addedModuleLayers) {
/* 154 */         if (layer != null) {
/* 155 */           findLayerOrder(layer, new HashSet(), parentLayers, layerOrder);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 162 */     if (scanSpec.ignoreParentModuleLayers) {
/* 163 */       layerOrderFinal = new ArrayList();
/* 164 */       for (Object layer : layerOrder) {
/* 165 */         if (!parentLayers.contains(layer)) {
/* 166 */           layerOrderFinal.add(layer);
/*     */         }
/*     */       } 
/*     */     } else {
/* 170 */       layerOrderFinal = new ArrayList(layerOrder);
/*     */     } 
/*     */ 
/*     */     
/* 174 */     Set<Object> addedModules = new HashSet();
/* 175 */     LinkedHashSet<ModuleRef> moduleRefOrder = new LinkedHashSet<>();
/* 176 */     for (Object layer : layerOrderFinal) {
/*     */       
/* 178 */       Object configuration = this.reflectionUtils.invokeMethod(true, layer, "configuration");
/* 179 */       if (configuration != null) {
/*     */ 
/*     */ 
/*     */         
/* 183 */         Set<Object> modules = (Set<Object>)this.reflectionUtils.invokeMethod(true, configuration, "modules");
/* 184 */         if (modules != null) {
/* 185 */           List<ModuleRef> modulesInLayer = new ArrayList<>();
/* 186 */           for (Object module : modules) {
/*     */             
/* 188 */             Object moduleReference = this.reflectionUtils.invokeMethod(true, module, "reference");
/* 189 */             if (moduleReference != null && addedModules.add(moduleReference)) {
/*     */               try {
/* 191 */                 modulesInLayer.add(new ModuleRef(moduleReference, layer, this.reflectionUtils));
/* 192 */               } catch (IllegalArgumentException e) {
/* 193 */                 if (log != null) {
/* 194 */                   log.log("Exception while creating ModuleRef for module " + moduleReference, e);
/*     */                 }
/*     */               } 
/*     */             }
/*     */           } 
/*     */           
/* 200 */           CollectionUtils.sortIfNotEmpty(modulesInLayer);
/* 201 */           moduleRefOrder.addAll(modulesInLayer);
/*     */         } 
/*     */       } 
/*     */     } 
/* 205 */     return new ArrayList<>(moduleRefOrder);
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
/*     */   private List<ModuleRef> findModuleRefsFromCallstack(Class<?>[] callStack, ScanSpec scanSpec, boolean scanNonSystemModules, LogNode log) {
/* 223 */     LinkedHashSet<Object> layers = new LinkedHashSet();
/* 224 */     if (callStack != null) {
/* 225 */       for (Class<?> stackFrameClass : callStack) {
/* 226 */         Object module = this.reflectionUtils.invokeMethod(false, stackFrameClass, "getModule");
/*     */         
/* 228 */         if (module != null) {
/* 229 */           Object layer = this.reflectionUtils.invokeMethod(true, module, "getLayer");
/*     */           
/* 231 */           if (layer != null) {
/* 232 */             layers.add(layer);
/* 233 */           } else if (scanNonSystemModules) {
/*     */ 
/*     */             
/* 236 */             this.forceScanJavaClassPath = true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 242 */     Class<?> moduleLayerClass = null;
/*     */     try {
/* 244 */       moduleLayerClass = Class.forName("java.lang.ModuleLayer");
/* 245 */     } catch (ClassNotFoundException|LinkageError classNotFoundException) {}
/*     */ 
/*     */     
/* 248 */     if (moduleLayerClass != null) {
/*     */       
/* 250 */       Object bootLayer = this.reflectionUtils.invokeStaticMethod(false, moduleLayerClass, "boot");
/* 251 */       if (bootLayer != null) {
/* 252 */         layers.add(bootLayer);
/* 253 */       } else if (scanNonSystemModules) {
/*     */ 
/*     */ 
/*     */         
/* 257 */         this.forceScanJavaClassPath = true;
/*     */       } 
/*     */     } 
/* 260 */     return findModuleRefs(layers, scanSpec, log);
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
/*     */   public ModuleFinder(Class<?>[] callStack, ScanSpec scanSpec, boolean scanNonSystemModules, boolean scanSystemModules, ReflectionUtils reflectionUtils, LogNode log) {
/* 281 */     this.reflectionUtils = reflectionUtils;
/*     */ 
/*     */     
/* 284 */     List<ModuleRef> allModuleRefsList = null;
/* 285 */     if (scanSpec.overrideModuleLayers == null) {
/*     */       
/* 287 */       if (callStack != null && callStack.length > 0) {
/* 288 */         allModuleRefsList = findModuleRefsFromCallstack(callStack, scanSpec, scanNonSystemModules, log);
/*     */       }
/*     */     } else {
/* 291 */       if (log != null) {
/* 292 */         LogNode subLog = log.log("Overriding module layers");
/* 293 */         for (Object moduleLayer : scanSpec.overrideModuleLayers) {
/* 294 */           subLog.log(moduleLayer.toString());
/*     */         }
/*     */       } 
/* 297 */       allModuleRefsList = findModuleRefs(new LinkedHashSet(scanSpec.overrideModuleLayers), scanSpec, log);
/*     */     } 
/* 299 */     if (allModuleRefsList != null) {
/*     */       
/* 301 */       this.systemModuleRefs = new ArrayList<>();
/* 302 */       this.nonSystemModuleRefs = new ArrayList<>();
/* 303 */       for (ModuleRef moduleRef : allModuleRefsList) {
/* 304 */         if (moduleRef != null) {
/* 305 */           boolean isSystemModule = moduleRef.isSystemModule();
/* 306 */           if (isSystemModule && scanSystemModules) {
/* 307 */             this.systemModuleRefs.add(moduleRef); continue;
/* 308 */           }  if (!isSystemModule && scanNonSystemModules) {
/* 309 */             this.nonSystemModuleRefs.add(moduleRef);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 315 */     if (log != null) {
/* 316 */       if (scanSystemModules) {
/* 317 */         LogNode sysSubLog = log.log("System modules found:");
/* 318 */         if (this.systemModuleRefs != null && !this.systemModuleRefs.isEmpty()) {
/* 319 */           for (ModuleRef moduleRef : this.systemModuleRefs) {
/* 320 */             sysSubLog.log(moduleRef.toString());
/*     */           }
/*     */         } else {
/* 323 */           sysSubLog.log("[None]");
/*     */         } 
/*     */       } else {
/* 326 */         log.log("Scanning of system modules is not enabled");
/*     */       } 
/* 328 */       if (scanNonSystemModules) {
/* 329 */         LogNode nonSysSubLog = log.log("Non-system modules found:");
/* 330 */         if (this.nonSystemModuleRefs != null && !this.nonSystemModuleRefs.isEmpty()) {
/* 331 */           for (ModuleRef moduleRef : this.nonSystemModuleRefs) {
/* 332 */             nonSysSubLog.log(moduleRef.toString());
/*     */           }
/*     */         } else {
/* 335 */           nonSysSubLog.log("[None]");
/*     */         } 
/*     */       } else {
/* 338 */         log.log("Scanning of non-system modules is not enabled");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classpath\ModuleFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */