package servlet;

import bean.GlobalParams;
import bean.GlobalResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;
import utils.MyUtils;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author Rice
 * @create 2020/07/01 17:58
 */
public class PersonalRelationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)    {
        String params = req.getParameter("params");
        System.out.println(params);
        ObjectMapper objectMapper = new ObjectMapper();
        GlobalParams globalParams = null ;
        try {
            globalParams = objectMapper.readValue(params, GlobalParams.class);
            switch (globalParams.getAction()){
                case "makeFriend":
                    makeFriend(req,resp,globalParams);
                    break;
                case "getFriends":
                    getFriends(req,resp,globalParams);
                    break;
                case "delFriend":
                    delFriend(req,resp,globalParams);
                    break;
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void delFriend(HttpServletRequest req, HttpServletResponse resp, GlobalParams params) {
        Map map = params.getParams();
        String user = (String) map.get("user");
        String friend = (String) map.get("friend");
        Jedis jedis = RedisPool.getJedis();
        jedis.srem(userFirendKey(user),friend);
        jedis.close();
        MyUtils.resp(resp,new GlobalResponse(100,"删除好友成功"));
    }

    private void getFriends(HttpServletRequest req, HttpServletResponse resp, GlobalParams globalParams) {
        Map params = globalParams.getParams();
        String user = (String) params.get("user");
        Jedis jedis = RedisPool.getJedis();
        Set<String> friends = jedis.smembers(userFirendKey(user));
        MyUtils.resp(resp,new GlobalResponse(100,friends));
    }

    private void makeFriend(HttpServletRequest req, HttpServletResponse resp,GlobalParams params) {
        Map map = params.getParams();
        String user = (String) map.get("user");
        String friend = (String) map.get("friend");
        Jedis jedis = RedisPool.getJedis();
        jedis.sadd(userFirendKey(user),friend);
        jedis.close();
        MyUtils.resp(resp,new GlobalResponse(100,"添加好友成功"));
    }

    private String userFirendKey(String user){
        return  user+"_"+"friends";
    }



}
