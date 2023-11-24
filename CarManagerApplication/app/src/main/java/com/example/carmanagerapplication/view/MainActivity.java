package com.example.carmanagerapplication.view;

import static android.car.CarAppFocusManager.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.car.Car;
import android.car.CarAppFocusManager;
import android.car.watchdog.CarWatchdogManager;
import android.car.watchdog.IoOveruseStats;
import android.car.watchdog.ResourceOveruseStats;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import com.example.carmanagerapplication.databinding.ActivityMainBinding;
import com.example.carmanagerapplication.view.ManagerListFragment;
import com.example.carmanagerapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


//        ManagerListFragment myFragment = new ManagerListFragment();
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        fragmentTransaction.add(R.id.fragment_container, myFragment);
//
//        fragmentTransaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    ParcelFileDescriptor mParcelFileDescriptor;

    private void createdParcelFileDescriptor() throws FileNotFoundException {
        File file = new File(String.valueOf(0));
        mParcelFileDescriptor = new ParcelFileDescriptor(ParcelFileDescriptor.open(file, 0));

    }

    CarWatchdogManager mCarWatchdogManager;
    Executor executor;
    ResourceOveruseStats resourceOveruseStats;
    public void createCarWatchdogManager() {
        Context mContext = this;
        Car car = Car.createCar(mContext);
        mCarWatchdogManager = (CarWatchdogManager) car.getCarManager(Car.CAR_WATCHDOG_SERVICE);
        executor = mContext.getMainExecutor();
        resourceOveruseStats = mCarWatchdogManager.getResourceOveruseStats(CarWatchdogManager.FLAG_RESOURCE_OVERUSE_IO, CarWatchdogManager.STATS_PERIOD_PAST_7_DAYS);
        Log.d("TAG", resourceOveruseStats.getPackageName());
        mCarWatchdogManager.addResourceOveruseListener(executor, CarWatchdogManager.STATS_PERIOD_PAST_7_DAYS, resourceOveruseListener);
        mCarWatchdogManager.removeResourceOveruseListener(resourceOveruseListener);
    }

    CarWatchdogManager.ResourceOveruseListener resourceOveruseListener = new CarWatchdogManager.ResourceOveruseListener() {
        @Override
        public void onOveruse(ResourceOveruseStats resourceOveruseStats) {
            IoOveruseStats ioOveruseStats = resourceOveruseStats.getIoOveruseStats();
            Log.d("TAG", String.valueOf(ioOveruseStats.getStartTime()));
        }
    };


    CarAppFocusManager mAppFocusManager;
    public void createCarFocusManager() {
        Context mContext = this;
        Car car = Car.createCar(mContext);
        mAppFocusManager = (CarAppFocusManager) car.getCarManager(Car.APP_FOCUS_SERVICE);
        boolean isOwning = mAppFocusManager.isOwningFocus(onAppFocusOwnershipCallback, APP_FOCUS_TYPE_NAVIGATION);
        if (isOwning) {
            mAppFocusManager.abandonAppFocus(onAppFocusOwnershipCallback);
            mAppFocusManager.abandonAppFocus(onAppFocusOwnershipCallback,APP_FOCUS_TYPE_NAVIGATION);
        } else {
            int appFocus = mAppFocusManager.requestAppFocus(APP_FOCUS_TYPE_NAVIGATION, onAppFocusOwnershipCallback);
            switch (appFocus) {
                case APP_FOCUS_REQUEST_SUCCEEDED:
                    mAppFocusManager.addFocusListener(onAppFocusChangedListener, APP_FOCUS_TYPE_NAVIGATION);
                case APP_FOCUS_REQUEST_FAILED:
                    Log.i("","FAILED");
            }
        }

    }
    OnAppFocusOwnershipCallback onAppFocusOwnershipCallback = new OnAppFocusOwnershipCallback() {

        @Override
        public void onAppFocusOwnershipLost(int i) {
            Log.i("","Stop Navigation");
        }

        @Override
        public void onAppFocusOwnershipGranted(int i) {
            Log.i("","Start Navigation");
        }
    };

    OnAppFocusChangedListener onAppFocusChangedListener = new OnAppFocusChangedListener() {

        @Override
        public void onAppFocusChanged(int i, boolean b) {
            Log.i("","Active Owner Already Changed");
            if (b) {
                Log.i("","Have an Active Owner");
            }else {
                Log.i("","Haven't an Active Owner");
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAppFocusManager.removeFocusListener(onAppFocusChangedListener);
        mAppFocusManager.removeFocusListener(onAppFocusChangedListener,APP_FOCUS_TYPE_NAVIGATION);
    }

}