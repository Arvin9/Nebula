package site.nebulas.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2017/2/23.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-redis.xml");
        JedisPool pool = (JedisPool) context.getBean("jedisPool");
        Jedis jedis = pool.getResource();
        System.out.println("判断某个键是否存在："+jedis.exists("username"));
        System.out.println("新增<'username','zzh'>的键值对："+jedis.set("username", "zzh"));
    }
}
