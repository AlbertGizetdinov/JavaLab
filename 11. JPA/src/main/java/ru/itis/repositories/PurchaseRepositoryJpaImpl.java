package ru.itis.repositories;

import ru.itis.models.Purchase;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class PurchaseRepositoryJpaImpl {
    private final EntityManager entityManager;

    public PurchaseRepositoryJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Purchase purchase) {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(purchase);
        transaction.commit();
    }

    public List<Purchase> findAllByProduct_name(String name) {
        TypedQuery<Purchase> query = entityManager.createQuery("select distinct purchase from Purchase purchase " +
                "left join purchase.products product where product.name = :name", Purchase.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}
