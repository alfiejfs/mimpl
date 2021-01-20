package tech.ajfs.mimpl.packet.clientbound;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import tech.ajfs.mimpl.packet.Packet;

public abstract class ClientboundPacket implements Packet {

  public abstract void read(DataInputStream in) throws IOException;

}
