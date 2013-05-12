/*
 * Copyright 2012 CodeSlap
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

package com.github.jobs.utils;

import android.content.Intent;
import com.github.jobs.bean.Job;

/** @author cristian */
public class ShareHelper {
  private static final String JOB_URL = "https://jobs.github.com/positions/%s";

  public static Intent getShareIntent(Job job) {
    Intent shareIntent = new Intent(Intent.ACTION_SEND);
    shareIntent.setType("text/plain");
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, job.getTitle());
    String format = "%s (%s, %s): %s";
    String message = String.format(format, job.getTitle(), job.getCompany(), job.getLocation(),
        String.format(JOB_URL, job.getId()));
    shareIntent.putExtra(Intent.EXTRA_TEXT, message);
    return shareIntent;
  }
}
