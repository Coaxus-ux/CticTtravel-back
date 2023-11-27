package ctictravel.ctictravel.Controllers;

import ctictravel.ctictravel.DAO.TouristPlans.TouristPlansInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tourist-plans")
public class TouristPlansController {
    @Autowired
    private TouristPlansInterfaces touristPlansInterface;

}
