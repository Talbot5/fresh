package com.chuhu.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class TwoCode {

    /*
     * 获取 token
     * 普通的 get 可获 token
     */
    public  static String getToken() {
        String appid = "wx33e927e7942f296d";
        String appsecret = "3196daa1edc43f967b2fb81d9318419a";
        try {

            Map<String, String> map = new LinkedHashMap<String, String>();
            map.put("grant_type", "client_credential");
            map.put("appid", appid);//改成自己的appid
            map.put("secret", appsecret);

            String rt = UrlUtil.sendPost("https://api.weixin.qq.com/cgi-bin/token", map);

            System.out.println("what is:"+rt);
            JSONObject json = JSONObject.parseObject(rt);

            if (json.getString("access_token") != null || json.getString("access_token") != "") {
                return json.getString("access_token");
            } else {
                return null;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }

    /*
     * 获取 二维码图片
     *
     */
    public static String getminiqrQr(String accessToken, HttpServletRequest request,int scene) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String time=df.format(new Date());
        String smscode = ""; //设置一个验证码
        for(int i=1;i<6;i++) {
            smscode += (int) ((Math.random() * 9 + 1));
        }
        String p="/www/wwwroot/xcy1.dg.gd.cn/twoCode/code"+time+smscode;
        String codeUrl=p+"/twoCode.png";
        String twourl="/twoCode/code"+time+smscode+"/twoCode.png";
        try
        {

            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
            // conn.setReadTimeout(2000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            JSONObject paramJson = new JSONObject();
            paramJson.put("scene", scene);
            paramJson.put("path", "pages/index/index");
            paramJson.put("width", 430);
            paramJson.put("is_hyaline", true);
            paramJson.put("auto_color", true);
            /**
             * line_color生效
             * paramJson.put("auto_color", false);
             * JSONObject lineColor = new JSONObject();
             * lineColor.put("r", 0);
             * lineColor.put("g", 0);
             * lineColor.put("b", 0);
             * paramJson.put("line_color", lineColor);
             * */

            printWriter.write(paramJson.toString());
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            File d=new File(p);
            d.mkdir();
            File dir=new File(codeUrl);
            if(dir.exists()){
                System.out.println("文件已存在");
            }else {
                dir.createNewFile();
            }
            OutputStream os = new FileOutputStream(dir);
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1)
            {
                os.write(arr, 0, len);
                os.flush();
            }
            os.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return twourl;
    }
}
