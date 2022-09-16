package com.example.assignmentandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateStudentActivity extends AppCompatActivity {
    private int studentId;
    SQLiteDatabaseHandle databaseHandle;
    EditText txtName, txtEmail, txtTel;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        studentId = getIntent().getIntExtra("id", 0);
        databaseHandle = new SQLiteDatabaseHandle(this);
        Student student = databaseHandle.getStudent(studentId);
        txtName = findViewById(R.id.ud_name);
        txtEmail = findViewById(R.id.ud_email);
        txtTel = findViewById(R.id.ud_tel);
        txtName.setText(student.getName());
        txtEmail.setText(student.getEmail());
        txtTel.setText(student.getTel());

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                student.setId(studentId);
                student.setName(txtName.getText().toString());
                student.setEmail(txtEmail.getText().toString());
                student.setTel(txtTel.getText().toString());
                databaseHandle.updateStudent(student);
                Intent intent = new Intent(UpdateStudentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

}