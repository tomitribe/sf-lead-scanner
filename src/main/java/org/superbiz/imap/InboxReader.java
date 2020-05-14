/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.superbiz.imap;

import org.apache.tomee.chatterbox.imap.api.BodyParam;
import org.apache.tomee.chatterbox.imap.api.FromParam;
import org.apache.tomee.chatterbox.imap.api.MailListener;
import org.apache.tomee.chatterbox.imap.api.Subject;
import org.apache.tomee.chatterbox.imap.api.SubjectParam;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven
public class InboxReader implements MailListener {

    private static final Logger LOGGER = Logger.getLogger(InboxReader.class.getName());

    @EJB
    private StorageService storageService;

    @Subject(".*\\[Salesforce Web .*?\\].*")
    public void captureLead(@FromParam final String from, @SubjectParam final String subject, @BodyParam final String message) {
        final String[] lines = message.split("\n");
        final Map<String, String> data = new HashMap<>();

        for (final String line : lines) {
            if (! line.contains(":")) continue;

            final int pos = line.indexOf(":");

            final String key = line.substring(0, pos).trim().toLowerCase().replaceAll("\\s+", "");
            final String value = line.substring(pos + 1).trim();

            data.put(key, value);
        }

        data.forEach((k,v) -> {
            System.out.println(String.format("%s: %s", k, v));
        });

        final Lead lead = new Lead();

        final SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy 'at' hh:mm a");
        try {
            lead.setDate(sdf.parse(data.get("date")));
        } catch (ParseException e) {
            // ignore
        }

        lead.setCompany(data.get("company"));
        lead.setEmail(data.get("email"));
        lead.setFirstname(data.get("firstname"));
        lead.setLastname(data.get("lastname"));
        lead.setPhone(data.get("phone"));

        storageService.persistLead(lead);
    }

}
