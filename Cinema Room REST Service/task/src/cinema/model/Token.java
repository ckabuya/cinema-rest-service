package cinema.model;

import java.util.Objects;
import java.util.UUID;

public class Token {
    UUID token;
    Seat ticket;

    public Token(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public Token(Seat ticket) {
        this.ticket = ticket;
        this.token =UUID.randomUUID();
    }

    public Token() {
        token = UUID.randomUUID();
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(token, token1.token) && Objects.equals(ticket, token1.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, ticket);
    }
}
