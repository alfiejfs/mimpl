package tech.ajfs.mimpl.packet.serverbound;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import tech.ajfs.mimpl.connection.Protocol;

@RequiredArgsConstructor
public class ServerboundHandshakePacket extends ServerboundPacket {

  private final int protocolVersion;
  private final String host;
  private final short port;
  private final int state;


  @Override
  public byte getId() {
    return 0x00;
  }

  @Override
  public void send(DataOutputStream out) throws IOException {
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    DataOutputStream dataOut = new DataOutputStream(byteOut);

    dataOut.write(getId());
    Protocol.writeVarInt(this.protocolVersion, dataOut);
    Protocol.writeVarInt(this.host.length(), dataOut);
    dataOut.writeBytes(this.host);
    dataOut.writeShort(this.port);
    Protocol.writeVarInt(this.state, dataOut);

    Protocol.writeVarInt(dataOut.size(), out);
    out.write(byteOut.toByteArray());
    out.flush();
  }

  public static class ServerboundHandshakeRequestPacket extends ServerboundPacket {

    @Override
    public byte getId() {
      return 0x00;
    }

    @Override
    public void send(DataOutputStream out) throws IOException {
      out.writeByte(0x01);
      out.writeByte(getId());
      out.flush();
    }
  }

}
