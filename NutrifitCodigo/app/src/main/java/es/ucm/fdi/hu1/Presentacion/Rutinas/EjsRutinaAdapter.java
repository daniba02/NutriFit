package es.ucm.fdi.hu1.Presentacion.Rutinas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaInfo;
import es.ucm.fdi.hu1.Negocio.Rutinas.Rutina_ejercicioInfo;
import es.ucm.fdi.hu1.Negocio.ServiceFactory;
import es.ucm.fdi.hu1.R;

public class EjsRutinaAdapter extends RecyclerView.Adapter<EjsRutinaAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<EjercicioInfo> ejesList;

    private final long id_rut;

    private Context context;

    public EjsRutinaAdapter(Context context, List<EjercicioInfo> ejersList, long id_rut){
        this.inflater = LayoutInflater.from(context);
        this.ejesList = ejersList;
        this.id_rut = id_rut;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list_view_ejs_rut, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = ejesList.get(position).getTitle();
        holder.nTitle.setText(title);

    }

    @Override
    public int getItemCount() {
        return ejesList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder  implements DataEjRutDialog.DataEjRutListener {
        private TextView nTitle;

        private EjercicioInfo ejInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nTitle = itemView.findViewById(R.id.eTitle);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DetailsActivityRutina.class);
                intent.putExtra("ID", ejesList.get(getAdapterPosition()).getID());
                v.getContext().startActivity(intent);
            });

            ImageView addbutton = itemView.findViewById(R.id.addButton);
            addbutton.setOnClickListener((v) -> {
                ejInfo = ejesList.get(getAdapterPosition());

                DataEjRutDialog dialogFragment = new DataEjRutDialog();
                // Establecer el adaptador como el listener para los datos ingresados en el diálogo
                dialogFragment.setDataEjRutListener(this);
                // Mostrar el diálogo
                dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "DataEjRutDialog");


            });

        }

        @Override
        public void onDataEntered(int peso, int reps, int series, String desc) {

            long id_ej = ejInfo.getID();

            Rutina_ejercicioInfo input = new Rutina_ejercicioInfo(id_rut, id_ej, reps, series, peso, desc);

            boolean ok = ServiceFactory.getInstance().generateRutinaService().VincularRutina(input);

            if (ok) {
                Toast.makeText(context, "¡Ejercicio guardado con éxito!", Toast.LENGTH_SHORT).show();
                ejesList.remove(ejInfo);
                notifyItemRemoved(getAdapterPosition());
            } else {
                Toast.makeText(context, "Error al guardar el ejercicio en la rutina", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFormatError(int errorCode) {
            switch (errorCode){
                case -1:
                    Toast.makeText(context, "Error: Los minutos deben estar entre 0 y 60", Toast.LENGTH_SHORT).show();
                    break;
                case -2:
                    Toast.makeText(context, "Error: Los segundos deben estar entre 0 y 60", Toast.LENGTH_SHORT).show();
                    break;
            }
        }


    }
}
