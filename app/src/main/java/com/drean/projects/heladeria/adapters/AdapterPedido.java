package com.drean.projects.heladeria.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.drean.projects.heladeria.DetalleProducto;
import com.drean.projects.heladeria.R;
import com.drean.projects.heladeria.db.DataBase;
import com.drean.projects.heladeria.fragments.Inicio;
import com.drean.projects.heladeria.pojo.Pedido;
import com.drean.projects.heladeria.presenter.Auxiliar;
import com.drean.projects.heladeria.presenter.RegistroPedidos;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterPedido extends RecyclerView.Adapter<AdapterPedido.PedidoViewHolder> {

    private ArrayList<Pedido> pedidos;
    private Activity activity;

    private TextView igv;
    private TextView precio;
    private TextView total;
    private TextView cantidad;

    public AdapterPedido(ArrayList<Pedido> pedidos, Activity activity) {
        this.pedidos = pedidos;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);
        return new PedidoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder pedidoViewHolder, final int position) {
        final Pedido pedido = pedidos.get(position);
        pedidoViewHolder.titulo.setText(pedido.getNombre());
        pedidoViewHolder.subTitulo.setText(pedido.getNombreHelado());
        pedidoViewHolder.precio.setText("" + pedido.getTotal());
        Glide.with(activity).load(Integer.parseInt(pedido.getImgHelado())).into(pedidoViewHolder.perfil);

        pedidoViewHolder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // aqui editar
                alertCantidad(pedido);
            }
        });

    }

    private void alertCantidad(final Pedido pedido) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View v = activity.getLayoutInflater().inflate(R.layout.alert_pedido, null);
        datosPedido(v, pedido);
        builder.setView(v);
        builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updatePedido(pedido);
                Toast.makeText(activity, "¡Cantidad Actualizada!", Toast.LENGTH_SHORT).show();
                ((FragmentActivity)activity).getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new Inicio()).commit();
                dialog.dismiss();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }

    private void updatePedido(Pedido pedidoA) {
        RegistroPedidos registro = new RegistroPedidos(activity);
        registro.updatePedido(pedido(pedidoA));
    }

    private Pedido pedido(Pedido pedido) {
        SharedPreferences prefs = activity.getSharedPreferences("DatosUser",Context.MODE_PRIVATE);
        String nombre = prefs.getString("nombre", "Defecto");
        String celular = prefs.getString("telefono", "955555555");

        Auxiliar aux = new Auxiliar(activity);
        return new Pedido(
                pedido.getId(),
                pedido.getNombreHelado(),
                pedido.getImgHelado(),
                nombre,
                Integer.parseInt(celular),
                Integer.parseInt(cantidad.getText().toString()),
                Double.parseDouble(precio.getText().toString()),
                Double.parseDouble(igv.getText().toString()),
                aux.obtenerFecha(),
                Double.parseDouble(total.getText().toString())
        );
    }

    private void datosPedido(View v, final Pedido pedido){
        igv = v.findViewById(R.id.tv_igv);
        igv.setText("" + pedido.getIgv());
        precio = v.findViewById(R.id.tv_precio);
        precio.setText("" + pedido.getPrecioUnidad());
        total = v.findViewById(R.id.tv_total);
        total.setText("" + pedido.getTotal());
        Button mas = v.findViewById(R.id.btn_mas);
        Button menos = v.findViewById(R.id.btn_menos);
        cantidad = v.findViewById(R.id.cantidad_c);
        cantidad.setText("" + pedido.getCantidad());

        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cant = cantidad.getText().toString();
                if(Integer.parseInt(cant) > 1) {
                    cantidad.setText("" + (Integer.parseInt(cant) - 1));
                    calcular(pedido.getPrecioUnidad());
                }
            }
        });

        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cant = cantidad.getText().toString();
                cantidad.setText("" + (Integer.parseInt(cant) + 1));
                calcular(pedido.getPrecioUnidad());
            }
        });
    }

    public void calcular(double precioA) {
        double precio = precioA;

        String cant = cantidad.getText().toString();
        int cantidad = Integer.parseInt(cant);
        double subtotal = precio * cantidad;
        double igv = subtotal * 0.18;
        double total = (precio * cantidad) + igv;

        this.igv.setText("" + igv);
        this.total.setText("" + total);
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo;
        private TextView subTitulo;
        private TextView precio;
        private CircleImageView perfil;
        private ImageView editar;
        public CardView contenedor;

        public PedidoViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.title_pedido);
            subTitulo = itemView.findViewById(R.id.descrip_pedido);
            precio = itemView.findViewById(R.id.precio_pedido);
            perfil = itemView.findViewById(R.id.ic_pedido);
            editar = itemView.findViewById(R.id.editar);
            contenedor = itemView.findViewById(R.id.contenedor_pedido);
        }
    }

    public void removeItem(int position){
        int id = pedidos.get(position).getId();
        pedidos.remove(position);
        notifyItemRemoved(position);
        DataBase db = new DataBase(activity);
        db.deleteItem(id);
        Toast.makeText(activity, "¡Pedido Eliminado!", Toast.LENGTH_SHORT).show();
    }
}
