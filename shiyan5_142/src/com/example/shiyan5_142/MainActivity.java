package com.example.shiyan5_142;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView itemList;
	private ImageView imageView;
	private String path;
	static String databasePath;
	static String databaseName = "myrelation.db";
	private static String TABLE_NAME ="relation";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			path=Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"relation";
		else
			path="relation";
		databasePath=path+File.separator+databaseName;
		
		if(!new File(path).exists()) {
			new File(path).mkdir();
		}
		if(!(new File(databasePath)).exists()) {
			try {
				InputStream is=getResources().openRawResource(R.raw.myrelation);
				
				FileOutputStream fos=new FileOutputStream(databasePath);
				
				byte[] buffer=new byte[is.available()];
				int count=0;
				System.out.println(5);
				while((count=is.read(buffer))>0) {
					fos.write(buffer,0,count);
				System.out.println(count);
				}
				fos.close();
				is.close();
			}catch(FileNotFoundException e) {
				e.printStackTrace();
				SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(databasePath, null);
				db.execSQL("create table "+TABLE_NAME
						+"(_id integer primary key autioncrement,name text,groupName text)");
			}catch(IOException e) {
			e.printStackTrace();
			}
		}
		imageView=(ImageView) findViewById(R.id.imageView1);
		imageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				add(v);
			}
		});
		
		itemList =(ListView) findViewById(R.id.listView1);
		itemList.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long id) {
				// TODO 自动生成的方法存根
				final long temp1=id;
				AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
				builder.setMessage(R.string.delete_message).setPositiveButton(R.string.confirm, new OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO 自动生成的方法存根
						SQLiteDatabase db=SQLiteDatabase.openDatabase(databasePath, null,
								SQLiteDatabase.OPEN_READWRITE);
						
						if(db!=null) {
							db.delete(TABLE_NAME, "_id=?", new String[] {String.valueOf((int) temp1)});
							
							Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null);
							String[] from= {"name","tel","groupName"};
							int[] to={R.id.name,R.id.tel,R.id.group};
							SimpleCursorAdapter scadapter=new SimpleCursorAdapter(MainActivity.this,
									R.layout.relationlist, cursor, from, to);
							itemList.setAdapter(scadapter);
							db.close();
						}
					}

				}).setNegativeButton(R.string.cancel,new OnClickListener() {
					public void onClick(DialogInterface dialog,int which) {
					}
				});
				AlertDialog alertDialog=builder.create();
				alertDialog.show();
			}
		});
		getRelationFromDB();
	}

	private void getRelationFromDB() {
		// TODO 自动生成的方法存根
		SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(databasePath, null);
		if(db !=null) {
			Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null, null);
			String[] from= {"name","tel","groupName"};
			int[] to={R.id.name,R.id.tel,R.id.group};
			SimpleCursorAdapter scadapter=new SimpleCursorAdapter(MainActivity.this,
					R.layout.relationlist, cursor, from, to);
			itemList.setAdapter(scadapter);
			db.close();
		}
	}

	 private void add(View view) {
		// TODO 自动生成的方法存根
		 Intent intent=new Intent(MainActivity.this,AddRelationActivity.class);
		 startActivityForResult(intent, 0x111);
	}
	 @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO 自动生成的方法存根
		 if(requestCode==0x111&&resultCode==0x111) {
			 getRelationFromDB();
		 }
		super.onActivityResult(requestCode, resultCode, data);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
