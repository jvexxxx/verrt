/*     */ package nonapi.io.github.classgraph.json;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Collection;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class FieldTypeInfo
/*     */ {
/*     */   final Field field;
/*     */   private final Type fieldTypePartiallyResolved;
/*     */   private final boolean hasUnresolvedTypeVariables;
/*     */   private final boolean isTypeVariable;
/*     */   private final PrimitiveType primitiveType;
/*     */   private Constructor<?> constructorForFieldTypeWithSizeHint;
/*     */   private Constructor<?> defaultConstructorForFieldType;
/*     */   
/*     */   private enum PrimitiveType
/*     */   {
/*  83 */     NON_PRIMITIVE,
/*     */     
/*  85 */     INTEGER,
/*     */     
/*  87 */     LONG,
/*     */     
/*  89 */     SHORT,
/*     */     
/*  91 */     DOUBLE,
/*     */     
/*  93 */     FLOAT,
/*     */     
/*  95 */     BOOLEAN,
/*     */     
/*  97 */     BYTE,
/*     */     
/*  99 */     CHARACTER,
/*     */     
/* 101 */     CLASS_REF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean hasTypeVariables(Type type) {
/* 112 */     if (type instanceof java.lang.reflect.TypeVariable || type instanceof java.lang.reflect.GenericArrayType)
/* 113 */       return true; 
/* 114 */     if (type instanceof ParameterizedType) {
/* 115 */       for (Type arg : ((ParameterizedType)type).getActualTypeArguments()) {
/* 116 */         if (hasTypeVariables(arg)) {
/* 117 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 121 */     return false;
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
/*     */   public FieldTypeInfo(Field field, Type fieldTypePartiallyResolved, ClassFieldCache classFieldCache) {
/* 136 */     this.field = field;
/* 137 */     this.fieldTypePartiallyResolved = fieldTypePartiallyResolved;
/* 138 */     this.isTypeVariable = fieldTypePartiallyResolved instanceof java.lang.reflect.TypeVariable;
/* 139 */     this.hasUnresolvedTypeVariables = (this.isTypeVariable || hasTypeVariables(fieldTypePartiallyResolved));
/*     */ 
/*     */ 
/*     */     
/* 143 */     boolean isArray = (fieldTypePartiallyResolved instanceof java.lang.reflect.GenericArrayType || (fieldTypePartiallyResolved instanceof Class && ((Class)fieldTypePartiallyResolved).isArray()));
/*     */     
/* 145 */     if (isArray || this.isTypeVariable) {
/* 146 */       this.primitiveType = PrimitiveType.NON_PRIMITIVE;
/*     */     } else {
/*     */       
/* 149 */       Class<?> fieldRawType = JSONUtils.getRawType(fieldTypePartiallyResolved);
/* 150 */       if (fieldRawType == int.class) {
/* 151 */         this.primitiveType = PrimitiveType.INTEGER;
/* 152 */       } else if (fieldRawType == long.class) {
/* 153 */         this.primitiveType = PrimitiveType.LONG;
/* 154 */       } else if (fieldRawType == short.class) {
/* 155 */         this.primitiveType = PrimitiveType.SHORT;
/* 156 */       } else if (fieldRawType == double.class) {
/* 157 */         this.primitiveType = PrimitiveType.DOUBLE;
/* 158 */       } else if (fieldRawType == float.class) {
/* 159 */         this.primitiveType = PrimitiveType.FLOAT;
/* 160 */       } else if (fieldRawType == boolean.class) {
/* 161 */         this.primitiveType = PrimitiveType.BOOLEAN;
/* 162 */       } else if (fieldRawType == byte.class) {
/* 163 */         this.primitiveType = PrimitiveType.BYTE;
/* 164 */       } else if (fieldRawType == char.class) {
/* 165 */         this.primitiveType = PrimitiveType.CHARACTER;
/* 166 */       } else if (fieldRawType == Class.class) {
/* 167 */         this.primitiveType = PrimitiveType.CLASS_REF;
/*     */       } else {
/* 169 */         this.primitiveType = PrimitiveType.NON_PRIMITIVE;
/*     */       } 
/*     */ 
/*     */       
/* 173 */       if (!JSONUtils.isBasicValueType(fieldRawType)) {
/* 174 */         if (Collection.class.isAssignableFrom(fieldRawType) || Map.class.isAssignableFrom(fieldRawType)) {
/* 175 */           this
/* 176 */             .constructorForFieldTypeWithSizeHint = classFieldCache.getConstructorWithSizeHintForConcreteTypeOf(fieldRawType);
/*     */         }
/* 178 */         if (this.constructorForFieldTypeWithSizeHint == null) {
/* 179 */           this
/* 180 */             .defaultConstructorForFieldType = classFieldCache.getDefaultConstructorForConcreteTypeOf(fieldRawType);
/*     */         }
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
/*     */   public Constructor<?> getConstructorForFieldTypeWithSizeHint(Type fieldTypeFullyResolved, ClassFieldCache classFieldCache) {
/* 197 */     if (!this.isTypeVariable) {
/* 198 */       return this.constructorForFieldTypeWithSizeHint;
/*     */     }
/* 200 */     Class<?> fieldRawTypeFullyResolved = JSONUtils.getRawType(fieldTypeFullyResolved);
/* 201 */     if (!Collection.class.isAssignableFrom(fieldRawTypeFullyResolved) && 
/* 202 */       !Map.class.isAssignableFrom(fieldRawTypeFullyResolved))
/*     */     {
/*     */       
/* 205 */       return null;
/*     */     }
/* 207 */     return classFieldCache.getConstructorWithSizeHintForConcreteTypeOf(fieldRawTypeFullyResolved);
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
/*     */   public Constructor<?> getDefaultConstructorForFieldType(Type fieldTypeFullyResolved, ClassFieldCache classFieldCache) {
/* 222 */     if (!this.isTypeVariable) {
/* 223 */       return this.defaultConstructorForFieldType;
/*     */     }
/* 225 */     Class<?> fieldRawTypeFullyResolved = JSONUtils.getRawType(fieldTypeFullyResolved);
/* 226 */     return classFieldCache.getDefaultConstructorForConcreteTypeOf(fieldRawTypeFullyResolved);
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
/*     */   public Type getFullyResolvedFieldType(TypeResolutions typeResolutions) {
/* 238 */     if (!this.hasUnresolvedTypeVariables)
/*     */     {
/* 240 */       return this.fieldTypePartiallyResolved;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 245 */     return typeResolutions.resolveTypeVariables(this.fieldTypePartiallyResolved);
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
/*     */   void setFieldValue(Object containingObj, Object value) {
/*     */     try {
/* 258 */       if (value == null) {
/* 259 */         if (this.primitiveType != PrimitiveType.NON_PRIMITIVE) {
/* 260 */           throw new IllegalArgumentException("Tried to set primitive-typed field " + this.field
/* 261 */               .getDeclaringClass().getName() + "." + this.field.getName() + " to null value");
/*     */         }
/* 263 */         this.field.set(containingObj, null);
/*     */         return;
/*     */       } 
/* 266 */       switch (this.primitiveType) {
/*     */         case NON_PRIMITIVE:
/* 268 */           this.field.set(containingObj, value);
/*     */           return;
/*     */         case CLASS_REF:
/* 271 */           if (!(value instanceof Class)) {
/* 272 */             throw new IllegalArgumentException("Expected value of type Class<?>; got " + value
/* 273 */                 .getClass().getName());
/*     */           }
/* 275 */           this.field.set(containingObj, value);
/*     */           return;
/*     */         case INTEGER:
/* 278 */           if (!(value instanceof Integer)) {
/* 279 */             throw new IllegalArgumentException("Expected value of type Integer; got " + value
/* 280 */                 .getClass().getName());
/*     */           }
/* 282 */           this.field.setInt(containingObj, ((Integer)value).intValue());
/*     */           return;
/*     */         case LONG:
/* 285 */           if (!(value instanceof Long)) {
/* 286 */             throw new IllegalArgumentException("Expected value of type Long; got " + value
/* 287 */                 .getClass().getName());
/*     */           }
/* 289 */           this.field.setLong(containingObj, ((Long)value).longValue());
/*     */           return;
/*     */         case SHORT:
/* 292 */           if (!(value instanceof Short)) {
/* 293 */             throw new IllegalArgumentException("Expected value of type Short; got " + value
/* 294 */                 .getClass().getName());
/*     */           }
/* 296 */           this.field.setShort(containingObj, ((Short)value).shortValue());
/*     */           return;
/*     */         case DOUBLE:
/* 299 */           if (!(value instanceof Double)) {
/* 300 */             throw new IllegalArgumentException("Expected value of type Double; got " + value
/* 301 */                 .getClass().getName());
/*     */           }
/* 303 */           this.field.setDouble(containingObj, ((Double)value).doubleValue());
/*     */           return;
/*     */         case FLOAT:
/* 306 */           if (!(value instanceof Float)) {
/* 307 */             throw new IllegalArgumentException("Expected value of type Float; got " + value
/* 308 */                 .getClass().getName());
/*     */           }
/* 310 */           this.field.setFloat(containingObj, ((Float)value).floatValue());
/*     */           return;
/*     */         case BOOLEAN:
/* 313 */           if (!(value instanceof Boolean)) {
/* 314 */             throw new IllegalArgumentException("Expected value of type Boolean; got " + value
/* 315 */                 .getClass().getName());
/*     */           }
/* 317 */           this.field.setBoolean(containingObj, ((Boolean)value).booleanValue());
/*     */           return;
/*     */         case BYTE:
/* 320 */           if (!(value instanceof Byte)) {
/* 321 */             throw new IllegalArgumentException("Expected value of type Byte; got " + value
/* 322 */                 .getClass().getName());
/*     */           }
/* 324 */           this.field.setByte(containingObj, ((Byte)value).byteValue());
/*     */           return;
/*     */         case CHARACTER:
/* 327 */           if (!(value instanceof Character)) {
/* 328 */             throw new IllegalArgumentException("Expected value of type Character; got " + value
/* 329 */                 .getClass().getName());
/*     */           }
/* 331 */           this.field.setChar(containingObj, ((Character)value).charValue());
/*     */           return;
/*     */       } 
/* 334 */       throw new IllegalArgumentException();
/*     */     }
/* 336 */     catch (IllegalArgumentException|IllegalAccessException e) {
/* 337 */       throw new IllegalArgumentException("Could not set field " + this.field
/* 338 */           .getDeclaringClass().getName() + "." + this.field.getName(), e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 347 */     return this.fieldTypePartiallyResolved + " " + this.field.getDeclaringClass().getName() + "." + this.field
/* 348 */       .getDeclaringClass().getName();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\FieldTypeInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */