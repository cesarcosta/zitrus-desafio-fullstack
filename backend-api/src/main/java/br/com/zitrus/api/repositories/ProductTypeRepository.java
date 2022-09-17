package br.com.zitrus.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zitrus.api.entities.ProductType;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, UUID> {

	Optional<ProductType> findByDescription(String description);
}