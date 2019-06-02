package com.example.totoro.shiyan3_142;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.totoro.entity.Book;

public class MainActivity2 extends AppCompatActivity {
    Button button;
    EditText nameText,priceText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button = (Button)findViewById(R.id.button);
        nameText=(EditText)findViewById(R.id.nameText);
        priceText=(EditText)findViewById(R.id.priceText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String book_name=nameText.getText().toString().trim();
                double book_price=Double.parseDouble(priceText.getText().toString().trim());
                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("book",new Book(book_name,book_price));
                intent.putExtras(bundle);
                int resultCode=1;
                setResult(resultCode,intent);
                finish();
            }
        });
    }
}
