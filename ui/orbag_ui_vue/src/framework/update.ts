import axios from "axios";
import { SerializableFieldGroup } from "./input";
import { ConfigurationItemReference } from "./reference";

export type UpdateRequest = {
    configurationItem:ConfigurationItemReference,
    properties: SerializableFieldGroup
}

export function getUpdateRequestTemplate(configurationItemReference:ConfigurationItemReference): Promise<UpdateRequest> {
    return new Promise<UpdateRequest>((resolve,reject) =>{
        axios.post<UpdateRequest>("/api/update/buildUpdateTemplate",configurationItemReference).then(r=> resolve(r.data)).catch(reason=> reject(reason));
    });
}

export function updateConfigurationItem(request:UpdateRequest): Promise<ConfigurationItemReference> {
    return new Promise<ConfigurationItemReference>((resolve,reject) =>{
        axios.post<ConfigurationItemReference>("/api/update/execute",request).then(r=> resolve(r.data)).catch(reason=> reject(reason));
    });
}