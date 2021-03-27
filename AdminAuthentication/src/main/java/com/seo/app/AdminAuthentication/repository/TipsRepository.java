package com.seo.app.AdminAuthentication.repository;

import com.seo.app.AdminAuthentication.domains.TipsDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipsRepository extends CrudRepository<TipsDomain, Integer> {
    Iterable<TipsDomain> findAll();
}
