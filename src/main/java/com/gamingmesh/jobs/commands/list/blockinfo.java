package com.gamingmesh.jobs.commands.list;

import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.gamingmesh.jobs.Jobs;
import com.gamingmesh.jobs.CMILib.CMIMaterial;
import com.gamingmesh.jobs.CMILib.VersionChecker.Version;
import com.gamingmesh.jobs.commands.Cmd;
import com.gamingmesh.jobs.commands.JobCommand;
import com.gamingmesh.jobs.utils.Util;

public class blockinfo implements Cmd {

    @Override
    @SuppressWarnings("deprecation")
    @JobCommand(1472)
    public boolean perform(Jobs plugin, CommandSender sender, String[] args) {
	if (!(sender instanceof Player)) {
	    sender.sendMessage(Jobs.getLanguage().getMessage("general.error.ingame"));
	    return false;
	}

	if (args.length != 0) {
	    Jobs.getCommandManager().sendUsage(sender, "blockinfo");
	    return true;
	}

	Player player = (Player) sender;
	Block block = Util.getTargetBlock(player, 15);
	if (block == null || CMIMaterial.isAir(block.getType()))
	    return true;

	String dataString = CMIMaterial.getBlockData(block) == 0 ? "" : "-" + CMIMaterial.getBlockData(block);

	sender.sendMessage(Jobs.getLanguage().getMessage("general.info.separator"));
	sender.sendMessage(Jobs.getLanguage().getMessage("command.blockinfo.output.name", "%blockname%", block.getType().name()));
	if (Version.isCurrentEqualOrLower(Version.v1_13_R2))
	    sender.sendMessage(Jobs.getLanguage().getMessage("command.blockinfo.output.id", "%blockid%", block.getType().getId()));
	sender.sendMessage(Jobs.getLanguage().getMessage("command.blockinfo.output.data", "%blockdata%", CMIMaterial.getBlockData(block)));
	if (Version.isCurrentEqualOrHigher(Version.v1_14_R1))
	    sender.sendMessage(Jobs.getLanguage().getMessage("command.blockinfo.output.usage", "%first%", "",
		"%second%", block.getType().name() + dataString));
	else
	    sender.sendMessage(Jobs.getLanguage().getMessage("command.blockinfo.output.usage", "%first%", block.getType().getId() + dataString,
		"%second%", block.getType().name() + dataString));
	sender.sendMessage(Jobs.getLanguage().getMessage("general.info.separator"));

	return true;
    }
}
