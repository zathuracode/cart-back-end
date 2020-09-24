package co.edu.usbcali.cart.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
* @author Zathura Code Generator http://zathuracode.org/
* www.zathuracode.org
*
*/
@Entity
@Table(name = "customer", schema = "public")
public class Customer implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    private String email;
    
    private String address;
   
    private String enable;
    
    private String name;
   
    private String phone;
   
    private String token;
    private List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>(0);

    public Customer() {
    }

    public Customer(String email, String address, String enable, String name,
        String phone, List<ShoppingCart> shoppingCarts, String token) {
        this.email = email;
        this.address = address;
        this.enable = enable;
        this.name = name;
        this.phone = phone;
        this.token = token;
        this.shoppingCarts = shoppingCarts;
    }

    @Id
    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "enable", nullable = false)
    public String getEnable() {
        return this.enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "phone", nullable = false)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "token", nullable = false)
    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public List<ShoppingCart> getShoppingCarts() {
        return this.shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}
