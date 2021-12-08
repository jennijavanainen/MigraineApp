package fi.javanainen.migraineapp;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MigraineViewModel extends AndroidViewModel {
    private MigraineRepository repository;
    private LiveData<List<Migraine>>allMigraines;

    public MigraineViewModel(@NonNull Application application) {
        super(application);
        repository = new MigraineRepository(application);
        allMigraines = repository.getAllMigraines();
    }
    public void insert(Migraine migraine){
        repository.Insert(migraine);
    }
    public void update(Migraine migraine){
        repository.Update(migraine);
    }
    public void delete(Migraine migraine){
        repository.Delete(migraine);
    }
    public LiveData<List<Migraine>>getAllMigraines(){
        return allMigraines;
    }


}