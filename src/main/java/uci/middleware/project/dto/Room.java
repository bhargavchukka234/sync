package uci.middleware.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

import static uci.middleware.project.utils.Constants.*;

public class Room {

    private String videoUrl;
    private String videoStatus;
    private Float videoPosition;
    private Long videoStatusUpdateTimestamp;
    private Long videoPositionUpdateTimestamp;

    public Room() {

    }

    public Room(String videoUrl, String videoStatus, Float videoPosition) {
        this.videoUrl = videoUrl;
        this.videoStatus = videoStatus;
        this.videoPosition = videoPosition;
    }

    public static Room roomFactory(Map<String, String> map) {

        Room room = new Room();
        room.setVideoUrl(map.get(VIDEO_URL));
        room.setVideoStatus(map.get(VIDEO_STATUS));
        if (map.get(VIDEO_POSITION) != null)
            room.setVideoPosition(Float.parseFloat(map.get(VIDEO_POSITION)));
        if (map.get(VIDEO_POSITION_UPDATE_TIME) != null) {
            room.setVideoPositionUpdateTimestamp(Long.parseLong(map.get(VIDEO_POSITION_UPDATE_TIME)));
        }
        if (map.get(VIDEO_STATUS_UPDATE_TIME) != null) {
            room.setVideoStatusUpdateTimestamp(Long.parseLong(map.get(VIDEO_STATUS_UPDATE_TIME)));
        }
        return room;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(String videoStatus) {
        this.videoStatus = videoStatus;
    }

    public Float getVideoPosition() {
        return videoPosition;
    }

    public void setVideoPosition(Float videoPosition) {
        this.videoPosition = videoPosition;
    }

    public Long getVideoPositionUpdateTimestamp() {
        return videoPositionUpdateTimestamp;
    }

    public void setVideoPositionUpdateTimestamp(Long videoPositionUpdateTimestamp) {
        this.videoPositionUpdateTimestamp = videoPositionUpdateTimestamp;
    }

    public Long getVideoStatusUpdateTimestamp() {
        return videoStatusUpdateTimestamp;
    }

    public void setVideoStatusUpdateTimestamp(Long videoStatusUpdateTimestamp) {
        this.videoStatusUpdateTimestamp = videoStatusUpdateTimestamp;
    }

    @JsonIgnore
    public Map<String, String> getHashMap() {

        Map<String, String> roomRecord = new HashMap<>();
        roomRecord.put(VIDEO_URL, videoUrl == null ? "" : videoUrl);
        if (videoStatus != null) roomRecord.put(VIDEO_STATUS, videoStatus);
        if (videoPosition != null) roomRecord.put(VIDEO_POSITION, String.valueOf(videoPosition));
        if (videoPositionUpdateTimestamp != null)
            roomRecord.put(VIDEO_POSITION_UPDATE_TIME, String.valueOf(videoPositionUpdateTimestamp));
        if (videoStatusUpdateTimestamp != null)
            roomRecord.put(VIDEO_STATUS_UPDATE_TIME, String.valueOf(videoStatusUpdateTimestamp));
        return roomRecord;
    }

    @Override
    public String toString() {
        return "Room{" +
                "videoUrl='" + videoUrl + '\'' +
                ", videoStatus='" + videoStatus + '\'' +
                ", videoPosition=" + videoPosition +
                ", videoStatusUpdateTimestamp=" + videoStatusUpdateTimestamp +
                ", videoPositionUpdateTimestamp=" + videoPositionUpdateTimestamp +
                '}';
    }
}
