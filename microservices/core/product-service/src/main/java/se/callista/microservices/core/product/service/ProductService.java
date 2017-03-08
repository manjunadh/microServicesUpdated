package se.callista.microservices.core.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;

import se.callista.microservices.core.product.dao.ProductRepository;
import se.callista.microservices.core.product.model.Product;

@Service
@Transactional
public class ProductService {
	public static final String productName = "created";
	public static final String RECIPIENT_QUEUE_NAME_SUFFIX = "recipient-";

	private HazelcastInstance hazelcastInstance;

	@Autowired
	public ProductService(HazelcastInstance hazelcastInstance) {
		this.hazelcastInstance = hazelcastInstance;
	}

	@Autowired
	private ProductRepository productRepository;

	private IQueue<Product> recipientQueue(String id) {
		return hazelcastInstance.getQueue(RECIPIENT_QUEUE_NAME_SUFFIX + id);
	}

	private IMap<Object, Object> acceptedMessageUidMap() {
		return hazelcastInstance.getMap(productName);
	}

	@Cacheable(value = "allproductscache")
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}

	@Cacheable(value = "products", key = "#id")
	public Product getProduct(String id) {
		return productRepository.findOne(id);
	}

	@CachePut(value = "products", key = "#result.id")
	public void addProduct(Product product) {
		productRepository.save(product);
	}

	@CachePut(value = "products", key = "#product.id")
	public void updateProduct(String id, Product product) {
		productRepository.save(product);
	}

	@CacheEvict(value = "products", key = "#id")
	public void deleteProduct(String id) {
		productRepository.delete(id);
	}

	@CacheEvict(value = "allproductscache", allEntries = true)
	public void evictAllProductsCache() {

	}

	@CacheEvict(cacheNames = "productcache", allEntries = true)
	public void evictProductCache() {

	}

}
