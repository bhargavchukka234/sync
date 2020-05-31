package uci.middleware.project.dao;

import redis.clients.jedis.JedisCluster;
import uci.middleware.project.dto.Client;
import uci.middleware.project.dto.RoomClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static uci.middleware.project.utils.Constants.*;

public class ClientDAO {

    private JedisCluster jedisCluster;

    public ClientDAO(JedisCluster jedisCluster){

        this.jedisCluster = jedisCluster;
    }

    public Map<String, RoomClient> getAllClientStatusInRoom(String roomName){

        Set<String> clients = jedisCluster.smembers(Redis.getHashTagKey(roomName) + MEMBERS_SUFFIX);
        Map<String , RoomClient> roomClientStatus = new HashMap<>();
        for(String client : clients){

            RoomClient roomClient = RoomClient.roomClientFactory(jedisCluster.hgetAll(Redis.getHashTagKey(roomName) + COLON + client));
            roomClientStatus.put(client, roomClient);
        }
        return roomClientStatus;
    }


    public void updateRoomClient(String roomName, String clientName, RoomClient roomClient){

        jedisCluster.hset(Redis.getHashTagKey(roomName) + COLON + clientName , roomClient.getHashMap());
    }

    public void updateRoomClient(String roomName , String clientName, String field, String value){

        jedisCluster.hset(Redis.getHashTagKey(roomName) + COLON + clientName , field, value);
    }
}
