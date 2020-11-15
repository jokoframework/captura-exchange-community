package py.com.sodep.mf.exchange.objects.data;

import java.util.ArrayList;

public class ConditionalCriteria implements MFRestriction {

	private ArrayList<MFRestriction> restrictions = new ArrayList<MFRestriction>();

	public static enum CONDITION_TYPE {
		AND
	};

	private CONDITION_TYPE conditionType;

	public ConditionalCriteria() {

	}

	public ConditionalCriteria(CONDITION_TYPE conditionType) {
		this.conditionType = conditionType;
	}

	public ArrayList<MFRestriction> getRestrictions() {
		return restrictions;
	}

	public void add(MFRestriction restriction) {
		this.restrictions.add(restriction);
	}
}
