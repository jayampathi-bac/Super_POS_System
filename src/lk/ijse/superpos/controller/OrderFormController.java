/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.superpos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import lk.ijse.superpos.view.util.tblmodel.OrderDetailTM;


public class OrderFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbCustomerID;
    @FXML
    private JFXComboBox<String> cmbItemCode;

    private Connection connection;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtQtyOnHand;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private TableView<OrderDetailTM> tblOrderDetails;

    private ObservableList<OrderDetailTM> olOrderDetails;

    private boolean update = false;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private Label lblTotal;
    @FXML
    private JFXTextField txtOrderID;
    @FXML
    private JFXDatePicker txtOrderDate;

    private void loadAllData() throws SQLException {

        Statement stm = connection.createStatement();

        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");

        cmbCustomerID.getItems().removeAll(cmbCustomerID.getItems());

        while (rst.next()) {
            String id = rst.getString(1);
            cmbCustomerID.getItems().add(id);
        }

        rst = stm.executeQuery("SELECT * FROM Item");

        cmbItemCode.getItems().removeAll(cmbItemCode.getItems());

        while (rst.next()) {
            String itemCode = rst.getString(1);
            cmbItemCode.getItems().add(itemCode);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/superpos", "root", "root");
            loadAllData();
        } catch (SQLException ex) {
            Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        }

        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String customerID = observable.getValue();
                if (customerID == null) {
                    txtCustomerName.setText("");
                    return;
                }

                try {
                    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
                    pstm.setObject(1, customerID);
                    ResultSet rst = pstm.executeQuery();

                    if (rst.next()) {
                        String customerName = rst.getString(2);
                        txtCustomerName.setText(customerName);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String itemCode = observable.getValue();

                if (itemCode == null) {
                    txtDescription.setText("");
                    txtQtyOnHand.setText("");
                    txtUnitPrice.setText("");
                    txtQty.setText("");
                    return;
                }

                try {
                    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code = ?");
                    pstm.setObject(1, itemCode);

                    ResultSet rst = pstm.executeQuery();

                    if (rst.next()) {
                        String description = rst.getString(2);
                        int qtyOnHand = rst.getInt(3);
                        double unitPrice = rst.getDouble(4);

                        txtDescription.setText(description);
                        txtUnitPrice.setText(unitPrice + "");
                        txtQtyOnHand.setText(qtyOnHand + "");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        olOrderDetails = FXCollections.observableArrayList();
        tblOrderDetails.setItems(olOrderDetails);

        tblOrderDetails.getItems().addListener(new ListChangeListener<OrderDetailTM>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends OrderDetailTM> c) {

                double total = 0.0;

                for (OrderDetailTM orderDetail : olOrderDetails) {
                    total += orderDetail.getTotal();
                }

                lblTotal.setText("Total : " + total);

            }
        });

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrderDetailTM>() {
            @Override
            public void changed(ObservableValue<? extends OrderDetailTM> observable, OrderDetailTM oldValue, OrderDetailTM newValue) {

                OrderDetailTM currentRow = observable.getValue();

                if (currentRow == null) {
                    cmbItemCode.getSelectionModel().clearSelection();
                    update = false;
                    btnRemove.setDisable(true);
                    return;
                }

                update = true;
                String itemCode = currentRow.getItemCode();
                btnRemove.setDisable(false);

                cmbItemCode.getSelectionModel().select(itemCode);
                txtQty.setText(currentRow.getQty() + "");

            }
        });

        btnRemove.setDisable(true);

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
    private void btnSaveOnAction(ActionEvent event) {

        String itemCode = cmbItemCode.getSelectionModel().getSelectedItem();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        if (!update) {
            for (OrderDetailTM orderDetail : olOrderDetails) {
                if (orderDetail.getItemCode().equals(itemCode)) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Please update the item instead of adding", ButtonType.OK);
                    error.setHeaderText("Duplicate Entry Found");
                    error.setTitle("Duplicate Error");
                    error.show();
                    return;
                }
            }
        }

//        if (!update){
        OrderDetailTM orderDetail = new OrderDetailTM(
                itemCode,
                txtDescription.getText(),
                qty,
                unitPrice,
                qty * unitPrice);

//        }else{
//        }
        if (!update) {
            olOrderDetails.add(orderDetail);
            tblOrderDetails.setItems(olOrderDetails);
        } else {
            OrderDetailTM selectedRow = tblOrderDetails.getSelectionModel().getSelectedItem();

            int index = olOrderDetails.indexOf(selectedRow);

            olOrderDetails.set(index, orderDetail);
        }

        cmbItemCode.getSelectionModel().clearSelection();
        cmbItemCode.requestFocus();

    }

    @FXML
    private void btnRemoveOnAction(ActionEvent event) {

        OrderDetailTM selectedRow = tblOrderDetails.getSelectionModel().getSelectedItem();

        olOrderDetails.remove(selectedRow);

    }

    @FXML
    private void btnPlaceOrderOnAction(ActionEvent event) {

        try {

            connection.setAutoCommit(false);

            String sql = "INSERT INTO Orders VALUES (?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, txtOrderID.getText());
            pstm.setObject(2, parseDate(txtOrderDate.getEditor().getText()));
//            pstm.setObject(2, "2018");
            pstm.setObject(3, cmbCustomerID.getSelectionModel().getSelectedItem());
            int affectedRows = pstm.executeUpdate();

            if (affectedRows == 0) {
                connection.rollback();
                return;
            }

            sql = "INSERT INTO OrderDetail VALUES (?,?,?,?)";
            pstm = connection.prepareStatement(sql);

            PreparedStatement pstm2 = connection.prepareStatement("UPDATE Item SET qtyOnHand=? WHERE code=?");

            for (OrderDetailTM orderDetail : olOrderDetails) {

                pstm.setObject(1, txtOrderID.getText());
                pstm.setObject(2, orderDetail.getItemCode());
                pstm.setObject(3, orderDetail.getQty());
                pstm.setObject(4, orderDetail.getUnitPrice());

                affectedRows = pstm.executeUpdate();

                if (affectedRows == 0) {
                    connection.rollback();
                    return;
                }

                int qtyOnHand = 0;

                Statement stm = connection.createStatement();
                ResultSet rst = stm.executeQuery("SELECT * FROM Item WHERE code='" + orderDetail.getItemCode() + "'");
                if (rst.next()) {
                    qtyOnHand = rst.getInt(4);
                }

                pstm2.setObject(1, qtyOnHand - orderDetail.getQty());
                pstm2.setObject(2, orderDetail.getItemCode());

                affectedRows = pstm2.executeUpdate();

                if (affectedRows == 0) {
                    connection.rollback();
                    return;
                }

            }

            connection.commit();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Payment Successful", ButtonType.OK);
            alert.show();

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private String parseDate(String oldDateString) throws ParseException {
        try {
            final String OLD_FORMAT = "dd/MM/yyyy";
            final String NEW_FORMAT = "yyyy-MM-dd";

            String newDateString;

            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
            return newDateString;

        } catch (ParseException ex) {

            Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
