package com.fakestoreInteract.Ecommerce.Services;

import com.fakestoreInteract.Ecommerce.DTOs.ProductDto;
import com.fakestoreInteract.Ecommerce.Exceptions.InvalidProductException;
import com.fakestoreInteract.Ecommerce.models.Product;
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
public class FakestoreServiceImpl implements ProductServices{

    private final String BASE_URL = "https://fakestoreapi.com/products";

    @Autowired
    private RestTemplate restTemplate;

    public FakestoreServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() throws InvalidProductException {

        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(BASE_URL, ProductDto[].class);

        if (response.getBody() == null) {
            throw new InvalidProductException("No products found");
        }

        return convertDtoArrayToProductList(response.getBody());
    }

    @Override
    public Product getSingleProduct(Long productId) throws InvalidProductException {
        ResponseEntity<ProductDto> response = restTemplate.getForEntity(BASE_URL + "/{id}", ProductDto.class, productId);

        ProductDto dto = response.getBody();
        if (dto == null) {
            throw new InvalidProductException("Product not found with ID: " + productId);
        }

        return dto.getProductFromProductDto(dto);
    }

    @Override
    public Product addNewProduct(ProductDto productDto) throws InvalidProductException {

        ResponseEntity<ProductDto> response = restTemplate.postForEntity(BASE_URL, productDto, ProductDto.class);

        ProductDto responseDto = response.getBody();
        if (responseDto == null) {
            throw new InvalidProductException("Failed to add product");
        }

        return responseDto.getProductFromProductDto(responseDto);
    }

    @Override
    public Product updateProduct(Long productId, ProductDto productDto) throws InvalidProductException {


        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, ProductDto.class);
        HttpMessageConverterExtractor<ProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(ProductDto.class, restTemplate.getMessageConverters());

        ProductDto responseDto = restTemplate.execute(BASE_URL + "/" + productId, HttpMethod.PUT, requestCallback, responseExtractor);

        if (responseDto == null) {
            throw new InvalidProductException("Failed to update product with ID: " + productId);
        }

        return responseDto.getProductFromProductDto(responseDto);
    }

    @Override
    public Product deleteProduct(Long productId) throws InvalidProductException {


        ResponseEntity<ProductDto> response = restTemplate.exchange(BASE_URL + "/" + productId, HttpMethod.DELETE, null, ProductDto.class);
        ProductDto deletedProductDto = response.getBody();

        if (deletedProductDto == null) {
            throw new InvalidProductException("Failed to delete product with ID: " + productId);
        }

        return deletedProductDto.getProductFromProductDto(deletedProductDto);
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) throws InvalidProductException {

        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(BASE_URL + "/category/{category}", ProductDto[].class, categoryName);

        ProductDto[] productDtos = response.getBody();
        if (productDtos == null || productDtos.length == 0) {
            throw new InvalidProductException("No products found in category: " + categoryName);
        }

        return convertDtoArrayToProductList(productDtos);
    }

    private List<Product> convertDtoArrayToProductList(ProductDto[] productDtos) throws InvalidProductException {
        List<Product> products = new ArrayList<>();
        for (ProductDto dto : productDtos) {
            if (dto != null) {
                products.add(dto.getProductFromProductDto(dto));
            }
        }
        return products;
    }
}
