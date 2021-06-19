package com.viniciusaugusto.Meeting.Room.repository;

import com.viniciusaugusto.Meeting.Room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
