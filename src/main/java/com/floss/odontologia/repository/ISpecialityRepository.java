package com.floss.odontologia.repository;

import com.floss.odontologia.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialityRepository extends JpaRepository<Speciality, Long> {
}
