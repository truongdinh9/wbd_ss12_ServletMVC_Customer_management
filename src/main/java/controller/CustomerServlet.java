package controller;

import Model.Customer;
import service.CustomerService;
import service.CustomerServiceInterface;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CustomerServlet extends HttpServlet {
    private CustomerServiceInterface customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                createCustomer(req, resp);
                break;
            case "edit":
                editCustomer(req,resp);
                break;
            case "delete":
                delete(req,resp);
                break;
            default:
                listCustomers(req, resp);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                showEditForm(req,resp);
                break;
            case "delete":
                showDeleteform(req,resp);
                break;
            case "view":
                showCustomerInfor(req,resp);
                break;
            default:
                listCustomers(req, resp);
                break;
        }
    }

    private void showDeleteform(HttpServletRequest req, HttpServletResponse resp) {
        int id= Integer.parseInt(req.getParameter("id"));
        Customer customer=customerService.findByID(id);
        req.setAttribute("customer",customer);
        RequestDispatcher dispatcher=req.getRequestDispatcher("customer/delete.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/create.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
//        try {
//            resp.sendRedirect("customer/create.jsp");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Customer customer = this.customerService.findByID(id);
        req.setAttribute("customer",customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/edit.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void createCustomer(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        int id = (int)(Math.random() * 10000);

        Customer customer = new Customer(id, name, email, address);
        this.customerService.save(customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/create.jsp");
        req.setAttribute("message", "New customer was created");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }  private void editCustomer(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        int id =Integer.parseInt(req.getParameter("id"));
        Customer customer =this.customerService.findByID(id);
        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);
        this.customerService.update(id,customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/edit.jsp");
        req.setAttribute("message", "Customer edited");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listCustomers(HttpServletRequest req, HttpServletResponse resp) {
        ArrayList<Customer> customers = this.customerService.findAll();
        req.setAttribute("customers", customers);

        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        int id=Integer.parseInt(req.getParameter("id"));
        customerService.remove(id);
        try {
            resp.sendRedirect("/customers");
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        req.setAttribute("message","Customer deleted");
//        RequestDispatcher dispatcher= req.getRequestDispatcher("customer/list.jsp");
//        try {
//            dispatcher.forward(req,resp);
//        } catch (ServletException | IOException e) {
//            e.printStackTrace();
//        }
    }
    private void showCustomerInfor(HttpServletRequest req, HttpServletResponse resp){
        int id=Integer.parseInt(req.getParameter("id"));
        Customer customer = this.customerService.findByID(id);
        req.setAttribute("customer",customer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("customer/view.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

