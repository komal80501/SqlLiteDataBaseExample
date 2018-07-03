package com.example.student_006.sqllitedatabaseexample;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    DbHandler db;
    Button btnView,btnAdd;

    EditText editName,editId,editEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnView=(Button) findViewById(R.id.btnView);
        editName=(EditText) findViewById(R.id.name);
        editId=(EditText) findViewById(R.id.id);
        editEmail=(EditText) findViewById(R.id.email);

        db= new DbHandler(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Visitors visitor=new Visitors();
                visitor.setName(editName.getText().toString());
                visitor.setEmail(editEmail.getText().toString());
                boolean result = db.insert_visitors(visitor);
                Log.i("result :",String.valueOf(result));

                if(result==true)
                {
                    Toast.makeText(MainActivity.this,"data is inserted", LENGTH_SHORT).show();

                }
                else
                    {
                        Toast.makeText(MainActivity.this,"data is not inserted", Toast.LENGTH_LONG).show();

                    }

            }
        });


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor res= db.getAlldata();

               if(res.getCount()==0)
               {
                   showMessage("Error","No data is Found");
                   return;
               }

               StringBuffer buffer= new StringBuffer();
               while (res.moveToNext())
               {
                   buffer.append("ID "+res.getString(0)+"\n");
                   buffer.append("NAME "+res.getString(1)+"\n");
                   buffer.append("EMAIL "+res.getString(2)+"\n");
               }
                showMessage("Data",buffer.toString());
//                ArrayList<Visitors> visitorslist= db.getallvisitors();
//               Toast.makeText(getApplicationContext(),"plz create list array to display thos visitors", LENGTH_SHORT).show();
            }
        });
    }

    public void showMessage(String title ,String msg)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();


    }

}
