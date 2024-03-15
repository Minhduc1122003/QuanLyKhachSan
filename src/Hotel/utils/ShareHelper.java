package Hotel.utils;

import Hotel.entity.NhanVien;
import Hotel.ui.FormDangNhap;

public class ShareHelper {

    public static NhanVien USER = null;

    /**
     *
     */
    public static int indentity = 0;

    public static void logoff() {
        ShareHelper.USER = null;
    }

    public static boolean auth() {
        return ShareHelper.USER != null;
    }
    public static boolean authenticated() {
        return ShareHelper.auth() && USER.isChucVu();
    }
    public static boolean authenticatedNV() {
        return ShareHelper.auth() && !USER.isChucVu();
    }
    public static int indentity() {
        return ShareHelper.indentity();
    }

}
