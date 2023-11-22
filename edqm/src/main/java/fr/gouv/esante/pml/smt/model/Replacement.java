package fr.gouv.esante.pml.smt.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Replacement{
	@JsonProperty("BDF") 
    public List<CLASS> bDF;
	@JsonProperty("SOM") 
    public List<CLASS> sOM;
	@JsonProperty("RCA") 
    public List<CLASS> rCA;
	@JsonProperty("TRA") 
    public List<CLASS> tRA;
	@JsonProperty("ISI") 
    public List<CLASS> iSI;
	@JsonProperty("AME") 
    public List<CLASS> aME;
	@JsonProperty("PDF") 
    public List<CLASS> pDF;
	@JsonProperty("UOP") 
    public List<CLASS> uOP;
	@JsonProperty("ROA") 
    public List<CLASS> rOA;
	@JsonProperty("PAC") 
    public List<CLASS> pAC;
	@JsonProperty("CON") 
    public List<CLASS> cON;
	@JsonProperty("CLO") 
    public List<CLASS> cLO;
	@JsonProperty("DEV") 
    public List<CLASS> dEV;
	@JsonProperty("CDF") 
    public List<CLASS> cDF;
	@JsonProperty("PFT") 
    public List<CLASS> pFT;
	@JsonProperty("CMT") 
    public List<CLASS> cMT;
	@JsonProperty("CMP") 
    public List<CLASS> cMP;
	@JsonProperty("MAP") 
    public List<CLASS> mAP;
	@JsonProperty("FIL") 
    public List<CLASS> fIL;
	
	public List<CLASS> allReplacement(){
		List<CLASS> allClass = new ArrayList<CLASS>();
		if(bDF != null)
		allClass.addAll(bDF);
		if(sOM != null)
		allClass.addAll(sOM);
		if(rCA != null)
		allClass.addAll(rCA);
		if(tRA != null)
		allClass.addAll(tRA);
		if(iSI != null)
		allClass.addAll(iSI);
		if(aME != null)
		allClass.addAll(aME);
		if(pDF != null)
		allClass.addAll(pDF);
		if(uOP != null)
		allClass.addAll(uOP);
		if(rOA != null)
		allClass.addAll(rOA);
		if(pAC != null)
		allClass.addAll(pAC);
		if(cON != null)
		allClass.addAll(cON);
		if(cLO != null)
		allClass.addAll(cLO);
		if(dEV != null)
		allClass.addAll(dEV);
		if(cDF != null)
		allClass.addAll(cDF);
		if(pFT != null)
		allClass.addAll(pFT);
		if(cMT != null)
		allClass.addAll(cMT);
		if(cMP != null)
		allClass.addAll(cMP);
		if(mAP != null)
		allClass.addAll(mAP);
		if(fIL != null)
		allClass.addAll(fIL);
		return allClass;
	}
}
