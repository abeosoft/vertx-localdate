/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package localdatetest.domain;

import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;

/**
 * Converter for {@link localdatetest.domain.Person}.
 *
 * NOTE: This class has been automatically generated from the {@link localdatetest.domain.Person} original class using Vert.x codegen.
 */
public class PersonConverter {

  public static void fromJson(JsonObject json, Person obj) {
    if (json.getValue("name") instanceof String) {
      obj.setName((String)json.getValue("name"));
    }
    if (json.getValue("personId") instanceof Number) {
      obj.setPersonId(((Number)json.getValue("personId")).intValue());
    }
  }

  public static void toJson(Person obj, JsonObject json) {
    if (obj.getName() != null) {
      json.put("name", obj.getName());
    }
    if (obj.getPersonId() != null) {
      json.put("personId", obj.getPersonId());
    }
  }
}