package com.memo.utils;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    public static boolean isEmpty(String label) {
        return label == null || "".equals(label.replaceAll("\\s*", ""));
    }

    public static Date getNow() {
        return Calendar.getInstance(TimeZone.getTimeZone(Const.TIME_ZONE_ID),
                Const.LOCALE).getTime();
    }

    public static String cript(String key) throws NullPointerException {
        if (Util.isEmpty(key)) {
            throw new NullPointerException("Key must not be null!");
        }
        String cripted = null;
        try {
            byte[] defaultBytes = key.getBytes("UTF-8");
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            cripted = hexString + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cripted;
    }

}
