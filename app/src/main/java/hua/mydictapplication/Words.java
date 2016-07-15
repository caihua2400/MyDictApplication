package hua.mydictapplication;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by caihua2300 on 15/07/2016.
 */
public final class Words {
    public static final String AUTHORITY="hua.mydictapplication.dictProvider";


    public static final class Word implements BaseColumns{
        public static final String _ID="_id";
        public static final String WORD="word";
        public static final String DETAIL="detail";
        public static final Uri DICT_CONTENT_URI = Uri.parse("content://" + AUTHORITY+"/words");
        public static final Uri WORD_CONTENT_URI = Uri.parse("content://" + AUTHORITY+"/word");
    }
}
