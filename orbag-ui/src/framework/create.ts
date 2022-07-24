import axios from "axios";
import { SerializableFieldGroup } from "./input";
import { ConfigurationItemReference } from "./reference";

export type CreateRequest = {
    configurationItemType:string
    parameters: SerializableFieldGroup
}

export function getCreateRequestTemplate(configurationItemType:string): Promise<CreateRequest> {
    return new Promise<CreateRequest>((resolve,reject) =>{
        axios.get<CreateRequest>("/api/create/template/"+configurationItemType).then(r=> resolve(r.data)).catch(reason=> reject(reason));
    });
}


export function createConfigurationItem(request:CreateRequest): Promise<ConfigurationItemReference> {
    return new Promise<ConfigurationItemReference>((resolve,reject) =>{
        axios.post<ConfigurationItemReference>("/api/create/execute",request).then(r=> resolve(r.data)).catch(reason=> reject(reason));
    });
}