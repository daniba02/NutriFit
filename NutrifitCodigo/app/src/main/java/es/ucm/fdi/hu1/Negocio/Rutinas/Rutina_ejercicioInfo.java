package es.ucm.fdi.hu1.Negocio.Rutinas;

public class Rutina_ejercicioInfo {

    private long ID_rutina;
    private long ID_ejer;
    private int num_repeticiones;
    private int num_series;
    private int peso;
    private String tiempo_descanso;
    private long ID;

    private int error;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {this.ID = ID;}

    public Rutina_ejercicioInfo(){}

    public Rutina_ejercicioInfo(long ID_r, long ID_e, int num_repeticiones, int num_series, int peso, String tiempo_descanso){
        this.ID_rutina = ID_r;
        this.ID_ejer = ID_e;
        this.num_repeticiones = num_repeticiones;
        this.num_series = num_series;
        this.peso = peso;
        this.tiempo_descanso = tiempo_descanso;

        this.error = 0;
    }

    public Rutina_ejercicioInfo(long id, long ID_r, long ID_e, int num_repeticiones, int num_series, int peso, String tiempo_descanso){
        this.ID_rutina = ID_r;
        this.ID_ejer = ID_e;
        this.num_repeticiones = num_repeticiones;
        this.num_series = num_series;
        this.peso = peso;
        this.tiempo_descanso = tiempo_descanso;
        this.ID = id;

        this.error = 0;
    }

    public long getID_ejer() {
        return ID_ejer;
    }
    public void setID_ejer(long ID_ejer) {
        this.ID_ejer = ID_ejer;
    }
    public long getID_rutina() {
        return ID_rutina;
    }
    public void setID_rutina(long ID_rutina) {
        this.ID_rutina = ID_rutina;
    }
    public int getNum_repeticiones() {
        return num_repeticiones;
    }
    public void setNum_repeticiones(int num_repeticiones) {this.num_repeticiones = num_repeticiones;}
    public int getNum_series() {return num_series;}
    public void setNum_series(int num_series) {this.num_series = num_series;}
    public int getPeso() {return peso;}
    public void setPeso(int peso) {this.peso = peso;}
    public String getTiempo_descanso() {return tiempo_descanso;}
    public void setTiempo_descanso(String tiempo_descanso) {this.tiempo_descanso = tiempo_descanso;}

    public int getError() {
        return error;
    }
    public void setError(int error) {
        this.error = error;
    }

    public boolean isCorrect(){
        return num_repeticiones > 0 && num_series > 0 && peso > 0 && validarFormatoTiempo(tiempo_descanso);
    }
    private static boolean validarFormatoTiempo(String tiempo) {
        // Dividir la cadena en minutos y segundos usando ":" como delimitador
        String[] partes = tiempo.split(":");

        // Comprobar si hay exactamente dos partes
        if (partes.length != 2) {
            return false;
        }

        try {
            // Convertir las partes a enteros
            int minutos = Integer.parseInt(partes[0]);
            int segundos = Integer.parseInt(partes[1]);

            // Comprobar si los minutos y segundos están en el rango válido
            if (minutos >= 0 && segundos >= 0 && segundos < 60) {
                return true;
            }
        } catch (NumberFormatException e) {
            // Error al convertir a números
            return false;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rutina_ejercicioInfo that = (Rutina_ejercicioInfo) o;
        return ID_rutina == that.ID_rutina && ID_ejer == that.ID_ejer && num_repeticiones == that.num_repeticiones
                && num_series == that.num_series && peso == that.peso && tiempo_descanso.equalsIgnoreCase(that.tiempo_descanso);
    }
}
