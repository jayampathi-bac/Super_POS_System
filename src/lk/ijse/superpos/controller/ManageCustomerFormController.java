   package lk.ijse.superpos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.superpos.service.BOFactory;
import lk.ijse.superpos.service.custom.CustomerBO;
import lk.ijse.superpos.view.util.tblmodel.CustomerTM;
import lk.ijse.superpos.model.CustomerDTO;

public class ManageCustomerFormController implements Initializable {

    @FXML
    private JFXTextField txtID;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtSalary;
    @FXML
    private TableView<CustomerTM> tblCustomers;

    private boolean update = false;
    @FXML
    private JFXButton btnDelete;

    private void loadAllCustomers() throws SQLException {
        try {
            CustomerBO cusBO = (CustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BOFacTypes.CUSTOMER);
            ArrayList<CustomerDTO> all = cusBO.getAllCustomers();
            ArrayList<CustomerTM> custoemrTMAll = new ArrayList<>();
            for (CustomerDTO customer : all) {
                CustomerTM tm = new CustomerTM(customer.getId(), customer.getName(), customer.getAddress(), customer.getSalary());
                custoemrTMAll.add(tm);
            }
            tblCustomers.setItems(FXCollections.observableArrayList(custoemrTMAll));
            tblCustomers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerTM>() {
                @Override
                public void changed(ObservableValue<? extends CustomerTM> observable, CustomerTM oldValue, CustomerTM newValue) {
                    CustomerTM customer = observable.getValue();
                    if (customer == null) {
                        update = false;
                        return;
                    }
                    update = true;
                    btnDelete.setDisable(!update);
                    txtID.setText(customer.getId());
                    txtName.setText(customer.getName());
                    txtAddress.setText(customer.getAddress());
                    txtSalary.setText(customer.getSalary() + "");
                }
            });
            if (all.size() > 0) {
                tblCustomers.getSelectionModel().clearAndSelect(0);
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearAllTextFileds() {
        txtID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtSalary.setText("");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tblCustomers.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblCustomers.getColumns().get(3).setStyle("-fx-alignment: CENTER_RIGHT;");
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("salary"));

        try {
            loadAllCustomers();
        } catch (SQLException ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void navigateToMain(MouseEvent event) throws IOException {
        Label lblMainNav = (Label) event.getSource();
        Stage primaryStage = (Stage) lblMainNav.getScene().getWindow();

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/superpos/view/MainForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.centerOnScreen();
    }

    @FXML
    private void btnNewCustomerOnAction(ActionEvent event) {
        tblCustomers.getSelectionModel().clearSelection();
        clearAllTextFileds();
        btnDelete.setDisable(!update);
        txtID.requestFocus();
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) throws SQLException {

        try {
            String id = txtID.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            double salary = Double.parseDouble(txtSalary.getText());
            CustomerBO cusBO = (CustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BOFacTypes.CUSTOMER);
            
            CustomerDTO cus = new CustomerDTO(id, name, address, salary);
            boolean add = cusBO.addCustomer(cus);
            if (add) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
                a.show();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Falied", ButtonType.OK);
                a.show();
            }
            btnNewCustomerOnAction(event);
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) throws SQLException {
        try {
            CustomerBO cusBO = (CustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BOFacTypes.CUSTOMER);
            
            boolean delete = cusBO.deleteCustomer(txtID.getText());
            if (delete) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Customer has been deleted successfully", ButtonType.OK);
                alert.setTitle("Customer Deleted !");
                alert.setHeaderText("Customer Deleted !");
                alert.showAndWait();
                loadAllCustomers();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Customer can't be deleted", ButtonType.OK);
                alert.setTitle("Delete Failed !");
                alert.setHeaderText("Failed to Delete!");
                alert.showAndWait();
            }
            
            btnNewCustomerOnAction(event);
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
