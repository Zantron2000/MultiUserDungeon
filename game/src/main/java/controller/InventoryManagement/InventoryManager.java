package controller.InventoryManagement;

import java.util.ArrayList;

public interface InventoryManager {
    public ArrayList<String> getChoices();

    public String executeChoice(String choice);

    public ArrayList<String> openInventories();

    public String interact();
}
