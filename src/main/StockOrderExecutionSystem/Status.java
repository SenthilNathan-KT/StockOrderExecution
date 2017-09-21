public enum Status
{
	OPEN("OPEN"),
	CLOSED("CLOSED");

	private String status;

	Status(String status)
	{
		this.status = status;
	}

	public String getStatus()
	{
		return status;
	}
}
