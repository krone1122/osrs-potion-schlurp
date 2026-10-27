package com.krone;

import java.io.IOException;
import javax.inject.Inject;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.SoundEffectPlayed;
import net.runelite.client.audio.AudioPlayer;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Schluuuurp"
)
public class SchluuuurpPlugin extends Plugin
{
	/**
	 * OSRS sound effect ID used when drinking a potion.
	 */
	private static final int POTION_DRINK_SOUND_ID = 2401;
	private static final String POTION_SOUND_RESOURCE = "/Schlurp.wav";

	@Inject
	private AudioPlayer audioPlayer;

	@Subscribe
	public void onSoundEffectPlayed(SoundEffectPlayed soundEffectPlayed)
	{
		if (soundEffectPlayed.isConsumed()
			|| soundEffectPlayed.getSoundId() != POTION_DRINK_SOUND_ID)
		{
			return;
		}

		try
		{
			audioPlayer.play(SchluuuurpPlugin.class, POTION_SOUND_RESOURCE, 0.0f);
			soundEffectPlayed.consume();
		}
		catch (IOException | UnsupportedAudioFileException | LineUnavailableException e)
		{
			// Leave the original sound unconsumed so a missing or invalid custom
			// resource does not make potion drinking silent.
			log.warn("Unable to replace the potion drinking sound", e);
		}
	}
}
