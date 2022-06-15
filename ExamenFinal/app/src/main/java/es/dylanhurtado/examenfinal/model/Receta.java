package es.dylanhurtado.examenfinal.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "receta")
public class Receta {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private int photo;
    private String time;
    private String dificultad;
    private String category;
    private String ingredientes;
    private String elaboracion;

    public Receta() {
    }

    public Receta(int id, String name, int photo, String time, String dificultad, String category, String ingredientes, String elaboracion) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.time = time;
        this.dificultad = dificultad;
        this.category = category;
        this.ingredientes = ingredientes;
        this.elaboracion = elaboracion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getElaboracion() {
        return elaboracion;
    }

    public void setElaboracion(String elaboracion) {
        this.elaboracion = elaboracion;
    }
}
