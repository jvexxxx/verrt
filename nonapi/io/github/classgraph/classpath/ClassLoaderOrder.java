/*     */ package nonapi.io.github.classgraph.classpath;
/*     */ 
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.classloaderhandler.ClassLoaderHandlerRegistry;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
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
/*     */ public class ClassLoaderOrder
/*     */ {
/*  50 */   private final List<Map.Entry<ClassLoader, ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry>> classLoaderOrder = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ReflectionUtils reflectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   private final Set<ClassLoader> added = Collections.newSetFromMap(new IdentityHashMap<>());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   private final Set<ClassLoader> delegatedTo = Collections.newSetFromMap(new IdentityHashMap<>());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   private final Set<ClassLoader> allParentClassLoaders = Collections.newSetFromMap(new IdentityHashMap<>());
/*     */ 
/*     */   
/*  78 */   private final Map<ClassLoader, ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry> classLoaderToClassLoaderHandlerRegistryEntry = new IdentityHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassLoaderOrder(ReflectionUtils reflectionUtils) {
/*  84 */     this.reflectionUtils = reflectionUtils;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Map.Entry<ClassLoader, ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry>> getClassLoaderOrder() {
/*  94 */     return this.classLoaderOrder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<ClassLoader> getAllParentClassLoaders() {
/* 103 */     return this.allParentClassLoaders;
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
/*     */   private ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry getRegistryEntry(ClassLoader classLoader, LogNode log) {
/* 116 */     ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry entry = this.classLoaderToClassLoaderHandlerRegistryEntry.get(classLoader);
/* 117 */     if (entry == null) {
/*     */       
/* 119 */       Class<?> currClassLoaderClass = classLoader.getClass();
/* 120 */       for (; currClassLoaderClass != Object.class && currClassLoaderClass != null; 
/* 121 */         currClassLoaderClass = currClassLoaderClass.getSuperclass()) {
/*     */         
/* 123 */         for (ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry ent : ClassLoaderHandlerRegistry.CLASS_LOADER_HANDLERS) {
/* 124 */           if (ent.canHandle(currClassLoaderClass, log)) {
/*     */             
/* 126 */             entry = ent;
/*     */             break;
/*     */           } 
/*     */         } 
/* 130 */         if (entry != null) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 135 */       if (entry == null)
/*     */       {
/* 137 */         entry = ClassLoaderHandlerRegistry.FALLBACK_HANDLER;
/*     */       }
/* 139 */       this.classLoaderToClassLoaderHandlerRegistryEntry.put(classLoader, entry);
/*     */     } 
/* 141 */     return entry;
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
/*     */   public void add(ClassLoader classLoader, LogNode log) {
/* 153 */     if (classLoader == null) {
/*     */       return;
/*     */     }
/* 156 */     if (this.added.add(classLoader)) {
/* 157 */       ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry entry = getRegistryEntry(classLoader, log);
/* 158 */       if (entry != null) {
/* 159 */         this.classLoaderOrder.add(new AbstractMap.SimpleEntry<>(classLoader, entry));
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
/*     */   
/*     */   public void delegateTo(ClassLoader classLoader, boolean isParent, LogNode log) {
/* 175 */     if (classLoader == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 181 */     if (isParent) {
/* 182 */       this.allParentClassLoaders.add(classLoader);
/*     */     }
/*     */     
/* 185 */     if (this.delegatedTo.add(classLoader)) {
/*     */       
/* 187 */       ClassLoaderHandlerRegistry.ClassLoaderHandlerRegistryEntry entry = getRegistryEntry(classLoader, log);
/*     */       
/* 189 */       entry.findClassLoaderOrder(classLoader, this, log);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classpath\ClassLoaderOrder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */