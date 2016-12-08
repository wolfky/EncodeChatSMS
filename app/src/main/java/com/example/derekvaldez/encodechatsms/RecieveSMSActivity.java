package com.example.derekvaldez.encodechatsms;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.content.ContentResolver;
import android.database.Cursor;
import android.content.Intent;
import java.text.SimpleDateFormat;
import java.util.Date;



public class RecieveSMSActivity extends AppCompatActivity implements OnItemClickListener{

    private static RecieveSMSActivity inst;
    ArrayList<String> smsMessageList = new ArrayList<String>();
    ListView smsListView;
    ArrayAdapter arrayAdapter;
    public final static String TEXTSMS=null;

    public static RecieveSMSActivity instance() {
        return inst;
    }

    protected void onStart() {
        super.onStart();
        inst = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_sms);

        smsListView = (ListView) findViewById(R.id.SMSList);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, smsMessageList);
        smsListView.setAdapter(arrayAdapter);
        smsListView.setOnItemClickListener(this);

        refreshSMSInbox();
    }

    public void refreshSMSInbox(){
        ContentResolver contentResolver = getContentResolver();
        Cursor smsInboxCursor =  contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        int IndexBody =  smsInboxCursor.getColumnIndex("body");
        int IndexAddress =  smsInboxCursor.getColumnIndex("address");
        int timeMillis =  smsInboxCursor.getColumnIndex("date");
        Date date = new Date(timeMillis);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
        String dateText = format.format(date);

        if (IndexBody <  0 || !smsInboxCursor.moveToFirst()) return;
        arrayAdapter.clear();
        do {
            String str = smsInboxCursor.getString(IndexAddress) + " \n "
                    + "at " + smsInboxCursor.getString(IndexBody) + dateText + "\n";
            arrayAdapter.add(str);
        }while(smsInboxCursor.moveToNext());

    }

    public void updateList(final String smsMessage){
        arrayAdapter.insert(smsMessage, 0);
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        try{
            String[] smsMessages = smsMessageList.get(i).split("\n");
            String address = smsMessages[0];
            String smsMessage = "";

            for (int j = 1; j < smsMessages.length; j++) {
                smsMessage += smsMessages[j];
            }

            String smsMessageStr = address + "\n";
            smsMessageStr += smsMessage;
            Intent intent = new Intent(RecieveSMSActivity.this, MessageView.class);
            intent.putExtra(TEXTSMS,smsMessageStr);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void GoToCompose(View v) {
        Intent intent = new Intent(RecieveSMSActivity.this, SendSMSActivity.class);
        startActivity(intent);
    }
}
