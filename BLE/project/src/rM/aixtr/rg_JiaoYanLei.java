
package rM.aixtr;

public class rg_JiaoYanLei {

    public rg_JiaoYanLei ()  { }

    public static int rg_cal_crc (int rg_value) {
               int crc = rg_value;
               int poly = 0x07;
               for (int i = 8; i > 0; i--) {
                   if ((crc & 0x80) >> 7 == 1) {
                       crc = (crc << 1) ^ poly;
                   } else {
                       crc = (crc << 1);
                   }
               }
               return crc & 0xFF;
    }

    public static int rg_crc (byte [] rg_data) {
                int crc = 0x00;
                int length = rg_data.length;
                for (int i = 0; i < length; i++) {
                    if (i == 0) {
                        crc = rg_JiaoYanLei.rg_cal_crc(rg_data[0] & 0xFF);
                    } else {
                        crc = (crc ^ (rg_data[i] & 0xFF)) & 0xFF;
                        crc = rg_JiaoYanLei.rg_cal_crc(crc);
                    }
                }
                return crc & 0xFF;
    }

    public static byte [] rg_hexStringToByteArray (String rg_hexString) {
          int len = rg_hexString.length();
                byte[] data = new byte[len / 2];
                for (int i = 0; i < len; i += 2) {
                    data[i / 2] = (byte) ((Character.digit(rg_hexString.charAt(i), 16) << 4)
                            + Character.digit(rg_hexString.charAt(i+1), 16));
                }
                return data;
    }

    public static String [] rg_FenGe1 (String rg_FenGeWenBen) {
                int length = rg_FenGeWenBen.length();
                int numChunks = (int) Math.ceil((double) length / 18);
                String[] chunks = new String[numChunks];
                for (int i = 0; i < numChunks; i++) {
                    int startIndex = i * 18;
                    int endIndex = Math.min(startIndex + 18, length);
                    chunks[i] = rg_FenGeWenBen.substring(startIndex, endIndex);
                }
                return chunks;
    }
}
