package com.example.derekvaldez.encodechatsms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.telephony.SmsManager;
import android.widget.Toast;
import android.content.Intent;

import java.security.SecureRandom;


public class SendSMSActivity extends AppCompatActivity {

    Button btnSend;
    Button btnEncode;
    EditText smsMessageET;
    EditText toPhoneNo;
    String passedAddress = null;
    EncodeKey encodekey = EncodeKey.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        btnSend = (Button) findViewById(R.id.btnSend);
        btnEncode = (Button) findViewById(R.id.btnEncode);
        toPhoneNo = (EditText) findViewById(R.id.editTextPhoneNumber);
        smsMessageET = (EditText) findViewById(R.id.editSMSMessage);
        passedAddress = getIntent().getStringExtra(MessageView.SMSaddress);

        toPhoneNo.setText(passedAddress);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms();
            }
        });

        btnEncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EncodeMessage();
            }
        });
    }

    public void EncodeMessage(){
        String smsMessage = smsMessageET.getText().toString();
        try {
            int[] keys;
            keys = new int[32];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.setSeed(encodekey.getKey().getBytes());
            String EncodedMessage = "";

            for(int x=0; x<32; x++)
            {
                keys[x] = (secureRandom.nextInt()%9500);
            }

            int Thiskey;
            for(int index = 0; index<smsMessage.length(); index++){
                Thiskey = keys[index];
                EncodedMessage += smsMessage.charAt(index)+Thiskey + " ";
            }

            Toast.makeText(this, "SMS encoded", Toast.LENGTH_LONG).show();
            sendSms(EncodedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendSms() {
        String toPhone = toPhoneNo.getText().toString();
        String smsMessage = smsMessageET.getText().toString();

        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhone, null, smsMessage, null, null);

            Toast.makeText(this, "SMS Sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendSms(String s) {
        String toPhone = toPhoneNo.getText().toString();


        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(toPhone, null, s, null, null);

            Toast.makeText(this, "SMS Sent", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GoToInbox(View v){
        Intent intent = new Intent(SendSMSActivity.this, RecieveSMSActivity.class);
        startActivity(intent);

    }
}
