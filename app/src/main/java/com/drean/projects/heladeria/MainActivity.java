package com.drean.projects.heladeria;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.drean.projects.heladeria.fragments.Inicio;
import com.drean.projects.heladeria.fragments.Pedidos;
import com.drean.projects.heladeria.fragments.Productos;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        View vHome = navigationView.getHeaderView(0);
        if(navigationView  != null) {
            onNavigationItemSelected(navigationView.getMenu().getItem(0));
        }

        if(detectarUsoApp() == 0){
            dialogoBienvenida();
        }

        datosCabecera(vHome);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            alertAcercaDe();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        boolean manager = false;

        if (id == R.id.nav_inicio) {
            manager = true;
            fragment = new Inicio();
        } else if (id == R.id.nav_productos) {
            manager = true;
            fragment = new Productos();
        } else if (id == R.id.nav_pedidos) {
            manager = true;
            fragment= new Pedidos();
        } else if (id == R.id.nav_perfil) {
            Intent i = new Intent(this, Perfil.class);
            startActivity(i);
        }

        if(manager) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void dialogoBienvenida(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setTitle("Ingresar Datos")
                .setCancelable(false);
        View v = getLayoutInflater().inflate(R.layout.alert_ingresar_datos, null);
        final EditText nombre = v.findViewById(R.id.nombre_i);
        final EditText telefono = v.findViewById(R.id.telefono_i);
        builder.setView(v);
        builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!nombre.getText().toString().equals("") && !telefono.getText().toString().equals("")){
                    guardarDatos(nombre.getText().toString(), telefono.getText().toString());
                    dialog.dismiss();
                }else{
                    Toast.makeText(MainActivity.this, "¡Llene todos los campos!", Toast.LENGTH_LONG).show();
                }
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void guardarDatos(String nombre, String telefono) {
        SharedPreferences prefs = getSharedPreferences("DatosUser",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nombre", nombre);
        editor.putString("telefono", telefono);
        editor.apply();
        editor.commit();
    }

    private void alertAcercaDe(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Acerca de Heladerai Peña")
                .setMessage("Somos una nueva empresa de la ciudad de Ica, que les ofrece un servicio de calidad unica.");
        builder.setPositiveButton("Listo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void datosCabecera(View v){
        ImageView fondo = v.findViewById(R.id.fondo_cabecera);
        CircleImageView perfil = v.findViewById(R.id.perfil_cabecera);

        Glide.with(this).load(getResources().getDrawable(R.drawable.portada)).into(fondo);
        Glide.with(this).load(getResources().getDrawable(R.drawable.defecto)).into(perfil);
    }

    private int detectarUsoApp() {
        SharedPreferences sp = getSharedPreferences("MYAPP", 0);
        int result, currentVersionCode = BuildConfig.VERSION_CODE;
        int lastVersionCode = sp.getInt("FIRSTTIMERUN", -1);
        if (lastVersionCode == -1) result = 0; else
            result = (lastVersionCode == currentVersionCode) ? 1 : 2;
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply();
        return result;
    }
}
