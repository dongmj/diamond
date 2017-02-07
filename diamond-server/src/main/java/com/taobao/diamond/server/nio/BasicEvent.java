package com.taobao.diamond.server.nio;

public class BasicEvent {
	private int term;
	private EventType type;
	private String rawContent;
	private String sequence;
	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}
	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	/**
	 * @return the term
	 */
	public int getTerm() {
		return term;
	}
	/**
	 * @param term the term to set
	 */
	public void setTerm(int term) {
		this.term = term;
	}
	/**
	 * @return the type
	 */
	public EventType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(EventType type) {
		this.type = type;
	}
	/**
	 * @return the rawContent
	 */
	public String getRawContent() {
		return rawContent;
	}
	/**
	 * @param rawContent the rawContent to set
	 */
	public void setRawContent(String rawContent) {
		this.rawContent = rawContent;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BasicEvent [term=" + term + ", type=" + type + ", rawContent=" + rawContent + ", sequence=" + sequence
				+ "]";
	}
}
