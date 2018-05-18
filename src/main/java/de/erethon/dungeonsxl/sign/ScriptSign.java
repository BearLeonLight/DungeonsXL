/*
 * Copyright (C) 2012-2018 Frank Baumann
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
package de.erethon.dungeonsxl.sign;

import de.erethon.caliburn.item.VanillaItem;
import de.erethon.dungeonsxl.world.DGameWorld;
import org.bukkit.block.Sign;

/**
 * @author Daniel Saukel
 */
public class ScriptSign extends DSign {

    private DSignType type = DSignTypeDefault.SCRIPT;

    private String name;

    public ScriptSign(Sign sign, String[] lines, DGameWorld gameWorld) {
        super(sign, lines, gameWorld);
        name = lines[1];
    }

    /**
     * @return the name of the script
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean check() {
        return plugin.getSignScripts().getByName(lines[1]) != null;
    }

    @Override
    public void onInit() {
        SignScript script = plugin.getSignScripts().getByName(name);
        for (String[] lines : script.getSigns()) {
            DSign dSign = DSign.create(getSign(), lines, getGameWorld());
            getGameWorld().getDSigns().add(dSign);

            dSign.onInit();
            if (!dSign.hasTriggers()) {
                dSign.onTrigger();
            }
        }

        getSign().getBlock().setType(VanillaItem.AIR.getMaterial());
    }

    @Override
    public DSignType getType() {
        return type;
    }

}
