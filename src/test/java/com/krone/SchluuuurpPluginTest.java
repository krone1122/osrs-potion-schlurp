package com.krone;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SchluuuurpPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SchluuuurpPlugin.class);
		RuneLite.main(args);
	}
}
