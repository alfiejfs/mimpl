package tech.ajfs.mimpl.test;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.junit.Test;
import tech.ajfs.mimpl.connection.MimplSocket;
import tech.ajfs.mimpl.connection.ProtocolVersion;
import tech.ajfs.mimpl.packet.clientbound.ClientboundHandshakePacket;
import tech.ajfs.mimpl.packet.serverbound.ServerboundHandshakePacket;
import tech.ajfs.mimpl.packet.serverbound.ServerboundHandshakePacket.ServerboundHandshakeRequestPacket;

import static org.junit.Assert.assertEquals;

public class ServerListTest {

  @Test
  public void paperOneSixteenFourTest() throws IOException {
    String host = "localhost";
    short port = 25565;
    int state = 1;

    Socket socket = MimplSocket.newSocket(host, port);
    socket.setSoTimeout(5);

    String stringResponse;
    try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        DataInputStream in = new DataInputStream(socket.getInputStream())) {
      ServerboundHandshakePacket handshake = new ServerboundHandshakePacket(
          ProtocolVersion.LATEST,
          host,
          port,
          state
      );
      handshake.send(out);

      ServerboundHandshakeRequestPacket request = new ServerboundHandshakeRequestPacket();
      request.send(out);

      ClientboundHandshakePacket response = new ClientboundHandshakePacket();

      response.read(in);
      stringResponse = response.getResponse();
    }

    stringResponse = stringResponse.substring(5).replace("\0", "");

    assertEquals("{\"description\":{\"extra\":[{\"text\":\"A Minecraft Server\"}],\"text\":\"\"},"
        + "\"players\":{\"max\":20,\"online\":0},\"version\":{\"name\":\"Paper 1.16.4\","
        + "\"protocol\":754}}", stringResponse);
  }

}
