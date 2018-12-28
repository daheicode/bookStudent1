package com.example.administrator.fengkuangngandroid;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.fengkuangngandroid.view.DrawView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private DrawView drawView;
    private DrawView draw;
    private Button button;
    private FileOutputStream out;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        drawView = new DrawView(this);
        setContentView(R.layout.activity_main);
        draw = (DrawView) findViewById(R.id.draw);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBitmapToSD(draw.getMyBitmap());
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw.clear();
            }
        });
    }

    //将Bitmap图片保存到sd卡

    protected void saveBitmapToSD(Bitmap bt) {
        File path = Environment.getExternalStorageDirectory();
        File file = new File(path, System.currentTimeMillis() + ".jpg");
//        System.out.println(Environment.getExternalStorageState() + "/Cool/" +"000000000000000000000000000");
        try {
            out = new FileOutputStream(file);
            bt.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
