package com.example.derekvaldez.encodechatsms;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.content.Context;

import java.security.SecureRandom;

public class MessageView extends AppCompatActivity {
    final Context context = this;
    private TextView passedView = null;
    String passedSMS =null;
    Button btnDecode;
    private String decodeKey = "";
    public final static String SMSaddress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);

        decodeKey=null;
        passedSMS = getIntent().getStringExtra(RecieveSMSActivity.TEXTSMS);
        passedView = (TextView) findViewById(R.id.textSMSDisplay);
        passedView.setText(passedSMS);

        btnDecode = (Button) findViewById(R.id.btnDecode);
        btnDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get keyprompt.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.keyprompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);


                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        decodeKey += userInput.getText().toString();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }
    public void GoToInbox(View v ) {
        Intent intent = new Intent(MessageView.this, RecieveSMSActivity.class);
        startActivity(intent);
    }

    public void GoToCompose(View v ) {
        Intent intent = new Intent(MessageView.this, SendSMSActivity.class);
        String[] separated = passedSMS.split("\n");
        intent.putExtra(SMSaddress,separated[0]);
        startActivity(intent);
    }
    public void DecodeMessage(){
        if (decodeKey == "") {
            return;
        }
        String[] separated = passedSMS.split("\n");
        String DecodedMessage = "";
        char decodedChar;
        int[] keys;
        keys = new int[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(decodeKey.getBytes());

        for(int x=0; x<32; x++)
        {
            keys[x] = (secureRandom.nextInt()%9500);
        }
        String[] EncodedCharacters = separated[1].split(" ");


        int Thiskey;
        int ThisInteger;
        for (int index = 0; index< EncodedCharacters.length; index++ ){
            ThisInteger = Integer.parseInt(EncodedCharacters[index]);
            Thiskey = keys[index];
            decodedChar = (char)( ThisInteger - Thiskey);
            DecodedMessage += Character.toString(decodedChar);
        }
        passedView.setText(DecodedMessage);
    }
}
