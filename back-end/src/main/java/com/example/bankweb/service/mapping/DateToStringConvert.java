package com.example.bankweb.service.mapping;

import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
@Configuration
public class DateToStringConvert {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("tr"));

    public String dateToString(Date date){
        return sdf.format(date);
    }
    public Date stringToDate(String date) throws ParseException {
        return sdf.parse(date);
    }
}
