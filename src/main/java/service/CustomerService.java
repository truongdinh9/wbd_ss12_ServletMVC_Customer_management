package service;

import Model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerService implements CustomerServiceInterface {
    private static Map<Integer, Customer> customerMap;
    static {
        customerMap = new HashMap<>();
        customerMap.put(1,new Customer(1,"Chung","Chung@gmail.com","Hanoi"));
        customerMap.put(2, new Customer(2, "Bill", "bill@codegym.vn", "Danang"));
        customerMap.put(9, new Customer(9, "Alex", "alex@codegym.vn", "Saigon"));
        customerMap.put(4, new Customer(4, "Adam", "adam@codegym.vn", "Beijin"));
        customerMap.put(5, new Customer(5, "Sophia", "sophia@codegym.vn", "Miami"));
        customerMap.put(6, new Customer(6, "Rose", "rose@codegym.vn", "Newyork"));


    }
    @Override
    public ArrayList<Customer> findAll() {
        return new ArrayList<>(customerMap.values());

    }

    @Override
    public void save(Customer customer) {
        customerMap.put(customer.getId(),customer);
    }

    @Override
    public Customer findByID(int id) {
        return customerMap.get(id);
    }

    @Override
    public void update(int id, Customer customer) {
        customerMap.replace(id,customer);
    }

    @Override
    public void remove(int id) {
        customerMap.remove(id);
    }
}
