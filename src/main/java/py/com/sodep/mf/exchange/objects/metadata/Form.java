package py.com.sodep.mf.exchange.objects.metadata;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

/**
 * 
 * @author Miguel
 * 
 */
// This classes are meant to be used for data exchange with the device.
// The Android App is not susceptible to HTML injection and also is not
// capable of rendering HTML escaped characters as they should (e.g. ñ will be
// seen as &ntilde;)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Form implements Serializable, DescribableObject {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String label;

	private String description;

	private Long projectId;

	private Long version;

	private boolean visible = true;

	private List<Long> requiredLookupTables;

	private String definition;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonSerialize(using = StringSerializer.class, include = Inclusion.NON_NULL)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@JsonSerialize(using = StringSerializer.class, include = Inclusion.NON_NULL)
	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	@JsonSerialize(using = StringSerializer.class)
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<Long> getRequiredLookupTables() {
		return requiredLookupTables;
	}

	public void setRequiredLookupTables(List<Long> requiredLookupTables) {
		this.requiredLookupTables = requiredLookupTables;
	}

	@Override
	public String toString() {
		return label;
	}

}
