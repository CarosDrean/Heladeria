package com.drean.projects.heladeria.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.drean.projects.heladeria.DetalleProducto;
import com.drean.projects.heladeria.R;
import com.drean.projects.heladeria.pojo.Producto;

import java.util.ArrayList;

public class AdapterProducto extends RecyclerView.Adapter<AdapterProducto.ProductoViewHolder> {

    private ArrayList<Producto> productos;
    private Activity activity;

    public AdapterProducto(ArrayList<Producto> productos, Activity activity) {
        this.productos = productos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inicio, parent, false);
        return new ProductoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        final Producto item = productos.get(position);
        holder.nombre.setText(item.getNombre());
        holder.precio.setText("" + item.getPrecio());
        Glide.with(activity).load(item.getImagen()).into(holder.imagen);
        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, DetalleProducto.class);
                i.putExtra("helado", item.getNombre());
                i.putExtra("descripcion", item.getDescripcion());
                i.putExtra("precio", item.getPrecio());
                i.putExtra("portada", item.getImagen());
                activity.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagen;
        private TextView precio;
        private TextView nombre;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre_comida);
            precio = itemView.findViewById(R.id.precio_comida);
            imagen = itemView.findViewById(R.id.miniatura_comida);
        }
    }
}
