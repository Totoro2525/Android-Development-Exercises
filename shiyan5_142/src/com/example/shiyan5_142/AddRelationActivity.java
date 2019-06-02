package com.example.shiyan5_142;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class AddRelationActivity extends Activity{
	private EditText addName,addTel;
	private Spinner addGroup;
	final static String path=Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"relation";
	final static String datebaseName="myrelation.db";
	final static String databasePath=path+File.separator+datebaseName;
	private static String TABLE_NAME="relation";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.additem);
		addName=(EditText) findViewById(R.id.addName);
		addTel=(EditText) findViewById(R.id.addTel);
		addGroup=(Spinner) findViewById(R.id.spinner1);
	}
	public void save(View view) {
		final ContentValues values =new ContentValues();
		values.put("name", addName.getText().toString());
		values.put("tel", addTel.getText().toString());
		values.put("groupName", addGroup.getSelectedItem().toString());
		final AlertDialog.Builder builder=new AlertDialog.Builder(AddRelationActivity.this);
		builder.setMessage(R.string.save_message).setPositiveButton(R.string.confirm, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO 自动生成的方法存根
				SQLiteDatabase db =SQLiteDatabase.openDatabase(databasePath,null,SQLiteDatabase.OPEN_READWRITE);
				db.insert(TABLE_NAME, null, values);
				db.close();
				
				Intent intent=getIntent();
				setResult(0x1111,intent);
				AddRelationActivity.this.finish();
			}
		});
		AlertDialog alertDialog=builder.create();
		alertDialog.show();
	}
	
}
