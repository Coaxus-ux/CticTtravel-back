package ctictravel.ctictravel.Controllers;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/systemstatus")
public class SystemStatus {
    @RequestMapping("/status")
    public String status() {
        return "OK";
    }
}
