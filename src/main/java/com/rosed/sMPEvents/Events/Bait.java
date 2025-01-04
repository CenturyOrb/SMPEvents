package com.rosed.sMPEvents.Events;

import com.rosed.sMPEvents.ConfigLocations;
import com.rosed.sMPEvents.Listeners.BaitListener;
import com.rosed.sMPEvents.Listeners.PlayerConnectionListener;
import com.rosed.sMPEvents.Utils.DSUtil;
import com.rosed.sMPEvents.Utils.SideBar.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class Bait extends EventGame {

    private HashMap<Player, Short> playerPoints = new HashMap<>();


    Bait() {
        super();
        spawn = ConfigLocations.BAIT;
        listeners.add(new PlayerConnectionListener());
        listeners.add(new BaitListener());
    }

    @Override
    protected void startScoreboards() {
        Bukkit.broadcastMessage("startScoreboards()");
        Bukkit.getOnlinePlayers().forEach(player -> {
            FastBoard board = new FastBoard(player);
            boards.put(player.getUniqueId(), board);
            board.updateTitle("       " + ChatColor.RED.toString() + ChatColor.BOLD + "BAIT" + ChatColor.RESET + "       ");
            board.updateLines(
                    "","","","","","","","","",""
            );
        });
        Bukkit.broadcastMessage("startScoreboards() finished");
    }

    @Override
    public void updateBoards() {
        Bukkit.broadcastMessage("updateBoards()");
        ArrayList<Map.Entry<Player, Short>> entries = new ArrayList<>(DSUtil.sortMapByValue(playerPoints).entrySet());
        ArrayList<String> lines = new ArrayList<>(10);
        ArrayList<String> scores = new ArrayList<>(10);

        // the lines that shows player names
        // the points that each player has
        for (int i = 0; i < 10; i++) {
            try {
                lines.add(entries.get(i).getKey().getName());
                scores.add(ChatColor.RED + entries.get(i).getValue().toString());
            } catch (IndexOutOfBoundsException e) {
                lines.add("");
                scores.add("");
            }
        }

        // the score of each player to the side
        // do this for everyone that is online
        Bukkit.getOnlinePlayers().forEach(player -> {
           boards.get(player.getUniqueId()).updateLines(lines);
           boards.get(player.getUniqueId()).updateScores(scores);
        });

        Bukkit.broadcastMessage("updateBoards() finished");
    }

    public HashMap<Player, Short> getPlayerPoints() { return playerPoints; }

}
