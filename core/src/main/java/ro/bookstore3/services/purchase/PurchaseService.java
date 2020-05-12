package ro.bookstore3.services.purchase;

import ro.bookstore3.models.Purchase;

import java.util.List;

public interface PurchaseService {

    String NAME = "PurchaseService";

    Purchase getPurchaseById(Long id);

    Purchase addPurchase(Purchase purchase);

    void removePurchase(Long id);

    Purchase updatePurchase(Purchase newPurchase);

    List<Purchase> getAllPurchases();
}
