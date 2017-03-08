package se.callista.microservices.core.product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import se.callista.microservices.core.product.model.Product;
import se.callista.microservices.core.product.service.ProductService;



@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}

	@RequestMapping("/products/{id}")
	public Product getProduct(@PathVariable String id) {
		return productService.getProduct(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/products")
	public void addProducts(@RequestBody Product product) {
		productService.addProduct(product);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
	public void updateProducts(@RequestBody Product product, @PathVariable String id) {
		productService.updateProduct(id, product);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
	public void deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);
	}
}

