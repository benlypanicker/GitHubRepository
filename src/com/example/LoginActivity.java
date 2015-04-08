package com.example;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sap.mobile.lib.supportability.ILogger;
import com.sap.mobile.lib.supportability.Logger;

import com.sap.gwpa.proxy.ILoginAsyncTask;
import com.sap.gwpa.proxy.connectivity.ConnectivitySettings;
import com.sap.gwpa.proxy.connectivity.DataVaultHelper;
import com.sap.gwpa.proxy.connectivity.SUPHelper;
import com.sap.gwpa.proxy.connectivity.SUPHelperException;

import com.example.preferences.MainPreferencesActivity;
import com.example.preferences.PreferencesUtilities;
import com.example.rmtsampleflight.v1.RMTSAMPLEFLIGHTRequestHandler;
import com.example.rmtsampleflight.v1.helpers.RMTSAMPLEFLIGHTLoginAsyncTask;

/**
 * Login screen
 */
public class LoginActivity extends Activity implements ILoginAsyncTask
{
	private static final int PREFERENCE_ACTIVITY_REQUEST_CODE = 1;
	
	public static final String TAG = "MyFlightAndroidApp";
	private ILogger logger;
	
	/* ******  */
    private String vaultPassword = "<vault password value>"; 
    private String vaultSalt = "<salt value>";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(com.example.R.layout.login);

		// initialize the Logger
		logger = new Logger();
		
		// load application preferences
		PreferencesUtilities prefUtils = PreferencesUtilities.getInstance(getApplicationContext());		
		ConnectivitySettings connectivitySettings = ConnectivitySettings.getInstance();
		prefUtils.updateConnectivitySettingsFromPreferences(connectivitySettings);
		
		// initialize the request handler
		RMTSAMPLEFLIGHTRequestHandler.getInstance(getApplicationContext()).setServiceDocumentURL(prefUtils.getServiceURL());
		RMTSAMPLEFLIGHTRequestHandler.getInstance(getApplicationContext()).setSAPClient(prefUtils.getServiceClient());
		RMTSAMPLEFLIGHTRequestHandler.getInstance(getApplicationContext()).setUseServiceNegotiation(true);
		RMTSAMPLEFLIGHTRequestHandler.getInstance(getApplicationContext()).setUseLocalMetadata(false);
		RMTSAMPLEFLIGHTRequestHandler.getInstance(getApplicationContext()).setUseJson(false);
		
		try 
		{
			// check if the user's device is already registered
			if (isCredentialsSaved(vaultPassword, vaultSalt)) 
			{
				showProgressBar();
				new RMTSAMPLEFLIGHTLoginAsyncTask(LoginActivity.this, getApplicationContext(), null, null, vaultPassword, vaultSalt).execute();
			}
		} 
		catch (SUPHelperException e) 
		{
			logger.e(TAG, e.getMessage());
		}
		
		Button button = (Button) findViewById(com.example.R.id.btnLogin);
		button.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				EditText username = (EditText) findViewById(com.example.R.id.username);
				String userString = username.getText().toString();
				EditText password = (EditText) findViewById(com.example.R.id.password);
				String passwordString = password.getText().toString();
				
				new RMTSAMPLEFLIGHTLoginAsyncTask(LoginActivity.this, getApplicationContext(), userString, passwordString, vaultPassword, vaultSalt).execute();
				
				showProgressBar();
			}
		});
	}
	
	private boolean isCredentialsSaved(String vaultPassword, String vaultSalt) throws SUPHelperException 
	{
		ConnectivitySettings connectivitySettings = ConnectivitySettings.getInstance();
		DataVaultHelper dataVaultHelper = DataVaultHelper.getInstance(getApplicationContext());
		
		if (connectivitySettings.isSUPMode())
		{
			return (SUPHelper.isUserRegistered() && dataVaultHelper.isCredentialsSaved(vaultPassword, vaultSalt));
		}
		else
		{
			return (dataVaultHelper.isCredentialsSaved(vaultPassword, vaultSalt));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(com.example.R.menu.main_menu, menu);
		MenuItem logoutMenuItem = menu.findItem(R.id.menu_item_logout);
		logoutMenuItem.setVisible(false);
		
		return true;		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
		{
			case com.example.R.id.menu_item_settings:
				Intent intent = new Intent(LoginActivity.this, MainPreferencesActivity.class);
				startActivityForResult(intent, PREFERENCE_ACTIVITY_REQUEST_CODE);
				return true;
			default:
				super.onOptionsItemSelected(item);
				return false;
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		if (requestCode == PREFERENCE_ACTIVITY_REQUEST_CODE) 
		{
			if (resultCode == RESULT_OK)
			{      
				if (data.getBooleanExtra(MainPreferencesActivity.PREFERENCES_CHANGED_FLAG_KEY, false))
				{
					// if Preferences state was change then clear the activities from cache and restart the login activity
					Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	 			
					startActivity(intent);
				}       
			}
		}
	}
	
	public void onLoginResult(Boolean loginResult) 
	{
    	if (loginResult)
    	{
  			// navigation to first screen
  			Intent intent = new Intent(getApplicationContext(), Page1ListActivity.class);
  			startActivity(intent);
  			finish();
      	}
      	else
      	{
      		Toast.makeText(getApplicationContext(), com.example.R.string.login_has_failed, Toast.LENGTH_LONG).show();
      		hideProgressBar();
      	}
	}
	
	
	/**
	 * Hides the progress bar and displays the form view
	 */
	private void hideProgressBar() 
	{
		View formView = findViewById(com.example.R.id.form);
		formView.setVisibility(View.VISIBLE);
		
		View loadingView = findViewById(com.example.R.id.loading_view);
		loadingView.setVisibility(View.GONE);
	}

	/**
	 * Hides the form view and displays the progress bar
	 */
	private void showProgressBar() 
	{
		View formView = findViewById(com.example.R.id.form);
		formView.setVisibility(View.GONE);
		
		View loadingView = findViewById(com.example.R.id.loading_view);
		loadingView.setVisibility(View.VISIBLE);
	}
}
