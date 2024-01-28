import java.nio.ByteBuffer;
import java.util.Arrays;

public class DNSMessage {
  public short id = 1234;
  public short flags = (short)0b10000000_00000000;
  public short qdcount;

  public short ancount;
  public short nscount;
  public short arcount;

  public DNSMessage() {}
  public byte[] array() {
    ByteBuffer byteBuffer = ByteBuffer.allocate(12);
    byteBuffer.putShort(id);
    byteBuffer.putShort(flags);
    byteBuffer.putShort(qdcount);
    byteBuffer.putShort(ancount);
    byteBuffer.putShort(nscount);
    byteBuffer.putShort(arcount);
    return byteBuffer.array();
  }
}
