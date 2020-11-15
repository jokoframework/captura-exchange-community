package py.com.sodep.mf.exchange.objects.metadata;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

@JsonInclude(Include.NON_NULL)
public class Application implements Serializable, DescribableObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String label;

	private String description;

	private List<Project> projects;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonSerialize(using = StringSerializer.class)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@JsonSerialize(using = StringSerializer.class, include = Inclusion.NON_NULL)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return label;
	}

}
