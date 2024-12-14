package foods;

public class Regular extends Food {
	
	private Boolean delivery;

	public Regular(String name, String type, Integer price, Integer orderCount, Boolean delivery) {
		super(name, type, price, orderCount);
		this.delivery = delivery;
	}

	public Boolean getDelivery() {
		return delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

}
