package com.m2l.m2l.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.m2l.m2l.dto.SaleDateNameArticle;
import com.m2l.m2l.entities.Sale;
import com.m2l.m2l.repositories.SaleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SaleServiceImpl implements SaleService{
    private SaleRepository saleRepository;
    @Override
    public List<Sale> findAllByMonth(Integer month, Integer year){
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.of(year, month,month == 2 && year % 4 == 0 && year % 100 != 0 || month == 2 && year % 400 == 0 ? 29 : month == 2 ? 28 : month % 2 == 0 ? 30 : 31);
        List<Sale> allSales = saleRepository.selectSaleFromDate2(startDate, endDate);
        return allSales;
    }
    @Override
    public List<Object> findAllByYear(Integer year){
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        List<Object> sales = saleRepository.selectSaleFromDate(startDate, endDate);        
        return sales;
    }

    @Override
    public Sale save(Sale sale){
        return saleRepository.save(sale);
    }
}
