package ro.siit.Proiect_AnaMaria_Maftei_Vlad_Levarda;

import java.io.*;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;


public class GestionareStoc {
        private static Logger LOGGER =  LogManager.getLogger(GestionareStoc.class);
        private static Scanner keyboard = new Scanner(System.in);
        private static Stoc stoc = new Stoc();

        public static void main(String[] args) {
            stoc = readProduseFromFile();
            if (stoc == null){
                stoc = new Stoc();
            }
            Actiune actiune;

            do {
                actiune = getActionFromKeyboard();
                switch(actiune){
                    case A:
                        //adauga produs
                        System.out.println("Adauga numeprodus");
                        String numeprodus = keyboard.nextLine();
                        System.out.println("Adauga cantitate");
                        int cantitate = Integer.parseInt(keyboard.nextLine());
                        stoc.adaugaProdus(new Produs(numeprodus, cantitate));
                        break;
                    case V:
                        //vinde produs
                        System.out.println("Adauga nume produs pentru vanzare");
                        String numeProdusVanzare = keyboard.nextLine();
                        System.out.println("Adauga cantitate pentru vanzare");
                        int cantitateVanzare = Integer.parseInt(keyboard.nextLine());

                        int cantitateRamasă = stoc.vindeProdus(numeProdusVanzare, cantitateVanzare);

                        if (cantitateRamasă >= 0) {
                            System.out.println("Vanzare realizata cu succes. Cantitate ramasa: " + cantitateRamasă);
                        } else if (cantitateRamasă == -1) {
                            System.out.println("Produsul nu exista in stoc.");
                        } else {
                            System.out.println("Nu există suficientă cantitate pentru vânzare.");
                        }
                        break;

                    case L:
                        //listeaza produsele
                        for (Produs produs: stoc.getProduse()){
                        System.out.println(produs);
                        }
                        break;
                    case E:
                        //editare
                        System.out.println("Adauga id");
                        String id = keyboard.nextLine();
                        System.out.println("Adauga numeprodus");
                        numeprodus = keyboard.nextLine();
                        System.out.println("Adauga cantitate");
                        cantitate = Integer.parseInt(keyboard.nextLine());
                        stoc.actualizareProdus(UUID.fromString(id), numeprodus, cantitate);
                        break;
                    case S:
                        //sterge
                        System.out.println("Adauga id");
                        id = keyboard.nextLine();
                        System.out.println("Sterse " + stoc.stergeProdus(UUID.fromString(id)));
                }
            }while(Actiune.F != actiune);
            saveProduseToFile();
        }
        public static void printMenu() {
                System.out.println("A - Adauga  produs");
                System.out.println("V - Vinde produs");
                System.out.println("L - Listeaza toate produsele");
                System.out.println("E - Editeaza produs");
                System.out.println("S - Sterge produs");
                System.out.println("F - Finalizeaza");

            }
            private static Actiune getActionFromKeyboard(){
            printMenu();
            Actiune actiune = null;
            boolean error;
                do {
                    System.out.println("Alege o optiune:");
                    error = false;
                    try {
                        actiune = Actiune.valueOf(keyboard.nextLine().toUpperCase());
                    } catch (IllegalArgumentException illegalArgumentException) {
                        error = true;
                    }
                }while (error);
                return actiune;
            }
            private static void saveProduseToFile(){
                try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("stoc.data"))){
                    outputStream.writeObject(stoc);
                }catch (IOException ioException){
                  LOGGER.error("Error saving produse",ioException);
                }
            }
            private static Stoc readProduseFromFile(){
                Stoc stoc =null;
                try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("stoc.data"))){
                    stoc  = (Stoc) inputStream.readObject();
                }catch (IOException | ClassNotFoundException readException){
                    LOGGER.error("Error reading produse",readException);
                }
                return  stoc;
              }
               }
        




