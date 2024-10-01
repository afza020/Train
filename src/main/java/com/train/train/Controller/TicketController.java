package com.train.train.Controller;

import com.train.train.DTO.UserDTO;
import com.train.train.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase")
    public ResponseEntity<UserDTO> purchaseTicket(@RequestParam String firstName, @RequestParam String lastName,
                                                  @RequestParam String email, @RequestParam String section) {
        UserDTO user = ticketService.purchaseTicket(firstName, lastName, email, section);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(400).build();
    }

    @GetMapping("/receipt")
    public ResponseEntity<UserDTO> getReceipt(@RequestParam String email) {
        UserDTO user = ticketService.getReceipt(email);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(404).build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsersBySection(@RequestParam String section) {
        return ResponseEntity.ok(ticketService.getUsersBySection(section));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeUser(@RequestParam String email) {
        return ticketService.removeUser(email) ? ResponseEntity.ok().build() : ResponseEntity.status(404).build();
    }

    @PutMapping("/modifySeat")
    public ResponseEntity<Void> modifyUserSeat(@RequestParam String email, @RequestParam String newSeat) {
        return ticketService.modifyUserSeat(email, newSeat) ? ResponseEntity.ok().build() : ResponseEntity.status(400).build();
    }

}
