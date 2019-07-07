/*
 * Copyright (C) 2012-2019 Frank Baumann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.erethon.dungeonsxl.api.player;

import de.erethon.dungeonsxl.util.DColor;
import org.bukkit.entity.Player;

/**
 * Represents a group of players provided by DungeonsXL.
 *
 * @author Daniel Saukel
 */
public interface DungeonGroup {

    int getId();

    String getName();

    String getRawName();

    void setName(String name);

    default void setName(DColor color) {
        setName(color.toString() + "#" + getId());
    }

    Player getCaptain();

    void setCaptain(Player player);

    default void addPlayer(Player player) {
        addPlayer(player, true);
    }

    void addPlayer(Player player, boolean message);

    default void removePlayer(Player player) {
        addPlayer(player, true);
    }

    void removePlayer(Player player, boolean message);

    boolean isPlaying();

    int getLives();

    void setLives(int lives);

}
