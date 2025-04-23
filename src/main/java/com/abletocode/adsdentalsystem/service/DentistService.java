package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest;
import com.abletocode.adsdentalsystem.domain.Dentist;

public interface DentistService {
    Dentist createDentist(CreateDentistRequest request);
}
