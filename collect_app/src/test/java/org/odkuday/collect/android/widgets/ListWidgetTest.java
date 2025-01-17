package org.odkuday.collect.android.widgets;

import android.support.annotation.NonNull;

import org.odkuday.collect.android.widgets.base.GeneralSelectOneWidgetTest;
import org.robolectric.RuntimeEnvironment;

/**
 * @author James Knight
 */

public class ListWidgetTest extends GeneralSelectOneWidgetTest<ListWidget> {
    @NonNull
    @Override
    public ListWidget createWidget() {
        return new ListWidget(RuntimeEnvironment.application, formEntryPrompt, false, false);
    }
}
