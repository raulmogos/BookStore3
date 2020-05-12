package ro.bookstore3.web.dto;

import lombok.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class PurchaseDto extends BaseDto {
    private Long bookId;
    private Long clientId;

    public PurchaseDto(Long bookId, Long clientId) {
        this.bookId = bookId;
        this.clientId = clientId;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getClientId() {
        return clientId;
    }
}
