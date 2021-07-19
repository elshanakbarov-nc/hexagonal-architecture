package com.example.paymentservice.adapters.balance.rest;

import com.example.paymentservice.adapters.balance.rest.dto.BalanceTransactionCreateRequest;
import com.example.paymentservice.balance.BalanceRetrieveCommandHandler;
import com.example.paymentservice.balance.command.BalanceRetrieve;
import com.example.paymentservice.adapters.balance.rest.dto.BalanceResponse;
import com.example.paymentservice.balance.command.BalanceTransactionCreate;
import com.example.paymentservice.balance.model.Balance;
import com.example.commons.commandhandler.CommandHandler;
import com.example.commons.rest.BaseController;
import com.example.commons.rest.Response;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/balances")
public class BalanceController extends BaseController {

    private final CommandHandler<BalanceRetrieve, Balance> balanceRetrieveCommandHandler;
    private final CommandHandler<BalanceTransactionCreate, Balance> balanceTransactionCreateCommandHandler;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<BalanceResponse> retrieve(@RequestParam("accountId") Long accountId){
        var balance = balanceRetrieveCommandHandler.handle(BalanceRetrieve.from(accountId));
        log.info("Balance is retrieved for account {} as {}", accountId, balance);
        return respond(BalanceResponse.fromModel(balance));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Response<BalanceResponse> addBalanceTransaction(@Valid @RequestBody BalanceTransactionCreateRequest balanceTransactionCreateRequest) {
        var balance = balanceTransactionCreateCommandHandler.handle(balanceTransactionCreateRequest.toCommand());
        log.info("Balance transaction is created as {} and balance became {}", balanceTransactionCreateRequest, balance);
        return respond(BalanceResponse.fromModel(balance));
    }



}

