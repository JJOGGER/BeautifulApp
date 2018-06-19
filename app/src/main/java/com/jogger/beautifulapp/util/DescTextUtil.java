package com.jogger.beautifulapp.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Html;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by Jogger on 2017/2/10.
 */

public class DescTextUtil {
    private Handler mHandler = new Handler();

    public DescTextUtil() {
    }

    public void setDrawableText(final String content, final TextView textView) {
        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(final String source) {
                final URLDrawable drawable = new URLDrawable();
                Glide.with(Util.getApp())
                        .load(source)
                        .asBitmap()
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<?
                                    super Bitmap> glideAnimation) {
                                int w = SizeUtil.getScreenWidth() - SizeUtil.dp2px(64);
                                int h = (int) (w / ((float) resource.getWidth()
                                        / (float) resource.getHeight()));
                                Matrix matrix = new Matrix();
                                matrix.postScale(w / (float) resource.getWidth(), h / (float)
                                        resource.getHeight());
                                Bitmap newBitmap = Bitmap.createBitmap(resource, 0, 0, resource
                                        .getWidth(), resource.getHeight(), matrix, true);//得到最终宽高
                                drawable.setBounds(0, 0, newBitmap.getWidth(), newBitmap.getHeight());
                                drawable.bitmap = newBitmap;
                                textView.invalidate();
                                textView.setText(textView.getText());
                            }
                        });
                return drawable;
            }

        };

        final CharSequence charSequence = Html.fromHtml(content, imageGetter, null);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText(charSequence);
                textView.invalidate();
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
}
