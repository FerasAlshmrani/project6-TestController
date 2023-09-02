package com.example.project6;


import com.example.project6.Model.Hotel;
import com.example.project6.Model.User;
import com.example.project6.Repository.AuthRepository;
import com.example.project6.Repository.HotelRepository;
import com.example.project6.Service.HotelService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
public class HotelRepositoryTest {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    AuthRepository authRepository;

    User user;

    Hotel hotel;


    @BeforeEach
    void setUp() {
        user = new User(null,"feras","123","USER",null,null);
        hotel = new Hotel(null,"khalid","966543276587","B7",2,4,300,false,"",0,user);
    }

    @Test
    public void findHotelByUserAndId(){
        authRepository.save(user);
        hotelRepository.save(hotel);
        Hotel hotel1 = hotelRepository.findHotelByUserAndId(user,hotel.getId());

        Assertions.assertThat(hotel1.getId()).isEqualTo(hotel.getId());

    }

    @Test
    public void findHotelByApartment(){
        authRepository.save(user);
        hotelRepository.save(hotel);

        Hotel hotel1 = hotelRepository.searchByApartment(hotel.getApartment());

        Assertions.assertThat(hotel.getId()).isEqualTo(hotel1.getId());
    }

}
