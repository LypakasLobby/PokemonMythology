package com.lypaka.pokemonmythology.Handlers;

import com.lypaka.pokemonmythology.MythicPokemon.MythicPokemon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.species.gender.Gender;

public class BreedHandler {

    public static boolean isParentMythic (Pokemon pokemon) {

        return MythicHandler.isPokemonMythic(pokemon);

    }

    public static MythicPokemon getMythicPassedDown (Pokemon parent1, Pokemon parent2) {

        if (!isParentMythic(parent1) && !isParentMythic(parent2)) return null;
        MythicPokemon mythic = null;

        // We want to pass down Mythic from Mom by default, but if Dad is holding Mystic Water pass down Mythic from him
        if (parent1.getGender() == Gender.MALE) {

            // a null check would be the most logical thing to do here...but its Pixelmon....so try/catch it is. Not giving them the chance to fuck up even something as simple as a null check
            try {

                if (parent1.getHeldItemAsItemHeld().getItem().getRegistryName().toString().equalsIgnoreCase("pixelmon:mystic_water")) {

                    mythic = MythicHandler.getMythicFromPokemon(parent1);

                }

            } catch (NullPointerException e) {



            }


        } else if (parent2.getGender() == Gender.MALE) {

            try {

                if (parent2.getHeldItemAsItemHeld().getItem().getRegistryName().toString().equalsIgnoreCase("pixelmon:mystic_water")) {

                    mythic = MythicHandler.getMythicFromPokemon(parent2);

                }

            } catch (NullPointerException e) {



            }

        }

        // if Mythic is still null, neither Dad has the item
        // Check if either parent is a Ditto, then check if the opposite parent is male or female
        if (mythic == null) {

            if (parent1.getSpecies().getName().equalsIgnoreCase("Ditto")) {

                if (parent2.getGender() == Gender.MALE) {

                    mythic = MythicHandler.getMythicFromPokemon(parent1);

                } else {

                    mythic = MythicHandler.getMythicFromPokemon(parent2);

                }

            } else if (parent2.getSpecies().getName().equalsIgnoreCase("Ditto")) {

                if (parent1.getGender() == Gender.MALE) {

                    mythic = MythicHandler.getMythicFromPokemon(parent2);

                } else {

                    mythic = MythicHandler.getMythicFromPokemon(parent1);

                }

            } else { // Neither "Dad" has the Mystic Water and neither parent is Ditto, pass down from Mom

                mythic = parent1.getGender() == Gender.FEMALE ? MythicHandler.getMythicFromPokemon(parent1) : MythicHandler.getMythicFromPokemon(parent2);

            }

        }

        return mythic;

    }

}
