package com.m2l.m2l.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleDateNameArticle{
    LocalDate add_date;
    Integer count;
}