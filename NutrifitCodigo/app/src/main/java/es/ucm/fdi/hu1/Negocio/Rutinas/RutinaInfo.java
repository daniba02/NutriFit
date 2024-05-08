package es.ucm.fdi.hu1.Negocio.Rutinas;

import androidx.annotation.Nullable;

import java.util.Objects;

import es.ucm.fdi.hu1.Negocio.GrupoMuscular;

public class RutinaInfo {
    private long ID;
    private String title;
    private String content;

    private int error;

    private GrupoMuscular grupoMuscular;


    public RutinaInfo(){}
    public RutinaInfo(String title, String content, GrupoMuscular grupoMuscular){
        this.title = title;
        this.content = content;
        this.grupoMuscular = grupoMuscular;
    }
    public RutinaInfo(long id ,String title, String content){
        this.ID = id;
        this.title = title;
        this.content = content;
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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public GrupoMuscular getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public boolean isCorrect(){
        return !title.equalsIgnoreCase("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RutinaInfo that = (RutinaInfo) o;
        return ID == that.ID && Objects.equals(title, that.title) && Objects.equals(content, that.content) && grupoMuscular == that.grupoMuscular;
    }


}
