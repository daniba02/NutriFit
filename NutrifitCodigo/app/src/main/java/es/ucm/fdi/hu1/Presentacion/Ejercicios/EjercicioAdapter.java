package es.ucm.fdi.hu1.Presentacion.Ejercicios;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.ServiceFactory;
import es.ucm.fdi.hu1.R;

public class EjercicioAdapter extends RecyclerView.Adapter<EjercicioAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private List<EjercicioInfo> ejersList;
    private List<EjercicioInfo> ejersList_original;

    private String search;
    List<String> grupoMuscFilter;
    List<String> TipoEjersFilter;


    public EjercicioAdapter(Context context, List<EjercicioInfo> ejersList){
        this.inflater = LayoutInflater.from(context);
        this.ejersList = ejersList;
        this.ejersList_original = new ArrayList<>();
        ejersList_original.addAll(ejersList);
        grupoMuscFilter = new ArrayList<>();
        TipoEjersFilter = new ArrayList<>();
        search = "";
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = ejersList.get(position).getTitle();
        holder.nTitle.setText(title);

    }

    public void filtrado(){
        int longitud = search.length();
        ejersList.clear();
        if(longitud == 0 && grupoMuscFilter.isEmpty() && TipoEjersFilter.isEmpty()){
            //ejersList.clear();
            ejersList.addAll(ejersList_original);
        }
        else {

            for(EjercicioInfo e: ejersList_original){
                if(e.getTitle().toLowerCase().contains(search.toLowerCase())
                        && !ejersList.contains(e) && (grupoMuscFilter.contains(e.getGrupoMuscular().toString()) || grupoMuscFilter.isEmpty()) &&
                        (TipoEjersFilter.contains(e.getTipo().toString()) || TipoEjersFilter.isEmpty())){
                    ejersList.add(e);
                }
                else if(e.getTitle().toLowerCase().contains(search.toLowerCase())
                        && ejersList.contains(e) && (!grupoMuscFilter.contains(e.getGrupoMuscular().toString())) &&
                        (!TipoEjersFilter.contains(e.getTipo().toString()))){
                    ejersList.remove(e);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return ejersList.size();
    }

    public void setEjers(List<EjercicioInfo> ejs) {
        this.ejersList = ejs;
        this.ejersList_original = new ArrayList<>(ejs);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nTitle;

        ImageView deleteButton;

        ImageView updateButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.eTitle);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            updateButton = itemView.findViewById(R.id.editButton);

            itemView.setOnClickListener(v -> {

                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("ID", ejersList.get(getAdapterPosition()).getID());
                v.getContext().startActivity(intent);

            });


            deleteButton.setOnClickListener((v) -> {

                long id = ejersList.get(getAdapterPosition()).getID();

                boolean ok = ServiceFactory.getInstance().generateEjercicioService().eliminarEjercicio(id);

                if(ok){
                    Toast.makeText(v.getContext(), "Ejercicio eliminado con éxito", Toast.LENGTH_SHORT).show();
                    EjercicioInfo ejInfo = ejersList.get(getAdapterPosition());
                    ejersList.remove(ejInfo);
                    ejersList_original.remove(ejInfo);
                    notifyItemRemoved(getAdapterPosition());
                }
                else{
                    Toast.makeText(v.getContext(), "Error al eliminar ejercicio: ejercicio vinculado a rutina", Toast.LENGTH_SHORT).show();
                }

            });

            updateButton.setOnClickListener((v) -> {

                long id = ejersList.get(getAdapterPosition()).getID();

                Intent intent = new Intent(v.getContext(), UpdateEjerActivity.class);
                intent.putExtra("idejer", id);
                v.getContext().startActivity(intent);


            });


        }
    }

    public void anadirFiltroG(String filtro){
        grupoMuscFilter.add(filtro);
    }
    public void eliminarFiltroG(String filtro){
        grupoMuscFilter.remove(filtro);
    }
    public void anadirFiltroT(String filtro){
        TipoEjersFilter.add(filtro);
    }
    public void eliminarFiltroT(String filtro){
        TipoEjersFilter.remove(filtro);
    }


    public void setSearchFilter(String text){
        this.search = text;
    }

}
