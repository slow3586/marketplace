package ru.teamtwo.api.controller.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.teamtwo.api.service.api.customer.CartItemService;
import ru.teamtwo.core.dtos.controller.customer.CartItemController;
import ru.teamtwo.core.dtos.customer.CartItemDto;

import java.util.Set;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/marketplace/api/cart_item")
public class CartItemControllerImpl implements CartItemController {
    private final CartItemService cartItemService;

    @Override
    @GetMapping("{id}")
    public ResponseEntity<CartItemDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(cartItemService.get(id));
    }

    @Override
    @ResponseBody
    @PostMapping("")
    public ResponseEntity<Set<Long>> save(Set<CartItemDto> dto) {
        return ResponseEntity.ok(cartItemService.save(dto));
    }

    @Override
    @ResponseBody
    @GetMapping("byCustomer/{customerId}")
    public ResponseEntity<Set<CartItemDto>> getAllByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartItemService.getAllByCustomer(customerId));
    }
}
