package com.anhdt.mediapicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by admin on 9/8/2017.
 */

public class MediaAdapter extends BaseAdapter {
    private ArrayList<Image> arrayImage;
    private Context mContext;

    public MediaAdapter(ArrayList<Image> arrayImage, Context mContext) {
        this.arrayImage = arrayImage;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return arrayImage.size();
    }

    @Override
    public Image getItem(int i) {
        return arrayImage.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gridview, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setData(arrayImage.get(position).getUrl());


        return convertView;
    }

    class ViewHolder {
        private ImageView ivImg;
        public ViewHolder(View itemView) {
            ivImg = itemView.findViewById(R.id.iv_item_img);
        }

        public void setData(String url) {
            Picasso.with(mContext).load(url).into(ivImg);
        }

    }
}
