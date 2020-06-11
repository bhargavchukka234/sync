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

    public void updateRoomVideoStatus(String roomName, String value) {

        Room room = new Room();
        room.setVideoStatus(value);
        room.setVideoStatusUpdateTimestamp(System.currentTimeMillis());
        jedisCluster.hset(Redis.getHashTagKey(roomName), room.getHashMap());
    }

    public void updateRoomVideoUrl(String roomName, String value) {

//        Transaction transaction = jedis.multi();
        Long curentTime = System.currentTimeMillis();
        Room room = new Room();
        room.setvideoID(value);
        room.setVideoStatus("pause");
        room.setVideoStatusUpdateTimestamp(curentTime);
        room.setVideoPosition(0f);
        room.setVideoPositionUpdateTimestamp(curentTime);
        jedisCluster.hset(Redis.getHashTagKey(roomName), room.getHashMap());
//        Set<String> clients = jedisCluster.smembers(Redis.getHashTagKey(roomName) + MEMBERS_SUFFIX);
//        for(String clientName : clients){
//
//            transaction.hset(Redis.getHashTagKey(roomName) + COLON + clientName , STREAM_POSITION, String.valueOf(0));
//            transaction.hset(Redis.getHashTagKey(roomName) + COLON + clientName , POSITION_SNAPSHOT_TIME, String.valueOf(curentTime));
//        }
//        transaction.exec();
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
