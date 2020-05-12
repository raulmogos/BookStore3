package ro.bookstore3.web.converter;

import org.springframework.stereotype.Component;
import ro.bookstore3.models.Purchase;
import ro.bookstore3.web.dto.PurchaseDto;

@Component
public class PurchaseConverter extends BaseConverter<Purchase, PurchaseDto> {
    @Override
    public Purchase convertDtoToModel(PurchaseDto dto) {
        Purchase book = new Purchase(dto.getBookId(), dto.getClientId());
        book.setId(dto.getId());
        return book;
    }

    @Override
    public PurchaseDto convertModelToDto(Purchase purchase) {
        PurchaseDto dto = new PurchaseDto(purchase.getBookId(), purchase.getClientId());
        dto.setId(purchase.getId());
        return dto;
    }
}
