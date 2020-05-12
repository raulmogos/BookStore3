package ro.bookstore3.web.dto;

import lombok.*;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class ClientDto extends BaseDto {
    private String firstName;
    private String lastName;
    private double moneySpent;

    public ClientDto(String firstName, String lastName, double moneySpent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.moneySpent = moneySpent;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getMoneySpent() {
        return moneySpent;
    }
}
