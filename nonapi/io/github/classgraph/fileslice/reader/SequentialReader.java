package nonapi.io.github.classgraph.fileslice.reader;

import java.io.IOException;

public interface SequentialReader {
  byte readByte() throws IOException;
  
  int readUnsignedByte() throws IOException;
  
  short readShort() throws IOException;
  
  int readUnsignedShort() throws IOException;
  
  int readInt() throws IOException;
  
  long readUnsignedInt() throws IOException;
  
  long readLong() throws IOException;
  
  void skip(int paramInt) throws IOException;
  
  String readString(int paramInt, boolean paramBoolean1, boolean paramBoolean2) throws IOException;
  
  String readString(int paramInt) throws IOException;
}


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fileslice\reader\SequentialReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */