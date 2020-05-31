package uci.middleware.project.dto;

import java.util.Set;

public class ServerActiveRooms {

    private Set<String> activeRooms;

    public Set<String> getActiveRooms() {
        return activeRooms;
    }

    public void setActiveRooms(Set<String> activeRooms) {
        this.activeRooms = activeRooms;
    }

    @Override
    public String toString() {
        return "ServerActiveRooms{" +
                "activeRooms=" + activeRooms +
                '}';
    }
}
