package model.entities;

public class ItemSold {
	
	private Integer quantity;
	
	private Item item;
	
	public ItemSold(Item item, Integer quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}
	
	public Double subTotal() {
		return item.getPrice() * quantity;
	}
	
	@Override
	public String toString() {
		return item.getName() + ";" + String.format("%.2f", subTotal());
	}
}
