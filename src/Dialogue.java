public class Dialogue {
	private final String text;
	private final boolean isMacbeth;

	public Dialogue(String text, boolean isMacbeth) {
		this.text = text;
		this.isMacbeth = isMacbeth;
	}

	public String getText() {
		return text;
	}

	public boolean isMacbeth() {
		return isMacbeth;
	}
	public int length() {
		return text.length();
	}

	@Override
	public String toString() {
		return text;
	}
}
