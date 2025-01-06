/*     */ package nonapi.io.github.classgraph.json;
/*     */ 
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ParameterizedTypeImpl
/*     */   implements ParameterizedType
/*     */ {
/*     */   private final Type[] actualTypeArguments;
/*     */   private final Class<?> rawType;
/*     */   private final Type ownerType;
/*  51 */   public static final Type MAP_OF_UNKNOWN_TYPE = new ParameterizedTypeImpl(Map.class, new Type[] { Object.class, Object.class }, null);
/*     */ 
/*     */ 
/*     */   
/*  55 */   public static final Type LIST_OF_UNKNOWN_TYPE = new ParameterizedTypeImpl(List.class, new Type[] { Object.class }, null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ParameterizedTypeImpl(Class<?> rawType, Type[] actualTypeArguments, Type ownerType) {
/*  69 */     this.actualTypeArguments = actualTypeArguments;
/*  70 */     this.rawType = rawType;
/*  71 */     this.ownerType = (ownerType != null) ? ownerType : rawType.getDeclaringClass();
/*  72 */     if ((rawType.getTypeParameters()).length != actualTypeArguments.length) {
/*  73 */       throw new IllegalArgumentException("Argument length mismatch");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type[] getActualTypeArguments() {
/*  82 */     return (Type[])this.actualTypeArguments.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class<?> getRawType() {
/*  90 */     return this.rawType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getOwnerType() {
/*  98 */     return this.ownerType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 106 */     if (obj == this)
/* 107 */       return true; 
/* 108 */     if (!(obj instanceof ParameterizedType)) {
/* 109 */       return false;
/*     */     }
/* 111 */     ParameterizedType other = (ParameterizedType)obj;
/* 112 */     return (Objects.equals(this.ownerType, other.getOwnerType()) && Objects.equals(this.rawType, other.getRawType()) && 
/* 113 */       Arrays.equals((Object[])this.actualTypeArguments, (Object[])other.getActualTypeArguments()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 121 */     return Arrays.hashCode((Object[])this.actualTypeArguments) ^ Objects.hashCode(this.ownerType) ^ Objects.hashCode(this.rawType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 129 */     StringBuilder buf = new StringBuilder();
/* 130 */     if (this.ownerType == null) {
/* 131 */       buf.append(this.rawType.getName());
/*     */     } else {
/* 133 */       if (this.ownerType instanceof Class) {
/* 134 */         buf.append(((Class)this.ownerType).getName());
/*     */       } else {
/* 136 */         buf.append(this.ownerType);
/*     */       } 
/* 138 */       buf.append('$');
/* 139 */       if (this.ownerType instanceof ParameterizedTypeImpl) {
/*     */         
/* 141 */         String simpleName = this.rawType.getName().replace(((ParameterizedTypeImpl)this.ownerType).rawType.getName() + "$", "");
/* 142 */         buf.append(simpleName);
/*     */       } else {
/* 144 */         buf.append(this.rawType.getSimpleName());
/*     */       } 
/*     */     } 
/* 147 */     if (this.actualTypeArguments != null && this.actualTypeArguments.length > 0) {
/* 148 */       buf.append('<');
/* 149 */       boolean first = true;
/* 150 */       for (Type t : this.actualTypeArguments) {
/* 151 */         if (first) {
/* 152 */           first = false;
/*     */         } else {
/* 154 */           buf.append(", ");
/*     */         } 
/* 156 */         buf.append(t.toString());
/*     */       } 
/* 158 */       buf.append('>');
/*     */     } 
/* 160 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\json\ParameterizedTypeImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */