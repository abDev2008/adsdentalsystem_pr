package com.abletocode.adsdentalsystem.mapper;

import com.abletocode.adsdentalsystem.domain.Availability;
import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.availability.AvailabilityResponse;
import com.abletocode.adsdentalsystem.dto.availability.CreateAvailabilityRequest;
import org.springframework.stereotype.Component;



@Component
public class AvailabilityMapper {

    public Availability toEntity(CreateAvailabilityRequest dto, Dentist dentist) {
        Availability availability = new Availability();
        availability.setDentist(dentist);
        availability.setStartTime(dto.getStartTime().toLocalTime());
        availability.setEndTime(dto.getEndTime().toLocalTime());
        availability.setDayOfWeek(dto.getEndTime().getDayOfWeek());
        return availability;
    }

//    public AvailabilityResponse toResponse(Availability entity) {
//        AvailabilityResponse response = new AvailabilityResponse();
//        response.setId(entity.getId());
//        response.setDentistId(entity.getDentist().getId());
//        response.setStartTime(entity.getStartTime().atDate(java.time.LocalDate.now()));
//        response.setEndTime(entity.getEndTime().atDate(java.time.LocalDate.now()));
//        return response;
//    }

    public AvailabilityResponse toResponse(Availability entity) {
        AvailabilityResponse response = new AvailabilityResponse();
        response.setId(entity.getId());
        response.setDentistId(entity.getDentist().getId()); // ✅ Only ID, not full Dentist
        response.setStartTime(entity.getStartTime().atDate(java.time.LocalDate.now()));
        response.setEndTime(entity.getEndTime().atDate(java.time.LocalDate.now()));
        response.setDayOfWeek(entity.getDayOfWeek()); // ✅ Include dayOfWeek if you want
        return response;
    }


}
