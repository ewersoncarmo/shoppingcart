package com.webstore.shoppingcart.domain.enumerator;

public enum PurchaseStatus {

    PENDING("P", "Pending"),
    FINISHED("F", "Finished");

    private String code;
	private String description;

    private PurchaseStatus(String code, String description) {
        this.code = code;
		this.description = description;
    }

    public String getCode() {
        return code;
    }
    
    public String getDescription() {
		return description;
	}

	@Override
    public String toString() {
      return String.valueOf(code);
    }

}
