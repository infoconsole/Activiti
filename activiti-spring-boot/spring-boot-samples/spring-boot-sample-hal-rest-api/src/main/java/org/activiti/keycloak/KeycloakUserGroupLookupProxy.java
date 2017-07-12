/*
 * Copyright 2017 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.keycloak;

import org.activiti.engine.UserGroupLookupProxy;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("usergrouplookuproxy")
public class KeycloakUserGroupLookupProxy implements UserGroupLookupProxy {

    @Value("${keycloak.auth-server-url}")
    private String authServer;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String resource;

    @Value("${keycloakadminuser}")
    private String adminUser;

    @Value("${keycloakadminuser}")
    private String adminPassword;

    @Autowired
    private KeycloakSpringBootProperties keycloakSpringBootProperties;


    public List<String> getGroupsForCandidateUser(String candidateUser){

        //TODO: should resource actually be admin-cli ?
        Keycloak keycloak = Keycloak.getInstance(authServer,realm,adminUser,adminPassword,resource);
        List<GroupRepresentation> groupRepresentations = keycloak.realms().realm(realm).users().get(candidateUser).groups();

        List<String> groups = null;
        if(groupRepresentations!=null && groupRepresentations.size()>0){
            groups = new ArrayList<String>();
            for(GroupRepresentation groupRepresentation:groupRepresentations){
                groups.add(groupRepresentation.getName());
            }
        }

        return groups;
    }
}
