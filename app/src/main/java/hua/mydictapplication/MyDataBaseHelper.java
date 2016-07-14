package hua.mydictapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by caihua2300 on 14/07/2016.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {
    final String TABLE_NAME="dict";
    final String WORD="word";
    final String DETAIL="detail";
    final String CREATE_TABLE_SQL="create table " +
            TABLE_NAME+"(_id integer primary key autoincrement, " +
            WORD+" TEXT  , " +
            DETAIL+"TEXT )";
    public MyDataBaseHelper(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
