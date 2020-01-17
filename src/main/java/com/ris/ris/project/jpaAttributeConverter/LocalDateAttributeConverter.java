package com.ris.ris.project.jpaAttributeConverter;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.LocalDate;

public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        if(localDate != null){
            return Date.valueOf(localDate);
        }else{
            return null;
        }
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        if(sqlDate != null){
            return sqlDate.toLocalDate();
        }else{
            return null;
        }
    }
}
