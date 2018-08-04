package mum.asd.repository;

import mum.asd.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT distinct p from Room p WHERE p.isRoomVailable = :status")
    List<Room> findAllByStatus(@Param("status") boolean status);

    @Query("SELECT distinct p from Room p WHERE p.isRoomVailable = true and p.bedType like :keyword")
    List<Room> findAllByKeyword(@Param("keyword") String keyword);
}
