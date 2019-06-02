package com.example.totoro.shiyan3_142;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.totoro.entity.Book;

public class MainActivity extends AppCompatActivity {
    Button bt1;
    int requestCode=2;
    Book book;
    TextView bookinfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookinfor = (TextView)findViewById(R.id.bookinfor);
        bt1= (Button)findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivityForResult(intent,requestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==2 && resultCode==1){
            book=(Book)data.getSerializableExtra("book");
            bookinfor.setText(bookinfor.getText()+"\n"+"书籍名称："+
                    book.getBook_name()+",   书籍价格："+book.getBook_price());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
