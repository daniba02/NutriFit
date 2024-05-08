package es.ucm.fdi.hu1.Presentacion.Rutinas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Presentacion.Ejercicios.DetailActivity;
import es.ucm.fdi.hu1.R;

public class ShowInfoRutAdapter extends RecyclerView.Adapter<ShowInfoRutAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<EjercicioInfo> ejersList;

    public ShowInfoRutAdapter(Context context, List<EjercicioInfo> ejersList){
        this.inflater = LayoutInflater.from(context);
        this.ejersList = ejersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view_ruts, parent, false);
        return new es.ucm.fdi.hu1.Presentacion.Rutinas.ShowInfoRutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowInfoRutAdapter.ViewHolder holder, int position) {
        String title = ejersList.get(position).getTitle();
        holder.nTitle.setText(title);

    }

    @Override
    public int getItemCount() {
        return ejersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.eTitle);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("ID",ejersList.get(getAdapterPosition()).getID());
                v.getContext().startActivity(intent);
            });
        }
    }
}
