package app.demo.wondersgroup.com.iofiledemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class LocalDirectoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_directory);

        Button directoryBtn = (Button) findViewById(R.id.directory_btn);
        directoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rootDir = Environment.getRootDirectory().getPath();
                String dataDir = Environment.getDataDirectory().getPath();
                String downDir = Environment.getDownloadCacheDirectory().getPath();

                Log.e("rootDir","rootDir=" + rootDir);
                Log.e("dataDir","dataDir=" + dataDir);
                Log.e("downDir","downDir=" + downDir);
            }
        });
    }
}
