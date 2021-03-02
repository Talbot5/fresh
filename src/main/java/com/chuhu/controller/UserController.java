package com.chuhu.controller;

import com.chuhu.pojo.Code;
import com.chuhu.pojo.Coupon;
import com.chuhu.pojo.User;
import com.chuhu.service.UserService;
import com.chuhu.utils.TwoCode;
import com.chuhu.utils.WxQrCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private String appid = "wx33e927e7942f296d";
    private String appsecret = "3196daa1edc43f967b2fb81d9318419a";
    private String openid;
    private String session_key;


    /**
     *通过code获取openid
     */
    @RequestMapping("/userCode")
    @ResponseBody
    public String userCode(String code) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+appsecret+"&js_code="+code+"&grant_type=authorization_code";
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 解析json
        JSONObject jsonObject = (JSONObject) JSONObject.parse(resultString);
        session_key = jsonObject.get("session_key")+"";
        openid = jsonObject.get("openid")+"";

//        System.out.println("session_key=="+session_key);
//        System.out.println("openid=="+openid);
        return resultString;
    }

    /**
     * 通过openid去数据库查询用户
     */
    @RequestMapping("judgeUser")
    @ResponseBody
    public User judgeUser(String openid){
        User user=userService.getjudgeUser(openid);
        System.out.println(user);
        if(user==null||user.equals("")){
            return user;
        }else {
            System.out.println(user.getMemberid());
            if(user.getMemberid()>0){
                User memUser= userService.getselUserMember(user.getUid());
                return memUser;
            }else {
                return user;
            }
        }


    }

    /**
     * 用户授权，判断用户是否注册,否则添加用户
     */
    @RequestMapping("userLogin")
    @ResponseBody
    public User userLogin(String nickName,String head,String openid,Integer shareid){
        int superagent;
        if(shareid==0||shareid==null||shareid.equals(" ")){
            shareid=0;
            superagent=0;
            int count=userService.getaddUser(nickName,head,openid,shareid,superagent);
            User newuser=userService.getjudgeUser(openid);
            int profitscount= userService.getselProfitsCount(newuser.getUid());
            if (profitscount>0){
                System.out.println("数据已存在");
            }else {
                userService.getaddProfitsCount(newuser.getUid());
            }
            return newuser;
        }else {
            User user=userService.getSelUser(shareid);
            if (user!=null){
                int isagent=user.getIsagent();
                if (isagent>0){
                    int agent=shareid;
                    int num=userService.getaddUser(nickName,head,openid,shareid,shareid);
                    int addnum=userService.getupProfitsCount(shareid);
                    System.out.println(addnum);
                    User newuser=userService.getjudgeUser(openid);
                    int profitscount= userService.getselProfitsCount(newuser.getUid());
                    if (profitscount>0){
                        System.out.println("数据已存在");
                    }else {
                        userService.getaddProfitsCount(newuser.getUid());
                    }
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    String time=df.format(new Date());
                    userService.getaddNews(time,shareid);
                    return newuser;
                }else {
                    superagent = user.getSuperagent();
                    int num = userService.getaddUser(nickName, head, openid, shareid, superagent);
                    System.out.println(shareid);
                    int addnum = userService.getupProfitsCount(shareid);
                    System.out.println(addnum);
                    User newuser = userService.getjudgeUser(openid);
                    int profitscount= userService.getselProfitsCount(newuser.getUid());
                    if (profitscount>0){
                        System.out.println("数据已存在");
                    }else {
                        userService.getaddProfitsCount(newuser.getUid());
                    }
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                    String time = df.format(new Date());
                    userService.getaddNews(time, shareid);
                    return newuser;
                }
            }else {
                shareid=0;
                superagent=0;
                int num=userService.getaddUser(nickName,head,openid,shareid,superagent);
                User newuser=userService.getjudgeUser(openid);
                int profitscount= userService.getselProfitsCount(newuser.getUid());
                if (profitscount>0){
                    System.out.println("数据已存在");
                }else {
                    userService.getaddProfitsCount(newuser.getUid());
                }
                return newuser;
            }
        }
    }

    @RequestMapping("selNewUser")
    @ResponseBody
    public int selNewUser(int uid){
        int isnew=userService.getselNewUser(uid);
        return isnew;
    }

    @RequestMapping("addCoupon")
    @ResponseBody
    public void addCoupon(int uid){
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String starttime=df.format(date);
        String stoptime=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+(date.getDate()+7);
        int count=userService.getaddCoupon(uid,starttime,stoptime);
        if (count>0) {
            userService.getupUser(uid);
        }
    }

    @RequestMapping("selCoupon")
    @ResponseBody
    public List<Coupon> selCoupon(int uid){
        List<Coupon> coupon= userService.getselCoupon(uid);
       return coupon;
    }

    @RequestMapping("selCouponNum")
    @ResponseBody
    public List<Coupon> selCouponNum(int uid,String num){
        List<Coupon> coupon= userService.getselCouponNum(uid,num);
        return coupon;
    }

    @RequestMapping("selOneUser")
    @ResponseBody
    public User selOneUser(int uid){
        User user=userService.getSelOneUser(uid);
        return user;
    }

    @RequestMapping("selSuperUser")
    @ResponseBody
    public List<User> selSuperUser(int superid){
        List<User> user=userService.getSelSuperUser(superid);
        return user;
    }

    @RequestMapping("selCode")
    @ResponseBody
    public Code selCode(){
        Code code=userService.getselCode();
        return code;
    }

    @RequestMapping("selPoster")
    @ResponseBody
    public Code selPoster(){
        Code code=userService.getselPoster();
        return code;
    }

    @RequestMapping("twoCode")
    @ResponseBody
    public Object twoCode(HttpServletRequest request,int uid) throws IOException {
        System.out.println(uid);
        JSONObject data = new JSONObject();
        String accessToken = TwoCode.getToken();
        System.out.println("accessToken;" + accessToken);
        String twoCodeUrl = TwoCode.getminiqrQr(accessToken, request,uid);
        data.put("twoCodeUrl", twoCodeUrl);
        return data;
    }

    @RequestMapping("addWithdrawal")
    @ResponseBody
    public void getaddWithdrawal(int uid,double withdmoney,double brforewmoney,String account, int type) throws IOException, ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String now=df.format(new Date());
        int wtime= (int) (df.parse(now).getTime()/1000);
        userService.getaddWithdrawal(uid,withdmoney,brforewmoney,account,type,wtime);
    }

}
