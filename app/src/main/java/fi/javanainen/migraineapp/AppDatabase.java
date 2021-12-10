package fi.javanainen.migraineapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {MigraineEvent.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract MigraineEventDao migraineEventDao();

    // --- INSTANCE ---
    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "migraine_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallBack)
                            .build();
                }
            }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        MigraineEventDao migraineEventDao;

        private PopulateDbAsyncTask(AppDatabase db){
            migraineEventDao = db.migraineEventDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //migraineEventDao.insertAll(new MigraineEvent("11","1");
            return null;
        }
    }
}
