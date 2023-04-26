package by.zelezinsky.reservationsystembooking.dto.filter;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
public class EventFilter {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DtoConstants.DATE_REGEXP)
    private LocalDate dateFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DtoConstants.DATE_REGEXP)
    private LocalDate dateTo;

    private List<UUID> contactIds;
    private Boolean hasTime;
    private Boolean hasEndDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DtoConstants.TIME_REGEXP)
    private LocalTime timeFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DtoConstants.TIME_REGEXP)
    private LocalTime timeTo;
}
