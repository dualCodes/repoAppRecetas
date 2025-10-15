package com.example.tarearecetas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.RecetaViewHolder> {

    private List<Receta> listaRecetas;

    public RecetaAdapter(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    @NonNull
    @Override
    public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);
        return new RecetaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {
        Receta receta = listaRecetas.get(position);
        holder.nombreReceta.setText("Nombre: " + receta.getNombre());
        holder.categoriaReceta.setText("Categoría: " + receta.getCategoria());
        holder.dificultadReceta.setText("Dificultad: " + receta.getDificultad());
        holder.tiempoReceta.setText("Tiempo: " + receta.getTiempo() + " minutos");
        holder.ingredientesReceta.setText("Ingredientes: " + String.join(", ", receta.getIngredientes()));
    }

    @Override
    public int getItemCount() {
        return listaRecetas.size();
    }

    public static class RecetaViewHolder extends RecyclerView.ViewHolder {
        TextView nombreReceta;
        TextView categoriaReceta;
        TextView dificultadReceta;
        TextView tiempoReceta;
        TextView ingredientesReceta;

        public RecetaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreReceta = itemView.findViewById(R.id.nombre_receta);
            categoriaReceta = itemView.findViewById(R.id.categoria_receta);
            dificultadReceta = itemView.findViewById(R.id.dificultad_receta);
            tiempoReceta = itemView.findViewById(R.id.tiempo_receta);
            ingredientesReceta = itemView.findViewById(R.id.ingredientes_receta);
        }
    }
}
