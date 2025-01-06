/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.BitSet;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.scanspec.ScanSpec;
/*     */ import nonapi.io.github.classgraph.utils.CollectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class GraphvizDotfileGenerator
/*     */ {
/*     */   private static final String STANDARD_CLASS_COLOR = "fff2b6";
/*     */   private static final String INTERFACE_COLOR = "b6e7ff";
/*     */   private static final String ANNOTATION_COLOR = "f3c9ff";
/*     */   private static final int PARAM_WRAP_WIDTH = 40;
/*  53 */   private static final BitSet IS_UNICODE_WHITESPACE = new BitSet(65536);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  67 */     String wsChars = " \t\n\013\f\r  ᠎               　";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     for (int i = 0; i < " \t\n\013\f\r  ᠎               　".length(); i++) {
/*  94 */       IS_UNICODE_WHITESPACE.set(" \t\n\013\f\r  ᠎               　".charAt(i));
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
/*     */   private static boolean isUnicodeWhitespace(char c) {
/* 106 */     return IS_UNICODE_WHITESPACE.get(c);
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
/*     */   private static void htmlEncode(CharSequence unsafeStr, boolean turnNewlineIntoBreak, StringBuilder buf) {
/* 121 */     for (int i = 0, n = unsafeStr.length(); i < n; i++) {
/* 122 */       char c = unsafeStr.charAt(i);
/* 123 */       switch (c) {
/*     */         case '&':
/* 125 */           buf.append("&amp;");
/*     */           break;
/*     */         case '<':
/* 128 */           buf.append("&lt;");
/*     */           break;
/*     */         case '>':
/* 131 */           buf.append("&gt;");
/*     */           break;
/*     */         case '"':
/* 134 */           buf.append("&quot;");
/*     */           break;
/*     */         case '\'':
/* 137 */           buf.append("&#x27;");
/*     */           break;
/*     */         case '\\':
/* 140 */           buf.append("&lsol;");
/*     */           break;
/*     */         case '/':
/* 143 */           buf.append("&#x2F;");
/*     */           break;
/*     */         
/*     */         case '—':
/* 147 */           buf.append("&mdash;");
/*     */           break;
/*     */         case '–':
/* 150 */           buf.append("&ndash;");
/*     */           break;
/*     */         case '“':
/* 153 */           buf.append("&ldquo;");
/*     */           break;
/*     */         case '”':
/* 156 */           buf.append("&rdquo;");
/*     */           break;
/*     */         case '‘':
/* 159 */           buf.append("&lsquo;");
/*     */           break;
/*     */         case '’':
/* 162 */           buf.append("&rsquo;");
/*     */           break;
/*     */         case '«':
/* 165 */           buf.append("&laquo;");
/*     */           break;
/*     */         case '»':
/* 168 */           buf.append("&raquo;");
/*     */           break;
/*     */         case '£':
/* 171 */           buf.append("&pound;");
/*     */           break;
/*     */         case '©':
/* 174 */           buf.append("&copy;");
/*     */           break;
/*     */         case '®':
/* 177 */           buf.append("&reg;");
/*     */           break;
/*     */         case ' ':
/* 180 */           buf.append("&nbsp;");
/*     */           break;
/*     */         case '\n':
/* 183 */           if (turnNewlineIntoBreak) {
/* 184 */             buf.append("<br>"); break;
/*     */           } 
/* 186 */           buf.append(' ');
/*     */           break;
/*     */         
/*     */         default:
/* 190 */           if (c <= ' ' || isUnicodeWhitespace(c)) {
/* 191 */             buf.append(' '); break;
/*     */           } 
/* 193 */           buf.append(c);
/*     */           break;
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
/*     */   private static void htmlEncode(CharSequence unsafeStr, StringBuilder buf) {
/* 209 */     htmlEncode(unsafeStr, false, buf);
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
/*     */   private static void labelClassNodeHTML(ClassInfo ci, String shape, String boxBgColor, boolean showFields, boolean showMethods, boolean useSimpleNames, ScanSpec scanSpec, StringBuilder buf) {
/* 235 */     buf.append("[shape=").append(shape).append(",style=filled,fillcolor=\"#").append(boxBgColor)
/* 236 */       .append("\",label=");
/* 237 */     buf.append('<');
/* 238 */     buf.append("<table border='0' cellborder='0' cellspacing='1'>");
/*     */ 
/*     */     
/* 241 */     buf.append("<tr><td><font point-size='12'>").append(ci.getModifiersStr()).append(' ')
/* 242 */       .append(ci.isEnum() ? "enum" : (
/* 243 */         ci.isAnnotation() ? "@interface" : (ci.isInterface() ? "interface" : "class")))
/* 244 */       .append("</font></td></tr>");
/*     */     
/* 246 */     if (ci.getName().contains(".")) {
/* 247 */       buf.append("<tr><td><font point-size='14'><b>");
/* 248 */       htmlEncode(ci.getPackageName() + ".", buf);
/* 249 */       buf.append("</b></font></td></tr>");
/*     */     } 
/*     */ 
/*     */     
/* 253 */     buf.append("<tr><td><font point-size='20'><b>");
/* 254 */     htmlEncode(ci.getSimpleName(), buf);
/* 255 */     buf.append("</b></font></td></tr>");
/*     */ 
/*     */     
/* 258 */     float darkness = 0.8F;
/* 259 */     int r = (int)(Integer.parseInt(boxBgColor.substring(0, 2), 16) * 0.8F);
/* 260 */     int g = (int)(Integer.parseInt(boxBgColor.substring(2, 4), 16) * 0.8F);
/* 261 */     int b = (int)(Integer.parseInt(boxBgColor.substring(4, 6), 16) * 0.8F);
/* 262 */     String darkerColor = String.format("#%s%s%s%s%s%s", new Object[] { Integer.toString(r >> 4, 16), 
/* 263 */           Integer.toString(r & 0xF, 16), Integer.toString(g >> 4, 16), Integer.toString(g & 0xF, 16), 
/* 264 */           Integer.toString(b >> 4, 16), Integer.toString(b & 0xF, 16) });
/*     */ 
/*     */     
/* 267 */     AnnotationInfoList annotationInfo = ci.annotationInfo;
/* 268 */     if (annotationInfo != null && !annotationInfo.isEmpty()) {
/* 269 */       buf.append("<tr><td colspan='3' bgcolor='").append(darkerColor)
/* 270 */         .append("'><font point-size='12'><b>ANNOTATIONS</b></font></td></tr>");
/* 271 */       AnnotationInfoList annotationInfoSorted = new AnnotationInfoList(annotationInfo);
/* 272 */       CollectionUtils.sortIfNotEmpty(annotationInfoSorted);
/* 273 */       for (AnnotationInfo ai : annotationInfoSorted) {
/* 274 */         String annotationName = ai.getName();
/* 275 */         if (!annotationName.startsWith("java.lang.annotation.")) {
/* 276 */           buf.append("<tr>");
/* 277 */           buf.append("<td align='center' valign='top'>");
/* 278 */           htmlEncode(ai.toString(), buf);
/* 279 */           buf.append("</td></tr>");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 285 */     FieldInfoList fieldInfo = ci.fieldInfo;
/* 286 */     if (showFields && fieldInfo != null && !fieldInfo.isEmpty()) {
/* 287 */       FieldInfoList fieldInfoSorted = new FieldInfoList(fieldInfo);
/* 288 */       CollectionUtils.sortIfNotEmpty(fieldInfoSorted);
/* 289 */       for (int i = fieldInfoSorted.size() - 1; i >= 0; i--) {
/*     */         
/* 291 */         if (fieldInfoSorted.get(i).getName().equals("serialVersionUID")) {
/* 292 */           fieldInfoSorted.remove(i);
/*     */         }
/*     */       } 
/* 295 */       if (!fieldInfoSorted.isEmpty()) {
/* 296 */         buf.append("<tr><td colspan='3' bgcolor='").append(darkerColor)
/* 297 */           .append("'><font point-size='12'><b>")
/* 298 */           .append(scanSpec.ignoreFieldVisibility ? "" : "PUBLIC ")
/* 299 */           .append("FIELDS</b></font></td></tr>");
/* 300 */         buf.append("<tr><td cellpadding='0'>");
/* 301 */         buf.append("<table border='0' cellborder='0'>");
/* 302 */         for (FieldInfo fi : fieldInfoSorted) {
/* 303 */           buf.append("<tr>");
/* 304 */           buf.append("<td align='right' valign='top'>");
/*     */ 
/*     */           
/* 307 */           AnnotationInfoList fieldAnnotationInfo = fi.annotationInfo;
/* 308 */           if (fieldAnnotationInfo != null) {
/* 309 */             for (AnnotationInfo ai : fieldAnnotationInfo) {
/* 310 */               if (buf.charAt(buf.length() - 1) != ' ') {
/* 311 */                 buf.append(' ');
/*     */               }
/* 313 */               htmlEncode(ai.toString(), buf);
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/* 318 */           if (scanSpec.ignoreFieldVisibility) {
/* 319 */             if (buf.charAt(buf.length() - 1) != ' ') {
/* 320 */               buf.append(' ');
/*     */             }
/* 322 */             buf.append(fi.getModifiersStr());
/*     */           } 
/*     */ 
/*     */           
/* 326 */           if (buf.charAt(buf.length() - 1) != ' ') {
/* 327 */             buf.append(' ');
/*     */           }
/* 329 */           TypeSignature typeSig = fi.getTypeSignatureOrTypeDescriptor();
/* 330 */           htmlEncode(useSimpleNames ? typeSig.toStringWithSimpleNames() : typeSig.toString(), buf);
/* 331 */           buf.append("</td>");
/*     */ 
/*     */           
/* 334 */           buf.append("<td align='left' valign='top'><b>");
/* 335 */           String fieldName = fi.getName();
/* 336 */           htmlEncode(fieldName, buf);
/* 337 */           buf.append("</b></td></tr>");
/*     */         } 
/* 339 */         buf.append("</table>");
/* 340 */         buf.append("</td></tr>");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 345 */     MethodInfoList methodInfo = ci.methodInfo;
/* 346 */     if (showMethods && methodInfo != null) {
/* 347 */       MethodInfoList methodInfoSorted = new MethodInfoList(methodInfo);
/* 348 */       CollectionUtils.sortIfNotEmpty(methodInfoSorted);
/* 349 */       for (int i = methodInfoSorted.size() - 1; i >= 0; i--) {
/*     */         
/* 351 */         MethodInfo mi = methodInfoSorted.get(i);
/* 352 */         String name = mi.getName();
/* 353 */         int numParam = (mi.getParameterInfo()).length;
/* 354 */         if (name.equals("<clinit>") || (name.equals("hashCode") && numParam == 0) || (name
/* 355 */           .equals("toString") && numParam == 0) || (name.equals("equals") && numParam == 1 && mi
/* 356 */           .getTypeDescriptor().toString().equals("boolean (java.lang.Object)"))) {
/* 357 */           methodInfoSorted.remove(i);
/*     */         }
/*     */       } 
/* 360 */       if (!methodInfoSorted.isEmpty()) {
/* 361 */         buf.append("<tr><td cellpadding='0'>");
/* 362 */         buf.append("<table border='0' cellborder='0'>");
/* 363 */         buf.append("<tr><td colspan='3' bgcolor='").append(darkerColor)
/* 364 */           .append("'><font point-size='12'><b>")
/* 365 */           .append(scanSpec.ignoreMethodVisibility ? "" : "PUBLIC ")
/* 366 */           .append("METHODS</b></font></td></tr>");
/* 367 */         for (MethodInfo mi : methodInfoSorted) {
/* 368 */           buf.append("<tr>");
/*     */ 
/*     */ 
/*     */           
/* 372 */           buf.append("<td align='right' valign='top'>");
/* 373 */           AnnotationInfoList methodAnnotationInfo = mi.annotationInfo;
/* 374 */           if (methodAnnotationInfo != null) {
/* 375 */             for (AnnotationInfo ai : methodAnnotationInfo) {
/* 376 */               if (buf.charAt(buf.length() - 1) != ' ') {
/* 377 */                 buf.append(' ');
/*     */               }
/* 379 */               htmlEncode(ai.toString(), buf);
/*     */             } 
/*     */           }
/*     */ 
/*     */           
/* 384 */           if (scanSpec.ignoreMethodVisibility) {
/* 385 */             if (buf.charAt(buf.length() - 1) != ' ') {
/* 386 */               buf.append(' ');
/*     */             }
/* 388 */             buf.append(mi.getModifiersStr());
/*     */           } 
/*     */ 
/*     */           
/* 392 */           if (buf.charAt(buf.length() - 1) != ' ') {
/* 393 */             buf.append(' ');
/*     */           }
/* 395 */           if (!mi.getName().equals("<init>")) {
/*     */             
/* 397 */             TypeSignature resultTypeSig = mi.getTypeSignatureOrTypeDescriptor().getResultType();
/* 398 */             htmlEncode(
/* 399 */                 useSimpleNames ? resultTypeSig.toStringWithSimpleNames() : resultTypeSig.toString(), buf);
/*     */           } else {
/*     */             
/* 402 */             buf.append("<b>&lt;constructor&gt;</b>");
/*     */           } 
/* 404 */           buf.append("</td>");
/*     */ 
/*     */           
/* 407 */           buf.append("<td align='left' valign='top'>");
/* 408 */           buf.append("<b>");
/* 409 */           if (mi.getName().equals("<init>")) {
/*     */             
/* 411 */             htmlEncode(ci.getSimpleName(), buf);
/*     */           } else {
/* 413 */             htmlEncode(mi.getName(), buf);
/*     */           } 
/* 415 */           buf.append("</b>&nbsp;");
/* 416 */           buf.append("</td>");
/*     */ 
/*     */           
/* 419 */           buf.append("<td align='left' valign='top'>");
/* 420 */           buf.append('(');
/* 421 */           MethodParameterInfo[] paramInfo = mi.getParameterInfo();
/* 422 */           if (paramInfo.length != 0) {
/* 423 */             for (int j = 0, wrapPos = 0; j < paramInfo.length; j++) {
/* 424 */               if (j > 0) {
/* 425 */                 buf.append(", ");
/* 426 */                 wrapPos += 2;
/*     */               } 
/* 428 */               if (wrapPos > 40) {
/* 429 */                 buf.append("</td></tr><tr><td></td><td></td><td align='left' valign='top'>");
/* 430 */                 wrapPos = 0;
/*     */               } 
/*     */ 
/*     */               
/* 434 */               AnnotationInfo[] paramAnnotationInfo = (paramInfo[j]).annotationInfo;
/* 435 */               if (paramAnnotationInfo != null) {
/* 436 */                 for (AnnotationInfo ai : paramAnnotationInfo) {
/* 437 */                   String ais = ai.toString();
/* 438 */                   if (!ais.isEmpty()) {
/* 439 */                     if (buf.charAt(buf.length() - 1) != ' ') {
/* 440 */                       buf.append(' ');
/*     */                     }
/* 442 */                     htmlEncode(ais, buf);
/* 443 */                     wrapPos += 1 + ais.length();
/* 444 */                     if (wrapPos > 40) {
/* 445 */                       buf.append("</td></tr><tr><td></td><td></td><td align='left' valign='top'>");
/*     */                       
/* 447 */                       wrapPos = 0;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               }
/*     */ 
/*     */               
/* 454 */               TypeSignature paramTypeSig = paramInfo[j].getTypeSignatureOrTypeDescriptor();
/*     */               
/* 456 */               String paramTypeStr = useSimpleNames ? paramTypeSig.toStringWithSimpleNames() : paramTypeSig.toString();
/* 457 */               htmlEncode(paramTypeStr, buf);
/* 458 */               wrapPos += paramTypeStr.length();
/*     */ 
/*     */               
/* 461 */               String paramName = paramInfo[j].getName();
/* 462 */               if (paramName != null) {
/* 463 */                 buf.append(" <B>");
/* 464 */                 htmlEncode(paramName, buf);
/* 465 */                 wrapPos += 1 + paramName.length();
/* 466 */                 buf.append("</B>");
/*     */               } 
/*     */             } 
/*     */           }
/* 470 */           buf.append(')');
/* 471 */           buf.append("</td></tr>");
/*     */         } 
/* 473 */         buf.append("</table>");
/* 474 */         buf.append("</td></tr>");
/*     */       } 
/*     */     } 
/* 477 */     buf.append("</table>");
/* 478 */     buf.append(">]");
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
/*     */   static String generateGraphVizDotFile(ClassInfoList classInfoList, float sizeX, float sizeY, boolean showFields, boolean showFieldTypeDependencyEdges, boolean showMethods, boolean showMethodTypeDependencyEdges, boolean showAnnotations, boolean useSimpleNames, ScanSpec scanSpec) {
/* 512 */     StringBuilder buf = new StringBuilder(1048576);
/* 513 */     buf.append("digraph {\n");
/* 514 */     buf.append("size=\"").append(sizeX).append(',').append(sizeY).append("\";\n");
/* 515 */     buf.append("layout=dot;\n");
/* 516 */     buf.append("rankdir=\"BT\";\n");
/* 517 */     buf.append("overlap=false;\n");
/* 518 */     buf.append("splines=true;\n");
/* 519 */     buf.append("pack=true;\n");
/* 520 */     buf.append("graph [fontname = \"Courier, Regular\"]\n");
/* 521 */     buf.append("node [fontname = \"Courier, Regular\"]\n");
/* 522 */     buf.append("edge [fontname = \"Courier, Regular\"]\n");
/*     */     
/* 524 */     ClassInfoList standardClassNodes = classInfoList.getStandardClasses();
/* 525 */     ClassInfoList interfaceNodes = classInfoList.getInterfaces();
/* 526 */     ClassInfoList annotationNodes = classInfoList.getAnnotations();
/*     */     
/* 528 */     for (ClassInfo node : standardClassNodes) {
/* 529 */       buf.append('"').append(node.getName()).append('"');
/* 530 */       labelClassNodeHTML(node, "box", "fff2b6", showFields, showMethods, useSimpleNames, scanSpec, buf);
/*     */       
/* 532 */       buf.append(";\n");
/*     */     } 
/*     */     
/* 535 */     for (ClassInfo node : interfaceNodes) {
/* 536 */       buf.append('"').append(node.getName()).append('"');
/* 537 */       labelClassNodeHTML(node, "diamond", "b6e7ff", showFields, showMethods, useSimpleNames, scanSpec, buf);
/*     */       
/* 539 */       buf.append(";\n");
/*     */     } 
/*     */     
/* 542 */     for (ClassInfo node : annotationNodes) {
/* 543 */       buf.append('"').append(node.getName()).append('"');
/* 544 */       labelClassNodeHTML(node, "oval", "f3c9ff", showFields, showMethods, useSimpleNames, scanSpec, buf);
/*     */       
/* 546 */       buf.append(";\n");
/*     */     } 
/*     */     
/* 549 */     Set<String> allVisibleNodes = new HashSet<>();
/* 550 */     allVisibleNodes.addAll(standardClassNodes.getNames());
/* 551 */     allVisibleNodes.addAll(interfaceNodes.getNames());
/* 552 */     allVisibleNodes.addAll(annotationNodes.getNames());
/*     */     
/* 554 */     buf.append('\n');
/* 555 */     for (ClassInfo classNode : standardClassNodes) {
/* 556 */       for (ClassInfo directSuperclassNode : classNode.getSuperclasses().directOnly()) {
/* 557 */         if (directSuperclassNode != null && allVisibleNodes.contains(directSuperclassNode.getName()) && 
/* 558 */           !directSuperclassNode.getName().equals("java.lang.Object"))
/*     */         {
/* 560 */           buf.append("  \"").append(classNode.getName()).append("\" -> \"")
/* 561 */             .append(directSuperclassNode.getName()).append("\" [arrowsize=2.5]\n");
/*     */         }
/*     */       } 
/*     */       
/* 565 */       for (ClassInfo implementedInterfaceNode : classNode.getInterfaces().directOnly()) {
/* 566 */         if (allVisibleNodes.contains(implementedInterfaceNode.getName()))
/*     */         {
/* 568 */           buf.append("  \"").append(classNode.getName()).append("\" -> \"")
/* 569 */             .append(implementedInterfaceNode.getName())
/* 570 */             .append("\" [arrowhead=diamond, arrowsize=2.5]\n");
/*     */         }
/*     */       } 
/*     */       
/* 574 */       if (showFieldTypeDependencyEdges && classNode.fieldInfo != null) {
/* 575 */         for (FieldInfo fi : classNode.fieldInfo) {
/* 576 */           for (ClassInfo referencedFieldType : fi.findReferencedClassInfo(null)) {
/* 577 */             if (allVisibleNodes.contains(referencedFieldType.getName()))
/*     */             {
/* 579 */               buf.append("  \"").append(referencedFieldType.getName()).append("\" -> \"")
/* 580 */                 .append(classNode.getName())
/* 581 */                 .append("\" [arrowtail=obox, arrowsize=2.5, dir=back]\n");
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 587 */       if (showMethodTypeDependencyEdges && classNode.methodInfo != null) {
/* 588 */         for (MethodInfo mi : classNode.methodInfo) {
/* 589 */           for (ClassInfo referencedMethodType : mi.findReferencedClassInfo(null)) {
/* 590 */             if (allVisibleNodes.contains(referencedMethodType.getName()))
/*     */             {
/* 592 */               buf.append("  \"").append(referencedMethodType.getName()).append("\" -> \"")
/* 593 */                 .append(classNode.getName())
/* 594 */                 .append("\" [arrowtail=box, arrowsize=2.5, dir=back]\n");
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 600 */     for (ClassInfo interfaceNode : interfaceNodes) {
/* 601 */       for (ClassInfo superinterfaceNode : interfaceNode.getInterfaces().directOnly()) {
/* 602 */         if (allVisibleNodes.contains(superinterfaceNode.getName()))
/*     */         {
/* 604 */           buf.append("  \"").append(interfaceNode.getName()).append("\" -> \"")
/* 605 */             .append(superinterfaceNode.getName()).append("\" [arrowhead=diamond, arrowsize=2.5]\n");
/*     */         }
/*     */       } 
/*     */     } 
/* 609 */     if (showAnnotations) {
/* 610 */       for (ClassInfo annotationNode : annotationNodes) {
/* 611 */         for (ClassInfo annotatedClassNode : annotationNode.getClassesWithAnnotationDirectOnly()) {
/* 612 */           if (allVisibleNodes.contains(annotatedClassNode.getName()))
/*     */           {
/* 614 */             buf.append("  \"").append(annotatedClassNode.getName()).append("\" -> \"")
/* 615 */               .append(annotationNode.getName()).append("\" [arrowhead=dot, arrowsize=2.5]\n");
/*     */           }
/*     */         } 
/* 618 */         for (ClassInfo classWithMethodAnnotationNode : annotationNode
/* 619 */           .getClassesWithMethodAnnotationDirectOnly()) {
/* 620 */           if (allVisibleNodes.contains(classWithMethodAnnotationNode.getName()))
/*     */           {
/* 622 */             buf.append("  \"").append(classWithMethodAnnotationNode.getName()).append("\" -> \"")
/* 623 */               .append(annotationNode.getName()).append("\" [arrowhead=odot, arrowsize=2.5]\n");
/*     */           }
/*     */         } 
/* 626 */         for (ClassInfo classWithMethodAnnotationNode : annotationNode
/* 627 */           .getClassesWithFieldAnnotationDirectOnly()) {
/* 628 */           if (allVisibleNodes.contains(classWithMethodAnnotationNode.getName()))
/*     */           {
/* 630 */             buf.append("  \"").append(classWithMethodAnnotationNode.getName()).append("\" -> \"")
/* 631 */               .append(annotationNode.getName()).append("\" [arrowhead=odot, arrowsize=2.5]\n");
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 636 */     buf.append('}');
/* 637 */     return buf.toString();
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
/*     */   static String generateGraphVizDotFileFromInterClassDependencies(ClassInfoList classInfoList, float sizeX, float sizeY, boolean includeExternalClasses) {
/* 662 */     StringBuilder buf = new StringBuilder(1048576);
/* 663 */     buf.append("digraph {\n");
/* 664 */     buf.append("size=\"").append(sizeX).append(',').append(sizeY).append("\";\n");
/* 665 */     buf.append("layout=dot;\n");
/* 666 */     buf.append("rankdir=\"BT\";\n");
/* 667 */     buf.append("overlap=false;\n");
/* 668 */     buf.append("splines=true;\n");
/* 669 */     buf.append("pack=true;\n");
/* 670 */     buf.append("graph [fontname = \"Courier, Regular\"]\n");
/* 671 */     buf.append("node [fontname = \"Courier, Regular\"]\n");
/* 672 */     buf.append("edge [fontname = \"Courier, Regular\"]\n");
/*     */     
/* 674 */     Set<ClassInfo> allVisibleNodes = new HashSet<>(classInfoList);
/* 675 */     if (includeExternalClasses) {
/* 676 */       for (ClassInfo ci : classInfoList) {
/* 677 */         allVisibleNodes.addAll(ci.getClassDependencies());
/*     */       }
/*     */     }
/*     */     
/* 681 */     for (ClassInfo ci : allVisibleNodes) {
/* 682 */       buf.append('"').append(ci.getName()).append('"');
/* 683 */       buf.append("[shape=").append(ci.isAnnotation() ? "oval" : (ci.isInterface() ? "diamond" : "box"))
/* 684 */         .append(",style=filled,fillcolor=\"#").append(ci.isAnnotation() ? "f3c9ff" : (
/* 685 */           ci.isInterface() ? "b6e7ff" : "fff2b6"))
/* 686 */         .append("\",label=");
/* 687 */       buf.append('<');
/* 688 */       buf.append("<table border='0' cellborder='0' cellspacing='1'>");
/*     */ 
/*     */       
/* 691 */       buf.append("<tr><td><font point-size='12'>").append(ci.getModifiersStr()).append(' ')
/* 692 */         .append(ci.isEnum() ? "enum" : (
/* 693 */           ci.isAnnotation() ? "@interface" : (ci.isInterface() ? "interface" : "class")))
/* 694 */         .append("</font></td></tr>");
/*     */       
/* 696 */       if (ci.getName().contains(".")) {
/* 697 */         buf.append("<tr><td><font point-size='14'><b>");
/* 698 */         htmlEncode(ci.getPackageName(), buf);
/* 699 */         buf.append("</b></font></td></tr>");
/*     */       } 
/*     */ 
/*     */       
/* 703 */       buf.append("<tr><td><font point-size='20'><b>");
/* 704 */       htmlEncode(ci.getSimpleName(), buf);
/* 705 */       buf.append("</b></font></td></tr>");
/* 706 */       buf.append("</table>");
/* 707 */       buf.append(">];\n");
/*     */     } 
/*     */     
/* 710 */     buf.append('\n');
/* 711 */     for (ClassInfo ci : classInfoList) {
/* 712 */       for (ClassInfo dep : ci.getClassDependencies()) {
/* 713 */         if (includeExternalClasses || allVisibleNodes.contains(dep))
/*     */         {
/* 715 */           buf.append("  \"").append(ci.getName()).append("\" -> \"").append(dep.getName())
/* 716 */             .append("\" [arrowsize=2.5]\n");
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 721 */     buf.append('}');
/* 722 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\GraphvizDotfileGenerator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */