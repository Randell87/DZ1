package org.skypro.skyshop;

public interface Searchable {

    public String searchTerm();

    public String typeOfContent();

    public String getName();

    default String getStringRepresentation(){
        return getName() + " - " + typeOfContent();
    }
}
