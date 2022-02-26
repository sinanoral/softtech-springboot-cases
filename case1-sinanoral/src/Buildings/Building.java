package Buildings;

import java.math.BigDecimal;

public abstract class Building {

    protected BigDecimal price;
    protected BigDecimal squareMeters;
    protected int rooms;
    protected int lounges;

    public Building(BigDecimal price, BigDecimal squareMeters, int rooms, int lounges) {
        this.price = price;
        this.squareMeters = squareMeters;
        this.rooms = rooms;
        this.lounges = lounges;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(BigDecimal squareMeters) {
        this.squareMeters = squareMeters;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getLounges() {
        return lounges;
    }

    public void setLounges(int lounges) {
        this.lounges = lounges;
    }
}
