package summer.camp.inventory_service.dtos;

public record ProductRequestDTO(
        String id,
        String name,
        double price,
        int quantity,
        Long categoryId
) {}
