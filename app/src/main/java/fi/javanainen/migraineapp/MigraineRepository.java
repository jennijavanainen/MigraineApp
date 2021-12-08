package fi.javanainen.migraineapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MigraineRepository {
    private MigraineDao migraineDao;

    private LiveData <List<Migraine>> allMigraines;

    public MigraineRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        migraineDao = database.migraineDao();
        allMigraines = migraineDao.getAll();
    }
    public void Insert(Migraine migraine){
        new InsertMigraineAsyncTask(migraineDao).execute(migraine);
    }
    public void Update(Migraine migraine){
        new UpdateMigraineAsyncTask(migraineDao).execute(migraine);
    }
    public void Delete(Migraine migraine){
        new DeleteMigraineAsyncTask(migraineDao).execute(migraine);
    }
    public LiveData<List<Migraine>>getAllMigraines(){
        return allMigraines;
    }
    private static class InsertMigraineAsyncTask extends AsyncTask<Migraine, Void, Void>{

        private MigraineDao migraineDao;

        private InsertMigraineAsyncTask(MigraineDao migraineDao){
            this.migraineDao = migraineDao;
        }
        @Override
        protected Void doInBackground(Migraine...migraines){
            migraineDao.insertAll(migraines[0]);
            return null;
        }
    }
    private static class UpdateMigraineAsyncTask extends AsyncTask<Migraine, Void, Void>{

        private MigraineDao migraineDao;

        private UpdateMigraineAsyncTask(MigraineDao migraineDao){
            this.migraineDao = migraineDao;
        }
        @Override
        protected Void doInBackground(Migraine...migraines){
            migraineDao.update(migraines[0]);
            return null;
        }
    }
    private static class DeleteMigraineAsyncTask extends AsyncTask<Migraine, Void, Void>{

        private MigraineDao migraineDao;

        private DeleteMigraineAsyncTask(MigraineDao migraineDao){
            this.migraineDao = migraineDao;
        }
        @Override
        protected Void doInBackground(Migraine...migraines){
            migraineDao.delete(migraines[0]);
            return null;
        }
    }
}
