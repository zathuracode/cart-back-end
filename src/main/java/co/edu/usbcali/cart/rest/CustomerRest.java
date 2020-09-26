package co.edu.usbcali.cart.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.cart.domain.Customer;
import co.edu.usbcali.cart.dto.CustomerDTO;
import co.edu.usbcali.cart.mapper.CustomerMapper;
import co.edu.usbcali.cart.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerRest {

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerMapper customerMapper;

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody CustomerDTO customerDTO)throws Exception {

		Customer customer = customerMapper.toCustomer(customerDTO);

		customer = customerService.save(customer);

		customerDTO = customerMapper.toCustomerDTO(customer);

		return ResponseEntity.ok().body(customerDTO);
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody CustomerDTO customerDTO)throws Exception {

		Customer customer = customerMapper.toCustomer(customerDTO);

		customer = customerService.update(customer);

		customerDTO = customerMapper.toCustomerDTO(customer);

		return ResponseEntity.ok().body(customerDTO);
	}

	@DeleteMapping("/{email}")
	public ResponseEntity<?> delete(@PathVariable("email") String email) throws Exception{
		customerService.deleteById(email);
		
		return ResponseEntity.status(HttpStatus.OK).body("");
	}

	@GetMapping()
	public ResponseEntity<?> findAll() {

		List<Customer> customers = customerService.findAll();
		return ResponseEntity.ok().body(customerMapper.toCustomersDTOs(customers));

	}

	@GetMapping("/{email}")
	public ResponseEntity<?> findById(@PathVariable("email") String email) {
		Optional<Customer> customerOptional = customerService.findById(email);

		if (customerOptional.isPresent() == false) {
			return null;
		}
		Customer customer = customerOptional.get();

		return ResponseEntity.ok().body(customerMapper.toCustomerDTO(customer));
	}

}