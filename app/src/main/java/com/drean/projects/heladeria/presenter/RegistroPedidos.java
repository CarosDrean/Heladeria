package com.drean.projects.heladeria.presenter;

import android.content.Context;

import com.drean.projects.heladeria.db.ConstructorRegistros;
import com.drean.projects.heladeria.pojo.Pedido;

import java.util.ArrayList;

public class RegistroPedidos implements IRegistroPedido {

    private Context context;
    private ConstructorRegistros constructorRegistros;
    private ArrayList<Pedido> pedidos;

    public RegistroPedidos(Context context) {
        this.context = context;
    }

    @Override
    public void createPedido(Pedido pedido) {
        constructorRegistros = new ConstructorRegistros(context);
        constructorRegistros.createItem(pedido);
    }

    @Override
    public void updatePedido(Pedido pedido) {
        constructorRegistros = new ConstructorRegistros(context);
        constructorRegistros.updateItem(pedido, pedido.getId());
    }

    @Override
    public ArrayList<Pedido> getPedidos() {
        constructorRegistros = new ConstructorRegistros(context);
        pedidos = constructorRegistros.getItems();
        return pedidos;
    }

    @Override
    public void deletePedido(int id) {
        constructorRegistros = new ConstructorRegistros(context);
        constructorRegistros.deleteItem(id);
    }
}