package ru.itis.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.itis.models.Customer;
import ru.itis.models.Product;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate\\hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Product product = session.find(Product.class, 1L);
        System.out.println(product.toString());

        Query<Customer> studentQuery = session.createQuery(
                "select distinct customer from Customer customer left join customer.purchases as purchase " +
                        "where customer.purchases.size > 1", Customer.class);

        List<Customer> list = studentQuery.getResultList();
        System.out.println(list);

        sessionFactory.close();
    }
}
