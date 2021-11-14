package shop.model.dto;

import java.util.Date;

public class ShopDTO {
	private int no;
	private int memberNo;
	private int productNo;
	private int amount;
	private Date regi_date;	
	private int totPrice;
	
	//멤버조인
	private String memberName;
	
	//상품조인
	private String productImgInfo;
	private String productName;
	private int price;
	
	public ShopDTO() {}

	public int tot() {
		int tot = price * amount;
		return tot;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getRegi_date() {
		return regi_date;
	}

	public void setRegi_date(Date regi_date) {
		this.regi_date = regi_date;
	}

	public int getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(int totPrice) {
		this.totPrice = totPrice;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getProductImgInfo() {
		return productImgInfo;
	}

	public void setProductImgInfo(String productImgInfo) {
		this.productImgInfo = productImgInfo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
