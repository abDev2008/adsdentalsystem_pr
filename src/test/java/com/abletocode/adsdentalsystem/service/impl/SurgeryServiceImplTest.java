package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Surgery;
import com.abletocode.adsdentalsystem.dto.surgery.CreateSurgeryRequest;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.mapper.SurgeryMapper;
import com.abletocode.adsdentalsystem.repository.SurgeryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SurgeryServiceImplTest {

    @Mock
    private SurgeryRepository surgeryRepository;

    @InjectMocks
    private SurgeryServiceImpl surgeryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateSurgery() {
        CreateSurgeryRequest request = new CreateSurgeryRequest("Surgery A", "Main Street", "0777777777");

        Surgery surgery = SurgeryMapper.toEntity(request);
        surgery.setId(1L);

        when(surgeryRepository.save(any(Surgery.class))).thenReturn(surgery);

        Surgery created = surgeryService.createSurgery(request);
        assertEquals("Surgery A", created.getName());
        verify(surgeryRepository).save(any(Surgery.class));
    }

    @Test
    void shouldReturnAllSurgeries() {
        Surgery s1 = new Surgery(); s1.setId(1L);
        Surgery s2 = new Surgery(); s2.setId(2L);
        when(surgeryRepository.findAll()).thenReturn(List.of(s1, s2));

        List<Surgery> result = surgeryService.getAllSurgeries();
        assertEquals(2, result.size());
        verify(surgeryRepository).findAll();
    }

    @Test
    void shouldReturnSurgeryById() {
        Surgery surgery = new Surgery(); surgery.setId(1L);
        when(surgeryRepository.findById(1L)).thenReturn(Optional.of(surgery));

        Surgery found = surgeryService.getSurgeryById(1L);
        assertEquals(1L, found.getId());
    }

    @Test
    void shouldThrowIfSurgeryNotFoundById() {
        when(surgeryRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> surgeryService.getSurgeryById(100L));
    }
}
