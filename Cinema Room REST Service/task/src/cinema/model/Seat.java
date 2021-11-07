package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
@JsonIgnoreProperties(value = { "isAvailable" })
public class Seat {
    private int row;
    private int column;
    private int price;

    private boolean isAvailable = true;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
    }
    public Seat(int row, int column,int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }
    public Seat(int row, int column,int price,boolean isAvailable) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public Seat() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    @JsonIgnore
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
