package com.train.train.Controller;

import com.train.train.DTO.UserDTO;
import com.train.train.Service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/purchase")
    public UserDTO purchaseTicket(@RequestBody Map<String, String> userDetails) {
        String user = userDetails.get("firstName") + " " + userDetails.get("lastName");
        String email = userDetails.get("email");
        String section = userDetails.get("section");
        return ticketService.purchaseTicket(user, email, section);
    }

    @GetMapping("/{email}")
    public UserDTO getTicket(@PathVariable String email) {
        return ticketService.getTicketByEmail(email);
    }

    @GetMapping("/seats/{section}")
    public List<UserDTO> getUsersBySection(@PathVariable String section) {
        return ticketService.getUsersBySection(section);
    }

    @DeleteMapping("/{email}")
    public String removeUser(@PathVariable String email) {
        boolean removed = ticketService.removeUserByEmail(email);
        return removed ? "User removed" : "User not found";
    }

    @PutMapping("/modify/{email}")
    public UserDTO modifyUserSeat(@PathVariable String email, @RequestBody Map<String, String> seatDetails) {
        return ticketService.modifyUserSeat(email, seatDetails.get("newSeat"));
    }
}
