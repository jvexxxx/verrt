/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class MethodInfoList
/*     */   extends InfoList<MethodInfo>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  44 */   static final MethodInfoList EMPTY_LIST = new MethodInfoList();
/*     */   static {
/*  46 */     EMPTY_LIST.makeUnmodifiable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MethodInfoList emptyList() {
/*  55 */     return EMPTY_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodInfoList() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodInfoList(int sizeHint) {
/*  70 */     super(sizeHint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MethodInfoList(Collection<MethodInfo> methodInfoCollection) {
/*  81 */     super(methodInfoCollection);
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
/*     */   protected void findReferencedClassInfo(Map<String, ClassInfo> classNameToClassInfo, Set<ClassInfo> refdClassInfo, LogNode log) {
/*  98 */     for (MethodInfo mi : this) {
/*  99 */       mi.findReferencedClassInfo(classNameToClassInfo, refdClassInfo, log);
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
/*     */   public Map<String, MethodInfoList> asMap() {
/* 116 */     Map<String, MethodInfoList> methodNameToMethodInfoList = new HashMap<>();
/* 117 */     for (MethodInfo methodInfo : this) {
/* 118 */       String name = methodInfo.getName();
/* 119 */       MethodInfoList methodInfoList = methodNameToMethodInfoList.get(name);
/* 120 */       if (methodInfoList == null) {
/* 121 */         methodInfoList = new MethodInfoList(1);
/* 122 */         methodNameToMethodInfoList.put(name, methodInfoList);
/*     */       } 
/* 124 */       methodInfoList.add(methodInfo);
/*     */     } 
/* 126 */     return methodNameToMethodInfoList;
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
/*     */   public boolean containsName(String methodName) {
/* 139 */     for (MethodInfo mi : this) {
/* 140 */       if (mi.getName().equals(methodName)) {
/* 141 */         return true;
/*     */       }
/*     */     } 
/* 144 */     return false;
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
/*     */   public MethodInfoList get(String methodName) {
/* 159 */     boolean hasMethodWithName = false;
/* 160 */     for (MethodInfo mi : this) {
/* 161 */       if (mi.getName().equals(methodName)) {
/* 162 */         hasMethodWithName = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 166 */     if (!hasMethodWithName) {
/* 167 */       return EMPTY_LIST;
/*     */     }
/* 169 */     MethodInfoList matchingMethods = new MethodInfoList(2);
/* 170 */     for (MethodInfo mi : this) {
/* 171 */       if (mi.getName().equals(methodName)) {
/* 172 */         matchingMethods.add(mi);
/*     */       }
/*     */     } 
/* 175 */     return matchingMethods;
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
/*     */   public MethodInfo getSingleMethod(String methodName) {
/* 191 */     int numMethodsWithName = 0;
/* 192 */     MethodInfo lastFoundMethod = null;
/* 193 */     for (MethodInfo mi : this) {
/* 194 */       if (mi.getName().equals(methodName)) {
/* 195 */         numMethodsWithName++;
/* 196 */         lastFoundMethod = mi;
/*     */       } 
/*     */     } 
/* 199 */     if (numMethodsWithName == 0)
/* 200 */       return null; 
/* 201 */     if (numMethodsWithName == 1) {
/* 202 */       return lastFoundMethod;
/*     */     }
/* 204 */     throw new IllegalArgumentException("There are multiple methods named \"" + methodName + "\" in class " + ((MethodInfo)
/* 205 */         iterator().next()).getClassInfo().getName());
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
/*     */   public MethodInfoList filter(MethodInfoFilter filter) {
/* 237 */     MethodInfoList methodInfoFiltered = new MethodInfoList();
/* 238 */     for (MethodInfo resource : this) {
/* 239 */       if (filter.accept(resource)) {
/* 240 */         methodInfoFiltered.add(resource);
/*     */       }
/*     */     } 
/* 243 */     return methodInfoFiltered;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface MethodInfoFilter {
/*     */     boolean accept(MethodInfo param1MethodInfo);
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\MethodInfoList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */