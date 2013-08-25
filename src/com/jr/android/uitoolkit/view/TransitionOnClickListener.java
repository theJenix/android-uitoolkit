package com.jr.android.uitoolkit.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class TransitionOnClickListener implements OnClickListener {

    private final Activity activity;
    private final Class<? extends Activity> transitionClass;
    private final boolean finish;
    private Bundle arguments;

    public TransitionOnClickListener(Activity activity, Class<? extends Activity> transitionClass) {
        this.activity = activity;
        this.transitionClass = transitionClass;
        this.arguments = null;
        this.finish = false;
    }

    public TransitionOnClickListener(Activity activity, Class<? extends Activity> transitionClass, boolean finish) {
        this.activity = activity;
        this.transitionClass = transitionClass;
        this.arguments = null;
        this.finish = finish;
    }

    public TransitionOnClickListener(Activity activity, Class<? extends Activity> transitionClass, Bundle arguments, boolean finish) {
        this.activity = activity;
        this.transitionClass = transitionClass;
        this.arguments = arguments;
        this.finish = finish;
    }

    @Override
    public void onClick(View v) {
        new Transitioner(this.activity).transitionTo(this.transitionClass, arguments, this.finish);
    }
}
