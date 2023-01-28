package ie.pt.springrestwithjwtsecurity.controller;

import ie.pt.springrestwithjwtsecurity.model.SalesRecord;
import ie.pt.springrestwithjwtsecurity.repo.SalesRecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesRecordController {

    @Autowired
    SalesRecordRepo repo;

    @GetMapping("")
    public List<SalesRecord> getAll() {

        return repo.findAll();
    }
}
