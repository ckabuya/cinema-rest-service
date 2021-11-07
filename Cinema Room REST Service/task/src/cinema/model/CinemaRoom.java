package cinema.model;

import java.util.List;
import java.util.Objects;

public class CinemaRoom {
    int totalRows;
    int totalColumns;
    List<Seat> availableSeats;

    public CinemaRoom(int totalRow, int totalColumn, List<Seat> availableSeats) {
        this.totalRows = totalRow;
        this.totalColumns = totalColumn;
        this.availableSeats =availableSeats;
        setAvailableSeats();
    }

    public CinemaRoom(int totalRow, int totalColumn) {
        this.totalRows = totalRow;
        this.totalColumns = totalColumn;
        setAvailableSeats();
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
        availableSeats.clear();
        for(int row=1; row<=9; row++){
            for(int col=1; col<=9; col++){
                availableSeats.add(new Seat(row,col));
            }
        }
        this.availableSeats = availableSeats;
    }
    public void setAvailableSeats() {
        for(int row=1; row<=9; row++){
            for(int col=1; col<=9; col++){
                availableSeats.add(new Seat(row,col));
            }
        }
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
}
