package org.example.internshipassignment.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutputDto {
    private List<TransferDto> selectedTransfers;
    private long totalCost;
    private long totalWeight;
}
