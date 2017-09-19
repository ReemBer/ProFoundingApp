package com.itransition.profunding.repository.fulltextSearch;


import com.itransition.profunding.model.db.Project;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 15.09.2017 1:59
 */
@Repository
@Transactional
public class FulltextRepository {

    private final int MAX_RESULTS = 10;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Project> fullTextSearch(String searchQuery, int offset) {
        if("".equals(searchQuery)) {
            return Collections.emptyList();
        }
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
                .forEntity(com.itransition.profunding.model.db.Project.class).get();
        Query jpaQuery = fullTextEntityManager.createFullTextQuery(createQuery(queryBuilder, searchQuery), Project.class);
        jpaQuery.setFirstResult(offset);
        jpaQuery.setMaxResults(MAX_RESULTS);
        @SuppressWarnings("unchecked")
        List<Project> result = jpaQuery.getResultList();
        return result;
    }

    private org.apache.lucene.search.Query createQuery(QueryBuilder queryBuilder, String searchQuery) {
        return queryBuilder.keyword().onFields("title",
                                                        "description",
                                                        "financialGoals.title",
                                                        "tags.tagName")
                .matching(searchQuery).createQuery();
    }
}
