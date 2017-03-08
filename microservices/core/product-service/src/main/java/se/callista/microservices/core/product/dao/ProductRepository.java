package se.callista.microservices.core.product.dao;

import org.springframework.data.repository.CrudRepository;

import se.callista.microservices.core.product.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}