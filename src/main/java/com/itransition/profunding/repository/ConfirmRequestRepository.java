package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.ConfirmRequest;
import com.itransition.profunding.model.db.ConfirmRequestPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 24.09.2017 20:02
 */
@Repository
public interface ConfirmRequestRepository extends JpaRepository<ConfirmRequest, ConfirmRequestPk> {
}
