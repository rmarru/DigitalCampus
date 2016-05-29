package com.example.sanfe.digitalcampus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;

public class ShowStudentActivity extends AppCompatActivity {
//Visualizar imagen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstudent);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        Student student = new Student();

        if (bundle != null) {
            student = (Student) bundle.get("STUDENT");
        }

        ImageView image = (ImageView) findViewById(R.id.showstudent_image);
        TextView name = (TextView) findViewById(R.id.showstudent_name);
        TextView degree = (TextView) findViewById(R.id.showstudent_class);
        TextView birth = (TextView) findViewById(R.id.showstudent_date);
        TextView gender = (TextView) findViewById(R.id.showstudent_gender);
        TextView subjects = (TextView) findViewById(R.id.showstudent_list);
        String subject = new String();

        image.setImageResource(R.mipmap.app_icon);
        name.setText(student.getStudentName());
        degree.setText(student.getStudentCareer());
        birth.setText(student.getStudentBirthdate().toString());
        gender.setText(student.getStudentGender());

        for (String a : student.getStudentSubjects()) {
            subject += a + "\n";
        }

        subjects.setText(subject);

    }
}