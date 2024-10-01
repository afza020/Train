package com.train.train.Service;

import com.train.train.DTO.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TicketService {
    private final Map<String, UserDTO> users = new HashMap<>();
    private final List<String> seatsA = new ArrayList<>();
    private final List<String> seatsB = new ArrayList<>();
    private static final double TICKET_PRICE = 20.0;

    public TicketService() {
        for (int i = 1; i <= 10; i++) {
            seatsA.add("A" + i);
            seatsB.add("B" + i);
        }
    }

    public UserDTO purchaseTicket(String firstName, String lastName, String email, String section) {
        List<String> availableSeats = section.equals("A") ? seatsA : seatsB;
        if (availableSeats.isEmpty()) {
            return null;
        }

        String seat = availableSeats.remove(0);
        UserDTO user = new UserDTO(firstName, lastName, email, section, seat, TICKET_PRICE);
        users.put(email, user);
        return user;
    }

    public UserDTO getReceipt(String email) {
        return users.get(email);
    }

    public List<UserDTO> getUsersBySection(String section) {
        List<UserDTO> sectionUsers = new ArrayList<>();
        for (UserDTO user : users.values()) {
            if (user.getSection().equals(section)) {
                sectionUsers.add(user);
            }
        }
        return sectionUsers;
    }

    public boolean removeUser(String email) {
        UserDTO user = users.remove(email);
        if (user != null) {
            List<String> availableSeats = user.getSection().equals("A") ? seatsA : seatsB;
            availableSeats.add(user.getSeat());
            return true;
        }
        return false;
    }

    public boolean modifyUserSeat(String email, String newSeat) {
        UserDTO user = users.get(email);
        if (user != null && (user.getSection().equals("A") ? seatsA : seatsB).contains(newSeat)) {
            List<String> availableSeats = user.getSection().equals("A") ? seatsA : seatsB;
            availableSeats.add(user.getSeat());
            user.setSeat(newSeat);
            availableSeats.remove(newSeat);
            return true;
        }
        return false;
    }
}
