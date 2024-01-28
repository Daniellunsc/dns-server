import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class DNSMessage {
  public short id = 1234;
  public short flags = (short)0b10000000_00000000;
  public short qdcount = 1;

  public short ancount = 1;
  public short nscount;
  public short arcount;

  public DNSMessage() {}
  private ByteBuffer writeHeader(ByteBuffer buffer) {
    buffer.putShort(id);
    buffer.putShort(flags);
    buffer.putShort(qdcount);
    buffer.putShort(ancount);
    buffer.putShort(nscount);
    buffer.putShort(arcount);
    return buffer;
  }

  private ByteBuffer writeQuestion(ByteBuffer buffer) {
    buffer.put(encodeDomainName("codecrafters.io"));
    buffer.putShort((short)1);
    buffer.putShort((short)1);
    return buffer;
  }

  private ByteBuffer writeAnswer(ByteBuffer buffer) {
    buffer.put(encodeDomainName("codecrafters.io"));
    buffer.putShort((short)1);
    buffer.putShort((short)1);
    buffer.putInt(300);
    buffer.putShort((short)4);
    buffer.put(new byte[] { 127, 0, 0, 1 });
    return buffer;
  }

  private byte[] encodeDomainName(String domain) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();

    for (String label : domain.split("\\.")) {
      out.write(label.length());
      out.writeBytes(label.getBytes());
    }

    out.write(0);

    return out.toByteArray();
  }
  public byte[] array() {
    ByteBuffer byteBuffer = ByteBuffer.allocate(512);
    writeHeader(byteBuffer);
    writeQuestion(byteBuffer);
    writeAnswer(byteBuffer);
    return byteBuffer.array();
  }
}
