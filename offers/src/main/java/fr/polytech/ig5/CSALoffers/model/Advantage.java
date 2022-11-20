package fr.polytech.ig5.CSALoffers.model;

import java.util.Objects;

public class Advantage {
    private int advantageId;
    private String libelle;

    public Advantage(int advantageId, String libelle) {
        this.advantageId = advantageId;
        this.libelle = libelle;
    }

    public Advantage() {
        this.advantageId = -1;
        this.libelle = "";
    }

    public int getAdvantageId() {
        return advantageId;
    }

    public void setAdvantageId(int advantageId) {
        this.advantageId = advantageId;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advantage advantage = (Advantage) o;
        return advantageId == advantage.advantageId && Objects.equals(libelle, advantage.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(advantageId, libelle);
    }
}
