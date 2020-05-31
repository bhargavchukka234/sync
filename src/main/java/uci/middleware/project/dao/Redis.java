package uci.middleware.project.dao;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import uci.middleware.project.dto.Room;
import uci.middleware.project.dto.RoomClient;
import uci.middleware.project.exception.InvalidRequestException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static uci.middleware.project.utils.Constants.*;

public class Redis {

    private JedisCluster jedisCluster;


    public Redis(String host, int port) {

        jedisCluster = new JedisCluster(new HostAndPort(host, port));
    }

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public void deleteAllKeys(String pattern){
        Set<String> keys = jedisCluster.keys(pattern);
        for(String key : keys){

            jedisCluster.del(key);
        }
    }

    public static String getHashTagKey(String key){

        return "{" + key + "}";
    }
}
