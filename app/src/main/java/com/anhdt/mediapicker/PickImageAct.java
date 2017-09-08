package com.anhdt.mediapicker;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;


public class PickImageAct extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<Image> images;
    private MediaAdapter mediaAdapter;
    private GridView gvImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pick_image);
        initViews();
        initData();

        setData();
    }

    private void initViews() {
        gvImage = (GridView) findViewById(R.id.gv_media);
        gvImage.setOnItemClickListener(this);
    }

    private void setData() {
        mediaAdapter = new MediaAdapter(images, this);
        gvImage.setAdapter(mediaAdapter);
    }

    private void initData() {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String projections[] = new String[]{
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.Thumbnails.DATA
        };

        String where = MediaStore.Images.ImageColumns.DISPLAY_NAME + " LIKE '%.png'";
        Cursor c = this.getContentResolver().query(uri, projections,
                where, null, null);

        if (c == null) {
            Log.e("TAG: ", "Error: Could not get audio list...");
            return;
        }
        c.moveToFirst();
        int indexID = c.getColumnIndex(MediaStore.Images.ImageColumns._ID);
        int indexPath = c.getColumnIndex(MediaStore.Images.Thumbnails.DATA);


        String id, path;
        images = new ArrayList<>();
        while (c.isAfterLast() == false) {
            id = c.getString(indexID);
            path = "file:" + c.getString(indexPath);
            images.add(new Image(id, path));
            c.moveToNext();
        }
        c.close();
        Log.i("TAG: ", images.toString());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "position clicked: " + i, Toast.LENGTH_SHORT).show();
    }
}
