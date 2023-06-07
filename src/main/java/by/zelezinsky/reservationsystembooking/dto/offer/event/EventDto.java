package by.zelezinsky.reservationsystembooking.dto.offer.event;

import by.zelezinsky.reservationsystembooking.dto.user.user.UserDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static by.zelezinsky.reservationsystembooking.dto.DtoConstants.DATE_REGEXP;
import static by.zelezinsky.reservationsystembooking.dto.DtoConstants.TIME_REGEXP;

@Data
@Valid
public class EventDto {

    private UUID id;

    @NotBlank(message = "Title can not be empty")
    @Size(min = 1, max = 255, message = "Title should be between 5 and 255 symbols")
    private String title;

    @Size(min = 5, max = 255, message = "Description text should be between 5 and 255 symbols")
    private String description;

    @NotNull(message = "Start date can not be empty")
    @DateTimeFormat(pattern = DATE_REGEXP)
    private LocalDate startDate;

    @DateTimeFormat(pattern = DATE_REGEXP)
    private LocalDate endDate;

    @NotNull(message = "End date flag can not be empty")
    private Boolean hasEndDate;

    @DateTimeFormat(pattern = TIME_REGEXP)
    private LocalTime time;

    @NotNull(message = "Time flag can not be null")
    private Boolean hasTime;

    private UUID contactId;

    private UserDto contact;
}
