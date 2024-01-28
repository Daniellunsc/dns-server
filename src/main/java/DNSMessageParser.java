import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;

public class DNSMessageParser {
  public DNSMessage parsePacket(DatagramPacket packet) throws IOException {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packet.getData());
    DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
    short id = dataInputStream.readShort();
    short flags = dataInputStream.readShort();
    short qdcount = dataInputStream.readShort();
    /*short ancount = */dataInputStream.readShort();
    short nscount = dataInputStream.readShort();
    short arcount = dataInputStream.readShort();

    char[] requestFlags = String.format("%16s", Integer.toBinaryString(flags)).replace(' ', '0').toCharArray();
    requestFlags[0] = '1';  // QR
    requestFlags[13] = '1'; // RCODE
    flags = (short)Integer.parseInt(new String(requestFlags), 2);

    return new DNSMessage(id, flags, qdcount, (short) 1, nscount, arcount);
  }
}
