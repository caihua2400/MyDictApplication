package hua.mydictapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    MyDataBaseHelper dataBaseHelper;
    Button insert;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dataBaseHelper=new MyDataBaseHelper(this,"myDict.db3",2);
        insert=(Button) findViewById(R.id.insert);
        search=(Button) findViewById(R.id.search);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word=((EditText)findViewById(R.id.word)).getText().toString();
                String detail=((EditText)findViewById(R.id.detail)).getText().toString();
                SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
                insertData(db,word,detail);
                Toast.makeText(MainActivity.this,"insert success",Toast.LENGTH_LONG).show();
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchword=((EditText)findViewById(R.id.key)).getText().toString();
                Cursor cursor=dataBaseHelper.getReadableDatabase().query("dict",null," word=?",new String[]{searchword},null,null,null);
                Bundle data=new Bundle();
                data.putSerializable("data",convertCursorToList(cursor));
                Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });
    }
    protected ArrayList<Map<String,String>> convertCursorToList(Cursor cursor){
        ArrayList<Map<String,String>> resultSet=new ArrayList<Map<String,String>>();
        while(cursor.moveToNext()){
            String word=cursor.getString(1);
            String detail=cursor.getString(2);
            Map<String,String> result=new HashMap<String, String>();
            result.put("word",word);
            result.put("detail",detail);
            resultSet.add(result);


        }
        cursor.close();
        return  resultSet;
    }
    private void insertData(SQLiteDatabase db,String word,String detail){
        ContentValues contentValues=new ContentValues();
        contentValues.put("word",word);
        contentValues.put("detail",detail);
        db.insert("dict",null,contentValues);



    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        if(dataBaseHelper!=null){
            dataBaseHelper.close();
        }

    }
}
