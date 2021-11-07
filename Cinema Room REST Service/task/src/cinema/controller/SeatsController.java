package cinema.controller;

import cinema.model.CinemaRoom;
import cinema.model.Seat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SeatsController {
    List<Seat> seats=new ArrayList<>() ;
    int total_row = 9;
    int total_column = 9;
    CinemaRoom room=new CinemaRoom(total_row,total_column,seats);
    @GetMapping("/seats")
    public CinemaRoom getSeats(){
    return room;

}
}
