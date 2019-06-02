package com.example.shiyan4_142;

import com.example.shiyan4_143.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends Activity {

	private TextView result;
	private ImageView image;
	private int[] images=new int[] {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.result_activity);
		result=(TextView)findViewById(R.id.result);
		image=(ImageView)findViewById(R.id.image);
		result.setText("从前一个页面传递过来的内容\n\n"+this.getIntent().getStringExtra("info"));
		image.setImageResource(images[this.getIntent().getIntExtra("imageIndex", 0)]);
	}
	public void newClick(View view) {
		finish();
	}
}