package com.toklar.tokcraftmixins.config;

public final class InfernalTierScaling {
    public static double rareIncrement;
    public static double ultraIncrement;
    public static double infernalIncrement;

    public static double increment(String tierKey) {
        switch (tierKey.toLowerCase()) {
            case "rare":     return rareIncrement;
            case "ultra":    return ultraIncrement;
            case "infernal": return infernalIncrement;
            default:         return 0.0D;
        }
    }
}