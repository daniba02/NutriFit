package es.ucm.fdi.hu1.DataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.hu1.Negocio.Ejercicio.EjercicioInfo;
import es.ucm.fdi.hu1.Negocio.Ejercicio.TipoEjercicio;
import es.ucm.fdi.hu1.Negocio.GrupoMuscular;
import es.ucm.fdi.hu1.Negocio.Rutinas.RutinaInfo;
import es.ucm.fdi.hu1.Negocio.Rutinas.Rutina_ejercicioInfo;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 21; //incrementa la versión en cada prueba distinta.

    private static final String DBNAME = "Base.db";

    private static DataBaseHelper instance;
    public DataBaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }

    public static DataBaseHelper getInstance(@Nullable Context c){
        if(instance == null){
            instance = new DataBaseHelper(c);
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table EJERCICIOS_TABLE(id INTEGER primary key autoincrement, title TEXT, content TEXT, exercise_type TEXT, muscle_group TEXT)");
        MyDatabase.execSQL("create Table RUTINA_TABLE(id INTEGER primary key autoincrement, title TEXT, content TEXT, muscle_group TEXT)");
        MyDatabase.execSQL("create Table RUTINA_EJER_TABLE(id INTEGER primary key autoincrement, id_rutina INTEGER, id_ejercicio INTEGER, num_repeticiones INTEGER, num_series INTEGER, peso INTEGER, tiempo_descanso TEXT, FOREIGN KEY (id_rutina) REFERENCES RUTINA_TABLE(id), FOREIGN KEY (id_ejercicio) REFERENCES EJERCICIOS_TABLE(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists EJERCICIOS_TABLE");
        MyDatabase.execSQL("drop Table if exists RUTINA_TABLE");
        MyDatabase.execSQL("drop Table if exists RUTINA_EJER_TABLE");
        onCreate(MyDatabase);
    }

    // CREAR EJERCICIOS
    public long addEjer(EjercicioInfo ejer){ // Donde se va a meter la informacion a dicha nota
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("title",ejer.getTitle());
        c.put("content", ejer.getContent());
        c.put("exercise_type", ejer.getTipo().toString());
        c.put("muscle_group", ejer.getGrupoMuscular().toString());

        long ID = db.insert("EJERCICIOS_TABLE", null, c);
        Log.d("Inserted", "ID -> " + ID);

        return ID;
    }

    public EjercicioInfo getEjer(long id){ // SELECT * FROM DATABASETABLE WHERE ID =...
        SQLiteDatabase db = this.getReadableDatabase();

        String[] query = new String[]{"id", "title", "content","exercise_type", "muscle_group"};
        Cursor cursor= db.query("EJERCICIOS_TABLE", query, "id=?", new String[]{String.valueOf(id)},null,null,null,null);

        EjercicioInfo ejer = null;

        if (cursor.moveToFirst()) { // El curso no esta vacio
            do {
                ejer = new EjercicioInfo(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        TipoEjercicio.valueOf(cursor.getString(3)),
                        GrupoMuscular.valueOf(cursor.getString(4))
                );

            } while (cursor.moveToNext());
        }
        return ejer;
    }

    public EjercicioInfo getEjer(String name){ // SELECT * FROM DATABASETABLE WHERE NAME =...
        SQLiteDatabase db = this.getReadableDatabase();

        String[] query = new String[]{"id", "title", "content","exercise_type", "muscle_group"};
        Cursor cursor= db.query("EJERCICIOS_TABLE", query, "title=?", new String[]{name},null,null,null,null);
        EjercicioInfo ejer = null;
        if (cursor.moveToFirst()) { // El curso no esta vacio
            do {
                ejer = new EjercicioInfo(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        TipoEjercicio.valueOf(cursor.getString(3)),
                        GrupoMuscular.valueOf(cursor.getString(4))
                );

            } while (cursor.moveToNext());
        }
        return ejer;
    }

    public List<EjercicioInfo> getEjercicios(){        //SELECT * FROM DATABASENAME
        SQLiteDatabase db = this.getReadableDatabase();
        List<EjercicioInfo> allNotes = new ArrayList<>();

        String query = "SELECT * FROM " + "EJERCICIOS_TABLE" + " ORDER BY " + "id" + " DESC";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){ // El curso no esta vacio
            do{
                EjercicioInfo note = new EjercicioInfo();
                note.setID(cursor.getInt(0));
                note.setTitle(cursor.getString(1));
                note.setContent(cursor.getString(2));
                note.setTipo(TipoEjercicio.valueOf(cursor.getString(3)));
                note.setGrupoMuscular(GrupoMuscular.valueOf(cursor.getString((4))));
                allNotes.add(note);

            }while(cursor.moveToNext());
        }
        return allNotes;
    }


    public boolean deleteEj(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean exito = true;
        String where = "id=?";

        String[] args = {String.valueOf(id)};

        // Ejecuta la sentencia DELETE en la tabla de ejercicios
        if(db.delete("EJERCICIOS_TABLE", where, args) == 0)
            exito = false;

        return exito;
    }

    public boolean updateEj(EjercicioInfo ejer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("title",ejer.getTitle());
        c.put("content", ejer.getContent());
        c.put("exercise_type", ejer.getTipo().name());
        c.put("muscle_group", ejer.getGrupoMuscular().name());
        boolean exito = true;
        String where = "id=?";

        String[] args = {String.valueOf(ejer.getID())};

        if(db.update("EJERCICIOS_TABLE", c, where, args ) == 0)
            exito = false;

        return exito;
    }


    //TABLA INTERMEDIA
    public boolean addEjercicioARutina(Rutina_ejercicioInfo rel) {
        SQLiteDatabase db = this.getWritableDatabase();

        boolean exito = true;

        ContentValues c = new ContentValues();
        String query2 = "INSERT INTO RUTINA_EJER_TABLE (id_rutina, id_ejercicio, num_repeticiones, num_series, peso, tiempo_descanso) " +
                "VALUES (" + rel.getID_rutina() + ", " + rel.getID_ejer() + ", " + rel.getNum_repeticiones() + ", " + rel.getNum_series() +
                ", '" + rel.getPeso() + "', '" + rel.getTiempo_descanso() + "');";        
      try{

            db.execSQL(query2);
        }
        catch (Exception e){
            exito = false;
        }

        return exito;
    }

    public Rutina_ejercicioInfo getEjerDeRutina(long id_rutina){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] query = new String[]{"id" ,"id_rutina", "id_ejercicio", "num_repeticiones", "num_series", "peso", "tiempo_descanso"};
        Cursor cursor= db.query("RUTINA_EJER_TABLE", query, "id_rutina=?", new String[]{String.valueOf(id_rutina)},null,null,null,null);

        Rutina_ejercicioInfo rutejer = null;

        if (cursor.moveToFirst()) { // El curso no esta vacio
            do {
                rutejer = new Rutina_ejercicioInfo(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getString(6));

            } while (cursor.moveToNext());
        }
        return rutejer;
    }

    public List<Rutina_ejercicioInfo> getRutsDeEjs(long id_ejer){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Rutina_ejercicioInfo> rutinaEjercicioInfos = new ArrayList<>();

        String[] query = new String[]{"id" ,"id_rutina", "id_ejercicio", "num_repeticiones", "num_series", "peso", "tiempo_descanso"};
        Cursor cursor= db.query("RUTINA_EJER_TABLE", query, "id_ejercicio=?", new String[]{String.valueOf(id_ejer)},null,null,null,null);

        if (cursor.moveToFirst()) {
            do {
                Rutina_ejercicioInfo rutinaEjercicioInfo = new Rutina_ejercicioInfo(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getString(6));
                rutinaEjercicioInfos.add(rutinaEjercicioInfo);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return rutinaEjercicioInfos;
    }

    public List<Rutina_ejercicioInfo> getEjerciciosDeRutina(long idrut){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] query = new String[]{"id", "id_rutina", "id_ejercicio", "num_repeticiones", "num_series", "peso", "tiempo_descanso"};
        Cursor cursor = db.query("RUTINA_EJER_TABLE", query, "id_rutina=?", new String[]{String.valueOf(idrut)}, null, null, null);

        List<Rutina_ejercicioInfo> ejersRut = new ArrayList<>();
        if(cursor.moveToFirst()){ // El curso no esta vacio
            do{
                Rutina_ejercicioInfo ejer = new Rutina_ejercicioInfo();
                ejer.setID(cursor.getInt(0));
                ejer.setID_rutina(cursor.getInt(1));
                ejer.setID_ejer(cursor.getInt(2));
                ejer.setNum_repeticiones(cursor.getInt(3));
                ejer.setNum_series(cursor.getInt(4));
                ejer.setPeso(cursor.getInt(5));
                ejer.setTiempo_descanso(cursor.getString(6));
                ejersRut.add(ejer);

            }while(cursor.moveToNext());
        }
        return ejersRut;
    }

    //CREAR RUTINAS

    public long addRutina(RutinaInfo rutina){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("title",rutina.getTitle());
        c.put("content", rutina.getContent());
        c.put("muscle_group", rutina.getGrupoMuscular().name());

        long ID = db.insert("RUTINA_TABLE", null, c);
        Log.d("Inserted", "ID -> " + ID);

        return ID;
    }

    public List<RutinaInfo> getRutinas(){        //SELECT * FROM DATABASENAME
        SQLiteDatabase db = this.getReadableDatabase();
        List<RutinaInfo> allRutinas = new ArrayList<>();

        String query = "SELECT * FROM " + "RUTINA_TABLE" + " ORDER BY " + "id" + " DESC";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){ // El curso no esta vacio
            do{
                RutinaInfo rutina = new RutinaInfo();
                rutina.setID(cursor.getInt(0));
                rutina.setTitle(cursor.getString(1));
                rutina.setContent(cursor.getString(2));
                rutina.setGrupoMuscular(GrupoMuscular.valueOf(cursor.getString((3))));
                allRutinas.add(rutina);

            }while(cursor.moveToNext());
        }
        return allRutinas;
    }

    public RutinaInfo getRutina(long id_rutina) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean ok = true;

        String[] query = new String[]{"id", "title", "content", "muscle_group"};
        Cursor cursor = db.query("RUTINA_TABLE", query, "id=?", new String[]{String.valueOf(id_rutina)}, null, null, null, null);

        RutinaInfo rutina = null;

        if (cursor.moveToFirst()) { // El curso no esta vacio
            do {
                rutina = new RutinaInfo();
                rutina.setID(cursor.getInt(0));
                rutina.setTitle(cursor.getString(1));
                rutina.setContent(cursor.getString(2));
                rutina.setGrupoMuscular(GrupoMuscular.valueOf(cursor.getString((3))));

            } while (cursor.moveToNext());
        }
        return rutina;

    }

    public RutinaInfo getRutina(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean ok = true;

        String[] query = new String[]{"id", "title", "content", "muscle_group"};
        Cursor cursor = db.query("RUTINA_TABLE", query, "title=?", new String[]{name}, null, null, null, null);
        RutinaInfo rutina = null;

        if (cursor.moveToFirst()) { // El curso no esta vacio
            do {
                rutina = new RutinaInfo();
                rutina.setID(cursor.getInt(0));
                rutina.setTitle(cursor.getString(1));
                rutina.setContent(cursor.getString(2));
                rutina.setGrupoMuscular(GrupoMuscular.valueOf(cursor.getString((3))));

            } while (cursor.moveToNext());
        }
        return rutina;

    }
}