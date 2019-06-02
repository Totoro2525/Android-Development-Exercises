package com.example.shiyan2guessnumber_142;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView infor;
	private EditText input;
	private Button button;
			int target=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		infor=(TextView)findViewById(R.id.infor);
		input=(EditText)findViewById(R.id.input);
		button=(Button)findViewById(R.id.button);
		target=(int)(Math.random()*10+1);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				if(button.getText().toString().equals("OK")) {
				int num=Integer.parseInt(input.getText().toString());
				if(num>target) {
					infor.setText("�´��ˣ����������� ��");
				}else if(num==target) {
					infor.setText("��ϲ�㣬�¶��ˣ�");
					button.setText("����һ��");
				}else 
					infor.setText("��С�ˣ����������룡");
			}else if(button.getText().toString().equals("����һ��")) {
				target=(int)(Math.random()*10+1);
				infor.setText("������1~10�������:");
				input.setText("");
				button.setText("OK");
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
