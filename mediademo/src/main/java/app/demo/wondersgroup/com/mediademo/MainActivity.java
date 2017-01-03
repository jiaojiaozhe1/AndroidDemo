package app.demo.wondersgroup.com.mediademo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button captureImg;
    private Button phtotsImg;
    private ImageView contentImg;
    private Uri uri;
    File imgFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();


        //下面目录的内容在app被卸载的时候也会被删除
        Log.e("tag", "path ==" + getExternalCacheDir().getPath());//获取当前app 缓存目录
        Log.e("tag", "getExtrrnal ==" + getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath());//获取当前app 文件目录下面 picture 目录
        Log.e("tag", "getExtrrnal ==" + getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath());//获取当前app 文件目录下面 music 目录
        Log.e("tag", "getFileDir ==" + getFilesDir().getPath());//获取当前app 文件目录

//            path ==/storage/emulated/0/Android/data/app.demo.wondersgroup.com.mediademo/cache
//           getExtrrnal ==/storage/emulated/0/Android/data/app.demo.wondersgroup.com.mediademo/files/Pictures
//           getFileDir ==/data/user/0/app.demo.wondersgroup.com.mediademo/files
    }

    private void initListener() {
        captureImg.setOnClickListener(this);
        phtotsImg.setOnClickListener(this);
    }

    private void initView() {
        captureImg = (Button) findViewById(R.id.capture_img_btn);
        phtotsImg = (Button) findViewById(R.id.choose_photos_btn);
        contentImg = (ImageView) findViewById(R.id.content_img);
    }

    @Override
    public void onClick(View view) {
        if (view == captureImg) {
            requestPermissionMedia();
//            takePhoto();
//            imgFile = new File(getExternalCacheDir(), "capture.jpg");
        } else if (view == phtotsImg) {
//            choosePhoto();
            requestPermissionMedia();
        }
    }

    private void choosePhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "/img*");
        startActivityForResult(intent, 2);
    }

    private void takePhoto() {
        //不同目录下面的文件在使用FileProvider 的时候需要看看xml 文件里面分享文件的路径
        imgFile = new File(Environment.getExternalStorageDirectory(), "capture.jpg");
//        imgFile = new File(getExternalCacheDir(), "capture.jpg");

        if (imgFile.exists()) {
            imgFile.delete();
        }
        try {
            imgFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        if (Build.VERSION.SDK_INT >= 24) {
        //FileProvider 将文件进行分装, 然后供外部应用（相机）访问提高了当前应用的安全性
        uri = FileProvider.getUriForFile(this, "com.example.android.fileprovider", imgFile);//通过FileProvider 来获取本地图片文件
//        } else {
//            uri = Uri.fromFile(imgFile);
//        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {//判断 隐式启动的 activity 是否存在
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);// 向多媒体数据库存入拍照后的照片路径
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            Glide.with(this).load(uri).into(contentImg);


//            if (imgFile.exists()) {
//                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getPath());
//                contentImg.setImageBitmap(bitmap);
//            } else {
//                Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
//            }
//            Bundle extras = data.getExtras();
//            Bitmap bitmap = (Bitmap) extras.get("data");
//            contentImg.setImageBitmap(bitmap);


//            requestPermissionMedia();

//            Bitmap bitmap = data.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
//            contentImg.setImageBitmap(bitmap);

//            Uri uri = data.getData();
//            contentImg.setImageBitmap(BitmapFactory.decodeFile(uri.getPath()));

        } else if (requestCode == 2) {
            Uri uri = data.getData();
            getImagePath(uri, null);
        }
    }

    private void getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor query = getContentResolver().query(uri, null, selection, null, null);
        if (query != null) {
            if (query.moveToFirst()) {
                path = query.getString(query.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            query.close();
        }

        contentImg.setImageBitmap(BitmapFactory.decodeFile(path));
    }

    private void requestPermissionMedia() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
//            choosePhoto();
            takePhoto();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                choosePhoto();
                takePhoto();
            } else {
                Toast.makeText(this, "you  denied permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showImg() {
//        try {
        //打开多媒体库 也就是读取里面的内容那么会以流的方式进行展现
//            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
//
//
//            Log.e("uri", "inputStrm" + getContentResolver().openInputStream(uri));
//            Log.e("uri", "bitmap" + bitmap);
//            contentImg.setImageBitmap(bitmap);//从多媒体库取出拍照图片

//        /storage/emulated/0/Android/data/app.demo.wondersgroup.com.mediademo/cache/capture.jpg
        Log.e("tag", "path ==" + imgFile.getPath());


//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
