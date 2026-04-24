package com.m2l.m2l.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.m2l.m2l.entities.Sale;
import com.m2l.m2l.services.SaleService;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/sale")
@AllArgsConstructor
public class SaleController {
    private SaleService saleService;

    @GetMapping("/{month}/{year}")
    public ResponseEntity<List<Sale>> findAllByMonth(@PathVariable Integer month, @PathVariable Integer year) {
        List<Sale> allSales = saleService.findAllByMonth(month, year);
        return new ResponseEntity<List<Sale>>(allSales, HttpStatus.OK);
    }

    @GetMapping("/{year}")
    public ResponseEntity<List<Object>> findAllByYear(@PathVariable Integer year) {
        List<Object> allSales = saleService.findAllByYear(year);
        return new ResponseEntity<List<Object>>(allSales, HttpStatus.OK);
    }
}
