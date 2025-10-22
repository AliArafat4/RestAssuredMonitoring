package pojos.randomUserPojo;

public class Info{
	private String seed;
	private Integer page;
	private Integer results;
	private String version;

	public void setSeed(String seed){
		this.seed = seed;
	}

	public String getSeed(){
		return seed;
	}

	public void setPage(Integer page){
		this.page = page;
	}

	public Integer getPage(){
		return page;
	}

	public void setResults(Integer results){
		this.results = results;
	}

	public Integer getResults(){
		return results;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getVersion(){
		return version;
	}

	@Override
 	public String toString(){
		return 
			"Info{" + 
			"seed = '" + seed + '\'' + 
			",page = '" + page + '\'' + 
			",results = '" + results + '\'' +
			",version = '" + version + '\'' + 
			"}";
		}
}