package co.edu.usbcali.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.cart.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
