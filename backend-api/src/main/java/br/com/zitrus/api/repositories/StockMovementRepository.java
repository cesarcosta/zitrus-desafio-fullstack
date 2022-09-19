package br.com.zitrus.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.zitrus.api.entities.StockMovement;
import br.com.zitrus.api.repositories.projections.ProductReportSale;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, UUID> {

	@Query("select p.id as productId, p.description as description, tp.id as productTypeId, tp.description as productType, p.price as price, "
			+ "sum(sp.total) as totalSold, sum(sp.quantity) as quantitySold from StockMovement sp left join sp.product p left join p.type tp "
			+ "where 1 = 1 and lower(p.description) like %:description% group by p.id ")
	List<ProductReportSale> getMovementsByProduct(@Param("description") String description);
}