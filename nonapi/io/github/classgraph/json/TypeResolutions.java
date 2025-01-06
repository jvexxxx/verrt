/*     */ package nonapi.io.github.classgraph.json;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.lang.reflect.GenericArrayType;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.lang.reflect.TypeVariable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TypeResolutions
/*     */ {
/*     */   private final TypeVariable<?>[] typeVariables;
/*     */   Type[] resolvedTypeArguments;
/*     */   
/*     */   TypeResolutions(ParameterizedType resolvedType) {
/*  55 */     this.typeVariables = (TypeVariable<?>[])((Class)resolvedType.getRawType()).getTypeParameters();
/*  56 */     this.resolvedTypeArguments = resolvedType.getActualTypeArguments();
/*  57 */     if (this.resolvedTypeArguments.length != this.typeVariables.length) {
/*  58 */       throw new IllegalArgumentException("Type parameter count mismatch");
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
/*     */   Type resolveTypeVariables(Type type) {
/*  70 */     if (type instanceof Class)
/*     */     {
/*  72 */       return type;
/*     */     }
/*  74 */     if (type instanceof ParameterizedType) {
/*     */       
/*  76 */       ParameterizedType parameterizedType = (ParameterizedType)type;
/*  77 */       Type[] typeArgs = parameterizedType.getActualTypeArguments();
/*  78 */       Type[] typeArgsResolved = null;
/*  79 */       for (int i = 0; i < typeArgs.length; i++) {
/*     */         
/*  81 */         Type typeArgResolved = resolveTypeVariables(typeArgs[i]);
/*     */         
/*  83 */         if (typeArgsResolved == null) {
/*  84 */           if (!typeArgResolved.equals(typeArgs[i])) {
/*     */             
/*  86 */             typeArgsResolved = new Type[typeArgs.length];
/*     */             
/*  88 */             System.arraycopy(typeArgs, 0, typeArgsResolved, 0, i);
/*     */             
/*  90 */             typeArgsResolved[i] = typeArgResolved;
/*     */           } 
/*     */         } else {
/*     */           
/*  94 */           typeArgsResolved[i] = typeArgResolved;
/*     */         } 
/*     */       } 
/*  97 */       if (typeArgsResolved == null)
/*     */       {
/*  99 */         return type;
/*     */       }
/*     */       
/* 102 */       return new ParameterizedTypeImpl((Class)parameterizedType.getRawType(), typeArgsResolved, parameterizedType
/* 103 */           .getOwnerType());
/*     */     } 
/*     */     
/* 106 */     if (type instanceof TypeVariable) {
/*     */       
/* 108 */       TypeVariable<?> typeVariable = (TypeVariable)type;
/* 109 */       for (int i = 0; i < this.typeVariables.length; i++) {
/* 110 */         if (this.typeVariables[i].getName().equals(typeVariable.getName())) {
/* 111 */           return this.resolvedTypeArguments[i];
/*     */         }
/*     */       } 
/*     */       
/* 115 */       return type;
/*     */     } 
/* 117 */     if (type instanceof GenericArrayType) {
/*     */       
/* 119 */       int numArrayDims = 0;
/* 120 */       Type t = type;
/* 121 */       while (t instanceof GenericArrayType) {
/* 122 */         numArrayDims++;
/* 123 */         t = ((GenericArrayType)t).getGenericComponentType();
/*     */       } 
/* 125 */       Type innermostType = t;
/* 126 */       Type innermostTypeResolved = resolveTypeVariables(innermostType);
/* 127 */       if (!(innermostTypeResolved instanceof Class)) {
/* 128 */         throw new IllegalArgumentException("Could not resolve generic array type " + type);
/*     */       }
/* 130 */       Class<?> innermostTypeResolvedClass = (Class)innermostTypeResolved;
/*     */ 
/*     */       
/* 133 */       int[] dims = (int[])Array.newInstance(int.class, numArrayDims);
/*     */ 
/*     */       
/* 136 */       Object arrayInstance = Array.newInstance(innermostTypeResolvedClass, dims);
/*     */ 
/*     */       
/* 139 */       return arrayInstance.getClass();
/*     */     } 
/* 141 */     if (type instanceof java.lang.reflect.WildcardType)
/*     */     {
/* 143 */       throw new RuntimeException("WildcardType not yet supported: " + type);
/*     */     }
/*     */     
/* 146 */     throw new RuntimeException("Got unexpected type: " + type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 155 */     if (this.typeVariables.length == 0) {
/* 156 */       return "{ }";
/*     */     }
/* 158 */     StringBuilder buf = new StringBuilder();
/* 159 */     buf.append("{ ");
/* 160 */     for (int i = 0; i < this.typeVariables.length; i++) {
/* 161 */       if (i > 0) {
/* 162 */         buf.append(", ");
/*     */       }
/* 164 */       buf.append(this.typeVariables[i]).append(" => ").append(this.resolvedTypeArguments[i]);
/*     */     } 
/* 166 */     buf.append(" }");
/* 167 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\TypeResolutions.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */