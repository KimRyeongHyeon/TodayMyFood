package com.myandroid.todaymyfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "FOODTABLE.db", null, 1);

        final EditText foodEditText = (EditText) findViewById(R.id.foodEditText);
        final TextView dbText = (TextView) findViewById(R.id.textView);

        //추가
        Button insertBtn = (Button) findViewById(R.id.insertBtn);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodname = foodEditText.getText().toString();

                dbHelper.insert(foodname);
                dbText.setText(dbHelper.getResult());

                foodEditText.setText("");
            }
        });

        //삭제
        Button deleteBtn = (Button) findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodname = foodEditText.getText().toString();
                dbHelper.delete(foodname);
                dbText.setText(dbHelper.getResult());
            }
        });

        //조회
        Button selectBtn = (Button) findViewById(R.id.selectBtn);
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbText.setText(dbHelper.getResult());
            }
        });

    }
}
