package nantel.java.boulder.models.entities.walls;

import nantel.java.boulder.models.exceptions.NotImplementedException;

public class ExpandingWall extends SteelWall
{
	private static final long serialVersionUID = 1075369999125961903L;

	public ExpandingWall()
	{
		throw new NotImplementedException("ExpandingWall is not implemented yet");
	}

	@Override
	public String getName()
	{
		return "EW";
	}

}
