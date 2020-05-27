package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.school.db.InfoDao;

public class AddInfoActivity extends AppCompatActivity {
    private TextView titleBar;
    private EditText title, context;
    private Button add_info;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        titleBar = findViewById(R.id.titleBar);
        title = findViewById(R.id.title);
        context = findViewById(R.id.context);
        add_info = findViewById(R.id.add_info);
        type = getIntent().getStringExtra("type");

        titleBar.setText(type);

        add_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestInfo(title.getText().toString(), context.getText().toString());

            }
        });
    }

    private void requestInfo(String title, String context) {
        InfoDao infoDao = new InfoDao(this);
        if (type.equals("添加新闻")) {
            infoDao.add("newsinfo", title, context);
        } else if (type.equals("添加资源")) {

            infoDao.add("msginfo", title, context);
        } else {
            infoDao.add("pdtinfo", title, context);
        }
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
