package com.example.barbaros.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {
TextView tv_bitti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        tv_bitti=(TextView)findViewById(R.id.textView_bitti);
    }
}
