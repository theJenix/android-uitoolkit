package com.jr.android.uitoolkit.view;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class TransitionOnClickListener implements OnClickListener {

    private Activity activity;
    private Class<? extends Activity> transitionClass;

    public TransitionOnClickListener(Activity activity, Class<? extends Activity> transitionClass) {
        this.activity = activity;
        this.transitionClass = transitionClass;
    }

    @Override
    public void onClick(View v) {
        new Transitioner(this.activity).transitionTo(this.transitionClass);
    }
}
