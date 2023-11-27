package ctictravel.ctictravel.Controllers;
import ctictravel.ctictravel.DAO.Users.UsersInterfaces;
import ctictravel.ctictravel.Interfaces.CommunicationInterface;
import ctictravel.ctictravel.Interfaces.ResponseEntityInterface;
import ctictravel.ctictravel.Models.Users;
import ctictravel.ctictravel.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersInterfaces usersInterfaces;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> registerUser(@RequestBody Users user) {
        //if (Stream.of(user.getUserEmail(), user.getUserPassword(), user.getUserName(), user.getUserLastName()).anyMatch(Objects::isNull))

        if(user.hasEmptyNullFields())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());


        CommunicationInterface response = usersInterfaces.register(user);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("User created successfully").build());
    }
    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> loginUser(@RequestBody Users user) {
        if (Stream.of(user.getUserEmail(), user.getUserPassword()).anyMatch(Objects::isNull))
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = usersInterfaces.login(user);
        String JWT = jwtUtil.generateToken(user.getUserEmail(), user.getUserPassword());
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("User logged successfully").setData(response.getData()).setJWT(JWT).build());
    }
    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> updateUser(@RequestBody Users user) {

        if(user.hasEmptyNullFields())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());


        CommunicationInterface response = usersInterfaces.updateUser(user);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("User updated successfully").build());
    }

    @PostMapping(value = "/get-by-id", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseEntityInterface> getUserById(@RequestBody Users user) {

        if (user.getUserEmail() == null || user.getUserEmail().isEmpty())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage("Missing parameters").build());

        CommunicationInterface response = usersInterfaces.getUserById(user);
        if (!response.getSuccessful())
            return ResponseEntity.status(400).body(new ResponseEntityInterface.Builder().setSuccessful(false).setMessage(response.getMessage()).build());
        return ResponseEntity.status(200).body(new ResponseEntityInterface.Builder().setSuccessful(true).setMessage("User found successfully").setData(response.getData()).build());
    }


}
