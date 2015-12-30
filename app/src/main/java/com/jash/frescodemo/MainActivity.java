package com.jash.frescodemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView drawee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawee = (SimpleDraweeView) findViewById(R.id.drawee);
        GenericDraweeHierarchy build = new GenericDraweeHierarchyBuilder(getResources())
                .setProgressBarImage(new ProgressBarDrawable()).build();
        drawee.setHierarchy(build);
        Uri uri = Uri.parse("http://wallpaperswide.com/download/captain_america_vs_iron_man-1440x1080.jpg");
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(drawee.getController())
                .build();

        drawee.setController(controller);
//        drawee.setImageURI(Uri.parse("https://material-design.storage.googleapis.com/publish/material_v_4/material_ext_publish/0Bx4BSt6jniD7NndTQW9VZTlZV2s/materialdesign_principles_bold.png"));
//        if (ActivityCompat.checkSelfPermission(
//                this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                == PackageManager.PERMISSION_GRANTED) {
//            drawee.setImageURI(Uri.fromFile(
//                    new File(
//                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "347.jpg")));
//        } else {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            drawee.setImageURI(Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "347.jpg")));
        }
    }
}
