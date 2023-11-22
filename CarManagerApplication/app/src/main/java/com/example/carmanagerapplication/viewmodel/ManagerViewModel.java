package com.example.carmanagerapplication.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carmanagerapplication.model.ManagerDataModel;

import java.util.ArrayList;
import java.util.Arrays;

public class ManagerViewModel extends ViewModel {

    public MutableLiveData<String> labelValue = new MutableLiveData<>();
    public MutableLiveData<ArrayList<ManagerDataModel>> _managerDataList = new MutableLiveData<>();

    public LiveData<ArrayList<ManagerDataModel>> managerDataList = _managerDataList;

    public void initViewModel() {
        _managerDataList.postValue(getManagerData());
    };
    private static final ManagerDataModel[] MANAGER_DATA_MODELS = {
            new ManagerDataModel("AudioManager")
    };

    public ArrayList getManagerData() {
        return new ArrayList<>(Arrays.asList(MANAGER_DATA_MODELS));
    }

    public void updateLabelValue(){
        labelValue.postValue("Update");
    }
}
