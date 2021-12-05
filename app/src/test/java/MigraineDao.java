import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import fi.javanainen.migraineapp.Migraine;

@Dao
public interface MigraineDao  {
    @Insert
    void insertAll(Migraine... users);

    @Delete
    void delete(Migraine user);

    @Query("SELECT * FROM migraine")
    List<Migraine> getAll();
}
