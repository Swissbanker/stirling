/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package stirling.fix.messages;

// FIXME: YYYYMMDD format validation is missing!
public class LocalMktDateField extends StringField {
    public LocalMktDateField(Tag<LocalMktDateField> tag) {
        this(tag, Required.YES);
    }

    protected LocalMktDateField(Tag<LocalMktDateField> tag, Required required) {
        super(tag, null, required);
    }
}
