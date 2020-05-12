package ro.bookstore3.web.converter;

import org.springframework.stereotype.Component;
import ro.bookstore3.models.Client;
import ro.bookstore3.web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client book = new Client(dto.getFirstName(), dto.getLastName(), dto.getMoneySpent());
        book.setId(dto.getId());
        return book;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = new ClientDto(client.getFirstName(), client.getLastName(), client.getMoneySpent());
        dto.setId(client.getId());
        return dto;
    }
}
