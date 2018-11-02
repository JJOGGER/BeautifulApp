package com.jogger.beautifulapp.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jogger on 2017/9/19.更新数据库逻辑的类
 */

public class MyDBMigrationHelper extends AbstractMigratorHelper {
    @Override
    public void onUpgrade(SQLiteDatabase db) {
         /* Create a temporal table where you will copy all the data from the previous table that you need to modify with a non supported sqlite operation */
        db.execSQL("CREATE TABLE " + "'GWELL_ALARM_INFO' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'SRC_ID' TEXT," + // 1: postId
                "'TYPE' INTEGER," + // 2: userId
                "'OPTION' INTEGER," + // 3: version
                "'ITEM' INTEGER," + // 4: type
                "'IMAGE_COUNTS' INTEGER," + // 5: magazineId
                "'IMAGE_PATH' TEXT," + // 6: serverTimestamp
                "'ALARM_CAP_DIR' TEXT," + // 7: clientTimestamp
                "'VIDEO_PATH' TEXT," + // 8: magazineReference
                "'SENSOR_NAME' TEXT," + // 8: magazineReference
                "'DEVICE_TYPE' INTEGER," + // 8: magazineReference
                "'ALARM_TIME' LONG);"); // 9: postContent

    /* 复制原表数据到新表 */
//        db.execSQL("INSERT INTO GWELL_ALARM_INFO2 (_id, SRC_ID, TYPE, OPTION, ITEM, IMAGE_COUNTS, IMAGE_PATH, ALARM_CAP_DIR, VIDEO_PATH,SENSOR_NAME,DEVICE_TYPE, ALARM_TIME)" +
//                "   SELECT _id, SRC_ID, TYPE, OPTION, ITEM, IMAGE_COUNTS, IMAGE_PATH, ALARM_CAP_DIR, VIDEO_PATH, SENSOR_NAME,DEVICE_TYPE,ALARM_TIME FROM GWELL_ALARM_INFO;");

    /* 删除原表 */
//        db.execSQL("DROP TABLE GWELL_ALARM_INFO");
    /* 将新表重命名为旧表名 */
//        db.execSQL("ALTER TABLE GWELL_ALARM_INFO RENAME TO GWELL_ALARM_INFO");

    /* 添加索引 */
//        db.execSQL("CREATE INDEX " + "IDX_post_USER_ID ON post" +
//                " (USER_ID);");
        //Example sql statement
//        db.execSQL("ALTER TABLE user ADD COLUMN USERNAME TEXT");
    }
}
