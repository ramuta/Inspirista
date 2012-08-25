package me.ramuta.inspirista.object;

public class Image {
	private String ID;
	private String linkLowRes;
	private String linkStandRes;
	private String linkThumb;
	
	/** constructor #1 */
	public Image() {
		super();
	}
	
	/** constructor #2 */
	public Image(String ID, String linkLowRes, String linkStandRes, String linkThumb) {
		super();
		this.ID = ID;
		this.linkLowRes = linkLowRes;
		this.linkStandRes = linkStandRes;
		this.linkThumb = linkThumb;
	}

	/**
	 * @return get the Instagram image ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @param ID set the Instagram image ID
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * Get link to low resolution photo
	 * @return the linkLowRes
	 */
	public String getLinkLowRes() {
		return linkLowRes;
	}

	/**
	 * Set link to low resolution photo
	 * @param linkLowRes the linkLowRes to set
	 */
	public void setLinkLowRes(String linkLowRes) {
		this.linkLowRes = linkLowRes;
	}

	/**
	 * Get link to standard resolution photo
	 * @return the linkStandRes
	 */
	public String getLinkStandRes() {
		return linkStandRes;
	}

	/**
	 * Set link to standard resolution photo
	 * @param linkStandRes the linkStandRes to set
	 */
	public void setLinkStandRes(String linkStandRes) {
		this.linkStandRes = linkStandRes;
	}

	/**
	 * Get link to thumbnail
	 * @return the linkThumb
	 */
	public String getLinkThumb() {
		return linkThumb;
	}

	/**
	 * Set link to thumbnail
	 * @param linkThumb the linkThumb to set
	 */
	public void setLinkThumb(String linkThumb) {
		this.linkThumb = linkThumb;
	}
}
