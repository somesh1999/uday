/*
 * Copyright (C) 2009 University of Washington
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.odkuday.collect.android.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;

import org.odkuday.collect.android.R;
import org.odkuday.collect.android.application.Collect;
import org.odkuday.collect.android.dao.InstancesDao;
import org.odkuday.collect.android.preferences.AdminKeys;
import org.odkuday.collect.android.preferences.AdminPreferencesActivity;
import org.odkuday.collect.android.preferences.AdminSharedPreferences;
import org.odkuday.collect.android.preferences.AutoSendPreferenceMigrator;
import org.odkuday.collect.android.preferences.GeneralSharedPreferences;
import org.odkuday.collect.android.preferences.PreferenceKeys;
import org.odkuday.collect.android.preferences.PreferencesActivity;
import org.odkuday.collect.android.provider.InstanceProviderAPI.InstanceColumns;
import org.odkuday.collect.android.utilities.ApplicationConstants;
import org.odkuday.collect.android.utilities.AuthDialogUtility;
import org.odkuday.collect.android.utilities.PlayServicesUtil;
import org.odkuday.collect.android.utilities.SharedPreferencesUtils;
import org.odkuday.collect.android.utilities.ToastUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import timber.log.Timber;

/**
 * Responsible for displaying buttons to launch the major activities. Launches
 * some activities based on returns of others.
 *
 * @author Carl Hartung (carlhartung@gmail.com)
 * @author Yaw Anokwa (yanokwa@gmail.com)
 */
public class MainMenuActivity4 extends CollectAbstractActivity {

    private static final int PASSWORD_DIALOG = 1;

    private static final boolean EXIT = true;
    // buttons
    private Button manageFilesButton;
    private Button sendDataButton;
    private Button viewSentFormsButton;
    private Button reviewDataButton;
    private Button getFormsButton;
    private View reviewSpacer;
    private View getFormsSpacer;
    private AlertDialog alertDialog;
    private SharedPreferences adminPreferences;
    private int completedCount;
    private int savedCount;
    private int viewSentCount;
    private Cursor finalizedCursor;
    private Cursor savedCursor;
    private Cursor viewSentCursor;
    private final IncomingHandler handler = new IncomingHandler(this);
    private final MyContentObserver contentObserver = new MyContentObserver();
    ProgressDialog progressDialog;
    private static String downloadUrl1="https://udaykiss.in/apk/forms/state_pmu_uday.xml";

    // private static boolean DO_NOT_EXIT = false;

    public static void startActivityAndCloseAllOthers(Activity activity) {
        activity.startActivity(new Intent(activity, MainMenuActivity4.class));
        activity.overridePendingTransition(0, 0);
        activity.finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        initToolbar();
        //savefile();
        initializefonts();
        downloadfile();

        SharedPreferences.Editor editor = getSharedPreferences("image_pref", MODE_PRIVATE).edit();
        editor.putString("formname", "abc");
        editor.apply();


        // enter data button. expects a result.
        Button enterDataButton = findViewById(R.id.enter_data);
        enterDataButton.setText("Fill Form");
        enterDataButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Collect.allowClick()) {
                    Collect.getInstance().getActivityLogger()
                            .logAction(this, "fillBlankForm", "click");
                    Intent i = new Intent(getApplicationContext(),
                            FormChooserList4.class);
                    startActivity(i);

                }
            }
        });

        // review data button. expects a result.
        reviewDataButton = findViewById(R.id.review_data);
        reviewDataButton.setText(getString(R.string.review_data_button));
        reviewDataButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Collect.allowClick()) {
                    Collect.getInstance().getActivityLogger()
                            .logAction(this, ApplicationConstants.FormModes.EDIT_SAVED, "click");
                    Intent i = new Intent(getApplicationContext(), InstanceChooserList4.class);
                    i.putExtra(ApplicationConstants.BundleKeys.FORM_MODE,
                            ApplicationConstants.FormModes.EDIT_SAVED);
                    startActivity(i);

                }
            }
        });

        // send data button. expects a result.
        sendDataButton = findViewById(R.id.send_data);
        sendDataButton.setText(getString(R.string.send_data_button));
        sendDataButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Collect.allowClick()) {
                    Collect.getInstance().getActivityLogger()
                            .logAction(this, "uploadForms", "click");
                    Intent i = new Intent(getApplicationContext(),
                            InstanceUploaderList4.class);
                    startActivity(i);
                }
            }
        });

        //View sent forms
        viewSentFormsButton = findViewById(R.id.view_sent_forms);
        viewSentFormsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Collect.allowClick()) {
                    Collect.getInstance().getActivityLogger().logAction(this,
                            ApplicationConstants.FormModes.VIEW_SENT, "click");
                    Intent i = new Intent(getApplicationContext(), InstanceChooserList.class);
                    i.putExtra(ApplicationConstants.BundleKeys.FORM_MODE,
                            ApplicationConstants.FormModes.VIEW_SENT);
                    startActivity(i);
                }
            }
        });

        // manage forms button. no result expected.
        getFormsButton = findViewById(R.id.get_forms);
        getFormsButton.setText(getString(R.string.get_forms));
        getFormsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Collect.allowClick()) {
                    Collect.getInstance().getActivityLogger()
                            .logAction(this, "downloadBlankForms", "click");
                    SharedPreferences sharedPreferences = PreferenceManager
                            .getDefaultSharedPreferences(MainMenuActivity4.this);
                    String protocol = sharedPreferences.getString(
                            PreferenceKeys.KEY_PROTOCOL, getString(R.string.protocol_odk_default));
                    Intent i = null;
                    if (protocol.equalsIgnoreCase(getString(R.string.protocol_google_sheets))) {
                        if (PlayServicesUtil.isGooglePlayServicesAvailable(MainMenuActivity4.this)) {
                            i = new Intent(getApplicationContext(),
                                    GoogleDriveActivity.class);
                        } else {
                            PlayServicesUtil.showGooglePlayServicesAvailabilityErrorDialog(MainMenuActivity4.this);
                            return;
                        }
                    } else {
                        i = new Intent(getApplicationContext(),
                                FormDownloadList.class);
                    }
                    startActivity(i);
                }
            }
        });

        // manage forms button. no result expected.
        manageFilesButton = findViewById(R.id.manage_forms);
        manageFilesButton.setText(getString(R.string.manage_files));
        manageFilesButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Collect.allowClick()) {
                    Collect.getInstance().getActivityLogger()
                            .logAction(this, "deleteSavedForms", "click");
                    Intent i = new Intent(getApplicationContext(),
                            FileManagerTabs4.class);
                    startActivity(i);
                }
            }
        });

        // must be at the beginning of any activity that can be called from an
        // external intent
        Timber.i("Starting up, creating directories");
        try {
            Collect.createODKDirs();
        } catch (RuntimeException e) {
            createErrorDialog(e.getMessage(), EXIT);
            return;
        }

        {
            // dynamically construct the "ODK Collect vA.B" string
            TextView mainMenuMessageLabel = findViewById(R.id.main_menu_header);
            mainMenuMessageLabel.setText(Collect.getInstance()
                    .getVersionedAppName());
        }

        File f = new File(Collect.ODK_ROOT + "/collect.settings");
        File j = new File(Collect.ODK_ROOT + "/collect.settings.json");
        // Give JSON file preference
        if (j.exists()) {
            boolean success = SharedPreferencesUtils.loadSharedPreferencesFromJSONFile(j);
            if (success) {
                ToastUtils.showLongToast(R.string.settings_successfully_loaded_file_notification);
                j.delete();
                recreate();

                // Delete settings file to prevent overwrite of settings from JSON file on next startup
                if (f.exists()) {
                    f.delete();
                }
            } else {
                ToastUtils.showLongToast(R.string.corrupt_settings_file_notification);
            }
        } else if (f.exists()) {
            boolean success = loadSharedPreferencesFromFile(f);
            if (success) {
                ToastUtils.showLongToast(R.string.settings_successfully_loaded_file_notification);
                f.delete();
                recreate();
            } else {
                ToastUtils.showLongToast(R.string.corrupt_settings_file_notification);
            }
        }

        reviewSpacer = findViewById(R.id.review_spacer);
        getFormsSpacer = findViewById(R.id.get_forms_spacer);

        adminPreferences = this.getSharedPreferences(
                AdminPreferencesActivity.ADMIN_PREFERENCES, 0);

        InstancesDao instancesDao = new InstancesDao();

        // count for finalized instances
        try {
            finalizedCursor = instancesDao.getUnsentInstancesCursor4();
        } catch (Exception e) {
            createErrorDialog(e.getMessage(), EXIT);
            return;
        }

        if (finalizedCursor != null) {
            startManagingCursor(finalizedCursor);
        }
        completedCount = finalizedCursor != null ? finalizedCursor.getCount() : 0;
        getContentResolver().registerContentObserver(InstanceColumns.CONTENT_URI, true,
                contentObserver);
        // finalizedCursor.registerContentObserver(contentObserver);

        // count for saved instances
        try {
            savedCursor = instancesDao.getUnsentInstancesCursor4();
        } catch (Exception e) {
            createErrorDialog(e.getMessage(), EXIT);
            return;
        }

        if (savedCursor != null) {
            startManagingCursor(savedCursor);
        }
        savedCount = savedCursor != null ? savedCursor.getCount() : 0;

        //count for view sent form
        try {
            viewSentCursor = instancesDao.getSentInstancesCursor();
        } catch (Exception e) {
            createErrorDialog(e.getMessage(), EXIT);
            return;
        }
        if (viewSentCursor != null) {
            startManagingCursor(viewSentCursor);
        }
        viewSentCount = viewSentCursor != null ? viewSentCursor.getCount() : 0;

        updateButtons();
        setupGoogleAnalytics();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("Main Menu");
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = this.getSharedPreferences(
                AdminPreferencesActivity.ADMIN_PREFERENCES, 0);

        boolean edit = sharedPreferences.getBoolean(
                AdminKeys.KEY_EDIT_SAVED, true);
        if (!edit) {
            if (reviewDataButton != null) {
                reviewDataButton.setVisibility(View.GONE);
            }
            if (reviewSpacer != null) {
                reviewSpacer.setVisibility(View.GONE);
            }
        } else {
            if (reviewDataButton != null) {
                reviewDataButton.setVisibility(View.VISIBLE);
            }
            if (reviewSpacer != null) {
                reviewSpacer.setVisibility(View.VISIBLE);
            }
        }

        boolean send = sharedPreferences.getBoolean(
                AdminKeys.KEY_SEND_FINALIZED, true);
        if (!send) {
            if (sendDataButton != null) {
                sendDataButton.setVisibility(View.GONE);
            }
        } else {
            if (sendDataButton != null) {
                sendDataButton.setVisibility(View.VISIBLE);
            }
        }

        boolean viewSent = sharedPreferences.getBoolean(
                AdminKeys.KEY_VIEW_SENT, true);
        if (!viewSent) {
            if (viewSentFormsButton != null) {
                viewSentFormsButton.setVisibility(View.GONE);
            }
        } else {
            if (viewSentFormsButton != null) {
                viewSentFormsButton.setVisibility(View.GONE);
            }
        }

        boolean getBlank = sharedPreferences.getBoolean(
                AdminKeys.KEY_GET_BLANK, true);
        if (!getBlank) {
            if (getFormsButton != null) {
                getFormsButton.setVisibility(View.GONE);
            }
            if (getFormsSpacer != null) {
                getFormsSpacer.setVisibility(View.GONE);
            }
        } else {
            if (getFormsButton != null) {
                getFormsButton.setVisibility(View.GONE);
            }
            if (getFormsSpacer != null) {
                getFormsSpacer.setVisibility(View.VISIBLE);
            }
        }

        boolean deleteSaved = sharedPreferences.getBoolean(
                AdminKeys.KEY_DELETE_SAVED, true);
        if (!deleteSaved) {
            if (manageFilesButton != null) {
                manageFilesButton.setVisibility(View.GONE);
            }
        } else {
            if (manageFilesButton != null) {
                manageFilesButton.setVisibility(View.VISIBLE);
            }
        }

        ((Collect) getApplication())
                .getDefaultTracker()
                .enableAutoActivityTracking(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Collect.getInstance().getActivityLogger().logOnStart(this);
    }

    @Override
    protected void onStop() {
        Collect.getInstance().getActivityLogger().logOnStop(this);
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Collect.getInstance().getActivityLogger()
                .logAction(this, "onCreateOptionsMenu", "show");
        //getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                Collect.getInstance()
                        .getActivityLogger()
                        .logAction(this, "onOptionsItemSelected",
                                "MENU_ABOUT");
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            case R.id.menu_general_preferences:
                Collect.getInstance()
                        .getActivityLogger()
                        .logAction(this, "onOptionsItemSelected",
                                "MENU_PREFERENCES");
                startActivity(new Intent(this, PreferencesActivity.class));
                return true;
            case R.id.menu_admin_preferences:
                Collect.getInstance().getActivityLogger()
                        .logAction(this, "onOptionsItemSelected", "MENU_ADMIN");
                String pw = adminPreferences.getString(
                        AdminKeys.KEY_ADMIN_PW, "");
                if ("".equalsIgnoreCase(pw)) {
                    startActivity(new Intent(this, AdminPreferencesActivity.class));
                } else {
                    showDialog(PASSWORD_DIALOG);
                    Collect.getInstance().getActivityLogger()
                            .logAction(this, "createAdminPasswordDialog", "show");
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createErrorDialog(String errorMsg, final boolean shouldExit) {
        Collect.getInstance().getActivityLogger()
                .logAction(this, "createErrorDialog", "show");
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setIcon(android.R.drawable.ic_dialog_info);
        alertDialog.setMessage(errorMsg);
        DialogInterface.OnClickListener errorListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Collect.getInstance()
                                .getActivityLogger()
                                .logAction(this, "createErrorDialog",
                                        shouldExit ? "exitApplication" : "OK");
                        if (shouldExit) {
                            finish();
                        }
                        break;
                }
            }
        };
        alertDialog.setCancelable(false);
        alertDialog.setButton(getString(R.string.ok), errorListener);
        alertDialog.show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case PASSWORD_DIALOG:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final AlertDialog passwordDialog = builder.create();
                passwordDialog.setTitle(getString(R.string.enter_admin_password));
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialogbox_layout, null);
                passwordDialog.setView(dialogView, 20, 10, 20, 10);
                final CheckBox checkBox = dialogView.findViewById(R.id.checkBox);
                final EditText input = dialogView.findViewById(R.id.editText);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (!checkBox.isChecked()) {
                            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        } else {
                            input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }
                    }
                });
                passwordDialog.setButton(AlertDialog.BUTTON_POSITIVE,
                        getString(R.string.ok),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                String value = input.getText().toString();
                                String pw = adminPreferences.getString(
                                        AdminKeys.KEY_ADMIN_PW, "");
                                if (pw.compareTo(value) == 0) {
                                    Intent i = new Intent(getApplicationContext(),
                                            AdminPreferencesActivity.class);
                                    startActivity(i);
                                    input.setText("");
                                    passwordDialog.dismiss();
                                } else {
                                    ToastUtils.showShortToast(R.string.admin_password_incorrect);
                                    Collect.getInstance()
                                            .getActivityLogger()
                                            .logAction(this, "adminPasswordDialog",
                                                    "PASSWORD_INCORRECT");
                                }
                            }
                        });

                passwordDialog.setButton(AlertDialog.BUTTON_NEGATIVE,
                        getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Collect.getInstance()
                                        .getActivityLogger()
                                        .logAction(this, "adminPasswordDialog",
                                                "cancel");
                                input.setText("");
                            }
                        });

                passwordDialog.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                return passwordDialog;

        }
        return null;
    }

    // This flag must be set each time the app starts up
    private void setupGoogleAnalytics() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(Collect
                .getInstance());
        boolean isAnalyticsEnabled = settings.getBoolean(PreferenceKeys.KEY_ANALYTICS, true);
        GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(getApplicationContext());
        googleAnalytics.setAppOptOut(!isAnalyticsEnabled);
    }

    private void updateButtons() {
        if (finalizedCursor != null && !finalizedCursor.isClosed()) {
            finalizedCursor.requery();
            completedCount = finalizedCursor.getCount();
            if (completedCount > 0) {
                sendDataButton.setText(
                        getString(R.string.send_data_button, String.valueOf(completedCount)));
            } else {
                sendDataButton.setText(getString(R.string.send_data));
            }
        } else {
            sendDataButton.setText(getString(R.string.send_data));
            Timber.w("Cannot update \"Send Finalized\" button label since the database is closed. "
                    + "Perhaps the app is running in the background?");
        }

        if (savedCursor != null && !savedCursor.isClosed()) {
            savedCursor.requery();
            savedCount = savedCursor.getCount();
            if (savedCount > 0) {
                reviewDataButton.setText(getString(R.string.review_data_button,
                        String.valueOf(savedCount)));
            } else {
                reviewDataButton.setText(getString(R.string.review_data));
            }
        } else {
            reviewDataButton.setText(getString(R.string.review_data));
            Timber.w("Cannot update \"Edit Form\" button label since the database is closed. "
                    + "Perhaps the app is running in the background?");
        }

        if (viewSentCursor != null && !viewSentCursor.isClosed()) {
            viewSentCursor.requery();
            viewSentCount = viewSentCursor.getCount();
            if (viewSentCount > 0) {
                viewSentFormsButton.setText(
                        getString(R.string.view_sent_forms_button, String.valueOf(viewSentCount)));
            } else {
                viewSentFormsButton.setText(getString(R.string.view_sent_forms));
            }
        } else {
            viewSentFormsButton.setText(getString(R.string.view_sent_forms));
            Timber.w("Cannot update \"View Sent\" button label since the database is closed. "
                    + "Perhaps the app is running in the background?");
        }
    }

    public void initializefonts(){
        Typeface  mytypeface= Typeface.createFromAsset(getAssets(),"demo.ttf");
        TextView mainMenuMessageLabel = findViewById(R.id.main_menu_header);
        mainMenuMessageLabel.setTypeface(mytypeface);

    }



    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public void downloadfile(){
        progressDialog = new ProgressDialog(this);
        if(!checkexist()) {
            if (isConnectingToInternet()) {
                new MainMenuActivity4.Downloadingfile().execute();
            } else {
                Toast.makeText(this, "Oops!! There is no internet connection", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private boolean checkexist(){
        File file = new File(Environment.getExternalStorageDirectory() + "/UDAY/forms/state_pmu_uday.xml");
        if (file.exists()) {
            return true;
        }
        else{
            return false;
        }
    }




    private class Downloadingfile extends AsyncTask<Void, Void, Void> {

        File apkStorage = null;
        File outputFile = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Downloading Forms");
            progressDialog.show();
            //Set Button Text when download started
        }

        @Override
        protected void onPostExecute(Void result) {
            try {
                if (outputFile != null) {
                    progressDialog.dismiss();
                    //If Download completed then change button text
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(MainMenuActivity4.this, "Form Download Failed", Toast.LENGTH_SHORT).show();
                    //If download failed change button text
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Change button text again after 3sec
                        }
                    }, 3000);

                    Log.e("Status", "Download Failed");

                }
            } catch (Exception e) {
                e.printStackTrace();

                //Change button text if exception occurs

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 3000);
                Log.e("Status", "Download Failed with Exception - " + e.getLocalizedMessage());

            }


            super.onPostExecute(result);
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {

                URL url = new URL(downloadUrl1);//Create Download URl
                HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
                c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
                c.connect();//connect the URL Connection


                //If Connection response is not OK then show Logs
                if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    Log.e("Status", "Server returned HTTP " + c.getResponseCode()
                            + " " + c.getResponseMessage());

                }


                //Get File if SD card is present
                if (new CheckForSDCard().isSDCardPresent()) {

                    apkStorage = new File(
                            Environment.getExternalStorageDirectory() + "/UDAY/forms");
                } else
                    Toast.makeText(getBaseContext(), "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

                //If File is not present create directory
                if (!apkStorage.exists()) {
                    apkStorage.mkdir();
                    Log.e("Status", "Directory Created.");
                }

                outputFile = new File(apkStorage, "state_pmu_uday.xml");//Create Output file in Main File

                //Create New File if not present
                if (!outputFile.exists()) {
                    outputFile.createNewFile();
                    Log.e("Status", "File Created");
                }

                FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

                InputStream is = c.getInputStream();//Get InputStream for connection

                byte[] buffer = new byte[1024];//Set buffer type
                int len1 = 0;//init length
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);//Write new file
                }

                //Close all connection after doing task
                fos.close();
                is.close();


            } catch (Exception e) {

                //Read exception if something went wrong
                e.printStackTrace();
                outputFile = null;
                Log.e("Status", "Download Error Exception " + e.getMessage());
            }

            return null;
        }

    }

    public void savefile(){
        String filename="state_pmu_uday.xml";
        String content="<?xml version=\"1.0\"?>\n" +
                "<h:html xmlns=\"http://www.w3.org/2002/xforms\" xmlns:ev=\"http://www.w3.org/2001/xml-events\" xmlns:h=\"http://www.w3.org/1999/xhtml\" xmlns:jr=\"http://openrosa.org/javarosa\" xmlns:odk=\"http://www.opendatakit.org/xforms\" xmlns:orx=\"http://openrosa.org/xforms\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "  <h:head>\n" +
                "    <h:title>State PMU Uday</h:title>\n" +
                "    <model>\n" +
                "      <itext>\n" +
                "        <translation default=\"true()\" lang=\"default\">\n" +
                "          <text id=\"static_instance-Block-18\">\n" +
                "            <value>Podia</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-12\">\n" +
                "            <value>Kalimela</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-13\">\n" +
                "            <value>Khairaput</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-10\">\n" +
                "            <value>Gudari</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-11\">\n" +
                "            <value>Bissam Cuttack</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-16\">\n" +
                "            <value>Malkangiri</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-17\">\n" +
                "            <value>Mathili</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-14\">\n" +
                "            <value>Korukonda</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-15\">\n" +
                "            <value>Kudmulgumma</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-District-2\">\n" +
                "            <value>Malkangiri</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-District-0\">\n" +
                "            <value>Koraput</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-District-1\">\n" +
                "            <value>Rayagada</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-jw98e46-0\">\n" +
                "            <value>Meeting</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-jw98e46-1\">\n" +
                "            <value>Liaison</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-jw98e46-2\">\n" +
                "            <value>Monitoring SAP</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-jw98e46-3\">\n" +
                "            <value>Monitoring CAP</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-0\">\n" +
                "            <value>Bandhugaon</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-1\">\n" +
                "            <value>Narayanpatna</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-2\">\n" +
                "            <value>Nandapur</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-3\">\n" +
                "            <value>Baipariguda</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-4\">\n" +
                "            <value>Potangi</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-5\">\n" +
                "            <value>Lamataput</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-6\">\n" +
                "            <value>Laxmipur</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-7\">\n" +
                "            <value>Chandrapur</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-8\">\n" +
                "            <value>K Singhpur</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-Block-9\">\n" +
                "            <value>Rayagada</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-ru48h95-4\">\n" +
                "            <value>Community</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-ru48h95-5\">\n" +
                "            <value>Others</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-ru48h95-2\">\n" +
                "            <value>Govt. Officials</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-ru48h95-3\">\n" +
                "            <value>Local Leaders</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-ru48h95-0\">\n" +
                "            <value>Trained Volunteer / Pragatisathi</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-ru48h95-1\">\n" +
                "            <value>Youth</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-kg4zj22-1\">\n" +
                "            <value>Sabyasachi Rout</value>\n" +
                "          </text>\n" +
                "          <text id=\"static_instance-kg4zj22-0\">\n" +
                "            <value>Brajamohan Otta</value>\n" +
                "          </text>\n" +
                "        </translation>\n" +
                "      </itext>\n" +
                "      <instance>\n" +
                "        <data id=\"snapshot_xml1\">\n" +
                "          <start/>\n" +
                "          <end/>\n" +
                "          <group_yv0ou78>\n" +
                "            <User_Type>State PMU Uday</User_Type>\n" +
                "            <User_Name/>\n" +
                "            <District/>\n" +
                "          </group_yv0ou78>\n" +
                "          <group_jf9sk41>\n" +
                "            <Block/>\n" +
                "            <GP/>\n" +
                "            <Village/>\n" +
                "            <Location/>\n" +
                "          </group_jf9sk41>\n" +
                "          <group_ww5yk85>\n" +
                "            <Purpose_Of_Visit/>\n" +
                "            <Whom_do_you_meet/>\n" +
                "            <Field_Difficulties_if_any/>\n" +
                "            <Remarks/>\n" +
                "            <Image/>\n" +
                "          </group_ww5yk85>\n" +
                "          <meta>\n" +
                "            <instanceID/>\n" +
                "          </meta>\n" +
                "        </data>\n" +
                "      </instance>\n" +
                "      <instance id=\"ru48h95\">\n" +
                "        <root>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-ru48h95-0</itextId>\n" +
                "            <name>trained_volunteer_or_pragatisathi</name>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-ru48h95-1</itextId>\n" +
                "            <name>youth</name>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-ru48h95-2</itextId>\n" +
                "            <name>govt_official</name>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-ru48h95-3</itextId>\n" +
                "            <name>local_leaders</name>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-ru48h95-4</itextId>\n" +
                "            <name>community</name>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-ru48h95-5</itextId>\n" +
                "            <name>others</name>\n" +
                "          </item>\n" +
                "        </root>\n" +
                "      </instance>\n" +
                "      <instance id=\"kg4zj22\">\n" +
                "        <root>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-kg4zj22-0</itextId>\n" +
                "            <name>brajamohan_otta</name>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-kg4zj22-1</itextId>\n" +
                "            <name>sabyasachi_rout</name>\n" +
                "          </item>\n" +
                "        </root>\n" +
                "      </instance>\n" +
                "      <instance id=\"jw98e46\">\n" +
                "        <root>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-jw98e46-0</itextId>\n" +
                "            <name>meeting</name>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-jw98e46-1</itextId>\n" +
                "            <name>liaison</name>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-jw98e46-2</itextId>\n" +
                "            <name>monitoring_sap</name>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-jw98e46-3</itextId>\n" +
                "            <name>monitoring_cap</name>\n" +
                "          </item>\n" +
                "        </root>\n" +
                "      </instance>\n" +
                "      <instance id=\"Block\">\n" +
                "        <root>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-0</itextId>\n" +
                "            <name>Bandhugaon</name>\n" +
                "            <District>Koraput</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-1</itextId>\n" +
                "            <name>Narayanpatna</name>\n" +
                "            <District>Koraput</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-2</itextId>\n" +
                "            <name>Nandapur</name>\n" +
                "            <District>Koraput</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-3</itextId>\n" +
                "            <name>Baipariguda</name>\n" +
                "            <District>Koraput</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-4</itextId>\n" +
                "            <name>Potangi</name>\n" +
                "            <District>Koraput</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-5</itextId>\n" +
                "            <name>Lamataput</name>\n" +
                "            <District>Koraput</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-6</itextId>\n" +
                "            <name>Laxmipur</name>\n" +
                "            <District>Koraput</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-7</itextId>\n" +
                "            <name>Chandrapur</name>\n" +
                "            <District>Rayagada</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-8</itextId>\n" +
                "            <name>K Singhpur</name>\n" +
                "            <District>Rayagada</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-9</itextId>\n" +
                "            <name>Rayagada</name>\n" +
                "            <District>Rayagada</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-10</itextId>\n" +
                "            <name>Gudari</name>\n" +
                "            <District>Rayagada</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-11</itextId>\n" +
                "            <name>Bissam Cuttack</name>\n" +
                "            <District>Rayagada</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-12</itextId>\n" +
                "            <name>Kalimela</name>\n" +
                "            <District>Malkangiri</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-13</itextId>\n" +
                "            <name>Khairaput</name>\n" +
                "            <District>Malkangiri</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-14</itextId>\n" +
                "            <name>Korukonda</name>\n" +
                "            <District>Malkangiri</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-15</itextId>\n" +
                "            <name>Kudmulgumma</name>\n" +
                "            <District>Malkangiri</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-16</itextId>\n" +
                "            <name>Malkangiri</name>\n" +
                "            <District>Malkangiri</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-17</itextId>\n" +
                "            <name>Mathili</name>\n" +
                "            <District>Malkangiri</District>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-Block-18</itextId>\n" +
                "            <name>Podia</name>\n" +
                "            <District>Malkangiri</District>\n" +
                "          </item>\n" +
                "        </root>\n" +
                "      </instance>\n" +
                "      <instance id=\"District\">\n" +
                "        <root>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-District-0</itextId>\n" +
                "            <name>Koraput</name>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-District-1</itextId>\n" +
                "            <name>Rayagada</name>\n" +
                "          </item>\n" +
                "          <item>\n" +
                "            <itextId>static_instance-District-2</itextId>\n" +
                "            <name>Malkangiri</name>\n" +
                "          </item>\n" +
                "        </root>\n" +
                "      </instance>\n" +
                "      <bind jr:preload=\"timestamp\" jr:preloadParams=\"start\" nodeset=\"/data/start\" type=\"dateTime\"/>\n" +
                "      <bind jr:preload=\"timestamp\" jr:preloadParams=\"end\" nodeset=\"/data/end\" type=\"dateTime\"/>\n" +
                "      <bind nodeset=\"/data/group_yv0ou78/User_Type\" required=\"false()\" type=\"string\"/>\n" +
                "      <bind nodeset=\"/data/group_yv0ou78/User_Name\" required=\"false()\" type=\"select1\"/>\n" +
                "      <bind nodeset=\"/data/group_yv0ou78/District\" required=\"false()\" type=\"select1\"/>\n" +
                "      <bind nodeset=\"/data/group_jf9sk41/Block\" required=\"false()\" type=\"select1\"/>\n" +
                "      <bind nodeset=\"/data/group_jf9sk41/GP\" required=\"false()\" type=\"string\"/>\n" +
                "      <bind nodeset=\"/data/group_jf9sk41/Village\" required=\"false()\" type=\"string\"/>\n" +
                "      <bind nodeset=\"/data/group_jf9sk41/Location\" required=\"false()\" type=\"geopoint\"/>\n" +
                "      <bind nodeset=\"/data/group_ww5yk85/Purpose_Of_Visit\" required=\"false()\" type=\"select\"/>\n" +
                "      <bind nodeset=\"/data/group_ww5yk85/Whom_do_you_meet\" required=\"false()\" type=\"select\"/>\n" +
                "      <bind nodeset=\"/data/group_ww5yk85/Field_Difficulties_if_any\" required=\"false()\" type=\"string\"/>\n" +
                "      <bind nodeset=\"/data/group_ww5yk85/Remarks\" required=\"false()\" type=\"string\"/>\n" +
                "      <bind nodeset=\"/data/group_ww5yk85/Image\" required=\"false()\" type=\"binary\"/>\n" +
                "      <bind calculate=\"concat('uuid:', uuid())\" nodeset=\"/data/meta/instanceID\" readonly=\"true()\" type=\"string\"/>\n" +
                "    </model>\n" +
                "  </h:head>\n" +
                "  <h:body>\n" +
                "    <group appearance=\"field-list\" ref=\"/data/group_yv0ou78\">\n" +
                "      <input ref=\"/data/group_yv0ou78/User_Type\">\n" +
                "        <label>User Type</label>\n" +
                "      </input>\n" +
                "      <select1 ref=\"/data/group_yv0ou78/User_Name\">\n" +
                "        <label>User Name</label>\n" +
                "        <item>\n" +
                "          <label>Brajamohan Otta</label>\n" +
                "          <value>brajamohan_otta</value>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "          <label>Sabyasachi Rout</label>\n" +
                "          <value>sabyasachi_rout</value>\n" +
                "        </item>\n" +
                "      </select1>\n" +
                "      <select1 ref=\"/data/group_yv0ou78/District\">\n" +
                "        <label>District</label>\n" +
                "        <item>\n" +
                "          <label>Koraput</label>\n" +
                "          <value>Koraput</value>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "          <label>Rayagada</label>\n" +
                "          <value>Rayagada</value>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "          <label>Malkangiri</label>\n" +
                "          <value>Malkangiri</value>\n" +
                "        </item>\n" +
                "      </select1>\n" +
                "    </group>\n" +
                "    <group appearance=\"field-list\" ref=\"/data/group_jf9sk41\">\n" +
                "      <select1 appearance=\"minimal\" ref=\"/data/group_jf9sk41/Block\">\n" +
                "        <label>Block</label>\n" +
                "        <itemset nodeset=\"instance('Block')/root/item[District= /data/group_yv0ou78/District ]\">\n" +
                "          <value ref=\"name\"/>\n" +
                "          <label ref=\"jr:itext(itextId)\"/>\n" +
                "        </itemset>\n" +
                "      </select1>\n" +
                "      <input ref=\"/data/group_jf9sk41/GP\">\n" +
                "        <label>GP</label>\n" +
                "      </input>\n" +
                "      <input ref=\"/data/group_jf9sk41/Village\">\n" +
                "        <label>Village</label>\n" +
                "      </input>\n" +
                "      <input ref=\"/data/group_jf9sk41/Location\">\n" +
                "        <label>Location</label>\n" +
                "      </input>\n" +
                "    </group>\n" +
                "    <group appearance=\"field-list\" ref=\"/data/group_ww5yk85\">\n" +
                "      <select appearance=\"minimal\" ref=\"/data/group_ww5yk85/Purpose_Of_Visit\">\n" +
                "        <label>Purpose Of Visit</label>\n" +
                "        <item>\n" +
                "          <label>Meeting</label>\n" +
                "          <value>meeting</value>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "          <label>Liaison</label>\n" +
                "          <value>liaison</value>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "          <label>Monitoring SAP</label>\n" +
                "          <value>monitoring_sap</value>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "          <label>Monitoring CAP</label>\n" +
                "          <value>monitoring_cap</value>\n" +
                "        </item>\n" +
                "      </select>\n" +
                "      <select appearance=\"minimal\" ref=\"/data/group_ww5yk85/Whom_do_you_meet\">\n" +
                "        <label>Whom do you meet</label>\n" +
                "        <item>\n" +
                "          <label>Trained Volunteer / Pragatisathi</label>\n" +
                "          <value>trained_volunteer_or_pragatisathi</value>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "          <label>Youth</label>\n" +
                "          <value>youth</value>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "          <label>Govt. Officials</label>\n" +
                "          <value>govt_officials</value>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "          <label>Local Leaders</label>\n" +
                "          <value>local_leaders</value>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "          <label>Community</label>\n" +
                "          <value>community</value>\n" +
                "        </item>\n" +
                "        <item>\n" +
                "          <label>Others</label>\n" +
                "          <value>others</value>\n" +
                "        </item>\n" +
                "      </select>\n" +
                "      <input ref=\"/data/group_ww5yk85/Field_Difficulties_if_any\">\n" +
                "        <label>Field Difficulties if any</label>\n" +
                "      </input>\n" +
                "      <input ref=\"/data/group_ww5yk85/Remarks\">\n" +
                "        <label>Remarks</label>\n" +
                "      </input>\n" +
                "      <upload mediatype=\"image/*\" ref=\"/data/group_ww5yk85/Image\">\n" +
                "        <label>Image</label>\n" +
                "      </upload>\n" +
                "    </group>\n" +
                "  </h:body>\n" +
                "</h:html>";

        File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/UDAY/forms",filename);
        try {

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            //Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //Toast.makeText(this, "File not saved", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e){
            e.printStackTrace();
            //Toast.makeText(this, "File not saved", Toast.LENGTH_SHORT).show();
        }


    }


    private boolean loadSharedPreferencesFromFile(File src) {
        // this should probably be in a thread if it ever gets big
        boolean res = false;
        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(src));
            GeneralSharedPreferences.getInstance().clear();

            // first object is preferences
            Map<String, ?> entries = (Map<String, ?>) input.readObject();

            AutoSendPreferenceMigrator.migrate(entries);

            for (Entry<String, ?> entry : entries.entrySet()) {
                GeneralSharedPreferences.getInstance().save(entry.getKey(), entry.getValue());
            }

            AuthDialogUtility.setWebCredentialsFromPreferences();

            AdminSharedPreferences.getInstance().clear();

            // second object is admin options
            Map<String, ?> adminEntries = (Map<String, ?>) input.readObject();
            for (Entry<String, ?> entry : adminEntries.entrySet()) {
                AdminSharedPreferences.getInstance().save(entry.getKey(), entry.getValue());
            }
            Collect.getInstance().initProperties();
            res = true;
        } catch (IOException | ClassNotFoundException e) {
            Timber.e(e, "Exception while loading preferences from file due to : %s ", e.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                Timber.e(ex, "Exception thrown while closing an input stream due to: %s ", ex.getMessage());
            }
        }
        return res;
    }

    /*
     * Used to prevent memory leaks
     */
    static class IncomingHandler extends Handler {
        private final WeakReference<MainMenuActivity4> target;

        IncomingHandler(MainMenuActivity4 target) {
            this.target = new WeakReference<MainMenuActivity4>(target);
        }

        @Override
        public void handleMessage(Message msg) {
            MainMenuActivity4 target = this.target.get();
            if (target != null) {
                target.updateButtons();
            }
        }
    }

    /**
     * notifies us that something changed
     */
    private class MyContentObserver extends ContentObserver {

        MyContentObserver() {
            super(null);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            handler.sendEmptyMessage(0);
        }
    }

}