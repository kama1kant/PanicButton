/*
 * Copyright (C) 2011 www.itcsolutions.eu
 *
 * This file is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 2.1, or (at your
 * option) any later version.
 *
 * This file is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 *
 */

/**
 *
 * @author Catalin - www.itcsolutions.eu
 * @version 2011
 *
 */
package eu.itcsolutions.android.tutorial;

import java.io.IOException;
import java.util.List;
import java.util.Locale;





import eu.itcsolutions.android.tutorial.Contact;
import eu.itcsolutions.android.tutorial.DatabaseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.telephony.SmsManager;

public class SecondActivity extends Activity {
	String MySms = null;
	private EditText edittext1, edittext2;
	private TextView t,t1,t2,t3,t4;
	Button button;
	Button btnupdate;
	Button btndisplay;
	String log;
	int count=0;
	Button buttonSend;
	Button btndelete;

	//GPS strat
	private LocationManager locationMangaer=null;
	private LocationListener locationListener=null; 

	private Button btnGetLocation = null;
	private EditText editLocation = null; 

	//private ProgressBar pb =null;

	private static final String TAG = "Debug";
	private Boolean flag = false;

	//gps end

	//private ListView lv;

	String name, phone, name1,phone1;
	DatabaseHandler db = new DatabaseHandler(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.second);
		t=new TextView(this); 
		t1=new TextView(this); 
		t2=new TextView(this); 

		edittext1=new EditText(this);
		edittext1 = (EditText) findViewById(R.id.editText1);

		edittext2=new EditText(this);
		edittext2 = (EditText) findViewById(R.id.editText2);

		//textSMS = (EditText) findViewById(R.id.editTextSMS);
		//edittext.getText();
		t=(TextView)findViewById(R.id.textView1); 
		t1=(TextView)findViewById(R.id.textView2);
		t2=(TextView)findViewById(R.id.textView3);
		t3=(TextView)findViewById(R.id.textView4);
		t4=(TextView)findViewById(R.id.textView5);
		
		
		t.setTextColor(Color.parseColor("#000000"));
		t1.setTextColor(Color.parseColor("#000000"));
		t2.setTextColor(Color.parseColor("#000000"));
		t3.setTextColor(Color.parseColor("#000000"));
		t4.setTextColor(Color.parseColor("#000000"));
		
		
		//GPS start
		setRequestedOrientation(ActivityInfo
				.SCREEN_ORIENTATION_PORTRAIT);

		//pb = (ProgressBar) findViewById(R.id.progressBar1);
		//pb.setVisibility(View.INVISIBLE);



		//btnGetLocation.setOnClickListener(this);

		locationMangaer = (LocationManager) 
				getSystemService(Context.LOCATION_SERVICE);


		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		List<Contact> contacts = db.getAllContacts();       
		count =0;
		for (Contact cn : contacts) {
			log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber()+"Count: "+count;
			String NameNumber = null;
			NameNumber = cn.getName()+" : "+cn.getPhoneNumber();
			String ll = ""+count;
			Log.d("Name: ", log);
			//textSMS.setText(NameNumber);
			if(count==0)
				t.setText(NameNumber);
			else if(count==1)
				t1.setText(NameNumber);
			else if(count==2)
				t2.setText(NameNumber);
			else if(count==3)
				t3.setText(NameNumber);
			
				
			count++;
			//db.deleteContact(new Contact("Nitu", "7879955750"));
		}
		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		
		
		
		
		
		
		
		

		//--------------------------------------------------------
		/*Log.d("Deleting: ", "Deleting all contacts..");
		List<Contact> contacts2 = db.getAllContacts();       

		for (Contact cn : contacts2) {

			//db.deleteContact(new Contact("Nitu", "7879955750"));
			db.deleteContact2(cn.getID());

		}*/

		//--------------------------------------------------------
		//GPS emd

		//t.setText(edittext.getText());
		//addKeyListener();
		/*
		Log.d("Reading: ", "Reading all contacts..");
		List<Contact> contacts = db.getAllContacts();       

		for (Contact cn : contacts) {
			log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber()+"Count: "+count;
			// Writing Contacts to log
			String ll = ""+count;
			Log.d("Name: ", log);
			//db.deleteContact(new Contact("Nitu", "7879955750"));
			count++;
		}
		*/
System.out.println("count = "+count);
		addListenerOnButton();
	}

	public void addListenerOnButton() {

		//String MySms = null;
		button = (Button) findViewById(R.id.button1);
		//button.setBackgroundResource(R.drawable.ic_launcher);
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				//count++;
				System.out.println(count);
				if(count < 4)
				{
					// Inserting Contacts
					name= edittext1.getText().toString();
					phone=edittext2.getText().toString();
					if(name.length()>0 && (phone.length()==10 || phone.length()==11))
					{

						Log.d("Insert: ", "Inserting ..");
						db.addContact(new Contact(name, phone));
						count++;
				
					}
		/*Log.d("Reading: ", "Reading all contacts..");
				List<Contact> contacts = db.getAllContacts();       

				for (Contact cn : contacts) {

					log = "Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
					// Writing Contacts to log
					Log.d("Name: ", log);

					//t.setText(edittext1.getText());
					//t.setText(edittext2.getText());
					if(count==1)
					t.setText(log);
					else if(count==2)
					t1.setText(log);
					else if(count==3)
					t2.setText(log);


				}*/

				}

				/*else
				{
					int j=0;
					Log.d("Reading: ", "Reading all contacts..");
					List<Contact> contacts = db.getAllContacts();       

					for (Contact cn : contacts) {
						log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
						// Writing Contacts to log
						Log.d("Name: ", log);
						if(j==0)
						t.setText(log);
						else if(j==1)
						t1.setText(log);
						else if(j==2)
						t2.setText(log);	
						j++;
					}

				}
				 */
			}

		});

		
		//-----------------------------------------------------------------
		/*btnupdate = (Button) findViewById(R.id.button4);

		btnupdate.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				// Updating Contacts

				name= edittext1.getText().toString();
				phone=edittext2.getText().toString();
				Log.d("Update: ", "Updating ..");
				db.updateContact(new Contact(name, phone));
				Log.d("Updated: ", "Updated ..");


				List<Contact> contacts2 = db.getAllContacts();       

				for (Contact cn : contacts2) {

					//db.deleteContact(new Contact("Nitu", "7879955750"));
					db.deleteContact2(cn.getID());

				}

			}

		});

		 */



		btnupdate = (Button) findViewById(R.id.button2);

		btnupdate.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				// Updating Contacts
				int idd;
				name= edittext1.getText().toString();
				phone=edittext2.getText().toString();
				Log.d("Update: ", "Updating ..");
				//db.updateContact(new Contact(name, phone));
				//Log.d("Updated: ", "Updated ..");				
				List<Contact> contacts2 = db.getAllContacts();       
				for (Contact cn : contacts2) {
					if(cn.getName().equals(name))
					{
						idd = cn.getID();
						db.updateContact2(idd, name, phone);
						break;
					}

				}

			}

		});

		btndisplay = (Button) findViewById(R.id.button3);

		btndisplay.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// Updating Contacts
				Log.d("Dislaying: ", "Displaying all contacts..");
				List<Contact> contactsd = db.getAllContacts();       
				count = 0;
				for (Contact cn : contactsd) {
					log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber()+"Count: "+count;
					String NameNumber = null;
					NameNumber = cn.getName()+" : "+cn.getPhoneNumber();
					String ll = ""+count;
					Log.d("Name: ", log);
					//textSMS.setText(NameNumber);
					if(count==0)
						t.setText(NameNumber);
					else if(count==1)
						t1.setText(NameNumber);
					else if(count==2)
						t2.setText(NameNumber);
					else if(count==3)
						t3.setText(NameNumber);
					
					count++;
					//db.deleteContact(new Contact("Nitu", "7879955750"));
				}

			}

		});
		
		
		
		btndelete = (Button) findViewById(R.id.button4);

		btndelete.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				// Deleting Contacts
				int idd;
				name= edittext1.getText().toString();
				phone=edittext2.getText().toString();
				Log.d("Delete: ", "Deleting ..");			
				List<Contact> contacts2 = db.getAllContacts();       
				for (Contact cn : contacts2) {
					if(cn.getName().equals(name))
					{
						idd = cn.getID();
						db.deleteContact2(idd);
						break;
					}

				}

			}

		});

		
		
		
		
		//-----------------------------------------------------------------
	}


	//Private class MylocationListener starts here
	private class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location loc) {
			String ad1 = null, ad2 = null;
			//textSMS.setText("");
			//pb.setVisibility(View.INVISIBLE);
			Toast.makeText(getBaseContext(),"Location changed : Lat: " +
					loc.getLatitude()+ " Lng: " + loc.getLongitude(),
					Toast.LENGTH_SHORT).show();
			String longitude = "Longitude: " +loc.getLongitude();  
			Log.v(TAG, longitude);
			String latitude = "Latitude: " +loc.getLatitude();
			Log.v(TAG, latitude);

			/*----------to get City-Name from coordinates ------------- */
			String cityName=null;              
			Geocoder gcd = new Geocoder(getBaseContext(), 
					Locale.getDefault());             
			List<Address>  addresses;  
			try {  
				addresses = gcd.getFromLocation(loc.getLatitude(), loc
						.getLongitude(), 1);  
				if (addresses.size() > 0)  
					//System.out.println(addresses.get(0).getLocality());  
					cityName=addresses.get(0).getLocality();
				ad1=addresses.get(0).getFeatureName();
				ad2=addresses.get(0).getAddressLine(0);

				//cityName=addresses.get(0).getLocality();  
			} catch (IOException e) {            
				e.printStackTrace();  
			} 

			String s = "I am here: "+cityName+" "+ad2;
			//textSMS.setText("nanital");
			MySms = s+"";
			//System.out.println(s);

			//SMS send  Start
			/*String phoneNo="7879955750";
			try {
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phoneNo, null, s, null, null);
				Toast.makeText(getApplicationContext(), "SMS Sent!",
						Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(),
						"SMS faild, please try again later!",
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}*/
			// SMS send Close


		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub         
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub         
		}

		@Override
		public void onStatusChanged(String provider, 
				int status, Bundle extras) {
			// TODO Auto-generated method stub         
		}
	}

	/*----------Method to create an AlertBox ------------- */
	protected void alertbox(String title, String mymessage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Your Device's GPS is Disable")
		.setCancelable(false)
		.setTitle("** Gps Status **")
		.setPositiveButton("Gps On",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// finish the current activity
				// AlertBoxAdvance.this.finish();
				Intent myIntent = new Intent(
						Settings.ACTION_SECURITY_SETTINGS);
				startActivity(myIntent);
				dialog.cancel();
			}
		})
		.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// cancel the dialog box
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}



	/*----Method to Check GPS is enable or disable ----- */
	private Boolean displayGpsStatus() {
		ContentResolver contentResolver = getBaseContext()
				.getContentResolver();
		boolean gpsStatus = Settings.Secure
				.isLocationProviderEnabled(contentResolver, 
						LocationManager.GPS_PROVIDER);
		if (gpsStatus) {
			return true;

		} else {
			return false;
		}
	}


}

