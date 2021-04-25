package com.noabsent.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.noabsent.R;


public class RegisterPresence extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;

    // this annotation because this core drequires android jelly bean
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_presence);

        autoCompleteTextView = findViewById(R.id.dropdownSelectClass);

        String []option = {"FUNDAMENTOS DE INTELIGÊNCIA ARTIFICIAL", "TRABALHO DE GRADUAÇÃO INTERDISCIPLINAR I", "LINGUAGENS FORMAIS E AUTÔMATOS", "PROGRAMAÇÃO PARA DISPOSITIVOS MÓVEIS"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.option, option);

        autoCompleteTextView.setText(arrayAdapter.getItem(0).toString(), false);

        autoCompleteTextView.setAdapter(arrayAdapter);
    }


}