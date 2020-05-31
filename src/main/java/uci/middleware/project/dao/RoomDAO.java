package uci.middleware.project.dao;

import redis.clients.jedis.JedisCluster;
import uci.middleware.project.dto.Room;
import uci.middleware.project.exception.InvalidRequestException;

import java.util.Map;
import java.util.Set;

import static uci.middleware.project.utils.Constants.MEMBERS_SUFFIX;

public class RoomDAO {


    private JedisCluster jedisCluster;
    private String serverIdentifier;

    public RoomDAO(JedisCluster jedisCluster, String serverIdentifier){

        this.jedisCluster = jedisCluster;
        this.serverIdentifier = serverIdentifier;
    }

    public void createRoom(String roomName, Room room) {

        Map<String,String> roomEntry = jedisCluster.hgetAll(Redis.getHashTagKey(roomName));
        if (!roomEntry.isEmpty()) {

            throw new InvalidRequestException("Virtual room with name : " + roomName + " already exists");
        }
        jedisCluster.hset(Redis.getHashTagKey(roomName), room.getHashMap());
        addRoomToActiveRooms(roomName);
    }

    private void addRoomToActiveRooms(String roomName) {

        jedisCluster.sadd(serverIdentifier, roomName);
    }

    public void updateRoom(String roomName, Room room){

        jedisCluster.hset(Redis.getHashTagKey(roomName), room.getHashMap());
    }

    public void updateRoom(String roomName, String field, String value){

        jedisCluster.hset(Redis.getHashTagKey(roomName), field, value);
    }

    public void addClientToRoom(String roomName, String clientName) {

        if(jedisCluster.sismember(Redis.getHashTagKey(roomName) + MEMBERS_SUFFIX , clientName)){

            throw new InvalidRequestException("Client : " + clientName + " has already joined the room : " + roomName);
        }
        jedisCluster.sadd(Redis.getHashTagKey(roomName) + MEMBERS_SUFFIX, clientName);
    }

    public Set<String> getActiveRooms(){

        return jedisCluster.smembers(serverIdentifier);
    }

    public Room getRoomInformation(String roomName){

        return Room.roomFactory(jedisCluster.hgetAll(Redis.getHashTagKey(roomName)));
    }
}
