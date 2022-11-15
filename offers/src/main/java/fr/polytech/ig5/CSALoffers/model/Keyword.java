package fr.polytech.ig5.CSALoffers.model;

import java.util.Objects;

public class Keyword {
    private int keywordId;
    private String libelle;

    public Keyword(int keywordId, String libelle) {
        this.keywordId = keywordId;
        this.libelle = libelle;
    }

    public Keyword() {
        this.keywordId = -1;
        this.libelle = "";
    }

    public int getKeywordId() {
        return keywordId;
    }

    public void setKeywordId(int keywordId) {
        this.keywordId = keywordId;
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
        Keyword keyword = (Keyword) o;
        return keywordId == keyword.keywordId && Objects.equals(libelle, keyword.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywordId, libelle);
    }
}
