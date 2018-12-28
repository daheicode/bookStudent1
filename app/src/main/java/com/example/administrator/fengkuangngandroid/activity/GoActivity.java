package com.example.administrator.fengkuangngandroid.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.fengkuangngandroid.MainActivity;
import com.example.administrator.fengkuangngandroid.R;

public class GoActivity extends AppCompatActivity {

    private Button button01;
    private Button button02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);
        button01 = (Button) findViewById(R.id.button01);
        button02 = (Button) findViewById(R.id.button02);
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoActivity.this,MainActivity.class));
            }
        });
    }
}
