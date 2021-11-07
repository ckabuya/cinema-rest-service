package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CinemaRoom {
    int totalRows;
    int totalColumns;
    List<Seat> availableSeats;
    @JsonIgnore
    List<Token> tokens;

    public CinemaRoom(int totalRow, int totalColumn, List<Seat> availableSeats) {
        this.totalRows = totalRow;
        this.totalColumns = totalColumn;
        this.availableSeats =availableSeats;
        //setAvailableSeats();
    }
    public CinemaRoom(int totalRow, int totalColumn, List<Seat> availableSeats, List<Token> tokens) {
        this.totalRows = totalRow;
        this.totalColumns = totalColumn;
        this.availableSeats =availableSeats;
        this.tokens =tokens;
        //setAvailableSeats();
    }
    public CinemaRoom(int totalRow, int totalColumn) {
        this.totalRows = totalRow;
        this.totalColumns = totalColumn;
        //setAvailableSeats();
    }

    public CinemaRoom() {
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
       /* availableSeats.clear();
        for(int row=1; row<=9; row++){
            for(int col=1; col<=9; col++){
                if(row <=4){
                    availableSeats.add(new Seat(row,col,10));
                }
                else
                {
                    availableSeats.add(new Seat(row,col,8));
                }
            }
        }*/
        this.availableSeats = availableSeats;
    }
    @JsonIgnore
   public List<Seat> notSoldSeats() {
        List<Seat> notSold=new ArrayList<>();
        for(Seat s: availableSeats){
           if(s.isAvailable()){
               notSold.add(s);
           }
        }
        return notSold;
    }
    @JsonIgnore
    public List<Seat> soldSeats() {
        List<Seat> sold=new ArrayList<>();
        for(Seat s: availableSeats){
            if(!s.isAvailable()){
                sold.add(s);
            }
        }
        return sold;
    }
    @JsonIgnore
    public int getIncome(){
        int sum=0;
        for(Token t: tokens){
            sum += t.getTicket().getPrice();
        }
        return sum;
    }
    public int ticketsPurchased(){
        return tokens.size();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaRoom that = (CinemaRoom) o;
        return totalRows == that.totalRows && totalColumns == that.totalColumns && Objects.equals(availableSeats, that.availableSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalRows, totalColumns, availableSeats);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
}
