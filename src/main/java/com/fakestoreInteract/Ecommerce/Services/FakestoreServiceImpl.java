package com.fakestoreInteract.Ecommerce.Services;

import com.fakestoreInteract.Ecommerce.DTOs.ProductDto;
import com.fakestoreInteract.Ecommerce.DTOs.ProductRequestDto;
//import com.fakestoreInteract.Ecommerce.DTOs.ProductResponseDto;
import com.fakestoreInteract.Ecommerce.Exceptions.InvalidProductException;
import com.fakestoreInteract.Ecommerce.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FakestoreServiceImpl implements ProductServices{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

//    private ProductDto productDto;

    public FakestoreServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<Product> getAllProducts() throws InvalidProductException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto[]> productDtos = restTemplate.getForEntity("https://fakestoreapi.com/products",ProductDto[].class);
        return getListOfProductsfromProductDtoArray(productDtos);
    }

    private List<Product> getListOfProductsfromProductDtoArray(ResponseEntity<ProductDto[]> productDtos) throws InvalidProductException {
        List<Product> productList = new ArrayList<>();
        for(ProductDto productDto:productDtos.getBody()){
            productList.add(productDto.getProductFromProductDto(productDto));
        }
        return productList;
    }

    @Override
    public Product getSingleProduct(Long productId) throws InvalidProductException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",ProductDto.class,productId);
        ProductDto productDto = response.getBody();
        if(productDto==null){
            throw new InvalidProductException();
        }
        Product product = productDto.getProductFromProductDto(productDto);
        return product;
    }

    @Override
    public Product addNewProduct(ProductDto productDto) throws InvalidProductException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> response = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",productDto,ProductDto.class);
        ProductDto productDtoresp = response.getBody();
        Product newProduct = productDtoresp.getProductFromProductDto(productDtoresp);
        return newProduct;
    }

    @Override
    public Product updateProduct(Long productId, ProductDto productDto) throws InvalidProductException {
        //put
        RestTemplate restTemplate = restTemplateBuilder.build();
        // we want the updated object also but 2 network calls so dig deep can we get it in one network call?
//        restTemplate.put();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto,ProductDto.class);
        HttpMessageConverterExtractor<ProductDto> responseExtractor = new HttpMessageConverterExtractor(ProductDto.class, restTemplate.getMessageConverters());
        ProductDto responseDto = restTemplate.execute("https://fakestoreapi.com/products/" + productId, HttpMethod.PUT, requestCallback, responseExtractor);
        return responseDto.getProductFromProductDto(responseDto);
    }  

    @Override
    public Product deleteProduct(Long productId) throws InvalidProductException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<ProductDto> resp = restTemplate.exchange("https://fakestoreapi.com/products/" + productId, HttpMethod.DELETE, null, ProductDto.class);
        ProductDto deletedProductDto = resp.getBody();
        Product productFromProductDto = deletedProductDto.getProductFromProductDto(deletedProductDto);
//        if (resp.getStatusCode().is2xxSuccessful()) {
//            productFromProductDto.setDeleted(true);
//        }
        return productFromProductDto;
//        return resp.getStatusCode().is2xxSuccessful()?deletedProductDto.getProductFromProductDto(deletedProductDto,true) : deletedProductDto.getProductFromProductDto(deletedProductDto,false);
    }
}
