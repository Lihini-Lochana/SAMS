module com.ijse.sams {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;

    opens com.ijse.sams to javafx.fxml;
    opens com.ijse.sams.controller to javafx.fxml;
    opens com.ijse.sams.entity to org.hibernate.orm.core;
    opens com.ijse.sams.util to org.hibernate.orm.core;
    opens com.ijse.sams.service to org.hibernate.orm.core;
    opens com.ijse.sams.dao to org.hibernate.orm.core;

    exports com.ijse.sams;
    exports com.ijse.sams.controller;
}