package ru.itis.repositories;

import org.springframework.stereotype.Repository;
import ru.itis.models.Car;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CarsRepositoryJpaImpl implements CarsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findAll() {
        TypedQuery<Car> query = entityManager.createQuery("select car from Car car", Car.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void save(Car entity) {
        entityManager.persist(entity);
    }
}
