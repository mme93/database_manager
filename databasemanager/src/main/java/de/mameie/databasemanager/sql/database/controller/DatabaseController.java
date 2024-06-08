package de.mameie.databasemanager.sql.database.controller;

import de.mameie.databasemanager.sql.database.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    private final DatabaseService databaseService;

    @Autowired
    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @DeleteMapping("/delete/{databaseName}")
    public ResponseEntity deleteDatabase(@PathVariable String databaseName) {
        if (databaseService.deleteDatabase(databaseName)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/create/{databaseName}")
    public ResponseEntity createDatabase(@PathVariable String databaseName) {
        if (databaseService.createDatabase(databaseName)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    public ResponseEntity getDatabaseNames() {
        return new ResponseEntity(databaseService.getDatabaseNames(), HttpStatus.OK);
    }
}
