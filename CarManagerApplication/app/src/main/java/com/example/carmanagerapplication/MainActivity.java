package com.example.carmanagerapplication;

import static android.car.CarAppFocusManager.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.car.Car;
import android.car.CarAppFocusManager;
import android.content.Context;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ManagerListFragment myFragment = new ManagerListFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, myFragment);

        fragmentTransaction.commit();
    }
    public void createCarFocusManager() {
        Context mContext = this;
        Car car = Car.createCar(mContext);
        CarAppFocusManager mAppFocusManager = (CarAppFocusManager) car.getCarManager(Car.APP_FOCUS_SERVICE);
        int appFocus = mAppFocusManager.requestAppFocus(APP_FOCUS_TYPE_NAVIGATION, onAppFocusOwnershipCallback);

        boolean isOwning = mAppFocusManager.isOwningFocus(onAppFocusOwnershipCallback, APP_FOCUS_TYPE_NAVIGATION);
        mAppFocusManager.abandonAppFocus(onAppFocusOwnershipCallback);
        mAppFocusManager.addFocusListener(onAppFocusChangedListener, APP_FOCUS_TYPE_NAVIGATION);


    }
    OnAppFocusOwnershipCallback onAppFocusOwnershipCallback = new OnAppFocusOwnershipCallback() {

        @Override
        public void onAppFocusOwnershipLost(int i) {

        }

        @Override
        public void onAppFocusOwnershipGranted(int i) {

        }
    };

    OnAppFocusChangedListener onAppFocusChangedListener = new OnAppFocusChangedListener() {

        @Override
        public void onAppFocusChanged(int i, boolean b) {

        }
    };

}