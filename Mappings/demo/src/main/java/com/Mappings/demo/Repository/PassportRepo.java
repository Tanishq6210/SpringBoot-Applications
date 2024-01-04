package com.Mappings.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Mappings.demo.Model.Passport;

@Repository
public interface PassportRepo extends JpaRepository<Passport, Integer> {

}
