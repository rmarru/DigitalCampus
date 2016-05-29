package com.example.sanfe.digitalcampus.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Subject;

import java.io.Serializable;

public class CreateSubject1Activity extends AppCompatActivity {
//Controlar que 2 assignatures no es diguin igual
//Falta ActionBar i Hints
    //Max caràcters de descripció?
    public static Subject subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createsubject1);

        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();

        final EditText title = (EditText) findViewById(R.id.createsubject1_titlefield);
        final EditText description = (EditText) findViewById(R.id.createsubject1_descriptionfield);
        Button continue_button = (Button) findViewById(R.id.createsubject1_nextbutton);
        subject = new Subject();

        if (bundle != null) {
            Subject subject1 = (Subject) bundle.get("SUBJECT2");
            try {
                title.setText(subject1.getSubjectTitle());
                description.setText(subject1.getSubjectDescription());
                subject = new Subject (subject1.getSubjectStudents(), subject1.getSubjectThemes());
            }catch(Exception e){}
        }

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject.setSubjectTitle(title.getText().toString());
                subject.setSubjectDescription(description.getText().toString());
                Intent intent = new Intent (getApplicationContext(), CreateSubject2Activity.class);
                intent.putExtra("SUBJECT1", subject);
                startActivity(intent);
                finish();
            }
        });
    }

    public void onBackPressed() {
        this.startActivity(new Intent(CreateSubject1Activity.this, SubjectManagerActivity.class));
        finish();
    }
}
