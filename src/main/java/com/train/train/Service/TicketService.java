package com.train.train.Service;

import com.train.train.DTO.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TicketService {
    private final List<UserDTO> tickets = new ArrayList<>();
    private final AtomicInteger seatCounterA = new AtomicInteger(1);
    private final AtomicInteger seatCounterB = new AtomicInteger(1);
    private static final String FROM = "London";
    private static final String TO = "France";
    private static final double PRICE = 20.0;

    public UserDTO purchaseTicket(String user, String email, String section) {
        String seat = section.equals("A") ? "A" + seatCounterA.getAndIncrement() : "B" + seatCounterB.getAndIncrement();
        UserDTO ticket = new UserDTO(FROM, TO, user, email, PRICE, seat, section);
        tickets.add(ticket);
        return ticket;
    }

    public UserDTO getTicketByEmail(String email) {
        return tickets.stream().filter(ticket -> ticket.getEmail().equals(email)).findFirst().orElse(null);
    }

    public List<UserDTO> getUsersBySection(String section) {
        return tickets.stream().filter(ticket -> ticket.getSection().equalsIgnoreCase(section)).toList();
    }

    public boolean removeUserByEmail(String email) {
        return tickets.removeIf(ticket -> ticket.getEmail().equals(email));
    }

    public UserDTO modifyUserSeat(String email, String newSeat) {
        for (UserDTO ticket : tickets) {
            if (ticket.getEmail().equals(email)) {
                ticket.setSeat(newSeat);
                return ticket;
            }
        }
        return null;
    }
}
