package es.dylanhurtado.examenandroid.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import es.dylanhurtado.examenandroid.model.Dato;

@Database(entities = {Dato.class}, version = 1)
public abstract class RoomDB extends RoomDatabase {

    private static String DATABASE_NAME = "basededatos";

    public abstract DataDao dataDao();

    private static volatile RoomDB INSTANCE;

    public synchronized static RoomDB getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class, DATABASE_NAME).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
