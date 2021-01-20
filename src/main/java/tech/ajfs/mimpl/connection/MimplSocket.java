package tech.ajfs.mimpl.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MimplSocket {

  public static Socket newSocket(String host, int port) {
    try {
      return new Socket(host, port);
    } catch (IOException err) {
      err.printStackTrace();
    }
    return null;
  }

}
