package com.example.carmanagerapplication.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.carmanagerapplication.model.ManagerDataModel;

import java.util.ArrayList;
import java.util.Arrays;

public class ManagerViewModel extends ViewModel {
    private static final String TAG = ManagerViewModel.class.getSimpleName();
    public MutableLiveData<String> labelValue = new MutableLiveData<>();
    public MutableLiveData<ArrayList<ManagerDataModel>> _managerDataList = new MutableLiveData<>();

    public LiveData<ArrayList<ManagerDataModel>> managerDataList = _managerDataList;

    public void initViewModel() {
        _managerDataList.postValue(getManagerData());
    };
    private static final ManagerDataModel[] MANAGER_DATA_MODELS = {
            new ManagerDataModel("CarAudioManager"),
            new ManagerDataModel("CarAppFocusManager"),
            new ManagerDataModel("CarProjectionManager"),
            new ManagerDataModel("CarTestManagerBinderWrapper"),
            new ManagerDataModel("CarDrivingStateManager"),
            new ManagerDataModel("CarBugreportManager"),
            new ManagerDataModel("CarUserManager"),
            new ManagerDataModel("CarWatchdogManager")
    };

    public ArrayList getManagerData() {
        return new ArrayList<>(Arrays.asList(MANAGER_DATA_MODELS));
    }

    public void updateLabelValue(){
        labelValue.postValue("Update");
    }
}
