package com.jr.android.uitoolkit.view;

import android.app.Activity;
import android.os.Handler;

import com.jr.android.uitoolkit.pres.IPresenter;

public abstract class AViewController extends Activity {
    private final Handler handler = new Handler(); 

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
    public final void transitionTo(IPresenter presenter) {
        transitionTo(presenter, false);
    }

    public void transitionTo(IPresenter presenter, boolean finish) {
        handler.post(presenter);
        if (finish) {
            finish();
        }
    }
}
