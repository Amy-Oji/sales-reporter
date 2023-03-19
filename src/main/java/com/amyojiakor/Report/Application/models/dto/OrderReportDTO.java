package com.amyojiakor.Report.Application.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReportDTO {
   private LocalDate startDate;
   private LocalDate endDate;

}
