package ro.siit.Proiect_AnaMaria_Maftei_Vlad_Levarda;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.UUID;

public class Stoc implements Serializable {
    private static final long serialVersionUID = 3274302293074090609L;
    private Map<UUID, Produs> produse = new HashMap();

    public void adaugaProdus(Produs produs) {
        this.produse.put(produs.getId(), produs);
    }

    public List<Produs> getProduse() {
        List<Produs> produseList = new ArrayList<>();
        produseList.addAll(produse.values());
        Collections.sort(produseList);
        return produseList;
    }

    public void actualizareProdus(UUID id, String numeprodus, int cantitate) {
        Produs actualizareProdus = this.produse.get(id);
        actualizareProdus.setNumeprodus(numeprodus);
        actualizareProdus.setCantitate(cantitate);
        this.produse.put(actualizareProdus.getId(), actualizareProdus);

    }

    public Produs stergeProdus(UUID id) {
        return produse.remove(id);
    }

    public int vindeProdus(String numeProdus, int cantitateVanzare) {
        for (Produs produs : this.produse.values()) {
            if (produs.getNumeprodus().equalsIgnoreCase(numeProdus)) {
                // Găsit produsul după nume
                int cantitateRamasă = produs.vindeProdus(cantitateVanzare);

                if (cantitateRamasă >= 0) {
                    // Actualizează cantitatea în stoc
                    this.produse.put(produs.getId(), produs);
                    return cantitateRamasă;
                } else {
                    // Întoarce -2 pentru a indica că nu există suficientă cantitate pentru vânzare
                    return -2;
                }
            }
        }

        // Întoarce -1 pentru a indica că produsul nu a fost găsit în stoc
        return -1;
    }
}
