package tech.ajfs.mimpl.packet.clientbound;

import java.io.DataInputStream;
import java.io.IOException;
import lombok.Getter;
import tech.ajfs.mimpl.connection.Protocol;

public class ClientboundHandshakePacket extends ClientboundPacket {

  @Getter
  private String response;

  @Override
  public byte getId() {
    return 0x00;
  }

  @Override
  public void read(DataInputStream in) throws IOException {
    byte[] read = new byte[1024];
    in.read(read);
    this.response = Protocol.stringFromBytes(read);
  }
}
