package com.m2l.m2l.services;

import java.util.List;

import com.m2l.m2l.entities.Sale;

public interface SaleService {
    List<Sale> findAllByMonth(Integer month, Integer year);
    List<Object> findAllByYear(Integer year);
    Sale save(Sale sale);
}
