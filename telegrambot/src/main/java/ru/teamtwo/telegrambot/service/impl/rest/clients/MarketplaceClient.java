package ru.teamtwo.telegrambot.service.impl.rest.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.teamtwo.core.dtos.product.ProductDto;

import java.util.Set;

@FeignClient(url = "${telegrambot.rest.webClientUri}/marketplace/api/product-offers", name="productOffers")
public interface MarketplaceClient {
    @GetMapping("")
    Set<ProductDto> getProductOffersByProductName(@RequestParam(value = "filter", required = true) String filter,
                                                  @RequestParam(value = "offset", defaultValue = "0") int offset,
                                                  @RequestParam(value = "limit", defaultValue = "20") int limit,
                                                  @RequestParam(value = "order", defaultValue = "desc_price") String order);
}