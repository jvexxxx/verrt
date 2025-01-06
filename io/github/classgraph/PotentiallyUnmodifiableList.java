/*     */ package io.github.classgraph;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.ListIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class PotentiallyUnmodifiableList<T>
/*     */   extends ArrayList<T>
/*     */ {
/*     */   static final long serialVersionUID = 1L;
/*     */   boolean modifiable = true;
/*     */   
/*     */   PotentiallyUnmodifiableList() {}
/*     */   
/*     */   PotentiallyUnmodifiableList(int sizeHint) {
/*  63 */     super(sizeHint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PotentiallyUnmodifiableList(Collection<T> collection) {
/*  73 */     super(collection);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  79 */     return super.equals(o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  85 */     return super.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   void makeUnmodifiable() {
/*  90 */     this.modifiable = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean add(T element) {
/*  95 */     if (!this.modifiable) {
/*  96 */       throw new IllegalArgumentException("List is immutable");
/*     */     }
/*  98 */     return super.add(element);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(int index, T element) {
/* 104 */     if (!this.modifiable) {
/* 105 */       throw new IllegalArgumentException("List is immutable");
/*     */     }
/* 107 */     super.add(index, element);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove(Object o) {
/* 113 */     if (!this.modifiable) {
/* 114 */       throw new IllegalArgumentException("List is immutable");
/*     */     }
/* 116 */     return super.remove(o);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public T remove(int index) {
/* 122 */     if (!this.modifiable) {
/* 123 */       throw new IllegalArgumentException("List is immutable");
/*     */     }
/* 125 */     return super.remove(index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAll(Collection<? extends T> c) {
/* 131 */     if (!this.modifiable && !c.isEmpty()) {
/* 132 */       throw new IllegalArgumentException("List is immutable");
/*     */     }
/* 134 */     return super.addAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addAll(int index, Collection<? extends T> c) {
/* 140 */     if (!this.modifiable && !c.isEmpty()) {
/* 141 */       throw new IllegalArgumentException("List is immutable");
/*     */     }
/* 143 */     return super.addAll(index, c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeAll(Collection<?> c) {
/* 149 */     if (!this.modifiable && !c.isEmpty()) {
/* 150 */       throw new IllegalArgumentException("List is immutable");
/*     */     }
/* 152 */     return super.removeAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean retainAll(Collection<?> c) {
/* 158 */     if (!this.modifiable && !isEmpty()) {
/* 159 */       throw new IllegalArgumentException("List is immutable");
/*     */     }
/* 161 */     return super.retainAll(c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 167 */     if (!this.modifiable && !isEmpty()) {
/* 168 */       throw new IllegalArgumentException("List is immutable");
/*     */     }
/* 170 */     super.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public T set(int index, T element) {
/* 176 */     if (!this.modifiable) {
/* 177 */       throw new IllegalArgumentException("List is immutable");
/*     */     }
/* 179 */     return super.set(index, element);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<T> iterator() {
/* 189 */     final Iterator<T> iterator = super.iterator();
/* 190 */     return new Iterator<T>()
/*     */       {
/*     */         public boolean hasNext() {
/* 193 */           if (PotentiallyUnmodifiableList.this.isEmpty()) {
/* 194 */             return false;
/*     */           }
/* 196 */           return iterator.hasNext();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public T next() {
/* 202 */           return iterator.next();
/*     */         }
/*     */ 
/*     */         
/*     */         public void remove() {
/* 207 */           if (!PotentiallyUnmodifiableList.this.modifiable) {
/* 208 */             throw new IllegalArgumentException("List is immutable");
/*     */           }
/* 210 */           iterator.remove();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator<T> listIterator() {
/* 218 */     final ListIterator<T> iterator = super.listIterator();
/* 219 */     return new ListIterator<T>()
/*     */       {
/*     */         public boolean hasNext() {
/* 222 */           if (PotentiallyUnmodifiableList.this.isEmpty()) {
/* 223 */             return false;
/*     */           }
/* 225 */           return iterator.hasNext();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public T next() {
/* 231 */           return iterator.next();
/*     */         }
/*     */ 
/*     */         
/*     */         public boolean hasPrevious() {
/* 236 */           if (PotentiallyUnmodifiableList.this.isEmpty()) {
/* 237 */             return false;
/*     */           }
/* 239 */           return iterator.hasPrevious();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public T previous() {
/* 245 */           return iterator.previous();
/*     */         }
/*     */ 
/*     */         
/*     */         public int nextIndex() {
/* 250 */           if (PotentiallyUnmodifiableList.this.isEmpty()) {
/* 251 */             return 0;
/*     */           }
/* 253 */           return iterator.nextIndex();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public int previousIndex() {
/* 259 */           if (PotentiallyUnmodifiableList.this.isEmpty()) {
/* 260 */             return -1;
/*     */           }
/* 262 */           return iterator.previousIndex();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void remove() {
/* 268 */           if (!PotentiallyUnmodifiableList.this.modifiable) {
/* 269 */             throw new IllegalArgumentException("List is immutable");
/*     */           }
/* 271 */           iterator.remove();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void set(T e) {
/* 277 */           if (!PotentiallyUnmodifiableList.this.modifiable) {
/* 278 */             throw new IllegalArgumentException("List is immutable");
/*     */           }
/* 280 */           iterator.set(e);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void add(T e) {
/* 286 */           if (!PotentiallyUnmodifiableList.this.modifiable) {
/* 287 */             throw new IllegalArgumentException("List is immutable");
/*     */           }
/* 289 */           iterator.add(e);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\io\github\classgraph\PotentiallyUnmodifiableList.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */