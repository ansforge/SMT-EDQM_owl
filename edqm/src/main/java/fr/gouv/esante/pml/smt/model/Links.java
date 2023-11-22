package fr.gouv.esante.pml.smt.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links{
	@JsonProperty("BDF") 
    public List<BDF> bDF;
	@JsonProperty("SOM") 
    public List<SOM> sOM;
	@JsonProperty("RCA") 
    public List<RCA> rCA;
	@JsonProperty("TRA") 
    public List<TRA> tRA;
	@JsonProperty("ISI") 
    public List<ISI> iSI;
	@JsonProperty("AME") 
    public List<AME> aME;
	@JsonProperty("PDF") 
    public List<PDF> pDF;
//	@JsonProperty("UOP") 
//    public List<UOP> uOP;
//	@JsonProperty("ROA") 
//    public List<ROA> rOA;
	@JsonProperty("PAC") 
    public List<PAC> pAC;
	@JsonProperty("CON") 
    public List<CONN> cON;
	@JsonProperty("CLO") 
    public List<CLO> cLO;
	@JsonProperty("DEV") 
    public List<DEV> dEV;
	@JsonProperty("CDF") 
    public List<CDF> cDF;
	@JsonProperty("PFT") 
    public List<PFT> pFT;
	@JsonProperty("CMT") 
    public List<CMT> cMT;
	@JsonProperty("CMP") 
    public List<CMP> cMP;
	/**
	 * @return the bDF
	 */
	public List<BDF> getbDF() {
		return bDF;
	}
	/**
	 * @param bDF the bDF to set
	 */
	public void setbDF(List<BDF> bDF) {
		this.bDF = bDF;
	}
	/**
	 * @return the sOM
	 */
	public List<SOM> getsOM() {
		return sOM;
	}
	/**
	 * @param sOM the sOM to set
	 */
	public void setsOM(List<SOM> sOM) {
		this.sOM = sOM;
	}
	/**
	 * @return the rCA
	 */
	public List<RCA> getrCA() {
		return rCA;
	}
	/**
	 * @param rCA the rCA to set
	 */
	public void setrCA(List<RCA> rCA) {
		this.rCA = rCA;
	}
	/**
	 * @return the tRA
	 */
	public List<TRA> gettRA() {
		return tRA;
	}
	/**
	 * @param tRA the tRA to set
	 */
	public void settRA(List<TRA> tRA) {
		this.tRA = tRA;
	}
	/**
	 * @return the iSI
	 */
	public List<ISI> getiSI() {
		return iSI;
	}
	/**
	 * @param iSI the iSI to set
	 */
	public void setiSI(List<ISI> iSI) {
		this.iSI = iSI;
	}
	/**
	 * @return the aME
	 */
	public List<AME> getaME() {
		return aME;
	}
	/**
	 * @param aME the aME to set
	 */
	public void setaME(List<AME> aME) {
		this.aME = aME;
	}
	/**
	 * @return the pDF
	 */
	public List<PDF> getpDF() {
		return pDF;
	}
	/**
	 * @param pDF the pDF to set
	 */
	public void setpDF(List<PDF> pDF) {
		this.pDF = pDF;
	}
//	/**
//	 * @return the uOP
//	 */
//	public List<UOP> getuOP() {
//		return uOP;
//	}
//	/**
//	 * @param uOP the uOP to set
//	 */
//	public void setuOP(List<UOP> uOP) {
//		this.uOP = uOP;
//	}
//	/**
//	 * @return the rOA
//	 */
//	public List<ROA> getrOA() {
//		return rOA;
//	}
//	/**
//	 * @param rOA the rOA to set
//	 */
//	public void setrOA(List<ROA> rOA) {
//		this.rOA = rOA;
//	}
	/**
	 * @return the pAC
	 */
	public List<PAC> getpAC() {
		return pAC;
	}
	/**
	 * @param pAC the pAC to set
	 */
	public void setpAC(List<PAC> pAC) {
		this.pAC = pAC;
	}
	/**
	 * @return the cON
	 */
	public List<CONN> getcON() {
		return cON;
	}
	/**
	 * @param cON the cON to set
	 */
	public void setcON(List<CONN> cON) {
		this.cON = cON;
	}
	/**
	 * @return the cLO
	 */
	public List<CLO> getcLO() {
		return cLO;
	}
	/**
	 * @param cLO the cLO to set
	 */
	public void setcLO(List<CLO> cLO) {
		this.cLO = cLO;
	}
	/**
	 * @return the dEV
	 */
	public List<DEV> getdEV() {
		return dEV;
	}
	/**
	 * @param dEV the dEV to set
	 */
	public void setdEV(List<DEV> dEV) {
		this.dEV = dEV;
	}
	/**
	 * @return the cDF
	 */
	public List<CDF> getcDF() {
		return cDF;
	}
	/**
	 * @param cDF the cDF to set
	 */
	public void setcDF(List<CDF> cDF) {
		this.cDF = cDF;
	}
	/**
	 * @return the pFT
	 */
	public List<PFT> getpFT() {
		return pFT;
	}
	/**
	 * @param pFT the pFT to set
	 */
	public void setpFT(List<PFT> pFT) {
		this.pFT = pFT;
	}
	/**
	 * @return the cMT
	 */
	public List<CMT> getcMT() {
		return cMT;
	}
	/**
	 * @param cMT the cMT to set
	 */
	public void setcMT(List<CMT> cMT) {
		this.cMT = cMT;
	}
	/**
	 * @return the cMP
	 */
	public List<CMP> getcMP() {
		return cMP;
	}
	/**
	 * @param cMP the cMP to set
	 */
	public void setcMP(List<CMP> cMP) {
		this.cMP = cMP;
	}
	
}
