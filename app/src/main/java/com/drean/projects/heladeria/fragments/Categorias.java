package com.drean.projects.heladeria.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drean.projects.heladeria.R;
import com.drean.projects.heladeria.adapters.AdapterCategoria;
import com.drean.projects.heladeria.adapters.AdapterProducto;
import com.drean.projects.heladeria.pojo.Producto;
import com.drean.projects.heladeria.presenter.Auxiliar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Categorias extends Fragment {

    ArrayList<Producto> productos;
    private RecyclerView listaproductos;

    public Categorias() {
        // Required empty public constructor
    }

    public static Categorias nuevaInstancia(int indiceSeccion) {
        Categorias fragment = new Categorias();
        Bundle args = new Bundle();
        args.putInt("1", indiceSeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_categorias, container, false);

        listaproductos = v.findViewById(R.id.reciclador);

        GridLayoutManager llm = new GridLayoutManager(getContext(), 2);
        listaproductos.setLayoutManager(llm);

        productos = new ArrayList<>();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        cargarDatos();
        inicializarAdaptador();
    }

    public void inicializarAdaptador(){
        AdapterCategoria adaptador = new AdapterCategoria(productos, getActivity());
        listaproductos.setAdapter(adaptador);
    }

    private void cargarDatos() {
        int indiceSeccion = getArguments().getInt("1");

        switch (indiceSeccion) {
            case 0:
                datos("Grande");
                inicializarAdaptador();
                break;
            case 1:
                datos("Pequeno");
                inicializarAdaptador();
                break;
        }
    }

    public void datos(String categoria){
        productos.clear();
        for (Producto item: inicializar()) {
            if(item.getCategoria().equals(categoria)){
                this.productos.add(item);
            }
        }
    }

    public ArrayList<Producto> inicializar() {
        Auxiliar aux = new Auxiliar();
        return aux.inicializarDatos();
    }

}
