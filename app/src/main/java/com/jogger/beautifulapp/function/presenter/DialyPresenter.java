package com.jogger.beautifulapp.function.presenter;

import com.jogger.beautifulapp.R;
import com.jogger.beautifulapp.base.BasePresenter;
import com.jogger.beautifulapp.entity.AppInfoData;
import com.jogger.beautifulapp.function.contract.DialyContract;
import com.jogger.beautifulapp.function.model.DialyModel;
import com.jogger.beautifulapp.http.listener.OnHttpRequestListener;
import com.jogger.beautifulapp.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DialyPresenter extends BasePresenter<DialyContract.View, DialyContract.Model>
        implements DialyContract.Presenter {
    private SimpleDateFormat mDateFormat;

    public DialyPresenter() {
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    }

    @Override
    public void getDialyDatas(int page, int pageSize) {
        getModel().getDialyDatas(page, pageSize, new OnHttpRequestListener<AppInfoData>() {
            @Override
            public void onFailure(int errorCode) {

            }

            @Override
            public void onSuccess(AppInfoData appData) {
                if (unViewAttached()) return;
                getView().loadDatas(appData);
            }
        });
    }

    @Override
    public void updateDate(String date) {
        int weekResId = -1;
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(mDateFormat.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                weekResId = R.string.sunday;
                break;
            case Calendar.MONDAY:
                weekResId = R.string.monday;
                break;
            case Calendar.TUESDAY:
                weekResId = R.string.tuesday;
                break;
            case Calendar.WEDNESDAY:
                weekResId = R.string.wednesday;
                break;
            case Calendar.THURSDAY:
                weekResId = R.string.thursday;
                break;
            case Calendar.FRIDAY:
                weekResId = R.string.friday;
                break;
            case Calendar.SATURDAY:
                weekResId = R.string.saturday;
                break;
        }
        //获取月份
        String month = c.get(Calendar.MONTH) + 1 + Util.getApp().getString(R.string.month);
        getView().updateDate(weekResId, month, c.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public DialyContract.Model attachModel() {
        return new DialyModel();
    }
}
