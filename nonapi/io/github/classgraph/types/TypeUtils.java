/*     */ package nonapi.io.github.classgraph.types;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TypeUtils
/*     */ {
/*     */   public static boolean getIdentifierToken(Parser parser, boolean stopAtDollarSign) {
/*  57 */     boolean consumedChar = false;
/*  58 */     while (parser.hasMore()) {
/*  59 */       char c = parser.peek();
/*  60 */       if (c == '/') {
/*  61 */         parser.appendToToken('.');
/*  62 */         parser.next();
/*  63 */         consumedChar = true; continue;
/*  64 */       }  if (c != ';' && c != '[' && c != '<' && c != '>' && c != ':' && (!stopAtDollarSign || c != '$')) {
/*     */         
/*  66 */         parser.appendToToken(c);
/*  67 */         parser.next();
/*  68 */         consumedChar = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  73 */     return consumedChar;
/*     */   }
/*     */ 
/*     */   
/*     */   public enum ModifierType
/*     */   {
/*  79 */     CLASS,
/*     */     
/*  81 */     METHOD,
/*     */     
/*  83 */     FIELD;
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
/*     */   private static void appendModifierKeyword(StringBuilder buf, String modifierKeyword) {
/*  96 */     if (buf.length() > 0 && buf.charAt(buf.length() - 1) != ' ') {
/*  97 */       buf.append(' ');
/*     */     }
/*  99 */     buf.append(modifierKeyword);
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
/*     */   public static void modifiersToString(int modifiers, ModifierType modifierType, boolean isDefault, StringBuilder buf) {
/* 116 */     if ((modifiers & 0x1) != 0) {
/* 117 */       appendModifierKeyword(buf, "public");
/* 118 */     } else if ((modifiers & 0x2) != 0) {
/* 119 */       appendModifierKeyword(buf, "private");
/* 120 */     } else if ((modifiers & 0x4) != 0) {
/* 121 */       appendModifierKeyword(buf, "protected");
/*     */     } 
/* 123 */     if (modifierType != ModifierType.FIELD && (modifiers & 0x400) != 0) {
/* 124 */       appendModifierKeyword(buf, "abstract");
/*     */     }
/* 126 */     if ((modifiers & 0x8) != 0) {
/* 127 */       appendModifierKeyword(buf, "static");
/*     */     }
/* 129 */     if (modifierType == ModifierType.FIELD) {
/* 130 */       if ((modifiers & 0x40) != 0)
/*     */       {
/* 132 */         appendModifierKeyword(buf, "volatile");
/*     */       }
/* 134 */       if ((modifiers & 0x80) != 0) {
/* 135 */         appendModifierKeyword(buf, "transient");
/*     */       }
/*     */     } 
/* 138 */     if ((modifiers & 0x10) != 0) {
/* 139 */       appendModifierKeyword(buf, "final");
/*     */     }
/* 141 */     if (modifierType == ModifierType.METHOD) {
/* 142 */       if ((modifiers & 0x20) != 0) {
/* 143 */         appendModifierKeyword(buf, "synchronized");
/*     */       }
/* 145 */       if (isDefault) {
/* 146 */         appendModifierKeyword(buf, "default");
/*     */       }
/*     */     } 
/* 149 */     if ((modifiers & 0x1000) != 0) {
/* 150 */       appendModifierKeyword(buf, "synthetic");
/*     */     }
/* 152 */     if (modifierType != ModifierType.FIELD && (modifiers & 0x40) != 0)
/*     */     {
/* 154 */       appendModifierKeyword(buf, "bridge");
/*     */     }
/* 156 */     if (modifierType == ModifierType.METHOD && (modifiers & 0x100) != 0) {
/* 157 */       appendModifierKeyword(buf, "native");
/*     */     }
/* 159 */     if (modifierType != ModifierType.FIELD && (modifiers & 0x800) != 0)
/* 160 */       appendModifierKeyword(buf, "strictfp"); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\types\TypeUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */