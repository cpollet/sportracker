/*
 * Copyright 2014 Christophe Pollet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.cpollet.sportracker.fit;

import com.garmin.fit.Decode;
import com.garmin.fit.MesgBroadcaster;
import com.garmin.fit.RecordMesgListener;
import net.cpollet.sportracker.data.Track;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Christophe Pollet
 */
public class ParserImpl implements Parser {
	@Override
	public Track parse(File file) {
		Decode decode = new Decode();
		MesgBroadcaster mesgBroadcaster = new MesgBroadcaster(decode);

		Listener listener = new Listener();
		mesgBroadcaster.addListener((RecordMesgListener) listener);

		assertFileIntegrtity(file);

		try (FileInputStream in = new FileInputStream(file)) {
			mesgBroadcaster.run(in);
			return listener.getTrack();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void assertFileIntegrtity(File file) {
		try (FileInputStream in = new FileInputStream(file)) {
			if (!Decode.checkIntegrity(in)) {
				throw new RuntimeException("FIT file integrity check failed");
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
