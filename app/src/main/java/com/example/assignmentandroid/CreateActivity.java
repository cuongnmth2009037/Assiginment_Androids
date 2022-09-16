package com.example.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateActivity extends AppCompatActivity {
    EditText txtName, txtEmail, txtTel;
    SQLiteDatabaseHandle sqLiteDatabaseHandle;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        txtName = findViewById(R.id.ed_name);
        txtEmail = findViewById(R.id.ed_email);
        txtTel = findViewById(R.id.ed_tel);
        sqLiteDatabaseHandle = new SQLiteDatabaseHandle(this);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setName(txtName.getText().toString());
                student.setEmail(txtEmail.getText().toString());
                student.setTel(txtTel.getText().toString());
                sqLiteDatabaseHandle.addStudent(student);
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}