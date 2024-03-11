package com.eg.swa.ntier.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eg.swa.ntier.shopping.model.Product;

import javax.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select P from Product P where upper(P.name) like concat('%',upper(:name),'%') ")
	List<Product> findByNameContainingIgnoreCase(String name);

	@Modifying
	@Transactional
	@Query(value = "update Product p set p.isDeleted = true where p.id = :productId")
	void setProductAsDeleted(Long productId);

}

