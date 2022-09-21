package br.com.zitrus.api.services;

import java.util.Collections;
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

import br.com.zitrus.api.entities.ProductType;
import br.com.zitrus.api.exceptions.EntityNotFoundException;
import br.com.zitrus.api.repositories.ProductRepository;
import br.com.zitrus.api.repositories.ProductTypeRepository;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 21/09/2022
 */
@ExtendWith(SpringExtension.class)
public class ProductTypeServiceTest {
	
	@InjectMocks
	private ProductTypeService productTypeService;

	@Mock
	private ProductTypeRepository productTypeRepository;

	@Mock
	private ProductRepository productRepository;

	@BeforeEach
	private void setUp() {
		ProductType productType = createProductType();

		ProductType productTypeSaved = createProductTypeSaved();

		List<ProductType> types = List.of(productTypeSaved);

		BDDMockito.when(productTypeRepository.save(productType)).thenReturn(productTypeSaved);

		BDDMockito.when(productTypeRepository.findAll()).thenReturn(types);

		BDDMockito.when(productTypeRepository.findByDescriptionContainingIgnoreCase(productType.getDescription())).thenReturn(
				types);
			
		BDDMockito.doNothing().when(productTypeRepository).delete(productTypeSaved);
	}

	@Test
	@DisplayName("Save ProductType when Successfull")
	public void save_PersistProductType_whenSuccessfull() {
		ProductType productType = createProductType();
		ProductType productTypeSaved = productTypeService.save(productType);

		Assertions.assertThat(productTypeSaved).isNotNull();
		Assertions.assertThat(productTypeSaved.getId()).isNotNull();
		Assertions.assertThat(productTypeSaved.getDescription()).isEqualTo(productType.getDescription());
	}

	@Test
	@DisplayName("Find all returns List ProductType when Successfull")
	public void findAll_ReturnListProductType_whenSuccessfull() {
		ProductType productType = createProductType();

		List<ProductType> types = productTypeService.listAll(productType.getDescription());

		Assertions.assertThat(types).isNotEmpty();
	}

	@Test
	@DisplayName("Find by id returns Product when successfull")
	public void findById_ReturnProduct_whenSuccessfull() {
		ProductType type = createProductType();

		ProductType productTypeSaved = productTypeService.save(type);
		
		UUID id = UUID.randomUUID();

		BDDMockito.when(productTypeRepository.findById(id)).thenReturn(Optional.of(productTypeSaved));

		ProductType productTypeFound = productTypeService.findById(id);

		Assertions.assertThat(productTypeFound).isNotNull();
		Assertions.assertThat(productTypeFound.getDescription()).isEqualTo(type.getDescription());
	}

	@Test
	@DisplayName("Find by id returns EntityNotFoundException when no Product found")
	public void findById_ReturnEntityNotFoundException_whenNoProductFound() {
		Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
				.isThrownBy(() -> productTypeService.findById(UUID.randomUUID()));
	}

	@Test
	@DisplayName("Delete ProductType when Successfull")
	public void delete_RemovesProductType_whenSuccessfull() {
		ProductType type = createProductType();

		ProductType productTypeSaved = productTypeService.save(type);

		UUID id = UUID.randomUUID();

		BDDMockito.when(productTypeRepository.findById(id)).thenReturn(Optional.of(productTypeSaved));

		BDDMockito.when(productRepository.findByProductType(id)).thenReturn(Collections.emptyList());

		productTypeService.remove(id);

		Assertions.assertThat(true).isEqualTo(true);
	}

	@Test
	@DisplayName("Update ProductType when Successfull")
	public void update_UpdateProductType_whenSuccessfull() {
		ProductType type = createProductType();

		ProductType productTypeSaved = productTypeService.save(type);

		UUID id = UUID.randomUUID();

		BDDMockito.when(productTypeRepository.findById(id)).thenReturn(Optional.of(productTypeSaved));

		productTypeSaved.setDescription("ProductType updated");

		productTypeService.update(id, productTypeSaved);

		Assertions.assertThat(true).isEqualTo(true);
	}

	private ProductType createProductType() {
		ProductType type = new ProductType();
		type.setDescription("Description Test");
		return type;
	}

	private ProductType createProductTypeSaved() {
		ProductType type = createProductType();
		type.setId(UUID.randomUUID());
		return type;
	}
	/**
	 * 
	 * public ProductType save(ProductType type) {
	 * validate(type);
	 * return productTypeRepository.save(type);
	 * }
	 * 
	 * public List<ProductType> listAll(String description) {
	 * if (!isNullOrEmpty(description)) {
	 * return
	 * productTypeRepository.findByDescriptionContainingIgnoreCase(description);
	 * }
	 * return productTypeRepository.findAll();
	 * }
	 * 
	 * public ProductType update(UUID id, ProductType type) {
	 * ProductType productTypeExists = findById(id);
	 * productTypeExists.setDescription(type.getDescription());
	 * save(productTypeExists);
	 * return productTypeExists;
	 * }
	 * 
	 * public void remove(UUID id) {
	 * ProductType productType = findById(id);
	 * 
	 * List<Product> productsByType =
	 * productRepository.findByProductType(productType.getId());
	 * 
	 * if (!isNullOrEmpty(productsByType)) {
	 * throw new BusinessException("Não é possível a exclusão do Tipo! Existem
	 * produtos relacionados com ele!");
	 * }
	 * 
	 * productTypeRepository.delete(productType);
	 * }
	 * 
	 * public ProductType findById(UUID id) {
	 * return productTypeRepository.findById(id).orElseThrow(() -> new
	 * EntityNotFoundException("Tipo de Produto não encontrado!"));
	 * }
	 * 
	 */
}
