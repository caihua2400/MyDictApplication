package hua.mydictapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    MyDataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        ListView listView=(ListView)findViewById(R.id.show);
        Intent intent=getIntent();
        ArrayList<Map<String,String>> list= (ArrayList<Map<String, String>>) intent.getSerializableExtra("data");


        //ArrayList<Map<String,String>> list=data.getSerializable("data");
        //ArrayList<Map<String,String>> list=(ArrayList<Map<String,String>>)data.getSerializable("data");
        MyAdaptor adaptor=new MyAdaptor(DetailActivity.this,list);
        listView.setAdapter(adaptor);


    }
}
