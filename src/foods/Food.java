package foods;

public abstract class Food {
	
	private String name;
	private String type;
	private Integer price;
	private Integer orderCount;
	
	public Food(String name, String type, Integer price, Integer orderCount) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
		this.orderCount = orderCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

}
