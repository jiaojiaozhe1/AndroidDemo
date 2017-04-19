package app.demo.wondersgroup.com.mediademo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity {
    private ImageView img_album;
    private File file;
    private File cutFile;
    Uri pathUri;


    private final int ALBUM_OK = 1, CAMERA_OK = 2, CUT_OK = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Button takeGrally = (Button) findViewById(R.id.take_grally);
        Button takePhoto = (Button) findViewById(R.id.take_photo);
        img_album = (ImageView) findViewById(R.id.image_view);


        // 定义拍照后存放图片的文件位置和名称，使用完毕后可以方便删除
        file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
//        cutFile = new File(Environment.getExternalStorageDirectory(), "cut.jpg");
//        file.delete();// 清空之前的文件

        takeGrally.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 来自相册
                Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
                /**
                 * 下面这句话，与其它方式写是一样的效果，如果：
                 * intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                 * intent.setType(""image/*");设置数据类型
                 * 要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                 */
                albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(albumIntent, ALBUM_OK);
            }
        });

        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这里被注掉的，是在6.0中进行权限判断的，大家可以根据情况，自行加上
                if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            123);
                    Log.e("Album", "我没有权限啊");
                } else {

                    Log.e("Album", "我有权限啊");
                }

                Uri uri = null;
                // 7.0 中的处理
                if (Build.VERSION.SDK_INT >= 24) {
                    //FileProvider 将文件进行分装, 然后供外部应用（相机）访问提高了当前应用的安全性
                    uri = FileProvider.getUriForFile(CameraActivity.this, "com.example.android.fileprovider", file);//通过FileProvider 来获取本地图片文件
                } else {
                    uri = Uri.fromFile(file);
                }
                // 来自相机
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 下面这句指定调用相机拍照后的照片存储的路径
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(cameraIntent, CAMERA_OK);// CAMERA_OK是用作判断返回结果的标识
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("requestCode = " + requestCode);
        switch (requestCode) {
            // 如果是直接从相册获取

            case ALBUM_OK:
                //4.4 以上系统版本号
                if (Build.VERSION.SDK_INT >= 19) {
                    String path = handleImageOnKitKat(data);
                    File file = new File(path);

                    if (!file.exists()) {
                        try {
                            file.createNewFile();//创建文件

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    startPhotoZoom(file, ALBUM_OK);

                } else {
                    //4.4 之前版本号
                    String path = handleImageBeforeKitkat(data);

                    File file = new File(path);

                    if (!file.exists()) {
                        try {
                            file.createNewFile();//创建文件

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    startPhotoZoom(file, ALBUM_OK);
                }
                break;
            // 如果是调用相机拍照时
            case CAMERA_OK:
                startPhotoZoom(file, CAMERA_OK);
                break;
            // 取得裁剪后的图片，这里将其设置到imageview中
            case CUT_OK:
                setPicToView();

                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 保存裁剪之后的图片数据 将图片设置到imageview中
     */
    private void setPicToView() {

        try {
            //方法1 通过file path 显示图片
//            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());


            //方法2 通过Uri 显示图片
            if (pathUri != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(
                        getContentResolver().openInputStream(pathUri));

                String path = saveBitmapByQuatity(bitmap, 80);

                Log.e("setPicToView", "path ==" + path);
                if (bitmap != null) {

                    img_album.setImageBitmap(bitmap);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 对图片进行压缩
     * @param bitmap
     * @param quatity
     * @return
     */
    private String saveBitmapByQuatity(Bitmap bitmap, int quatity) {
        String cropPath = "";
        cropPath = file.getPath();

        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quatity, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cropPath;
    }

    /**
     * 裁剪图片
     *
     * @param
     */
    public void startPhotoZoom(File file, int type) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        // 7.0 中的处理
        if (Build.VERSION.SDK_INT >= 24) {

            intent.setDataAndType(getImageContentUri(this, file), "image/*");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "image/*");
        }


        // 下面这个crop = true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例，这里设置的是正方形（长宽比为1:1）
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", false);

        /**
         * 此处做一个判断
         * １，相机取到的照片，我们把它做放到了定义的目录下。就是file
         * ２，相册取到的照片，这里注意了，因为相册照片本身有一个位置，我们进行了裁剪后，要给一个裁剪后的位置，
         * 　　不然onActivityResult方法中，data一直是null 这里我们将相册的图片也用文件进行保存,这样相册和拍照都可以使用
         *
         * 同样的代码处理方式
         *   intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
         *
         *   否则 需要做如下处理
         *
         *     if(type==CAMERA_OK)
         {
         intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
         }else {
         intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
         }
         */

        pathUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pathUri);

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, CUT_OK);
    }


    private String handleImageBeforeKitkat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        return imagePath;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private String handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String documentId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }

        Log.e("ExceptionException", "imagePath=====" + imagePath);
        return imagePath;
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    /**
     * 7.0 获取图片Uri 处理
     * @param context
     * @param imageFile
     * @return
     */
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=?", new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.Images.Media.DATA, filePath);

                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            } else {
                return null;
            }
        }
    }

}
