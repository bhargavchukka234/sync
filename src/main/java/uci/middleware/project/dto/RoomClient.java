package uci.middleware.project.dto;

import java.util.HashMap;
import java.util.Map;

import static uci.middleware.project.utils.Constants.*;

public class RoomClient {

    private Float streamPosition;
    private Long positionSnapshotTime;
    private Integer currentQuality;
    private Integer consecutiveSlowClientCount;

    public RoomClient() {

    }

    public RoomClient(Float streamPosition, Long positionSnapshotTime, Integer currentQuality) {

        this.streamPosition = streamPosition;
        this.positionSnapshotTime = positionSnapshotTime;
        this.currentQuality = currentQuality;
    }

    public static RoomClient roomClientFactory(Map<String, String> map) {

        RoomClient roomClient = new RoomClient();
        if (map.get(STREAM_POSITION) != null)
            roomClient.setStreamPosition(Float.parseFloat(map.get(STREAM_POSITION)));
        if (map.get(POSITION_SNAPSHOT_TIME) != null)
            roomClient.setPositionSnapshotTime(Long.parseLong(map.get(POSITION_SNAPSHOT_TIME)));
        if (map.get(CURRENT_QUALITY) != null)
            roomClient.setCurrentQuality(Integer.parseInt(map.get(CURRENT_QUALITY)));
        if (map.get(CONSECUTIVE_SLOW_CLIENT_COUNT) != null)
            roomClient.setConsecutiveSlowClientCount(Integer.parseInt(map.get(CONSECUTIVE_SLOW_CLIENT_COUNT)));

        return roomClient;
    }

    public Float getStreamPosition() {
        return streamPosition;
    }

    public void setStreamPosition(Float streamPosition) {
        this.streamPosition = streamPosition;
    }

    public long getPositionSnapshotTime() {
        return positionSnapshotTime;
    }

    public void setPositionSnapshotTime(Long positionSnapshotTime) {
        this.positionSnapshotTime = positionSnapshotTime;
    }

    public Integer getCurrentQuality() {
        return currentQuality;
    }

    public void setCurrentQuality(Integer currentQuality) {
        this.currentQuality = currentQuality;
    }

    public Integer getConsecutiveSlowClientCount() {
        return consecutiveSlowClientCount;
    }

    public void setConsecutiveSlowClientCount(Integer consecutiveSlowClientCount) {
        this.consecutiveSlowClientCount = consecutiveSlowClientCount;
    }

    public Map<String, String> getHashMap() {

        Map<String, String> map = new HashMap<>();
        if (streamPosition != null) map.put(STREAM_POSITION, String.valueOf(streamPosition));
        if (positionSnapshotTime != null) map.put(POSITION_SNAPSHOT_TIME, String.valueOf(positionSnapshotTime));
        if (currentQuality != null) map.put(CURRENT_QUALITY, String.valueOf(currentQuality));
        if (consecutiveSlowClientCount != null)
            map.put(CONSECUTIVE_SLOW_CLIENT_COUNT, String.valueOf(consecutiveSlowClientCount));
        return map;
    }

    @Override
    public String toString() {
        return "RoomClient{" +
                "streamPosition=" + streamPosition +
                ", positionSnapshotTime=" + positionSnapshotTime +
                ", currentQuality=" + currentQuality +
                ", consecutiveSlowClientCount=" + consecutiveSlowClientCount +
                '}';
    }
}
