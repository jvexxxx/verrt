/*     */ package nonapi.io.github.classgraph.utils;
/*     */ 
/*     */ import io.github.classgraph.ClassGraph;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URL;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Properties;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.xpath.XPathConstants;
/*     */ import javax.xml.xpath.XPathFactory;
/*     */ import javax.xml.xpath.XPathFactoryConfigurationException;
/*     */ import org.w3c.dom.Document;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class VersionFinder
/*     */ {
/*     */   private static final String MAVEN_PACKAGE = "io.github.classgraph";
/*     */   private static final String MAVEN_ARTIFACT = "classgraph";
/*     */   public static final OperatingSystem OS;
/*  67 */   public static final String JAVA_VERSION = getProperty("java.version");
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int JAVA_MAJOR_VERSION;
/*     */ 
/*     */   
/*     */   public static final int JAVA_MINOR_VERSION;
/*     */ 
/*     */   
/*     */   public static final int JAVA_SUB_VERSION;
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  82 */     int javaMajorVersion = 0;
/*  83 */     int javaMinorVersion = 0;
/*  84 */     int javaSubVersion = 0;
/*  85 */     List<Integer> versionParts = new ArrayList<>();
/*  86 */     if (JAVA_VERSION != null) {
/*  87 */       for (String versionPart : JAVA_VERSION.split("[^0-9]+")) {
/*     */         try {
/*  89 */           versionParts.add(Integer.valueOf(Integer.parseInt(versionPart)));
/*  90 */         } catch (NumberFormatException numberFormatException) {}
/*     */       } 
/*     */ 
/*     */       
/*  94 */       if (!versionParts.isEmpty() && ((Integer)versionParts.get(0)).intValue() == 1)
/*     */       {
/*  96 */         versionParts.remove(0);
/*     */       }
/*  98 */       if (versionParts.isEmpty()) {
/*  99 */         throw new RuntimeException("Could not determine Java version: " + JAVA_VERSION);
/*     */       }
/* 101 */       javaMajorVersion = ((Integer)versionParts.get(0)).intValue();
/* 102 */       if (versionParts.size() > 1) {
/* 103 */         javaMinorVersion = ((Integer)versionParts.get(1)).intValue();
/*     */       }
/* 105 */       if (versionParts.size() > 2) {
/* 106 */         javaSubVersion = ((Integer)versionParts.get(2)).intValue();
/*     */       }
/*     */     } 
/* 109 */     JAVA_MAJOR_VERSION = javaMajorVersion;
/* 110 */     JAVA_MINOR_VERSION = javaMinorVersion;
/* 111 */     JAVA_SUB_VERSION = javaSubVersion;
/* 112 */   } public static final boolean JAVA_IS_EA_VERSION = (JAVA_VERSION != null && JAVA_VERSION.endsWith("-ea"));
/*     */ 
/*     */ 
/*     */   
/*     */   public enum OperatingSystem
/*     */   {
/* 118 */     Windows,
/*     */ 
/*     */     
/* 121 */     MacOSX,
/*     */ 
/*     */     
/* 124 */     Linux,
/*     */ 
/*     */     
/* 127 */     Solaris,
/*     */ 
/*     */     
/* 130 */     BSD,
/*     */ 
/*     */     
/* 133 */     Unix,
/*     */ 
/*     */     
/* 136 */     Unknown;
/*     */   }
/*     */   
/*     */   static {
/* 140 */     String osName = getProperty("os.name", "unknown").toLowerCase(Locale.ENGLISH);
/* 141 */     if (File.separatorChar == '\\') {
/* 142 */       OS = OperatingSystem.Windows;
/* 143 */     } else if (osName == null) {
/* 144 */       OS = OperatingSystem.Unknown;
/* 145 */     } else if (osName.contains("win")) {
/* 146 */       OS = OperatingSystem.Windows;
/* 147 */     } else if (osName.contains("mac") || osName.contains("darwin")) {
/* 148 */       OS = OperatingSystem.MacOSX;
/* 149 */     } else if (osName.contains("nux")) {
/* 150 */       OS = OperatingSystem.Linux;
/* 151 */     } else if (osName.contains("sunos") || osName.contains("solaris")) {
/* 152 */       OS = OperatingSystem.Solaris;
/* 153 */     } else if (osName.contains("bsd")) {
/* 154 */       OS = OperatingSystem.Unix;
/* 155 */     } else if (osName.contains("nix") || osName.contains("aix")) {
/* 156 */       OS = OperatingSystem.Unix;
/*     */     } else {
/* 158 */       OS = OperatingSystem.Unknown;
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
/*     */   public static String getProperty(String propName) {
/*     */     try {
/* 182 */       return System.getProperty(propName);
/* 183 */     } catch (SecurityException e) {
/* 184 */       return null;
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
/*     */   public static String getProperty(String propName, String defaultVal) {
/*     */     try {
/* 199 */       return System.getProperty(propName, defaultVal);
/* 200 */     } catch (SecurityException e) {
/* 201 */       return null;
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
/*     */   public static synchronized String getVersion() {
/* 214 */     Class<?> cls = ClassGraph.class;
/*     */     try {
/* 216 */       String className = cls.getName();
/* 217 */       URL classpathResource = cls.getResource("/" + JarUtils.classNameToClassfilePath(className));
/* 218 */       if (classpathResource != null) {
/* 219 */         Path absolutePackagePath = Paths.get(classpathResource.toURI()).getParent();
/* 220 */         int packagePathSegments = className.length() - className.replace(".", "").length();
/*     */         
/* 222 */         Path path = absolutePackagePath; int i;
/* 223 */         for (i = 0; i < packagePathSegments && path != null; i++) {
/* 224 */           path = path.getParent();
/*     */         }
/*     */         
/* 227 */         for (i = 0; i < 3 && path != null; i++, path = path.getParent()) {
/* 228 */           Path pom = path.resolve("pom.xml"); 
/* 229 */           try { InputStream is = Files.newInputStream(pom, new java.nio.file.OpenOption[0]); 
/* 230 */             try { Document doc = getSecureDocumentBuilderFactory().newDocumentBuilder().parse(is);
/* 231 */               doc.getDocumentElement().normalize();
/*     */               
/* 233 */               String version = (String)getSecureXPathFactory().newXPath().compile("/project/version").evaluate(doc, XPathConstants.STRING);
/* 234 */               if (version != null)
/* 235 */               { version = version.trim();
/* 236 */                 if (!version.isEmpty())
/* 237 */                 { String str = version;
/*     */ 
/*     */                   
/* 240 */                   if (is != null) is.close();  return str; }  }  if (is != null) is.close();  } catch (Throwable throwable) { if (is != null) try { is.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException iOException) {}
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 245 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 250 */     try { InputStream is = cls.getResourceAsStream("/META-INF/maven/io.github.classgraph/classgraph/pom.properties");
/*     */       
/* 252 */       try { if (is != null)
/* 253 */         { Properties p = new Properties();
/* 254 */           p.load(is);
/* 255 */           String version = p.getProperty("version", "").trim();
/* 256 */           if (!version.isEmpty())
/* 257 */           { String str = version;
/*     */ 
/*     */             
/* 260 */             if (is != null) is.close();  return str; }  }  if (is != null) is.close();  } catch (Throwable throwable) { if (is != null) try { is.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     Package pkg = cls.getPackage();
/* 266 */     if (pkg != null) {
/* 267 */       String version = pkg.getImplementationVersion();
/* 268 */       if (version == null) {
/* 269 */         version = "";
/*     */       }
/* 271 */       version = version.trim();
/* 272 */       if (version.isEmpty()) {
/* 273 */         version = pkg.getSpecificationVersion();
/* 274 */         if (version == null) {
/* 275 */           version = "";
/*     */         }
/* 277 */         version = version.trim();
/*     */       } 
/* 279 */       if (!version.isEmpty()) {
/* 280 */         return version;
/*     */       }
/*     */     } 
/* 283 */     return "unknown";
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
/*     */   private static DocumentBuilderFactory getSecureDocumentBuilderFactory() throws ParserConfigurationException {
/* 297 */     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
/* 298 */     dbf.setXIncludeAware(false);
/* 299 */     dbf.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
/* 300 */     dbf.setAttribute("http://javax.xml.XMLConstants/property/accessExternalDTD", "");
/* 301 */     dbf.setAttribute("http://javax.xml.XMLConstants/property/accessExternalSchema", "");
/* 302 */     dbf.setExpandEntityReferences(false);
/* 303 */     dbf.setNamespaceAware(true);
/* 304 */     dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
/* 305 */     dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
/* 306 */     dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
/* 307 */     dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
/* 308 */     return dbf;
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
/*     */   private static XPathFactory getSecureXPathFactory() throws XPathFactoryConfigurationException {
/* 320 */     XPathFactory xPathFactory = XPathFactory.newInstance();
/* 321 */     xPathFactory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
/* 322 */     return xPathFactory;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgrap\\utils\VersionFinder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */