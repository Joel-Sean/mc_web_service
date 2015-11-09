package mc.webservice.utils;

import java.io.Serializable;

public interface TextImage {

	public Image getImage() throws Exception;

	public static final class Image implements Serializable {
		private static final long serialVersionUID = -5939216967943935669L;
		public final String text;
		public final byte[] content;

		public Image(String text, byte[] content) {
			this.text = text;
			this.content = content;
		}
	}

}
