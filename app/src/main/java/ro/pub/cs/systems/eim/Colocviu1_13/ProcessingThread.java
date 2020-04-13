package ro.pub.cs.systems.eim.Colocviu1_13;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;

import java.util.Date;

public class ProcessingThread extends Thread {

    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.Colocviu1_13.text",
    };

    private Context context = null;
    private String text;


    public ProcessingThread(Context context, String text) {
        this.context = context;
        this.text = text;
    }

    @Override
    public void run() {
        super.run();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sendMessage();
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("broadcast_text");
        intent.putExtra("broadcast", new Date(System.currentTimeMillis()) + " " + text);
        context.sendBroadcast(intent);
    }
}
