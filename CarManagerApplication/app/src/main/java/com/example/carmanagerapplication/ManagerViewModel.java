package com.example.carmanagerapplication;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ManagerViewModel extends ViewModel {

    public ArrayList getManagerData() {
        ArrayList<ManagerDataModel> managerList = new ArrayList<ManagerDataModel>();
        ManagerDataModel man1 = new ManagerDataModel("AudioManager");
        managerList.add(man1);
        return managerList;
    }
}
