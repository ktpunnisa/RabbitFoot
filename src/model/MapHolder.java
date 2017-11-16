package model;

import java.util.ArrayList;
import java.util.List;

public class MapHolder {

	private List<Block> map;
	public MapHolder()
	{
		
	}
	public void genMap(int diff)
	{
		//gen map using list
		map = new ArrayList<Block>();
		
	}
	public void destroyTrap(int blockIndex)
	{
		//if wolf are trapped, block will turn to normal
	}
	public List<Block> getMap() {
		return map;
	}
}
