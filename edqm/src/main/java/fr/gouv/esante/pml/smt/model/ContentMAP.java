package fr.gouv.esante.pml.smt.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentMAP{
    @JsonProperty("class") 
    public String myclass;
    public String code;
    public String domain;
    public String status;
    public String creation_date;
    public String modification_date;
    public String term;
    public String definition;
    public String comment;
    public String language;
    public String region;
    public String authority;
    public String source;
    public String link;
    public String region_id;
    public String region_link;
    public int version;
    public MappingMAP mapping;
	/**
	 * @return the myclass
	 */
	public String getMyclass() {
		return myclass;
	}
	/**
	 * @param myclass the myclass to set
	 */
	public void setMyclass(String myclass) {
		this.myclass = myclass;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}
	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the creation_date
	 */
	public String getCreation_date() {
		return creation_date;
	}
	/**
	 * @param creation_date the creation_date to set
	 */
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	/**
	 * @return the modification_date
	 */
	public String getModification_date() {
		return modification_date;
	}
	/**
	 * @param modification_date the modification_date to set
	 */
	public void setModification_date(String modification_date) {
		this.modification_date = modification_date;
	}
	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}
	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	/**
	 * @return the definition
	 */
	public String getDefinition() {
		return definition;
	}
	/**
	 * @param definition the definition to set
	 */
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the authority
	 */
	public String getAuthority() {
		return authority;
	}
	/**
	 * @param authority the authority to set
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * @return the region_id
	 */
	public String getRegion_id() {
		return region_id;
	}
	/**
	 * @param region_id the region_id to set
	 */
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	/**
	 * @return the region_link
	 */
	public String getRegion_link() {
		return region_link;
	}
	/**
	 * @param region_link the region_link to set
	 */
	public void setRegion_link(String region_link) {
		this.region_link = region_link;
	}
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return the mapping
	 */
	public MappingMAP getMapping() {
		return mapping;
	}
	/**
	 * @param mapping the mapping to set
	 */
	public void setMapping(MappingMAP mapping) {
		this.mapping = mapping;
	}
    
    
}
