package redis.beans;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

    private final long id;
    private long credits;

    public User(long id, long credits) {
        this.id = id;
        this.credits = credits;
    }
    
    public User(long id) {
        this.id = id;
        this.credits = 0;
    }
    
    public long getId() {
        return id;
    }

    public long getCredits() {
        return credits;
    }

    public void setCredits(long credits) {
        this.credits = credits;
    }
    
	public String toString() {
	      return "ID: " + id + " - Credits: " + credits;
		}
}

