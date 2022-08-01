package com.example.hotelcuzcoweb.Business.Services;

import com.example.hotelcuzcoweb.Business.Entities.Commands.CreateRoom;
import com.example.hotelcuzcoweb.Business.Entities.Room;
import com.example.hotelcuzcoweb.Business.Exceptions.InvalidRoomIdException;
import com.example.hotelcuzcoweb.DataProviders.Repositories.RoomJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;

    @Mock
    private RoomJpaRepository roomJpaRepository;

    @Test
    void givenCreateRoomThenCreateRoom() throws InvalidRoomIdException {
        // given
        CreateRoom createRoom = new CreateRoom(101, 1, "awesome room", 3, List.of());

        Room expectedRoom = new Room(101, 1, "awesome room", 3, List.of());
        when(roomJpaRepository.save(expectedRoom)).thenReturn(expectedRoom);

        // when
        Room roomObtained = roomService.create(createRoom);

        // then
        verify(roomJpaRepository).save(expectedRoom);

        assertThat(roomObtained).isEqualTo(expectedRoom);
    }

    @Test
    void givenCreateRoomWithoutDescriptionThenCreateRoomWithDefaultDescription() throws InvalidRoomIdException {
        // given
        CreateRoom createRoom = new CreateRoom(101, 1, null, 3, List.of());

        Room expectedRoom = new Room(101, 1, "My beautiful room", 3, List.of());
        when(roomJpaRepository.save(expectedRoom)).thenReturn(expectedRoom);

        // when
        Room roomObtained = roomService.create(createRoom);

        // then
        verify(roomJpaRepository).save(expectedRoom);

        assertThat(roomObtained).isEqualTo(expectedRoom);
    }
}