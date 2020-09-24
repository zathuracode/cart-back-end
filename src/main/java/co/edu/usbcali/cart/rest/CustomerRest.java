package co.edu.usbcali.cart.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import co.edu.usbcali.cart.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customer")
public class CustomerRest {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerMapper customerMapper;
	
	@PostMapping("/save")
	public CustomerDTO save(@RequestBody CustomerDTO customerDTO) {
		
		Customer customer=customerMapper.toCustomer(customerDTO);
		
		customer=customerRepository.save(customer);
		
		customerDTO=customerMapper.toCustomerDTO(customer);
		
		return customerDTO;
	}
	
	@PutMapping("/update")
	public CustomerDTO update(@RequestBody CustomerDTO customerDTO) {
		
		Customer customer=customerMapper.toCustomer(customerDTO);
		
		customer=customerRepository.save(customer);
		
		customerDTO=customerMapper.toCustomerDTO(customer);
		
		return customerDTO;
	}
	
	@DeleteMapping("/delete/{email}")
	public void delete(@PathVariable("email") String email) {		
		customerRepository.deleteById(email);		
	}
	
	@GetMapping("/findAll")
	public List<CustomerDTO> findAll(){
		
		List<Customer> customers=customerRepository.findAll();		
		return customerMapper.toCustomersDTOs(customers);
		
	}
	
	@GetMapping("/findById/{email}")
	public CustomerDTO findById(@PathVariable("email") String email) {
		Optional<Customer> customerOptional=customerRepository.findById(email);
		
		if(customerOptional.isPresent()==false){
			return null;
		}		
		Customer customer=customerOptional.get();
		
		return customerMapper.toCustomerDTO(customer);		
	}
	
}
