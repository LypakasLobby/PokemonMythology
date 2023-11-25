package com.lypaka.pokemonmythology.Handlers;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.MutableRibbonData;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.RibbonRegistry;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class RibbonHandler {

    public static MutableRibbonData alphaRibbonData;
    public static MutableRibbonData betaRibbonData;
    public static MutableRibbonData deltaRibbonData;
    public static MutableRibbonData gammaRibbonData;
    public static MutableRibbonData omegaRibbonData;
    public static MutableRibbonData sigmaRibbonData;
    public static MutableRibbonData thetaRibbonData;
    public static MutableRibbonData zetaRibbonData;

    public static Ribbon alphaRibbon;
    public static Ribbon betaRibbon;
    public static Ribbon deltaRibbon;
    public static Ribbon gammaRibbon;
    public static Ribbon omegaRibbon;
    public static Ribbon sigmaRibbon;
    public static Ribbon thetaRibbon;
    public static Ribbon zetaRibbon;

    public static Map<String, Ribbon> customMythicRibbons;
    public static Map<String, MutableRibbonData> customMythicRibbonData;

    public static void loadRibbons() {

        alphaRibbonData = new MutableRibbonData();
        alphaRibbonData.setPrefix(FancyText.getFormattedText(ConfigGetters.displayTitles.get("Alpha")));
        alphaRibbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), alphaRibbonData);

        betaRibbonData = new MutableRibbonData();
        betaRibbonData.setPrefix(FancyText.getFormattedText(ConfigGetters.displayTitles.get("Beta")));
        betaRibbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), betaRibbonData);

        deltaRibbonData = new MutableRibbonData();
        deltaRibbonData.setPrefix(FancyText.getFormattedText(ConfigGetters.displayTitles.get("Delta")));
        deltaRibbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), deltaRibbonData);

        gammaRibbonData = new MutableRibbonData();
        gammaRibbonData.setPrefix(FancyText.getFormattedText(ConfigGetters.displayTitles.get("Gamma")));
        gammaRibbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), gammaRibbonData);

        omegaRibbonData = new MutableRibbonData();
        omegaRibbonData.setPrefix(FancyText.getFormattedText(ConfigGetters.displayTitles.get("Omega")));
        omegaRibbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), omegaRibbonData);

        sigmaRibbonData = new MutableRibbonData();
        sigmaRibbonData.setPrefix(FancyText.getFormattedText(ConfigGetters.displayTitles.get("Sigma")));
        sigmaRibbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), sigmaRibbonData);

        thetaRibbonData = new MutableRibbonData();
        thetaRibbonData.setPrefix(FancyText.getFormattedText(ConfigGetters.displayTitles.get("Theta")));
        thetaRibbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), thetaRibbonData);

        zetaRibbonData = new MutableRibbonData();
        zetaRibbonData.setPrefix(FancyText.getFormattedText(ConfigGetters.displayTitles.get("Zeta")));
        zetaRibbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), zetaRibbonData);

    }

    public static void loadCustomMythicRibbonData() {

        if (ConfigGetters.customMythics.size() == 0) return;
        customMythicRibbons = new HashMap<>();
        customMythicRibbonData = new HashMap<>();
        for (Map.Entry<String, Map<String, String>> entry : ConfigGetters.customMythics.entrySet()) {

            Map<String, String> data = entry.getValue();
            String displayTitle = data.get("Display-Title");
            MutableRibbonData ribbonData = new MutableRibbonData();
            ribbonData.setPrefix(FancyText.getFormattedText(displayTitle));
            Ribbon ribbon = new Ribbon(RibbonRegistry.SPECIAL.getKey(), LocalDateTime.now().getDayOfYear(), FancyText.getFormattedText(""), ribbonData);
            customMythicRibbons.put(entry.getKey(), ribbon);
            customMythicRibbonData.put(entry.getKey(), ribbonData);

        }

    }

}
