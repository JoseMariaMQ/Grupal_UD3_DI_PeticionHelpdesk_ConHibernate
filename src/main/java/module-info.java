module DI_Drupal1_UD3 {
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires com.sun.xml.bind;
    requires net.bytebuddy;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires com.fasterxml.classmate;

    opens controller to javafx.fxml;
    opens model to com.fasterxml.classmate, org.hibernate.orm.core;
    exports controller;
    exports model;
}