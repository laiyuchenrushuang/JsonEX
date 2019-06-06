package video.hc.com.jsonex;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import video.hc.com.jsonex.enity.Book;
import video.hc.com.jsonex.enity.Weather;
import video.hc.com.jsonex.observer.MyObservable;
import video.hc.com.jsonex.observer.MyObserver;
import video.hc.com.jsonex.upload.StringListenerThread;
import video.hc.com.jsonex.utils.DataUtils;
import video.hc.com.jsonex.utils.JsonUtils;

public class MainActivity extends AppCompatActivity implements PropertyChangeListener {
    @BindView(R.id.jsonExport)
    Button bt_json;
    @BindView(R.id.textdata)
    TextView tv_content;
    @BindView(R.id.bt_up)
    Button bt_jsonUp;
    @BindView(R.id.title)
    TextView tx_title;
    UpdataTask updataTask;
    public StringBuffer sbString = new StringBuffer();
    private StringListenerThread stringLisener;
    private MyObserver myObserver;
    private MyObservable myObservable;
    List<Weather> weatherList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        stringLisener = new StringListenerThread(sbString, mHandler);
        new Thread(stringLisener).start();
        updataTask = new UpdataTask(mHandler);
        new Thread(updataTask).start();
        initChangeStringListener();

        initObserver();
        Log.d("lylog", "onCreate");
    }

    private void initChangeStringListener() {
        PropertyChangeSupport changes = new PropertyChangeSupport(this);
        changes.addPropertyChangeListener(this);
        changes.firePropertyChange("tx_string", null, this.sbString.toString());
    }

    private void initObserver() {
        myObserver = new MyObserver(mHandler);
        myObservable = MyObservable.getIncetance();
        myObservable.addObserver(myObserver);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DataUtils.SUCESS:
                    tv_content.setText(updataTask.getData());
                    break;
                case DataUtils.CONTENT_UPDATA:
                    Log.d("lylog", "ssss");
                    tv_content.setText(sbString.toString());
                    break;
                case DataUtils.ABILITY_UPING:
                    deleteOringin();
                    break;
                case DataUtils.ABILITY_UPING_GO:
                    goNewJsonEx();
                    break;
            }

        }
    };

    private void goNewJsonEx() {
        updataTask.setNewDataFlag(false);
        new Thread(updataTask).start();

    }

    private void deleteOringin() {

        sbString.delete(0, sbString.length());
        tv_content.setText(sbString.toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                doProgress();
            }
        }).start();

        tv_content.setText("倒计时......");
    }

    int time = 3;

    private void doProgress() {
        if (time == 0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_content.setTextSize(10);
                    tv_content.setTextColor(Color.BLUE);
                    bt_json.setVisibility(View.GONE);
                    bt_jsonUp.setVisibility(View.VISIBLE);
                    tx_title.setTextColor(Color.BLUE);
                    Message msg = new Message();
                    msg.what = DataUtils.ABILITY_UPING_GO;
                    mHandler.sendMessage(msg);
                }
            });

            return;
        }
        sleep(1000);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_content.setTextSize(100);
                tv_content.setText(time-- + "");
            }
        });
        doProgress();
    }

    public void doJson(View view) throws JSONException {
        bt_json.setEnabled(false);
        sbString.append("第一种解析开始......\n");

        //updateText(sbString);
        observerNotify(sbString.toString());
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(500);
                Book bookInfo = JsonUtils.getIncetence().beginBookGson(updataTask.getData(), Book.class);
                sbString.append("id = " + bookInfo.getId() + "\n");
                sbString.append("language = " + bookInfo.getLanguage() + "\n");
                sbString.append("edition = " + bookInfo.getEdition() + "\n");
                sbString.append("author = " + bookInfo.getAuthor() + "\n");

                //updateText(sbString);
                observerNotify(sbString.toString());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(2000);
                sbString.append("另一种解析开始......\n");

                //updateText(sbString);
                observerNotify(sbString.toString());
                sleep(500);
                JsonObject json = JsonUtils.getIncetence().beginJson(updataTask.getData());

                JsonElement id = json.get("id");
                JsonElement language = json.get("language");
                JsonElement edition = json.get("edition");
                JsonElement author = json.get("author");

                sbString.append("id = " + id.toString() + "\n");
                sbString.append("language = " + language.toString() + "\n");
                sbString.append("edition = " + edition.toString() + "\n");
                sbString.append("author = " + author.toString() + "\n");

                //updateText(sbString);
                observerNotify(sbString.toString());
                sleep(2000);
                Message msg = new Message();
                msg.what = DataUtils.ABILITY_UPING;
                mHandler.sendMessage(msg);
            }
        }).start();

    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 和 updateText 等效 看看效果，我觉得观察者模式好些，装逼一下
     */

    private void observerNotify(String source) {
        myObservable.notifyObserver(source);
    }

    private void updateText(StringBuffer sbString) {

        stringLisener.updateString(sbString.toString());
    }

    int num = 0;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //Log.d("lylog", "propertyChange " + num++);
    }

    public void doJsonUp(View view) {
        bt_jsonUp.setEnabled(false);
        notifys("解析一个json带二维数组", 1000);
        notifys("接下来见证奇迹的时刻", 0);
        notifys("复杂的解析其实是上面两种解析的结合", 0);
        notifys("--------------------------------", 0);


        JsonObject json = JsonUtils.getIncetence().beginJson(updataTask.getData());
        notifys("获取数据源成功", 0);
        notifys("获取HeWeather6里面的数据", 1000);
        JsonArray array = json.getAsJsonArray("HeWeather6");

        notifys("取出HeWeather6里面的数据数组下标为0的数据", 1000);
        JsonObject jsonArray_0 = (JsonObject) array.get(0);

        notifys("取出HeWeather6里面的数据数组下标为0的数据中的daily_forecast项", 1000);
        JsonArray jsonWeatherArray = jsonArray_0.getAsJsonArray("daily_forecast");

        weatherList = new ArrayList<>();
        for (int i = 0; i < jsonWeatherArray.size(); i++) {
            weatherList.add(JsonUtils.getIncetence().beginWeatherGson((JsonObject) jsonWeatherArray.get(i), Weather.class));
        }

        notifys("把daily_forecast项的数据储存到list的集合中方便使用", 0);
        notifys("解析数据开始......ING", 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Weather weather : weatherList) {
                    notifys("\n", 0);
                    notifys("weather "+num+++"\n", 0);
                    notifys("cond_code_d = " + weather.getCond_code_d(), 0);
                    notifys("cond_code_n = " + weather.getCond_code_n(), 0);
                    notifys("cond_txt_d = " + weather.getCond_code_d(), 0);
                    notifys("date = " + weather.getDate(), 0);
                    notifys("hum = " + weather.getHum(), 0);
                    notifys("pcpn = " + weather.getPcpn(), 0);
                    notifys("pop = " + weather.getPop(), 0);
                    notifys("pres = " + weather.getPres(), 0);
                    notifys("tmp_max = " + weather.getTmp_max(), 0);
                    notifys("tmp_min = " + weather.getTmp_min(), 0);
                    notifys("uv_index = " + weather.getUv_index(), 0);
                    notifys("vis = " + weather.getVis(), 0);
                    notifys("wind_deg = " + weather.getWind_deg(), 0);
                    notifys("wind_dir = " + weather.getWind_dir(), 0);
                    notifys("wind_sc = " + weather.getWind_sc(), 0);
                    notifys("wind_spd = " + weather.getWind_spd(), 2000);

                }
            }
        }).start();


    }

    public void notifys(String msg, int i) {
        sbString.append(msg + "\n");
        observerNotify(sbString.toString());
        if (i != 0) {
            sleep(i);
        }
    }
}
