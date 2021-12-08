package fi.javanainen.migraineapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import fi.javanainen.migraineapp.Migraine;

@Dao
public interface MigraineDao  {
    @Insert
    void insertAll(Migraine migraine);

    @Delete
    void delete(Migraine migraine);

    @Update
    void update(Migraine migraine);

    @Query("SELECT * FROM migraine_data ORDER BY id ASC")
    LiveData<List<Migraine>> getAll();
}
