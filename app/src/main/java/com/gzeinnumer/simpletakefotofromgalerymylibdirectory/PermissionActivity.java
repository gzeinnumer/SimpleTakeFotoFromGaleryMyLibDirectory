package com.gzeinnumer.simpletakefotofromgalerymylibdirectory;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gzeinnumer.gzndirectory.helper.FGPermission;

import rebus.permissionutils.PermissionEnum;
import rebus.permissionutils.PermissionManager;

public class PermissionActivity extends AppCompatActivity {

    //todo 6
    PermissionEnum[] permissions = new PermissionEnum[]{
            PermissionEnum.WRITE_EXTERNAL_STORAGE,
            PermissionEnum.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        //todo 10 call the permission dialog
        FGPermission.checkPermissions(this, permissions);

        checkPermissions();
    }

    //todo 9 cal this function if all permission granted
    private void onSuccessCheckPermitions() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finishAffinity();
    }

    //todo 7
    private void checkPermissions() {
        boolean isAllGranted = FGPermission.getPermissionResult(this, permissions);

        if (isAllGranted){
            onSuccessCheckPermitions();
        } else {
            Toast.makeText(this, "Permission Required", Toast.LENGTH_SHORT).show();
        }
    }

    //todo 8
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handleResult(this, requestCode, permissions, grantResults);

        checkPermissions();
    }
}