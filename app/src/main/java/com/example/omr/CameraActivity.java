package com.example.omr;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class CameraActivity extends AppCompatActivity {

        public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;
        public static final String ALLOW_KEY = "ALLOWED";
        public static final String CAMERA_PREF = "camera_pref";

        public static void saveToPreferences(Context context, String key, Boolean allowed) {
            SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = myPrefs.edit();
            prefsEditor.putBoolean(key, allowed);
            prefsEditor.commit();
        }

        public static Boolean getFromPref(Context context, String key) {
            SharedPreferences myPrefs = context.getSharedPreferences(CAMERA_PREF,
                    Context.MODE_PRIVATE);
            return (myPrefs.getBoolean(key, false));
        }

        public static void startInstalledAppDetailsActivity(final Activity context) {
            if (context == null) {
                return;
            }

            final Intent i = new Intent();
            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setData(Uri.parse("package:" + context.getPackageName()));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(i);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_camera);

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (getFromPref(this, ALLOW_KEY)) {
                    showSettingsAlert();
                } else if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)

                        != PackageManager.PERMISSION_GRANTED) {

                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.CAMERA)) {
                        showAlert();
                    } else {
                        // No explanation needed, we can request the permission.
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                }
            } else {
                openCamera();
            }

        }

        private void showAlert() {
            AlertDialog alertDialog = new AlertDialog.Builder(CameraActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("App needs to access the Camera.");

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ALLOW",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            ActivityCompat.requestPermissions(CameraActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    MY_PERMISSIONS_REQUEST_CAMERA);
                        }
                    });
            alertDialog.show();
        }

        private void showSettingsAlert() {
            AlertDialog alertDialog = new AlertDialog.Builder(CameraActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("App needs to access the Camera.");

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DONT ALLOW",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            //finish();
                        }
                    });

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            startInstalledAppDetailsActivity(CameraActivity.this);
                        }
                    });

            alertDialog.show();
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_CAMERA: {
                    for (int i = 0, len = permissions.length; i < len; i++) {
                        String permission = permissions[i];

                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            boolean
                                    showRationale =
                                    ActivityCompat.shouldShowRequestPermissionRationale(
                                            this, permission);

                            if (showRationale) {
                                showAlert();
                            } else if (!showRationale) {
                                saveToPreferences(CameraActivity.this, ALLOW_KEY, true);
                            }
                        }
                    }
                }
            }
        }

        @Override
        protected void onResume() {
            super.onResume();
        }

        private void openCamera() {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);
        }
}
