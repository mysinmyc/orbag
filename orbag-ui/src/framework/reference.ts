import axios from "axios"

export type ConfigurationItemReference = {
    identifier: string,
    configurationItemType: string,
    displayLabel?: string
    configurationItemTypeDisplayLabel?: string
}


export function loadReference(identifier:string, configurationItemType:string): Promise<ConfigurationItemReference> {
    return new Promise<ConfigurationItemReference>((resolve,reject) =>{
            axios.get<ConfigurationItemReference>("/api/reference/"+configurationItemType+"/"+identifier).then(r=> resolve(r.data)).catch(reason=> reject(reason));
    })
}
