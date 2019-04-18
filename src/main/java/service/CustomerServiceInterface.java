package service;

import Model.Customer;

import java.util.ArrayList;

public interface CustomerServiceInterface {
    ArrayList<Customer> findAll();
    void save(Customer customer);
    Customer findByID(int id);
    void update(int id, Customer customer);
    void remove(int id);
}
//    findAll(): Trả về danh sách tất cả khách hàng
//        save(): Lưu một khách hàng
//        findById(): Tìm một khách hàng theo Id
//        update(): Cập nhật thông tin của một khách hàng
//        remove(): Xoá một khách hàng khỏi danh sách
