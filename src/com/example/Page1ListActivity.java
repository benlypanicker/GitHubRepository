package com.example;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.preferences.MainPreferencesActivity;
import com.example.rmtsampleflight.v1.RMTSAMPLEFLIGHTRequestHandler;
import com.example.rmtsampleflight.v1.entitytypes.Flight;
import com.example.rmtsampleflight.v1.helpers.IRMTSAMPLEFLIGHTRequestHandlerListener;
import com.example.rmtsampleflight.v1.helpers.RMTSAMPLEFLIGHTRequestID;
import com.sap.gwpa.proxy.RequestStatus;
import com.sap.gwpa.proxy.RequestStatus.StatusType;
import com.sap.gwpa.proxy.connectivity.authenticators.AuthenticatingException;
import com.sap.mobile.lib.request.IResponse;
import com.sap.mobile.lib.supportability.ILogger;
import com.sap.mobile.lib.supportability.Logger;

/**
 * List screen.
 */
public class Page1ListActivity extends ListActivity implements
		IRMTSAMPLEFLIGHTRequestHandlerListener
{
	private static final int PREFERENCE_ACTIVITY_REQUEST_CODE = 1;
	public static final String TAG = "MyFlightAndroidApp";
	private ILogger logger;
	
	// result of List Request
	private List<Flight> entries;

	// need handler for callbacks to the UI thread
	final Handler mHandler = new Handler(); 

	// create runnable for posting
	final Runnable mUpdateResults = new Runnable()
	{
		public void run()
		{
			updateResultsInUi();
		}
	};

	private Page1ListAdapter listAdapter;
	
	// connectivity error message
	private String emessage = "";

	/**
	 * Refresh UI from background thread
	 */
	protected void updateResultsInUi()
	{
		// Error occurred
		if (entries == null)
		{
			View loadingView = findViewById(com.example.R.id.loading_view);
			loadingView.setVisibility(View.GONE);
			Toast.makeText(getApplicationContext(), emessage, Toast.LENGTH_LONG).show();
			finish();
			return;
		}
		
		listAdapter = new Page1ListAdapter(this);
		listAdapter.setEntries(entries);

		setListAdapter(listAdapter);
		RelativeLayout loadingView = (RelativeLayout) findViewById(com.example.R.id.loading_view);
		
		// No results - empty set
		if (entries.size() == 0)
		{
			ProgressBar progressBar = (ProgressBar) findViewById(com.example.R.id.progressBar1);
			progressBar.setVisibility(View.GONE);
			
			TextView textView = new TextView(getApplicationContext());
			textView.setText(com.example.R.string.no_results);
			loadingView.addView(textView);
			return;
			
		}
		
		// No error and has results
		loadingView.setVisibility(View.GONE);
		
		// show search box
		View searchBoxView = findViewById(com.example.R.id.linearLayout1);
		searchBoxView.setVisibility(View.VISIBLE);

		EditText searchBox = (EditText) findViewById(com.example.R.id.search_box);
		FilterTextWatcher filterTextWatcher = new FilterTextWatcher(listAdapter);
		searchBox.addTextChangedListener(filterTextWatcher);
		searchBox.setVisibility(View.VISIBLE);
	}

	private class FilterTextWatcher implements TextWatcher
	{
		private Page1ListAdapter adapter;

		public FilterTextWatcher(Page1ListAdapter adapter)
		{
			this.adapter = adapter;
		}

		public void afterTextChanged(Editable s)
		{
		}

		public void beforeTextChanged(CharSequence s, int start, int count, int after)
		{
		}

		public void onTextChanged(CharSequence s, int start, int before, int count)
		{
			adapter.filter(s);
		}
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(com.example.R.layout.list);
		
		// initialize the Logger
		logger = new Logger();
		
		RMTSAMPLEFLIGHTRequestHandler.getInstance(getApplicationContext()).register(this,
				RMTSAMPLEFLIGHTRequestID.LOAD_FLIGHTCOLLECTION);

		// make the request
		// the response should be in "requestCompleted"
		RMTSAMPLEFLIGHTRequestHandler.getInstance(getApplicationContext()).loadFlightCollection();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		Intent intent = new Intent(this, Page2DetailsActivity.class);
		Flight item = (Flight) listAdapter.getItem(position);
		
		Page2DetailsActivity.parentEntry = item;
		
		// navigation to next screen
		startActivity(intent);

		super.onListItemClick(l, v, position, id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		getMenuInflater().inflate(com.example.R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case com.example.R.id.menu_item_settings:
				Intent intent = new Intent(Page1ListActivity.this,MainPreferencesActivity.class);
				startActivityForResult(intent, PREFERENCE_ACTIVITY_REQUEST_CODE);
				return true;
			case com.example.R.id.menu_item_logout:
				performLogout();
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
					Intent intent = new Intent(Page1ListActivity.this, LoginActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
	 			
					startActivity(intent);
					finish();
				}       
			}
		}
	}
	
	private void performLogout()
    {
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() 
		{
			public void onClick(DialogInterface dialog, int which) 
			{
				switch (which)
				{
					case DialogInterface.BUTTON_POSITIVE:
						//Yes button clicked
						(new AsyncTask<Void, Void, Boolean>()
					    {
					         @Override
					         protected Boolean doInBackground(Void... arg0) 
					         {
								try
								{
								       RMTSAMPLEFLIGHTRequestHandler.getInstance(getApplicationContext()).logout();
								       return true;
								}
								catch (AuthenticatingException e)
								{
								       logger.e(TAG, "Logout Failed " + e.getLocalizedMessage());
								       return false;
								}
							 }
							 @Override
							 protected void onPostExecute(Boolean result)
							 {
								// In the first page use finish to quit the application
								finish();
								Process.killProcess(Process.myPid());
							 }
						}).execute();
					break;
				}
			}
		};
		 
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(com.example.R.string.logout_title).setMessage(com.example.R.string.logout_msg).setPositiveButton(com.example.R.string.yes, dialogClickListener)
			.setNegativeButton(com.example.R.string.no, dialogClickListener).show();
	}
	

	@SuppressWarnings("unchecked")
	public void requestCompleted(RMTSAMPLEFLIGHTRequestID requestID, List<?> entries, RequestStatus requestStatus) 
	{
		// first check the request's status
		StatusType type = requestStatus.getType();

		if (type == StatusType.OK) 
		{
			if (requestID.equals(RMTSAMPLEFLIGHTRequestID.LOAD_FLIGHTCOLLECTION))
			{
				// cast to the right type
				this.entries = (List<Flight>) entries;
				// post in the UI
				mHandler.post(mUpdateResults);
			}
		} 
		else 
		{
			// do some error handling
			logger.e(TAG, "The request has returned with an error");
			entries = null;
			emessage = requestStatus.getMessage();
			mHandler.post(mUpdateResults);
		}

	}

	public void authenticationNeeded(String message) 
	{
		logger.e(TAG, "Authentication is needed");
		entries = null;
		emessage = message;
		mHandler.post(mUpdateResults);
		// navigate back to login page
		Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
	}

	public void batchCompleted(String batchID, IResponse response,
			RequestStatus requestStatus)
	{
		// here you can handle the response of the batch request.
	} 		
}
