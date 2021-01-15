package io.namoosori.travelclub.step3;

import io.namoosori.travelclub.step3.ui.menu.MainMenu;

public class StoryAssistant {
    //
    private void startStory() {
        //
        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }

    public static void main(String[] args) {
        //
        StoryAssistant assistant = new StoryAssistant();
        assistant.startStory();
    }
}
