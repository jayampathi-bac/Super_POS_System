package lk.ijse.superpos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import lk.ijse.superpos.business.BOFactory;
import lk.ijse.superpos.business.custom.ItemBO;
import lk.ijse.superpos.model.ItemDTO;
import lk.ijse.superpos.view.util.tblmodel.ItemTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManageItemFormController implements Initializable {
    private final ItemBO itemBO;
    @FXML
    private JFXTextField txtCode;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQtyOnHand;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<ItemTM> tblItems;
    private boolean update = false;

    public ManageItemFormController() {
        this.itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BOFacTypes.ITEM);
        ;
    }

    private void loadAllItems() throws SQLException {
        try {
            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            ArrayList<ItemTM> itemTMAll = new ArrayList<>();

            for (ItemDTO item : allItems) {
                ItemTM tm = new ItemTM(item.getCode(), item.getDescription(), item.getQty(), item.getUnitPrice());
                itemTMAll.add(tm);
            }
            tblItems.setItems(FXCollections.observableArrayList(itemTMAll));
            tblItems.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemTM>() {
                @Override
                public void changed(ObservableValue<? extends ItemTM> observable, ItemTM oldValue, ItemTM newValue) {
                    ItemTM item = observable.getValue();
                    if (item == null) {
                        update = false;
                        return;
                    }
                    update = true;
                    btnDelete.setDisable(!update);
                    txtCode.setText(item.getCode());
                    txtDescription.setText(item.getDescription());
                    txtQtyOnHand.setText(item.getQty() + "");
                    txtUnitPrice.setText(item.getUnitPrice() + "");
                }
            });
            if (allItems.size() > 0) {
                tblItems.getSelectionModel().clearAndSelect(0);
            }
        } catch (Exception e) {
            Logger.getLogger(ManageItemFormController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void clearAllTextFileds() {
        txtCode.setText("");
        txtDescription.setText("");
        txtQtyOnHand.setText("");
        txtUnitPrice.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblItems.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblItems.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblItems.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblItems.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        try {
            loadAllItems();
        } catch (SQLException ex) {
            Logger.getLogger(ManageItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean delete = itemBO.deleteItem(txtCode.getText());
            if (delete) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Item has been deleted successfully", ButtonType.OK);
                alert.setTitle("Item Deleted !");
                alert.setHeaderText("Item Deleted !");
                alert.showAndWait();
                loadAllItems();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Item can't be deleted", ButtonType.OK);
                alert.setTitle("Delete Failed !");
                alert.setHeaderText("Failed to Delete!");
                alert.showAndWait();
            }

            btnNewItemOnAction(event);
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnNewItemOnAction(ActionEvent event) {
        tblItems.getSelectionModel().clearSelection();
        clearAllTextFileds();
        btnDelete.setDisable(!update);
        txtCode.requestFocus();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        try {
            String code = txtCode.getText();
            String description = txtDescription.getText();
            int qty = Integer.parseInt(txtQtyOnHand.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());

            ItemDTO item = new ItemDTO(code, description, qty, unitPrice);
            boolean add = itemBO.addItem(item);
            if (add) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK);
                a.show();
                loadAllItems();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK);
                a.show();
            }
            btnNewItemOnAction(event);
        } catch (Exception ex) {
            Logger.getLogger(ManageCustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            ItemDTO item = new ItemDTO(txtCode.getText(), txtDescription.getText(), Integer.parseInt(txtQtyOnHand.getText()), Double.parseDouble(txtUnitPrice.getText()));
            boolean b = itemBO.updateItem(item);
            if (b) {
                loadAllItems();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update the item", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageItemFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void navigateToMain(MouseEvent event) throws IOException {
        Label lblMainNav = (Label) event.getSource();
        Stage primaryStage = (Stage) lblMainNav.getScene().getWindow();

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/superpos/view/MainForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.centerOnScreen();
    }


}
