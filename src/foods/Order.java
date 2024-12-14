package foods;

public class Order {
	
	private String foodName;
	private Integer count;
	private Integer foodPrice;
	
	public Order(String foodName, Integer count, Integer foodPrice) {
		super();
		this.foodName = foodName;
		this.count = count;
		this.foodPrice = foodPrice;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(Integer foodPrice) {
		this.foodPrice = foodPrice;
	}

}
