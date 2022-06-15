package es.dylanhurtado.examenfinal.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.dylanhurtado.examenfinal.model.Category;
import es.dylanhurtado.examenfinal.model.Receta;

@Dao
public interface DataDao {
    @Insert
    long insertCategory(Category c);

    @Query("SELECT * FROM category")
    List<Category> selectAllCategory();

    @Query("DELETE FROM category WHERE id = :mId")
    void deleteCategory(Integer mId);

    @Query("SELECT name FROM category")
    String[] getNombreCategorias();

    @Insert
    long insertReceta(Receta r);

    @Query("SELECT * FROM receta")
    List<Receta> selectAllRecetas();

    @Query("DELETE FROM receta WHERE id = :mId")
    void deleteTarea(Integer mId);
}
