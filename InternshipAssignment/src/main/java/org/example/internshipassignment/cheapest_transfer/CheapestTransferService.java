package org.example.internshipassignment.cheapest_transfer;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.internshipassignment.api.InputDto;
import org.example.internshipassignment.api.OutputDto;
import org.example.internshipassignment.api.TransferDto;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class CheapestTransferService {

    public OutputDto getCheapestTransferRoute(InputDto inputDto) {
        // Extract inputs
        List<TransferDto> transfers = inputDto.getAvailableTransfers();
        long maxWeight = inputDto.getMaxWeight();
        log.info("Calculating cheapest transfer route for {} transfers with max weight {}", transfers.size(), maxWeight);
        log.info("Transfers: {}", transfers);

        int n = transfers.size();
        int[][] dp = new int[n + 1][(int) maxWeight + 1]; // DP table for maximum cost

        // Fill DP table
        for (int i = 1; i <= n; i++) {
            TransferDto currentTransfer = transfers.get(i - 1);
            for (int w = 0; w <= maxWeight; w++) {
                if (currentTransfer.getWeight() <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][(int) (w - currentTransfer.getWeight())] + currentTransfer.getCost());
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtrack to find the selected transfers
        List<TransferDto> selectedTransfers = new ArrayList<>();
        int remainingWeight = (int) maxWeight;
        for (int i = n; i > 0; i--) {
            if (dp[i][remainingWeight] != dp[i - 1][remainingWeight]) {
                TransferDto selectedTransfer = transfers.get(i - 1);
                selectedTransfers.add(selectedTransfer);
                remainingWeight -= selectedTransfer.getWeight();
            }
        }

        // Calculate total cost and total weight of selected transfers
        int totalCost = dp[n][(int) maxWeight];
        long totalWeight = selectedTransfers.stream().mapToLong(TransferDto::getWeight).sum();

        log.info("Selected Transfers: {}", selectedTransfers);
        log.info("Total Cost: {}", totalCost);
        log.info("Total Weight: {}", totalWeight);

        // Return the result in OutputDto
        return new OutputDto(selectedTransfers, totalCost, totalWeight);
    }
}

