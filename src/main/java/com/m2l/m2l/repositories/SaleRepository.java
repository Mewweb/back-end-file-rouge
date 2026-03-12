package com.m2l.m2l.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.m2l.m2l.dto.SaleDateNameArticle;
import com.m2l.m2l.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale,Integer>{
    @Query("""
            SELECT DISTINCT s FROM Sale s WHERE :startDate <= s.addDate AND :endDate >= s.addDate
            """)
    List<Sale> selectSaleFromDate2(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);   

   @Query("""
            SELECT DISTINCT s.addDate, COUNT(s.article) FROM Sale s WHERE :startDate <= s.addDate AND :endDate >= s.addDate GROUP BY MONTH(s.addDate)
            """)
    List<Object> selectSaleFromDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);   
   
    @Query("""
            SELECT DISTINCT s.addDate, COUNT(s.article) FROM Sale s WHERE :startDate <= s.addDate AND :endDate >= s.addDate GROUP BY MONTH(s.addDate)
            """)
    List<SaleDateNameArticle> test(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);   


}
