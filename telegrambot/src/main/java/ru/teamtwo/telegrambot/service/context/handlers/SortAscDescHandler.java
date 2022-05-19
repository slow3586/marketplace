package ru.teamtwo.telegrambot.service.context.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.teamtwo.core.dtos.product.ProductDto;
import ru.teamtwo.telegrambot.model.customer.CustomerState;
import ru.teamtwo.telegrambot.model.menus.TelegramBotInlineMenus;
import ru.teamtwo.telegrambot.model.sorting.SortingTypeAscDesc;
import ru.teamtwo.telegrambot.service.bot.handlers.ProductQuery;
import ru.teamtwo.telegrambot.service.bot.handlers.RESTHandler;
import ru.teamtwo.telegrambot.service.bot.handlers.SendMessageHandler;
import ru.teamtwo.telegrambot.service.context.ContextHandler;
import ru.teamtwo.telegrambot.service.context.ProcessingContext;
import ru.teamtwo.telegrambot.service.customer.CustomerStateHandler;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SortAscDescHandler implements ContextHandler {

    final CustomerStateHandler customerStateHandler;
    final SendMessageHandler sendMessageHandler;
    final RESTHandler restHandler;
    final TelegramBotInlineMenus inlineMenus;

    @Override
    public boolean shouldRun(ProcessingContext context) {
        return context.getCustomerState().getState()== CustomerState.State.WAITING_FOR_SORTING_TYPE_FIELD;
    }

    @Override
    public void execute(ProcessingContext context) {
        for(SortingTypeAscDesc type : SortingTypeAscDesc.values()){
            if(type.inputName.equals(context.getMessage())){
                context.getCustomerState().setSortingTypeAscDesc(type);
            }
        }
        if(context.getCustomerState().getSortingTypeAscDesc() == null) return;

        ProductQuery productQuery = new ProductQuery(
                context.getCustomerState().getSearchQuery(),
                context.getCustomerState().getSortingTypeField(),
                context.getCustomerState().getSortingTypeAscDesc(),
                context.getCustomerState().getOffset(),
                context.getCustomerState().getLimit()
        );

        List<ProductDto> products = restHandler.queryProducts(productQuery);

        products.forEach(productDTO -> {
            sendMessageHandler.sendMessage(context.getChatId(), productDTO.toString(), inlineMenus.createAddButton(String.valueOf(productDTO.id())));
        });

        context.getCustomerState().setState(CustomerState.State.WAITING_FOR_ADD_OR_FINISH);
    }
}