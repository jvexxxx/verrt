/*     */ package nonapi.io.github.classgraph.json;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReferenceEqualityKey<K>
/*     */ {
/*     */   private final K wrappedKey;
/*     */   
/*     */   public ReferenceEqualityKey(K wrappedKey) {
/*  50 */     this.wrappedKey = wrappedKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public K get() {
/*  59 */     return this.wrappedKey;
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
/*     */   public int hashCode() {
/*  72 */     K key = this.wrappedKey;
/*     */ 
/*     */     
/*  75 */     return (key == null) ? 0 : System.identityHashCode(key);
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
/*     */   public boolean equals(Object obj) {
/*  90 */     if (obj == this)
/*  91 */       return true; 
/*  92 */     if (!(obj instanceof ReferenceEqualityKey)) {
/*  93 */       return false;
/*     */     }
/*  95 */     return (this.wrappedKey == ((ReferenceEqualityKey)obj).wrappedKey);
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
/*     */   public String toString() {
/* 108 */     K key = this.wrappedKey;
/* 109 */     return (key == null) ? "null" : key.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\ReferenceEqualityKey.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */