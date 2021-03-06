/*
 *   Copyright (C) 2012 Alan Woolley
 *   
 *   See LICENSE.TXT for full license
 */
package uk.co.armedpineapple.corsixth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import android.util.Log;

import com.bugsense.trace.BugSenseHandler;

public class CorsixTHApplication extends android.app.Application {

	@Override
	public void onCreate() {
		super.onCreate();

		/*
		 * BugSense helps to discover bugs by reporting unhandled exceptions. If
		 * you want to use this, create a file called application.properties in
		 * the assets folder and insert the line:
		 * 
		 * bugsense.key=<your API key>
		 */
		
		Properties properties = new Properties();
		try {
			InputStream inputStream = getAssets()
					.open("application.properties");
			Log.d(getClass().getSimpleName(), "Loading properties");
			properties.load(inputStream);

			if (properties.containsKey("bugsense.key")) {
				Log.d(getClass().getSimpleName(), "Setting up bugsense");
				BugSenseHandler.setup(this,
						(String) properties.get("bugsense.key"));
			}

		} catch (IOException e) {
			Log.i(getClass().getSimpleName(), "No properties file found");
		}

	}

}
