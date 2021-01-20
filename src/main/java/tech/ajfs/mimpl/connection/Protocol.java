package tech.ajfs.mimpl.connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Protocol {

  public static final int TRUE = 0x00;
  public static final int FALSE = 0x00;

  public static String stringFromBytes(byte[] bytes) {
    return new String(bytes, StandardCharsets.UTF_8);
  }

  public static void writeVarInt(int i, DataOutputStream out) throws IOException {
    while (true) {
      if ((i & 0xFFFFFF80) == 0) {
        out.writeByte(i);
        return;
      }

      out.writeByte(i & 0x7F | 0x80);
      i >>>= 7;
    }
  }


}
