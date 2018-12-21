package com.ocr.nicolas;

public class CommonUseToGames extends Games{

    protected CommonUseToGames() {
        // lecture config.propertie et variable a zero
        ReadPropertiesFile read = new ReadPropertiesFile();
        nbrDigit = read.getNbrDigit();
        developerMode = read.getDeveloperMode(); // developer mode?
        nbrOfTry = read.getNbrOfTry();
        nbrMaxOnDigit = read.getNbrMaxOnDigit();
        boolean isWin = false;
    }

    public void playGames() {

        int replay = 3;

        // objets
        MenuDisplay display = new MenuDisplay();
        SearchNumber searchNumber = new SearchNumber();
        Mastermind mastermind = new Mastermind();

        // info
        logger.info("info dans la class mere Games (nbrDigit= "+ nbrDigit +" nbrOfTry = " + nbrOfTry +" developerMode =" + developerMode + "nbrMaxOnDigit = " + nbrMaxOnDigit);

        // boucle si replay
        do {
            // Affichage du menu du choix des jeux.
            display.displayAskGamesMenu();

            // Recuperation variable du choix des jeux
            int gamesMenuChoice = display.displayGamesMenuChoice();
            if (gamesMenuChoice == 3) {
                System.exit(0);
            }

            switch (gamesMenuChoice) {
                case 1:
                    while (gamesMenuChoice == 1) {
                        replay = searchNumber.playSearchNumber();
                        break;
                    }
                case 2:
                    while (gamesMenuChoice == 2) {
                        replay = mastermind.playMastermind();
                        break;
                    }
                default:
                    break;
            }
        } while (replay >= 2);

    }



    /**
     * for make random computer number(s) combination
     *
     * @return random digit number (type of character string)
     */
    protected String computerNbrCombination(int min, int max) {

        String finalRandomDigitNumberString = "";
        int counterNbrDigit = this.nbrDigit;

        while (counterNbrDigit >= 1) {

            //int de 0 a 9 (base 10) avec min et max
            int base10RandomDigitNumber = min + (int) (Math.random() * ((max - min) + 1));
            //logger.info("base 10 random =" + base10RandomDigitNumber);

            // je converti le chiffre en string
            String base10RandomDigitNumberString = String.valueOf(base10RandomDigitNumber);

            // Je l'ajoute au string computer final
            finalRandomDigitNumberString = finalRandomDigitNumberString + base10RandomDigitNumberString;

            // j'incremente le compteur
            counterNbrDigit--;
        }
        return finalRandomDigitNumberString;
    }

    /**
     * For redirection of replay or leave
     */
    protected int replay() {

        //variable
        int replay = 3;

        // objets
        MenuDisplay display = new MenuDisplay();

        // affichage console for replay et redirection
        display.displayAskIfReplay();
        int replayIntern = display.displayReplayChoice();
        if (replayIntern == 1) {replay = 1;}
        if (replayIntern == 2) {replay = 2;}
        if (replayIntern == 3) {System.exit(0);}

        return replay;
    }

    /**
     * For redirection of replay or leave
     * @return enum
     */
    protected static Replay replayEnum() { //todo for check with mentor

        // objets
        MenuDisplay display = new MenuDisplay();
        Replay replayEnum = Replay.EXIT;

        // affichage console for replay et redirection
        display.displayAskIfReplay();
        int replayIntern = display.displayReplayChoice();
        if (replayIntern == 1) {replayEnum = Replay.REPLAY;}
        if (replayIntern == 2) {replayEnum = Replay.GAMESCHOICE;}
        if (replayIntern == 3) {System.exit(0);}

        return replayEnum;
    }
}
