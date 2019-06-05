package video.hc.com.jsonex.observer;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import video.hc.com.jsonex.utils.DataUtils;

/**
 * Created by ly on 2019/6/5.
 */

public class MyObserver implements Observer {

    private Handler handler;

    public MyObserver(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d("lylog_update","arg = "+arg.toString());
        Message msg = new Message();
        msg.what = DataUtils.CONTENT_UPDATA;
        handler.sendMessage(msg);
    }
}
