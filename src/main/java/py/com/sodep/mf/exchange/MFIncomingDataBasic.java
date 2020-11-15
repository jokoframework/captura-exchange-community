package py.com.sodep.mf.exchange;

import java.util.Map;

public class MFIncomingDataBasic implements MFIncomingDataI {

	private Integer handle;
	private Map<String, ?> data;
	private Map<String, ?> meta;

	public MFIncomingDataBasic(Integer handle, Map<String, ?> data) {
		super();
		this.handle = handle;
		this.data = data;
	}
	
	public MFIncomingDataBasic(Integer handle, Map<String, ?> data, Map<String, ?> meta) {
		this(handle, data);
		this.meta = meta;
	}

	@Override
	public Object getHandle() {
		return handle;
	}

	@Override
	public Map<String, ?> getData() {
		return data;
	}
	
	public Map<String, ?> getMeta(){
		return meta;
	}

}

