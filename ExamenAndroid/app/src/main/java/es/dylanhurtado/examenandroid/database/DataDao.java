package es.dylanhurtado.examenandroid.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import es.dylanhurtado.examenandroid.model.Dato;

import java.util.List;

@Dao
public interface DataDao {

    @Insert
    long insert(Dato t);

    @Query("SELECT * FROM dato")
    List<Dato> selectAllDatos();


    @Query("SELECT * FROM dato where dia = :dia")
    List<Dato> getDatoByDia(String dia);
}
