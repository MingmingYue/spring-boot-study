package com.spring.study.controller;

import com.alibaba.fastjson.JSONObject;
import com.spring.study.common.TimeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Created by 2019-06-28
 */
public class TestController {

    private static final Logger log = LogManager.getLogger(TestController.class);

    public static int a = 1;
    static List<Double> doubleList = new ArrayList<>(Arrays.asList(1.11));
    static List<String> stringList = new ArrayList<>(Arrays.asList("0", "1"));
    static List<String> reveTypeList = new ArrayList<>(Arrays.asList("RPA", "RPC", "RPI", "RPM"));
    static List<String> payTypeList = new ArrayList<>(Arrays.asList("CPA", "CPC", "CPI", "CPM"));
    static List<Integer> integerList = new ArrayList<>(Arrays.asList(1));
    static List<String> deviceList = new ArrayList<>(Arrays.asList("hP"));

    static List<String> devicefilterList = new ArrayList<>(Arrays.asList("11"));
    static List<String> counterIdList = new ArrayList<>(Arrays.asList("adfill", "adclick", "adview", "conversion"));//,

    public static void getUser() {

        int offerId = new Random().nextInt(8) + 10001;
//        int offerId =  10003;
        long time = (System.currentTimeMillis());
        int publisherId = 10002;


        JSONObject ad = new JSONObject();
        ad.put("adid", offerId);

        int countA = new Random().nextInt(4);

        ad.put("creativesid", 1);
        ad.put("revenue", getDouble(doubleList));
        ad.put("revenuetype", getString(reveTypeList));
        ad.put("payout", getDouble(doubleList));
        ad.put("payouttype", getString(payTypeList));
        ad.put("currency", getString(reveTypeList));
        ad.put("pcur", getString(reveTypeList));

        JSONObject device = new JSONObject();
        device.put("dtype", getInteger(integerList));
        device.put("imei", getString(deviceList));
        device.put("imsi", getString(deviceList));
        device.put("aaid", getString(deviceList));
        device.put("anid", getString(deviceList));
        device.put("idfa", getString(deviceList));
        device.put("idfv", getString(deviceList));
        device.put("mac", getString(deviceList));
        device.put("ip", getString(deviceList));
        device.put("language", "zh-Hans-CN");
        device.put("brand", getString(deviceList));
        device.put("model", getString(deviceList));
        device.put("os", getInteger(integerList));
        device.put("osv", getString(stringList));
        device.put("conntype", getInteger(integerList));
        device.put("orientation", getInteger(integerList));

        JSONObject devicefilter = new JSONObject();
        devicefilter.put("imei", UUID.randomUUID().toString());
//        devicefilter.put("anid", UUID.randomUUID().toString());
        devicefilter.put("anid", getString(devicefilterList));
        devicefilter.put("idfa", getString(devicefilterList));


        JSONObject ext = new JSONObject();
        ext.put("fill", 1 + a % 4);
        a++;

        JSONObject app = new JSONObject();
        app.put("bundle", getString(deviceList));
        app.put("appv", getString(stringList));
        app.put("name", getString(stringList));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rowkey", UUID.randomUUID().toString());
        jsonObject.put("requestid", getString(counterIdList));
        jsonObject.put("counterid", getString(counterIdList));
        jsonObject.put("version", getString(deviceList));
        jsonObject.put("publishid", publisherId);
        jsonObject.put("subid", "Ww18jU6X00j2dV41o8r7");
        jsonObject.put("time", TimeUtils.getCurr());
        jsonObject.put("hour", "2019-05-8T12");
        jsonObject.put("adtype", getInteger(integerList));
        jsonObject.put("device", device);
        jsonObject.put("devicefilter", devicefilter);
        jsonObject.put("app", app);
        jsonObject.put("ad", ad);
        jsonObject.put("country", "CN");
        String d_o = getString(stringList);
        jsonObject.put("dkey", d_o);
        jsonObject.put("dval", d_o + "23131231");
        jsonObject.put("city", getString(stringList));
        jsonObject.put("ext", ext);

        log.info(jsonObject.toString());

//        log.info("{\"rowkey\":\"80f23b0115534972000058rkq\",\"requestid\":\"10685813e3434c729162780b49d5e109\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1553497200005,\"hour\":\"2019-03-25T15\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"devicefilter\":{\"imei\":\"865002032845935\",\"anid\":\"adf174da249c909d\",\"idfa\":\"\"},\"ad\":{\"adid\":10046,\"adindex\":0,\"creativesid\":10384,\"revenue\":1.4,\"revenuetype\":\"RPA\",\"payout\":1.12,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Jinan\",\"dkey\":\"0\",\"dval\":\"0\",\"expirationtime\":1553504400005}");
//        log.info("{\"rowkey\":\"80f23b011553497200033a6ha\",\"requestid\":\"5b84a40ca6b947758e0587172184c109\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1553497200033,\"hour\":\"2019-03-25T15\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"devicefilter\":{\"imei\":\"869114037932406\",\"anid\":\"e1f4a73f741d5623\",\"idfa\":\"\"},\"ad\":{\"adid\":10042,\"adindex\":1,\"creativesid\":10349,\"revenue\":0.7,\"revenuetype\":\"RPA\",\"payout\":0.56,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Nanning\",\"dkey\":\"0\",\"dval\":\"0\",\"expirationtime\":1553504400033}");
//        log.info("{\"rowkey\":\"80f23b011553497200033gyuu\",\"requestid\":\"e51eac41a48543cb87babad129fc9900\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1553497200033,\"hour\":\"2019-03-25T15\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"devicefilter\":{\"imei\":\"865031039909980\",\"anid\":\"6dcbb7fde8a4f5c7\",\"idfa\":\"\"},\"ad\":{\"adid\":10004,\"adindex\":0,\"creativesid\":10050,\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Beijing\",\"dkey\":\"0\",\"dval\":\"0\",\"expirationtime\":1553504400033}");
//        log.info("{\"rowkey\":\"986af33c1553500799989abwe\",\"requestid\":\"9144ff4110554c26b0b3d84ff48f499b\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1553500799989,\"hour\":\"2019-03-25T15\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"devicefilter\":{\"imei\":\"868228031183807\",\"anid\":\"61554263f333516e\",\"idfa\":\"\"},\"ad\":{\"adid\":10054,\"adindex\":2,\"creativesid\":10430,\"revenue\":1.6,\"revenuetype\":\"RPA\",\"payout\":1.28,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Nanjing\",\"dkey\":\"0\",\"dval\":\"0\",\"expirationtime\":1553507999989}");
//        log.info("{\"rowkey\":\"986af33c1553500799994muh3\",\"requestid\":\"819094744b994670acb885a555b8b23d\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1553500799994,\"hour\":\"2019-03-25T15\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"devicefilter\":{\"imei\":\"865360035359175\",\"anid\":\"5179857ded0e3608\",\"idfa\":\"\"},\"ad\":{\"adid\":10054,\"adindex\":1,\"creativesid\":10430,\"revenue\":1.6,\"revenuetype\":\"RPA\",\"payout\":1.28,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Tianjin\",\"dkey\":\"0\",\"dval\":\"0\",\"expirationtime\":1553507999994}");
//        log.info("{\"rowkey\":\"986af33c1553500799997c7y6\",\"requestid\":\"5732fcf767e84f9fb4065994dde8833e\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1553500799997,\"hour\":\"2019-03-25T15\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"devicefilter\":{\"imei\":\"865422038261962\",\"anid\":\"6a89fe6ba16f9b8c\",\"idfa\":\"\"},\"ad\":{\"adid\":10028,\"adindex\":2,\"creativesid\":10254,\"revenue\":1,\"revenuetype\":\"RPA\",\"payout\":1,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Chongqing\",\"dkey\":\"0\",\"dval\":\"0\",\"expirationtime\":1553507999997}");
//


//        log.info("{\"rowkey\":\"3795f1351552023576119hq3u\",\"requestid\":\"28f5a49a1e3743199fea6d2597275a8e\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"2J70l171oa71F93n067F\",\"time\":1552023583003,\"hour\":\"2019-03-08T13\",\"adtype\":3,\"country\":\"Unknown\",\"source\":0,\"devicefilter\":{\"imei\":\"868291022995894\",\"anid\":\"a60fd2fb34dd3f07\",\"idfa\":\"D081F4E0-708D-44DF-A6D1-C974BA36B671\"},\"ad\":{\"adid\":10018,\"adindex\":0,\"creativesid\":10159,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Unknown\",\"expirationtime\":1552030776118}");
//        log.info("{\"rowkey\":\"ba91aaca1552023700780kow3\",\"requestid\":\"bd152182a1a846baa0326585971fcc0b\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"2J70l171oa71F93n067F\",\"time\":1552023737612,\"hour\":\"2019-03-08T13\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10025,\"adindex\":0,\"creativesid\":10233,\"revenue\":1,\"revenuetype\":\"RPA\",\"payout\":1,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Beijing\",\"expirationtime\":1552030900780}");
//        log.info("{\"rowkey\":\"834e9b7815506604000066g1q\",\"requestid\":\"963ffe09ea2b42b1879d57a1369532bb\",\"counterid\":\"request\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1550660400006,\"hour\":\"2019-02-20T19\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"app\":{\"bundle\":\"c.l.a\",\"appv\":\"20190121\",\"name\":\"20190121\"},\"device\":{\"dtype\":1,\"imei\":\"869326034013895\",\"imsi\":\"460028696442808\",\"aaid\":\"\",\"anid\":\"7915057c8093e55f\",\"idfa\":\"\",\"idfv\":\"\",\"mac\":\"\",\"ip\":\"60.181.188.64\",\"language\":\"zh\",\"maker\":\"OPPO\",\"brand\":\"OPPO\",\"model\":\"OPPOA83\",\"os\":1,\"osv\":\"7.1.1\",\"conntype\":1,\"orientation\":1},\"city\":\"Wen'zhou\",\"ext\":{\"fill\":1},\"expirationtime\":1550667600006}");
//        log.info("{\"rowkey\":\"834e9b781550660400007xwod\",\"requestid\":\"23c073f9bd3e4378872ca143c74c7a6a\",\"counterid\":\"request\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778b\",\"time\":1550660400007,\"hour\":\"2019-02-20T19\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"app\":{\"bundle\":\"c.l.a\",\"appv\":\"20190121\"},\"device\":{\"dtype\":1,\"imei\":\"866197030270919\",\"imsi\":\"460004372927466\",\"aaid\":\"\",\"anid\":\"84408f979f3a00aa\",\"idfa\":\"\",\"idfv\":\"\",\"mac\":\"\",\"ip\":\"223.104.64.244\",\"language\":\"zh\",\"brand\":\"vivo\",\"model\":\"vivoX20PlusA\",\"os\":1,\"osv\":\"8.1.0\",\"conntype\":4,\"orientation\":1},\"city\":\"Dongguan\",\"ext\":{\"fill\":1},\"expirationtime\":1550667600007}");
//        log.info("{\"rowkey\":\"834e9b7815506604000094g8a\",\"requestid\":\"c0ace372f2c0426996be7f3b052c803b\",\"counterid\":\"request\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778c\",\"time\":1550660400009,\"hour\":\"2019-02-20T19\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"app\":{\"bundle\":\"c.l.a\",\"appv\":\"20190121\"},\"device\":{\"dtype\":1,\"imei\":\"867278047044155\",\"imsi\":\"460110072789784\",\"aaid\":\"\",\"anid\":\"f6b2139b58544f86\",\"idfa\":\"\",\"idfv\":\"\",\"mac\":\"\",\"ip\":\"1.183.67.126\",\"language\":\"zh\",\"brand\":\"OPPO\",\"model\":\"PAFM00\",\"os\":1,\"osv\":\"8.1.0\",\"conntype\":1,\"orientation\":1},\"city\":\"Baotou\",\"ext\":{\"fill\":1},\"expirationtime\":1550667600009}");
//        log.info("{\"rowkey\":\"834e9b781550660400012akmy\",\"requestid\":\"e05aa949e3bb48539340e27386d4f649\",\"counterid\":\"request\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778d\",\"time\":1550660400012,\"hour\":\"2019-02-20T19\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"app\":{\"bundle\":\"c.l.a\",\"appv\":\"20190121\"},\"device\":{\"dtype\":1,\"imei\":\"862977048655464\",\"imsi\":\"460008661640214\",\"aaid\":\"\",\"anid\":\"62270df8c4f06915\",\"idfa\":\"\",\"idfv\":\"\",\"mac\":\"\",\"ip\":\"223.78.211.197\",\"language\":\"zh\",\"brand\":\"HONOR\",\"model\":\"LND-AL30\",\"os\":1,\"osv\":\"8.0.0\",\"conntype\":1,\"orientation\":1},\"city\":\"Weifang\",\"ext\":{\"fill\":1},\"expirationtime\":1550667600012}");
//        log.info("{\"rowkey\":\"834e9b781550660400016h4np\",\"requestid\":\"6640b6f8d3ea468da75ff949bb8d6449\",\"counterid\":\"request\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778e\",\"time\":1550660400016,\"hour\":\"2019-02-20T19\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"app\":{\"bundle\":\"c.l.a\",\"appv\":\"20190121\"},\"device\":{\"dtype\":1,\"imei\":\"869020035746054\",\"imsi\":\"460023836917083\",\"aaid\":\"\",\"anid\":\"a264d65b3eb78c30\",\"idfa\":\"\",\"idfv\":\"\",\"mac\":\"\",\"ip\":\"111.72.244.109\",\"language\":\"zh\",\"brand\":\"vivo\",\"model\":\"vivoX21\",\"os\":1,\"osv\":\"8.1.0\",\"conntype\":1,\"orientation\":1},\"city\":\"Nanchang\",\"ext\":{\"fill\":1},\"expirationtime\":1550667600016}");
//        log.info("{\"rowkey\":\"834e9b781550660400025l00i\",\"requestid\":\"c95aab6d0a2c486a9c8c7d126709cabc\",\"counterid\":\"request\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778f\",\"time\":1550660400025,\"hour\":\"2019-02-20T19\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"app\":{\"bundle\":\"c.l.a\",\"appv\":\"20190121\"},\"device\":{\"dtype\":1,\"imei\":\"862629034888043\",\"imsi\":\"460030874012647\",\"aaid\":\"\",\"anid\":\"7f61f37c3fba9b20\",\"idfa\":\"\",\"idfv\":\"\",\"mac\":\"\",\"ip\":\"223.87.230.203\",\"language\":\"zh\",\"brand\":\"HUAWEI\",\"model\":\"EVA-AL10\",\"os\":1,\"osv\":\"8.0.0\",\"conntype\":1,\"orientation\":1},\"city\":\"Chengdu\",\"ext\":{\"fill\":1},\"expirationtime\":1550667600025}");
//        log.info("{\"rowkey\":\"834e9b7815506604000292mef\",\"requestid\":\"387caffb37a84e3c8679fee1981abfe6\",\"counterid\":\"request\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778g\",\"time\":1550660400029,\"hour\":\"2019-02-20T19\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"app\":{\"bundle\":\"c.l.a\",\"appv\":\"20190121\"},\"device\":{\"dtype\":1,\"imei\":\"862858031013475\",\"imsi\":\"460018479212094\",\"aaid\":\"\",\"anid\":\"1b4648f305b24969\",\"idfa\":\"\",\"idfv\":\"\",\"mac\":\"\",\"ip\":\"223.90.4.180\",\"language\":\"zh\",\"brand\":\"HONOR\",\"model\":\"PRA-AL00\",\"os\":1,\"osv\":\"8.0.0\",\"conntype\":1,\"orientation\":1},\"city\":\"Zhengzhou\",\"ext\":{\"fill\":1},\"expirationtime\":1550667600029}");
//        log.info("{\"rowkey\":\"834e9b781550660400037cwps\",\"requestid\":\"488239d9803b428da61ff2a68fea5a63\",\"counterid\":\"request\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778h\",\"time\":1550660400037,\"hour\":\"2019-02-20T19\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"app\":{\"bundle\":\"c.l.a\",\"appv\":\"20190121\"},\"device\":{\"dtype\":1,\"imei\":\"862524041164787\",\"imsi\":\"460007941385151\",\"aaid\":\"\",\"anid\":\"7b543f62e00f0eff\",\"idfa\":\"\",\"idfv\":\"\",\"mac\":\"\",\"ip\":\"106.85.37.72\",\"language\":\"zh\",\"brand\":\"HONOR\",\"model\":\"DUA-AL00\",\"os\":1,\"osv\":\"8.1.0\",\"conntype\":1,\"orientation\":1},\"city\":\"Chongqing\",\"ext\":{\"fill\":1},\"expirationtime\":1550667600037}");
//        log.info("{\"rowkey\":\"834e9b781550660400043mbpq\",\"requestid\":\"895a2b441ed44127a4a18367cf45a296\",\"counterid\":\"request\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778i\",\"time\":1550660400043,\"hour\":\"2019-02-20T19\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"app\":{\"bundle\":\"c.l.a\",\"appv\":\"20190121\"},\"device\":{\"dtype\":1,\"imei\":\"863472024670215\",\"imsi\":\"460075506605345\",\"aaid\":\"\",\"anid\":\"4aa16ad471fbcf2\",\"idfa\":\"\",\"idfv\":\"\",\"mac\":\"f8:a4:5f:57:80:c5\",\"ip\":\"120.33.229.48\",\"language\":\"zh\",\"brand\":\"Xiaomi\",\"model\":\"MI2SC\",\"os\":1,\"osv\":\"5.0.2\",\"conntype\":1,\"orientation\":1},\"city\":\"Putian\",\"ext\":{\"fill\":1},\"expirationtime\":1550667600043}");
//        log.info("{\"rowkey\":\"834e9b781550660400054aitx\",\"requestid\":\"993bdfeaed774507ba78b73210a42251\",\"counterid\":\"request\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778j\",\"time\":1550660400054,\"hour\":\"2019-02-20T19\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"app\":{\"bundle\":\"c.l.a\",\"appv\":\"20190121\"},\"device\":{\"dtype\":1,\"imei\":\"866546040832359\",\"imsi\":\"460003023950545\",\"aaid\":\"\",\"anid\":\"95b454f4e0528b43\",\"idfa\":\"\",\"idfv\":\"\",\"mac\":\"\",\"ip\":\"117.157.36.126\",\"language\":\"zh\",\"brand\":\"OPPO\",\"model\":\"PBEM00\",\"os\":1,\"osv\":\"8.1.0\",\"conntype\":1,\"orientation\":1},\"city\":\"Lanzhou\",\"ext\":{\"fill\":1},\"expirationtime\":1550667600054}");
//        log.info("{\"rowkey\":\"834e9b7815506604000066g1q\",\"requestid\":\"963ffe09ea2b42b1879d57a1369532bb\",\"counterid\":\"request\",\"version\":\"1\",\"publishid\":10002,\"subid\":\"zPM68X78r094gh9N778k\",\"time\":1550660400006,\"hour\":\"2019-02-20T19\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"app\":{\"bundle\":\"c.l.a\",\"appv\":\"20190121\"},\"device\":{\"dtype\":1,\"imei\":\"869326034013895\",\"imsi\":\"460028696442808\",\"aaid\":\"\",\"anid\":\"7915057c8093e55f\",\"idfa\":\"\",\"idfv\":\"\",\"mac\":\"\",\"ip\":\"60.181.188.64\",\"language\":\"zh\",\"brand\":\"OPPO\",\"model\":\"OPPOA83\",\"os\":1,\"osv\":\"7.1.1\",\"conntype\":1,\"orientation\":1},\"city\":\"Wenzhou\",\"ext\":{\"fill\":1},\"expirationtime\":1550667600006}");


//        log.info("{\"rowkey\":\"61da624d15455552819646gfb\",\"requestid\":\"863fb51f624847469565be32ab65bc74\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":" + publisherId + ",\"subid\":\"fPM68X78r094gh9N778a\",\"time\":" + time + ",\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":" + offerId + ",\"adindex\":0,\"creativesid\":10167,\"revenue\":0.5,\"revenuetype\":\"RPA\",\"payout\":0.5,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Changsha\",\"expirationtime\":1545562481964}");
//        log.info("{\"rowkey\":\"61da624d15455552819646gfb\",\"requestid\":\"863fb51f624847469565be32ab65bc74\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":" + publisherId + 1 + ",\"subid\":\"fPM68X78r094gh9N778a\",\"time\":" + time + ",\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":" + offerId + ",\"adindex\":0,\"creativesid\":10167,\"revenue\":0.5,\"revenuetype\":\"RPA\",\"payout\":0.5,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Changsha\",\"expirationtime\":1545562481964}");
//        log.info("{\"rowkey\":\"61da624d15455552819646gfb\",\"requestid\":\"863fb51f624847469565be32ab65bc74\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":" + publisherId + 2 + ",\"subid\":\"fPM68X78r094gh9N778a\",\"time\":" + time + ",\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":" + offerId + ",\"adindex\":0,\"creativesid\":10167,\"revenue\":0.5,\"revenuetype\":\"RPA\",\"payout\":0.5,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Changsha\",\"expirationtime\":1545562481964}");

//        log.info("{\"rowkey\":\"9c5b12071545555245262m13f\",\"requestid\":\"9fceb1162ff2460ab89c071eb5a1870e\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"dPM68X78r094gh9N778a\",\"time\":1545555612438,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10003,\"adindex\":0,\"creativesid\":10045,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Unknown\",\"expirationtime\":1545562445262}");
//        log.info("{\"rowkey\":\"ac7d5c0915455527821815src\",\"requestid\":\"a74461b9ad7b42388ffb39a307a41f24\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545555614987,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10013,\"adindex\":1,\"creativesid\":10167,\"revenue\":0.5,\"revenuetype\":\"RPA\",\"payout\":0.5,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Hebei\",\"expirationtime\":1545559982181}");
//        log.info("{\"rowkey\":\"54231be61545555231829ci4y\",\"requestid\":\"a360ca4f7a0a4b409fcac5e7ab95c159\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545555628803,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10004,\"adindex\":2,\"creativesid\":10050,\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Jinan\",\"expirationtime\":1545562431829}");
//        log.info("{\"rowkey\":\"81800f2e1545553941640ujxi\",\"requestid\":\"6974e50930ae41b09aa04e5138d3caff\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545555633136,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10004,\"adindex\":1,\"creativesid\":10050,\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Beijing\",\"expirationtime\":1545561141640}");
//        log.info("{\"rowkey\":\"94d630e215455556201986su1\",\"requestid\":\"55708a76b7a24337b98ef487ac17908f\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545555634063,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10003,\"adindex\":1,\"creativesid\":10045,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Xi'an\",\"expirationtime\":1545562820198}");
//        log.info("{\"rowkey\":\"d0f171d31545554527257v4o6\",\"requestid\":\"7d7175850a8b43b0bccb3e4182a43c23\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545555645763,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10004,\"adindex\":2,\"creativesid\":10050,\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Zhengzhou\",\"expirationtime\":1545561727257}");
//        log.info("{\"rowkey\":\"c1e77f7f1545551634129x5xl\",\"requestid\":\"52c7bddca2d140d184f5b51123d99cbc\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545555648086,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10018,\"adindex\":1,\"creativesid\":10159,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Hangzhou\",\"expirationtime\":1545558834129}");
//        log.info("{\"rowkey\":\"c959f79e1545555363452feq6\",\"requestid\":\"e50772bbecaf47e3a577a4c3723aa23d\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545555650748,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10018,\"adindex\":1,\"creativesid\":10159,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Harbin\",\"expirationtime\":1545562563452}");
//        log.info("{\"rowkey\":\"8a59bb9815455556287543uje\",\"requestid\":\"8445914aba4849f8a76af4b34b7d228a\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545555650981,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10004,\"adindex\":2,\"creativesid\":10050,\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Kunming\",\"expirationtime\":1545562828754}");

//        log.info("{\"rowkey\":\"4ef7ff631545559199930kexh\",\"requestid\":\"50934f5c04024e8e82e2066445de5d9e\",\"counterid\":\"conversion\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"cPM68X78r094gh9N778a\",\"time\":1545559199930,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10003,\"adindex\":2,\"creativesid\":10045,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Hefei\",\"expirationtime\":1545566399930}");
//        log.info("{\"rowkey\":\"4ef7ff63154555919996155x9\",\"requestid\":\"01427f0da36e4deabef5255237a8495b\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545559199961,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10018,\"adindex\":0,\"creativesid\":10159,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Guiyang\",\"expirationtime\":1545566399961}");
//        log.info("{\"rowkey\":\"4ef7ff6315455591999620w9b\",\"requestid\":\"01427f0da36e4deabef5255237a8495b\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545559199962,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10013,\"adindex\":1,\"creativesid\":10167,\"revenue\":0.5,\"revenuetype\":\"RPA\",\"payout\":0.5,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Guiyang\",\"expirationtime\":1545566399962}");
//        log.info("{\"rowkey\":\"4ef7ff631545559199962u63o\",\"requestid\":\"04a908af676c4737a3d59a61e271dd8c\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545559199962,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10013,\"adindex\":0,\"creativesid\":10167,\"revenue\":0.5,\"revenuetype\":\"RPA\",\"payout\":0.5,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Hebei\",\"expirationtime\":1545566399962}");
//        log.info("{\"rowkey\":\"4ef7ff631545559199963oyh8\",\"requestid\":\"01427f0da36e4deabef5255237a8495b\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545559199963,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10003,\"adindex\":2,\"creativesid\":10045,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Guiyang\",\"expirationtime\":1545566399963}");
//        log.info("{\"rowkey\":\"4ef7ff63154555919996328rj\",\"requestid\":\"04a908af676c4737a3d59a61e271dd8c\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545559199963,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10004,\"adindex\":1,\"creativesid\":10050,\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Hebei\",\"expirationtime\":1545566399963}");
//        log.info("{\"rowkey\":\"4ef7ff631545559199963xij9\",\"requestid\":\"288a88641797483ea1500017723a40f9\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545559199963,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10018,\"adindex\":0,\"creativesid\":10159,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Kaifeng\",\"expirationtime\":1545566399963}");
//        log.info("{\"rowkey\":\"4ef7ff631545559199963akcn\",\"requestid\":\"04a908af676c4737a3d59a61e271dd8c\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545559199963,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10003,\"adindex\":2,\"creativesid\":10045,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Hebei\",\"expirationtime\":1545566399963}");
//        log.info("{\"rowkey\":\"4ef7ff631545559199963lg3x\",\"requestid\":\"288a88641797483ea1500017723a40f9\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545559199963,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10004,\"adindex\":1,\"creativesid\":10050,\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Kaifeng\",\"expirationtime\":1545566399963}");
//        log.info("{\"rowkey\":\"4ef7ff6315455591999649dtf\",\"requestid\":\"288a88641797483ea1500017723a40f9\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"zPM68X78r094gh9N778a\",\"time\":1545559199964,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10003,\"adindex\":2,\"creativesid\":10045,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Kaifeng\",\"expirationtime\":1545566399964}");

//        log.info(" {\"rowkey\":\"279a26f51545559197888x4eb\",\"requestid\":\"979fbf630c4748ae80c4cde83e8ebfaa\",\"counterid\":\"conversion\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"U9zl1172zn8NEeI0235O\",\"time\":1545559198245,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10013,\"adindex\":0,\"creativesid\":10167,\"revenue\":0.5,\"revenuetype\":\"RPA\",\"payout\":0.5,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Unknown\",\"expirationtime\":1545566397888}");
//        log.info("{\"rowkey\":\"f1bf10d515455591981412317\",\"requestid\":\"a1c4895eab31487eb24e8832013678b8\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"2J70l171oa71F93n067F\",\"time\":1545559198768,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10013,\"adindex\":0,\"creativesid\":10167,\"revenue\":0.5,\"revenuetype\":\"RPA\",\"payout\":0.5,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Chaozhou\",\"expirationtime\":1545566398141}");
//        log.info("{\"rowkey\":\"f1bf10d51545559198547cqgf\",\"requestid\":\"39cd29b31cd641ab98f5dbe5180b8d12\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"2J70l171oa71F93n067F\",\"time\":1545559198993,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10018,\"adindex\":0,\"creativesid\":10159,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Hefei\",\"expirationtime\":1545566398547}");
//        log.info("{\"rowkey\":\"f1bf10d515455591985232xos\",\"requestid\":\"5667bc70c4a44115ad82eba44ce82afa\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"2J70l171oa71F93n067F\",\"time\":1545559199028,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10003,\"adindex\":0,\"creativesid\":10045,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Hebei\",\"expirationtime\":1545566398523}");
//        log.info("{\"rowkey\":\"f1bf10d51545559198763sk53\",\"requestid\":\"f4ad04cc721e452d8fa373c9b8866bcd\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"2J70l171oa71F93n067F\",\"time\":1545559199072,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10004,\"adindex\":0,\"creativesid\":10050,\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Zhengzhou\",\"expirationtime\":1545566398763}");
//        log.info("{\"rowkey\":\"f1bf10d51545559198956surf\",\"requestid\":\"b7f5900809604535a3a392211d013e94\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"2J70l171oa71F93n067F\",\"time\":1545559199198,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10018,\"adindex\":0,\"creativesid\":10159,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Shanghai\",\"expirationtime\":1545566398956}");
//        log.info("{\"rowkey\":\"f1bf10d51545559198790o6vz\",\"requestid\":\"91b7290962b94dc594e50bf38ebd3449\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"2J70l171oa71F93n067F\",\"time\":1545559199225,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10013,\"adindex\":0,\"creativesid\":10167,\"revenue\":0.5,\"revenuetype\":\"RPA\",\"payout\":0.5,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Unknown\",\"expirationtime\":1545566398790}");
//        log.info("{\"rowkey\":\"0318a16015455591991526zr3\",\"requestid\":\"97ea4bfa5fda4005b0169c6041e562e3\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"2J70l171oa71F93n067F\",\"time\":1545559199343,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10004,\"adindex\":0,\"creativesid\":10050,\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Jinan\",\"expirationtime\":1545566399152}");
//        log.info("{\"rowkey\":\"f1bf10d51545559198666pgzh\",\"requestid\":\"0cbdd0211c7b48fb84953dbc18696c08\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"2J70l171oa71F93n067F\",\"time\":1545559199348,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10003,\"adindex\":0,\"creativesid\":10045,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Nanchang\",\"expirationtime\":1545566398666}");
//        log.info("{\"rowkey\":\"f1bf10d51545559198791l2id\",\"requestid\":\"09fdbc27b5844ec6b57f77c79b53fb2b\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"2J70l171oa71F93n067F\",\"time\":1545559199716,\"hour\":\"2018-12-23T17\",\"adtype\":3,\"country\":\"CN\",\"source\":0,\"ad\":{\"adid\":10018,\"adindex\":0,\"creativesid\":10159,\"revenue\":0.8,\"revenuetype\":\"RPA\",\"payout\":0.8,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Jinan\",\"expirationtime\":1545566398791}");

//        int a = new Random().nextInt(10051);
//        log.info("{\"rowkey\":\"c9a4ee6615420060144103zgf\",\"requestid\":\"db7ac9cd38b84fe89924a78f04a3d96b\",\"counterid\":\"adfill\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"lB3G74u4d1443Ho5kVN6\",\"time\":1542006014410,\"hour\":\"2018-11-12T15\",\"adtype\":3,\"country\":\"Unknown\",\"ad\":{\"adid\":10004,\"adindex\":0,\"creativesid\":" + a + ",\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Unknown\",\"expirationtime\":1542013214410}");
//        a = new Random().nextInt(10051);
//        log.info("{\"rowkey\":\"c9a4ee6615420060144103zgf\",\"requestid\":\"db7ac9cd38b84fe89924a78f04a3d96b\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"lB3G74u4d1443Ho5kVN6\",\"time\":1542006014410,\"hour\":\"2018-11-12T15\",\"adtype\":3,\"country\":\"Unknown\",\"ad\":{\"adid\":10004,\"adindex\":0,\"creativesid\":" + a + ",\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Unknown\",\"expirationtime\":1542013214410}");
////        a = new Random().nextInt(10051);
//        log.info("{\"rowkey\":\"c9a4ee6615420060144103zgf\",\"requestid\":\"db7ac9cd38b84fe89924a78f04a3d96b\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"lB3G74u4d1443Ho5kVN6\",\"time\":1542006014410,\"hour\":\"2018-11-12T15\",\"adtype\":3,\"country\":\"Unknown\",\"ad\":{\"adid\":10004,\"adindex\":0,\"creativesid\":" + a + ",\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Unknown\",\"expirationtime\":1542013214410}");
////        a = new Random().nextInt(10051);
//        log.info("{\"rowkey\":\"c9a4ee6615420060144103zgf\",\"requestid\":\"db7ac9cd38b84fe89924a78f04a3d96b\",\"counterid\":\"adclick\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"lB3G74u4d1443Ho5kVN6\",\"time\":1542006014410,\"hour\":\"2018-11-12T15\",\"adtype\":3,\"country\":\"Unknown\",\"ad\":{\"adid\":10004,\"adindex\":0,\"creativesid\":" + a + ",\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Unknown\",\"expirationtime\":1542013214410}");
////        a = new Random().nextInt(10051);
//        log.info("{\"rowkey\":\"c9a4ee6615420060144103zgf\",\"requestid\":\"db7ac9cd38b84fe89924a78f04a3d96b\",\"counterid\":\"adview\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"lB3G74u4d1443Ho5kVN6\",\"time\":1542006014410,\"hour\":\"2018-11-12T15\",\"adtype\":3,\"country\":\"Unknown\",\"ad\":{\"adid\":10004,\"adindex\":0,\"creativesid\":" + a + ",\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Unknown\",\"expirationtime\":1542013214410}");
////        a = new Random().nextInt(10051);
//        log.info("{\"rowkey\":\"c9a4ee6615420060144103zgf\",\"requestid\":\"db7ac9cd38b84fe89924a78f04a3d96b\",\"counterid\":\"conversion\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"lB3G74u4d1443Ho5kVN6\",\"time\":1542006014410,\"hour\":\"2018-11-12T15\",\"adtype\":3,\"country\":\"Unknown\",\"ad\":{\"adid\":10004,\"adindex\":0,\"creativesid\":" + a + ",\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Unknown\",\"expirationtime\":1542013214410}");
////        a = new Random().nextInt(10051);
//        log.info("{\"rowkey\":\"c9a4ee6615420060144103zgf\",\"requestid\":\"db7ac9cd38b84fe89924a78f04a3d96b\",\"counterid\":\"conversion\",\"version\":\"1\",\"publishid\":10001,\"subid\":\"lB3G74u4d1443Ho5kVN6\",\"time\":1542006014410,\"hour\":\"2018-11-12T15\",\"adtype\":3,\"country\":\"Unknown\",\"ad\":{\"adid\":10004,\"adindex\":0,\"creativesid\":" + a + ",\"revenue\":0.4,\"revenuetype\":\"RPA\",\"payout\":0.4,\"payouttype\":\"CPA\",\"currency\":\"CNY\"},\"city\":\"Unknown\",\"expirationtime\":1542013214410}");
//

//        log.info("{\"publisher_id\":\"" + publisherId + "\",\"sub_id\":\"2J70l171oa71F93n067F\",\"ad_id\":\"10003\",\"ad_type\":3,\"timestamp\":" + time + ",\"imei\":\"\",\"anid\":\"1f42800c7e23dfb2\",\"idfa\":\"\",\"idfv\":\"\",\"is_mp\":0,\"os\":1,\"network\":10003,\"counter_id\":\"mp_click\",\"row_key\":\"1b5d4b7e1544605242661j031\",\"date\":\"2018-12-12 17\",\"country\":\"CN\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"lB3G74u4d1443Ho5kVN6\",\"ad_id\":\"10004\",\"ad_type\":3,\"timestamp\":1544605262783,\"imei\":\"864878047824796\",\"anid\":\"58e35038cd88fb4b\",\"idfa\":\"\",\"idfv\":\"\",\"is_mp\":0,\"os\":1,\"network\":10004,\"counter_id\":\"mp_click\",\"row_key\":\"35d6392b1544605259559bwqd\",\"date\":\"2018-12-12 17\",\"country\":\"CN\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"lB3G74u4d1443Ho5kVN6\",\"ad_id\":\"10007\",\"ad_type\":3,\"timestamp\":1544605772832,\"imei\":\"865649033328123\",\"anid\":\"9daaefc24603e91b\",\"idfa\":\"\",\"idfv\":\"\",\"is_mp\":0,\"os\":1,\"network\":10001,\"counter_id\":\"mp_click\",\"row_key\":\"d128ad311544605767581wtfl\",\"date\":\"2018-12-12 17\",\"country\":\"CN\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"zPM68X78r094gh9N778a\",\"ad_id\":\"10007\",\"ad_type\":3,\"timestamp\":1544605490351,\"imei\":\"864496030474030\",\"anid\":\"8ff1c111eb596f\",\"idfa\":\"\",\"idfv\":\"\",\"is_mp\":0,\"os\":1,\"network\":10001,\"counter_id\":\"mp_click\",\"row_key\":\"3b8600f01544605484888hhnb\",\"date\":\"2018-12-12 17\",\"country\":\"CN\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"zPM68X78r094gh9N778a\",\"ad_id\":\"10008\",\"ad_type\":3,\"timestamp\":1544605690271,\"imei\":\"863979034638362\",\"anid\":\"fea01bf5f07c144a\",\"idfa\":\"\",\"idfv\":\"\",\"is_mp\":0,\"os\":1,\"network\":10006,\"counter_id\":\"mp_click\",\"row_key\":\"c4579ad21544605687309oxmu\",\"date\":\"2018-12-12 17\",\"country\":\"CN\"}");
//
//        log.info("{\"publisher_id\":\"" + publisherId + 1 + "\",\"sub_id\":\"U9zl1172zn8NEeI0235O\",\"ad_id\":\"10003\",\"ad_type\":3,\"timestamp\":" + time + ",\"imei\":\"\",\"anid\":\"\",\"idfa\":\"4990EA20-E7BC-474D-8EF2-D76837EFC1FC\",\"idfv\":\"\",\"is_mp\":0,\"os\":2,\"network\":10003,\"counter_id\":\"mp_view\",\"row_key\":\"80bee8311544605221904f7cp\",\"date\":\"2018-12-12 17\",\"country\":\"CN\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"2J70l171oa71F93n067F\",\"ad_id\":\"10003\",\"ad_type\":3,\"timestamp\":1544605243461,\"imei\":\"\",\"anid\":\"1f42800c7e23dfb2\",\"idfa\":\"\",\"idfv\":\"\",\"is_mp\":0,\"os\":1,\"network\":10003,\"counter_id\":\"mp_view\",\"row_key\":\"9db9083c1544605242661lyp5\",\"date\":\"2018-12-12 17\",\"country\":\"CN\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"lB3G74u4d1443Ho5kVN6\",\"ad_id\":\"10004\",\"ad_type\":3,\"timestamp\":1544605260313,\"imei\":\"864878047824796\",\"anid\":\"58e35038cd88fb4b\",\"idfa\":\"\",\"idfv\":\"\",\"is_mp\":0,\"os\":1,\"network\":10004,\"counter_id\":\"mp_view\",\"row_key\":\"fc759f921544605259559qq4m\",\"date\":\"2018-12-12 17\",\"country\":\"CN\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"lB3G74u4d1443Ho5kVN6\",\"ad_id\":\"10009\",\"ad_type\":3,\"timestamp\":1544605357195,\"imei\":\"869175030252696\",\"anid\":\"58c07718792b56b9\",\"idfa\":\"\",\"idfv\":\"\",\"is_mp\":0,\"os\":1,\"network\":10007,\"counter_id\":\"mp_view\",\"row_key\":\"7f00e1141544605356927lh7v\",\"date\":\"2018-12-12 17\",\"country\":\"CN\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"2J70l171oa71F93n067F\",\"ad_id\":\"10003\",\"ad_type\":3,\"timestamp\":1544605418415,\"imei\":\"\",\"anid\":\"7d679f06808f60aa\",\"idfa\":\"\",\"idfv\":\"\",\"is_mp\":0,\"os\":1,\"network\":10003,\"counter_id\":\"mp_view\",\"row_key\":\"942219431544605417938h41g\",\"date\":\"2018-12-12 17\",\"country\":\"CN\"}");
//
//        log.info("{\"publisher_id\":\"" + publisherId + 2 + "\",\"sub_id\":\"youyouhezi\",\"ad_id\":\"10003\",\"ad_type\":0,\"imei\":\"868291022995894\",\"anid\":\"a60fd2fb34dd3f07\",\"idfa\":\"D081F4E0-708D-44DF-A6D1-C974BA36B671\",\"is_mp\":0,\"settlement\":\"CPA\",\"revenue\":10.01,\"currency\":\"USD\",\"event_time\":" + time + ",\"receive_time\":1540262513011,\"counter_id\":\"mp_postback\",\"row_key\":\"50c4f91915402624644225bld\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"youyouhezi\",\"ad_id\":\"10004\",\"ad_type\":3,\"is_mp\":1,\"settlement\":\"CPA\",\"revenue\":10.01,\"currency\":\"USD\",\"event_time\":1544605243461,\"receive_time\":1540262597308,\"counter_id\":\"mp_postback\",\"row_key\":\"2ce0506b1540261410590nzmd\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"youyouhezi\",\"ad_id\":\"10007\",\"ad_type\":0,\"imei\":\"868291022995894\",\"anid\":\"a60fd2fb34dd3f07\",\"idfa\":\"D081F4E0-708D-44DF-A6D1-C974BA36B671\",\"is_mp\":0,\"settlement\":\"CPA\",\"revenue\":10.01,\"currency\":\"USD\",\"event_time\":1544605260313,\"receive_time\":1540262513011,\"counter_id\":\"mp_postback\",\"row_key\":\"50c4f91915402624644225bld\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"youyouhezi\",\"ad_id\":\"10007\",\"ad_type\":3,\"imei\":\"868291022995894\",\"anid\":\"a60fd2fb34dd3f07\",\"idfa\":\"D081F4E0-708D-44DF-A6D1-C974BA36B671\",\"idfv\":\"\",\"is_mp\":0,\"os\":2,\"network\":2,\"settlement\":\"CPA\",\"revenue\":10.01,\"currency\":\"USD\",\"event_time\":1544605357195,\"receive_time\":1540463424600,\"counter_id\":\"mp_postback\",\"row_key\":\"334096ff15404633833981nkk\",\"date\":\"2018-10-25 10\"}");
//        log.info("{\"publisher_id\":\"10001\",\"sub_id\":\"youyouhezi\",\"ad_id\":\"10008\",\"ad_type\":3,\"imei\":\"868291022995894\",\"anid\":\"a60fd2fb34dd3f07\",\"idfa\":\"D081F4E0-708D-44DF-A6D1-C974BA36B671\",\"idfv\":\"\",\"is_mp\":0,\"os\":2,\"network\":2,\"settlement\":\"CPA\",\"revenue\":10.01,\"currency\":\"USD\",\"event_time\":1540434756000,\"receive_time\":1540463508012,\"counter_id\":\"mp_postback\",\"row_key\":\"52c16b131540463491429rnox\",\"date\":\"2018-10-25 10\"}");
    }


    private static Double getDouble(List<Double> obj) {
        int a = new Random().nextInt(obj.size());
        return obj.get(a);
    }

    private static String getString(List<String> obj) {
        int a = new Random().nextInt(obj.size());
        return obj.get(a);
    }

    private static Integer getInteger(List<Integer> obj) {
        int a = new Random().nextInt(obj.size());
        return obj.get(a);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            try {
                if (i % 100 == 0) {
                    Thread.sleep(50);
                }
                Thread.sleep(new Random().nextInt(20));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            getUser();
        }
    }

}
