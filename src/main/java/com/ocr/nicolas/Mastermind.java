package com.ocr.nicolas;

import java.util.*;

public class Mastermind extends Games {

    protected int nbrMaxOnDigit;

    public Mastermind(int nbrDigit, int nbrOfTry, String developerMode, int nbrMaxOnDigit) {
        super(nbrDigit, nbrOfTry, developerMode);
        this.nbrMaxOnDigit = nbrMaxOnDigit;
    }

    Scanner sc = new Scanner(System.in);


    /**
     * For playing Mastermind Game
     */
    protected void playMastermind() {

        // Affichage du menu du type de jeux.
        MenuDisplay display = new MenuDisplay();
        display.displayAskTypeOfGame();

        // Recuperation variable du type de jeux
        int gameTypeChoice = display.displayGameTypeChoice();

        // objets
        MastermindChallenger mastermindChallenger = new MastermindChallenger(nbrDigit, nbrOfTry, developerMode, nbrMaxOnDigit);

        do
            switch (gameTypeChoice) {
                case 1:
                    while (gameTypeChoice == 1) {
                        mastermindChallenger.playChallengerModeMastermind();
                        display.displayAskTypeOfGame();
                        gameTypeChoice = display.displayGameTypeChoice();
                        break;
                    }
                case 2:
                    while (gameTypeChoice == 2) {
                        break;
                    }
                case 3:
                    while (gameTypeChoice == 3) {
                        break;
                    }
                default:
                    break;
            } while (gameTypeChoice == 1 || gameTypeChoice == 2 || gameTypeChoice == 3);
    }


    /**
     * For force user input combination ok
     *
     * @return user input combination ok (string)
     */
    protected String inputUserStringMast() {

        boolean responseIsGood = true;
        int digitOk;
        String inputUserFinal;

        //creation d'une list avec les chiffres possible
        List<Integer> listNbrOk = new ArrayList<>();

        //je met les chiffres ok a l'interieur
        int count = nbrMaxOnDigit;

        for (int i = 0; i < (nbrMaxOnDigit + 1); i++) {
            listNbrOk.add(count);
            count--;
        }
        logger.info("liste chiffres ok = " + listNbrOk);

        do {
            // entrée finale ok
            inputUserFinal = "";
            responseIsGood = true;

            // je fais venir l'entrée utilisateur
            String inputUser = sc.nextLine();

            // je compare chaque digit avec le tableau de chiffre ok sinon boucle
            int countTooManyDigit = 0;

            try {
                for (int i = 0; i < inputUser.length(); i++) {
                    char letter = inputUser.charAt(i);
                    String letterStr = String.valueOf(letter);
                    Integer letterInt = Integer.valueOf(letterStr);
                    // comparaison si il est dans la liste
                    for (int j = 0; j < listNbrOk.size(); j++) {
                        digitOk = listNbrOk.get(j);
                        if (digitOk == letterInt) {
                            inputUserFinal = inputUserFinal + letterStr;
                            countTooManyDigit++;
                        }
                    }
                }
                if (countTooManyDigit != nbrDigit) {
                    System.out.println("choisi une combinaison de " + nbrDigit + " chiffre (chiffre entre 0 et " + nbrMaxOnDigit + ")");
                    logger.info("mauvaise entrée utilisateur");
                    responseIsGood = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("lettre non accepté, tu doit choisir une combinaison de " + nbrDigit + " chiffre (chiffre entre 0 et " + nbrMaxOnDigit + ")");
                responseIsGood = false;
            }

        } while (!responseIsGood);
        logger.info(" entrée finale utilisateur = " + inputUserFinal);
        return inputUserFinal;

    }

    /**
     * For compare 2 String and put information's (present and good place) on hashMap
     *
     * @param pFirst first String to compare
     * @param pSecond second String to compare
     * @return hashMap with information
     */
    protected Map<String, Integer> compareTwoStringMastToHashMap(String pFirst, String pSecond) {

        // je crée une HashMap pour les futur résultats
        Map<String, Integer> hashMapWithCompareInfo = new HashMap<>();

        // je crée une ArrayList avec le premier paramètre
        List<Integer> pFirstList = new ArrayList<>();

        // je crée une seconde ArrayList avec le deuxiemes paramètre
        List<Integer> pSecondList = new ArrayList<>();

        // je rempli les deux array List ave les valeurs
        for (int i = 0; i < pFirst.length(); i++) {
            // pour pFirst
            char letter = pFirst.charAt(i);
            String letterStr = String.valueOf(letter);
            Integer letterInt = Integer.valueOf(letterStr);
            pFirstList.add(letterInt);

            // pour pSecond
            char letter2 = pSecond.charAt(i);
            String letter2Str = String.valueOf(letter2);
            Integer letter2Int = Integer.valueOf(letter2Str);
            pSecondList.add(letter2Int);
        }
        logger.info("liste a comparer = " + pFirstList + " et " + pSecondList);

        // je regarde si chiffre du premier est present dans l'autre liste et si il est a la bonne place

        int digitNumber = 0; //1er, 2eme, 3em etc... numero de digit
        int digitFirst; // digit pour comparer avec la deuxieme liste
        int digitSecond; // digit de comparaison
        int digitrepeat = 1; // in case of one digit is present > 1 fois

        for (int j = 0; j < pFirstList.size(); j++) {
            // j'increment le nombre de digit
            digitNumber++;
            // je prend la premiere valeur, premiere liste
            digitFirst = pFirstList.get(j);
            // je compare par rapport a l'autre liste
            for (int k = 0; k < pSecondList.size(); k++) {
                digitSecond = pSecondList.get(k);
                for (int l = 0; l < pSecondList.size(); l++) {
                    if (digitFirst == digitSecond) {
                        // je supprime les doublons
                        if (hashMapWithCompareInfo.containsValue(k + 1)) {
                            digitrepeat++;
                        } else {
                            hashMapWithCompareInfo.put("(" + digitrepeat + ")" + "Digit " + digitNumber + " présent a la place ", (k + 1));
                            digitrepeat++;
                        }
                    }
                }
            }
        }
        logger.info("hashMap avec les info d'emplacement " + hashMapWithCompareInfo);
        return hashMapWithCompareInfo;
    }

}
