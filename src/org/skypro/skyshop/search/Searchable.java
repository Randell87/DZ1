package org.skypro.skyshop.search;

public interface Searchable {

    public String searchTerm();

    public String typeOfContent();

    public String getName();

    default String getStringRepresentation(){
        return getName() + " - " + typeOfContent();
    }
}
