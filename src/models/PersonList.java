package models;

import com.sun.istack.internal.NotNull;
import javafx.beans.DefaultProperty;

/**
 * Created by Ilya on 17.09.2017 16:45, ForDormitory.
 */
public class PersonList extends Person {
    private boolean selected;

    public PersonList(String name, boolean selected) {
        super(name);
        this.selected = selected;
    }

    public PersonList(String name) {
        super(name);
        selected = true;
    }

    public PersonList(Person p) {
        super(p.getName());
        selected = true;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
