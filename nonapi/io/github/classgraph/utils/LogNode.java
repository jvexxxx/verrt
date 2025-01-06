/*     */ package nonapi.io.github.classgraph.utils;
/*     */ 
/*     */ import io.github.classgraph.ClassGraph;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collection;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentSkipListMap;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.logging.Logger;
/*     */ import nonapi.io.github.classgraph.classpath.SystemJarFinder;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class LogNode
/*     */ {
/*     */   static {
/*  55 */     System.getProperties().setProperty("log4j2.formatMsgNoLookups", "true");
/*     */   }
/*     */ 
/*     */   
/*  59 */   private static final Logger log = Logger.getLogger(ClassGraph.class.getName());
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   private final long timeStampNano = System.nanoTime();
/*     */ 
/*     */   
/*  67 */   private final long timeStampMillis = System.currentTimeMillis();
/*     */ 
/*     */   
/*     */   private final String msg;
/*     */ 
/*     */   
/*     */   private String stackTrace;
/*     */ 
/*     */   
/*     */   private long elapsedTimeNanos;
/*     */ 
/*     */   
/*     */   private LogNode parent;
/*     */ 
/*     */   
/*  82 */   private final Map<String, LogNode> children = new ConcurrentSkipListMap<>();
/*     */ 
/*     */   
/*     */   private final String sortKeyPrefix;
/*     */ 
/*     */   
/*  88 */   private static AtomicInteger sortKeyUniqueSuffix = new AtomicInteger(0);
/*     */ 
/*     */   
/*  91 */   private static final SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ", Locale.US);
/*     */ 
/*     */ 
/*     */   
/*  95 */   private static final DecimalFormat nanoFormatter = new DecimalFormat("0.000000");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean logInRealtime;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void logInRealtime(boolean logInRealtime) {
/* 109 */     LogNode.logInRealtime = logInRealtime;
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
/*     */   private LogNode(String sortKey, String msg, long elapsedTimeNanos, Throwable exception) {
/* 127 */     this.sortKeyPrefix = sortKey;
/* 128 */     this.msg = msg;
/* 129 */     this.elapsedTimeNanos = elapsedTimeNanos;
/* 130 */     if (exception != null) {
/* 131 */       StringWriter writer = new StringWriter();
/* 132 */       exception.printStackTrace(new PrintWriter(writer));
/* 133 */       this.stackTrace = writer.toString();
/*     */     } else {
/* 135 */       this.stackTrace = null;
/*     */     } 
/* 137 */     if (logInRealtime) {
/* 138 */       log.info(toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public LogNode() {
/* 144 */     this("", "", -1L, null);
/* 145 */     log("ClassGraph version " + VersionFinder.getVersion());
/* 146 */     logJavaInfo();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void logJavaInfo() {
/* 153 */     log("Operating system: " + VersionFinder.getProperty("os.name") + " " + 
/* 154 */         VersionFinder.getProperty("os.version") + " " + VersionFinder.getProperty("os.arch"));
/* 155 */     log("Java version: " + VersionFinder.getProperty("java.version") + " / " + 
/* 156 */         VersionFinder.getProperty("java.runtime.version") + " (" + 
/* 157 */         VersionFinder.getProperty("java.vendor") + ")");
/* 158 */     log("Java home: " + VersionFinder.getProperty("java.home"));
/* 159 */     String jreRtJarPath = SystemJarFinder.getJreRtJarPath();
/* 160 */     if (jreRtJarPath != null) {
/* 161 */       log("JRE rt.jar:").log(jreRtJarPath);
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
/*     */   private void appendLine(String timeStampStr, int indentLevel, String line, StringBuilder buf) {
/* 179 */     buf.append(timeStampStr);
/* 180 */     buf.append('\t');
/* 181 */     buf.append(ClassGraph.class.getSimpleName());
/* 182 */     buf.append('\t');
/* 183 */     int numDashes = 2 * (indentLevel - 1);
/* 184 */     for (int i = 0; i < numDashes; i++) {
/* 185 */       buf.append('-');
/*     */     }
/* 187 */     if (numDashes > 0) {
/* 188 */       buf.append(' ');
/*     */     }
/* 190 */     buf.append(line);
/* 191 */     buf.append('\n');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void toString(int indentLevel, StringBuilder buf) {
/*     */     String timeStampStr;
/* 203 */     Calendar cal = Calendar.getInstance();
/* 204 */     cal.setTimeInMillis(this.timeStampMillis);
/*     */     
/* 206 */     synchronized (dateTimeFormatter) {
/* 207 */       timeStampStr = dateTimeFormatter.format(cal.getTime());
/*     */     } 
/*     */     
/* 210 */     if (this.msg != null && !this.msg.isEmpty()) {
/* 211 */       appendLine(timeStampStr, indentLevel, 
/* 212 */           (this.elapsedTimeNanos > 0L) ? (
/* 213 */           this.msg + " (took " + nanoFormatter.format(this.elapsedTimeNanos * 1.0E-9D) + " sec)") : 
/* 214 */           this.msg, buf);
/*     */     }
/*     */     
/* 217 */     if (this.stackTrace != null && !this.stackTrace.isEmpty()) {
/* 218 */       String[] parts = this.stackTrace.split("\n");
/* 219 */       for (String part : parts) {
/* 220 */         appendLine(timeStampStr, indentLevel, part, buf);
/*     */       }
/*     */     } 
/*     */     
/* 224 */     for (Map.Entry<String, LogNode> ent : this.children.entrySet()) {
/* 225 */       LogNode child = ent.getValue();
/* 226 */       child.toString(indentLevel + 1, buf);
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
/*     */   public String toString() {
/* 238 */     synchronized (dateTimeFormatter) {
/* 239 */       StringBuilder buf = new StringBuilder();
/* 240 */       toString(0, buf);
/* 241 */       return buf.toString();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addElapsedTime() {
/* 250 */     this.elapsedTimeNanos = System.nanoTime() - this.timeStampNano;
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
/*     */   private LogNode addChild(String sortKey, String msg, long elapsedTimeNanos, Throwable exception) {
/* 269 */     String newSortKey = this.sortKeyPrefix + "\t" + ((sortKey == null) ? "" : sortKey) + "\t" + String.format("%09d", new Object[] { Integer.valueOf(sortKeyUniqueSuffix.getAndIncrement()) });
/* 270 */     LogNode newChild = new LogNode(newSortKey, msg, elapsedTimeNanos, exception);
/* 271 */     newChild.parent = this;
/*     */ 
/*     */     
/* 274 */     this.children.put(newSortKey, newChild);
/* 275 */     return newChild;
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
/*     */   private LogNode addChild(String sortKey, String msg, long elapsedTimeNanos) {
/* 290 */     return addChild(sortKey, msg, elapsedTimeNanos, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private LogNode addChild(Throwable exception) {
/* 301 */     return addChild("", "", -1L, exception);
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
/*     */   public LogNode log(String sortKey, String msg, long elapsedTimeNanos, Throwable e) {
/* 318 */     return addChild(sortKey, msg, elapsedTimeNanos).addChild(e);
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
/*     */   public LogNode log(String sortKey, String msg, long elapsedTimeNanos) {
/* 333 */     return addChild(sortKey, msg, elapsedTimeNanos);
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
/*     */   public LogNode log(String sortKey, String msg, Throwable e) {
/* 348 */     return addChild(sortKey, msg, -1L).addChild(e);
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
/*     */   public LogNode log(String sortKey, String msg) {
/* 361 */     return addChild(sortKey, msg, -1L);
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
/*     */   public LogNode log(String msg, long elapsedTimeNanos, Throwable e) {
/* 376 */     return addChild("", msg, elapsedTimeNanos).addChild(e);
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
/*     */   public LogNode log(String msg, long elapsedTimeNanos) {
/* 389 */     return addChild("", msg, elapsedTimeNanos);
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
/*     */   public LogNode log(String msg, Throwable e) {
/* 402 */     return addChild("", msg, -1L).addChild(e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LogNode log(String msg) {
/* 413 */     return addChild("", msg, -1L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LogNode log(Collection<String> msgs) {
/* 424 */     LogNode last = null;
/* 425 */     for (String m : msgs) {
/* 426 */       last = log(m);
/*     */     }
/* 428 */     return last;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LogNode log(Throwable e) {
/* 439 */     return log("Exception thrown").addChild(e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {
/* 448 */     if (this.parent != null) {
/* 449 */       throw new IllegalArgumentException("Only flush the toplevel LogNode");
/*     */     }
/* 451 */     if (!this.children.isEmpty()) {
/* 452 */       String logOutput = toString();
/* 453 */       this.children.clear();
/* 454 */       log.info(logOutput);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgrap\\utils\LogNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */