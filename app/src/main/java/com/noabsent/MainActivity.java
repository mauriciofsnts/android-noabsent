package com.noabsent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.noabsent.activity.RegisterPresence;
import com.noabsent.dao.StudentDAO;


public class MainActivity extends AppCompatActivity {

    EditText textFieldUserRGM;
    EditText textFieldPassword;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        textFieldUserRGM = (EditText) findViewById(R.id.textFieldUserRGM);
        textFieldPassword = (EditText) findViewById(R.id.textFieldPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    System.out.println("errordebug" + e);
                }


                String userRGM = textFieldUserRGM.getText().toString();
                String userPass = textFieldPassword.getText().toString();

                StudentDAO dao = new StudentDAO();

                boolean isValid = dao.LoginUser(userRGM, userPass);

                if (!isValid) {
                    Context context = getApplicationContext();
                    CharSequence text = "Usuário inválido!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    startActivity(new Intent(MainActivity.this, RegisterPresence.class));
                }


            }
        });

    }
}