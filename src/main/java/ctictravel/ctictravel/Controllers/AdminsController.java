package ctictravel.ctictravel.Controllers;

import ctictravel.ctictravel.DAO.Admins.AdminsInterfaces;
import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;
import ctictravel.ctictravel.Models.Admins;

import ctictravel.ctictravel.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/admins")
public class AdminsController {
    @Autowired
    private AdminsInterfaces adminsInterfaces;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> registerAdmin(@RequestBody Admins admin) {
        if (admin.hasEmptyNullFields())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = adminsInterfaces.createAdmin(admin);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Admin created successfully").build());
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> loginAdmin(@RequestBody Admins admin) {
        if (Stream.of(admin.getAdminEmail(), admin.getAdminPassword()).anyMatch(value -> value == null || value.isEmpty()))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = adminsInterfaces.loginAdmin(admin);
        String JWT = jwtUtil.generateToken(admin.getAdminEmail(), admin.getAdminPassword());
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Admin logged successfully").setData(response.getData()).setJWT(JWT).build());
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> updateAdmin(@RequestBody Admins admin) {

        if(admin.hasEmptyNullFields())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = adminsInterfaces.updateAdmin(admin);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("Admin updated successfully").build());
    }
}