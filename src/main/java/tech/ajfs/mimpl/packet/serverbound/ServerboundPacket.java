package tech.ajfs.mimpl.packet.serverbound;

import java.io.DataOutputStream;
import java.io.IOException;
import tech.ajfs.mimpl.packet.Packet;

public abstract class ServerboundPacket implements Packet {

  public abstract void send(DataOutputStream out) throws IOException;

}
