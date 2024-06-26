package de.mameie.databasemanager.sql.server.database.controller;

import de.mameie.databasemanager.sql.server.database.service.DatabaseService;
import de.mameie.databasemanager.util.check.CheckParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/database/{serverName}")
public class SqlDatabaseController {

    private final DatabaseService databaseService;

    @Autowired
    public SqlDatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @DeleteMapping("/delete/{databaseName}")
    public ResponseEntity deleteDatabase(@PathVariable String databaseName, @PathVariable String serverName) {
        CheckParam.isNotNull(databaseName, "databaseName");
        CheckParam.isNotNull(serverName, "serverName");
        if (databaseService.deleteDatabase(databaseName, serverName)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/create/{databaseName}")
    public ResponseEntity createDatabase(@PathVariable String databaseName, @PathVariable String serverName) {
        CheckParam.isNotNull(databaseName, "databaseName");
        CheckParam.isNotNull(serverName, "serverName");
        if (databaseService.createDatabase(databaseName, serverName)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/all")
    public ResponseEntity getDatabaseNames(@PathVariable String serverName) {
        CheckParam.isNotNull(serverName, "serverName");
        return new ResponseEntity(databaseService.getDatabaseNames(serverName), HttpStatus.OK);
    }
}
