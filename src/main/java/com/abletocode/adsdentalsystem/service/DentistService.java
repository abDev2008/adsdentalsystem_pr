package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest;
import com.abletocode.adsdentalsystem.dto.dentist.DentistResponse;
import com.abletocode.adsdentalsystem.dto.dentist.UpdateDentistRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DentistService {
    Dentist createDentist(CreateDentistRequest request);
    Page<DentistResponse> getAllDentists(Pageable pageable);
    Dentist getDentistById(Long id);
    DentistResponse updateDentist(Long id, UpdateDentistRequest request);
    void deleteDentist(Long id);
}
