package com.drean.projects.heladeria.presenter;

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
}
