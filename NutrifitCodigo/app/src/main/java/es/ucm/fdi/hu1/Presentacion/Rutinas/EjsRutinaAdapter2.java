package es.ucm.fdi.hu1.Presentacion.Rutinas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.List;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaInfo;
import es.ucm.fdi.hu1.Negocio.Rutinas.Rutina_ejercicioInfo;
import es.ucm.fdi.hu1.Negocio.ServiceFactory;
import es.ucm.fdi.hu1.R;

public class EjsRutinaAdapter2 extends RecyclerView.Adapter<EjsRutinaAdapter2.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<EjercicioInfo> ejesList;
    private final long id_rut;

    public EjsRutinaAdapter2(Context context, List<EjercicioInfo> ejersList, long id_rut){
        this.inflater = LayoutInflater.from(context);
        this.ejesList = ejersList;
        this.id_rut = id_rut;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ejercicio_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EjercicioInfo ejer = ejesList.get(position);
        holder.checkBox.setText(ejer.getTitle());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Desactivar la interacción con el CheckBox después de hacer clic en él
            buttonView.setEnabled(false);

            // Aquí puedes realizar cualquier acción adicional según sea necesario
            // Por ejemplo, mostrar un mensaje de confirmación
            if (isChecked) {
                Toast.makeText(buttonView.getContext(), "Ejercicio seleccionado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(buttonView.getContext(), "Ejercicio deseleccionado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ejesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        TextView nTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            nTitle = itemView.findViewById(R.id.eTitle);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DetailsActivityRutina.class);
                intent.putExtra("ID",ejesList.get(getAdapterPosition()).getID());
                v.getContext().startActivity(intent);
            });

        }
    }
}
