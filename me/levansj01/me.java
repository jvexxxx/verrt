package me.levansj01;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface me {
  public static final boolean czb = false;
  
  public static final int cza;
  
  int priority() default 1;
  
  ig unsupportedServerAtleast() default ig.NONE;
  
  kb unsupportedAtleast() default kb.NONE;
  
  double minViolations() default 0.0D;
  
  int maxViolations() default 2147483647;
  
  String friendlyName() default "";
  
  ig[] unsupportedServers() default {};
  
  boolean logData() default false;
  
  String subType();
  
  boolean blocks() default false;
  
  boolean schem() default false;
  
  j type();
  
  boolean butterfly() default false;
  
  lk version() default lk.RELEASE;
  
  boolean heavy() default false;
  
  kb[] unsupportedVersions() default {};
}


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\me\levansj01\me.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */