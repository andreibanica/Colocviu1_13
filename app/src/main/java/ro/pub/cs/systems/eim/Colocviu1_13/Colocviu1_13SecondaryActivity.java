package ro.pub.cs.systems.eim.Colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Colocviu1_13SecondaryActivity extends AppCompatActivity {

    EditText second;
    Button register, cancel;

    final public static String TEXT = "text";

    String show;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register:
                    Intent intent = new Intent(getApplicationContext(), Colocviu1_13MainActivity.class);
                    intent.putExtra("pushed", "REGISTER");
                    setResult(RESULT_OK, intent);
                    break;
                case R.id.cancel:
                    intent = new Intent(getApplicationContext(), Colocviu1_13MainActivity.class);
                    intent.putExtra("pushed", "CANCEL");
                    setResult(RESULT_CANCELED, intent);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_secondary2);

        second = findViewById(R.id.second);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);

        register.setOnClickListener(buttonClickListener);
        cancel.setOnClickListener(buttonClickListener);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(TEXT)) {
            show = intent.getStringExtra(TEXT);
            second.setText(show);
        }
    }
}
