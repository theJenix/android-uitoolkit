package com.jr.android.uitoolkit.pres;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public abstract class APresenter implements IPresenter {

	private static boolean presenting = false;
    private Context context;
	private Class<? extends Activity> activityClass;

	public APresenter(Context context,
			Class<? extends Activity> activityClass) {
		this.context = context;
		this.activityClass = activityClass;
	}

	protected Context getContext() {
        return context;
    }

	public void startActivity() {
	    APresenter.presenting = true;
	    try {
	        startActivity(null);
	    } finally {
	        APresenter.presenting = false;
	    }
	}

	public void startActivity(Bundle parameters) {
    	//fire up the conference selector app
//   	 debugOut("Doin Stuff");
        Intent intent = new Intent();
//        if (intent == null) {
//            debugOut("Actvitiy is null");
//        }
        //start at the Conference Picker activity
 //       intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
//        intent.addFlags(Intent.FLAG_)
//        intent.addFlags(Intent.FL)
        intent.setClass(this.context, this.activityClass);
        if (parameters != null) {
        	intent.putExtras(parameters);
        }
//        intent.set
//        debugOut("launching " + intent.getComponent().getClassName());
        //intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        this.context.startActivity(intent);
	}

    public static boolean isPresenting() {
        return APresenter.presenting;
    }
}
