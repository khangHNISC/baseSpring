package com.example.base.service1;

import com.example.base.common.annotation.StartDateBeforeEndDate;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by khangld5 on Apr 08, 2021
 */
@Value
@StartDateBeforeEndDate(startDate = "fromDate", endDate = "toDate")
public class AdvanceGetForm {
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date fromDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date toDate;
}
