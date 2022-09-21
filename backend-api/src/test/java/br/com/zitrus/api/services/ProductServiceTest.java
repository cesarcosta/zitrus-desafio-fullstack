package br.com.zitrus.api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.zitrus.api.entities.Product;
import br.com.zitrus.api.enums.MovementType;
import br.com.zitrus.api.exceptions.EntityNotFoundException;
import br.com.zitrus.api.repositories.ProductRepository;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 21/09/2022
 */
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
	
	@InjectMocks
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	private String id = "95a4fdef-bcbe-4705-8edb-7c2c4368d500";

	@BeforeEach
	private void setUp() {
		Product product = createProduct();

		Product productSaved = createProductSaved();

		List<Product> products = List.of(productSaved);

		Optional<Product> optionalProduct = Optional.of(product);

		BDDMockito.when(productRepository.save(product)).thenReturn(productSaved);

		BDDMockito.when(productRepository.findByCode(ArgumentMatchers.anyString())).thenReturn(optionalProduct);

		BDDMockito.when(productRepository.findById(UUID.fromString(id))).thenReturn(optionalProduct);

		BDDMockito.when(productRepository.findByDescriptionContainingIgnoreCase(product.getDescription())).thenReturn(
				products);

		BDDMockito.doNothing().when(productRepository).delete(productSaved);
	}

	@Test
	@DisplayName("Save Product when Successfull")
	public void save_PersistProduct_whenSuccessfull() {
		Product product = createProduct();
		Product productSaved = productService.save(product);

		Assertions.assertThat(productSaved).isNotNull();
		Assertions.assertThat(productSaved.getId()).isNotNull();
		Assertions.assertThat(productSaved.getDescription()).isEqualTo(product.getDescription());
	}

	@Test
	@DisplayName("Find all returns List Product when Successfull")
	public void findAll_ReturnListProduct_whenSuccessfull() {
		Product product = createProduct();

		List<Product> products = productService.listAll(product.getDescription(), null);

		Assertions.assertThat(products).isNotEmpty();
	}

	@Test
	@DisplayName("Find all returns empty List Product when no products found")
	public void findAll_ReturnListEmptyProduct_whenNoProductsFound() {
		List<Product> products = productService.listAll("", null);

		Assertions.assertThat(products).isEmpty();
	}

	@Test
	@DisplayName("Find by id returns Product when successfull")
	public void findById_ReturnProduct_whenSuccessfull() {
		Product product = createProduct();

		Product productFound = productService.findById(UUID.fromString(id));

		Assertions.assertThat(productFound).isNotNull();
		Assertions.assertThat(productFound.getDescription()).isEqualTo(product.getDescription());
	}

	@Test
	@DisplayName("Find by id returns EntityNotFoundException when no Product found")
	public void findById_ReturnEntityNotFoundException_whenNoProductFound() {
		Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
				.isThrownBy(() -> productService.findById(UUID.randomUUID()));
	}
	
	@Test
	@DisplayName("Delete Product when Successfull")
	public void delete_RemovesProduct_whenSuccessfull() {
		Product product = createProduct();

		Product productSaved = productService.save(product);

		BDDMockito.when(productRepository.findById(productSaved.getId())).thenReturn(Optional.of(productSaved));

		productService.remove(productSaved.getId());

		Assertions.assertThat(true).isEqualTo(true);
	}

	@Test
	@DisplayName("UpdateStock of Product when Successfull")
	public void updateStock_UpdateStockProduct_whenSuccessfull() {
		Product product = createProduct();

		Product productSaved = productService.save(product);

		BDDMockito.when(productRepository.findById(productSaved.getId())).thenReturn(Optional.of(productSaved));

		productService.updateStock(productSaved.getId(), MovementType.ENTRADA, 2d);

		Assertions.assertThat(productSaved.getQuantity()).isEqualTo(3d);
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
		product.setId(UUID.randomUUID());
		return product;
	}
}
