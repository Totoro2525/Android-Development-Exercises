package com.example.shiyan4_142;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.shiyan4_143.R;

import android.view.View.OnClickListener;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private List<CheckBox> favs;
    private EditText userName;
    private EditText userPass;
    private EditText tel;
    private TextView birthday;
    private RadioGroup sex;
    private Spinner dept,imag;
    private CheckBox book;
    private CheckBox sport;
    private CheckBox music;
    private CheckBox movie;
    private int[] images=new int[] {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l};
    
    private int imageSelectIndex;
    private int cyear,cmonth,cday;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		userName=(EditText)findViewById(R.id.name);
		userPass=(EditText)findViewById(R.id.password);
		tel=(EditText)findViewById(R.id.tel);
		sex=(RadioGroup)findViewById(R.id.sex);
		dept=(Spinner)findViewById(R.id.dept);
		imag=(Spinner)findViewById(R.id.imagspin);
		book=(CheckBox)findViewById(R.id.book);
		sport=(CheckBox)findViewById(R.id.sport);
		music=(CheckBox)findViewById(R.id.music);
		movie=(CheckBox)findViewById(R.id.movie);
		favs=new ArrayList<CheckBox>();
		favs.add(book);
		favs.add(sport);
		favs.add(music);
		favs.add(movie);
		Button btnok=(Button)findViewById(R.id.btnok);
		btnok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				myclick(v);
			}
		});
		birthday=(TextView)findViewById(R.id.birtherday);
		birthday.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 Calendar c=Calendar.getInstance();
				  cyear=c.get(Calendar.YEAR);
				  cmonth=c.get(Calendar.MONTH);
				  cday=c.get(Calendar.DAY_OF_MONTH);
				  DatePickerDialog datePickerDialog= new DatePickerDialog(MainActivity.this,new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
						  cyear=year;
						  cmonth=monthOfYear;
						  cday=dayOfMonth;
						  birthday.setText(cyear+"-"+(cmonth+1)+"-"+cday);
						
					}
				},cyear,cmonth,cday);
				  datePickerDialog.setMessage("��ѡ������");
				  datePickerDialog.show();
			}
		});
		List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();
		for(int i=0;i<images.length;i++)
		{
		     Map<String,Object> listItem=new HashMap<String,Object>();
		     listItem.put("image", images[i]);
		     listItems.add(listItem);
		}
		SimpleAdapter adapter=new SimpleAdapter(MainActivity.this,listItems,R.layout.list_itm,new String[] {"image"},new int[] {R.id.image});
		imag.setAdapter(adapter);
	}
	//��ȡ��ѡ��ťֵ
	public String getSex()
	{
	     RadioButton radioButton=(RadioButton)findViewById(sex.getCheckedRadioButtonId());
	     return radioButton.getText().toString();
	}
	private boolean check()
	{
	     if(userName.getText().toString().length()==0) {
	    	  userName.setError("�û�������Ϊ��");
	    	  Toast.makeText(this, "�û�������Ϊ��", Toast.LENGTH_SHORT).show();
	    	  return false;
	     }
	     if(userPass.getText().toString().length()==0)
	     {
	    	 userPass.setError("���벻��Ϊ��");
	    	 Toast.makeText(this, "���벻��Ϊ��", Toast.LENGTH_SHORT).show();
	    	  return false;
	     }
	     return true;
	}
	//��ȡ����
	public String getFavorite()
	{
		 String favo="";
		 for(CheckBox cb:favs)
		 {
			  if(cb.isChecked())
			  {
				  favo+=cb.getText().toString();
				  favo+=",";
			  }
		 }
		 if(!"".equals(favo))
		 {
			  favo=favo.substring(0,favo.length()-1);
			  
		 }
		 else
		 {
			  favo="��δѡ�񰮺�!";
		 }
		 return favo;
	}
	public void myclick(View view)
	{
		 if(check())
		 {
			 StringBuilder sb=new StringBuilder();
			 sb.append("������"+userName.getText().toString()+"\n");
			 sb.append("�Ա�"+getSex().toString()+"\n");
			 sb.append("�绰��"+tel.getText().toString()+"\n");
			 sb.append("���գ�"+birthday.getText().toString()+"\n");
			 sb.append("���ţ�"+dept.getSelectedItem()+"\n");
			 sb.append("���ã�"+getFavorite()+"\n");
			 Toast.makeText(this,sb.toString(), Toast.LENGTH_LONG).show();
			 Intent intent=new Intent();
			 intent.setClass(this,ResultActivity.class);
			 intent.putExtra("info", sb.toString());
			 intent.putExtra("imageIndex",imag.getSelectedItemPosition());
			 this.startActivity(intent);
		 }
	}
}
