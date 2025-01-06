package nonapi.io.github.classgraph.fileslice.reader;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface RandomAccessReader {
  int read(long paramLong, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2) throws IOException;
  
  int read(long paramLong, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  byte readByte(long paramLong) throws IOException;
  
  int readUnsignedByte(long paramLong) throws IOException;
  
  short readShort(long paramLong) throws IOException;
  
  int readUnsignedShort(long paramLong) throws IOException;
  
  int readInt(long paramLong) throws IOException;
  
  long readUnsignedInt(long paramLong) throws IOException;
  
  long readLong(long paramLong) throws IOException;
  
  String readString(long paramLong, int paramInt, boolean paramBoolean1, boolean paramBoolean2) throws IOException;
  
  String readString(long paramLong, int paramInt) throws IOException;
}


/* Location:              C:\Users\Administrator\Desktop\jvg\sv spigot\pl\verus_b4157.jar!\nonapi\io\github\classgraph\fileslice\reader\RandomAccessReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */