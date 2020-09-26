package co.edu.usbcali.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.cart.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

	public List<Customer> findByName(String name);

}
