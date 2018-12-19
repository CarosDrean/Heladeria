package com.drean.projects.heladeria.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drean.projects.heladeria.R;
import com.drean.projects.heladeria.adapters.AdapterPedido;
import com.drean.projects.heladeria.adapters.RecyclerItemTouchHelper;
import com.drean.projects.heladeria.pojo.Pedido;
import com.drean.projects.heladeria.presenter.RegistroPedidos;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Pedidos extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    ArrayList<Pedido> pedidos;
    private RecyclerView listapedidos;
    AdapterPedido adaptadorPedido;

    public Pedidos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pedidos, container, false);
        listapedidos = v.findViewById(R.id.lista_pedidos);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listapedidos.setLayoutManager(llm);

        RecyclerItemTouchHelper itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(listapedidos);

        inicializar();
        inicializarAdaptador();
        return v;
    }

    public void inicializarAdaptador(){
        adaptadorPedido = new AdapterPedido(pedidos, getActivity());
        listapedidos.setAdapter(adaptadorPedido);
    }

    public void inicializar(){
        pedidos = new ArrayList<>();
        RegistroPedidos registro = new RegistroPedidos(getActivity());
        pedidos = registro.getPedidos();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        adaptadorPedido.removeItem(viewHolder.getAdapterPosition());
    }
}
