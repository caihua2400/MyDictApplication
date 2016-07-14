package hua.mydictapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by caihua2300 on 14/07/2016.
 */
public class MyAdaptor extends ArrayAdapter {
    public MyAdaptor(Context context, List objects) {
        super(context, 0, objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Map<String,String> item=(Map<String, String>) getItem(position);

        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView wordshow=(TextView) convertView.findViewById(R.id.wordshow);
        TextView detailshow=(TextView) convertView.findViewById(R.id.detailshow);
        wordshow.setText(item.get("word"));
        detailshow.setText(item.get("detail"));



        return convertView;
    }
}
