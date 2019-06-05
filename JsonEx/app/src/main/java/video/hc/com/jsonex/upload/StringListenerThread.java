package video.hc.com.jsonex.upload;

import android.os.Handler;
import android.os.Message;

import video.hc.com.jsonex.utils.DataUtils;

/**
 * Created by ly on 2019/6/4.
 */

public class StringListenerThread implements Runnable {
    String sbString;
    Handler handler;
    public static String beifen = null;

    public StringListenerThread(StringBuffer sbString, Handler handler) {
        this.sbString = sbString.toString();
        this.handler = handler;
    }

    @Override
    public void run() {
        while(true){
            if (!sbString.equals(beifen) ){
                Message msg = new Message();
                msg.what = DataUtils.CONTENT_UPDATA;
                handler.sendMessage(msg);
                beifen = sbString;
            }
        }
    }

    public void updateString(String s) {
        this.sbString = s;
    }
}
