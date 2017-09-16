package com.itransition.profunding.repository.fulltextSearch;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 15.09.2017 1:22
 */
@Component
public class SearchIndexBuilder implements ApplicationListener<ApplicationReadyEvent> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        try {
            FullTextEntityManager fullTextEntityManager =
                    Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException ex) {
            System.out.println("An error occurred trying to build the serach index: " + ex.toString());
        }
    }
}
