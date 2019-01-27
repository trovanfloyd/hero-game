package game.items;

import java.io.Serializable;

public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean visible = true;
	private String description;
	private String detailDescription;
	
	public Item (String description, String detailDescription) {
		this.description = description;
		this.detailDescription = detailDescription;
	}

	public boolean isVisible() {
		return visible;
	}

	public String getDescription() {
		return description;
	}

	public String getDetailDescription() {
		return detailDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
	
	
}
