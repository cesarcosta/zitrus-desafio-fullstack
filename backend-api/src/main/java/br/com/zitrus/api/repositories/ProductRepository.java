package br.com.zitrus.api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zitrus.api.entities.Product;
import br.com.zitrus.api.repositories.projections.ProductReportStockQuantity;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

	Optional<Product> findByCode(String code);
	
	@Query("from Product p where p.type.id = :typeId ")
	List<Product> findByProductType(UUID typeId);
	
	@Query("select p.id as productId, p.description as description, p.code as code, tp.id as productTypeId, tp.description as productType, p.quantity as quantityAvailable, sum(sp.quantity) as quantitySold "
			+ "from StockMovement sp left join sp.product p left join p.type tp where 1 = 1 and (:typeId is null or tp.id = :typeId ) group by p.id ")
	List<ProductReportStockQuantity> getProductsByTypeWithStock(@Param("typeId") UUID typeId);
}