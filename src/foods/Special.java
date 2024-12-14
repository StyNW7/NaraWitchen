package foods;

public class Special extends Food {

	private String size;

	public Special(String name, String type, Integer price, Integer orderCount, String size) {
		super(name, type, price, orderCount);
		this.size = size;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
