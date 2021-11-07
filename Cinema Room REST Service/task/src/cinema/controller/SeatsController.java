package cinema.controller;

import cinema.model.CinemaRoom;
import cinema.model.Seat;
import cinema.model.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class SeatsController {
    static List<Seat> seats = new ArrayList<>();
    static List<Token> tokens = new ArrayList<>();

    static {
        Seat s;
        for (int row = 1; row <= 9; row++) {
            for (int col = 1; col <= 9; col++) {
                if (row <= 4) {
                    s = new Seat(row, col, 10);
                    seats.add(s);
                } else {
                    seats.add(new Seat(row, col, 8));
                }
            }
        }
    }

    int total_row = 9;
    int total_column = 9;
    CinemaRoom room = new CinemaRoom(total_row, total_column, seats,tokens);

    @GetMapping("/seats")
    public CinemaRoom getSeats() {
        return room;
    }

    @PostMapping(value = "/purchase")
    public Object purchaseTicket(@RequestBody Seat seat) {
        //check if seat is available
        HashMap<String, String> msg = new HashMap<>();
        String message = null;
        List<Seat> list = room.getAvailableSeats();
        if (list.contains(seat)) {
            //check if seat is available
            for (Seat s : list) {
                if (s.equals(seat) && s.isAvailable()) {
                    s.setAvailable(false); //set it bought
                    seat = s;
                    Token t = new Token(s);
                    room.getTokens().add(t);
                    return t;
                } else if (s.equals(seat) && !s.isAvailable()) {
                    //set is taken
                    msg.clear();
                    msg.put("error", "The ticket has been already purchased!");
                    return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
                }
            }
        }

        //no set error message
        msg.clear();
        msg.put("error", "The number of a row or a column is out of bounds!");
        return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);


    }

    @PostMapping("/return")
    public Object returnTicket(@RequestBody Token token) {
        HashMap<String, Seat> map = new HashMap<>();
        for (Token t : tokens) {
            if (t.getToken().equals(token.getToken())) {
                //taken exists
                map.put("returned_ticket", t.getTicket());
                tokens.remove(t);
                for(Seat s: room.getAvailableSeats()){
                    if(s.equals(t.getTicket())){
                        s.setAvailable(true);
                        break;
                    }
                }
                return map;
            }
        }
        HashMap<String, String> error = new HashMap<>();
        error.put("error", "Wrong token!");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/stats")
    public Object getStatistics(@RequestParam(value = "password", required = false) String password){
        if(password !=null && !password.isEmpty() && password.equals("super_secret")) {
            HashMap<String, Integer> stats = new HashMap<>();
            stats.put("current_income", room.getIncome());
            stats.put("number_of_available_seats", room.notSoldSeats().size());
            stats.put("number_of_purchased_tickets", room.ticketsPurchased());
            return stats;
        }
        else{
            HashMap<String, String> error = new HashMap<>();
            error.put("error", "The password is wrong!");
            return new ResponseEntity(error,HttpStatus.UNAUTHORIZED);
        }
    }
}
