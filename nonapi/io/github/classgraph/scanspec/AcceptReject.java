/*     */ package nonapi.io.github.classgraph.scanspec;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.regex.Pattern;
/*     */ import nonapi.io.github.classgraph.utils.CollectionUtils;
/*     */ import nonapi.io.github.classgraph.utils.FastPathResolver;
/*     */ import nonapi.io.github.classgraph.utils.FileUtils;
/*     */ import nonapi.io.github.classgraph.utils.JarUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AcceptReject
/*     */ {
/*     */   protected Set<String> accept;
/*     */   protected Set<String> reject;
/*     */   protected Set<String> acceptPrefixesSet;
/*     */   protected List<String> acceptPrefixes;
/*     */   protected List<String> rejectPrefixes;
/*     */   protected Set<String> acceptGlobs;
/*     */   protected Set<String> rejectGlobs;
/*     */   protected transient List<Pattern> acceptPatterns;
/*     */   protected transient List<Pattern> rejectPatterns;
/*     */   protected char separatorChar;
/*     */   
/*     */   public AcceptReject() {}
/*     */   
/*     */   public AcceptReject(char separatorChar) {
/*  77 */     this.separatorChar = separatorChar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class AcceptRejectPrefix
/*     */     extends AcceptReject
/*     */   {
/*     */     public AcceptRejectPrefix() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AcceptRejectPrefix(char separatorChar) {
/*  94 */       super(separatorChar);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addToAccept(String str) {
/* 105 */       if (str.contains("*")) {
/* 106 */         throw new IllegalArgumentException("Cannot use a glob wildcard here: " + str);
/*     */       }
/* 108 */       if (this.acceptPrefixesSet == null) {
/* 109 */         this.acceptPrefixesSet = new HashSet<>();
/*     */       }
/* 111 */       this.acceptPrefixesSet.add(str);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addToReject(String str) {
/* 122 */       if (str.contains("*")) {
/* 123 */         throw new IllegalArgumentException("Cannot use a glob wildcard here: " + str);
/*     */       }
/* 125 */       if (this.rejectPrefixes == null) {
/* 126 */         this.rejectPrefixes = new ArrayList<>();
/*     */       }
/* 128 */       this.rejectPrefixes.add(str);
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
/*     */     public boolean isAcceptedAndNotRejected(String str) {
/* 140 */       boolean isAccepted = (this.acceptPrefixes == null);
/* 141 */       if (!isAccepted) {
/* 142 */         for (String prefix : this.acceptPrefixes) {
/* 143 */           if (str.startsWith(prefix)) {
/* 144 */             isAccepted = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/* 149 */       if (!isAccepted) {
/* 150 */         return false;
/*     */       }
/* 152 */       if (this.rejectPrefixes != null) {
/* 153 */         for (String prefix : this.rejectPrefixes) {
/* 154 */           if (str.startsWith(prefix)) {
/* 155 */             return false;
/*     */           }
/*     */         } 
/*     */       }
/* 159 */       return true;
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
/*     */     public boolean isAccepted(String str) {
/* 171 */       boolean isAccepted = (this.acceptPrefixes == null);
/* 172 */       if (!isAccepted) {
/* 173 */         for (String prefix : this.acceptPrefixes) {
/* 174 */           if (str.startsWith(prefix)) {
/* 175 */             isAccepted = true;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/* 180 */       return isAccepted;
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
/*     */     public boolean acceptHasPrefix(String str) {
/* 194 */       throw new IllegalArgumentException("Can only find prefixes of whole strings");
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
/*     */     public boolean isRejected(String str) {
/* 206 */       if (this.rejectPrefixes != null) {
/* 207 */         for (String prefix : this.rejectPrefixes) {
/* 208 */           if (str.startsWith(prefix)) {
/* 209 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/* 213 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class AcceptRejectWholeString
/*     */     extends AcceptReject
/*     */   {
/*     */     public AcceptRejectWholeString() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AcceptRejectWholeString(char separatorChar) {
/* 231 */       super(separatorChar);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addToAccept(String str) {
/* 242 */       if (str.contains("*")) {
/* 243 */         if (this.acceptGlobs == null) {
/* 244 */           this.acceptGlobs = new HashSet<>();
/* 245 */           this.acceptPatterns = new ArrayList<>();
/*     */         } 
/* 247 */         this.acceptGlobs.add(str);
/* 248 */         this.acceptPatterns.add(globToPattern(str, true));
/*     */       } else {
/* 250 */         if (this.accept == null) {
/* 251 */           this.accept = new HashSet<>();
/*     */         }
/* 253 */         this.accept.add(str);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 260 */       if (this.acceptPrefixesSet == null) {
/* 261 */         this.acceptPrefixesSet = new HashSet<>();
/* 262 */         this.acceptPrefixesSet.add("");
/* 263 */         this.acceptPrefixesSet.add("/");
/*     */       } 
/* 265 */       String separator = Character.toString(this.separatorChar);
/* 266 */       String prefix = str;
/* 267 */       if (prefix.contains("*")) {
/*     */ 
/*     */         
/* 270 */         prefix = prefix.substring(0, prefix.indexOf('*'));
/*     */ 
/*     */         
/* 273 */         int sepIdx = prefix.lastIndexOf(this.separatorChar);
/* 274 */         if (sepIdx < 0) {
/* 275 */           prefix = "";
/*     */         } else {
/* 277 */           prefix = prefix.substring(0, prefix.lastIndexOf(this.separatorChar));
/*     */         } 
/*     */       } 
/*     */       
/* 281 */       while (prefix.endsWith(separator)) {
/* 282 */         prefix = prefix.substring(0, prefix.length() - 1);
/*     */       }
/*     */       
/* 285 */       for (; !prefix.isEmpty(); prefix = FileUtils.getParentDirPath(prefix, this.separatorChar)) {
/* 286 */         this.acceptPrefixesSet.add(prefix + this.separatorChar);
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
/*     */     public void addToReject(String str) {
/* 298 */       if (str.contains("*")) {
/* 299 */         if (this.rejectGlobs == null) {
/* 300 */           this.rejectGlobs = new HashSet<>();
/* 301 */           this.rejectPatterns = new ArrayList<>();
/*     */         } 
/* 303 */         this.rejectGlobs.add(str);
/* 304 */         this.rejectPatterns.add(globToPattern(str, true));
/*     */       } else {
/* 306 */         if (this.reject == null) {
/* 307 */           this.reject = new HashSet<>();
/*     */         }
/* 309 */         this.reject.add(str);
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
/*     */     public boolean isAcceptedAndNotRejected(String str) {
/* 322 */       return (isAccepted(str) && !isRejected(str));
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
/*     */     public boolean isAccepted(String str) {
/* 334 */       return ((this.accept == null && this.acceptPatterns == null) || (this.accept != null && this.accept.contains(str)) || AcceptReject
/* 335 */         .matchesPatternList(str, this.acceptPatterns));
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
/*     */     public boolean acceptHasPrefix(String str) {
/* 347 */       if (this.acceptPrefixesSet == null) {
/* 348 */         return false;
/*     */       }
/* 350 */       return this.acceptPrefixesSet.contains(str);
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
/*     */     public boolean isRejected(String str) {
/* 362 */       return ((this.reject != null && this.reject.contains(str)) || AcceptReject.matchesPatternList(str, this.rejectPatterns));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class AcceptRejectLeafname
/*     */     extends AcceptRejectWholeString
/*     */   {
/*     */     public AcceptRejectLeafname() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AcceptRejectLeafname(char separatorChar) {
/* 380 */       super(separatorChar);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addToAccept(String str) {
/* 391 */       super.addToAccept(JarUtils.leafName(str));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addToReject(String str) {
/* 402 */       super.addToReject(JarUtils.leafName(str));
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
/*     */     public boolean isAcceptedAndNotRejected(String str) {
/* 414 */       return super.isAcceptedAndNotRejected(JarUtils.leafName(str));
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
/*     */     public boolean isAccepted(String str) {
/* 426 */       return super.isAccepted(JarUtils.leafName(str));
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
/*     */     public boolean acceptHasPrefix(String str) {
/* 440 */       throw new IllegalArgumentException("Can only find prefixes of whole strings");
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
/*     */     public boolean isRejected(String str) {
/* 452 */       return super.isRejected(JarUtils.leafName(str));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String normalizePath(String path) {
/* 516 */     String pathResolved = FastPathResolver.resolve(path);
/* 517 */     while (pathResolved.startsWith("/")) {
/* 518 */       pathResolved = pathResolved.substring(1);
/*     */     }
/* 520 */     return pathResolved;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String normalizePackageOrClassName(String packageOrClassName) {
/* 531 */     return normalizePath(packageOrClassName.replace('.', '/')).replace('/', '.');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String pathToPackageName(String path) {
/* 542 */     return path.replace('/', '.');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String packageNameToPath(String packageName) {
/* 553 */     return packageName.replace('.', '/');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String classNameToClassfilePath(String className) {
/* 564 */     return JarUtils.classNameToClassfilePath(className);
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
/*     */   public static Pattern globToPattern(String glob, boolean simpleGlob) {
/* 585 */     return Pattern.compile("^" + (
/* 586 */         simpleGlob ? 
/*     */         
/* 588 */         glob.replace(".", "\\.").replace("*", ".*") : 
/*     */ 
/*     */ 
/*     */         
/* 592 */         glob.replace(".", "\\.").replace("*", "[^/]*").replace("[^/]*[^/]*", ".*").replace('?', '.')) + "$");
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
/*     */   private static boolean matchesPatternList(String str, List<Pattern> patterns) {
/* 607 */     if (patterns != null) {
/* 608 */       for (Pattern pattern : patterns) {
/* 609 */         if (pattern.matcher(str).matches()) {
/* 610 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 614 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean acceptIsEmpty() {
/* 623 */     return (this.accept == null && this.acceptPrefixes == null && this.acceptGlobs == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean rejectIsEmpty() {
/* 632 */     return (this.reject == null && this.rejectPrefixes == null && this.rejectGlobs == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean acceptAndRejectAreEmpty() {
/* 641 */     return (acceptIsEmpty() && rejectIsEmpty());
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
/*     */   public boolean isSpecificallyAcceptedAndNotRejected(String str) {
/* 653 */     return (!acceptIsEmpty() && isAcceptedAndNotRejected(str));
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
/*     */   public boolean isSpecificallyAccepted(String str) {
/* 665 */     return (!acceptIsEmpty() && isAccepted(str));
/*     */   }
/*     */ 
/*     */   
/*     */   void sortPrefixes() {
/* 670 */     if (this.acceptPrefixesSet != null) {
/* 671 */       this.acceptPrefixes = new ArrayList<>(this.acceptPrefixesSet);
/*     */     }
/* 673 */     if (this.acceptPrefixes != null) {
/* 674 */       CollectionUtils.sortIfNotEmpty(this.acceptPrefixes);
/*     */     }
/* 676 */     if (this.rejectPrefixes != null) {
/* 677 */       CollectionUtils.sortIfNotEmpty(this.rejectPrefixes);
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
/*     */   private static void quoteList(Collection<String> coll, StringBuilder buf) {
/* 690 */     buf.append('[');
/* 691 */     boolean first = true;
/* 692 */     for (String item : coll) {
/* 693 */       if (first) {
/* 694 */         first = false;
/*     */       } else {
/* 696 */         buf.append(", ");
/*     */       } 
/* 698 */       buf.append('"');
/* 699 */       for (int i = 0; i < item.length(); i++) {
/* 700 */         char c = item.charAt(i);
/* 701 */         if (c == '"') {
/* 702 */           buf.append("\\\"");
/*     */         } else {
/* 704 */           buf.append(c);
/*     */         } 
/*     */       } 
/* 707 */       buf.append('"');
/*     */     } 
/* 709 */     buf.append(']');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 717 */     StringBuilder buf = new StringBuilder();
/* 718 */     if (this.accept != null) {
/* 719 */       buf.append("accept: ");
/* 720 */       quoteList(this.accept, buf);
/*     */     } 
/* 722 */     if (this.acceptPrefixes != null) {
/* 723 */       if (buf.length() > 0) {
/* 724 */         buf.append("; ");
/*     */       }
/* 726 */       buf.append("acceptPrefixes: ");
/* 727 */       quoteList(this.acceptPrefixes, buf);
/*     */     } 
/* 729 */     if (this.acceptGlobs != null) {
/* 730 */       if (buf.length() > 0) {
/* 731 */         buf.append("; ");
/*     */       }
/* 733 */       buf.append("acceptGlobs: ");
/* 734 */       quoteList(this.acceptGlobs, buf);
/*     */     } 
/* 736 */     if (this.reject != null) {
/* 737 */       if (buf.length() > 0) {
/* 738 */         buf.append("; ");
/*     */       }
/* 740 */       buf.append("reject: ");
/* 741 */       quoteList(this.reject, buf);
/*     */     } 
/* 743 */     if (this.rejectPrefixes != null) {
/* 744 */       if (buf.length() > 0) {
/* 745 */         buf.append("; ");
/*     */       }
/* 747 */       buf.append("rejectPrefixes: ");
/* 748 */       quoteList(this.rejectPrefixes, buf);
/*     */     } 
/* 750 */     if (this.rejectGlobs != null) {
/* 751 */       if (buf.length() > 0) {
/* 752 */         buf.append("; ");
/*     */       }
/* 754 */       buf.append("rejectGlobs: ");
/* 755 */       quoteList(this.rejectGlobs, buf);
/*     */     } 
/* 757 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public abstract void addToAccept(String paramString);
/*     */   
/*     */   public abstract void addToReject(String paramString);
/*     */   
/*     */   public abstract boolean isAcceptedAndNotRejected(String paramString);
/*     */   
/*     */   public abstract boolean isAccepted(String paramString);
/*     */   
/*     */   public abstract boolean acceptHasPrefix(String paramString);
/*     */   
/*     */   public abstract boolean isRejected(String paramString);
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\scanspec\AcceptReject.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */