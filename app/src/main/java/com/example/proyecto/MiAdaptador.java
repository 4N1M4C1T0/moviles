package com.example.proyecto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    private List<Pedido> misDatos;

    public MiAdaptador(List<Pedido> misDatos) {
        this.misDatos = misDatos;
    }

    @NonNull
    @Override
    public MiAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_pedidos, parent,false);

        ViewHolder miViewHolder = new MiAdaptador.ViewHolder(vista);
        return miViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.ViewHolder holder, int position) {
        holder.traerDatos(misDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return misDatos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUbicacion,tvEstado,tvIdPedido,tvtelefono;

        public ViewHolder(View vista) {
            super(vista);

                tvUbicacion = vista.findViewById(R.id.tvUbicacion);
                tvEstado = vista.findViewById(R.id.tvEstado);
                tvIdPedido = vista.findViewById(R.id.tvIdPedido);
                tvtelefono = vista.findViewById(R.id.tvtelefono);
        }

        public void traerDatos(Pedido pedido) {

            tvUbicacion.setText(pedido.getUbicacion());
            tvEstado.setText(pedido.getEstado());
            tvIdPedido.setText(pedido.getId());
            tvtelefono.setText(pedido.getTelefono());
        }
    }
}
