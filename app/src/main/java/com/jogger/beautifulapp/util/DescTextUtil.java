package com.jogger.beautifulapp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Jogger on 2017/2/10.
 */

public class DescTextUtil {
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Handler mBackgroundHandler;
    private TextView mTextView;

    public DescTextUtil() {
    }

    public void setDrawableText(final String content, TextView textView) {
        mTextView = textView;
        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(final String source) {
                final URLDrawable drawable = new URLDrawable();
                Glide.with(Util.getApp())
                        .load(source)
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(final Bitmap resource, GlideAnimation<?
                                    super Bitmap> glideAnimation) {
                                getBackgroundHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        parseImg(resource, drawable);
                                    }
                                });

                            }
                        });
                return drawable;
            }

        };
        final CharSequence charSequence = Html.fromHtml(content, imageGetter, null);
        if (mTextView != null) {
            mTextView.setText(charSequence);
            mTextView.invalidate();
        }
    }

    private void parseImg(Bitmap resource, URLDrawable drawable) {
        int w = SizeUtil.getScreenWidth() - SizeUtil.dp2px(64);
        int h = (int) (w / ((float) resource.getWidth()
                / (float) resource.getHeight()));
        Matrix matrix = new Matrix();
        matrix.postScale(w / (float) resource.getWidth(), h / (float)
                resource.getHeight());
        Bitmap newBitmap = Bitmap.createBitmap(resource, 0, 0, resource
                .getWidth(), resource.getHeight(), matrix, true);//得到最终宽高
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int opt =80;
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
        while (baos.toByteArray().length / 1024f > 1024 * 0.8f) {//控制图片质量在800k以内
            baos.reset();
            opt -= 5;
            newBitmap.compress(Bitmap.CompressFormat.JPEG, opt, baos);
        }
        newBitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.toByteArray().length,new BitmapFactory.Options());
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        drawable.setBounds(0, 0, newBitmap.getWidth(), newBitmap.getHeight());
        drawable.bitmap = newBitmap;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mTextView.invalidate();
                mTextView.setText(mTextView.getText());
            }
        });

    }

    private class URLDrawable extends BitmapDrawable {
        Bitmap bitmap;
        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, SizeUtil.dp2px(16), SizeUtil.dp2px(8), getPaint());
            }
        }
    }

    private Handler getBackgroundHandler() {
        if (mBackgroundHandler == null) {
            HandlerThread thread = new HandlerThread("background");
            thread.start();
            mBackgroundHandler = new Handler(thread.getLooper());
        }
        return mBackgroundHandler;
    }

    private void releaseHandler() {
        if (mBackgroundHandler != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                mBackgroundHandler.getLooper().quitSafely();
            } else {
                mBackgroundHandler.getLooper().quit();
            }
            mBackgroundHandler = null;
        }
    }
}
