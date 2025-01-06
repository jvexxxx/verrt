/*     */ package nonapi.io.github.classgraph.classloaderhandler;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import nonapi.io.github.classgraph.classpath.ClassLoaderOrder;
/*     */ import nonapi.io.github.classgraph.classpath.ClasspathOrder;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
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
/*     */ public class ClassLoaderHandlerRegistry
/*     */ {
/*  48 */   public static final List<ClassLoaderHandlerRegistryEntry> CLASS_LOADER_HANDLERS = Collections.unmodifiableList(Arrays.asList(new ClassLoaderHandlerRegistryEntry[] { 
/*     */           new ClassLoaderHandlerRegistryEntry(AntClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(EquinoxClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(EquinoxContextFinderClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(FelixClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(JBossClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(WeblogicClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(WebsphereLibertyClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(WebsphereTraditionalClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(OSGiDefaultClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(SpringBootRestartClassLoaderHandler.class), 
/*     */           new ClassLoaderHandlerRegistryEntry(TomcatWebappClassLoaderBaseHandler.class), new ClassLoaderHandlerRegistryEntry(CxfContainerClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(PlexusClassWorldsClassRealmClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(QuarkusClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(UnoOneJarClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(ParentLastDelegationOrderTestClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(JPMSClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(URLClassLoaderHandler.class), new ClassLoaderHandlerRegistryEntry(ClassGraphClassLoaderHandler.class) }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static final ClassLoaderHandlerRegistryEntry FALLBACK_HANDLER = new ClassLoaderHandlerRegistryEntry(FallbackClassLoaderHandler.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   public static final String[] AUTOMATIC_LIB_DIR_PREFIXES = new String[] { "BOOT-INF/lib/", "WEB-INF/lib/", "WEB-INF/lib-provided/", "META-INF/lib/", "lib/", "lib/ext/", "main/" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public static final String[] AUTOMATIC_PACKAGE_ROOT_PREFIXES = new String[] { "classes/", "test-classes/", "BOOT-INF/classes/", "WEB-INF/classes/" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ClassLoaderHandlerRegistryEntry
/*     */   {
/*     */     private final Method canHandleMethod;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final Method findClassLoaderOrderMethod;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final Method findClasspathOrderMethod;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public final Class<? extends ClassLoaderHandler> classLoaderHandlerClass;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private ClassLoaderHandlerRegistryEntry(Class<? extends ClassLoaderHandler> classLoaderHandlerClass) {
/* 157 */       this.classLoaderHandlerClass = classLoaderHandlerClass;
/*     */       try {
/* 159 */         this.canHandleMethod = classLoaderHandlerClass.getDeclaredMethod("canHandle", new Class[] { Class.class, LogNode.class });
/*     */       }
/* 161 */       catch (Exception e) {
/* 162 */         throw new RuntimeException("Could not find canHandle method for " + classLoaderHandlerClass
/* 163 */             .getName(), e);
/*     */       } 
/*     */       try {
/* 166 */         this.findClassLoaderOrderMethod = classLoaderHandlerClass.getDeclaredMethod("findClassLoaderOrder", new Class[] { ClassLoader.class, ClassLoaderOrder.class, LogNode.class });
/*     */       }
/* 168 */       catch (Exception e) {
/* 169 */         throw new RuntimeException("Could not find findClassLoaderOrder method for " + classLoaderHandlerClass
/* 170 */             .getName(), e);
/*     */       } 
/*     */       try {
/* 173 */         this.findClasspathOrderMethod = classLoaderHandlerClass.getDeclaredMethod("findClasspathOrder", new Class[] { ClassLoader.class, ClasspathOrder.class, ScanSpec.class, LogNode.class });
/*     */       }
/* 175 */       catch (Exception e) {
/* 176 */         throw new RuntimeException("Could not find findClasspathOrder method for " + classLoaderHandlerClass
/* 177 */             .getName(), e);
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
/*     */ 
/*     */     
/*     */     public boolean canHandle(Class<?> classLoader, LogNode log) {
/*     */       try {
/* 192 */         return ((Boolean)this.canHandleMethod.invoke(null, new Object[] { classLoader, log })).booleanValue();
/* 193 */       } catch (Throwable e) {
/* 194 */         throw new RuntimeException("Exception while calling canHandle for " + this.classLoaderHandlerClass
/* 195 */             .getName(), e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void findClassLoaderOrder(ClassLoader classLoader, ClassLoaderOrder classLoaderOrder, LogNode log) {
/*     */       try {
/* 213 */         this.findClassLoaderOrderMethod.invoke(null, new Object[] { classLoader, classLoaderOrder, log });
/* 214 */       } catch (Throwable e) {
/* 215 */         throw new RuntimeException("Exception while calling findClassLoaderOrder for " + this.classLoaderHandlerClass
/* 216 */             .getName(), e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void findClasspathOrder(ClassLoader classLoader, ClasspathOrder classpathOrder, ScanSpec scanSpec, LogNode log) {
/*     */       try {
/* 236 */         this.findClasspathOrderMethod.invoke(null, new Object[] { classLoader, classpathOrder, scanSpec, log });
/* 237 */       } catch (Throwable e) {
/* 238 */         throw new RuntimeException("Exception while calling findClassLoaderOrder for " + this.classLoaderHandlerClass
/* 239 */             .getName(), e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\classloaderhandler\ClassLoaderHandlerRegistry.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */