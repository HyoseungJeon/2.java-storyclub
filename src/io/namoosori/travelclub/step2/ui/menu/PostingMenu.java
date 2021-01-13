package io.namoosori.travelclub.step2.ui.menu;

import io.namoosori.travelclub.step2.ui.console.PostingConsole;
import io.namoosori.travelclub.util.Narrator;
import io.namoosori.travelclub.util.TalkingAt;

import java.util.Scanner;

public class PostingMenu {

    private PostingConsole postingConsole;

    private Scanner scanner;
    private Narrator narrator;

    public PostingMenu(){
        this.postingConsole = new PostingConsole();
        this.scanner = new Scanner(System.in);
        this.narrator = new Narrator(this, TalkingAt.Left);
    }

    public void show(){

        short inputNumber = 0;

        while (true){
            displayMenu();
            inputNumber = selectMenu();

            switch (inputNumber){
                case 1:
                    postingConsole.findBoard();
                    break;
                case 2:
                    postingConsole.register();
                    break;
                case 3:
                    postingConsole.findByBoardId();
                    break;
                case 4:
                    postingConsole.find();
                    break;
                case 5:
                    postingConsole.modify();
                    break;
                case 6:
                    postingConsole.remove();
                    break;
                case 0:
                    return;

                default:
                    narrator.sayln("Choose again!");
            }
        }
    }

    private void displayMenu(){
        narrator.sayln("");
        narrator.sayln("..............................");
        narrator.sayln(" Posting menu ");
        narrator.sayln("..............................");
        narrator.sayln(" 1. Find a board");
        narrator.sayln(" 2. Register a posting");
        narrator.sayln(" 3. Find postings in the board");
        narrator.sayln(" 4. Find a posting");
        narrator.sayln(" 5. Modify a posting");
        narrator.sayln(" 6. Remove a posting");
        narrator.sayln("..............................");
        narrator.sayln(" 0. Previous");
        narrator.sayln("..............................");
    }

    private short selectMenu(){
        System.out.println("Select: ");
        short menuNumber = scanner.nextShort();

        if(menuNumber >= 0 && menuNumber <= 6){
            scanner.nextLine();
            return menuNumber;
        }else {
            narrator.sayln("It's a invalid number --> " + menuNumber);
            return -1;
        }
    }
}
