package fr.gouv.esante.pml.smt.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Mapping{
    @JsonProperty("USFDA_SPL") 
    public USFDASPL uSFDA_SPL;
    @JsonProperty("ICH_E2B") 
    public USFDASPL iCH_E2B;
}
