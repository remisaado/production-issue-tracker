package com.remi.production_issue_tracker.service;

import com.remi.production_issue_tracker.model.ProductionLine;
import com.remi.production_issue_tracker.repository.ProductionLineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductionLineService {
    private final ProductionLineRepository productionLineRepository;

    public ProductionLineService(ProductionLineRepository productionLineRepository) { this.productionLineRepository = productionLineRepository; }

    public List<ProductionLine> getAllProductionLines() { return productionLineRepository.findAll(); }

    public ProductionLine createProductionLine(ProductionLine productionLine) { return productionLineRepository.save(productionLine); }

    public ProductionLine getProductionLineById(Long id) {
        return productionLineRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Production line not found"));
    }

    public void deleteProductionLine(Long id) { productionLineRepository.deleteById(id); }

    public ProductionLine updateProductionLine(Long id, ProductionLine updatedProductionLine) {
        ProductionLine existingProductionLine = productionLineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Production line not found"));

        existingProductionLine.setTitle(updatedProductionLine.getTitle());

        return productionLineRepository.save(existingProductionLine);
    }
}
