package org.example.internshipassignment;

import org.example.internshipassignment.api.InputDto;
import org.example.internshipassignment.api.OutputDto;
import org.example.internshipassignment.api.TransferDto;
import org.example.internshipassignment.cheapest_transfer.CheapestTransferService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InternshipAssignmentApplicationTests {

    private final CheapestTransferService service = new CheapestTransferService();

    @Test
    void testSimpleInput() {
        InputDto inputDto = new InputDto();
        inputDto.setMaxWeight(15);
        inputDto.setAvailableTransfers(Arrays.asList(
                new TransferDto(5, 10),
                new TransferDto(10, 20),
                new TransferDto(3, 5),
                new TransferDto(8, 15)
        ));

        OutputDto result = service.getCheapestTransferRoute(inputDto);

        assertNotNull(result);
        assertEquals(30, result.getTotalCost());
        assertEquals(15, result.getTotalWeight());
        assertEquals(2, result.getSelectedTransfers().size()); // should return 2 selected transfers
    }

    @Test
    void testSmallInput() {
        InputDto inputDto = new InputDto();
        inputDto.setMaxWeight(5);
        inputDto.setAvailableTransfers(Arrays.asList(
                new TransferDto(1, 3),
                new TransferDto(2, 5),
                new TransferDto(3, 8)
        ));

        OutputDto result = service.getCheapestTransferRoute(inputDto);

        assertNotNull(result);
        assertEquals(13, result.getTotalCost());
        assertEquals(5, result.getTotalWeight());
        assertEquals(2, result.getSelectedTransfers().size()); // should return 2 selected transfers
    }

    @Test
    void testHeavyInput() {
        InputDto inputDto = new InputDto();
        inputDto.setMaxWeight(50);
        inputDto.setAvailableTransfers(Arrays.asList(
                new TransferDto(10, 20),
                new TransferDto(15, 30),
                new TransferDto(25, 40),
                new TransferDto(50, 100)
        ));

        OutputDto result = service.getCheapestTransferRoute(inputDto);

        assertNotNull(result);
        assertEquals(100, result.getTotalCost());
        assertEquals(50, result.getTotalWeight());
        assertEquals(1, result.getSelectedTransfers().size()); // should return 1 selected transfer
    }

    @Test
    void testSingleTransferInput() {
        InputDto inputDto = new InputDto();
        inputDto.setMaxWeight(10);
        inputDto.setAvailableTransfers(List.of(
                new TransferDto(10, 25)
        ));

        OutputDto result = service.getCheapestTransferRoute(inputDto);

        assertNotNull(result);
        assertEquals(25, result.getTotalCost());
        assertEquals(10, result.getTotalWeight());
        assertEquals(1, result.getSelectedTransfers().size()); // should return 1 selected transfer
    }

    @Test
    void testUnusableTransferInput() {
        InputDto inputDto = new InputDto();
        inputDto.setMaxWeight(30);
        inputDto.setAvailableTransfers(List.of(
                new TransferDto(50, 150)
        ));

        OutputDto result = service.getCheapestTransferRoute(inputDto);

        assertNotNull(result);
        assertEquals(0, result.getTotalCost());  // No valid transfer can be selected
        assertEquals(0, result.getTotalWeight());  // No valid transfer can be selected
        assertTrue(result.getSelectedTransfers().isEmpty()); // Should return no selected transfers
    }

    @Test
    void testMaxWeightInput() {
        InputDto inputDto = new InputDto();
        inputDto.setMaxWeight(100);
        inputDto.setAvailableTransfers(Arrays.asList(
                new TransferDto(10, 15),
                new TransferDto(20, 25),
                new TransferDto(30, 30),
                new TransferDto(40, 40),
                new TransferDto(50, 50)
        ));

        OutputDto result = service.getCheapestTransferRoute(inputDto);

        assertNotNull(result);
        assertEquals(110, result.getTotalCost());
        assertEquals(100, result.getTotalWeight());
        assertEquals(4, result.getSelectedTransfers().size()); // should return 3 selected transfers
    }

}
