package com.jr.android.uitoolkit.view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public abstract class AViewController extends Activity {
    private final Handler handler = new Handler();
    private Transitioner transitioner; 

    public AViewController() {
        this.transitioner = new Transitioner(this);
    }

    protected Handler getHandler() {
        return handler;
    }
    /**
     * Transition to a new view, by way of a presenter.
     * 
     * NOTE: this method is final, to reduce the amount of work a subclass needs
     * to do to hook into the transition process.  Simply override the other method
     * and rest assured it will be called.
     * 
     * @param presenter
     */
    public final void transitionTo(Class<? extends AViewController> activityClass, Bundle args) {
        this.transitioner.transitionTo(activityClass, args);
    }

    public final void transitionToAndFinish(Class<? extends AViewController> activityClass, Bundle args) {
        this.transitioner.transitionToAndFinish(activityClass, args);
    }
}
