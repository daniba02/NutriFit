package es.ucm.fdi.hu1.Presentacion.Rutinas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import es.ucm.fdi.hu1.R;

public class DataEjRutDialog extends DialogFragment {

    public interface DataEjRutListener {
        void onDataEntered(int value1, int value2, int value3, String value4);
        void onFormatError(int errorCode);
    }

    private DataEjRutListener  mListener;
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Inflar el diseño personalizado
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_data_ej_rut, null);

        // Obtener referencias a los EditText
        EditText peso = dialogView.findViewById(R.id.dialogDataPeso);
        EditText reps = dialogView.findViewById(R.id.dialogDataReps);
        EditText series = dialogView.findViewById(R.id.dialogDataSeries);
        EditText descamins = dialogView.findViewById(R.id.dialogDataDescansoMins);
        EditText descasegs = dialogView.findViewById(R.id.dialogDataDescansoSec);


        peso.setText("0");
        reps.setText("0");
        series.setText("0");

        builder.setView(dialogView)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Obtenenemos los valores de texto de los EditText
                int value1;
                int value2;
                int value3;
                String value4;

                //Usamos una funcion adicional para parsearlos
                value1 = integerParseFromEditText(peso);

                value2 = integerParseFromEditText(reps);

                value3 = integerParseFromEditText(series);


                if(descamins.getText().toString().equalsIgnoreCase(""))
                    value4 = "0";
                else
                    value4 = descamins.getText().toString();

                value4 = value4 + ":";
                if(descasegs.getText().toString().equalsIgnoreCase(""))
                    value4 = value4 + "0";
                else
                    value4 = value4 + descasegs.getText().toString();

                int errorFormato = comprobarHora(value4);

                // Pasamos los valores de vuelta al adaptador a través de la interfaz
                if(errorFormato < 0)
                    mListener.onFormatError(errorFormato);
                else
                    mListener.onDataEntered(value1, value2, value3, value4);
            }
        });

        return builder.create();
    }

    // Método para configurar el listener
    public void setDataEjRutListener(DataEjRutListener listener) {
        mListener = listener;
    }


    private int comprobarHora(String hora){

        String[] valores = hora.split(":");

        int min = Integer.parseInt(valores[0]);

        int seg = Integer.parseInt(valores[1]);

        if(min < 0 || min > 60)
            return -1;
        if(seg < 0 || seg > 60)
            return -2;

        return 0;
    }

    int integerParseFromEditText(EditText text){
        if(text.getText().toString().equalsIgnoreCase(""))
            return 0;
        else
            return Integer.parseInt(text.getText().toString());
    }
}
