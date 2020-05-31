package uci.middleware.project.messaging;

public interface KafkaConstants {
    public static String KAFKA_BROKERS = "localhost:9092,localhost:9093";
    public static Integer MESSAGE_COUNT=10;
    public static String TOPIC_NAME="demo";

    //producer properties
    public static String CLIENT_ID="client1";

    //consumer properties
    public static String GROUP_ID_CONFIG="consumerGroup1";
    public static Integer MAX_POLL_RECORDS=10;
    public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
    public static String OFFSET_RESET_LATEST="latest";
    public static String OFFSET_RESET_EARLIER="earliest";
}
