package com.jogger.beautifulapp.http;

import android.support.v4.util.LruCache;

/**
 * 内存缓存
 */

public class MemoryCacheUtil {
    private static LruCache<String, String> sLruCache;

    static {
        long maxMemory = Runtime.getRuntime().maxMemory();//获取最大内存
        //创建缓存对象，重写sizeOf方法，让单位一致
        //参数是内存缓存的空间，一般约定最大内存空间的1/8
        sLruCache = new LruCache<String, String>((int) (maxMemory / 8)) {
            //统一单位，总内存使用的单位是字节，sizeOf方法的返回值就必须是字节为单位
            //回调方法suzeOf：用来计算一次缓存数据的大小的

            /**
             * @param key   要缓存的数据的关键字
             * @param value 要缓存的数据
             * @return 此次缓存需要的空间大小
             */
            @Override
            protected int sizeOf(String key, String value) {
                return value.getBytes().length;
            }
        };
    }

    /**
     * 从内存中读数据
     *
     * @param url 地址
     * @return json数据
     */
    public static String getJsonStringFromMemory(String url) {
        return sLruCache.get(url);
    }

    /**
     * 将数据存到内存
     */
    public static void setJsonStringToMemory(String url, String jsonString) {
        sLruCache.put(url, jsonString);
    }

    public static void clearMemoryData(String url) {
        if (sLruCache != null)
            sLruCache.remove(url);
    }
}
