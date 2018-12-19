package com.drean.projects.heladeria.presenter;

import com.drean.projects.heladeria.R;
import com.drean.projects.heladeria.pojo.Producto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Auxiliar {
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

        // para tener mas elementos solo se deben duplicar estas lineas y cambiar los datos
        // las categorias solo pueden ser: Grande/Pequeno (exactamente como esta escrito, primera letra matyscula)
        productos.add(new Producto("title", R.drawable.defecto, 30.00, "descripcion", "Grande"));
        productos.add(new Producto("title2", R.drawable.defecto, 30.00, "descripcion2", "Grande"));
        productos.add(new Producto("title3", R.drawable.defecto, 30.00, "descripcion3", "Pequeno"));

        return productos;
    }
}
