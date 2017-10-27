package factors;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;

import models.PersonList;

/**
 * Created by Ilya on 16.09.2017 22:07, ForDormitory.
 */
class PeopleCell extends ListCell<PersonList> {
    private BorderPane root = new BorderPane();
    private Label name = new Label();
    private CheckBox pay = new CheckBox();
    private ItemCell cell;

    PeopleCell(ItemCell cell) {
        super();
        root.setLeft(name);
        root.setRight(pay);
        this.cell = cell;
        cell.addCell(this);
    }

    @Override
    protected void updateItem(PersonList item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null)
            setGraphic(null);
        else {
            name.setText(item.getName());

            pay.setSelected(item.isSelected());
            pay.setOnAction(event -> { item.setSelected(pay.isSelected()); /*cell.reloadChecks(item); */ });

            setGraphic(root);
        }
    }

    /*void updateCheck(PersonList item) {
        if (item == null)
            setGraphic(null);
        else {
            pay.setSelected(item.isSelected());
            setGraphic(root);
        }
    }*/
}
