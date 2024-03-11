package com.eg.swa.ntier.shopping.repository;


import com.eg.swa.ntier.shopping.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {


    Optional<Role> findByName(String name);
}
