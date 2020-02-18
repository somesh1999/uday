package org.odkuday.collect.android.injection.config;

import android.app.Application;

import org.odkuday.collect.android.activities.InstanceUploaderList;
import org.odkuday.collect.android.activities.InstanceUploaderList10;
import org.odkuday.collect.android.activities.InstanceUploaderList11;
import org.odkuday.collect.android.activities.InstanceUploaderList12;
import org.odkuday.collect.android.activities.InstanceUploaderList4;
import org.odkuday.collect.android.activities.InstanceUploaderList5;
import org.odkuday.collect.android.activities.InstanceUploaderList6;
import org.odkuday.collect.android.activities.InstanceUploaderList7;
import org.odkuday.collect.android.activities.InstanceUploaderList8;
import org.odkuday.collect.android.activities.InstanceUploaderList9;
import org.odkuday.collect.android.adapters.InstanceUploaderAdapter;
import org.odkuday.collect.android.application.Collect;
import org.odkuday.collect.android.fragments.DataManagerList;
import org.odkuday.collect.android.fragments.DataManagerList1;
import org.odkuday.collect.android.fragments.DataManagerList10;
import org.odkuday.collect.android.fragments.DataManagerList11;
import org.odkuday.collect.android.fragments.DataManagerList12;
import org.odkuday.collect.android.fragments.DataManagerList2;
import org.odkuday.collect.android.fragments.DataManagerList3;
import org.odkuday.collect.android.fragments.DataManagerList4;
import org.odkuday.collect.android.fragments.DataManagerList5;
import org.odkuday.collect.android.fragments.DataManagerList6;
import org.odkuday.collect.android.fragments.DataManagerList7;
import org.odkuday.collect.android.fragments.DataManagerList8;
import org.odkuday.collect.android.fragments.DataManagerList9;
import org.odkuday.collect.android.injection.ActivityBuilder;
import org.odkuday.collect.android.injection.config.scopes.PerApplication;
import org.odkuday.collect.android.tasks.sms.SmsSentBroadcastReceiver;
import org.odkuday.collect.android.tasks.sms.SmsNotificationReceiver;
import org.odkuday.collect.android.tasks.sms.SmsSender;
import org.odkuday.collect.android.tasks.sms.SmsService;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Primary module, bootstraps the injection system and
 * injects the main Collect instance here.
 * <p>
 * Shouldn't be modified unless absolutely necessary.
 */
@PerApplication
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class
})
public interface AppComponent {

    void inject(DataManagerList1 dataManagerList1);

    void inject(DataManagerList2 dataManagerList2);

    void inject(DataManagerList3 dataManagerList3);

    void inject(InstanceUploaderList4 instanceUploaderList4);

    void inject(InstanceUploaderList5 instanceUploaderList5);

    void inject(InstanceUploaderList6 instanceUploaderList6);

    void inject(InstanceUploaderList7 instanceUploaderList7);

    void inject(DataManagerList4 dataManagerList4);

    void inject(DataManagerList6 dataManagerList6);

    void inject(DataManagerList7 dataManagerList7);

    void inject(DataManagerList5 dataManagerList5);

    void inject(InstanceUploaderList8 instanceUploaderList8);

    void inject(DataManagerList8 dataManagerList8);

    void inject(InstanceUploaderList9 instanceUploaderList9);

    void inject(InstanceUploaderList10 instanceUploaderList10);

    void inject(InstanceUploaderList11 instanceUploaderList11);

    void inject(InstanceUploaderList12 instanceUploaderList12);

    void inject(DataManagerList9 dataManagerList9);

    void inject(DataManagerList10 dataManagerList10);

    void inject(DataManagerList11 dataManagerList11);

    void inject(DataManagerList12 dataManagerList12);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(Collect collect);

    void inject(SmsService smsService);

    void inject(SmsSender smsSender);

    void inject(SmsSentBroadcastReceiver smsSentBroadcastReceiver);

    void inject(SmsNotificationReceiver smsNotificationReceiver);

    void inject(InstanceUploaderList instanceUploaderList);

    void inject(InstanceUploaderAdapter instanceUploaderAdapter);

    void inject(DataManagerList dataManagerList);
}
