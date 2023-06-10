package view.confirmationPTUI;

import java.util.Scanner;

import model.character.Character;
import model.map.room.tile.Occupier;
import view.GamePTUI;

public class OccupierPTUI implements GamePTUI {
    private Character character;
    private Occupier occupier;
    private String description;
    
    public OccupierPTUI(Character character, Occupier occupier, String description) {
        this.character = character;
        this.occupier = occupier;
        this.description = description;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = ""; 
        System.out.println("Would you like to: " + this.description);
        
        while(!input.equals("yes") && !input.equals("no")) {
            input = scanner.nextLine().toLowerCase();
        }
        scanner.close();

        if(input.equals("yes")) {
            this.occupier.interact(this.character);
        }
    }
}
