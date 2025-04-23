package com.abletocode.adsdentalsystem.mapper;

import com.abletocode.adsdentalsystem.domain.Surgery;
import com.abletocode.adsdentalsystem.dto.surgery.CreateSurgeryRequest;

public class SurgeryMapper {
    public static Surgery toEntity(CreateSurgeryRequest dto) {
        Surgery s = new Surgery();
        s.setName(dto.getName());
        s.setAddress(dto.getAddress());
        s.setPhone(dto.getPhone());
        return s;
    }
}
