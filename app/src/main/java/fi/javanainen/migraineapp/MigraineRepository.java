package fi.javanainen.migraineapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MigraineRepository {
    private MigraineEventDao migraineEventDao;

    private LiveData <List<MigraineEvent>> allMigraines;

    public MigraineRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        migraineEventDao = database.migraineEventDao();
        allMigraines = migraineEventDao.getAll();
    }
    public void Insert(MigraineEvent migraine){
        new InsertMigraineAsyncTask(migraineEventDao).execute(migraine);
    }
    public void Update(MigraineEvent migraine){
        new UpdateMigraineAsyncTask(migraineEventDao).execute(migraine);
    }
    public void Delete(MigraineEvent migraine){
        new DeleteMigraineAsyncTask(migraineEventDao).execute(migraine);
    }
    public LiveData<List<MigraineEvent>>getAllMigraines(){
        return allMigraines;
    }
    private static class InsertMigraineAsyncTask extends AsyncTask<MigraineEvent, Void, Void>{

        private MigraineEventDao migraineEventDao;

        private InsertMigraineAsyncTask(MigraineEventDao migraineDao){
            this.migraineEventDao = migraineDao;
        }
        @Override
        protected Void doInBackground(MigraineEvent...migraines){
            migraineEventDao.insertAll(migraines[0]);
            return null;
        }
    }
    private static class UpdateMigraineAsyncTask extends AsyncTask<MigraineEvent, Void, Void>{

        private MigraineEventDao migraineEventDao;

        private UpdateMigraineAsyncTask(MigraineEventDao migraineEventDao){
            this.migraineEventDao = migraineEventDao;
        }
        @Override
        protected Void doInBackground(MigraineEvent...migraines){
            migraineEventDao.update(migraines[0]);
            return null;
        }
    }
    private static class DeleteMigraineAsyncTask extends AsyncTask<MigraineEvent, Void, Void>{

        private MigraineEventDao migraineEventDao;

        private DeleteMigraineAsyncTask(MigraineEventDao migraineDao){
            this.migraineEventDao = migraineDao;
        }
        @Override
        protected Void doInBackground(MigraineEvent...migraines){
            migraineEventDao.delete(migraines[0]);
            return null;
        }
    }
}
