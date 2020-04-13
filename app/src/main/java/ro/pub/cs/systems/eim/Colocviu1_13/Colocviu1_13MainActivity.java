package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    Button north, south, east, west, navigate;
    EditText editText, tot;
    int total = 0;
    final public static String TOTAL = "total";

    private IntentFilter intentFilter = new IntentFilter();

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String text = editText.getText().toString();

            switch (v.getId()) {
                case R.id.north:
                    text = text + "north, ";
                    break;
                case R.id.south:
                    text = text + "south, ";
                    break;
                case R.id.east:
                    text = text + "east, ";
                    break;
                case R.id.west:
                    text = text + "west, ";
                    break;
                case R.id.navigate_to_secondary_activity_button:
                    Intent intent = new Intent(getApplicationContext(), Colocviu1_13SecondaryActivity.class);
                    intent.putExtra("text", editText.getText().toString());
                    startActivityForResult(intent, 10);
                    break;
            }

            editText.setText(text);
            total++;
            tot.setText(String.valueOf(total));

            if (total == 4) {
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("text", text);
                getApplicationContext().startService(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        editText = (EditText)findViewById(R.id.text);
        tot = (EditText)findViewById(R.id.tot);
        navigate = findViewById(R.id.navigate_to_secondary_activity_button);

        north = (Button)findViewById(R.id.north);
        south = (Button)findViewById(R.id.south);
        east = (Button)findViewById(R.id.east);
        west = (Button)findViewById(R.id.west);

        north.setOnClickListener(buttonClickListener);
        south.setOnClickListener(buttonClickListener);
        east.setOnClickListener(buttonClickListener);
        west.setOnClickListener(buttonClickListener);
        navigate.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(TOTAL)) {
                total = savedInstanceState.getInt(TOTAL);
            } else {
                total = 0;
            }
        } else {
            total = 0;
        }

        intentFilter.addAction("broadcast_text");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(TOTAL, total);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(TOTAL)) {
            total = savedInstanceState.getInt(TOTAL);
        } else {
            total = 0;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 10) {
            Toast.makeText(this, "button pushed was " + intent.getExtras().getString("pushed"), Toast.LENGTH_LONG).show();
            editText.setText("");
            total = 0;
            tot.setText("0");
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        super.onDestroy();
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();

    private static class MessageBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("BROADCAST", intent.getStringExtra("broadcast"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

}
