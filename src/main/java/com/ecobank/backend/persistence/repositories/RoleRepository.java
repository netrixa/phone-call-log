package com.ecobank.backend.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecobank.backend.persistence.domain.backend.Role;

/**
 * Created by tedonema on 29/03/2016.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
