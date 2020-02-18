/*
 * Copyright 2017 Nafundi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.odkuday.collect.android.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.CursorLoader;

import org.odkuday.collect.android.application.Collect;
import org.odkuday.collect.android.dto.Form;
import org.odkuday.collect.android.provider.FormsProviderAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to encapsulate all access to the {@link org.odkuday.collect.android.database.helpers.FormsDatabaseHelper#DATABASE_NAME}
 * For more information about this pattern go to https://en.wikipedia.org/wiki/Data_access_object
 */
public class FormsDao {

    public Cursor getFormsCursor() {
        return getFormsCursor(null, null, null, null);
    }

    public Cursor getFormsCursor(String selection, String[] selectionArgs) {
        return getFormsCursor(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"Volunteer / Pragatisathi"};
        return getFormsCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }






    // start of formsloader for the Linkage Of Youth (RC)//

    public Cursor getFormsCursor1() {
        return getFormsCursor1(null, null, null, null);
    }

    public Cursor getFormsCursor1(String selection, String[] selectionArgs) {
        return getFormsCursor1(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor1(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor1(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor1(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader1(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"Benefits Received (RC)"};
        return getFormsCursorLoader1(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader1(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader1(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader1(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader1(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }





    // start of formsloader for the district coordinator//

    public Cursor getFormsCursor2() {
        return getFormsCursor2(null, null, null, null);
    }

    public Cursor getFormsCursor2(String selection, String[] selectionArgs) {
        return getFormsCursor2(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor2(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor2(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor2(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader2(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"Benefits Received (DC)"};
        return getFormsCursorLoader2(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader2(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader2(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader2(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader2(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }




    // start of formsloader for the state coordinator//

    public Cursor getFormsCursor3() {
        return getFormsCursor3(null, null, null, null);
    }

    public Cursor getFormsCursor3(String selection, String[] selectionArgs) {
        return getFormsCursor3(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor3(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor3(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor3(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader3(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"Benefits Received (PO)"};
        return getFormsCursorLoader3(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader3(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader3(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader3(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader3(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }



    // start of formsloader for the Project mamager//

    public Cursor getFormsCursor8() {
        return getFormsCursor3(null, null, null, null);
    }

    public Cursor getFormsCursor8(String selection, String[] selectionArgs) {
        return getFormsCursor8(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor8(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor8(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor8(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader8(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"Benefits Received (PM)"};
        return getFormsCursorLoader8(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader8(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader8(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader8(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader8(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }







    // start of formsloader for the statePMU//

    public Cursor getFormsCursor4() {
        return getFormsCursor1(null, null, null, null);
    }

    public Cursor getFormsCursor4(String selection, String[] selectionArgs) {
        return getFormsCursor4(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor4(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor4(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor4(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader4(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"State PMU Uday"};
        return getFormsCursorLoader4(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader4(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader4(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader4(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader4(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }



    // start of formsloader for the dcmon//

    public Cursor getFormsCursor5() {
        return getFormsCursor5(null, null, null, null);
    }

    public Cursor getFormsCursor5(String selection, String[] selectionArgs) {
        return getFormsCursor5(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor5(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor5(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor5(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader5(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"District Coordinator"};
        return getFormsCursorLoader5(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader5(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader5(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader5(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader5(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }




    // start of formsloader for the regional coordinator//

    public Cursor getFormsCursor6() {
        return getFormsCursor6(null, null, null, null);
    }

    public Cursor getFormsCursor6(String selection, String[] selectionArgs) {
        return getFormsCursor6(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor6(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor6(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor6(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader6(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"Regional Coordinator"};
        return getFormsCursorLoader6(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader6(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader6(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader6(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader6(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }





    // start of formsloader for the cluster coordinator//

    public Cursor getFormsCursor7() {
        return getFormsCursor7(null, null, null, null);
    }

    public Cursor getFormsCursor7(String selection, String[] selectionArgs) {
        return getFormsCursor7(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor7(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor7(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor7(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader7(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"Cluster Coordinator"};
        return getFormsCursorLoader7(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader7(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader7(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader7(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader7(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }



    // start of formsloader for the state_pmu visit hq//

    public Cursor getFormsCursor9() {
        return getFormsCursor9(null, null, null, null);
    }

    public Cursor getFormsCursor9(String selection, String[] selectionArgs) {
        return getFormsCursor9(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor9(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor9(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor9(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader9(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"HQ Visit (PMU)"};
        return getFormsCursorLoader9(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader9(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader9(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader9(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader9(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }







    // start of formsloader for the dc visit hq//

    public Cursor getFormsCursor10() {
        return getFormsCursor10(null, null, null, null);
    }

    public Cursor getFormsCursor10(String selection, String[] selectionArgs) {
        return getFormsCursor10(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor10(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor10(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor10(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader10(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"HQ Visit (DC)"};
        return getFormsCursorLoader10(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader10(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader10(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader10(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader10(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }







    // start of formsloader for the rc visit hq//

    public Cursor getFormsCursor11() {
        return getFormsCursor11(null, null, null, null);
    }

    public Cursor getFormsCursor11(String selection, String[] selectionArgs) {
        return getFormsCursor11(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor11(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor11(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor11(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader11(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"HQ Visit (RC)"};
        return getFormsCursorLoader11(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader11(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader11(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader11(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader11(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }











    // start of formsloader for the cc visit hq//

    public Cursor getFormsCursor12() {
        return getFormsCursor12(null, null, null, null);
    }

    public Cursor getFormsCursor12(String selection, String[] selectionArgs) {
        return getFormsCursor12(null, selection, selectionArgs, null);
    }

    Cursor getFormsCursor12(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver().query(FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    private Cursor getFormsCursor12(String formId, String formVersion) {

        String[] selectionArgs;
        String selection;

        if (formVersion == null) {
            selectionArgs = new String[]{formId};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + " IS NULL";
        } else {
            selectionArgs = new String[]{formId, formVersion};
            selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=? AND "
                    + FormsProviderAPI.FormsColumns.JR_VERSION + "=?";
        }

        String order = FormsProviderAPI.FormsColumns.DATE + " DESC"; //as long as we allow to store multiple forms with the same id and version number, choose the newest one

        return getFormsCursor12(null, selection, selectionArgs, order);
    }

    private CursorLoader getFormsCursorLoader12(String sortOrder) {
        String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + "=?";
        String[] selectionArgs = {"HQ Visit (CC)"};
        return getFormsCursorLoader12(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFormsCursorLoader12(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFormsCursorLoader12(sortOrder);
        } else {
            String selection = FormsProviderAPI.FormsColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = new String[]{"%" + charSequence + "%"};
            cursorLoader = getFormsCursorLoader12(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    private CursorLoader getFormsCursorLoader12(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(Collect.getInstance(), FormsProviderAPI.FormsColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }
















    public Cursor getFormsCursorForFormId(String formId) {
        String selection = FormsProviderAPI.FormsColumns.JR_FORM_ID + "=?";
        String[] selectionArgs = {formId};

        return getFormsCursor(null, selection, selectionArgs, null);
    }

    public boolean isFormEncrypted(String formId, String formVersion) {
        boolean encrypted = false;

        Cursor cursor = getFormsCursor(formId, formVersion);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int base64RSAPublicKeyColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.BASE64_RSA_PUBLIC_KEY);
                    encrypted = cursor.getString(base64RSAPublicKeyColumnIndex) != null;
                }
            } finally {
                cursor.close();
            }
        }
        return encrypted;
    }

    public String getFormMediaPath(String formId, String formVersion) {
        String formMediaPath = null;

        Cursor cursor = getFormsCursor(formId, formVersion);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int formMediaPathColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.FORM_MEDIA_PATH);
                    formMediaPath = cursor.getString(formMediaPathColumnIndex);
                }
            } finally {
                cursor.close();
            }
        }
        return formMediaPath;
    }

    public Cursor getFormsCursorForFormFilePath(String formFilePath) {
        String selection = FormsProviderAPI.FormsColumns.FORM_FILE_PATH + "=?";
        String[] selectionArgs = {formFilePath};

        return getFormsCursor(null, selection, selectionArgs, null);
    }

    public Cursor getFormsCursorForMd5Hash(String md5Hash) {
        String selection = FormsProviderAPI.FormsColumns.MD5_HASH + "=?";
        String[] selectionArgs = {md5Hash};

        return getFormsCursor(null, selection, selectionArgs, null);
    }

    public void deleteFormsDatabase() {
        Collect.getInstance().getContentResolver().delete(FormsProviderAPI.FormsColumns.CONTENT_URI, null, null);
    }

    public void deleteFormsFromIDs(String[] idsToDelete) {
        String selection = FormsProviderAPI.FormsColumns._ID + " in (";
        for (int i = 0; i < idsToDelete.length - 1; i++) {
            selection += "?, ";
        }
        selection += "? )";

        //This will break if the number of forms to delete > SQLITE_MAX_VARIABLE_NUMBER (999)
        Collect.getInstance().getContentResolver().delete(FormsProviderAPI.FormsColumns.CONTENT_URI, selection, idsToDelete);
    }

    public void deleteFormsFromMd5Hash(String... hashes) {
        List<String> idsToDelete = new ArrayList<>();
        Cursor c = null;
        try {
            for (String hash : hashes) {
                c = getFormsCursorForMd5Hash(hash);
                if (c.getCount() > 0) {
                    c.moveToFirst();
                    String id = c.getString(c.getColumnIndex(FormsProviderAPI.FormsColumns._ID));
                    idsToDelete.add(id);
                }
                c.close();
                c = null;
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        deleteFormsFromIDs(idsToDelete.toArray(new String[idsToDelete.size()]));
    }

    public Uri saveForm(ContentValues values) {
        return Collect.getInstance().getContentResolver().insert(FormsProviderAPI.FormsColumns.CONTENT_URI, values);
    }

    public int updateForm(ContentValues values) {
        return updateForm(values, null, null);
    }

    public int updateForm(ContentValues values, String where, String[] whereArgs) {
        return Collect.getInstance().getContentResolver().update(FormsProviderAPI.FormsColumns.CONTENT_URI, values, where, whereArgs);
    }

    public List<Form> getFormsFromCursor(Cursor cursor) {
        List<Form> forms = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int idColumnIndex = cursor.getColumnIndex(BaseColumns._ID);
                int displayNameColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.DISPLAY_NAME);
                int descriptionColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.DESCRIPTION);
                int jrFormIdColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.JR_FORM_ID);
                int jrVersionColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.JR_VERSION);
                int formFilePathColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.FORM_FILE_PATH);
                int submissionUriColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.SUBMISSION_URI);
                int base64RSAPublicKeyColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.BASE64_RSA_PUBLIC_KEY);
                int displaySubtextColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.DISPLAY_SUBTEXT);
                int md5HashColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.MD5_HASH);
                int dateColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.DATE);
                int jrCacheFilePathColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.JRCACHE_FILE_PATH);
                int formMediaPathColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.FORM_MEDIA_PATH);
                int languageColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.LANGUAGE);
                int autoSendColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.AUTO_SEND);
                int autoDeleteColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.AUTO_DELETE);
                int lastDetectedFormVersionHashColumnIndex = cursor.getColumnIndex(FormsProviderAPI.FormsColumns.LAST_DETECTED_FORM_VERSION_HASH);

                Form form = new Form.Builder()
                        .id(cursor.getInt(idColumnIndex))
                        .displayName(cursor.getString(displayNameColumnIndex))
                        .description(cursor.getString(descriptionColumnIndex))
                        .jrFormId(cursor.getString(jrFormIdColumnIndex))
                        .jrVersion(cursor.getString(jrVersionColumnIndex))
                        .formFilePath(cursor.getString(formFilePathColumnIndex))
                        .submissionUri(cursor.getString(submissionUriColumnIndex))
                        .base64RSAPublicKey(cursor.getString(base64RSAPublicKeyColumnIndex))
                        .displaySubtext(cursor.getString(displaySubtextColumnIndex))
                        .md5Hash(cursor.getString(md5HashColumnIndex))
                        .date(cursor.getLong(dateColumnIndex))
                        .jrCacheFilePath(cursor.getString(jrCacheFilePathColumnIndex))
                        .formMediaPath(cursor.getString(formMediaPathColumnIndex))
                        .language(cursor.getString(languageColumnIndex))
                        .autoSend(cursor.getString(autoSendColumnIndex))
                        .autoDelete(cursor.getString(autoDeleteColumnIndex))
                        .lastDetectedFormVersionHash(cursor.getString(lastDetectedFormVersionHashColumnIndex))
                        .build();

                forms.add(form);
            } while (cursor.moveToNext());
        }
        return forms;
    }

    public ContentValues getValuesFromFormObject(Form form) {
        ContentValues values = new ContentValues();
        values.put(FormsProviderAPI.FormsColumns.DISPLAY_NAME, form.getDisplayName());
        values.put(FormsProviderAPI.FormsColumns.DESCRIPTION, form.getDescription());
        values.put(FormsProviderAPI.FormsColumns.JR_FORM_ID, form.getJrFormId());
        values.put(FormsProviderAPI.FormsColumns.JR_VERSION, form.getJrVersion());
        values.put(FormsProviderAPI.FormsColumns.FORM_FILE_PATH, form.getFormFilePath());
        values.put(FormsProviderAPI.FormsColumns.SUBMISSION_URI, form.getSubmissionUri());
        values.put(FormsProviderAPI.FormsColumns.BASE64_RSA_PUBLIC_KEY, form.getBASE64RSAPublicKey());
        values.put(FormsProviderAPI.FormsColumns.DISPLAY_SUBTEXT, form.getDisplaySubtext());
        values.put(FormsProviderAPI.FormsColumns.MD5_HASH, form.getMD5Hash());
        values.put(FormsProviderAPI.FormsColumns.DATE, form.getDate());
        values.put(FormsProviderAPI.FormsColumns.JRCACHE_FILE_PATH, form.getJrCacheFilePath());
        values.put(FormsProviderAPI.FormsColumns.FORM_MEDIA_PATH, form.getFormMediaPath());
        values.put(FormsProviderAPI.FormsColumns.LANGUAGE, form.getLanguage());
        return values;
    }
}
