package com.uladzislau.tylkovich.secretmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {


    //setup variables
    EditText txtIn;
    EditText txtKey;
    EditText txtOut;
    SeekBar sb;
    Button button;


    public String encode(String message, int k) {
        String out ="";
        char key = (char) k;
        for (int x=0;x<message.length();x++){
            char in = message.charAt(x);
            if (in>='A' && in<='Z')
            {
                in +=key;
                if(in>'Z')
                    in-=26;
                if(in<'A')
                    in+=26;
            }
            if (in>='a' && in<='z')
            {
                in +=key;
                if(in>'z')
                    in-=26;
                if(in<'a')
                    in+=26;
            }


            if (in>='0' && in<='9')
            {
                in +=(k%10);
                if(in>'9')
                    in -=10;
                if (in<'0')
                    in +=10;
            }
            out +=in;

        }
        return out;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtIn = (EditText) findViewById(R.id.txtIn);
        txtKey = (EditText) findViewById(R.id.txtKey);
        txtOut = (EditText) findViewById(R.id.txtOut);
        sb = (SeekBar) findViewById(R.id.sb);
button = (Button) findViewById(R.id.button);

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        int key = Integer.parseInt(txtKey.getText().toString());
        int i =key +13;
        String message = txtIn.getText().toString();
        String out = encode(message,key);
        txtOut.setText(out);
        sb.setProgress(i);
    }
});

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int key = i-13;
                String message = txtIn.getText().toString();
                String out = encode(message,key);
                txtOut.setText(out);
                txtKey.setText("" +key);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
