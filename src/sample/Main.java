package sample;

import factors.ItemCell;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.Item;
import models.Person;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    private List<Item> items;
    private ObservableList<Item> list;
    private static List<Person> people;
    private ObservableList<String> listPeople;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //values
        items = new ArrayList<>();
        people = new ArrayList<>();

        //init elements
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 650, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("");
        primaryStage.show();

        list = FXCollections.observableList(new ArrayList<Item>());
        listPeople = FXCollections.observableArrayList(new ArrayList<>());

        //left
        Label lblItems = new Label("Items");
        ListView<Item> listView = new ListView<>(list);
        listView.setCellFactory(params -> new ItemCell());
        VBox layoutItemsList = new VBox(lblItems, listView);
        Label lblPeople = new Label("Peoples");
        ListView<String> listViewPeople = new ListView<>(listPeople);
        VBox layoutPeopleList = new VBox(lblPeople, listViewPeople);
        HBox left = new HBox(layoutItemsList, layoutPeopleList);
        root.setLeft(left);

        //right
        //items
        Label lblItem = new Label("Item:");
        lblItem.setFont(new Font("Arial", 20));
        Label itemNameText = new Label("Name:");
        TextField itemName = new TextField();
        Label itemCostText = new Label("Cost:");
        TextField itemCost = new TextField();
        Button addButton = new Button("Add");
        VBox addItem = new VBox(lblItem, itemNameText, itemName, itemCostText, itemCost, addButton);
        addItem.setStyle("-fx-border-color: black; -fx-border-radius: 5");
        addItem.setSpacing(5);

        //peoples
        Label lblPerson = new Label("Person:");
        lblPerson.setFont(new Font("Arial", 20));
        Label personNameText = new Label("Name:");
        TextField personName = new TextField();
        Button addPersonButton = new Button("Add");
        VBox addPerson = new VBox(lblPerson, personNameText, personName, addPersonButton);
        addPerson.setStyle("-fx-border-color: black; -fx-border-radius: 5");
        addPerson.setSpacing(5);
        addPerson.setBorder(new Border(new BorderStroke(null, null, new CornerRadii(3), new BorderWidths(3))));

        //add all
        VBox layoutAdd = new VBox(addItem, addPerson);
        layoutAdd.setSpacing(3);
        root.setRight(layoutAdd);

        //top
        Label name = new Label("For checks");
        name.setAlignment(Pos.TOP_CENTER);
        name.setFont(new Font("Arial", 25));
        root.setTop(name);

        //bottom
        Button calc = new Button("Calculate");
        calc.setFont(new Font("Arial", 16));
        calc.setAlignment(Pos.BOTTOM_CENTER);
        Label lblResult = new Label("Result:");
        lblResult.setFont(new Font("Arial", 16));
        HBox layoutBottom = new HBox(calc, lblResult);
        layoutBottom.setAlignment(Pos.BOTTOM_CENTER);
        root.setBottom(layoutBottom);

        //set events
        addButton.setOnMouseClicked(event -> {
            try {
                if (!itemName.getText().equals("")) {
                    Item i = new Item(itemName.getText(), Double.valueOf(itemCost.getText()));
                    items.add(i);
                    reloadListViews();
                    itemName.setText("");
                    itemCost.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        addPersonButton.setOnMouseClicked(event -> {
            try {
                if (!personName.getText().equals("")) {
                    Person p = new Person(personName.getText());
                    people.add(p);
                    reloadListViews();
                    personName.setText("");
                    for (Item i : items)
                        i.setPersons(people);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        calc.setOnMouseClicked(event -> calculate());
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void reloadListViews() {
        list.clear();
        list.addAll(items);
        listPeople.clear();
        people.forEach(person -> listPeople.add(person.toString()));
    }

    private void calculate() {
        double S[] = new double[people.size()];
        for(int i = 0; i < S.length; i++) {
            double s[] = {0};
            final int fi = i;
            items.forEach(item -> s[0] += getCostForEachPerson(item, fi));
            S[i] = s[0];
        }

        for(int i = 0; i < people.size(); i++) {
            people.get(i).setMoney(S[i]);
        }

        reloadListViews();
    }

    private double getCostForEachPerson(Item item, int i) {
        if (item.getPersons().get(i).isSelected()) {
            int count[] = {0};
            item.getPersons().forEach(personList -> {if(personList.isSelected()) count[0]++;});
            return item.getCost() / count[0];
        }
        return 0;
    }

    public static List<Person> getPeople() {
        return people;
    }
}
