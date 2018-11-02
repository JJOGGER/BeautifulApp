package com.jogger.beautifulapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jogger.beautifulapp.entity.DaoMaster;
import com.jogger.beautifulapp.entity.DaoSession;
import com.jogger.beautifulapp.entity.DownloadInfo;
import com.jogger.beautifulapp.entity.DownloadInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by jogger on 2017/7/21.
 */

public class DBManager {

    // 是否加密
    public static final boolean ENCRYPTED = true;

    private static final String DB_NAME = "nice_db.db";
    private static DBManager mDBManager;
    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private static Context sContext;

    private DBManager() {
        // 初始化数据库信息
        mDevOpenHelper = new DaoMaster.DevOpenHelper(sContext, DB_NAME);
        getDaoMaster();
        getDaoSession();
    }

    public static void initDB(Context context) {
        sContext = context.getApplicationContext();
        getInstance();
    }

    public static DBManager getInstance() {
        if (null == mDBManager) {
            synchronized (DBManager.class) {
                if (null == mDBManager) {
                    mDBManager = new DBManager();
                }
            }
        }
        return mDBManager;
    }

    /**
     * 获取可读数据库
     *
     * @return
     */
    public SQLiteDatabase getReadableDatabase() {
        return mDevOpenHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     */
    public SQLiteDatabase getWritableDatabase() {
        return mDevOpenHelper.getWritableDatabase();
    }

    /**
     * 获取DaoMaster
     * <p>
     * 判断是否存在数据库，如果没有则创建数据库
     */
    public static DaoMaster getDaoMaster() {
        if (null == mDaoMaster) {
            synchronized (DBManager.class) {
                if (null == mDaoMaster) {
                    MyOpenHelper helper = new MyOpenHelper(sContext, DB_NAME, null);
                    mDaoMaster = new DaoMaster(helper.getWritableDatabase());
                }
            }
        }
        return mDaoMaster;
    }

    /**
     * 获取DaoSession
     */
    public static DaoSession getDaoSession() {
        if (null == mDaoSession) {
            synchronized (DBManager.class) {
                mDaoSession = getDaoMaster().newSession();
            }
        }

        return mDaoSession;
    }

    public List<DownloadInfo> queryDownloadAll() {
        DownloadInfoDao DownloadInfoDao = getDaoSession().getDownloadInfoDao();
        QueryBuilder<DownloadInfo> qb = DownloadInfoDao.queryBuilder();
        return qb.list();
    }

    public void saveDownload(DownloadInfo info) {
        List<DownloadInfo> downloadInfos = queryDownloadAll();
        if (downloadInfos != null)
            for (int i = 0; i < downloadInfos.size(); i++) {
                if (downloadInfos.get(i).getId() == info.getId()) {
                    updateDownload(info);
                    return;
                }
            }
        DownloadInfoDao downloadInfoDao = getDaoSession().getDownloadInfoDao();
        downloadInfoDao.insert(info);

    }

    public void updateDownload(DownloadInfo info) {
        DownloadInfoDao DownloadInfoDao = getDaoSession().getDownloadInfoDao();
        DownloadInfoDao.update(info);
    }
}
