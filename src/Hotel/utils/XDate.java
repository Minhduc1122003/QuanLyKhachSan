/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Hotel.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author Phat Do
 */
public class XDate {

    static SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
    JLabel lblText;

    public XDate(JLabel lblText) {
        this.lblText = lblText;
    }

    /**
     * Chuyển đổi String sang Date date là String cần chuyển pattern là định
     * dạng thời gian return Date kết quả
     */
    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                formater.applyPattern(pattern[0]);
            }
            if (date == null) {
                return XDate.now2();
            }
            return formater.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Chuyển đổi từ Date sang String date là Date cần chuyển đổi pattern là
     * định dạng thời gian return String kết quả
     */
    public static String toString(Date date, String... pattern) {
        if (pattern.length > 0) {
            formater.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = XDate.now2();
        }
        return formater.format(date);
    }


    public static Date now2() {
        return new Date();
    }

    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 1000 * 60 * 60 * 24);
        return date;
    }

    public static Date add(int days) {
        Date now = XDate.now2();
        now.setTime(now.getTime() + days * 24 * 60 * 60 * 1000);
        return now;
    }
}
