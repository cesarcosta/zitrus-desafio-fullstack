package br.com.zitrus.api.services;

import static br.com.zitrus.api.util.IsNullUtil.isNullOrEmpty;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zitrus.api.entities.Product;
import br.com.zitrus.api.entities.StockMovement;
import br.com.zitrus.api.enums.MovementType;
import br.com.zitrus.api.exceptions.BusinessException;
import br.com.zitrus.api.repositories.ProductRepository;
import br.com.zitrus.api.repositories.StockMovementRepository;

/**
 * @author César Rangel - cesarrangelfonseca@gmail.com
 * @since 17/09/2022
 */
@Service
public class StockMovementService {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private StockMovementRepository stockMovementRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public StockMovement create(StockMovement movement) {
		validate(movement);
		
		stockMovementRepository.save(movement);
		
		productService.updateStock(movement.getProduct().getId(), movement.getMovementType(), movement.getQuantity());
		
		return movement;
	}
	
	private void validate(StockMovement movement) {
		if (!isNullOrEmpty(movement.getProduct()) && !isNullOrEmpty(movement.getProduct().getId())) {
			Optional<Product> product = productRepository.findById(movement.getProduct().getId());
			
			if (product.isEmpty()) {
				throw new BusinessException("Produto não encontrado!");
			}
			
			if (movement.getMovementType().equals(MovementType.SAIDA)) {
				if (movement.getQuantity() > product.get().getQuantity()) {
					throw new BusinessException("Não é possível realizar a movimentação! O Produto não possui a quantidade informada em estoque!");
				}
			}
		}
	}

	// post /movement --> productID / movementType / quantity / price
	
	// Caso movementType === 'ENTRADA', adicionar a quantidade na entidade Product;

	// Caso quantityProduto > quantitySaida -- realizar baixa e diminuir quantidade na entidade Product;

	// Caso movementType === 'SAIDA', É importante validar o saldo, caso não haja quantidade suficiente, deve ser retornado uma mensagem específica;
	
}