package com.lypaka.pokemonmythology.Handlers;

import com.lypaka.lypakautils.FancyText;
import com.lypaka.pokemonmythology.ConfigGetters;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.MutableRibbonData;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.Ribbon;
import com.pixelmonmod.pixelmon.api.pokemon.ribbon.RibbonRegistry;

import java.time.LocalDateTime;

public class RibbonHandler {

    public static MutableRibbonData alphaRibbonData;
    public static MutableRibbonData betaRibbonData;
    public static MutableRibbonData deltaRibbonData;
    public static MutableRibbonData gammaRibbonData;
    public static MutableRibbonData omegaRibbonData;
    public static MutableRibbonData sigmaRibbonData;

    public static Ribbon alphaRibbon;
    public static Ribbon betaRibbon;
    public static Ribbon deltaRibbon;
    public static Ribbon gammaRibbon;
    public static Ribbon omegaRibbon;
    public static Ribbon sigmaRibbon;

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

    }

}
