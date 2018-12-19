package com.drean.projects.heladeria.db;

import android.content.ContentValues;
import android.content.Context;

import com.drean.projects.heladeria.pojo.Pedido;

import java.util.ArrayList;

public class ConstructorRegistros {
    private Context context;

    public ConstructorRegistros(Context context) {
        this.context = context;
    }

    public ArrayList<Pedido> getItems() {
        DataBase db = new DataBase(context);
        return db.getItems();
    }

    private ContentValues values(Pedido pedido) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.ID, pedido.getId());
        contentValues.put(ConstantesBaseDatos.HELADO, pedido.getNombreHelado());
        contentValues.put(ConstantesBaseDatos.HELADO_IMG, pedido.getImgHelado());
        contentValues.put(ConstantesBaseDatos.CANTIDAD, pedido.getCantidad());
        contentValues.put(ConstantesBaseDatos.CELULAR, pedido.getCelular());
        contentValues.put(ConstantesBaseDatos.FECHA, pedido.getFecha());
        contentValues.put(ConstantesBaseDatos.IGV, pedido.getIgv());
        contentValues.put(ConstantesBaseDatos.NOMBRE, pedido.getNombre());
        contentValues.put(ConstantesBaseDatos.PRECIO_UNIDAD, pedido.getPrecioUnidad());
        contentValues.put(ConstantesBaseDatos.TOTAL, pedido.getTotal());
        return contentValues;
    }

    public void createItem(Pedido pedido) {
        DataBase db = new DataBase(context);
        db.createItem(values(pedido));
    }

    public void updateItem(Pedido pedido, int id) {
        DataBase db = new DataBase(context);
        db.updateItem(values(pedido), id);
    }

    public void deleteItem(int id) {
        DataBase db = new DataBase(context);
        db.deleteItem(id);
    }
}
