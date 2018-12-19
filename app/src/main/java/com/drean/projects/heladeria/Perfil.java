package com.drean.projects.heladeria;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Perfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        datos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void datos() {
        SharedPreferences prefs = getSharedPreferences("DatosUser",Context.MODE_PRIVATE);
        String nombre = prefs.getString("nombre", "Defecto");
        String celular = prefs.getString("telefono", "955555555");
        TextView nombreTv = findViewById(R.id.p_nombre);
        TextView telefonoTv = findViewById(R.id.p_telefono);
        nombreTv.setText(nombre);
        telefonoTv.setText(celular);
    }
}
