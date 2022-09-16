package com.example.assignmentandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseHandle extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentData";
    private static final String TABLE_STUDENT= "Student";
    private static final String KEY_ID = "id";
    private static final String STUDENT_NAME = "studentName";
    private static final String STUDENT_EMAIL = "studentEmail";
    private static final String STUDENT_TEL = "studentTel";

    public SQLiteDatabaseHandle(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COUNTRY_TABLE = "CREATE TABLE " + TABLE_STUDENT
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + STUDENT_NAME + " TEXT,"
                + STUDENT_EMAIL + " TEXT,"
                + STUDENT_TEL + " TEXT" + ")";
        db.execSQL(CREATE_COUNTRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getName());
        values.put(STUDENT_EMAIL, student.getEmail());
        values.put(STUDENT_TEL, student.getTel());


        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }


    Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENT, new String[] { KEY_ID,

                        STUDENT_NAME, STUDENT_EMAIL, STUDENT_TEL }, KEY_ID + "=?",
                new String[] { String.valueOf(id)
                }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student((cursor.getString(1)),
                cursor.getString(2), cursor.getString(3));
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setEmail(cursor.getString(2));
                student.setTel(cursor.getString(3));
                // Adding country to list
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        return studentList;
    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getName());
        values.put(STUDENT_EMAIL, student.getEmail());
        values.put(STUDENT_TEL, student.getTel());


        return db.update(TABLE_STUDENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
        db.close();
    }

    public void deleteAllStudents(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT,null,null);
        db.close();
    }





}
