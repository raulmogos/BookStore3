package ro.bookstore3.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class ClientsDto {
    private Set<ClientDto> clients;

    public ClientsDto(Set<ClientDto> clients) {
        this.clients = clients;
    }

    public Set<ClientDto> getClients() {
        return clients;
    }
}
