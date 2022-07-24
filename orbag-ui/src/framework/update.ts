import axios from "axios";
import { SerializableFieldGroup } from "./input";
import { ConfigurationItemReference } from "./reference";

export type UpdateRequest = {
    configurationItem:ConfigurationItemReference,
    parameters: SerializableFieldGroup
}

export function getUpdateRequestTemplate(configurationItemReference:ConfigurationItemReference): Promise<UpdateRequest> {
    return new Promise<UpdateRequest>((resolve,reject) =>{
        axios.get<UpdateRequest>("/api/update/template/"+configurationItemReference.configurationItemType+"/"+configurationItemReference.identifier).then(r=> resolve(r.data)).catch(reason=> reject(reason));
    });
}

export function updateConfigurationItem(request:UpdateRequest): Promise<ConfigurationItemReference> {
    return new Promise<ConfigurationItemReference>((resolve,reject) =>{
        axios.post<ConfigurationItemReference>("/api/update/execute",request).then(r=> resolve(r.data)).catch(reason=> reject(reason));
    });
}