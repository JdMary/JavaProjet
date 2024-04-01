package core.controllers;

import core.models.User;
import core.services.UserService;
import core.session.UserSession;
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
import java.util.ResourceBundle;

public class SigninController implements Initializable {
    @FXML
    private TextField useremailTF;
    @FXML
    private PasswordField userpasswordPF;
    @FXML
    private Button signinBUTTON;
    public void signin()
    {
        User user=new User();
        UserService userService=new UserService();
        String email=useremailTF.getText();
        String password =userpasswordPF.getText();
        user=userService.login(email,password);
        System.out.println(user.getEmail());
        if(user != null) {
            UserSession.setSession(user);
        }


    }
    public void loadHome()
    {
        Stage stage = (Stage) signinBUTTON.getScene().getWindow();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/core/gui/home.fxml"));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
