package fr.gouv.esante.pml.smt.model;

import java.util.List;

public class Root{
    public List<Content> content;
    public int count;
    public String last_update;
	/**
	 * @return the content
	 */
	public List<Content> getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(List<Content> content) {
		this.content = content;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @return the last_update
	 */
	public String getLast_update() {
		return last_update;
	}
	/**
	 * @param last_update the last_update to set
	 */
	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}
    
    
}
