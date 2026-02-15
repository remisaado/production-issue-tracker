package com.remi.production_issue_tracker.controller;

import com.remi.production_issue_tracker.model.ProductionLine;
import com.remi.production_issue_tracker.service.ProductionLineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production-lines")
public class ProductionLineController {

    private final ProductionLineService productionLineService;

    public ProductionLineController(ProductionLineService productionLineService) { this.productionLineService = productionLineService; }

    @GetMapping
    public List<ProductionLine> getAllProductionLines() {
        return productionLineService.getAllProductionLines();
    }

    @GetMapping("/{id}")
    public ProductionLine getProductionLineById(@PathVariable Long id) {
        return productionLineService.getProductionLineById(id);
    }

    @PostMapping
    public ProductionLine createProductionLine(@RequestBody ProductionLine productionLine) {
        return productionLineService.createProductionLine(productionLine);
    }

    @PutMapping("/{id}")
    public ProductionLine updateProductionLine(@PathVariable Long id, @RequestBody ProductionLine productionLine) {
        return productionLineService.updateProductionLine(id, productionLine);
    }

    @DeleteMapping("/{id}")
    public void deleteProductionLine(@PathVariable Long id) {
        productionLineService.deleteProductionLine(id);
    }
}
