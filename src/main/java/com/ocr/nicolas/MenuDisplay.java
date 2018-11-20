package com.ocr.nicolas;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.ocr.nicolas.Log4j.logger;

public class MenuDisplay {

    Scanner sc = new Scanner(System.in);

    private String userChoiceStringExport;

    public String getUserChoiceStringExport() { return userChoiceStringExport; }

    /**
     * Display ask games menu.
     */
    public void displayAskGamesMenu() {
        System.out.println("Voulez-vous jouer:");
        System.out.println("1-> Recherche +/-");
        System.out.println("2-> Mastermind");
        System.out.println("3-> quitter");
    }

    /**
     * Display and confirmation of games choice.
     */
    public int displayGamesMenuChoice() {

        boolean responseIsGood;
        //Combien de choix maximum pour limité l'entree utilisateur
        int nbrChoiceMax = 3;
        int gameChoice = forceProposedChoice(nbrChoiceMax);

        do {
            switch (gameChoice) {
                case 1:
                    System.out.println("Vous avez choisi le jeux Recherche +/- ");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 2:
                    System.out.println("Vous avez choisi le Mastermind");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 3:
                    System.out.println("arrêt des jeux");
                    System.exit(0);
                    responseIsGood = true;
                    break;
                default:
                    responseIsGood = false;
                    break;
            }
        } while (!responseIsGood);

        logger.info("Choix du jeux-> " + gameChoice + " (1- Recherche+/- ; 2- Mastermind; 3 -arret)");
        return gameChoice;
    }

    /**
     * Display Type of Games.
     */
    public void displayAskTypeOfGame() {

        System.out.println("");
        System.out.println("****** Choisissez le mode de jeux: *******");
        System.out.println("1-Challenger -> trouve le nombre mystère de l'ordinateur");
        System.out.println("2-Défenseur -> L'ordinateur doit trouver ton nombre");
        System.out.println("3-Duel");
    }

    /**
     * Display and confirmation of Type games choice.
     */
    public int displayGameTypeChoice() {

        boolean responseIsGood;

        // je demande a l'utilisateur de choisir
        int nbrChoiceMax = 3;
        int gameTypeChoice = forceProposedChoice(nbrChoiceMax);

        do {
            switch (gameTypeChoice) {
                case 1:
                    System.out.println("Vous avez choisi le mode Challenger");
                    System.out.println("************************************");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 2:
                    System.out.println("Vous avez choisi le mode Défenseur");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 3:
                    System.out.println("Vous avez choisi le mode Duel");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                default:
                    responseIsGood = false;
                    break;
            }
        } while (!responseIsGood);
        logger.info("Mode de jeux-> " + gameTypeChoice + " (1- Challenger ; 2- défenseur ; 3- Duel)");
        return gameTypeChoice;
    }

    /**
     * display first choice of number on String
     *
     * @param nbr number of boxes (digit)
     */
    public void displayAskNumber(int nbr) {

        // demande et verification nombre de digit entrée utilisateur (re-demande si pas assez ou trop de nombre ou lettre(s))

        boolean responseIsGood;
        responseIsGood = false;
        int nbrDigit = nbr;
        String nbrMax = "";
        int counterTotalLetter = 0;
        int counterOfDigit = 0;
        int tooManyNbr = 0;

        System.out.println("Choisissez une suite de " + nbr + " chiffre :");

        do {
            try {
                String userChoiceString = sc.next();

                // Verification si y a trop de caractères en entrée
                if ( userChoiceString.length() > nbrDigit) {tooManyNbr++;}

                if (tooManyNbr != 1 ) {
                    userChoiceStringExport = userChoiceString;

                    String userChoiceForCountString = userChoiceString;
                    while (responseIsGood != true) {
                        // je prend le premier caractère
                        char letter = userChoiceForCountString.charAt(counterTotalLetter);
                        // je le converti en string
                        String letterString = String.valueOf(letter);
                        // je le converti en Int
                        int letterInt = Integer.parseInt(letterString);
                        // j'incrémente mon compteur de nombre de chiffre
                        counterTotalLetter++;
                        // je compare par rapport au nombre de digit
                        if (counterTotalLetter == nbrDigit) {responseIsGood = true;}
                    }
                }else {
                    System.out.println("oupss ! trop de caractères, veuillez choisir une suite de " + nbrDigit + " chiffres: ");
                    logger.info("- INFO - trop de caractère entrée par l'utilisateur");
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Vous devez rentrer " + nbr + " chiffres:");
                logger.info("- INFO - pas assez de caractère entrée par l'utilisateur");
            } catch (NumberFormatException e ) {
                System.out.println("Lettre(s) non acceptée(s), veuillez choisir une suite de " + nbrDigit + " chiffres: ");
                logger.info("- INFO - Lettre(s) entrée par l'utilisateur");
            }
            tooManyNbr = 0;

            }while (responseIsGood != true) ;
    }

    /**
     * display end of game menu
     */
    public void displayAskIfReplay () {
        System.out.println("****** voulez-vous rejouer ? *******");
        System.out.println(" 1 -> oui je veux rejouer au meme jeux");
        System.out.println(" 2 -> retour a l'ecran de choix des jeux");
        System.out.println(" 3 -> quitter");
    }


    /**
     * for display replay choice
     * @return replay choice (int)
     */
    public int displayReplayChoice () {
        boolean responseIsGood;
        int nbrChoiceMax = 3;
        int replayChoice = forceProposedChoice(nbrChoiceMax);

        do {
            switch (replayChoice) {
                case 1:
                    System.out.println("Vous avez choisi de rejouer au jeux Recherche +/- ");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 2:
                    System.out.println("Vous avez le retour a l'ecran du choix des jeux");
                    System.out.println("");
                    responseIsGood = true;
                    break;
                case 3:
                    System.out.println("Vous avez choisi de quitter");
                    System.exit(0);
                    System.out.println("");
                    responseIsGood = true;
                    break;
                default:
                    responseIsGood = false;
                    break;
            }
        } while (!responseIsGood);
        logger.info("Choix du replay-> " + replayChoice + " (1- Recherche+/- ; 2- choix d'un autre jeux; 3- quitter)");
        return replayChoice;
    }

    private int forceProposedChoice(int nbrMax) {
        boolean responseIsGood;
        int forcedChoice = 0;

        do {
            try {
                System.out.println("-> Veuillez choisir parmi les choix proposés:");
                forcedChoice = sc.nextInt();
                if (forcedChoice <= 0 || forcedChoice > nbrMax) {
                    forcedChoice = 0;
                    responseIsGood = false;
                }else {
                    responseIsGood = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("erreur chiffre uniquement");
                sc.next();
                responseIsGood = false;
            }
        }while (!responseIsGood);

        return forcedChoice;

    }
}





