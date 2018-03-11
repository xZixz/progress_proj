package com.besmall.kien.progressproj.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.besmall.kien.progressproj.database.AppDatabase;
import com.besmall.kien.progressproj.model.TargetModel;

import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kien on 09/03/2018.
 */

public class AppViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LiveData<List<TargetModel>> targetModels;
    private LiveData<Integer> targetsCount;

    public AppViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getDatabase(this.getApplication());

        targetModels = appDatabase.targetModelDao().getAllTargetItems();
        targetsCount = appDatabase.targetModelDao().getTargetModelCount();
    }

    public LiveData<List<TargetModel>> getTargetModels() {
        return targetModels;
    }

    public LiveData<Integer> getTargetsCount() {
        return targetsCount;
    }

    public void addTarget(String name) {
        Observable.fromCallable(() -> appDatabase.targetModelDao().addTargetModel(new TargetModel(name, Calendar.getInstance().getTime())))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void removeLastTarget() {
        List<TargetModel> list = targetModels.getValue();
        if (list != null && (list.size() - 1 >= 0)) {
            Observable.fromCallable(() -> {
                appDatabase.targetModelDao().deleteTarget(list.get(list.size() - 1));
                return true;
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        }
    }
}
