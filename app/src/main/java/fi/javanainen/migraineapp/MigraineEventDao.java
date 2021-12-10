package fi.javanainen.migraineapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;



@Dao
public interface MigraineEventDao {
    @Insert
    void insertAll(MigraineEvent migraineEvent);

    @Delete
    void delete(MigraineEvent migraineEvent);

    @Update
    void update(MigraineEvent migraineEvent);

    @Query("SELECT * FROM migraine_data ORDER BY id ASC")
    LiveData<List<MigraineEvent>> getAll();
}
