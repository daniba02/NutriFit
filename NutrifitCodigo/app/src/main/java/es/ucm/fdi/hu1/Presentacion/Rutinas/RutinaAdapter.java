package es.ucm.fdi.hu1.Presentacion.Rutinas;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import es.ucm.fdi.hu1.DataAccess.DataBaseHelper;
import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaInfo;
import es.ucm.fdi.hu1.Negocio.Rutinas.Rutina_ejercicioInfo;
import es.ucm.fdi.hu1.R;

public class RutinaAdapter extends RecyclerView.Adapter<RutinaAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private List<RutinaInfo> rutslist;
    private List<RutinaInfo> rutslist_original;

    private HashSet<Long> rutinasAceptadas;

    private List<Long> listaid;

    private String search;

    List<String> grupoMuscFilter;

    public RutinaAdapter(Context context, List<RutinaInfo> rutslist){
        this.inflater = LayoutInflater.from(context);
        this.rutslist = rutslist;
        this.rutslist_original = new ArrayList<>();
        rutslist_original.addAll(rutslist);

        grupoMuscFilter = new ArrayList<>();
        search = "";

        rutinasAceptadas = new HashSet<>();

        listaid = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view_ruts, parent, false);
        return new es.ucm.fdi.hu1.Presentacion.Rutinas.RutinaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = rutslist.get(position).getTitle();
        holder.nTitle.setText(title);

    }

    public void filtrado(){
        int longitud = search.length();
        rutslist.clear();
        if(longitud == 0 && grupoMuscFilter.isEmpty() && listaid.isEmpty()){
            //ejersList.clear();
            rutslist.addAll(rutslist_original);
        }
        else {

            for(RutinaInfo e: rutslist_original){
                if(e.getTitle().toLowerCase().contains(search.toLowerCase())
                        && !rutslist.contains(e) && (grupoMuscFilter.contains(e.getGrupoMuscular().toString()) || grupoMuscFilter.isEmpty())
                        && (rutinasAceptadas.contains(e.getID()) || listaid.isEmpty())){
                    rutslist.add(e);
                }
                else if(!e.getTitle().toLowerCase().contains(search.toLowerCase())
                        || (rutslist.contains(e) && (!grupoMuscFilter.contains(e.getGrupoMuscular().toString())))
                        || !rutinasAceptadas.contains(e.getID())){
                    rutslist.remove(e);

                }

            }


        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return rutslist.size();
    }

    public void setRutinas(List<RutinaInfo> ruts) {
        this.rutslist = ruts;
        this.rutslist_original = new ArrayList<>(ruts);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.eTitle);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DetailsActivityRutina.class);
                intent.putExtra("ID",rutslist.get(getAdapterPosition()).getID());
                v.getContext().startActivity(intent);
            });
        }
    }

    public void anadirFiltro(String filtro){
        grupoMuscFilter.add(filtro);
    }
    public void eliminarFiltro(String filtro){
        grupoMuscFilter.remove(filtro);
    }

    public void setSearchFilter(String text){
        this.search = text;
    }

    public void anadirID(long id){
        listaid.add(id);
        List<Rutina_ejercicioInfo> list = DataBaseHelper.getInstance(null).getRutsDeEjs(id);
        for(Rutina_ejercicioInfo r : list){
            rutinasAceptadas.add(r.getID_rutina());
        };
    }
    public void eliminarID(long id){
        listaid.remove(id);
        List<Rutina_ejercicioInfo> list = DataBaseHelper.getInstance(null).getRutsDeEjs(id);
        for(Rutina_ejercicioInfo r : list){
            rutinasAceptadas.remove(r.getID_rutina());
        };
    }
}


