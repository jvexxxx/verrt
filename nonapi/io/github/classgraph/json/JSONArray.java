/*     */ package nonapi.io.github.classgraph.json;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JSONArray
/*     */ {
/*     */   List<Object> items;
/*     */   
/*     */   public JSONArray() {
/*  44 */     this.items = new ArrayList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JSONArray(List<Object> items) {
/*  54 */     this.items = items;
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
/*     */   void toJSONString(Map<ReferenceEqualityKey<JSONReference>, CharSequence> jsonReferenceToId, boolean includeNullValuedFields, int depth, int indentWidth, StringBuilder buf) {
/*  74 */     boolean prettyPrint = (indentWidth > 0);
/*  75 */     int n = this.items.size();
/*  76 */     if (n == 0) {
/*  77 */       buf.append("[]");
/*     */     } else {
/*  79 */       buf.append('[');
/*  80 */       if (prettyPrint) {
/*  81 */         buf.append('\n');
/*     */       }
/*  83 */       for (int i = 0; i < n; i++) {
/*  84 */         Object item = this.items.get(i);
/*  85 */         if (prettyPrint) {
/*  86 */           JSONUtils.indent(depth + 1, indentWidth, buf);
/*     */         }
/*  88 */         JSONSerializer.jsonValToJSONString(item, jsonReferenceToId, includeNullValuedFields, depth + 1, indentWidth, buf);
/*     */         
/*  90 */         if (i < n - 1) {
/*  91 */           buf.append(',');
/*     */         }
/*  93 */         if (prettyPrint) {
/*  94 */           buf.append('\n');
/*     */         }
/*     */       } 
/*  97 */       if (prettyPrint) {
/*  98 */         JSONUtils.indent(depth, indentWidth, buf);
/*     */       }
/* 100 */       buf.append(']');
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\JSONArray.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */