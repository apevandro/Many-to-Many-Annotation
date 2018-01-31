package join_table.bidirectional;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "disciplines")
public class Discipline {

	@Id
	@Column(name = "DiscId")
    private int discId;
	
	@Column(name = "Name")
    private String name;
	
	@Column(name = "Credits")
    private int credits;
	
	@ManyToMany(mappedBy = "disciplines")
    private Set<Course> courses = new HashSet<Course>();
    
    public Discipline() {}

    public Discipline(int discId, String name, int credits) {
    	this.discId = discId;
    	this.name = name;
        this.credits = credits;
    }

    public int getDiscId() {
        return discId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public boolean equals(Object obj) {
    	// Object identity. 
        // Two object references are identical if they point to the same memory location. 
    	if (this == obj) {
    		return true;
    	}
    			
    	if (!(obj instanceof Discipline)) {
    		return false;
    	}
    			
    	final Discipline other = (Discipline) obj;

    	// Database identity.
    	if (!(other.getDiscId() == discId)) {
    		return false;
    	}

    	// Object equality, sometimes also referred to as equivalence.
    	// Equivalence means that two different (nonidentical) objects have the same value.		
    	if (!(other.getName().equals(name) &&
    	          other.getCredits() == credits)) {
    	    return false;
    	}
    			
    	return true;
    }

    public int hashCode() {
    	final int prime = 31;
		int result = 1;
		result = prime * result + (name + credits).hashCode();
		return result;
    }

}