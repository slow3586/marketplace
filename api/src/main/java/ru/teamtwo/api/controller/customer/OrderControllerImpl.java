package ru.teamtwo.api.controller.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.teamtwo.api.service.api.customer.OrderService;
import ru.teamtwo.core.dtos.controller.customer.OrderController;
import ru.teamtwo.core.dtos.customer.OrderDto;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
@RequestMapping("/marketplace/api/order")
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;

    @Override
    @GetMapping("{id}")
    public ResponseEntity<OrderDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.get(id));
    }

    @Override
    @ResponseBody
    @PostMapping("")
    public ResponseEntity<Set<Long>> save(@RequestBody Set<OrderDto> dtos){
        return ResponseEntity.ok(orderService.save(dtos));
    }

    @Override
    @GetMapping("byCustomer/{customerId}")
    public ResponseEntity<Set<OrderDto>> getAllByCustomer(Long customerId) {
        return ResponseEntity.ok(orderService.getAllByCustomer(customerId));
    }
}

