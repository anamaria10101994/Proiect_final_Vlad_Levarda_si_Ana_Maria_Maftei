package ro.siit.Proiect_AnaMaria_Maftei_Vlad_Levarda;

import java.io.Serializable;
import java.util.UUID;

    public class Produs implements Comparable<Produs>, Serializable {
        private static final long serialVersionUID = -6154641043647697070L;
        private final UUID id;
        private String numeprodus;
        private int cantitate;


        public Produs(String numeprodus, int cantitate) {
            this.id = UUID.randomUUID();
            this.numeprodus = numeprodus;
            this.cantitate = cantitate;


        }

        public UUID getId() {
            return id;
        }

        public String getNumeprodus() {
            return numeprodus;
        }

        public int getCantitate() {
            return cantitate;
        }



        @Override
        public String toString() {
            return "Produs{" +
                    "id=" + id +
                    ", numeprodus='" + numeprodus + '\'' +
                    ", cantitate=" + cantitate +
                    '}';
        }

        @Override
        public int compareTo(Produs o){
            return this.getNumeprodus().compareTo(o.getNumeprodus());
        }

        public void setNumeprodus(String numeprodus) {
            this.numeprodus = numeprodus;
        }

        public void setCantitate(int cantitate) {
            this.cantitate = cantitate;
        }

        public int vindeProdus(int cantitateVanzare) {
            if (cantitateVanzare > 0 && this.cantitate >= cantitateVanzare) {
                this.cantitate -= cantitateVanzare;
                return this.cantitate;
            }
            // Întoarce -1 pentru a indica că nu s-a putut realiza vânzarea
            return -1;
        }
    }

