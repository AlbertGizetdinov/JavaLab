package ru.itis.app;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itis.models.Customer;
import ru.itis.models.Purchase;
import ru.itis.repositories.PurchaseRepositoryJpaImpl;

import javax.persistence.EntityManager;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate\\hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        EntityManager entityManager = sessionFactory.createEntityManager();
        PurchaseRepositoryJpaImpl purchaseRepositoryJpa = new PurchaseRepositoryJpaImpl(entityManager);
//        System.out.println(purchaseRepositoryJpa.findAllByProduct_name("Adrenaline Rush"));

        purchaseRepositoryJpa.save(Purchase.builder()
                .name("Покупка 5")
                .price(555.55F)
                .customer(entityManager.find(Customer.class, 1L))
                .build());
    }
}
