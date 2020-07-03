package servlet;

import bean.GlobalResponse;
import redis.clients.jedis.Jedis;
import utils.MyUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Rice
 * @create 2020/06/29 11:10
 */
public class LoginServlet extends HttpServlet {

    //登录
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            //获取账号密码
            String user = req.getParameter("user");
            //登录
            login(user);
            MyUtils.resp(resp,new GlobalResponse(100,"登录成功"));
        }catch (Exception e){
            e.printStackTrace();
            MyUtils.resp(resp,new GlobalResponse(e.getMessage()));
        }
    }

    private void login(String user) throws  Exception{
        String olu = "olu";
        Jedis jedis = RedisPool.getJedis();
        jedis.sadd(olu, user);
        jedis.close();
    }




}
