package br.com.zitrus.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.zitrus.api.entities.StockMovement;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {

	@Query("select sum(s.quantity) from StockMovement s where s.product.id = :productId and movementType = 'SAIDA'")
	Double findQuantitySoldByProduct(UUID productId);
	
}