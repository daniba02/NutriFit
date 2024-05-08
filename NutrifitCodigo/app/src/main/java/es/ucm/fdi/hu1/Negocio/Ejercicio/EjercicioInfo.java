package es.ucm.fdi.hu1.Negocio.Ejercicio;

import androidx.annotation.Nullable;

import es.ucm.fdi.hu1.Negocio.GrupoMuscular;

public class EjercicioInfo {
    private long ID;
    private String title;
    private String content;
    private TipoEjercicio tipo;
    private GrupoMuscular grupoMuscular;
    private int error;

    public EjercicioInfo(){}
    public EjercicioInfo(String title, String content, TipoEjercicio tipo, GrupoMuscular grupoMuscular){
        this.title = title;
        this.content = content;
        this.tipo = tipo;
        this.grupoMuscular = grupoMuscular;
        this.error = 0;
    }
    public EjercicioInfo(long id ,String title, String content, TipoEjercicio tipo, GrupoMuscular grupoMuscular){
        this.ID = id;
        this.title = title;
        this.content = content;
        this.tipo = tipo;
        this.grupoMuscular = grupoMuscular;
        this.error = 0;
    }

    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public TipoEjercicio getTipo() {return tipo;}
    public void setTipo(TipoEjercicio tipo) {
        this.tipo = tipo;
    }
    public GrupoMuscular getGrupoMuscular() {return grupoMuscular;}
    public void setGrupoMuscular(GrupoMuscular grupoMuscular) {this.grupoMuscular = grupoMuscular;}
    public int getError() {
        return error;
    }
    public void setError(int error) {
        this.error = error;
    }

    public boolean isCorrect(){
        return !title.equalsIgnoreCase("");
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj.getClass() != this.getClass())
            return  false;
        if(!((EjercicioInfo) obj).getTitle().equalsIgnoreCase(this.getTitle()))
            return false;
        if(!((EjercicioInfo) obj).getContent().equalsIgnoreCase(this.getContent()))
            return false;
        if(!((EjercicioInfo) obj).getTipo().equals(this.getTipo()))
            return false;
        if(!((EjercicioInfo) obj).getGrupoMuscular().equals(this.getGrupoMuscular()))
            return false;

        return true;
    }
}
