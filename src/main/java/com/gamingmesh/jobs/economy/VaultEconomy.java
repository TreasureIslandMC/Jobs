/**
 * Jobs Plugin for Bukkit
 * Copyright (C) 2011 Zak Ford <zak.j.ford@gmail.com>
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.gamingmesh.jobs.economy;

import org.bukkit.OfflinePlayer;

//TODO: Implementation of vault?? Jobs doesn't have it's own economy system....
@Deprecated
public class VaultEconomy implements Economy {
	private net.milkbowl.vault.economy.Economy vault;

	public VaultEconomy(net.milkbowl.vault.economy.Economy vault) {
		this.vault = vault;
	}

	@Override
	public boolean depositPlayer(OfflinePlayer offlinePlayer, double money) {
		return vault.depositPlayer(offlinePlayer, money).transactionSuccess();
	}

	@Override
	public boolean depositPlayer(String playerName, double money) {
		return vault.depositPlayer(playerName, money).transactionSuccess();
	}

	@Override
	public boolean withdrawPlayer(OfflinePlayer offlinePlayer, double money) {
		return vault.withdrawPlayer(offlinePlayer, money).transactionSuccess();
	}

	@Override
	public boolean withdrawPlayer(String playerName, double money) {
		return vault.withdrawPlayer(playerName, money).transactionSuccess();
	}

	@Override
	public boolean hasMoney(OfflinePlayer offlinePlayer, double money) {
		if (offlinePlayer.getName() == null)
			return false;
		return vault.has(offlinePlayer, money);
	}

	@Override
	public boolean hasMoney(String playerName, double money) {
		return vault.has(playerName, money);
	}

	@Override
	public String format(double money) {
		return vault.format(money);
	}

}
