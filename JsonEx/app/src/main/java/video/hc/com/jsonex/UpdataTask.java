package video.hc.com.jsonex;

import android.os.Handler;
import android.os.Message;

import video.hc.com.jsonex.utils.DataUtils;


/**
 * Created by ly on 2019/6/3.
 */

class UpdataTask implements Runnable {
    public String data = "";
    private Handler handler;
    boolean flag = true;

    @Override
    public void run() {
        getJsonData();
    }

    public UpdataTask(Handler handler) {
        this.handler = handler;
    }

    private void getJsonData() {
        if (flag) {
            data = "  {\n" +
                    "        \"id\":\"01\",\n" +
                    "        \"language\": \"Java\",\n" +
                    "        \"edition\": \"third\",\n" +
                    "        \"author\": \"Herbert Schildt\"\n" +
                    "  }";
        } else {
            data = "{\n" +
                    "    \"HeWeather6\": [\n" +
                    "        {\n" +
                    "            \"basic\": {\n" +
                    "                \"cid\": \"CN101010100\",\n" +
                    "                \"location\": \"北京\",\n" +
                    "                \"parent_city\": \"北京\",\n" +
                    "                \"admin_area\": \"北京\",\n" +
                    "                \"cnty\": \"中国\",\n" +
                    "                \"lat\": \"39.90498734\",\n" +
                    "                \"lon\": \"116.40528870\",\n" +
                    "                \"tz\": \"8.0\"\n" +
                    "            },\n" +
                    "            \"daily_forecast\": [\n" +
                    "                {\n" +
                    "                    \"cond_code_d\": \"103\",\n" +
                    "                    \"cond_code_n\": \"101\",\n" +
                    "                    \"cond_txt_d\": \"晴间多云\",\n" +
                    "                    \"cond_txt_n\": \"多云\",\n" +
                    "                    \"date\": \"2017-10-26\",\n" +
                    "                    \"hum\": \"57\",\n" +
                    "                    \"pcpn\": \"0.0\",\n" +
                    "                    \"pop\": \"0\",\n" +
                    "                    \"pres\": \"1020\",\n" +
                    "                    \"tmp_max\": \"16\",\n" +
                    "                    \"tmp_min\": \"8\",\n" +
                    "                    \"uv_index\": \"3\",\n" +
                    "                    \"vis\": \"16\",\n" +
                    "                    \"wind_deg\": \"0\",\n" +
                    "                    \"wind_dir\": \"无持续风向\",\n" +
                    "                    \"wind_sc\": \"微风\",\n" +
                    "                    \"wind_spd\": \"5\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"cond_code_d\": \"101\",\n" +
                    "                    \"cond_code_n\": \"501\",\n" +
                    "                    \"cond_txt_d\": \"多云\",\n" +
                    "                    \"cond_txt_n\": \"雾\",\n" +
                    "                    \"date\": \"2017-10-27\",\n" +
                    "                    \"hum\": \"56\",\n" +
                    "                    \"pcpn\": \"0.0\",\n" +
                    "                    \"pop\": \"0\",\n" +
                    "                    \"pres\": \"1018\",\n" +
                    "                    \"tmp_max\": \"18\",\n" +
                    "                    \"tmp_min\": \"9\",\n" +
                    "                    \"uv_index\": \"3\",\n" +
                    "                    \"vis\": \"20\",\n" +
                    "                    \"wind_deg\": \"187\",\n" +
                    "                    \"wind_dir\": \"南风\",\n" +
                    "                    \"wind_sc\": \"微风\",\n" +
                    "                    \"wind_spd\": \"6\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"cond_code_d\": \"101\",\n" +
                    "                    \"cond_code_n\": \"101\",\n" +
                    "                    \"cond_txt_d\": \"多云\",\n" +
                    "                    \"cond_txt_n\": \"多云\",\n" +
                    "                    \"date\": \"2017-10-28\",\n" +
                    "                    \"hum\": \"26\",\n" +
                    "                    \"pcpn\": \"0.0\",\n" +
                    "                    \"pop\": \"0\",\n" +
                    "                    \"pres\": \"1029\",\n" +
                    "                    \"tmp_max\": \"17\",\n" +
                    "                    \"tmp_min\": \"5\",\n" +
                    "                    \"uv_index\": \"2\",\n" +
                    "                    \"vis\": \"20\",\n" +
                    "                    \"wind_deg\": \"2\",\n" +
                    "                    \"wind_dir\": \"北风\",\n" +
                    "                    \"wind_sc\": \"3-4\",\n" +
                    "                    \"wind_spd\": \"19\"\n" +
                    "                }\n" +
                    "            ],\n" +
                    "            \"status\": \"ok\",\n" +
                    "            \"update\": {\n" +
                    "                \"loc\": \"2017-10-26 23:09\",\n" +
                    "                \"utc\": \"2017-10-26 15:09\"\n" +
                    "            }\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}\n";
        }


        Message msg = new Message();
        msg.what = DataUtils.SUCESS;
        handler.sendMessage(msg);
    }

    public String getData() {
        return data;
    }

    public void setNewDataFlag(boolean b) {
        flag = b;
    }
}
