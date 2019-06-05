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
                    "    \"showapi_res_code\":0,\n" +
                    "    \"showapi_res_error\":\"\",\n" +
                    "    \"showapi_res_body\":{\n" +
                    "        \"pagebean\":{\n" +
                    "            \"allNum\":577,\n" +
                    "            \"allPages\":29,\n" +
                    "            \"contentlist\":[\n" +
                    "                {\n" +
                    "                    \"channelId\":\"5572a108b3cdc86cf39001cd\",\n" +
                    "                    \"channelName\":\"国内焦点\",\n" +
                    "                    \"desc\":\" 　　中国警察网北京4月22日电中国共产党的优秀党员，公安部原部长、党委书记陶驷驹同志，因病医治无效，于2016年4月18日0时36分在北京逝世， 享年81岁。22日上午，陶驷驹同志遗体送别仪式在北京举行。　　习近平、李克强、刘云山、张高丽、刘延东、李源潮、孟建柱、赵乐际、胡锦....\",\n" +
                    "                    \"imageurls\":[\n" +
                    "                        {\n" +
                    "                            \"height\":480,\n" +
                    "                            \"url\":\"http://n.sinaimg.cn/news/transform/20160423/P7Jb-fxrqhar9853560.jpg\",\n" +
                    "                            \"width\":400\n" +
                    "                        }\n" +
                    "                    ],\n" +
                    "                    \"link\":\"http://news.sina.com.cn/c/nd/2016-04-23/doc-ifxrpvea1126476.shtml\",\n" +
                    "                    \"pubDate\":\"2016-04-23 10:16:54\",\n" +
                    "                    \"source\":\"新浪\",\n" +
                    "                    \"title\":\"公安部原部长陶驷驹逝世 习近平胡锦涛等送花圈\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"channelId\":\"5572a108b3cdc86cf39001cd\",\n" +
                    "                    \"channelName\":\"国内焦点\",\n" +
                    "                    \"desc\":\" 　　原标题：山东3县市购房补钱！继邹城、寿光后，即墨也加入资料图　　日前，即墨市制定《关于促进房地产市场持续健康平稳发展的实施意见》，即墨市财政 将对购房者予以补贴。从《意见》出台之日起到今年年底前，在即墨首次购新建商品房的市民，每平米可领取补贴50到200元。对购....\",\n" +
                    "                    \"imageurls\":[\n" +
                    "                        {\n" +
                    "                            \"height\":327,\n" +
                    "                            \"url\":\"http://ww4.sinaimg.cn/mw690/77de9208jw1f36aamk03mj20go093406.jpg\",\n" +
                    "                            \"width\":600\n" +
                    "                        }\n" +
                    "                    ],\n" +
                    "                    \"link\":\"http://news.sina.com.cn/c/nd/2016-04-23/doc-ifxrpvea1122744.shtml\",\n" +
                    "                    \"pubDate\":\"2016-04-23 07:52:13\",\n" +
                    "                    \"source\":\"新浪\",\n" +
                    "                    \"title\":\"山东3县市对购房者予以财政补贴\"\n" +
                    "                },\n" +
                    "                {\n" +
                    "                    \"channelId\":\"5572a108b3cdc86cf39001cd\",\n" +
                    "                    \"channelName\":\"国内焦点\",\n" +
                    "                    \"desc\":\" 　　原标题：首家商业火箭公司成立　　京华时报讯（记者潘珊菊）昨天下午，记者从航天科工集团获悉，在成功发射首颗卫星“东方红一号”46年后，中国航天 技术步入“商用时代”，我国首家商业模式开展研发和应用的专业化火箭公司已于今年2月16日在武汉注册成立。　　据介绍，该公....\",\n" +
                    "                    \"imageurls\":[\n" +
                    "                    ],\n" +
                    "                    \"link\":\"http://news.sina.com.cn/o/2016-04-23/doc-ifxrpvqz6479220.shtml\",\n" +
                    "                    \"pubDate\":\"2016-04-23 03:19:35\",\n" +
                    "                    \"source\":\"新浪\",\n" +
                    "                    \"title\":\"中国首家商业火箭公司成立 注册时曾引官方争议\"\n" +
                    "                }\n" +
                    "            ],\n" +
                    "            \"currentPage\":1,\n" +
                    "            \"maxResult\":20\n" +
                    "        },\n" +
                    "        \"ret_code\":0\n" +
                    "    }\n" +
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
