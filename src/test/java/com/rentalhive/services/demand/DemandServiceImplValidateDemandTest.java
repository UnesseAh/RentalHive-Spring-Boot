package com.rentalhive.services.demand;

import com.rentalhive.models.entities.Demand;
import com.rentalhive.models.entities.Equipment;
import com.rentalhive.models.entities.EquipmentDemand;
import com.rentalhive.models.enums.Status;
import com.rentalhive.repositories.DemandRepository;
import com.rentalhive.repositories.EquipmentDemandRepository;
import com.rentalhive.repositories.EquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DemandServiceImplValidateDemandTest {
    DemandRepository demandRepository;
    EquipmentDemandRepository equipmentDemandRepository;
    EquipmentRepository equipmentRepository;
    DemandService demandService;
    @BeforeEach
    void setUp() {
        demandRepository = Mockito.mock(DemandRepository.class);
        equipmentDemandRepository = Mockito.mock(EquipmentDemandRepository.class);
        equipmentRepository = Mockito.mock(EquipmentRepository.class);
        demandService = new DemandServiceImpl(demandRepository, equipmentRepository, equipmentDemandRepository);

    }
    @Test
    void shouldValidateDemandIdIsNotNull(){
        assertThrows(IllegalArgumentException.class, () -> demandService.validateDemand(null));
    }
    @Test
    void shouldValidateDemandExists(){
        Mockito.when(demandRepository.findById(1L)).thenReturn(null);
        assertThrows(RuntimeException.class, () -> demandService.validateDemand(1L));
    }
    @Test
    void shouldNotValidateDemandIfEquipmentIsNotAvailable(){
        Mockito.when(demandRepository.findById(1L)).thenReturn(Optional.of(new Demand()));
        Mockito.when(equipmentDemandRepository.getEquipmentDemandByIdAndRange(
                Status.ACCEPTED,
                1L,
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 2)
        )).thenReturn(List.of(new EquipmentDemand()));
        assertThrows(RuntimeException.class, () -> demandService.validateDemand(1L), "Demand most not be   valid");
    }
    @Test
    void shouldValidateDemandIfEquipmentsareAvailable(){
        Mockito.when(demandRepository.findById(1L)).thenReturn(Optional.of(new Demand(
                1L,
                List.of(new EquipmentDemand().builder().equipment(new Equipment().builder().id(1L).build()).build()),
                "title",
                "description",
                null,
                Status.ACCEPTED,
                null,
                null
        )));
        Mockito.when(equipmentDemandRepository.getEquipmentDemandByIdAndRange(
                Status.ACCEPTED,
                1L,
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 2)
        )).thenReturn(List.of());
        assertEquals(demandService.validateDemand(1L), true, "Demand most be  valid");
    }


}