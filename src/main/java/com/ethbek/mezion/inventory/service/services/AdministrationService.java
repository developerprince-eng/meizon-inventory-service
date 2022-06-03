package com.ethbek.mezion.inventory.service.services;

import com.ethbek.mezion.inventory.service.models.dto.CategoryDto;
import com.ethbek.mezion.inventory.service.models.dto.ProductDto;
import com.ethbek.mezion.inventory.service.models.entites.Category;
import com.ethbek.mezion.inventory.service.models.entites.Product;
import com.ethbek.mezion.inventory.service.models.entites.ProductType;
import com.ethbek.mezion.inventory.service.models.entites.PumpType;
import com.ethbek.mezion.inventory.service.repositories.CategoryRepository;
import com.ethbek.mezion.inventory.service.repositories.ProductRepository;
import com.ethbek.mezion.inventory.service.repositories.ProductTypeRepository;
import com.ethbek.mezion.inventory.service.repositories.PumpTypeRepository;
import com.ethbek.mezion.inventory.service.utilites.IDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class AdministrationService {

    @Autowired
    private IDGenerator idGenerator;

    @Autowired
    private PumpTypeRepository pumpTypeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private static final String CATEGORY_NOT_FOUND= "Category not Found";
    private static final String CATEGORY_ALREADY_EXIST= "Category Already";
    private static final String PRODUCT_NOT_FOUND= "Product not Found";
    private static final String PRODUCT_ALREADY_EXIST= "Product already";
    private static final String PRODUCT_DELETED_SUCCESS= "Product has been successfully been deleted";

    public ResponseEntity<Object> addPumpType(String name) {
        return new ResponseEntity<>( pumpTypeRepository.save( PumpType.builder()
                .typeId(idGenerator.pumpTypeIdGenerate())
                .type(name)
                .build()), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> addNewProduct(ProductDto productDto){
        Optional<Category> optionalCategory = categoryRepository.findByName(productDto.getCategory());
        Optional<ProductType> optionalProductType = productTypeRepository.findByType(productDto.getType());
        Optional<Product> optionalProduct = productRepository.findByName(productDto.getName());
        if(!optionalProduct.isPresent()) {
            if (optionalCategory.isPresent()) {
                if (optionalProductType.isPresent()) {
                    return new ResponseEntity<>( productRepository.save( Product.builder()
                            .createdAt( new Date() )
                            .category( optionalCategory.get() )
                            .description( productDto.getDescription() )
                            .name( productDto.getName() )
                            .productSku( idGenerator.productIdGenerate() )
                            .type( optionalProductType.get() )
                            .build() ), HttpStatus.CREATED );
                }
                return new ResponseEntity<>( PRODUCT_NOT_FOUND, HttpStatus.EXPECTATION_FAILED );
            }

            return new ResponseEntity<>( CATEGORY_NOT_FOUND, HttpStatus.EXPECTATION_FAILED );
        }
        return new ResponseEntity<>(PRODUCT_ALREADY_EXIST, HttpStatus.EXPECTATION_FAILED );
    }

    public ResponseEntity<Object> addNewCategory(CategoryDto categoryDto){
        Optional<Category> optionalCategory = categoryRepository.findByName(categoryDto.getName());
        if(optionalCategory.isPresent()){
            return new ResponseEntity<>( CATEGORY_ALREADY_EXIST, HttpStatus.EXPECTATION_FAILED );
        }
        return new ResponseEntity<>( categoryRepository.save(Category.builder()
                .categoryId( idGenerator.uuidGenerate( 32) )
                .description(categoryDto.getDescription())
                .name(categoryDto.getName())
                .build()), HttpStatus.CREATED );
    }

    public ResponseEntity<Object> deleteProduct(String productSku){
        Optional<Product> optionalProduct = productRepository.findByProductSku(productSku);
        if (optionalProduct.isPresent()){
            productRepository.delete(optionalProduct.get());
            return new ResponseEntity<>(PRODUCT_DELETED_SUCCESS, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PRODUCT_NOT_FOUND, HttpStatus.EXPECTATION_FAILED);
    }
}
