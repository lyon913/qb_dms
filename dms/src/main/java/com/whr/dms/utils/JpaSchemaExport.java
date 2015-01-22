package com.whr.dms.utils;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.Persistence;

import org.hibernate.jpa.AvailableSettings;

public class JpaSchemaExport {

    public static void main(String[] args) throws IOException {
        execute(args[0], args[1]);
        System.exit(0);
    }

    public static void execute(String persistenceUnitName, String destination) {
        System.out.println("Generating DDL create script to : " + destination);

        final Properties persistenceProperties = new Properties();

        // XXX force persistence properties : remove database target
        persistenceProperties.setProperty(org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO, "");
        persistenceProperties.setProperty(AvailableSettings.SCHEMA_GEN_DATABASE_ACTION, "none");
        persistenceProperties.setProperty(AvailableSettings.JDBC_URL, "jdbc:mysql://localhost:3306/dms");
        persistenceProperties.setProperty(AvailableSettings.JDBC_USER, "dms");
        persistenceProperties.setProperty(AvailableSettings.JDBC_PASSWORD, "dms123456");
        persistenceProperties.setProperty(AvailableSettings.JDBC_DRIVER, "com.mysql.jdbc.Driver");

        // XXX force persistence properties : define create script target from metadata to destination
        // persistenceProperties.setProperty(AvailableSettings.SCHEMA_GEN_CREATE_SCHEMAS, "true");
        persistenceProperties.setProperty(AvailableSettings.SCHEMA_GEN_SCRIPTS_ACTION, "create");
        persistenceProperties.setProperty(AvailableSettings.SCHEMA_GEN_CREATE_SOURCE, "metadata");
        persistenceProperties.setProperty(AvailableSettings.SCHEMA_GEN_SCRIPTS_CREATE_TARGET, destination);

        Persistence.generateSchema(persistenceUnitName, persistenceProperties);
    }

}
