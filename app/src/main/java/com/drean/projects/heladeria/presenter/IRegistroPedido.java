package com.drean.projects.heladeria.presenter;

import com.drean.projects.heladeria.pojo.Pedido;

import java.util.ArrayList;

public interface IRegistroPedido {
    public void createPedido(Pedido pedido);
    public void updatePedido(Pedido pedido);
    public ArrayList<Pedido> getPedidos();
    public void deletePedido(int id);
}
