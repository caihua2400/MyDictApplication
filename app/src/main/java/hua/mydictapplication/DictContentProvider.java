package hua.mydictapplication;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.UserDictionary;
import android.support.annotation.Nullable;

/**
 * Created by caihua2300 on 15/07/2016.
 */
public class DictContentProvider extends ContentProvider {
    private static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
    private static final int WORDS=1;
    private static final int WORD=2;
    private MyDataBaseHelper dataBaseHelper;
    static{
        matcher.addURI(Words.AUTHORITY,"words",WORDS);
        matcher.addURI(Words.AUTHORITY,"word/#",WORD);
    }
    @Override
    public boolean onCreate() {
        dataBaseHelper=new MyDataBaseHelper(this.getContext(),"myDict.db3",2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
        switch(matcher.match(uri)){
            case WORDS:
                return db.query("dict",projection,selection,selectionArgs,null,null,sortOrder);
            case WORD:
                long id=ContentUris.parseId(uri);
                String whereClause=Words.Word._ID+"="+id;
                if(selection!=null&&!selection.equals("")){
                    whereClause=whereClause+" and "+selection;

                }
                return db.query("dict",projection,whereClause,selectionArgs,null,null,sortOrder);
            default:
                throw new IllegalArgumentException("Unknown Uri"+uri);

        }

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (matcher.match(uri)){
            case WORDS:
                return "hua.mydictapplication.dir";
            case WORD:
                return "hua.mydictapplication.item";
            default:
                throw new IllegalArgumentException("Unknown Uri");
        }


    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
        switch(matcher.match(uri)){
            case WORDS:
                long rowid;
                rowid=db.insert("dict",Words.Word._ID,values);
                if(rowid>0){
                    Uri wordUri=ContentUris.withAppendedId(uri,rowid);
                    getContext().getContentResolver().notifyChange(wordUri,null);
                    return wordUri;
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri"+uri);
        }

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
        int num=0;
        switch(matcher.match(uri)){
            case WORDS:
            num=db.delete("dict",selection,selectionArgs);
            break;
            case WORD:
                long id=ContentUris.parseId(uri);
                String whereClause=Words.Word._ID+"="+id;
                if(selection!=null&&!selection.equals("")){
                    whereClause=whereClause+" and "+selection;

                }
                db.delete("dict",whereClause,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknow Uri"+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return num;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db=dataBaseHelper.getWritableDatabase();
        int num=0;
        switch (matcher.match(uri)){
            case WORDS:
                num=db.update("dict",values,selection,selectionArgs);
                break;
            case WORD:
                long id=ContentUris.parseId(uri);
                String whereClause=Words.Word._ID+"="+id;
                if(selection!=null&&!selection.equals("")){
                    whereClause=whereClause+" and "+selection;

                }
                num=db.update("dict",values,whereClause,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri"+uri);


        }
        getContext().getContentResolver().notifyChange(uri,null);
        return num;
    }
}
