package tw.com.ei.mythreadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    private MyThread myThread,myThread2;
    Message msg;
    UIHandle handle;
    private MyTask myTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.tv);
        handle=new UIHandle();
    }

    public void Btn1(View v){
        myThread=new MyThread("A");
        //myThread2=new MyThread("B");
        myThread.start();
        //myThread2.start();

    }

    public void Btn3(View v){
        myTask.cancel();

    }
    public void Btn2(View v){
        myTask=new MyTask();
        timer.schedule(myTask,1000,500);

    }
    Timer timer=new Timer();
    private class MyTask extends TimerTask{
        @Override
        public void run() {
            for(int i=0;i<20;i++){
                Log.i("simon",i+" = "+i);
            }
        }
    }
    private class UIHandle extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String key=msg.getData().getString("key");
            tv.setText(key);
        }
    }

    private class MyThread extends Thread{
        private String name;
        MyThread(String name){
            this.name=name;
        }
        @Override
        public void run() {
            for(int i=0;i<20;i++){
                Log.i("simon","i = "+i);
                Bundle bundle=new Bundle();
                bundle.putString("key","i = "+i);
                msg=new Message();
                msg.setData(bundle);
                handle.sendMessage(msg);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {

                }
            }


        }
    }
}
