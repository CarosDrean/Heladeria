package com.drean.projects.heladeria;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.drean.projects.heladeria.pojo.Pedido;
import com.drean.projects.heladeria.presenter.Auxiliar;
import com.drean.projects.heladeria.presenter.RegistroPedidos;

public class DetalleProducto extends AppCompatActivity {

    TextView igv;
    TextView precio;
    TextView total;
    TextView cantidad;
    boolean habilitar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getIntent().getStringExtra("helado"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        com.robertlevonyan.views.customfloatingactionbutton.FloatingActionButton fab = findViewById(R.id.fab_pedir);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertCantidad();
            }
        });

        reemplazar();
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

    private void alertCantidad() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.alert_pedido, null);
        datosPedido(v);
        builder.setView(v);
        builder.setPositiveButton("Pedir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(habilitar) {
                    createPedido();
                    finish();
                } else {
                    Toast.makeText(DetalleProducto.this, "¡Asigne una cantidad mayor a 0!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private Pedido pedido() {
        SharedPreferences prefs = getSharedPreferences("DatosUser",Context.MODE_PRIVATE);
        String nombre = prefs.getString("nombre", "Defecto");
        String celular = prefs.getString("telefono", "955555555");

        Auxiliar aux = new Auxiliar();
        return new Pedido(
                (int) System.currentTimeMillis(),
                getIntent().getStringExtra("helado"),
                Integer.toString(getIntent().getIntExtra("portada", R.drawable.defecto)),
                nombre,
                Integer.parseInt(celular),
                Integer.parseInt(cantidad.getText().toString()),
                Double.parseDouble(precio.getText().toString()),
                Double.parseDouble(igv.getText().toString()),
                aux.obtenerFecha(),
                Double.parseDouble(total.getText().toString())
        );
    }

    private void createPedido() {
        RegistroPedidos registro = new RegistroPedidos(this);
        registro.createPedido(pedido());
    }

    private void datosPedido(View v){
        igv = v.findViewById(R.id.tv_igv);
        precio = v.findViewById(R.id.tv_precio);
        precio.setText("" + getIntent().getDoubleExtra("precio", 00.00));
        total = v.findViewById(R.id.tv_total);
        Button mas = v.findViewById(R.id.btn_mas);
        Button menos = v.findViewById(R.id.btn_menos);
        cantidad = v.findViewById(R.id.cantidad_c);

        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cant = cantidad.getText().toString();
                if(Integer.parseInt(cant) > 1) {
                    cantidad.setText("" + (Integer.parseInt(cant) - 1));
                    calcular();
                }
            }
        });

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cant = cantidad.getText().toString();
                cantidad.setText("" + (Integer.parseInt(cant) + 1));
                calcular();
                habilitar = true;
            }
        });
    }

    public void calcular() {
        double precio = getIntent().getDoubleExtra("precio", 00.00);

        String cant = cantidad.getText().toString();
        int cantidad = Integer.parseInt(cant);
        double subtotal = precio * cantidad;
        double igv = subtotal * 0.18;
        double total = (precio * cantidad) + igv;

        this.igv.setText("" + igv);
        this.total.setText("" + total);
    }

    private void reemplazar() {
        ImageView portada = findViewById(R.id.portada_dp);
        TextView descripcion = findViewById(R.id.descripcion_dp);
        TextView precio = findViewById(R.id.precio_dp);
        descripcion.setText(getIntent().getStringExtra("descripcion"));
        precio.setText("S/." + getIntent().getDoubleExtra("precio", 00.00));
        Glide.with(this).load(getIntent().getIntExtra("portada", R.drawable.defecto)).into(portada);
    }
}
