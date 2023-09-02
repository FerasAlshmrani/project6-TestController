package com.example.project6;

import com.example.project6.Model.Hotel;
import com.example.project6.Model.User;
import com.example.project6.Repository.AuthRepository;
import com.example.project6.Repository.HotelRepository;
import com.example.project6.Service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class HotelServiceTest {

    @InjectMocks
    HotelService hotelService;

    @Mock
    HotelRepository hotelRepository;


    User user;

    Hotel hotel;

    @BeforeEach
    void setUp() {
        user = new User(null,"feras","123","USER",null,null);
        hotel = new Hotel(null,"khalid","966543276587","B7",2,4,300,false,"",0,user);
    }

    @Test
    public void deleteHotelTest(){
        when(hotelRepository.findHotelByUserAndId(user,hotel.getId())).thenReturn(hotel);

        hotelService.delete(user,hotel.getId());

        verify(hotelRepository,times(1)).findHotelByUserAndId(user,hotel.getId());
        verify(hotelRepository,times(1)).delete(hotel);
    }

    @Test
    public void searchApartmentTest(){
        when(hotelRepository.searchByApartment(hotel.getApartment())).thenReturn(hotel);

        hotelService.searchApartment(hotel.getApartment());

        verify(hotelRepository,times(2)).searchByApartment(hotel.getApartment());
    }
}
