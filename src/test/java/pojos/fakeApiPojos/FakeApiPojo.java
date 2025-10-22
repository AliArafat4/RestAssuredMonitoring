package pojos.fakeApiPojos;

public class FakeApiPojo {
	private String dueDate;
	private Integer id;
	private Boolean completed;
	private String title;

	public void setDueDate(String dueDate){
		this.dueDate = dueDate;
	}

	public String getDueDate(){
		return dueDate;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setCompleted(Boolean completed){
		this.completed = completed;
	}

	public Boolean isCompleted(){
		return completed;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"dueDate = '" + dueDate + '\'' + 
			",id = '" + id + '\'' + 
			",completed = '" + completed + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}