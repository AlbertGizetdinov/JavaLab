package ru.itis.repositories;

import org.springframework.stereotype.Repository;
import ru.itis.dto.AccountDto;
import ru.itis.models.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static ru.itis.dto.AccountDto.from;

@Repository
public class AccountsRepositoryJpaImpl implements AccountsRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AccountDto> searchByEmail(String email) {
        TypedQuery<Account> query = entityManager.createQuery("select account from Account account " +
                "where account.email = :email", Account.class);
        query.setParameter("email", email);
        return from(query.getResultList());
    }

    @Override
    public List<Account> findAll() {
        TypedQuery<Account> query = entityManager.createQuery("select account from Account account",
                Account.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void save(Account entity) {
        entityManager.persist(entity);
    }
}
