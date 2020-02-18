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
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import org.odkuday.collect.android.R;

/**
 * Responsible for displaying buttons to launch the major activities. Launches
 * some activities based on returns of others.
 *
 * @author Carl Hartung (carlhartung@gmail.com)
 * @author Yaw Anokwa (yanokwa@gmail.com)
 */
public class enrollment_report extends CollectAbstractActivity {

    private static final int PASSWORD_DIALOG = 1;

    private static final boolean EXIT = true;
    Button district;
    Button regional;
    Button officer;
    Button manager;


    public static void startActivityAndCloseAllOthers(Activity activity) {
        activity.startActivity(new Intent(activity, MainMenuActivity.class));
        activity.overridePendingTransition(0, 0);
        activity.finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_district);
        initToolbar();

        district=(Button) findViewById(R.id.fill_district);
        district.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),MainMenuActivity2.class);
                startActivity(i);
            }
        });


        regional=(Button) findViewById(R.id.fill_regional);
        regional.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),MainMenuActivity1.class);
                startActivity(i);
            }
        });


        officer=(Button) findViewById(R.id.project_officer);
        officer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),MainMenuActivity3.class);
                startActivity(i);
            }
        });


        manager=(Button) findViewById(R.id.project_manager);
        manager.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),MainMenuActivity8.class);
                startActivity(i);
            }
        });






    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("UDAY");
        setSupportActionBar(toolbar);
    }
}


