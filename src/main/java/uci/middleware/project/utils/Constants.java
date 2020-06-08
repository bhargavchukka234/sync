package uci.middleware.project.utils;

public interface Constants {

    //room map keys
    public static final String VIDEO_URL = "video:url";
    public static final String VIDEO_LEAST_QUALITY = "video:leastQuality";
    public static final String VIDEO_STATUS = "video:status";
    public static final String VIDEO_POSITION = "video:position";
    public static final String VIDEO_POSITION_UPDATE_TIME = "video:positionUpdateTime";
    public static final String VIDEO_STATUS_UPDATE_TIME = "video:statusUpdateTime";
    public static final String SYNC_SERVER_ID = "syncServerId";

    //room client map keys
    public static final String STREAM_POSITION = "streamPosition";
    public static final String POSITION_SNAPSHOT_TIME = "positionSnapshotTime";
    public static final String CURRENT_QUALITY = "currentQuality";
    public static final String CONSECUTIVE_SLOW_CLIENT_COUNT = "consecutiveSlowClientCount";

    //room members key siffix
    public static final String MEMBERS_SUFFIX = ":members";

    public static final String COLON = ":";

}
