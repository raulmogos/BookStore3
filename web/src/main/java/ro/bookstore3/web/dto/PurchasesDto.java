package ro.bookstore3.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class PurchasesDto {
    private Set<PurchaseDto> purchases;

    public PurchasesDto(Set<PurchaseDto> purchases) {
        this.purchases = purchases;
    }

    public Set<PurchaseDto> getPurchases() {
        return purchases;
    }
}
