package com.jogger.beautifulapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.jogger.beautifulapp.entity.DaoMaster;
import com.jogger.beautifulapp.entity.DaoSession;
import com.jogger.beautifulapp.entity.DownloadInfo;
import com.jogger.beautifulapp.entity.DownloadInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Calendar;
import java.util.List;


/**
 * 断点续传
 * 数据库工具类-geendao运用
 * Created by WZG on 2016/10/25.
 */

public class DBDownloadUtil {
    private static DBDownloadUtil sDownloadUtil;
    private final static String mDBName = "nice_db";
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;


    public DBDownloadUtil() {
        context = Util.getApp();
        openHelper = new DaoMaster.DevOpenHelper(context, mDBName, null);
    }


    /**
     * 获取单例
     *
     * @return
     */
    public static DBDownloadUtil getInstance() {
        if (sDownloadUtil == null) {
            synchronized (DBDownloadUtil.class) {
                if (sDownloadUtil == null) {
                    sDownloadUtil = new DBDownloadUtil();
                }
            }
        }
        return sDownloadUtil;
    }


    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, mDBName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, mDBName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }


    public void save(DownloadInfo info) {
        List<DownloadInfo> downloadInfos = queryDownAll();
        if (downloadInfos != null)
            for (int i = 0; i < downloadInfos.size(); i++) {
                if (downloadInfos.get(i).getId() == info.getId()) {
                    update(info);
                    return;
                }
            }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DownloadInfoDao downloadInfoDao = daoSession.getDownloadInfoDao();
        downloadInfoDao.insert(info);

    }


    public void query() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DownloadInfoDao downloadInfoDao = daoSession.getDownloadInfoDao();
        List<DownloadInfo> list = downloadInfoDao.queryBuilder().where(DownloadInfoDao.Properties.DownloadDate.lt(1))
                .list();
        L.e("----------query:" + list);
    }

    public void update(DownloadInfo info) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DownloadInfoDao DownloadInfoDao = daoSession.getDownloadInfoDao();
        DownloadInfoDao.update(info);
    }

    public void deleteDownloadInfo(DownloadInfo info) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DownloadInfoDao DownloadInfoDao = daoSession.getDownloadInfoDao();
        DownloadInfoDao.delete(info);
    }


    public DownloadInfo queryDownBy(long Id) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DownloadInfoDao DownloadInfoDao = daoSession.getDownloadInfoDao();
        QueryBuilder<DownloadInfo> qb = DownloadInfoDao.queryBuilder();
        qb.where(com.jogger.beautifulapp.entity.DownloadInfoDao.Properties.Id.eq(Id));
        List<DownloadInfo> list = qb.list();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public List<DownloadInfo> queryDownAll() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DownloadInfoDao DownloadInfoDao = daoSession.getDownloadInfoDao();
        QueryBuilder<DownloadInfo> qb = DownloadInfoDao.queryBuilder();
        return qb.list();
    }

    private long getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }
}
