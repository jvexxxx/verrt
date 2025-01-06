/*     */ package nonapi.io.github.classgraph.json;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class JSONSerializer
/*     */ {
/*     */   private static void assignObjectIds(Object jsonVal, Map<ReferenceEqualityKey<Object>, JSONObject> objToJSONVal, ClassFieldCache classFieldCache, Map<ReferenceEqualityKey<JSONReference>, CharSequence> jsonReferenceToId, AtomicInteger objId, boolean onlySerializePublicFields) {
/*  82 */     if (jsonVal instanceof JSONObject) {
/*  83 */       for (Map.Entry<String, Object> item : ((JSONObject)jsonVal).items) {
/*  84 */         assignObjectIds(item.getValue(), objToJSONVal, classFieldCache, jsonReferenceToId, objId, onlySerializePublicFields);
/*     */       }
/*     */     }
/*  87 */     else if (jsonVal instanceof JSONArray) {
/*  88 */       for (Object item : ((JSONArray)jsonVal).items) {
/*  89 */         assignObjectIds(item, objToJSONVal, classFieldCache, jsonReferenceToId, objId, onlySerializePublicFields);
/*     */       }
/*     */     }
/*  92 */     else if (jsonVal instanceof JSONReference) {
/*     */       
/*  94 */       Object refdObj = ((JSONReference)jsonVal).idObject;
/*  95 */       if (refdObj == null)
/*     */       {
/*  97 */         throw new RuntimeException("Internal inconsistency");
/*     */       }
/*     */       
/* 100 */       ReferenceEqualityKey<Object> refdObjKey = new ReferenceEqualityKey(refdObj);
/* 101 */       JSONObject refdJsonVal = objToJSONVal.get(refdObjKey);
/* 102 */       if (refdJsonVal == null)
/*     */       {
/* 104 */         throw new RuntimeException("Internal inconsistency");
/*     */       }
/*     */ 
/*     */       
/* 108 */       Field annotatedField = (classFieldCache.get(refdObj.getClass())).idField;
/* 109 */       CharSequence idStr = null;
/* 110 */       if (annotatedField != null) {
/*     */         
/*     */         try {
/* 113 */           Object idObject = annotatedField.get(refdObj);
/* 114 */           if (idObject != null) {
/* 115 */             idStr = idObject.toString();
/* 116 */             refdJsonVal.objectId = idStr;
/*     */           } 
/* 118 */         } catch (IllegalArgumentException|IllegalAccessException e) {
/*     */           
/* 120 */           throw new IllegalArgumentException("Could not access @Id-annotated field " + annotatedField, e);
/*     */         } 
/*     */       }
/* 123 */       if (idStr == null)
/*     */       {
/* 125 */         if (refdJsonVal.objectId == null) {
/*     */           
/* 127 */           idStr = "[#" + objId.getAndIncrement() + "]";
/* 128 */           refdJsonVal.objectId = idStr;
/*     */         } else {
/* 130 */           idStr = refdJsonVal.objectId;
/*     */         } 
/*     */       }
/*     */       
/* 134 */       jsonReferenceToId.put(new ReferenceEqualityKey<>((JSONReference)jsonVal), idStr);
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
/*     */   private static void convertVals(Object[] convertedVals, Set<ReferenceEqualityKey<Object>> visitedOnPath, Set<ReferenceEqualityKey<Object>> standardObjectVisited, ClassFieldCache classFieldCache, Map<ReferenceEqualityKey<Object>, JSONObject> objToJSONVal, boolean onlySerializePublicFields) {
/* 167 */     ReferenceEqualityKey[] arrayOfReferenceEqualityKey = new ReferenceEqualityKey[convertedVals.length];
/* 168 */     boolean[] needToConvert = new boolean[convertedVals.length]; int i;
/* 169 */     for (i = 0; i < convertedVals.length; i++) {
/* 170 */       Object val = convertedVals[i];
/*     */       
/* 172 */       needToConvert[i] = !JSONUtils.isBasicValueType(val);
/* 173 */       if (needToConvert[i] && !JSONUtils.isCollectionOrArray(val)) {
/*     */ 
/*     */ 
/*     */         
/* 177 */         ReferenceEqualityKey<Object> valKey = new ReferenceEqualityKey(val);
/* 178 */         arrayOfReferenceEqualityKey[i] = valKey;
/* 179 */         boolean alreadyVisited = !standardObjectVisited.add(valKey);
/* 180 */         if (alreadyVisited) {
/* 181 */           convertedVals[i] = new JSONReference(val);
/* 182 */           needToConvert[i] = false;
/*     */         } 
/*     */       } 
/*     */       
/* 186 */       if (val instanceof Class) {
/* 187 */         convertedVals[i] = ((Class)val).getName();
/*     */       }
/*     */     } 
/*     */     
/* 191 */     for (i = 0; i < convertedVals.length; i++) {
/* 192 */       if (needToConvert[i]) {
/*     */ 
/*     */         
/* 195 */         Object val = convertedVals[i];
/* 196 */         convertedVals[i] = toJSONGraph(val, visitedOnPath, standardObjectVisited, classFieldCache, objToJSONVal, onlySerializePublicFields);
/*     */         
/* 198 */         if (!JSONUtils.isCollectionOrArray(val)) {
/*     */ 
/*     */ 
/*     */           
/* 202 */           ReferenceEqualityKey<Object> valKey = arrayOfReferenceEqualityKey[i];
/* 203 */           objToJSONVal.put(valKey, (JSONObject)convertedVals[i]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 213 */   private static final Comparator<Object> SET_COMPARATOR = new Comparator()
/*     */     {
/*     */       public int compare(Object o1, Object o2) {
/* 216 */         if (o1 == null || o2 == null) {
/* 217 */           return ((o1 == null) ? 0 : 1) - ((o2 == null) ? 0 : 1);
/*     */         }
/* 219 */         if (Comparable.class.isAssignableFrom(o1.getClass()) && Comparable.class
/* 220 */           .isAssignableFrom(o2.getClass())) {
/*     */           
/* 222 */           Comparable<Object> comparableO1 = (Comparable<Object>)o1;
/* 223 */           return comparableO1.compareTo(o2);
/*     */         } 
/*     */ 
/*     */         
/* 227 */         return o1.toString().compareTo(o2.toString());
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object toJSONGraph(Object obj, Set<ReferenceEqualityKey<Object>> visitedOnPath, Set<ReferenceEqualityKey<Object>> standardObjectVisited, ClassFieldCache classFieldCache, Map<ReferenceEqualityKey<Object>, JSONObject> objToJSONVal, boolean onlySerializePublicFields) {
/*     */     Object jsonVal;
/* 255 */     if (obj instanceof Class) {
/* 256 */       return ((Class)obj).getName();
/*     */     }
/*     */ 
/*     */     
/* 260 */     if (JSONUtils.isBasicValueType(obj)) {
/* 261 */       return obj;
/*     */     }
/*     */ 
/*     */     
/* 265 */     ReferenceEqualityKey<Object> objKey = new ReferenceEqualityKey(obj);
/* 266 */     if (!visitedOnPath.add(objKey)) {
/*     */       
/* 268 */       if (JSONUtils.isCollectionOrArray(obj))
/*     */       {
/*     */ 
/*     */         
/* 272 */         throw new IllegalArgumentException("Cycles involving collections cannot be serialized, since collections are not assigned object ids. Reached cycle at: " + obj);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 277 */       return new JSONReference(obj);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 282 */     Class<?> cls = obj.getClass();
/* 283 */     boolean isArray = cls.isArray();
/*     */     
/* 285 */     if (Map.class.isAssignableFrom(cls)) {
/* 286 */       Map<Object, Object> map = (Map<Object, Object>)obj;
/*     */ 
/*     */ 
/*     */       
/* 290 */       ArrayList<?> keys = new ArrayList(map.keySet());
/* 291 */       int n = keys.size();
/* 292 */       boolean keysComparable = false;
/* 293 */       Object firstNonNullKey = null;
/* 294 */       for (int i = 0; i < n && firstNonNullKey == null; i++) {
/* 295 */         firstNonNullKey = keys.get(i);
/*     */       }
/* 297 */       if (firstNonNullKey != null && Comparable.class.isAssignableFrom(firstNonNullKey.getClass())) {
/* 298 */         CollectionUtils.sortIfNotEmpty(keys);
/* 299 */         keysComparable = true;
/*     */       } 
/*     */ 
/*     */       
/* 303 */       String[] convertedKeys = new String[n];
/* 304 */       for (int j = 0; j < n; j++) {
/* 305 */         Object key = keys.get(j);
/* 306 */         if (key != null && !JSONUtils.isBasicValueType(key)) {
/* 307 */           throw new IllegalArgumentException("Map key of type " + key.getClass().getName() + " is not a basic type (String, Integer, etc.), so can't be easily serialized as a JSON associative array key");
/*     */         }
/*     */ 
/*     */         
/* 311 */         convertedKeys[j] = JSONUtils.escapeJSONString((key == null) ? "null" : key.toString());
/*     */       } 
/*     */ 
/*     */       
/* 315 */       if (!keysComparable) {
/* 316 */         Arrays.sort((Object[])convertedKeys);
/*     */       }
/*     */ 
/*     */       
/* 320 */       Object[] convertedVals = new Object[n];
/* 321 */       for (int k = 0; k < n; k++) {
/* 322 */         convertedVals[k] = map.get(keys.get(k));
/*     */       }
/* 324 */       convertVals(convertedVals, visitedOnPath, standardObjectVisited, classFieldCache, objToJSONVal, onlySerializePublicFields);
/*     */ 
/*     */ 
/*     */       
/* 328 */       List<Map.Entry<String, Object>> convertedKeyValPairs = new ArrayList<>(n);
/* 329 */       for (int m = 0; m < n; m++) {
/* 330 */         convertedKeyValPairs.add(new AbstractMap.SimpleEntry<>(convertedKeys[m], convertedVals[m]));
/*     */       }
/* 332 */       jsonVal = new JSONObject(convertedKeyValPairs);
/*     */     }
/* 334 */     else if (isArray || List.class.isAssignableFrom(cls)) {
/*     */       
/* 336 */       boolean isList = List.class.isAssignableFrom(cls);
/* 337 */       List<?> list = isList ? (List)obj : null;
/* 338 */       int n = (list != null) ? list.size() : (isArray ? Array.getLength(obj) : 0);
/*     */ 
/*     */       
/* 341 */       Object[] convertedVals = new Object[n];
/* 342 */       for (int i = 0; i < n; i++) {
/* 343 */         convertedVals[i] = (list != null) ? list.get(i) : (isArray ? Array.get(obj, i) : Integer.valueOf(0));
/*     */       }
/* 345 */       convertVals(convertedVals, visitedOnPath, standardObjectVisited, classFieldCache, objToJSONVal, onlySerializePublicFields);
/*     */ 
/*     */ 
/*     */       
/* 349 */       jsonVal = new JSONArray(Arrays.asList(convertedVals));
/*     */     }
/* 351 */     else if (Collection.class.isAssignableFrom(cls)) {
/* 352 */       Collection<?> collection = (Collection)obj;
/*     */ 
/*     */       
/* 355 */       List<Object> convertedValsList = new ArrayList(collection);
/* 356 */       if (Set.class.isAssignableFrom(cls)) {
/* 357 */         CollectionUtils.sortIfNotEmpty(convertedValsList, SET_COMPARATOR);
/*     */       }
/*     */ 
/*     */       
/* 361 */       Object[] convertedVals = convertedValsList.toArray();
/* 362 */       convertVals(convertedVals, visitedOnPath, standardObjectVisited, classFieldCache, objToJSONVal, onlySerializePublicFields);
/*     */ 
/*     */ 
/*     */       
/* 366 */       jsonVal = new JSONArray(Arrays.asList(convertedVals));
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 372 */       ClassFields resolvedFields = classFieldCache.get(cls);
/* 373 */       List<FieldTypeInfo> fieldOrder = resolvedFields.fieldOrder;
/* 374 */       int n = fieldOrder.size();
/*     */ 
/*     */       
/* 377 */       String[] fieldNames = new String[n];
/* 378 */       Object[] convertedVals = new Object[n];
/* 379 */       for (int i = 0; i < n; i++) {
/* 380 */         FieldTypeInfo fieldTypeInfo = fieldOrder.get(i);
/* 381 */         Field field = fieldTypeInfo.field;
/* 382 */         fieldNames[i] = field.getName();
/*     */         try {
/* 384 */           convertedVals[i] = JSONUtils.getFieldValue(obj, field);
/* 385 */         } catch (IllegalArgumentException|IllegalAccessException e) {
/* 386 */           throw new RuntimeException("Could not get value of field \"" + fieldNames[i] + "\" in object of class " + obj
/* 387 */               .getClass().getName(), e);
/*     */         } 
/*     */       } 
/* 390 */       convertVals(convertedVals, visitedOnPath, standardObjectVisited, classFieldCache, objToJSONVal, onlySerializePublicFields);
/*     */ 
/*     */ 
/*     */       
/* 394 */       List<Map.Entry<String, Object>> convertedKeyValPairs = new ArrayList<>(n);
/* 395 */       for (int j = 0; j < n; j++) {
/* 396 */         convertedKeyValPairs.add(new AbstractMap.SimpleEntry<>(fieldNames[j], convertedVals[j]));
/*     */       }
/* 398 */       jsonVal = new JSONObject(convertedKeyValPairs);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 404 */     visitedOnPath.remove(objKey);
/*     */     
/* 406 */     return jsonVal;
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
/*     */   static void jsonValToJSONString(Object jsonVal, Map<ReferenceEqualityKey<JSONReference>, CharSequence> jsonReferenceToId, boolean includeNullValuedFields, int depth, int indentWidth, StringBuilder buf) {
/* 432 */     if (jsonVal == null) {
/* 433 */       buf.append("null");
/*     */     }
/* 435 */     else if (jsonVal instanceof JSONObject) {
/*     */       
/* 437 */       ((JSONObject)jsonVal).toJSONString(jsonReferenceToId, includeNullValuedFields, depth, indentWidth, buf);
/*     */     
/*     */     }
/* 440 */     else if (jsonVal instanceof JSONArray) {
/*     */       
/* 442 */       ((JSONArray)jsonVal).toJSONString(jsonReferenceToId, includeNullValuedFields, depth, indentWidth, buf);
/*     */     }
/* 444 */     else if (jsonVal instanceof JSONReference) {
/*     */ 
/*     */       
/* 447 */       Object referencedObjectId = jsonReferenceToId.get(new ReferenceEqualityKey<>((JSONReference)jsonVal));
/* 448 */       jsonValToJSONString(referencedObjectId, jsonReferenceToId, includeNullValuedFields, depth, indentWidth, buf);
/*     */     
/*     */     }
/* 451 */     else if (jsonVal instanceof CharSequence || jsonVal instanceof Character || jsonVal.getClass().isEnum()) {
/*     */       
/* 453 */       buf.append('"');
/* 454 */       JSONUtils.escapeJSONString(jsonVal.toString(), buf);
/* 455 */       buf.append('"');
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 460 */       buf.append(jsonVal);
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
/*     */   public static String serializeObject(Object obj, int indentWidth, boolean onlySerializePublicFields, ClassFieldCache classFieldCache) {
/* 486 */     HashMap<ReferenceEqualityKey<Object>, JSONObject> objToJSONVal = new HashMap<>();
/*     */     
/* 488 */     Object rootJsonVal = toJSONGraph(obj, new HashSet<>(), new HashSet<>(), classFieldCache, objToJSONVal, onlySerializePublicFields);
/*     */ 
/*     */ 
/*     */     
/* 492 */     Map<ReferenceEqualityKey<JSONReference>, CharSequence> jsonReferenceToId = new HashMap<>();
/* 493 */     AtomicInteger objId = new AtomicInteger(0);
/* 494 */     assignObjectIds(rootJsonVal, objToJSONVal, classFieldCache, jsonReferenceToId, objId, onlySerializePublicFields);
/*     */ 
/*     */     
/* 497 */     StringBuilder buf = new StringBuilder(32768);
/* 498 */     jsonValToJSONString(rootJsonVal, jsonReferenceToId, false, 0, indentWidth, buf);
/*     */     
/* 500 */     return buf.toString();
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
/*     */   public static String serializeObject(Object obj, int indentWidth, boolean onlySerializePublicFields, ReflectionUtils reflectionUtils) {
/* 520 */     return serializeObject(obj, indentWidth, onlySerializePublicFields, new ClassFieldCache(false, false, reflectionUtils));
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
/*     */   public static String serializeObject(Object obj, int indentWidth, boolean onlySerializePublicFields) {
/* 541 */     return serializeObject(obj, indentWidth, onlySerializePublicFields, new ReflectionUtils());
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
/*     */   public static String serializeObject(Object obj) {
/* 555 */     return serializeObject(obj, 0, false);
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
/*     */   public static String serializeFromField(Object containingObject, String fieldName, int indentWidth, boolean onlySerializePublicFields, ClassFieldCache classFieldCache) {
/*     */     Object fieldValue;
/* 580 */     FieldTypeInfo fieldResolvedTypeInfo = (classFieldCache.get(containingObject.getClass())).fieldNameToFieldTypeInfo.get(fieldName);
/* 581 */     if (fieldResolvedTypeInfo == null) {
/* 582 */       throw new IllegalArgumentException("Class " + containingObject.getClass().getName() + " does not have a field named \"" + fieldName + "\"");
/*     */     }
/*     */     
/* 585 */     Field field = fieldResolvedTypeInfo.field;
/* 586 */     if (!JSONUtils.fieldIsSerializable(field, false, classFieldCache.reflectionUtils))
/*     */     {
/* 588 */       throw new IllegalArgumentException("Field " + containingObject.getClass().getName() + "." + fieldName + " needs to be accessible, non-transient, and non-final");
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 593 */       fieldValue = JSONUtils.getFieldValue(containingObject, field);
/* 594 */     } catch (IllegalAccessException e) {
/* 595 */       throw new IllegalArgumentException("Could get value of field " + fieldName, e);
/*     */     } 
/* 597 */     return serializeObject(fieldValue, indentWidth, onlySerializePublicFields, classFieldCache);
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
/*     */   public static String serializeFromField(Object containingObject, String fieldName, int indentWidth, boolean onlySerializePublicFields, ReflectionUtils reflectionUtils) {
/* 621 */     ClassFieldCache classFieldCache = new ClassFieldCache(false, onlySerializePublicFields, reflectionUtils);
/*     */     
/* 623 */     return serializeFromField(containingObject, fieldName, indentWidth, onlySerializePublicFields, classFieldCache);
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
/*     */   public static String serializeFromField(Object containingObject, String fieldName, int indentWidth, boolean onlySerializePublicFields) {
/* 645 */     return serializeFromField(containingObject, fieldName, indentWidth, onlySerializePublicFields, new ReflectionUtils());
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\JSONSerializer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */