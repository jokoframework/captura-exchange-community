package py.com.sodep.mf.exchange;

import java.util.List;

import py.com.sodep.mf.exchange.objects.lookup.LookupTableDTO;

public class MFLoookupTableDefinition extends MFDataSetDefinition {

	// #3078
	public static final int MAX_FIELDS = 30;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LookupTableDTO info;

	public MFLoookupTableDefinition() {

	}

	public MFLoookupTableDefinition(MFDataSetDefinition def) {
		super(def);
	}

	public MFLoookupTableDefinition(List<MFField> fields, Long version, String metaDataRef) {
		super(fields, version, metaDataRef);
	}

	public LookupTableDTO getInfo() {
		return info;
	}

	public void setInfo(LookupTableDTO info) {
		this.info = info;
	}

}
