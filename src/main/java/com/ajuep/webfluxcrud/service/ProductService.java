package com.ajuep.webfluxcrud.service;

import com.ajuep.webfluxcrud.dto.ProductDto;
import com.ajuep.webfluxcrud.mapper.ProductMapper;
import com.ajuep.webfluxcrud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getProducts(){
        return productRepository.findAll().map(product -> ProductMapper.entityToDto(product));
    }

    public Mono<ProductDto> getProduct(String id){
        return productRepository.findById(id).map(product -> ProductMapper.entityToDto(product));
    }

    public Flux<ProductDto> getProductInRange(double min, double max){
        return productRepository.findByPriceBetween(Range.closed(min,max));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        System.out.println("service method called ...");
        return productDtoMono.map(productDto -> ProductMapper.dtoToEntity(productDto))
                .flatMap(product -> productRepository.insert(product))
                .map(product -> ProductMapper.entityToDto(product));
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, String id){
        return productRepository.findById(id)
                .flatMap(product -> productDtoMono.map(productDto -> ProductMapper.dtoToEntity(productDto)))
                .doOnNext(updateProduct -> updateProduct.setId(id))
                .flatMap(product -> productRepository.save(product))
                .map(product -> ProductMapper.entityToDto(product));
    }

    public Mono<Void> deleteProduct(String id){
        return productRepository.deleteById(id);
    }
}
