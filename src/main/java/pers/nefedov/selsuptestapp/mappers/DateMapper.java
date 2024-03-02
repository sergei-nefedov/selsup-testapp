package pers.nefedov.selsuptestapp.mappers;

import java.util.Date;

public interface DateMapper {
    String mapDateToString(Date date);
    Date mapToDate(String dateString);
}
