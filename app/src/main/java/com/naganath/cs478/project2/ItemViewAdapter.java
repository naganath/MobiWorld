package com.naganath.cs478.project2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;


public class ItemViewAdapter extends ArrayAdapter<Mobile> {
    private ArrayList<Mobile> data ;
    private int layoutResourceId;

    ItemViewAdapter(Context context,int layoutId, ArrayList<Mobile> data) {
       super(context, layoutId, data);
        this.data = data;
        this.layoutResourceId = layoutId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        Mobile mobileData = getItem(position);
        MobileWrapper wrapper = new MobileWrapper();
        if (item == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            item = inflater.inflate(layoutResourceId, parent, false);
            wrapper.details = (TextView) item.findViewById(R.id.text2);
            wrapper.title = (TextView) item.findViewById(R.id.text1);
            wrapper.thumbnail = (ImageView) item.findViewById(R.id.thumbnail);
            item.setTag(wrapper);
        } else {
            wrapper = (MobileWrapper) item.getTag();
        }


        InputStream is = getContext().getResources().openRawResource(mobileData.getImage());
        Bitmap mThumbnail = scaleBitmap(BitmapFactory.decodeStream(is));

        wrapper.details.setText(mobileData.getDetails());
        wrapper.title.setText(mobileData.getName());
        wrapper.thumbnail.setImageBitmap(mThumbnail);
//                setImageBitmap(ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(mobileData.getImage()),100, 100));
//                setImageResource(  mobileData.getImage());

        /*
        set on click listener
         */

        return item;
    }

    private static Bitmap scaleBitmap(Bitmap source) {
        int maxSize = source.getWidth() > source.getHeight() ? source.getWidth() : source.getHeight();
        return ThumbnailUtils.extractThumbnail(source, 96, 96);
//        return Bitmap.createScaledBitmap(source, source.getWidth() * 96 / maxSize,
//                source.getHeight() * 96 / maxSize, true);
    }



    static class  MobileWrapper {
        ImageView thumbnail;
        TextView title;
        TextView details;
        int id;

    }

}
