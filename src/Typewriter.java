import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Creates dialogue box, displays dialogue between character (left box) and protagonist (right box)
public class Typewriter implements ActionListener {
	// Constants
	private static final int X = 1, Y = 1; // starting coordinates of rectangle
	private static final int leftX = X; // starting coordinate of left speech rectangle (for Macbeth)
	private static final int rightX = X + GamePanel.WIDTH / 2 + 10; // starting coordinate of right speech rectange (for enemies)
	private static final int WIDTH = GamePanel.WIDTH / 2 - 50; // width of each rectangle
	private static final int HEIGHT = 70; // ht of each rectangle, bearing dialogue
	private static final int CHAR_MAX = 50; // char limit per line
	private static final int PAUSE_TIME = 30; // interval between drawing each letter for dialogue

	private int y = Y; // starting y pos of test, useful for line breaks
	// rectangle colors
	private static final Color LEFT_RECT_INACTIVE = new Color(100,100,100);
	private static final Color LEFT_RECT_ACTIVE = new Color(195,90,90);
	private static final Color RIGHT_RECT_INACTIVE = Color.GRAY;
	private static final Color RIGHT_RECT_ACTIVE = Color.WHITE;

	// font
	private static final Font DEFAULT_FONT = new Font(Font.SERIF, Font.PLAIN, 18);

	public boolean hasBeenActivated = false;
	private final Dialogue[] dialogue; // list of strings to show
	private boolean animate = false; // whether to draw string or not
	private Timer timer; // used for drawing letters
	private Dialogue currentDialogue; // current Dialogue to display

	// counters
	private int charCounter = 0; // counts the latest drawn char in string (used for displaying chars one by one)
	private int dialogueCounter = 0; // index of current string being drawn in dialogue[]

	public Typewriter(Dialogue[] dialogue) {
		this.dialogue = dialogue;
		this.timer = new Timer(PAUSE_TIME, this);
	}

	// begins drawing text by starting timer
	public void start() {
		charCounter = 0;
		dialogueCounter = 0;
		currentDialogue = dialogue[dialogueCounter];
		timer.start();
		animate = true;
		hasBeenActivated =true;
	}

	// determine whether to stop printing dialogue
	@Override
	public void actionPerformed(ActionEvent e) {
		if (charCounter >= currentDialogue.length())
			timer.stop();
		else charCounter++;
	}

	// draw appropriate string & rectangles based on dialogueCounter
	public void draw(Graphics2D g) {
		if (!animate) return;
		g.setFont(DEFAULT_FONT);
		String s = currentDialogue.getText();
		// speech rectangles
		g.setColor(currentDialogue.isMacbeth() ? LEFT_RECT_ACTIVE : LEFT_RECT_INACTIVE);
		g.fillRect(leftX, X, WIDTH, HEIGHT); // Macbeth dialogue screen
		g.setColor(!currentDialogue.isMacbeth() ? RIGHT_RECT_ACTIVE : RIGHT_RECT_INACTIVE);
		g.fillRect(rightX, Y, WIDTH, HEIGHT); // enemy dialogue screen
		// display text
		g.setColor(Color.BLACK);
//		g.drawString(s.substring(0, Math.min(charCounter, CHAR_MAX)), currentDialogue.isMacbeth() ? leftX : rightX + 10, y + 10);
		// line break
//		if (charCounter > CHAR_MAX)
//			g.drawString(s.substring(CHAR_MAX, Math.min(charCounter, CHAR_MAX * 2)), currentDialogue.isMacbeth() ? leftX : rightX, y + 20);
		int sY = y + 20;
		for (int lineIndex = 0; charCounter > lineIndex * CHAR_MAX && lineIndex < 2; lineIndex++) {
			g.drawString(s.substring(lineIndex * CHAR_MAX, Math.min(charCounter, (lineIndex + 1) * CHAR_MAX)), (currentDialogue.isMacbeth() ? leftX : rightX) + 15, sY);
			sY += 20;
		}
	}

	// move onto next sentence in dialogue, or terminate
	public boolean advance() {
		if (isTyping())
			charCounter = currentDialogue.length();
		else {
			charCounter = 0;
			animate = ++dialogueCounter < dialogue.length;
			if (animate) {
				currentDialogue = dialogue[dialogueCounter];
				timer.restart();
			} else {
				return true;
			}
		}
		return false;
	}

	// Getters
	public boolean isAnimate() { return animate; }

	// whether or not all typewriter is currently typing
	public boolean isTyping() { return charCounter < currentDialogue.length() && animate; }


}