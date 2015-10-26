package clueGame;

public enum CardType {
	PERSON('P'), WEAPON('W'), ROOM('R');
    private char value;

    private CardType(char value) {
            this.value = value;
    }
}
