package com.gzeinnumer.simpletakefotofromgalerymylibdirectory;

import android.app.Application;

import com.gzeinnumer.gzndirectory.helper.FGDir;

//todo 11 make class MyApp
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //todo 12 Declare your folder external name
        String externalFolderName = getApplicationContext().getString(R.string.app_name); //MyLibsTesting
        FGDir.initExternalDirectoryName(externalFolderName);
    }
}