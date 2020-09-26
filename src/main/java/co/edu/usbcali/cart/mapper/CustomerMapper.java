package co.edu.usbcali.cart.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.cart.domain.Customer;
import co.edu.usbcali.cart.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {

	public CustomerDTO toCustomerDTO(Customer customer);

	public Customer toCustomer(CustomerDTO customerDTO);

	public List<CustomerDTO> toCustomersDTOs(List<Customer> customers);

	public List<Customer> toCustomers(List<CustomerDTO> customerDTOs);

}