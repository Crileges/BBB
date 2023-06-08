package com.example.bbbthirdtry;

import android.Manifest;
import android.Manifest.permission;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.widget.Toast;

/**
 * Utility class for access to runtime permissions.
 */
public abstract class PermissionUtils {

    /**
     * Requests the fine and coarse location permissions. If a rationale with an additional
     * explanation should be shown to the user, displays a dialog that triggers the request.
     */
    public static void requestLocationPermissions(Activity activity, int requestId,
                                                  boolean finishActivity) {
        if (ActivityCompat
                .shouldShowRequestPermissionRationale(activity, permission.ACCESS_FINE_LOCATION) ||
                ActivityCompat.shouldShowRequestPermissionRationale(activity,
                        permission.ACCESS_COARSE_LOCATION)) {
        } else {

            // Location permission has not been granted yet, request it.
            ActivityCompat.requestPermissions(activity,
                    new String[]{permission.ACCESS_FINE_LOCATION, permission.ACCESS_COARSE_LOCATION},
                    requestId);
        }
    }

    /**
     * Checks if the result contains a {@link PackageManager#PERMISSION_GRANTED} result for a
     * permission from a runtime permissions request.
     *
     * @see androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
     */
    public static boolean isPermissionGranted(String[] grantPermissions, int[] grantResults,
                                              String permission) {
        for (int i = 0; i < grantPermissions.length; i++) {
            if (permission.equals(grantPermissions[i])) {
                return grantResults[i] == PackageManager.PERMISSION_GRANTED;
            }
        }
        return false;
    }

}
