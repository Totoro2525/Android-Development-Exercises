package com.example.shiyan5_142;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private EditText accountEdit;
	private EditText passwordEdit;
	private Button login;
	private CheckBox rememberPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		pref =PreferenceManager.getDefaultSharedPreferences(this);
		accountEdit =(EditText) findViewById(R.id.account);
		passwordEdit =(EditText) findViewById(R.id.password);
		rememberPass =(CheckBox) findViewById(R.id.remember_pass);
		login =(Button) findViewById(R.id.login);
		boolean isRemember=pref.getBoolean("rember_password", false);
		if(isRemember) {
			String account =pref.getString("account", "");
			String password =pref.getString("password", "");
			accountEdit.setText(account);
			passwordEdit.setText(password);
			rememberPass.setChecked(true);
		}
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				String account=accountEdit.getText().toString();
				String password=passwordEdit.getText().toString();
				if(account.equals("admin")&&password.equals("123456")) {
					editor=pref.edit();
					if(rememberPass.isChecked()){
					editor.putBoolean("remember_password", true);
					editor.putString("account", account);
					editor.putString("password", password);
					}else {
						editor.clear();
					}
					editor.apply();
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);
					startActivity(intent);
					finish();
				}else {
					Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
				}
			}
		});
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
