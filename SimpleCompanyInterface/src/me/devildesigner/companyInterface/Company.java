/*
 * Today is 4/26/2020
 * Session by: *DevilDesigner (Dev.)
 * Create Time: 4:44 AM
 * This Class: General
 */

package me.devildesigner.companyInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Company extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        users.add(new User("Alice", 20, 150, false));
        users.add(new User("Bob", 34, 300, true));
        users.add(new User("Peter", 18, 100, false));
        users.add(new User("Kate", 38, 300, true));
        users.add(new User("Steve", 31, 250, true));
        users.add(new User("Alan", 62, 500, true));
        users.add(new User("Julia", 33, 320, true));
        users.add(new User("Patric", 37, 300, true));
        users.add(new User("Alexander", 34, 280, true));
        users.add(new User("George", 28, 180, true));
        users.add(new User("Mary", 22, 190, false));

        userBox.getItems().addAll(users);

        ageFilterBox.getItems().addAll(
                "no matter",
                "over 20",
                "over 30",
                "over 40"
        );

        salaryFilterBox.getItems().addAll(
                "no matter",
                "over 150",
                "over 250",
                "over 500"
        );

        relationshipFilterBox.getItems().addAll(
                "no matter",
                "Married",
                "Single"
        );

        root.getChildren().add(strings);

        strings.setPadding(new Insets(10, 30, 10, 30));
        strings.setSpacing(20);

        strings.getChildren().add(new Text("Select the user"));
        strings.getChildren().add(buttonBox);
        strings.getChildren().add(new Text("Change the salary"));
        strings.getChildren().add(changeSalaryBox);
        strings.getChildren().add(new Text("Add new User"));
        strings.getChildren().add(addUserBox);
        strings.getChildren().add(filters);
        strings.getChildren().add(resultFilter);

        buttonBox.setSpacing(10);
        buttonBox.getChildren().add(userBox);
        buttonBox.getChildren().add(buttonGetInfo);
        buttonBox.getChildren().add(textInfo);

        changeSalaryBox.setSpacing(10);
        changeSalaryBox.getChildren().add(buttonChangeSalary);
        changeSalaryBox.getChildren().add(howMuchChange);

        addUserBox.setSpacing(10);
        addUserBox.getChildren().add(new Text("Name: "));
        addUserBox.getChildren().add(name);
        addUserBox.getChildren().add(new Text("Age: "));
        addUserBox.getChildren().add(age);
        addUserBox.getChildren().add(new Text("Salary: "));
        addUserBox.getChildren().add(salary);
        addUserBox.getChildren().add(new Text("Married: "));
        addUserBox.getChildren().add(married);
        addUserBox.getChildren().add(buttonAddUser);

        filters.setSpacing(10);
        filters.getChildren().add(new Text("Age"));
        filters.getChildren().add(ageFilterBox);
        filters.getChildren().add(new Text("Salary"));
        filters.getChildren().add(salaryFilterBox);
        filters.getChildren().add(new Text("Relationship"));
        filters.getChildren().add(relationshipFilterBox);
        filters.getChildren().add(filter);


        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Company");
        primaryStage.setScene(scene);
        primaryStage.show();

        buttonGetInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                User u = (User) userBox.getSelectionModel().getSelectedItem();
                if (u != null) {
                    textInfo.setText("Age is:  " + u.getAge() + "; \n" +
                            "Salary is:  " + u.getSalary() + "; \n" +
                            "Relationship:  " + u.getMarried() + ".");
                } else {
                    textInfo.setText("User not selected");
                }
            }
        });

        buttonChangeSalary.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                User u = (User) userBox.getSelectionModel().getSelectedItem();
                if(u != null) {
                    u.changeSalary(Integer.parseInt(howMuchChange.getText()));
                    textInfo.setText("Age is " + u.getAge() + ", " +
                            "Salary is " + u.getSalary() + ", " +
                            "Relationshp: " + u.getMarried());
                    howMuchChange.clear();
                }
            }
        });

        buttonAddUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String m = married.getText();
                boolean mm = (m.equals("Married"))? true:false;
                User u = new User(name.getText(), Integer.parseInt(age.getText()),
                        Integer.parseInt(salary.getText()), mm);
                users.add(u);
                userBox.getItems().addAll(u);
                name.clear();
                age.clear();
                salary.clear();
                married.clear();
            }
        });

        filter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int age;
                int index = ageFilterBox.getSelectionModel().getSelectedIndex();
                age = (index == 0)? 0: (index == 1)? 21: (index == 2)? 31: 41;

                int salary;
                index = salaryFilterBox.getSelectionModel().getSelectedIndex();
                salary = (index == 0)? 0 : (index == 1)? 151 : (index == 2)? 251:501;

                boolean relate;
                index = relationshipFilterBox.getSelectionModel().getSelectedIndex();
                relate = (index == 1)? true: (index == 2)? false: true;

                //resultFilter.setText(String.valueOf(age)  + " " + String.valueOf(salary)  + " "+ relate);

                List<User> list;
                if(index != 0) {  list = users.stream().
                        filter(u -> u.age > age).
                        filter(u -> u.salary > salary).
                        filter(u -> u.married == relate).
                        collect(Collectors.toList()); }

                else { list = users.stream().
                        filter(u -> u.age > age).
                        filter(u -> u.salary > salary).
                        collect(Collectors.toList()); }

                String res = "";
                for(User u: list) {
                    res += u.toString() + ", ";
                }

                resultFilter.setText(res);

            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

    Group root = new Group();

    VBox strings = new VBox();

    HBox buttonBox = new HBox();
    HBox changeSalaryBox = new HBox();
    HBox addUserBox = new HBox();
    HBox filters = new HBox();

    ComboBox<User> userBox = new ComboBox<>();
    ComboBox<String> ageFilterBox = new ComboBox<>();
    ComboBox<String> salaryFilterBox = new ComboBox<>();
    ComboBox<String> relationshipFilterBox = new ComboBox<>();

    final private int WIDTH = 1000;
    final private int HEIGHT = 600;

    private ArrayList<User> users = new ArrayList<>();

    Button buttonGetInfo = new Button("Info");
    Text textInfo = new Text();

    Button buttonChangeSalary = new Button("Change salary");
    TextField howMuchChange = new TextField();

    Button buttonAddUser = new Button("Add User");
    TextField name = new TextField();
    TextField age = new TextField();
    TextField salary = new TextField();
    TextField married = new TextField();

    Button filter = new Button("Filter!");
    Text resultFilter = new Text();

}