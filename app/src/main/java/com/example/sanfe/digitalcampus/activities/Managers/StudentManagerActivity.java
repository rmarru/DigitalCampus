package com.example.sanfe.digitalcampus.activities.Managers;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.activities.Creators.CreateStudentActivity;
import com.example.sanfe.digitalcampus.activities.StartApp.MenuActivity;
import com.example.sanfe.digitalcampus.activities.Show.ShowStudentActivity;
import com.example.sanfe.digitalcampus.adapters.StudentManagerAdapter;
import com.example.sanfe.digitalcampus.logic.data.Singleton;
import com.example.sanfe.digitalcampus.logic.data.Student;
import com.example.sanfe.digitalcampus.logic.data.Subject;
import com.example.sanfe.digitalcampus.logic.dataManager.SharedPreferencesManager;

import java.util.ArrayList;
//Canviar el concatenat del adapter
//Els noms no apareixen complets a vegades
public class StudentManagerActivity extends AppCompatActivity {
    public static final String SELECTION_TEXT = "Selecciona un alumno:";
    public static ArrayList<Student> list;
    public static StudentManagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectmanager);

        TextView title = (TextView) findViewById (R.id.subjectmanager_selectiontext);
        title.setText(SELECTION_TEXT);

        list = Singleton.getInstance().getStudentList();

        ListView listview = (ListView) findViewById (R.id.subjectmanager_list);
        adapter = new StudentManagerAdapter(this, list, getResources().getString(R.string.title_elimination), getResources().getString(R.string.text2_elimination));
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle extras = new Bundle();
                Intent intent = new Intent(getApplicationContext(), ShowStudentActivity.class);
                extras.putSerializable("STUDENT", list.get(position));
                extras.putInt("FROM", 0);
                intent.putExtras(extras);
                startActivity(intent);
                finish();
            }
        });
    }

    public static void eliminaAlumno(int position) {

        try {
            for (String subject : list.get(position).getStudentSubjects()) {
                for (Subject subjectSingleton : Singleton.getInstance().getSubjectList()) {
                    if (subject.equals(subjectSingleton.getSubjectTitle())) {
                        int sIndex = Singleton.getInstance().getSubjectList().indexOf(subjectSingleton);
                        Singleton.getInstance().getSubjectList().get(sIndex).removeSubjectStudent(list.get(position));
                    }
                }
            }
        }catch (Exception e) {}
        Singleton.getInstance().removeStudent(list.get(position));
        SharedPreferencesManager.updateSubjectsJSON();
        SharedPreferencesManager.updateStudentsJSON();

        adapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.subjectmanager_actionbar, menu);

        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
        Bitmap new_icon = MenuActivity.resizeBitmapImageFn(icon, 72);
        Drawable d = new BitmapDrawable(getResources(), new_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(d);

        getSupportActionBar().setTitle("  " + getResources().getString(R.string.app_name));
        getSupportActionBar().setSubtitle("   " + getResources().getString(R.string.studentmanager_actionbar));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.create_subject:
                Intent intent = new Intent(getApplicationContext(), CreateStudentActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        finish();
    }
}
