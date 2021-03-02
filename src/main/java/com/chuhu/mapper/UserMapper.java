package com.chuhu.mapper;

import com.chuhu.pojo.Code;
import com.chuhu.pojo.Coupon;
import com.chuhu.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    @Select("select * from user  where openid=#{openid}")
    User judgeUser(String openid);

    @Insert("insert into user(nickname,headportrait,openid,superid,isnew,superagent,isagent) value(#{nickName},#{head},#{openid},#{shareid},1,#{superagent},0)")
    int addUser(String nickName, String head, String openid,int shareid,int superagent);

    @Select("select * from user u inner join profits_count p on u.uid=p.userid where uid=#{uid}")
    User selUser(int uid);

    @Insert("insert into coupon(title,coupon,num,starttime,stoptime,uid) value('新人红包',2,0.5,#{starttime},#{stoptime},#{uid})")
    int addCoupon(int uid,String starttime,String stoptime);

    @Update("update user set isnew=0 where uid=#{uid}")
    void upUser(int uid);

    @Select("select * from coupon where uid=#{uid}")
    List<Coupon> selCoupon(int uid);

    @Select("select * from coupon where uid=#{uid} and #{num}>num")
    List<Coupon> selCouponNum(int uid, String num);

    @Insert("insert into news(title,content,time,status,uid) value('邀请好友','您通过邀请一位好友获得佣金0.5$,邀请更多的好友还可以获得更多的佣金哦！！！',#{time},0,#{shareid})")
    void addNews(String time,Integer shareid);

    @Select("select * from user u inner join profits_count p on u.uid=p.userid where uid=#{uid}")
    User selOneUser(int uid);

    @Select("select * from user where superid=#{superid}")
    List<User> selSuperUser(int superid);

    @Select("select * from code where type=1 and display=1")
    Code selCode();

    @Select("select * from code where type=2 and display=1")
    Code selPoster();

    @Select("select count(*) from user where uid=#{uid} and isnew=1")
    int selNewUser(int uid);

    @Select("select count(*) from profits_count where userid=#{uid}")
    int selProfitsCount(Integer uid);

    @Insert("insert into profits_count(userid) value(#{uid})")
    void addProfitsCount(Integer uid);

    @Update("update profits_count set pmoney=pmoney+0.5,pbalance=pbalance+0.5 where userid=#{shareid}")
    int upProfitsCount(Integer shareid);

    @Insert("insert into withdrawal(userid,withdmoney,brforewmoney,account,type,status,wtime) value(#{uid},#{withdmoney},#{brforewmoney},#{account},#{type},1,#{wtime})")
    void addWithdrawal(int uid, double withdmoney, double brforewmoney, String account, int type, int wtime);

    @Select("select * from user u inner join member m on u.memberid=m.mid where uid=#{uid}")
    User selUserMember(Integer uid);
}
