package my.company.blog;

public class Post  extends my.company.common.hasAuthor
  {
	private String title;
	    
	public String getTitle() {
	    return title;
	}
	    
	public void setTitle(String title) {
	    this.title = title;
	}
	private String content;
	    
	public String getContent() {
	    return content;
	}
	    
	public void setContent(String content) {
	    this.content = content;
	}
	private my.company.blog.Comment comments;
	    
	public my.company.blog.Comment getComments() {
	    return comments;
	}
	    
	public void setComments(my.company.blog.Comment comments) {
	    this.comments = comments;
	}
	}
	
