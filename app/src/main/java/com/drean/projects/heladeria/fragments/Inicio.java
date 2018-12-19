package com.drean.projects.heladeria.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.drean.projects.heladeria.R;
import com.drean.projects.heladeria.adapters.AdapterProducto;
import com.drean.projects.heladeria.pojo.Producto;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inicio extends Fragment {

    ArrayList<Producto> productos;
    private RecyclerView listaproductos;

    public Inicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_inicio, container, false);
        setHasOptionsMenu(true);
        listaproductos = v.findViewById(R.id.lista_productos);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaproductos.setLayoutManager(llm);

        inicializar();
        inicializarAdaptador();
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_inicio, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint(getText(R.string.search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<Producto> newAutos = new ArrayList<>();
                inicializar();
                for (Producto item: productos) {
                    int i = item.getNombre().toLowerCase().indexOf(query);
                    if(i != -1){
                        newAutos.add(item);
                    }
                }
                productos = newAutos;
                inicializarAdaptador();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }

    public void inicializarAdaptador(){
        AdapterProducto adaptador = new AdapterProducto(productos, getActivity());
        listaproductos.setAdapter(adaptador);
    }

    public void inicializar(){
        productos = new ArrayList<>();

        productos.add(new Producto("oscar", R.drawable.defecto, 30.00, "descripcion", "Grande"));
        productos.add(new Producto("oscar2", R.drawable.defecto, 30.00, "descripcion2", "Grande"));
    }

}
