package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Services;
import model.ServicesDAO;

import java.util.ArrayList;

public class ShowServicesController implements EventHandler<ActionEvent> {

    public TableView<Services> table;
    public TableColumn<Services, String> columnUser;
    public TableColumn<Services, Integer> columnPhone;
    public TableColumn<Services, String> columnEmail;
    public TableColumn<Services, String> columnCity;
    public TableColumn<Services, Integer> columnCP;
    public TableColumn<Services, String> columnServicies;
    public Button btnLoadServices;

    private static TableRow<Services> row1;
    public Button btnClose;

    public void initialize() {
        btnLoadServices.setOnAction(this);
        btnClose.setOnAction(this::closeWindow);
        //Capturamos cuando se pulsa en una fila para abrir ventana de modificar servicio
        // y le pasamos los datos de la fila elegida
        table.setRowFactory(tv -> {
            TableRow<Services> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                //Comprobamos si clica una vez
                if(event.getClickCount() == 1 && (!row.isEmpty())) {
                    row1 = row;
                    ModifyService ms = new ModifyService();
                    ms.modifyService(row1);
                }
            });
            return row;
        });
    }

    public void servicesWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("showservices.fxml"));
            Parent root1 = fxmlLoader.load();
            Scene scene = new Scene(root1, 800, 400);
            Stage stage = new Stage();
            stage.setTitle("Servicios");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        ArrayList<Services> services = new ArrayList<>();
        Services service;
        ServicesDAO sDAO = new ServicesDAO();
        sDAO.showServices();

        //Recorremos la consulta a la base de datos para instanciar Services
        // y crear un array de la clase Services para el ObservableList
        for(Services s : sDAO.showServices()) {
            service = new Services(s.getId(), s.getUser(), s.getPhone(), s.getEmail(), s.getCity(), s.getCp(), s.getServices());
            services.add(service);
        }

        //Mostramos los datos en la tabla de la ventana
        ObservableList<Services> servicesList = FXCollections.observableArrayList(services);
        columnUser.setCellValueFactory(new PropertyValueFactory<Services, String>("user"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Services, Integer>("phone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Services, String>("email"));
        columnCity.setCellValueFactory(new PropertyValueFactory<Services, String>("city"));
        columnCP.setCellValueFactory(new PropertyValueFactory<Services, Integer>("cp"));
        columnServicies.setCellValueFactory(new PropertyValueFactory<Services, String>("services"));

        table.setItems(servicesList);
    }

    //Método de cerrar ventana con botón
    public void closeWindow(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnClose) {
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        }
    }
}
