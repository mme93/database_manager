package de.mameie.databasemanager.sql.server.database.table.controller;

import de.mameie.databasemanager.sql.server.database.table.model.DatabaseTableView;
import de.mameie.databasemanager.sql.server.database.table.service.SqlTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/table/{serverName}/{database}")
public class SqlTableController {

    private final SqlTableService sqlTableService;

    @Autowired
    public SqlTableController(SqlTableService sqlTableService) {
        this.sqlTableService = sqlTableService;
    }

    @GetMapping("/name/all")
    public ResponseEntity<List<String>> getAllTableNames(@PathVariable String serverName, @PathVariable String database) {
        List<String> tableNames = sqlTableService.getAllTableNames(serverName, database);
        if (tableNames.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tableNames, HttpStatus.OK);
    }

    @GetMapping("/{tableName}")
    public ResponseEntity<DatabaseTableView> getTableByName(@PathVariable String serverName, @PathVariable String database, @PathVariable String tableName) {
        return new ResponseEntity<>(sqlTableService.getTableByName(serverName, database, tableName), HttpStatus.OK);
    }

    @PostMapping("/{tableName}")
    public ResponseEntity createTable(@PathVariable String serverName, @PathVariable String database, @PathVariable String tableName) {
        sqlTableService.createTable(serverName, database, tableName);
        return new ResponseEntity(HttpStatus.OK);
    }

}
