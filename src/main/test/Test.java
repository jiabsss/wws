import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author Rice
 * @create 2020/06/29 17:57
 */
public class Test {
    String s ;
    @org.junit.Test
    public  void test(){
        Jedis jedis = new Jedis("http://localhost:6379");
        System.out.println(jedis);
        //String k1 = jedis.get("k1");
        //System.out.println(k1);
        jedis.lpush("rice","test");
        List<String> lrange = jedis.lrange("rice", 0, jedis.llen("rice"));
        System.out.println(lrange);
        jedis.close();
    }

    @org.junit.Test
    public void test2(){
        System.out.println("s:"+s);
    }
}
