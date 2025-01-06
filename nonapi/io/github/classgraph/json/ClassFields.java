/*     */ package nonapi.io.github.classgraph.json;
/*     */ 
/*     */ import io.github.classgraph.ScanResult;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import nonapi.io.github.classgraph.reflection.ReflectionUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ClassFields
/*     */ {
/*  63 */   final List<FieldTypeInfo> fieldOrder = new ArrayList<>();
/*     */ 
/*     */   
/*  66 */   final Map<String, FieldTypeInfo> fieldNameToFieldTypeInfo = new HashMap<>();
/*     */ 
/*     */   
/*     */   Field idField;
/*     */ 
/*     */ 
/*     */   
/*  73 */   private static final Comparator<Field> FIELD_NAME_ORDER_COMPARATOR = new Comparator<Field>()
/*     */     {
/*     */       public int compare(Field a, Field b)
/*     */       {
/*  77 */         return a.getName().compareTo(b.getName());
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static final Comparator<Field> SERIALIZATION_FORMAT_FIELD_NAME_ORDER_COMPARATOR = new Comparator<Field>()
/*     */     {
/*     */       public int compare(Field a, Field b)
/*     */       {
/*  89 */         return a.getName().equals("format") ? -1 : (
/*  90 */           b.getName().equals("format") ? 1 : a.getName().compareTo(b.getName()));
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*  95 */   private static final String SERIALIZATION_FORMAT_CLASS_NAME = ScanResult.class.getName() + "$SerializationFormat";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ClassFields(Class<?> cls, boolean resolveTypes, boolean onlySerializePublicFields, ClassFieldCache classFieldCache, ReflectionUtils reflectionUtils) {
/* 114 */     Set<String> visibleFieldNames = new HashSet<>();
/* 115 */     List<List<FieldTypeInfo>> fieldSuperclassReversedOrder = new ArrayList<>();
/* 116 */     TypeResolutions currTypeResolutions = null;
/* 117 */     for (Type<?> currType = cls; currType != Object.class && currType != null; ) {
/*     */       Class<?> currRawType;
/*     */       
/* 120 */       if (currType instanceof ParameterizedType) {
/* 121 */         ParameterizedType currParameterizedType = (ParameterizedType)currType;
/* 122 */         currRawType = (Class)currParameterizedType.getRawType();
/* 123 */       } else if (currType instanceof Class) {
/* 124 */         currRawType = (Class)currType;
/*     */       } else {
/*     */         
/* 127 */         throw new IllegalArgumentException("Illegal class type: " + currType);
/*     */       } 
/*     */ 
/*     */       
/* 131 */       Field[] fields = currRawType.getDeclaredFields();
/* 132 */       Arrays.sort(fields, cls.getName().equals(SERIALIZATION_FORMAT_CLASS_NAME) ? 
/*     */           
/* 134 */           SERIALIZATION_FORMAT_FIELD_NAME_ORDER_COMPARATOR : 
/*     */           
/* 136 */           FIELD_NAME_ORDER_COMPARATOR);
/*     */ 
/*     */       
/* 139 */       List<FieldTypeInfo> fieldOrderWithinClass = new ArrayList<>();
/* 140 */       for (Field field : fields) {
/*     */         
/* 142 */         if (visibleFieldNames.add(field.getName())) {
/*     */           
/* 144 */           boolean isIdField = field.isAnnotationPresent((Class)Id.class);
/* 145 */           if (isIdField) {
/* 146 */             if (this.idField != null) {
/* 147 */               throw new IllegalArgumentException("More than one @Id annotation: " + this.idField
/* 148 */                   .getDeclaringClass() + "." + this.idField + " ; " + currRawType
/* 149 */                   .getName() + "." + field.getName());
/*     */             }
/* 151 */             this.idField = field;
/*     */           } 
/*     */           
/* 154 */           if (JSONUtils.fieldIsSerializable(field, onlySerializePublicFields, reflectionUtils)) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 159 */             Type fieldGenericType = field.getGenericType();
/*     */ 
/*     */             
/* 162 */             Type fieldTypePartiallyResolved = (currTypeResolutions != null && resolveTypes) ? currTypeResolutions.resolveTypeVariables(fieldGenericType) : fieldGenericType;
/*     */ 
/*     */             
/* 165 */             FieldTypeInfo fieldTypeInfo = new FieldTypeInfo(field, fieldTypePartiallyResolved, classFieldCache);
/*     */             
/* 167 */             this.fieldNameToFieldTypeInfo.put(field.getName(), fieldTypeInfo);
/* 168 */             fieldOrderWithinClass.add(fieldTypeInfo);
/*     */           }
/* 170 */           else if (isIdField) {
/* 171 */             throw new IllegalArgumentException("@Id annotation field must be accessible, final, and non-transient: " + currRawType
/*     */                 
/* 173 */                 .getName() + "." + field.getName());
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 178 */       fieldSuperclassReversedOrder.add(fieldOrderWithinClass);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 186 */       Type genericSuperType = currRawType.getGenericSuperclass();
/* 187 */       if (resolveTypes) {
/* 188 */         if (genericSuperType instanceof ParameterizedType) {
/*     */ 
/*     */           
/* 191 */           Type resolvedSupertype = (currTypeResolutions == null) ? genericSuperType : currTypeResolutions.resolveTypeVariables(genericSuperType);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 196 */           currTypeResolutions = (resolvedSupertype instanceof ParameterizedType) ? new TypeResolutions((ParameterizedType)resolvedSupertype) : null;
/*     */ 
/*     */           
/* 199 */           currType = resolvedSupertype; continue;
/*     */         } 
/* 201 */         if (genericSuperType instanceof Class) {
/*     */ 
/*     */           
/* 204 */           currType = genericSuperType;
/* 205 */           currTypeResolutions = null;
/*     */           continue;
/*     */         } 
/* 208 */         throw new IllegalArgumentException("Got unexpected supertype " + genericSuperType);
/*     */       } 
/*     */ 
/*     */       
/* 212 */       currType = genericSuperType;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 217 */     for (int i = fieldSuperclassReversedOrder.size() - 1; i >= 0; i--) {
/* 218 */       List<FieldTypeInfo> fieldGroupingForClass = fieldSuperclassReversedOrder.get(i);
/* 219 */       this.fieldOrder.addAll(fieldGroupingForClass);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\ClassFields.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */