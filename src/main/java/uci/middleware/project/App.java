package uci.middleware.project;

import redis.clients.jedis.Jedis;
import uci.middleware.project.dao.ClientDAO;
import uci.middleware.project.dao.Redis;
import uci.middleware.project.dao.RoomDAO;
import uci.middleware.project.dto.Room;
import uci.middleware.project.dto.RoomClient;
import uci.middleware.project.messaging.ConsumerAndProducer;

import static uci.middleware.project.utils.Constants.CONSECUTIVE_SLOW_CLIENT_COUNT;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        Jedis jedis = new Jedis("localhost", 7001);
//        String cachedResponse = jedis.get("room1");
//        //JedisCluster jedisCluster = new JedisCluster(new HostAndPort("localhost", 7000));
//        //String cachedResponse = jedisCluster.get("room1");
//        System.out.println( "room1 value : " + cachedResponse);

        redisTest();
        //kafkaTest();
    }

    private static void kafkaTest() {

        ConsumerAndProducer.runProducer("demo");
        ConsumerAndProducer.runConsumer("demo");
    }

    private static void redisTest() {
        Redis redis = new Redis("localhost", 7001);
        ClientDAO clientDAO = new ClientDAO(redis.getJedisCluster());
        RoomDAO roomDAO = new RoomDAO(redis.getJedisCluster());
//        roomDAO.createRoom("cultAriana", new Room());
//        roomDAO.addClientToRoom("cultAriana", "bhargav");
//
//        roomDAO.updateRoom("cultAriana", new Room("https://www.youtube.com/watch?v=AoAm4om0wTs", "play", 0f));
//
//        roomDAO.addClientToRoom("cultAriana", "apoorva");
//        roomDAO.getRoomInformation("cultAriana");
//        clientDAO.updateRoomClient("cultAriana", "bhargav", new RoomClient(10f, System.currentTimeMillis()));
//        roomDAO.addClientToRoom("cultAriana", "tanvi");
//        clientDAO.updateRoomClient("cultAriana", "apoorva", new RoomClient(11f, System.currentTimeMillis()));
//        clientDAO.updateRoomClient("cultAriana", "tanvi", CONSECUTIVE_SLOW_CLIENT_COUNT, "1");
//
//        System.out.println("client status in a room : " + clientDAO.getAllClientStatusInRoom("cultAriana"));
//        System.out.println("room information of a room : " + roomDAO.getRoomInformation("cultAriana"));
        redis.deleteAllKeys("{test}*");
    }
}
