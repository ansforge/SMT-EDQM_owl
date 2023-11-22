package fr.gouv.esante.pml.smt.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Source{
    @JsonProperty("<language>") 
    public List<Language> language;
}
