package com.backend.Retrospect.room.repository;


import com.backend.Retrospect.room.entiry.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoomRepository extends JpaRepository<RoomEntity , Long>
{

}