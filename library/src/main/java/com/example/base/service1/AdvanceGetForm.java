package com.example.base.service1;

import com.example.base.common.annotation.EndDate;
import com.example.base.common.annotation.EnumFromId;
import com.example.base.common.annotation.StartDate;
import com.example.base.common.annotation.StartDateBeforeEndDate;
import com.example.base.common.enummeration.SimpleEnum;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by khangld5 on Apr 08, 2021
 */
@Value
@StartDateBeforeEndDate
public class AdvanceGetForm {
    @NotNull
    @StartDate
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date fromDate;
    @NotNull
    @EndDate
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date toDate;
    @NotNull
    @EnumFromId
    SimpleEnum simpleEnum;
}
