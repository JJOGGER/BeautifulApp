package com.jogger.beautifulapp.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jogger on 2017/9/19.
 */

public abstract class AbstractMigratorHelper {
    public abstract void onUpgrade(SQLiteDatabase db);
}
