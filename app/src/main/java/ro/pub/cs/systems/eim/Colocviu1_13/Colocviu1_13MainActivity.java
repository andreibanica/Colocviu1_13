package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    Button north, south, east, west;
    EditText editText, tot;
    int total = 0;
    final public static String TOTAL = "total";

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
            }

            editText.setText(text);
            total++;
            tot.setText(String.valueOf(total));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        editText = (EditText)findViewById(R.id.text);
        tot = (EditText)findViewById(R.id.tot);

        north = (Button)findViewById(R.id.north);
        south = (Button)findViewById(R.id.south);
        east = (Button)findViewById(R.id.east);
        west = (Button)findViewById(R.id.west);

        north.setOnClickListener(buttonClickListener);
        south.setOnClickListener(buttonClickListener);
        east.setOnClickListener(buttonClickListener);
        west.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(TOTAL)) {
                total = savedInstanceState.getInt(TOTAL);
            } else {
                total = 0;
            }
        } else {
            total = 0;
        }
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

}
