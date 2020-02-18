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
import android.support.v4.content.CursorLoader;

import org.odkuday.collect.android.application.Collect;
import org.odkuday.collect.android.dto.Instance;
import org.odkuday.collect.android.provider.InstanceProviderAPI;
import org.odkuday.collect.android.utilities.ApplicationConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to encapsulate all access to the {@link org.odkuday.collect.android.provider.InstanceProvider#DATABASE_NAME}
 * For more information about this pattern go to https://en.wikipedia.org/wiki/Data_access_object
 */
public class InstancesDao {

    public Cursor getSentInstancesCursor() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " =? ";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED};
        String sortOrder = InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSentInstancesCursorLoader(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSentInstancesCursorLoader(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " =? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }

    public CursorLoader getSentInstancesCursorLoader(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " =? ";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }


    // start of unsent cursor loader
    public Cursor getUnsentInstancesCursor() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Volunteer / Pragatisathi"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Volunteer / Pragatisathi"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }

    // for the Benefits Received (RC)

    public Cursor getUnsentInstancesCursor1() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Benefits Received (RC)"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader1(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Benefits Received (RC)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader1(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader1(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }


    // for the district coordinator

    public Cursor getUnsentInstancesCursor2() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Benefits Received (DC)"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader2(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Benefits Received (DC)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader2(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader2(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }



    // for the state coordinator

    public Cursor getUnsentInstancesCursor3() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Benefits Received (PO)"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader3(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Benefits Received (PO)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader3(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader3(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }


    // for the projrct manager

    public Cursor getUnsentInstancesCursor8() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Benefits Received (PM)"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader8(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Benefits Received (PM)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader8(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader8(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }

  // for statepmuuday//

    public Cursor getUnsentInstancesCursor4() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"State PMU Uday"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader4(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"State PMU Uday"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader4(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader4(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }



    // for the District Coordinator(Mon)

    public Cursor getUnsentInstancesCursor5() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"District Coordinator"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader5(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"District Coordinator"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader5(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader5(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }




    // for the regional coordinator

    public Cursor getUnsentInstancesCursor6() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Regional Coordinator"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader6(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Regional Coordinator"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader6(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader6(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }



    // for the cluster coordinator

    public Cursor getUnsentInstancesCursor7() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Cluster Coordinator"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader7(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"Cluster Coordinator"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader7(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader7(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }





    // for the visit state PMU

    public Cursor getUnsentInstancesCursor9() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"HQ Visit (PMU)"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader9(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"HQ Visit (PMU)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader9(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader9(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }




    // for the visit DC

    public Cursor getUnsentInstancesCursor10() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"HQ Visit (DC)"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader10(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"HQ Visit (DC)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader10(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader10(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }







    // for the visit RC

    public Cursor getUnsentInstancesCursor11() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"HQ Visit (RC)"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader11(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"HQ Visit (RC)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader11(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader11(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }






    // for the visit CC

    public Cursor getUnsentInstancesCursor12() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"HQ Visit (CC)"};
        String sortOrder = InstanceProviderAPI.InstanceColumns.STATUS + " DESC, " + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader12(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + " !=? AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_SUBMITTED,"HQ Visit (CC)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getUnsentInstancesCursorLoader12(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getUnsentInstancesCursorLoader12(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.STATUS + " !=? and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }









    // for the State PMU Uday

    public Cursor getSavedInstancesCursor4(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader4(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"State PMU Uday"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader4(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader4(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }


    // for the District Coordinator

    public Cursor getSavedInstancesCursor5(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader5(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"District Coordinator"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader5(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader5(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }



    // for the regional coordinator

    public Cursor getSavedInstancesCursor6(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader6(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"Regional Coordinator"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader6(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader6(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }



    // for the cluster coordinator

    public Cursor getSavedInstancesCursor7(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader7(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"Cluster Coordinator"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader7(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader7(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }








    // for the Volunteer / Pragatisathis

    public Cursor getSavedInstancesCursor(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"Volunteer / Pragatisathi"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }


    // for the Benefits Received (RC)s

    public Cursor getSavedInstancesCursor1(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader1(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"Benefits Received (RC)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader1(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader1(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }



    // for the district coordinator

    public Cursor getSavedInstancesCursor2(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader2(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"Benefits Received (DC)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader2(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader2(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }



    // for the state coordinator

    public Cursor getSavedInstancesCursor3(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader3(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"Benefits Received (PO)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader3(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader3(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }





    // for the project manager

    public Cursor getSavedInstancesCursor8(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader8(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"Benefits Received (PM)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader8(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader8(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }




    // for the state pmu visit

    public Cursor getSavedInstancesCursor9(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader9(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"HQ Visit (PMU)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader9(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader9(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }





    // for the dc visit

    public Cursor getSavedInstancesCursor10(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader10(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"HQ Visit (DC)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader10(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader10(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }




    // for the RC visit

    public Cursor getSavedInstancesCursor11(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader11(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"HQ Visit (RC)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader11(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader11(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }





    // for the CC visit

    public Cursor getSavedInstancesCursor12(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL ";

        return getInstancesCursor(null, selection, null, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader12(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL AND "+ InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " =?";
        String[] selectionArgs = {"HQ Visit (CC)"};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getSavedInstancesCursorLoader12(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getSavedInstancesCursorLoader12(sortOrder);
        } else {
            String selection =
                    InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {"%" + charSequence + "%"};
            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }








    public Cursor getFinalizedInstancesCursor() {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + "=? or " + InstanceProviderAPI.InstanceColumns.STATUS + "=?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_COMPLETE, InstanceProviderAPI.STATUS_SUBMISSION_FAILED};
        String sortOrder = InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFinalizedInstancesCursorLoader(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.STATUS + "=? or " + InstanceProviderAPI.InstanceColumns.STATUS + "=?";
        String[] selectionArgs = {InstanceProviderAPI.STATUS_COMPLETE, InstanceProviderAPI.STATUS_SUBMISSION_FAILED};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getFinalizedInstancesCursorLoader(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getFinalizedInstancesCursorLoader(sortOrder);
        } else {
            String selection =
                    "(" + InstanceProviderAPI.InstanceColumns.STATUS + "=? or "
                            + InstanceProviderAPI.InstanceColumns.STATUS + "=?) and "
                            + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";
            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_COMPLETE,
                    InstanceProviderAPI.STATUS_SUBMISSION_FAILED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }

        return cursorLoader;
    }

    public Cursor getInstancesCursorForFilePath(String path) {
        String selection = InstanceProviderAPI.InstanceColumns.INSTANCE_FILE_PATH + "=?";
        String[] selectionArgs = {path};

        return getInstancesCursor(null, selection, selectionArgs, null);
    }

    public Cursor getAllCompletedUndeletedInstancesCursor() {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and ("
                + InstanceProviderAPI.InstanceColumns.STATUS + "=? or "
                + InstanceProviderAPI.InstanceColumns.STATUS + "=? or "
                + InstanceProviderAPI.InstanceColumns.STATUS + "=?)";

        String[] selectionArgs = {InstanceProviderAPI.STATUS_COMPLETE,
                InstanceProviderAPI.STATUS_SUBMISSION_FAILED,
                InstanceProviderAPI.STATUS_SUBMITTED};
        String sortOrder = InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " ASC";

        return getInstancesCursor(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getAllCompletedUndeletedInstancesCursorLoader(String sortOrder) {
        String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and ("
                + InstanceProviderAPI.InstanceColumns.STATUS + "=? or "
                + InstanceProviderAPI.InstanceColumns.STATUS + "=? or "
                + InstanceProviderAPI.InstanceColumns.STATUS + "=?)";

        String[] selectionArgs = {InstanceProviderAPI.STATUS_COMPLETE,
                InstanceProviderAPI.STATUS_SUBMISSION_FAILED,
                InstanceProviderAPI.STATUS_SUBMITTED};

        return getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getCompletedUndeletedInstancesCursorLoader(CharSequence charSequence, String sortOrder) {
        CursorLoader cursorLoader;
        if (charSequence.length() == 0) {
            cursorLoader = getAllCompletedUndeletedInstancesCursorLoader(sortOrder);
        } else {
            String selection = InstanceProviderAPI.InstanceColumns.DELETED_DATE + " IS NULL and ("
                    + InstanceProviderAPI.InstanceColumns.STATUS + "=? or "
                    + InstanceProviderAPI.InstanceColumns.STATUS + "=? or "
                    + InstanceProviderAPI.InstanceColumns.STATUS + "=?) and "
                    + InstanceProviderAPI.InstanceColumns.DISPLAY_NAME + " LIKE ?";

            String[] selectionArgs = {
                    InstanceProviderAPI.STATUS_COMPLETE,
                    InstanceProviderAPI.STATUS_SUBMISSION_FAILED,
                    InstanceProviderAPI.STATUS_SUBMITTED,
                    "%" + charSequence + "%"};

            cursorLoader = getInstancesCursorLoader(null, selection, selectionArgs, sortOrder);
        }
        return cursorLoader;
    }

    public Cursor getInstancesCursorForId(String id) {
        String selection = InstanceProviderAPI.InstanceColumns._ID + "=?";
        String[] selectionArgs = {id};

        return getInstancesCursor(null, selection, selectionArgs, null);
    }

    public Cursor getInstancesCursor(String selection, String[] selectionArgs) {
        return getInstancesCursor(null, selection, selectionArgs, null);
    }

    public Cursor getInstancesCursor(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return Collect.getInstance().getContentResolver()
                .query(InstanceProviderAPI.InstanceColumns.CONTENT_URI, projection, selection, selectionArgs, sortOrder);
    }

    public CursorLoader getInstancesCursorLoader(String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return new CursorLoader(
                Collect.getInstance(),
                InstanceProviderAPI.InstanceColumns.CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder);
    }

    public Uri saveInstance(ContentValues values) {
        return Collect.getInstance().getContentResolver().insert(InstanceProviderAPI.InstanceColumns.CONTENT_URI, values);
    }

    public int updateInstance(ContentValues values, String where, String[] whereArgs) {
        return Collect.getInstance().getContentResolver().update(InstanceProviderAPI.InstanceColumns.CONTENT_URI, values, where, whereArgs);
    }

    public void deleteInstancesDatabase() {
        Collect.getInstance().getContentResolver().delete(InstanceProviderAPI.InstanceColumns.CONTENT_URI, null, null);
    }

    public void deleteInstancesFromIDs(List<String> ids) {
        int count = ids.size();
        int counter = 0;
        while (count > 0) {
            String[] selectionArgs = null;
            if (count > ApplicationConstants.SQLITE_MAX_VARIABLE_NUMBER) {
                selectionArgs = new String[
                        ApplicationConstants.SQLITE_MAX_VARIABLE_NUMBER];
            } else {
                selectionArgs = new String[count];
            }

            StringBuilder selection = new StringBuilder();
            selection.append(InstanceProviderAPI.InstanceColumns.INSTANCE_FILE_PATH + " IN (");
            int j = 0;
            while (j < selectionArgs.length) {
                selectionArgs[j] = ids.get(
                        counter * ApplicationConstants.SQLITE_MAX_VARIABLE_NUMBER + j);
                selection.append('?');

                if (j != selectionArgs.length - 1) {
                    selection.append(',');
                }
                j++;
            }
            counter++;
            count -= selectionArgs.length;
            selection.append(')');
            Collect.getInstance().getContentResolver()
                    .delete(InstanceProviderAPI.InstanceColumns.CONTENT_URI,
                            selection.toString(), selectionArgs);

        }
    }

    public List<Instance> getInstancesFromCursor(Cursor cursor) {
        List<Instance> instances = new ArrayList<>();
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    int displayNameColumnIndex = cursor.getColumnIndex(InstanceProviderAPI.InstanceColumns.DISPLAY_NAME);
                    int submissionUriColumnIndex = cursor.getColumnIndex(InstanceProviderAPI.InstanceColumns.SUBMISSION_URI);
                    int canEditWhenCompleteIndex = cursor.getColumnIndex(InstanceProviderAPI.InstanceColumns.CAN_EDIT_WHEN_COMPLETE);
                    int instanceFilePathIndex = cursor.getColumnIndex(InstanceProviderAPI.InstanceColumns.INSTANCE_FILE_PATH);
                    int jrFormIdColumnIndex = cursor.getColumnIndex(InstanceProviderAPI.InstanceColumns.JR_FORM_ID);
                    int jrVersionColumnIndex = cursor.getColumnIndex(InstanceProviderAPI.InstanceColumns.JR_VERSION);
                    int statusColumnIndex = cursor.getColumnIndex(InstanceProviderAPI.InstanceColumns.STATUS);
                    int lastStatusChangeDateColumnIndex = cursor.getColumnIndex(InstanceProviderAPI.InstanceColumns.LAST_STATUS_CHANGE_DATE);
                    int displaySubtextColumnIndex = cursor.getColumnIndex(InstanceProviderAPI.InstanceColumns.DISPLAY_SUBTEXT);
                    int deletedDateColumnIndex = cursor.getColumnIndex(InstanceProviderAPI.InstanceColumns.DELETED_DATE);

                    Instance instance = new Instance.Builder()
                            .displayName(cursor.getString(displayNameColumnIndex))
                            .submissionUri(cursor.getString(submissionUriColumnIndex))
                            .canEditWhenComplete(cursor.getString(canEditWhenCompleteIndex))
                            .instanceFilePath(cursor.getString(instanceFilePathIndex))
                            .jrFormId(cursor.getString(jrFormIdColumnIndex))
                            .jrVersion(cursor.getString(jrVersionColumnIndex))
                            .status(cursor.getString(statusColumnIndex))
                            .lastStatusChangeDate(cursor.getLong(lastStatusChangeDateColumnIndex))
                            .displaySubtext(cursor.getString(displaySubtextColumnIndex))
                            .deletedDate(cursor.getLong(deletedDateColumnIndex))
                            .build();

                    instances.add(instance);
                }
            } finally {
                cursor.close();
            }
        }
        return instances;
    }

    public ContentValues getValuesFromInstanceObject(Instance instance) {
        ContentValues values = new ContentValues();
        values.put(InstanceProviderAPI.InstanceColumns.DISPLAY_NAME, instance.getDisplayName());
        values.put(InstanceProviderAPI.InstanceColumns.SUBMISSION_URI, instance.getSubmissionUri());
        values.put(InstanceProviderAPI.InstanceColumns.CAN_EDIT_WHEN_COMPLETE, instance.getCanEditWhenComplete());
        values.put(InstanceProviderAPI.InstanceColumns.INSTANCE_FILE_PATH, instance.getInstanceFilePath());
        values.put(InstanceProviderAPI.InstanceColumns.JR_FORM_ID, instance.getJrFormId());
        values.put(InstanceProviderAPI.InstanceColumns.JR_VERSION, instance.getJrVersion());
        values.put(InstanceProviderAPI.InstanceColumns.STATUS, instance.getStatus());
        values.put(InstanceProviderAPI.InstanceColumns.LAST_STATUS_CHANGE_DATE, instance.getLastStatusChangeDate());
        values.put(InstanceProviderAPI.InstanceColumns.DISPLAY_SUBTEXT, instance.getDisplaySubtext());
        values.put(InstanceProviderAPI.InstanceColumns.DELETED_DATE, instance.getDeletedDate());

        return values;
    }
}
