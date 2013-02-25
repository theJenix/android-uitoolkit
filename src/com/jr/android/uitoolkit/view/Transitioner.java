package com.jr.android.uitoolkit.view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Transitioner {
    private final Handler handler = new Handler();
    private Activity activity; 

    public Transitioner(Activity activity) {
        this.activity = activity;
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
    public final void transitionTo(Class<? extends Activity> activityClass) {
        try {
            transitionTo(activityClass, null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public final void transitionTo(Class<? extends Activity> activityClass, Bundle arguments) {
        try {
            transitionTo(activityClass, arguments, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void transitionToAndFinish(Class<? extends Activity> activityClass) {
        try {
            transitionTo(activityClass, null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public final void transitionToAndFinish(Class<? extends Activity> activityClass, Bundle arguments) {
        try {
            transitionTo(activityClass, arguments, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    protected void transitionTo(Class<? extends Activity> activityClass, Bundle arguments, boolean finish) {
        handler.post(new TransitionRunnable(activityClass, arguments));
        if (finish) {
            Transitioner.this.activity.finish();
        }
    }
    
    private boolean hasArguments(Object[] args) {
       return args != null && args.length > 0;
    }
    private Bundle bundleArguments(Class<? extends Activity> activityClass, Object[] args)
            throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        //TODO: this may or may not work the way we want
        Method method = activityClass.getMethod("bundleArguments", Object[].class);
        return (Bundle)method.invoke(null, args);
    }

    private class TransitionRunnable implements Runnable {

        private Class<? extends Activity> activityClass;
        private Bundle arguments;

        public TransitionRunnable(Class<? extends Activity> activityClass, Bundle arguments) {
            this.activityClass = activityClass;
            this.arguments     = arguments;
        }

        @Override
        public void run() {
            //fire up the conference selector app
    //       debugOut("Doin Stuff");
            Intent intent = new Intent();
    //        if (intent == null) {
    //            debugOut("Actvitiy is null");
    //        }
            //start at the Conference Picker activity
     //       intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
    //        intent.addFlags(Intent.FLAG_)
    //        intent.addFlags(Intent.FL)
            intent.setClass(Transitioner.this.activity, this.activityClass);
            if (this.arguments != null) {
                intent.putExtras(arguments);
            }
    //        intent.set
    //        debugOut("launching " + intent.getComponent().getClassName());
            //intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
            Transitioner.this.activity.startActivity(intent);
            
        }
    }
}
