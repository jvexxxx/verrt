/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.Arrays;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicBoolean;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ import nonapi.io.github.classgraph.utils.JarUtils;
/*     */ import nonapi.io.github.classgraph.utils.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModulePathInfo
/*     */ {
/*  57 */   public final Set<String> modulePath = new LinkedHashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   public final Set<String> addModules = new LinkedHashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public final Set<String> patchModules = new LinkedHashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public final Set<String> addExports = new LinkedHashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public final Set<String> addOpens = new LinkedHashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public final Set<String> addReads = new LinkedHashSet<>();
/*     */ 
/*     */   
/* 101 */   private final List<Set<String>> fields = Arrays.asList((Set<String>[])new Set[] { this.modulePath, this.addModules, this.patchModules, this.addExports, this.addOpens, this.addReads });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 111 */   private static final List<String> argSwitches = Arrays.asList(new String[] { "--module-path=", "--add-modules=", "--patch-module=", "--add-exports=", "--add-opens=", "--add-reads=" });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   private static final List<Character> argPartSeparatorChars = Arrays.asList(new Character[] {
/* 122 */         Character.valueOf(File.pathSeparatorChar), 
/* 123 */         Character.valueOf(','), 
/* 124 */         Character.valueOf(false), 
/* 125 */         Character.valueOf(false), 
/* 126 */         Character.valueOf(false), 
/* 127 */         Character.valueOf(false)
/*     */       });
/*     */ 
/*     */   
/* 131 */   private final AtomicBoolean gotRuntimeInfo = new AtomicBoolean();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void getRuntimeInfo(ReflectionUtils reflectionUtils) {
/* 137 */     if (!this.gotRuntimeInfo.getAndSet(true)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       Class<?> managementFactory = reflectionUtils.classForNameOrNull("java.lang.management.ManagementFactory");
/*     */       
/* 145 */       Object runtimeMXBean = (managementFactory == null) ? null : reflectionUtils.invokeStaticMethod(false, managementFactory, "getRuntimeMXBean");
/*     */ 
/*     */ 
/*     */       
/* 149 */       List<String> commandlineArguments = (runtimeMXBean == null) ? null : (List<String>)reflectionUtils.invokeMethod(false, runtimeMXBean, "getInputArguments");
/*     */       
/* 151 */       if (commandlineArguments != null) {
/* 152 */         for (String arg : commandlineArguments) {
/* 153 */           for (int i = 0; i < this.fields.size(); i++) {
/* 154 */             String argSwitch = argSwitches.get(i);
/* 155 */             if (arg.startsWith(argSwitch)) {
/* 156 */               String argParam = arg.substring(argSwitch.length());
/* 157 */               Set<String> argField = this.fields.get(i);
/* 158 */               char sepChar = ((Character)argPartSeparatorChars.get(i)).charValue();
/* 159 */               if (sepChar == '\000') {
/*     */                 
/* 161 */                 argField.add(argParam);
/*     */               } else {
/*     */                 
/* 164 */                 argField.addAll(
/* 165 */                     Arrays.asList(JarUtils.smartPathSplit(argParam, sepChar, null)));
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
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
/*     */   public String toString() {
/* 181 */     StringBuilder buf = new StringBuilder(1024);
/* 182 */     if (!this.modulePath.isEmpty()) {
/* 183 */       buf.append("--module-path=");
/* 184 */       buf.append(StringUtils.join(File.pathSeparator, this.modulePath));
/*     */     } 
/* 186 */     if (!this.addModules.isEmpty()) {
/* 187 */       if (buf.length() > 0) {
/* 188 */         buf.append(' ');
/*     */       }
/* 190 */       buf.append("--add-modules=");
/* 191 */       buf.append(StringUtils.join(",", this.addModules));
/*     */     } 
/* 193 */     for (String patchModulesEntry : this.patchModules) {
/* 194 */       if (buf.length() > 0) {
/* 195 */         buf.append(' ');
/*     */       }
/* 197 */       buf.append("--patch-module=");
/* 198 */       buf.append(patchModulesEntry);
/*     */     } 
/* 200 */     for (String addExportsEntry : this.addExports) {
/* 201 */       if (buf.length() > 0) {
/* 202 */         buf.append(' ');
/*     */       }
/* 204 */       buf.append("--add-exports=");
/* 205 */       buf.append(addExportsEntry);
/*     */     } 
/* 207 */     for (String addOpensEntry : this.addOpens) {
/* 208 */       if (buf.length() > 0) {
/* 209 */         buf.append(' ');
/*     */       }
/* 211 */       buf.append("--add-opens=");
/* 212 */       buf.append(addOpensEntry);
/*     */     } 
/* 214 */     for (String addReadsEntry : this.addReads) {
/* 215 */       if (buf.length() > 0) {
/* 216 */         buf.append(' ');
/*     */       }
/* 218 */       buf.append("--add-reads=");
/* 219 */       buf.append(addReadsEntry);
/*     */     } 
/* 221 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ModulePathInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */