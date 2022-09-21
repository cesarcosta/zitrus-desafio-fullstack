package br.com.zitrus.api.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.zitrus.api.entities.Product;
import br.com.zitrus.api.entities.StockMovement;
import br.com.zitrus.api.enums.MovementType;
import br.com.zitrus.api.repositories.ProductRepository;
import br.com.zitrus.api.repositories.StockMovementRepository;

@ExtendWith(SpringExtension.class)
public class StockMovementServiceTest {
	
	@InjectMocks
	private StockMovementService stockMovementService;

	@Mock
	private StockMovementRepository stockMovementRepository;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private ProductService productService;

	@BeforeEach
	private void setUp() {
		Product product = createProduct();

		Product productSaved = createProductSaved();

		BDDMockito.when(productRepository.save(product)).thenReturn(productSaved);

		StockMovement movement = createStockMovement();

		StockMovement movementSaved = createStockMovementSaved();

		BDDMockito.when(stockMovementRepository.save(movement)).thenReturn(movementSaved);

		BDDMockito.doNothing().when(productService).updateStock(productSaved.getId(), movement.getMovementType(),
				movement.getQuantity());
	}

	@Test
	@DisplayName("Save Product when Successfull")
	public void save_PersistProduct_whenSuccessfull() {
		StockMovement movement = createStockMovement();
		
		Product productSaved = productRepository.save(createProduct());
		
		movement.setProduct(productSaved);

		BDDMockito.when(productRepository.findById(UUID.fromString("33fb1c42-8a6b-4273-aa95-233e2bf195fd"))).thenReturn(Optional.of(productSaved));

		StockMovement movementCreated = stockMovementService.create(movement);

		Assertions.assertThat(movementCreated).isNotNull();
	}

	private StockMovement createStockMovement() {
		StockMovement movement = new StockMovement();

		movement.setDateSale(new Date());
		movement.setMovementType(MovementType.ENTRADA);
		movement.setQuantity(1d);
		movement.setTotal(1000d);

		return movement;
	}

	private StockMovement createStockMovementSaved() {
		StockMovement movement = createStockMovement();
		movement.setId(UUID.randomUUID());
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

	private Product createProductSaved() {
		Product product = createProduct();
		product.setId(UUID.fromString("33fb1c42-8a6b-4273-aa95-233e2bf195fd"));
		return product;
	}
}
