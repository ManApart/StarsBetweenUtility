package rak.utility.attributeGrouper;

import java.util.HashMap;

public abstract class AttributeHolder<E extends Attribute<E>, Item> {
	private HashMap<String, E> attributes = new HashMap<>();
	private Item item;
	
	public void addAttribute(E attribute){
		attributes.put(attribute.getKey(), attribute);
	}
	
	public void setItem(Item item){
		this.item = item;
	}
	
	public Item getItem(){
		return item;
	}
	
	public abstract void initialize();
	
	public boolean canCompareWith(AttributeHolder<E,Item> otherItem){
		for (E attribute : attributes.values()){
			
			if (otherItem.attributes.containsKey(attribute.getKey())){
				E otherAttribute = otherItem.attributes.get(attribute.getKey());
				
				if (!attribute.canCompareWith(otherAttribute)){
					return false;
				}
			}
		}
		return true;
	}
	
	public int getDistance(AttributeHolder<E,Item> otherItem){
		int distance = 0;
		int comparedAttributes = 0;
		for (E attribute : attributes.values()){
			
			if (otherItem.attributes.containsKey(attribute.getKey())){
				E otherAttribute = otherItem.attributes.get(attribute.getKey());
				
				distance += attribute.getDistance(otherAttribute);
				comparedAttributes ++;
			}
		}
		
		int averageDistance = distance/comparedAttributes;
		return averageDistance;
	}
}
