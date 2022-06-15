package es.dylanhurtado.examenfinal.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "category")
public class Category {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private int photo;

    public Category() {
    }

    public Category(int id, String name, int photo) {
        this.id = id;
        this.name = name;
        this.photo = photo;
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
}
