package py.com.sodep.mf.exchange.objects.data;

import java.util.ArrayList;

/**
 * This is an object that represented a restriction
 */
public class Criteria implements MFRestriction {

	private OPERATOR op;
	private Object value;
	protected String field;
	protected String namespace = "data";
	private ArrayList<OPERATOR_MODIF> modificators = new ArrayList<MFRestriction.OPERATOR_MODIF>();

	public String getField() {
		return field;
	}

	public Criteria(String field, OPERATOR op, Object value) {
		this.modificators = new ArrayList<OPERATOR_MODIF>();
		this.field = field;
		this.op = op;
		this.value = value;
	}

	public Criteria(String field, OPERATOR op) {
		this(field, op, null);
	}

	public Criteria() {

	}

	public OPERATOR getOp() {
		return op;
	}

	public Object getValue() {
		return value;
	}

	public ArrayList<OPERATOR_MODIF> getModificators() {
		return modificators;
	}

	public void addModificator(OPERATOR_MODIF modificator) {
		modificators.add(modificator);
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setOp(OPERATOR op) {
		this.op = op;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public void setField(String field) {
		this.field = field;
	}

}