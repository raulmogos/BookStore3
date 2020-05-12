package ro.bookstore3.services.purchase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bookstore3.models.Purchase;
import ro.bookstore3.models.validation.PurchaseValidator;
import ro.bookstore3.repositories.PurchaseRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PurchaseMainService implements PurchaseService {

    public static final Logger log = LoggerFactory.getLogger(PurchaseMainService.class);

    @Autowired
    private PurchaseRepository purchaseRepository;
    private PurchaseValidator purchaseValidator = new PurchaseValidator();

    @Override
    public Purchase getPurchaseById(Long id) {
        log.trace("getPurchaseById - method entered: id={}", id);
        return purchaseRepository.getOne(id);
    }

    @Override
    public Purchase addPurchase(Purchase purchase) {
        log.trace("addPurchase - method entered: purchase={}", purchase);
        purchaseValidator.validate(purchase);
        log.trace("addPurchase - purchase validated: purchase={}", purchase);
        Purchase returnPurchase = purchaseRepository.save(purchase);
        log.trace("addPurchase - method finished");
        return returnPurchase;
    }

    @Override
    public void removePurchase(Long id) {
        log.trace("removePurchase - method entered: id={}", id);
        purchaseRepository.deleteById(id);
        log.trace("removePurchase - method finished");
    }

    @Override
    public Purchase updatePurchase(Purchase newPurchase) {
        AtomicReference<Purchase> purchase = new AtomicReference<>();
        log.trace("updatePurchase - method entered: newPurchase={}", newPurchase);
        purchaseValidator.validate(newPurchase);
        log.trace("updatePurchase - newPurchase validated: newPurchase={}", newPurchase);
        purchaseRepository.findById(newPurchase.getId()).ifPresent(oldPurchase -> {
            oldPurchase.setBookId(newPurchase.getBookId());
            oldPurchase.setClientId(newPurchase.getClientId());
            purchase.set(purchaseRepository.save(oldPurchase));
            log.debug("updatePurchase - updated: oldPurchase={}", oldPurchase);
        });
        log.trace("updatePurchase - method finished");
        return purchase.get();
    }

    @Override
    public List<Purchase> getAllPurchases() {
        log.trace("getAllPurchases - method entered");
        return purchaseRepository.findAll();
    }
}
