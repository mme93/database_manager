package de.mameie.databasemanager.sql.server.database.table.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SqlTableService {

    public List<String> getAllTableNames(String serverName, String database) {
        List<String>tableNames= new ArrayList<>();

        return tableNames;
    }
}
