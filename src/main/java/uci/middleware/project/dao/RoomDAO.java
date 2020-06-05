package uci.middleware.project.dao;

import redis.clients.jedis.JedisCluster;
import uci.middleware.project.dto.Room;
import uci.middleware.project.exception.InvalidRequestException;

import java.util.Map;

import static uci.middleware.project.utils.Constants.MEMBERS_SUFFIX;

public class RoomDAO {

    private JedisCluster jedisCluster;

    public RoomDAO(JedisCluster jedisCluster) {

        this.jedisCluster = jedisCluster;
    }

    public Room createRoom(String roomName, Room room) {

        Map<String, String> roomEntry = jedisCluster.hgetAll(Redis.getHashTagKey(roomName));
        if (roomEntry.isEmpty()) {

            jedisCluster.hset(Redis.getHashTagKey(roomName), room.getHashMap());
            return room;
        } else
            return Room.roomFactory(roomEntry);
    }

    public void updateRoom(String roomName, Room room) {

        jedisCluster.hset(Redis.getHashTagKey(roomName), room.getHashMap());
    }

    public void updateRoom(String roomName, String field, String value) {

        jedisCluster.hset(Redis.getHashTagKey(roomName), field, value);
    }

    public void addClientToRoom(String roomName, String clientName) {

        if (jedisCluster.sismember(Redis.getHashTagKey(roomName) + MEMBERS_SUFFIX, clientName)) {

            throw new InvalidRequestException("Client : " + clientName + " has already joined the room : " + roomName);
        }
        jedisCluster.sadd(Redis.getHashTagKey(roomName) + MEMBERS_SUFFIX, clientName);
    }

    public Room getRoomInformation(String roomName) {

        return Room.roomFactory(jedisCluster.hgetAll(Redis.getHashTagKey(roomName)));
    }
}
