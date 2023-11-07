package com.example.carmanagerapplication;

import java.util.ArrayList;

public class ManagerViewModel {
    public ArrayList getManagerData() {
        ArrayList<ManagerDataModel> managerList = new ArrayList<ManagerDataModel>();
        ManagerDataModel man1 = new ManagerDataModel("AudioManager");
        managerList.add(man1);
        return managerList;
    }
}
