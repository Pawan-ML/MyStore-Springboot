package com.ijse.database.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.database.dto.OrderDTO;
import com.ijse.database.entity.Order;
import com.ijse.database.entity.Product;
import com.ijse.database.repository.OrderRepository;
import com.ijse.database.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {

        Order order = new Order();

        List<Long> products = orderDTO.getProducts();

        Set<Product> productsSet = new HashSet<>();

        order.setTotal(0.0);

        for (Long productId : products) {
            Product product = productRepository.findById(productId).orElse(null);

            if(product != null && product.getQty() != 0) {
                productsSet.add(product);
                order.setTotal(order.getTotal() + product.getPrice());

                //Reduce the QTY of current stock
            }
        }

        Double tax = (order.getTotal()/100) * 15; //Adding 15% Tax

        order.setTax(tax);
        order.setOrderTime(LocalDateTime.now());
        order.setProducts(productsSet);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}
