
package volcano.Java.base;

public class rg_ZiJieShuZuCaoZuo {

    public rg_ZiJieShuZuCaoZuo ()  { }

    static byte[] byteToArray(java.util.List<Byte> byteList){
        if(byteList != null && byteList.size() > 0){
            byte[] bytes = new byte[byteList.size()];
            for (int i = 0; i < byteList.size(); i++) {
                bytes[i] = byteList.get(i);
            }
            return bytes;
        }else{
            return null;
        }
    }
    static java.util.List<byte[]> byteSplit(byte[] array, byte[] split){
        java.util.List<byte[]> newArray = new java.util.ArrayList<byte[]>();
        java.util.List<Byte> newByte = new java.util.ArrayList<Byte>();
        boolean bol;
        for (int i = 0; i < array.length; i++) {
            bol = false;
            if(i < array.length + 1 - split.length){
                bol = true;
                for (int k = 0; k < split.length; k++) {
                    if(array[i + k] != split[k]){
                        bol = false;
                        break;
                    }
                }
            }
            if(bol){
                if(newByte.size() > 0){
                    newArray.add(byteToArray(newByte));
                }
                newByte = new java.util.ArrayList<Byte>();
                i += split.length - 1;
            }else{
                newByte.add(array[i]);
            }
        }
        if(newByte.size() > 0){
            newArray.add(byteToArray(newByte));
        }
        return newArray;
    }

    public static byte [] rg_GeBing9 (byte [] rg_DaiGeBingShuZu5, byte [] rg_DaiGeBingShuZu6) {
        byte[] aryResult = new byte [rg_DaiGeBingShuZu5.length + rg_DaiGeBingShuZu6.length];
        System.arraycopy (rg_DaiGeBingShuZu5, 0, aryResult, 0, rg_DaiGeBingShuZu5.length);
        System.arraycopy (rg_DaiGeBingShuZu6, 0, aryResult, rg_DaiGeBingShuZu5.length, rg_DaiGeBingShuZu6.length);
        return aryResult;
    }

    public static byte [] rg_QuShuZuZuoBian1 (byte [] rg_DaiChuLiShuZu5, int rg_YuHuoQuChengYuanShu3) {
        byte[] aryResult = new byte [rg_YuHuoQuChengYuanShu3];
        System.arraycopy (rg_DaiChuLiShuZu5, 0, aryResult, 0, rg_YuHuoQuChengYuanShu3);
        return aryResult;
    }
}
