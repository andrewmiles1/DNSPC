package com.burnt_toast.dungeons_n_stuff_pc.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.burnt_toast.dungeons_n_stuff.MainFrame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 12 * 60;
		config.height = 6 * 60;
		new LwjglApplication(new MainFrame(), config);
	}
}
