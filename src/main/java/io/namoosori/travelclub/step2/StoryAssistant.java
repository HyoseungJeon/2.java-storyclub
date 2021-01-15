package io.namoosori.travelclub.step2;

import io.namoosori.travelclub.step2.ui.menu.MainMenu;

public class StoryAssistant {

    private void start(){
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }

    public static void main(String[] args) {
        StoryAssistant assistant = new StoryAssistant();
        assistant.start();
    }
}
