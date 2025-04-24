package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest;
import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.dentist.DentistResponse;
import com.abletocode.adsdentalsystem.dto.dentist.UpdateDentistRequest;

import java.util.List;

public interface DentistService {
    Dentist createDentist(CreateDentistRequest request);
    List<Dentist> getAllDentists();
    Dentist getDentistById(Long id);
    DentistResponse updateDentist(Long id, UpdateDentistRequest request);


}
