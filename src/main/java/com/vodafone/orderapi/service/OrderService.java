package com.vodafone.orderapi.service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.vodafone.orderapi.data.Order;
import com.vodafone.orderapi.exception.InvalidInputException;
import com.vodafone.orderapi.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Value("${usersapi.endpoint}")
    private String usersEndpoint;

    private OrderRepository orderRepository;

    public OrderService(final @Autowired OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders() {
        // for the sake of simplicity, list of all orders is assumed to be small, so no pagination is implemented
        return orderRepository.findAll();
    }

    public Long createOrder(final Order order) {
        if (nonNull(order) && nonNull(order.getProductID()) && nonNull(order.getEmail())) {
            validateUser(order);
            if (!orderRepository.findByEmailAndProductID(order.getEmail(), order.getProductID()).isEmpty()) {
                throw new InvalidInputException(String.format("Order for product id: %s already exists for user: %s", order.getProductID(), order.getEmail()));
            }
            return orderRepository.save(order).getOrderID();
        } else {
            throw new InvalidInputException("Order can not be null and productID and email should be provided");
        }
    }

    private void validateUser(Order order) {
        RestTemplate usersRestTemplate = new RestTemplate();
        // For simplicity reasons, creating a value object for usersapi response is avoided,
        // we just need to check whether response contains order email or not
        ResponseEntity<String> usersResponse = usersRestTemplate.getForEntity(usersEndpoint, String.class);
        if (isNull(usersResponse) || !usersResponse.getBody().contains(String.format("\"email\":\"%s\"", order.getEmail()))) {
            throw new InvalidInputException(String.format("Invalid email: %s", order.getEmail()));
        }
    }
}
