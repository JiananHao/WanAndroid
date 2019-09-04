package com.example.wanandroid.ui.fragment.five.child;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseAcitivty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchActivity extends BaseAcitivty {

    EditText editText;
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    ContentResolver contentResolver;
    List<String> personList;
    List<String> settingList;
    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        editText = findViewById(R.id.et_search);
        recyclerView = findViewById(R.id.rv_search);
    }

    @Override
    protected void initData() {
        personList = new ArrayList<>();
        settingList = new ArrayList<>();
        contentResolver = getContentResolver();
//        Cursor cursor = contentResolver.query(Uri.parse("content://com.android.contacts/raw_contacts"), null, null, null, null);
//        while (cursor.moveToNext()){
//            String name = cursor.getString(cursor.getColumnIndex("display_name"));
//            Log.d("hao","========="+ name);
//            personList.add(name);
//        }
//        Uri.parse("content://com.android.settings/settings/indexables_raw")
        Cursor cursor1 = contentResolver.query(Settings.Global.CONTENT_URI, null, null, null, null);

        if(cursor1!=null&&cursor1.moveToFirst()){
            do{
                StringBuilder stringBuilder = new StringBuilder();
                for (String name : cursor1.getColumnNames()){
                    stringBuilder.append(name+"/");
                }
                Log.d("hao","name========="+ stringBuilder);
//                String title = cursor1.getString(cursor1.getColumnIndex("title"));
//                Log.d("hao","title========="+ title);
//                personList.add(title);
            }while(cursor1.moveToNext());

        }
        searchAdapter = new SearchAdapter(R.layout.rv_search_item,personList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        recyclerView.setAdapter(searchAdapter);
    }

    public void initPerson(){

    }

}
