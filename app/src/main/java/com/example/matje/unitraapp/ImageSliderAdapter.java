package com.example.matje.unitraapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Matje on 01.09.2017.
 */

public class ImageSliderAdapter extends PagerAdapter {
    Context mContext;
    //int[] sliderImagesId;
    Bitmap[] sliderImagesId;

    ImageSliderAdapter(Context context,Bitmap []sliderImagesId) {
        this.mContext = context;
        this.sliderImagesId=sliderImagesId;
    }

    @Override
    public int getCount() {

        return sliderImagesId.length;


        //return 3;
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType((ImageView.ScaleType.FIT_CENTER));
        //fitCenter
        mImageView.setImageBitmap(sliderImagesId[i]);
        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }

    }
