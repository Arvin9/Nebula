package site.nebulas.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/2/23.
 */
public class RedisTest {
    private static final String ipAddr = "127.0.0.1";
    private static final int port = 6379;
    private static Jedis jedis = null;
    public static void main(String[] args) throws InterruptedException {
        jedis = JedisUtil.getInstance().getJedis(ipAddr, port);
        //System.out.println("清空数据："+jedis.flushDB());
        System.out.println("判断某个键是否存在："+jedis.exists("username"));
        System.out.println("新增<'username','zzh'>的键值对："+jedis.set("username", "zzh"));
        System.out.println(jedis.exists("name"));
        System.out.println("新增<'password','password'>的键值对："+jedis.set("password", "password"));
        System.out.print("系统中所有的键如下：");
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        System.out.println("删除键password:"+jedis.del("password"));
        System.out.println("判断键password是否存在："+jedis.exists("password"));
        System.out.println("设置键username的过期时间为5s:"+jedis.expire("username", 5));
        Thread.sleep(2000);
        System.out.println("查看键username的剩余生存时间："+jedis.ttl("username"));
        System.out.println("移除键username的生存时间："+jedis.persist("username"));
        System.out.println("查看键username的剩余生存时间："+jedis.ttl("username"));
        System.out.println("查看键username所存储的值的类型："+jedis.type("username"));
        JedisUtil.getInstance().closeJedis(jedis,ipAddr, port);
    }
}
