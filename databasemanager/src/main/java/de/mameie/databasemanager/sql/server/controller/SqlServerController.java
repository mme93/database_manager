package de.mameie.databasemanager.sql.server.controller;

import de.mameie.databasemanager.sql.server.service.SqlServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/server")
public class SqlServerController {

    private final SqlServerService sqlServerService;

    @Autowired
    public SqlServerController(SqlServerService sqlServerService) {
        this.sqlServerService = sqlServerService;
    }

}
