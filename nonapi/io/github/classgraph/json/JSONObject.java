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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JSONObject
/*     */ {
/*     */   List<Map.Entry<String, Object>> items;
/*     */   CharSequence objectId;
/*     */   
/*     */   public JSONObject(int sizeHint) {
/*  51 */     this.items = new ArrayList<>(sizeHint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JSONObject(List<Map.Entry<String, Object>> items) {
/*  61 */     this.items = items;
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
/*     */   void toJSONString(Map<ReferenceEqualityKey<JSONReference>, CharSequence> jsonReferenceToId, boolean includeNullValuedFields, int depth, int indentWidth, StringBuilder buf) {
/*     */     int numDisplayedFields;
/*  81 */     boolean prettyPrint = (indentWidth > 0);
/*  82 */     int n = this.items.size();
/*     */     
/*  84 */     if (includeNullValuedFields) {
/*  85 */       numDisplayedFields = n;
/*     */     } else {
/*  87 */       numDisplayedFields = 0;
/*  88 */       for (Map.Entry<String, Object> item : this.items) {
/*  89 */         if (item.getValue() != null) {
/*  90 */           numDisplayedFields++;
/*     */         }
/*     */       } 
/*     */     } 
/*  94 */     if (this.objectId == null && numDisplayedFields == 0) {
/*  95 */       buf.append("{}");
/*     */     } else {
/*  97 */       buf.append(prettyPrint ? "{\n" : "{");
/*  98 */       if (this.objectId != null) {
/*     */ 
/*     */         
/* 101 */         if (prettyPrint) {
/* 102 */           JSONUtils.indent(depth + 1, indentWidth, buf);
/*     */         }
/* 104 */         buf.append('"');
/* 105 */         buf.append("__ID");
/* 106 */         buf.append(prettyPrint ? "\": " : "\":");
/* 107 */         JSONSerializer.jsonValToJSONString(this.objectId, jsonReferenceToId, includeNullValuedFields, depth + 1, indentWidth, buf);
/*     */         
/* 109 */         if (numDisplayedFields > 0) {
/* 110 */           buf.append(',');
/*     */         }
/* 112 */         if (prettyPrint) {
/* 113 */           buf.append('\n');
/*     */         }
/*     */       } 
/* 116 */       for (int i = 0, j = 0; i < n; i++) {
/* 117 */         Map.Entry<String, Object> item = this.items.get(i);
/* 118 */         Object val = item.getValue();
/* 119 */         if (val != null || includeNullValuedFields) {
/* 120 */           String key = item.getKey();
/* 121 */           if (key == null)
/*     */           {
/*     */             
/* 124 */             throw new IllegalArgumentException("Cannot serialize JSON object with null key");
/*     */           }
/* 126 */           if (prettyPrint) {
/* 127 */             JSONUtils.indent(depth + 1, indentWidth, buf);
/*     */           }
/* 129 */           buf.append('"');
/* 130 */           JSONUtils.escapeJSONString(key, buf);
/* 131 */           buf.append(prettyPrint ? "\": " : "\":");
/* 132 */           JSONSerializer.jsonValToJSONString(val, jsonReferenceToId, includeNullValuedFields, depth + 1, indentWidth, buf);
/*     */           
/* 134 */           if (++j < numDisplayedFields) {
/* 135 */             buf.append(',');
/*     */           }
/* 137 */           if (prettyPrint) {
/* 138 */             buf.append('\n');
/*     */           }
/*     */         } 
/*     */       } 
/* 142 */       if (prettyPrint) {
/* 143 */         JSONUtils.indent(depth, indentWidth, buf);
/*     */       }
/* 145 */       buf.append('}');
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\JSONObject.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */