
package volcano.Java.base;

public class rg_XiaoShuLei {

    public static boolean rg_XiaoShuJinShiWei (double rg_YuJianChaXiaoShuZhi5) {
        return ((rg_YuJianChaXiaoShuZhi5 > 0.0000001) == false && (rg_YuJianChaXiaoShuZhi5 < -0.0000001) == false);
    }

    public static boolean rg_XiaoShuJinShiXiangDeng (double rg_YuJianChaXiaoShuZhi8, double rg_YuJianChaXiaoShuZhi9) {
        return (rg_XiaoShuJinShiWei (rg_YuJianChaXiaoShuZhi8 - rg_YuJianChaXiaoShuZhi9));
    }
}
