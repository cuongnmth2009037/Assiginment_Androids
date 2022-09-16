package com.example.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSubmit;
    SQLiteDatabaseHandle databaseHandle;
    StudentListAdapter studentListAdapter;
    List<Student> students;
    ListView listViewStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewStudent = findViewById(R.id.listStudent);
        databaseHandle = new SQLiteDatabaseHandle(this);
        students = databaseHandle.getAllStudents();
        studentListAdapter = new StudentListAdapter(this, students, databaseHandle);
        listViewStudent.setAdapter(studentListAdapter);

        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSubmit:
                Intent intent = new Intent(this, CreateActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}