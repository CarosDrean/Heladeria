package com.drean.projects.heladeria.presenter;

import android.app.Activity;

import com.drean.projects.heladeria.R;
import com.drean.projects.heladeria.pojo.Producto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Auxiliar {

    private Activity activity;

    public Auxiliar(Activity activity) {
        this.activity = activity;
    }

    public String obtenerFecha() {
        GregorianCalendar calendario = new GregorianCalendar();
        return formatearFecha (calendario.get(Calendar.DAY_OF_MONTH),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.YEAR));
    }

    public String formatearFecha(int dia, int mes, int ano) {
        return (String.format("%02d", dia)
                + "/" + String.format("%02d", mes + 1)
                + "/" + ano);
    }

    public ArrayList<Producto> inicializarDatos(){
        // datos domi
        ArrayList<Producto> productos = new ArrayList<>();

        productos.add(new Producto(activity.getString(R.string.name_uno), R.drawable.heladouno, 10.00, activity.getString(R.string.descrip_uno), "Grande"));
        productos.add(new Producto(activity.getString(R.string.name_dos), R.drawable.heladodos, 20.00, activity.getString(R.string.descrip_dos), "Grande"));
        productos.add(new Producto(activity.getString(R.string.name_tres), R.drawable.heladotres, 30.00, activity.getString(R.string.descrip_tres), "Grande"));
        productos.add(new Producto(activity.getString(R.string.name_cuatro), R.drawable.heladocuatro, 40.00, activity.getString(R.string.descrip_cuatro), "Grande"));
        productos.add(new Producto(activity.getString(R.string.name_cinco), R.drawable.defecto, 8.00, activity.getString(R.string.descrip_cinco), "Pequeno"));

        return productos;
    }
}
