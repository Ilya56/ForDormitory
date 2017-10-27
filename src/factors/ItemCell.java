package factors;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import models.Item;
import models.PersonList;

import java.util.*;

/**
 * Created by Ilya on 16.09.2017 21:47, ForDormitory.
 */
public class ItemCell extends ListCell<Item> {
    private VBox root = new VBox();

    private Label info = new Label();

    private ListView<PersonList> listView = new ListView<>();
    //private PersonList p0 = new PersonList("Name");
    private List<PersonList> people = new ArrayList<>();

    private List<PeopleCell> cells = new ArrayList<>();

    public ItemCell() {
        super();
        listView.setCellFactory(params -> new PeopleCell(this));
        root.getChildren().addAll(info, listView);
    }

    @Override
    protected void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null)
            setGraphic(null);
        else {
            info.setText(item.getName() + ", " + item.getCost());

            people.clear();
            //people.add(p0);
            people.addAll(item.getPersons());

            listView.setItems(FXCollections.observableArrayList(people));
            listView.setPrefHeight(120);
            listView.setPrefWidth(200);

            setGraphic(root);
        }
    }

    /*void reloadChecks(PersonList item) {
        final boolean[] all = {true};
        people.forEach(person -> { if (!person.isSelected()) all[0] = false; });
        p0.setSelected(all[0]);
        //listView.setItems(FXCollections.observableArrayList(people));
        //cells.forEach(peopleCell -> people.forEach(peopleCell::updateCheck));
        for (int i = 0; i < Math.min(cells.size(), people.size()); i++) {
            cells.get(i).updateCheck(people.get(i));
        }
    }*/

    void addCell(PeopleCell cell) {
        cells.add(cell);
    }
}
