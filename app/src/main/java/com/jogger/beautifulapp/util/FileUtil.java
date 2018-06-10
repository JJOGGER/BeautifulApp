package com.jogger.beautifulapp.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Jogger on 2018/6/10.
 */

public class FileUtil {
    private static final String MEMORY_CACHE = "memory_cache";

    public static String getCacheDir() {
//        Util.getApp().getDir(MEMORY_CACHE, Context.MODE_PRIVATE).getAbsolutePath()+File
// .separator+"cache"+FILE
//        return Util.getApp().getRootPath() + "cache/";
        return Util.getApp().getCacheDir().getAbsolutePath();
    }

    public static String readTextFromSDcard(String fileName) {

        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            int availableLength = fileInputStream.available();
            byte[] buffer = new byte[availableLength];
            fileInputStream.read(buffer);
            fileInputStream.close();

            return new String(buffer, "UTF-8");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean saveText2Sdcard(String fileName, String text) {

        File file = new File(fileName);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(text);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            file.delete();
            return false;
        }
        return true;
    }
}
