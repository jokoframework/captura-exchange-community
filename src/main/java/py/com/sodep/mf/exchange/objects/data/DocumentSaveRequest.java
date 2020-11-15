package py.com.sodep.mf.exchange.objects.data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * A JSON Serialized instance of this class is sent by the device to the server
 * to save data
 * 
 * @author Miguel
 * 
 */
public class DocumentSaveRequest implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static class PageData implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private Long pageId;

		/**
		 * Humberto suggested that this should be a list of Objects or Maps i.e.
		 * [ { "id" : "prop1", "value" : "val1"}, { "id" : "prop2", "value" :
		 * "val2"} ... ] instead of { "prop1" : "val1", "prop2" : "val2"}
		 * 
		 * Pros: - Extensibility : for every entry some metadata could be added
		 * easily. - Structure : It's cited as a good practice as there are no
		 * dynamic properties. The structure is well known.
		 * 
		 * Cons: - May be a little harder to work with - Bigger JSON
		 * 
		 * I'm in favour of the change but given the "state of affairs" it's
		 * easier to implement Map<String, String>.
		 * 
		 * jmpr - 12/01/2013
		 */
		private Map<String, Object> data;

		public Long getPageId() {
			return pageId;
		}

		public void setPageId(Long pageId) {
			this.pageId = pageId;
		}

		public Map<String, Object> getData() {
			return data;
		}

		public void setData(Map<String, Object> data) {
			this.data = data;
		}

	}

	// This is redundant information...
	private Long formId;

	// This is redundant information...
	private Long version;

	private String savedAt;

	private List<PageData> pageData;

	private String location;
	
	public Long getFormId() {
		return formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public List<PageData> getPageData() {
		return pageData;
	}

	public void setPageData(List<PageData> pageData) {
		this.pageData = pageData;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSavedAt() {
		return savedAt;
	}

	public void setSavedAt(String savedAt) {
		this.savedAt = savedAt;
	}

}
