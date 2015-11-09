package mc.webservice.utils;

import java.util.List;

public class KeyValue {

	public int key;
	public String value;

	public KeyValue(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public KeyValue() {
		super();
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}


	public void setKey(int key) {
		this.key = key;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public KeyValue withNewValue(String value) {
		KeyValue newKV = new KeyValue(this.key, value);
		return newKV;
	}
	
	public static List<CodeName> toCodeNameList(List<KeyValue> kvl) {
		if (kvl == null) {
			return null;
		}
		List<CodeName> cns = CollectionUtil.createGenericList();
		for (KeyValue kv : kvl) {
			if (kv == null) {
				continue;
			}
			cns.add(new CodeName(kv.getKey(), kv.getValue()));
		}
		return cns;
	}
}
