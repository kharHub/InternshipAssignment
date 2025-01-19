package org.example.internshipassignment.cheapest_transfer;


import lombok.AllArgsConstructor;
import org.example.internshipassignment.api.CheapestTransferExchange;
import org.example.internshipassignment.api.InputDto;
import org.example.internshipassignment.api.OutputDto;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CheapestTransferController implements CheapestTransferExchange {

    private final CheapestTransferService cheapestTransferService;

    @Override
    public OutputDto getCheapestTransferRoute(InputDto inputDto) {
        return cheapestTransferService.getCheapestTransferRoute(inputDto);
    }
}
