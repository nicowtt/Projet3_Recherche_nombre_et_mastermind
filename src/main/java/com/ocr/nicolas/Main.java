package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {

    static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {

        // lecture du fichier config.properties.
        ReadPropertiesFile read = new ReadPropertiesFile();
        read.readConfigProperties();
        int nbrBoxesCombinationMysteryNumber = read.getNbrBoxesCombinationSearchNumber(); // = nbr de digit
        String developerMode = read.getDeveloperMode(); // developer mode?
        int nbrOfTryMysteryNumber = read.getNbrOfTryMysteryNumber(); // nomber of try

        // Affichage du menu du choix des jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskGamesMenu();

        // Recuperation variable du choix des jeux
        int gamesMenuChoice = display.displayGamesMenuChoice();

        // Affichage du menu du type de jeux  .
        display.displayAskTypeOfGame();

        // Recuperation variable du type de jeux
        int gameTypeChoice = display.displayGameTypeChoice();

        // Set du jeu Recherche nombre
        MysteryNumber mysteryNumber = new MysteryNumber();
        mysteryNumber.setNbrDigit(nbrBoxesCombinationMysteryNumber);


        // Lancement des jeux:
        switch (gamesMenuChoice) {
            case 1:
                switch (gameTypeChoice) {
                    case 1:
                        //**********jeux searchnumber+/-****** mode challenger******

                        // Recuperation variable random ordinateur  -> randomNumberString
                        String randomNumberString = mysteryNumber.computerNbrCombination(nbrBoxesCombinationMysteryNumber);
                        logger.info("--------> aleatoire String computeur = " + randomNumberString);

                        // Je donne le nombre d'essai possible
                        System.out.println("vous avez " + nbrOfTryMysteryNumber + " essai");

                        // Je lance le jeux
                        int nbrLoop = nbrOfTryMysteryNumber;
                        int win = 0;
                        int loopForInformation = 0;

                        do {
                            while (nbrLoop != 0) {
                                loopForInformation += 1;
                                logger.info("");
                                logger.info("********************   Boucle " + loopForInformation + "    *********************");

                                // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserString
                                display.displayAskNumber(nbrBoxesCombinationMysteryNumber);
                                String nbrUserString = display.getUserChoiceStringExport();

                                logger.info("nombre entré par l'utilisateur (class main) = " + nbrUserString);

                                // Je lance la comparaison des deux nombres (class MysteryNumber)
                                mysteryNumber.CompareTwoString(randomNumberString, nbrUserString, nbrBoxesCombinationMysteryNumber);

                                // J'affiche la réponse de la comparaison
                                String afterCompareImport = mysteryNumber.getAfterCompareExport();
                                System.out.println("" + afterCompareImport);

                                // je verifie si le Mode developper a été demandé
                                if (developerMode.contains("true")) {
                                    System.out.println("(" + randomNumberString + ")");
                                } else {
                                    System.out.println("");
                                }

                                // je teste si gagnant ou perdant
                                int winTest = mysteryNumber.getCounterForWinExport();
                                if (winTest == 1) {
                                    System.out.println(" Exellent Vous avez gagné !!!");
                                    win = 1;
                                    nbrLoop = 0;
                                } else {
                                    nbrLoop--;
                                    System.out.println(" il vous reste " + nbrLoop + " essai.");
                                }
                            }
                        } while (nbrLoop != 0);
                        if (win == 0) {
                            System.out.println(" vous avez perdu");
                            System.out.println("le nombre mystere etait: " + randomNumberString);
                        } else {

                        }
                }
        }
    }
}







