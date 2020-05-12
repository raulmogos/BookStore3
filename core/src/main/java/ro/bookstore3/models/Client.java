package ro.bookstore3.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class Client extends BaseEntity<Long> {

    private String firstName;
    private String lastName;
    private double moneySpent;

    public Client() {
    }

    public Client(String firstName, String lastName, double moneySpent) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.moneySpent = moneySpent;
    }

    public Client(Long id, String firstName, String lastName, double moneySpent) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.moneySpent = moneySpent;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(double moneySpent) {
        this.moneySpent = moneySpent;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", moneySpent=" + moneySpent +
                ", id=" + id +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "Client - ID: " + id + ", firstName: " + firstName + ", lastName: " + lastName + ", moneySpent: " + moneySpent + ";";
//    }
}
