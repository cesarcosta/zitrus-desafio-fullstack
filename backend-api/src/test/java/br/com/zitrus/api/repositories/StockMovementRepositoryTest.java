package br.com.zitrus.api.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.zitrus.api.entities.Product;
import br.com.zitrus.api.entities.StockMovement;
import br.com.zitrus.api.enums.MovementType;
import br.com.zitrus.api.repositories.projections.ProductReportSale;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 20/09/2022
 */
@DataJpaTest
@DisplayName("Tests for Product Repository")
public class StockMovementRepositoryTest {
	
	@Autowired
	private StockMovementRepository stockMovementRepository;

	@Autowired
	private ProductRepository productRepository;

	@Test
	@DisplayName("Save StockMovement when Successfull")
	public void save_PersistStockMovement_whenSuccessfull() {
		Product product = createProduct();

		Product productSaved = productRepository.save(product);

		StockMovement movement = createMovement();

		movement.setProduct(productSaved);

		StockMovement movementSaved = stockMovementRepository.save(movement);

		Assertions.assertThat(movementSaved).isNotNull();
		Assertions.assertThat(movementSaved.getId()).isNotNull();
		Assertions.assertThat(movementSaved.getProduct().getId()).isEqualTo(productSaved.getId());
	}

	@Test
	@DisplayName("Find by ProductTypeId returns a list of StockMovement when Successfull")
	public void findByProduct_ReturnsListStockMovement_whenSuccessfull() {
		String typeId = "95a4fdef-bcbe-4705-8edb-7c2c4368d500";
		
		
		Product product = createProduct();

		Product productSaved = productRepository.save(product);

		StockMovement movement = createMovement();

		movement.setProduct(productSaved);

		StockMovement movementSaved = stockMovementRepository.save(movement);

		List<ProductReportSale> movements = stockMovementRepository.getMovementsByProduct(UUID.fromString(typeId));

		Assertions.assertThat(movementSaved).isNotNull();
		Assertions.assertThat(movementSaved.getId()).isNotNull();
		Assertions.assertThat(movementSaved.getProduct().getId()).isEqualTo(productSaved.getId());
		Assertions.assertThat(movements).isNotEmpty();
	}
	
	private StockMovement createMovement() {
		StockMovement movement = new StockMovement();
		movement.setMovementType(MovementType.SAIDA);
		movement.setQuantity(1d);
		movement.setTotal(1000d);
		movement.setDateSale(new Date());
		return movement;
	}

	private Product createProduct() {
		Product product = new Product();
		product.setCode("1782364782634");
		product.setDescription("Product Test");
		product.setQuantity(1d);
		product.setPrice(100d);
		product.setType(null);
		return product;
	}
}