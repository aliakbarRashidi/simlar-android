/**
 * Copyright (C) 2013 - 2014 The Simlar Authors.
 *
 * This file is part of Simlar. (http://www.simlar.org)
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.simlar;

import android.util.Log;

public final class Lg
{
	private static final int LOG_LEVEL_NORMAL = Log.WARN;
	private static final int LOG_LEVEL_DEBUG = Log.INFO;
	private static volatile int mLevel = LOG_LEVEL_NORMAL;

	private static void println(final int priority, final String tag, final Throwable exception, final Object... messageParts)
	{
		if (priority < mLevel) {
			return;
		}

		final StringBuilder message = new StringBuilder();
		if (messageParts != null) {
			for (final Object part : messageParts) {
				message.append(part);
			}
		}

		if (exception != null) {
			message.append('\n')
					.append(exception.getClass().getName()).append(": ").append(exception.getMessage()).append('\n')
					.append(Log.getStackTraceString(exception));
		}

		Log.println(priority, tag, message.toString());
	}

	public static void setDebugMode(final boolean enabled)
	{
		mLevel = enabled ? LOG_LEVEL_DEBUG : LOG_LEVEL_NORMAL;
	}

	public static boolean isDebugModeEnabled()
	{
		return mLevel < LOG_LEVEL_NORMAL;
	}

	public static void v(final String tag, final Object... messageParts)
	{
		println(Log.VERBOSE, tag, null, messageParts);
	}

	public static void d(final String tag, final Object... messageParts)
	{
		println(Log.DEBUG, tag, null, messageParts);
	}

	public static void i(final String tag, final Object... messageParts)
	{
		println(Log.INFO, tag, null, messageParts);
	}

	public static void w(final String tag, final Object... messageParts)
	{
		println(Log.WARN, tag, null, messageParts);
	}

	public static void e(final String tag, final Object... messageParts)
	{
		println(Log.ERROR, tag, null, messageParts);
	}

	public static void ex(final String tag, final Throwable exception, final Object... messageParts)
	{
		println(Log.ERROR, tag, exception, messageParts);
	}
}
