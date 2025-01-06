/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.AbstractMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
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
/*     */ public class ResourceList
/*     */   extends PotentiallyUnmodifiableList<Resource>
/*     */   implements AutoCloseable
/*     */ {
/*     */   static final long serialVersionUID = 1L;
/*  53 */   static final ResourceList EMPTY_LIST = new ResourceList();
/*     */   static {
/*  55 */     EMPTY_LIST.makeUnmodifiable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ResourceList emptyList() {
/*  64 */     return EMPTY_LIST;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceList() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceList(int sizeHint) {
/*  81 */     super(sizeHint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceList(Collection<Resource> resourceCollection) {
/*  91 */     super(resourceCollection);
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
/*     */   public ResourceList get(String resourcePath) {
/* 107 */     boolean hasResourceWithPath = false;
/* 108 */     for (Resource res : this) {
/* 109 */       if (res.getPath().equals(resourcePath)) {
/* 110 */         hasResourceWithPath = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 114 */     if (!hasResourceWithPath) {
/* 115 */       return EMPTY_LIST;
/*     */     }
/* 117 */     ResourceList matchingResources = new ResourceList(2);
/* 118 */     for (Resource res : this) {
/* 119 */       if (res.getPath().equals(resourcePath)) {
/* 120 */         matchingResources.add(res);
/*     */       }
/*     */     } 
/* 123 */     return matchingResources;
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
/*     */   public List<String> getPaths() {
/* 136 */     List<String> resourcePaths = new ArrayList<>(size());
/* 137 */     for (Resource resource : this) {
/* 138 */       resourcePaths.add(resource.getPath());
/*     */     }
/* 140 */     return resourcePaths;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getPathsRelativeToClasspathElement() {
/* 150 */     List<String> resourcePaths = new ArrayList<>(size());
/* 151 */     for (Resource resource : this) {
/* 152 */       resourcePaths.add(resource.getPath());
/*     */     }
/* 154 */     return resourcePaths;
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
/*     */   public List<URL> getURLs() {
/* 166 */     List<URL> resourceURLs = new ArrayList<>(size());
/* 167 */     for (Resource resource : this) {
/* 168 */       resourceURLs.add(resource.getURL());
/*     */     }
/* 170 */     return resourceURLs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<URI> getURIs() {
/* 179 */     List<URI> resourceURLs = new ArrayList<>(size());
/* 180 */     for (Resource resource : this) {
/* 181 */       resourceURLs.add(resource.getURI());
/*     */     }
/* 183 */     return resourceURLs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 189 */   private static final ResourceFilter CLASSFILE_FILTER = new ResourceFilter()
/*     */     {
/*     */       public boolean accept(Resource resource) {
/* 192 */         String path = resource.getPath();
/* 193 */         if (!path.endsWith(".class") || path.length() < 7) {
/* 194 */           return false;
/*     */         }
/*     */         
/* 197 */         char c = path.charAt(path.length() - 7);
/* 198 */         return (c != '/' && c != '.');
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceList classFilesOnly() {
/* 208 */     return filter(CLASSFILE_FILTER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceList nonClassFilesOnly() {
/* 218 */     return filter(new ResourceFilter()
/*     */         {
/*     */           public boolean accept(Resource resource) {
/* 221 */             return !ResourceList.CLASSFILE_FILTER.accept(resource);
/*     */           }
/*     */         });
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
/*     */   public Map<String, ResourceList> asMap() {
/* 236 */     Map<String, ResourceList> pathToResourceList = new HashMap<>();
/* 237 */     for (Resource resource : this) {
/* 238 */       String path = resource.getPath();
/* 239 */       ResourceList resourceList = pathToResourceList.get(path);
/* 240 */       if (resourceList == null) {
/* 241 */         resourceList = new ResourceList(1);
/* 242 */         pathToResourceList.put(path, resourceList);
/*     */       } 
/* 244 */       resourceList.add(resource);
/*     */     } 
/* 246 */     return pathToResourceList;
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
/*     */   public List<Map.Entry<String, ResourceList>> findDuplicatePaths() {
/* 258 */     List<Map.Entry<String, ResourceList>> duplicatePaths = new ArrayList<>();
/* 259 */     for (Map.Entry<String, ResourceList> pathAndResourceList : asMap().entrySet()) {
/*     */       
/* 261 */       if (((ResourceList)pathAndResourceList.getValue()).size() > 1) {
/* 262 */         duplicatePaths.add(new AbstractMap.SimpleEntry<>(pathAndResourceList.getKey(), pathAndResourceList.getValue()));
/*     */       }
/*     */     } 
/* 265 */     CollectionUtils.sortIfNotEmpty(duplicatePaths, new Comparator<Map.Entry<String, ResourceList>>()
/*     */         {
/*     */           public int compare(Map.Entry<String, ResourceList> o1, Map.Entry<String, ResourceList> o2)
/*     */           {
/* 269 */             return ((String)o1.getKey()).compareTo(o2.getKey());
/*     */           }
/*     */         });
/* 272 */     return duplicatePaths;
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
/*     */   public ResourceList filter(ResourceFilter filter) {
/* 302 */     ResourceList resourcesFiltered = new ResourceList();
/* 303 */     for (Resource resource : this) {
/* 304 */       if (filter.accept(resource)) {
/* 305 */         resourcesFiltered.add(resource);
/*     */       }
/*     */     } 
/* 308 */     return resourcesFiltered;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void forEachByteArray(ByteArrayConsumer byteArrayConsumer, boolean ignoreIOExceptions) {
/* 365 */     for (Resource resource : this) { 
/* 366 */       try { Resource resourceToClose = resource; 
/* 367 */         try { byteArrayConsumer.accept(resourceToClose, resourceToClose.load());
/* 368 */           if (resourceToClose != null) resourceToClose.close();  } catch (Throwable throwable) { if (resourceToClose != null) try { resourceToClose.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/* 369 */       { if (!ignoreIOExceptions) {
/* 370 */           throw new IllegalArgumentException("Could not load resource " + resource, e);
/*     */         } }
/*     */        }
/*     */   
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
/*     */   @Deprecated
/*     */   public void forEachByteArray(ByteArrayConsumer byteArrayConsumer) {
/* 389 */     forEachByteArray(byteArrayConsumer, false);
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
/*     */   public void forEachByteArrayIgnoringIOException(ByteArrayConsumer byteArrayConsumer) {
/* 402 */     for (Resource resource : this) { 
/* 403 */       try { Resource resourceToClose = resource; 
/* 404 */         try { byteArrayConsumer.accept(resourceToClose, resourceToClose.load());
/* 405 */           if (resourceToClose != null) resourceToClose.close();  } catch (Throwable throwable) { if (resourceToClose != null) try { resourceToClose.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException iOException) {} }
/*     */   
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
/*     */   public void forEachByteArrayThrowingIOException(ByteArrayConsumerThrowsIOException byteArrayConsumerThrowsIOException) throws IOException {
/* 423 */     for (Resource resource : this) {
/* 424 */       Resource resourceToClose = resource; try {
/* 425 */         byteArrayConsumerThrowsIOException.accept(resourceToClose, resourceToClose.load());
/* 426 */         if (resourceToClose != null) resourceToClose.close();
/*     */       
/*     */       } catch (Throwable throwable) {
/*     */         if (resourceToClose != null) {
/*     */           try {
/*     */             resourceToClose.close();
/*     */           } catch (Throwable throwable1) {
/*     */             throwable.addSuppressed(throwable1);
/*     */           } 
/*     */         }
/*     */         throw throwable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void forEachInputStream(InputStreamConsumer inputStreamConsumer, boolean ignoreIOExceptions) {
/* 485 */     for (Resource resource : this) { 
/* 486 */       try { Resource resourceToClose = resource; 
/* 487 */         try { inputStreamConsumer.accept(resourceToClose, resourceToClose.open());
/* 488 */           if (resourceToClose != null) resourceToClose.close();  } catch (Throwable throwable) { if (resourceToClose != null) try { resourceToClose.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/* 489 */       { if (!ignoreIOExceptions) {
/* 490 */           throw new IllegalArgumentException("Could not load resource " + resource, e);
/*     */         } }
/*     */        }
/*     */   
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
/*     */   @Deprecated
/*     */   public void forEachInputStream(InputStreamConsumer inputStreamConsumer) {
/* 509 */     forEachInputStream(inputStreamConsumer, false);
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
/*     */   public void forEachInputStreamIgnoringIOException(InputStreamConsumer inputStreamConsumer) {
/* 522 */     for (Resource resource : this) { 
/* 523 */       try { Resource resourceToClose = resource; 
/* 524 */         try { inputStreamConsumer.accept(resourceToClose, resourceToClose.open());
/* 525 */           if (resourceToClose != null) resourceToClose.close();  } catch (Throwable throwable) { if (resourceToClose != null) try { resourceToClose.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException iOException) {} }
/*     */   
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
/*     */   public void forEachInputStreamThrowingIOException(InputStreamConsumerThrowsIOException inputStreamConsumerThrowsIOException) throws IOException {
/* 544 */     for (Resource resource : this) {
/* 545 */       Resource resourceToClose = resource; try {
/* 546 */         inputStreamConsumerThrowsIOException.accept(resourceToClose, resourceToClose.open());
/* 547 */         if (resourceToClose != null) resourceToClose.close();
/*     */       
/*     */       } catch (Throwable throwable) {
/*     */         if (resourceToClose != null) {
/*     */           try {
/*     */             resourceToClose.close();
/*     */           } catch (Throwable throwable1) {
/*     */             throwable.addSuppressed(throwable1);
/*     */           } 
/*     */         }
/*     */         throw throwable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void forEachByteBuffer(ByteBufferConsumer byteBufferConsumer, boolean ignoreIOExceptions) {
/* 604 */     for (Resource resource : this) { 
/* 605 */       try { Resource resourceToClose = resource; 
/* 606 */         try { byteBufferConsumer.accept(resourceToClose, resourceToClose.read());
/* 607 */           if (resourceToClose != null) resourceToClose.close();  } catch (Throwable throwable) { if (resourceToClose != null) try { resourceToClose.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/* 608 */       { if (!ignoreIOExceptions) {
/* 609 */           throw new IllegalArgumentException("Could not load resource " + resource, e);
/*     */         } }
/*     */        }
/*     */   
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
/*     */   @Deprecated
/*     */   public void forEachByteBuffer(ByteBufferConsumer byteBufferConsumer) {
/* 628 */     forEachByteBuffer(byteBufferConsumer, false);
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
/*     */   public void forEachByteBufferIgnoringIOException(ByteBufferConsumer byteBufferConsumer) {
/* 641 */     for (Resource resource : this) { 
/* 642 */       try { Resource resourceToClose = resource; 
/* 643 */         try { byteBufferConsumer.accept(resourceToClose, resourceToClose.read());
/* 644 */           if (resourceToClose != null) resourceToClose.close();  } catch (Throwable throwable) { if (resourceToClose != null) try { resourceToClose.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException iOException) {} }
/*     */   
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
/*     */   public void forEachByteBufferThrowingIOException(ByteBufferConsumerThrowsIOException byteBufferConsumerThrowsIOException) throws IOException {
/* 662 */     for (Resource resource : this) {
/* 663 */       Resource resourceToClose = resource; try {
/* 664 */         byteBufferConsumerThrowsIOException.accept(resourceToClose, resourceToClose.read());
/* 665 */         if (resourceToClose != null) resourceToClose.close(); 
/*     */       } catch (Throwable throwable) {
/*     */         if (resourceToClose != null)
/*     */           try {
/*     */             resourceToClose.close();
/*     */           } catch (Throwable throwable1) {
/*     */             throwable.addSuppressed(throwable1);
/*     */           }   throw throwable;
/*     */       } 
/* 674 */     }  } public void close() { for (Resource resource : this)
/* 675 */       resource.close();  }
/*     */ 
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ResourceFilter {
/*     */     boolean accept(Resource param1Resource);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ByteArrayConsumer {
/*     */     void accept(Resource param1Resource, byte[] param1ArrayOfbyte);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ByteArrayConsumerThrowsIOException {
/*     */     void accept(Resource param1Resource, byte[] param1ArrayOfbyte) throws IOException;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface InputStreamConsumer {
/*     */     void accept(Resource param1Resource, InputStream param1InputStream);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface InputStreamConsumerThrowsIOException {
/*     */     void accept(Resource param1Resource, InputStream param1InputStream) throws IOException;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ByteBufferConsumer {
/*     */     void accept(Resource param1Resource, ByteBuffer param1ByteBuffer);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ByteBufferConsumerThrowsIOException {
/*     */     void accept(Resource param1Resource, ByteBuffer param1ByteBuffer) throws IOException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\ResourceList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */