/*    */ package io.github.classgraph;
/*    */ 
/*    */ import nonapi.io.github.classgraph.types.ParseException;
/*    */ import nonapi.io.github.classgraph.types.Parser;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ReferenceTypeSignature
/*    */   extends TypeSignature
/*    */ {
/*    */   static ReferenceTypeSignature parseReferenceTypeSignature(Parser parser, String definingClassName) throws ParseException {
/* 57 */     ClassRefTypeSignature classTypeSignature = ClassRefTypeSignature.parse(parser, definingClassName);
/* 58 */     if (classTypeSignature != null) {
/* 59 */       return classTypeSignature;
/*    */     }
/* 61 */     TypeVariableSignature typeVariableSignature = TypeVariableSignature.parse(parser, definingClassName);
/* 62 */     if (typeVariableSignature != null) {
/* 63 */       return typeVariableSignature;
/*    */     }
/* 65 */     ArrayTypeSignature arrayTypeSignature = ArrayTypeSignature.parse(parser, definingClassName);
/* 66 */     if (arrayTypeSignature != null) {
/* 67 */       return arrayTypeSignature;
/*    */     }
/* 69 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static ReferenceTypeSignature parseClassBound(Parser parser, String definingClassName) throws ParseException {
/* 85 */     parser.expect(':');
/*    */     
/* 87 */     return parseReferenceTypeSignature(parser, definingClassName);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ReferenceTypeSignature.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */