package core.controllers;

import core.models.User;
import core.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    @FXML
    private TextField useremailTF;
    @FXML
    private TextField userfirstnameTF;
    @FXML
    private TextField userlastnameTF;
    @FXML
    private TextField userphoneTF;
    @FXML
    private TextField usercinTF;
    @FXML
    private DatePicker userdobDP;
    @FXML
    private TextField useraddressTF;
    @FXML
    private PasswordField userpasswordPF;
    @FXML
    private Button signupBUTTON;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void signup()
    {
        User user=new User();
        UserService userService=new UserService();
        user.setEmail(useremailTF.getText());
        user.setFirstName(userfirstnameTF.getText());
        user.setLastName(userlastnameTF.getText());
        user.setAddress(useraddressTF.getText());
        String cin = usercinTF.getText();
        int userCin = Integer.parseInt(cin);
        user.setCin(userCin);
        String phone =userphoneTF.getText();
        int userphone=Integer.parseInt(phone);
        user.setPhone(userphone);
        LocalDate localDate = userdobDP.getValue(); // Get the selected date from the DatePicker
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault())); // Convert LocalDate to Instant
        Date date = Date.from(instant); // Convert Instant to Date
        user.setBirthDate(date);
        user.setJoiningDate(new Date());
        user.setRoles("[\"ROLE_USER\"]");
        user.setType("Member");
        user.setVerified(false);
        user.setPassword(userpasswordPF.getText());
        userService.createUser(user);
        loadLogin();
    }
    public void loadLogin(){

        Stage stage = (Stage) signupBUTTON.getScene().getWindow();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/core/gui/signin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
