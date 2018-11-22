package com.ocr.nicolas;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {

        // lecture du fichier config.properties.
        ReadPropertiesFile read = new ReadPropertiesFile();
        read.readConfigProperties();
        int nbrBoxesCombinationMysteryNumber = read.getNbrBoxesCombinationSearchNumber(); // = nbr de digit
        String developerMode = read.getDeveloperMode(); // developer mode?
        int nbrOfTryMysteryNumber = read.getNbrOfTryMysteryNumber(); // nomber of try

        // Set du jeu Recherche nombre
        MysteryNumber mysteryNumber = new MysteryNumber();
        mysteryNumber.setNbrDigit(nbrBoxesCombinationMysteryNumber);

        //declaration variable pour jouer
        int playGames = 1;
        int replay = 1;

        // Lancement des jeux:

        // boucle principale "demande du jeux"
        while (playGames == 1) {

            // Affichage du menu du choix des jeux.
            MenuDisplay display = new MenuDisplay();
            display.displayAskGamesMenu();

            // Recuperation variable du choix des jeux
            int gamesMenuChoice = display.displayGamesMenuChoice();
            if (gamesMenuChoice == 3) {
                System.exit(0);
            }

            // Affichage du menu du type de jeux  .
            display.displayAskTypeOfGame();

            // Recuperation variable du type de jeux
            int gameTypeChoice = display.displayGameTypeChoice();

            //possibilite de replay
            replay = 1;

            //boucle secondaire "demande de replay"
            while (replay == 1) {
                switch (gamesMenuChoice) {
                    case 1:
                        switch (gameTypeChoice) {
                            case 1:
                                //**********jeux searchnumber+/-****** mode challenger******

                                // Recuperation variable random ordinateur  -> randCompChallenger
                                String randCompChallenger = mysteryNumber.computerNbrCombination(nbrBoxesCombinationMysteryNumber, 0, 10);
                                logger.info("--------> aleatoire String computeur = " + randCompChallenger);

                                // Je donne le nombre d'essai possible
                                System.out.println("vous avez " + nbrOfTryMysteryNumber + " essai");

                                // Je lance le jeux
                                int nbrLoopChallengerMode = nbrOfTryMysteryNumber;
                                int winChallenger = 0;
                                int loopForChallengerMode = 0;

                                do {
                                    while (nbrLoopChallengerMode != 0) {
                                        loopForChallengerMode += 1;
                                        logger.info("");
                                        logger.info("********************   Boucle " + loopForChallengerMode + "    *********************");

                                        // J'affiche la demande de nombre utilisateur et recupere la valeur  -> nbrUserChallenger
                                        display.displayAskNumber(nbrBoxesCombinationMysteryNumber);
                                        String nbrUserChallenger = display.getUserChoiceStringExport();

                                        logger.info("nombre entré par l'utilisateur = " + nbrUserChallenger);

                                        // Je lance la comparaison et j'affiche le resultat
                                        String afterCompareChallenger = mysteryNumber.CompareTwoString(randCompChallenger, nbrUserChallenger);
                                        System.out.println(afterCompareChallenger);

                                        // je verifie si le Mode developper a été demandé
                                        if (developerMode.contains("true")) {
                                            System.out.println("(" + randCompChallenger + ")");
                                        } else {
                                            System.out.println("");
                                        }

                                        // je teste si gagnant ou perdant
                                        int winTestChallenger = mysteryNumber.getCounterForWinExport();
                                        if (winTestChallenger == 1) {
                                            System.out.println(" Exellent Vous avez gagné !!!");
                                            winChallenger = 1;
                                            nbrLoopChallengerMode = 0;
                                            logger.info("l'utilisateur a gagné contre l'ordinateur aprés " + loopForChallengerMode + " essais");
                                            System.out.println("");
                                            //remise a zero des parametres de choix
                                            gamesMenuChoice = 0;
                                            gameTypeChoice = 0;
                                            //lancement du menu du nouveau choix (1- rejouer; 2- Retour choix jeux; 3- quitter
                                            display.displayAskIfReplay();
                                            display.displayReplayChoice();
                                        } else {
                                            nbrLoopChallengerMode--;
                                            System.out.println(" il vous reste " + nbrLoopChallengerMode + " essai.");
                                        }
                                    }
                                } while (nbrLoopChallengerMode != 0);
                                if (winChallenger == 0) {
                                    System.out.println(" vous avez perdu...");
                                    System.out.println("-----> le nombre mystere etait: " + randCompChallenger);
                                    System.out.println("");
                                    logger.info("l'utilisateur a perdu");

                                    //remise a zero des parametres de choix
                                    gamesMenuChoice = 0;
                                    gameTypeChoice = 0;

                                    //lancement du menu pour nouveau choix (1- rejouer; 2- Retour choix jeux; 3- quitter
                                    display.displayAskIfReplay();
                                    int replayIntern = display.displayReplayChoice();
                                    switch (replayIntern) {
                                        case 1:
                                            //si l'utilisateur veut rejouer
                                            display.displayAskTypeOfGame();
                                            display.displayGameTypeChoice();
                                            gamesMenuChoice = 1;
                                            gameTypeChoice = 1;
                                            break;
                                        case 2:
                                            //l'utilisateur veut rechoisir un autre jeux
                                            gamesMenuChoice = 1;
                                            gameTypeChoice = 0;
                                            replay = 0;
                                            break;
                                        case 3:
                                            // l'utilisateur veut quitter
                                            System.exit(0);

                                        default:
                                            playGames = 0;
                                            break;
                                    }
                                    break;
                                }
                            case 2:
                                //**********jeux searchnumber+/-****** mode defenseur******

                                // Je donne le nombre d'essai possible
                                System.out.println("l'ordinateur a " + nbrOfTryMysteryNumber + " essai pour trouver ta combinaison");

                                // Je demande la suite de chiffre a l'utilisateur -> nbrUserString
                                display.displayAskNumber(nbrBoxesCombinationMysteryNumber);
                                String nbrUserDefender = display.getUserChoiceStringExport(); //-> variable string utilisateur = nbrUserDefender

                                logger.info("nombre entré par l'utilisateur = " + nbrUserDefender);

                                // Je lance le jeux
                                int nbrLoopDefenderMode = nbrOfTryMysteryNumber;
                                int winDefender = 0;
                                int loopForDefenderMode = 1;
                                do {
                                    // je met les variables (pour le random computeur) au niveau normal :
                                    int refinedMax = 9;
                                    int refinedMin = 0;
                                    int digitCompOk = 0;
                                    int CompOk = 0;
                                    String compDefenderStringFull = "";
                                    String refineStringCompDefender = "";

                                    // premier jet computeur en recuperant chaque digit dans un string
                                    for (int i = 0; i < nbrBoxesCombinationMysteryNumber; i++) {
                                        // je fais un random computeur 1 seul digit
                                        String nbrCompDigitDefenderString = mysteryNumber.computerNbrCombination(1, refinedMin, refinedMax);

                                        // je converti le string computeur en arraylist de integer -> listCompDefenderInt -> 1 seul digit
                                        List<Integer> listCompDigitDefenderInt = mysteryNumber.stringToArrayList(nbrCompDigitDefenderString);

                                        // je met ce chiffre dans une variable integer
                                        int nbrCompDigitDefenderInteger = listCompDigitDefenderInt.get(0);

                                        // je l'ajoute a une nouvelle liste computeur qui sera donc complete a la fin du cycle
                                        List<Integer> listCompDefenderIntFull = new ArrayList<>();
                                        listCompDefenderIntFull.add(nbrCompDigitDefenderInteger);

                                        // je l'ajoute dans une variable String: String Computeur --> compDefenderIntFull
                                        compDefenderStringFull = compDefenderStringFull + String.valueOf(listCompDefenderIntFull.get(0));
                                        logger.info("variable computeur premier jet " + compDefenderStringFull );
                                    }

                                    /// je lancer la comparaison des deux string pour affichage sur la console ( !! user en premier pour inversé)
                                    String displayResultCompareBase = mysteryNumber.CompareTwoString(nbrUserDefender,compDefenderStringFull);
                                    System.out.println(displayResultCompareBase + "( computeur = " + compDefenderStringFull + ")");
                                    logger.info(" 1er comparaison " + displayResultCompareBase);

                                    while (loopForDefenderMode > 0 && loopForDefenderMode != nbrOfTryMysteryNumber) {

                                        logger.info("");
                                        logger.info("********************   Boucle " + loopForDefenderMode + "    *********************");

                                        //boucle for pour chaque digit
                                        for (int i = 0; i < nbrBoxesCombinationMysteryNumber; i++) {
                                            //lecture des variable d'affinage


                                            // !!!!!! Comparaison  digit par digit !! pour avoir mes variables pour prochain random computeur (attention je met en premier le user !! pour inversé le resultat + et -)

                                            // je converti le string user en arraylist de integer -> listUserDefenderInt -> Il peut y en avoir plusieurs
                                            List<Integer> listUserDefenderInt = mysteryNumber.stringToArrayList(nbrUserDefender);

                                            // je converti le string computer en arraylist de integer -> listCompDefenderInt -> Il peut y en avoir plusieurs
                                            List<Integer> listCompDefenderInt = mysteryNumber.stringToArrayList(compDefenderStringFull);

                                            // je fais une liste avec un seul digit user -> listUserDigitDefenderInt (digit user)
                                            List<Integer> listUserDigitDefenderInt = new ArrayList<>();
                                            int digit1 = listUserDefenderInt.get(i);
                                            listUserDigitDefenderInt.add(digit1);

                                            // je fais une liste avec un seul digit comp -> listCompDigitDefenderInt (digit computer)
                                            List<Integer> listCompDigitDefenderInt = new ArrayList<>();
                                            int digit2 = listCompDefenderInt.get(i);
                                            listCompDigitDefenderInt.add(digit2);

                                            // je compare digit par digit et je recupere les variables affiné au fur et a mesure pour le prochain random (en premier le user !! pour inversé)

                                            // variable affiné suivant l'ancien random
                                            if (refinedMax > mysteryNumber.getRefinedMaxExport()) {
                                                refinedMax = mysteryNumber.getRefinedMaxExport();
                                                logger.info("limites max affiné suivant l'ancien random " + refinedMax);
                                            }
                                            if (refinedMin < mysteryNumber.getRefinedMinExport()) {
                                                refinedMin = mysteryNumber.getRefinedMinExport();
                                                logger.info("limites min affiné suivant l'ancien random " + refinedMin);
                                            }


                                            // je peux donc comparé les deux listes de 1 digit
                                            //String compareDigitWithRefine = mysteryNumber.compareTwoArrayList(listUserDigitDefenderInt, listCompDigitDefenderInt);

                                            // je recupere la valeur de la valeur ok
                                            digitCompOk = mysteryNumber.getDigitCompOkExport();
                                            logger.info("digit computeur ok = " + digitCompOk);

                                            // je peux recuperer la variables pour affiner le prochain random de ce digit

                                            // j'enleve la valeur user des variable (seul le digit computeur va varier)
                                            if (refinedMax == digit1) {
                                                refinedMax = 0;
                                            }
                                            if (refinedMin == digit1) {
                                                refinedMin = 0;
                                            }

                                            // je les ajoute dans une liste au fur et a mesure du nombre de digit -> list donc avec indication affiné ou gardé
                                            // je crée une liste pour le nombre de digit
                                            List<Integer> nbrDigitList = new ArrayList<>();

                                            // je crée une  liste hashmap pour metre l'info de chaque digit
                                            Map<String, Integer> indicateRefineAndEqualMap = new HashMap<String, Integer>();
                                            // J'ajoute dans cette liste uniquement ce qui change pour ce digit et je l'ajoute dans la list nbrDigit (une hash map dans une list)
                                            if (refinedMin != 0) {
                                                nbrDigitList.add(indicateRefineAndEqualMap.put("refinedMin", refinedMin));
                                            }
                                            if (refinedMax != 0) {
                                                nbrDigitList.add(indicateRefineAndEqualMap.put("refinedMax", refinedMax));
                                            }
                                            if (digitCompOk != 0) {
                                                nbrDigitList.add(indicateRefineAndEqualMap.put("digitCompOk", digitCompOk));
                                            }
                                            logger.info("hashmap = " + indicateRefineAndEqualMap);

                                            // je vais faire un random affiner pour chaque digit en String
                                            // je consulte la liste avec la hashmap dedans et je lance du random affiné par digit au besoin
                                            // je cree une list pour affiché les resultats affiné
                                            List<Integer> nbrListRefine = new ArrayList<>();

                                            // je cree une list  pour afficher les resultats ok
                                            List<Integer> listNumbCompOk = new ArrayList<>();

                                            // pour chaque digit dans la liste avec le nombre de digit + hashmap
                                            int refineNumberMin = 10;
                                            int refineNumberMax = 10;
                                            int refineNumberMinAndMax = 10;
                                            if (indicateRefineAndEqualMap.containsKey("refinedMin") && !indicateRefineAndEqualMap.containsValue(10)) {
                                                refineNumberMin = indicateRefineAndEqualMap.get("refinedMin") + (int) (Math.random() * ((9 - indicateRefineAndEqualMap.get("refinedMin")) + 1));
                                                nbrListRefine.add(refineNumberMin);
                                            }
                                            if (indicateRefineAndEqualMap.containsKey("refinedMax")&& !indicateRefineAndEqualMap.containsValue(10)) {
                                                refineNumberMax = (int) (Math.random() * ((indicateRefineAndEqualMap.get("refinedMax") + 1)));
                                                nbrListRefine.add(refineNumberMax);
                                            }
                                            if (indicateRefineAndEqualMap.containsKey("digitCompOk")) {
                                                listNumbCompOk.add(digitCompOk);
                                                CompOk += 1;
                                            }
                                            if (indicateRefineAndEqualMap.containsKey("refinedMin") && indicateRefineAndEqualMap.containsKey("refinedMax")) {
                                                refineNumberMinAndMax = indicateRefineAndEqualMap.get("refinedMin") + (int) (Math.random() * (((indicateRefineAndEqualMap.get("refinedMax")) - (indicateRefineAndEqualMap.get("refinedMin"))) + 1));
                                                nbrListRefine.add(refineNumberMinAndMax);
                                            }
                                            // j'enleve l'ancien Strin affiné
                                            refineStringCompDefender = "";
                                            // j'ajoute le digit affiné en string
                                            if (nbrListRefine.get(0) != null) {
                                                int newInt = nbrListRefine.get(0);
                                                refineStringCompDefender = refineStringCompDefender + newInt;
                                            }
                                        }
                                        // j'affiche le string affiné
                                        System.out.println(refineStringCompDefender);
                                        logger.info("aléatoire computeur affiné boucle(" + loopForDefenderMode + ") = " + refineStringCompDefender);

                                        // j'incremente la boucle
                                        loopForDefenderMode += 1;

                                        //je regarde si l'ordinateur à gagné pour finir
                                        if (CompOk == nbrBoxesCombinationMysteryNumber) {
                                            System.out.println("L'ordinateur à gagné aprés " + loopForDefenderMode + "essais");
                                        }
                                    }

                                    }while (loopForDefenderMode > 0 && loopForDefenderMode != nbrOfTryMysteryNumber);
                                System.out.println(" l'ordinateur n'a pas trouvé ta combinaison aprés " + nbrOfTryMysteryNumber + " essais tu a donc gagné !!!");
                                System.exit(0);
                                    //todo faire la boucle pour rejouer ou relancer un autre jeux.
                                }
                        }
                }
            }
        }
    }










