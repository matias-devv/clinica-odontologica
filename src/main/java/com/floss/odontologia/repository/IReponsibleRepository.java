package com.floss.odontologia.repository;

import com.floss.odontologia.model.ResponsibleAdult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReponsibleRepository extends JpaRepository<ResponsibleAdult, Long> {
}
