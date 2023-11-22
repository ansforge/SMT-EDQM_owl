package fr.gouv.esante.pml.smt.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MappingMAP{
    @JsonProperty("PDF") 
    public List<CLASS> pDF;
    @JsonProperty("ROA") 
    public List<CLASS> rOA;
	/**
	 * @return the pDF
	 */
	public List<CLASS> getpDF() {
		return pDF;
	}
	/**
	 * @param pDF the pDF to set
	 */
	public void setpDF(List<CLASS> pDF) {
		this.pDF = pDF;
	}
	/**
	 * @return the rOA
	 */
	public List<CLASS> getrOA() {
		return rOA;
	}
	/**
	 * @param rOA the rOA to set
	 */
	public void setrOA(List<CLASS> rOA) {
		this.rOA = rOA;
	}
    
    
}
