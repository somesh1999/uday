package org.odkuday.collect.android.activities;

/**
 * Created by KIIT on 05-12-2018.
 */

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
public class monitoring extends CollectAbstractActivity {

    private static final int PASSWORD_DIALOG = 1;

    private static final boolean EXIT = true;
    Button volunteer;
    Button facilitator;
    Button districtCoordinator;
    Button stateCoordinator;

    public static void startActivityAndCloseAllOthers(Activity activity) {
        activity.startActivity(new Intent(activity, MainMenuActivity.class));
        activity.overridePendingTransition(0, 0);
        activity.finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitoring);
        initToolbar();

        volunteer=(Button) findViewById(R.id.statepmu);
        volunteer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),MainMenuActivity4.class);
                startActivity(i);
            }
        });


        facilitator=(Button) findViewById(R.id.district);
        facilitator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),MainMenuActivity5.class);
                startActivity(i);
            }
        });

        districtCoordinator=(Button) findViewById(R.id.regional);
        districtCoordinator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),MainMenuActivity6.class);
                startActivity(i);
            }
        });

        stateCoordinator=(Button) findViewById(R.id.cluster);
        stateCoordinator.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getBaseContext(),MainMenuActivity7.class);
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


