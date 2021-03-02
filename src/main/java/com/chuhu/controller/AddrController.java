package com.chuhu.controller;


import com.alibaba.fastjson.parser.JSONToken;
import com.chuhu.pojo.Address;
import com.chuhu.service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AddrController {
    @Autowired
    private AddrService addrService;

    /**
     * 查询所有地址
     */
    @RequestMapping("selAddress")
    @ResponseBody
    public List<Address> selAddress(HttpServletResponse response, int uid) {
        // System.out.println("uid="+uid);
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        List<Address> addressList = addrService.getselAddr(uid);
//        System.out.println(addressList);
        return addressList;

    }

    /**
     * 添加地址
     */
    @RequestMapping("addAddress")
    @ResponseBody
    public void addAddress(int uid, String uname, String phone, String addr, String detailedaddr, int istrue, String uploaderurl) {
        System.out.println(uploaderurl);
        if (istrue == 1) {
            Address add = addrService.getselAddrDef(uid, istrue);
            if (add == null) {
                addrService.getaddAddress(uid, uname, phone, addr, detailedaddr, istrue, uploaderurl);
            } else {
                addrService.getupAddrDef(add.getAddrid(), 0);
                addrService.getaddAddress(uid, uname, phone, addr, detailedaddr, istrue, uploaderurl);
            }
        } else {
            addrService.getaddAddress(uid, uname, phone, addr, detailedaddr, istrue, uploaderurl);
        }

    }

    /**
     * 查询单个地址
     */
    @RequestMapping("selOneAddr")
    @ResponseBody
    public Address selOneAddr(int addrid) {
        Address address = addrService.getselOneAddr(addrid);
        return address;
    }

    /**
     * 修改地址
     */
    @RequestMapping("upAddress")
    @ResponseBody
    public void upAddress(int uid, String uname, String phone, String address, String addr, int addrid, int istrue, String uploaderurl) {
        if (istrue == 1) {
            Address add = addrService.getselAddrDef(uid, istrue);
            if (add == null || add.equals(" ")) {
                addrService.getupAddress(uid, uname, phone, address, addr, addrid, istrue, uploaderurl);
            } else {
                addrService.getupAddrDef(add.getAddrid(), 0);
                addrService.getupAddress(uid, uname, phone, address, addr, addrid, istrue, uploaderurl);
            }

        } else {
            addrService.getupAddress(uid, uname, phone, address, addr, addrid, istrue, uploaderurl);
        }
    }

    /**
     * 删除地址
     */
    @RequestMapping("delAddress")
    @ResponseBody
    public void delAddress() {
        int addrid = 1;
        addrService.getdelAddr(addrid);
    }

    /**
     * 修改默认地址
     */
    @RequestMapping("updefAddress")
    @ResponseBody
    public void updefAddress(int uid, int addrid) {
        int istrue = 1;
        Address add = addrService.getselAddrDef(uid, istrue);
        if (add == null || add.equals(" ")) {
            addrService.getupAddrDef(addrid, 1);
        } else {
            addrService.getupAddrDef(add.getAddrid(), 0);
            addrService.getupAddrDef(addrid, 1);
        }

    }


    @RequestMapping("upload")
    @ResponseBody
    public String upload(HttpServletRequest request, MultipartFile file) throws IOException {
        System.out.println("执行upload");
        request.setCharacterEncoding("UTF-8");
        String path = null;
        String type = null;
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                    String time = df.format(new Date());
                    String smscode = ""; //设置一个验证码
                    for (int i = 1; i < 6; i++) {
                        smscode += (int) ((Math.random() * 9 + 1));
                    }
                    String realPath = "/www/wwwroot/xcy1.dg.gd.cn/img/addr" + time + smscode;
                    File pfile = new File(realPath);
                    if (!pfile.exists()) {
                        //不存在时,创建文件夹
                        pfile.mkdirs();
                    }
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    String url = "https://xcy1.dg.gd.cn/img/addr" + time + smscode;
                    path = realPath + "/" + trueFileName;
                    file.transferTo(new File(path));
                    path = url + "/" + trueFileName;
                } else {
                    return "error";
                }
            }
            return path;
        } else {
            return "error";
        }
    }


}
