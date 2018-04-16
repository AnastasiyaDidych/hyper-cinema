package com.softserve.edu.hypercinema;

import com.softserve.edu.entity.Movie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class HyperCinemaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyperCinemaApplication.class, args);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("JavaLv304");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Movie movie = new Movie("title","description", "duration", "genre");
        em.persist(movie);

        em.getTransaction().commit();

        em.close();
        factory.close();
    }
}
