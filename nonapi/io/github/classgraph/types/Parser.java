/*     */ package nonapi.io.github.classgraph.types;
/*     */ 
/*     */ import nonapi.io.github.classgraph.json.JSONUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Parser
/*     */ {
/*     */   private final String string;
/*     */   private int position;
/*  44 */   private final StringBuilder token = new StringBuilder();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Object state;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int SHOW_BEFORE = 80;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int SHOW_AFTER = 80;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Parser(String string) throws ParseException {
/*  64 */     if (string == null) {
/*  65 */       throw new ParseException(null, "Cannot parse null string");
/*     */     }
/*  67 */     this.string = string;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPositionInfo() {
/*  76 */     int showStart = Math.max(0, this.position - 80);
/*  77 */     int showEnd = Math.min(this.string.length(), this.position + 80);
/*  78 */     return "before: \"" + JSONUtils.escapeJSONString(this.string.substring(showStart, this.position)) + "\"; after: \"" + 
/*  79 */       JSONUtils.escapeJSONString(this.string.substring(this.position, showEnd)) + "\"; position: " + this.position + "; token: \"" + this.token + "\"";
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
/*     */   public Object setState(Object state) {
/*  91 */     Object oldState = this.state;
/*  92 */     this.state = state;
/*  93 */     return oldState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getState() {
/* 102 */     return this.state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char getc() throws ParseException {
/* 113 */     if (this.position >= this.string.length()) {
/* 114 */       throw new ParseException(this, "Ran out of input while parsing");
/*     */     }
/* 116 */     return this.string.charAt(this.position++);
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
/*     */   public void expect(char expectedChar) throws ParseException {
/* 128 */     int next = getc();
/* 129 */     if (next != expectedChar) {
/* 130 */       throw new ParseException(this, "Expected '" + expectedChar + "'; got '" + (char)next + "'");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public char peek() {
/* 140 */     return (this.position == this.string.length()) ? Character.MIN_VALUE : this.string.charAt(this.position);
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
/*     */   public void peekExpect(char expectedChar) throws ParseException {
/* 153 */     if (this.position == this.string.length()) {
/* 154 */       throw new ParseException(this, "Expected '" + expectedChar + "'; reached end of string");
/*     */     }
/* 156 */     char next = this.string.charAt(this.position);
/* 157 */     if (next != expectedChar) {
/* 158 */       throw new ParseException(this, "Expected '" + expectedChar + "'; got '" + next + "'");
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
/*     */   public boolean peekMatches(String strMatch) {
/* 170 */     return this.string.regionMatches(this.position, strMatch, 0, strMatch.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void next() {
/* 177 */     this.position++;
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
/*     */   public void advance(int numChars) {
/* 189 */     if (this.position + numChars >= this.string.length()) {
/* 190 */       throw new IllegalArgumentException("Invalid skip distance");
/*     */     }
/* 192 */     this.position += numChars;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasMore() {
/* 201 */     return (this.position < this.string.length());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosition() {
/* 210 */     return this.position;
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
/*     */   public void setPosition(int position) {
/* 222 */     if (position < 0 || position >= this.string.length()) {
/* 223 */       throw new IllegalArgumentException("Invalid position");
/*     */     }
/* 225 */     this.position = position;
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
/*     */   public CharSequence getSubsequence(int startPosition, int endPosition) {
/* 238 */     return this.string.subSequence(startPosition, endPosition);
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
/*     */   public String getSubstring(int startPosition, int endPosition) {
/* 251 */     return this.string.substring(startPosition, endPosition);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendToToken(String str) {
/* 261 */     this.token.append(str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendToToken(char c) {
/* 271 */     this.token.append(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void skipWhitespace() {
/* 278 */     while (this.position < this.string.length()) {
/* 279 */       char c = this.string.charAt(this.position);
/* 280 */       if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
/* 281 */         this.position++;
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
/*     */   public String currToken() {
/* 294 */     String tok = this.token.toString();
/* 295 */     this.token.setLength(0);
/* 296 */     return tok;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 304 */     return getPositionInfo();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\types\Parser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */