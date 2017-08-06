package rak.utility.attributeGrouper;

import java.util.ArrayList;

public class AttributeGrouper <ItemWithAttributes extends AttributeHolder<AttributeE,Item>, AttributeE extends Attribute<AttributeE>, Item> {
	private ArrayList<AttributeHolder<AttributeE, Item>> mappedItems = new ArrayList<>();
	
	public void addItem(AttributeHolder<AttributeE, Item> item){
		mappedItems.add(item);
	}
	
	public Item getNearestItem(AttributeHolder<AttributeE, Item> item){
		if (mappedItems.isEmpty()){
			return null;
		}
		
		AttributeHolder<AttributeE, Item> closestItem = null;
		item.initialize();
		AttributeHolder<AttributeE, Item> baseline = mappedItems.get(0);
		baseline.initialize();
		int closestDistance = item.getDistance(mappedItems.get(0));
		closestItem = baseline;
		
		for (AttributeHolder<AttributeE, Item> mappedItem : mappedItems){
			mappedItem.initialize();
			if (item.canCompareWith(mappedItem)){
				int distance = item.getDistance(mappedItem);
				if (distance < closestDistance){
					closestItem = mappedItem;
					closestDistance = distance;
				}
			}
		}
		return closestItem.getItem();
	}
	
	public ArrayList<Item> getAllItems(){
		ArrayList<Item> items = new ArrayList<>();
		for (AttributeHolder<AttributeE, Item> attribute : mappedItems){
			items.add(attribute.getItem());
		}
		return items;
	}
	
	public void addAll(AttributeGrouper<ItemWithAttributes, AttributeE, Item> other){
		for (AttributeHolder<AttributeE, Item> item : other.mappedItems){
			addItem(item);
		}
	}
	
}
