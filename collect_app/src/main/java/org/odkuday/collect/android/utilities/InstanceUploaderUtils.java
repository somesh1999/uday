/*
 * Copyright 2018 Nafundi
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

package org.odkuday.collect.android.utilities;

import android.database.Cursor;

import org.odkuday.collect.android.R;
import org.odkuday.collect.android.application.Collect;
import org.odkuday.collect.android.provider.InstanceProviderAPI;

import java.util.HashMap;

public class InstanceUploaderUtils {

    public static final String DEFAULT_SUCCESSFUL_TEXT = "full submission upload was successful!";

    private InstanceUploaderUtils() {
    }

    public static String getUploadResultMessage(Cursor results, HashMap<String, String> result) {
        StringBuilder queryMessage = new StringBuilder();
        try {
            if (results != null && results.getCount() > 0) {
                results.moveToPosition(-1);
                while (results.moveToNext()) {
                    String name =
                            results.getString(
                                    results.getColumnIndex(InstanceProviderAPI.InstanceColumns.DISPLAY_NAME));
                    String id = results.getString(results.getColumnIndex(InstanceProviderAPI.InstanceColumns._ID));
                    String text = localizeDefaultAggregateSuccessfulText(result.get(id));
                    queryMessage
                            .append(name)
                            .append(" - ")
                            .append(text)
                            .append("\n\n");
                }
            }
        } finally {
            if (results != null) {
                results.close();
            }
        }
        return String.valueOf(queryMessage);
    }

    private static String localizeDefaultAggregateSuccessfulText(String text) {
        if (text != null && text.equals(DEFAULT_SUCCESSFUL_TEXT)) {
            text = Collect.getInstance().getString(R.string.success);
        }
        return text;
    }
}