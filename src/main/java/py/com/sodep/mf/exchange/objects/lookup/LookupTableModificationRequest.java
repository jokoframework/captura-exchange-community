package py.com.sodep.mf.exchange.objects.lookup;

import java.util.Map;

import py.com.sodep.mf.exchange.objects.data.ConditionalCriteria;

public class LookupTableModificationRequest {

	public static enum OPERATION_TYPE {
		UPDATE, DELETE
	};

	private OPERATION_TYPE operationType;

	private Map<String, String> newData;

	private ConditionalCriteria selector;

	public Map<String, String> getNewData() {
		return newData;
	}

	public void setNewData(Map<String, String> newData) {
		this.newData = newData;
	}

	public ConditionalCriteria getSelector() {
		return selector;
	}

	public void setSelector(ConditionalCriteria conditionalCriteria) {
		this.selector = conditionalCriteria;
	}

	public OPERATION_TYPE getOperationType() {
		return operationType;
	}

	public void setOperationType(OPERATION_TYPE operationType) {
		this.operationType = operationType;
	}

}
