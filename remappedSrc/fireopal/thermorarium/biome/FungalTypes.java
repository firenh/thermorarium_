package fireopal.thermorarium.biome;

public enum FungalTypes {
    CRIMSON,
    WARPED;

    public boolean isCrimson() {
        return this == CRIMSON;
    }

    public boolean isWarped() {
        return this == WARPED;
    }
}
