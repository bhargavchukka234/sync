package uci.middleware.project.dto;

import java.util.HashMap;
import java.util.Map;

import static uci.middleware.project.utils.Constants.*;

public class Room {

    private String videoUrl;
    private Integer videoLeastQuality;
    private String videoStatus;
    private Float videoPosition;

    public Room() {

    }

    public Room(String videoUrl, Integer videoLeastQuality, String videoStatus, Float videoPosition) {
        this.videoUrl = videoUrl;
        this.videoLeastQuality = videoLeastQuality;
        this.videoStatus = videoStatus;
        this.videoPosition = videoPosition;
    }

    public static Room roomFactory(Map<String, String> map) {

        Room room = new Room();
        room.setVideoUrl(map.get(VIDEO_URL));
        if (map.get(VIDEO_LEAST_QUALITY) != null)
            room.setVideoLeastQuality(Integer.parseInt(map.get(VIDEO_LEAST_QUALITY)));
        room.setVideoStatus(map.get(VIDEO_STATUS));
        if (map.get(VIDEO_POSITION) != null)
            room.setVideoPosition(Float.parseFloat(map.get(VIDEO_POSITION)));
        return room;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getVideoLeastQuality() {
        return videoLeastQuality;
    }

    public void setVideoLeastQuality(Integer videoLeastQuality) {
        this.videoLeastQuality = videoLeastQuality;
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

    public Map<String, String> getHashMap() {

        Map<String, String> roomRecord = new HashMap<>();
        roomRecord.put(VIDEO_URL, videoUrl == null ? "" : videoUrl);
        if (videoLeastQuality != null) roomRecord.put(VIDEO_LEAST_QUALITY, String.valueOf(videoLeastQuality));
        if (videoStatus != null) roomRecord.put(VIDEO_STATUS, videoStatus);
        if (videoPosition != null) roomRecord.put(VIDEO_POSITION, String.valueOf(videoPosition));
        return roomRecord;
    }

    @Override
    public String toString() {
        return "Room{" +
                "videoUrl='" + videoUrl + '\'' +
                ", videoLeastQuality=" + videoLeastQuality +
                ", videoStatus='" + videoStatus + '\'' +
                ", videoPosition=" + videoPosition +
                '}';
    }
}
