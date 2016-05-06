package com.example.sanfe.digitalcampus;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity  extends AppCompatActivity {

    public static final String TITLE = "Ha habido un error !";
    public static final String EMAIL = "administrador@salleurl.edu";
    public static final String PASSWORD = "123qwe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email = (EditText) findViewById(R.id.field_email);
        final EditText password = (EditText) findViewById(R.id.field_password);
        CheckBox remember_me = (CheckBox) findViewById(R.id.checkbox_remember_me);
        Button enter_button = (Button) findViewById(R.id.enter_button);
        final Context context = this;

        enter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isEmailValid(email.getText().toString())) {
                    AlertDialogWindow.errorMessage(context, TITLE, "El formato del email no es correcto.");
                }
                else {
                    if (!email.getText().toString().equals(EMAIL)) {
                        AlertDialogWindow.errorMessage(context, TITLE, "El email introducido no esta registrado.");
                    }
                    else {
                        if (!password.getText().toString().equals(PASSWORD)) {
                            AlertDialogWindow.errorMessage(context, TITLE, "La contraseña introducida es incorrecta");
                        }
                       /* else {
                            //activitat menú
                            //fer remember_me
                            //ficar textfield en blanc
                        }*/
                    }
                }

            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
