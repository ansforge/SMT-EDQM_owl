package fr.gouv.esante.pml.smt.model;

import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Content{
	@JsonProperty("class")
	public String clazz;
    public String code;
    public String monograph;
    public String domain;
    public String status;
    public String creation_date;
    public String modification_date;
    public String english;
    public String definition;
    public String comment;
    public String req_auth;
    public String auth_req_id;
    public String auth_term_id;
    public String edqm_req_id;
    public int version;
    public String version_date;
    public List<Translation> translations;
    public Links links;
    public Mapping mapping;
    public Replacement replacement;
    public List<String> tags;
	/**
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}
	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
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
	 * @return the monograph
	 */
	public String getMonograph() {
		return monograph;
	}
	/**
	 * @param monograph the monograph to set
	 */
	public void setMonograph(String monograph) {
		this.monograph = monograph;
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
	 * @return the english
	 */
	public String getEnglish() {
		return english;
	}
	/**
	 * @param english the english to set
	 */
	public void setEnglish(String english) {
		this.english = english;
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
	 * @return the req_auth
	 */
	public String getReq_auth() {
		return req_auth;
	}
	/**
	 * @param req_auth the req_auth to set
	 */
	public void setReq_auth(String req_auth) {
		this.req_auth = req_auth;
	}
	/**
	 * @return the auth_req_id
	 */
	public String getAuth_req_id() {
		return auth_req_id;
	}
	/**
	 * @param auth_req_id the auth_req_id to set
	 */
	public void setAuth_req_id(String auth_req_id) {
		this.auth_req_id = auth_req_id;
	}
	/**
	 * @return the auth_term_id
	 */
	public String getAuth_term_id() {
		return auth_term_id;
	}
	/**
	 * @param auth_term_id the auth_term_id to set
	 */
	public void setAuth_term_id(String auth_term_id) {
		this.auth_term_id = auth_term_id;
	}
	/**
	 * @return the edqm_req_id
	 */
	public String getEdqm_req_id() {
		return edqm_req_id;
	}
	/**
	 * @param edqm_req_id the edqm_req_id to set
	 */
	public void setEdqm_req_id(String edqm_req_id) {
		this.edqm_req_id = edqm_req_id;
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
	 * @return the version_date
	 */
	public String getVersion_date() {
		return version_date;
	}
	/**
	 * @param version_date the version_date to set
	 */
	public void setVersion_date(String version_date) {
		this.version_date = version_date;
	}
	/**
	 * @return the translations
	 */
	public List<Translation> getTranslations() {
		return translations;
	}
	/**
	 * @param translations the translations to set
	 */
	public void setTranslations(List<Translation> translations) {
		this.translations = translations;
	}
	
	/**
	 * @return the links
	 */
	public Links getLinks() {
		return links;
	}
	/**
	 * @param links the links to set
	 */
	public void setLinks(Links links) {
		this.links = links;
	}
	
	/**
	 * @return the mapping
	 */
	public Mapping getMapping() {
		return mapping;
	}
	/**
	 * @param mapping the mapping to set
	 */
	public void setMapping(Mapping mapping) {
		this.mapping = mapping;
	}
	/**
	 * @return the replacement
	 */
	public Replacement getReplacement() {
		return replacement;
	}
	/**
	 * @param replacement the replacement to set
	 */
	public void setReplacement(Replacement replacement) {
		this.replacement = replacement;
	}
	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
    
    
}
