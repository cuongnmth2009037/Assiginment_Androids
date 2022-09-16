package com.example.assignmentandroid;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends BaseAdapter {
    private Activity context;
    List<Student> students;
    SQLiteDatabaseHandle db;

    public StudentListAdapter(Activity context, List students, SQLiteDatabaseHandle db) {
        this.context = context;
        this.students= students;
        this.db=db;
    }

    public static class ViewHolder
    {
        TextView textViewId;
        TextView textViewName;
        TextView textViewEmail;
        TextView textViewTel;
        Button editButton;
        Button deleteButton;
    }
    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            row = inflater.inflate(R.layout.row_item, null, true);


            vh.textViewId = (TextView) row.findViewById(R.id.textViewId);
            vh.textViewName = (TextView) row.findViewById(R.id.textViewName);
            vh.textViewEmail = (TextView) row.findViewById(R.id.textViewEmail);
            vh.textViewTel = (TextView) row.findViewById(R.id.textViewTel);
            vh.editButton = (Button) row.findViewById(R.id.edit);
            vh.deleteButton = (Button) row.findViewById(R.id.delete);


            row.setTag(vh);
        } else {

            vh = (ViewHolder) convertView.getTag();

        }
        Student student = students.get(position);
        vh.textViewId.setText("" + student.getId());
        vh.textViewName.setText(student.getName());
        vh.textViewEmail.setText(student.getEmail());
        vh.textViewTel.setText("" + student.getTel());
        Log.d("student", student.toString());

        vh.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateStudentActivity.class);
                intent.putExtra("id", student.getId());
                context.startActivity(intent);
            }
        });

        vh.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteStudent(student);
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });
        return  row;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {
        return students.size();
    }

}
