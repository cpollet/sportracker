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

package net.cpollet.sportacker.aggregator;

import net.cpollet.sportracker.quantities.Quantity;
import net.cpollet.sportracker.units.Length;
import net.cpollet.sportracker.units.Speed;

/**
 * @author Christophe Pollet
 */
public interface TrackPointAggregator {
	Quantity<Speed> getAverageSpeed();

	Quantity<Speed> getMaxSpeed();

	Quantity<Speed> getMinSpeed();

	Quantity<Length> getAverageAltitude();

	Quantity<Length> getMinAltitude();

	Quantity<Length> getMaxAltitude();
}
