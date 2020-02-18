package org.odkuday.collect.android.activities;

import android.os.Environment;

/**
 * Created by KIIT on 12-12-2018.
 */

public class CheckForSDCard {
    //Check If SD Card is present or not method
    public boolean isSDCardPresent() {
        if (Environment.getExternalStorageState().equals(

                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}