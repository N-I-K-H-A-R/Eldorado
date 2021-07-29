/**
 * 
 */
package com.eldorado.service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HP
 *
 */
public class AddProductDetails {

	/**
	 * @param args
	 */
	private String productName; 
	private String productDescription;
	private String category;
	private Integer price;
	private Integer quantity;
	private String imageLinks; 
	private String videoLinks;
	private String pdfLinks;
	private List<String>tags = new ArrayList<String>();
	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the price
	 */
	public Integer getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the imageLinks
	 */
	public String getImageLinks() {
		return imageLinks;
	}
	/**
	 * @param imageLinks the imageLinks to set
	 */
	public void setImageLinks(String imageLinks) {
		this.imageLinks = imageLinks;
	}
	/**
	 * @return the videoLinks
	 */
	public String getVideoLinks() {
		return videoLinks;
	}
	/**
	 * @param videoLinks the videoLinks to set
	 */
	public void setVideoLinks(String videoLinks) {
		this.videoLinks = videoLinks;
	}
	/**
	 * @return the pdfLinks
	 */
	public String getPdfLinks() {
		return pdfLinks;
	}
	/**
	 * @param pdfLinks the pdfLinks to set
	 */
	public void setPdfLinks(String pdfLinks) {
		this.pdfLinks = pdfLinks;
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
