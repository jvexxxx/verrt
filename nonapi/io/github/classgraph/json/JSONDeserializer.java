/*     */ package nonapi.io.github.classgraph.json;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ import nonapi.io.github.classgraph.types.ParseException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JSONDeserializer
/*     */ {
/*     */   private static Object jsonBasicValueToObject(Object jsonVal, Type expectedType, boolean convertStringToNumber) {
/*  72 */     if (jsonVal == null)
/*  73 */       return null; 
/*  74 */     if (jsonVal instanceof JSONArray || jsonVal instanceof JSONObject) {
/*  75 */       throw new RuntimeException("Expected a basic value type");
/*     */     }
/*  77 */     if (expectedType instanceof ParameterizedType) {
/*  78 */       if (((ParameterizedType)expectedType).getRawType().getClass() == Class.class) {
/*  79 */         String str = jsonVal.toString();
/*  80 */         int idx = str.indexOf('<');
/*  81 */         String className = str.substring(0, (idx < 0) ? str.length() : idx);
/*     */         try {
/*  83 */           return Class.forName(className);
/*  84 */         } catch (ClassNotFoundException e) {
/*  85 */           throw new IllegalArgumentException("Could not deserialize class reference " + jsonVal, e);
/*     */         } 
/*     */       } 
/*  88 */       throw new IllegalArgumentException("Got illegal ParameterizedType: " + expectedType);
/*     */     } 
/*  90 */     if (!(expectedType instanceof Class)) {
/*  91 */       throw new IllegalArgumentException("Got illegal basic value type: " + expectedType);
/*     */     }
/*     */     
/*  94 */     Class<?> rawType = (Class)expectedType;
/*  95 */     if (rawType == String.class) {
/*  96 */       if (!(jsonVal instanceof CharSequence)) {
/*  97 */         throw new IllegalArgumentException("Expected string; got " + jsonVal.getClass().getName());
/*     */       }
/*  99 */       return jsonVal.toString();
/*     */     } 
/* 101 */     if (rawType == CharSequence.class) {
/* 102 */       if (!(jsonVal instanceof CharSequence)) {
/* 103 */         throw new IllegalArgumentException("Expected CharSequence; got " + jsonVal.getClass().getName());
/*     */       }
/* 105 */       return jsonVal;
/*     */     } 
/* 107 */     if (rawType == Integer.class || rawType == int.class) {
/* 108 */       if (convertStringToNumber && jsonVal instanceof CharSequence) {
/* 109 */         return Integer.valueOf(Integer.parseInt(jsonVal.toString()));
/*     */       }
/* 111 */       if (!(jsonVal instanceof Integer)) {
/* 112 */         throw new IllegalArgumentException("Expected integer; got " + jsonVal.getClass().getName());
/*     */       }
/* 114 */       return jsonVal;
/*     */     } 
/* 116 */     if (rawType == Long.class || rawType == long.class) {
/* 117 */       boolean isLong = jsonVal instanceof Long;
/* 118 */       boolean isInteger = jsonVal instanceof Integer;
/* 119 */       if (convertStringToNumber && jsonVal instanceof CharSequence) {
/* 120 */         return Long.valueOf(isLong ? Long.parseLong(jsonVal.toString()) : Integer.parseInt(jsonVal.toString()));
/*     */       }
/* 122 */       if (!isLong && !isInteger) {
/* 123 */         throw new IllegalArgumentException("Expected long; got " + jsonVal.getClass().getName());
/*     */       }
/* 125 */       if (isLong) {
/* 126 */         return jsonVal;
/*     */       }
/* 128 */       return Long.valueOf(((Integer)jsonVal).intValue());
/*     */     } 
/*     */     
/* 131 */     if (rawType == Short.class || rawType == short.class) {
/* 132 */       if (convertStringToNumber && jsonVal instanceof CharSequence) {
/* 133 */         return Short.valueOf(Short.parseShort(jsonVal.toString()));
/*     */       }
/* 135 */       if (!(jsonVal instanceof Integer)) {
/* 136 */         throw new IllegalArgumentException("Expected short; got " + jsonVal.getClass().getName());
/*     */       }
/* 138 */       int intValue = ((Integer)jsonVal).intValue();
/* 139 */       if (intValue < -32768 || intValue > 32767) {
/* 140 */         throw new IllegalArgumentException("Expected short; got out-of-range value " + intValue);
/*     */       }
/* 142 */       return Short.valueOf((short)intValue);
/*     */     } 
/* 144 */     if (rawType == Float.class || rawType == float.class) {
/* 145 */       if (convertStringToNumber && jsonVal instanceof CharSequence) {
/* 146 */         return Float.valueOf(Float.parseFloat(jsonVal.toString()));
/*     */       }
/* 148 */       if (!(jsonVal instanceof Double)) {
/* 149 */         throw new IllegalArgumentException("Expected float; got " + jsonVal.getClass().getName());
/*     */       }
/* 151 */       double doubleValue = ((Double)jsonVal).doubleValue();
/* 152 */       if (doubleValue < -3.4028234663852886E38D || doubleValue > 3.4028234663852886E38D) {
/* 153 */         throw new IllegalArgumentException("Expected float; got out-of-range value " + doubleValue);
/*     */       }
/* 155 */       return Float.valueOf((float)doubleValue);
/*     */     } 
/* 157 */     if (rawType == Double.class || rawType == double.class) {
/* 158 */       if (convertStringToNumber && jsonVal instanceof CharSequence) {
/* 159 */         return Double.valueOf(Double.parseDouble(jsonVal.toString()));
/*     */       }
/* 161 */       if (!(jsonVal instanceof Double)) {
/* 162 */         throw new IllegalArgumentException("Expected double; got " + jsonVal.getClass().getName());
/*     */       }
/* 164 */       return jsonVal;
/*     */     } 
/* 166 */     if (rawType == Byte.class || rawType == byte.class) {
/* 167 */       if (convertStringToNumber && jsonVal instanceof CharSequence) {
/* 168 */         return Byte.valueOf(Byte.parseByte(jsonVal.toString()));
/*     */       }
/* 170 */       if (!(jsonVal instanceof Integer)) {
/* 171 */         throw new IllegalArgumentException("Expected byte; got " + jsonVal.getClass().getName());
/*     */       }
/* 173 */       int intValue = ((Integer)jsonVal).intValue();
/* 174 */       if (intValue < -128 || intValue > 127) {
/* 175 */         throw new IllegalArgumentException("Expected byte; got out-of-range value " + intValue);
/*     */       }
/* 177 */       return Byte.valueOf((byte)intValue);
/*     */     } 
/* 179 */     if (rawType == Character.class || rawType == char.class) {
/* 180 */       if (!(jsonVal instanceof CharSequence)) {
/* 181 */         throw new IllegalArgumentException("Expected character; got " + jsonVal.getClass().getName());
/*     */       }
/* 183 */       CharSequence charSequence = (CharSequence)jsonVal;
/* 184 */       if (charSequence.length() != 1) {
/* 185 */         throw new IllegalArgumentException("Expected single character; got string");
/*     */       }
/* 187 */       return Character.valueOf(charSequence.charAt(0));
/*     */     } 
/* 189 */     if (rawType == Boolean.class || rawType == boolean.class) {
/* 190 */       if (convertStringToNumber && jsonVal instanceof CharSequence) {
/* 191 */         return Boolean.valueOf(Boolean.parseBoolean(jsonVal.toString()));
/*     */       }
/* 193 */       if (!(jsonVal instanceof Boolean)) {
/* 194 */         throw new IllegalArgumentException("Expected boolean; got " + jsonVal.getClass().getName());
/*     */       }
/* 196 */       return jsonVal;
/* 197 */     }  if (Enum.class.isAssignableFrom(rawType)) {
/* 198 */       if (!(jsonVal instanceof CharSequence)) {
/* 199 */         throw new IllegalArgumentException("Expected string for enum value; got " + jsonVal
/* 200 */             .getClass().getName());
/*     */       }
/*     */       
/* 203 */       Enum enumValue = (Enum)Enum.valueOf(rawType, jsonVal.toString());
/* 204 */       return enumValue;
/*     */     } 
/* 206 */     if (JSONUtils.getRawType(expectedType).isAssignableFrom(jsonVal.getClass())) {
/* 207 */       return jsonVal;
/*     */     }
/*     */     
/* 210 */     throw new IllegalArgumentException("Got type " + jsonVal.getClass() + "; expected " + expectedType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class ObjectInstantiation
/*     */   {
/*     */     Object jsonVal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Object objectInstance;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Type type;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public ObjectInstantiation(Object objectInstance, Type type, Object jsonVal) {
/* 240 */       this.jsonVal = jsonVal;
/* 241 */       this.objectInstance = objectInstance;
/* 242 */       this.type = type;
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
/*     */   private static void populateObjectFromJsonObject(Object objectInstance, Type objectResolvedType, Object jsonVal, ClassFieldCache classFieldCache, Map<CharSequence, Object> idToObjectInstance, List<Runnable> collectionElementAdders) {
/*     */     TypeResolutions typeResolutions;
/*     */     Type mapKeyType, commonResolvedValueType;
/*     */     Class<?> arrayComponentType;
/*     */     boolean is1DArray;
/*     */     Constructor<?> commonValueConstructorWithSizeHint, commonValueDefaultConstructor;
/* 267 */     if (jsonVal == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 272 */     boolean isJsonObject = jsonVal instanceof JSONObject;
/* 273 */     boolean isJsonArray = jsonVal instanceof JSONArray;
/* 274 */     if (!isJsonArray && !isJsonObject) {
/* 275 */       throw new IllegalArgumentException("Expected JSONObject or JSONArray, got " + jsonVal
/* 276 */           .getClass().getSimpleName());
/*     */     }
/* 278 */     JSONObject jsonObject = isJsonObject ? (JSONObject)jsonVal : null;
/* 279 */     JSONArray jsonArray = isJsonArray ? (JSONArray)jsonVal : null;
/*     */ 
/*     */     
/* 282 */     Class<?> rawType = objectInstance.getClass();
/* 283 */     boolean isMap = Map.class.isAssignableFrom(rawType);
/*     */     
/* 285 */     Map<Object, Object> mapInstance = isMap ? (Map<Object, Object>)objectInstance : null;
/* 286 */     boolean isCollection = Collection.class.isAssignableFrom(rawType);
/*     */     
/* 288 */     final Collection<Object> collectionInstance = isCollection ? (Collection<Object>)objectInstance : null;
/* 289 */     boolean isArray = rawType.isArray();
/* 290 */     boolean isObj = (!isMap && !isCollection && !isArray);
/* 291 */     if (((isMap || isObj)) != isJsonObject || ((isCollection || isArray)) != isJsonArray) {
/* 292 */       throw new IllegalArgumentException("Wrong JSON type for class " + objectInstance.getClass().getName());
/*     */     }
/*     */ 
/*     */     
/* 296 */     Type objectResolvedTypeGeneric = objectResolvedType;
/* 297 */     if (objectResolvedType instanceof Class) {
/* 298 */       Class<?> objectResolvedCls = (Class)objectResolvedType;
/* 299 */       if (Map.class.isAssignableFrom(objectResolvedCls)) {
/* 300 */         if (!isMap) {
/* 301 */           throw new IllegalArgumentException("Got an unexpected map type");
/*     */         }
/* 303 */         objectResolvedTypeGeneric = objectResolvedCls.getGenericSuperclass();
/* 304 */       } else if (Collection.class.isAssignableFrom(objectResolvedCls)) {
/* 305 */         if (!isCollection) {
/* 306 */           throw new IllegalArgumentException("Got an unexpected map type");
/*     */         }
/* 308 */         objectResolvedTypeGeneric = objectResolvedCls.getGenericSuperclass();
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
/*     */ 
/*     */     
/* 322 */     if (objectResolvedTypeGeneric instanceof Class) {
/*     */       
/* 324 */       typeResolutions = null;
/* 325 */       mapKeyType = null;
/* 326 */       Class<?> objectResolvedCls = (Class)objectResolvedTypeGeneric;
/* 327 */       if (isArray) {
/* 328 */         arrayComponentType = objectResolvedCls.getComponentType();
/* 329 */         is1DArray = !arrayComponentType.isArray();
/*     */       } else {
/* 331 */         arrayComponentType = null;
/* 332 */         is1DArray = false;
/*     */       } 
/* 334 */       commonResolvedValueType = null;
/* 335 */     } else if (objectResolvedTypeGeneric instanceof ParameterizedType) {
/*     */ 
/*     */       
/* 338 */       ParameterizedType parameterizedResolvedType = (ParameterizedType)objectResolvedTypeGeneric;
/* 339 */       typeResolutions = new TypeResolutions(parameterizedResolvedType);
/*     */       
/* 341 */       int numTypeArgs = typeResolutions.resolvedTypeArguments.length;
/* 342 */       if (isMap && numTypeArgs != 2) {
/* 343 */         throw new IllegalArgumentException("Wrong number of type arguments for Map: got " + numTypeArgs + "; expected 2");
/*     */       }
/* 345 */       if (isCollection && numTypeArgs != 1) {
/* 346 */         throw new IllegalArgumentException("Wrong number of type arguments for Collection: got " + numTypeArgs + "; expected 1");
/*     */       }
/*     */       
/* 349 */       mapKeyType = isMap ? typeResolutions.resolvedTypeArguments[0] : null;
/*     */       
/* 351 */       commonResolvedValueType = isMap ? typeResolutions.resolvedTypeArguments[1] : (isCollection ? typeResolutions.resolvedTypeArguments[0] : null);
/* 352 */       is1DArray = false;
/* 353 */       arrayComponentType = null;
/*     */     } else {
/* 355 */       throw new IllegalArgumentException("Got illegal type: " + objectResolvedTypeGeneric);
/*     */     } 
/*     */     
/* 358 */     Class<?> commonValueRawType = (commonResolvedValueType == null) ? null : JSONUtils.getRawType(commonResolvedValueType);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 364 */     if (isMap || isCollection || (is1DArray && !JSONUtils.isBasicValueType(arrayComponentType))) {
/*     */       
/* 366 */       commonValueConstructorWithSizeHint = classFieldCache.getConstructorWithSizeHintForConcreteTypeOf(
/* 367 */           is1DArray ? arrayComponentType : commonValueRawType);
/* 368 */       if (commonValueConstructorWithSizeHint != null) {
/*     */         
/* 370 */         commonValueDefaultConstructor = null;
/*     */       } else {
/* 372 */         commonValueDefaultConstructor = classFieldCache.getDefaultConstructorForConcreteTypeOf(
/* 373 */             is1DArray ? arrayComponentType : commonValueRawType);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 378 */       commonValueConstructorWithSizeHint = null;
/* 379 */       commonValueDefaultConstructor = null;
/*     */     } 
/*     */ 
/*     */     
/* 383 */     ClassFields classFields = isObj ? classFieldCache.get(rawType) : null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 388 */     ArrayList<ObjectInstantiation> itemsToRecurseToInPass2 = null;
/*     */ 
/*     */ 
/*     */     
/* 392 */     int numItems = (jsonObject != null) ? jsonObject.items.size() : ((jsonArray != null) ? jsonArray.items.size() : 0);
/* 393 */     for (int i = 0; i < numItems; i++) {
/*     */       String itemJsonKey; Object itemJsonValue;
/*     */       FieldTypeInfo fieldTypeInfo;
/*     */       final Object instantiatedItemObject;
/* 397 */       if (jsonObject != null) {
/* 398 */         Map.Entry<String, Object> jsonObjectItem = jsonObject.items.get(i);
/* 399 */         itemJsonKey = jsonObjectItem.getKey();
/* 400 */         itemJsonValue = jsonObjectItem.getValue();
/* 401 */       } else if (jsonArray != null) {
/* 402 */         itemJsonKey = null;
/* 403 */         itemJsonValue = jsonArray.items.get(i);
/*     */       } else {
/*     */         
/* 406 */         throw new RuntimeException("This exception should not be thrown");
/*     */       } 
/* 408 */       boolean itemJsonValueIsJsonObject = itemJsonValue instanceof JSONObject;
/* 409 */       boolean itemJsonValueIsJsonArray = itemJsonValue instanceof JSONArray;
/*     */       
/* 411 */       JSONObject itemJsonValueJsonObject = itemJsonValueIsJsonObject ? (JSONObject)itemJsonValue : null;
/* 412 */       JSONArray itemJsonValueJsonArray = itemJsonValueIsJsonArray ? (JSONArray)itemJsonValue : null;
/*     */ 
/*     */ 
/*     */       
/* 416 */       if (classFields != null) {
/*     */ 
/*     */         
/* 419 */         fieldTypeInfo = classFields.fieldNameToFieldTypeInfo.get(itemJsonKey);
/* 420 */         if (fieldTypeInfo == null) {
/* 421 */           throw new IllegalArgumentException("Field " + rawType.getName() + "." + itemJsonKey + " does not exist or is not accessible, non-final, and non-transient");
/*     */         }
/*     */       } else {
/*     */         
/* 425 */         fieldTypeInfo = null;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 439 */       Type resolvedItemValueType = (fieldTypeInfo != null) ? fieldTypeInfo.getFullyResolvedFieldType(typeResolutions) : (isArray ? arrayComponentType : commonResolvedValueType);
/*     */ 
/*     */ 
/*     */       
/* 443 */       if (itemJsonValue == null) {
/*     */         
/* 445 */         instantiatedItemObject = null;
/*     */       }
/* 447 */       else if (resolvedItemValueType == Object.class) {
/*     */ 
/*     */         
/* 450 */         if (itemJsonValueIsJsonObject) {
/* 451 */           instantiatedItemObject = new HashMap<>();
/* 452 */           if (itemsToRecurseToInPass2 == null) {
/* 453 */             itemsToRecurseToInPass2 = new ArrayList<>();
/*     */           }
/* 455 */           itemsToRecurseToInPass2.add(new ObjectInstantiation(instantiatedItemObject, ParameterizedTypeImpl.MAP_OF_UNKNOWN_TYPE, itemJsonValue));
/*     */         
/*     */         }
/* 458 */         else if (itemJsonValueIsJsonArray) {
/* 459 */           instantiatedItemObject = new ArrayList();
/* 460 */           if (itemsToRecurseToInPass2 == null) {
/* 461 */             itemsToRecurseToInPass2 = new ArrayList<>();
/*     */           }
/* 463 */           itemsToRecurseToInPass2.add(new ObjectInstantiation(instantiatedItemObject, ParameterizedTypeImpl.LIST_OF_UNKNOWN_TYPE, itemJsonValue));
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 468 */           instantiatedItemObject = jsonBasicValueToObject(itemJsonValue, resolvedItemValueType, false);
/*     */         }
/*     */       
/*     */       }
/* 472 */       else if (JSONUtils.isBasicValueType(resolvedItemValueType)) {
/*     */         
/* 474 */         if (itemJsonValueIsJsonObject || itemJsonValueIsJsonArray) {
/* 475 */           throw new IllegalArgumentException("Got JSONObject or JSONArray type when expecting a simple value type");
/*     */         }
/*     */ 
/*     */         
/* 479 */         instantiatedItemObject = jsonBasicValueToObject(itemJsonValue, resolvedItemValueType, false);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 485 */       else if (CharSequence.class.isAssignableFrom(itemJsonValue.getClass())) {
/*     */ 
/*     */         
/* 488 */         Object linkedObject = idToObjectInstance.get(itemJsonValue);
/* 489 */         if (linkedObject == null)
/*     */         {
/*     */           
/* 492 */           throw new IllegalArgumentException("Object id not found: " + itemJsonValue);
/*     */         }
/*     */         
/* 495 */         instantiatedItemObject = linkedObject;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 500 */         if (!itemJsonValueIsJsonObject && !itemJsonValueIsJsonArray) {
/* 501 */           throw new IllegalArgumentException("Got simple value type when expecting a JSON object or JSON array");
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         try {
/* 511 */           int numSubItems = (itemJsonValueJsonObject != null) ? itemJsonValueJsonObject.items.size() : ((itemJsonValueJsonArray != null) ? itemJsonValueJsonArray.items.size() : 0);
/* 512 */           if (resolvedItemValueType instanceof Class && ((Class)resolvedItemValueType)
/* 513 */             .isArray()) {
/*     */             
/* 515 */             if (!itemJsonValueIsJsonArray) {
/* 516 */               throw new IllegalArgumentException("Expected JSONArray, got " + itemJsonValue
/* 517 */                   .getClass().getName());
/*     */             }
/* 519 */             instantiatedItemObject = Array.newInstance(((Class)resolvedItemValueType)
/* 520 */                 .getComponentType(), numSubItems);
/*     */           
/*     */           }
/* 523 */           else if (isCollection || isMap || is1DArray) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 531 */             instantiatedItemObject = (commonValueConstructorWithSizeHint != null) ? commonValueConstructorWithSizeHint.newInstance(new Object[] { Integer.valueOf(numSubItems) }) : ((commonValueDefaultConstructor != null) ? commonValueDefaultConstructor.newInstance(new Object[0]) : null);
/* 532 */           } else if (fieldTypeInfo != null) {
/*     */ 
/*     */ 
/*     */             
/* 536 */             Constructor<?> valueConstructorWithSizeHint = fieldTypeInfo.getConstructorForFieldTypeWithSizeHint(resolvedItemValueType, classFieldCache);
/*     */             
/* 538 */             if (valueConstructorWithSizeHint != null) {
/* 539 */               instantiatedItemObject = valueConstructorWithSizeHint.newInstance(new Object[] { Integer.valueOf(numSubItems) });
/*     */             } else {
/*     */               
/* 542 */               instantiatedItemObject = fieldTypeInfo.getDefaultConstructorForFieldType(resolvedItemValueType, classFieldCache).newInstance(new Object[0]);
/*     */             } 
/* 544 */           } else if (isArray && !is1DArray) {
/*     */             
/* 546 */             instantiatedItemObject = Array.newInstance(rawType.getComponentType(), numSubItems);
/*     */           } else {
/*     */             
/* 549 */             throw new IllegalArgumentException("Got illegal type");
/*     */           }
/*     */         
/* 552 */         } catch (ReflectiveOperationException|SecurityException e) {
/* 553 */           throw new IllegalArgumentException("Could not instantiate type " + resolvedItemValueType, e);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 560 */         if (itemJsonValue instanceof JSONObject) {
/* 561 */           JSONObject itemJsonObject = (JSONObject)itemJsonValue;
/* 562 */           if (itemJsonObject.objectId != null) {
/* 563 */             idToObjectInstance.put(itemJsonObject.objectId, instantiatedItemObject);
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 568 */         if (itemsToRecurseToInPass2 == null) {
/* 569 */           itemsToRecurseToInPass2 = new ArrayList<>();
/*     */         }
/* 571 */         itemsToRecurseToInPass2.add(new ObjectInstantiation(instantiatedItemObject, resolvedItemValueType, itemJsonValue));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 577 */       if (fieldTypeInfo != null) {
/* 578 */         fieldTypeInfo.setFieldValue(objectInstance, instantiatedItemObject);
/* 579 */       } else if (mapInstance != null) {
/*     */ 
/*     */         
/* 582 */         Object mapKey = jsonBasicValueToObject(itemJsonKey, mapKeyType, true);
/*     */         
/* 584 */         mapInstance.put(mapKey, instantiatedItemObject);
/* 585 */       } else if (isArray) {
/* 586 */         Array.set(objectInstance, i, instantiatedItemObject);
/* 587 */       } else if (collectionInstance != null) {
/*     */ 
/*     */         
/* 590 */         collectionElementAdders.add(new Runnable()
/*     */             {
/*     */               public void run() {
/* 593 */                 collectionInstance.add(instantiatedItemObject);
/*     */               }
/*     */             });
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 600 */     if (itemsToRecurseToInPass2 != null) {
/* 601 */       for (ObjectInstantiation objectInstantiation : itemsToRecurseToInPass2) {
/* 602 */         populateObjectFromJsonObject(objectInstantiation.objectInstance, objectInstantiation.type, objectInstantiation.jsonVal, classFieldCache, idToObjectInstance, collectionElementAdders);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Map<CharSequence, Object> getInitialIdToObjectMap(Object objectInstance, Object parsedJSON) {
/* 622 */     Map<CharSequence, Object> idToObjectInstance = new HashMap<>();
/* 623 */     if (parsedJSON instanceof JSONObject) {
/* 624 */       JSONObject itemJsonObject = (JSONObject)parsedJSON;
/* 625 */       if (!itemJsonObject.items.isEmpty()) {
/* 626 */         Map.Entry<String, Object> firstItem = itemJsonObject.items.get(0);
/* 627 */         if (((String)firstItem.getKey()).equals("__ID")) {
/* 628 */           Object firstItemValue = firstItem.getValue();
/* 629 */           if (firstItemValue == null || !CharSequence.class.isAssignableFrom(firstItemValue.getClass())) {
/* 630 */             idToObjectInstance.put((CharSequence)firstItemValue, objectInstance);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 635 */     return idToObjectInstance;
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
/*     */   private static <T> T deserializeObject(Class<T> expectedType, String json, ClassFieldCache classFieldCache) throws IllegalArgumentException {
/*     */     Object parsedJSON;
/*     */     T objectInstance;
/*     */     try {
/* 663 */       parsedJSON = JSONParser.parseJSON(json);
/* 664 */     } catch (ParseException e) {
/* 665 */       throw new IllegalArgumentException("Could not parse JSON", e);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 671 */       Constructor<?> constructor = classFieldCache.getDefaultConstructorForConcreteTypeOf(expectedType);
/*     */       
/* 673 */       T newInstance = (T)constructor.newInstance(new Object[0]);
/* 674 */       objectInstance = newInstance;
/* 675 */     } catch (ReflectiveOperationException|SecurityException e) {
/* 676 */       throw new IllegalArgumentException("Could not construct object of type " + expectedType.getName(), e);
/*     */     } 
/*     */ 
/*     */     
/* 680 */     List<Runnable> collectionElementAdders = new ArrayList<>();
/* 681 */     populateObjectFromJsonObject(objectInstance, expectedType, parsedJSON, classFieldCache, 
/* 682 */         getInitialIdToObjectMap(objectInstance, parsedJSON), collectionElementAdders);
/* 683 */     for (Runnable runnable : collectionElementAdders) {
/* 684 */       runnable.run();
/*     */     }
/* 686 */     return objectInstance;
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
/*     */   public static <T> T deserializeObject(Class<T> expectedType, String json, ReflectionUtils reflectionUtils) throws IllegalArgumentException {
/* 706 */     ClassFieldCache classFieldCache = new ClassFieldCache(true, false, reflectionUtils);
/*     */     
/* 708 */     return deserializeObject(expectedType, json, classFieldCache);
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
/*     */   public static <T> T deserializeObject(Class<T> expectedType, String json) throws IllegalArgumentException {
/* 727 */     return deserializeObject(expectedType, json, new ReflectionUtils());
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
/*     */   public static void deserializeToField(Object containingObject, String fieldName, String json, ClassFieldCache classFieldCache) throws IllegalArgumentException {
/*     */     Object parsedJSON;
/* 749 */     if (containingObject == null) {
/* 750 */       throw new IllegalArgumentException("Cannot deserialize to a field of a null object");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 756 */       parsedJSON = JSONParser.parseJSON(json);
/* 757 */     } catch (ParseException e) {
/* 758 */       throw new IllegalArgumentException("Could not parse JSON", e);
/*     */     } 
/*     */ 
/*     */     
/* 762 */     JSONObject wrapperJsonObj = new JSONObject(1);
/* 763 */     wrapperJsonObj.items.add(new AbstractMap.SimpleEntry<>(fieldName, parsedJSON));
/*     */ 
/*     */ 
/*     */     
/* 767 */     List<Runnable> collectionElementAdders = new ArrayList<>();
/* 768 */     populateObjectFromJsonObject(containingObject, containingObject.getClass(), wrapperJsonObj, classFieldCache, new HashMap<>(), collectionElementAdders);
/*     */     
/* 770 */     for (Runnable runnable : collectionElementAdders) {
/* 771 */       runnable.run();
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
/*     */   public static void deserializeToField(Object containingObject, String fieldName, String json, ReflectionUtils reflectionUtils) throws IllegalArgumentException {
/* 791 */     ClassFieldCache typeCache = new ClassFieldCache(true, false, reflectionUtils);
/*     */     
/* 793 */     deserializeToField(containingObject, fieldName, json, typeCache);
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
/*     */   public static void deserializeToField(Object containingObject, String fieldName, String json) throws IllegalArgumentException {
/* 812 */     deserializeToField(containingObject, fieldName, json, new ReflectionUtils());
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\JSONDeserializer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */