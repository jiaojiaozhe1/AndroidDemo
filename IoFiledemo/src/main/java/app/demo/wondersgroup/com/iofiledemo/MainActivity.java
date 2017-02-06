package app.demo.wondersgroup.com.iofiledemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button createFile;
    private Button readFile;
    private Button byteArrayBtn;
    private Button dataOptionBtn;
    private Button fileDirecBtn;

    private String basePath;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basePath = Environment.getExternalStorageDirectory().getPath();
        initView();
        initListener();
    }

    private void initListener() {
        createFile.setOnClickListener(this);
        readFile.setOnClickListener(this);
        byteArrayBtn.setOnClickListener(this);
        dataOptionBtn.setOnClickListener(this);
        fileDirecBtn.setOnClickListener(this);
    }

    private void initView() {
        createFile = (Button) findViewById(R.id.create_file_btn);
        readFile = (Button) findViewById(R.id.read_file_btn);
        byteArrayBtn = (Button) findViewById(R.id.byte_array_file_btn);
        dataOptionBtn = (Button) findViewById(R.id.data_read_write_btn);
        fileDirecBtn = (Button) findViewById(R.id.file_directory_btn);

    }

    @Override
    public void onClick(View view) {
        if (view == createFile) {
            addFilePermission();
        } else if (view == readFile) {

            writeFile();
        } else if (view == byteArrayBtn) {

            byteArrayOption();
        } else if (view == dataOptionBtn) {

//            dataOption();

            fileReadAndWrite();

        } else if (view == fileDirecBtn){
            startActivity(new Intent(this,LocalDirectoryActivity.class));
        }
    }

    /**
     * 字符流读写
     */
    private void fileReadAndWrite() {
        try {
            FileReader fileReader = new FileReader(basePath + File.separator + "wifi_config.log");

            FileWriter fileWriter = new FileWriter(basePath + File.separator + "smile/iofile/temp/log.txt");

            char[] chars = new char[1024];


            int len =0;

            while ( (len = fileReader.read(chars)) != -1){
                fileWriter.write(chars);
                Log.e("dataInput", "data ====" + " dataStr=" + new String(chars));
            }
            fileWriter.close();
            fileReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 结合字节数组输入输出流 进行操作
     */
    private void dataOption() {
        try {
            //写入数据
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);

            dos.writeUTF("哈哈哈 我写入第一条数据");
//            dos.writeInt(100);

//            Log.e("dataOut", "data ====" + baos.toString());

            //读出来写入的数据
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            DataInputStream dis = new DataInputStream(bais);

            String dataStr = dis.readUTF();

            Log.e("dataInput", "data ====" + " dataStr=" + dataStr);

        } catch (IOException e) {
            Log.e("IOException", "eeeeee ====" + e);
            e.printStackTrace();
        }

    }

    /**
     * 争对内存的操作
     */
    private void byteArrayOption() {
        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(1);
        baos.write(2);
        baos.write(3);

        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bais = new ByteArrayInputStream(baos.toByteArray());
            int len = 0;

            while ((len = bais.read()) != -1) {

                Log.e("byteArray", "byte====" + len);
            }
        } finally {
            try {
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void writeFile() {
        String content = "//当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件。  ";
        FileOutputStream fos = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            File[] files = file.listFiles();
            Log.e("logggggg", "length=====" + files.length);
            fis = new FileInputStream(basePath + File.separator + "wifi_config.log");//读取指定路径下文件内容
            bis = new BufferedInputStream(fis);//输入流缓存 先读取到缓存里面

            fos = new FileOutputStream(basePath + File.separator + "smile/iofile/temp/log.txt", true);//写入到指定路径文件下面，true是进行追加不会进行覆盖内容
            baos = new ByteArrayOutputStream(1024);//在内存中开辟一块内存,从而对字节进行操作,提高效率

            byte[] buf = new byte[1024];
            int len = 0;

            //读取的内容放到buf 缓存,每次读取长度是len  当-1 已经读完了
            while ((len = bis.read(buf)) != -1) {
//                Log.e("logggggg", "buf=====" + new String(buf));
//                fos.write(buf, 0, len); // 从0开始每次写入的长度为len ,写入内容是buf 缓存中的 字节
                baos.write(buf, 0, len);//先读入缓存再由缓存写入到 文件
            }

            baos.writeTo(fos);// 将缓存的内容写到文件里面 和下面任意一个方法都行
//            fos.write(baos.toByteArray());// 同上面的方法
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("logggggg", "e=====" + e);
        } finally {

            try {
                if (fis != null) {
                    fis.close();
                    if (fos != null) {
                        fos.close();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                e.printStackTrace();
                Log.e("logggggg", "finally=====" + e);
            }
        }

    }

    private void addFilePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        } else {
            Log.e("logggggg", "已经有权限了");

            createFile();
        }
    }

    private void createFile() {

//        file = new File(basePath + File.separator + "smile/iofile/log.txt");
//        File file2 = new File(basePath + File.separator + "log.txt");//指定路径下面的一个文件 通过createNewFile 即可创建
//        basePath = Environment.getExternalStorageDirectory().getPath();

        //如果需要创建的文件所在的目录不存在可以先创建目录,再创建文件
        file = new File(basePath + File.separator + "smile/iofile/temp");//指定一个文件路径
        try {
            if (!file.exists()) {
                file.mkdirs();//创建目录 可以创建多级目录

                File file1 = new File(file.getPath() + "/log.txt");

                file1.createNewFile();//创建文件

            }

//            File.createTempFile("temp", "txt", file);  创建临时文件 file是临时文件所在目录文件
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("logggggg", "eee ==" + e);
        }


//        if (!file.exists()) {
//            try {
//                file.mkdirs();
//                file.createNewFile();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("logggggg", "授权成功");

                createFile();
            } else {
                Log.e("logggggg", "授权失败");
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
