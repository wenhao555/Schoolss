package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InfoContextActivity extends AppCompatActivity {
    private TextView tv_title, tv_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_context);
        String title = getIntent().getStringExtra("title");
        String context = getIntent().getStringExtra("context");
        tv_title = findViewById(R.id.tv_title);
        tv_context = findViewById(R.id.tv_context);
        tv_title.setText(title);
        tv_context.setText(context);
    }

    public void fin(View view) {
        finish();
    }
}
