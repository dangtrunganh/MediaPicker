package com.anhdt.mediapicker;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnChoose;
    private EditText edtMaxFiles;
    private TextView tvLinkImages, tvLinkVideos;
    private int maxFiles = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        btnChoose = (Button) findViewById(R.id.btn_choose);
        edtMaxFiles = (EditText) findViewById(R.id.edt_max_files);
        tvLinkImages = (TextView) findViewById(R.id.tv_link_images);
        tvLinkVideos = (TextView) findViewById(R.id.tv_link_videos);
        btnChoose.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (edtMaxFiles.getText().toString().equals("")) {
            Toast.makeText(this, "Default max files selected is chosen!", Toast.LENGTH_SHORT).show();
            maxFiles = -1;
        }
        switch (view.getId()) {
            case R.id.btn_choose:
                chooseOption();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void chooseOption() {
        PopupMenu popup = new PopupMenu(this, btnChoose);
        popup.getMenuInflater().inflate(R.menu.menu_choose, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_only_image:
                        chooseOnlyImages();
                        break;
                    case R.id.menu_only_video:
                        chooseOnlyVideos();
                        break;
                    case R.id.menu_choose_both:
                        chooseBothVideoAndImage();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        popup.show();
    }

    private void chooseBothVideoAndImage() {
        Toast.makeText(this, "Both!", Toast.LENGTH_SHORT).show();
    }

    private void chooseOnlyVideos() {
        Toast.makeText(this, "Video!", Toast.LENGTH_SHORT).show();
    }

    private void chooseOnlyImages() {
        Toast.makeText(this, "Image!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, PickImageAct.class));
    }

    //==========================================

}
