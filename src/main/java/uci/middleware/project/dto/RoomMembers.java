package uci.middleware.project.dto;

import java.util.Set;

public class RoomMembers {

    private Set<String> clients;

    public Set<String> getClients() {
        return clients;
    }

    public void setClients(Set<String> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "RoomMembers{" +
                "clients=" + clients +
                '}';
    }
}
