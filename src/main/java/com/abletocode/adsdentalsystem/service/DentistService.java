package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest;
import com.abletocode.adsdentalsystem.domain.Dentist;

import java.util.List;

public interface DentistService {
    Dentist createDentist(CreateDentistRequest request);
    List<Dentist> getAllDentists();
    Dentist getDentistById(Long id);

}
