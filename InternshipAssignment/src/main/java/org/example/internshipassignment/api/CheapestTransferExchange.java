package org.example.internshipassignment.api;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/cheapest-transfer")
public interface CheapestTransferExchange {

    @GetExchange("/route")
    OutputDto getCheapestTransferRoute(@RequestBody InputDto inputDto);


}
